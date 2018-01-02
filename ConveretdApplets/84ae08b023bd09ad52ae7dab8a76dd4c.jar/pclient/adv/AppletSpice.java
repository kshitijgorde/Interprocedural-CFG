// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import javax.swing.JCheckBox;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import pclient.shd.ConnData;
import java.awt.GraphicsEnvironment;
import com.pchat.sc.ServicePack;
import pclient.shd.PlaybackMsg;
import java.net.InetAddress;
import javax.swing.JDialog;
import pclient.shd.ClientUtil;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import com.pchat.sc.GenericResponse;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Container;
import javax.swing.JOptionPane;
import java.util.Hashtable;
import java.awt.event.WindowEvent;
import com.pchat.sc.MsgOptions;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import pclient.shd.RoomItem;
import com.pchat.sc.StringUtil;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.Component;
import java.net.MalformedURLException;
import java.awt.Image;
import java.util.Properties;
import java.net.URL;
import java.awt.Color;
import com.pchat.sc.WindowUtil;
import pclient.shd.UserSession;
import javax.swing.Icon;
import pclient.shd.ChatModel;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import pclient.shd.GlobalChoice;
import pclient.shd.UserChoice;
import pclient.shd.Config;
import java.applet.Applet;
import pclient.main.MainClient;
import pclient.shd.ChatView;
import pclient.shd.ChatApplet;
import java.awt.event.WindowListener;

public class AppletSpice implements WindowListener, ChatApplet, ChatView
{
    public static final int WIDTH_DEF_FLOAT = 680;
    public static final int HEIGHT_DEF_FLOAT = 540;
    public String BTN_OK;
    public String BTN_CANCEL;
    public static final int RM_ROOMS = 2;
    public static final int RM_USERS = 4;
    public static final int RM_SCH = 6;
    public static final int RM_R_ADD = 8;
    public static final int RM_R_DEL = 10;
    public static final int RM_R_FRS = 12;
    private MainClient chatClient;
    private Applet theApplet;
    public Config paraConf;
    protected ContentParser msgParser;
    public RoamRooms roamRooms;
    public UserChoice userChoice;
    public GlobalChoice globalChoice;
    private JFrame floatWin;
    private JComponent floatIdle;
    private JPanel cardPanel;
    private CardLayout cardFlip;
    private static final String CARD_LOG = "LG";
    private static final String CARD_MAIN = "MN";
    private LoginPanel loginPanel;
    private PopLogin popLogin;
    public ChatModel chatModel;
    private PrivInter privateMan;
    public JPanel mainPanel;
    public DisplayArea mainChat;
    private ComInter adBrand;
    private ComInter adText;
    private JFrame popWindow;
    private CardLayout popFlip;
    private JPanel popPanel;
    private Icon lockIcon;
    private static final String POP_LOG = "PG";
    private static final String POP_IDLE = "PI";
    protected PlayFrame playWin;
    private AdmInter admConsole;
    private ModInter modConsole;
    private String connUser;
    private String connUserPass;
    private String connCookie;
    private String connEmail;
    private String connRoomPass;
    private String connRoom;
    private String connSetRoomPass;
    private SpiceEventTasks eventTasks;
    private String logoffMsg;
    protected ClientCharacter clientChar;
    private boolean joinGood;
    private ComInter avCastReqPopup;
    private PopupInfoFloat publicNotify;
    private PopupInfoFloat privateNotify;
    private ReconnectChat reconnectChat;
    private boolean doNotAskConsole;
    
    public AppletSpice() {
        this.BTN_OK = "OK";
        this.BTN_CANCEL = "Cancel";
        this.chatClient = null;
        this.theApplet = null;
        this.paraConf = null;
        this.floatWin = null;
        this.floatIdle = null;
        this.loginPanel = null;
        this.popLogin = null;
        this.chatModel = null;
        this.privateMan = null;
        this.adBrand = null;
        this.adText = null;
        this.popWindow = null;
        this.lockIcon = null;
        this.playWin = null;
        this.admConsole = null;
        this.modConsole = null;
        this.connUser = null;
        this.connUserPass = null;
        this.connCookie = null;
        this.connEmail = null;
        this.connRoomPass = null;
        this.connRoom = null;
        this.connSetRoomPass = null;
        this.joinGood = false;
        this.avCastReqPopup = null;
        this.publicNotify = null;
        this.privateNotify = null;
        this.reconnectChat = null;
        this.doNotAskConsole = false;
        this.roamRooms = new RoamRooms();
        this.userChoice = new UserChoice();
        this.globalChoice = new GlobalChoice();
        this.eventTasks = new SpiceEventTasks(this);
    }
    
    public void setParent(final MainClient chatClient, final Applet theApplet, final Config paraConf) {
        this.paraConf = paraConf;
        this.chatClient = chatClient;
        this.theApplet = theApplet;
        this.chatModel = new UserSession(this, paraConf);
        this.msgParser = new ContentParser(paraConf.getSmiley().getEmoticon());
    }
    
    public void init() {
        this.logoffMsg = this.paraConf.get("Msg.Exited", "You logged off.");
        this.BTN_OK = this.paraConf.get("Bt.OK", this.BTN_OK);
        this.BTN_CANCEL = this.paraConf.get("Bt.Cancel", this.BTN_CANCEL);
        this.setLook();
        this.setColors();
        this.presetDefaults();
        this.buildGUI();
        this.changeDefaults();
    }
    
    public void start() {
        System.out.println("starting");
        this.clientChar = new ClientCharacter(this);
        if (this.isAuto()) {
            this.connAuto();
        }
        this.clientChar.retrieveSaved();
        ClientCharacter.printNet();
    }
    
    private void popuClientChar() {
        final Boolean tree = this.clientChar.getTree();
        if (tree != null) {
            this.mainChat.topMenu.checkTree.setState(tree);
            this.mainChat.showTreeOrNot(tree);
        }
        final Boolean optTime = this.clientChar.getOptTime();
        if (optTime != null) {
            this.globalChoice.timestamp = optTime;
            this.mainChat.topMenu.checkTime.setState(optTime);
        }
        final Boolean smiley = this.clientChar.getSmiley();
        if (smiley != null) {
            this.globalChoice.smiley = smiley;
            this.mainChat.topMenu.checkSmile.setState(smiley);
        }
        final Boolean roomAudio = this.clientChar.getRoomAudio();
        if (roomAudio != null) {
            this.globalChoice.audio = roomAudio;
            this.mainChat.topMenu.checkAudio.setState(roomAudio);
        }
        final Boolean blockPrivate = this.clientChar.getBlockPrivate();
        if (blockPrivate != null) {
            this.globalChoice.block = blockPrivate;
            this.mainChat.topMenu.checkBlock.setState(blockPrivate);
        }
        final Boolean auto = this.clientChar.getAuto();
        if (auto != null) {
            this.globalChoice.reconnect = auto;
            this.mainChat.topMenu.checkReconnect.setState(auto);
        }
        final Boolean onEnter = this.clientChar.getOnEnter();
        if (onEnter != null) {
            this.userChoice.soundEnterOn = onEnter;
            this.mainChat.topMenu.checkSoundEnter.setState(onEnter);
        }
        final String fileEnter = this.clientChar.getFileEnter();
        if (fileEnter != null) {
            this.userChoice.soundEnter = fileEnter;
        }
        final Boolean onExit = this.clientChar.getOnExit();
        if (onExit != null) {
            this.userChoice.soundExitOn = onExit;
            this.mainChat.topMenu.checkSoundExit.setState(onExit);
        }
        final String fileExit = this.clientChar.getFileExit();
        if (fileExit != null) {
            this.userChoice.soundExit = fileExit;
        }
        final Boolean onText = this.clientChar.getOnText();
        if (onText != null) {
            this.userChoice.soundNewOn = onText;
            this.mainChat.topMenu.checkSoundNew.setState(onText);
        }
        final String fileText = this.clientChar.getFileText();
        if (fileText != null) {
            this.userChoice.soundNew = fileText;
        }
        final Boolean notifyPub = this.clientChar.getNotifyPub();
        if (notifyPub != null) {
            this.globalChoice.popPublic = notifyPub;
            this.mainChat.topMenu.checkNtPub.setState(notifyPub);
        }
        final Boolean notifyPrv = this.clientChar.getNotifyPrv();
        if (notifyPrv != null) {
            this.globalChoice.popPrivate = notifyPrv;
            this.mainChat.topMenu.checkNtPrv.setState(notifyPrv);
        }
        final Boolean approvePrv = this.clientChar.getApprovePrv();
        if (approvePrv != null) {
            this.globalChoice.approvePrivate = approvePrv;
            this.mainChat.topMenu.checkApprove.setState(approvePrv);
        }
        final Boolean showJoin = this.clientChar.getShowJoin();
        if (showJoin != null) {
            this.userChoice.showJoin = showJoin;
            this.mainChat.topMenu.checkJoin.setState(showJoin);
        }
        final Boolean showAvatar = this.clientChar.getShowAvatar();
        if (showAvatar != null) {
            this.userChoice.showAvatar = showAvatar;
            this.mainChat.topMenu.checkAvatar.setState(showAvatar);
        }
        final Boolean showWater = this.clientChar.getShowWater();
        if (showWater != null) {
            this.mainChat.chatRender.toggleWater(showWater);
            this.mainChat.topMenu.checkWater.setState(showWater);
        }
        final Boolean double1 = this.clientChar.getDouble();
        if (double1 != null) {
            this.userChoice.doubleSpace = double1;
            this.mainChat.topMenu.checkDouble.setState(double1);
        }
        final Boolean sep = this.clientChar.getSep();
        if (sep != null) {
            this.userChoice.separator = sep;
            this.mainChat.topMenu.checkSep.setState(sep);
        }
        final String fontSize = this.clientChar.getFontSize();
        if (fontSize != null) {
            this.userChoice.localFontSize = WindowUtil.parseInt(fontSize, 12);
            this.mainChat.chatRender.fontChanged(this.userChoice.localFontSize);
            this.mainChat.userFontChanged(this.userChoice.localFontSize);
        }
        final String myFont = this.clientChar.getMyFont();
        if (myFont != null) {
            this.userChoice.fontName = myFont;
            this.mainChat.userFontChanged(this.userChoice.fontName);
        }
        final String bg = this.clientChar.getBg();
        if (bg != null) {
            final int int1 = WindowUtil.parseInt(bg, 0);
            if (int1 != 0) {
                this.mainChat.chatRender.textPane.setBackground(new Color(int1));
            }
        }
    }
    
    public static Properties loadAdPropServer(final URL url, final AppletSpice appletSpice) {
        final Properties properties = new Properties();
        if (appletSpice.paraConf.readFile(url, "appletconf/panel.ad/brand.cf", properties)) {
            return properties;
        }
        return null;
    }
    
    public static Properties loadAdPropSite(final URL url, final String s, final AppletSpice appletSpice) {
        final Properties properties = new Properties();
        boolean file = false;
        final String string = Config.getSitesDir() + "/" + s + ".st" + "/" + "panel.ad";
        if (s != null) {
            file = appletSpice.paraConf.readFile(url, string + "/" + "brand.cf", properties);
        }
        if (file) {
            return properties;
        }
        return null;
    }
    
    public static Image loadImageServer(final URL url, final AppletSpice appletSpice, final String s) {
        final String s2 = "appletconf/image.dir";
        URL url2;
        try {
            url2 = new URL(url, s2 + "/" + s);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            url2 = null;
        }
        Image downloadImage = null;
        if (url2 != null) {
            downloadImage = downloadImage(url2, appletSpice.paraConf.getApplet(), appletSpice);
        }
        return downloadImage;
    }
    
    public static Image loadImageSite(final URL url, final String s, final AppletSpice appletSpice, final String s2) {
        final String string = Config.getSitesDir() + "/" + s + ".st" + "/" + "image.dir";
        URL url2;
        try {
            url2 = new URL(url, string + "/" + s2);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            url2 = null;
        }
        Image downloadImage = null;
        if (url2 != null) {
            downloadImage = downloadImage(url2, appletSpice.paraConf.getApplet(), appletSpice);
        }
        return downloadImage;
    }
    
    private static Image downloadImage(final URL url, final Component component, final AppletSpice appletSpice) {
        final MediaTracker mediaTracker = new MediaTracker(component);
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        appletSpice.paraConf.printer().print("downloading ad image," + url);
        if (url == null) {
            return null;
        }
        Image image = defaultToolkit.getImage(url);
        final int n = 18;
        if (image == null) {
            System.err.println("Err794. img was not downloaded.");
            return null;
        }
        mediaTracker.addImage(image, n);
        boolean waitForID;
        try {
            waitForID = mediaTracker.waitForID(n, 60000L);
        }
        catch (InterruptedException ex) {
            waitForID = false;
        }
        if (!waitForID) {
            System.err.println("img failed," + url + "," + 60);
            image = null;
        }
        return image;
    }
    
    private void setColors() {
        final String value = this.paraConf.get("Col.MainBg");
        final Color color = WindowUtil.parseColor(value, Color.white);
        final String value2 = this.paraConf.get("Col.MainFg");
        final Color color2 = WindowUtil.parseColor(value2, Color.BLACK);
        if (value != null) {
            UIManager.put("Panel.background", color);
            UIManager.put("MenuBar.background", color);
            UIManager.put("Menu.background", color);
            UIManager.put("ToolBar.background", color);
            UIManager.put("SplitPane.background", color);
            UIManager.put("SplitPane.shadow", color);
            UIManager.put("SplitPane.highlight", color);
            UIManager.put("Button.background", color);
            UIManager.put("CheckBox.background", color);
        }
        if (value2 != null) {
            UIManager.put("Panel.foreground", color2);
            UIManager.put("MenuBar.foreground", color2);
            UIManager.put("Menu.foreground", color2);
            UIManager.put("ToolBar.foreground", color2);
            UIManager.put("Label.foreground", color2);
            UIManager.put("Button.foreground", color2);
            UIManager.put("CheckBox.foreground", color2);
        }
    }
    
    private void presetDefaults() {
        this.userChoice.showJoin = this.paraConf.getBool("Val.JoinLeave", true);
        if (this.paraConf.isSimpleCSR()) {
            this.userChoice.showJoin = false;
        }
        this.userChoice.showAvatar = this.paraConf.getBool("Val.Avatar", true);
        this.userChoice.soundEnterOn = this.paraConf.getBool("Val.State.SoundEnter", false);
        this.userChoice.soundExitOn = this.paraConf.getBool("Val.State.SoundExit", false);
        this.userChoice.soundNewOn = this.paraConf.getBool("Val.State.SoundText", false);
        this.userChoice.soundEnter = this.paraConf.get("Val.Name.SoundEnter", null);
        this.userChoice.soundExit = this.paraConf.get("Val.Name.SoundExit", null);
        this.userChoice.soundNew = this.paraConf.get("Val.Name.SoundText", null);
        final String value = this.paraConf.get("Col.Mod.Q", null);
        if (!StringUtil.isTrimmedEmpty(value)) {
            this.paraConf.getPref().questionColor = WindowUtil.parseColor(value, this.paraConf.getPref().questionColor);
        }
        final String value2 = this.paraConf.get("Col.Mod.A", null);
        if (!StringUtil.isTrimmedEmpty(value2)) {
            this.paraConf.getPref().answerColor = WindowUtil.parseColor(value2, this.paraConf.getPref().answerColor);
        }
        final String value3 = this.paraConf.get("Col.Ch.Users", null);
        if (!StringUtil.isTrimmedEmpty(value3)) {
            this.paraConf.getPref().userName = WindowUtil.parseColor(value3, this.paraConf.getPref().userName);
        }
        final String value4 = this.paraConf.get("Col.Ch.Self", null);
        if (!StringUtil.isTrimmedEmpty(value4)) {
            this.paraConf.getPref().selfName = WindowUtil.parseColor(value4, this.paraConf.getPref().selfName);
        }
        final String value5 = this.paraConf.get("Col.Local", null);
        if (!StringUtil.isTrimmedEmpty(value5)) {
            this.paraConf.getPref().localColor = WindowUtil.parseColor(value5, this.paraConf.getPref().localColor);
        }
        RoomItem.deleteEmpty = this.paraConf.getBool("Op.Tr.Del", false);
        if (this.paraConf.getBool("Val.Auto.Rec", false)) {
            this.globalChoice.reconnect = true;
        }
        if (this.paraConf.getBool("Val.Auto.Rec", false)) {
            this.globalChoice.reconnect = true;
        }
        this.globalChoice.popPublic = this.paraConf.getBool("Op.Noti.Pub", false);
        this.globalChoice.popPrivate = this.paraConf.getBool("Op.Noti.Prv", false);
        this.globalChoice.approvePrivate = this.paraConf.getBool("Op.Apv.Prv", true);
        this.globalChoice.block = this.paraConf.getBool("Op.Blk.Prv", false);
    }
    
    private void changeDefaults() {
        final String value = this.paraConf.get("Val.MainFont", null);
        if (!StringUtil.isTrimmedEmpty(value)) {
            this.userChoice.localFontSize = WindowUtil.parseInt(value, 12);
            this.mainChat.chatRender.fontChanged(this.userChoice.localFontSize);
            this.mainChat.userFontChanged(this.userChoice.localFontSize);
        }
        final String value2 = this.paraConf.get("Col.Area.Bg", null);
        if (!StringUtil.isTrimmedEmpty(value2)) {
            this.mainChat.chatRender.textPane.setBackground(WindowUtil.parseColor(value2, Color.WHITE));
        }
        final String value3 = this.paraConf.get("Col.MainText", null);
        if (!StringUtil.isTrimmedEmpty(value3)) {
            StyleConstants.setForeground(this.mainChat.chatRender.textStyle, WindowUtil.parseColor(value3, this.paraConf.getPref().textColor));
        }
        final String value4 = this.paraConf.get("Col.SysText", null);
        if (!StringUtil.isTrimmedEmpty(value4)) {
            StyleConstants.setForeground(this.mainChat.chatRender.systemStyle, WindowUtil.parseColor(value4, this.paraConf.getPref().systemColor));
        }
        final String value5 = this.paraConf.get("Col.LinkText", null);
        if (!StringUtil.isTrimmedEmpty(value5)) {
            StyleConstants.setForeground(this.mainChat.chatRender.linkStyle, WindowUtil.parseColor(value5, this.paraConf.getPref().linkColor));
        }
        final String value6 = this.paraConf.get("Col.UserList.Fg", null);
        if (!StringUtil.isTrimmedEmpty(value6)) {
            this.paraConf.getPref().userColor = WindowUtil.parseColor(value6, this.paraConf.getPref().userColor);
        }
        final String value7 = this.paraConf.get("Col.List.Self", null);
        if (!StringUtil.isTrimmedEmpty(value7)) {
            this.paraConf.getPref().selfColor = WindowUtil.parseColor(value7, this.paraConf.getPref().selfColor);
        }
        final String value8 = this.paraConf.get("Col.List.Adm", null);
        if (!StringUtil.isTrimmedEmpty(value8)) {
            this.paraConf.getPref().adminColor = WindowUtil.parseColor(value8, this.paraConf.getPref().adminColor);
        }
        final String value9 = this.paraConf.get("Col.List.Mod", null);
        if (!StringUtil.isTrimmedEmpty(value9)) {
            this.paraConf.getPref().modColor = WindowUtil.parseColor(value9, this.paraConf.getPref().modColor);
        }
        final String value10 = this.paraConf.get("Col.List.Spk", null);
        if (!StringUtil.isTrimmedEmpty(value10)) {
            this.paraConf.getPref().spkColor = WindowUtil.parseColor(value10, this.paraConf.getPref().spkColor);
        }
        this.mainChat.topMenu.checkTree.setState(this.paraConf.getBool("Op.Tree", false));
        this.mainChat.topMenu.checkTime.setState(this.paraConf.getBool("Val.Stamp", false));
        this.globalChoice.timestamp = this.mainChat.topMenu.checkTime.isSelected();
        this.globalChoice.haveVideo = this.mainChat.topMenu.checkShowVideo.isSelected();
    }
    
    public Applet getApplet() {
        return this.theApplet;
    }
    
    public void stop() {
        if (this.chatModel.cmIsConnected()) {
            this.mainChat.chatRender.showLocalInfo(this.logoffMsg, null);
        }
        this.chatModel.cmExit();
        this.mainChat.keepThread = false;
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
        if (this.admConsole != null) {
            this.admConsole.clearAll();
            this.admConsole = null;
        }
        if (this.modConsole != null) {
            this.modConsole.clearAll();
            this.modConsole = null;
        }
        if (this.adBrand != null) {
            this.adBrand.destroy();
            this.adBrand = null;
        }
        if (this.adText != null) {
            this.adText.destroy();
            this.adText = null;
        }
        if (this.loginPanel != null && this.loginPanel.adText != null) {
            this.loginPanel.adText.destroy();
            this.loginPanel.adText = null;
        }
        if (this.playWin != null) {
            this.playWin.destroy();
            this.playWin = null;
        }
        if (this.avCastReqPopup != null) {
            this.avCastReqPopup.destroy();
            this.avCastReqPopup = null;
        }
        if (this.reconnectChat != null) {
            this.reconnectChat.stopIt();
            this.reconnectChat = null;
        }
        if (this.mainChat.topMenu.popTrouble != null) {
            this.mainChat.topMenu.popTrouble.destroy();
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
    
    public boolean isAvatar() {
        return this.paraConf.getBool("Op.Avatar", false) && this.userChoice.showAvatar;
    }
    
    public Hashtable getIconCache() {
        return this.mainChat.userList.iconCache;
    }
    
    public JComponent getMainComp() {
        if (!this.isPopMode()) {
            return this.cardPanel;
        }
        if (this.popWindow != null) {
            return this.mainPanel;
        }
        return this.popPanel;
    }
    
    public boolean exitChat() {
        if (JOptionPane.showConfirmDialog(this.getMainComp(), this.paraConf.get("Msg.ExitConfirm", "Are you sure you want to quit?"), " ", 0) == 0) {
            this.mainChat.chatRender.showLocalInfo(this.logoffMsg, null);
            this.chatModel.cmExit();
            if (this.reconnectChat != null) {
                this.reconnectChat.setHint(false);
            }
            return true;
        }
        return false;
    }
    
    private void clearPlayback() {
        if (this.playWin != null) {
            this.playWin.clear();
        }
    }
    
    private void setConnected() {
        this.eventTasks.setConn();
    }
    
    protected void handleSetConnected() {
        this.mainChat.setConn(false);
        this.mainChat.statusChanged(false);
        if (this.paraConf.getBool("Op.Lobby", false)) {
            this.mainChat.showRooms();
        }
        else {
            this.mainChat.showUsers();
        }
        if (this.globalChoice.block) {
            this.chatModel.cmBlock(this.globalChoice.block);
        }
        if (this.globalChoice.haveVideo) {
            this.chatModel.cmAvShowVid(this.globalChoice.haveVideo);
        }
        this.clearPlayback();
    }
    
    private void setDisconnected() {
        this.joinGood = false;
        this.eventTasks.setDis();
    }
    
    protected void handleSetDisconnected() {
        this.joinGood = false;
        this.mainChat.setConn(true);
        this.mainChat.statusChanged(false);
        this.mainChat.setCount(" ");
        this.mainChat.clearUserList();
        if (this.modConsole != null) {
            this.modConsole.clearAll();
            this.modConsole = null;
        }
        if (this.paraConf.isSecure()) {
            this.mainChat.topMenu.adjustSecBad();
        }
    }
    
    public void reconnect() {
        if (this.chatModel.cmIsConnected()) {
            this.mainChat.chatRender.showLocalInfo(this.paraConf.get("Msg.AlreadyConnected", "Already connected."), null);
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
    
    public void reconnectAuto() {
        if (this.reconnectChat != null) {
            this.loginUser();
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
        this.connSetRoomPass = null;
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
        if (this.loginPanel != null) {
            this.loginPanel.repaint();
        }
    }
    
    private void showPopChat() {
        this.popWindow.setVisible(true);
        this.popFlip.show(this.popPanel, "PI");
    }
    
    private void showPanelChat() {
        this.cardFlip.show(this.cardPanel, "MN");
        this.mainChat.showMainChat();
    }
    
    public void connPopLogin(final String connUser) {
        this.showPopChat();
        this.connUser = connUser;
        this.connRoom = this.paraConf.get("Net.Room");
        this.connCookie = this.paraConf.get("Net.Cookie");
        this.loginUser();
    }
    
    public void connPanelLogin(final String connRoom, final String connUser, final String connUserPass, final String connEmail, final String connRoomPass) {
        this.connUser = connUser;
        this.connUserPass = connUserPass;
        this.connEmail = connEmail;
        this.connRoomPass = connRoomPass;
        this.connRoom = this.paraConf.get("Net.Room");
        this.connCookie = this.paraConf.get("Net.Cookie");
        this.connSetRoomPass = null;
        if (connRoom != null) {
            this.connRoom = connRoom;
        }
        this.showPanelChat();
        this.loginUser();
    }
    
    public void connRoamSwitch(final String connRoom) {
        this.paraConf.printer().print("roam to " + connRoom);
        this.connRoom = connRoom;
        this.connSetRoomPass = null;
        if (this.chatModel.cmIsConnected()) {
            this.chatModel.cmSwitch(connRoom, null);
        }
        else {
            this.loginUser();
        }
    }
    
    public void connRoamCreate(final String connRoom, final String connSetRoomPass) {
        this.paraConf.printer().print("create room " + connRoom + " " + connSetRoomPass);
        this.connRoom = connRoom;
        this.connSetRoomPass = connSetRoomPass;
        if (this.chatModel.cmIsConnected()) {
            this.chatModel.cmRoamCreate(this.connRoom, this.connSetRoomPass);
        }
        else {
            this.loginUser();
        }
    }
    
    public Image getBusy() {
        return this.paraConf.getIcon("busy.png");
    }
    
    public Image getShowVideo() {
        String s = "showvid.png";
        if (this.paraConf.isAudioOnly()) {
            s = "showaud.png";
        }
        return this.paraConf.getIcon(s);
    }
    
    public Icon getLock() {
        if (this.lockIcon == null) {
            final Image icon = this.paraConf.getIcon("lock.png");
            if (icon != null) {
                this.lockIcon = new ImageIcon(icon);
            }
        }
        return this.lockIcon;
    }
    
    public Icon getSmile(final String s) {
        final Image image = this.paraConf.getSmiley().getImage(s);
        if (image == null) {
            return null;
        }
        return new ImageIcon(image);
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
        this.mainChat.setFloat(true);
        this.theApplet.removeAll();
        this.theApplet.add(this.floatIdle, "Center");
        this.floatWin.getContentPane().add(this.cardPanel, "Center");
        this.theApplet.invalidate();
        this.theApplet.validate();
        this.theApplet.repaint();
        this.floatWin.getContentPane().invalidate();
        this.floatWin.getContentPane().validate();
        this.floatWin.getContentPane().repaint();
        this.floatWin.setVisible(true);
    }
    
    protected void unfloatWindow() {
        this.createFloat();
        this.mainChat.setFloat(false);
        this.floatWin.setVisible(false);
        this.floatWin.getContentPane().removeAll();
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
        (this.floatWin = new JFrame(this.paraConf.title())).setSize(680, 540);
        this.floatWin.getContentPane().setLayout(new BorderLayout());
        this.floatWin.addWindowListener(this);
        this.floatWin.setDefaultCloseOperation(0);
        this.floatIdle = this.popIdleComp();
    }
    
    public UserChoice vwChoice() {
        return this.userChoice;
    }
    
    public GlobalChoice vwGlobal() {
        return this.globalChoice;
    }
    
    public void vwLoginStatus(final GenericResponse genericResponse) {
        if (this.publicNotify == null) {
            this.publicNotify = new PopupInfoFloat(this);
            this.privateNotify = new PopupInfoFloat(this);
            this.publicNotify.setMsg(this.createPopMsg(this.publicNotify, false));
            this.privateNotify.setMsg(this.createPopMsg(this.privateNotify, true));
        }
        if (!genericResponse.successful) {
            this.mainChat.chatRender.showLocalInfo(genericResponse.message, null);
            if (this.paraConf.isSecure()) {
                this.mainChat.topMenu.adjustSecBad();
            }
            return;
        }
        this.paraConf.printer().print("login OK." + genericResponse.message);
        this.checkBan();
        this.checkGag();
        if (this.connSetRoomPass == null) {
            this.chatModel.cmJoin(this.connRoom, this.connRoomPass, false);
        }
        else {
            this.chatModel.cmRoamCreate(this.connRoom, this.connSetRoomPass);
        }
        if (this.paraConf.isSecure()) {
            this.eventTasks.verifySec();
        }
    }
    
    protected void handleSec() {
        this.dispSec();
        DisplayArea.popCommon("pclient.sec.SecVerify", this, null);
    }
    
    private void dispSec() {
        if (this.paraConf.isSecure()) {
            if (this.chatModel.cmSecConnected()) {
                this.mainChat.topMenu.adjustSecGood();
            }
            else {
                this.mainChat.topMenu.adjustSecBad();
            }
        }
    }
    
    public void vwUserPass(final String s) {
        this.eventTasks.userPass(s);
    }
    
    protected void handleVwUserPass(final String s) {
        final String string = "<html>" + this.connUser + ":<BR>" + this.paraConf.get("Msg.UserPass", "Please Enter User Password") + "</html>";
        final JPasswordField passwordField = new JPasswordField(24);
        passwordField.setEchoChar('*');
        final JButton button = new JButton(this.paraConf.get("Bt.EnterWithout", "Enter Without Password"));
        final Object[] array = { string, passwordField };
        Object[] array2 = { this.BTN_OK, this.BTN_CANCEL };
        if (this.paraConf.getBool("Op.Add.EnterWithout", false)) {
            array2 = new Object[] { this.BTN_OK, this.BTN_CANCEL, button };
        }
        final JOptionPane optionPane = new JOptionPane(array, 3, 2, null, array2, null);
        final JDialog dialog = optionPane.createDialog(this.getMainComp(), "");
        final UserPassAction userPassAction = new UserPassAction(this, dialog, button);
        passwordField.addActionListener(userPassAction);
        button.addActionListener(userPassAction);
        dialog.setVisible(true);
        if (optionPane.getValue() == null || this.BTN_CANCEL.equals(optionPane.getValue())) {
            this.paraConf.printer().print("###user pass cancel.");
            dialog.setVisible(false);
            dialog.dispose();
            this.mainChat.chatRender.showLocalInfo(this.logoffMsg, null);
            this.chatModel.cmExit();
            this.reconnectChat.setHint(false);
            return;
        }
        if (userPassAction.enterWithoutClicked) {
            this.paraConf.printer().print("###Enter WO.");
            dialog.dispose();
            this.chatModel.cmExit();
            this.connUser += ClientUtil.randomNum();
            this.loginUser();
            return;
        }
        final String text = passwordField.getText();
        this.paraConf.printer().print("entered user pass," + text);
        this.connUserPass = text;
        this.chatModel.cmSendUserPass(text);
        dialog.dispose();
    }
    
    protected void handleAvViewBcast(final String s, final String s2) {
        this.avCastReqPopup = DisplayArea.popCommon("pclient.adx.PopAvCastReq", this, this.avCastReqPopup);
        if (this.avCastReqPopup != null) {
            this.avCastReqPopup.restart();
            this.avCastReqPopup.process(0, new String[] { s, s2 });
        }
    }
    
    private String getInfo(final String s, final String s2, final String s3) {
        return null;
    }
    
    public void vwJoinStatus(final GenericResponse genericResponse) {
        this.paraConf.printer().print("join response." + genericResponse);
        if (!genericResponse.successful) {
            this.mainChat.chatRender.showLocalInfo(genericResponse.message, null);
            return;
        }
        this.reconnectChat.clearOnGoodJoin();
        this.mainChat.clearUserList();
        final String cmRoomName = this.chatModel.cmRoomName();
        if (cmRoomName != null) {
            final String value = this.paraConf.get("Msg.YouJoined", "You have joined room:");
            if (!this.paraConf.isSimpleCSR()) {
                this.mainChat.chatRender.showLocalInfo(value + " " + cmRoomName, null);
            }
        }
        this.paraConf.printer().print("join OK." + genericResponse.message);
        this.setConnected();
        this.joinGood = true;
        this.reconnectChat.hadSuccess();
        this.clientChar.setUser(this.connUser);
        this.clientChar.saveInfo();
        if (!StringUtil.isEmpty(this.clientChar.getAvatar()) && this.paraConf.getBool("Op.Avatar", false)) {
            this.chatModel.cmAvatar(this.clientChar.getAvatar());
        }
        if (this.paraConf.getBool("Op.Pb.Auto", false)) {
            this.openPlay();
            this.playWin.retrieve();
        }
    }
    
    protected void sendDocIP() {
        final long currentTimeMillis = System.currentTimeMillis();
        final String host = this.paraConf.getApplet().getDocumentBase().getHost();
        String hostAddress;
        try {
            hostAddress = InetAddress.getByName(host).getHostAddress();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            hostAddress = null;
        }
        if (hostAddress != null) {
            this.chatModel.cmSendDocIP(hostAddress);
        }
        System.out.println("resolve:" + (System.currentTimeMillis() - currentTimeMillis));
        System.out.println("resolve:" + hostAddress);
    }
    
    public void userAvatar(final String avatar) {
        this.chatModel.cmAvatar(avatar);
        this.clientChar.setAvatar(avatar);
        if (this.chatModel.cmIsConnected()) {
            this.clientChar.saveInfo();
        }
    }
    
    public void vwRoomPass(final String s) {
        this.eventTasks.roomPass(s);
    }
    
    protected void handleVwRoomPass(final String s) {
        final String showInputDialog = JOptionPane.showInputDialog(this.getMainComp(), this.paraConf.get("Msg.RoomPass", "Please Enter Room Password:"));
        this.paraConf.printer().print("entered room pass," + showInputDialog);
        if (showInputDialog == null) {
            this.chatModel.cmCancelRoomPass();
            return;
        }
        this.connRoomPass = showInputDialog;
        this.chatModel.cmSendRoomPass(showInputDialog);
    }
    
    public void vwReconnHint(final boolean hint) {
        this.reconnectChat.setHint(hint);
    }
    
    public void vwInfo(final String s) {
        this.mainChat.chatRender.showSysInfo(s, null);
    }
    
    public void vwWarn(final String s) {
        this.mainChat.chatRender.showSysInfo(s, null);
    }
    
    public void vwPopupWarn(final String s) {
        JOptionPane.showMessageDialog(this.mainChat.chatRender.getComp(), s, this.paraConf.title(), 2);
    }
    
    public void vwError(final String s, final boolean b) {
        this.mainChat.chatRender.showLocalError(s, null, b);
    }
    
    public void vwInfoPrivate(final String s, final String s2, final MsgOptions msgOptions) {
        this.privateMan = this.getPrivMan();
        if (this.privateMan != null) {
            this.privateMan.info(this.chatModel.cmUserSelf(), s, s2, msgOptions);
        }
    }
    
    public void vwWarnOncePrivate(final String s, final String s2, final MsgOptions msgOptions) {
        this.privateMan = this.getPrivMan();
        if (this.privateMan != null) {
            this.privateMan.warnOnce(this.chatModel.cmUserSelf(), s, s2, msgOptions);
        }
    }
    
    public void vwSelfPublic(final String s, final String s2, final MsgOptions msgOptions) {
        this.mainChat.showSelf(s, s2, msgOptions);
    }
    
    private String createPopMsg(final PopupInfoFloat popupInfoFloat, final boolean b) {
        String s;
        if (b) {
            s = this.paraConf.get("Msg.Nt.Prv", "The number of new private messages recently posted:");
        }
        else {
            s = this.paraConf.get("Msg.Nt.Pub", "The number of new messages recently posted to the chat room:");
        }
        return "<html><p>" + s + " " + popupInfoFloat.getCount() + "</p></html>";
    }
    
    private void popupNotify(final PopupInfoFloat popupInfoFloat, final boolean b) {
        popupInfoFloat.increment();
        popupInfoFloat.setMsg(this.createPopMsg(popupInfoFloat, b));
        popupInfoFloat.setVisible(true);
    }
    
    public void vwPublic(final String s, final String s2, final MsgOptions msgOptions) {
        if (this.globalChoice.popPublic) {
            this.popupNotify(this.publicNotify, false);
        }
        this.mainChat.showOthers(s, s2, msgOptions);
        this.checkSound(this.userChoice.soundNewOn, "drip", this.userChoice.soundNew);
    }
    
    public void vwPrivate(final String s, final String s2, final String s3, final MsgOptions msgOptions) {
        if (this.globalChoice.popPrivate) {
            this.popupNotify(this.privateNotify, true);
        }
        this.privateMan = this.getPrivMan();
        if (this.privateMan != null) {
            this.privateMan.receivedPrivate(this.chatModel.cmUserSelf(), s, s2, s3, msgOptions);
        }
        this.checkSound(this.userChoice.soundNewOn, "drip", this.userChoice.soundNew);
    }
    
    public void vwSelfPrivate(final String s, final String s2, final String s3, final String s4, final MsgOptions msgOptions) {
        this.privateMan = this.getPrivMan();
        if (this.privateMan != null) {
            this.privateMan.receivedSelfPrivate(s, s2, s3, s4, msgOptions);
        }
    }
    
    public void vwPrivSound(final String s, final String s2, final String s3, final MsgOptions msgOptions) {
        this.privateMan = this.getPrivMan();
        if (this.privateMan != null) {
            this.privateMan.receivedPrivSound(this.chatModel.cmUserSelf(), s, s2);
        }
    }
    
    public void vwTypingPub(final String s) {
        this.mainChat.showTyping(s);
    }
    
    public void vwTypingPriv(final String s) {
        this.privateMan = this.getPrivMan();
        if (this.privateMan != null) {
            this.privateMan.showTyping(s);
        }
    }
    
    public void vwDisconnected(final String s) {
        if (s != null) {
            this.mainChat.chatRender.showSysInfo(s, null);
            if (this.privateMan != null) {
                this.privateMan.broadcast(s, null);
            }
        }
        this.setDisconnected();
        if (this.reconnectChat != null) {
            this.reconnectChat.setState(false);
        }
    }
    
    public void vwUserList(final String[] array) {
        if (array == null) {
            return;
        }
        this.mainChat.addUsers(array);
        this.mainChat.refreshUserList();
    }
    
    public void vwCount(final String count) {
        this.mainChat.setCount(count);
    }
    
    public void vwRefreshUsers() {
        this.mainChat.refreshUserProps();
        this.mainChat.refreshUserList();
    }
    
    public void vwRefreshUser(final String s) {
        this.mainChat.refreshUser(s);
        this.mainChat.refreshUserList();
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
            this.vwInfo(s + " " + this.paraConf.get("Msg.Joined", "has joined."));
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
            String s3 = s + " " + this.paraConf.get("Msg.Left", "has left.");
            if (!StringUtil.isTrimmedEmpty(s2)) {
                s3 = s3 + " [IP=" + s2 + "]";
            }
            this.vwInfo(s3);
        }
        this.checkSound(this.userChoice.soundExitOn, "tone", this.userChoice.soundExit);
    }
    
    public void vwAddUser(final String s) {
        this.mainChat.addUser(s);
    }
    
    public void vwRoamUsers(String simpleForm, final String s) {
        this.paraConf.printer().print("users=" + simpleForm + "," + s);
        simpleForm = RoomItem.simpleForm(simpleForm);
        this.mainChat.addRoamUsers(simpleForm, s);
    }
    
    public void vwUserSearch(final String s) {
        this.paraConf.printer().print("search=" + s);
        final ComInter popRoam = this.mainChat.topMenu.popRoam;
        if (popRoam != null) {
            popRoam.process(6, new String[] { s });
        }
    }
    
    public void vwRoomList(final String s) {
        this.paraConf.printer().print("rooms=" + s);
        this.roamRooms.replaceRoomList(s);
    }
    
    public void vwTreeJoin(String simpleForm, final String s) {
        this.roamRooms.insertIfNotFound(simpleForm);
        simpleForm = RoomItem.simpleForm(simpleForm);
        this.mainChat.treeJoin(simpleForm, s);
    }
    
    public void vwTreeExit(String simpleForm, final String s) {
        this.roamRooms.insertIfNotFound(simpleForm);
        simpleForm = RoomItem.simpleForm(simpleForm);
        this.mainChat.treeExit(simpleForm, s);
    }
    
    public void vwTreeCount(String simpleForm, final String s, final boolean b) {
        this.roamRooms.updateCount(simpleForm, WindowUtil.parseInt(s, 0), b);
        simpleForm = RoomItem.simpleForm(simpleForm);
        this.mainChat.treeCount(simpleForm);
    }
    
    public void vwLockedList(final String lockForRooms) {
        this.paraConf.printer().print("locked=" + lockForRooms);
        this.roamRooms.setLockForRooms(lockForRooms);
        this.mainChat.addAllRoomNames(this.roamRooms.getRoomNames());
    }
    
    public RoomItem lookupRoom(final String s) {
        return this.roamRooms.lookupRoom(s);
    }
    
    public void vwAvatar(final String s, final String s2) {
    }
    
    public void vwPlayback(final PlaybackMsg playbackMsg) {
        this.paraConf.printer().print("play=" + playbackMsg);
        this.openPlay();
        this.playWin.addMsg(playbackMsg);
    }
    
    public void vwAvAsk(final String s, final String s2) {
        this.privateMan = this.getPrivMan();
        if (this.privateMan != null) {
            this.privateMan.receivedAvReq(this.chatModel.cmUserSelf(), s, s2);
        }
    }
    
    public void vwAvReject(final String s) {
        this.privateMan = this.getPrivMan();
        if (this.privateMan != null) {
            this.privateMan.receivedAvReject(this.chatModel.cmUserSelf(), s);
        }
    }
    
    public void vwAvWeb(final String s, final String s2) {
        this.privateMan = this.getPrivMan();
        if (this.privateMan != null) {
            this.privateMan.receivedAvWeb(this.chatModel.cmUserSelf(), s, s2);
        }
    }
    
    public void vwAvViewPermit(final String s, final String s2) {
        this.eventTasks.avViewPermit(s, s2);
    }
    
    public void vwAvShowVid(final String s, final boolean b) {
    }
    
    public void openPlay() {
        if (this.playWin == null) {
            this.playWin = new PlayFrame(this);
        }
        this.playWin.setVisible(true);
    }
    
    public void vwModerate(final boolean b) {
        if (b) {
            this.turnOnMod();
        }
        else {
            this.turnOffMod();
        }
    }
    
    public void vwModPrivate(final boolean b) {
    }
    
    public void vwModPublic(final boolean b) {
        this.eventTasks.pubMod(b);
    }
    
    protected void handleVwModPublic(final boolean b) {
        if (b && this.chatModel.cmIsModerated()) {
            this.mainChat.addOpenMod();
        }
        else {
            this.mainChat.removeOpenMod();
        }
    }
    
    public void vwModMsg(final String s, final String s2) {
        this.mainChat.chatRender.modMsg(s, s2);
    }
    
    public void vwModAnswer(final String s, final String s2, final String s3, final String s4) {
        this.mainChat.chatRender.modAnswer(s, s2, s3, s4);
    }
    
    public void vwAdmin(final ServicePack servicePack) {
        if (this.admConsole == null) {
            if (this.applySwingAdmin()) {
                this.eventTasks.createAdmin();
            }
            else {
                this.handleAdminCreate();
            }
        }
        if (this.applySwingAdmin()) {
            this.eventTasks.serviceAdmin(servicePack);
        }
        else {
            this.handleVwAdmin(servicePack);
        }
    }
    
    protected void handleVwAdmin(final ServicePack servicePack) {
        final AdmInter admin = this.getAdmin();
        if (admin != null) {
            admin.serverMsg(servicePack);
        }
    }
    
    public void vwModPop() {
        if (this.applySwingMod()) {
            this.eventTasks.createMod();
            this.eventTasks.openMod();
        }
        else {
            this.handleModCreate();
            this.handleModPop();
        }
    }
    
    protected void handleModCreate() {
        this.getModMin();
    }
    
    protected void handleModPop() {
        final ModInter modMin = this.getModMin();
        if (modMin != null) {
            modMin.startUp();
        }
    }
    
    public void vwModmin(final ServicePack servicePack) {
        if (this.modConsole == null) {
            if (this.applySwingMod()) {
                this.eventTasks.createMod();
            }
            else {
                this.handleModCreate();
            }
        }
        if (this.applySwingMod()) {
            this.eventTasks.serviceMod(servicePack);
        }
        else {
            this.handleModmin(servicePack);
        }
    }
    
    protected void handleModmin(final ServicePack servicePack) {
        final ModInter modMin = this.getModMin();
        if (modMin != null) {
            modMin.serverMsg(servicePack);
        }
    }
    
    private ModInter getModMin() {
        if (this.modConsole != null) {
            return this.modConsole;
        }
        System.out.println("alt,mod," + this.applySwingMod());
        this.mainChat.topMenu.adjustMod(true);
        final String s = "pclient.adx.ModManager";
        ModInter modConsole;
        try {
            modConsole = (ModInter)Class.forName(s).newInstance();
            modConsole.setPara(this);
            modConsole.startUp();
        }
        catch (Exception ex) {
            System.out.println("Error CN786. Low Memory or no connection.");
            ex.printStackTrace();
            this.warn("Cannot start moderation console");
            this.warn("Error CN786. Low Memory or no connection.");
            modConsole = null;
        }
        return this.modConsole = modConsole;
    }
    
    public void vwPropsChange() {
        final String role = this.chatModel.cmProps().role;
        String s = null;
        if ("super".equals(role)) {
            s = this.paraConf.get("Msg.SuperAdmin", "You are a super admin.");
        }
        else if ("site".equals(role)) {
            s = this.paraConf.get("Msg.SiteAdmin", "You are a site admin.");
        }
        else if ("room".equals(role)) {
            s = this.paraConf.get("Msg.RoomAdmin", "You are a room admin.");
        }
        else if ("oper".equals(role)) {
            s = "You are an operator.";
        }
        if (s != null) {
            this.mainChat.chatRender.showLocalInfo(s, null);
        }
        if ("super".equals(role) || "site".equals(role) || "room".equals(role) || "oper".equals(role)) {
            this.promptForAdmin();
        }
    }
    
    public boolean isRegularUser() {
        final String role = this.chatModel.cmProps().role;
        return !"super".equals(role) && !"site".equals(role) && !"room".equals(role) && !"oper".equals(role);
    }
    
    public void vwCkBanned(final String s) {
        this.clientChar.storeBan(s, this.paraConf.get("Net.Site"));
        this.clientChar.saveInfo();
        if (this.chatModel.cmIsConnected()) {
            this.checkBan();
        }
    }
    
    private void checkBan() {
        final String loadBanSite = this.clientChar.loadBanSite();
        if (loadBanSite == null) {
            return;
        }
        if (!loadBanSite.equals(this.paraConf.get("Net.Site"))) {
            return;
        }
        final String loadBan = this.clientChar.loadBan();
        this.paraConf.printer().print("cookie time=" + loadBan + " bsite=" + loadBanSite);
        if (loadBan != null) {
            this.chatModel.cmCheckCk(loadBan);
        }
    }
    
    public void vwCkGagged(final String s) {
        this.clientChar.storeCkGag(s, this.paraConf.get("Net.Site"));
        this.clientChar.saveInfo();
        if (this.chatModel.cmIsConnected()) {
            this.checkGag();
        }
    }
    
    public void vwPing(final String s) {
        this.paraConf.printer().print("ping echo:" + s);
        final ComInter popTrouble = this.mainChat.topMenu.popTrouble;
        if (popTrouble != null) {
            popTrouble.process(-1, new String[] { s });
        }
    }
    
    private void checkGag() {
        final String loadCkGagSite = this.clientChar.loadCkGagSite();
        if (loadCkGagSite == null) {
            return;
        }
        if (!loadCkGagSite.equals(this.paraConf.get("Net.Site"))) {
            return;
        }
        final String loadCkGag = this.clientChar.loadCkGag();
        this.paraConf.printer().print("cookie time=" + loadCkGag + " gsite=" + loadCkGagSite);
        if (loadCkGag != null) {
            this.chatModel.cmCheckCkGag(loadCkGag);
        }
    }
    
    public boolean isPopMode() {
        return this.paraConf.getBool("Ctrl.PopMode", false);
    }
    
    public String[] getUserNames() {
        return this.mainChat.userList.userNames();
    }
    
    public void startPrivate(final String s) {
        if (!this.chatModel.cmIsConnected()) {
            System.out.println("warn792. not connected");
            return;
        }
        if (this.chatModel.cmIsSelf(s)) {
            System.out.println("talking to self.");
            return;
        }
        final PrivInter privMan = this.getPrivMan();
        if (privMan != null) {
            privMan.initPrivate(this.chatModel.cmUserSelf(), s, this.chatModel.cmRoomName());
        }
    }
    
    public void startAv(final String s) {
        if (!this.chatModel.cmIsConnected()) {
            System.out.println("warn793. not connected");
            return;
        }
        if (this.chatModel.cmIsSelf(s)) {
            System.out.println("AV talking to self.");
            return;
        }
        final PrivInter privMan = this.getPrivMan();
        if (privMan != null) {
            privMan.initAv(this.chatModel.cmUserSelf(), s, this.chatModel.cmRoomName());
        }
    }
    
    protected PrivInter getPrivMan() {
        if (this.privateMan != null) {
            return this.privateMan;
        }
        try {
            (this.privateMan = (PrivInter)Class.forName("pclient.adx.PrivManager").newInstance()).setPara(this);
        }
        catch (Exception ex) {
            System.out.println("Error #723. Low Memory or no connection.");
            ex.printStackTrace();
            this.warn("Error CN723. Cannot start private chat. Low Memory or no connection.");
            this.privateMan = null;
        }
        return this.privateMan;
    }
    
    private void loginUser() {
        this.paraConf.printer().print("headless?" + GraphicsEnvironment.isHeadless());
        if (this.reconnectChat == null) {
            this.reconnectChat = new ReconnectChat(this);
        }
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
            this.mainChat.chatRender.showLocalError("Error CF536: No site name.", null, true);
            return;
        }
        if (StringUtil.isTrimmedEmpty(connData.username)) {
            this.mainChat.chatRender.showLocalError("Error CF539: No user name.", null, true);
            return;
        }
        this.chatModel.cmLogin(connData);
        this.reconnectChat.setState(true);
        this.reconnectChat.setHint(true);
        if (this.adBrand != null) {
            this.adBrand.restart();
            this.mainPanel.validate();
            this.mainPanel.repaint();
        }
        if (this.adText != null) {
            this.adText.restart();
        }
        if (this.paraConf.getBool("Demo.AdmCon", false)) {
            this.getAdmin();
        }
    }
    
    private void warn(final String s) {
        this.mainChat.chatRender.showLocalInfo(s, null);
    }
    
    private void turnOnMod() {
        this.eventTasks.onMod();
    }
    
    protected void handleTurnOnMod() {
        this.mainChat.mainSendButton.setText(this.paraConf.get("Bt.Ask", "Submit"));
    }
    
    private void turnOffMod() {
        this.eventTasks.offMod();
    }
    
    protected void handleTurnOffMod() {
        this.mainChat.mainSendButton.setText(this.paraConf.get("Bt.Send", "Send"));
        this.mainChat.removeOpenMod();
    }
    
    private void checkSound(final boolean b, final String s, final String s2) {
        if (!b) {
            return;
        }
        String s3 = s;
        if (!StringUtil.isEmpty(s2)) {
            s3 = s2;
        }
        this.paraConf.play(s3);
    }
    
    protected void charNotify() {
        System.out.println("CK done");
        final String user = this.clientChar.getUser();
        if (!this.isAuto() && !StringUtil.isEmpty(user)) {
            if (this.loginPanel != null && StringUtil.isEmpty(this.loginPanel.userField.getText())) {
                this.loginPanel.userField.setText(user);
            }
            if (this.popLogin != null && StringUtil.isEmpty(this.popLogin.inputField.getText())) {
                this.popLogin.inputField.setText(user);
            }
        }
        if (this.joinGood) {
            final String avatar = this.clientChar.getAvatar();
            if (!StringUtil.isEmpty(avatar)) {
                this.chatModel.cmAvatar(avatar);
            }
        }
        try {
            this.popuClientChar();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (this.chatModel.cmIsConnected()) {
            this.checkBan();
            this.checkGag();
        }
    }
    
    private void buildGUI() {
        this.theApplet.setLayout(new BorderLayout());
        this.createMain();
        if (this.isPopMode()) {
            this.popGUI();
        }
        else {
            this.regularGUI();
        }
        if (this.paraConf.getBool("Demo.AdmPopup", false)) {
            this.getAdmin();
        }
    }
    
    private void createMain() {
        this.mainChat = new DisplayArea(this);
        final JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setLayout(new BorderLayout());
        (this.mainPanel = new JPanel()).setLayout(new BorderLayout());
        this.mainPanel.add(this.mainChat, "Center");
        this.mainPanel.add(panel, "South");
        if (this.paraConf.getBool("Ad.BrandOn", false)) {
            final String s = "pclient.adx.AdBrand";
            try {
                (this.adBrand = (ComInter)Class.forName(s).newInstance()).setPara(this);
            }
            catch (Exception ex) {
                System.out.println("Error #2782." + s);
                this.adBrand = null;
            }
        }
        if (this.adBrand != null) {
            if (this.paraConf.getBool("Ad.BrandTop", true)) {
                this.mainPanel.add((Component)this.adBrand, "North");
            }
            else {
                panel.add((Component)this.adBrand, "Center");
            }
        }
        if (this.paraConf.getBool("Ad.TextOn", false)) {
            final String s2 = "pclient.adx.AdText";
            try {
                (this.adText = (ComInter)Class.forName(s2).newInstance()).setPara(this);
            }
            catch (Exception ex2) {
                System.out.println("Error #32782." + s2);
                this.adText = null;
            }
        }
        if (this.adText != null) {
            panel.add((Component)this.adText, "South");
        }
    }
    
    private void popGUI() {
        this.popWindow = new JFrame(this.paraConf.title());
        this.popWindow.getContentPane().setLayout(new BorderLayout());
        this.popWindow.getContentPane().add(this.mainPanel, "Center");
        this.popWindow.setSize(600, 560);
        this.popWindow.setVisible(false);
        this.popWindow.addWindowListener(this);
        this.popWindow.setDefaultCloseOperation(0);
        final PopLogin popLogin = new PopLogin(this);
        this.popFlip = new CardLayout();
        (this.popPanel = new JPanel(this.popFlip)).add(popLogin, "PG");
        this.popPanel.add(this.popIdleComp(), "PI");
        this.popFlip.show(this.popPanel, "PG");
        this.popLogin = popLogin;
        this.theApplet.add(this.popPanel, "Center");
    }
    
    private JComponent popIdleComp() {
        final JLabel label = new JLabel();
        final JScrollPane scrollPane = new JScrollPane(label, 20, 30);
        label.setHorizontalAlignment(0);
        label.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        label.setText("<html><center>" + this.paraConf.get("Msg.KeepOpen", "Chat in progress.  Keep this window open to stay connected!") + "</center></html>");
        return label;
    }
    
    private void regularGUI() {
        this.loginPanel = new LoginPanel(this);
        this.cardFlip = new CardLayout();
        final JPanel cardPanel = new JPanel(this.cardFlip);
        cardPanel.add(this.loginPanel, "LG");
        cardPanel.add(this.mainPanel, "MN");
        this.theApplet.add(cardPanel, "Center");
        this.cardPanel = cardPanel;
        this.showPanelLogin();
    }
    
    private void promptForAdmin() {
        if (this.applySwingAdmin()) {
            this.eventTasks.promptAdmin();
        }
        else {
            this.handlePromptForAdmin();
        }
    }
    
    protected void handlePromptForAdmin() {
        this.mainChat.topMenu.adjustAdmin(true);
        if (this.admConsole != null && this.admConsole.isShowing()) {
            return;
        }
        if (!this.paraConf.getBool("Op.Adm.Prompt", true)) {
            return;
        }
        if (this.doNotAskConsole) {
            return;
        }
        this.showPromptForAdmin();
    }
    
    private void showPromptForAdmin() {
        final JCheckBox checkBox = new JCheckBox("Do not ask me again", false);
        final int showOptionDialog = JOptionPane.showOptionDialog(this.getMainComp(), "Would you like to open Admin Console?", "Chat Admin", 0, 3, null, new Object[] { "Yes", "No", checkBox }, null);
        this.doNotAskConsole = checkBox.isSelected();
        if (showOptionDialog == 0) {
            this.getAdmin();
            if (this.admConsole != null) {
                this.admConsole.startUp();
            }
        }
    }
    
    protected void openAdmin() {
        if (this.applySwingAdmin()) {
            this.eventTasks.createAdmin();
        }
        else {
            this.handleAdminCreate();
        }
    }
    
    protected void handleAdminCreate() {
        final AdmInter admin = this.getAdmin();
        if (admin != null) {
            admin.startUp();
        }
    }
    
    private AdmInter getAdmin() {
        if (this.admConsole != null) {
            return this.admConsole;
        }
        System.out.println("alt,admin," + this.applySwingAdmin());
        final String s = "pclient.adx.AdmManager";
        AdmInter admConsole;
        try {
            admConsole = (AdmInter)Class.forName(s).newInstance();
            admConsole.setPara(this);
            admConsole.startUp();
        }
        catch (Exception ex) {
            System.out.println("Error CN783. Low Memory or no connection.");
            ex.printStackTrace();
            this.warn("Cannot start admin console");
            this.warn("Error CN783. Low Memory or no connection.");
            admConsole = null;
        }
        return this.admConsole = admConsole;
    }
    
    private void setLook() {
        String systemLookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
        this.paraConf.printer().print("LF" + systemLookAndFeelClassName);
        if (this.chatClient.isL3()) {
            return;
        }
        if (this.chatClient.isWND()) {
            systemLookAndFeelClassName = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        }
        if (this.setLF(systemLookAndFeelClassName)) {
            return;
        }
    }
    
    private boolean setLF(final String lookAndFeel) {
        this.paraConf.printer().print("LF" + lookAndFeel);
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        }
        catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }
    
    private boolean applySwingAdmin() {
        return this.paraConf.getBool("Alt.Adm", false);
    }
    
    private boolean applySwingMod() {
        return this.paraConf.getBool("Alt.Mod", true);
    }
}
