package cn.paypalm.jmeter.rmq;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import java.io.IOException;

public abstract class AbstractRmqMessenger implements JavaSamplerClient {

    private static final Logger log = LoggingManager.getLoggerForClass();

    protected RmqConnector rmqConnector;

    protected abstract void transmitMsg(JavaSamplerContext javaSamplerContext, SampleResult result) throws Exception;

    @Override
    public void setupTest(JavaSamplerContext javaSamplerContext) {
        String esbIp = javaSamplerContext.getParameter(RmqConstant.ESB_IP);
        String esbVirtualHost = javaSamplerContext.getParameter(RmqConstant.ESB_VIRTUAL_HOST);
        String esbChannel = javaSamplerContext.getParameter(RmqConstant.ESB_CHANNEL);
        String esbUsername = javaSamplerContext.getParameter(RmqConstant.ESB_USERNAME);
        String esbPassword = javaSamplerContext.getParameter(RmqConstant.ESB_PASSWORD);
        rmqConnector = new RmqConnector();
        try {
            rmqConnector.connect(esbIp,esbVirtualHost,esbChannel,esbUsername,esbPassword);
        } catch (IOException e) {
            log.error("RmqConnector Connect Failed",e);
        }
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult result = new SampleResult();
        result.setSampleLabel(javaSamplerContext.getParameter("TestElement.name"));
        result.setSamplerData("");
        result.setResponseData("", RmqConstant.UTF_8);
        result.setDataType(SampleResult.TEXT);
        result.setDataEncoding(RmqConstant.UTF_8);
        result.setSuccessful(true);
        result.setResponseMessageOK();
        result.setResponseCodeOK();

        result.sampleStart();
        try {
            transmitMsg(javaSamplerContext,result);
            result.latencyEnd();
            result.setSuccessful(true);
        } catch (Exception e) {
            result.setSuccessful(false);
            result.setResponseMessage(e.getMessage());
            result.setResponseCode("000");
            result.setResponseData(e.getMessage().getBytes());
            log.error("RmqConnector message transmit failed", e);
        }

        result.sampleEnd();
        return result;
    }

    @Override
    public void teardownTest(JavaSamplerContext javaSamplerContext) {
        rmqConnector.close();
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments args = new Arguments();
        args.addArgument(RmqConstant.ESB_IP, RmqConstant.DEFAULT_ESB_IP);
        args.addArgument(RmqConstant.ESB_VIRTUAL_HOST, RmqConstant.DEFAULT_ESB_VIRTUAL_HOST);
        args.addArgument(RmqConstant.ESB_CHANNEL, RmqConstant.DEFAULT_ESB_CHANNEL);
        args.addArgument(RmqConstant.ESB_USERNAME, RmqConstant.DEFAULT_ESB_USERNAME);
        args.addArgument(RmqConstant.ESB_PASSWORD, RmqConstant.DEFAULT_ESB_PASSWORD);
        return args;
    }
}
