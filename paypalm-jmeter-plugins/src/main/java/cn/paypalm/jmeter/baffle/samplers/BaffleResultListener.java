package cn.paypalm.jmeter.baffle.samplers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.SynchronousQueue;

import org.apache.jmeter.reporters.AbstractListenerElement;
import org.apache.jmeter.samplers.SampleEvent;
import org.apache.jmeter.samplers.SampleListener;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

public class BaffleResultListener extends AbstractListenerElement implements SampleListener {

	private static final Logger log = LoggingManager.getLoggerForClass();
	
	public static final SynchronousQueue<String> sampleResultQueue = new SynchronousQueue<>();
	
	public static String getResponseData() throws InterruptedException, BrokenBarrierException {
		return sampleResultQueue.take();
	}

	@Override
	public void sampleOccurred(SampleEvent e) {
		try {
			sampleResultQueue.put(e.getResult().getResponseDataAsString());
		} catch (InterruptedException e1) {
			log.error("", e1);
		}
	}

	@Override
	public void sampleStarted(SampleEvent e) {
	}

	@Override
	public void sampleStopped(SampleEvent e) {
	}

}
