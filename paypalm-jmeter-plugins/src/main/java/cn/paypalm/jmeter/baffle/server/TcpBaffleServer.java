package cn.paypalm.jmeter.baffle.server;

import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.jmeter.gui.Stoppable;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.jorphan.util.JOrphanUtils;
import org.apache.log.Logger;

public class TcpBaffleServer extends Thread implements Stoppable {
	private static final Logger log = LoggingManager.getLoggerForClass();

	private static final int ACCEPT_TIMEOUT = 1000;

	private static final long KEEP_ALIVE_TIME = 10;

	private final int daemonPort;

	private volatile boolean running;

	private volatile Exception except;

	private int maxThreadPoolSize;

	private int maxQueueSize;

	private String codition;

	private String flag;
	private String timeout;

	public String getCodition() {
		return codition;
	}

	public void setCodition(String codition) {
		this.codition = codition;
	}

	public TcpBaffleServer(int port) {
		this(port, TcpBaffleControl.DEFAULT_MAX_POOL_SIZE,
				TcpBaffleControl.DEFAULT_MAX_QUEUE_SIZE);
	}

	public TcpBaffleServer(int port, int maxThreadPoolSize, int maxQueueSize) {
		super("HttpMirrorServer");
		this.daemonPort = port;
		this.maxThreadPoolSize = maxThreadPoolSize;
		this.maxQueueSize = maxQueueSize;
	}

	public TcpBaffleServer(int port, int maxThreadPoolSize, int maxQueueSize,
			String condition) {
		super("HttpMirrorServer");
		this.daemonPort = port;
		this.maxThreadPoolSize = maxThreadPoolSize;
		this.maxQueueSize = maxQueueSize;
		this.codition = condition;
	}

	public TcpBaffleServer(int port, int maxThreadPoolSize, int maxQueueSize,
			String condition, String flag, String timeout) {
		super("HttpMirrorServer");
		this.daemonPort = port;
		this.maxThreadPoolSize = maxThreadPoolSize;
		this.maxQueueSize = maxQueueSize;
		this.codition = condition;
		this.flag = flag;
		this.timeout = timeout;
	}

	@Override
	public void run() {
		except = null;
		running = true;
		ServerSocket mainSocket = null;
		ThreadPoolExecutor threadPoolExecutor = null;
		if (maxThreadPoolSize > 0) {
			final ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(
					maxQueueSize);
			threadPoolExecutor = new ThreadPoolExecutor(maxThreadPoolSize / 2,
					maxThreadPoolSize, KEEP_ALIVE_TIME, TimeUnit.SECONDS, queue);
			threadPoolExecutor
					.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
		}
		try {
			log.info("Creating HttpMirror ... on port " + daemonPort);
			mainSocket = new ServerSocket(daemonPort);
			mainSocket.setSoTimeout(ACCEPT_TIMEOUT);
			log.info("HttpMirror up and running!");
			while (running) {
				try {

					Socket clientSocket = mainSocket.accept();
					if (running) {

						if (threadPoolExecutor != null) {

							threadPoolExecutor.execute(new TcpBaffleThread(
									clientSocket, this.getCodition(), flag,
									timeout));
						} else {

							Thread thd = new Thread(new TcpBaffleThread(
									clientSocket, this.getCodition(), flag,
									timeout));

							log.debug("Starting new Mirror thread");
							thd.start();
						}
					} else {
						log.warn("Server not running");
						JOrphanUtils.closeQuietly(clientSocket);
					}
				} catch (InterruptedIOException e) {
					log.debug(e.getLocalizedMessage());
				}
			}
			log.info("HttpMirror Server stopped");
		} catch (Exception e) {
			except = e;
			log.warn("HttpMirror Server stopped", e);
		} finally {
			if (threadPoolExecutor != null) {
				threadPoolExecutor.shutdownNow();
			}
			JOrphanUtils.closeQuietly(mainSocket);
		}
	}

	public void stopServer() {
		running = false;
	}

	public Exception getException() {
		return except;
	}

	public static void main(String args[]) {
		int port = TcpBaffleControl.DEFAULT_PORT;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		LoggingManager.setPriority("INFO");
		LoggingManager.setLoggingLevels(System.getProperties());
		TcpBaffleServer serv = new TcpBaffleServer(port);
		serv.start();
	}
}
