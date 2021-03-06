package cn.paypalm.jmeter.baffle.server;

import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.jmeter.engine.JMeterEngine;
import org.apache.jmeter.gui.Stoppable;
import org.apache.jmeter.gui.tree.JMeterTreeNode;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.jorphan.util.JOrphanUtils;
import org.apache.log.Logger;

import cn.paypalm.jmeter.baffle.samplers.BaffleResultListener;

/**
 * Server daemon thread.
 * Creates main socket and listens on it.
 * For each client request, creates a thread to handle the request.
 *
 */
public class HttpBaffleServer extends Thread implements Stoppable {
    private static final Logger log = LoggingManager.getLoggerForClass();

    /**
     * The time (in milliseconds) to wait when accepting a client connection.
     * The accept will be retried until the Daemon is told to stop. So this
     * interval is the longest time that the Daemon will have to wait after
     * being told to stop.
     */
    private static final int ACCEPT_TIMEOUT = 1000;

    private static final long KEEP_ALIVE_TIME = 10;

    /** The port to listen on. */
    private final int daemonPort;

    /** True if the Daemon is currently running. */
    private volatile boolean running;

    // Saves the error if one occurs
    private volatile Exception except;

    /**
     * Max Executor Pool size
     */
    private int maxThreadPoolSize;

    /**
     * Max Queue size
     */
    private int maxQueueSize;

	private JMeterTreeNode selectedNode;

    
    /**
     * Create a new Daemon with the specified port and target.
     *
     * @param port
     *            the port to listen on.
     */
    public HttpBaffleServer(int port,JMeterEngine engine,JMeterTreeNode selectedNode) {
       this(port, HttpBaffleControl.DEFAULT_MAX_POOL_SIZE, HttpBaffleControl.DEFAULT_MAX_QUEUE_SIZE,selectedNode);
    }
    
    /**
     * Create a new Daemon with the specified port and target.
     *
     * @param port
     *            the port to listen on.
     * @param maxThreadPoolSize Max Thread pool size
     * @param maxQueueSize Max Queue size
     * @param resultListener 
     */
    public HttpBaffleServer(int port, int maxThreadPoolSize, int maxQueueSize,JMeterTreeNode selectedNode) {
        super("HttpBaffleServer");
        this.daemonPort = port;
        this.maxThreadPoolSize = maxThreadPoolSize;
        this.maxQueueSize = maxQueueSize;
        this.selectedNode = selectedNode;
    }

    /**
     * Listen on the daemon port and handle incoming requests. This method will
     * not exit until {@link #stopServer()} is called or an error occurs.
     */
    @Override
    public void run() {
        except = null;
        running = true;
        ServerSocket mainSocket = null;
        ThreadPoolExecutor threadPoolExecutor = null;
        if(maxThreadPoolSize>0) {
            final ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(
                    maxQueueSize);
            threadPoolExecutor = new ThreadPoolExecutor(
                    maxThreadPoolSize/2, 
                    maxThreadPoolSize, KEEP_ALIVE_TIME, TimeUnit.SECONDS, queue);
            threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        }
        try {
            log.info("Creating HttpMirror ... on port " + daemonPort);
            mainSocket = new ServerSocket(daemonPort);
            mainSocket.setSoTimeout(ACCEPT_TIMEOUT);
            log.info("HttpMirror up and running!");
            while (running) {
                try {
                    // Listen on main socket
                    Socket clientSocket = mainSocket.accept();
                    if (running) {
                        // Pass request to new thread
                        if(threadPoolExecutor != null) {
                            threadPoolExecutor.execute(new HttpBaffleThread(clientSocket,selectedNode));
                        } else {
                            Thread thd = new Thread(new HttpBaffleThread(clientSocket,selectedNode));
                            log.debug("Starting new Mirror thread");
                            thd.start();
                        }
                    } else {
                        log.warn("Server not running");
                        JOrphanUtils.closeQuietly(clientSocket);
                    }
                } catch (InterruptedIOException e) {
                    // Timeout occurred. Ignore, and keep looping until we're
                    // told to stop running.
                }
            }
            log.info("HttpMirror Server stopped");
        } catch (Exception e) {
            except = e;
            log.warn("HttpMirror Server stopped", e);
        } finally {
            if(threadPoolExecutor != null) {
                threadPoolExecutor.shutdownNow();
            }
            JOrphanUtils.closeQuietly(mainSocket);
        }
    }

    public void stopServer() {
        running = false;
    }

    public Exception getException(){
        return except;
    }

}
