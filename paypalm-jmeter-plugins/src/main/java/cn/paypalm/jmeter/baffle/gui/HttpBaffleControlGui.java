package cn.paypalm.jmeter.baffle.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.jmeter.control.Controller;
import org.apache.jmeter.control.ModuleController;
import org.apache.jmeter.control.gui.LogicControllerGui;
import org.apache.jmeter.control.gui.TreeNodeWrapper;
import org.apache.jmeter.gui.GuiPackage;
import org.apache.jmeter.gui.JMeterGUIComponent;
import org.apache.jmeter.gui.UnsharedComponent;
import org.apache.jmeter.gui.tree.JMeterTreeNode;
import org.apache.jmeter.gui.util.HorizontalPanel;
import org.apache.jmeter.gui.util.MenuFactory;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.testelement.WorkBench;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import cn.paypalm.jmeter.baffle.server.HttpBaffleControl;

public class HttpBaffleControlGui extends LogicControllerGui
    implements JMeterGUIComponent, ActionListener, UnsharedComponent {

    private static final long serialVersionUID = 240L;

    private static final Logger log = LoggingManager.getLoggerForClass();

    private JTextField portField;

    private JTextField maxPoolSizeField;

    private JTextField maxQueueSizeField;

    private JButton stop, start;

    private static final String ACTION_STOP = "stop"; // $NON-NLS-1$

    private static final String ACTION_START = "start"; // $NON-NLS-1$
    
    private HttpBaffleControl baffleController;


    private JMeterTreeNode selected = null;
    private final JComboBox nodes;
    private final DefaultComboBoxModel nodesModel;
    
    public HttpBaffleControlGui() {
        super();
        nodesModel = new DefaultComboBoxModel();
        nodes = new JComboBox(nodesModel);
        log.debug("Creating HttpBaffleControlGui");
        init();
    }

    @Override
    public TestElement createTestElement() {
        baffleController = new HttpBaffleControl();
        log.debug("creating/configuring model = " + baffleController);
        modifyTestElement(baffleController);
        return baffleController;
    }

    /**
     * Modifies a given TestElement to mirror the data in the gui components.
     *
     * @see org.apache.jmeter.gui.JMeterGUIComponent#modifyTestElement(TestElement)
     */
    @Override
    public void modifyTestElement(TestElement el) {
        configureTestElement(el);
        if (el instanceof HttpBaffleControl) {
            baffleController = (HttpBaffleControl) el;
            baffleController.setPort(portField.getText());
            baffleController.setMaxPoolSize(maxPoolSizeField.getText());
            baffleController.setMaxQueueSize(maxQueueSizeField.getText());
            TreeNodeWrapper treeNodeWrapper = (TreeNodeWrapper)nodesModel.getSelectedItem();
            if (treeNodeWrapper!=null && treeNodeWrapper.getTreeNode()!=null) {
				selected = treeNodeWrapper.getTreeNode();
				baffleController.setSelectedNode(selected);
			}
        }
    }
    

    @Override
	public String getStaticLabel() {
		return "HTTP Baffle Server";
	}

	@Override
    public Collection<String> getMenuCategories() {
        return Arrays.asList(new String[] { MenuFactory.NON_TEST_ELEMENTS,MenuFactory.CONTROLLERS });
    }

    @Override
    public void configure(TestElement element) {
        log.debug("Configuring gui with " + element);
        super.configure(element);
        baffleController = (HttpBaffleControl) element;
        
        selected = baffleController.getSelectedNode();
        
        portField.setText(baffleController.getPortString());
        maxPoolSizeField.setText(baffleController.getMaxPoolSizeAsString());
        maxQueueSizeField.setText(baffleController.getMaxQueueSizeAsString());
        repaint();
        
        reinitialize();
    }


    public void actionPerformed(ActionEvent action) {
        String command = action.getActionCommand();

        if (command.equals(ACTION_STOP)) {
            baffleController.stopHttpMirror();
            stop.setEnabled(false);
            start.setEnabled(true);
        } else if (command.equals(ACTION_START)) {
            modifyTestElement(baffleController);
            baffleController.startHttpMirror();
            start.setEnabled(false);
            stop.setEnabled(true);
        }
    }

    private void init() {
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());

        add(makeTitlePanel(), BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());

        Box myBox = Box.createVerticalBox();
        myBox.add(createPortPanel());
        myBox.add(createModulePanel());
        mainPanel.add(myBox, BorderLayout.NORTH);

        mainPanel.add(createControls(), BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createControls() {
        start = new JButton(JMeterUtils.getResString("start")); // $NON-NLS-1$
        start.addActionListener(this);
        start.setActionCommand(ACTION_START);
        start.setEnabled(true);

        stop = new JButton(JMeterUtils.getResString("stop")); // $NON-NLS-1$
        stop.addActionListener(this);
        stop.setActionCommand(ACTION_STOP);
        stop.setEnabled(false);

        JPanel panel = new JPanel();
        panel.add(start);
        panel.add(stop);
        return panel;
    }

    private JPanel createPortPanel() {
        portField = new JTextField(HttpBaffleControl.DEFAULT_PORT_S, 8);
        portField.setName(HttpBaffleControl.PORT);

        JLabel label = new JLabel(JMeterUtils.getResString("port")); // $NON-NLS-1$
        label.setLabelFor(portField);

        maxPoolSizeField = new JTextField(Integer.toString(HttpBaffleControl.DEFAULT_MAX_POOL_SIZE), 8);
        maxPoolSizeField.setName(HttpBaffleControl.MAX_POOL_SIZE);

        JLabel mpsLabel = new JLabel(JMeterUtils.getResString("httpmirror_max_pool_size")); // $NON-NLS-1$
        mpsLabel.setLabelFor(maxPoolSizeField);

        maxQueueSizeField = new JTextField(Integer.toString(HttpBaffleControl.DEFAULT_MAX_QUEUE_SIZE), 8);
        maxQueueSizeField.setName(HttpBaffleControl.MAX_QUEUE_SIZE);

        JLabel mqsLabel = new JLabel(JMeterUtils.getResString("httpmirror_max_queue_size")); // $NON-NLS-1$
        mqsLabel.setLabelFor(maxQueueSizeField);

        HorizontalPanel panel = new HorizontalPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                JMeterUtils.getResString("httpmirror_settings"))); // $NON-NLS-1$
        
        panel.add(label);
        panel.add(portField);

        panel.add(mpsLabel);
        panel.add(maxPoolSizeField);

        panel.add(mqsLabel);
        panel.add(maxQueueSizeField);
        
        panel.add(Box.createHorizontalStrut(10));

        return panel;
    }

    private JPanel createModulePanel() {
    	HorizontalPanel panel = new HorizontalPanel();
    	panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Module"));
    	reinitialize();
    	panel.add(nodes);
    	panel.add(Box.createHorizontalStrut(10));
    	return panel;
    }
    
    private void reinitialize() {
        nodesModel.removeAllElements();
        GuiPackage gp = GuiPackage.getInstance();
        JMeterTreeNode root;
        if (gp != null) {
            root = (JMeterTreeNode) GuiPackage.getInstance().getTreeModel().getRoot();
            buildNodesModel(root, "", 0); // $NON-NLS-1$
        }
        if (selected != null) {
            TreeNodeWrapper current;
            for (int i = 0; i < nodesModel.getSize(); i++) {
                current = (TreeNodeWrapper) nodesModel.getElementAt(i);
                if (current.getTreeNode() != null && current.getTreeNode().equals(selected)) {
                    nodesModel.setSelectedItem(current);
                    break;
                }
            }
        }
    }
    
    private void buildNodesModel(JMeterTreeNode node, String parent_name, int level) {
        if (level == 0 && (parent_name == null || parent_name.length() == 0)) {
            nodesModel.addElement(new TreeNodeWrapper(null, "")); // $NON-NLS-1$
        }
        String separator = " > "; // $NON-NLS-1$
        if (node != null) {
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < node.getChildCount(); i++) {
                name.setLength(0);
                JMeterTreeNode cur = (JMeterTreeNode) node.getChildAt(i);
                TestElement te = cur.getTestElement();
                if (te instanceof Controller && !(te instanceof ModuleController) && !(te instanceof HttpBaffleControl)) {
                    name.append(parent_name);
                    name.append(cur.getName());
                    TreeNodeWrapper tnw = new TreeNodeWrapper(cur, name.toString());
                    nodesModel.addElement(tnw);
                    name.append(separator);
                    buildNodesModel(cur, name.toString(), level + 1);
                } else if (te instanceof TestPlan || te instanceof WorkBench) {
                    name.append(cur.getName());
                    name.append(separator);
                    buildNodesModel(cur, name.toString(), 0);
                }
            }
        }
    }
    
    @Override
    public void clearGui(){
        super.clearGui();
        portField.setText(HttpBaffleControl.DEFAULT_PORT_S);
        maxPoolSizeField.setText(Integer.toString(HttpBaffleControl.DEFAULT_MAX_POOL_SIZE));
    }
}