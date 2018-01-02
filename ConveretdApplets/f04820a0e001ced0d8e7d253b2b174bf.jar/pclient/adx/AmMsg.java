// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.AbstractButton;
import javax.swing.JRadioButton;
import pclient.shd.Config;
import pclient.adv.TextFieldCopy;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.Dimension;
import pclient.adv.CompUtil;
import javax.swing.border.Border;
import javax.swing.JComponent;
import javax.swing.Icon;
import java.awt.Component;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.pchat.sc.StringUtil;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class AmMsg extends JPanel implements ActionListener, MouseListener
{
    protected JButton addMsg;
    protected JButton delMsg;
    protected JButton saveMsg;
    protected JButton loadMsg;
    protected JButton sendMsg;
    private static final String ACT_FRESH = "refresh";
    private static final String ACT_ADD = "ADD";
    private static final String ACT_DEL = "DEL";
    private static final String ACT_SAVE = "SAVE";
    private static final String ACT_LOAD = "LOAD";
    private static final String ACT_SEND = "SEND";
    private static final String ACT_UNAME = "UNAME";
    private static final String ACT_ROOM = "ROOM";
    private ButtonGroup radioGroup;
    private JTextField textInput;
    private JCheckBox alertCheck;
    private JList userNameList;
    private DefaultListModel userNameModel;
    private JList msgList;
    private DefaultListModel msgModel;
    private AdmHandler adminHandler;
    private EmptyBorder border5;
    private EmptyBorder border10;
    
    public AmMsg(final AdmHandler adminHandler) {
        this.border5 = new EmptyBorder(5, 5, 5, 5);
        this.border10 = new EmptyBorder(10, 10, 10, 10);
        this.adminHandler = adminHandler;
        this.buildUI();
    }
    
    public void refresh() {
        final String[] userNames = this.adminHandler.userNames();
        this.userNameModel.clear();
        for (int i = 0; i < userNames.length; ++i) {
            this.userNameModel.addElement(userNames[i]);
        }
    }
    
    protected void populateList(final String[] array) {
        if (array == null) {
            return;
        }
        this.msgModel.clear();
        for (int i = 0; i < array.length; ++i) {
            this.msgModel.addElement(array[i]);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() != 1) {
            if (mouseEvent.getClickCount() == 2) {}
            return;
        }
        final int selectedIndex = this.msgList.getSelectedIndex();
        if (selectedIndex < 0) {
            return;
        }
        this.textInput.setText((String)this.msgModel.get(selectedIndex));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.adminHandler.paraApplet.paraConf.printer().print("command=" + actionCommand);
        if ("refresh".equals(actionCommand)) {
            this.refresh();
        }
        else if ("ADD".equals(actionCommand)) {
            final String text = this.textInput.getText();
            if (StringUtil.isTrimmedEmpty(text)) {
                this.adminHandler.info("Please enter some text you would like to add in the text field.");
                return;
            }
            this.msgModel.addElement(text);
        }
        else if ("DEL".equals(actionCommand)) {
            this.deleteMessage();
        }
        else if ("SAVE".equals(actionCommand)) {
            final String[] array = new String[this.msgModel.getSize()];
            this.msgModel.copyInto(array);
            this.adminHandler.saveMessages(array);
        }
        else if ("LOAD".equals(actionCommand)) {
            this.adminHandler.loadMessages();
        }
        else if ("SEND".equals(actionCommand)) {
            this.sendMsg();
        }
    }
    
    private String selectedUser() {
        final int selectedIndex = this.userNameList.getSelectedIndex();
        if (selectedIndex < 0) {
            this.adminHandler.info("please select a user first");
            return null;
        }
        return (String)this.userNameModel.get(selectedIndex);
    }
    
    private void deleteMessage() {
        final int selectedIndex = this.msgList.getSelectedIndex();
        if (selectedIndex < 0) {
            this.adminHandler.info("please select a message to delete.");
            return;
        }
        this.msgModel.remove(selectedIndex);
    }
    
    private void sendMsg() {
        final String text = this.textInput.getText();
        if (StringUtil.isTrimmedEmpty(text)) {
            this.adminHandler.info("Please select a message or enter some text in the text field.");
            return;
        }
        final String actionCommand = this.radioGroup.getSelection().getActionCommand();
        boolean b = true;
        if ("ROOM".equals(actionCommand)) {
            b = false;
        }
        if (b) {
            final String selectedUser = this.selectedUser();
            if (selectedUser != null) {
                this.adminHandler.sendUser(selectedUser, text);
                if (this.alertCheck.isSelected()) {
                    this.adminHandler.alertUser(selectedUser);
                }
            }
        }
        else {
            this.adminHandler.sendRoom(text);
            if (this.alertCheck.isSelected()) {
                this.adminHandler.alertRoom();
            }
        }
    }
    
    private void buildUI() {
        this.setLayout(new BorderLayout());
        final JTabbedPane tabbedPane = new JTabbedPane();
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.add(tabbedPane);
        tabbedPane.addTab("Messages", null, this.createMsg(), "Manage stored messages");
        this.add(panel, "Center");
    }
    
    private JComponent createMsg() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(this.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(this.getLists());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 6)));
        verticalPanel.add(this.getBottom());
        return panel;
    }
    
    private JComponent getLists() {
        final JLabel label = new JLabel("<html><p>Stored messages are not changed until you click \"Save to Server\" </p></html>");
        final JSplitPane splitPane = new JSplitPane(1, this.getUsers(), this.getMsgs());
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerLocation(90);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Stored Messages", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 3)));
        verticalPanel.add(splitPane);
        return verticalPanel;
    }
    
    private JComponent getUsers() {
        final JLabel label = new JLabel("User Names");
        label.setAlignmentX(0.5f);
        final DefaultListModel<Object> userNameModel = new DefaultListModel<Object>();
        final JList userNameList = new JList(userNameModel);
        userNameList.setSelectionMode(0);
        userNameList.setVisibleRowCount(32);
        final JScrollPane scrollPane = new JScrollPane(userNameList, 20, 30);
        scrollPane.setMinimumSize(new Dimension(60, 180));
        scrollPane.setPreferredSize(new Dimension(90, 190));
        final JButton button = new JButton("Refresh");
        button.setActionCommand("refresh");
        button.addActionListener(this);
        button.setAlignmentX(0.5f);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(scrollPane);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 3)));
        verticalPanel.add(button);
        this.userNameList = userNameList;
        this.userNameModel = userNameModel;
        return verticalPanel;
    }
    
    private JComponent getMsgs() {
        final JLabel label = new JLabel("Stored Messages");
        label.setAlignmentX(0.5f);
        final DefaultListModel<Object> msgModel = new DefaultListModel<Object>();
        final JList msgList = new JList(msgModel);
        msgList.setVisibleRowCount(32);
        msgList.setSelectionMode(0);
        msgList.addMouseListener(this);
        final JScrollPane scrollPane = new JScrollPane(msgList, 20, 30);
        scrollPane.setMinimumSize(new Dimension(120, 190));
        scrollPane.setPreferredSize(new Dimension(160, 200));
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(scrollPane);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 3)));
        verticalPanel.add(this.getButtons());
        this.msgList = msgList;
        this.msgModel = msgModel;
        return verticalPanel;
    }
    
    private JComponent getButtons() {
        final JButton addMsg = new JButton("Add");
        addMsg.setActionCommand("ADD");
        addMsg.addActionListener(this);
        final JButton delMsg = new JButton("Delete");
        delMsg.setActionCommand("DEL");
        delMsg.addActionListener(this);
        final JButton saveMsg = new JButton("Save to Server");
        saveMsg.setActionCommand("SAVE");
        saveMsg.addActionListener(this);
        final JButton loadMsg = new JButton("Load from Server");
        loadMsg.setActionCommand("LOAD");
        loadMsg.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.setAlignmentX(0.5f);
        horizontalPanel.add(addMsg);
        horizontalPanel.add(delMsg);
        horizontalPanel.add(saveMsg);
        horizontalPanel.add(loadMsg);
        this.addMsg = addMsg;
        this.delMsg = delMsg;
        this.saveMsg = saveMsg;
        this.loadMsg = loadMsg;
        return horizontalPanel;
    }
    
    private JComponent getBottom() {
        final JLabel label = new JLabel("<html><p>Select a user or send to the entire room.</p></html>");
        final TextFieldCopy textInput = new TextFieldCopy((Config)null);
        textInput.setColumns(32);
        textInput.setMaximumSize(new Dimension(1200, 24));
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.add(Box.createRigidArea(new Dimension(2, 1)));
        horizontalPanel.add(textInput);
        horizontalPanel.add(Box.createRigidArea(new Dimension(3, 1)));
        final JButton sendMsg = new JButton("Send");
        sendMsg.setActionCommand("SEND");
        sendMsg.addActionListener(this);
        final JCheckBox alertCheck = new JCheckBox("Also send an alert sound", false);
        final JPanel horizontalPanel2 = CompUtil.createHorizontalPanel(false);
        horizontalPanel2.add(Box.createRigidArea(new Dimension(2, 1)));
        horizontalPanel2.add(sendMsg);
        horizontalPanel2.add(Box.createRigidArea(new Dimension(8, 1)));
        horizontalPanel2.add(alertCheck);
        final JRadioButton radioButton = new JRadioButton("To the highlighted user(private chat)");
        radioButton.setActionCommand("UNAME");
        radioButton.addActionListener(this);
        radioButton.setSelected(true);
        final JRadioButton radioButton2 = new JRadioButton("To the entire room");
        radioButton2.setActionCommand("ROOM");
        radioButton2.addActionListener(this);
        radioButton2.setSelected(false);
        final ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radioButton);
        radioGroup.add(radioButton2);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 3)));
        verticalPanel.add(horizontalPanel2);
        verticalPanel.add(radioButton);
        verticalPanel.add(radioButton2);
        this.sendMsg = sendMsg;
        this.alertCheck = alertCheck;
        this.radioGroup = radioGroup;
        this.textInput = textInput;
        return verticalPanel;
    }
}
