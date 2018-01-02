import symantec.itools.awt.BaseTabbedPanel;
import symantec.itools.awt.ButtonBase;
import java.awt.Container;
import symantec.itools.awt.BorderPanel;
import java.awt.event.ComponentAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import COM.jscape.io.JSFileHandler;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.io.IOException;
import COM.jscape.mailwidgets.JSServerCommandException;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.File;
import java.util.Date;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.MenuShortcut;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeListener;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.FlowLayout;
import java.awt.Font;
import java.beans.PropertyVetoException;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import COM.jscape.mailwidgets.JSConnection;
import COM.jscape.mailwidgets.JSMessage;
import COM.jscape.mailwidgets.JSSMTPMailbox;
import symantec.itools.awt.ImageButton;
import symantec.itools.awt.util.ToolBarPanel;
import java.awt.List;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.TextArea;
import symantec.itools.awt.TabPanel;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class sendFrame extends Frame
{
    boolean DEBUG;
    boolean fComponentsAdjusted;
    Panel centerPnl;
    Panel fieldsPnl;
    Panel labelsPnl;
    Button toLbl;
    Button ccLbl;
    Label subjectLbl;
    Panel textPnl;
    TextField toTxt;
    TextField ccTxt;
    TextField subjectTxt;
    Label label1;
    TabPanel tabPnl;
    Panel messagePnl;
    TextArea messageTxt;
    Panel extrasPnl;
    Panel northPnl;
    Panel fields2Pnl;
    Panel panel3;
    Button bccLbl;
    Label replytoLbl;
    Panel panel4;
    TextField bccTxt;
    TextField replytoTxt;
    Label label5;
    Label label4;
    Panel receiptPnl;
    Checkbox receiptChk;
    Panel panel1;
    Label typeLbl;
    Choice typeChc;
    Label whenLbl;
    Choice receiptChc;
    Label label6;
    Panel attachPnl;
    List attachList;
    Panel eastPnl;
    Panel buttonPnl;
    Button addBtn;
    Button remBtn;
    Panel toolPnl;
    ToolBarPanel toolBarPanel1;
    ImageButton sendBtn;
    ImageButton newBtn;
    ImageButton attBtn;
    ImageButton addressBtn;
    ImageButton spellBtn;
    ImageButton helpBtn;
    JSSMTPMailbox smtpMbox;
    JSMessage message;
    JSConnection connection;
    String fromAddress;
    TextField lastSelected;
    JMProps props;
    String placement;
    int buttonSize;
    String attDirectory;
    MenuBar menuBar1;
    Menu menu1;
    MenuItem newMen;
    MenuItem sendMen;
    MenuItem closeMen;
    Menu menu3;
    MenuItem spellMen;
    MenuItem addressMen;
    MenuItem attachMen;
    
    public sendFrame(final JMProps props) {
        this.DEBUG = false;
        this.fComponentsAdjusted = false;
        this.props = props;
        this.setBackground(this.props.background);
        this.setForeground(this.props.foreground);
        this.setFont(this.props.font);
        this.props.addWindow(this);
        final Point location = new Point(this.props.newPosX, this.props.newPosY);
        this.setSize(new Dimension(this.props.newWidth, this.props.newHeight));
        this.setLocation(location);
        this.setLayout(new BorderLayout(5, 5));
        this.setVisible(false);
        (this.centerPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.centerPnl.setBounds(this.insets().left, this.insets().top + 45, 450, 385);
        this.add("Center", this.centerPnl);
        (this.fieldsPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.fieldsPnl.setBounds(0, 0, 450, 0);
        this.centerPnl.add("North", this.fieldsPnl);
        (this.textPnl = new Panel()).setLayout(new GridLayout(3, 1, 1, 1));
        this.textPnl.setBounds(60, 0, 372, 74);
        this.fieldsPnl.add("Center", this.textPnl);
        (this.toTxt = new TextField()).setBounds(0, 0, 372, 24);
        this.toTxt.setBackground(Color.white);
        this.toTxt.setForeground(Color.black);
        this.textPnl.add(this.toTxt);
        (this.ccTxt = new TextField()).setBounds(0, 25, 372, 24);
        this.ccTxt.setBackground(Color.white);
        this.ccTxt.setForeground(Color.black);
        this.textPnl.add(this.ccTxt);
        (this.subjectTxt = new TextField()).setBounds(0, 50, 372, 24);
        this.subjectTxt.setBackground(Color.white);
        this.subjectTxt.setForeground(Color.black);
        this.textPnl.add(this.subjectTxt);
        (this.labelsPnl = new Panel()).setLayout(new GridLayout(3, 1, 1, 1));
        this.labelsPnl.setBounds(0, 0, -1, 10);
        this.fieldsPnl.add("West", this.labelsPnl);
        (this.toLbl = new Button(this.props.language.getProperty("labels.to", "To:"))).setBackground(this.props.background);
        this.labelsPnl.add(this.toLbl);
        (this.ccLbl = new Button(this.props.language.getProperty("labels.cc", "CC:"))).setBackground(this.props.background);
        this.labelsPnl.add(this.ccLbl);
        (this.subjectLbl = new Label(this.props.language.getProperty("labels.subject", "Subject:"), 1)).setBounds(0, 50, 34, 24);
        this.labelsPnl.add(this.subjectLbl);
        (this.label1 = new Label(" ")).setBounds(432, 0, 18, 74);
        this.fieldsPnl.add("East", this.label1);
        this.tabPnl = new TabPanel();
        try {
            this.tabPnl.setPanelLabels(new String[] { this.props.language.getProperty("tabs.message", "Message"), this.props.language.getProperty("tabs.options", "Options"), this.props.language.getProperty("tabs.attachments", "Attachments") });
        }
        catch (PropertyVetoException ex) {}
        try {
            this.tabPnl.setTabsOnBottom(true);
        }
        catch (PropertyVetoException ex2) {}
        try {
            this.tabPnl.setCurrentPanelNdx(2);
        }
        catch (PropertyVetoException ex3) {}
        ((Component)this.tabPnl).setBounds(0, 74, 450, 311);
        this.centerPnl.add("Center", (Component)this.tabPnl);
        (this.messagePnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.messagePnl.setVisible(false);
        this.messagePnl.setBounds(12, 16, 426, 267);
        this.tabPnl.add((Component)this.messagePnl);
        (this.messageTxt = new TextArea("", 0, 80, 1)).setBounds(0, 0, 426, 267);
        this.messageTxt.setFont(new Font("Courier", 0, 12));
        this.messageTxt.setBackground(new Color(16777215));
        this.messagePnl.add("Center", this.messageTxt);
        (this.extrasPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.extrasPnl.setVisible(false);
        this.extrasPnl.setBounds(12, 16, 426, 267);
        this.tabPnl.add((Component)this.extrasPnl);
        (this.northPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.northPnl.setBounds(0, 0, 20, 40);
        this.extrasPnl.add("North", this.northPnl);
        (this.fields2Pnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.fields2Pnl.setBounds(0, 0, 450, 0);
        this.northPnl.add("North", this.fields2Pnl);
        (this.panel3 = new Panel()).setLayout(new GridLayout(3, 1, 1, 1));
        this.panel3.setBounds(0, 0, -1, 10);
        this.fields2Pnl.add("West", this.panel3);
        (this.bccLbl = new Button(this.props.language.getProperty("labels.bcc", "BCC:"))).setBackground(this.props.background);
        this.panel3.add(this.bccLbl);
        (this.replytoLbl = new Label(this.props.language.getProperty("labels.reply_to", "Reply-To:"), 1)).setBounds(0, 25, 33, 24);
        this.panel3.add(this.replytoLbl);
        (this.panel4 = new Panel()).setLayout(new GridLayout(3, 1, 1, 1));
        this.panel4.setBounds(60, 0, 372, 74);
        this.fields2Pnl.add("Center", this.panel4);
        (this.bccTxt = new TextField()).setBounds(0, 0, 372, 24);
        this.bccTxt.setBackground(Color.white);
        this.bccTxt.setForeground(Color.black);
        this.panel4.add(this.bccTxt);
        (this.replytoTxt = new TextField()).setBounds(0, 25, 372, 24);
        this.replytoTxt.setBackground(Color.white);
        this.replytoTxt.setForeground(Color.black);
        this.panel4.add(this.replytoTxt);
        (this.label5 = new Label(" ")).setBounds(432, 0, 18, 74);
        this.fields2Pnl.add("East", this.label5);
        (this.label4 = new Label(" ")).setBounds(0, 0, 18, 74);
        this.fields2Pnl.add("North", this.label4);
        (this.receiptPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.receiptPnl.setBounds(0, 0, 0, 0);
        this.northPnl.add("Center", this.receiptPnl);
        (this.receiptChk = new Checkbox(this.props.language.getProperty("labels.return_receipt", "Return receipt"))).setBounds(0, 0, 112, 24);
        this.receiptPnl.add("North", this.receiptChk);
        (this.panel1 = new Panel()).setLayout(new GridLayout(2, 2, 0, 0));
        this.panel1.setBounds(0, 0, 20, 40);
        this.receiptPnl.add("West", this.panel1);
        this.panel1.setEnabled(false);
        (this.typeLbl = new Label(this.props.language.getProperty("labels.type", "Type: "), 2)).setBounds(0, 0, 100, 40);
        this.panel1.add(this.typeLbl);
        (this.typeChc = new Choice()).addItem(this.props.language.getProperty("receipt.headers", "Headers"));
        this.typeChc.addItem(this.props.language.getProperty("receipt.complete", "Complete"));
        this.typeChc.setBounds(0, 0, 100, 40);
        this.panel1.add(this.typeChc);
        this.typeChc.setEnabled(false);
        (this.whenLbl = new Label(this.props.language.getProperty("labels.when", "When: "), 2)).setBounds(0, 0, 100, 40);
        this.panel1.add(this.whenLbl);
        (this.receiptChc = new Choice()).addItem(this.props.language.getProperty("receipt.received", "Received"));
        this.receiptChc.addItem(this.props.language.getProperty("receipt.failed", "Failed"));
        this.receiptChc.addItem(this.props.language.getProperty("receipt.delayed", "Delayed"));
        this.receiptChc.setBounds(0, 0, 112, 24);
        this.panel1.add(this.receiptChc);
        this.receiptChc.setEnabled(false);
        (this.label6 = new Label(" ")).setBounds(0, 0, 100, 40);
        this.northPnl.add("West", this.label6);
        (this.attachPnl = new Panel()).setLayout(new BorderLayout(5, 5));
        this.attachPnl.setBounds(12, 16, 426, 267);
        if (this.props.allowAttachments) {
            this.tabPnl.add((Component)this.attachPnl);
        }
        (this.attachList = new List(0, false)).setBounds(0, 0, 100, 40);
        this.attachPnl.add("Center", this.attachList);
        (this.eastPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.eastPnl.setBounds(366, 0, 60, 267);
        this.attachPnl.add("East", this.eastPnl);
        (this.buttonPnl = new Panel()).setLayout(new GridLayout(2, 1, 5, 5));
        this.buttonPnl.setBounds(0, 0, 60, 53);
        this.eastPnl.add("North", this.buttonPnl);
        (this.addBtn = new Button()).setActionCommand("button");
        this.addBtn.setLabel(this.props.language.getProperty("button.add", "Add..."));
        this.addBtn.setBounds(0, 0, 60, 24);
        this.buttonPnl.add(this.addBtn);
        (this.remBtn = new Button()).setActionCommand("button");
        this.remBtn.setLabel(this.props.language.getProperty("button.remove", "Remove"));
        this.remBtn.setBounds(0, 29, 60, 24);
        this.buttonPnl.add(this.remBtn);
        (this.toolPnl = new Panel()).setLayout(new BorderLayout(5, 0));
        this.toolPnl.setBounds(this.insets().left, this.insets().top, 450, 0);
        this.add(this.props.toolbarSide, this.toolPnl);
        this.toolBarPanel1 = new ToolBarPanel();
        try {
            ((BorderPanel)this.toolBarPanel1).setIPadBottom(0);
        }
        catch (PropertyVetoException ex4) {}
        try {
            ((BorderPanel)this.toolBarPanel1).setIPadSides(0);
        }
        catch (PropertyVetoException ex5) {}
        try {
            ((BorderPanel)this.toolBarPanel1).setIPadTop(0);
        }
        catch (PropertyVetoException ex6) {}
        this.toolBarPanel1.setLayout((LayoutManager)new FlowLayout(0, 0, 0));
        ((BorderPanel)this.toolBarPanel1).setBounds(0, 0, this.props.buttonSize, this.props.buttonSize);
        this.toolPnl.add("Center", (Component)this.toolBarPanel1);
        this.sendBtn = new ImageButton();
        try {
            URL imageURL;
            if (this.props.buttonSize == 28) {
                imageURL = new URL(this.props.base, "client/images/send24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL = new URL(this.props.base, "client/images/send76.gif");
            }
            else {
                imageURL = new URL(this.props.base, "client/images/send.gif");
            }
            this.sendBtn.setImageURL(imageURL);
        }
        catch (MalformedURLException ex7) {}
        catch (PropertyVetoException ex8) {}
        ((Component)this.sendBtn).setBounds(0, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.sendBtn);
        this.newBtn = new ImageButton();
        try {
            URL imageURL2;
            if (this.props.buttonSize == 28) {
                imageURL2 = new URL(this.props.base, "client/images/new24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL2 = new URL(this.props.base, "client/images/new76.gif");
            }
            else {
                imageURL2 = new URL(this.props.base, "client/images/new.gif");
            }
            this.newBtn.setImageURL(imageURL2);
        }
        catch (MalformedURLException ex9) {}
        catch (PropertyVetoException ex10) {}
        ((Component)this.newBtn).setBounds(46, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.newBtn);
        this.attBtn = new ImageButton();
        try {
            URL imageURL3;
            if (this.props.buttonSize == 28) {
                imageURL3 = new URL(this.props.base, "client/images/attach24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL3 = new URL(this.props.base, "client/images/attach76.gif");
            }
            else {
                imageURL3 = new URL(this.props.base, "client/images/attach.gif");
            }
            this.attBtn.setImageURL(imageURL3);
        }
        catch (MalformedURLException ex11) {}
        catch (PropertyVetoException ex12) {}
        ((Component)this.attBtn).setBounds(92, 0, 46, 46);
        if (this.props.allowAttachments) {
            ((Container)this.toolBarPanel1).add((Component)this.attBtn);
        }
        this.addressBtn = new ImageButton();
        try {
            URL imageURL4;
            if (this.props.buttonSize == 28) {
                imageURL4 = new URL(this.props.base, "client/images/address24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL4 = new URL(this.props.base, "client/images/address76.gif");
            }
            else {
                imageURL4 = new URL(this.props.base, "client/images/address.gif");
            }
            this.addressBtn.setImageURL(imageURL4);
        }
        catch (MalformedURLException ex13) {}
        catch (PropertyVetoException ex14) {}
        ((Component)this.addressBtn).setBounds(138, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.addressBtn);
        this.spellBtn = new ImageButton();
        try {
            URL imageURL5;
            if (this.props.buttonSize == 28) {
                imageURL5 = new URL(this.props.base, "client/images/spell24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL5 = new URL(this.props.base, "client/images/spell76.gif");
            }
            else {
                imageURL5 = new URL(this.props.base, "client/images/spell.gif");
            }
            this.spellBtn.setImageURL(imageURL5);
        }
        catch (MalformedURLException ex15) {}
        catch (PropertyVetoException ex16) {}
        ((Component)this.spellBtn).setBounds(184, 0, 46, 46);
        if (!this.props.proxyMode) {
            ((Container)this.toolBarPanel1).add((Component)this.spellBtn);
        }
        this.helpBtn = new ImageButton();
        try {
            URL imageURL6;
            if (this.props.buttonSize == 28) {
                imageURL6 = new URL(this.props.base, "client/images/help24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL6 = new URL(this.props.base, "client/images/help76.gif");
            }
            else {
                imageURL6 = new URL(this.props.base, "client/images/help.gif");
            }
            this.helpBtn.setImageURL(imageURL6);
        }
        catch (MalformedURLException ex17) {}
        catch (PropertyVetoException ex18) {}
        ((Component)this.helpBtn).setBounds(230, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.helpBtn);
        this.setTitle("New Message");
        this.menuBar1 = new MenuBar();
        this.menu1 = new Menu(this.props.language.getProperty("menu.file", "File"));
        this.newMen = new MenuItem(this.props.language.getProperty("menu.new_message", "New message..."));
        this.menu1.add(this.newMen);
        this.sendMen = new MenuItem(this.props.language.getProperty("menu.send_message", "Send message"));
        this.menu1.add(this.sendMen);
        this.menu1.addSeparator();
        this.closeMen = new MenuItem(this.props.language.getProperty("menu.close", "Close"));
        this.menu1.add(this.closeMen);
        this.menuBar1.add(this.menu1);
        this.menu3 = new Menu(this.props.language.getProperty("menu.tools", "Tools"));
        this.spellMen = new MenuItem(this.props.language.getProperty("menu.check_spelling", "Check spelling..."));
        if (!this.props.proxyMode) {
            this.menu3.add(this.spellMen);
        }
        this.addressMen = new MenuItem(this.props.language.getProperty("menu.address_book", "Address book..."));
        this.menu3.add(this.addressMen);
        this.attachMen = new MenuItem(this.props.language.getProperty("menu.attach_file", "Attach file..."));
        if (this.props.allowAttachments) {
            this.menu3.add(this.attachMen);
        }
        this.menuBar1.add(this.menu3);
        this.setMenuBar(this.menuBar1);
        this.addWindowListener(new SymWindow());
        final SymAction symAction = new SymAction();
        ((ButtonBase)this.attBtn).addActionListener((ActionListener)symAction);
        this.subjectTxt.addKeyListener(new SymKey());
        ((ButtonBase)this.sendBtn).addActionListener((ActionListener)symAction);
        ((ButtonBase)this.newBtn).addActionListener((ActionListener)symAction);
        this.receiptChk.addItemListener(new SymItem());
        ((ButtonBase)this.addressBtn).addActionListener((ActionListener)symAction);
        this.toLbl.addActionListener(symAction);
        this.ccLbl.addActionListener(symAction);
        this.bccLbl.addActionListener(symAction);
        final SymFocus symFocus = new SymFocus();
        this.ccTxt.addFocusListener(symFocus);
        this.toTxt.addFocusListener(symFocus);
        this.messageTxt.addFocusListener(symFocus);
        this.subjectTxt.addFocusListener(symFocus);
        this.addBtn.addActionListener(symAction);
        this.remBtn.addActionListener(symAction);
        ((ButtonBase)this.spellBtn).addActionListener((ActionListener)symAction);
        ((ButtonBase)this.helpBtn).addActionListener((ActionListener)symAction);
        ((Component)this.sendBtn).addFocusListener(symFocus);
        ((Component)this.newBtn).addFocusListener(symFocus);
        ((Component)this.attBtn).addFocusListener(symFocus);
        ((Component)this.addressBtn).addFocusListener(symFocus);
        ((Component)this.spellBtn).addFocusListener(symFocus);
        ((Component)this.helpBtn).addFocusListener(symFocus);
        this.bccTxt.addFocusListener(symFocus);
        ((BaseTabbedPanel)this.tabPnl).addCurrentTabListener((PropertyChangeListener)new SymPropertyChange());
        this.replytoTxt.addFocusListener(symFocus);
        this.attachList.addFocusListener(symFocus);
        this.addComponentListener(new SymComponent());
        this.newMen.addActionListener(symAction);
        this.sendMen.addActionListener(symAction);
        this.closeMen.addActionListener(symAction);
        this.spellMen.addActionListener(symAction);
        this.addressMen.addActionListener(symAction);
        this.attachMen.addActionListener(symAction);
        this.newMen.setShortcut(new MenuShortcut(78, false));
        this.sendMen.setShortcut(new MenuShortcut(83, false));
        this.addressMen.setShortcut(new MenuShortcut(66, false));
        if (this.props.allowAttachments) {
            this.attachMen.setShortcut(new MenuShortcut(65, false));
        }
        try {
            this.tabPnl.setCurrentPanelNdx(0);
        }
        catch (PropertyVetoException ex19) {}
        this.messageTxt.setFont(this.props.messageFont);
        this.messageTxt.setForeground(Color.black);
        this.lastSelected = this.toTxt;
        if (!this.props.toolbars) {
            ((Component)this.toolBarPanel1).setVisible(false);
        }
        else {
            ((Component)this.toolBarPanel1).setVisible(true);
        }
        this.placement = this.props.toolbarSide;
        this.buttonSize = this.props.buttonSize;
        this.fromAddress = this.props.fromAddress;
        this.connection = new JSConnection(this.props.smtpHost, this.props.username, this.props.password);
        this.smtpMbox = new JSSMTPMailbox();
        final String replyTo = this.props.replyTo;
        if (!replyTo.equalsIgnoreCase("none")) {
            this.replytoTxt.setText(replyTo);
        }
        if (!this.props.signature.equals("")) {
            this.messageTxt.setText("\n\n" + this.props.signature);
        }
        if (!this.props.connected) {
            this.disableGui();
        }
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
    
    void sendFrame_WindowClosing(final WindowEvent windowEvent) {
        final String text = this.messageTxt.getText();
        if (text.equals("") || text.equals("\n\n" + this.props.signature)) {
            this.hide();
            this.props.removeWindow(this);
            return;
        }
        final ConfirmDlg confirmDlg = new ConfirmDlg(this, "Discard message?", this.props.language.getProperty("prompt.close_new", "Are you sure you want to close this message? The text that you have entered will be discarded."), null);
        confirmDlg.setBackground(this.props.background);
        confirmDlg.setForeground(this.props.foreground);
        confirmDlg.setFont(this.props.font);
        confirmDlg.show();
    }
    
    void attBtn_actionPerformed(final ActionEvent actionEvent) {
        try {
            this.tabPnl.setCurrentPanelNdx(2);
        }
        catch (PropertyVetoException ex) {}
        this.attachFile();
    }
    
    void subjectTxt_KeyRelease(final KeyEvent keyEvent) {
        this.setTitle(this.subjectTxt.getText());
    }
    
    void sendBtn_actionPerformed(final ActionEvent actionEvent) {
        if (!this.props.connected) {
            final ErrorDlg errorDlg = new ErrorDlg(this, "ERROR!", this.props.language.getProperty("error.disconnected_send", "You cannot send messages when disconnected. To send this message, restore the server connection."), true);
            errorDlg.setBackground(this.props.background);
            errorDlg.setForeground(this.props.foreground);
            errorDlg.setFont(this.props.font);
            errorDlg.show();
            return;
        }
        this.setCursor(Cursor.getPredefinedCursor(3));
        (this.message = new JSMessage()).setToAddress(this.convertToCommas(this.toTxt.getText()));
        final String text = this.ccTxt.getText();
        if (!text.equals("")) {
            this.message.setCCAddress(this.convertToCommas(text));
        }
        final String text2 = this.bccTxt.getText();
        if (!text2.equals("")) {
            this.message.setBCCAddress(this.convertToCommas(text2));
        }
        final String text3 = this.replytoTxt.getText();
        if (!text3.equals("")) {
            this.message.setReplyToAddress(text3);
        }
        this.message.setFromAddress(this.fromAddress);
        this.message.setSubject(this.subjectTxt.getText());
        this.message.setDate(new RFC822Date().toString());
        this.message.setMessageText(this.getParsedBody(this.messageTxt.getText()));
        this.message.setHeaderElement("X-Mailer", "MochaMail 1.0");
        final int itemCount = this.attachList.getItemCount();
        if (itemCount > 0) {
            this.message.setMIMEBoundary("==--------MML" + new Date().getTime());
            for (int i = 0; i < itemCount; ++i) {
                final String item = this.attachList.getItem(i);
                try {
                    final FileInputStream fileInputStream = new FileInputStream(new File(item));
                    final byte[] array = new byte[fileInputStream.available()];
                    fileInputStream.read(array);
                    this.message.setAttachment(item, new String(array));
                }
                catch (OutOfMemoryError outOfMemoryError) {
                    System.out.println("MEMORY ERROR: " + outOfMemoryError.toString());
                    final ErrorDlg errorDlg2 = new ErrorDlg(this, "Out of memory", this.props.language.getProperty("error.send_memory", "There is not enough memory available to send this message. Try removing one or more attachments and resending."), true);
                    errorDlg2.setBackground(this.props.background);
                    errorDlg2.setForeground(this.props.foreground);
                    errorDlg2.setFont(this.props.font);
                    errorDlg2.show();
                    this.setCursor(Cursor.getPredefinedCursor(0));
                    return;
                }
                catch (Exception ex4) {}
            }
        }
        int receiptType = -1;
        int receiptCondition = -1;
        final boolean state = this.receiptChk.getState();
        if (state) {
            if (this.typeChc.getSelectedIndex() == 0) {
                receiptType = 1;
            }
            else {
                receiptType = 0;
            }
            final int selectedIndex = this.receiptChc.getSelectedIndex();
            if (selectedIndex == 0) {
                receiptCondition = 2;
            }
            else if (selectedIndex == 1) {
                receiptCondition = 8;
            }
            else if (selectedIndex == 2) {
                receiptCondition = 4;
            }
            else {
                receiptCondition = 1;
            }
        }
        if (this.props.serverSend) {
            String s = null;
            try {
                boolean confirm = false;
                try {
                    confirm = this.props.server.confirm();
                }
                catch (Exception ex5) {}
                if (!confirm) {
                    this.props.confirmConnection();
                }
                if (state) {
                    s = this.props.server.sendMail(this.message, this.fromAddress, receiptType, receiptCondition);
                }
                else {
                    s = this.props.server.sendMail(this.message, this.fromAddress);
                }
                if (!confirm) {
                    this.props.closeMailbox();
                }
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
            if (s == null) {
                final ErrorDlg errorDlg3 = new ErrorDlg(this, "Error!", this.props.language.getProperty("error.sending", "An error occured while trying to send the message. Check the destination address(es) for possible syntax errors."), true);
                errorDlg3.setBackground(this.props.background);
                errorDlg3.setForeground(this.props.foreground);
                errorDlg3.setFont(this.props.font);
                errorDlg3.show();
                this.setCursor(Cursor.getPredefinedCursor(0));
                return;
            }
            if (s.toLowerCase().startsWith("error")) {
                final ErrorDlg errorDlg4 = new ErrorDlg(this, "Error!", s, true);
                errorDlg4.setBackground(this.props.background);
                errorDlg4.setForeground(this.props.foreground);
                errorDlg4.setFont(this.props.font);
                errorDlg4.show();
                this.setCursor(Cursor.getPredefinedCursor(0));
                return;
            }
            if (s.equals("success")) {
                this.setCursor(Cursor.getPredefinedCursor(0));
                this.setVisible(false);
                this.props.removeWindow(this);
                this.saveProps();
            }
        }
        else {
            try {
                this.smtpMbox.connect(this.connection);
                this.smtpMbox.setDebugMode(this.DEBUG);
                if (state) {
                    this.smtpMbox.setReceiptType(receiptType);
                    this.smtpMbox.setReceiptCondition(receiptCondition);
                }
                try {
                    final Vector toAddressVector = this.message.getToAddressVector();
                    final Vector ccAddressVector = this.message.getCCAddressVector();
                    final Vector bccAddressVector = this.message.getBCCAddressVector();
                    final Vector vector = new Vector<String>();
                    if (toAddressVector != null) {
                        for (int j = 0; j < toAddressVector.size(); ++j) {
                            vector.addElement(toAddressVector.elementAt(j));
                        }
                    }
                    if (ccAddressVector != null) {
                        for (int k = 0; k < ccAddressVector.size(); ++k) {
                            vector.addElement(ccAddressVector.elementAt(k));
                        }
                    }
                    if (bccAddressVector != null) {
                        for (int l = 0; l < bccAddressVector.size(); ++l) {
                            vector.addElement(bccAddressVector.elementAt(l));
                        }
                    }
                    this.smtpMbox.sendServerLine("MAIL FROM: <" + this.fromAddress + ">");
                    String s2 = this.smtpMbox.readServerLine();
                    for (int n = 0, size = vector.size(); !s2.startsWith("4") && !s2.startsWith("5") && n < size; s2 = this.smtpMbox.readServerLine(), ++n) {
                        this.smtpMbox.sendServerLine("RCPT TO: <" + vector.elementAt(n) + ">");
                    }
                    this.smtpMbox.sendServerLine("RSET");
                    this.smtpMbox.readServerLine();
                    if (s2.startsWith("4") || s2.startsWith("5")) {
                        final ErrorDlg errorDlg5 = new ErrorDlg(this, "Error!", "Error: " + s2, true);
                        errorDlg5.setBackground(this.props.background);
                        errorDlg5.setForeground(this.props.foreground);
                        errorDlg5.setFont(this.props.font);
                        errorDlg5.show();
                        this.setCursor(Cursor.getPredefinedCursor(0));
                        return;
                    }
                }
                catch (Exception ex6) {}
                try {
                    this.smtpMbox.sendMail(this.message);
                    this.smtpMbox.disconnect();
                    this.setCursor(Cursor.getPredefinedCursor(0));
                    this.setVisible(false);
                    this.props.removeWindow(this);
                    this.saveProps();
                }
                catch (JSServerCommandException ex2) {
                    System.out.println("JSX ERROR: " + ((Throwable)ex2).toString());
                    final ErrorDlg errorDlg6 = new ErrorDlg(this, "Error!", this.props.language.getProperty("error.sending", "An error occured while trying to send the message. Check the destination address(es) for possible syntax errors."), true);
                    errorDlg6.setBackground(this.props.background);
                    errorDlg6.setForeground(this.props.foreground);
                    errorDlg6.setFont(this.props.font);
                    errorDlg6.show();
                    this.setCursor(Cursor.getPredefinedCursor(0));
                }
                catch (IOException ex3) {
                    System.out.println("IOX ERROR: " + ex3.toString());
                    final ErrorDlg errorDlg7 = new ErrorDlg(this, "Error!", this.props.language.getProperty("error.sending", "An error occured while trying to send the message. Check the destination address(es) for possible syntax errors."), true);
                    errorDlg7.setBackground(this.props.background);
                    errorDlg7.setForeground(this.props.foreground);
                    errorDlg7.setFont(this.props.font);
                    errorDlg7.show();
                    this.setCursor(Cursor.getPredefinedCursor(0));
                }
                catch (OutOfMemoryError outOfMemoryError2) {
                    System.out.println("MEMORY ERROR: " + outOfMemoryError2.toString());
                    final ErrorDlg errorDlg8 = new ErrorDlg(this, "Out of memory", this.props.language.getProperty("error.send_memory", "There is not enough memory available to send this message. Try removing one or more attachments and resending."), true);
                    errorDlg8.setBackground(this.props.background);
                    errorDlg8.setForeground(this.props.foreground);
                    errorDlg8.setFont(this.props.font);
                    errorDlg8.show();
                    this.setCursor(Cursor.getPredefinedCursor(0));
                }
            }
            catch (Exception ex7) {
                final ErrorDlg errorDlg9 = new ErrorDlg(this, "Error!", this.props.language.getProperty("error.no_connect", "Could not establish a connection with the mail server. This may be a temporary problem. Contact your service provider if the problem continues."), true);
                errorDlg9.setBackground(this.props.background);
                errorDlg9.setForeground(this.props.foreground);
                errorDlg9.setFont(this.props.font);
                errorDlg9.show();
                this.setCursor(Cursor.getPredefinedCursor(0));
            }
        }
    }
    
    void newBtn_actionPerformed(final ActionEvent actionEvent) {
        final sendFrame sendFrame = new sendFrame(this.props);
        final Point location = this.getLocation();
        location.x += 10;
        location.y += 10;
        sendFrame.move(location.x, location.y);
        sendFrame.show();
    }
    
    public void setData(final String text, final String text2, final String s, final StringBuffer sb) {
        this.toTxt.setText(text);
        this.ccTxt.setText(text2);
        this.subjectTxt.setText(s);
        if (sb != null) {
            this.messageTxt.setText(sb.toString());
        }
        else {
            this.messageTxt.setText("");
        }
        if (!this.props.signature.equals("")) {
            this.messageTxt.append("\n\n" + this.props.signature);
        }
        this.setTitle(s);
    }
    
    void receiptChk_ItemStateChanged(final ItemEvent itemEvent) {
        this.panel1.setEnabled(!this.panel1.isEnabled());
        this.typeChc.setEnabled(!this.typeChc.isEnabled());
        this.receiptChc.setEnabled(!this.receiptChc.isEnabled());
    }
    
    void addressBtn_actionPerformed(final ActionEvent actionEvent) {
        this.props.aBook.show(this.lastSelected);
    }
    
    void toLbl_actionPerformed(final ActionEvent actionEvent) {
        this.lastSelected = this.toTxt;
        this.addressBtn_actionPerformed(actionEvent);
    }
    
    void ccLbl_actionPerformed(final ActionEvent actionEvent) {
        this.lastSelected = this.ccTxt;
        this.addressBtn_actionPerformed(actionEvent);
    }
    
    void bccLbl_actionPerformed(final ActionEvent actionEvent) {
        this.lastSelected = this.bccTxt;
        this.addressBtn_actionPerformed(actionEvent);
    }
    
    void ccTxt_GotFocus(final FocusEvent focusEvent) {
        this.lastSelected = this.ccTxt;
    }
    
    void toTxt_GotFocus(final FocusEvent focusEvent) {
        this.lastSelected = this.toTxt;
    }
    
    void bccTxt_GotFocus(final FocusEvent focusEvent) {
        this.lastSelected = this.bccTxt;
    }
    
    void messageTxt_GotFocus(final FocusEvent focusEvent) {
        this.lastSelected = this.toTxt;
    }
    
    void subjectTxt_GotFocus(final FocusEvent focusEvent) {
        this.lastSelected = this.toTxt;
    }
    
    void replytoTxt_GotFocus(final FocusEvent focusEvent) {
        this.lastSelected = this.toTxt;
    }
    
    void attachList_GotFocus(final FocusEvent focusEvent) {
        this.lastSelected = this.toTxt;
    }
    
    public void attachFile() {
        try {
            final String filePathDialog;
            if ((filePathDialog = JSFileHandler.getFilePathDialog()) != null) {
                this.attachList.addItem(filePathDialog);
            }
        }
        catch (SecurityException ex) {
            System.out.println("READ ERROR: " + ex.toString());
            try {
                this.props.parentApp.getAppletContext().showDocument(new URL(this.props.base, "client/trust.html"), "_blank");
            }
            catch (Exception ex3) {
                final NotHereFrame notHereFrame = new NotHereFrame("Trust required", "The attachment could not be loaded, because MochaMail does not have permission to read from your disk drive.");
                notHereFrame.setBackground(this.props.background);
                notHereFrame.setForeground(this.props.foreground);
                notHereFrame.setFont(this.props.font);
                notHereFrame.show();
            }
        }
        catch (Exception ex2) {
            System.out.println("READ ERROR: " + ex2.toString());
        }
    }
    
    void addBtn_Action(final ActionEvent actionEvent) {
        this.attachFile();
    }
    
    void remBtn_Action(final ActionEvent actionEvent) {
        this.attachList.delItem(this.attachList.getSelectedIndex());
    }
    
    String getParsedBody(final String s) {
        final String s2 = new String(s);
        final int length;
        final int n = length = s2.length();
        int i = 0;
        final char[] array = new char[n];
        s2.getChars(0, length, array, 0);
        while (i < length - 76) {
            final String s3 = new String(array, i, 76);
            final int index = s3.indexOf(10);
            if (index != -1) {
                i = i + index + 1;
            }
            else {
                final int n2 = i + s3.lastIndexOf(32);
                array[n2] = '\n';
                i = n2 + 1;
            }
        }
        String s4 = new String(array);
        if (s4.startsWith(".\n")) {
            s4 = "." + s4;
        }
        if (s4.endsWith("\n.")) {
            s4 = String.valueOf(s4) + ".";
        }
        for (int j = s4.indexOf("\n.\n"); j != -1; j = s4.indexOf("\n.\n")) {
            s4 = String.valueOf(s4.substring(0, j)) + "\n..\n" + s4.substring(j + 3);
        }
        return s4;
    }
    
    void spellBtn_actionPerformed(final ActionEvent actionEvent) {
        try {
            this.tabPnl.setCurrentPanelNdx(0);
        }
        catch (PropertyVetoException ex) {}
        final String hostName = this.connection.getHostName();
        SpellEngine.initChecker();
        SpellEngine.port = 5317;
        SpellEngine.host = hostName;
        SpellEngine.setProgPos(new Point(this.location().x + 30, this.location().y + 30));
        SpellEngine.setBatchSize(25);
        SpellEngine.check(this.messageTxt);
    }
    
    void helpBtn_actionPerformed(final ActionEvent actionEvent) {
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
    
    void sendBtn_focusGained(final FocusEvent focusEvent) {
        ((Component)this.sendBtn).transferFocus();
    }
    
    void newBtn_focusGained(final FocusEvent focusEvent) {
        ((Component)this.newBtn).transferFocus();
    }
    
    void attBtn_focusGained(final FocusEvent focusEvent) {
        ((Component)this.attBtn).transferFocus();
    }
    
    void addressBtn_focusGained(final FocusEvent focusEvent) {
        if (this.lastSelected == this.ccTxt) {
            this.ccTxt.requestFocus();
            return;
        }
        if (this.lastSelected == this.bccTxt) {
            this.bccTxt.requestFocus();
            return;
        }
        ((Component)this.addressBtn).transferFocus();
    }
    
    void spellBtn_focusGained(final FocusEvent focusEvent) {
        ((Component)this.spellBtn).transferFocus();
    }
    
    void helpBtn_focusGained(final FocusEvent focusEvent) {
        ((Component)this.helpBtn).transferFocus();
    }
    
    void closeMen_event() {
        final String text = this.messageTxt.getText();
        if (text.equals("") || text.equals("\n\n" + this.props.signature)) {
            this.hide();
            this.props.removeWindow(this);
            return;
        }
        final ConfirmDlg confirmDlg = new ConfirmDlg(this, "Discard message?", "Are you sure you want to close this message? The text that you have entered will be discarded.", null);
        confirmDlg.setBackground(this.props.background);
        confirmDlg.setForeground(this.props.foreground);
        confirmDlg.setFont(this.props.font);
        confirmDlg.show();
    }
    
    void tabPnl_propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        this.lastSelected = this.toTxt;
    }
    
    void sendFrame_ComponentResized(final ComponentEvent componentEvent) {
        this.messageTxt.setFont(this.props.messageFont);
        if (!this.props.toolbars) {
            ((Component)this.toolBarPanel1).setVisible(false);
        }
        else {
            ((Component)this.toolBarPanel1).setVisible(true);
        }
        if (this.buttonSize != this.props.buttonSize) {
            this.remove(this.toolPnl);
            this.toolPnl.remove((Component)this.toolBarPanel1);
            this.toolBarPanel1 = new ToolBarPanel();
            try {
                ((BorderPanel)this.toolBarPanel1).setIPadBottom(0);
                ((BorderPanel)this.toolBarPanel1).setIPadSides(0);
                ((BorderPanel)this.toolBarPanel1).setIPadTop(0);
            }
            catch (PropertyVetoException ex) {}
            this.toolBarPanel1.setLayout((LayoutManager)new FlowLayout(0, 0, 0));
            ((BorderPanel)this.toolBarPanel1).setBounds(0, 0, this.props.buttonSize, this.props.buttonSize);
            this.toolPnl.add("Center", (Component)this.toolBarPanel1);
            try {
                URL imageURL;
                if (this.props.buttonSize == 28) {
                    imageURL = new URL(this.props.base, "client/images/send24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL = new URL(this.props.base, "client/images/send76.gif");
                }
                else {
                    imageURL = new URL(this.props.base, "client/images/send.gif");
                }
                this.sendBtn.setImageURL(imageURL);
            }
            catch (MalformedURLException ex2) {}
            catch (PropertyVetoException ex3) {}
            ((Component)this.sendBtn).setBounds(0, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.sendBtn);
            try {
                URL imageURL2;
                if (this.props.buttonSize == 28) {
                    imageURL2 = new URL(this.props.base, "client/images/new24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL2 = new URL(this.props.base, "client/images/new76.gif");
                }
                else {
                    imageURL2 = new URL(this.props.base, "client/images/new.gif");
                }
                this.newBtn.setImageURL(imageURL2);
            }
            catch (MalformedURLException ex4) {}
            catch (PropertyVetoException ex5) {}
            ((Component)this.newBtn).setBounds(46, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.newBtn);
            try {
                URL imageURL3;
                if (this.props.buttonSize == 28) {
                    imageURL3 = new URL(this.props.base, "client/images/attach24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL3 = new URL(this.props.base, "client/images/attach76.gif");
                }
                else {
                    imageURL3 = new URL(this.props.base, "client/images/attach.gif");
                }
                this.attBtn.setImageURL(imageURL3);
            }
            catch (MalformedURLException ex6) {}
            catch (PropertyVetoException ex7) {}
            ((Component)this.attBtn).setBounds(92, 0, 46, 46);
            if (this.props.allowAttachments) {
                ((Container)this.toolBarPanel1).add((Component)this.attBtn);
            }
            try {
                URL imageURL4;
                if (this.props.buttonSize == 28) {
                    imageURL4 = new URL(this.props.base, "client/images/address24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL4 = new URL(this.props.base, "client/images/address76.gif");
                }
                else {
                    imageURL4 = new URL(this.props.base, "client/images/address.gif");
                }
                this.addressBtn.setImageURL(imageURL4);
            }
            catch (MalformedURLException ex8) {}
            catch (PropertyVetoException ex9) {}
            ((Component)this.addressBtn).setBounds(138, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.addressBtn);
            try {
                URL imageURL5;
                if (this.props.buttonSize == 28) {
                    imageURL5 = new URL(this.props.base, "client/images/spell24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL5 = new URL(this.props.base, "client/images/spell76.gif");
                }
                else {
                    imageURL5 = new URL(this.props.base, "client/images/spell.gif");
                }
                this.spellBtn.setImageURL(imageURL5);
            }
            catch (MalformedURLException ex10) {}
            catch (PropertyVetoException ex11) {}
            ((Component)this.spellBtn).setBounds(184, 0, 46, 46);
            if (!this.props.proxyMode) {
                ((Container)this.toolBarPanel1).add((Component)this.spellBtn);
            }
            try {
                URL imageURL6;
                if (this.props.buttonSize == 28) {
                    imageURL6 = new URL(this.props.base, "client/images/help24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL6 = new URL(this.props.base, "client/images/help76.gif");
                }
                else {
                    imageURL6 = new URL(this.props.base, "client/images/help.gif");
                }
                this.helpBtn.setImageURL(imageURL6);
            }
            catch (MalformedURLException ex12) {}
            catch (PropertyVetoException ex13) {}
            ((Component)this.helpBtn).setBounds(230, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.helpBtn);
            this.add(this.props.toolbarSide, this.toolPnl);
            this.placement = this.props.toolbarSide;
            this.buttonSize = this.props.buttonSize;
            return;
        }
        if (!this.placement.equalsIgnoreCase(this.props.toolbarSide)) {
            this.remove(this.toolPnl);
            ((BorderPanel)this.toolBarPanel1).setBounds(0, 0, this.props.buttonSize, this.props.buttonSize);
            this.add(this.props.toolbarSide, this.toolPnl);
            this.placement = this.props.toolbarSide;
        }
    }
    
    public void removeFromProps() {
        this.hide();
        this.props.removeWindow(this);
    }
    
    String convertToCommas(final String s) {
        if (s.indexOf(59) == -1) {
            return s;
        }
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            if (charArray[i] == ';') {
                charArray[i] = ',';
            }
        }
        return new String(charArray);
    }
    
    void disableGui() {
        ((ButtonBase)this.spellBtn).setEnabled(false);
        this.spellMen.setEnabled(false);
    }
    
    void saveProps() {
        final Dimension size = this.size();
        this.props.newWidth = size.width - 8;
        if (this.props.NETSCAPE) {
            this.props.newHeight = size.height - 64;
        }
        else {
            this.props.newHeight = size.height - 46;
        }
        final Point location = this.location();
        this.props.newPosX = location.x;
        this.props.newPosY = location.y;
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == sendFrame.this) {
                sendFrame.this.sendFrame_WindowClosing(windowEvent);
            }
        }
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == sendFrame.this.attBtn) {
                sendFrame.this.attBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == sendFrame.this.sendBtn) {
                sendFrame.this.sendBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == sendFrame.this.newBtn) {
                sendFrame.this.newBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == sendFrame.this.addressBtn) {
                sendFrame.this.addressBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == sendFrame.this.addBtn) {
                sendFrame.this.addBtn_Action(actionEvent);
                return;
            }
            if (source == sendFrame.this.remBtn) {
                sendFrame.this.remBtn_Action(actionEvent);
                return;
            }
            if (source == sendFrame.this.spellBtn) {
                sendFrame.this.spellBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == sendFrame.this.helpBtn) {
                sendFrame.this.helpBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == sendFrame.this.newMen) {
                sendFrame.this.newBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == sendFrame.this.sendMen) {
                sendFrame.this.sendBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == sendFrame.this.closeMen) {
                sendFrame.this.closeMen_event();
                return;
            }
            if (source == sendFrame.this.spellMen) {
                sendFrame.this.spellBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == sendFrame.this.addressMen) {
                sendFrame.this.addressBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == sendFrame.this.attachMen) {
                sendFrame.this.attBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == sendFrame.this.toLbl) {
                sendFrame.this.toLbl_actionPerformed(actionEvent);
                return;
            }
            if (source == sendFrame.this.ccLbl) {
                sendFrame.this.ccLbl_actionPerformed(actionEvent);
                return;
            }
            if (source == sendFrame.this.bccLbl) {
                sendFrame.this.bccLbl_actionPerformed(actionEvent);
            }
        }
    }
    
    class SymKey extends KeyAdapter
    {
        public void keyReleased(final KeyEvent keyEvent) {
            if (keyEvent.getSource() == sendFrame.this.subjectTxt) {
                sendFrame.this.subjectTxt_KeyRelease(keyEvent);
            }
        }
    }
    
    class SymItem implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            if (itemEvent.getSource() == sendFrame.this.receiptChk) {
                sendFrame.this.receiptChk_ItemStateChanged(itemEvent);
            }
        }
    }
    
    class SymFocus extends FocusAdapter
    {
        public void focusGained(final FocusEvent focusEvent) {
            final Object source = focusEvent.getSource();
            if (source == sendFrame.this.ccTxt) {
                sendFrame.this.ccTxt_GotFocus(focusEvent);
                return;
            }
            if (source == sendFrame.this.toTxt) {
                sendFrame.this.toTxt_GotFocus(focusEvent);
                return;
            }
            if (source == sendFrame.this.messageTxt) {
                sendFrame.this.messageTxt_GotFocus(focusEvent);
                return;
            }
            if (source == sendFrame.this.subjectTxt) {
                sendFrame.this.subjectTxt_GotFocus(focusEvent);
                return;
            }
            if (source == sendFrame.this.sendBtn) {
                sendFrame.this.sendBtn_focusGained(focusEvent);
                return;
            }
            if (source == sendFrame.this.newBtn) {
                sendFrame.this.newBtn_focusGained(focusEvent);
                return;
            }
            if (source == sendFrame.this.attBtn) {
                sendFrame.this.attBtn_focusGained(focusEvent);
                return;
            }
            if (source == sendFrame.this.addressBtn) {
                sendFrame.this.addressBtn_focusGained(focusEvent);
                return;
            }
            if (source == sendFrame.this.spellBtn) {
                sendFrame.this.spellBtn_focusGained(focusEvent);
                return;
            }
            if (source == sendFrame.this.helpBtn) {
                sendFrame.this.helpBtn_focusGained(focusEvent);
                return;
            }
            if (source == sendFrame.this.bccTxt) {
                sendFrame.this.bccTxt_GotFocus(focusEvent);
                return;
            }
            if (source == sendFrame.this.replytoTxt) {
                sendFrame.this.replytoTxt_GotFocus(focusEvent);
                return;
            }
            if (source == sendFrame.this.attachList) {
                sendFrame.this.attachList_GotFocus(focusEvent);
            }
        }
    }
    
    class SymPropertyChange implements PropertyChangeListener
    {
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            if (propertyChangeEvent.getSource() == sendFrame.this.tabPnl) {
                sendFrame.this.tabPnl_propertyChange(propertyChangeEvent);
            }
        }
    }
    
    class SymComponent extends ComponentAdapter
    {
        public void componentResized(final ComponentEvent componentEvent) {
            if (componentEvent.getSource() == sendFrame.this) {
                sendFrame.this.sendFrame_ComponentResized(componentEvent);
            }
        }
    }
}
