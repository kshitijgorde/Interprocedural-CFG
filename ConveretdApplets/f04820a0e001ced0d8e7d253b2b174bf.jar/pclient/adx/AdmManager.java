// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.AbstractButton;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JComponent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.pchat.sc.StringUtil;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import com.pchat.sc.ServicePack;
import java.awt.Container;
import java.util.Hashtable;
import pclient.shd.Config;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import java.awt.CardLayout;
import javax.swing.JPanel;
import pclient.adv.AppletSpice;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import pclient.adv.AdmInter;
import javax.swing.JFrame;

public class AdmManager extends JFrame implements AdmInter, ActionListener, WindowListener
{
    private static final String ACT_NEWS = "news";
    private static final String ACT_ROOM = "room";
    private static final String ACT_USER = "user";
    private static final String ACT_MSG = "msg";
    private static final String ACT_CRUISE = "cruise";
    private static final String ACT_MOD = "MOD";
    private static final String ACT_SITE = "SITE";
    private static final String ACT_SUPER = "SUPER";
    private static final String ACT_LOG = "log";
    private static final String ACT_HELP = "HELP";
    protected AppletSpice paraApplet;
    private JPanel cardPanel;
    private CardLayout cardFlip;
    private ButtonGroup toolbarGroup;
    private JLabel infoText;
    private AmLog tabLog;
    private AmSuper tabSuper;
    private AmMod tabMod;
    private AmCruise tabCruise;
    private AmMsg tabMsg;
    private AmUser tabUser;
    private AmNews tabNews;
    private AmRoom tabRoom;
    private AmSite tabSite;
    private AmHelp tabHelp;
    private AdmHandler adminHandler;
    private Config paraConf;
    private Hashtable permTable;
    
    public AdmManager() {
        this.permTable = new Hashtable();
    }
    
    public void setPara(final AppletSpice paraApplet) {
        this.paraApplet = paraApplet;
        this.paraConf = paraApplet.paraConf;
        this.adminHandler = new AdmHandler(paraApplet, this);
        this.toolbarGroup = new ButtonGroup();
        this.setSize(600, 680);
        this.addWindowListener(this);
        this.setDefaultCloseOperation(0);
        this.buildGUI();
        this.cardFlip.show(this.cardPanel, "news");
        String cmUserSelf = this.paraApplet.chatModel.cmUserSelf();
        if (cmUserSelf == null) {
            cmUserSelf = "";
        }
        this.setTitle("Admin Console - " + cmUserSelf + " \"" + ("Site ID=" + this.paraConf.get("Net.Site")) + "\"");
    }
    
    public void startUp() {
        this.setOperatorPermissions();
        this.setVisible(true);
    }
    
    public void clearAll() {
        this.setVisible(false);
        this.dispose();
    }
    
    public void serverMsg(final ServicePack servicePack) {
        this.adminHandler.paraApplet.paraConf.printer().print("service=" + servicePack);
        if (servicePack.category != 300) {
            final String s = "Error: #582.";
            this.printInfo(s);
            this.printLog(s);
            System.out.println(s);
            return;
        }
        String pvalue = null;
        if (servicePack.paramCount > 0) {
            pvalue = servicePack.paramList[0].pvalue;
        }
        switch (servicePack.command) {
            case 602: {
                if (pvalue != null) {
                    this.adminHandler.info(pvalue);
                    break;
                }
                break;
            }
            case 604: {
                if (pvalue != null) {
                    this.adminHandler.error(pvalue);
                    break;
                }
                break;
            }
            case 612: {
                if (pvalue != null) {
                    this.tabCruise.populateList(this.split(pvalue));
                    break;
                }
                break;
            }
            case 614: {
                if (pvalue != null) {
                    this.tabMsg.populateList(this.split(pvalue));
                    break;
                }
                break;
            }
            case 618: {
                if (pvalue != null) {
                    this.tabRoom.setStatus(pvalue);
                    break;
                }
                break;
            }
            case 620: {
                if (pvalue != null) {
                    this.adminHandler.popInfo(pvalue);
                    break;
                }
                break;
            }
            default: {
                System.out.println("Err24372382. unknown=." + servicePack.command);
                break;
            }
        }
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
        System.out.println("admin console closed ");
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand != null) {
            this.cardFlip.show(this.cardPanel, actionCommand);
            this.tabSuper.refresh();
            this.tabMod.refresh();
            this.tabCruise.refresh();
            this.tabMsg.refresh();
            this.tabUser.refresh();
        }
    }
    
    public boolean isAdvDisabled(final String s) {
        return this.paraConf.isAdv();
    }
    
    protected void printInfo(String string) {
        string = "<html><p>&nbsp " + string + "</p></html>";
        this.infoText.setText(string);
    }
    
    protected void printLog(final String s) {
        this.tabLog.appendLog(s);
    }
    
    private String[] split(final String s) {
        return StringUtil.splitString(s, "\n", false);
    }
    
    private void setOperatorPermissions() {
        System.out.println("role:" + this.paraApplet.chatModel.cmProps().role);
        System.out.println("perms:" + this.paraApplet.chatModel.cmProps().permissions);
        this.permTable.clear();
        if (!"oper".equals(this.paraApplet.chatModel.cmProps().role)) {
            return;
        }
        final String permissions = this.paraApplet.chatModel.cmProps().permissions;
        if (!StringUtil.isTrimmedEmpty(permissions)) {
            final String[] splitString = StringUtil.splitString(permissions, ",", true);
            for (int i = 0; i < splitString.length; ++i) {
                this.permTable.put(splitString[i], splitString[i]);
            }
        }
        this.fixAdminPerms();
    }
    
    private boolean getAllowed(final String s) {
        return this.permTable.get(s) != null;
    }
    
    private void fixAdminPerms() {
        this.permsRooms();
        this.permsUsers();
        this.permsMessages();
        this.permsCruise();
        this.permsModeration();
        this.permsSite();
    }
    
    private void permsRooms() {
        this.tabRoom.topicButton.setEnabled(this.getAllowed("P_GREET"));
        this.tabRoom.openButton.setEnabled(this.getAllowed("P_OPEN"));
        this.tabRoom.closeButton.setEnabled(this.getAllowed("P_CLOSE"));
        this.tabRoom.setPasswd.setEnabled(this.getAllowed("P_PASS"));
        this.tabRoom.removePasswd.setEnabled(this.getAllowed("P_PASS"));
        this.tabRoom.transcriptOn.setEnabled(this.getAllowed("P_TRANS"));
        this.tabRoom.transcriptOff.setEnabled(this.getAllowed("P_TRANS"));
        this.tabRoom.clearHistory.setEnabled(this.getAllowed("P_HIST"));
        this.tabRoom.profaneOn.setEnabled(this.getAllowed("P_PROF"));
        this.tabRoom.profaneOff.setEnabled(this.getAllowed("P_PROF"));
        this.tabRoom.shoutOn.setEnabled(this.getAllowed("P_SHOUT"));
        this.tabRoom.shoutOff.setEnabled(this.getAllowed("P_SHOUT"));
    }
    
    private void permsUsers() {
        this.tabUser.gagOn.setEnabled(this.getAllowed("P_GAG"));
        this.tabUser.gagOff.setEnabled(this.getAllowed("P_GAG"));
        this.tabUser.expelUser.setEnabled(this.getAllowed("P_EXP"));
        this.tabUser.banIPCheck.setEnabled(this.getAllowed("P_IP"));
        this.tabUser.banIPRange.setEnabled(this.getAllowed("P_IPC"));
        this.tabUser.cookieGag.setEnabled(this.getAllowed("P_COOKGG"));
        this.tabUser.cookieBan.setEnabled(this.getAllowed("P_COOKB"));
        this.tabUser.invisibleOn.setEnabled(this.getAllowed("P_INV"));
        this.tabUser.invisibleOff.setEnabled(this.getAllowed("P_INV"));
        this.tabUser.avatarOn.setEnabled(this.getAllowed("P_AVA"));
        this.tabUser.avatarOff.setEnabled(this.getAllowed("P_AVA"));
        this.tabUser.topOn.setEnabled(this.getAllowed("P_TOP"));
        this.tabUser.topOff.setEnabled(this.getAllowed("P_TOP"));
        this.tabUser.unbanIPButton.setEnabled(this.getAllowed("P_IP"));
        this.tabUser.banNameButton.setEnabled(this.getAllowed("P_BANU"));
        this.tabUser.unbanNameButton.setEnabled(this.getAllowed("P_BANU"));
    }
    
    private void permsMessages() {
        this.tabMsg.addMsg.setEnabled(this.getAllowed("P_STORE"));
        this.tabMsg.delMsg.setEnabled(this.getAllowed("P_STORE"));
        this.tabMsg.saveMsg.setEnabled(this.getAllowed("P_STORE"));
        this.tabMsg.loadMsg.setEnabled(this.getAllowed("P_STORE"));
        this.tabMsg.sendMsg.setEnabled(this.getAllowed("P_SEND"));
    }
    
    private void permsCruise() {
        this.tabCruise.addLink.setEnabled(this.getAllowed("P_LINKS"));
        this.tabCruise.delLink.setEnabled(this.getAllowed("P_LINKS"));
        this.tabCruise.saveLink.setEnabled(this.getAllowed("P_LINKS"));
        this.tabCruise.loadLink.setEnabled(this.getAllowed("P_LINKS"));
        this.tabCruise.sendLink.setEnabled(this.getAllowed("P_URL"));
    }
    
    private void permsModeration() {
        this.tabMod.moderationOn.setEnabled(this.getAllowed("P_MOD"));
        this.tabMod.moderationOff.setEnabled(this.getAllowed("P_MOD"));
        this.tabMod.addModerator.setEnabled(this.getAllowed("P_TOR"));
        this.tabMod.delModerator.setEnabled(this.getAllowed("P_TOR"));
        this.tabMod.showModerator.setEnabled(this.getAllowed("P_TOR"));
        this.tabMod.addSpeaker.setEnabled(this.getAllowed("P_KER"));
        this.tabMod.delSpeaker.setEnabled(this.getAllowed("P_KER"));
        this.tabMod.showSpeaker.setEnabled(this.getAllowed("P_KER"));
    }
    
    private void permsSite() {
        this.tabSite.broadcastSite.setEnabled(this.getAllowed("P_CAST"));
        this.tabSite.clearSite.setEnabled(this.getAllowed("P_CLEAR"));
    }
    
    private void buildGUI() {
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(this.getToolbar(this.paraConf), "North");
        this.getContentPane().add(this.getMain(), "Center");
        this.getContentPane().add(this.getBottomStatus(), "South");
    }
    
    private JComponent getToolbar(final Config config) {
        final JToolBar toolBar = new JToolBar("Main Menu");
        this.getToggle(config, "News", null, toolBar, "news", "Thank you for using ParaChat");
        this.getToggle(config, "Rooms", null, toolBar, "room", "Settings of Basic Room Configuration");
        this.getToggle(config, "Users", null, toolBar, "user", "User Admin");
        this.getToggle(config, "Messages", null, toolBar, "msg", "Prepared Messages");
        this.getToggle(config, "Cruise", null, toolBar, "cruise", "Saved Cruise Web Links");
        this.getToggle(config, "Moderation", null, toolBar, "MOD", "Settings for Moderation");
        this.getToggle(config, "Site", null, toolBar, "SITE", "Site Level Functions");
        final JToggleButton toggle = this.getToggle(config, "Super", null, toolBar, "SUPER", "Super User");
        if (!"super".equals(this.paraApplet.chatModel.cmProps().role)) {
            toolBar.remove(toggle);
        }
        this.getToggle(config, "Log", null, toolBar, "log", "admin log");
        this.getToggle(config, "Help", null, toolBar, "HELP", "help");
        toolBar.setRollover(true);
        return toolBar;
    }
    
    private JComponent getBottomStatus() {
        final JLabel infoText = new JLabel(" ");
        infoText.setForeground(Color.BLUE);
        return this.infoText = infoText;
    }
    
    private JToggleButton getToggle(final Config config, final String s, final String s2, final JToolBar toolBar, final String actionCommand, final String toolTipText) {
        final JToggleButton toggleButton = new JToggleButton(s);
        toggleButton.setMargin(new Insets(1, 1, 1, 1));
        toggleButton.setEnabled(true);
        toggleButton.setActionCommand(actionCommand);
        toggleButton.addActionListener(this);
        if (toolTipText != null) {
            toggleButton.setToolTipText(toolTipText);
        }
        toolBar.add(toggleButton);
        this.toolbarGroup.add(toggleButton);
        return toggleButton;
    }
    
    private JComponent getMain() {
        this.cardFlip = new CardLayout();
        this.cardPanel = new JPanel(this.cardFlip);
        this.tabNews = new AmNews(this.adminHandler);
        this.tabRoom = new AmRoom(this.adminHandler);
        this.tabSite = new AmSite(this.adminHandler);
        this.tabHelp = new AmHelp(this.adminHandler);
        this.tabLog = new AmLog(this.adminHandler);
        this.tabSuper = new AmSuper(this.adminHandler);
        this.tabMod = new AmMod(this.adminHandler);
        this.tabCruise = new AmCruise(this.adminHandler);
        this.tabMsg = new AmMsg(this.adminHandler);
        this.tabUser = new AmUser(this.adminHandler);
        this.cardPanel.add(this.tabNews, "news");
        this.cardPanel.add(this.tabRoom, "room");
        this.cardPanel.add(this.tabUser, "user");
        this.cardPanel.add(this.tabMsg, "msg");
        this.cardPanel.add(this.tabCruise, "cruise");
        this.cardPanel.add(this.tabMod, "MOD");
        this.cardPanel.add(this.tabSite, "SITE");
        this.cardPanel.add(this.tabSuper, "SUPER");
        this.cardPanel.add(this.tabLog, "log");
        this.cardPanel.add(this.tabHelp, "HELP");
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(Box.createRigidArea(new Dimension(5, 12)), "North");
        panel.add(this.cardPanel, "Center");
        return panel;
    }
}
