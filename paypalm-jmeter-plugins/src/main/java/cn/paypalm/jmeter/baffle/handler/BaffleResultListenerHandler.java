package cn.paypalm.jmeter.baffle.handler;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.SynchronousQueue;

import org.apache.jmeter.reporters.AbstractListenerElement;
import org.apache.jmeter.samplers.SampleEvent;
import org.apache.jmeter.samplers.SampleListener;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import cn.paypalm.jmeter.baffle.vo.TCPValues;

public class BaffleResultListenerHandler extends AbstractListenerElement implements
		SampleListener {

	private static final Logger log = LoggingManager.getLoggerForClass();

	public static final SynchronousQueue<String> queueString = new SynchronousQueue<String>();
	public  SynchronousQueue<byte[]> queueBytes ;
	private String requestContent;
	private byte[] bytes;
	

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	public BaffleResultListenerHandler(byte[] bytes, String requestContent) {
		log.info("BaffleResultListener starts listening....");
		log.info("reqeust message:byte[]=" + Arrays.toString(bytes)
				+ ";String=" + requestContent);
		this.requestContent = requestContent;
		this.bytes = bytes;
		queueBytes = new SynchronousQueue<byte[]>();
	}

	public void sampleOccurred(SampleEvent e) {
		try {

			if ("ProxySampler".equals(e.getResult().getSampleLabel())) {
				socketRequest();

			} else {
				queueBytes.clear();
				queueBytes.put(e.getResult().getResponseData());

				queueString.put(e.getResult().getResponseDataAsString());
			}
			
		} catch (Exception e1) {
			log.error("", e1);
		}
	}

	private void socketRequest() {
		String TCPBaffleProxyIP = super.getThreadContext().getVariables()
				.get("host");
		String TCPBaffleProxyPort = super.getThreadContext().getVariables()
				.get("port");
		if (TCPBaffleProxyIP == null || TCPBaffleProxyIP.isEmpty())
			return;
		if (TCPBaffleProxyPort == null || TCPBaffleProxyPort.isEmpty())
			return;
		try {
			Socket proxySocket = null;
			InputStream proxyInputStream = null;
			OutputStream proxyOutputStream = null;
			if (TCPValues.socket == null) {
				proxySocket = new Socket(TCPBaffleProxyIP,
						Integer.valueOf(TCPBaffleProxyPort));
				proxyInputStream = proxySocket.getInputStream();
				proxyOutputStream = proxySocket.getOutputStream();
				log.info("proxyServer starts["
						+ proxySocket.getRemoteSocketAddress()
						+ "].....................");
				TCPValues.socket = proxySocket;
				TCPValues.in = proxyInputStream;
				TCPValues.out = proxyOutputStream;
			} else {
				proxySocket = TCPValues.socket;
				proxyInputStream = TCPValues.in;
				proxyOutputStream = TCPValues.out;
			}

			String str = new String(bytes);
			if (str != null && str.indexOf("select") != -1) {
				TCPValues.select = 1;
			}
			proxyOutputStream.write(getBytes());
			proxyOutputStream.flush();

			byte[] buffer = new byte[1024 * 1024];
			int length = -1;
			if ((length = proxyInputStream.read(buffer)) != -1) {
				byte[] bytes = Arrays.copyOfRange(buffer, 0, length);
				log.info("报文内容........................");
				log.info(new String(bytes));
				queueBytes.put(bytes);

			} else {
				if (proxySocket != null)
					proxySocket.close();
				TCPValues.socket = null;
				TCPValues.in = null;
				TCPValues.out = null;
				log.info("ProxyServer close.................");
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

	public void sampleStarted(SampleEvent e) {
	}

	public void sampleStopped(SampleEvent e) {
	}

	public Object clone() {
		return this;
	}

	public String getResultByString() throws InterruptedException {

		String str = queueString.take();
		queueString.clear();
		return str;

	}

	public byte[] getResultByBytes() throws InterruptedException {
		byte[] bytes = queueBytes.take();
		queueBytes.clear();
		return bytes;

	}

}