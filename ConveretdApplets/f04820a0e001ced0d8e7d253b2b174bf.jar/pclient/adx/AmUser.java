// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import pclient.shd.Config;
import pclient.adv.TextFieldCopy;
import java.awt.FlowLayout;
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
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class AmUser extends JPanel implements ActionListener
{
    protected JButton gagOn;
    protected JButton gagOff;
    protected JButton expelUser;
    protected JCheckBox banIPCheck;
    protected JCheckBox banIPRange;
    protected JButton cookieGag;
    protected JButton cookieBan;
    protected JButton invisibleOn;
    protected JButton invisibleOff;
    protected JButton avatarOn;
    protected JButton avatarOff;
    protected JButton topOn;
    protected JButton topOff;
    protected JTextField unbanIPInput;
    protected JButton unbanIPButton;
    protected JButton banNameButton;
    protected JTextField unbanNameInput;
    protected JButton unbanNameButton;
    private static final String ACT_FRESH = "ref";
    private static final String ACT_INFO = "inf";
    private static final String ACT_GAG = "gag";
    private static final String ACT_UNGAG = "ung";
    private static final String ACT_EXPEL = "exp";
    private static final String ACT_CGAG = "cg";
    private static final String ACT_CBAN = "cb";
    private static final String ACT_UNIP = "uip";
    private static final String ACT_BANU = "bu";
    private static final String ACT_UNU = "uu";
    private static final String U_INV = "In";
    private static final String U_VIS = "Vis";
    private static final String U_AV = "Av";
    private static final String U_AV_N = "AvN";
    private static final String U_TP = "Tp";
    private static final String U_TP_N = "TpN";
    private JList userNameList;
    private DefaultListModel userNameModel;
    private JComboBox choiceDays;
    private JComboBox gagDays;
    private static final String D_ONE = "1 day(24 hours)";
    private static final String D_SEVEN = "7 days";
    private static final String D_365 = "52 weeks";
    private String[] cookieDays;
    private AdmHandler adminHandler;
    private EmptyBorder border5;
    private EmptyBorder border10;
    
    public AmUser(final AdmHandler adminHandler) {
        this.cookieDays = new String[] { "1 day(24 hours)", "7 days", "52 weeks" };
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
        if ("ref".equals(actionCommand)) {
            this.refresh();
        }
        else if ("inf".equals(actionCommand)) {
            final String selectedUser = this.selectedUser();
            if (selectedUser != null) {
                this.adminHandler.userInfo(selectedUser);
            }
        }
        else if ("gag".equals(actionCommand)) {
            final String selectedUser2 = this.selectedUser();
            if (selectedUser2 != null) {
                this.adminHandler.gag(selectedUser2);
            }
        }
        else if ("ung".equals(actionCommand)) {
            final String selectedUser3 = this.selectedUser();
            if (selectedUser3 != null) {
                this.adminHandler.ungag(selectedUser3);
            }
        }
        else if ("exp".equals(actionCommand)) {
            final String selectedUser4 = this.selectedUser();
            if (selectedUser4 != null) {
                this.adminHandler.expel(selectedUser4, this.banIPCheck.isSelected(), this.banIPRange.isSelected());
            }
        }
        else if ("In".equals(actionCommand)) {
            this.adminHandler.invisibleOn();
        }
        else if ("Vis".equals(actionCommand)) {
            this.adminHandler.invisibleOff();
        }
        else if ("Av".equals(actionCommand)) {
            this.adminHandler.avatarOn();
        }
        else if ("AvN".equals(actionCommand)) {
            this.adminHandler.avatarOff();
        }
        else if ("Tp".equals(actionCommand)) {
            this.adminHandler.topListOn();
        }
        else if ("TpN".equals(actionCommand)) {
            this.adminHandler.topListOff();
        }
        else if ("cb".equals(actionCommand)) {
            final String selectedUser5 = this.selectedUser();
            final Object selectedItem = this.choiceDays.getSelectedItem();
            if (selectedItem != null && selectedUser5 != null) {
                int n = 1;
                if ("1 day(24 hours)".equals(selectedItem)) {
                    n = 1;
                }
                else if ("7 days".equals(selectedItem)) {
                    n = 7;
                }
                else if ("52 weeks".equals(selectedItem)) {
                    n = 365;
                }
                this.adminHandler.cookieBan(selectedUser5, n);
            }
        }
        else if ("cg".equals(actionCommand)) {
            final String selectedUser6 = this.selectedUser();
            final Object selectedItem2 = this.choiceDays.getSelectedItem();
            if (selectedItem2 != null && selectedUser6 != null) {
                int n2 = 1;
                if ("1 day(24 hours)".equals(selectedItem2)) {
                    n2 = 1;
                }
                else if ("7 days".equals(selectedItem2)) {
                    n2 = 7;
                }
                else if ("52 weeks".equals(selectedItem2)) {
                    n2 = 365;
                }
                this.adminHandler.cookieGag(selectedUser6, n2);
            }
        }
        else if ("uip".equals(actionCommand)) {
            final String text = this.unbanIPInput.getText();
            if (StringUtil.isEmpty(text)) {
                this.adminHandler.error("Unban IP: IP is empty.");
                return;
            }
            this.adminHandler.unbanIP(text);
        }
        else if ("uu".equals(actionCommand)) {
            final String text2 = this.unbanNameInput.getText();
            if (StringUtil.isEmpty(text2)) {
                this.adminHandler.error("Unban Name: User name is empty.");
                return;
            }
            this.adminHandler.unbanUser(text2);
        }
        else if ("bu".equals(actionCommand)) {
            final String selectedUser7 = this.selectedUser();
            if (selectedUser7 != null) {
                this.adminHandler.banUser(selectedUser7);
            }
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
        tabbedPane.addTab("Expel/Ban/Gag", null, this.createUser(), "User Admin");
        tabbedPane.addTab("Un-Ban", null, this.createUnban(), "Un-Ban IP or User Name");
        tabbedPane.addTab("Admin Settings", null, this.createMyUser(), "Admin User's Options");
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
        final JLabel label = new JLabel("Click Refresh button to see users.");
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
        verticalPanel.add(this.getGag());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 6)));
        verticalPanel.add(this.getExpel());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 2)));
        verticalPanel.add(this.getBanUser());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(this.getCookie());
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
        button.setActionCommand("ref");
        button.addActionListener(this);
        final JButton button2 = new JButton("User Info");
        button2.setActionCommand("inf");
        button2.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.setAlignmentX(0.5f);
        horizontalPanel.add(button);
        horizontalPanel.add(button2);
        return horizontalPanel;
    }
    
    private JComponent getGag() {
        final JLabel label = new JLabel("<html><p>Click Gag to ignore all messages from that user.</p></html>");
        final JButton gagOn = new JButton("Gag");
        gagOn.setActionCommand("gag");
        gagOn.addActionListener(this);
        final JLabel label2 = new JLabel("<html><p>Click Ungag to remove the gag on that user.</p></html>");
        final JButton gagOff = new JButton("Ungag");
        gagOff.setActionCommand("ung");
        gagOff.addActionListener(this);
        if (this.adminHandler.theMan.isAdvDisabled("Cons.History")) {
            gagOn.setEnabled(false);
            gagOff.setEnabled(false);
        }
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Gag", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(gagOn);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 8)));
        verticalPanel.add(label2);
        verticalPanel.add(gagOff);
        this.gagOn = gagOn;
        this.gagOff = gagOff;
        return verticalPanel;
    }
    
    private JComponent getExpel() {
        final JLabel label = new JLabel("<html><p>Select a user to expel.</p></html>");
        final JButton expelUser = new JButton("Expel");
        expelUser.setActionCommand("exp");
        expelUser.addActionListener(this);
        final JCheckBox banIPCheck = new JCheckBox("Also ban the IP", false);
        final JCheckBox banIPRange = new JCheckBox("Also ban the entire C class IP range", false);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Expel", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 2)));
        verticalPanel.add(expelUser);
        verticalPanel.add(banIPCheck);
        verticalPanel.add(banIPRange);
        this.banIPCheck = banIPCheck;
        this.banIPRange = banIPRange;
        this.expelUser = expelUser;
        return verticalPanel;
    }
    
    private JComponent getCookie() {
        new JLabel("Select a user to ban.").setHorizontalAlignment(2);
        final JPanel ckBan = this.createCkBan();
        final JPanel ckGag = this.createCkGag();
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Ban", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(ckGag);
        verticalPanel.add(ckBan);
        return verticalPanel;
    }
    
    private JPanel createCkBan() {
        final JButton cookieBan = new JButton("Cookie Ban");
        cookieBan.setActionCommand("cb");
        cookieBan.addActionListener(this);
        final JComboBox choiceDays = new JComboBox((E[])this.cookieDays);
        choiceDays.setEditable(false);
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(0);
        final JPanel panel = new JPanel(flowLayout);
        panel.add(cookieBan);
        panel.add(choiceDays);
        if (this.adminHandler.theMan.isAdvDisabled("")) {
            cookieBan.setEnabled(false);
        }
        this.cookieBan = cookieBan;
        this.choiceDays = choiceDays;
        return panel;
    }
    
    private JPanel createCkGag() {
        final JButton cookieGag = new JButton("Cookie Gag");
        cookieGag.setActionCommand("cg");
        cookieGag.addActionListener(this);
        final JComboBox gagDays = new JComboBox((E[])this.cookieDays);
        gagDays.setEditable(false);
        final FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(0);
        final JPanel panel = new JPanel(flowLayout);
        panel.add(cookieGag);
        panel.add(gagDays);
        if (this.adminHandler.theMan.isAdvDisabled("")) {
            cookieGag.setEnabled(false);
        }
        this.cookieGag = cookieGag;
        this.gagDays = gagDays;
        return panel;
    }
    
    private JComponent createUnban() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(this.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        verticalPanel.add(this.getUnbanIP());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        verticalPanel.add(this.getUnbanName());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        return panel;
    }
    
    private JComponent getBanUser() {
        final JButton banNameButton = new JButton("Ban User Name");
        banNameButton.setActionCommand("bu");
        banNameButton.addActionListener(this);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Ban User Name", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(banNameButton);
        if (this.adminHandler.theMan.isAdvDisabled("")) {
            banNameButton.setEnabled(false);
        }
        this.banNameButton = banNameButton;
        return verticalPanel;
    }
    
    private JComponent getUnbanIP() {
        final JLabel label = new JLabel("<html><p>Type the IP you want to unban below</p></html>");
        final TextFieldCopy unbanIPInput = new TextFieldCopy((Config)null);
        unbanIPInput.setColumns(32);
        unbanIPInput.setPreferredSize(new Dimension(620, 24));
        unbanIPInput.setMaximumSize(new Dimension(1200, 24));
        final JButton unbanIPButton = new JButton("Unban IP");
        unbanIPButton.setActionCommand("uip");
        unbanIPButton.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.add(unbanIPInput);
        horizontalPanel.add(Box.createRigidArea(new Dimension(6, 1)));
        horizontalPanel.add(unbanIPButton);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Unban IP", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(horizontalPanel);
        this.unbanIPButton = unbanIPButton;
        this.unbanIPInput = unbanIPInput;
        return verticalPanel;
    }
    
    private JComponent getUnbanName() {
        final JLabel label = new JLabel("<html><p>Type the user name you want to unban below</p></html>");
        final TextFieldCopy unbanNameInput = new TextFieldCopy((Config)null);
        unbanNameInput.setColumns(32);
        unbanNameInput.setPreferredSize(new Dimension(620, 24));
        unbanNameInput.setMaximumSize(new Dimension(1200, 24));
        final JButton unbanNameButton = new JButton("Unban User");
        unbanNameButton.setActionCommand("uu");
        unbanNameButton.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.add(unbanNameInput);
        horizontalPanel.add(Box.createRigidArea(new Dimension(6, 1)));
        horizontalPanel.add(unbanNameButton);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Unban User Name", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(horizontalPanel);
        if (this.adminHandler.theMan.isAdvDisabled("")) {
            unbanNameButton.setEnabled(false);
        }
        this.unbanNameButton = unbanNameButton;
        this.unbanNameInput = unbanNameInput;
        return verticalPanel;
    }
    
    private JComponent createMyUser() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(this.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(this.getInvisible());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        verticalPanel.add(this.getAdminAvatar());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        verticalPanel.add(this.getTopList());
        return panel;
    }
    
    private JComponent getInvisible() {
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        final JLabel label = new JLabel("Click On button to keep login invisible always.");
        final JButton invisibleOn = new JButton("On");
        invisibleOn.setActionCommand("In");
        invisibleOn.addActionListener(this);
        horizontalPanel.add(invisibleOn);
        horizontalPanel.add(Box.createRigidArea(new Dimension(4, 1)));
        horizontalPanel.add(label);
        final JPanel horizontalPanel2 = CompUtil.createHorizontalPanel(false);
        final JLabel label2 = new JLabel("Click Off button to remove invisible login.");
        final JButton invisibleOff = new JButton("Off");
        invisibleOff.setActionCommand("Vis");
        invisibleOff.addActionListener(this);
        horizontalPanel2.add(invisibleOff);
        horizontalPanel2.add(Box.createRigidArea(new Dimension(4, 1)));
        horizontalPanel2.add(label2);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Invisible Log-in", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(horizontalPanel2);
        this.invisibleOn = invisibleOn;
        this.invisibleOff = invisibleOff;
        return verticalPanel;
    }
    
    private JComponent getAdminAvatar() {
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        final JLabel label = new JLabel("Click On button to user admin avatar always.");
        final JButton avatarOn = new JButton("On");
        avatarOn.setActionCommand("Av");
        avatarOn.addActionListener(this);
        horizontalPanel.add(avatarOn);
        horizontalPanel.add(Box.createRigidArea(new Dimension(4, 1)));
        horizontalPanel.add(label);
        final JPanel horizontalPanel2 = CompUtil.createHorizontalPanel(false);
        final JLabel label2 = new JLabel("Click Off button to remove admin avatar.");
        final JButton avatarOff = new JButton("Off");
        avatarOff.setActionCommand("AvN");
        avatarOff.addActionListener(this);
        horizontalPanel2.add(avatarOff);
        horizontalPanel2.add(Box.createRigidArea(new Dimension(4, 1)));
        horizontalPanel2.add(label2);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Admin User Avatar", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(horizontalPanel2);
        this.avatarOn = avatarOn;
        this.avatarOff = avatarOff;
        return verticalPanel;
    }
    
    private JComponent getTopList() {
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        final JLabel label = new JLabel("Click On button to show your name on top of user list.");
        final JButton topOn = new JButton("On");
        topOn.setActionCommand("Tp");
        topOn.addActionListener(this);
        horizontalPanel.add(topOn);
        horizontalPanel.add(Box.createRigidArea(new Dimension(4, 1)));
        horizontalPanel.add(label);
        final JPanel horizontalPanel2 = CompUtil.createHorizontalPanel(false);
        final JLabel label2 = new JLabel("Click Off button to show your name alphabetically.");
        final JButton topOff = new JButton("Off");
        topOff.setActionCommand("TpN");
        topOff.addActionListener(this);
        horizontalPanel2.add(topOff);
        horizontalPanel2.add(Box.createRigidArea(new Dimension(4, 1)));
        horizontalPanel2.add(label2);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Stay On Top of User List", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(horizontalPanel2);
        this.topOn = topOn;
        this.topOff = topOff;
        return verticalPanel;
    }
}
