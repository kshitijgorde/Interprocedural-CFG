import java.awt.event.FocusAdapter;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.beans.PropertyVetoException;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import symantec.itools.lang.Context;
import symantec.itools.awt.LabelButton;
import java.awt.Checkbox;
import symantec.itools.awt.util.spinner.NumericSpinner;
import symantec.itools.awt.Label3D;
import java.awt.Choice;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Panel;
import java.net.URLConnection;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class edit extends Applet implements Runnable
{
    Thread m_scroll;
    Color[] color;
    int SPEED;
    int COLORSPEED;
    int BACKGROUNDCOLOR;
    int WIDTH;
    int HEIGTH;
    int MOVECOLORS;
    int TEXTCOLOR;
    int YES;
    int FONTSIZE;
    int SCROLLHEIGTH;
    int MOVEFONT;
    int FONTSTYLE;
    int BACKIMAGE;
    int colorarraysize;
    String SCROLLTEXT;
    String WHATFONT;
    String IMAGEFILE;
    boolean first;
    Image offScreenImage;
    Graphics offScreen;
    Image image1;
    URL url;
    URLConnection urlc;
    String[] availableFonts;
    Panel panel1;
    TextField textField1;
    Label scrolllabel;
    Label fontlabel;
    Label widthlable;
    Choice widthchoice;
    Choice bgcchoice;
    Label bgcolorlabel;
    Choice fgcchoice;
    Label forecolorlabel;
    Choice fontsizechoice;
    Label fontsizelabel;
    Choice fontstylechoice;
    Label fontstylelabel;
    Label imagefilelabel;
    Label3D label3D2;
    Label3D label3D3;
    Label3D label3D1;
    NumericSpinner scrollspeedspinner;
    Label scrollspeedlabel;
    Checkbox cyclecolorcheckbox;
    Checkbox backimagecheck;
    Label cyclespdlabel;
    NumericSpinner colorspeedspinner;
    Choice choice1;
    Choice imagefilechoice;
    LabelButton homebutton;
    LabelButton registerbutton;
    LabelButton downloadbutton;
    int x;
    int coll;
    int colorcounter;
    int yes;
    int textwidth;
    
    public void init() {
        this.createColors();
        this.first = true;
        this.offScreenImage = this.createImage(this.WIDTH, this.HEIGTH);
        this.offScreen = this.offScreenImage.getGraphics();
        this.grabimage();
        Context.setApplet(this);
        this.setLayout(null);
        this.setSize(660, 402);
        (this.panel1 = new Panel()).setLayout(null);
        this.panel1.setBounds(0, 120, 660, 316);
        this.panel1.setBackground(new Color(12632256));
        this.add(this.panel1);
        (this.textField1 = new TextField()).setText("ENTER YOUR MESSAGE HERE.");
        this.textField1.setBounds(0, 204, 660, 26);
        this.textField1.setForeground(new Color(255));
        this.textField1.setBackground(new Color(-8355776));
        this.panel1.add(this.textField1);
        (this.scrolllabel = new Label("SCROLLTEXT GOES HERE")).setBounds(216, 192, 144, 12);
        this.scrolllabel.setFont(new Font("Courier", 1, 12));
        this.scrolllabel.setBackground(new Color(16776960));
        this.panel1.add(this.scrolllabel);
        (this.fontlabel = new Label("SELECT A FONT")).setBounds(12, 12, 96, 12);
        this.fontlabel.setFont(new Font("Dialog", 1, 12));
        this.fontlabel.setForeground(new Color(65280));
        this.fontlabel.setBackground(new Color(-16744320));
        this.panel1.add(this.fontlabel);
        (this.widthlable = new Label("WIDTH")).setBounds(180, 96, 48, 12);
        this.widthlable.setFont(new Font("Dialog", 1, 12));
        this.widthlable.setForeground(new Color(65280));
        this.widthlable.setBackground(new Color(-12550016));
        this.panel1.add(this.widthlable);
        (this.widthchoice = new Choice()).addItem("100");
        this.widthchoice.addItem("200");
        this.widthchoice.addItem("300");
        this.widthchoice.addItem("400");
        this.widthchoice.addItem("500");
        this.widthchoice.addItem("600");
        this.widthchoice.addItem("640");
        try {
            this.widthchoice.select(6);
        }
        catch (IllegalArgumentException ex) {}
        this.panel1.add(this.widthchoice);
        this.widthchoice.setBounds(180, 108, 60, 20);
        this.widthchoice.setForeground(new Color(16776960));
        this.widthchoice.setBackground(new Color(-16744320));
        (this.bgcchoice = new Choice()).addItem("BLACK");
        this.bgcchoice.addItem("BLUE");
        this.bgcchoice.addItem("GREEN");
        this.bgcchoice.addItem("CYAN");
        this.bgcchoice.addItem("RED");
        this.bgcchoice.addItem("MAGENTA");
        this.bgcchoice.addItem("BROWN");
        this.bgcchoice.addItem("LIGHT GRAY");
        this.bgcchoice.addItem("DARK GRAY");
        this.bgcchoice.addItem("LIGHT GREEN");
        this.bgcchoice.addItem("LIGHT CYAN");
        this.bgcchoice.addItem("LIGHT RED");
        this.bgcchoice.addItem("LIGHT MAGENTA");
        this.bgcchoice.addItem("YELLOW");
        this.bgcchoice.addItem("WHITE");
        try {
            this.bgcchoice.select(0);
        }
        catch (IllegalArgumentException ex2) {}
        this.panel1.add(this.bgcchoice);
        this.bgcchoice.setBounds(264, 72, 144, 24);
        this.bgcchoice.setForeground(new Color(16776960));
        this.bgcchoice.setBackground(new Color(-16744320));
        (this.bgcolorlabel = new Label("BACKGROUND COLOR")).setBounds(264, 60, 144, 12);
        this.bgcolorlabel.setFont(new Font("Dialog", 1, 12));
        this.bgcolorlabel.setForeground(new Color(65280));
        this.bgcolorlabel.setBackground(new Color(-12550016));
        this.panel1.add(this.bgcolorlabel);
        (this.fgcchoice = new Choice()).addItem("BLACK");
        this.fgcchoice.addItem("BLUE");
        this.fgcchoice.addItem("GREEN");
        this.fgcchoice.addItem("CYAN");
        this.fgcchoice.addItem("RED");
        this.fgcchoice.addItem("MAGENTA");
        this.fgcchoice.addItem("BROWN");
        this.fgcchoice.addItem("LIGHT GRAY");
        this.fgcchoice.addItem("DARK GRAY");
        this.fgcchoice.addItem("LIGHT GREEN");
        this.fgcchoice.addItem("LIGHT CYAN");
        this.fgcchoice.addItem("LIGHT RED");
        this.fgcchoice.addItem("LIGHT MAGENTA");
        this.fgcchoice.addItem("YELLOW");
        this.fgcchoice.addItem("WHITE");
        try {
            this.fgcchoice.select(5);
        }
        catch (IllegalArgumentException ex3) {}
        this.panel1.add(this.fgcchoice);
        this.fgcchoice.setBounds(264, 24, 144, 22);
        this.fgcchoice.setForeground(new Color(16776960));
        this.fgcchoice.setBackground(new Color(-16744320));
        (this.forecolorlabel = new Label("TEXT COLOR")).setBounds(264, 12, 84, 12);
        this.forecolorlabel.setFont(new Font("Dialog", 1, 12));
        this.forecolorlabel.setForeground(new Color(65280));
        this.forecolorlabel.setBackground(new Color(-12550016));
        this.panel1.add(this.forecolorlabel);
        (this.fontsizechoice = new Choice()).addItem("10");
        this.fontsizechoice.addItem("20");
        this.fontsizechoice.addItem("30");
        this.fontsizechoice.addItem("40");
        this.fontsizechoice.addItem("50");
        this.fontsizechoice.addItem("60");
        this.fontsizechoice.addItem("70");
        this.fontsizechoice.addItem("80");
        this.fontsizechoice.addItem("");
        this.fontsizechoice.addItem("");
        try {
            this.fontsizechoice.select(7);
        }
        catch (IllegalArgumentException ex4) {}
        this.panel1.add(this.fontsizechoice);
        this.fontsizechoice.setBounds(12, 72, 146, 28);
        this.fontsizechoice.setForeground(new Color(16776960));
        this.fontsizechoice.setBackground(new Color(-16744320));
        (this.fontsizelabel = new Label("FONT SIZE")).setBounds(12, 60, 72, 12);
        this.fontsizelabel.setFont(new Font("Dialog", 1, 12));
        this.fontsizelabel.setForeground(new Color(65280));
        this.fontsizelabel.setBackground(new Color(-12550016));
        this.panel1.add(this.fontsizelabel);
        (this.fontstylechoice = new Choice()).addItem("BOLD");
        this.fontstylechoice.addItem("ITALIC");
        this.fontstylechoice.addItem("PLAIN");
        this.fontstylechoice.addItem("BOLD & ITALIC");
        try {
            this.fontstylechoice.select(0);
        }
        catch (IllegalArgumentException ex5) {}
        this.panel1.add(this.fontstylechoice);
        this.fontstylechoice.setBounds(12, 120, 142, 22);
        this.fontstylechoice.setForeground(new Color(16776960));
        this.fontstylechoice.setBackground(new Color(-16744320));
        (this.fontstylelabel = new Label("FONT STYLE")).setBounds(12, 108, 72, 12);
        this.fontstylelabel.setFont(new Font("Dialog", 1, 12));
        this.fontstylelabel.setForeground(new Color(65280));
        this.fontstylelabel.setBackground(new Color(-12550016));
        this.panel1.add(this.fontstylelabel);
        (this.imagefilelabel = new Label("BACKGROUND IMAGE")).setBounds(264, 108, 134, 12);
        this.imagefilelabel.setFont(new Font("Dialog", 1, 12));
        this.imagefilelabel.setForeground(new Color(65280));
        this.imagefilelabel.setBackground(new Color(-12550016));
        this.panel1.add(this.imagefilelabel);
        this.label3D2 = new Label3D();
        try {
            this.label3D2.setBorderedColor(new Color(16777215));
        }
        catch (PropertyVetoException ex6) {}
        try {
            this.label3D2.setText("JAVA SCROLL MACHINE");
        }
        catch (PropertyVetoException ex7) {}
        try {
            this.label3D2.setTextColor(new Color(16777215));
        }
        catch (PropertyVetoException ex8) {}
        this.label3D2.setBounds(0, 228, 144, 24);
        this.label3D2.setForeground(new Color(16777215));
        this.label3D2.setBackground(new Color(-8355840));
        this.panel1.add(this.label3D2);
        this.label3D3 = new Label3D();
        try {
            this.label3D3.setBorderedColor(new Color(16777215));
        }
        catch (PropertyVetoException ex9) {}
        try {
            this.label3D3.setText("V. 1.01");
        }
        catch (PropertyVetoException ex10) {}
        try {
            this.label3D3.setTextColor(new Color(16777215));
        }
        catch (PropertyVetoException ex11) {}
        this.label3D3.setBounds(0, 252, 144, 22);
        this.label3D3.setForeground(new Color(16777215));
        this.label3D3.setBackground(new Color(-8355840));
        this.panel1.add(this.label3D3);
        this.label3D1 = new Label3D();
        try {
            this.label3D1.setText("Copywrite 1998 RETRO VISION");
        }
        catch (PropertyVetoException ex12) {}
        try {
            this.label3D1.setTextColor(new Color(16777215));
        }
        catch (PropertyVetoException ex13) {}
        this.label3D1.setBounds(468, 228, 192, 22);
        this.label3D1.setForeground(new Color(16777215));
        this.label3D1.setBackground(new Color(-8355840));
        this.panel1.add(this.label3D1);
        this.scrollspeedspinner = new NumericSpinner();
        try {
            this.scrollspeedspinner.setCurrent(4);
        }
        catch (PropertyVetoException ex14) {}
        this.scrollspeedspinner.setBounds(180, 48, 56, 29);
        this.scrollspeedspinner.setForeground(new Color(16762880));
        this.scrollspeedspinner.setBackground(new Color(-16744320));
        this.panel1.add(this.scrollspeedspinner);
        (this.scrollspeedlabel = new Label("SCROLL SPEED")).setBounds(168, 36, 84, 12);
        this.scrollspeedlabel.setFont(new Font("Courier", 1, 12));
        this.scrollspeedlabel.setForeground(new Color(65280));
        this.scrollspeedlabel.setBackground(new Color(-12550016));
        this.panel1.add(this.scrollspeedlabel);
        (this.cyclecolorcheckbox = new Checkbox("CYCLE COLORS")).setBounds(492, 36, 108, 12);
        this.cyclecolorcheckbox.setForeground(new Color(65280));
        this.cyclecolorcheckbox.setBackground(new Color(-12550016));
        this.panel1.add(this.cyclecolorcheckbox);
        (this.backimagecheck = new Checkbox("BACKGROUND IMAGE")).setBounds(492, 12, 144, 16);
        this.backimagecheck.setForeground(new Color(65280));
        this.backimagecheck.setBackground(new Color(-12550016));
        this.panel1.add(this.backimagecheck);
        (this.cyclespdlabel = new Label("SPEED")).setBounds(492, 60, 48, 12);
        this.cyclespdlabel.setForeground(new Color(65280));
        this.cyclespdlabel.setBackground(new Color(-12550016));
        this.panel1.add(this.cyclespdlabel);
        this.colorspeedspinner = new NumericSpinner();
        try {
            this.colorspeedspinner.setMin(1);
        }
        catch (PropertyVetoException ex15) {}
        try {
            this.colorspeedspinner.setCurrent(1);
        }
        catch (PropertyVetoException ex16) {}
        this.colorspeedspinner.setBounds(492, 72, 56, 24);
        this.colorspeedspinner.setForeground(new Color(16762880));
        this.colorspeedspinner.setBackground(new Color(-16744320));
        this.panel1.add(this.colorspeedspinner);
        this.choice1 = new Choice();
        this.panel1.add(this.choice1);
        this.choice1.setBounds(12, 24, 144, 25);
        this.choice1.setForeground(new Color(16776960));
        this.choice1.setBackground(new Color(-16744320));
        (this.imagefilechoice = new Choice()).addItem("image1.gif");
        this.imagefilechoice.addItem("image2.gif");
        this.imagefilechoice.addItem("image3.gif");
        this.imagefilechoice.addItem("image4.gif");
        this.imagefilechoice.addItem("image5.gif");
        this.imagefilechoice.addItem("image6.gif");
        this.imagefilechoice.addItem("image7.gif");
        this.imagefilechoice.addItem("image8.gif");
        this.imagefilechoice.addItem("image9.gif");
        this.imagefilechoice.addItem("image10.gif");
        this.imagefilechoice.addItem("image11.gif");
        this.imagefilechoice.addItem("image12.gif");
        this.imagefilechoice.addItem("image13.gif");
        this.imagefilechoice.addItem("image14.gif");
        this.imagefilechoice.addItem("image15.gif");
        this.imagefilechoice.addItem("image16.gif");
        this.imagefilechoice.addItem("image17.gif");
        this.imagefilechoice.addItem("image18.gif");
        this.imagefilechoice.addItem("image19.gif");
        this.imagefilechoice.addItem("image20.gif");
        try {
            this.imagefilechoice.select(0);
        }
        catch (IllegalArgumentException ex17) {}
        this.panel1.add(this.imagefilechoice);
        this.imagefilechoice.setBounds(264, 120, 148, 24);
        this.imagefilechoice.setForeground(new Color(16776960));
        this.imagefilechoice.setBackground(new Color(-16744320));
        this.homebutton = new LabelButton();
        try {
            this.homebutton.setText("HOME PAGE");
        }
        catch (PropertyVetoException ex18) {}
        try {
            this.homebutton.setLinkURL(new URL("http://ddi.digital.net/~retro/"));
        }
        catch (MalformedURLException ex19) {}
        catch (PropertyVetoException ex20) {}
        try {
            this.homebutton.setBorderColor(new Color(16777215));
        }
        catch (PropertyVetoException ex21) {}
        try {
            this.homebutton.setButtonColor(new Color(-16744256));
        }
        catch (PropertyVetoException ex22) {}
        this.homebutton.setBounds(384, 228, 86, 32);
        this.homebutton.setFont(new Font("Dialog", 1, 12));
        this.panel1.add(this.homebutton);
        this.registerbutton = new LabelButton();
        try {
            this.registerbutton.setText("REGISTER");
        }
        catch (PropertyVetoException ex23) {}
        try {
            this.registerbutton.setLinkURL(new URL("http://ddi.digital.net/~retro/order.htm"));
        }
        catch (MalformedURLException ex24) {}
        catch (PropertyVetoException ex25) {}
        try {
            this.registerbutton.setBorderColor(new Color(16777215));
        }
        catch (PropertyVetoException ex26) {}
        try {
            this.registerbutton.setButtonColor(new Color(-16744256));
        }
        catch (PropertyVetoException ex27) {}
        this.registerbutton.setBounds(144, 228, 86, 32);
        this.registerbutton.setFont(new Font("Dialog", 1, 12));
        this.panel1.add(this.registerbutton);
        this.downloadbutton = new LabelButton();
        try {
            this.downloadbutton.setText("DOWNLOAD JSM DEMO");
        }
        catch (PropertyVetoException ex28) {}
        try {
            this.downloadbutton.setLinkURL(new URL("http://ddi.digital.net/~retro/downloads/index.html"));
        }
        catch (MalformedURLException ex29) {}
        catch (PropertyVetoException ex30) {}
        try {
            this.downloadbutton.setBorderColor(new Color(16777215));
        }
        catch (PropertyVetoException ex31) {}
        try {
            this.downloadbutton.setButtonColor(new Color(-16744256));
        }
        catch (PropertyVetoException ex32) {}
        this.downloadbutton.setBounds(228, 228, 155, 32);
        this.downloadbutton.setFont(new Font("Dialog", 1, 12));
        this.panel1.add(this.downloadbutton);
        this.getfonts();
        final SymAction symAction = new SymAction();
        this.scrollspeedspinner.addActionListener(symAction);
        final SymItem symItem = new SymItem();
        this.choice1.addItemListener(symItem);
        this.colorspeedspinner.addActionListener(symAction);
        this.textField1.addFocusListener(new SymFocus());
        this.widthchoice.addItemListener(symItem);
        this.bgcchoice.addItemListener(symItem);
        this.fgcchoice.addItemListener(symItem);
        this.fontsizechoice.addItemListener(symItem);
        this.cyclecolorcheckbox.addItemListener(symItem);
        this.fontstylechoice.addItemListener(symItem);
        this.imagefilechoice.addItemListener(symItem);
        this.backimagecheck.addItemListener(symItem);
    }
    
    public void getfonts() {
        this.availableFonts = Toolkit.getDefaultToolkit().getFontList();
        for (int i = 0; i < this.availableFonts.length; ++i) {
            this.choice1.addItem(this.availableFonts[i]);
        }
    }
    
    public void createColors() {
        this.color = new Color[16];
        final int[] array = { 0, 0, 0, 0, 150, 150, 150, 150, 90, 0, 0, 255, 255, 255, 255 };
        final int[] array2 = { 0, 0, 150, 150, 0, 0, 90, 150, 90, 255, 255, 0, 0, 255, 255 };
        final int[] array3 = { 0, 150, 0, 150, 0, 150, 0, 150, 90, 0, 255, 0, 255, 0, 255 };
        for (int i = 0; i < 15; ++i) {
            this.color[i] = new Color(array[i], array2[i], array3[i]);
        }
    }
    
    void scrollspeedspinner_actionPerformed(final ActionEvent actionEvent) {
        this.SPEED = new Integer(this.scrollspeedspinner.getCurrentText());
    }
    
    void choice1_ItemStateChanged(final ItemEvent itemEvent) {
        this.WHATFONT = this.choice1.getSelectedItem().toString();
    }
    
    void colorspeedspinner_actionPerformed(final ActionEvent actionEvent) {
        this.COLORSPEED = new Integer(this.colorspeedspinner.getCurrentText());
    }
    
    void textField1_FocusLost(final FocusEvent focusEvent) {
        this.SCROLLTEXT = this.textField1.getText();
        this.first = true;
    }
    
    void widthchoice_ItemStateChanged(final ItemEvent itemEvent) {
        this.WIDTH = new Integer(this.widthchoice.getSelectedItem());
        this.first = true;
    }
    
    void bgcchoice_ItemStateChanged(final ItemEvent itemEvent) {
        final String selectedItem = this.bgcchoice.getSelectedItem();
        if (selectedItem == "BLACK") {
            this.BACKGROUNDCOLOR = 0;
        }
        if (selectedItem == "BLUE") {
            this.BACKGROUNDCOLOR = 1;
        }
        if (selectedItem == "GREEN") {
            this.BACKGROUNDCOLOR = 2;
        }
        if (selectedItem == "CYAN") {
            this.BACKGROUNDCOLOR = 3;
        }
        if (selectedItem == "RED") {
            this.BACKGROUNDCOLOR = 4;
        }
        if (selectedItem == "MAGENTA") {
            this.BACKGROUNDCOLOR = 5;
        }
        if (selectedItem == "BROWN") {
            this.BACKGROUNDCOLOR = 6;
        }
        if (selectedItem == "LIGHT GRAY") {
            this.BACKGROUNDCOLOR = 7;
        }
        if (selectedItem == "DARK GRAY") {
            this.BACKGROUNDCOLOR = 8;
        }
        if (selectedItem == "LIGHT GREEN") {
            this.BACKGROUNDCOLOR = 9;
        }
        if (selectedItem == "LIGHT CYAN") {
            this.BACKGROUNDCOLOR = 10;
        }
        if (selectedItem == "LIGHT RED") {
            this.BACKGROUNDCOLOR = 11;
        }
        if (selectedItem == "LIGHT MAGENTA") {
            this.BACKGROUNDCOLOR = 12;
        }
        if (selectedItem == "YELLOW") {
            this.BACKGROUNDCOLOR = 13;
        }
        if (selectedItem == "WHITE") {
            this.BACKGROUNDCOLOR = 14;
        }
    }
    
    void fgcchoice_ItemStateChanged(final ItemEvent itemEvent) {
        final String selectedItem = this.fgcchoice.getSelectedItem();
        if (selectedItem == "BLACK") {
            this.TEXTCOLOR = 0;
        }
        if (selectedItem == "BLUE") {
            this.TEXTCOLOR = 1;
        }
        if (selectedItem == "GREEN") {
            this.TEXTCOLOR = 2;
        }
        if (selectedItem == "CYAN") {
            this.TEXTCOLOR = 3;
        }
        if (selectedItem == "RED") {
            this.TEXTCOLOR = 4;
        }
        if (selectedItem == "MAGENTA") {
            this.TEXTCOLOR = 5;
        }
        if (selectedItem == "BROWN") {
            this.TEXTCOLOR = 6;
        }
        if (selectedItem == "LIGHT GRAY") {
            this.TEXTCOLOR = 7;
        }
        if (selectedItem == "DARK GRAY") {
            this.TEXTCOLOR = 8;
        }
        if (selectedItem == "LIGHT GREEN") {
            this.TEXTCOLOR = 9;
        }
        if (selectedItem == "LIGHT CYAN") {
            this.TEXTCOLOR = 10;
        }
        if (selectedItem == "LIGHT RED") {
            this.TEXTCOLOR = 11;
        }
        if (selectedItem == "LIGHT MAGENTA") {
            this.TEXTCOLOR = 12;
        }
        if (selectedItem == "YELLOW") {
            this.TEXTCOLOR = 13;
        }
        if (selectedItem == "WHITE") {
            this.TEXTCOLOR = 14;
        }
    }
    
    void fontsizechoice_ItemStateChanged(final ItemEvent itemEvent) {
        this.FONTSIZE = new Integer(this.fontsizechoice.getSelectedItem());
        if (this.FONTSIZE == 10) {
            this.HEIGTH = 20;
            this.SCROLLHEIGTH = 12;
        }
        if (this.FONTSIZE == 20) {
            this.HEIGTH = 30;
            this.SCROLLHEIGTH = 20;
        }
        if (this.FONTSIZE == 30) {
            this.HEIGTH = 48;
            this.SCROLLHEIGTH = 30;
        }
        if (this.FONTSIZE == 40) {
            this.HEIGTH = 60;
            this.SCROLLHEIGTH = 40;
        }
        if (this.FONTSIZE == 50) {
            this.HEIGTH = 78;
            this.SCROLLHEIGTH = 50;
        }
        if (this.FONTSIZE == 60) {
            this.HEIGTH = 90;
            this.SCROLLHEIGTH = 60;
        }
        if (this.FONTSIZE == 70) {
            this.HEIGTH = 108;
            this.SCROLLHEIGTH = 70;
        }
        if (this.FONTSIZE == 80) {
            this.HEIGTH = 120;
            this.SCROLLHEIGTH = 80;
        }
        if (this.FONTSIZE == 90) {
            this.HEIGTH = 138;
            this.SCROLLHEIGTH = 90;
        }
        if (this.FONTSIZE == 100) {
            this.HEIGTH = 150;
            this.SCROLLHEIGTH = 100;
        }
        this.first = true;
    }
    
    void cyclecolorcheckbox_ItemStateChanged(final ItemEvent itemEvent) {
        final boolean state = this.cyclecolorcheckbox.getState();
        if (state) {
            this.MOVECOLORS = 1;
        }
        if (!state) {
            this.MOVECOLORS = 0;
        }
    }
    
    void fontstylechoice_ItemStateChanged(final ItemEvent itemEvent) {
        final String string = this.fontstylechoice.getSelectedItem().toString();
        if (string == "BOLD") {
            this.FONTSTYLE = 1;
        }
        if (string == "ITALIC") {
            this.FONTSTYLE = 2;
        }
        if (string == "PLAIN") {
            this.FONTSTYLE = 0;
        }
        if (string == "BOLD & ITALIC") {
            this.FONTSTYLE = 3;
        }
    }
    
    void imagefilechoice_ItemStateChanged(final ItemEvent itemEvent) {
        this.backimagecheck.setState(false);
        this.BACKIMAGE = 0;
        this.IMAGEFILE = this.imagefilechoice.getSelectedItem().toString();
        if (this.IMAGEFILE == "image1.gif") {
            this.IMAGEFILE = "images/image1.gif";
        }
        if (this.IMAGEFILE == "image2.gif") {
            this.IMAGEFILE = "images/image2.gif";
        }
        if (this.IMAGEFILE == "image3.gif") {
            this.IMAGEFILE = "images/image3.gif";
        }
        if (this.IMAGEFILE == "image4.gif") {
            this.IMAGEFILE = "images/image4.gif";
        }
        if (this.IMAGEFILE == "image5.gif") {
            this.IMAGEFILE = "images/image5.gif";
        }
        if (this.IMAGEFILE == "image6.gif") {
            this.IMAGEFILE = "images/image6.gif";
        }
        if (this.IMAGEFILE == "image7.gif") {
            this.IMAGEFILE = "images/image7.gif";
        }
        if (this.IMAGEFILE == "image8.gif") {
            this.IMAGEFILE = "images/image8.gif";
        }
        if (this.IMAGEFILE == "image9.gif") {
            this.IMAGEFILE = "images/image9.gif";
        }
        if (this.IMAGEFILE == "image10.gif") {
            this.IMAGEFILE = "images/image10.gif";
        }
        if (this.IMAGEFILE == "image11.gif") {
            this.IMAGEFILE = "images/image11.gif";
        }
        if (this.IMAGEFILE == "image12.gif") {
            this.IMAGEFILE = "images/image12.gif";
        }
        if (this.IMAGEFILE == "image13.gif") {
            this.IMAGEFILE = "images/image13.gif";
        }
        if (this.IMAGEFILE == "image14.gif") {
            this.IMAGEFILE = "images/image14.gif";
        }
        if (this.IMAGEFILE == "image15.gif") {
            this.IMAGEFILE = "images/image15.gif";
        }
        if (this.IMAGEFILE == "image16.gif") {
            this.IMAGEFILE = "images/image16.gif";
        }
        if (this.IMAGEFILE == "image17.gif") {
            this.IMAGEFILE = "images/image17.gif";
        }
        if (this.IMAGEFILE == "image18.gif") {
            this.IMAGEFILE = "images/image18.gif";
        }
        if (this.IMAGEFILE == "image19.gif") {
            this.IMAGEFILE = "images/image19.gif";
        }
        if (this.IMAGEFILE == "image20.gif") {
            this.IMAGEFILE = "images/image20.gif";
        }
        this.grabimage();
    }
    
    void backimagecheck_ItemStateChanged(final ItemEvent itemEvent) {
        final boolean state = this.backimagecheck.getState();
        if (state) {
            this.BACKIMAGE = 1;
        }
        if (!state) {
            this.BACKIMAGE = 0;
        }
    }
    
    public void grabimage() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        try {
            mediaTracker.addImage(this.image1 = this.getImage(this.getDocumentBase(), this.IMAGEFILE), 0);
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
    }
    
    public void paint(final Graphics graphics) {
        final Font font = new Font(this.WHATFONT, this.FONTSTYLE, this.FONTSIZE);
        this.offScreen.setFont(font);
        if (this.first) {
            this.first = false;
            this.x = this.WIDTH;
            this.textwidth = graphics.getFontMetrics(font).stringWidth(this.SCROLLTEXT);
            this.offScreen.setColor(this.color[14]);
            this.offScreen.fillRect(0, 0, 640, 120);
            graphics.drawImage(this.offScreenImage, 0, 0, this.color[14], this);
            this.offScreenImage = this.createImage(this.WIDTH, this.HEIGTH);
            this.offScreen = this.offScreenImage.getGraphics();
            graphics.drawImage(this.offScreenImage, 0, 0, this.color[14], this);
        }
        if (this.MOVECOLORS == 0 & this.BACKIMAGE == 1) {
            this.offScreen.drawImage(this.image1, 0, 0, this.WIDTH, this.HEIGTH, this);
            this.offScreen.setColor(this.color[this.TEXTCOLOR]);
            this.offScreen.drawString(this.SCROLLTEXT, this.x, this.SCROLLHEIGTH);
            this.offScreen.setColor(this.color[this.TEXTCOLOR]);
            this.offScreen.drawString(this.SCROLLTEXT, this.x, this.SCROLLHEIGTH);
            graphics.drawImage(this.offScreenImage, 0, 0, this);
            this.x -= this.SPEED;
        }
        if (this.MOVECOLORS == 0 & this.BACKIMAGE == 0) {
            this.offScreen.setColor(this.color[this.BACKGROUNDCOLOR]);
            this.offScreen.fillRect(0, 0, this.WIDTH, this.HEIGTH);
            this.offScreen.setColor(this.color[this.TEXTCOLOR]);
            this.offScreen.drawString(this.SCROLLTEXT, this.x, this.SCROLLHEIGTH);
            graphics.drawImage(this.offScreenImage, 0, 0, this);
            this.x -= this.SPEED;
        }
        if (this.MOVECOLORS == 1 & this.BACKIMAGE == 1) {
            this.offScreen.drawImage(this.image1, 0, 0, this.WIDTH, this.HEIGTH, this);
            this.offScreen.setColor(this.color[this.coll]);
            this.offScreen.drawString(this.SCROLLTEXT, this.x, this.SCROLLHEIGTH);
            graphics.drawImage(this.offScreenImage, 0, 0, this);
            this.x -= this.SPEED;
            ++this.colorcounter;
            if (this.colorcounter % this.COLORSPEED == 0) {
                ++this.coll;
                if (this.coll > this.colorarraysize) {
                    this.coll = 1;
                }
            }
        }
        if (this.MOVECOLORS == 1 & this.BACKIMAGE == 0) {
            this.offScreen.setColor(this.color[this.BACKGROUNDCOLOR]);
            this.offScreen.fillRect(0, 0, this.WIDTH, this.HEIGTH);
            this.offScreen.setColor(this.color[this.coll]);
            this.offScreen.drawString(this.SCROLLTEXT, this.x, this.SCROLLHEIGTH);
            graphics.drawImage(this.offScreenImage, 0, 0, this);
            this.x -= this.SPEED;
            ++this.colorcounter;
            if (this.colorcounter % this.COLORSPEED == 0) {
                ++this.coll;
                if (this.coll > this.colorarraysize) {
                    this.coll = 1;
                }
            }
        }
        if (this.x < -this.textwidth) {
            this.x = this.WIDTH + 200;
        }
    }
    
    public void start() {
        if (this.m_scroll == null) {
            (this.m_scroll = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.m_scroll != null) {
            this.m_scroll.stop();
            this.m_scroll = null;
        }
    }
    
    public void run() {
        while (true) {
            try {
                while (true) {
                    this.paint(this.getGraphics());
                    Thread.sleep(30L);
                }
            }
            catch (InterruptedException ex) {
                this.stop();
                continue;
            }
            break;
        }
    }
    
    public edit() {
        this.SPEED = 4;
        this.COLORSPEED = 1;
        this.WIDTH = 640;
        this.HEIGTH = 60;
        this.TEXTCOLOR = 5;
        this.YES = 1;
        this.FONTSIZE = 40;
        this.SCROLLHEIGTH = 40;
        this.FONTSTYLE = 0;
        this.colorarraysize = 14;
        this.SCROLLTEXT = "Hello ..... Thankyou for trying out Retro Visions new product. ---(THE JAVA SCROLL MACHINE)--- We hope you will get plenty of use out of this program and continue to use it for along time. Updates will come on a regular basis.  Right now we are working on letting you use graphic fonts. the average graphic font is about 3k in size and looks better then the ttf fonts. 3k for your loading pleasure. Also we will be working on sideways moving colors with a much larger pallete. Links within the scroll will be added and you will be able to set idividual colors.  Thats it for now . the updates will keep me busy for awhile but you should see atleast one update per week.!! Keep those web pages scrolling!";
        this.WHATFONT = "Dialog";
        this.IMAGEFILE = "images/image1.gif";
        this.yes = 1;
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == edit.this.scrollspeedspinner) {
                edit.this.scrollspeedspinner_actionPerformed(actionEvent);
                return;
            }
            if (source == edit.this.colorspeedspinner) {
                edit.this.colorspeedspinner_actionPerformed(actionEvent);
            }
        }
    }
    
    class SymItem implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            final Object source = itemEvent.getSource();
            if (source == edit.this.choice1) {
                edit.this.choice1_ItemStateChanged(itemEvent);
                return;
            }
            if (source == edit.this.widthchoice) {
                edit.this.widthchoice_ItemStateChanged(itemEvent);
                return;
            }
            if (source == edit.this.bgcchoice) {
                edit.this.bgcchoice_ItemStateChanged(itemEvent);
                return;
            }
            if (source == edit.this.fgcchoice) {
                edit.this.fgcchoice_ItemStateChanged(itemEvent);
                return;
            }
            if (source == edit.this.fontsizechoice) {
                edit.this.fontsizechoice_ItemStateChanged(itemEvent);
                return;
            }
            if (source == edit.this.cyclecolorcheckbox) {
                edit.this.cyclecolorcheckbox_ItemStateChanged(itemEvent);
                return;
            }
            if (source == edit.this.fontstylechoice) {
                edit.this.fontstylechoice_ItemStateChanged(itemEvent);
                return;
            }
            if (source == edit.this.imagefilechoice) {
                edit.this.imagefilechoice_ItemStateChanged(itemEvent);
                return;
            }
            if (source == edit.this.backimagecheck) {
                edit.this.backimagecheck_ItemStateChanged(itemEvent);
            }
        }
    }
    
    class SymFocus extends FocusAdapter
    {
        public void focusLost(final FocusEvent focusEvent) {
            if (focusEvent.getSource() == edit.this.textField1) {
                edit.this.textField1_FocusLost(focusEvent);
            }
        }
    }
}
