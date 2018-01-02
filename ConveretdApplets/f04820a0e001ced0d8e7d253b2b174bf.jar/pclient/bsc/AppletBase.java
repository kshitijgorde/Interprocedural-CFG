// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Container;
import pclient.shd.ConnData;
import pclient.shd.RoomItem;
import com.pchat.sc.WindowUtil;
import com.pchat.sc.StringUtil;
import com.pchat.sc.ServicePack;
import pclient.shd.PlaybackMsg;
import com.pchat.sc.MsgOptions;
import com.pchat.sc.GenericResponse;
import java.awt.event.WindowEvent;
import pclient.shd.UserSession;
import java.awt.CardLayout;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Frame;
import pclient.shd.GlobalChoice;
import pclient.shd.UserChoice;
import java.util.Vector;
import pclient.shd.ChatModel;
import pclient.shd.Config;
import java.applet.Applet;
import pclient.main.MainClient;
import java.awt.event.WindowListener;
import pclient.shd.ChatView;
import pclient.shd.ChatApplet;

public class AppletBase implements ChatApplet, ChatView, WindowListener
{
    private MainClient chatClient;
    private Applet theApplet;
    public Config paraConf;
    public ChatModel chatModel;
    private PrivateInter privateMan;
    public BaseChat mainChat;
    private String connUser;
    private String connUserPass;
    private String connCookie;
    private String connEmail;
    private String connRoomPass;
    private String connRoom;
    private String connSetPass;
    public Vector roamRooms;
    public UserChoice userChoice;
    public GlobalChoice globalChoice;
    private Frame popWindow;
    private Frame floatWin;
    private Component floatIdle;
    private Panel cardPanel;
    private CardLayout cardFlip;
    private static final String CARD_LOG = "LG";
    private static final String CARD_MAIN = "MN";
    private CardLayout popFlip;
    private Panel popPanel;
    private CommonInter avCastReqPopup;
    private static final String POP_LOG = "PG";
    private static final String POP_IDLE = "PI";
    
    public AppletBase() {
        this.chatClient = null;
        this.theApplet = null;
        this.chatModel = null;
        this.privateMan = null;
        this.mainChat = null;
        this.connUser = null;
        this.connUserPass = null;
        this.connCookie = null;
        this.connEmail = null;
        this.connRoomPass = null;
        this.connRoom = null;
        this.connSetPass = null;
        this.popWindow = null;
        this.floatWin = null;
        this.floatIdle = null;
        this.avCastReqPopup = null;
        this.roamRooms = new Vector(32);
        this.userChoice = new UserChoice();
        this.globalChoice = new GlobalChoice();
    }
    
    public void init() {
        if (!this.paraConf.getBool("Ctrl.SupportLite", true)) {
            this.denyLite();
            return;
        }
        this.buildGUI();
        if (this.paraConf.isSimpleCSR()) {
            this.userChoice.showJoin = false;
        }
        if (this.paraConf.isSecure() && this.paraConf.isSecOnly()) {
            this.showLocalError(this.paraConf.get("Msg.NoSec", "Error: Java upgrade required to support secure connection."));
        }
    }
    
    public void start() {
        if (this.isAuto()) {
            this.connAuto();
        }
    }
    
    public void setParent(final MainClient chatClient, final Applet theApplet, final Config paraConf) {
        this.paraConf = paraConf;
        this.chatClient = chatClient;
        this.theApplet = theApplet;
        this.chatModel = new UserSession(this, paraConf);
    }
    
    public Applet getApplet() {
        return this.theApplet;
    }
    
    public void stop() {
        this.chatModel.cmExit();
        if (this.popWindow != null) {
            this.popWindow.dispose();
            this.popWindow = null;
        }
        if (this.floatWin != null) {
            this.floatWin.dispose();
            this.floatWin = null;
        }
        if (this.privateMan != null) {
            this.privateMan.clearAll();
            this.privateMan = null;
        }
        if (this.mainChat != null) {
            this.mainChat.pChatText.stop();
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (windowEvent.getSource() == this.popWindow) {
            if (!this.exitChat()) {
                return;
            }
            if (!this.isAuto()) {
                this.showPopLogin();
            }
            else {
                this.popWindow.setVisible(false);
            }
        }
        else if (windowEvent.getSource() == this.floatWin) {
            this.unfloatWindow();
        }
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void startPrivate(final String s) {
        if (this.chatModel.cmIsSelf(s)) {
            System.out.println("talking to self.");
            return;
        }
        final PrivateInter private1 = this.getPrivate();
        if (private1 != null) {
            private1.addPrivate(this.chatModel.cmUserSelf(), s);
        }
    }
    
    public static boolean hasOne(final String[] array) {
        return array != null && array.length > 0;
    }
    
    public UserChoice vwChoice() {
        return this.userChoice;
    }
    
    public GlobalChoice vwGlobal() {
        return this.globalChoice;
    }
    
    public void vwLoginStatus(final GenericResponse genericResponse) {
        if (!genericResponse.successful) {
            this.showLocalError(genericResponse.message);
            return;
        }
        this.paraConf.printer().print("login OK." + genericResponse.message);
        if (this.connSetPass == null) {
            this.chatModel.cmJoin(this.connRoom, this.connRoomPass, false);
        }
        else {
            this.chatModel.cmRoamCreate(this.connRoom, this.connSetPass);
        }
    }
    
    public void vwUserPass(final String s) {
        this.mainChat.promptUserPass(s);
    }
    
    public void vwJoinStatus(final GenericResponse genericResponse) {
        this.paraConf.printer().print("join response." + genericResponse);
        if (!genericResponse.successful) {
            this.showLocalError(genericResponse.message);
            return;
        }
        this.mainChat.clearUserNames();
        final String cmRoomName = this.chatModel.cmRoomName();
        if (cmRoomName != null && !this.paraConf.isSimpleCSR()) {
            this.showLocalInfo("You have joined room: " + cmRoomName);
        }
        this.paraConf.printer().print("join OK." + genericResponse.message);
        this.setConnected();
    }
    
    public void vwRoomPass(final String s) {
        this.mainChat.promptRoomPass();
    }
    
    public void vwReconnHint(final boolean b) {
    }
    
    public void vwInfo(final String s) {
        this.showLocalInfo(s);
    }
    
    public void vwWarn(final String s) {
        this.showLocalInfo(s);
    }
    
    public void vwPopupWarn(final String s) {
        this.showLocalInfo(s);
    }
    
    public void vwError(final String s, final boolean b) {
        this.showLocalError(s);
    }
    
    public void vwInfoPrivate(final String s, final String s2, final MsgOptions msgOptions) {
        this.paraConf.printer().print(s + " PRV=" + s2);
        this.privateMan = this.getPrivate();
        if (this.privateMan != null) {
            this.privateMan.info(this.chatModel.cmUserSelf(), s, s2);
        }
    }
    
    public void vwWarnOncePrivate(final String s, final String s2, final MsgOptions msgOptions) {
        this.paraConf.printer().print(s + " warnOnce=" + s2);
        this.privateMan = this.getPrivate();
        if (this.privateMan != null) {
            this.privateMan.warnOnce(this.chatModel.cmUserSelf(), s, s2);
        }
    }
    
    public void vwSelfPublic(final String s, final String s2, final MsgOptions msgOptions) {
        this.mainChat.pChatText.appendLocalEcho(s, s2, msgOptions);
    }
    
    public void vwPublic(final String s, final String s2, final MsgOptions msgOptions) {
        this.mainChat.pChatText.appendTextWithWrap(s, "", s2, true, msgOptions);
        this.checkSound(this.userChoice.soundNewOn, "drip", this.userChoice.soundNew);
    }
    
    public void vwPrivate(final String s, final String s2, final String s3, final MsgOptions msgOptions) {
        this.privateMan = this.getPrivate();
        if (this.privateMan != null) {
            this.privateMan.doReceivedPrivate(s, this.chatModel.cmUserSelf(), s2);
        }
        this.checkSound(this.userChoice.soundNewOn, "drip", this.userChoice.soundNew);
    }
    
    public void vwSelfPrivate(final String s, final String s2, final String s3, final String s4, final MsgOptions msgOptions) {
        this.privateMan = this.getPrivate();
        if (this.privateMan != null) {
            this.privateMan.doSelfPrivate(s2, s, s3);
        }
    }
    
    public void vwPrivSound(final String s, final String s2, final String s3, final MsgOptions msgOptions) {
        final String string = "<" + s + "> " + this.paraConf.get("Msg.SentSound", "sent sound:") + " " + s2;
        this.paraConf.play(s2);
        this.vwInfoPrivate(s, string, null);
    }
    
    public void vwTypingPriv(final String s) {
    }
    
    public void vwTypingPub(final String s) {
    }
    
    public void vwUserList(final String[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            this.mainChat.addUser(array[i]);
        }
    }
    
    public void vwLockedList(final String s) {
        this.refreshRoomList();
    }
    
    public void vwCount(final String s) {
        this.mainChat.countLabel.setText("    " + s);
        this.mainChat.countLabel.invalidate();
        this.mainChat.toobarPanel.validate();
    }
    
    public void vwAvatar(final String s, final String s2) {
    }
    
    public void vwRefreshUsers() {
        this.mainChat.pUsersBox.refresh();
    }
    
    public void vwRefreshUser(final String s) {
    }
    
    public void vwDisconnected(final String s) {
        if (s != null) {
            this.showLocalInfo(s);
            if (this.privateMan != null) {
                this.privateMan.broadcast(s);
            }
        }
        if (this.mainChat != null) {
            this.setDisconnected();
        }
    }
    
    public void vwNewJoin(final String s) {
        if (s == null) {
            return;
        }
        this.mainChat.addUser(s);
        if (this.privateMan != null) {
            this.privateMan.joinLeave(s, false);
        }
        if (!this.chatModel.cmIsModerated() && this.userChoice.showJoin && !this.chatModel.cmIsSelf(s)) {
            this.vwInfo(s + " " + "has joined.");
        }
        this.checkSound(this.userChoice.soundEnterOn, "bark", this.userChoice.soundEnter);
    }
    
    public void vwNewExit(final String s, final String s2) {
        if (s == null) {
            return;
        }
        this.mainChat.deleteUser(s);
        if (this.privateMan != null) {
            this.privateMan.joinLeave(s, true);
        }
        if (!this.chatModel.cmIsModerated() && this.userChoice.showJoin) {
            this.vwInfo(s + " " + "has left.");
        }
        this.checkSound(this.userChoice.soundExitOn, "tone", this.userChoice.soundExit);
    }
    
    public void vwAddUser(final String s) {
        this.mainChat.addUser(s);
    }
    
    public void vwRoomList(final String s) {
        this.paraConf.printer().print("rooms=" + s);
        this.roamRooms.removeAllElements();
        this.parseRooms(s, this.roamRooms);
    }
    
    private void refreshRoomList() {
        if (this.mainChat.controlPanel != null) {
            this.mainChat.controlPanel.process(200, null);
        }
        if (this.mainChat.pRoomList != null) {
            this.mainChat.pRoomList.process(200, null);
        }
    }
    
    public void vwRoamUsers(final String s, final String s2) {
        this.paraConf.printer().print("users=" + s + "," + s2);
        final String[] array = { s, s2 };
        if (this.mainChat.controlPanel != null) {
            this.mainChat.controlPanel.process(204, array);
        }
    }
    
    public void vwUserSearch(final String s) {
        this.paraConf.printer().print("search=" + s);
        if (this.mainChat.controlPanel != null) {
            this.mainChat.controlPanel.process(206, new String[] { s });
        }
    }
    
    public void vwTreeJoin(final String s, final String s2) {
    }
    
    public void vwTreeExit(final String s, final String s2) {
    }
    
    public void vwTreeCount(final String s, final String s2, final boolean b) {
    }
    
    public void vwPlayback(final PlaybackMsg playbackMsg) {
    }
    
    public void vwAvAsk(final String s, final String s2) {
        this.privateMan = this.getPrivate();
        if (this.privateMan != null) {
            this.privateMan.receivedAvReq(this.chatModel.cmUserSelf(), s, s2);
        }
    }
    
    public void vwAvReject(final String s) {
        this.privateMan = this.getPrivate();
        if (this.privateMan != null) {
            this.privateMan.receivedAvReject(this.chatModel.cmUserSelf(), s);
        }
    }
    
    public void vwAvWeb(final String s, final String s2) {
        this.privateMan = this.getPrivate();
        if (this.privateMan != null) {
            this.privateMan.receivedAvWeb(this.chatModel.cmUserSelf(), s, s2);
        }
    }
    
    public void vwAvViewPermit(final String s, final String s2) {
        if (this.avCastReqPopup == null) {
            try {
                (this.avCastReqPopup = (CommonInter)Class.forName("pclient.bsx.PopAvViewBcast").newInstance()).setPara(this.mainChat);
            }
            catch (Exception ex) {
                this.avCastReqPopup = null;
                ex.printStackTrace();
                this.showLocalInfo("Err 867. Cannot start video approval window. Low memory or no connection.\n");
                return;
            }
        }
        this.avCastReqPopup.restart();
        this.avCastReqPopup.process(0, new String[] { s, s2 });
    }
    
    public void vwModerate(final boolean b) {
        if (b) {
            this.mainChat.pSendBtn.setLabel("Ask");
        }
        else {
            this.mainChat.pSendBtn.setLabel("Send");
            this.mainChat.removeOpenMod();
        }
    }
    
    public void vwModPrivate(final boolean b) {
    }
    
    public void vwModPublic(final boolean b) {
        if (b && this.chatModel.cmIsModerated()) {
            this.mainChat.addOpenMod();
        }
        else {
            this.mainChat.removeOpenMod();
        }
    }
    
    public void vwModPop() {
    }
    
    public void vwModMsg(final String s, final String s2) {
        this.mainChat.pChatText.modMsg(s, s2);
    }
    
    public void vwModAnswer(final String s, final String s2, final String s3, final String s4) {
        this.mainChat.pChatText.modAnswer(s, s2, s3, s4, false);
    }
    
    public void vwAdmin(final ServicePack servicePack) {
    }
    
    public void vwModmin(final ServicePack servicePack) {
    }
    
    public void vwPropsChange() {
        final String role = this.chatModel.cmProps().role;
        String s = null;
        if ("super".equals(role)) {
            s = "You are a super admin.";
        }
        else if ("site".equals(role)) {
            s = "You are a site admin.";
        }
        else if ("room".equals(role)) {
            s = "You are a room admin.";
        }
        if (s != null) {
            this.showLocalInfo(s);
        }
        if ("super".equals(role) || "site".equals(role) || "room".equals(role)) {
            this.showLocalInfo("Warning AM579. Admin console is not available in lite version.");
        }
    }
    
    public void vwCkBanned(final String s) {
    }
    
    public void vwCkGagged(final String s) {
    }
    
    public void vwPing(final String s) {
    }
    
    private void checkSound(final boolean b, final String s, final String s2) {
        this.paraConf.printer().print("play sound," + b + " " + s + " " + s2);
        if (!b) {
            return;
        }
        String s3 = s;
        if (!StringUtil.isEmpty(s2)) {
            s3 = s2;
        }
        this.paraConf.play(s3);
    }
    
    private void parseRooms(final String s, final Vector vector) {
        final String[] splitString = StringUtil.splitString(s, "|", false);
        for (int i = 0, length = splitString.length; i < length; ++i) {
            final String s2 = splitString[i];
            String s3;
            if (++i < length) {
                s3 = splitString[i];
            }
            else {
                s3 = "0";
            }
            vector.addElement(new RoomItem(s2, WindowUtil.parseInt(s3, 0)));
        }
    }
    
    private PrivateInter getPrivate() {
        if (this.privateMan != null) {
            return this.privateMan;
        }
        try {
            (this.privateMan = (PrivateInter)Class.forName("pclient.bsx.PrivateManager").newInstance()).setPara(this.mainChat);
        }
        catch (Exception ex) {
            this.privateMan = null;
            this.showLocalInfo("Err 903, private chat. Low memory or no connection.\n");
        }
        return this.privateMan;
    }
    
    public void showLocalInfo(final String s) {
        this.mainChat.pChatText.appendTextWithWrap(s, true, null);
    }
    
    public void showLocalError(final String s) {
        this.mainChat.pChatText.appendTextWithWrap(s, true, null);
    }
    
    private void loginUser() {
        final ConnData connData = new ConnData();
        connData.username = this.connUser;
        connData.cookie = this.connCookie;
        connData.passwd = this.connUserPass;
        connData.sitename = this.paraConf.get("Net.Site");
        connData.isGuest = this.paraConf.getBool("Ctrl.GuestLogin", false);
        if (connData.isGuest) {
            connData.username = "_";
        }
        if (StringUtil.isTrimmedEmpty(connData.sitename)) {
            this.showLocalInfo("Error: No site name");
            return;
        }
        if (StringUtil.isTrimmedEmpty(connData.username)) {
            this.showLocalInfo("Error: No user name");
            return;
        }
        this.chatModel.cmLogin(connData);
    }
    
    public boolean isPopMode() {
        return this.paraConf.getBool("Ctrl.PopMode", false);
    }
    
    public Component getMainComp() {
        if (!this.isPopMode()) {
            return this.cardPanel;
        }
        if (this.popWindow != null) {
            return this.mainChat;
        }
        return this.popPanel;
    }
    
    public boolean exitChat() {
        this.chatModel.cmExit();
        return true;
    }
    
    private void setConnected() {
        this.mainChat.setConn(false);
        this.mainChat.showUsers();
        this.mainChat.privateButton.setEnabled(true);
        this.mainChat.pIgnoreUser.setEnabled(true);
        this.mainChat.pWhoisBtn.setEnabled(true);
        if (this.globalChoice.block) {
            this.chatModel.cmBlock(this.globalChoice.block);
        }
    }
    
    private void setDisconnected() {
        this.mainChat.countLabel.setText("");
        this.mainChat.setConn(true);
        this.mainChat.clearUserNames();
        this.mainChat.privateButton.setEnabled(false);
        this.mainChat.pIgnoreUser.setEnabled(false);
        this.mainChat.pWhoisBtn.setEnabled(false);
    }
    
    public void reconnect() {
        if (this.chatModel.cmIsConnected()) {
            this.showLocalInfo("Already connected.");
            return;
        }
        this.chatModel.cmExit();
        if (this.isAuto()) {
            this.connAuto();
        }
        else if (this.isPopMode()) {
            this.showPopLogin();
        }
        else {
            this.showPanelLogin();
        }
    }
    
    private boolean isAuto() {
        return this.paraConf.getBool("Ctrl.AutoLogin", false) || this.paraConf.getBool("Ctrl.GuestLogin", false);
    }
    
    public void connAuto() {
        this.connUser = this.paraConf.get("Net.User");
        this.connUserPass = this.paraConf.get("Net.UserPass");
        this.connEmail = null;
        this.connRoomPass = this.paraConf.get("Net.RoomPass");
        this.connRoom = this.paraConf.get("Net.Room");
        this.connCookie = this.paraConf.get("Net.Cookie");
        this.connSetPass = null;
        if (this.isPopMode()) {
            this.showPopChat();
        }
        else {
            this.showPanelChat();
        }
        this.loginUser();
    }
    
    private void showPopLogin() {
        if (this.popWindow != null) {
            this.popWindow.setVisible(false);
        }
        this.popFlip.show(this.popPanel, "PG");
    }
    
    private void showPanelLogin() {
        this.cardFlip.show(this.cardPanel, "LG");
    }
    
    private void showPopChat() {
        this.popWindow.setVisible(true);
        this.popFlip.show(this.popPanel, "PI");
    }
    
    private void showPanelChat() {
        this.cardFlip.show(this.cardPanel, "MN");
    }
    
    public void connPopLogin(final String connUser) {
        this.showPopChat();
        this.connUser = connUser;
        this.connRoom = this.paraConf.get("Net.Room");
        this.connCookie = this.paraConf.get("Net.Cookie");
        this.loginUser();
    }
    
    public void connPanelLogin(final String connUser, final String connUserPass, final String connEmail, final String connRoomPass) {
        this.connUser = connUser;
        this.connUserPass = connUserPass;
        this.connEmail = connEmail;
        this.connRoomPass = connRoomPass;
        this.connRoom = this.paraConf.get("Net.Room");
        this.connCookie = this.paraConf.get("Net.Cookie");
        this.connSetPass = null;
        this.showPanelChat();
        this.loginUser();
    }
    
    public void connRoamSwitch(String replace) {
        this.paraConf.printer().print("roam to " + replace);
        this.connRoom = replace;
        this.connSetPass = null;
        if (replace != null) {
            replace = replace.replace(' ', '_');
        }
        if (this.chatModel.cmIsConnected()) {
            this.chatModel.cmSwitch(replace, null);
        }
        else {
            this.loginUser();
        }
    }
    
    public void connRoamCreate(final String connRoom, final String connSetPass) {
        this.paraConf.printer().print("create room " + connRoom + " " + connSetPass);
        this.connRoom = connRoom;
        this.connSetPass = connSetPass;
        if (this.chatModel.cmIsConnected()) {
            this.chatModel.cmRoamCreate(this.connRoom, this.connSetPass);
        }
        else {
            this.loginUser();
        }
    }
    
    protected void floatOrNot() {
        this.createFloat();
        if (this.floatWin.isShowing()) {
            this.unfloatWindow();
        }
        else {
            this.floatWindow();
        }
    }
    
    protected void floatWindow() {
        this.createFloat();
        this.mainChat.optionBar.setFloat(true);
        this.theApplet.removeAll();
        this.theApplet.add(this.floatIdle, "Center");
        this.floatWin.add(this.cardPanel, "Center");
        this.theApplet.invalidate();
        this.theApplet.validate();
        this.theApplet.repaint();
        this.floatWin.invalidate();
        this.floatWin.validate();
        this.floatWin.repaint();
        this.floatWin.setVisible(true);
    }
    
    protected void unfloatWindow() {
        this.createFloat();
        this.mainChat.optionBar.setFloat(false);
        this.floatWin.setVisible(false);
        this.floatWin.removeAll();
        this.theApplet.removeAll();
        this.theApplet.add(this.cardPanel, "Center");
        this.theApplet.invalidate();
        this.theApplet.validate();
        this.theApplet.repaint();
    }
    
    private void createFloat() {
        if (this.floatWin != null) {
            return;
        }
        (this.floatWin = new Frame()).setSize(640, 520);
        this.floatWin.setLayout(new BorderLayout());
        this.floatWin.addWindowListener(this);
        this.floatIdle = this.popIdleComp();
    }
    
    public boolean isMac() {
        return System.getProperty("os.name").startsWith("Mac");
    }
    
    private void buildGUI() {
        final String value = this.paraConf.get("Col.MainBg");
        final String value2 = this.paraConf.get("Col.MainFg");
        final Color color = WindowUtil.parseColor(value, Color.blue);
        final Color color2 = WindowUtil.parseColor(value2, Color.black);
        this.theApplet.setBackground(color);
        this.theApplet.setForeground(color2);
        this.theApplet.setLayout(new BorderLayout());
        this.mainChat = new BaseChat(this, this.paraConf);
        if (this.isPopMode()) {
            this.popGUI();
        }
        else {
            this.regularGUI();
        }
    }
    
    private void popGUI() {
        (this.popWindow = new Frame(this.paraConf.title())).setLayout(new BorderLayout());
        this.popWindow.add(this.mainChat, "Center");
        this.popWindow.setSize(600, 560);
        this.popWindow.setVisible(false);
        this.popWindow.addWindowListener(this);
        final PopupLogin popupLogin = new PopupLogin(this);
        this.popFlip = new CardLayout();
        (this.popPanel = new Panel(this.popFlip)).add(popupLogin, "PG");
        this.popPanel.add(this.popIdleComp(), "PI");
        this.popFlip.show(this.popPanel, "PG");
        this.theApplet.add(this.popPanel, "Center");
    }
    
    private Component popIdleComp() {
        final TextArea textArea = new TextArea("Chat in progress.  Keep this window open to stay connected!", 12, 48, 3);
        textArea.setEditable(false);
        textArea.setFont(new Font("Dialog", 0, 18));
        return textArea;
    }
    
    private void regularGUI() {
        final ConnectPanel connectPanel = new ConnectPanel(this);
        this.cardFlip = new CardLayout();
        final Panel cardPanel = new Panel(this.cardFlip);
        cardPanel.add(connectPanel, "LG");
        cardPanel.add(this.mainChat, "MN");
        this.theApplet.add(cardPanel, "Center");
        this.cardPanel = cardPanel;
        this.showPanelLogin();
    }
    
    private void denyLite() {
        String s = "Java upgrade required. The webmaster requires you to have the latest version of Java installed on your computer to access the chat room. Get the latest version of Java for free by visiting Java.com at: \n\n      HTTP://java.com  ";
        final String value = this.paraConf.get("Msg.NoLite");
        if (!StringUtil.isTrimmedEmpty(value)) {
            s = value;
        }
        final TextArea textArea = new TextArea(s, 10, 48, 3);
        textArea.setEditable(false);
        this.theApplet.setLayout(new BorderLayout());
        this.theApplet.add(textArea, "Center");
    }
}
