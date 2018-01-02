// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import javax.swing.Box;
import java.awt.Insets;
import javax.swing.JScrollPane;
import java.awt.ComponentOrientation;
import javax.swing.BorderFactory;
import java.util.Properties;
import java.net.URL;
import javax.swing.text.Keymap;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.text.Highlighter;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.event.CaretEvent;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import javax.swing.event.ChangeEvent;
import javax.swing.JColorChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import com.pchat.sc.WindowUtil;
import com.pchat.sc.MsgOptions;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import pclient.shd.RoomItem;
import com.pchat.sc.StringUtil;
import java.awt.Component;
import java.awt.Container;
import pclient.shd.ClientUtil;
import pclient.shd.Config;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JComponent;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.event.ChangeListener;
import javax.swing.event.CaretListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class DisplayArea extends JPanel implements ActionListener, KeyListener, Runnable, CaretListener, ChangeListener
{
    public static final int TR_CLR = 21;
    public static final int TR_REF = 23;
    public static final int TR_PRP = 25;
    public static final int TR_ADD = 27;
    public static final int TR_DEL = 29;
    public static final int TR_RM_CLR = 31;
    public static final int TR_RM_ADD = 33;
    public static final int TR_RM_USERS = 35;
    public static final int TR_RM_JOIN = 37;
    public static final int TR_RM_EXIT = 39;
    public static final int TR_RM_COUNT = 41;
    public static final int TR_RM_DEL = 43;
    private static final String ACT_SEND = "snd";
    private static final String ACT_OPEN = "opnc";
    private static final String ACT_USERS = "ul";
    private static final String ACT_BOLD = "BD";
    private static final String ACT_ITA = "ITA";
    private static final String ACT_SMILE = "SM";
    private static final String ACT_COLOR = "CL";
    private static final String ACT_SOUND = "SD";
    private static final String ACT_STATUS = "STS";
    private static final String ACT_FLT = "FT";
    private static final String ACT_HELP = "HP";
    private static final String ACT_OUT = "UT";
    private static final String ACT_VID = "VD";
    private String Non_Empty;
    protected TopMenu topMenu;
    private JMenuBar menuBar;
    private ComInter smileyPop;
    private ComInter soundPop;
    protected JToolBar chatToolbar;
    private PopupStatus statusPop;
    protected PopupVideo videoPop;
    private JButton statusButton;
    protected JButton floatButton;
    protected JButton connButton;
    protected JButton videoButton;
    protected JButton mainSendButton;
    private JToggleButton boldToggle;
    private JToggleButton italicToggle;
    private long lastConnectExit;
    private long partyTyping;
    private boolean typingErased;
    private long typingGap;
    private long myTyping;
    private JTabbedPane usersRoomsTabs;
    private static final String CARD_RM = "RM";
    private static final String CARD_UR = "UR";
    protected boolean keepThread;
    protected LoginNames userList;
    private RoomNames roomList;
    private ComInter treeView;
    private JPanel treePanel;
    private CardLayout treeCard;
    public StageBox chatRender;
    private DualTextArea textInput;
    private boolean textInputFirstTime;
    private DualTextArea openModInput;
    private GridLayout openPanelLayout;
    private JComponent openInputBox;
    private JPanel openMainPanel;
    private JSplitPane horizontalPane;
    protected JLabel lengthLabel;
    protected JLabel typingLabel;
    protected JLabel countLabel;
    protected AppletSpice appletChat;
    private Config paraConf;
    private AreaEventTasks eventTasks;
    private boolean treeAdded;
    
    public DisplayArea(final AppletSpice appletChat) {
        this.Non_Empty = " ";
        this.statusPop = null;
        this.videoPop = null;
        this.lastConnectExit = 0L;
        this.partyTyping = 0L;
        this.typingErased = true;
        this.typingGap = 5000L;
        this.myTyping = 0L;
        this.usersRoomsTabs = null;
        this.keepThread = true;
        this.treeView = null;
        this.textInputFirstTime = true;
        this.openModInput = null;
        this.horizontalPane = null;
        this.treeAdded = false;
        this.appletChat = appletChat;
        this.keepThread = true;
        this.partyTyping = 0L;
        this.typingErased = true;
        this.myTyping = 0L;
        this.paraConf = this.appletChat.paraConf;
        this.eventTasks = new AreaEventTasks(this);
        this.buildUI();
        new Thread(this).start();
        this.afterUI();
    }
    
    public void run() {
        while (this.keepThread) {
            try {
                this.checkTyping();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            ClientUtil.doze(1000);
        }
    }
    
    private void erasePubTyping() {
        this.setTyping(this.Non_Empty);
        this.typingErased = true;
    }
    
    protected void showTreeOrNot(final boolean b) {
        if (!this.paraConf.isRoam()) {
            System.out.println("warn7823");
            return;
        }
        if (!b) {
            this.treeCard.show(this.treePanel, "ul");
            return;
        }
        if (this.treeView == null) {
            this.treeView = this.createTreeView();
        }
        if (!this.treeAdded) {
            this.treePanel.add((Component)this.treeView, "opnc");
            this.treeAdded = true;
        }
        this.treeCard.show(this.treePanel, "opnc");
    }
    
    private void addCurrent() {
        final String catString = StringUtil.catString(this.userList.userNames(), "|");
        final String cmRoomName = this.appletChat.chatModel.cmRoomName();
        if (cmRoomName != null) {
            this.addRoamUsers(cmRoomName, catString);
        }
    }
    
    public void clearUserList() {
        this.userList.clearAll();
        if (this.treeView != null) {
            this.treeView.process(21, null);
        }
    }
    
    public void refreshUserList() {
        this.userList.refresh();
        if (this.treeView != null) {
            this.treeView.process(23, null);
        }
    }
    
    public void refreshUserProps() {
        this.userList.refreshProps();
        if (this.treeView != null) {
            this.treeView.process(25, null);
        }
    }
    
    public void refreshUser(final String s) {
        this.userList.refreshUser(s);
        if (this.treeView != null) {
            this.treeView.process(25, null);
        }
    }
    
    public void addUser(final String s) {
        if (!this.appletChat.chatModel.cmIsConnected()) {
            return;
        }
        this.userList.addName(s);
        if (this.treeView != null) {
            this.treeView.process(27, new String[] { s });
        }
    }
    
    public void addUsers(final String[] array) {
        if (!this.appletChat.chatModel.cmIsConnected()) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            this.addUser(array[i]);
        }
    }
    
    public void deleteUser(final String s) {
        this.userList.deleteName(s);
        if (this.treeView != null) {
            this.treeView.process(29, new String[] { s });
        }
    }
    
    public void addAllRoomNames(final String[] array) {
        final ComInter popRoam = this.topMenu.popRoam;
        if (popRoam != null) {
            popRoam.process(2, null);
        }
        this.roomList.roamingList.replaceRooms();
        if (this.treeView != null) {
            this.treeView.process(33, array);
        }
    }
    
    public void addRoamUsers(final String s, final String s2) {
        this.paraConf.printer().print("users=" + s + "," + s2);
        final String[] array = { s, s2 };
        final ComInter popRoam = this.topMenu.popRoam;
        if (popRoam != null) {
            popRoam.process(4, array);
        }
        if (this.treeView != null) {
            this.treeView.process(35, array);
        }
    }
    
    public void treeJoin(final String s, final String s2) {
        this.paraConf.printer().print("JN,room=" + s + "," + s2);
        final String[] array = { s, s2 };
        final ComInter popRoam = this.topMenu.popRoam;
        if (popRoam != null) {
            popRoam.process(8, array);
        }
        this.roomList.roamingList.addRoom(s);
        if (this.treeView != null) {
            this.treeView.process(37, array);
        }
    }
    
    public void treeExit(final String s, final String s2) {
        this.paraConf.printer().print("Exit,room=" + s + "," + s2);
        final String[] array = { s, s2 };
        if (this.topMenu.popRoam != null) {}
        if (this.treeView != null) {
            this.treeView.process(39, array);
        }
    }
    
    public void treeCount(final String s) {
        this.paraConf.printer().print("room=" + s);
        final RoomItem lookupRoom = this.appletChat.lookupRoom(s);
        final String[] array = { s };
        final ComInter popRoam = this.topMenu.popRoam;
        if (popRoam != null) {
            if (lookupRoom == null) {
                System.out.println("Alert7829=" + s + " all:" + this.appletChat.roamRooms);
                popRoam.process(10, array);
            }
            else if (lookupRoom.isRemovable()) {
                this.appletChat.roamRooms.deleteRoom(s);
                popRoam.process(10, array);
            }
            popRoam.process(12, null);
        }
        if (lookupRoom == null) {
            System.out.println("alert7834=" + s + " all:" + this.appletChat.roamRooms);
            this.roomList.roamingList.deleteRoom(s);
        }
        else if (lookupRoom.isRemovable()) {
            this.roomList.roamingList.deleteRoom(s);
        }
        this.roomList.roamingList.refreshList();
        if (this.treeView != null) {
            if (lookupRoom == null) {
                System.out.println("alert7823=" + s + " all:" + this.appletChat.roamRooms);
                this.treeView.process(43, array);
            }
            else if (lookupRoom.isRemovable()) {
                this.appletChat.roamRooms.deleteRoom(s);
                this.treeView.process(43, array);
            }
            else {
                this.treeView.process(41, array);
            }
        }
    }
    
    public void setFloat(final boolean b) {
        Icon icon = null;
        if (b) {
            final Image icon2 = this.paraConf.getIcon(this.paraConf.getDefOrBgImg("closearrow16.png", "closearrow16-bg.png"));
            if (icon2 != null) {
                icon = new ImageIcon(icon2);
            }
        }
        else {
            final Image icon3 = this.paraConf.getIcon(this.paraConf.getDefOrBgImg("arrow16.png", "arrow16-bg.png"));
            if (icon3 != null) {
                icon = new ImageIcon(icon3);
            }
        }
        this.floatButton.setIcon(icon);
    }
    
    public void setConn(final boolean b) {
        Icon icon = null;
        Image image = null;
        if (!b) {
            this.connButton.setToolTipText(this.paraConf.get("Tip.Logout", "Log out"));
            if (!this.paraConf.isSimpleCSR()) {
                image = this.paraConf.getIcon(this.paraConf.getDefOrBgImg("log-off.png", "log-off-bg.png"));
            }
            if (image != null) {
                icon = new ImageIcon(image);
            }
        }
        else {
            this.connButton.setToolTipText(this.paraConf.get("Tip.Connect", "Connect"));
            if (!this.paraConf.isSimpleCSR()) {
                image = this.paraConf.getIcon(this.paraConf.getDefOrBgImg("log-on.png", "log-on-bg.png"));
            }
            if (image != null) {
                icon = new ImageIcon(image);
            }
        }
        this.connButton.setIcon(icon);
    }
    
    private void checkTyping() {
        final PrivInter privMan = this.appletChat.getPrivMan();
        if (privMan != null) {
            privMan.checkTyping();
        }
        if (this.typingErased) {
            return;
        }
        if (System.currentTimeMillis() - this.partyTyping > 4L * 1000L) {
            this.erasePubTyping();
        }
    }
    
    protected void showTyping(final String s) {
        this.partyTyping = System.currentTimeMillis();
        this.setTyping(s + " " + this.paraConf.get("Lb.PubTyping", "is typing."));
        this.typingErased = false;
    }
    
    private void sendTyping() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.myTyping < this.typingGap) {
            return;
        }
        this.appletChat.chatModel.cmTypingPub();
        this.myTyping = currentTimeMillis;
    }
    
    public void showSelf(final String s, final String s2, final MsgOptions msgOptions) {
        this.erasePubTyping();
        this.chatRender.showSelf(s, s2, msgOptions);
    }
    
    public void showOthers(final String s, final String s2, final MsgOptions msgOptions) {
        this.erasePubTyping();
        this.chatRender.showOthers(s, s2, msgOptions);
    }
    
    public void insertImage(final String s) {
        this.textInput.insert(" ::" + s + " ", this.textInput.getCaretPosition());
    }
    
    public void userFontChanged(final String s) {
        this.textInput.setFont(WindowUtil.changeName(this.textInput.getFont(), s));
        this.openModInput.setFont(WindowUtil.changeName(this.openModInput.getFont(), s));
    }
    
    public void userFontChanged(final int n) {
        this.textInput.setFont(WindowUtil.changeSize(this.textInput.getFont(), n));
        this.openModInput.setFont(WindowUtil.changeSize(this.openModInput.getFont(), n));
    }
    
    public void statusChanged(final boolean b) {
        Image image = null;
        if (b) {
            if (!this.paraConf.isSimpleCSR()) {
                image = this.paraConf.getIcon(this.paraConf.getDefOrBgImg("statusbusy.png", "statusbusy-bg.png"));
            }
        }
        else if (!this.paraConf.isSimpleCSR()) {
            image = this.paraConf.getIcon(this.paraConf.getDefOrBgImg("status20.png", "status20-bg.png"));
        }
        if (image != null) {
            this.statusButton.setIcon(new ImageIcon(image));
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if ("opnc".equals(actionCommand)) {
            this.actOpenMod();
        }
        else if ("snd".equals(actionCommand)) {
            this.actSend();
        }
        else if ("SM".equals(actionCommand)) {
            this.smileyPop = popCommon("pclient.adx.PopSmile", this.appletChat, this.smileyPop, true);
        }
        else {
            if ("CL".equals(actionCommand)) {
                final Color showDialog = JColorChooser.showDialog(this.appletChat.getMainComp(), this.paraConf.title(), Color.BLACK);
                this.paraConf.printer().print("color=" + showDialog);
                if (showDialog != null) {
                    this.textInput.setForeground(showDialog);
                    this.openModInput.setForeground(showDialog);
                    this.appletChat.userChoice.inputColor = showDialog;
                }
                return;
            }
            if ("SD".equals(actionCommand)) {
                this.soundPop = popCommon("pclient.adx.PopSound", this.appletChat, this.soundPop);
            }
            else if ("STS".equals(actionCommand)) {
                if (this.statusPop == null) {
                    this.statusPop = new PopupStatus(this.appletChat);
                }
                this.statusPop.popMenu.show(this.statusButton, 12, 12);
            }
            else if ("FT".equals(actionCommand)) {
                this.appletChat.floatOrNot();
            }
            else if ("HP".equals(actionCommand)) {
                final String value = this.appletChat.paraConf.get("URL.Help", "http://www.parachat.com/helpdesk/");
                this.appletChat.vwInfo(value);
                this.paraConf.loadPage(value);
            }
            else if ("UT".equals(actionCommand)) {
                final long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.lastConnectExit < 1900L) {
                    return;
                }
                this.lastConnectExit = currentTimeMillis;
                if (this.appletChat.chatModel.cmIsConnected()) {
                    this.appletChat.exitChat();
                }
                else {
                    this.appletChat.reconnect();
                }
            }
            else if ("BD".equals(actionCommand)) {
                this.doBold();
            }
            else if ("ITA".equals(actionCommand)) {
                this.appletChat.userChoice.italic = this.italicToggle.isSelected();
                WindowUtil.changeStyle(this.textInput.getBox(), this.appletChat.userChoice.bold, this.appletChat.userChoice.italic);
                WindowUtil.changeStyle(this.openModInput.getBox(), this.appletChat.userChoice.bold, this.appletChat.userChoice.italic);
            }
            else if ("VD".equals(actionCommand)) {
                if (this.videoPop == null) {
                    this.videoPop = new PopupVideo(this.appletChat);
                }
                this.videoPop.popMenu.show(this.videoButton, 12, 12);
            }
            else if ("roamplus.png".equals(actionCommand) || "roamglass.png".equals(actionCommand)) {
                this.topMenu.popRoam = popCommon("pclient.adx.PopRoam", this.appletChat, this.topMenu.popRoam);
            }
        }
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JTabbedPane tabbedPane = (JTabbedPane)changeEvent.getSource();
        if (tabbedPane != this.usersRoomsTabs) {
            System.out.println("WARN973. not the tab:" + tabbedPane);
            return;
        }
        final int selectedIndex = this.usersRoomsTabs.getSelectedIndex();
        if (selectedIndex == 0) {
            this.showUsers();
        }
        else if (selectedIndex == 1) {
            this.showRooms();
        }
    }
    
    private boolean checkInputLen(final String s, final boolean b) {
        if (!this.paraConf.getBool("Op.MaxIn", false)) {
            return true;
        }
        if (!this.appletChat.isRegularUser()) {
            return true;
        }
        final int int1 = this.paraConf.getInt("Max.InputLen", 150);
        final int length = s.length();
        int n = int1 - length;
        if (--n < 0) {
            n = 0;
        }
        if (b) {
            this.lengthLabel.setText("" + n);
            this.lengthLabel.invalidate();
            this.chatToolbar.validate();
        }
        if (length < int1) {
            return true;
        }
        if (b) {
            Toolkit.getDefaultToolkit().beep();
        }
        return false;
    }
    
    private boolean allowedKey(final int n) {
        return n == 8 || n == 127 || n == 40 || n == 38 || n == 39 || n == 37 || n == 227 || n == 226;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getSource() == this.textInput.getBox()) {
            if (!this.checkInputLen(this.textInput.getText(), true) && !this.allowedKey(keyCode)) {
                keyEvent.consume();
            }
            if (this.textInput.getRows() > 4) {
                this.textInput.setRows(5);
                this.textInput.invalidate();
            }
            if (keyCode == 10) {
                if (this.appletChat.userChoice.enterSubmit) {
                    this.actSend();
                }
                keyEvent.consume();
                return;
            }
            this.sendTyping();
        }
        if (keyEvent.getSource() == this.openModInput) {
            if (!this.checkInputLen(this.openModInput.getText(), true) && !this.allowedKey(keyCode)) {
                keyEvent.consume();
            }
            if (this.openModInput.getRows() > 4) {
                this.openModInput.setRows(5);
                this.openModInput.invalidate();
            }
            if (keyCode == 10) {
                if (this.appletChat.userChoice.enterSubmit) {
                    this.actOpenMod();
                }
                keyEvent.consume();
                return;
            }
            this.sendTyping();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getSource() == this.textInput.getBox() && !this.checkInputLen(this.textInput.getText(), false) && !this.allowedKey(keyCode)) {
            keyEvent.consume();
        }
        if (keyEvent.getSource() == this.openModInput.getBox() && !this.checkInputLen(this.openModInput.getText(), false) && !this.allowedKey(keyCode)) {
            keyEvent.consume();
        }
    }
    
    public void caretUpdate(final CaretEvent caretEvent) {
        if (caretEvent.getSource() == this.textInput.getBox() && this.textInputFirstTime) {
            this.textInputFirstTime = false;
            this.textInput.setText("");
        }
    }
    
    protected void showRooms() {
        this.appletChat.chatModel.cmQueryList();
        if (this.usersRoomsTabs.getTabCount() > 1) {
            this.usersRoomsTabs.setSelectedIndex(1);
        }
    }
    
    protected void showUsers() {
        if (this.paraConf.getBool("Add.UserList", true) && this.usersRoomsTabs.getTabCount() > 1) {
            this.usersRoomsTabs.setSelectedIndex(0);
        }
    }
    
    private void actSend() {
        final String text = this.textInput.getText();
        this.textInput.setText("");
        this.lengthLabel.setText("   ");
        this.chatRender.scrollBottom();
        if (StringUtil.isTrimmedEmpty(text)) {
            return;
        }
        this.myTyping = System.currentTimeMillis() - this.typingGap;
        if (this.appletChat.chatModel.cmIsModerated()) {
            this.appletChat.chatModel.cmModSubmit(text);
        }
        else {
            this.appletChat.chatModel.cmPublic(text, this.genOptions(this.textInput.getBox()));
        }
    }
    
    private void actOpenMod() {
        final String text = this.openModInput.getText();
        this.openModInput.setText("");
        this.chatRender.scrollBottom();
        if (StringUtil.isTrimmedEmpty(text)) {
            return;
        }
        this.myTyping = System.currentTimeMillis() - this.typingGap;
        this.appletChat.chatModel.cmPublic(text, this.genOptions(this.openModInput.getBox()));
    }
    
    private MsgOptions genOptions(final Component component) {
        final MsgOptions msgOptions = new MsgOptions();
        msgOptions.fontname = this.appletChat.userChoice.fontName;
        msgOptions.fontBold = this.appletChat.userChoice.bold;
        msgOptions.fontItalic = this.appletChat.userChoice.italic;
        msgOptions.color = this.appletChat.userChoice.inputColor;
        return msgOptions;
    }
    
    protected void addOpenMod() {
        this.openPanelLayout.setRows(2);
        this.openMainPanel.remove(this.openInputBox);
        this.openMainPanel.add(this.openInputBox);
        this.adjustPane(true);
        this.openMainPanel.invalidate();
        this.validate();
        this.repaint();
    }
    
    protected void removeOpenMod() {
        this.openMainPanel.remove(this.openInputBox);
        this.openPanelLayout.setRows(1);
        this.adjustPane(false);
        this.openMainPanel.invalidate();
        this.validate();
        this.repaint();
    }
    
    public static ComInter popCommon(final String s, final AppletSpice appletSpice, final ComInter comInter) {
        return popCommon(s, appletSpice, comInter, false);
    }
    
    public static ComInter popCommon(final String s, final AppletSpice para, final ComInter comInter, boolean bool) {
        if (comInter != null) {
            comInter.restart();
            return comInter;
        }
        if (bool) {
            bool = para.paraConf.getBool("Xp.Pop.Th", true);
        }
        ComInter comInter2;
        try {
            comInter2 = (ComInter)Class.forName(s).newInstance();
        }
        catch (Exception ex) {
            System.out.println("Error #293." + s);
            ex.printStackTrace();
            return null;
        }
        if (!bool) {
            comInter2.setPara(para);
            comInter2.restart();
            return comInter2;
        }
        final ThreadPop threadPop = new ThreadPop(para, comInter2);
        return comInter2;
    }
    
    protected void showMainChat() {
        if (!this.textInput.isOldVersion()) {
            return;
        }
    }
    
    private void buildUI() {
        this.menuBar = this.createMenu();
        this.setLayout(new BorderLayout());
        this.add(this.getCenter(), "Center");
        if (!this.paraConf.isSimpleCSR() && !this.paraConf.getBool("Op.Bar.Half", true)) {
            this.add(this.menuBar, "North");
        }
    }
    
    private JComponent getCenter() {
        final JComponent inputAndBar = this.getInputAndBar();
        final JComponent displayAndList = this.getDisplayAndList();
        if (this.paraConf.isSimpleCSR()) {
            return displayAndList;
        }
        JSplitPane horizontalPane = null;
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final boolean bool = this.paraConf.getBool("Op.V.S.Bar", false);
        if (!bool) {
            panel.add(displayAndList, "Center");
            panel.add(inputAndBar, "South");
        }
        else {
            horizontalPane = new JSplitPane(0, displayAndList, inputAndBar);
            horizontalPane.setOneTouchExpandable(false);
            inputAndBar.setPreferredSize(new Dimension(560, 56));
        }
        if (!bool) {
            return panel;
        }
        this.horizontalPane = horizontalPane;
        this.adjustPane(false);
        return horizontalPane;
    }
    
    private void adjustPane(final boolean b) {
        if (this.horizontalPane == null) {
            return;
        }
        int height = this.paraConf.getApplet().getHeight();
        if (this.appletChat.isPopMode()) {
            height = 526;
        }
        int n = 50;
        if (!this.paraConf.getBool("Op.Tbar", true)) {
            n = 0;
        }
        int n2 = 0;
        if (b) {
            n2 = 32;
        }
        this.horizontalPane.setDividerLocation(height - n - 32 - 5 - n2);
    }
    
    private JMenuBar createMenu() {
        this.topMenu = new TopMenu(this);
        final JMenuBar bar = this.topMenu.getBar();
        bar.setAlignmentX(0.5f);
        return bar;
    }
    
    private JComponent getDisplayAndList() {
        final JComponent mainChatArea = this.getMainChatArea();
        final JComponent roomsUsers = this.getRoomsUsers();
        if (this.paraConf.isSimpleCSR()) {
            return mainChatArea;
        }
        if (!this.paraConf.getBool("Op.Add.Lists", true)) {
            return mainChatArea;
        }
        final boolean bool = this.paraConf.getBool("Pos.UserList.West", false);
        int int1 = this.paraConf.getInt("Pos.UList.Div", -1);
        JSplitPane splitPane;
        if (bool) {
            splitPane = new JSplitPane(1, roomsUsers, mainChatArea);
            if (int1 < 0) {
                int1 = 150;
            }
            splitPane.setDividerLocation(int1);
        }
        else {
            splitPane = new JSplitPane(1, mainChatArea, roomsUsers);
            splitPane.setDividerLocation(this.estimatedPositionForPane(int1));
        }
        splitPane.setOneTouchExpandable(true);
        roomsUsers.setMinimumSize(new Dimension(16, 60));
        mainChatArea.setMinimumSize(new Dimension(40, 100));
        return splitPane;
    }
    
    private int estimatedPositionForPane(int n) {
        if (this.appletChat.isPopMode()) {
            if (n < 0) {
                n = 234;
            }
            return 680 - n;
        }
        if (n < 0) {
            n = 156;
        }
        return this.paraConf.getApplet().getWidth() - n;
    }
    
    private JComponent getMainChatArea() {
        (this.chatRender = new StageBox(this.appletChat)).setChoice(this.appletChat.userChoice);
        this.chatRender.clickPrivate(this.paraConf.getBool("Op.ClickName", true));
        final JComponent component = (JComponent)this.chatRender.getComp();
        if (!this.paraConf.getBool("Op.Main.Der", true)) {
            System.out.println("to remove border");
            this.chatRender.removeScrcollBorder();
        }
        if (!this.paraConf.getBool("Op.Copy", true)) {
            this.chatRender.textPane.setHighlighter(null);
            final Keymap keymap = this.chatRender.textPane.getKeymap();
            keymap.removeKeyStrokeBinding(KeyStroke.getKeyStroke(67, 2, true));
            keymap.removeKeyStrokeBinding(KeyStroke.getKeyStroke(67, 2));
            keymap.removeBindings();
            keymap.addActionForKeyStroke(KeyStroke.getKeyStroke(67, 2), new AbstractAction() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    System.out.println("c-c disabled");
                }
            });
        }
        this.checkWater();
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(component, "Center");
        if (!this.paraConf.isSimpleCSR() && this.paraConf.getBool("Op.Bar.Half", true)) {
            panel.add(this.menuBar, "North");
        }
        return panel;
    }
    
    private void checkWater() {
        if (!this.paraConf.getBool("Op.Water", false)) {
            return;
        }
        final URL codeBase = this.paraConf.getApplet().getCodeBase();
        final String value = this.paraConf.get("Net.Site");
        final Properties loadAdPropSite = AppletSpice.loadAdPropSite(codeBase, value, this.appletChat);
        if (loadAdPropSite != null) {
            this.doBackground(codeBase, loadAdPropSite, value);
        }
        else {
            final Properties loadAdPropServer = AppletSpice.loadAdPropServer(codeBase, this.appletChat);
            if (loadAdPropServer == null) {
                System.out.println("WARN946.");
                return;
            }
            this.doBackground(codeBase, loadAdPropServer, null);
        }
    }
    
    private void doBackground(final URL url, final Properties properties, final String s) {
        String property = properties.getProperty("Wm.Type");
        if (property == null) {
            property = "GRD";
        }
        if (property.equalsIgnoreCase("GRD")) {
            this.doGrad(url, properties, s);
        }
        else if (property.equalsIgnoreCase("IMG")) {
            this.doWater(url, properties, s);
        }
    }
    
    private void doWater(final URL url, final Properties properties, final String s) {
        final String property = properties.getProperty("Wm.Img");
        if (property == null) {
            System.out.println("WARN784." + s);
            return;
        }
        final String property2 = properties.getProperty("Wm.Style");
        Image image;
        if (s == null) {
            image = AppletSpice.loadImageServer(url, this.appletChat, property);
        }
        else {
            image = AppletSpice.loadImageSite(url, s, this.appletChat, property);
        }
        if (image != null) {
            this.waterImg(image, property2);
        }
    }
    
    private void waterImg(final Image image, final String s) {
        final WmPainter wmPainter = null;
        final WmFillPainter wmFillPainter = new WmFillPainter();
        wmFillPainter.setImage(image);
        final WmViewport watermark = new WmViewport(wmFillPainter, wmPainter);
        if (s != null) {
            if (s.equalsIgnoreCase("TILE")) {
                wmFillPainter.setTiled(true);
            }
            else if (s.equalsIgnoreCase("CENTER")) {
                wmFillPainter.setTiled(false);
            }
        }
        this.chatRender.setWatermark(watermark);
    }
    
    private void doGrad(final URL url, final Properties properties, final String s) {
        this.gradImg(WindowUtil.parseColor(properties.getProperty("Wm.Col.From"), null), WindowUtil.parseColor(properties.getProperty("Wm.Col.To"), null));
    }
    
    private void gradImg(final Color from, final Color to) {
        final WmPainter wmPainter = null;
        final WmGradPainter wmGradPainter = new WmGradPainter();
        if (from != null) {
            wmGradPainter.setFrom(from);
        }
        if (to != null) {
            wmGradPainter.setTo(to);
        }
        final WmGradPainter wmGradPainter2 = new WmGradPainter();
        this.chatRender.setWatermark(new WmViewport(wmGradPainter, wmPainter));
    }
    
    private JComponent getInputAndBar() {
        final JComponent bothInput = this.getBothInput();
        final JToolBar toolbar = this.getToolbar(this.paraConf);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(bothInput, "Center");
        if (this.paraConf.getBool("Op.Tbar", true)) {
            panel.add(toolbar, "North");
        }
        this.chatToolbar = toolbar;
        return panel;
    }
    
    private JComponent getBothInput() {
        final JComponent input = this.getInput();
        final JPanel openMainPanel = new JPanel();
        final GridLayout gridLayout = new GridLayout(1, 1);
        openMainPanel.setLayout(gridLayout);
        openMainPanel.add(input);
        openMainPanel.setBorder(BorderFactory.createEtchedBorder(0));
        final JComponent openInput = this.getOpenInput();
        this.openPanelLayout = gridLayout;
        this.openInputBox = openInput;
        return this.openMainPanel = openMainPanel;
    }
    
    private JComponent getInput() {
        final DualTextArea textInput = new DualTextArea(this.paraConf.getBool("Op.InputPub.Old", false), false);
        textInput.setRows(this.paraConf.getInt("Sz.InH", 2));
        textInput.setColumns(20);
        if (this.paraConf.getBool("Op.Ori.RL", false)) {
            textInput.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        final JScrollPane scrollPane = new JScrollPane(textInput.getBox(), 20, 31);
        scrollPane.setMaximumSize(new Dimension(64000, 440));
        textInput.setBorder(BorderFactory.createLoweredBevelBorder());
        textInput.setEditable(true);
        textInput.setLineWrap(true);
        textInput.setWrapStyleWord(true);
        textInput.addKeyListener(this);
        textInput.addCaretListener(this);
        if (this.paraConf.getBool("Op.Start", true) && !textInput.isOldVersion()) {
            textInput.setText(this.paraConf.get("Val.Start", "Start typing here"));
        }
        final String value = this.paraConf.get("Col.Input.Bg");
        if (value != null) {
            final Color color = WindowUtil.parseColor(value, Color.WHITE);
            if (!textInput.isOldVersion()) {
                textInput.setBackground(color);
            }
        }
        final String value2 = this.paraConf.get("Col.Input.Fg");
        if (value2 != null) {
            final Color color2 = WindowUtil.parseColor(value2, Color.BLACK);
            if (!textInput.isOldVersion()) {
                textInput.setForeground(color2);
            }
        }
        final JButton mainSendButton = new JButton(this.paraConf.get("Bt.Send", "Send"));
        mainSendButton.setVerticalTextPosition(0);
        mainSendButton.setHorizontalTextPosition(10);
        mainSendButton.setActionCommand("snd");
        mainSendButton.addActionListener(this);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(2, 1));
        if (this.paraConf.getBool("Add.Input", true)) {
            panel.add(scrollPane, "Center");
            panel.add(mainSendButton, "East");
        }
        this.textInput = textInput;
        this.mainSendButton = mainSendButton;
        return panel;
    }
    
    private JComponent getOpenInput() {
        final DualTextArea openModInput = new DualTextArea(this.paraConf, false);
        if (!openModInput.isOldVersion()) {
            openModInput.setRows(this.paraConf.getInt("Sz.OpnH", 2));
            openModInput.setColumns(20);
        }
        if (this.paraConf.getBool("Op.Ori.RL", false)) {
            openModInput.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        final JScrollPane scrollPane = new JScrollPane(openModInput.getBox(), 20, 31);
        scrollPane.setPreferredSize(new Dimension(400, 160));
        scrollPane.setMaximumSize(new Dimension(64000, 440));
        openModInput.setBorder(BorderFactory.createLoweredBevelBorder());
        openModInput.setEditable(true);
        openModInput.setLineWrap(true);
        openModInput.setWrapStyleWord(true);
        openModInput.setMaximumSize(new Dimension(64000, 360));
        openModInput.addKeyListener(this);
        final JButton button = new JButton(this.paraConf.get("Bt.Send", "Send"));
        button.setVerticalTextPosition(0);
        button.setHorizontalTextPosition(10);
        button.setActionCommand("opnc");
        button.addActionListener(this);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(2, 1));
        panel.add(openModInput.getBox(), "Center");
        panel.add(button, "East");
        this.openModInput = openModInput;
        return panel;
    }
    
    private JComponent getRoomsUsers() {
        this.treeCard = new CardLayout();
        (this.treePanel = new JPanel(this.treeCard)).add(this.getClassicRoomsUsers(), "ul");
        if (this.paraConf.isRoam() && this.treeView == null) {
            this.treeView = this.createTreeView();
        }
        if (this.paraConf.getBool("Op.Tree", false)) {
            if (this.treeView == null) {
                this.treeView = this.createTreeView();
            }
            if (!this.treeAdded) {
                this.treePanel.add((Component)this.treeView, "opnc");
                this.treeAdded = true;
            }
            this.treeCard.show(this.treePanel, "opnc");
        }
        else {
            this.treeCard.show(this.treePanel, "ul");
        }
        return this.treePanel;
    }
    
    private ComInter createTreeView() {
        final String s = "pclient.ady.TrStructView";
        ComInter comInter;
        try {
            comInter = (ComInter)Class.forName(s).newInstance();
            comInter.setPara(this.appletChat);
        }
        catch (Exception ex) {
            System.out.println("Error #7967." + s);
            ex.printStackTrace();
            comInter = null;
        }
        return comInter;
    }
    
    private JComponent getClassicRoomsUsers() {
        final JComponent userList = this.getUserList();
        final JComponent roomList = this.getRoomList();
        (this.usersRoomsTabs = new JTabbedPane()).addChangeListener(this);
        final boolean bool = this.paraConf.getBool("Add.UserList", true);
        final boolean b = this.paraConf.isRoam() && this.paraConf.getBool("Add.RoomList", true);
        if (!bool && !b) {
            return new JPanel();
        }
        if (bool && !b) {
            return userList;
        }
        if (!bool && b) {
            return roomList;
        }
        final String value;
        String s = value = this.paraConf.get("Tb.Users", "Users");
        Icon genIcon = null;
        if (!this.paraConf.isSimpleCSR()) {
            genIcon = this.genIcon("users.png");
        }
        if (genIcon != null) {
            s = "";
        }
        this.usersRoomsTabs.addTab(s, genIcon, userList, value);
        final String value2;
        String s2 = value2 = this.paraConf.get("Tb.Rooms", "Rooms");
        Icon genIcon2 = null;
        if (!this.paraConf.isSimpleCSR()) {
            genIcon2 = this.genIcon("rooms.png");
        }
        if (genIcon2 != null) {
            s2 = "";
        }
        this.usersRoomsTabs.addTab(s2, genIcon2, roomList, value2);
        return this.usersRoomsTabs;
    }
    
    private Icon genIcon(final String s) {
        Icon icon = null;
        final Image icon2 = this.paraConf.getIcon(s);
        if (icon2 != null) {
            icon = new ImageIcon(icon2);
        }
        return icon;
    }
    
    private JComponent getUserList() {
        final String value = this.paraConf.get("Tip.Count", "Number of users in this room");
        final boolean bool = this.paraConf.getBool("Add.Count", true);
        (this.countLabel = new JLabel(" ")).setToolTipText(value);
        this.countLabel.setForeground(WindowUtil.parseColor(this.paraConf.get("Col.Count", ""), Color.BLUE));
        this.countLabel.setHorizontalAlignment(0);
        this.userList = new LoginNames(this.appletChat);
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(this.userList.getBox(), "Center");
        if (bool) {
            panel.add(this.countLabel, "North");
        }
        if (!this.paraConf.isRoam() || this.appletChat.paraConf.getBool("Add.RoomList", true)) {}
        return panel;
    }
    
    private JComponent getRoomList() {
        final JButton roamButton = this.createRoamButton("roamplus.png", "roamplus.png", this.paraConf.get("Tip.Rm.Cr", "Create a new room"));
        final JButton roamButton2 = this.createRoamButton("roamglass.png", "roamglass.png", this.paraConf.get("Tip.Rm.Su", "Search for a user"));
        final JPanel panel = new JPanel(new GridLayout(1, 2));
        if (this.appletChat.paraConf.getBool("Add.Rm.Ps", true)) {
            panel.add(roamButton);
        }
        if (this.appletChat.paraConf.getBool("Add.Rm.Gs", true)) {
            panel.add(roamButton2);
        }
        this.roomList = new RoomNames(this.appletChat);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(this.roomList.getBox(), "Center");
        panel2.add(panel, "North");
        return panel2;
    }
    
    private JButton createRoamButton(final String s, final String actionCommand, final String toolTipText) {
        Image icon = null;
        if (!this.paraConf.isSimpleCSR()) {
            icon = this.paraConf.getIcon(s);
        }
        Icon icon2 = null;
        if (icon != null) {
            icon2 = new ImageIcon(icon);
        }
        final JButton button = new JButton(icon2);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        button.setMargin(new Insets(1, 1, 1, 1));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setRolloverEnabled(false);
        button.setToolTipText(toolTipText);
        return button;
    }
    
    private JToolBar getToolbar(final Config config) {
        final JToolBar toolBar = new JToolBar("Chat");
        String s = null;
        String s2 = null;
        if (!config.isSimpleCSR()) {
            s = config.getDefOrBgImg("b16.png", "b16-bg.png");
            s2 = config.getDefOrBgImg("b16p.png", "b16p-bg.png");
        }
        this.boldToggle = getToggle("BD", config, s, s2, this, "Add.Bold", toolBar, "Tip.Bold", "Bold Text");
        if (!config.isSimpleCSR()) {
            s = config.getDefOrBgImg("i16.png", "i16-bg.png");
            s2 = config.getDefOrBgImg("i16p.png", "i16p-bg.png");
        }
        this.italicToggle = getToggle("ITA", config, s, s2, this, "Add.Italic", toolBar, "Tip.Italic", "Italic Text");
        if (!config.isSimpleCSR()) {
            s = config.getDefOrBgImg("select-color.png", "select-color-bg.png");
        }
        getBtn("CL", config, s, this, "Add.Color", toolBar, "Tip.Color", "Select Color for Text");
        if (!config.isSimpleCSR()) {
            s = config.getDefOrBgImg("smiley.png", "smiley-bg.png");
        }
        getBtn("SM", config, s, this, "Add.Smiley", toolBar, "Tip.Smiley", "Insert a Smiley Face");
        if (!config.isSimpleCSR()) {
            s = config.getDefOrBgImg("sound.png", "sound-bg.png");
        }
        getBtn("SD", config, s, this, "Add.Sound", toolBar, "Tip.Sound", "Send a Sound to Room");
        if (!config.isSimpleCSR()) {
            s = config.getDefOrBgImg("status20.png", "status20-bg.png");
        }
        this.statusButton = getBtn("STS", config, s, this, "Add.Status", toolBar, "Tip.Status", "Change Online Status");
        if (!config.isSimpleCSR()) {
            s = config.getDefOrBgImg("arrow16.png", "arrow16-bg.png");
        }
        this.floatButton = getBtn("FT", config, s, this, "Add.Float", toolBar, "Tip.Float", "Float or Unfloat");
        if (this.appletChat.isPopMode()) {
            this.floatButton.setEnabled(false);
            toolBar.remove(this.floatButton);
        }
        if (!config.isSimpleCSR()) {
            s = "question16.gif";
        }
        final JButton btn = getBtn("HP", config, s, this, "Add.Help", toolBar, "Tip.Help", "Help");
        if (!config.getBool("Add.Help", false)) {
            toolBar.remove(btn);
        }
        String s3 = config.getDefOrBgImg("showvid.png", "showvid-bg.png");
        if (config.isAudioOnly()) {
            s3 = config.getDefOrBgImg("showaud.png", "showaud-bg.png");
        }
        if (config.isSimpleCSR()) {
            s3 = null;
        }
        JButton videoButton;
        if (config.isAudioOnly()) {
            videoButton = getBtn("VD", config, s3, this, "Add.Vid", toolBar, "Tip.Vid", "Voice Chat");
        }
        else {
            videoButton = getBtn("VD", config, s3, this, "Add.Vid", toolBar, "Tip.Vid.Vid", "Voice/Video Chat");
        }
        this.videoButton = videoButton;
        if (!config.isSimpleCSR()) {
            s = config.getDefOrBgImg("log-off.png", "log-off-bg.png");
        }
        final JButton btn2 = getBtn("UT", config, s, this, "Add.Logout", toolBar, "Tip.Logout", "Log out");
        this.connButton = btn2;
        toolBar.add(Box.createRigidArea(new Dimension(12, 2)));
        toolBar.add(this.lengthLabel = new JLabel("   "));
        this.lengthLabel.setForeground(Color.RED);
        toolBar.add(Box.createRigidArea(new Dimension(8, 2)));
        toolBar.add(this.typingLabel = new JLabel(" "));
        config.printer().print("toolbar margin," + toolBar.getMargin());
        config.printer().print("button margin," + btn2.getMargin());
        toolBar.setRollover(true);
        return toolBar;
    }
    
    public static JButton getBtn(final String actionCommand, final Config config, final String s, final ActionListener actionListener, final String s2, final JToolBar toolBar, final String s3, final String s4) {
        final Image icon = config.getIcon(s);
        Icon icon2 = null;
        if (icon != null) {
            icon2 = new ImageIcon(icon);
        }
        JButton button;
        if (icon2 != null) {
            button = new JButton(icon2);
        }
        else {
            button = new JButton(" ");
        }
        final String value = config.get(s3, s4);
        if (value != null) {
            button.setToolTipText(value);
        }
        button.setActionCommand(actionCommand);
        button.addActionListener(actionListener);
        button.setRolloverEnabled(true);
        button.setContentAreaFilled(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        if (config.getBool(s2, true)) {
            toolBar.add(button);
            toolBar.add(Box.createRigidArea(new Dimension(1, 1)));
        }
        return button;
    }
    
    public static JToggleButton getToggle(final String actionCommand, final Config config, final String s, final String s2, final ActionListener actionListener, final String s3, final JToolBar toolBar, final String s4, final String s5) {
        final Image icon = config.getIcon(s);
        Icon icon2 = null;
        if (icon != null) {
            icon2 = new ImageIcon(icon);
        }
        JToggleButton toggleButton;
        if (icon2 != null) {
            toggleButton = new JToggleButton(icon2);
        }
        else {
            toggleButton = new JToggleButton(" ");
        }
        if (s2 != null) {
            final Image icon3 = config.getIcon(s2);
            Icon selectedIcon = null;
            if (icon3 != null) {
                selectedIcon = new ImageIcon(icon3);
            }
            if (selectedIcon != null) {
                toggleButton.setSelectedIcon(selectedIcon);
            }
        }
        final String value = config.get(s4, s5);
        if (value != null) {
            toggleButton.setToolTipText(value);
        }
        toggleButton.setActionCommand(actionCommand);
        toggleButton.addActionListener(actionListener);
        toggleButton.setRolloverEnabled(true);
        toggleButton.setContentAreaFilled(false);
        toggleButton.setMargin(new Insets(0, 0, 0, 0));
        if (config.getBool(s3, true)) {
            toolBar.add(toggleButton);
            toolBar.add(Box.createRigidArea(new Dimension(1, 1)));
        }
        return toggleButton;
    }
    
    protected void setCount(final String count) {
        this.eventTasks.setCount(count);
    }
    
    private void setTyping(final String typing) {
        this.eventTasks.setTyping(typing);
    }
    
    private void afterUI() {
        this.boldToggle.setSelected(this.paraConf.getBool("Val.Bold", false));
        this.doBold();
    }
    
    private void doBold() {
        this.appletChat.userChoice.bold = this.boldToggle.isSelected();
        WindowUtil.changeStyle(this.textInput.getBox(), this.appletChat.userChoice.bold, this.appletChat.userChoice.italic);
        WindowUtil.changeStyle(this.openModInput.getBox(), this.appletChat.userChoice.bold, this.appletChat.userChoice.italic);
    }
}
