// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class AmRoom extends JPanel implements ActionListener
{
    protected JButton topicButton;
    protected JButton openButton;
    protected JButton closeButton;
    protected JButton setPasswd;
    protected JButton removePasswd;
    protected JButton transcriptOn;
    protected JButton transcriptOff;
    protected JButton clearHistory;
    protected JButton profaneOn;
    protected JButton profaneOff;
    protected JButton shoutOn;
    protected JButton shoutOff;
    private static final String ACT_FRESH = "refresh";
    private static final String ACT_TOPIC = "topic";
    private static final String ACT_OPEN = "open";
    private static final String ACT_CLOSE = "close";
    private static final String ACT_PASS = "pass";
    private static final String ACT_REMOVE = "remove";
    private static final String ACT_TS_ON = "ts_on";
    private static final String ACT_TS_OFF = "ts_off";
    private static final String ACT_P_ON = "p_on";
    private static final String ACT_P_OFF = "p_off";
    private static final String ACT_S_ON = "s_on";
    private static final String ACT_S_OFF = "s_off";
    private static final String ACT_CL_PB = "Cp";
    private JCheckBox emptyRoomCheck;
    private JTextField topicTextInput;
    private JTextField reasonInput;
    private JTextField passwordInput;
    private JTextArea statusTextArea;
    private AdmHandler adminHandler;
    private EmptyBorder border5;
    private EmptyBorder border10;
    
    public AmRoom(final AdmHandler adminHandler) {
        this.border5 = new EmptyBorder(5, 5, 5, 5);
        this.border10 = new EmptyBorder(10, 10, 10, 10);
        this.adminHandler = adminHandler;
        this.buildUI();
    }
    
    public void setStatus(final String text) {
        this.statusTextArea.setText(text);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.adminHandler.paraApplet.paraConf.printer().print("command=" + actionCommand);
        if ("refresh".equals(actionCommand)) {
            this.adminHandler.roomStatus();
        }
        else if ("topic".equals(actionCommand)) {
            final String text = this.topicTextInput.getText();
            if (StringUtil.isEmpty(text)) {
                this.adminHandler.error("Topic is empty.");
                return;
            }
            this.adminHandler.setTopic(text);
        }
        else if ("open".equals(actionCommand)) {
            this.adminHandler.openRoom();
        }
        else if ("close".equals(actionCommand)) {
            this.adminHandler.closeRoom(this.reasonInput.getText(), this.emptyRoomCheck.isSelected());
        }
        else if ("pass".equals(actionCommand)) {
            final String text2 = this.passwordInput.getText();
            if (StringUtil.isEmpty(text2)) {
                this.adminHandler.error("Room password is empty.");
                return;
            }
            this.adminHandler.roomPasswd(text2);
        }
        else if ("remove".equals(actionCommand)) {
            this.adminHandler.removeRoomPasswd();
        }
        else if ("ts_on".equals(actionCommand)) {
            this.adminHandler.transcript(true);
        }
        else if ("ts_off".equals(actionCommand)) {
            this.adminHandler.transcript(false);
        }
        else if ("p_on".equals(actionCommand)) {
            this.adminHandler.profanityFilter(true);
        }
        else if ("p_off".equals(actionCommand)) {
            this.adminHandler.profanityFilter(false);
        }
        else if ("s_on".equals(actionCommand)) {
            this.adminHandler.shoutFilter(true);
        }
        else if ("s_off".equals(actionCommand)) {
            this.adminHandler.shoutFilter(false);
        }
        else if ("Cp".equals(actionCommand)) {
            this.adminHandler.clearPlayback();
        }
    }
    
    private void buildUI() {
        this.setLayout(new BorderLayout());
        final JTabbedPane tabbedPane = new JTabbedPane();
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.add(tabbedPane);
        tabbedPane.addTab("Status", null, this.createStatus(), "Room Status");
        tabbedPane.addTab("Basic", null, this.createBasic(), "Basic admin of room");
        tabbedPane.addTab("Transcript", null, this.createTranscript(), "Configure room transcript.");
        tabbedPane.addTab("Filter", null, this.createFilter(), "Manage message filters.");
        this.add(panel, "Center");
    }
    
    private JComponent createBasic() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(this.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(this.getTopic());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        verticalPanel.add(this.getOpen());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        verticalPanel.add(this.getClose());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        verticalPanel.add(this.getPasswd());
        return panel;
    }
    
    private JComponent getTopic() {
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Room Greeting", 1, 2), this.border5));
        final JTextField topicTextInput = new JTextField(16);
        topicTextInput.setPreferredSize(new Dimension(480, 24));
        topicTextInput.setMaximumSize(new Dimension(1200, 24));
        horizontalPanel.add(topicTextInput);
        horizontalPanel.add(Box.createRigidArea(new Dimension(10, 1)));
        final JButton topicButton = new JButton("Set");
        topicButton.setActionCommand("topic");
        topicButton.addActionListener(this);
        horizontalPanel.add(topicButton);
        this.topicButton = topicButton;
        this.topicTextInput = topicTextInput;
        return horizontalPanel;
    }
    
    private JComponent getOpen() {
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Open Room", 1, 2), this.border5));
        horizontalPanel.add(new JLabel("Click the button to open this room if it is closed."));
        horizontalPanel.add(Box.createRigidArea(new Dimension(10, 1)));
        final JButton openButton = new JButton("Open Room");
        openButton.setActionCommand("open");
        openButton.addActionListener(this);
        horizontalPanel.add(openButton);
        this.openButton = openButton;
        return horizontalPanel;
    }
    
    private JComponent getClose() {
        final JLabel label = new JLabel("Enter a short message for closing this room");
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        final JTextField reasonInput = new JTextField(16);
        reasonInput.setPreferredSize(new Dimension(480, 24));
        reasonInput.setMaximumSize(new Dimension(1200, 24));
        horizontalPanel.add(reasonInput);
        horizontalPanel.add(Box.createRigidArea(new Dimension(10, 1)));
        final JButton closeButton = new JButton("Close Room");
        horizontalPanel.add(closeButton);
        closeButton.setActionCommand("close");
        closeButton.addActionListener(this);
        final JCheckBox emptyRoomCheck = new JCheckBox("Also Empty Room", false);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Close Room", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(emptyRoomCheck);
        this.closeButton = closeButton;
        this.reasonInput = reasonInput;
        this.emptyRoomCheck = emptyRoomCheck;
        return verticalPanel;
    }
    
    private JComponent getPasswd() {
        final JLabel label = new JLabel("Enter a password and click Set to set a room password.");
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        final JTextField passwordInput = new JTextField(16);
        passwordInput.setPreferredSize(new Dimension(480, 24));
        passwordInput.setMaximumSize(new Dimension(1200, 24));
        horizontalPanel.add(passwordInput);
        horizontalPanel.add(Box.createRigidArea(new Dimension(10, 1)));
        final JButton setPasswd = new JButton(" Set ");
        horizontalPanel.add(setPasswd);
        setPasswd.setActionCommand("pass");
        setPasswd.addActionListener(this);
        final JPanel horizontalPanel2 = CompUtil.createHorizontalPanel(false);
        final JLabel label2 = new JLabel("Or remove the room password by clicking Remove.");
        final JButton removePasswd = new JButton(" Remove ");
        removePasswd.setActionCommand("remove");
        removePasswd.addActionListener(this);
        horizontalPanel2.add(label2);
        horizontalPanel2.add(Box.createRigidArea(new Dimension(4, 1)));
        horizontalPanel2.add(removePasswd);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Room Password", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(horizontalPanel);
        horizontalPanel2.add(Box.createRigidArea(new Dimension(1, 3)));
        verticalPanel.add(horizontalPanel2);
        this.setPasswd = setPasswd;
        this.removePasswd = removePasswd;
        this.passwordInput = passwordInput;
        return verticalPanel;
    }
    
    private JComponent createTranscript() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(this.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(this.getTrans());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        verticalPanel.add(this.getPlayback());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        return panel;
    }
    
    private JComponent getTrans() {
        final JLabel label = new JLabel("<html><p>" + "Generate chat transcripts automatically for this chat room. When turned \"On\", chat text will be continously logged to a transcript file until this feature is turned \"Off\".  Log into the service administration web page to retrieve transcripts, and to access related features." + "</p></html>");
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        final JLabel label2 = new JLabel("Turn on automatic transcript logging");
        final JButton transcriptOn = new JButton("On");
        transcriptOn.setActionCommand("ts_on");
        transcriptOn.addActionListener(this);
        horizontalPanel.add(transcriptOn);
        horizontalPanel.add(Box.createRigidArea(new Dimension(4, 1)));
        horizontalPanel.add(label2);
        final JPanel horizontalPanel2 = CompUtil.createHorizontalPanel(false);
        final JLabel label3 = new JLabel("Turn off automatic transcript logging");
        final JButton transcriptOff = new JButton("Off");
        transcriptOff.setActionCommand("ts_off");
        transcriptOff.addActionListener(this);
        if (this.adminHandler.theMan.isAdvDisabled("Cons.Trans")) {
            transcriptOn.setEnabled(false);
            transcriptOff.setEnabled(false);
        }
        horizontalPanel2.add(transcriptOff);
        horizontalPanel2.add(Box.createRigidArea(new Dimension(4, 1)));
        horizontalPanel2.add(label3);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Auto Transcript Logging", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 8)));
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(horizontalPanel2);
        this.transcriptOn = transcriptOn;
        this.transcriptOff = transcriptOff;
        return verticalPanel;
    }
    
    private JComponent getPlayback() {
        final JLabel label = new JLabel("<html><p>" + "Purge recent room history text" + "</p></html>");
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        final JLabel label2 = new JLabel(" ");
        final JButton clearHistory = new JButton("Clear");
        clearHistory.setActionCommand("Cp");
        clearHistory.addActionListener(this);
        horizontalPanel.add(clearHistory);
        horizontalPanel.add(Box.createRigidArea(new Dimension(4, 1)));
        horizontalPanel.add(label2);
        if (this.adminHandler.theMan.isAdvDisabled("Cons.History")) {
            clearHistory.setEnabled(false);
        }
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Recent Room History", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(label);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 8)));
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        this.clearHistory = clearHistory;
        return verticalPanel;
    }
    
    private JComponent createFilter() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(this.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(this.getProfane());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        verticalPanel.add(this.getShout());
        return panel;
    }
    
    private JComponent getProfane() {
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        final JLabel label = new JLabel("Click On button to turn on profanity filter.");
        final JButton profaneOn = new JButton("On");
        profaneOn.setActionCommand("p_on");
        profaneOn.addActionListener(this);
        horizontalPanel.add(profaneOn);
        horizontalPanel.add(Box.createRigidArea(new Dimension(4, 1)));
        horizontalPanel.add(label);
        final JPanel horizontalPanel2 = CompUtil.createHorizontalPanel(false);
        final JLabel label2 = new JLabel("Click Off button to turn off profanity filter.");
        final JButton profaneOff = new JButton("Off");
        profaneOff.setActionCommand("p_off");
        profaneOff.addActionListener(this);
        horizontalPanel2.add(profaneOff);
        horizontalPanel2.add(Box.createRigidArea(new Dimension(4, 1)));
        horizontalPanel2.add(label2);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Profanity Filter", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(horizontalPanel2);
        this.profaneOn = profaneOn;
        this.profaneOff = profaneOff;
        return verticalPanel;
    }
    
    private JComponent getShout() {
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        final JLabel label = new JLabel("Click On button to turn on Shout filter.");
        final JButton shoutOn = new JButton("On");
        shoutOn.setActionCommand("s_on");
        shoutOn.addActionListener(this);
        horizontalPanel.add(shoutOn);
        horizontalPanel.add(Box.createRigidArea(new Dimension(4, 1)));
        horizontalPanel.add(label);
        final JPanel horizontalPanel2 = CompUtil.createHorizontalPanel(false);
        final JLabel label2 = new JLabel("Click Off button to turn off Shout filter.");
        final JButton shoutOff = new JButton("Off");
        shoutOff.setActionCommand("s_off");
        shoutOff.addActionListener(this);
        horizontalPanel2.add(shoutOff);
        horizontalPanel2.add(Box.createRigidArea(new Dimension(4, 1)));
        horizontalPanel2.add(label2);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Shout Filter", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 4)));
        verticalPanel.add(horizontalPanel2);
        this.shoutOn = shoutOn;
        this.shoutOff = shoutOff;
        return verticalPanel;
    }
    
    private JComponent createStatus() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        panel.setBorder(this.border5);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(true);
        verticalPanel.setAlignmentY(0.0f);
        panel.add(verticalPanel);
        verticalPanel.add(this.getStatus());
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        return panel;
    }
    
    private JComponent getStatus() {
        final JPanel horizontalPanel = CompUtil.createHorizontalPanel(false);
        horizontalPanel.add(new JLabel("Site ID: " + this.adminHandler.paraApplet.paraConf.get("Net.Site") + ". Click Refresh button to get room status."));
        horizontalPanel.add(Box.createRigidArea(new Dimension(10, 1)));
        final JButton button = new JButton("Refresh");
        horizontalPanel.add(button);
        button.setActionCommand("refresh");
        button.addActionListener(this);
        final JTextArea statusTextArea = new JTextArea(12, 20);
        statusTextArea.setEditable(false);
        final JScrollPane scrollPane = new JScrollPane(statusTextArea, 20, 31);
        final JPanel verticalPanel = CompUtil.createVerticalPanel(false);
        verticalPanel.setBorder(new CompoundBorder(new TitledBorder(null, "Status", 1, 2), this.border5));
        verticalPanel.setAlignmentY(0.0f);
        verticalPanel.add(horizontalPanel);
        verticalPanel.add(Box.createRigidArea(new Dimension(1, 8)));
        verticalPanel.add(scrollPane);
        this.statusTextArea = statusTextArea;
        return verticalPanel;
    }
}
