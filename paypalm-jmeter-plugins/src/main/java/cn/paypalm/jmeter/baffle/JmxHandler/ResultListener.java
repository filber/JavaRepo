package cn.paypalm.jmeter.baffle.JmxHandler;

import java.util.concurrent.SynchronousQueue;

import org.apache.jmeter.reporters.AbstractListenerElement;
import org.apache.jmeter.samplers.SampleEvent;
import org.apache.jmeter.samplers.SampleListener;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;


public class ResultListener extends AbstractListenerElement implements SampleListener{
	private static final Logger log=LoggingManager.getLoggerForClass();
	public static final SynchronousQueue<String> queue=new SynchronousQueue<String>();
	
	public  String getResult() throws InterruptedException{
		
		return queue.take();
		
	}

	public void sampleOccurred(SampleEvent e) {
		try {
			queue.put(e.getResult().getResponseDataAsString());
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.error(""+e1.getMessage());
		}
		
	}

	public void sampleStarted(SampleEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void sampleStopped(SampleEvent e) {
		// TODO Auto-generated method stub
		
	}

}
