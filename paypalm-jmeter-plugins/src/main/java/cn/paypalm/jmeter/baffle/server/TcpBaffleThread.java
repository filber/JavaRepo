package cn.paypalm.jmeter.baffle.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.JMeterEngine;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.gui.tree.JMeterTreeNode;
import org.apache.jmeter.samplers.SampleListener;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.BeanShellInterpreter;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.ListedHashTree;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.jorphan.util.JOrphanUtils;
import org.apache.log.Logger;

import cn.paypalm.jmeter.baffle.handler.BaffleResultListenerHandler;
import cn.paypalm.jmeter.baffle.handler.BeanShellDeal;
import cn.paypalm.jmeter.baffle.handler.JmxHandler;
import cn.paypalm.jmeter.baffle.vo.TCPValues;

/**
 * 处理业务逻辑的主要代码
 * 
 * */
public class TcpBaffleThread implements Runnable {
	private static final Logger log = LoggingManager.getLoggerForClass();
	private final Socket clientSocket;

	private String timeout;
	private String jmxPath;
	private JMeterTreeNode selected;

	public JMeterTreeNode getSelected() {
		return selected;
	}

	public void setSelected(JMeterTreeNode selected) {
		this.selected = selected;
	}

	public String getJmxPath() {
		return jmxPath;
	}

	public void setJmxPath(String jmxPath) {
		this.jmxPath = jmxPath;
	}

	public TcpBaffleThread(Socket _clientSocket) {
		this.clientSocket = _clientSocket;
	}

	public TcpBaffleThread(Socket _clientSocket, String jmxPath, String timeout) {
		this.clientSocket = _clientSocket;
		this.jmxPath = jmxPath;

		this.timeout = timeout;
	}

	public void run() {
		log.debug("Starting thread");
		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try {
			if (timeout != null && timeout.length() > 0)
				clientSocket.setSoTimeout(Integer.parseInt(timeout));
			in = new BufferedInputStream(clientSocket.getInputStream());
			out = new BufferedOutputStream(clientSocket.getOutputStream());
			log.info("客户端已连接,地址信息为:" + clientSocket.getInetAddress());
			System.out.println("TCPValues.script:" + TCPValues.inScript);
			BeanShellInterpreter bsi = new BeanShellInterpreter();
			bsi.set("socket", clientSocket);
			bsi.set("in", in);
			bsi.set("out", out);
			BeanShellDeal bsd = new BeanShellDeal();
			bsd.setBshInterpreter(bsi);
			JmxHandler jmxHandler = new JmxHandler(jmxPath);
			while (true) {
				Object o = bsd.doDeal(TCPValues.inScript, "");
				String result = null;
				byte[] bytes = null;
				String response = null;
				if (o != null) {
					bytes = (byte[]) o;
					result = new String(bytes);
				} else {
					log.info("clientSocket close................");
					break;
				}
				byte[] responseBytes = null;
				if (jmxPath == null || jmxPath.length() < 1) {

					String str = new String((byte[]) o);
					if (str.indexOf("\r\n") != -1) {
						str = str.replaceAll("\r\n", "#");
					} else {
						str = str.replaceAll("\n", "#");
					}
					JMeterEngine engine = new StandardJMeterEngine();
					TestPlan testPlan = new TestPlan();

					BaffleResultListenerHandler listener = new BaffleResultListenerHandler(
							bytes, null);
					testPlan.addParameter("message", str);
					engine.configure(getReplacementSubTree(selected, testPlan,
							listener));

					engine.runTest();
					byte[] results = listener.getResultByBytes();
					response = listener.getResultByString();

					System.out.println("listener result:" + results);
					responseBytes = results;

				} else {
					jmxHandler.setMessage(result);
					jmxHandler.setBytes(bytes);
					jmxHandler.start();
					responseBytes = jmxHandler.getResponseBytes();
				}
				if (responseBytes == null) {
					log.info("clientSocket close................");
					break;
				}
				log.info("resonseBytes=" + Arrays.toString(responseBytes)
						+ ";new String()=" + new String(responseBytes));
				Object o1 = bsd.doDeal(TCPValues.outScript, responseBytes);
				if (o1 == null) {
					log.info("clientSocket close................");
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("", e);
		} finally {
			JOrphanUtils.closeQuietly(clientSocket);
		}
		log.debug("End of Thread");
	}

	private static HashTree getReplacementSubTree(JMeterTreeNode selectedNode,
			TestPlan testPlan, SampleListener listener) {
		HashTree tree = new ListedHashTree();
		if (selectedNode == null)
			return tree;
		JMeterTreeNode nodeToReplace = selectedNode;
		if (!nodeToReplace.isEnabled()) {
			nodeToReplace = cloneTreeNode(selectedNode);
			nodeToReplace.setEnabled(true);
		}

		HashTree testPlanHashTree = tree.add(testPlan);

		testPlanHashTree.add(listener);

		ThreadGroup threadGroup = new ThreadGroup();
		threadGroup.setNumThreads(1);

		LoopController loopController = new LoopController();
		loopController.setLoops(1);
		threadGroup.setSamplerController(loopController);

		HashTree threadGroupHashTree = testPlanHashTree.add(threadGroup);
		HashTree subtree = threadGroupHashTree.add(((TestElement) nodeToReplace
				.getUserObject()).clone());
		createSubTree(subtree, nodeToReplace);
		return tree;
	}

	private static void createSubTree(HashTree tree, JMeterTreeNode node) {
		Enumeration<JMeterTreeNode> e = node.children();
		while (e.hasMoreElements()) {
			JMeterTreeNode subNode = e.nextElement();
			TestElement testElement = (TestElement) subNode.getUserObject();
			if (testElement.isEnabled()) {
				HashTree subTree = tree.add(testElement.clone());
				createSubTree(subTree, subNode);
			}
		}
	}

	private static JMeterTreeNode cloneTreeNode(JMeterTreeNode node) {
		JMeterTreeNode treeNode = (JMeterTreeNode) node.clone();
		treeNode.setUserObject(((TestElement) node.getUserObject()).clone());
		cloneChildren(treeNode, node);
		return treeNode;
	}

	private static void cloneChildren(JMeterTreeNode to, JMeterTreeNode from) {
		Enumeration<JMeterTreeNode> enumr = from.children();
		while (enumr.hasMoreElements()) {
			JMeterTreeNode child = enumr.nextElement();
			JMeterTreeNode childClone = (JMeterTreeNode) child.clone();
			childClone.setUserObject(((TestElement) child.getUserObject())
					.clone());
			to.add(childClone);
			cloneChildren((JMeterTreeNode) to.getLastChild(), child);
		}
	}

}
