// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

import javax.swing.AbstractButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.JColorChooser;
import java.awt.Color;
import com.pchat.sc.StringUtil;
import com.pchat.sc.WindowUtil;
import java.awt.event.ActionEvent;
import javax.net.ssl.SSLSession;
import javax.swing.Icon;
import java.awt.Image;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import pclient.shd.Config;
import java.awt.event.ActionListener;

public class TopMenu implements ActionListener
{
    public static String TXT_AV_CAST;
    public static String TXT_AV_CAST_VID;
    public static String TXT_AV_Q;
    public static String TXT_AV_Q_VID;
    public static String TXT_AV_SHOW;
    public static String TXT_AV_SHOW_VID;
    private Config paraConf;
    private DisplayArea mainChat;
    private JMenuBar menuBar;
    private JMenu actionMenu;
    private JMenu videoMenu;
    private JMenu secureMenu;
    private boolean adminAdded;
    private boolean modAdded;
    private ComInter popAvatar;
    protected ComInter popRoam;
    private ComInter popAbout;
    protected ComInter popTrouble;
    protected ComInter popMobile;
    public static String DEF_ON;
    private String PRE_SD_ENTER;
    private String PRE_SD_EXIT;
    private String PRE_SD_NEW;
    private String PRE_F_SIZE;
    private String PRE_F_NAME;
    protected JCheckBoxMenuItem checkJoin;
    protected JCheckBoxMenuItem checkAvatar;
    protected JCheckBoxMenuItem checkBlock;
    protected JCheckBoxMenuItem checkReconnect;
    protected JCheckBoxMenuItem checkTree;
    protected JCheckBoxMenuItem checkTime;
    protected JCheckBoxMenuItem checkSmile;
    protected JCheckBoxMenuItem checkAudio;
    protected JCheckBoxMenuItem checkDouble;
    protected JCheckBoxMenuItem checkSep;
    protected JCheckBoxMenuItem checkWater;
    protected JCheckBoxMenuItem checkSoundEnter;
    protected JCheckBoxMenuItem checkSoundExit;
    protected JCheckBoxMenuItem checkSoundNew;
    protected JCheckBoxMenuItem checkShowVideo;
    protected JCheckBoxMenuItem checkNtPub;
    protected JCheckBoxMenuItem checkNtPrv;
    protected JCheckBoxMenuItem checkApprove;
    
    public TopMenu(final DisplayArea mainChat) {
        this.adminAdded = false;
        this.modAdded = false;
        this.popAvatar = null;
        this.popRoam = null;
        this.popAbout = null;
        this.popTrouble = null;
        this.popMobile = null;
        this.PRE_SD_ENTER = "_et";
        this.PRE_SD_EXIT = "_ex";
        this.PRE_SD_NEW = "_nt";
        this.PRE_F_SIZE = "_fs";
        this.PRE_F_NAME = "_fn";
        this.mainChat = mainChat;
        this.paraConf = mainChat.appletChat.paraConf;
        this.createMenu();
    }
    
    public JMenuBar getBar() {
        return this.menuBar;
    }
    
    public void adjustAdmin(final boolean b) {
        if (!b) {
            return;
        }
        if (this.adminAdded) {
            return;
        }
        this.actionMenu.addSeparator();
        final JMenuItem item = createItem("Mn.Admin", "Admin Console", this.actionMenu, TopMenu.DEF_ON, this.paraConf, this);
        final Image icon = this.paraConf.getIcon("admin.png");
        Icon icon2 = null;
        if (icon != null) {
            icon2 = new ImageIcon(icon);
        }
        item.setIcon(icon2);
        this.adminAdded = true;
    }
    
    public void adjustMod(final boolean b) {
        if (!b) {
            return;
        }
        if (this.modAdded) {
            return;
        }
        this.actionMenu.addSeparator();
        final JMenuItem item = createItem("Mn.Mod", "Moderation Console", this.actionMenu, TopMenu.DEF_ON, this.paraConf, this);
        final Image icon = this.paraConf.getIcon("mod.png");
        Icon icon2 = null;
        if (icon != null) {
            icon2 = new ImageIcon(icon);
        }
        item.setIcon(icon2);
        this.modAdded = true;
    }
    
    public void adjustSecBad() {
        this.setSecIcon("secbad.png");
        this.secureMenu.setToolTipText(null);
    }
    
    public void adjustSecGood() {
        this.setSecIcon("secure.png");
        try {
            if (!this.mainChat.appletChat.chatModel.cmIsTunnel()) {
                this.secureMenu.setToolTipText(((SSLSession)this.mainChat.appletChat.chatModel.cmSecSession()).getCipherSuite());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void setSecIcon(final String s) {
        final Image icon = this.paraConf.getIcon(s);
        Icon icon2 = null;
        if (icon != null) {
            icon2 = new ImageIcon(icon);
        }
        this.secureMenu.setIcon(icon2);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.paraConf.printer().print("act=" + actionCommand);
        if (actionCommand == null) {
            return;
        }
        if (actionCommand.startsWith(this.PRE_SD_ENTER)) {
            final String removePrefix = this.removePrefix(actionCommand, this.PRE_SD_ENTER);
            this.mainChat.appletChat.userChoice.soundEnter = removePrefix;
            this.mainChat.appletChat.clientChar.setFileEnter(removePrefix);
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.startsWith(this.PRE_SD_EXIT)) {
            final String removePrefix2 = this.removePrefix(actionCommand, this.PRE_SD_EXIT);
            this.mainChat.appletChat.userChoice.soundExit = removePrefix2;
            this.mainChat.appletChat.clientChar.setFileExit(removePrefix2);
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.startsWith(this.PRE_SD_NEW)) {
            final String removePrefix3 = this.removePrefix(actionCommand, this.PRE_SD_NEW);
            this.mainChat.appletChat.userChoice.soundNew = removePrefix3;
            this.mainChat.appletChat.clientChar.setFileText(removePrefix3);
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.startsWith(this.PRE_F_SIZE)) {
            final String removePrefix4 = this.removePrefix(actionCommand, this.PRE_F_SIZE);
            this.mainChat.appletChat.userChoice.localFontSize = WindowUtil.parseInt(removePrefix4, 12);
            this.mainChat.chatRender.fontChanged(this.mainChat.appletChat.userChoice.localFontSize);
            this.mainChat.userFontChanged(this.mainChat.appletChat.userChoice.localFontSize);
            this.mainChat.appletChat.clientChar.setFontSize(removePrefix4);
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.startsWith(this.PRE_F_NAME)) {
            final String removePrefix5 = this.removePrefix(actionCommand, this.PRE_F_NAME);
            this.mainChat.appletChat.userChoice.fontName = removePrefix5;
            if (!StringUtil.isEmpty(this.mainChat.appletChat.userChoice.fontName)) {
                this.mainChat.userFontChanged(this.mainChat.appletChat.userChoice.fontName);
            }
            this.mainChat.appletChat.clientChar.setMyFont(removePrefix5);
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.equals("Mn.Sound.Enter.On")) {
            this.mainChat.appletChat.userChoice.soundEnterOn = this.checkSoundEnter.isSelected();
            this.mainChat.appletChat.clientChar.setOnEnter(this.checkSoundEnter.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.equals("Mn.Sound.Exit.On")) {
            this.mainChat.appletChat.userChoice.soundExitOn = this.checkSoundExit.isSelected();
            this.mainChat.appletChat.clientChar.setOnExit(this.checkSoundExit.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.equals("Mn.Sound.New.On")) {
            this.mainChat.appletChat.userChoice.soundNewOn = this.checkSoundNew.isSelected();
            this.mainChat.appletChat.clientChar.setOnText(this.checkSoundNew.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if ("Mn.Playback".equals(actionCommand)) {
            this.mainChat.appletChat.openPlay();
            this.mainChat.appletChat.playWin.retrieve();
        }
        else if ("Mn.Avatar".equals(actionCommand)) {
            this.popAvatar = DisplayArea.popCommon("pclient.adx.PopFace", this.mainChat.appletChat, this.popAvatar, true);
        }
        else if (actionCommand.equals("Mn.Clear")) {
            this.actClear();
        }
        else if (actionCommand.equals("Mn.Bcast") || actionCommand.equals("Mn.Bcast.Vid")) {
            this.mainChat.appletChat.chatModel.cmAvBcast();
        }
        else if (actionCommand.equals("Mn.VwQ") || actionCommand.equals("Mn.VwQ.Vid")) {
            this.mainChat.appletChat.vwAvViewPermit(null, null);
        }
        else if (actionCommand.equals("Mn.Connect")) {
            this.mainChat.appletChat.reconnect();
        }
        else if (actionCommand.equals("Mn.Exit")) {
            this.mainChat.appletChat.exitChat();
        }
        else if (actionCommand.equals("Mn.RoomList") || actionCommand.equals("Mn.CreateRoom") || actionCommand.equals("Mn.UserSearch")) {
            this.popRoam = DisplayArea.popCommon("pclient.adx.PopRoam", this.mainChat.appletChat, this.popRoam);
        }
        else if (actionCommand.equals("Mn.ChatHelp")) {
            final String value = this.paraConf.get("URL.Help", "http://www.parachat.com/helpdesk/");
            this.mainChat.appletChat.vwInfo(value);
            this.paraConf.loadPage(value);
        }
        else if (actionCommand.equals("Mn.About")) {
            this.popAbout = DisplayArea.popCommon("pclient.adx.PopAbout", this.mainChat.appletChat, this.popAbout);
        }
        else if (actionCommand.equals("Mn.Trouble")) {
            this.popTrouble = DisplayArea.popCommon("pclient.adx.PopTrouble", this.mainChat.appletChat, this.popTrouble);
        }
        else if (actionCommand.equals("Mn.Mi")) {
            this.popMobile = DisplayArea.popCommon("pclient.adx.PopMobile", this.mainChat.appletChat, this.popMobile);
        }
        else if ("Mn.ShowJoin".equals(actionCommand)) {
            this.mainChat.appletChat.userChoice.showJoin = this.checkJoin.isSelected();
            this.mainChat.appletChat.clientChar.setShowJoin(this.checkJoin.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if ("Mn.ShowAvatar".equals(actionCommand)) {
            this.mainChat.appletChat.userChoice.showAvatar = this.checkAvatar.isSelected();
            this.mainChat.refreshUserList();
            this.mainChat.appletChat.clientChar.setShowAvatar(this.checkAvatar.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if ("Mn.BlockPrivate".equals(actionCommand)) {
            this.mainChat.appletChat.globalChoice.block = this.checkBlock.isSelected();
            this.mainChat.appletChat.chatModel.cmBlock(this.mainChat.appletChat.globalChoice.block);
            this.mainChat.appletChat.clientChar.setBlockPrivate(this.checkBlock.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if ("Mn.Rec".equals(actionCommand)) {
            this.mainChat.appletChat.globalChoice.reconnect = this.checkReconnect.isSelected();
            this.mainChat.appletChat.clientChar.setAuto(this.checkReconnect.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.equals("Mn.Tree")) {
            this.mainChat.showTreeOrNot(this.checkTree.isSelected());
            if (this.mainChat.appletChat.chatModel.cmIsConnected()) {
                this.mainChat.appletChat.chatModel.cmQueryList();
            }
            this.mainChat.appletChat.clientChar.setTree(this.checkTree.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.equals("Mn.Timestamp")) {
            this.mainChat.appletChat.globalChoice.timestamp = this.checkTime.isSelected();
            this.mainChat.appletChat.clientChar.setOptTime(this.checkTime.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.equals("Mn.RoomAudio")) {
            this.mainChat.appletChat.globalChoice.audio = this.checkAudio.isSelected();
            this.mainChat.appletChat.clientChar.setRoomAudio(this.checkAudio.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.equals("Mn.Smiley")) {
            this.mainChat.appletChat.globalChoice.smiley = this.checkSmile.isSelected();
            this.mainChat.appletChat.clientChar.setSmiley(this.checkSmile.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.equals("Mn.DoubleSpace")) {
            this.mainChat.appletChat.userChoice.doubleSpace = this.checkDouble.isSelected();
            this.mainChat.appletChat.clientChar.setDouble(this.checkDouble.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.equals("Mn.Separator")) {
            this.mainChat.appletChat.userChoice.separator = this.checkSep.isSelected();
            this.mainChat.appletChat.clientChar.setSep(this.checkSep.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.equals("Mn.Water")) {
            this.mainChat.chatRender.toggleWater(this.checkWater.isSelected());
            this.mainChat.appletChat.clientChar.setShowWater(this.checkWater.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.equals("Mn.Bg")) {
            final Color showDialog = JColorChooser.showDialog(this.mainChat.appletChat.getMainComp(), this.mainChat.appletChat.paraConf.title(), Color.WHITE);
            if (showDialog != null) {
                this.mainChat.chatRender.textPane.setBackground(showDialog);
                this.mainChat.appletChat.clientChar.setBg("" + showDialog.getRGB());
                this.mainChat.appletChat.clientChar.saveInfo();
            }
        }
        else if (actionCommand.equals("Mn.Page")) {
            this.mainChat.appletChat.chatModel.cmPage();
        }
        else if (actionCommand.equals("Mn.Admin")) {
            this.mainChat.appletChat.openAdmin();
        }
        else if (actionCommand.equals("Mn.Mod")) {
            this.mainChat.appletChat.vwModPop();
        }
        else if (actionCommand.equals("Mn.SecConn")) {
            DisplayArea.popCommon("pclient.adx.PopSecure", this.mainChat.appletChat, null);
        }
        else if (actionCommand.equals("Mn.ShowVid") || actionCommand.equals("Mn.ShowVid.Vid")) {
            this.mainChat.appletChat.globalChoice.haveVideo = this.checkShowVideo.isSelected();
            this.mainChat.appletChat.chatModel.cmAvShowVid(this.checkShowVideo.isSelected());
            if (this.mainChat.videoPop != null) {
                this.mainChat.videoPop.showVideoCheck.setState(this.checkShowVideo.isSelected());
            }
        }
        else if (actionCommand.equals("Mn.Nt.Pub")) {
            this.mainChat.appletChat.globalChoice.popPublic = this.checkNtPub.isSelected();
            this.mainChat.appletChat.clientChar.setNotifyPub(this.checkNtPub.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.equals("Mn.Nt.Prv")) {
            this.mainChat.appletChat.globalChoice.popPrivate = this.checkNtPrv.isSelected();
            this.mainChat.appletChat.clientChar.setNotifyPrv(this.checkNtPrv.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
        else if (actionCommand.equals("Mn.Apv.Prv")) {
            this.mainChat.appletChat.globalChoice.approvePrivate = this.checkApprove.isSelected();
            this.mainChat.appletChat.clientChar.setApprovePrv(this.checkApprove.isSelected());
            this.mainChat.appletChat.clientChar.saveInfo();
        }
    }
    
    private String removePrefix(final String s, final String s2) {
        if (s.length() <= s2.length()) {
            return "";
        }
        return s.substring(s2.length());
    }
    
    private void actClear() {
        if (JOptionPane.showConfirmDialog(this.mainChat.appletChat.getMainComp(), this.paraConf.get("Msg.Clear", "Are you sure you want to clear all the chat text?"), " ", 0) == 0) {
            this.mainChat.chatRender.clearText();
        }
    }
    
    private void createMenu() {
        this.menuBar = new JMenuBar();
        this.videoMenu = this.getVideo();
        final boolean bool = this.paraConf.getBool("Add.Mn.Action", true);
        final JMenu action = this.getAction();
        if (bool) {
            this.menuBar.add(action);
        }
        this.actionMenu = action;
        final boolean bool2 = this.paraConf.getBool("Add.Mn.Options", true);
        final JMenu options = this.getOptions();
        if (bool2) {
            this.menuBar.add(options);
        }
        final boolean bool3 = this.paraConf.getBool("Add.Mn.Roam", false);
        final JMenu roam = this.getRoam();
        if (bool3 && this.paraConf.isRoam()) {
            this.menuBar.add(roam);
        }
        if (this.paraConf.getBool("Add.Mn.Av", true) && !this.paraConf.getBool("Op.Av.Sub", true)) {
            this.menuBar.add(this.videoMenu);
        }
        final boolean bool4 = this.paraConf.getBool("Add.Mn.M", true);
        final JMenu mobile = this.getMobile();
        if (bool4) {
            this.menuBar.add(mobile);
        }
        final boolean bool5 = this.paraConf.getBool("Add.Mn.Help", true);
        final JMenu help = this.getHelp();
        if (bool5) {
            this.menuBar.add(help);
        }
        final boolean secure = this.paraConf.isSecure();
        final JMenu secure2 = this.getSecure();
        this.secureMenu = secure2;
        if (secure) {
            this.menuBar.add(secure2);
            this.adjustSecBad();
        }
    }
    
    private JMenu getAction() {
        final JMenu menu = new JMenu(this.paraConf.get("Mn.Action", "Action"));
        Image image = null;
        if (this.paraConf.getBool("Add.Mn.Av", true) && this.paraConf.getBool("Op.Av.Sub", true)) {
            menu.add(this.videoMenu);
            if (!this.paraConf.isSimpleCSR()) {
                if (this.paraConf.isAudioOnly()) {
                    image = this.paraConf.getIcon("showaud.png");
                }
                else {
                    image = this.paraConf.getIcon("showvid.png");
                }
            }
            Icon icon = null;
            if (image != null) {
                icon = new ImageIcon(image);
            }
            if (icon != null) {
                this.videoMenu.setIcon(icon);
            }
        }
        Image icon2 = null;
        if (!this.paraConf.isSimpleCSR()) {
            icon2 = this.paraConf.getIcon("history.png");
        }
        Icon icon3 = null;
        if (icon2 != null) {
            icon3 = new ImageIcon(icon2);
        }
        final JMenuItem item = createItem("Mn.Playback", "Recent Room History", menu, "Add.Mn.Play", this.paraConf, this);
        item.setIcon(icon3);
        if (!this.paraConf.getBool("Enable.Mn.Play", true)) {
            item.setEnabled(false);
        }
        Image icon4 = null;
        if (!this.paraConf.isSimpleCSR()) {
            icon4 = this.paraConf.getIcon("avatar.png");
        }
        Icon icon5 = null;
        if (icon4 != null) {
            icon5 = new ImageIcon(icon4);
        }
        final JMenuItem item2 = createItem("Mn.Avatar", "My Avatar", menu, "Add.Mn.Avatar", this.paraConf, this);
        item2.setIcon(icon5);
        if (!this.paraConf.getBool("Op.Avatar", false)) {
            menu.remove(item2);
        }
        Image icon6 = null;
        if (!this.paraConf.isSimpleCSR()) {
            icon6 = this.paraConf.getIcon("erase.png");
        }
        Icon icon7 = null;
        if (icon6 != null) {
            icon7 = new ImageIcon(icon6);
        }
        createItem("Mn.Clear", "Clear Text", menu, TopMenu.DEF_ON, this.paraConf, this).setIcon(icon7);
        Image icon8 = null;
        if (!this.paraConf.isSimpleCSR()) {
            icon8 = this.paraConf.getIcon("log-on.png");
        }
        Icon icon9 = null;
        if (icon8 != null) {
            icon9 = new ImageIcon(icon8);
        }
        createItem("Mn.Connect", "Connect", menu, TopMenu.DEF_ON, this.paraConf, this).setIcon(icon9);
        menu.addSeparator();
        Image icon10 = null;
        if (!this.paraConf.isSimpleCSR()) {
            icon10 = this.paraConf.getIcon("log-off.png");
        }
        Icon icon11 = null;
        if (icon10 != null) {
            icon11 = new ImageIcon(icon10);
        }
        createItem("Mn.Exit", "Exit", menu, TopMenu.DEF_ON, this.paraConf, this).setIcon(icon11);
        return menu;
    }
    
    private JMenu getOptions() {
        final JMenu menu = new JMenu(this.paraConf.get("Mn.Options", "Options"));
        this.checkTree = createCheck("Mn.Tree", "Tree View", menu, "Add.Mn.Tree", this.paraConf, this);
        this.checkTime = createCheck("Mn.Timestamp", "Timestamp", menu, "Add.Mn.Timestamp", this.paraConf, this);
        (this.checkSmile = createCheck("Mn.Smiley", "Display Smiley Faces", menu, TopMenu.DEF_ON, this.paraConf, this)).setState(true);
        (this.checkAudio = createCheck("Mn.RoomAudio", "Room Audio", menu, "Add.Mn.RoomAudio", this.paraConf, this)).setState(true);
        (this.checkBlock = createCheck("Mn.BlockPrivate", "Block All Private Messages", menu, "Add.Mn.BlockPrivate", this.paraConf, this)).setState(this.mainChat.appletChat.globalChoice.block);
        (this.checkReconnect = createCheck("Mn.Rec", "Auto Reconnect", menu, "Add.Mn.Rec", this.paraConf, this)).setState(this.mainChat.appletChat.globalChoice.reconnect);
        if (!this.paraConf.isRoam() || !this.paraConf.getBool("Add.Mn.Tree", false)) {
            menu.remove(this.checkTree);
        }
        menu.addSeparator();
        int n = 0;
        if (this.paraConf.getBool("Add.Mn.SubEnter", true)) {
            menu.add(this.getSoundEnter());
            ++n;
        }
        if (this.paraConf.getBool("Add.Mn.SubExit", true)) {
            menu.add(this.getSoundExit());
            ++n;
        }
        if (this.paraConf.getBool("Add.Mn.SubNew", true)) {
            menu.add(this.getSoundNew());
            ++n;
        }
        this.checkNtPub = createCheck("Mn.Nt.Pub", "Notify Public Messages", menu, "Add.Mn.NotiPub", this.paraConf, this);
        this.checkNtPrv = createCheck("Mn.Nt.Prv", "Notify Private Messages", menu, "Add.Mn.NotiPrv", this.paraConf, this);
        this.checkApprove = createCheck("Mn.Apv.Prv", "Approve All Private Chat", menu, "Add.Mn.ApvPrv", this.paraConf, this);
        this.checkNtPub.setState(this.mainChat.appletChat.globalChoice.popPublic);
        this.checkNtPrv.setState(this.mainChat.appletChat.globalChoice.popPrivate);
        this.checkApprove.setState(this.mainChat.appletChat.globalChoice.approvePrivate);
        menu.addSeparator();
        (this.checkJoin = createCheck("Mn.ShowJoin", "Show Join/Leave", menu, "Add.Mn.ShowJoin", this.paraConf, this)).setState(this.mainChat.appletChat.userChoice.showJoin);
        (this.checkAvatar = createCheck("Mn.ShowAvatar", "Show Avatars", menu, TopMenu.DEF_ON, this.paraConf, this)).setState(this.mainChat.appletChat.userChoice.showAvatar);
        if (!this.paraConf.getBool("Op.Avatar", false)) {
            menu.remove(this.checkAvatar);
        }
        (this.checkWater = createCheck("Mn.Water", "Show Background Image", menu, "Add.Mn.Water", this.paraConf, this)).setState(true);
        this.checkDouble = createCheck("Mn.DoubleSpace", "Double Space", menu, TopMenu.DEF_ON, this.paraConf, this);
        this.checkSep = createCheck("Mn.Separator", "Insert Separator", menu, TopMenu.DEF_ON, this.paraConf, this);
        menu.addSeparator();
        menu.add(this.getFontSize());
        menu.add(this.getFontList());
        createItem("Mn.Bg", "Window Background Color", menu, TopMenu.DEF_ON, this.paraConf, this);
        return menu;
    }
    
    private JMenu getRoam() {
        final JMenu menu = new JMenu(this.paraConf.get("Mn.Roam", "Roam"));
        createItem("Mn.RoomList", "Room List", menu, "Add.Mn.RoomList", this.paraConf, this);
        createItem("Mn.CreateRoom", "Create Room", menu, "Add.Mn.CreateRoom", this.paraConf, this);
        createItem("Mn.UserSearch", "User Search", menu, "Add.Mn.UserSearch", this.paraConf, this);
        return menu;
    }
    
    private JMenu getMobile() {
        final JMenu menu = new JMenu(this.paraConf.get("Mn.M", "Mobile"));
        createItem("Mn.Mi", "Mobile Interface", menu, "Add.Mn.Mi", this.paraConf, this);
        return menu;
    }
    
    private JMenu getHelp() {
        final JMenu menu = new JMenu(this.paraConf.get("Mn.Help", "Help"));
        createItem("Mn.ChatHelp", "Chat Help", menu, "Add.Mn.ChatHelp", this.paraConf, this);
        createItem("Mn.About", "About", menu, "Add.Mn.About", this.paraConf, this);
        createItem("Mn.Trouble", "Troubleshooting", menu, "Add.Mn.Trouble", this.paraConf, this);
        return menu;
    }
    
    private JMenu getVideo() {
        String s;
        if (this.paraConf.isAudioOnly()) {
            s = this.paraConf.get("Mn.Av", "Voice Chat");
        }
        else {
            s = this.paraConf.get("Mn.Av.Vid", "AV");
        }
        final JMenu menu = new JMenu(s);
        this.checkShowVideo = createVideoItems(menu, this.paraConf, this);
        return menu;
    }
    
    private static JCheckBoxMenuItem createVideoItems(final JMenu menu, final Config config, final ActionListener actionListener) {
        Image image = null;
        if (!config.isSimpleCSR()) {
            if (config.isAudioOnly()) {
                image = config.getIcon("showaud.png");
            }
            else {
                image = config.getIcon("showvid.png");
            }
        }
        Icon icon = null;
        if (image != null) {
            icon = new ImageIcon(image);
        }
        JMenuItem menuItem;
        if (config.isAudioOnly()) {
            menuItem = createItem("Mn.Bcast", TopMenu.TXT_AV_CAST, menu, TopMenu.DEF_ON, config, actionListener);
        }
        else {
            menuItem = createItem("Mn.Bcast.Vid", TopMenu.TXT_AV_CAST_VID, menu, TopMenu.DEF_ON, config, actionListener);
        }
        menuItem.setIcon(icon);
        if (!config.getBool("Ctrl.Av", false)) {
            menuItem.setEnabled(false);
        }
        Image icon2 = null;
        if (!config.isSimpleCSR()) {
            icon2 = config.getIcon("showvq.png");
        }
        Icon icon3 = null;
        if (icon2 != null) {
            icon3 = new ImageIcon(icon2);
        }
        JMenuItem menuItem2;
        if (config.isAudioOnly()) {
            menuItem2 = createItem("Mn.VwQ", TopMenu.TXT_AV_Q, menu, TopMenu.DEF_ON, config, actionListener);
        }
        else {
            menuItem2 = createItem("Mn.VwQ.Vid", TopMenu.TXT_AV_Q_VID, menu, TopMenu.DEF_ON, config, actionListener);
        }
        menuItem2.setIcon(icon3);
        if (!config.getBool("Ctrl.Av", false)) {
            menuItem2.setEnabled(false);
        }
        menu.addSeparator();
        JCheckBoxMenuItem checkBoxMenuItem;
        if (config.isAudioOnly()) {
            checkBoxMenuItem = createCheck("Mn.ShowVid", TopMenu.TXT_AV_SHOW, menu, TopMenu.DEF_ON, config, actionListener);
        }
        else {
            checkBoxMenuItem = createCheck("Mn.ShowVid.Vid", TopMenu.TXT_AV_SHOW_VID, menu, TopMenu.DEF_ON, config, actionListener);
        }
        if (!config.getBool("Ctrl.Av", false)) {
            checkBoxMenuItem.setEnabled(false);
        }
        return checkBoxMenuItem;
    }
    
    private JMenu getSecure() {
        final JMenu menu = new JMenu(this.paraConf.get("Mn.Sec", "Security"));
        createItem("Mn.SecConn", "Security Info", menu, TopMenu.DEF_ON, this.paraConf, this);
        return menu;
    }
    
    private JMenu getSoundEnter() {
        final ButtonGroup buttonGroup = new ButtonGroup();
        (this.checkSoundEnter = this.createCheck("Mn.Sound.Enter.On", "On")).setState(this.mainChat.appletChat.userChoice.soundEnterOn);
        return this.getSubSound("Mn.Sound.Enter", "Sound on Enter", buttonGroup, this.checkSoundEnter, "Mn.Sound.Enter.On", this.PRE_SD_ENTER);
    }
    
    private JMenu getSoundExit() {
        final ButtonGroup buttonGroup = new ButtonGroup();
        (this.checkSoundExit = this.createCheck("Mn.Sound.Exit.On", "On")).setState(this.mainChat.appletChat.userChoice.soundExitOn);
        return this.getSubSound("Mn.Sound.Exit", "Sound on Exit", buttonGroup, this.checkSoundExit, "Mn.Sound.Exit.On", this.PRE_SD_EXIT);
    }
    
    private JMenu getSoundNew() {
        final ButtonGroup buttonGroup = new ButtonGroup();
        (this.checkSoundNew = this.createCheck("Mn.Sound.New.On", "On")).setState(this.mainChat.appletChat.userChoice.soundNewOn);
        return this.getSubSound("Mn.Sound.NewText", "Sound on New Text", buttonGroup, this.checkSoundNew, "Mn.Sound.New.On", this.PRE_SD_NEW);
    }
    
    private JMenu getSubSound(final String s, final String s2, final ButtonGroup buttonGroup, final JCheckBoxMenuItem checkBoxMenuItem, final String s3, final String s4) {
        final JMenu menu = new JMenu(this.paraConf.get(s, s2));
        if (this.paraConf.getBool(s3, true)) {
            menu.add(checkBoxMenuItem);
        }
        menu.addSeparator();
        String s5 = this.paraConf.get("Cf.Alert", null);
        if (s5 == null) {
            return menu;
        }
        if (StringUtil.isTrimmedEmpty(s5)) {
            s5 = this.paraConf.serverConf("Cf.Alert", "");
        }
        final String[] splitString = StringUtil.splitString(s5, ",", true);
        for (int i = 0; i < splitString.length; ++i) {
            final JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem(splitString[i]);
            radioButtonMenuItem.setActionCommand(s4 + splitString[i]);
            radioButtonMenuItem.addActionListener(this);
            menu.add(radioButtonMenuItem);
            buttonGroup.add(radioButtonMenuItem);
        }
        return menu;
    }
    
    private JMenu getFontSize() {
        final JMenu menu = new JMenu(this.paraConf.get("Mn.FontSize", "Font Size"));
        final ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 8; i < 24; i += 2) {
            final JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem(" " + i + " ");
            radioButtonMenuItem.setActionCommand(this.PRE_F_SIZE + i);
            radioButtonMenuItem.addActionListener(this);
            menu.add(radioButtonMenuItem);
            buttonGroup.add(radioButtonMenuItem);
        }
        return menu;
    }
    
    private JMenu getFontList() {
        final JMenu menu = new JMenu(this.paraConf.get("Mn.FontName", "My User Font"));
        final String value = this.paraConf.get("Cf.Fonts", null);
        if (value == null) {
            return menu;
        }
        final ButtonGroup buttonGroup = new ButtonGroup();
        final String[] splitString = StringUtil.splitString(value, ",", true);
        for (int i = 0; i < splitString.length; ++i) {
            final JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem(splitString[i]);
            radioButtonMenuItem.setActionCommand(this.PRE_F_NAME + splitString[i]);
            radioButtonMenuItem.addActionListener(this);
            menu.add(radioButtonMenuItem);
            buttonGroup.add(radioButtonMenuItem);
        }
        return menu;
    }
    
    private static JMenuItem createItem(final String actionCommand, final String s, final JMenu menu, final String s2, final Config config, final ActionListener actionListener) {
        final JMenuItem menuItem = new JMenuItem(config.get(actionCommand, s));
        menuItem.setActionCommand(actionCommand);
        menuItem.addActionListener(actionListener);
        if (config.getBool(s2, true)) {
            menu.add(menuItem);
        }
        return menuItem;
    }
    
    private static JCheckBoxMenuItem createCheck(final String actionCommand, final String s, final JMenu menu, final String s2, final Config config, final ActionListener actionListener) {
        final JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem(config.get(actionCommand, s));
        checkBoxMenuItem.setActionCommand(actionCommand);
        checkBoxMenuItem.addActionListener(actionListener);
        if (config.getBool(s2, true)) {
            menu.add(checkBoxMenuItem);
        }
        return checkBoxMenuItem;
    }
    
    private JCheckBoxMenuItem createCheck(final String actionCommand, final String s) {
        final JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem(this.paraConf.get(actionCommand, s));
        checkBoxMenuItem.setActionCommand(actionCommand);
        checkBoxMenuItem.addActionListener(this);
        return checkBoxMenuItem;
    }
    
    static {
        TopMenu.TXT_AV_CAST = "Start My Voice Chat Broadcast";
        TopMenu.TXT_AV_CAST_VID = "Start My Voice/Video Chat Broadcast";
        TopMenu.TXT_AV_Q = "Show Requests to Hear My Broadcast";
        TopMenu.TXT_AV_Q_VID = "Show Requests to View My Broadcast";
        TopMenu.TXT_AV_SHOW = "Show My Voice Chat Icon";
        TopMenu.TXT_AV_SHOW_VID = "Show My Voice/Video Icon";
        TopMenu.DEF_ON = "#__";
    }
}
