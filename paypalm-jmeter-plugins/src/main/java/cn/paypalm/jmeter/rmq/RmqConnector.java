package cn.paypalm.jmeter.rmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.apache.commons.lang.StringUtils;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import java.io.IOException;

public class RmqConnector {

    private static final Logger log = LoggingManager.getLoggerForClass();

    private ConnectionFactory connectionFactory;
    private Connection connection;
    protected Channel channel;
    protected String channelName;

    public RmqConnector(){
        connectionFactory = new ConnectionFactory();
    }

    public void connect(String ip,String virtualHost, String channelName,String username, String password)throws IOException
    {
        if (StringUtils.isNotEmpty(ip)){
            connectionFactory.setHost(ip);
        }
        if (StringUtils.isNotEmpty(virtualHost)){
            connectionFactory.setVirtualHost(virtualHost);
        }
        if (StringUtils.isNotEmpty(username)){
            connectionFactory.setUsername(username);
        }
        if (StringUtils.isNotEmpty(password)){
            connectionFactory.setPassword(password);
        }
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
        this.channelName = channelName;
    }


    public void send(String strSendMsg)
    {
        try {
            //channel.basicPublish("ex." + channelName, "", null, strSendMsg.getBytes());
            channel.basicPublish("ex." + channelName, "", null, strSendMsg.getBytes());
        } catch (IOException e) {
            log.error("Send failed",e);
        }
    }

    private QueueingConsumer consumer;

    public String receive()
    {
        try
        {
            if (consumer == null){
                consumer = new QueueingConsumer(channel);
                //channel.basicConsume(channelName, false, consumer);
                channel.basicConsume("que." + channelName, false, consumer);
            }
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            return new String(delivery.getBody());
        } catch (Exception e) {
            log.error("Receive failed",e);
            return e.getMessage();
        }
    }

	public void close()
	{
        try {
            channel.close();
            connection.close();
        } catch (IOException e) {
            log.error("RmqConnector Close Failed",e);
        }
	}
}