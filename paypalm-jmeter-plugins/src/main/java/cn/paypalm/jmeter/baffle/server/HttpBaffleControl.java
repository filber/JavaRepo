package cn.paypalm.jmeter.baffle.server;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

import org.apache.jmeter.control.GenericController;
import org.apache.jmeter.control.ModuleController;
import org.apache.jmeter.gui.GuiPackage;
import org.apache.jmeter.gui.tree.JMeterTreeNode;
import org.apache.jmeter.testelement.property.CollectionProperty;
import org.apache.jmeter.testelement.property.IntegerProperty;
import org.apache.jmeter.testelement.property.JMeterProperty;
import org.apache.jmeter.testelement.property.NullProperty;

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

    public static final String PORT = "HttpBaffleControlGui.port"; // $NON-NLS-1$

    public static final String MAX_POOL_SIZE = "HttpBaffleControlGui.maxPoolSize"; // $NON-NLS-1$

    public static final String MAX_QUEUE_SIZE = "HttpBaffleControlGui.maxQueueSize"; // $NON-NLS-1$

    private static final String NODE_PATH = "HttpBaffleControlGui.node_path";// $NON-NLS-1$
    
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

    public void setSelectedNode(JMeterTreeNode selectedNode) {
		this.selectedNode = selectedNode;
		setNodePath();
	}
    
    public JMeterTreeNode getSelectedNode() {
        if (selectedNode == null){
            restoreSelected();
        }
        return selectedNode;
    }
    
    private void restoreSelected() {
        GuiPackage gp = GuiPackage.getInstance();
        if (gp != null) {
            JMeterTreeNode root = (JMeterTreeNode) gp.getTreeModel().getRoot();
            resolveReplacementSubTree(root);
        }
    }
    
    private void resolveReplacementSubTree(JMeterTreeNode context) {
        if (selectedNode == null) {
            List<?> nodePathList = getNodePath();
            if (nodePathList != null && nodePathList.size() > 0) {
                traverse(context, nodePathList, 1);
            }
        }
    }

    private void traverse(JMeterTreeNode node, List<?> nodePath, int level) {
        if (node != null && nodePath.size() > level) {
            for (int i = 0; i < node.getChildCount(); i++) {
                JMeterTreeNode cur = (JMeterTreeNode) node.getChildAt(i);
                // Bug55375 - don't allow selectedNode to be a ModuleController as can cause recursion
                if (!(cur.getTestElement() instanceof ModuleController)) {
                    if (cur.getName().equals(nodePath.get(level).toString())) {
                        if (nodePath.size() == (level + 1)) {
                            selectedNode = cur;
                        }
                        traverse(cur, nodePath, level + 1);
                    }
                }
            }
        }
    }
    
    private void setNodePath() {
        List<String> nodePath = new ArrayList<String>();
        if (selectedNode != null) {
            TreeNode[] path = selectedNode.getPath();
            for (TreeNode node : path) {
                nodePath.add(((JMeterTreeNode) node).getName());
            }
        }
        setProperty(new CollectionProperty(NODE_PATH, nodePath));
    }
    
    public List<?> getNodePath() {
        JMeterProperty prop = getProperty(NODE_PATH);
        if (!(prop instanceof NullProperty)) {
            return (List<?>) ((CollectionProperty) prop).getObjectValue();
        }
        return null;
    }
    
    public void startHttpMirror() {
    	if (selectedNode==null) return;

    	server = new HttpBaffleServer(getPort(), getMaxPoolSize(), getMaxQueueSize(),getSelectedNode());
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

    @Override
    public boolean canRemove() {
        return null == server;
    }

    public boolean isServerAlive(){
        return server != null && server.isAlive();
    }
}
