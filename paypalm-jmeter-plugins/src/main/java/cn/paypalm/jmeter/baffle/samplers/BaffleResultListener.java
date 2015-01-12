package cn.paypalm.jmeter.baffle.samplers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import org.apache.jmeter.reporters.AbstractListenerElement;
import org.apache.jmeter.samplers.SampleEvent;
import org.apache.jmeter.samplers.SampleListener;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

public class BaffleResultListener extends AbstractListenerElement implements SampleListener {

	private static final Logger log = LoggingManager.getLoggerForClass();
	
	private final SynchronousQueue<String> sampleResultQueue = new SynchronousQueue<String>();
	
	public String getResponseData() throws InterruptedException, BrokenBarrierException {
        String result = sampleResultQueue.poll(5, TimeUnit.SECONDS);
        if (result == null) {
            result = "TIME OUT for listening sample result. Make sure you have at least one BaffleSampler.";
        }
        log.info("Sample Result:" + result);
        return result;
    }

	@Override
	public void sampleOccurred(SampleEvent e) {
        if ("BaffleSampler".equals(e.getResult().getSampleLabel())) {
            try {
                sampleResultQueue.put(e.getResult().getResponseDataAsString());
            } catch (InterruptedException e1) {
                log.error("", e1);
            }
        }
	}

	@Override
	public void sampleStarted(SampleEvent e) {
	}

	@Override
	public void sampleStopped(SampleEvent e) {
	}

    @Override
    public Object clone() {
        return this;
    }
}
