/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package cn.paypalm.jmeter.baffle.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.TestFragmentController;
import org.apache.jmeter.engine.JMeterEngine;
import org.apache.jmeter.engine.JMeterEngineException;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.samplers.SampleListener;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.services.FileServer;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.jorphan.util.JOrphanUtils;
import org.apache.log.Logger;

/**
 * 读取保存的测试用例,并加载为一个TestPlan
 * 
 * **/
public class JmxHandler {
	private static final Logger log = LoggingManager.getLoggerForClass();
	private String fileName;
	private String response;
	private String message;
	private String result;
	private OutputStream out;
	private byte[] bytes;
	private byte[] responseBytes;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getResponseBytes() {
		return responseBytes;
	}

	public void setResponseBytes(byte[] responseBytes) {
		this.responseBytes = responseBytes;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public OutputStream getOut() {
		return out;
	}

	public void setOut(OutputStream out) {
		this.out = out;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public JmxHandler() {

	}

	public JmxHandler(String fileName) {

		this.fileName = fileName;

	}

	public void init() {

	}

	public void start() {

		JMeterEngine engine = new StandardJMeterEngine();
		BaffleResultListenerHandler listener = new BaffleResultListenerHandler(bytes, message);
		HashTree testPlan = createTestPlane(listener);
		engine = new StandardJMeterEngine();

		engine.configure(testPlan);
		try {

			engine.runTest();
			byte[] buff = listener.getResultByBytes();
			log.info("BaffleResultListener getResponse byte[]="
					+ Arrays.toString(buff));
			setResponseBytes(buff);

		} catch (Exception e) {
			e.printStackTrace();
			log.equals(e.getMessage());
		} 

		log.debug("test plan after cloning and running test is running version: "
				+ ((TestPlan) testPlan.getArray()[0]).isRunningVersion());
	}

	public HashTree createTestPlane(SampleListener listener) {
		HashTree testPlan = null;
		HashTree hashTree = null;
		HashTree tree = loadIncludedElements();
		ThreadGroup threadGroup = new ThreadGroup();
		threadGroup.setNumThreads(1);
		LoopController loop = new LoopController();
		loop.setLoops(1);
		threadGroup.setSamplerController(loop);

		hashTree = new HashTree();
		hashTree.put(threadGroup, tree);
		hashTree.add(listener);
		TestPlan plan = new TestPlan();

		if(message.indexOf("\r\n")!=-1)
		plan.addParameter("message", message.replaceAll("\r\n", "#"));
		else plan.addParameter("message", message.replaceAll("\n", "#"));

		testPlan = new HashTree();
		testPlan.put(plan, hashTree);
		return testPlan;
	}

	public HashTree loadIncludedElements() {

		String includePath = fileName;

		InputStream reader = null;
		HashTree tree = null;
		if (includePath != null && includePath.length() > 0) {
			try {
				String fileName = includePath;
				File file = new File(fileName);
				final String absolutePath = file.getAbsolutePath();
				log.info("loadIncludedElements -- try to load included module: "
						+ absolutePath);
				if (!file.exists() && !file.isAbsolute()) {
					log.info("loadIncludedElements -failed for: "
							+ absolutePath);
					file = new File(FileServer.getFileServer().getBaseDir(),
							includePath);
					log.info("loadIncludedElements -Attempting to read it from: "
							+ absolutePath);
					if (!file.exists()) {
						log.error("loadIncludedElements -failed for: "
								+ absolutePath);
						throw new IOException(
								"loadIncludedElements -failed for: "
										+ absolutePath);
					}
				}

				reader = new FileInputStream(file);
				tree = SaveService.loadTree(reader);
				tree = getProperBranch(tree);
				removeDisabledItems(tree);

				return tree;

			} catch (NoClassDefFoundError ex) {
				String msg = ex.getMessage();
				if (msg == null) {
					msg = "Missing jar file - see log for details";
				}
				log.warn("Missing jar file", ex);
				JMeterUtils.reportErrorToUser(msg);
			} catch (FileNotFoundException ex) {
				String msg = ex.getMessage();
				JMeterUtils.reportErrorToUser(msg);
				log.warn(msg);
			} catch (Exception ex) {
				String msg = ex.getMessage();
				if (msg == null) {
					msg = "Unexpected error - see log for details";
				}
				JMeterUtils.reportErrorToUser(msg);
				log.warn("Unexpected error", ex);
			} finally {
				JOrphanUtils.closeQuietly(reader);
			}
		}
		return tree;

	}

	// 遍历hashTree
	private HashTree getProperBranch(HashTree tree) {
		Iterator<Object> iter = new LinkedList<Object>(tree.list()).iterator();
		while (iter.hasNext()) {
			TestElement item = (TestElement) iter.next();

			if (item instanceof TestPlan) {
				return getProperBranch(tree.getTree(item));
			}

			if (item instanceof TestFragmentController) {
				return tree.getTree(item);
			}
		}
		log.warn("No Test Fragment was found in included Test Plan, returning empty HashTree");
		return new HashTree();
	}

	private void removeDisabledItems(HashTree tree) {
		Iterator<Object> iter = new LinkedList<Object>(tree.list()).iterator();
		while (iter.hasNext()) {
			TestElement item = (TestElement) iter.next();
			if (!item.isEnabled()) {

				tree.remove(item);
			} else {

				removeDisabledItems(tree.getTree(item));// Recursive call
			}
		}
	}

}
