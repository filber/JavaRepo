package cn.paypalm.jmeter.baffle.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import cn.paypalm.jmeter.baffle.server.TcpBaffleControl;
import org.apache.jmeter.control.Controller;
import org.apache.jmeter.control.ModuleController;
import org.apache.jmeter.control.gui.LogicControllerGui;
import org.apache.jmeter.control.gui.TreeNodeWrapper;
import org.apache.jmeter.gui.GuiPackage;
import org.apache.jmeter.gui.JMeterGUIComponent;
import org.apache.jmeter.gui.UnsharedComponent;
import org.apache.jmeter.gui.tree.JMeterTreeNode;
import org.apache.jmeter.gui.util.FilePanel;
import org.apache.jmeter.gui.util.HorizontalPanel;
import org.apache.jmeter.gui.util.JSyntaxTextArea;
import org.apache.jmeter.gui.util.MenuFactory;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.testelement.WorkBench;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import cn.paypalm.jmeter.baffle.vo.TCPValues;

public class TcpBaffleControlGui extends LogicControllerGui implements
		JMeterGUIComponent, ActionListener, UnsharedComponent {

	private static final long serialVersionUID = 240L;
	private static final Logger log = LoggingManager.getLoggerForClass();

	private JTextField portField;
	private JTextField flag;
	private JTextField timeout;

	private JSyntaxTextArea inScriptField;
	private JSyntaxTextArea outScriptField;
	private JTextField maxPoolSizeField;

	private JTextField maxQueueSizeField;

	private JButton stop, start;

	private static final String ACTION_STOP = "stop";

	private static final String ACTION_START = "start";

	private TcpBaffleControl mirrorController;

	private JMeterTreeNode selected = null;
	private final JComboBox nodes;
	private final DefaultComboBoxModel nodesModel;
	private final FilePanel includePanel = new FilePanel(
			JMeterUtils.getResString("include_path"), ".jmx");

	public TcpBaffleControlGui() {
		super();
		nodesModel = new DefaultComboBoxModel();
		nodes = new JComboBox(nodesModel);
		log.debug("Creating TCPMirrorControlGui");
		init();

	}

	@Override
	public TestElement createTestElement() {
		mirrorController = new TcpBaffleControl();
		log.debug("creating/configuring model = " + mirrorController);
		modifyTestElement(mirrorController);
		return mirrorController;
	}

	public String getStaticLabel() {
		return "TCPMirror";
	}

	@Override
	public void modifyTestElement(TestElement el) {
		configureTestElement(el);

		if (el instanceof TcpBaffleControl) {
			mirrorController = (TcpBaffleControl) el;
			mirrorController.setPort(portField.getText());
			mirrorController.setMaxPoolSize(maxPoolSizeField.getText());
			mirrorController.setMaxQueueSize(maxQueueSizeField.getText());
			mirrorController.setTimeout(timeout.getText());
			mirrorController.setJmxPath(includePanel.getFilename());
			mirrorController.setFlag(flag.getText());
			mirrorController.setInScript(inScriptField.getText());
			mirrorController.setOutScript(outScriptField.getText());

			System.out.println("flag=" + flag.getText());
			mirrorController.timeout = timeout.getText();
			System.out.println("timeout=" + timeout.getText());
			TCPValues.inScript = inScriptField.getText();
			TCPValues.outScript = outScriptField.getText();
			System.out.println("Script:" + inScriptField.getText());
			System.out.println("outScriptField:" + outScriptField.getText());
			TreeNodeWrapper treeNodeWrapper = (TreeNodeWrapper) nodesModel
					.getSelectedItem();
			if (treeNodeWrapper != null
					&& treeNodeWrapper.getTreeNode() != null) {
				selected = treeNodeWrapper.getTreeNode();
				mirrorController.setSelectedNode(selected);
				System.out.println("selected:"+selected);
			}

		}
		log.info("flag=" + flag + ";timeout=" + timeout);
	}

	@Override
	public String getLabelResource() {
		return "TCPmirror_title"; // $NON-NLS-1$
	}

	@Override
	public Collection<String> getMenuCategories() {
		return Arrays.asList(new String[] { MenuFactory.NON_TEST_ELEMENTS });
	}

	@Override
	public void configure(TestElement element) {
		log.debug("Configuring gui with " + element);
		super.configure(element);
		mirrorController = (TcpBaffleControl) element;
		portField.setText(mirrorController.getPortString());
		maxPoolSizeField.setText(mirrorController.getMaxPoolSizeAsString());
		maxQueueSizeField.setText(mirrorController.getMaxQueueSizeAsString());
		timeout.setText(mirrorController.getTimeout());
		flag.setText(mirrorController.getFlag());
		inScriptField.setText(mirrorController.getInScript());
		outScriptField.setText(mirrorController.getOutScript());
		includePanel.setFilename(mirrorController.getJmxPath());
		selected = mirrorController.getSelectedNode();
		repaint();
	}

	public void actionPerformed(ActionEvent action) {
		String command = action.getActionCommand();

		if (command.equals(ACTION_STOP)) {
			mirrorController.stopTCPMirror();
			stop.setEnabled(false);
			start.setEnabled(true);
		} else if (command.equals(ACTION_START)) {
			modifyTestElement(mirrorController);
			mirrorController.startTCPMirror();
			start.setEnabled(false);
			stop.setEnabled(true);

		}

	}

	// 初始化gui
	private void init() {

		setLayout(new BorderLayout(0, 5));
		setBorder(makeBorder());

		Box myBox = Box.createVerticalBox();
		myBox.add(createPortPanel());

		JPanel north = new JPanel(new BorderLayout());
		north.add(makeTitlePanel(), BorderLayout.NORTH);
		north.add(myBox, BorderLayout.SOUTH);

		add(north, BorderLayout.NORTH);
		JPanel mainPanel = new JPanel(new BorderLayout(0, 5));
		JPanel inOut = new JPanel(new GridLayout(2, 1));
		inScriptField = new JSyntaxTextArea(20, 20);
		outScriptField = new JSyntaxTextArea(20, 20);
		inOut.add(inScriptField);
		inOut.add(outScriptField);
		JPanel controlsPanel = new JPanel(new BorderLayout(0, 5));
		controlsPanel.add(createModulePanel(), BorderLayout.NORTH);
		controlsPanel.add(includePanel, BorderLayout.CENTER);
		controlsPanel.add(createControls(), BorderLayout.SOUTH);
		mainPanel.add(new JScrollPane(inScriptField), BorderLayout.NORTH);
		mainPanel.add(new JScrollPane(outScriptField), BorderLayout.CENTER);
		mainPanel.add(controlsPanel, BorderLayout.SOUTH);
		add(mainPanel, BorderLayout.CENTER);

	}

	// 控制面板 star&stop
	private JPanel createControls() {
		start = new JButton(JMeterUtils.getResString("start")); // $NON-NLS-1$
		start.addActionListener(this);
		start.setActionCommand(ACTION_START);
		start.setEnabled(true);

		stop = new JButton(JMeterUtils.getResString("stop")); // $NON-NLS-1$
		stop.addActionListener(this);
		stop.setActionCommand(ACTION_STOP);
		stop.setEnabled(false);
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel panel = new JPanel();

		panel.add(start);
		panel.add(stop);

		mainPanel.add(includePanel, BorderLayout.NORTH);

		mainPanel.add(panel, BorderLayout.CENTER);

		return mainPanel;
	}

	// 端口,ip地址设置窗口
	private JPanel createPortPanel() {
		portField = new JTextField(TcpBaffleControl.DEFAULT_PORT_S, 5);
		portField.setName(TcpBaffleControl.PORT);

		JLabel label = new JLabel(JMeterUtils.getResString("port")); // $NON-NLS-1$
		label.setLabelFor(portField);

		maxPoolSizeField = new JTextField(
				Integer.toString(TcpBaffleControl.DEFAULT_MAX_POOL_SIZE), 5);
		maxPoolSizeField.setName(TcpBaffleControl.MAX_POOL_SIZE);

		JLabel mpsLabel = new JLabel(
				JMeterUtils.getResString("TCPmirror_max_pool_size")); // $NON-NLS-1$
		mpsLabel.setLabelFor(maxPoolSizeField);

		maxQueueSizeField = new JTextField(
				Integer.toString(TcpBaffleControl.DEFAULT_MAX_QUEUE_SIZE), 5);
		maxQueueSizeField.setName(TcpBaffleControl.MAX_QUEUE_SIZE);

		JLabel mqsLabel = new JLabel(
				JMeterUtils.getResString("TCPmirror_max_queue_size")); // $NON-NLS-1$
		mqsLabel.setLabelFor(maxQueueSizeField);

		flag = new JTextField(8);

		JLabel clabel = new JLabel("endOfFlag"); // $NON-NLS-1$
		clabel.setLabelFor(flag);

		timeout = new JTextField(5);
		timeout.setName(TcpBaffleControl.TIMEOUT);
		JLabel timeoutLable = new JLabel("Timeout");
		clabel.setLabelFor(timeout);

		HorizontalPanel panel = new HorizontalPanel();
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(),
				JMeterUtils.getResString("TCPmirror_settings"))); // $NON-NLS-1$

		panel.add(label);
		panel.add(portField);

		panel.add(mpsLabel);
		panel.add(maxPoolSizeField);

		panel.add(mqsLabel);
		panel.add(maxQueueSizeField);

		panel.add(clabel);
		panel.add(flag);

		panel.add(timeoutLable);
		panel.add(timeout);
		panel.add(Box.createHorizontalStrut(10));

		return panel;
	}

	private JPanel createModulePanel() {
		HorizontalPanel panel = new HorizontalPanel();
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Module"));
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
			root = (JMeterTreeNode) GuiPackage.getInstance().getTreeModel()
					.getRoot();
			buildNodesModel(root, "", 0); // $NON-NLS-1$
		}
		if (selected != null) {
			TreeNodeWrapper current;
			for (int i = 0; i < nodesModel.getSize(); i++) {
				current = (TreeNodeWrapper) nodesModel.getElementAt(i);
				if (current.getTreeNode() != null
						&& current.getTreeNode().equals(selected)) {
					nodesModel.setSelectedItem(current);
					break;
				}
			}
		}
	}

	private void buildNodesModel(JMeterTreeNode node, String parent_name,
			int level) {
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
				if (te instanceof Controller
						&& !(te instanceof ModuleController)
						&& !(te instanceof TcpBaffleControl)) {
					name.append(parent_name);
					name.append(cur.getName());
					TreeNodeWrapper tnw = new TreeNodeWrapper(cur,
							name.toString());
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
	public void clearGui() {
		super.clearGui();
		// inScriptField.setInitialText("");
		// outScriptField.setInitialText("");
		nodesModel.removeAllElements();
		JMeterTreeNode root;
		GuiPackage gp = GuiPackage.getInstance();
		if (gp != null) {
			root = (JMeterTreeNode) GuiPackage.getInstance().getTreeModel()
					.getRoot();
			buildNodesModel(root, "", 0);
		}
		if (selected != null) {
			TreeNodeWrapper current;
			for (int i = 0; i < nodesModel.getSize(); i++) {
				current = (TreeNodeWrapper) nodesModel.getElementAt(i);
				if (current.getTreeNode() != null
						&& current.getTreeNode().equals(selected)) {
					nodesModel.setSelectedItem(current);
					break;
				}
			}
		}
		portField.setText(TcpBaffleControl.DEFAULT_PORT_S);
		maxPoolSizeField.setText(Integer
				.toString(TcpBaffleControl.DEFAULT_MAX_POOL_SIZE));
	}

}