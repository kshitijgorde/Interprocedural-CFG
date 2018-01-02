import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.WindowEvent;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowListener;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.beans.PropertyVetoException;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Button;
import java.awt.Choice;
import symantec.itools.awt.WrappingLabel;
import java.awt.TextArea;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Checkbox;
import symantec.itools.awt.BorderPanel;
import symantec.itools.awt.TabPanel;
import java.awt.Panel;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class PropsFrame extends Frame
{
    boolean fComponentsAdjusted;
    Panel tabPnl;
    TabPanel tabPanel1;
    Panel optionPnl;
    BorderPanel borderPanel2;
    Panel panel9;
    Panel panel7;
    Panel panel12;
    Checkbox expireChk;
    TextField expireTxt;
    Label label6;
    Checkbox checkOnLoadChk;
    Panel panel13;
    Checkbox checkChk;
    TextField checkTxt;
    Label label13;
    Checkbox htmlChk;
    Label label14;
    Panel panel6;
    Label label1;
    TextField replyToTxt;
    Label label15;
    TextArea signatureTxt;
    Panel appearPnl;
    BorderPanel borderPanel1;
    Panel panel1;
    TextField msgPreviewTxt;
    WrappingLabel previewLbl;
    Panel panel2;
    Panel panel10;
    BorderPanel fontPnl;
    Panel panel3;
    Panel panel4;
    Choice fontChc;
    Choice sizeChc;
    Choice styleChc;
    Choice colorChc;
    Choice backgroundChc;
    Panel panel5;
    Label label2;
    Label label3;
    Label label4;
    Label label7;
    Label label8;
    Label label11;
    Panel panel11;
    BorderPanel borderPanel4;
    Panel panel14;
    Panel panel15;
    Choice msgFontChc;
    Choice msgSizeChc;
    Choice msgStyleChc;
    Panel panel16;
    Label label5;
    Label label9;
    Label label10;
    Label label12;
    Panel toolbarPnl;
    BorderPanel borderPanel3;
    Panel panel8;
    Panel panel17;
    Checkbox toolbarChk;
    Panel panel18;
    Label placementLbl;
    Choice placementChc;
    Panel panel19;
    Label bsizeLbl;
    Choice bsizeChc;
    Panel cachePnl;
    BorderPanel borderPanel5;
    Panel panel21;
    Panel panel22;
    Label cacheLbl;
    Panel panel23;
    Checkbox newChk;
    Checkbox unreadChk;
    Checkbox readChk;
    Checkbox recoveredChk;
    Checkbox unmarkedChk;
    Panel buttonPnl;
    Button okBtn;
    Button cancelBtn;
    Button defaultsBtn;
    Button helpBtn;
    Font font;
    JMProps props;
    
    public PropsFrame(final JMProps props) {
        this.fComponentsAdjusted = false;
        this.props = props;
        this.setBackground(this.props.background);
        this.setForeground(this.props.foreground);
        this.setFont(this.props.font);
        this.props.addWindow(this);
        this.setLayout(new BorderLayout(0, 0));
        this.setVisible(false);
        this.setSize(this.insets().left + this.insets().right + 416, this.insets().top + this.insets().bottom + 395);
        (this.tabPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.tabPnl.setBounds(this.insets().left, this.insets().top, 416, 361);
        this.add("Center", this.tabPnl);
        this.tabPanel1 = new TabPanel();
        try {
            this.tabPanel1.setPanelLabels(new String[] { this.props.language.getProperty("tabs.options", "Options"), this.props.language.getProperty("tabs.appearance", "Appearance"), this.props.language.getProperty("tabs.toolbars", "Toolbars"), this.props.language.getProperty("tabs.disconnect", "Disconnect") });
        }
        catch (PropertyVetoException ex) {}
        try {
            this.tabPanel1.setCurrentPanelNdx(3);
        }
        catch (PropertyVetoException ex2) {}
        ((Component)this.tabPanel1).setBounds(0, 0, 416, 361);
        this.tabPnl.add("Center", (Component)this.tabPanel1);
        (this.optionPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.optionPnl.setVisible(false);
        this.optionPnl.setBounds(0, 0, 392, 332);
        this.tabPanel1.add((Component)this.optionPnl);
        this.borderPanel2 = new BorderPanel();
        try {
            this.borderPanel2.setBevelStyle(3);
        }
        catch (PropertyVetoException ex3) {}
        this.borderPanel2.setLayout((LayoutManager)new BorderLayout(0, 0));
        this.borderPanel2.setBounds(0, 0, 392, 332);
        this.optionPnl.add("Center", (Component)this.borderPanel2);
        (this.panel9 = new Panel()).setLayout(new BorderLayout(0, 0));
        this.panel9.setBounds(0, 0, 371, 0);
        ((Container)this.borderPanel2).add("North", this.panel9);
        (this.panel7 = new Panel()).setLayout(new GridLayout(7, 1, 0, 0));
        this.panel7.setBounds(0, 0, 371, 10);
        this.panel9.add("North", this.panel7);
        (this.panel12 = new Panel()).setLayout(new FlowLayout(0, 0, 0));
        this.panel12.setBounds(0, 0, 20, 40);
        this.panel7.add(this.panel12);
        final String property = this.props.language.getProperty("labels.delete_read", "Delete read messages older than _X_ days.");
        final int index = property.indexOf("_X_");
        final String substring = property.substring(0, index);
        final String substring2 = property.substring(index + 3);
        (this.expireChk = new Checkbox(substring)).setBounds(0, 0, 100, 40);
        this.panel12.add(this.expireChk);
        (this.expireTxt = new TextField(3)).setText("7");
        this.expireTxt.setBounds(0, 0, 100, 40);
        this.panel12.add(this.expireTxt);
        (this.label6 = new Label(substring2)).setBounds(0, 0, 100, 40);
        this.panel12.add(this.label6);
        (this.checkOnLoadChk = new Checkbox(this.props.language.getProperty("labels.check_new", "Check for new mail when opening mailbox."))).setBounds(0, 0, 100, 40);
        this.panel7.add(this.checkOnLoadChk);
        (this.panel13 = new Panel()).setLayout(new FlowLayout(0, 0, 0));
        this.panel13.setBounds(0, 0, 20, 40);
        this.panel7.add(this.panel13);
        final String property2 = this.props.language.getProperty("labels.check_interval", "Check for mail every _X_ minutes.");
        final int index2 = property2.indexOf("_X_");
        final String substring3 = property2.substring(0, index2);
        final String substring4 = property2.substring(index2 + 3);
        (this.checkChk = new Checkbox(substring3)).setBounds(0, 0, 100, 40);
        this.panel13.add(this.checkChk);
        (this.checkTxt = new TextField(3)).setText("15");
        this.checkTxt.setBounds(0, 0, 100, 40);
        this.panel13.add(this.checkTxt);
        (this.label13 = new Label(substring4)).setBounds(0, 0, 100, 40);
        this.panel13.add(this.label13);
        (this.htmlChk = new Checkbox(this.props.language.getProperty("labels.display_html", "Display HTML messages as hypertext"))).setBounds(0, 0, 100, 40);
        this.panel7.add(this.htmlChk);
        (this.label14 = new Label(" ")).setBounds(0, 0, 100, 40);
        this.panel7.add(this.label14);
        (this.panel6 = new Panel()).setLayout(new BorderLayout(0, 0));
        this.panel6.setBounds(0, 0, 371, 24);
        this.panel7.add(this.panel6);
        (this.label1 = new Label(this.props.language.getProperty("labels.reply_to_address", "Reply-To address:"))).setBounds(0, 0, 123, 24);
        this.panel6.add("West", this.label1);
        (this.replyToTxt = new TextField(30)).setBounds(123, 0, 248, 24);
        this.panel6.add("Center", this.replyToTxt);
        (this.label15 = new Label(this.props.language.getProperty("labels.signature", "Signature:"))).setBounds(0, 0, 100, 40);
        this.panel7.add(this.label15);
        (this.signatureTxt = new TextArea()).setBounds(0, 0, 100, 40);
        ((Container)this.borderPanel2).add("Center", this.signatureTxt);
        (this.appearPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.appearPnl.setVisible(false);
        this.appearPnl.setBounds(12, 33, 392, 332);
        this.tabPanel1.add((Component)this.appearPnl);
        this.borderPanel1 = new BorderPanel();
        try {
            this.borderPanel1.setBevelStyle(0);
        }
        catch (PropertyVetoException ex4) {}
        try {
            this.borderPanel1.setIPadBottom(2);
        }
        catch (PropertyVetoException ex5) {}
        try {
            this.borderPanel1.setIPadSides(2);
        }
        catch (PropertyVetoException ex6) {}
        try {
            this.borderPanel1.setPaddingTop(6);
        }
        catch (PropertyVetoException ex7) {}
        this.borderPanel1.setLayout((LayoutManager)new BorderLayout(0, 0));
        this.borderPanel1.setBounds(0, 257, 392, 100);
        this.appearPnl.add("South", (Component)this.borderPanel1);
        (this.panel1 = new Panel()).setLayout(new BorderLayout(0, 0));
        this.panel1.setBounds(0, 0, 375, 58);
        ((Container)this.borderPanel1).add("Center", this.panel1);
        (this.msgPreviewTxt = new TextField()).setText(this.props.language.getProperty("labels.message_preview", "This is a sample of message text font."));
        this.msgPreviewTxt.setBounds(0, 0, 100, 40);
        this.msgPreviewTxt.setForeground(new Color(0));
        this.msgPreviewTxt.setBackground(new Color(16777215));
        this.panel1.add("South", this.msgPreviewTxt);
        this.previewLbl = new WrappingLabel();
        try {
            this.previewLbl.setText(this.props.language.getProperty("labels.general_preview", "This is a preview of your general font/color settings."));
        }
        catch (PropertyVetoException ex8) {}
        try {
            this.previewLbl.setAlignStyle(1);
        }
        catch (PropertyVetoException ex9) {}
        ((Component)this.previewLbl).setBounds(0, 0, 375, 58);
        this.panel1.add("Center", (Component)this.previewLbl);
        (this.panel2 = new Panel()).setLayout(new GridLayout(1, 2, 0, 0));
        this.panel2.setBounds(0, 0, 392, 257);
        this.appearPnl.add("Center", this.panel2);
        (this.panel10 = new Panel()).setLayout(new BorderLayout(0, 0));
        this.panel10.setBounds(0, 0, 20, 40);
        this.panel2.add(this.panel10);
        this.fontPnl = new BorderPanel();
        try {
            this.fontPnl.setBevelStyle(0);
        }
        catch (PropertyVetoException ex10) {}
        try {
            this.fontPnl.setIPadSides(6);
        }
        catch (PropertyVetoException ex11) {}
        try {
            this.fontPnl.setPaddingTop(0);
        }
        catch (PropertyVetoException ex12) {}
        try {
            this.fontPnl.setIPadTop(10);
        }
        catch (PropertyVetoException ex13) {}
        this.fontPnl.setLayout((LayoutManager)new BorderLayout(0, 0));
        this.fontPnl.setBounds(0, 0, 392, 257);
        this.panel10.add("Center", (Component)this.fontPnl);
        (this.panel3 = new Panel()).setLayout(new BorderLayout(0, 0));
        this.panel3.setBounds(0, 0, 367, 120);
        ((Container)this.fontPnl).add("North", this.panel3);
        (this.panel4 = new Panel()).setLayout(new GridLayout(5, 1, 0, 0));
        this.panel4.setBounds(86, 0, 281, 120);
        this.panel3.add("Center", this.panel4);
        (this.fontChc = new Choice()).addItem("Courier");
        this.fontChc.addItem("Dialog");
        this.fontChc.addItem("TimesRoman");
        this.fontChc.addItem("Helvetica");
        this.fontChc.setBounds(0, 0, 281, 24);
        this.panel4.add(this.fontChc);
        (this.sizeChc = new Choice()).addItem("6");
        this.sizeChc.addItem("8");
        this.sizeChc.addItem("10");
        this.sizeChc.addItem("12");
        this.sizeChc.addItem("14");
        this.sizeChc.addItem("16");
        this.sizeChc.addItem("18");
        this.sizeChc.addItem("20");
        this.sizeChc.addItem("24");
        this.sizeChc.addItem("30");
        this.sizeChc.addItem("36");
        this.sizeChc.setBounds(0, 24, 281, 24);
        this.panel4.add(this.sizeChc);
        (this.styleChc = new Choice()).addItem(this.props.language.getProperty("style.plain", "Plain"));
        this.styleChc.addItem(this.props.language.getProperty("style.bold", "Bold"));
        this.styleChc.addItem(this.props.language.getProperty("style.italic", "Italic"));
        this.styleChc.addItem(this.props.language.getProperty("style.bold_italic", "Bold+Italic"));
        this.styleChc.setBounds(0, 48, 281, 24);
        this.panel4.add(this.styleChc);
        (this.colorChc = new Choice()).addItem(this.props.language.getProperty("color.black", "Black"));
        this.colorChc.addItem(this.props.language.getProperty("color.blue", "Blue"));
        this.colorChc.addItem(this.props.language.getProperty("color.cyan", "Cyan"));
        this.colorChc.addItem(this.props.language.getProperty("color.darkgray", "Dark gray"));
        this.colorChc.addItem(this.props.language.getProperty("color.gray", "Gray"));
        this.colorChc.addItem(this.props.language.getProperty("color.green", "Green"));
        this.colorChc.addItem(this.props.language.getProperty("color.lightgray", "Light gray"));
        this.colorChc.addItem(this.props.language.getProperty("color.magenta", "Magenta"));
        this.colorChc.addItem(this.props.language.getProperty("color.orange", "Orange"));
        this.colorChc.addItem(this.props.language.getProperty("color.pink", "Pink"));
        this.colorChc.addItem(this.props.language.getProperty("color.red", "Red"));
        this.colorChc.addItem(this.props.language.getProperty("color.white", "White"));
        this.colorChc.addItem(this.props.language.getProperty("color.yellow", "Yellow"));
        this.colorChc.setBounds(0, 72, 281, 24);
        this.panel4.add(this.colorChc);
        (this.backgroundChc = new Choice()).addItem(this.props.language.getProperty("color.black", "Black"));
        this.backgroundChc.addItem(this.props.language.getProperty("color.blue", "Blue"));
        this.backgroundChc.addItem(this.props.language.getProperty("color.cyan", "Cyan"));
        this.backgroundChc.addItem(this.props.language.getProperty("color.darkgray", "Dark gray"));
        this.backgroundChc.addItem(this.props.language.getProperty("color.gray", "Gray"));
        this.backgroundChc.addItem(this.props.language.getProperty("color.green", "Green"));
        this.backgroundChc.addItem(this.props.language.getProperty("color.lightgray", "Light gray"));
        this.backgroundChc.addItem(this.props.language.getProperty("color.magenta", "Magenta"));
        this.backgroundChc.addItem(this.props.language.getProperty("color.orange", "Orange"));
        this.backgroundChc.addItem(this.props.language.getProperty("color.pink", "Pink"));
        this.backgroundChc.addItem(this.props.language.getProperty("color.red", "Red"));
        this.backgroundChc.addItem(this.props.language.getProperty("color.white", "White"));
        this.backgroundChc.addItem(this.props.language.getProperty("color.yellow", "Yellow"));
        this.backgroundChc.setBounds(0, 96, 281, 24);
        this.panel4.add(this.backgroundChc);
        (this.panel5 = new Panel()).setLayout(new GridLayout(5, 1, 0, 0));
        this.panel5.setBounds(0, 0, 86, 120);
        this.panel3.add("West", this.panel5);
        (this.label2 = new Label(this.props.language.getProperty("labels.font", "Font:"))).setBounds(0, 0, 86, 24);
        this.panel5.add(this.label2);
        (this.label3 = new Label(this.props.language.getProperty("labels.size", "Size:"))).setBounds(0, 24, 86, 24);
        this.panel5.add(this.label3);
        (this.label4 = new Label(this.props.language.getProperty("labels.style", "Style:"))).setBounds(0, 48, 86, 24);
        this.panel5.add(this.label4);
        (this.label7 = new Label(this.props.language.getProperty("labels.color", "Color:"))).setBounds(0, 72, 86, 24);
        this.panel5.add(this.label7);
        (this.label8 = new Label(this.props.language.getProperty("labels.background", "Back:"))).setBounds(0, 96, 86, 24);
        this.panel5.add(this.label8);
        (this.label11 = new Label(this.props.language.getProperty("labels.general", " General:"))).setBounds(0, 0, 100, 40);
        this.panel10.add("North", this.label11);
        (this.panel11 = new Panel()).setLayout(new BorderLayout(0, 0));
        this.panel11.setBounds(0, 0, 20, 40);
        this.panel2.add(this.panel11);
        this.borderPanel4 = new BorderPanel();
        try {
            this.borderPanel4.setBevelStyle(0);
        }
        catch (PropertyVetoException ex14) {}
        try {
            this.borderPanel4.setIPadSides(6);
        }
        catch (PropertyVetoException ex15) {}
        try {
            this.borderPanel4.setPaddingTop(0);
        }
        catch (PropertyVetoException ex16) {}
        try {
            this.borderPanel4.setIPadTop(10);
        }
        catch (PropertyVetoException ex17) {}
        this.borderPanel4.setLayout((LayoutManager)new BorderLayout(0, 0));
        this.borderPanel4.setBounds(0, 0, 392, 257);
        this.panel11.add("Center", (Component)this.borderPanel4);
        (this.panel14 = new Panel()).setLayout(new BorderLayout(0, 0));
        this.panel14.setBounds(0, 0, 367, 72);
        ((Container)this.borderPanel4).add("North", this.panel14);
        (this.panel15 = new Panel()).setLayout(new GridLayout(3, 1, 0, 0));
        this.panel15.setBounds(48, 0, 319, 72);
        this.panel14.add("Center", this.panel15);
        (this.msgFontChc = new Choice()).addItem("Courier");
        this.msgFontChc.addItem("Dialog");
        this.msgFontChc.addItem("TimesRoman");
        this.msgFontChc.addItem("Helvetica");
        this.msgFontChc.setBounds(0, 0, 319, 24);
        this.panel15.add(this.msgFontChc);
        (this.msgSizeChc = new Choice()).addItem("6");
        this.msgSizeChc.addItem("8");
        this.msgSizeChc.addItem("10");
        this.msgSizeChc.addItem("12");
        this.msgSizeChc.addItem("14");
        this.msgSizeChc.addItem("16");
        this.msgSizeChc.addItem("18");
        this.msgSizeChc.addItem("20");
        this.msgSizeChc.addItem("24");
        this.msgSizeChc.addItem("30");
        this.msgSizeChc.addItem("36");
        this.msgSizeChc.setBounds(0, 24, 319, 24);
        this.panel15.add(this.msgSizeChc);
        (this.msgStyleChc = new Choice()).addItem(this.props.language.getProperty("style.plain", "Plain"));
        this.msgStyleChc.addItem(this.props.language.getProperty("style.bold", "Bold"));
        this.msgStyleChc.addItem(this.props.language.getProperty("style.italic", "Italic"));
        this.msgStyleChc.addItem(this.props.language.getProperty("style.bold_italic", "Bold+Italic"));
        this.msgStyleChc.setBounds(0, 48, 319, 24);
        this.panel15.add(this.msgStyleChc);
        (this.panel16 = new Panel()).setLayout(new GridLayout(3, 1, 0, 0));
        this.panel16.setBounds(0, 0, 48, 72);
        this.panel14.add("West", this.panel16);
        (this.label5 = new Label(this.props.language.getProperty("labels.font", "Font:"))).setBounds(0, 0, 48, 24);
        this.panel16.add(this.label5);
        (this.label9 = new Label(this.props.language.getProperty("labels.size", "Size:"))).setBounds(0, 24, 48, 24);
        this.panel16.add(this.label9);
        (this.label10 = new Label(this.props.language.getProperty("labels.style", "Style:"))).setBounds(0, 48, 48, 24);
        this.panel16.add(this.label10);
        (this.label12 = new Label(this.props.language.getProperty("labels.message_text", " Message text:"))).setBounds(0, 0, 100, 40);
        this.panel11.add("North", this.label12);
        (this.toolbarPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.toolbarPnl.setVisible(false);
        this.toolbarPnl.setBounds(12, 33, 392, 317);
        this.tabPanel1.add((Component)this.toolbarPnl);
        this.borderPanel3 = new BorderPanel();
        try {
            this.borderPanel3.setBevelStyle(3);
        }
        catch (PropertyVetoException ex18) {}
        this.borderPanel3.setLayout((LayoutManager)new BorderLayout(0, 10));
        this.borderPanel3.setBounds(0, 0, 20, 40);
        this.toolbarPnl.add("Center", (Component)this.borderPanel3);
        (this.panel8 = new Panel()).setLayout(new BorderLayout(0, 0));
        this.panel8.setBounds(0, 0, 20, 40);
        ((Container)this.borderPanel3).add("Center", this.panel8);
        (this.panel17 = new Panel()).setLayout(new GridLayout(6, 1, 0, 5));
        this.panel17.setBounds(0, 0, 371, 10);
        this.panel8.add("North", this.panel17);
        (this.toolbarChk = new Checkbox(this.props.language.getProperty("labels.show_toolbars", "Show toolbars on windows"))).setBounds(0, 0, 100, 40);
        this.panel17.add(this.toolbarChk);
        this.toolbarChk.setState(true);
        (this.panel18 = new Panel()).setLayout(new FlowLayout(0, 0, 0));
        this.panel18.setBounds(0, 0, 20, 40);
        this.panel17.add(this.panel18);
        (this.placementLbl = new Label(this.props.language.getProperty("labels.toolbar_placement", "     Toolbar placement:"))).setBounds(0, 0, 100, 40);
        this.panel18.add(this.placementLbl);
        (this.placementChc = new Choice()).addItem(this.props.language.getProperty("placement.top", "Top"));
        this.placementChc.addItem(this.props.language.getProperty("placement.right", "Right"));
        this.placementChc.addItem(this.props.language.getProperty("placement.bottom", "Bottom"));
        this.placementChc.addItem(this.props.language.getProperty("placement.left", "Left"));
        this.placementChc.setBounds(0, 0, 100, 40);
        this.panel18.add(this.placementChc);
        (this.panel19 = new Panel()).setLayout(new FlowLayout(0, 0, 0));
        this.panel19.setBounds(0, 0, 20, 40);
        this.panel17.add(this.panel19);
        (this.bsizeLbl = new Label(this.props.language.getProperty("labels.button_size", "     Button size:"))).setBounds(0, 0, 100, 40);
        this.panel19.add(this.bsizeLbl);
        (this.bsizeChc = new Choice()).addItem(this.props.language.getProperty("size.small", "Small"));
        this.bsizeChc.addItem(this.props.language.getProperty("size.medium", "Medium"));
        this.bsizeChc.addItem(this.props.language.getProperty("size.large", "Large"));
        try {
            this.bsizeChc.select(1);
        }
        catch (IllegalArgumentException ex19) {}
        this.bsizeChc.setBounds(0, 0, 100, 40);
        this.panel19.add(this.bsizeChc);
        (this.cachePnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.cachePnl.setBounds(0, 0, 392, 317);
        this.tabPanel1.add((Component)this.cachePnl);
        this.borderPanel5 = new BorderPanel();
        try {
            this.borderPanel5.setBevelStyle(3);
        }
        catch (PropertyVetoException ex20) {}
        this.borderPanel5.setLayout((LayoutManager)new BorderLayout(0, 10));
        this.borderPanel5.setBounds(0, 0, 20, 40);
        this.cachePnl.add("Center", (Component)this.borderPanel5);
        (this.panel21 = new Panel()).setLayout(new BorderLayout(0, 0));
        this.panel21.setBounds(0, 0, 20, 40);
        ((Container)this.borderPanel5).add("Center", this.panel21);
        (this.panel22 = new Panel()).setLayout(new BorderLayout(0, 0));
        this.panel22.setBounds(0, 0, 371, 10);
        this.panel21.add("North", this.panel22);
        (this.cacheLbl = new Label(this.props.language.getProperty("labels.on_disconnect", "On Disconnect, cache messages marked as:"))).setBounds(0, 0, 100, 40);
        this.panel22.add("North", this.cacheLbl);
        (this.panel23 = new Panel()).setLayout(new GridLayout(3, 2, 0, 0));
        this.panel23.setBounds(0, 0, 20, 40);
        this.panel22.add("South", this.panel23);
        (this.newChk = new Checkbox(this.props.language.getProperty("status.new", "NEW!"))).setBounds(0, 0, 100, 40);
        this.panel23.add(this.newChk);
        this.newChk.setState(true);
        (this.unreadChk = new Checkbox(this.props.language.getProperty("status.unread", "Unread"))).setBounds(0, 0, 100, 40);
        this.panel23.add(this.unreadChk);
        this.unreadChk.setState(true);
        (this.readChk = new Checkbox(this.props.language.getProperty("status.read", "Read"))).setBounds(0, 0, 100, 40);
        this.panel23.add(this.readChk);
        this.readChk.setState(true);
        (this.recoveredChk = new Checkbox(this.props.language.getProperty("status.recovered", "Recovered"))).setBounds(0, 0, 100, 40);
        this.panel23.add(this.recoveredChk);
        this.recoveredChk.setState(true);
        (this.unmarkedChk = new Checkbox(this.props.language.getProperty("status.unmarked", "(unmarked)"))).setBounds(0, 0, 100, 40);
        this.panel23.add(this.unmarkedChk);
        this.unmarkedChk.setState(true);
        (this.buttonPnl = new Panel()).setLayout(new FlowLayout(2, 5, 5));
        this.buttonPnl.setBounds(this.insets().left, this.insets().top + 361, 416, 34);
        this.add("South", this.buttonPnl);
        (this.okBtn = new Button()).setActionCommand("button");
        this.okBtn.setLabel(this.props.language.getProperty("button.save", "Save"));
        this.okBtn.setBounds(245, 5, 55, 24);
        this.buttonPnl.add(this.okBtn);
        (this.defaultsBtn = new Button()).setActionCommand("button");
        this.defaultsBtn.setLabel(this.props.language.getProperty("button.defaults", "Defaults"));
        this.defaultsBtn.setBounds(305, 5, 52, 24);
        this.buttonPnl.add(this.defaultsBtn);
        (this.cancelBtn = new Button()).setActionCommand("button");
        this.cancelBtn.setLabel(this.props.language.getProperty("button.cancel", "Cancel"));
        this.cancelBtn.setBounds(305, 5, 52, 24);
        this.buttonPnl.add(this.cancelBtn);
        (this.helpBtn = new Button()).setActionCommand("button");
        this.helpBtn.setLabel(this.props.language.getProperty("button.help", " Help "));
        this.helpBtn.setBounds(362, 5, 49, 24);
        this.buttonPnl.add(this.helpBtn);
        this.setTitle(this.props.language.getProperty("title.preferences", "MochaMail Preferences"));
        this.addWindowListener(new SymWindow());
        final SymItem symItem = new SymItem();
        this.fontChc.addItemListener(symItem);
        this.sizeChc.addItemListener(symItem);
        this.styleChc.addItemListener(symItem);
        this.colorChc.addItemListener(symItem);
        this.backgroundChc.addItemListener(symItem);
        final SymAction symAction = new SymAction();
        this.cancelBtn.addActionListener(symAction);
        this.defaultsBtn.addActionListener(symAction);
        this.okBtn.addActionListener(symAction);
        this.msgSizeChc.addItemListener(symItem);
        this.msgStyleChc.addItemListener(symItem);
        this.msgFontChc.addItemListener(symItem);
        this.expireChk.addItemListener(symItem);
        this.checkChk.addItemListener(symItem);
        this.toolbarChk.addItemListener(symItem);
        this.helpBtn.addActionListener(symAction);
        try {
            this.tabPanel1.setCurrentPanelNdx(0);
        }
        catch (PropertyVetoException ex21) {}
        this.setValues();
        this.move(50, 50);
    }
    
    public synchronized void show() {
        this.setBackground(this.props.background);
        this.setForeground(this.props.foreground);
        this.setFont(this.props.font);
        this.bsizeChc.setVisible(this.props.connected);
        this.bsizeLbl.setVisible(this.props.connected);
        super.show();
    }
    
    public void addNotify() {
        final Dimension size = this.getSize();
        super.addNotify();
        if (this.fComponentsAdjusted) {
            return;
        }
        this.setSize(this.insets().left + this.insets().right + size.width, this.insets().top + this.insets().bottom + size.height);
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Point location = components[i].getLocation();
            location.translate(this.insets().left, this.insets().top);
            components[i].setLocation(location);
        }
        this.fComponentsAdjusted = true;
    }
    
    void Frame1_WindowClosing(final WindowEvent windowEvent) {
        this.hide();
    }
    
    void fontChc_ItemStateChanged(final ItemEvent itemEvent) {
        final String selectedItem = this.fontChc.getSelectedItem();
        final Font font = ((Component)this.previewLbl).getFont();
        font.getName();
        this.font = new Font(selectedItem, font.getStyle(), new Integer(font.getSize()));
        ((Component)this.previewLbl).setFont(this.font);
    }
    
    void sizeChc_ItemStateChanged(final ItemEvent itemEvent) {
        final int intValue = new Integer(this.sizeChc.getSelectedItem());
        final Font font = ((Component)this.previewLbl).getFont();
        final String name = font.getName();
        new Integer(font.getSize());
        this.font = new Font(name, font.getStyle(), intValue);
        ((Component)this.previewLbl).setFont(this.font);
    }
    
    void styleChc_ItemStateChanged(final ItemEvent itemEvent) {
        final int selectedIndex = this.styleChc.getSelectedIndex();
        int n = 0;
        if (selectedIndex == 0) {
            n = 0;
        }
        else if (selectedIndex == 1) {
            n = 1;
        }
        else if (selectedIndex == 2) {
            n = 2;
        }
        else if (selectedIndex == 3) {
            n = 3;
        }
        final Font font = ((Component)this.previewLbl).getFont();
        final String name = font.getName();
        final int intValue = new Integer(font.getSize());
        font.getStyle();
        this.font = new Font(name, n, intValue);
        ((Component)this.previewLbl).setFont(this.font);
    }
    
    void colorChc_ItemStateChanged(final ItemEvent itemEvent) {
        final int selectedIndex = this.colorChc.getSelectedIndex();
        Color foreground;
        if (selectedIndex == 0) {
            foreground = Color.black;
        }
        else if (selectedIndex == 1) {
            foreground = Color.blue;
        }
        else if (selectedIndex == 2) {
            foreground = Color.cyan;
        }
        else if (selectedIndex == 3) {
            foreground = Color.darkGray;
        }
        else if (selectedIndex == 4) {
            foreground = Color.gray;
        }
        else if (selectedIndex == 5) {
            foreground = Color.green;
        }
        else if (selectedIndex == 6) {
            foreground = Color.lightGray;
        }
        else if (selectedIndex == 7) {
            foreground = Color.magenta;
        }
        else if (selectedIndex == 8) {
            foreground = Color.orange;
        }
        else if (selectedIndex == 9) {
            foreground = Color.pink;
        }
        else if (selectedIndex == 10) {
            foreground = Color.red;
        }
        else if (selectedIndex == 11) {
            foreground = Color.white;
        }
        else if (selectedIndex == 12) {
            foreground = Color.yellow;
        }
        else {
            foreground = Color.black;
        }
        ((Component)this.previewLbl).setForeground(foreground);
    }
    
    void backgroundChc_ItemStateChanged(final ItemEvent itemEvent) {
        final int selectedIndex = this.backgroundChc.getSelectedIndex();
        Color background;
        if (selectedIndex == 0) {
            background = Color.black;
        }
        else if (selectedIndex == 1) {
            background = Color.blue;
        }
        else if (selectedIndex == 2) {
            background = Color.cyan;
        }
        else if (selectedIndex == 3) {
            background = Color.darkGray;
        }
        else if (selectedIndex == 4) {
            background = Color.gray;
        }
        else if (selectedIndex == 5) {
            background = Color.green;
        }
        else if (selectedIndex == 6) {
            background = Color.lightGray;
        }
        else if (selectedIndex == 7) {
            background = Color.magenta;
        }
        else if (selectedIndex == 8) {
            background = Color.orange;
        }
        else if (selectedIndex == 9) {
            background = Color.pink;
        }
        else if (selectedIndex == 10) {
            background = Color.red;
        }
        else if (selectedIndex == 11) {
            background = Color.white;
        }
        else if (selectedIndex == 12) {
            background = Color.yellow;
        }
        else {
            background = Color.lightGray;
        }
        ((Component)this.previewLbl).setBackground(background);
    }
    
    void cancelBtn_Action(final ActionEvent actionEvent) {
        this.setValues();
        this.hide();
    }
    
    void defaultsBtn_Action(final ActionEvent actionEvent) {
        this.fontChc.select(1);
        this.msgFontChc.select(0);
        this.sizeChc.select("12");
        this.msgSizeChc.select("12");
        this.styleChc.select(0);
        this.msgStyleChc.select(0);
        this.colorChc.select(0);
        this.backgroundChc.select(6);
        this.toolbarChk.setState(true);
        this.bsizeChc.select(1);
        this.placementChc.select(0);
        this.panel18.setEnabled(true);
        this.panel19.setEnabled(true);
        ((Component)this.previewLbl).setForeground(Color.black);
        ((Component)this.previewLbl).setBackground(Color.lightGray);
        ((Component)this.previewLbl).setFont(new Font("Dialog", 0, 12));
        this.msgPreviewTxt.setFont(new Font("Courier", 0, 12));
        this.newChk.setState(true);
        this.unreadChk.setState(true);
        this.readChk.setState(false);
        this.recoveredChk.setState(false);
        this.unmarkedChk.setState(false);
        this.expireChk.setState(false);
        this.expireTxt.setEnabled(true);
        this.expireTxt.setText("7");
        this.checkOnLoadChk.setState(true);
        this.checkChk.setState(true);
        this.checkTxt.setEnabled(true);
        this.checkTxt.setText("10");
        this.htmlChk.setState(true);
    }
    
    void okBtn_Action(final ActionEvent actionEvent) {
        this.setCursor(Cursor.getPredefinedCursor(3));
        if (((Component)this.previewLbl).getFont() != null) {
            this.props.font = ((Component)this.previewLbl).getFont();
        }
        if (this.msgPreviewTxt.getFont() != null) {
            this.props.messageFont = this.msgPreviewTxt.getFont();
        }
        if (((Component)this.previewLbl).getBackground() != null) {
            this.props.background = ((Component)this.previewLbl).getBackground();
        }
        if (((Component)this.previewLbl).getForeground() != null) {
            this.props.foreground = ((Component)this.previewLbl).getForeground();
        }
        this.props.replyTo = this.replyToTxt.getText();
        this.props.useInterval = this.checkChk.getState();
        this.props.showHtml = this.htmlChk.getState();
        this.props.checkOnLoad = this.checkOnLoadChk.getState();
        this.props.toolbars = this.toolbarChk.getState();
        this.props.useAging = this.expireChk.getState();
        this.props.signature = this.signatureTxt.getText();
        this.props.cacheNew = this.newChk.getState();
        this.props.cacheUnread = this.unreadChk.getState();
        this.props.cacheRead = this.readChk.getState();
        this.props.cacheRecovered = this.recoveredChk.getState();
        this.props.cacheUnmarked = this.unmarkedChk.getState();
        if (this.bsizeChc.getSelectedIndex() == 0) {
            this.props.buttonSize = 28;
        }
        else if (this.bsizeChc.getSelectedIndex() == 2) {
            this.props.buttonSize = 78;
        }
        else {
            this.props.buttonSize = 48;
        }
        if (this.placementChc.getSelectedIndex() == 0) {
            this.props.toolbarSide = "North";
        }
        else if (this.placementChc.getSelectedIndex() == 1) {
            this.props.toolbarSide = "East";
        }
        else if (this.placementChc.getSelectedIndex() == 2) {
            this.props.toolbarSide = "South";
        }
        else if (this.placementChc.getSelectedIndex() == 3) {
            this.props.toolbarSide = "West";
        }
        try {
            this.props.aging = new Integer(this.expireTxt.getText());
        }
        catch (Exception ex2) {
            this.props.aging = 7;
            this.expireTxt.setText("7");
        }
        try {
            this.props.interval = new Integer(this.checkTxt.getText());
        }
        catch (Exception ex3) {
            this.props.aging = 10;
            this.checkTxt.setText("10");
        }
        if (this.props.connected) {
            try {
                this.props.saveProperties();
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
        this.props.updateProps();
        this.setCursor(Cursor.getPredefinedCursor(0));
        this.hide();
    }
    
    void setValues() {
        ((Component)this.previewLbl).setFont(this.props.font);
        ((Component)this.previewLbl).setBackground(this.props.background);
        ((Component)this.previewLbl).setForeground(this.props.foreground);
        this.msgPreviewTxt.setFont(this.props.messageFont);
        this.signatureTxt.setFont(this.props.messageFont);
        this.replyToTxt.setText(this.props.replyTo);
        this.signatureTxt.setText(this.props.signature);
        if (this.props.useAging) {
            this.expireChk.setState(true);
            this.expireTxt.setEnabled(true);
            this.expireTxt.setText(String.valueOf(this.props.aging));
        }
        else {
            this.expireChk.setState(false);
            this.expireTxt.setEnabled(false);
            this.expireTxt.setText(String.valueOf(this.props.aging));
        }
        this.htmlChk.setState(this.props.showHtml);
        this.checkOnLoadChk.setState(this.props.checkOnLoad);
        if (this.props.useInterval) {
            this.checkChk.setState(true);
            this.checkTxt.setEnabled(true);
            this.checkTxt.setText(String.valueOf(this.props.interval));
        }
        else {
            this.checkChk.setState(false);
            this.checkTxt.setEnabled(false);
            this.checkTxt.setText(String.valueOf(this.props.interval));
        }
        this.setMenus();
        if (this.props.toolbars) {
            this.panel18.setEnabled(true);
            this.panel19.setEnabled(true);
        }
        else {
            this.panel18.setEnabled(false);
            this.panel19.setEnabled(false);
        }
        this.newChk.setState(this.props.cacheNew);
        this.unreadChk.setState(this.props.cacheUnread);
        this.readChk.setState(this.props.cacheRead);
        this.recoveredChk.setState(this.props.cacheRecovered);
        this.unmarkedChk.setState(this.props.cacheUnmarked);
    }
    
    void setMenus() {
        final Font font = this.props.font;
        final Font messageFont = this.props.messageFont;
        final String name = font.getName();
        final int style = font.getStyle();
        final String value = String.valueOf(font.getSize());
        final String name2 = messageFont.getName();
        final int style2 = messageFont.getStyle();
        final String value2 = String.valueOf(messageFont.getSize());
        this.fontChc.select(name);
        this.msgFontChc.select(name2);
        this.sizeChc.select(value);
        this.msgSizeChc.select(value2);
        if (style == 0) {
            this.styleChc.select(0);
        }
        else if (style == 1) {
            this.styleChc.select(1);
        }
        else if (style == 2) {
            this.styleChc.select(2);
        }
        else if (style == 3) {
            this.styleChc.select(3);
        }
        if (style2 == 0) {
            this.msgStyleChc.select(0);
        }
        else if (style2 == 1) {
            this.msgStyleChc.select(1);
        }
        else if (style2 == 2) {
            this.msgStyleChc.select(2);
        }
        else if (style2 == 3) {
            this.msgStyleChc.select(3);
        }
        final Color foreground = this.props.foreground;
        if (foreground.equals(Color.black)) {
            this.colorChc.select(0);
        }
        else if (foreground.equals(Color.blue)) {
            this.colorChc.select(1);
        }
        else if (foreground.equals(Color.cyan)) {
            this.colorChc.select(2);
        }
        else if (foreground.equals(Color.darkGray)) {
            this.colorChc.select(3);
        }
        else if (foreground.equals(Color.gray)) {
            this.colorChc.select(4);
        }
        else if (foreground.equals(Color.green)) {
            this.colorChc.select(5);
        }
        else if (foreground.equals(Color.lightGray)) {
            this.colorChc.select(6);
        }
        else if (foreground.equals(Color.magenta)) {
            this.colorChc.select(7);
        }
        else if (foreground.equals(Color.orange)) {
            this.colorChc.select(8);
        }
        else if (foreground.equals(Color.pink)) {
            this.colorChc.select(9);
        }
        else if (foreground.equals(Color.red)) {
            this.colorChc.select(10);
        }
        else if (foreground.equals(Color.white)) {
            this.colorChc.select(11);
        }
        else if (foreground.equals(Color.yellow)) {
            this.colorChc.select(12);
        }
        final Color background = this.props.background;
        if (background.equals(Color.black)) {
            this.backgroundChc.select(0);
        }
        else if (background.equals(Color.blue)) {
            this.backgroundChc.select(1);
        }
        else if (background.equals(Color.cyan)) {
            this.backgroundChc.select(2);
        }
        else if (background.equals(Color.darkGray)) {
            this.backgroundChc.select(3);
        }
        else if (background.equals(Color.gray)) {
            this.backgroundChc.select(4);
        }
        else if (background.equals(Color.green)) {
            this.backgroundChc.select(5);
        }
        else if (background.equals(Color.lightGray)) {
            this.backgroundChc.select(6);
        }
        else if (background.equals(Color.magenta)) {
            this.backgroundChc.select(7);
        }
        else if (background.equals(Color.orange)) {
            this.backgroundChc.select(8);
        }
        else if (background.equals(Color.pink)) {
            this.backgroundChc.select(9);
        }
        else if (background.equals(Color.red)) {
            this.backgroundChc.select(10);
        }
        else if (background.equals(Color.white)) {
            this.backgroundChc.select(11);
        }
        else if (background.equals(Color.yellow)) {
            this.backgroundChc.select(12);
        }
        this.toolbarChk.setState(this.props.toolbars);
        if (this.props.buttonSize == 28) {
            this.bsizeChc.select(0);
        }
        else if (this.props.buttonSize == 78) {
            this.bsizeChc.select(2);
        }
        else {
            this.bsizeChc.select(1);
        }
        if (this.props.toolbarSide.equalsIgnoreCase("North")) {
            this.placementChc.select(0);
            return;
        }
        if (this.props.toolbarSide.equalsIgnoreCase("East")) {
            this.placementChc.select(1);
            return;
        }
        if (this.props.toolbarSide.equalsIgnoreCase("South")) {
            this.placementChc.select(2);
            return;
        }
        if (this.props.toolbarSide.equalsIgnoreCase("West")) {
            this.placementChc.select(3);
        }
    }
    
    void msgFontChc_ItemStateChanged(final ItemEvent itemEvent) {
        final String selectedItem = this.msgFontChc.getSelectedItem();
        final Font font = this.msgPreviewTxt.getFont();
        font.getName();
        this.font = new Font(selectedItem, font.getStyle(), new Integer(font.getSize()));
        this.msgPreviewTxt.setFont(this.font);
    }
    
    void msgSizeChc_ItemStateChanged(final ItemEvent itemEvent) {
        final int intValue = new Integer(this.msgSizeChc.getSelectedItem());
        final Font font = this.msgPreviewTxt.getFont();
        final String name = font.getName();
        new Integer(font.getSize());
        this.font = new Font(name, font.getStyle(), intValue);
        this.msgPreviewTxt.setFont(this.font);
    }
    
    void msgStyleChc_ItemStateChanged(final ItemEvent itemEvent) {
        final int selectedIndex = this.msgStyleChc.getSelectedIndex();
        int n = 0;
        if (selectedIndex == 0) {
            n = 0;
        }
        else if (selectedIndex == 1) {
            n = 1;
        }
        else if (selectedIndex == 2) {
            n = 2;
        }
        else if (selectedIndex == 3) {
            n = 3;
        }
        final Font font = this.msgPreviewTxt.getFont();
        final String name = font.getName();
        final int intValue = new Integer(font.getSize());
        font.getStyle();
        this.font = new Font(name, n, intValue);
        this.msgPreviewTxt.setFont(this.font);
    }
    
    void expireChk_ItemStateChanged(final ItemEvent itemEvent) {
        this.expireTxt.setEnabled(this.expireChk.getState());
    }
    
    void checkChk_ItemStateChanged(final ItemEvent itemEvent) {
        this.checkTxt.setEnabled(this.checkChk.getState());
    }
    
    void toolbarChk_ItemStateChanged(final ItemEvent itemEvent) {
        this.panel18.setEnabled(this.toolbarChk.getState());
        this.panel19.setEnabled(this.toolbarChk.getState());
    }
    
    void helpBtn_Action(final ActionEvent actionEvent) {
        if (!this.props.connected) {
            final ErrorDlg errorDlg = new ErrorDlg(this, "ERROR!", this.props.language.getProperty("error.disconnected_help", "Online help is not available when disconnected."), true);
            errorDlg.setBackground(this.props.background);
            errorDlg.setForeground(this.props.foreground);
            errorDlg.setFont(this.props.font);
            errorDlg.show();
            return;
        }
        this.props.showHelp();
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == PropsFrame.this) {
                PropsFrame.this.Frame1_WindowClosing(windowEvent);
            }
        }
    }
    
    class SymItem implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            final Object source = itemEvent.getSource();
            if (source == PropsFrame.this.fontChc) {
                PropsFrame.this.fontChc_ItemStateChanged(itemEvent);
                return;
            }
            if (source == PropsFrame.this.sizeChc) {
                PropsFrame.this.sizeChc_ItemStateChanged(itemEvent);
                return;
            }
            if (source == PropsFrame.this.styleChc) {
                PropsFrame.this.styleChc_ItemStateChanged(itemEvent);
                return;
            }
            if (source == PropsFrame.this.colorChc) {
                PropsFrame.this.colorChc_ItemStateChanged(itemEvent);
                return;
            }
            if (source == PropsFrame.this.backgroundChc) {
                PropsFrame.this.backgroundChc_ItemStateChanged(itemEvent);
                return;
            }
            if (source == PropsFrame.this.msgSizeChc) {
                PropsFrame.this.msgSizeChc_ItemStateChanged(itemEvent);
                return;
            }
            if (source == PropsFrame.this.msgStyleChc) {
                PropsFrame.this.msgStyleChc_ItemStateChanged(itemEvent);
                return;
            }
            if (source == PropsFrame.this.msgFontChc) {
                PropsFrame.this.msgFontChc_ItemStateChanged(itemEvent);
                return;
            }
            if (source == PropsFrame.this.expireChk) {
                PropsFrame.this.expireChk_ItemStateChanged(itemEvent);
                return;
            }
            if (source == PropsFrame.this.checkChk) {
                PropsFrame.this.checkChk_ItemStateChanged(itemEvent);
                return;
            }
            if (source == PropsFrame.this.toolbarChk) {
                PropsFrame.this.toolbarChk_ItemStateChanged(itemEvent);
            }
        }
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == PropsFrame.this.cancelBtn) {
                PropsFrame.this.cancelBtn_Action(actionEvent);
                return;
            }
            if (source == PropsFrame.this.defaultsBtn) {
                PropsFrame.this.defaultsBtn_Action(actionEvent);
                return;
            }
            if (source == PropsFrame.this.okBtn) {
                PropsFrame.this.okBtn_Action(actionEvent);
                return;
            }
            if (source == PropsFrame.this.helpBtn) {
                PropsFrame.this.helpBtn_Action(actionEvent);
            }
        }
    }
}
