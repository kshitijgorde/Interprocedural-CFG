// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import com.pchat.sc.WindowUtil;
import com.pchat.sc.StringUtil;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Font;
import pclient.bsc.ChatPrevRenderer;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.Event;
import com.pchat.sc.MsgOptions;
import java.awt.event.KeyEvent;
import java.awt.MenuItem;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Button;
import java.awt.TextField;
import java.net.URL;
import java.awt.event.KeyListener;
import java.awt.Frame;

public class PrivateWindow extends Frame implements KeyListener
{
    private PrivateManager manager;
    private String thisUser;
    private String otherUser;
    private URL lastURL;
    protected long typingGap;
    protected long myTyping;
    private TextField inputField;
    private Button sendButton;
    private Button videoButton;
    private Choice sizeChoice;
    private Choice colorChoice;
    private ChatPanel chatArea;
    private Label titleLabel;
    private Checkbox ignoreUser;
    private Checkbox beepBox;
    private Checkbox doubleBox;
    private MenuItem videoMenu;
    private boolean isActive;
    private boolean warnedOnce;
    private long lastAvClick;
    private AvRequestDia avRequest;
    
    public PrivateWindow(final PrivateManager manager, final String thisUser, final String s) {
        this.lastURL = null;
        this.typingGap = 6000L;
        this.myTyping = 0L;
        this.isActive = false;
        this.warnedOnce = false;
        this.lastAvClick = 0L;
        this.avRequest = null;
        this.manager = manager;
        this.thisUser = thisUser;
        this.otherUser = s;
        this.buildUI();
        this.resize(500, 300);
        this.enableEvents(64L);
        this.setTitle(s);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            keyEvent.consume();
            this.sendMessage();
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.myTyping < this.typingGap) {
            return;
        }
        this.manager.paraGUI.chatModel.cmTypingPriv(this.otherUser);
        this.myTyping = currentTimeMillis;
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void partnerJoinLeave(final String s, final boolean b, final String s2) {
        if (!this.otherUser.equals(s)) {
            return;
        }
        String s3;
        if (b) {
            s3 = s2 + ">> " + s + " " + "has joined room";
        }
        else {
            s3 = s2 + ">> " + s + " " + "has left room";
        }
        this.chatArea.appendTextWithWrap(s3, true, null);
    }
    
    public void setBeep(final boolean state) {
        this.beepBox.setState(state);
    }
    
    public void displayText(final String s) {
        this.addTextToChat(s, s);
        if (this.isShowing()) {
            this.pickFocus();
        }
    }
    
    public void warnOnce(final String s) {
        if (this.warnedOnce) {
            return;
        }
        this.warnedOnce = true;
        this.addTextToChat(s, s);
        if (this.isShowing()) {
            this.pickFocus();
        }
    }
    
    public void pickFocus() {
        this.doze(200);
        if (this.isActiveWindow()) {
            this.manager.paraGUI.paraConf.printer().print("already has focus in private");
            this.inputField.requestFocus();
            return;
        }
        if (!this.isShowing()) {
            this.show();
        }
        this.playAlert();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            if (event.target == this.sendButton) {
                this.sendMessage();
            }
            else if (event.target == this.videoButton) {
                this.actAv();
            }
            else if (event.target == this.sizeChoice) {
                this.changeFont((String)event.arg);
            }
            else if (event.target == this.colorChoice) {
                this.changeColor((String)event.arg);
            }
            else if (event.target == this.ignoreUser) {
                this.ignoreOrNot();
            }
            else if (event.target == this.beepBox) {
                this.manager.setBeep(this.beepBox.getState());
            }
            else if (event.target == this.doubleBox) {
                this.chatArea.setnl(this.doubleBox.getState());
            }
            else if (event.target == this.videoMenu) {
                this.manager.paraGUI.chatModel.cmAvViewBc(this.otherUser);
            }
            return super.handleEvent(event);
        }
        if (event.id == 201) {
            this.closeWindow();
        }
        return super.handleEvent(event);
    }
    
    public void processWindowEvent(final WindowEvent windowEvent) {
        final int id = windowEvent.getID();
        if (id == 205) {
            this.manager.paraGUI.paraConf.printer().print("window active");
            this.isActive = true;
            this.inputField.requestFocus();
        }
        else if (id == 206) {
            this.manager.paraGUI.paraConf.printer().print("window NOT active");
            this.isActive = false;
        }
        else if (id == 201) {
            this.closeWindow();
        }
        super.processWindowEvent(windowEvent);
    }
    
    public void receiveMessage(final String s, final String s2, final String s3, final String s4) {
        this.addTextToChat(s3, s4 + s + "> " + s3);
        this.pickFocus();
    }
    
    public void selfPrivateMessage(final String s, final String s2, final String s3) {
        this.addTextToChat(s2, s3 + s + ": " + s2);
        this.pickFocus();
    }
    
    public void receivedAvReq(final String s, final String s2, final String s3) {
        if (this.avRequest != null) {
            return;
        }
        (this.avRequest = new AvRequestDia(this, s, s2, s3)).show();
    }
    
    protected void handleAvReqAccept() {
        this.manager.paraGUI.chatModel.cmAvAccept(this.otherUser);
        this.closeReq();
    }
    
    protected void handleAvReqReject() {
        this.manager.paraGUI.chatModel.cmAvDeny(this.otherUser);
        this.closeReq();
    }
    
    protected void handleAvIgnore() {
        this.ignoreUser.setState(true);
        this.ignoreOrNot();
        this.closeReq();
    }
    
    protected void handleAvCancel() {
        this.closeReq();
    }
    
    private void closeReq() {
        this.avRequest.shutdown();
        this.avRequest = null;
    }
    
    private void playAlert() {
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        if (this.manager.isBeep) {
            defaultToolkit.beep();
        }
    }
    
    private boolean isActiveWindow() {
        return this.isActive;
    }
    
    private void addTextToChat(final String s, final String s2) {
        this.chatArea.appendTextWithWrap(s2, true, null);
    }
    
    private void ignoreOrNot() {
        this.manager.paraGUI.setIgnore(this.otherUser, this.ignoreUser.getState());
        if (this.ignoreUser.getState()) {
            this.displayText("You will no longer receive any message from user: " + this.otherUser);
        }
        else {
            this.displayText("You have stopped ignoring: " + this.otherUser);
        }
    }
    
    private void buildUI() {
        if (this.manager.paraGUI.paraConf.getBool("Ctrl.Av", false)) {
            this.addMenuBar();
        }
        final Panel buildTitlePanel = this.buildTitlePanel();
        final Panel buildTextPanel = this.buildTextPanel();
        final Panel buildSendPanel = this.buildSendPanel();
        final Panel buildOptionPanel = this.buildOptionPanel();
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("North", buildSendPanel);
        panel.add("South", buildOptionPanel);
        this.setLayout(new BorderLayout());
        this.add("North", buildTitlePanel);
        this.add("Center", buildTextPanel);
        this.add("South", panel);
        this.inputField.requestFocus();
        this.inputField.selectAll();
    }
    
    private Panel buildTitlePanel() {
        final Label titleLabel = new Label(" ");
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("Center", titleLabel);
        titleLabel.setText(" " + this.thisUser + " " + "private chat with" + " " + this.otherUser);
        this.titleLabel = titleLabel;
        return panel;
    }
    
    private Panel buildTextPanel() {
        final Panel panel = new Panel();
        final ChatPanel chatPanel = new ChatPanel();
        this.manager.setAppletVar(chatPanel);
        panel.setLayout(new BorderLayout());
        panel.add("Center", chatPanel);
        this.chatArea = chatPanel;
        return panel;
    }
    
    private Panel buildSendPanel() {
        final TextField inputField = new TextField("");
        inputField.setFont(new Font("Dialog", 0, 11));
        inputField.setForeground(Color.blue);
        inputField.setBackground(Color.white);
        inputField.addKeyListener(this);
        final Button sendButton = new Button("Send");
        sendButton.setFont(new Font("Dialog", 1, 11));
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("East", sendButton);
        panel.add("Center", inputField);
        this.sendButton = sendButton;
        this.inputField = inputField;
        return panel;
    }
    
    private Panel buildOptionPanel() {
        final Choice sizeChoice = new Choice();
        sizeChoice.addItem("10");
        sizeChoice.addItem("12");
        sizeChoice.addItem("14");
        sizeChoice.addItem("16");
        sizeChoice.addItem("18");
        sizeChoice.addItem("20");
        final Choice buildColorChoice = this.buildColorChoice();
        final Button videoButton = new Button("Audio/Video");
        videoButton.setFont(new Font("Dialog", 1, 11));
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout());
        panel.add(sizeChoice);
        panel.add(buildColorChoice);
        if (this.manager.paraGUI.paraConf.getBool("Ctrl.Av", false)) {
            panel.add(videoButton);
        }
        final Checkbox ignoreUser = new Checkbox("Ignore", false);
        final Checkbox beepBox = new Checkbox("Beep", true);
        final Checkbox doubleBox = new Checkbox("Double", false);
        final Panel panel2 = new Panel();
        panel2.setLayout(new FlowLayout());
        panel2.add(ignoreUser);
        panel2.add(beepBox);
        panel2.add(doubleBox);
        final Panel panel3 = new Panel();
        panel3.setLayout(new BorderLayout());
        panel3.add("West", panel);
        panel3.add("East", panel2);
        this.sizeChoice = sizeChoice;
        this.ignoreUser = ignoreUser;
        this.beepBox = beepBox;
        this.doubleBox = doubleBox;
        this.colorChoice = buildColorChoice;
        this.videoButton = videoButton;
        return panel3;
    }
    
    private Choice buildColorChoice() {
        final Choice choice = new Choice();
        choice.addItem("black");
        choice.addItem("white");
        choice.addItem("red");
        choice.addItem("green");
        choice.addItem("blue");
        choice.addItem("gray");
        choice.addItem("yellow");
        choice.addItem("magenta");
        choice.addItem("cyan");
        choice.addItem("violet");
        return choice;
    }
    
    private void addMenuBar() {
        final MenuBar menuBar = new MenuBar();
        this.setMenuBar(menuBar);
        final Menu menu = new Menu("Action");
        final MenuItem videoMenu = new MenuItem("View This User's Audio/Video Broadcast");
        menu.add(videoMenu);
        menuBar.add(menu);
        this.videoMenu = videoMenu;
    }
    
    private void sendMessage() {
        final String text = this.inputField.getText();
        if (StringUtil.isTrimmedEmpty(text)) {
            return;
        }
        if (!this.manager.isConnected()) {
            this.addTextToChat("Not Connected to Chat Room.", "Not Connected to Chat Room.");
            return;
        }
        final String s = "You are blocking all private chat. Your party will not able to reply.";
        if (this.manager.paraGUI.parentComp.globalChoice.block) {
            this.addTextToChat(s, s);
        }
        if (this.manager.paraGUI.chatModel.cmIsIgnored(this.otherUser)) {
            final String s2 = "You have ignored this user. You will not be able to see any reply from this user.";
            this.addTextToChat(s2, s2);
        }
        this.inputField.setText("");
        this.manager.sendPrivate(this.thisUser, this.otherUser, text);
        this.pickFocus();
    }
    
    private void actAv() {
        if (System.currentTimeMillis() - this.lastAvClick < 5000L) {
            return;
        }
        this.lastAvClick = System.currentTimeMillis();
        if (!this.manager.isConnected()) {
            this.addTextToChat("Not Connected to Chat Room.", "Not Connected to Chat Room.");
            return;
        }
        final String s = "You are blocking all private chat. Your party will not able to reply.";
        if (this.manager.paraGUI.parentComp.globalChoice.block) {
            this.addTextToChat(s, s);
        }
        this.manager.paraGUI.chatModel.cmAvReq(this.otherUser);
    }
    
    private void closeWindow() {
        this.hide();
    }
    
    private void changeFont(final String s) {
        final int int1 = WindowUtil.parseInt(s, 12);
        if (int1 < 0) {
            return;
        }
        final Font font = this.chatArea.getFont();
        this.chatArea.setFont(new Font(font.getName(), font.getStyle(), int1));
    }
    
    private void changeColor(final String s) {
        final ColorTable colorTable = new ColorTable();
        final Color color = ColorTable.getColor(s);
        if (color != null) {
            this.chatArea.setForeground(color);
        }
    }
    
    private void doze(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
}
