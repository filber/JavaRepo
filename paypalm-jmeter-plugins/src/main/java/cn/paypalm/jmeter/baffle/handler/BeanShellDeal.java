package cn.paypalm.jmeter.baffle.handler;

import org.apache.jmeter.util.BeanShellInterpreter;
import org.apache.jorphan.util.JMeterException;

/**
 * 处理BeanShell,
 * bshInterpreter为执行BeanShell代码,并设置BeanShell中的context对象
 * */
public class BeanShellDeal {

	private BeanShellInterpreter bshInterpreter;

	public BeanShellInterpreter getBshInterpreter() {
		return bshInterpreter;
	}

	public void setBshInterpreter(BeanShellInterpreter bshInterpreter) {
		this.bshInterpreter = bshInterpreter;
	}

	public Object doDeal(String script, Object o) {
		Object bshOut = null;
		try {
			bshInterpreter.set("Parameters", o);

			bshOut = bshInterpreter.eval(script);
		} catch (JMeterException e) {
			e.printStackTrace();
		}
		return bshOut;
	}

}