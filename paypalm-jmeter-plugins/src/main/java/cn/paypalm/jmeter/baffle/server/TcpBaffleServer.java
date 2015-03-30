package cn.paypalm.jmeter.baffle.server;

import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.jmeter.gui.Stoppable;
import org.apache.jmeter.gui.tree.JMeterTreeNode;
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

	private String jmxPath;

	private String timeout;
	private JMeterTreeNode selectedNode;

	public JMeterTreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(JMeterTreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public String getJmxPath() {
		return jmxPath;
	}

	public void setJmxPath(String jmxPath) {
		this.jmxPath = jmxPath;
	}

	public TcpBaffleServer(int port) {
		this(port, TcpBaffleControl.DEFAULT_MAX_POOL_SIZE,
				TcpBaffleControl.DEFAULT_MAX_QUEUE_SIZE);
	}

	public TcpBaffleServer(int port, int maxThreadPoolSize, int maxQueueSize) {
		super("TCPMirrorServer");
		this.daemonPort = port;
		this.maxThreadPoolSize = maxThreadPoolSize;
		this.maxQueueSize = maxQueueSize;
	}

	public TcpBaffleServer(int port, int maxThreadPoolSize, int maxQueueSize,
                           String jmxPath) {
		super("TCPMirrorServer");
		this.daemonPort = port;
		this.maxThreadPoolSize = maxThreadPoolSize;
		this.maxQueueSize = maxQueueSize;
		this.jmxPath = jmxPath;
	}

	public TcpBaffleServer(int port, int maxThreadPoolSize, int maxQueueSize,
                           String jmxPath, String timeout) {
		super("TCPMirrorServer");
		this.daemonPort = port;
		this.maxThreadPoolSize = maxThreadPoolSize;
		this.maxQueueSize = maxQueueSize;
		this.jmxPath = jmxPath;
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
			log.info("Creating TCPMirror ... on port " + daemonPort);
			mainSocket = new ServerSocket(daemonPort);
			mainSocket.setSoTimeout(ACCEPT_TIMEOUT);
			log.info("TCPMirror up and running!");
			while (running) {
				try {
					int i = 0;
					Socket clientSocket = mainSocket.accept();

					if (running) {
						TcpBaffleThread tcpBaffleThread = new TcpBaffleThread(
								clientSocket, this.getJmxPath(), timeout);
						tcpBaffleThread.setSelected(selectedNode);
						if (threadPoolExecutor != null) {

							threadPoolExecutor.execute(tcpBaffleThread);
						} else {

							Thread thd = new Thread(tcpBaffleThread);

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
			log.info("TCPMirror Server stopped");
		} catch (Exception e) {
			except = e;
			log.warn("TCPMirror Server stopped", e);
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
