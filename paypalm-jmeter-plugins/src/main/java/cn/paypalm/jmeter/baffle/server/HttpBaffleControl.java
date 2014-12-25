package cn.paypalm.jmeter.baffle.server;

import org.apache.jmeter.control.Controller;
import org.apache.jmeter.control.GenericController;
import org.apache.jmeter.gui.GuiPackage;
import org.apache.jmeter.gui.tree.JMeterTreeNode;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.testelement.property.IntegerProperty;

//For unit tests, @see TestHttpMirrorControl

/**
 * Test element that implements the Workbench HTTP Mirror function
 */
public class HttpBaffleControl extends GenericController {

    private static final long serialVersionUID = 233L;

    private transient HttpBaffleServer server;

    // Used by HttpMirrorServer
    static final int DEFAULT_PORT = 8081;

    // and as a string
    public static final String DEFAULT_PORT_S =
        Integer.toString(DEFAULT_PORT);// Used by GUI

    public static final String PORT = "HttpMirrorControlGui.port"; // $NON-NLS-1$

    public static final String MAX_POOL_SIZE = "HttpMirrorControlGui.maxPoolSize"; // $NON-NLS-1$

    public static final String MAX_QUEUE_SIZE = "HttpMirrorControlGui.maxQueueSize"; // $NON-NLS-1$

    public static final int DEFAULT_MAX_POOL_SIZE = 0;

    public static final int DEFAULT_MAX_QUEUE_SIZE = 25;

    private transient JMeterTreeNode selectedNode = null;
    
    public HttpBaffleControl() {
        initPort(DEFAULT_PORT);
    }

    public HttpBaffleControl(int port) {
        initPort(port);
    }

    private void initPort(int port){
        setProperty(new IntegerProperty(PORT, port));
    }

    public void setSelectedNode(JMeterTreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}
    
    public void setPort(int port) {
        initPort(port);
    }

    public void setPort(String port) {
        setProperty(PORT, port);
    }

    public int getPort() {
        return getPropertyAsInt(PORT);
    }

    public String getPortString() {
        return getPropertyAsString(PORT);
    }
    
    /**
     * @return Max Thread Pool size
     */
    public String getMaxPoolSizeAsString() {
        return getPropertyAsString(MAX_POOL_SIZE);
    }
    
    /**
     * @return Max Thread Pool size
     */
    private int getMaxPoolSize() {
        return getPropertyAsInt(MAX_POOL_SIZE, DEFAULT_MAX_POOL_SIZE);
    }
    
    /**
     * @param maxPoolSize Max Thread Pool size
     */
    public void setMaxPoolSize(String maxPoolSize) {
        setProperty(MAX_POOL_SIZE, maxPoolSize);
    }

    /**
     * @return Max Queue size
     */
    public String getMaxQueueSizeAsString() {
        return getPropertyAsString(MAX_QUEUE_SIZE);
    }
    
    /**
     * @return Max Queue size
     */
    private int getMaxQueueSize() {
        return getPropertyAsInt(MAX_QUEUE_SIZE, DEFAULT_MAX_QUEUE_SIZE);
    }
    
    /**
     * @param maxQueueSize Max Queue size
     */
    public void setMaxQueueSize(String maxQueueSize) {
        setProperty(MAX_QUEUE_SIZE, maxQueueSize);
    }
    
    public int getDefaultPort() {
        return DEFAULT_PORT;
    }

    public void startHttpMirror() {
        server = new HttpBaffleServer(getPort(), getMaxPoolSize(), getMaxQueueSize());
        server.start();
        GuiPackage instance = GuiPackage.getInstance();
        if (instance != null) {
            instance.register(server);
        }
    }

    public void stopHttpMirror() {
        if (server != null) {
            server.stopServer();
            GuiPackage instance = GuiPackage.getInstance();
            if (instance != null) {
                instance.unregister(server);
            }
            try {
                server.join(1000); // wait for server to stop
            } catch (InterruptedException e) {
            }
            server = null;
        }
    }
    
    public void runBaffle() {
    	if (selectedNode==null) return;
    	Object userObject = selectedNode.getUserObject();
    	if (userObject instanceof Controller) {
    		Controller controller = (Controller)userObject;
    		Sampler sampler = null;
    		while ((sampler =controller.next())!=null) {
				SampleResult result = sampler.sample(null);
				System.out.println(result);
			}
		}
    }

    @Override
    public boolean canRemove() {
        return null == server;
    }

    public boolean isServerAlive(){
        return server != null && server.isAlive();
    }
}
