package cn.paypalm.jmeter.baffle.vo;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.jmeter.gui.tree.JMeterTreeNode;
import org.apache.jorphan.collections.HashTree;

/**
 * 负责传值从TCPMirror的GUI传入到TCPBaffleThread中处理
 * */
public class TCPValues {

	public static String inScript;
	public static String include;
	public static String end;
	public static String outScript;
	public static Socket socket;
	public static InputStream in;
	public static OutputStream out;
	public static int select;
	public static JMeterTreeNode selectedNode;
}
