// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.Image;
import pclient.shd.Config;
import java.awt.event.ActionListener;
import java.awt.Panel;

public class OptionBar extends Panel implements ActionListener
{
    private BaseChat pChat;
    private CommonInter popupWin;
    private CommonInter popupColor;
    private CommonInter popupSound;
    private ToggleButton mBoldButton;
    private ToggleButton mItalicButton;
    private IconButton mColorButton;
    private IconButton smileButton;
    private IconButton floatButton;
    private IconButton questionButton;
    private IconButton toolButton;
    private IconButton saveButton;
    private IconButton soundButton;
    private IconButton connectButton;
    private Config paraConf;
    
    public OptionBar(final BaseChat pChat) {
        this.popupWin = null;
        this.popupColor = null;
        this.popupSound = null;
        this.questionButton = null;
        this.toolButton = null;
        this.saveButton = null;
        this.soundButton = null;
        this.connectButton = null;
        this.pChat = pChat;
        this.paraConf = pChat.paraConf;
        this.popupWin = null;
        this.popupColor = null;
        this.buildGUI();
    }
    
    public void setFloat(final boolean b) {
        if (b) {
            final Image icon = this.paraConf.getIcon("closearrow16.png");
            final Image icon2 = this.paraConf.getIcon("closearrow16p.gif");
            this.floatButton.setImage(icon);
            this.floatButton.setPressed(icon2);
        }
        else {
            final Image icon3 = this.paraConf.getIcon("arrow16.png");
            final Image icon4 = this.paraConf.getIcon("arrow16p.gif");
            this.floatButton.setImage(icon3);
            this.floatButton.setPressed(icon4);
        }
        this.floatButton.drawButton();
    }
    
    public void setConnect(final boolean b) {
        if (b) {
            final Image icon = this.paraConf.getIcon("log-on.png");
            final Image icon2 = this.paraConf.getIcon("logon20p.gif");
            this.connectButton.setImage(icon);
            this.connectButton.setPressed(icon2);
        }
        else {
            final Image icon3 = this.paraConf.getIcon("log-off.png");
            final Image icon4 = this.paraConf.getIcon("logoff20p.gif");
            this.connectButton.setImage(icon3);
            this.connectButton.setPressed(icon4);
        }
        this.connectButton.drawButton();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.mBoldButton) {
            this.changeFont();
        }
        else if (actionEvent.getSource() == this.mItalicButton) {
            this.changeFont();
        }
        else if (actionEvent.getSource() == this.mColorButton) {
            this.popColors();
        }
        else if (actionEvent.getSource() == this.smileButton) {
            this.popupIcons();
        }
        else if (actionEvent.getSource() == this.floatButton) {
            this.floating();
        }
        else if (actionEvent.getSource() == this.questionButton) {
            this.pChat.paraConf.loadPage(this.pChat.paraConf.get("URL.Help", "http://www.parachat.com/help/"));
        }
        else if (actionEvent.getSource() == this.toolButton) {
            this.pChat.fireControlPanel(14, null);
        }
        else if (actionEvent.getSource() == this.saveButton) {
            this.pChat.getRenderer().saveText();
        }
        else if (actionEvent.getSource() == this.soundButton) {
            this.popSound();
        }
        else if (actionEvent.getSource() == this.connectButton) {
            if (this.pChat.isConnected()) {
                this.pChat.parentComp.exitChat();
            }
            else {
                this.pChat.parentComp.reconnect();
            }
        }
    }
    
    private void changeFont() {
        final Font font = this.pChat.getTextInput().getFont();
        final String name = font.getName();
        final int size = font.getSize();
        int n;
        if (this.mBoldButton.getState() && this.mItalicButton.getState()) {
            n = 3;
            this.pChat.parentComp.userChoice.bold = true;
            this.pChat.parentComp.userChoice.italic = true;
        }
        else if (this.mBoldButton.getState()) {
            n = 1;
            this.pChat.parentComp.userChoice.bold = true;
        }
        else if (this.mItalicButton.getState()) {
            n = 2;
            this.pChat.parentComp.userChoice.italic = true;
        }
        else {
            n = 0;
            this.pChat.parentComp.userChoice.bold = false;
            this.pChat.parentComp.userChoice.italic = false;
        }
        final Font font2 = new Font(name, n, size);
        this.pChat.getTextInput().setFont(font2);
        this.pChat.getInputx().setFont(font2);
    }
    
    private void floating() {
        this.pChat.parentComp.floatOrNot();
    }
    
    private void popupIcons() {
        this.popupWin = this.initPop("pclient.bsx.IconPop", this.popupWin);
    }
    
    private void popColors() {
        this.popupColor = this.initPop("pclient.bsx.ColorPop", this.popupColor);
    }
    
    private void popSound() {
        this.popupSound = this.initPop("pclient.bsx.SoundPop", this.popupSound);
    }
    
    private CommonInter initPop(final String s, final CommonInter commonInter) {
        if (commonInter != null) {
            commonInter.restart();
            return commonInter;
        }
        CommonInter commonInter2;
        try {
            commonInter2 = (CommonInter)Class.forName(s).newInstance();
        }
        catch (Exception ex) {
            System.out.println("Error #243.");
            return null;
        }
        commonInter2.setPara(this.pChat);
        commonInter2.restart();
        return commonInter2;
    }
    
    protected void finalize() {
        if (this.popupWin != null) {
            this.popupWin.destroy();
            this.popupWin = null;
        }
        if (this.popupColor != null) {
            this.popupColor.destroy();
            this.popupColor = null;
        }
        if (this.popupSound != null) {
            this.popupSound.destroy();
            this.popupSound = null;
        }
    }
    
    private void buildGUI() {
        this.setLayout(new FlowLayout(0, 1, 1));
        if (this.pChat.isMegaText()) {
            this.buildMega();
        }
        this.add(this.buildTools());
    }
    
    private void buildMega() {
        this.mBoldButton = new ToggleButton(this.pChat, "Bold Text");
        final Image icon = this.paraConf.getIcon("b16p.png");
        final Image icon2 = this.paraConf.getIcon("b16.png");
        this.mBoldButton.setTrueImage(icon);
        this.mBoldButton.setFalseImage(icon2);
        this.mBoldButton.setState(false);
        this.mBoldButton.addActionListener(this);
        this.mItalicButton = new ToggleButton(this.pChat, "Italic Text");
        final Image icon3 = this.paraConf.getIcon("i16p.png");
        final Image icon4 = this.paraConf.getIcon("i16.png");
        this.mItalicButton.setTrueImage(icon3);
        this.mItalicButton.setFalseImage(icon4);
        this.mItalicButton.setState(false);
        this.mItalicButton.addActionListener(this);
        this.mColorButton = this.getBtn("Text Color", "select-color.png", "color16p.gif");
    }
    
    private Panel buildTools() {
        this.smileButton = this.getBtn("Add a smiley image", "smiley.png", "smilebutton16p.gif");
        this.floatButton = this.getBtn("Float chat window", "arrow16.png", "arrow16p.gif");
        this.questionButton = this.getBtn("Help", "question16.gif", "question16p.gif");
        this.toolButton = this.getBtn("Control Panel", "tool16.gif", "tool16p.gif");
        this.saveButton = this.getBtn("Save chat text", "save16.gif", "save16p.gif");
        this.soundButton = this.getBtn("Send a sound", "sound.png", "sound20p.gif");
        this.connectButton = this.getBtn("Log in/log off", "log-on.png", "logon20p.gif");
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(0, 1, 1));
        boolean b = !this.pChat.isMegaText();
        if (!this.pChat.paraConf.getBool("Add.Bold", true)) {
            b = true;
        }
        if (!b) {
            panel.add(this.mBoldButton);
        }
        boolean b2 = !this.pChat.isMegaText();
        if (!this.pChat.paraConf.getBool("Add.Italic", true)) {
            b2 = true;
        }
        if (!b2) {
            panel.add(this.mItalicButton);
        }
        if (this.pChat.paraConf.getBool("Add.Color", true) && this.pChat.isMegaText()) {
            panel.add(this.mColorButton);
        }
        if (this.pChat.paraConf.getBool("Add.Smiley", true) && this.pChat.isMegaText()) {
            panel.add(this.smileButton);
        }
        panel.add(this.soundButton);
        final boolean b3 = !this.pChat.paraConf.getBool("Add.Float", true);
        if (!this.pChat.parentComp.isPopMode() && !b3) {
            panel.add(this.floatButton);
        }
        panel.add(this.toolButton);
        if (this.pChat.isMegaText() && this.pChat.paraConf.getBool("Op.Copy", true)) {
            panel.add(this.saveButton);
        }
        panel.add(this.questionButton);
        if (this.pChat.paraConf.getBool("Add.Logout", true)) {
            panel.add(this.connectButton);
        }
        return panel;
    }
    
    private IconButton getBtn(final String s, final String s2, final String s3) {
        final IconButton iconButton = new IconButton(this.pChat, s);
        final Image icon = this.paraConf.getIcon(s2);
        final Image icon2 = this.paraConf.getIcon(s3);
        iconButton.setImage(icon);
        iconButton.setPressed(icon2);
        iconButton.addActionListener(this);
        return iconButton;
    }
}
