// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JSplitPane;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import pclient.adv.CompUtil;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.Icon;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.event.MouseEvent;
import com.pchat.sc.StringUtil;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class AmMod extends JPanel implements ActionListener, MouseListener
{
    protected JButton moderationOn;
    protected JButton moderationOff;
    protected JButton addModerator;
    protected JButton delModerator;
    protected JButton showModerator;
    protected JButton addSpeaker;
    protected JButton delSpeaker;
    protected JButton showSpeaker;
    private static final String ACT_FRESH = "refresh";
    private static final String ACT_ON = "ON";
    private static final String ACT_OFF = "off";
    private static final String ACT_M_ADD = "madd";
    private static final String ACT_M_DEL = "MDEL";
    private static final String ACT_M_SHOW = "MSHO";
    private static final String ACT_S_ADD = "Sadd";
    private static final String ACT_S_DEL = "SDEL";
    private static final String ACT_S_SHOW = "SSHO";
    private JList userNameList;
    private DefaultListModel userNameModel;
    private JTextField modInput;
    private JTextField speakerInput;
    private AdmHandler adminHandler;
    private EmptyBorder border5;
    private EmptyBorder border10;
    
    public AmMod(final AdmHandler adminHandler) {
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
        this.adminHandler.paraApplet.paraConf.printer().print("com=" + actionCommand);
        if ("refresh".equals(actionCommand)) {
            this.refresh();
        }
        else if ("ON".equals(actionCommand)) {
            this.adminHandler.setModeration(true);
        }
        else if ("off".equals(actionCommand)) {
            if (this.confirmModOff()) {
                this.adminHandler.setModeration(false);
            }
        }
        else if ("madd".equals(actionCommand)) {
            final String text = this.modInput.getText();
            if (StringUtil.isTrimmedEmpty(text)) {
                this.adminHandler.error("Please select a user, or enter a name.");
                return;
            }
            this.adminHandler.addModerator(text);
        }
        else if ("MDEL".equals(actionCommand)) {
            final String text2 = this.modInput.getText();
            if (StringUtil.isTrimmedEmpty(text2)) {
                this.adminHandler.error("Please select a user, or enter a name.");
                return;
            }
            this.adminHandler.delModerator(text2);
        }
        else if ("MSHO".equals(actionCommand)) {
            this.adminHandler.showModerators();
        }
        else if ("Sadd".equals(actionCommand)) {
            final String text3 = this.speakerInput.getText();
            if (StringUtil.isTrimmedEmpty(text3)) {
                this.adminHandler.error("Please select a user, or enter a name.");
                return;
            }
            this.adminHandler.addSpeaker(text3);
        }
        else if ("SDEL".equals(actionCommand)) {
            final String text4 = this.speakerInput.getText();
            if (StringUtil.isTrimmedEmpty(text4)) {
                this.adminHandler.error("Please select a user, or enter a name.");
                return;
            }
            this.adminHandler.delSpeaker(text4);
        }
        else if ("SSHO".equals(actionCommand)) {
            this.adminHandler.showSpeakers();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() != 1) {
            return;
        }
        final int selectedIndex = this.userNameList.getSelectedIndex();
        if (selectedIndex < 0) {
            return;
        }
        final String s = this.userNameModel.get(selectedIndex);
        this.modInput.setText(s);
        this.speakerInput.setText(s);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    private String selectedUser() {
        final int selectedIndex = this.userNameList.getSelectedIndex();
        if (selectedIndex < 0) {
            this.adminHandler.info("please select a user first.");
            return null;
        }
        return (String)this.userNameModel.get(selectedIndex);
    }
    
    private boolean confirmModOff() {
        return JOptionPane.showConfirmDialog(this.modInput, "All stored event questions will be permanently deleted when moderation is turned off.  Would you like to proceed?", " ", 0) == 0;
    }
    
    private void buildUI() {
        this.setLayout(new BorderLayout());
        final JTabbedPane tabbedPane = new JTabbedPane();
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.add(tabbedPane);
        tabbedPane.addTab("Moderation", null, this.createMod(), "Manage Moderation Features");
        this.add(panel, "Center");
    }
    
    private JComponent createMod() {
        final JLabel label = new JLabel("<html><p>Here you may select a user and assign the user a role in moderation. You can also turn on or turn off moderation.</p></html>");
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(this.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Moderated Chat", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 3)));
        verticalPanel.add(this.getCenter());
        panel.add(verticalPanel);
        return panel;
    }
    
    private JComponent getCenter() {
        final JSplitPane splitPane = new JSplitPane(1, this.getList(), this.getControl());
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerLocation(100);
        return splitPane;
    }
    
    private JComponent getList() {
        final DefaultListModel<Object> userNameModel = new DefaultListModel<Object>();
        final JList userNameList = new JList(userNameModel);
        userNameList.setVisibleRowCount(32);
        userNameList.setSelectionMode(0);
        userNameList.addMouseListener(this);
        final JScrollPane scrollPane = new JScrollPane(userNameList, 20, 30);
        scrollPane.setMinimumSize(new Dimension(80, 240));
        scrollPane.setPreferredSize(new Dimension(90, 260));
        final JButton button = new JButton("Refresh");
        button.setAlignmentX(0.5f);
        button.setActionCommand("refresh");
        button.addActionListener(this);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(scrollPane);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 2)));
        verticalPanel.add(button);
        this.userNameList = userNameList;
        this.userNameModel = userNameModel;
        return verticalPanel;
    }
    
    private JComponent getControl() {
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(this.getOnOff());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 26)));
        verticalPanel.add(this.getModerator());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 26)));
        verticalPanel.add(this.getSpeaker());
        return verticalPanel;
    }
    
    private JComponent getOnOff() {
        final JLabel label = new JLabel("Click On button to turn on moderation.");
        final JButton moderationOn = new JButton("On");
        moderationOn.setActionCommand("ON");
        moderationOn.addActionListener(this);
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.add(moderationOn);
        horizontalPanel.add(Box.createRigidArea(new Dimension(4, 1)));
        horizontalPanel.add(label);
        final JLabel label2 = new JLabel("Click Off button to turn off moderation.");
        final JButton moderationOff = new JButton("Off");
        moderationOff.setActionCommand("off");
        moderationOff.addActionListener(this);
        if (this.adminHandler.theMan.isAdvDisabled("Cons.Mod")) {
            moderationOn.setEnabled(false);
            moderationOff.setEnabled(false);
        }
        final JPanel horizontalPanel2 = CompUtil.createHorizontalPanel(false);
        horizontalPanel2.add(moderationOff);
        horizontalPanel2.add(Box.createRigidArea(new Dimension(4, 1)));
        horizontalPanel2.add(label2);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "On/Off", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.setAlignmentX(0.0f);
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(horizontalPanel2);
        this.moderationOn = moderationOn;
        this.moderationOff = moderationOff;
        return verticalPanel;
    }
    
    private JComponent getModerator() {
        final JLabel label = new JLabel("<html><p>Select a user to add or delete as a moderator.</p></html>");
        label.setAlignmentX(0.5f);
        final JTextField modInput = new JTextField(16);
        modInput.setPreferredSize(new Dimension(120, 24));
        modInput.setMaximumSize(new Dimension(1200, 24));
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Moderator", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(modInput);
        verticalPanel.add(this.getModButtons());
        this.modInput = modInput;
        return verticalPanel;
    }
    
    private JComponent getModButtons() {
        final JButton addModerator = new JButton(" Add ");
        addModerator.setActionCommand("madd");
        addModerator.addActionListener(this);
        final JButton delModerator = new JButton("Delete");
        delModerator.setActionCommand("MDEL");
        delModerator.addActionListener(this);
        final JButton showModerator = new JButton("Show Moderators");
        showModerator.setActionCommand("MSHO");
        showModerator.addActionListener(this);
        if (this.adminHandler.theMan.isAdvDisabled("Cons.Mod")) {
            addModerator.setEnabled(false);
        }
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.setAlignmentX(0.5f);
        horizontalPanel.add(addModerator);
        horizontalPanel.add(delModerator);
        horizontalPanel.add(showModerator);
        this.addModerator = addModerator;
        this.delModerator = delModerator;
        this.showModerator = showModerator;
        return horizontalPanel;
    }
    
    private JComponent getSpeaker() {
        final JLabel label = new JLabel("<html><p>Select a user to add or delete as a speaker.</p></html>");
        label.setAlignmentX(0.5f);
        final JTextField speakerInput = new JTextField(16);
        speakerInput.setPreferredSize(new Dimension(120, 24));
        speakerInput.setMaximumSize(new Dimension(1200, 24));
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Speaker", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(speakerInput);
        verticalPanel.add(this.getSpeakerButtons());
        this.speakerInput = speakerInput;
        return verticalPanel;
    }
    
    private JComponent getSpeakerButtons() {
        final JButton addSpeaker = new JButton("Add");
        addSpeaker.setActionCommand("Sadd");
        addSpeaker.addActionListener(this);
        final JButton delSpeaker = new JButton("Delete");
        delSpeaker.setActionCommand("SDEL");
        delSpeaker.addActionListener(this);
        final JButton showSpeaker = new JButton("Show Speakers");
        showSpeaker.setActionCommand("SSHO");
        showSpeaker.addActionListener(this);
        if (this.adminHandler.theMan.isAdvDisabled("Cons.Mod")) {
            addSpeaker.setEnabled(false);
        }
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.setAlignmentX(0.5f);
        horizontalPanel.add(addSpeaker);
        horizontalPanel.add(delSpeaker);
        horizontalPanel.add(showSpeaker);
        this.addSpeaker = addSpeaker;
        this.delSpeaker = delSpeaker;
        this.showSpeaker = showSpeaker;
        return horizontalPanel;
    }
}
