package cn.paypalm.jmeter.rmq;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;


public class RmqReceiver extends AbstractRmqMessenger{

    @Override
    protected void transmitMsg(JavaSamplerContext javaSamplerContext, SampleResult result) throws Exception {
        String receivedMsg = rmqConnector.receive();
        result.setResponseData(receivedMsg, RmqConstant.UTF_8);
    }
}
