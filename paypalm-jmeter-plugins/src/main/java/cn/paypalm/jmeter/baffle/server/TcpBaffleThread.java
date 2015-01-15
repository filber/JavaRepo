package cn.paypalm.jmeter.baffle.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.apache.jmeter.util.BeanShellInterpreter;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.jorphan.util.JMeterException;
import org.apache.jorphan.util.JOrphanUtils;
import org.apache.log.Logger;

import cn.paypalm.jmeter.baffle.BeanShellHandler.BeanShellDeal;
import cn.paypalm.jmeter.baffle.JmxHandler.JmxHandler;
import cn.paypalm.jmeter.baffle.vo.TCPValues;

/**
 * 处理业务逻辑的主要代码
 * 
 * */
public class TcpBaffleThread implements Runnable {
	private static final Logger log = LoggingManager.getLoggerForClass();
	private final Socket clientSocket;
	private String flag;
	private String timeout;
	private String condition;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public TcpBaffleThread(Socket _clientSocket) {
		this.clientSocket = _clientSocket;
	}

	public TcpBaffleThread(Socket _clientSocket, String condition, String flag,
			String timeout) {
		this.clientSocket = _clientSocket;
		this.condition = condition;
		this.flag = flag;
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
			log.info("客户端已连接,inet信息为:" + clientSocket.getInetAddress());
			log.info("客户端已连接,inet信息为:" + clientSocket.getInetAddress());
			System.out.println("TCPValues.script:" + TCPValues.inScript);
			BeanShellInterpreter bsi = new BeanShellInterpreter();
			bsi.set("Socket", clientSocket);
			bsi.set("in", in);
			bsi.set("out", out);
			BeanShellDeal bsd = new BeanShellDeal();
			bsd.setBshInterpreter(bsi);

			Object o = bsd.doDeal(TCPValues.inScript, "");

			JmxHandler jmxHandler = new JmxHandler(condition);
			String result = null;
			if (o != null) {
				result = o.toString();
				jmxHandler.setMessage(result);

			} else {
				log.error("报文为空!");
			}

			jmxHandler.start();
			String response = jmxHandler.getResponse();
			log.info("用例返回的结果" + response);
			Object o1 = bsd.doDeal(TCPValues.outScript, response);
			if (o1 != null) {
				result = o1.toString();
				log.info("处理后的报文:" + result);
				System.out.println("处理后的报文:" + result);
			} else {
				log.error("处理报文为空!");
			}

		} catch (IOException e) {
			log.error("", e);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (JMeterException e) {

			e.printStackTrace();
		} finally {
			//关闭操作放入到BeanShell中,一定要在BeanShell的outScript中写关闭连接的操作
			// JOrphanUtils.closeQuietly(clientSocket);
		}
		log.debug("End of Thread");
	}

}
