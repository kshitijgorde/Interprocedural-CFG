// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import pclient.shd.Config;
import pclient.adv.TextFieldCopy;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JSplitPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
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
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class AmSuper extends JPanel implements ActionListener
{
    private static final String ACT_FRESH = "refresh";
    private static final String ACT_INFO = "info";
    private static final String ACT_EXPEL = "expel";
    private static final String ACT_S_EXPEL = "Sexpel";
    private static final String ACT_S_BAN = "SBAN";
    private static final String ACT_SEND = "send";
    private JCheckBox banIPCheck;
    private JCheckBox banIPRange;
    private JCheckBox serverBanIPCheck;
    private JCheckBox serverBanIPRange;
    private JTextField castTextInput;
    private JList userNameList;
    private DefaultListModel userNameModel;
    private AdmHandler adminHandler;
    private EmptyBorder border5;
    private EmptyBorder border10;
    
    public AmSuper(final AdmHandler adminHandler) {
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
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.adminHandler.paraApplet.paraConf.printer().print("command=" + actionCommand);
        if ("refresh".equals(actionCommand)) {
            this.refresh();
        }
        else if ("info".equals(actionCommand)) {
            final String selectedUser = this.selectedUser();
            if (selectedUser != null) {
                this.adminHandler.userInfo(selectedUser);
            }
        }
        else if ("expel".equals(actionCommand)) {
            final String selectedUser2 = this.selectedUser();
            if (selectedUser2 != null) {
                this.adminHandler.expel(selectedUser2, this.banIPCheck.isSelected(), this.banIPRange.isSelected());
            }
        }
        else if ("Sexpel".equals(actionCommand)) {
            final String selectedUser3 = this.selectedUser();
            if (selectedUser3 != null) {
                this.adminHandler.expelServer(selectedUser3, this.serverBanIPCheck.isSelected(), this.serverBanIPRange.isSelected());
            }
        }
        else if ("SBAN".equals(actionCommand)) {
            this.adminHandler.banRoom();
        }
        else if ("send".equals(actionCommand)) {
            final String text = this.castTextInput.getText();
            if (StringUtil.isTrimmedEmpty(text)) {
                this.adminHandler.error("Message is empty.");
                return;
            }
            this.adminHandler.serverCast(text);
        }
    }
    
    private String selectedUser() {
        final int selectedIndex = this.userNameList.getSelectedIndex();
        if (selectedIndex < 0) {
            this.adminHandler.info("please select a user first.");
            return null;
        }
        return (String)this.userNameModel.get(selectedIndex);
    }
    
    private void buildUI() {
        this.setLayout(new BorderLayout());
        final JTabbedPane tabbedPane = new JTabbedPane();
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.add(tabbedPane);
        tabbedPane.addTab("User", null, this.createUser(), "User Admin");
        tabbedPane.addTab("Message", null, this.createMessage(), "Send server wide messages");
        this.add(panel, "Center");
    }
    
    private JComponent createUser() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(this.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(this.getCenter());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 2)));
        return panel;
    }
    
    private JComponent getCenter() {
        final JLabel label = new JLabel("<html><p>" + "This area is reserved for super users only.<br> Click Refresh button to see users." + "</p></html>");
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Users in Room", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 3)));
        verticalPanel.add(this.getBottom());
        return verticalPanel;
    }
    
    private JComponent getBottom() {
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(this.getExpel());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 8)));
        verticalPanel.add(this.getExpelServer());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 8)));
        verticalPanel.add(this.getRoomServer());
        final JSplitPane splitPane = new JSplitPane(1, this.getList(), verticalPanel);
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerLocation(160);
        return splitPane;
    }
    
    private JComponent getList() {
        final DefaultListModel<Object> userNameModel = new DefaultListModel<Object>();
        final JList userNameList = new JList(userNameModel);
        userNameList.setVisibleRowCount(32);
        final JScrollPane scrollPane = new JScrollPane(userNameList, 20, 30);
        scrollPane.setMinimumSize(new Dimension(100, 240));
        scrollPane.setPreferredSize(new Dimension(120, 260));
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(scrollPane);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 2)));
        verticalPanel.add(this.getButtons());
        this.userNameList = userNameList;
        this.userNameModel = userNameModel;
        return verticalPanel;
    }
    
    private JComponent getButtons() {
        final JButton button = new JButton("Refresh");
        button.setActionCommand("refresh");
        button.addActionListener(this);
        final JButton button2 = new JButton("User Info");
        button2.setActionCommand("info");
        button2.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.setAlignmentX(0.5f);
        horizontalPanel.add(button);
        horizontalPanel.add(button2);
        return horizontalPanel;
    }
    
    private JComponent getExpel() {
        final JLabel label = new JLabel("<html><p>Select a user to expel.</p></html>");
        final JButton button = new JButton("Expel");
        button.setActionCommand("expel");
        button.addActionListener(this);
        final JCheckBox banIPCheck = new JCheckBox("Also ban the IP", false);
        final JCheckBox banIPRange = new JCheckBox("Also ban the entire C class IP range", false);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Expel", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(button);
        verticalPanel.add(banIPCheck);
        verticalPanel.add(banIPRange);
        this.banIPCheck = banIPCheck;
        this.banIPRange = banIPRange;
        return verticalPanel;
    }
    
    private JComponent getExpelServer() {
        final JLabel label = new JLabel("<html><p>Select a user to expel at server level.</p></html>");
        final JButton button = new JButton("Expel");
        button.setActionCommand("Sexpel");
        button.addActionListener(this);
        final JCheckBox serverBanIPCheck = new JCheckBox("Also ban the IP at server level", false);
        final JCheckBox serverBanIPRange = new JCheckBox("Also ban C class IP range at server level", false);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Expel at Server Level", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(button);
        verticalPanel.add(serverBanIPCheck);
        verticalPanel.add(serverBanIPRange);
        this.serverBanIPCheck = serverBanIPCheck;
        this.serverBanIPRange = serverBanIPRange;
        return verticalPanel;
    }
    
    private JComponent getRoomServer() {
        final JLabel label = new JLabel("<html><p>Ban room name at server level.</p></html>");
        final JButton button = new JButton("Ban Room Name");
        button.setActionCommand("SBAN");
        button.addActionListener(this);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Ban Room Name", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(button);
        return verticalPanel;
    }
    
    private JComponent createMessage() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(this.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(this.getMessage());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        return panel;
    }
    
    private JComponent getMessage() {
        final JLabel label = new JLabel("<html><p>" + "This area is reserved for super users only.<br> Send a message to all active rooms of the entire chat server." + "</p></html>");
        final TextFieldCopy castTextInput = new TextFieldCopy((Config)null);
        castTextInput.setColumns(32);
        castTextInput.setPreferredSize(new Dimension(620, 24));
        castTextInput.setMaximumSize(new Dimension(1200, 24));
        final JButton button = new JButton("Send");
        button.setActionCommand("send");
        button.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.add(castTextInput);
        horizontalPanel.add(Box.createRigidArea(new Dimension(6, 1)));
        horizontalPanel.add(button);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Broadcast", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(horizontalPanel);
        this.castTextInput = castTextInput;
        return verticalPanel;
    }
}
