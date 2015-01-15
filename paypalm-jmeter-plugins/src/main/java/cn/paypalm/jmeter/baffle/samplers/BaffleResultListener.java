package cn.paypalm.jmeter.baffle.samplers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.apache.jmeter.reporters.AbstractListenerElement;
import org.apache.jmeter.samplers.SampleEvent;
import org.apache.jmeter.samplers.SampleListener;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.jorphan.util.JOrphanUtils;
import org.apache.log.Logger;

public class BaffleResultListener extends AbstractListenerElement implements SampleListener {

	private static final Logger log = LoggingManager.getLoggerForClass();
    private static final String ISO_8859_1 = "ISO-8859-1"; //$NON-NLS-1$
    private static final byte[] CRLF = { 0x0d, 0x0a };

    private final String requestContent;
    private final OutputStream outputStream;
    private final CyclicBarrier barrier;

    public BaffleResultListener(String requestContent, OutputStream outputStream, CyclicBarrier barrier) {
        this.requestContent = requestContent;
        this.outputStream = outputStream;
        this.barrier = barrier;
    }

	@Override
	public void sampleOccurred(SampleEvent e) {
        try {
            if ("BaffleSampler".equals(e.getResult().getSampleLabel())) {
                // The headers are written using ISO_8859_1 encoding
                outputStream.write(("HTTP/1.0 200 OK").getBytes(ISO_8859_1)); //$NON-NLS-1$
                outputStream.write(CRLF);
                outputStream.write("Content-Type: text/plain".getBytes(ISO_8859_1)); //$NON-NLS-1$
                outputStream.write(CRLF);
                outputStream.write(CRLF);
                outputStream.write(e.getResult().getResponseData());
                outputStream.flush();
            }
            if ("ProxySampler".equals(e.getResult().getSampleLabel())) {
                proxyRequest();
            }
            barrier.await();
        } catch (Exception e1) {
            log.error("", e1);
        }
    }
    private void proxyRequest(){
        String httpBaffleProxyIP= super.getThreadContext().getVariables().get("HttpBaffleProxyIP");
        String httpBaffleProxyPort = super.getThreadContext().getVariables().get("HttpBaffleProxyPort");
        try {
            Socket proxySocket = new Socket(httpBaffleProxyIP,Integer.valueOf(httpBaffleProxyPort));
            InputStream proxyInputStream = proxySocket.getInputStream();
            OutputStream proxyOutputStream = proxySocket.getOutputStream();
            proxyOutputStream.write(requestContent.getBytes());
            proxyOutputStream.flush();

            byte[] buffer = new byte[1024];
            int length = 0;
            StringBuffer proxyResponse = new StringBuffer();
            while ((length = proxyInputStream.read(buffer)) != -1) {
                proxyResponse.append(new String(buffer, 0, length, ISO_8859_1));
            }

            JOrphanUtils.closeQuietly(proxyOutputStream);
            JOrphanUtils.closeQuietly(proxyInputStream);
            JOrphanUtils.closeQuietly(proxySocket);

            outputStream.write(proxyResponse.toString().getBytes());
            outputStream.flush();
        } catch (Exception e) {
            log.error("",e);
            try {
                outputStream.write(("HTTP/1.0 500 OK").getBytes(ISO_8859_1)); //$NON-NLS-1$
                outputStream.write(CRLF);
                outputStream.write("Content-Type: text/plain".getBytes(ISO_8859_1)); //$NON-NLS-1$
                outputStream.write(CRLF);
                outputStream.write(CRLF);
                outputStream.write(e.getMessage().getBytes(ISO_8859_1));
                outputStream.flush();
            } catch (IOException e1) {
                log.error("",e1);
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
