package cn.paypalm.jmeter.baffle.server;

import org.apache.jmeter.control.GenericController;
import org.apache.jmeter.control.NextIsNullException;
import org.apache.jmeter.control.ReplaceableController;
import org.apache.jmeter.gui.GuiPackage;
import org.apache.jmeter.gui.tree.JMeterTreeNode;
import org.apache.jmeter.samplers.Sampler;

import org.apache.jmeter.testelement.property.IntegerProperty;

import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.logging.LoggingManager;

import org.apache.log.Logger;

public class TcpBaffleControl extends GenericController implements
		ReplaceableController {

	private static final long serialVersionUID = 233L;

	private transient TcpBaffleServer server;

	public String flag;
	public String timeout;

	static final int DEFAULT_PORT = 8081;

	private static final Logger log = LoggingManager.getLoggerForClass();

	public static final String DEFAULT_PORT_S = Integer.toString(DEFAULT_PORT);
	public static final String PORT = "TCPMirrorControlGui.port";

	public static final String MAX_POOL_SIZE = "HttpMirrorControlGui.maxPoolSize";

	public static final String MAX_QUEUE_SIZE = "HttpMirrorControlGui.maxQueueSize";

	public static final String TIMEOUT = "TCPMirrorControlGui.timeout";
	public static final String FLAG = "TCPMirrorControlGui.flag";
	public static final String INSCRIPT = "TCPMirrorControlGui.inScript";
	public static final String OUTSCRIPT = "TCPMirrorControlGui.outScript";
	public static final String FILEPATH = "TCPMirrorControlGui.filePath";

	public static final int DEFAULT_MAX_POOL_SIZE = 0;

	public static final int DEFAULT_MAX_QUEUE_SIZE = 25;

	private static final String INCLUDE_PATH = "IncludeController.includepath";

	public void setInScript(String inScript) {
		setProperty(INSCRIPT, inScript);
	}

	public String getInScript() {
		return getPropertyAsString(INSCRIPT);
	}

	public void setOutScript(String outScript) {
		setProperty(OUTSCRIPT, outScript);
	}

	public String getOutScript() {
		return getPropertyAsString(OUTSCRIPT);
	}

	public void setFilePath(String filePath) {
		setProperty(FILEPATH, filePath);
	}

	public String getFilePath() {
		return getPropertyAsString(FILEPATH);
	}

	public void setFlag(String flag) {
		setProperty(FLAG, flag);
	}

	public String getFlag() {
		return getPropertyAsString(FLAG);
	}

	public TcpBaffleControl() {
		initPort(DEFAULT_PORT);
	}

	public TcpBaffleControl(int port) {
		initPort(port);
	}

	private void initPort(int port) {
		setProperty(new IntegerProperty(PORT, port));
	}

	public void setPort(int port) {
		initPort(port);
	}

	public void setTimeout(String timeout) {
		setProperty(TIMEOUT, timeout);
	}

	public String getTimeout() {
		return getPropertyAsString(TIMEOUT);
	}

	public String getPortString() {
		return getPropertyAsString(PORT);
	}

	public void setPort(String port) {
		setProperty(PORT, port);
	}

	public int getPort() {
		return getPropertyAsInt(PORT);
	}

	/**
	 * @return Max Thread Pool size
	 */
	public String getMaxPoolSizeAsString() {
		return getPropertyAsString(MAX_POOL_SIZE);
	}

	/**
	 * @return Max Thread Pool size
	 */
	private int getMaxPoolSize() {
		return getPropertyAsInt(MAX_POOL_SIZE, DEFAULT_MAX_POOL_SIZE);
	}

	/**
	 * @param maxPoolSize
	 *            Max Thread Pool size
	 */
	public void setMaxPoolSize(String maxPoolSize) {
		setProperty(MAX_POOL_SIZE, maxPoolSize);
	}

	/**
	 * @return Max Queue size
	 */
	public String getMaxQueueSizeAsString() {
		return getPropertyAsString(MAX_QUEUE_SIZE);
	}

	/**
	 * @return Max Queue size
	 */
	private int getMaxQueueSize() {
		return getPropertyAsInt(MAX_QUEUE_SIZE, DEFAULT_MAX_QUEUE_SIZE);
	}

	/**
	 * @param maxQueueSize
	 *            Max Queue size
	 */
	public void setMaxQueueSize(String maxQueueSize) {
		setProperty(MAX_QUEUE_SIZE, maxQueueSize);
	}

	public int getDefaultPort() {
		return DEFAULT_PORT;
	}

	public void startHttpMirror() {

		server = new TcpBaffleServer(getPort(), getMaxPoolSize(),
				getMaxQueueSize(), getFilePath(), flag, timeout);
		server.start();
		GuiPackage instance = GuiPackage.getInstance();
		if (instance != null) {
			instance.register(server);
		}
	}

	public void stopHttpMirror() {
		if (server != null) {
			server.stopServer();
			GuiPackage instance = GuiPackage.getInstance();
			if (instance != null) {
				instance.unregister(server);
			}
			try {
				server.join(1000);
			} catch (InterruptedException e) {
			}
			server = null;
		}
	}

	public void setIncludePath(String jmxfile) {
		this.setProperty(INCLUDE_PATH, jmxfile);
	}

	public String getIncludePath() {
		return this.getPropertyAsString(INCLUDE_PATH);
	}

	@Override
	public boolean canRemove() {
		return null == server;
	}

	public boolean isServerAlive() {
		return server != null && server.isAlive();
	}

	public HashTree getReplacementSubTree() {

		return null;
	}

	public void resolveReplacementSubTree(JMeterTreeNode context) {

	}

	public Sampler next() {

		boolean result = true;

		if (result) {
			System.out.println("if Controller! run");
			return super.next();
		}

		try {
			reInitializeSubController();
			return nextIsNull();
		} catch (NextIsNullException e1) {
			return null;
		}
	}

	public void triggerEndOfLoop() {
		reInitializeSubController();
		super.triggerEndOfLoop();
	}

	@Override
	public boolean isDone() {
		// boolean result = true;
		// try {
		// result = !evaluateCondition();
		// } catch (Exception e) {
		// logger.error(e.getMessage(), e);
		// }
		// setDone(true);
		// return result;
		// setDone(false);
		return false;
	}

}
