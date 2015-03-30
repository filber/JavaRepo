package cn.paypalm.jmeter.rmq;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class RmqSender extends AbstractRmqMessenger {

    @Override
    protected void transmitMsg(JavaSamplerContext javaSamplerContext, SampleResult result) throws Exception {
        String msgContent = javaSamplerContext.getParameter(RmqConstant.MSG_CONTENT);
        result.setSamplerData(msgContent);
        rmqConnector.send(msgContent);
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments arguments = super.getDefaultParameters();
        arguments.addArgument(RmqConstant.MSG_CONTENT, RmqConstant.DEFAULT_MSG_CONTENT);
        return arguments;
    }
}