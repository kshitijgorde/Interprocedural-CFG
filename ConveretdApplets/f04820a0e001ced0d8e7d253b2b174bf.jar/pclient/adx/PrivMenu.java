// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import javax.swing.AbstractButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import pclient.shd.ChatModel;
import java.awt.Component;
import javax.swing.JColorChooser;
import java.awt.Color;
import com.pchat.sc.StringUtil;
import com.pchat.sc.WindowUtil;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuBar;
import pclient.shd.Config;
import java.awt.event.ActionListener;

public class PrivMenu implements ActionListener
{
    private Config paraConf;
    private PrivWindow privateChat;
    private JMenuBar menuBar;
    private String DEF_ON;
    private String PRE_F_SIZE;
    private String PRE_F_NAME;
    protected JCheckBoxMenuItem checkIgnore;
    private JCheckBoxMenuItem checkDouble;
    private JCheckBoxMenuItem checkSep;
    protected JCheckBoxMenuItem checkBeep;
    
    public PrivMenu(final PrivWindow privateChat) {
        this.DEF_ON = "#__";
        this.PRE_F_SIZE = "_fs";
        this.PRE_F_NAME = "_fn";
        this.privateChat = privateChat;
        this.paraConf = privateChat.getConf();
        this.createMenu();
    }
    
    public JMenuBar getBar() {
        return this.menuBar;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        final ChatModel chatModel = this.privateChat.privateMan.paraApplet.chatModel;
        this.paraConf.printer().print("act=" + actionCommand);
        if (actionCommand == null) {
            return;
        }
        if (actionCommand.startsWith(this.PRE_F_SIZE)) {
            this.privateChat.userChoice.localFontSize = WindowUtil.parseInt(this.removePrefix(actionCommand, this.PRE_F_SIZE), 12);
            this.privateChat.chatRender.fontChanged(this.privateChat.userChoice.localFontSize);
            this.privateChat.textInput.setFont(WindowUtil.changeSize(this.privateChat.textInput.getFont(), this.privateChat.userChoice.localFontSize));
        }
        else if (actionCommand.startsWith(this.PRE_F_NAME)) {
            this.privateChat.userChoice.fontName = this.removePrefix(actionCommand, this.PRE_F_NAME);
            if (!StringUtil.isEmpty(this.privateChat.userChoice.fontName)) {
                this.privateChat.textInput.setFont(WindowUtil.changeName(this.privateChat.textInput.getFont(), this.privateChat.userChoice.fontName));
            }
        }
        else if (actionCommand.equals("Mn.Ignore")) {
            if (this.checkIgnore.isSelected()) {
                chatModel.cmAddIgnore(this.privateChat.thirdParty);
            }
            else {
                chatModel.cmDeleteIgnore(this.privateChat.thirdParty);
            }
        }
        else if (actionCommand.equals("Mn.Clear")) {
            this.actClear();
        }
        else if (actionCommand.equals("Mn.DoubleSpace")) {
            this.privateChat.userChoice.doubleSpace = this.checkDouble.isSelected();
        }
        else if (actionCommand.equals("Mn.Separator")) {
            this.privateChat.userChoice.separator = this.checkSep.isSelected();
        }
        else if (actionCommand.equals("Mn.Beep")) {
            this.privateChat.privateMan.setBeep(this.checkBeep.isSelected());
        }
        else if (actionCommand.equals("Mn.Bg")) {
            final Color showDialog = JColorChooser.showDialog(this.privateChat, this.paraConf.title(), Color.WHITE);
            if (showDialog != null) {
                this.privateChat.chatRender.textPane.setBackground(showDialog);
            }
        }
        else if (actionCommand.equals("Mn.VwVid") || actionCommand.equals("Mn.VwVid.Vid")) {
            chatModel.cmAvViewBc(this.privateChat.thirdParty);
        }
    }
    
    private String removePrefix(final String s, final String s2) {
        if (s.length() <= s2.length()) {
            return "";
        }
        return s.substring(s2.length());
    }
    
    private void actClear() {
        if (JOptionPane.showConfirmDialog(this.privateChat, this.paraConf.get("Msg.Clear", "Are you sure you want to clear all the chat text?"), " ", 0) == 0) {
            this.privateChat.chatRender.clearText();
        }
    }
    
    private void createMenu() {
        (this.menuBar = new JMenuBar()).add(this.getAction());
        this.menuBar.add(this.getOptions());
    }
    
    private JMenu getAction() {
        final JMenu menu = new JMenu(this.paraConf.get("Mn.Action", "Action"));
        (this.checkIgnore = this.createCheck("Mn.Ignore", "Ignore", menu, this.DEF_ON)).setState(this.privateChat.privateMan.paraApplet.chatModel.cmIsIgnored(this.privateChat.thirdParty));
        this.createItem("Mn.Clear", "Clear Text", menu, this.DEF_ON);
        JMenuItem menuItem;
        if (this.paraConf.isAudioOnly()) {
            menuItem = this.createItem("Mn.VwVid", "Listen To This User's Voice Chat Broadcast", menu, this.DEF_ON);
        }
        else {
            menuItem = this.createItem("Mn.VwVid.Vid", "View This User's Broadcast", menu, this.DEF_ON);
        }
        if (!this.paraConf.getBool("Ctrl.Av", false)) {
            menu.remove(menuItem);
        }
        return menu;
    }
    
    private JMenu getOptions() {
        final JMenu menu = new JMenu(this.paraConf.get("Mn.Options", "Options"));
        (this.checkBeep = this.createCheck("Mn.Beep", "Beep on New Text", menu, this.DEF_ON)).setState(true);
        menu.addSeparator();
        this.checkDouble = this.createCheck("Mn.DoubleSpace", "Double Space", menu, this.DEF_ON);
        this.checkSep = this.createCheck("Mn.Separator", "Insert Separator", menu, this.DEF_ON);
        menu.addSeparator();
        menu.add(this.getFontSize());
        menu.add(this.getFontList());
        this.createItem("Mn.Bg", "Window Background Color", menu, this.DEF_ON);
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
    
    private JMenuItem createItem(final String actionCommand, final String s, final JMenu menu, final String s2) {
        final JMenuItem menuItem = new JMenuItem(this.paraConf.get(actionCommand, s));
        menuItem.setActionCommand(actionCommand);
        menuItem.addActionListener(this);
        if (this.paraConf.getBool(s2, true)) {
            menu.add(menuItem);
        }
        return menuItem;
    }
    
    private JCheckBoxMenuItem createCheck(final String actionCommand, final String s, final JMenu menu, final String s2) {
        final JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem(this.paraConf.get(actionCommand, s));
        checkBoxMenuItem.setActionCommand(actionCommand);
        checkBoxMenuItem.addActionListener(this);
        if (this.paraConf.getBool(s2, true)) {
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
}
