import symantec.itools.awt.ButtonBase;
import java.awt.event.KeyAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.AdjustmentEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.awt.Container;
import java.util.Date;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyListener;
import java.awt.event.FocusListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.MenuShortcut;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.CardLayout;
import java.beans.PropertyVetoException;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.applet.AppletContext;
import java.awt.FileDialog;
import java.awt.Scrollbar;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.TextArea;
import symantec.itools.awt.BorderPanel;
import symantec.itools.awt.MultiList;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;
import symantec.itools.awt.ImageButton;
import symantec.itools.awt.util.ToolBarPanel;
import java.awt.Panel;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class msgFrame extends Frame
{
    static int STRIKES;
    char SEP;
    boolean fComponentsAdjusted;
    Panel northPnl;
    ToolBarPanel toolBarPanel1;
    ImageButton newBtn;
    ImageButton delBtn;
    ImageButton replyBtn;
    ImageButton fwdBtn;
    ImageButton addressBtn;
    ImageButton nextBtn;
    ImageButton prevBtn;
    ImageButton helpBtn;
    Panel centerPnl;
    Panel headersPnl;
    Panel labelsPnl;
    Panel htmlMessagePnl;
    Panel textMessagePnl;
    Button fromLbl;
    Label dateLbl;
    Label subjectLbl;
    Panel dataPnl;
    TextField fromTxt;
    TextField dateTxt;
    TextField subjectTxt;
    Panel attachPnl;
    MultiList attachList;
    BorderPanel messagePnl;
    TextArea messageTxt;
    MenuBar menuBar2;
    Menu menu1;
    MenuItem menuItem1;
    MenuItem saveMen;
    MenuItem closeMen;
    Menu menu2;
    MenuItem cutMen;
    MenuItem copyMen;
    MenuItem pasteMen;
    MenuItem clearMen;
    MenuItem selectMen;
    Menu menu3;
    MenuItem newMen;
    MenuItem deleteMen;
    MenuItem replyMen;
    MenuItem replyallMen;
    MenuItem forwardMen;
    MenuItem nextMen;
    MenuItem prevMen;
    Scrollbar scrollBar;
    FileDialog saveFileDialog1;
    String fromAddress;
    JMessage message;
    POP3mboxFrame popMboxFrame;
    AddressBookFrame addressBook;
    int mNum;
    int index;
    String uidl;
    AppletContext browser;
    HtmlCanvas canvas;
    HtmlDocument doc;
    JMProps props;
    long lastMouseClick;
    String placement;
    int buttonSize;
    
    public msgFrame(final JMProps props) {
        this.SEP = File.separatorChar;
        this.fComponentsAdjusted = false;
        this.props = props;
        this.setBackground(this.props.background);
        this.setForeground(this.props.foreground);
        this.setFont(this.props.font);
        this.props.addWindow(this);
        final Point location = new Point(this.props.messagePosX, this.props.messagePosY);
        this.setSize(new Dimension(this.props.messageWidth, this.props.messageHeight));
        this.setLocation(location);
        this.setLayout(new BorderLayout(5, 5));
        this.setVisible(false);
        (this.centerPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.centerPnl.setBounds(this.insets().left, this.insets().top + 45, 450, 385);
        this.add("Center", this.centerPnl);
        this.messagePnl = new BorderPanel();
        try {
            this.messagePnl.setIPadBottom(1);
        }
        catch (PropertyVetoException ex) {}
        try {
            this.messagePnl.setIPadSides(0);
        }
        catch (PropertyVetoException ex2) {}
        try {
            this.messagePnl.setPaddingRight(0);
        }
        catch (PropertyVetoException ex3) {}
        try {
            this.messagePnl.setPaddingBottom(0);
        }
        catch (PropertyVetoException ex4) {}
        try {
            this.messagePnl.setPaddingTop(5);
        }
        catch (PropertyVetoException ex5) {}
        try {
            this.messagePnl.setIPadTop(1);
        }
        catch (PropertyVetoException ex6) {}
        try {
            this.messagePnl.setPaddingLeft(0);
        }
        catch (PropertyVetoException ex7) {}
        this.messagePnl.setLayout((LayoutManager)new CardLayout(0, 0));
        this.messagePnl.setBounds(this.insets().left, this.insets().top, 20, 40);
        this.centerPnl.add("Center", (Component)this.messagePnl);
        (this.textMessagePnl = new Panel()).setLayout(new BorderLayout(0, 0));
        ((Container)this.messagePnl).add("textCard", this.textMessagePnl);
        (this.htmlMessagePnl = new Panel()).setLayout(new BorderLayout(0, 0));
        ((Container)this.messagePnl).add("htmlCard", this.htmlMessagePnl);
        (this.messageTxt = new TextArea("", 0, 0, 1)).setEditable(false);
        this.messageTxt.setBounds(0, 0, 479, 192);
        this.messageTxt.setFont(this.props.messageFont);
        this.messageTxt.setBackground(new Color(16777215));
        this.messageTxt.setForeground(Color.black);
        this.textMessagePnl.add("Center", this.messageTxt);
        (this.northPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.northPnl.setBounds(this.insets().left, this.insets().top, 479, 0);
        this.centerPnl.add("North", this.northPnl);
        this.toolBarPanel1 = new ToolBarPanel();
        try {
            ((BorderPanel)this.toolBarPanel1).setIPadBottom(0);
        }
        catch (PropertyVetoException ex8) {}
        try {
            ((BorderPanel)this.toolBarPanel1).setIPadSides(0);
        }
        catch (PropertyVetoException ex9) {}
        try {
            ((BorderPanel)this.toolBarPanel1).setIPadTop(0);
        }
        catch (PropertyVetoException ex10) {}
        this.toolBarPanel1.setLayout((LayoutManager)new FlowLayout(0, 0, 0));
        ((BorderPanel)this.toolBarPanel1).setBounds(0, 0, this.props.buttonSize, this.props.buttonSize);
        this.add(this.props.toolbarSide, (Component)this.toolBarPanel1);
        this.newBtn = new ImageButton();
        try {
            URL imageURL;
            if (this.props.buttonSize == 28) {
                imageURL = new URL(this.props.base, "client/images/new24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL = new URL(this.props.base, "client/images/new76.gif");
            }
            else {
                imageURL = new URL(this.props.base, "client/images/new.gif");
            }
            this.newBtn.setImageURL(imageURL);
        }
        catch (MalformedURLException ex11) {}
        catch (PropertyVetoException ex12) {}
        ((Component)this.newBtn).setBounds(0, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.newBtn);
        this.delBtn = new ImageButton();
        try {
            URL imageURL2;
            if (this.props.buttonSize == 28) {
                imageURL2 = new URL(this.props.base, "client/images/delete24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL2 = new URL(this.props.base, "client/images/delete76.gif");
            }
            else {
                imageURL2 = new URL(this.props.base, "client/images/delete.gif");
            }
            this.delBtn.setImageURL(imageURL2);
        }
        catch (MalformedURLException ex13) {}
        catch (PropertyVetoException ex14) {}
        ((Component)this.delBtn).setBounds(46, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.delBtn);
        this.replyBtn = new ImageButton();
        try {
            URL imageURL3;
            if (this.props.buttonSize == 28) {
                imageURL3 = new URL(this.props.base, "client/images/reply24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL3 = new URL(this.props.base, "client/images/reply76.gif");
            }
            else {
                imageURL3 = new URL(this.props.base, "client/images/reply.gif");
            }
            this.replyBtn.setImageURL(imageURL3);
        }
        catch (MalformedURLException ex15) {}
        catch (PropertyVetoException ex16) {}
        ((Component)this.replyBtn).setBounds(92, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.replyBtn);
        this.fwdBtn = new ImageButton();
        try {
            URL imageURL4;
            if (this.props.buttonSize == 28) {
                imageURL4 = new URL(this.props.base, "client/images/forward24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL4 = new URL(this.props.base, "client/images/forward76.gif");
            }
            else {
                imageURL4 = new URL(this.props.base, "client/images/forward.gif");
            }
            this.fwdBtn.setImageURL(imageURL4);
        }
        catch (MalformedURLException ex17) {}
        catch (PropertyVetoException ex18) {}
        ((Component)this.fwdBtn).setBounds(138, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.fwdBtn);
        this.addressBtn = new ImageButton();
        try {
            URL imageURL5;
            if (this.props.buttonSize == 28) {
                imageURL5 = new URL(this.props.base, "client/images/address24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL5 = new URL(this.props.base, "client/images/address76.gif");
            }
            else {
                imageURL5 = new URL(this.props.base, "client/images/address.gif");
            }
            this.addressBtn.setImageURL(imageURL5);
        }
        catch (MalformedURLException ex19) {}
        catch (PropertyVetoException ex20) {}
        ((Component)this.addressBtn).setBounds(184, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.addressBtn);
        this.nextBtn = new ImageButton();
        try {
            URL imageURL6;
            if (this.props.buttonSize == 28) {
                imageURL6 = new URL(this.props.base, "client/images/next24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL6 = new URL(this.props.base, "client/images/next76.gif");
            }
            else {
                imageURL6 = new URL(this.props.base, "client/images/next.gif");
            }
            this.nextBtn.setImageURL(imageURL6);
        }
        catch (MalformedURLException ex21) {}
        catch (PropertyVetoException ex22) {}
        ((Component)this.nextBtn).setBounds(230, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.nextBtn);
        this.prevBtn = new ImageButton();
        try {
            URL imageURL7;
            if (this.props.buttonSize == 28) {
                imageURL7 = new URL(this.props.base, "client/images/previous24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL7 = new URL(this.props.base, "client/images/previous76.gif");
            }
            else {
                imageURL7 = new URL(this.props.base, "client/images/previous.gif");
            }
            this.prevBtn.setImageURL(imageURL7);
        }
        catch (MalformedURLException ex23) {}
        catch (PropertyVetoException ex24) {}
        ((Component)this.prevBtn).setBounds(276, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.prevBtn);
        this.helpBtn = new ImageButton();
        try {
            URL imageURL8;
            if (this.props.buttonSize == 28) {
                imageURL8 = new URL(this.props.base, "client/images/help24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL8 = new URL(this.props.base, "client/images/help76.gif");
            }
            else {
                imageURL8 = new URL(this.props.base, "client/images/help.gif");
            }
            this.helpBtn.setImageURL(imageURL8);
        }
        catch (MalformedURLException ex25) {}
        catch (PropertyVetoException ex26) {}
        ((Component)this.helpBtn).setBounds(322, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.helpBtn);
        (this.headersPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.headersPnl.setBounds(0, 48, 479, 10);
        this.northPnl.add("Center", this.headersPnl);
        (this.labelsPnl = new Panel()).setLayout(new GridLayout(3, 1, 0, 0));
        this.labelsPnl.setBounds(0, 0, 0, 10);
        this.headersPnl.add("West", this.labelsPnl);
        (this.fromLbl = new Button(this.props.language.getProperty("labels.from", "From:"))).setBackground(this.props.background);
        this.labelsPnl.add(this.fromLbl);
        (this.dateLbl = new Label(this.props.language.getProperty("labels.date", "Date:"), 1)).setBounds(0, 24, 48, 22);
        this.labelsPnl.add(this.dateLbl);
        (this.subjectLbl = new Label(this.props.language.getProperty("labels.subject", "Subject:"), 1)).setBounds(0, 48, 65, 24);
        this.labelsPnl.add(this.subjectLbl);
        (this.dataPnl = new Panel()).setLayout(new GridLayout(3, 1, 0, 0));
        this.dataPnl.setBounds(75, 0, 404, 66);
        this.headersPnl.add("Center", this.dataPnl);
        (this.fromTxt = new TextField("")).setEditable(false);
        this.fromTxt.setBounds(0, 0, 404, 24);
        this.dataPnl.add(this.fromTxt);
        (this.dateTxt = new TextField("")).setEditable(false);
        this.dateTxt.setBounds(0, 24, 404, 24);
        this.dataPnl.add(this.dateTxt);
        (this.subjectTxt = new TextField("")).setEditable(false);
        this.subjectTxt.setBounds(0, 48, 404, 24);
        this.dataPnl.add(this.subjectTxt);
        (this.attachPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.attachPnl.setVisible(false);
        this.attachPnl.setBounds(this.insets().left, this.insets().top + 312, 479, 125);
        this.centerPnl.add("South", this.attachPnl);
        (this.attachList = new MultiList()).setLayout((LayoutManager)null);
        try {
            this.attachList.setColumnSizes(new String[] { new String("25"), new String("330"), new String("75") });
        }
        catch (PropertyVetoException ex27) {}
        try {
            this.attachList.setAllowSorting(false);
        }
        catch (PropertyVetoException ex28) {}
        try {
            this.attachList.setHeadings(new String[] { new String(" "), this.props.language.getProperty("column.attachment", "Attachment"), this.props.language.getProperty("column.size", "Size") });
        }
        catch (PropertyVetoException ex29) {}
        ((Component)this.attachList).setBounds(0, 0, 479, 125);
        ((Component)this.attachList).setBackground(new Color(16777215));
        this.attachPnl.add("Center", (Component)this.attachList);
        this.setTitle("Untitled");
        this.menuBar2 = new MenuBar();
        this.menu1 = new Menu(this.props.language.getProperty("menu.file", "File"));
        this.menuItem1 = new MenuItem(this.props.language.getProperty("menu.view_headers", "View headers..."));
        this.menu1.add(this.menuItem1);
        this.saveMen = new MenuItem(this.props.language.getProperty("menu.save", "Save..."));
        this.menu1.add(this.saveMen);
        this.menu1.addSeparator();
        this.closeMen = new MenuItem(this.props.language.getProperty("menu.close", "Close"));
        this.menu1.add(this.closeMen);
        this.menuBar2.add(this.menu1);
        this.menu2 = new Menu(this.props.language.getProperty("menu.edit", "Edit"));
        this.selectMen = new MenuItem(this.props.language.getProperty("menu.select_all", "Select all"));
        this.menu2.add(this.selectMen);
        this.menuBar2.add(this.menu2);
        this.menu3 = new Menu(this.props.language.getProperty("menu.message", "Message"));
        this.newMen = new MenuItem(this.props.language.getProperty("menu.new", "New..."));
        this.menu3.add(this.newMen);
        this.deleteMen = new MenuItem(this.props.language.getProperty("menu.delete", "Delete"));
        this.menu3.add(this.deleteMen);
        this.menu3.addSeparator();
        this.replyMen = new MenuItem(this.props.language.getProperty("menu.reply", "Reply..."));
        this.menu3.add(this.replyMen);
        this.replyallMen = new MenuItem(this.props.language.getProperty("menu.reply_to_all", "Reply to All..."));
        this.menu3.add(this.replyallMen);
        this.forwardMen = new MenuItem(this.props.language.getProperty("menu.forward", "Forward..."));
        this.menu3.add(this.forwardMen);
        this.menuBar2.add(this.menu3);
        this.menu3.addSeparator();
        this.nextMen = new MenuItem(this.props.language.getProperty("menu.next", "Next"));
        this.menu3.add(this.nextMen);
        this.prevMen = new MenuItem(this.props.language.getProperty("menu.previous", "Previous"));
        this.menu3.add(this.prevMen);
        this.setMenuBar(this.menuBar2);
        this.saveMen.setShortcut(new MenuShortcut(83, false));
        this.selectMen.setShortcut(new MenuShortcut(65, false));
        this.newMen.setShortcut(new MenuShortcut(78, false));
        this.deleteMen.setShortcut(new MenuShortcut(127, false));
        this.replyMen.setShortcut(new MenuShortcut(82, false));
        this.replyallMen.setShortcut(new MenuShortcut(82, true));
        this.forwardMen.setShortcut(new MenuShortcut(70, false));
        this.nextMen.setShortcut(new MenuShortcut(78, true));
        this.prevMen.setShortcut(new MenuShortcut(80, true));
        this.addWindowListener(new SymWindow());
        final SymAction symAction = new SymAction();
        ((ButtonBase)this.newBtn).addActionListener((ActionListener)symAction);
        ((ButtonBase)this.nextBtn).addActionListener((ActionListener)symAction);
        ((ButtonBase)this.prevBtn).addActionListener((ActionListener)symAction);
        ((ButtonBase)this.helpBtn).addActionListener((ActionListener)symAction);
        ((ButtonBase)this.replyBtn).addActionListener((ActionListener)symAction);
        ((ButtonBase)this.fwdBtn).addActionListener((ActionListener)symAction);
        this.closeMen.addActionListener(symAction);
        this.saveMen.addActionListener(symAction);
        this.selectMen.addActionListener(symAction);
        this.newMen.addActionListener(symAction);
        this.deleteMen.addActionListener(symAction);
        this.replyMen.addActionListener(symAction);
        this.replyallMen.addActionListener(symAction);
        this.forwardMen.addActionListener(symAction);
        this.nextMen.addActionListener(symAction);
        this.prevMen.addActionListener(symAction);
        ((ButtonBase)this.addressBtn).addActionListener((ActionListener)symAction);
        final SymMouse symMouse = new SymMouse();
        this.fromTxt.addMouseListener(symMouse);
        this.attachList.addActionListener((ActionListener)symAction);
        this.fromLbl.addActionListener(symAction);
        ((ButtonBase)this.delBtn).addActionListener((ActionListener)symAction);
        this.menuItem1.addActionListener(symAction);
        this.addComponentListener(new SymComponent());
        final SymFocus symFocus = new SymFocus();
        ((Component)this.newBtn).addFocusListener(symFocus);
        ((Component)this.delBtn).addFocusListener(symFocus);
        ((Component)this.replyBtn).addFocusListener(symFocus);
        ((Component)this.fwdBtn).addFocusListener(symFocus);
        ((Component)this.addressBtn).addFocusListener(symFocus);
        ((Component)this.nextBtn).addFocusListener(symFocus);
        ((Component)this.prevBtn).addFocusListener(symFocus);
        ((Component)this.helpBtn).addFocusListener(symFocus);
        this.fromTxt.addFocusListener(symFocus);
        this.messageTxt.addKeyListener(new SymKey());
        try {
            this.attachList.setCellFont(this.props.font);
            this.attachList.setHeadingBg(this.props.background);
            this.attachList.setHeadingFg(this.props.foreground);
            this.attachList.setHeadingFont(this.props.font);
        }
        catch (Exception ex30) {}
        (this.scrollBar = new Scrollbar(1)).setBounds(0, 0, 20, 40);
        this.htmlMessagePnl.add("East", this.scrollBar);
        this.scrollBar.addAdjustmentListener(new SymAdjustment());
        (this.canvas = new HtmlCanvas()).addMouseListener(symMouse);
        this.canvas.setWidth(this.size().width - 20);
        this.canvas.setBackground(Color.white);
        this.canvas.setForeground(Color.black);
        this.canvas.setFont(this.props.messageFont);
        this.htmlMessagePnl.add("Center", this.canvas);
        this.canvas.size();
        if (!this.props.toolbars) {
            ((Component)this.toolBarPanel1).setVisible(false);
        }
        else {
            ((Component)this.toolBarPanel1).setVisible(true);
        }
        this.placement = this.props.toolbarSide;
        this.buttonSize = this.props.buttonSize;
        if (!this.props.connected) {
            this.disableGui();
        }
    }
    
    public msgFrame(final POP3mboxFrame popMboxFrame, final int mNum, final JMessage message, final JMProps jmProps) {
        this(jmProps);
        this.mNum = mNum;
        this.index = this.mNum - 1;
        this.uidl = this.props.uidls[this.index];
        this.fromAddress = this.props.fromAddress;
        this.popMboxFrame = popMboxFrame;
        this.addressBook = this.props.aBook;
        this.browser = this.props.parentApp.getAppletContext();
        this.message = message;
        this.fillDataFields();
    }
    
    public msgFrame(final JMessage message, final int mNum, final JMProps jmProps) {
        this(jmProps);
        this.mNum = mNum;
        this.fromAddress = this.props.fromAddress;
        this.message = message;
        this.addressBook = this.props.aBook;
        this.fillDataFields();
        ((ButtonBase)this.prevBtn).setEnabled(false);
        ((ButtonBase)this.nextBtn).setEnabled(false);
    }
    
    public synchronized void show() {
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
        this.saveProps();
        this.props.removeWindow(this);
    }
    
    void newBtn_actionPerformed(final ActionEvent actionEvent) {
        final sendFrame sendFrame = new sendFrame(this.props);
        final Point location = this.getLocation();
        location.x += 10;
        location.y += 10;
        sendFrame.move(location.x, location.y);
        sendFrame.show();
    }
    
    void nextBtn_actionPerformed(final ActionEvent actionEvent) {
        this.popMboxFrame.blockUpdate = true;
        this.setCursor(Cursor.getPredefinedCursor(3));
        final int nextNum = this.popMboxFrame.getNextNum(this.mNum);
        if (nextNum > -1) {
            try {
                final String s = this.props.uidls[nextNum - 1];
                if (s != null) {
                    JMessage message = this.props.cache.getMessage(s);
                    if (message != null && this.props.connected && message.getNumAtts() > 0) {
                        Thread.currentThread();
                        while (this.popMboxFrame.blockMain) {
                            try {
                                Thread.sleep(1000L);
                            }
                            catch (Exception ex2) {}
                        }
                        this.props.confirmConnection();
                        boolean writeAttachments = false;
                        for (int n = 0; n <= msgFrame.STRIKES && !writeAttachments; writeAttachments = this.props.server.writeAttachments(this.props.password, nextNum, s), ++n) {}
                    }
                    if (message == null && this.props.connected) {
                        Thread.currentThread();
                        while (this.popMboxFrame.blockMain) {
                            try {
                                Thread.sleep(1000L);
                            }
                            catch (Exception ex3) {}
                        }
                        this.props.confirmConnection();
                        for (int n2 = 0; n2 <= msgFrame.STRIKES && message == null; message = this.props.server.getMessage(this.props.password, nextNum, s, true), ++n2) {}
                    }
                    if (message == null) {
                        final ErrorDlg errorDlg = new ErrorDlg(this, "ERROR!", this.props.language.getProperty("error.message_next", "An error occured when attempting to get the next message."), true);
                        errorDlg.setBackground(this.props.background);
                        errorDlg.setForeground(this.props.foreground);
                        errorDlg.setFont(this.props.font);
                        errorDlg.show();
                    }
                    else {
                        this.props.cache.addMessage(message);
                        this.reshow(nextNum, message);
                        this.popMboxFrame.markAsRead(nextNum);
                    }
                }
            }
            catch (Exception ex) {
                System.out.println("NEXT ERROR: " + ex.toString());
            }
        }
        this.setCursor(Cursor.getPredefinedCursor(0));
        try {
            this.props.server.logout();
        }
        catch (Exception ex4) {}
        this.popMboxFrame.blockUpdate = false;
    }
    
    void prevBtn_actionPerformed(final ActionEvent actionEvent) {
        this.popMboxFrame.blockUpdate = true;
        this.setCursor(Cursor.getPredefinedCursor(3));
        final int previousNum = this.popMboxFrame.getPreviousNum(this.mNum);
        if (previousNum > -1) {
            final String s = this.props.uidls[previousNum - 1];
            if (s != null) {
                try {
                    JMessage message = this.props.cache.getMessage(s);
                    if (message != null && this.props.connected && message.getNumAtts() > 0) {
                        Thread.currentThread();
                        while (this.popMboxFrame.blockMain) {
                            try {
                                Thread.sleep(1000L);
                            }
                            catch (Exception ex2) {}
                        }
                        boolean writeAttachments = false;
                        this.props.confirmConnection();
                        for (int n = 0; n <= msgFrame.STRIKES && !writeAttachments; writeAttachments = this.props.server.writeAttachments(this.props.password, previousNum, s), ++n) {}
                    }
                    if (message == null && this.props.connected) {
                        Thread.currentThread();
                        while (this.popMboxFrame.blockMain) {
                            try {
                                Thread.sleep(1000L);
                            }
                            catch (Exception ex3) {}
                        }
                        this.props.confirmConnection();
                        for (int n2 = 0; n2 <= msgFrame.STRIKES && message == null; message = this.props.server.getMessage(this.props.password, previousNum, s, true), ++n2) {}
                    }
                    if (message == null) {
                        final ErrorDlg errorDlg = new ErrorDlg(this, "ERROR!", this.props.language.getProperty("error.message_prev", "An error occured when attempting to get the previous message."), true);
                        errorDlg.setBackground(this.props.background);
                        errorDlg.setForeground(this.props.foreground);
                        errorDlg.setFont(this.props.font);
                        errorDlg.show();
                    }
                    else {
                        this.props.cache.addMessage(message);
                        this.reshow(previousNum, message);
                        this.popMboxFrame.markAsRead(previousNum);
                    }
                }
                catch (Exception ex) {
                    System.out.println("PREV ERROR: " + ex.toString());
                }
            }
        }
        this.setCursor(Cursor.getPredefinedCursor(0));
        try {
            this.props.server.logout();
        }
        catch (Exception ex4) {}
        this.popMboxFrame.blockUpdate = false;
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
    
    public void replyToMessage() {
        final StringBuffer originalMessage = this.getOriginalMessage();
        final String headerValue = this.message.getHeaderValue("reply-to");
        String text;
        if (headerValue == null) {
            text = this.fromTxt.getText();
        }
        else {
            text = headerValue;
        }
        final String address = new JMailAddress(text).getAddress();
        final sendFrame sendFrame = new sendFrame(this.props);
        String s = this.subjectTxt.getText();
        if (!s.startsWith("RE:") && !s.startsWith("Re:")) {
            s = "Re: " + s;
        }
        sendFrame.setData(address, "", s, originalMessage);
        final Point location = this.getLocation();
        location.x += 10;
        location.y += 10;
        sendFrame.move(location.x, location.y);
        this.hide();
        this.saveProps();
        sendFrame.show();
    }
    
    public void forwardMessage() {
        final StringBuffer originalMessage = this.getOriginalMessage();
        final sendFrame sendFrame = new sendFrame(this.props);
        String s = this.subjectTxt.getText();
        if (!s.startsWith("FWD:") && !s.startsWith("Fwd:")) {
            s = "Fwd: " + s;
        }
        sendFrame.setData("", "", s, originalMessage);
        final Point location = this.getLocation();
        location.x += 10;
        location.y += 10;
        sendFrame.move(location.x, location.y);
        this.hide();
        this.saveProps();
        sendFrame.show();
    }
    
    public void replyToAll() {
        final StringBuffer originalMessage = this.getOriginalMessage();
        final String headerValue = this.message.getHeaderValue("reply-to");
        String text;
        if (headerValue == null) {
            text = this.fromTxt.getText();
        }
        else {
            text = headerValue;
        }
        final String address = new JMailAddress(text).getAddress();
        final sendFrame sendFrame = new sendFrame(this.props);
        final String headerValue2 = this.message.getHeaderValue("to");
        String headerValue3 = this.message.getHeaderValue("cc");
        if (headerValue3 == null) {
            headerValue3 = "";
        }
        final String string = String.valueOf(address) + ", " + headerValue2;
        String s = this.subjectTxt.getText();
        if (!s.startsWith("RE:") && !s.startsWith("Re:")) {
            s = "Re: " + s;
        }
        sendFrame.setData(string, headerValue3, s, originalMessage);
        final Point location = this.getLocation();
        location.x += 10;
        location.y += 10;
        sendFrame.move(location.x, location.y);
        this.hide();
        this.saveProps();
        sendFrame.show();
    }
    
    StringBuffer getOriginalMessage() {
        final StringBuffer sb = new StringBuffer("");
        int n = 0;
        sb.append("\n\n----------\n");
        sb.append("> From: " + this.fromTxt.getText() + "\n");
        sb.append("> To: " + this.message.getHeaderValue("to") + "\n");
        sb.append("> Subject: " + this.subjectTxt.getText() + "\n");
        sb.append("> Date: " + this.dateTxt.getText() + "\n> \n");
        final StringTokenizer stringTokenizer = new StringTokenizer(this.message.getMessageText(), "\n", true);
        boolean b = true;
        while (b) {
            try {
                final String nextToken = stringTokenizer.nextToken();
                if (!nextToken.equals("\n")) {
                    sb.append("> " + nextToken + "\n");
                    n = 0;
                }
                else if (n == 1) {
                    sb.append("> \n");
                    n = 1;
                }
                else {
                    n = 1;
                }
            }
            catch (Exception ex) {
                b = false;
            }
        }
        return sb;
    }
    
    void replyBtn_actionPerformed(final ActionEvent actionEvent) {
        this.replyToMessage();
    }
    
    void fwdBtn_actionPerformed(final ActionEvent actionEvent) {
        this.forwardMessage();
    }
    
    void closeMen_Action(final ActionEvent actionEvent) {
        this.hide();
        this.saveProps();
    }
    
    void cutMen_Action(final ActionEvent actionEvent) {
    }
    
    void copyMen_Action(final ActionEvent actionEvent) {
    }
    
    void pasteMen_Action(final ActionEvent actionEvent) {
    }
    
    void clearMen_Action(final ActionEvent actionEvent) {
    }
    
    void selectMen_Action(final ActionEvent actionEvent) {
        this.messageTxt.selectAll();
    }
    
    void newMen_Action(final ActionEvent actionEvent) {
        final sendFrame sendFrame = new sendFrame(this.props);
        final Point location = this.getLocation();
        location.x += 10;
        location.y += 10;
        sendFrame.move(location.x, location.y);
        sendFrame.show();
    }
    
    void deleteMen_Action(final ActionEvent actionEvent) {
        this.deleteMessage();
    }
    
    void replyMen_Action(final ActionEvent actionEvent) {
        this.replyToMessage();
    }
    
    void replyallMen_Action(final ActionEvent actionEvent) {
        this.replyToAll();
    }
    
    void forwardMen_Action(final ActionEvent actionEvent) {
        this.forwardMessage();
    }
    
    void addressBtn_actionPerformed(final ActionEvent actionEvent) {
        this.addressBook.show();
    }
    
    void fromTxt_MouseClick(final MouseEvent mouseEvent) {
        final long time = new Date().getTime();
        final long n = time - this.lastMouseClick;
        this.lastMouseClick = time;
        if (n < 500L) {
            final JMailAddress mailAddress = new JMailAddress(this.fromTxt.getText());
            this.addressBook.show();
            this.addressBook.addNewAddress(mailAddress);
            return;
        }
        this.fromTxt.selectAll();
    }
    
    void canvas_MouseClick(final MouseEvent mouseEvent) {
        final URL href = this.canvas.getHref(mouseEvent.getX() - this.canvas.location().x, mouseEvent.getY() - this.canvas.location().y);
        if (href != null) {
            final String string = href.toString();
            if (string.toLowerCase().startsWith("mailto:")) {
                final String substring = string.substring(7);
                final sendFrame sendFrame = new sendFrame(this.props);
                sendFrame.setData(substring, "", "", null);
                final Point location = this.getLocation();
                location.x += 10;
                location.y += 10;
                sendFrame.move(location.x, location.y);
                sendFrame.show();
                return;
            }
            this.setCursor(Cursor.getPredefinedCursor(3));
            if (this.props.appletviewer) {
                this.props.addWindow(new HtmlViewer(href));
            }
            else {
                this.browser.showDocument(href, "blank");
            }
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
    }
    
    void fillDataFields() {
        this.fromTxt.setText(this.message.getHeaderValue("from"));
        this.dateTxt.setText(this.message.getHeaderValue("date"));
        this.subjectTxt.setText(this.message.getHeaderValue("subject"));
        this.setTitle(this.subjectTxt.getText());
        String s = this.message.getMessageText();
        if (s.equals("TOO_LARGE")) {
            s = this.props.language.getProperty("labels.max_message", "[This message is too large to be displayed in the message window, and was instead saved as an attachment. Open the 'Message text' attachment below to view the contents of this message.]");
        }
        final String htmlBody = this.message.getHtmlBody();
        final boolean b = htmlBody != null;
        if (this.props.showHtml && (b || this.isHtml(s))) {
            if (b) {
                try {
                    s = htmlBody;
                }
                catch (Exception ex4) {}
            }
            try {
                ((CardLayout)this.messagePnl.getLayout()).show((Container)this.messagePnl, "htmlCard");
            }
            catch (ClassCastException ex) {
                System.out.println("E1:" + ex.toString());
            }
            final String property = this.props.language.getProperty("labels.to", "To:");
            final String property2 = this.props.language.getProperty("labels.cc", "CC:");
            final String headerValue = this.message.getHeaderValue("to");
            String s2;
            if (headerValue == null) {
                s2 = "<B>" + property;
            }
            else {
                s2 = "<B>" + property + "</B> " + this.stripBadChars(headerValue);
            }
            final String headerValue2 = this.message.getHeaderValue("cc");
            String string = null;
            if (headerValue2 != null) {
                string = "<BR><B>" + property2 + "</B> " + this.stripBadChars(headerValue2);
            }
            final StringBuffer sb = new StringBuffer();
            sb.append(s2);
            if (headerValue2 != null && !headerValue2.trim().equals("")) {
                sb.append(string);
            }
            sb.append("<HR><P>");
            sb.append(s);
            final StringBufferInputStream stringBufferInputStream = new StringBufferInputStream(sb.toString());
            this.canvas.setWidth(this.htmlMessagePnl.size().width - 20);
            try {
                this.doc = new HtmlDocument(stringBufferInputStream);
                this.canvas.changeDocument(this.doc);
                this.redoLayout();
            }
            catch (Exception ex5) {}
        }
        else {
            try {
                ((CardLayout)this.messagePnl.getLayout()).show((Container)this.messagePnl, "textCard");
            }
            catch (ClassCastException ex2) {
                System.out.println("E2:" + ex2.toString());
            }
            final String property3 = this.props.language.getProperty("labels.to", "To:");
            final String property4 = this.props.language.getProperty("labels.cc", "CC:");
            final String headerValue3 = this.message.getHeaderValue("to");
            String s3;
            if (headerValue3 == null) {
                s3 = String.valueOf(property3) + "\n";
            }
            else {
                s3 = String.valueOf(property3) + " " + headerValue3 + "\n";
            }
            final String headerValue4 = this.message.getHeaderValue("cc");
            final String string2 = String.valueOf(property4) + " " + headerValue4 + "\n";
            final StringBuffer sb2 = new StringBuffer();
            sb2.append(s3);
            if (headerValue4 != null && !headerValue4.trim().equals("")) {
                sb2.append(string2);
            }
            sb2.append("===\n\n");
            sb2.append(s);
            this.messageTxt.setText(sb2.toString());
        }
        final int numAtts = this.message.getNumAtts();
        if (numAtts > 0) {
            try {
                this.attachPnl.setVisible(true);
                for (int i = 0; i < numAtts; ++i) {
                    final int n = i;
                    final String attName = this.message.getAttName(i);
                    final boolean attIsMessage = this.message.getAttIsMessage(i);
                    int n2 = this.message.getAttSize(i) / 1000;
                    if (n2 == 0) {
                        ++n2;
                    }
                    final String string3 = String.valueOf(n2) + "k";
                    final int lastIndex = attName.lastIndexOf(".");
                    if (attIsMessage) {
                        final InputStream resourceAsStream = this.getClass().getResourceAsStream("images/_message.gif");
                        final byte[] array = new byte[resourceAsStream.available()];
                        resourceAsStream.read(array);
                        this.attachList.addImageCell(n, 0, this.getToolkit().createImage(array));
                    }
                    else if (lastIndex == -1) {
                        try {
                            final InputStream resourceAsStream2 = this.getClass().getResourceAsStream("images/document.gif");
                            final byte[] array2 = new byte[resourceAsStream2.available()];
                            resourceAsStream2.read(array2);
                            this.attachList.addImageCell(n, 0, this.getToolkit().createImage(array2));
                        }
                        catch (Exception ex6) {}
                    }
                    else {
                        try {
                            final InputStream resourceAsStream3 = this.getClass().getResourceAsStream("images/_" + attName.substring(lastIndex + 1).toLowerCase() + ".gif");
                            final byte[] array3 = new byte[resourceAsStream3.available()];
                            resourceAsStream3.read(array3);
                            this.attachList.addImageCell(n, 0, this.getToolkit().createImage(array3));
                        }
                        catch (Exception ex7) {
                            try {
                                final InputStream resourceAsStream4 = this.getClass().getResourceAsStream("images/document.gif");
                                final byte[] array4 = new byte[resourceAsStream4.available()];
                                resourceAsStream4.read(array4);
                                this.attachList.addImageCell(n, 0, this.getToolkit().createImage(array4));
                            }
                            catch (Exception ex8) {}
                        }
                    }
                    this.attachList.addTextCell(n, 1, attName);
                    this.attachList.addTextCell(n, 2, string3);
                }
            }
            catch (Exception ex3) {
                System.out.println("ERROR getting message: " + ex3.toString());
            }
        }
        this.redoLayout();
        ((Component)this.attachList).repaint();
        this.repaint();
    }
    
    void fromLbl_actionPerformed(final ActionEvent actionEvent) {
        final JMailAddress mailAddress = new JMailAddress(this.fromTxt.getText());
        this.addressBook.show();
        this.addressBook.addNewAddress(mailAddress);
    }
    
    void attachList_actionPerformed(final ActionEvent actionEvent) {
        if (!this.props.connected) {
            final ErrorDlg errorDlg = new ErrorDlg(this, "ERROR!", this.props.language.getProperty("error.disconnected_attachments", "You cannot view attachments when disconnected. To view attachment files, restore the server connection and reopen the message."), true);
            errorDlg.setBackground(this.props.background);
            errorDlg.setForeground(this.props.foreground);
            errorDlg.setFont(this.props.font);
            errorDlg.show();
            return;
        }
        final int selectedRow = this.attachList.getSelectedRow();
        final String attUrl = this.message.getAttUrl(selectedRow);
        final String cellText = this.attachList.getCellText(selectedRow, 1);
        if (attUrl == null) {
            final ErrorDlg errorDlg2 = new ErrorDlg(this, "ERROR!", this.props.language.getProperty("error.attachment", "This attachment could not be decoded -- you cannot view it."), true);
            errorDlg2.setBackground(this.props.background);
            errorDlg2.setForeground(this.props.foreground);
            errorDlg2.setFont(this.props.font);
            errorDlg2.show();
            return;
        }
        try {
            final URL url = new URL(this.props.base, attUrl);
            if (this.props.appletviewer) {
                final int lastIndex = attUrl.lastIndexOf(".");
                int n;
                if (lastIndex == -1 || attUrl.endsWith(".")) {
                    n = HtmlDocument.TYPE_TEXT;
                }
                else {
                    final String substring = attUrl.substring(lastIndex + 1);
                    if (substring.equalsIgnoreCase("gif")) {
                        n = HtmlDocument.TYPE_IMAGE;
                    }
                    else if (substring.equalsIgnoreCase("jpg")) {
                        n = HtmlDocument.TYPE_IMAGE;
                    }
                    else if (substring.equalsIgnoreCase("jpeg")) {
                        n = HtmlDocument.TYPE_IMAGE;
                    }
                    else if (substring.equalsIgnoreCase("html")) {
                        n = HtmlDocument.TYPE_HTML;
                    }
                    else if (substring.equalsIgnoreCase("htm")) {
                        n = HtmlDocument.TYPE_HTML;
                    }
                    else if (substring.equalsIgnoreCase("au")) {
                        n = HtmlDocument.TYPE_SOUND;
                    }
                    else {
                        n = HtmlDocument.TYPE_TEXT;
                    }
                }
                final HtmlViewer htmlViewer = new HtmlViewer(url, n);
                if (n != HtmlDocument.TYPE_HTML) {
                    htmlViewer.setTitle(cellText);
                }
                this.props.addWindow(htmlViewer);
                return;
            }
            this.browser.showDocument(url, "_blank");
        }
        catch (Exception ex) {}
    }
    
    void delBtn_actionPerformed(final ActionEvent actionEvent) {
        this.deleteMessage();
    }
    
    void deleteMessage() {
        this.popMboxFrame.deleteMessage(this.mNum);
        this.hide();
        this.saveProps();
    }
    
    void menuItem1_Action(final ActionEvent actionEvent) {
        new sourceFrame(this.message, this.props).show();
    }
    
    void saveMen_Action(final ActionEvent actionEvent) {
        try {
            final FileDialog fileDialog = new FileDialog(this);
            fileDialog.setMode(1);
            fileDialog.setTitle("Save");
            final char[] charArray = this.subjectTxt.getText().toCharArray();
            for (int length = charArray.length, i = 0; i < length; ++i) {
                if (charArray[i] == '\\' || charArray[i] == '/' || charArray[i] == ':' || charArray[i] == '*' || charArray[i] == '?' || charArray[i] == '\"' || charArray[i] == '<' || charArray[i] == '>' || charArray[i] == '|' || charArray[i] == ' ' || charArray[i] == '\t' || charArray[i] == '.') {
                    charArray[i] = '_';
                }
            }
            fileDialog.setFile(String.valueOf(new String(charArray)) + ".txt");
            fileDialog.show();
            final String directory = fileDialog.getDirectory();
            final String file = fileDialog.getFile();
            if (directory != null && file != null) {
                final PrintStream printStream = new PrintStream(new FileOutputStream(new File(String.valueOf(fileDialog.getDirectory()) + this.SEP + fileDialog.getFile())));
                printStream.println("From:    " + this.fromTxt.getText());
                printStream.println("To:      " + this.message.getHeaderValue("to"));
                final String headerValue = this.message.getHeaderValue("cc");
                if (headerValue != null) {
                    printStream.println("CC:      " + headerValue);
                }
                printStream.println("Subject: " + this.subjectTxt.getText());
                printStream.println("Date:    " + this.dateTxt.getText());
                printStream.println("");
                final StringTokenizer stringTokenizer = new StringTokenizer(this.message.getMessageText(), "\n", true);
                boolean b = true;
                while (b) {
                    try {
                        final String nextToken = stringTokenizer.nextToken();
                        if (nextToken.equals("\n")) {
                            printStream.println("");
                        }
                        else {
                            printStream.print(nextToken);
                        }
                    }
                    catch (Exception ex3) {
                        b = false;
                    }
                }
                printStream.close();
            }
        }
        catch (SecurityException ex) {
            System.out.println("WRITE ERROR: " + ex.toString());
            try {
                this.browser.showDocument(new URL(this.props.base, "client/trust.html"), "_blank");
            }
            catch (Exception ex4) {
                final NotHereFrame notHereFrame = new NotHereFrame("Trust required.", "The message could not be saved, because MochaMail does not have permission to write to your disk drive.");
                notHereFrame.setBackground(this.props.background);
                notHereFrame.setForeground(this.props.foreground);
                notHereFrame.setFont(this.props.font);
                notHereFrame.show();
            }
        }
        catch (IOException ex2) {
            final ErrorDlg errorDlg = new ErrorDlg(this, "ERROR!", String.valueOf(this.props.language.getProperty("error.save", "An error occured when saving this file")) + ": " + ex2.toString(), true);
            errorDlg.setBackground(this.props.background);
            errorDlg.setForeground(this.props.foreground);
            errorDlg.setFont(this.props.font);
            errorDlg.show();
        }
    }
    
    public void redoLayout() {
        final Dimension size = ((Component)this.messagePnl).size();
        this.scrollBar.size();
        if (this.canvas.setWidth(size.width - 20) > size.height && size.height > 0) {
            this.scrollBar.setValues(this.scrollBar.getValue(), size.height, 0, this.canvas.setWidth(size.width - 20));
            this.scrollBar.setBlockIncrement(size.height);
            this.scrollBar.setUnitIncrement(20);
            this.scrollBar.show();
        }
        else {
            this.scrollBar.setValue(0);
            this.scrollBar.hide();
        }
        this.canvas.setStart(this.scrollBar.getValue());
        this.repaint();
    }
    
    boolean isHtml(final String s) {
        final int index = s.indexOf("<HTML>");
        s.lastIndexOf("</HTML>");
        final int index2 = s.indexOf("<html>");
        s.lastIndexOf("</html>");
        return index != -1 || index2 != -1;
    }
    
    void scrollBar_AdjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.canvas.setStart(this.scrollBar.getValue());
    }
    
    void msgFrame_ComponentResized(final ComponentEvent componentEvent) {
        this.messageTxt.setFont(this.props.messageFont);
        try {
            this.attachList.setCellFont(this.props.font);
            this.attachList.setHeadingBg(this.props.background);
            this.attachList.setHeadingFg(this.props.foreground);
            this.attachList.setHeadingFont(this.props.font);
        }
        catch (Exception ex) {}
        ((Component)this.messagePnl).size();
        this.redoLayout();
        this.canvas.repaint();
        if (!this.props.toolbars) {
            ((Component)this.toolBarPanel1).setVisible(false);
        }
        else {
            ((Component)this.toolBarPanel1).setVisible(true);
        }
        if (this.buttonSize != this.props.buttonSize) {
            this.remove((Component)this.toolBarPanel1);
            this.toolBarPanel1 = new ToolBarPanel();
            try {
                ((BorderPanel)this.toolBarPanel1).setIPadBottom(0);
            }
            catch (PropertyVetoException ex2) {}
            try {
                ((BorderPanel)this.toolBarPanel1).setIPadSides(0);
            }
            catch (PropertyVetoException ex3) {}
            try {
                ((BorderPanel)this.toolBarPanel1).setIPadTop(0);
            }
            catch (PropertyVetoException ex4) {}
            this.toolBarPanel1.setLayout((LayoutManager)new FlowLayout(0, 0, 0));
            ((BorderPanel)this.toolBarPanel1).setBounds(0, 0, this.props.buttonSize, this.props.buttonSize);
            this.add(this.props.toolbarSide, (Component)this.toolBarPanel1);
            try {
                URL imageURL;
                if (this.props.buttonSize == 28) {
                    imageURL = new URL(this.props.base, "client/images/new24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL = new URL(this.props.base, "client/images/new76.gif");
                }
                else {
                    imageURL = new URL(this.props.base, "client/images/new.gif");
                }
                this.newBtn.setImageURL(imageURL);
            }
            catch (MalformedURLException ex5) {}
            catch (PropertyVetoException ex6) {}
            ((Component)this.newBtn).setBounds(0, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.newBtn);
            try {
                URL imageURL2;
                if (this.props.buttonSize == 28) {
                    imageURL2 = new URL(this.props.base, "client/images/delete24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL2 = new URL(this.props.base, "client/images/delete76.gif");
                }
                else {
                    imageURL2 = new URL(this.props.base, "client/images/delete.gif");
                }
                this.delBtn.setImageURL(imageURL2);
            }
            catch (MalformedURLException ex7) {}
            catch (PropertyVetoException ex8) {}
            ((Component)this.delBtn).setBounds(46, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.delBtn);
            try {
                URL imageURL3;
                if (this.props.buttonSize == 28) {
                    imageURL3 = new URL(this.props.base, "client/images/reply24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL3 = new URL(this.props.base, "client/images/reply76.gif");
                }
                else {
                    imageURL3 = new URL(this.props.base, "client/images/reply.gif");
                }
                this.replyBtn.setImageURL(imageURL3);
            }
            catch (MalformedURLException ex9) {}
            catch (PropertyVetoException ex10) {}
            ((Component)this.replyBtn).setBounds(92, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.replyBtn);
            try {
                URL imageURL4;
                if (this.props.buttonSize == 28) {
                    imageURL4 = new URL(this.props.base, "client/images/forward24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL4 = new URL(this.props.base, "client/images/forward76.gif");
                }
                else {
                    imageURL4 = new URL(this.props.base, "client/images/forward.gif");
                }
                this.fwdBtn.setImageURL(imageURL4);
            }
            catch (MalformedURLException ex11) {}
            catch (PropertyVetoException ex12) {}
            ((Component)this.fwdBtn).setBounds(138, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.fwdBtn);
            try {
                URL imageURL5;
                if (this.props.buttonSize == 28) {
                    imageURL5 = new URL(this.props.base, "client/images/address24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL5 = new URL(this.props.base, "client/images/address76.gif");
                }
                else {
                    imageURL5 = new URL(this.props.base, "client/images/address.gif");
                }
                this.addressBtn.setImageURL(imageURL5);
            }
            catch (MalformedURLException ex13) {}
            catch (PropertyVetoException ex14) {}
            ((Component)this.addressBtn).setBounds(184, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.addressBtn);
            try {
                URL imageURL6;
                if (this.props.buttonSize == 28) {
                    imageURL6 = new URL(this.props.base, "client/images/next24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL6 = new URL(this.props.base, "client/images/next76.gif");
                }
                else {
                    imageURL6 = new URL(this.props.base, "client/images/next.gif");
                }
                this.nextBtn.setImageURL(imageURL6);
            }
            catch (MalformedURLException ex15) {}
            catch (PropertyVetoException ex16) {}
            ((Component)this.nextBtn).setBounds(230, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.nextBtn);
            try {
                URL imageURL7;
                if (this.props.buttonSize == 28) {
                    imageURL7 = new URL(this.props.base, "client/images/previous24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL7 = new URL(this.props.base, "client/images/previous76.gif");
                }
                else {
                    imageURL7 = new URL(this.props.base, "client/images/previous.gif");
                }
                this.prevBtn.setImageURL(imageURL7);
            }
            catch (MalformedURLException ex17) {}
            catch (PropertyVetoException ex18) {}
            ((Component)this.prevBtn).setBounds(276, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.prevBtn);
            try {
                URL imageURL8;
                if (this.props.buttonSize == 28) {
                    imageURL8 = new URL(this.props.base, "client/images/help24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL8 = new URL(this.props.base, "client/images/help76.gif");
                }
                else {
                    imageURL8 = new URL(this.props.base, "client/images/help.gif");
                }
                this.helpBtn.setImageURL(imageURL8);
            }
            catch (MalformedURLException ex19) {}
            catch (PropertyVetoException ex20) {}
            ((Component)this.helpBtn).setBounds(322, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.helpBtn);
            ((BorderPanel)this.toolBarPanel1).setBounds(0, 0, this.props.buttonSize, this.props.buttonSize);
            this.add(this.props.toolbarSide, (Component)this.toolBarPanel1);
            this.placement = this.props.toolbarSide;
            this.buttonSize = this.props.buttonSize;
        }
        else if (!this.placement.equalsIgnoreCase(this.props.toolbarSide)) {
            this.remove((Component)this.toolBarPanel1);
            ((BorderPanel)this.toolBarPanel1).setBounds(0, 0, this.props.buttonSize, this.props.buttonSize);
            this.add(this.props.toolbarSide, (Component)this.toolBarPanel1);
            this.placement = this.props.toolbarSide;
            this.buttonSize = this.props.buttonSize;
        }
        this.repaint();
    }
    
    String stripBadChars(String s) {
        if (s.indexOf(60) == -1) {
            return s;
        }
        for (int i = s.indexOf(60); i != -1; i = s.indexOf(60)) {
            s = String.valueOf(s.substring(0, i)) + "&lt;" + s.substring(i + 1);
        }
        for (int j = s.indexOf(62); j != -1; j = s.indexOf(62)) {
            final String substring = s.substring(0, j);
            String substring2;
            if (j >= s.length() - 1) {
                substring2 = "";
            }
            else {
                substring2 = s.substring(j + 1);
            }
            s = String.valueOf(substring) + "&gt;" + substring2;
        }
        return s;
    }
    
    void saveProps() {
        final Dimension size = this.size();
        this.props.messageWidth = size.width - 8;
        if (this.props.NETSCAPE) {
            this.props.messageHeight = size.height - 64;
        }
        else {
            this.props.messageHeight = size.height - 46;
        }
        final Point location = this.location();
        this.props.messagePosX = location.x;
        this.props.messagePosY = location.y;
        if (this.message.getNumAtts() > 0 && this.props.connected) {
            try {
                this.props.server.deleteAttachments(this.props.password, this.props.uidls[this.mNum - 1]);
            }
            catch (Exception ex) {}
        }
    }
    
    void newBtn_focusGained(final FocusEvent focusEvent) {
        this.messageTxt.requestFocus();
    }
    
    void delBtn_focusGained(final FocusEvent focusEvent) {
        this.messageTxt.requestFocus();
    }
    
    void replyBtn_focusGained(final FocusEvent focusEvent) {
        this.messageTxt.requestFocus();
    }
    
    void fwdBtn_focusGained(final FocusEvent focusEvent) {
        this.messageTxt.requestFocus();
    }
    
    void addressBtn_focusGained(final FocusEvent focusEvent) {
        this.messageTxt.requestFocus();
    }
    
    void nextBtn_focusGained(final FocusEvent focusEvent) {
        this.messageTxt.requestFocus();
    }
    
    void prevBtn_focusGained(final FocusEvent focusEvent) {
        this.messageTxt.requestFocus();
    }
    
    void helpBtn_focusGained(final FocusEvent focusEvent) {
        this.messageTxt.requestFocus();
    }
    
    void fromTxt_LostFocus(final FocusEvent focusEvent) {
        this.fromTxt.select(0, 0);
    }
    
    void disableGui() {
        ((ButtonBase)this.delBtn).setEnabled(false);
        this.deleteMen.setEnabled(false);
    }
    
    void reshow(final int mNum, final JMessage message) {
        if (this.message.getNumAtts() > 0 && this.props.connected) {
            try {
                this.props.server.deleteAttachments(this.props.password, this.props.uidls[this.mNum - 1]);
            }
            catch (Exception ex) {}
        }
        this.attachList.clear();
        this.attachPnl.setVisible(false);
        final StringBufferInputStream stringBufferInputStream = new StringBufferInputStream("");
        try {
            this.doc = new HtmlDocument(stringBufferInputStream);
            this.canvas.changeDocument(this.doc);
        }
        catch (Exception ex2) {}
        this.scrollBar.setValue(0);
        this.mNum = mNum;
        this.index = this.mNum - 1;
        this.uidl = this.props.uidls[this.index];
        this.message = message;
        this.fillDataFields();
        final Dimension size = this.size();
        ++size.width;
        size.height += 5;
        this.resize(size);
        --size.width;
        size.height -= 5;
        this.resize(size);
        this.repaint();
    }
    
    void messageTxt_KeyPress(final KeyEvent keyEvent) {
        if (KeyEvent.getKeyText(keyEvent.getKeyCode()).equalsIgnoreCase("Tab")) {
            this.messageTxt.transferFocus();
        }
    }
    
    static {
        msgFrame.STRIKES = 3;
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == msgFrame.this) {
                msgFrame.this.Frame1_WindowClosing(windowEvent);
            }
        }
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == msgFrame.this.newBtn) {
                msgFrame.this.newBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == msgFrame.this.nextBtn) {
                msgFrame.this.nextBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == msgFrame.this.prevBtn) {
                msgFrame.this.prevBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == msgFrame.this.helpBtn) {
                msgFrame.this.helpBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == msgFrame.this.replyBtn) {
                msgFrame.this.replyBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == msgFrame.this.fwdBtn) {
                msgFrame.this.fwdBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == msgFrame.this.saveMen) {
                msgFrame.this.saveMen_Action(actionEvent);
                return;
            }
            if (source == msgFrame.this.closeMen) {
                msgFrame.this.closeMen_Action(actionEvent);
                return;
            }
            if (source == msgFrame.this.cutMen) {
                msgFrame.this.cutMen_Action(actionEvent);
                return;
            }
            if (source == msgFrame.this.copyMen) {
                msgFrame.this.copyMen_Action(actionEvent);
                return;
            }
            if (source == msgFrame.this.pasteMen) {
                msgFrame.this.pasteMen_Action(actionEvent);
                return;
            }
            if (source == msgFrame.this.clearMen) {
                msgFrame.this.clearMen_Action(actionEvent);
                return;
            }
            if (source == msgFrame.this.selectMen) {
                msgFrame.this.selectMen_Action(actionEvent);
                return;
            }
            if (source == msgFrame.this.newMen) {
                msgFrame.this.newMen_Action(actionEvent);
                return;
            }
            if (source == msgFrame.this.deleteMen) {
                msgFrame.this.deleteMen_Action(actionEvent);
                return;
            }
            if (source == msgFrame.this.replyMen) {
                msgFrame.this.replyMen_Action(actionEvent);
                return;
            }
            if (source == msgFrame.this.replyallMen) {
                msgFrame.this.replyallMen_Action(actionEvent);
                return;
            }
            if (source == msgFrame.this.forwardMen) {
                msgFrame.this.forwardMen_Action(actionEvent);
                return;
            }
            if (source == msgFrame.this.addressBtn) {
                msgFrame.this.addressBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == msgFrame.this.attachList) {
                msgFrame.this.attachList_actionPerformed(actionEvent);
                return;
            }
            if (source == msgFrame.this.delBtn) {
                msgFrame.this.delBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == msgFrame.this.nextMen) {
                msgFrame.this.nextBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == msgFrame.this.prevMen) {
                msgFrame.this.prevBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == msgFrame.this.menuItem1) {
                msgFrame.this.menuItem1_Action(actionEvent);
                return;
            }
            if (source == msgFrame.this.fromLbl) {
                msgFrame.this.fromLbl_actionPerformed(actionEvent);
            }
        }
    }
    
    class SymMouse extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
            final Object source = mouseEvent.getSource();
            if (source == msgFrame.this.fromTxt) {
                msgFrame.this.fromTxt_MouseClick(mouseEvent);
                return;
            }
            if (source == msgFrame.this.canvas) {
                msgFrame.this.canvas_MouseClick(mouseEvent);
            }
        }
    }
    
    class SymAdjustment implements AdjustmentListener
    {
        public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
            if (adjustmentEvent.getSource() == msgFrame.this.scrollBar) {
                msgFrame.this.scrollBar_AdjustmentValueChanged(adjustmentEvent);
            }
        }
    }
    
    class SymComponent extends ComponentAdapter
    {
        public void componentResized(final ComponentEvent componentEvent) {
            if (componentEvent.getSource() == msgFrame.this) {
                msgFrame.this.msgFrame_ComponentResized(componentEvent);
            }
        }
    }
    
    class SymFocus extends FocusAdapter
    {
        public void focusLost(final FocusEvent focusEvent) {
            if (focusEvent.getSource() == msgFrame.this.fromTxt) {
                msgFrame.this.fromTxt_LostFocus(focusEvent);
            }
        }
        
        public void focusGained(final FocusEvent focusEvent) {
            final Object source = focusEvent.getSource();
            if (source == msgFrame.this.newBtn) {
                msgFrame.this.newBtn_focusGained(focusEvent);
                return;
            }
            if (source == msgFrame.this.delBtn) {
                msgFrame.this.delBtn_focusGained(focusEvent);
                return;
            }
            if (source == msgFrame.this.replyBtn) {
                msgFrame.this.replyBtn_focusGained(focusEvent);
                return;
            }
            if (source == msgFrame.this.fwdBtn) {
                msgFrame.this.fwdBtn_focusGained(focusEvent);
                return;
            }
            if (source == msgFrame.this.addressBtn) {
                msgFrame.this.addressBtn_focusGained(focusEvent);
                return;
            }
            if (source == msgFrame.this.nextBtn) {
                msgFrame.this.nextBtn_focusGained(focusEvent);
                return;
            }
            if (source == msgFrame.this.prevBtn) {
                msgFrame.this.prevBtn_focusGained(focusEvent);
                return;
            }
            if (source == msgFrame.this.helpBtn) {
                msgFrame.this.helpBtn_focusGained(focusEvent);
            }
        }
    }
    
    class SymKey extends KeyAdapter
    {
        public void keyPressed(final KeyEvent keyEvent) {
            if (keyEvent.getSource() == msgFrame.this.messageTxt) {
                msgFrame.this.messageTxt_KeyPress(keyEvent);
            }
        }
    }
}
