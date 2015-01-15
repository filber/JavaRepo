package cn.paypalm.jmeter.baffle.server;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.JMeterEngine;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.gui.tree.JMeterTreeNode;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.ListedHashTree;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.jorphan.util.JOrphanUtils;
import org.apache.log.Logger;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

import cn.paypalm.jmeter.baffle.samplers.BaffleResultListener;

/**
 * Thread to handle one client request. Gets the request from the client and
 * sends the response back to the client.
 */
public class HttpBaffleThread implements Runnable {
	private static final Logger log = LoggingManager.getLoggerForClass();

	private static final String ISO_8859_1 = "ISO-8859-1"; //$NON-NLS-1$
	private static final byte[] CRLF = { 0x0d, 0x0a };

	/** Socket to client. */
	private final Socket clientSocket;

	private JMeterTreeNode selectedNode;
	
	public HttpBaffleThread(Socket _clientSocket,JMeterTreeNode selectedNode) {
		this.clientSocket = _clientSocket;
		this.selectedNode = selectedNode;
	}

	/**
	 * Main processing method for the HttpMirror object
	 */
	public void run() {
		log.debug("Starting thread");
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		
		try {
			in = new BufferedInputStream(clientSocket.getInputStream());
			out = new BufferedOutputStream(clientSocket.getOutputStream());

			TestPlan testPlan = new TestPlan();
			int contentLength = -1;
			byte[] buffer = new byte[1024];
			StringBuilder headers = new StringBuilder();
			String headerString = null;
			int length = 0;
			int positionOfBody = 0;
			while (positionOfBody <= 0 && ((length = in.read(buffer)) != -1)) {
				headers.append(new String(buffer, 0, length, ISO_8859_1));
				positionOfBody = getPositionOfBody(headers.toString());
			}
			headerString = headers.toString();
			String requestFullPath = getRequestFullPath(headerString);

			String contentLengthHeaderValue = getRequestHeaderValue(
					headerString, "Content-Length");
			String contentBody = null;
			if (contentLengthHeaderValue != null) {
				contentLength = Integer.parseInt(contentLengthHeaderValue);
				contentBody = headerString.substring(headerString.length()
						- contentLength);
				testPlan.addParameter("HttpBaffleContentBody", contentBody);//Prop-报文体
			}
			
			String requestPath = null;
			if (headerString.indexOf('?')!=-1) {
				Map<String, String> params = new HashMap<String,String>();
				requestPath = getRequestPath(headerString, params);
				for (String key : params.keySet()) {
					testPlan.addParameter(key, params.get(key));//Prop-参数列表
				}
			} else {
				requestPath = getRequestPath(headerString);
			}
            testPlan.addParameter("HttpBaffleRequestPath", requestPath);//Prop-请求路径

	    	JMeterEngine engine = new StandardJMeterEngine();
            CyclicBarrier barrier = new CyclicBarrier(2);
	    	BaffleResultListener listener = new BaffleResultListener(headerString,out,barrier);
	    	engine.configure(getReplacementSubTree(selectedNode,testPlan,listener));
			engine.runTest();

            barrier.await(5, TimeUnit.SECONDS);//等待监听器对消息进行处理.
		} catch (Exception e) {
			log.error("", e);
		} finally {
			JOrphanUtils.closeQuietly(out);
			JOrphanUtils.closeQuietly(in);
			JOrphanUtils.closeQuietly(clientSocket);
		}
		log.debug("End of Thread");
	}



	private static HashTree  getReplacementSubTree(JMeterTreeNode selectedNode,TestPlan testPlan,BaffleResultListener listener) {
		HashTree tree = new ListedHashTree();
		if (selectedNode==null)return tree;
		
        // Use a local variable to avoid replacing reference by modified clone (see Bug 54950)
        JMeterTreeNode nodeToReplace = selectedNode;
        // We clone to avoid enabling existing node
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
        HashTree subtree = threadGroupHashTree.add(((TestElement)nodeToReplace.getUserObject()).clone());
        createSubTree(subtree, nodeToReplace);
        return tree;
	}
	
	private static void createSubTree(HashTree tree, JMeterTreeNode node) {
        Enumeration<JMeterTreeNode> e = node.children();
        while (e.hasMoreElements()) {
            JMeterTreeNode subNode = e.nextElement();
            TestElement testElement = (TestElement)subNode.getUserObject();
            if (testElement.isEnabled()){
                HashTree subTree= tree.add(testElement.clone());
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
            childClone.setUserObject(((TestElement) child.getUserObject()).clone());
            to.add(childClone);
            cloneChildren((JMeterTreeNode) to.getLastChild(), child);
        }
    }
	
	private static String getRequestPath(String headerString) {
		Perl5Matcher localMatcher = JMeterUtils.getMatcher();
		String expression = "^(POST|GET) " + "/(.*)" + " HTTP/";
		Pattern pattern = JMeterUtils.getPattern(expression,
				Perl5Compiler.READ_ONLY_MASK
						| Perl5Compiler.CASE_INSENSITIVE_MASK
						| Perl5Compiler.MULTILINE_MASK);
		if (localMatcher.contains(headerString, pattern)) {
			return localMatcher.getMatch().group(2);
		} else {
			return null;
		}
	}
	
	private static String getRequestPath(String headerString,Map<String, String> params) {
		Perl5Matcher localMatcher = JMeterUtils.getMatcher();
		String expression = "^(POST|GET) " + "/(.*)(\\?)(.*)" + " HTTP/";
		Pattern pattern = JMeterUtils.getPattern(expression,
				Perl5Compiler.READ_ONLY_MASK
						| Perl5Compiler.CASE_INSENSITIVE_MASK
						| Perl5Compiler.MULTILINE_MASK);
		if (localMatcher.contains(headerString, pattern)) {
			params.putAll(splitQuery(localMatcher.getMatch().group(4)));
			return localMatcher.getMatch().group(2);
		} else {
			return null;
		}
	}
	
	private static String getRequestFullPath(String headerString) {
		Perl5Matcher localMatcher = JMeterUtils.getMatcher();
		String expression = "^(POST|GET) " + "(.*)" + " HTTP/";
		Pattern pattern = JMeterUtils.getPattern(expression,
				Perl5Compiler.READ_ONLY_MASK
						| Perl5Compiler.CASE_INSENSITIVE_MASK
						| Perl5Compiler.MULTILINE_MASK);
		if (localMatcher.contains(headerString, pattern)) {
			return localMatcher.getMatch().group(2);
		} else {
			return null;
		}
	}
	private static String getRequestHeaderValue(String requestHeaders,
			String headerName) {
		Perl5Matcher localMatcher = JMeterUtils.getMatcher();
		// We use multi-line mask so can prefix the line with ^
		String expression = "^" + headerName + ":\\s+([^\\r\\n]+)"; // $NON-NLS-1$
																	// $NON-NLS-2$
		Pattern pattern = JMeterUtils.getPattern(expression,
				Perl5Compiler.READ_ONLY_MASK
						| Perl5Compiler.CASE_INSENSITIVE_MASK
						| Perl5Compiler.MULTILINE_MASK);
		if (localMatcher.contains(requestHeaders, pattern)) {
			// The value is in the first group, group 0 is the whole match
			// System.out.println("Found:'"+localMatcher.getMatch().group(1)+"'");
			// System.out.println("in: '"+localMatcher.getMatch().group(0)+"'");
			return localMatcher.getMatch().group(1);
		} else {
			return null;
		}
	}

	private static int getPositionOfBody(String stringToCheck) {
		Perl5Matcher localMatcher = JMeterUtils.getMatcher();
		// The headers and body are divided by a blank line (the \r is to allow
		// for the CR before LF)
		String regularExpression = "^\\r$"; // $NON-NLS-1$
		Pattern pattern = JMeterUtils.getPattern(regularExpression,
				Perl5Compiler.READ_ONLY_MASK
						| Perl5Compiler.CASE_INSENSITIVE_MASK
						| Perl5Compiler.MULTILINE_MASK);

		PatternMatcherInput input = new PatternMatcherInput(stringToCheck);
		if (localMatcher.contains(input, pattern)) {
			MatchResult match = localMatcher.getMatch();
			return match.beginOffset(0);
		}
		// No divider was found
		return -1;
	}
	
	private static Map<String, String> splitQuery(String query){
	    Map<String, String> query_pairs = new LinkedHashMap<String, String>();
	    if (query==null || query.isEmpty()) return query_pairs;
	    try {
	    	String[] pairs = query.split("&");
		    for (String pair : pairs) {
		        int idx = pair.indexOf("=");
				query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
		    }	
		} catch (Exception e) {
			log.error("", e);
		}
	    
	    return query_pairs;
	}
}
