import symantec.itools.awt.ButtonBase;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.MenuShortcut;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.FlowLayout;
import java.awt.Color;
import java.beans.PropertyVetoException;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.applet.AppletContext;
import java.awt.Label;
import symantec.itools.awt.BorderPanel;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import symantec.itools.awt.ImageButton;
import symantec.itools.awt.util.ToolBarPanel;
import symantec.itools.awt.MultiList;
import java.awt.Panel;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class POP3mboxFrame extends Frame
{
    static boolean DEBUG;
    static int STRIKES;
    static String BOUND;
    boolean fComponentsAdjusted;
    Panel centerPnl;
    MultiList messageList;
    ToolBarPanel toolBarPanel1;
    ImageButton updateBtn;
    ImageButton newBtn;
    ImageButton readBtn;
    ImageButton replyBtn;
    ImageButton fwdBtn;
    ImageButton delBtn;
    ImageButton addressBtn;
    ImageButton helpBtn;
    MenuBar menuBar1;
    Menu menu1;
    MenuItem newMen;
    MenuItem checkMen;
    MenuItem rebuildMen;
    MenuItem prefMen;
    MenuItem disconnectMen;
    MenuItem quitMen;
    Menu menu3;
    MenuItem newmsgMen;
    MenuItem readMen;
    MenuItem markReadMen;
    MenuItem markUnreadMen;
    MenuItem replyMen;
    MenuItem replyallMen;
    MenuItem forwardMen;
    MenuItem resendMen;
    MenuItem selectallMen;
    MenuItem deletemsgMen;
    MenuItem undeletemsgMen;
    Menu menu5;
    MenuItem addressMen;
    MenuItem prefstoolMen;
    Menu menu6;
    MenuItem helpMen;
    MenuItem aboutMen;
    BorderPanel statusPnl;
    Label statusLbl;
    String fromAddress;
    AppletContext browser;
    JMProps props;
    UpdateMbox updater;
    String placement;
    int buttonSize;
    ErrorDlg newDlg;
    public boolean blockUpdate;
    public boolean blockMain;
    String[] newUidls;
    
    public POP3mboxFrame(final JMProps props) {
        this.fComponentsAdjusted = false;
        this.blockUpdate = false;
        this.blockMain = false;
        this.props = props;
        this.setBackground(this.props.background);
        this.setForeground(this.props.foreground);
        this.setFont(this.props.font);
        final Point location = new Point(this.props.mailboxPosX, this.props.mailboxPosY);
        this.setSize(new Dimension(this.props.mailboxWidth, this.props.mailboxHeight));
        this.setLocation(location);
        this.props.addWindow(this);
        this.setLayout(new BorderLayout(5, 5));
        this.setVisible(false);
        (this.centerPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.centerPnl.setBounds(this.insets().left, this.insets().top + 45, 450, 385);
        this.add("Center", this.centerPnl);
        this.messageList = new MultiList();
        try {
            this.messageList.setColumnAlignments(new String[] { new String("Right"), new String("Left"), new String("Left"), new String("Left"), new String("Right"), new String("Left") });
        }
        catch (PropertyVetoException ex) {}
        this.messageList.setLayout((LayoutManager)null);
        try {
            this.messageList.setColumnSizes(this.props.mailboxColumns);
        }
        catch (PropertyVetoException ex2) {}
        try {
            this.messageList.setHeadings(new String[] { this.props.language.getProperty("column.number", "#"), this.props.language.getProperty("column.status", "Status"), this.props.language.getProperty("column.from", "From"), this.props.language.getProperty("column.subject", "Subject"), this.props.language.getProperty("column.size", "Size"), this.props.language.getProperty("column.date", "Date"), new String("        ") });
        }
        catch (PropertyVetoException ex3) {}
        ((Component)this.messageList).setBounds(this.insets().left + 150, this.insets().top + 53, 480, 382);
        ((Component)this.messageList).setBackground(new Color(16777215));
        this.centerPnl.add("Center", (Component)this.messageList);
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
        this.add(this.props.toolbarSide, (Component)this.toolBarPanel1);
        this.updateBtn = new ImageButton();
        try {
            URL imageURL;
            if (this.props.buttonSize == 28) {
                imageURL = new URL(this.props.base, "client/images/update24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL = new URL(this.props.base, "client/images/update76.gif");
            }
            else {
                imageURL = new URL(this.props.base, "client/images/update.gif");
            }
            this.updateBtn.setImageURL(imageURL);
        }
        catch (MalformedURLException ex7) {}
        catch (PropertyVetoException ex8) {}
        ((Component)this.updateBtn).setBounds(0, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.updateBtn);
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
        this.readBtn = new ImageButton();
        try {
            URL imageURL3;
            if (this.props.buttonSize == 28) {
                imageURL3 = new URL(this.props.base, "client/images/read24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL3 = new URL(this.props.base, "client/images/read76.gif");
            }
            else {
                imageURL3 = new URL(this.props.base, "client/images/read.gif");
            }
            this.readBtn.setImageURL(imageURL3);
        }
        catch (MalformedURLException ex11) {}
        catch (PropertyVetoException ex12) {}
        ((Component)this.readBtn).setBounds(0, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.readBtn);
        this.replyBtn = new ImageButton();
        try {
            URL imageURL4;
            if (this.props.buttonSize == 28) {
                imageURL4 = new URL(this.props.base, "client/images/reply24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL4 = new URL(this.props.base, "client/images/reply76.gif");
            }
            else {
                imageURL4 = new URL(this.props.base, "client/images/reply.gif");
            }
            this.replyBtn.setImageURL(imageURL4);
        }
        catch (MalformedURLException ex13) {}
        catch (PropertyVetoException ex14) {}
        ((Component)this.replyBtn).setBounds(102, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.replyBtn);
        this.fwdBtn = new ImageButton();
        try {
            URL imageURL5;
            if (this.props.buttonSize == 28) {
                imageURL5 = new URL(this.props.base, "client/images/forward24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL5 = new URL(this.props.base, "client/images/forward76.gif");
            }
            else {
                imageURL5 = new URL(this.props.base, "client/images/forward.gif");
            }
            this.fwdBtn.setImageURL(imageURL5);
        }
        catch (MalformedURLException ex15) {}
        catch (PropertyVetoException ex16) {}
        ((Component)this.fwdBtn).setBounds(148, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.fwdBtn);
        this.delBtn = new ImageButton();
        try {
            URL imageURL6;
            if (this.props.buttonSize == 28) {
                imageURL6 = new URL(this.props.base, "client/images/delete24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL6 = new URL(this.props.base, "client/images/delete76.gif");
            }
            else {
                imageURL6 = new URL(this.props.base, "client/images/delete.gif");
            }
            this.delBtn.setImageURL(imageURL6);
        }
        catch (MalformedURLException ex17) {}
        catch (PropertyVetoException ex18) {}
        ((Component)this.delBtn).setBounds(194, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.delBtn);
        this.addressBtn = new ImageButton();
        try {
            URL imageURL7;
            if (this.props.buttonSize == 28) {
                imageURL7 = new URL(this.props.base, "client/images/address24.gif");
            }
            else if (this.props.buttonSize == 78) {
                imageURL7 = new URL(this.props.base, "client/images/address76.gif");
            }
            else {
                imageURL7 = new URL(this.props.base, "client/images/address.gif");
            }
            this.addressBtn.setImageURL(imageURL7);
        }
        catch (MalformedURLException ex19) {}
        catch (PropertyVetoException ex20) {}
        ((Component)this.addressBtn).setBounds(262, 0, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.addressBtn);
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
        catch (MalformedURLException ex21) {}
        catch (PropertyVetoException ex22) {}
        ((Component)this.helpBtn).setBounds(308, 20, 46, 46);
        ((Container)this.toolBarPanel1).add((Component)this.helpBtn);
        this.statusPnl = new BorderPanel();
        try {
            this.statusPnl.setBevelStyle(0);
            this.statusPnl.setIPadBottom(0);
            this.statusPnl.setIPadTop(2);
            this.statusPnl.setPaddingRight(2);
            this.statusPnl.setPaddingBottom(2);
            this.statusPnl.setPaddingTop(4);
            this.statusPnl.setPaddingLeft(2);
        }
        catch (PropertyVetoException ex23) {}
        this.statusPnl.setLayout((LayoutManager)new BorderLayout(0, 0));
        this.statusPnl.setBounds(0, 0, 405, 40);
        this.centerPnl.add("South", (Component)this.statusPnl);
        (this.statusLbl = new Label(this.props.language.getProperty("statusbar.ready", "Ready."))).setBounds(0, 0, 384, 14);
        ((Container)this.statusPnl).add("Center", this.statusLbl);
        this.setTitle(this.props.language.getProperty("mochamail", "MochaMail"));
        this.menuBar1 = new MenuBar();
        this.menu1 = new Menu(this.props.language.getProperty("menu.file", "File"));
        this.newMen = new MenuItem(this.props.language.getProperty("menu.new_message", "New message..."));
        this.menu1.add(this.newMen);
        this.checkMen = new MenuItem(this.props.language.getProperty("menu.check_for_new_mail", "Check for new mail"));
        this.menu1.add(this.checkMen);
        this.rebuildMen = new MenuItem(this.props.language.getProperty("menu.rebuild_mailbox", "Rebuild message list"));
        this.menu1.add(this.rebuildMen);
        this.prefMen = new MenuItem(this.props.language.getProperty("menu.preferences", "Preferences..."));
        this.menu1.add(this.prefMen);
        this.menu1.addSeparator();
        this.disconnectMen = new MenuItem(this.props.language.getProperty("menu.disconnect", "Disconnect"));
        this.menu1.add(this.disconnectMen);
        this.menu1.addSeparator();
        this.quitMen = new MenuItem(this.props.language.getProperty("menu.quit", "Quit"));
        this.menu1.add(this.quitMen);
        this.menuBar1.add(this.menu1);
        this.menu3 = new Menu(this.props.language.getProperty("menu.message", "Message"));
        this.newmsgMen = new MenuItem(this.props.language.getProperty("menu.new", "New..."));
        this.menu3.add(this.newmsgMen);
        this.readMen = new MenuItem(this.props.language.getProperty("menu.read", "Read..."));
        this.menu3.add(this.readMen);
        this.menu3.addSeparator();
        this.replyMen = new MenuItem(this.props.language.getProperty("menu.reply", "Reply..."));
        this.menu3.add(this.replyMen);
        this.replyallMen = new MenuItem(this.props.language.getProperty("menu.reply_to_all", "Reply to All..."));
        this.menu3.add(this.replyallMen);
        this.forwardMen = new MenuItem(this.props.language.getProperty("menu.forward", "Forward..."));
        this.menu3.add(this.forwardMen);
        this.menu3.addSeparator();
        this.selectallMen = new MenuItem(this.props.language.getProperty("menu.select_all", "Select all"));
        this.menu3.add(this.selectallMen);
        this.markReadMen = new MenuItem(this.props.language.getProperty("menu.mark_as_read", "Mark as Read"));
        this.menu3.add(this.markReadMen);
        this.markUnreadMen = new MenuItem(this.props.language.getProperty("menu.mark_as_unread", "Mark as Unread"));
        this.menu3.add(this.markUnreadMen);
        this.menu3.addSeparator();
        this.deletemsgMen = new MenuItem(this.props.language.getProperty("menu.delete", "Delete"));
        this.menu3.add(this.deletemsgMen);
        this.undeletemsgMen = new MenuItem(this.props.language.getProperty("menu.undelete", "Undelete"));
        this.menu3.add(this.undeletemsgMen);
        this.menuBar1.add(this.menu3);
        this.menu5 = new Menu(this.props.language.getProperty("menu.tools", "Tools"));
        this.addressMen = new MenuItem(this.props.language.getProperty("menu.address_book", "Address book..."));
        this.menu5.add(this.addressMen);
        this.prefstoolMen = new MenuItem(this.props.language.getProperty("menu.preferences", "Preferences..."));
        this.menu5.add(this.prefstoolMen);
        this.menuBar1.add(this.menu5);
        this.menu6 = new Menu(this.props.language.getProperty("menu.help", "Help"));
        this.menuBar1.setHelpMenu(this.menu6);
        this.helpMen = new MenuItem(this.props.language.getProperty("menu.online_help", "Online Help..."));
        this.menu6.add(this.helpMen);
        this.menu6.addSeparator();
        this.aboutMen = new MenuItem(this.props.language.getProperty("menu.about_mochamail", "About MochaMail..."));
        this.menu6.add(this.aboutMen);
        this.menuBar1.add(this.menu6);
        this.setMenuBar(this.menuBar1);
        this.newMen.setShortcut(new MenuShortcut(78, false));
        this.checkMen.setShortcut(new MenuShortcut(77, false));
        this.rebuildMen.setShortcut(new MenuShortcut(66, false));
        this.quitMen.setShortcut(new MenuShortcut(81, false));
        this.readMen.setShortcut(new MenuShortcut(79, false));
        this.replyMen.setShortcut(new MenuShortcut(82, false));
        this.replyallMen.setShortcut(new MenuShortcut(82, true));
        this.forwardMen.setShortcut(new MenuShortcut(70, false));
        this.selectallMen.setShortcut(new MenuShortcut(65, false));
        this.addWindowListener(new SymWindow());
        final SymAction symAction = new SymAction();
        ((ButtonBase)this.readBtn).addActionListener((ActionListener)symAction);
        this.messageList.addActionListener((ActionListener)symAction);
        this.newMen.addActionListener(symAction);
        this.newmsgMen.addActionListener(symAction);
        this.quitMen.addActionListener(symAction);
        this.disconnectMen.addActionListener(symAction);
        ((ButtonBase)this.newBtn).addActionListener((ActionListener)symAction);
        ((ButtonBase)this.replyBtn).addActionListener((ActionListener)symAction);
        ((ButtonBase)this.fwdBtn).addActionListener((ActionListener)symAction);
        ((ButtonBase)this.delBtn).addActionListener((ActionListener)symAction);
        this.replyMen.addActionListener(symAction);
        this.replyallMen.addActionListener(symAction);
        this.forwardMen.addActionListener(symAction);
        ((ButtonBase)this.updateBtn).addActionListener((ActionListener)symAction);
        this.checkMen.addActionListener(symAction);
        this.rebuildMen.addActionListener(symAction);
        this.selectallMen.addActionListener(symAction);
        ((Component)this.messageList).addKeyListener(new SymKey());
        ((ButtonBase)this.addressBtn).addActionListener((ActionListener)symAction);
        this.addressMen.addActionListener(symAction);
        this.undeletemsgMen.addActionListener(symAction);
        this.deletemsgMen.addActionListener(symAction);
        this.readMen.addActionListener(symAction);
        this.markReadMen.addActionListener(symAction);
        this.markUnreadMen.addActionListener(symAction);
        ((ButtonBase)this.helpBtn).addActionListener((ActionListener)symAction);
        this.helpMen.addActionListener(symAction);
        this.aboutMen.addActionListener(symAction);
        this.prefMen.addActionListener(symAction);
        this.prefstoolMen.addActionListener(symAction);
        final SymFocus symFocus = new SymFocus();
        ((Component)this.updateBtn).addFocusListener(symFocus);
        ((Component)this.newBtn).addFocusListener(symFocus);
        ((Component)this.readBtn).addFocusListener(symFocus);
        ((Component)this.replyBtn).addFocusListener(symFocus);
        ((Component)this.fwdBtn).addFocusListener(symFocus);
        ((Component)this.delBtn).addFocusListener(symFocus);
        ((Component)this.addressBtn).addFocusListener(symFocus);
        ((Component)this.helpBtn).addFocusListener(symFocus);
        this.addComponentListener(new SymComponent());
        try {
            this.messageList.setMultipleMode(true);
            this.messageList.setCellFont(this.props.font);
            this.messageList.setHeadingBg(this.props.background);
            this.messageList.setHeadingFg(this.props.foreground);
            this.messageList.setHeadingFont(this.props.font);
        }
        catch (Exception ex24) {}
        if (!this.props.toolbars) {
            ((Component)this.toolBarPanel1).setVisible(false);
        }
        else {
            ((Component)this.toolBarPanel1).setVisible(true);
        }
        this.placement = this.props.toolbarSide;
        this.buttonSize = this.props.buttonSize;
        this.fromAddress = this.props.fromAddress;
        this.setTitle(String.valueOf(this.getTitle()) + ": " + this.fromAddress);
        this.browser = this.props.parentApp.getAppletContext();
        this.newDlg = new ErrorDlg(this, "MochaMail alert", "You have new mail.", true);
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
    
    void POP3mboxFrame_WindowOpen(final WindowEvent windowEvent) {
        this.setCursor(Cursor.getPredefinedCursor(3));
        try {
            this.displayCachedHeaders();
            if (!this.verifyNumbers()) {
                this.updateMailbox();
            }
            else if (this.props.checkOnLoad) {
                this.getNewMessages();
            }
        }
        catch (Exception ex) {
            try {
                this.updateMailbox();
            }
            catch (Exception ex2) {
                System.out.println("OPEN ERROR: " + ex.toString());
            }
        }
        try {
            this.props.server.logout();
        }
        catch (Exception ex3) {}
        (this.updater = new UpdateMbox()).start();
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    void POP3mboxFrame_WindowClosing(final WindowEvent windowEvent) {
        this.closeMailbox();
    }
    
    void disconnect() {
        if (!this.props.connected) {
            try {
                this.props.reconnect();
                this.hide();
                this.props.mbox = null;
                this.props.removeWindow(this);
                this.props.showMailbox();
                this.props.connected = true;
                return;
            }
            catch (Exception ex) {
                final ErrorDlg errorDlg = new ErrorDlg(this, "ERROR!", this.props.language.getProperty("error.broker_reconnect", "Could not reconnect with Broker. Re-establish a connection with the network, and then try connecting again."), true);
                errorDlg.setBackground(this.props.background);
                errorDlg.setForeground(this.props.foreground);
                errorDlg.setFont(this.props.font);
                errorDlg.show();
                return;
            }
        }
        this.setCursor(Cursor.getPredefinedCursor(3));
        ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(3));
        this.updater.stop();
        this.saveProps();
        this.setStatusText(this.props.language.getProperty("statusbar.downloading", "Downloading messages for disconnected mode..."));
        final String[] cacheMessages = this.cacheMessages();
        this.saveMessageHeaders();
        this.messageList.clear();
        this.displayHeaders(cacheMessages);
        this.props.uidls = this.newUidls;
        this.disableGui();
        new sendFrame(this.props);
        new msgFrame(this.props);
        this.props.disconnect();
        this.setStatusText(this.props.language.getProperty("statusbar.disconnected", "Disconnected."));
        this.setCursor(Cursor.getPredefinedCursor(0));
        ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(0));
    }
    
    void messageList_actionPerformed(final ActionEvent actionEvent) {
        this.readMessage();
    }
    
    void messageList_keyPressed(final KeyEvent keyEvent) {
        final String keyText = KeyEvent.getKeyText(keyEvent.getKeyCode());
        if (!keyText.equalsIgnoreCase("Shift") && keyText.equalsIgnoreCase("Delete") && this.props.connected) {
            this.deleteMessage();
        }
    }
    
    void messageList_keyReleased(final KeyEvent keyEvent) {
        KeyEvent.getKeyText(keyEvent.getKeyCode()).equalsIgnoreCase("Shift");
    }
    
    void newMen_Action(final ActionEvent actionEvent) {
        new sendFrame(this.props).show();
    }
    
    void checkMen_Action(final ActionEvent actionEvent) {
        try {
            Thread.currentThread();
            while (this.blockMain) {
                try {
                    Thread.sleep(1000L);
                }
                catch (Exception ex2) {}
            }
            this.blockMain = true;
            this.updater.stop();
            this.getNewMessages();
            this.props.server.logout();
            this.blockMain = false;
            (this.updater = new UpdateMbox()).start();
        }
        catch (Exception ex) {
            System.out.println("ERROR getting new messages: " + ex.toString());
        }
    }
    
    void rebuildMen_Action(final ActionEvent actionEvent) {
        this.updater.stop();
        this.commitDeletes();
        try {
            this.props.server.logout();
        }
        catch (Exception ex) {}
        this.updateMailbox();
        try {
            this.props.server.logout();
        }
        catch (Exception ex2) {}
        (this.updater = new UpdateMbox()).start();
    }
    
    void quitMen_Action(final ActionEvent actionEvent) {
        this.closeMailbox();
    }
    
    void newmsgMen_Action(final ActionEvent actionEvent) {
        new sendFrame(this.props).show();
    }
    
    void readMen_Action(final ActionEvent actionEvent) {
        this.readMessage();
    }
    
    void replyMen_Action(final ActionEvent actionEvent) {
        if (this.messageList.getSelectedRow() == -1) {
            return;
        }
        this.blockUpdate = true;
        this.setCursor(Cursor.getPredefinedCursor(3));
        ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(3));
        this.setStatusText(this.props.language.getProperty("statusbar.getting_original", "Getting original message..."));
        final String cellText = this.messageList.getCellText(this.messageList.getSelectedRow(), 0);
        String s;
        if (cellText.startsWith("  ")) {
            s = cellText.substring(2);
        }
        else if (cellText.startsWith(" ")) {
            s = cellText.substring(1);
        }
        else {
            s = cellText;
        }
        final int intValue = new Integer(s);
        try {
            final String s2 = this.props.uidls[intValue - 1];
            JMessage message = this.props.cache.getMessage(s2);
            if (message == null) {
                this.props.confirmConnection();
                Thread.currentThread();
                while (this.blockMain) {
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (Exception ex) {}
                }
                for (int n = 0; n <= POP3mboxFrame.STRIKES && message == null; message = this.props.server.getMessage(this.props.password, intValue, s2, false), ++n) {}
            }
            if (message == null) {
                this.setStatusText(this.props.language.getProperty("statusbar.message_error", "Message error."));
                final ErrorDlg errorDlg = new ErrorDlg(this, "ERROR!", this.props.server.getErrorMessage(), true);
                errorDlg.setBackground(this.props.background);
                errorDlg.setForeground(this.props.foreground);
                errorDlg.setFont(this.props.font);
                errorDlg.show();
            }
            else {
                this.props.cache.addMessage(message);
                new msgFrame(message, intValue, this.props).replyToMessage();
            }
        }
        catch (Exception ex2) {}
        this.setStatusText(this.props.language.getProperty("statusbar.done", "Done."));
        this.setCursor(Cursor.getPredefinedCursor(0));
        ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(0));
        try {
            this.props.server.logout();
        }
        catch (Exception ex3) {}
        this.blockUpdate = false;
    }
    
    void replyallMen_Action(final ActionEvent actionEvent) {
        if (this.messageList.getSelectedRow() == -1) {
            return;
        }
        this.blockUpdate = true;
        this.setCursor(Cursor.getPredefinedCursor(3));
        ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(3));
        this.setStatusText(this.props.language.getProperty("statusbar.getting_original", "Getting original message..."));
        final String cellText = this.messageList.getCellText(this.messageList.getSelectedRow(), 0);
        String s;
        if (cellText.startsWith("  ")) {
            s = cellText.substring(2);
        }
        else if (cellText.startsWith(" ")) {
            s = cellText.substring(1);
        }
        else {
            s = cellText;
        }
        final int intValue = new Integer(s);
        try {
            final String s2 = this.props.uidls[intValue - 1];
            JMessage message = this.props.cache.getMessage(s2);
            if (message == null) {
                this.props.confirmConnection();
                Thread.currentThread();
                while (this.blockMain) {
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (Exception ex) {}
                }
                for (int n = 0; n <= POP3mboxFrame.STRIKES && message == null; message = this.props.server.getMessage(this.props.password, intValue, s2, false), ++n) {}
            }
            if (message == null) {
                this.setStatusText(this.props.language.getProperty("statusbar.message_error", "Message error."));
                final ErrorDlg errorDlg = new ErrorDlg(this, "ERROR!", this.props.server.getErrorMessage(), true);
                errorDlg.setBackground(this.props.background);
                errorDlg.setForeground(this.props.foreground);
                errorDlg.setFont(this.props.font);
                errorDlg.show();
            }
            else {
                this.props.cache.addMessage(message);
                new msgFrame(message, intValue, this.props).replyToAll();
            }
        }
        catch (Exception ex2) {}
        this.setStatusText(this.props.language.getProperty("statusbar.done", "Done."));
        this.setCursor(Cursor.getPredefinedCursor(0));
        ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(0));
        try {
            this.props.server.logout();
        }
        catch (Exception ex3) {}
        this.blockUpdate = false;
    }
    
    void forwardMen_Action(final ActionEvent actionEvent) {
        if (this.messageList.getSelectedRow() == -1) {
            return;
        }
        this.blockUpdate = true;
        this.setCursor(Cursor.getPredefinedCursor(3));
        ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(3));
        this.setStatusText(this.props.language.getProperty("statusbar.getting_original", "Getting original message..."));
        final String cellText = this.messageList.getCellText(this.messageList.getSelectedRow(), 0);
        String s;
        if (cellText.startsWith("  ")) {
            s = cellText.substring(2);
        }
        else if (cellText.startsWith(" ")) {
            s = cellText.substring(1);
        }
        else {
            s = cellText;
        }
        final int intValue = new Integer(s);
        try {
            final String s2 = this.props.uidls[intValue - 1];
            JMessage message = this.props.cache.getMessage(s2);
            if (message == null) {
                this.props.confirmConnection();
                Thread.currentThread();
                while (this.blockMain) {
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (Exception ex) {}
                }
                for (int n = 0; n <= POP3mboxFrame.STRIKES && message == null; message = this.props.server.getMessage(this.props.password, intValue, s2, false), ++n) {}
            }
            if (message == null) {
                this.setStatusText(this.props.language.getProperty("statusbar.message_error", "Message error."));
                final ErrorDlg errorDlg = new ErrorDlg(this, "ERROR!", this.props.server.getErrorMessage(), true);
                errorDlg.setBackground(this.props.background);
                errorDlg.setForeground(this.props.foreground);
                errorDlg.setFont(this.props.font);
                errorDlg.show();
            }
            else {
                this.props.cache.addMessage(message);
                new msgFrame(message, intValue, this.props).forwardMessage();
            }
        }
        catch (Exception ex2) {}
        this.setStatusText(this.props.language.getProperty("statusbar.done", "Done."));
        this.setCursor(Cursor.getPredefinedCursor(0));
        ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(0));
        try {
            this.props.server.logout();
        }
        catch (Exception ex3) {}
        this.blockUpdate = false;
    }
    
    void selectallMen_Action(final ActionEvent actionEvent) {
        this.messageList.selectAll();
    }
    
    void markReadMen_Action(final ActionEvent actionEvent) {
        final int[] selectedRows = this.messageList.getSelectedRows();
        for (int i = 0; i < selectedRows.length; ++i) {
            final int n = selectedRows[i];
            if (!this.messageList.getCellText(n, 1).equals(this.props.statusDeleted)) {
                this.messageList.addTextCell(n, 1, this.props.statusRead);
            }
        }
    }
    
    void markUnreadMen_Action(final ActionEvent actionEvent) {
        final int[] selectedRows = this.messageList.getSelectedRows();
        for (int i = 0; i < selectedRows.length; ++i) {
            final int n = selectedRows[i];
            if (!this.messageList.getCellText(n, 1).equals(this.props.statusDeleted)) {
                this.messageList.addTextCell(n, 1, this.props.statusUnread);
            }
        }
    }
    
    void deletemsgMen_Action(final ActionEvent actionEvent) {
        this.deleteMessage();
    }
    
    void undeletemsgMen_Action(final ActionEvent actionEvent) {
        final int[] selectedRows = this.messageList.getSelectedRows();
        for (int i = 0; i < selectedRows.length; ++i) {
            final int selectedRow = selectedRows[i];
            final String cellText = this.messageList.getCellText(selectedRow, 0);
            String s;
            if (cellText.startsWith("  ")) {
                s = cellText.substring(2);
            }
            else if (cellText.startsWith(" ")) {
                s = cellText.substring(1);
            }
            else {
                s = cellText;
            }
            new Integer(s);
            if (this.messageList.getCellText(selectedRow, 1).equals(this.props.statusDeleted)) {
                try {
                    this.messageList.addTextCell(selectedRow, 1, this.props.statusRecovered);
                    this.messageList.setSelectedRow(selectedRow);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    void addressMen_Action(final ActionEvent actionEvent) {
        this.props.showAddressBook();
    }
    
    void updateBtn_actionPerformed(final ActionEvent actionEvent) {
        try {
            Thread.currentThread();
            while (this.blockMain) {
                try {
                    Thread.sleep(1000L);
                }
                catch (Exception ex2) {}
            }
            this.blockMain = true;
            this.updater.stop();
            this.getNewMessages();
            this.props.server.logout();
            (this.updater = new UpdateMbox()).start();
        }
        catch (Exception ex) {
            System.out.println("ERROR getting new messages: " + ex.toString());
            this.setStatusText(this.props.language.getProperty("statusbar.mailbox_error", "Mailbox error."));
        }
    }
    
    void newBtn_actionPerformed(final ActionEvent actionEvent) {
        new sendFrame(this.props).show();
    }
    
    void readBtn_actionPerformed(final ActionEvent actionEvent) {
        this.readMessage();
    }
    
    void replyBtn_actionPerformed(final ActionEvent actionEvent) {
        this.replyMen_Action(actionEvent);
    }
    
    void fwdBtn_actionPerformed(final ActionEvent actionEvent) {
        this.forwardMen_Action(actionEvent);
    }
    
    void delBtn_actionPerformed(final ActionEvent actionEvent) {
        this.deleteMessage();
    }
    
    void addressBtn_actionPerformed(final ActionEvent actionEvent) {
        this.props.showAddressBook();
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
    
    void displayHeaders(final String[] array) {
        this.blockUpdate = true;
        this.setCursor(Cursor.getPredefinedCursor(3));
        ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(3));
        final String property = this.props.language.getProperty("statusbar.loading", "Loading message information...");
        this.setStatusText(property);
        final Vector<String> vector = new Vector<String>();
        String[] headerList = null;
        Label_0616: {
            if (array == null) {
                try {
                    headerList = null;
                    this.props.confirmConnection();
                    Thread.currentThread();
                    while (this.blockMain) {
                        try {
                            Thread.sleep(1000L);
                        }
                        catch (Exception ex3) {}
                    }
                    final int numberOfMessages = this.props.server.getNumberOfMessages(this.props.password);
                    int n;
                    if (numberOfMessages == 0) {
                        n = 0;
                    }
                    else {
                        n = (numberOfMessages - 1) / 50 + 1;
                    }
                    if (n <= 0) {
                        headerList = new String[0];
                    }
                    else if (n == 1) {
                        for (int i = 0; i <= POP3mboxFrame.STRIKES; ++i) {
                            if (headerList != null) {
                                break;
                            }
                            headerList = this.props.server.getHeaderList(this.props.password);
                        }
                    }
                    else {
                        headerList = new String[numberOfMessages];
                        final String[][] array2 = new String[n][50];
                        for (int j = 0; j < n; ++j) {
                            int n2 = 50 * j;
                            this.setStatusText(String.valueOf(property) + " (" + n2 + "/" + numberOfMessages + ")");
                            final int[] array3 = new int[50];
                            for (int k = 0; k < 50; ++k) {
                                array3[k] = k + 1 + 50 * j;
                            }
                            array2[j] = null;
                            for (int n3 = 0; n3 <= POP3mboxFrame.STRIKES && array2[j] == null; ++n3) {
                                array2[j] = this.props.server.getHeaderList(this.props.password, array3);
                            }
                            for (int n4 = 0; n4 < 50 && n2 < numberOfMessages; ++n2, ++n4) {
                                if (n4 < array2[j].length) {
                                    headerList[n2] = array2[j][n4];
                                }
                            }
                        }
                    }
                    this.blockUpdate = false;
                    if (headerList == null) {
                        this.setStatusText(this.props.language.getProperty("statusbar.mailbox_error", "Mailbox error."));
                        final ErrorDlg errorDlg = new ErrorDlg(this, "ERROR!", this.props.server.getErrorMessage(), true);
                        errorDlg.setBackground(this.props.background);
                        errorDlg.setForeground(this.props.foreground);
                        errorDlg.setFont(this.props.font);
                        errorDlg.show();
                        this.setCursor(Cursor.getPredefinedCursor(0));
                        ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(0));
                        return;
                    }
                    break Label_0616;
                }
                catch (Exception ex) {
                    System.out.println("HEADERS ERROR: " + ex.toString());
                    this.setStatusText(this.props.language.getProperty("statusbar.error_headers", "ERROR, could not display headers."));
                    this.setCursor(Cursor.getPredefinedCursor(0));
                    ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(0));
                    return;
                }
            }
            headerList = array;
        }
        int length;
        int l;
        for (length = headerList.length, l = 0; l < length; ++l) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(headerList[l], POP3mboxFrame.BOUND);
                final String nextToken = stringTokenizer.nextToken();
                final String nextToken2 = stringTokenizer.nextToken();
                final String nextToken3 = stringTokenizer.nextToken();
                final String nextToken4 = stringTokenizer.nextToken();
                final String nextToken5 = stringTokenizer.nextToken();
                final String nextToken6 = stringTokenizer.nextToken();
                final String nextToken7 = stringTokenizer.nextToken();
                this.messageList.addTextCell(l, 0, nextToken);
                this.messageList.addTextCell(l, 1, nextToken2);
                this.messageList.addTextCell(l, 2, nextToken3);
                this.messageList.addTextCell(l, 3, nextToken4);
                this.messageList.addTextCell(l, 4, nextToken5);
                this.messageList.addTextCell(l, 5, nextToken6);
                vector.addElement(nextToken7);
            }
            catch (Exception ex2) {
                System.out.println("ERROR:" + ex2.toString());
            }
        }
        if (l == 1) {
            this.setStatusText("1 " + this.props.language.getProperty("message", "message").toLowerCase() + ".");
        }
        else {
            this.setStatusText(String.valueOf(l) + " " + this.props.language.getProperty("messages", "messages").toLowerCase() + ".");
        }
        final int n5 = l;
        if (n5 > 0) {
            try {
                this.messageList.setSelectedRow(n5 - 1);
            }
            catch (NullPointerException ex4) {}
        }
        ((Component)this.messageList).requestFocus();
        if (array == null) {
            this.props.uidls = new String[length];
            for (int n6 = 0; n6 < length; ++n6) {
                this.props.uidls[n6] = vector.elementAt(n6);
            }
        }
        this.setCursor(Cursor.getPredefinedCursor(0));
        ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(0));
        this.blockUpdate = false;
    }
    
    void displayCachedHeaders() {
        this.setCursor(Cursor.getPredefinedCursor(3));
        ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(3));
        final String property = this.props.language.getProperty("statusbar.loading_stored", "Loading stored mailbox information...");
        this.setStatusText(property);
        String[] cachedHeaders = null;
        this.props.confirmConnection();
        Thread.currentThread();
        while (this.blockMain) {
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex) {}
        }
        try {
            final int numberCachedHeaders = this.props.server.getNumberCachedHeaders(this.props.password);
            cachedHeaders = new String[numberCachedHeaders];
            int n;
            if (numberCachedHeaders == 0) {
                n = 0;
            }
            else {
                n = (numberCachedHeaders - 1) / 50 + 1;
            }
            if (n == 0) {
                cachedHeaders = new String[0];
            }
            else if (n == 1) {
                cachedHeaders = this.props.server.getCachedHeaders(this.props.password);
            }
            else {
                final String[][] array = new String[n][50];
                for (int i = 0; i < n; ++i) {
                    int n2 = 50 * i;
                    this.setStatusText(String.valueOf(property) + " (" + n2 + "/" + numberCachedHeaders + ")");
                    final int[] array2 = new int[50];
                    for (int j = 0; j < 50; ++j) {
                        array2[j] = j + 1 + 50 * i;
                    }
                    array[i] = this.props.server.getCachedHeaders(this.props.password, array2);
                    for (int n3 = 0; n3 < 50 && n2 < numberCachedHeaders; ++n2, ++n3) {
                        if (n3 < array[i].length) {
                            cachedHeaders[n2] = array[i][n3];
                        }
                    }
                }
            }
        }
        catch (Exception ex2) {}
        if (cachedHeaders == null) {
            this.displayHeaders(null);
            return;
        }
        final int length = cachedHeaders.length;
        this.props.uidls = new String[length];
        int k;
        for (k = 0; k < length; ++k) {
            final StringTokenizer stringTokenizer = new StringTokenizer(cachedHeaders[k], POP3mboxFrame.BOUND);
            final String nextToken = stringTokenizer.nextToken();
            String s = stringTokenizer.nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            final String nextToken3 = stringTokenizer.nextToken();
            final String nextToken4 = stringTokenizer.nextToken();
            final String nextToken5 = stringTokenizer.nextToken();
            final String nextToken6 = stringTokenizer.nextToken();
            if (s.equals(this.props.statusNew)) {
                s = this.props.statusUnread;
            }
            else if (s.equals(this.props.statusRecovered)) {
                s = " ";
            }
            this.messageList.addTextCell(k, 0, nextToken);
            this.messageList.addTextCell(k, 1, s);
            this.messageList.addTextCell(k, 2, nextToken2);
            this.messageList.addTextCell(k, 3, nextToken3);
            this.messageList.addTextCell(k, 4, nextToken4);
            this.messageList.addTextCell(k, 5, nextToken5);
            String s2;
            if (nextToken.startsWith("  ")) {
                s2 = nextToken.substring(2);
            }
            else if (nextToken.startsWith(" ")) {
                s2 = nextToken.substring(1);
            }
            else {
                s2 = nextToken;
            }
            this.props.uidls[new Integer(s2) - 1] = nextToken6;
        }
        if (k == 1) {
            this.setStatusText("1 " + this.props.language.getProperty("message", "message").toLowerCase() + ".");
        }
        else {
            this.setStatusText(String.valueOf(k) + " " + this.props.language.getProperty("messages", "messages").toLowerCase() + ".");
        }
        ((Component)this.messageList).requestFocus();
        this.setCursor(Cursor.getPredefinedCursor(0));
        ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void deleteMessage() {
        final int[] selectedRows = this.messageList.getSelectedRows();
        for (int i = 0; i < selectedRows.length; ++i) {
            final int n = selectedRows[i];
            final String cellText = this.messageList.getCellText(n, 0);
            final String cellText2 = this.messageList.getCellText(n, 1);
            String s;
            if (cellText.startsWith("  ")) {
                s = cellText.substring(2);
            }
            else if (cellText.startsWith(" ")) {
                s = cellText.substring(1);
            }
            else {
                s = cellText;
            }
            new Integer(s);
            if (cellText2.equals(this.props.statusDeleted)) {
                try {
                    this.messageList.addTextCell(n, 1, this.props.statusRecovered);
                    this.messageList.setSelectedRow(n);
                }
                catch (Exception ex) {}
            }
            else {
                try {
                    this.messageList.addTextCell(n, 1, this.props.statusDeleted);
                    this.messageList.setSelectedRow(n);
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    private void updateMailbox() {
        try {
            this.messageList.clear();
            this.displayHeaders(null);
        }
        catch (Exception ex) {
            this.setStatusText(this.props.language.getProperty("statusbar.error_update", "Update Error."));
        }
    }
    
    void readMessage() {
        if (this.messageList.getSelectedRow() == -1) {
            return;
        }
        this.blockUpdate = true;
        this.setCursor(Cursor.getPredefinedCursor(3));
        ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(3));
        this.setStatusText(this.props.language.getProperty("statusbar.getting_message", "Getting message..."));
        final int selectedRow = this.messageList.getSelectedRow();
        final String cellText = this.messageList.getCellText(selectedRow, 0);
        String s;
        if (cellText.startsWith("  ")) {
            s = cellText.substring(2);
        }
        else if (cellText.startsWith(" ")) {
            s = cellText.substring(1);
        }
        else {
            s = cellText;
        }
        final int intValue = new Integer(s);
        try {
            final String s2 = this.props.uidls[intValue - 1];
            JMessage message = this.props.cache.getMessage(s2);
            if (message != null) {
                if (message.getNumAtts() > 0 && this.props.connected) {
                    boolean writeAttachments = false;
                    this.props.confirmConnection();
                    Thread.currentThread();
                    while (this.blockMain) {
                        try {
                            Thread.sleep(1000L);
                        }
                        catch (Exception ex2) {}
                    }
                    for (int i = 0; i <= POP3mboxFrame.STRIKES; ++i) {
                        if (writeAttachments) {
                            break;
                        }
                        writeAttachments = this.props.server.writeAttachments(this.props.password, intValue, s2);
                    }
                }
            }
            else {
                this.props.confirmConnection();
                Thread.currentThread();
                while (this.blockMain) {
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (Exception ex3) {}
                }
                for (int n = 0; n <= POP3mboxFrame.STRIKES && message == null; message = this.props.server.getMessage(this.props.password, intValue, s2, true), ++n) {}
            }
            if (message == null) {
                this.setStatusText(this.props.language.getProperty("statusbar.message_error", "Message error."));
                final ErrorDlg errorDlg = new ErrorDlg(this, "ERROR!", this.props.server.getErrorMessage(), true);
                errorDlg.setBackground(this.props.background);
                errorDlg.setForeground(this.props.foreground);
                errorDlg.setFont(this.props.font);
                this.setCursor(Cursor.getPredefinedCursor(0));
                ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(0));
                errorDlg.show();
            }
            else {
                this.props.cache.addMessage(message);
                final msgFrame msgFrame = new msgFrame(this, intValue, message, this.props);
                this.setCursor(Cursor.getPredefinedCursor(0));
                ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(0));
                this.setStatusText(this.props.language.getProperty("statusbar.done", "Done."));
                msgFrame.show();
                if (!this.messageList.getCellText(selectedRow, 1).equals(this.props.statusDeleted)) {
                    this.messageList.addTextCell(selectedRow, 1, this.props.statusRead);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("ERROR: " + ex.toString());
            this.setCursor(Cursor.getPredefinedCursor(0));
            ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(0));
            this.setStatusText(this.props.language.getProperty("statusbar.message_error", "Message error."));
        }
        try {
            this.props.server.logout();
        }
        catch (Exception ex4) {}
        this.blockUpdate = false;
    }
    
    public void markAsRead(final int n) {
        String s;
        if (n >= 100) {
            s = String.valueOf(n);
        }
        else if (n >= 10) {
            s = " " + n;
        }
        else {
            s = "  " + n;
        }
        final int numberOfRows = this.messageList.getNumberOfRows();
        boolean b = false;
        int n2 = 0;
        for (int n3 = 0; n3 < numberOfRows && !b; ++n3) {
            if (this.messageList.getCellText(n3, 0).equals(s)) {
                b = true;
                n2 = n3;
            }
        }
        if (b && !this.messageList.getCellText(n2, 1).equals(this.props.statusDeleted)) {
            this.messageList.addTextCell(n2, 1, this.props.statusRead);
        }
    }
    
    public void markAsUnread(final int n) {
        String s;
        if (n >= 100) {
            s = String.valueOf(n);
        }
        else if (n >= 10) {
            s = " " + n;
        }
        else {
            s = "  " + n;
        }
        final int numberOfRows = this.messageList.getNumberOfRows();
        boolean b = false;
        int n2 = 0;
        for (int n3 = 0; n3 < numberOfRows && !b; ++n3) {
            if (this.messageList.getCellText(n3, 0).equals(s)) {
                b = true;
                n2 = n3;
            }
        }
        if (b && !this.messageList.getCellText(n2, 1).equals(this.props.statusDeleted)) {
            this.messageList.addTextCell(n2, 1, this.props.statusUnread);
        }
    }
    
    public void deleteMessage(final int n) {
        String s;
        if (n >= 100) {
            s = String.valueOf(n);
        }
        else if (n >= 10) {
            s = " " + n;
        }
        else {
            s = "  " + n;
        }
        final int numberOfRows = this.messageList.getNumberOfRows();
        boolean b = false;
        int selectedRow = 0;
        for (int n2 = 0; n2 < numberOfRows && !b; ++n2) {
            if (this.messageList.getCellText(n2, 0).equals(s)) {
                b = true;
                selectedRow = n2;
            }
        }
        if (b && !this.messageList.getCellText(selectedRow, 1).equals(this.props.statusDeleted)) {
            try {
                this.messageList.setSelectedRow(selectedRow);
                this.messageList.addTextCell(selectedRow, 1, this.props.statusDeleted);
            }
            catch (Exception ex) {
                System.out.println("DELETE ERROR: " + ex.toString());
            }
        }
    }
    
    public AppletContext getBrowser() {
        return this.browser;
    }
    
    void helpMen_Action(final ActionEvent actionEvent) {
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
    
    void aboutMen_Action(final ActionEvent actionEvent) {
        new AboutFrame(this.props).show();
    }
    
    void saveProps() {
        this.setStatusText(this.props.language.getProperty("statusbar.saving_prefs", "Saving preferences..."));
        final Dimension size = this.size();
        this.props.mailboxWidth = size.width - 8;
        if (this.props.NETSCAPE) {
            this.props.mailboxHeight = size.height - 64;
        }
        else {
            this.props.mailboxHeight = size.height - 46;
        }
        this.props.mailboxName = "Inbox";
        final Point location = this.location();
        this.props.mailboxPosX = location.x;
        this.props.mailboxPosY = location.y;
        this.props.saveProperties();
    }
    
    void saveMessageHeaders() {
        this.setStatusText(this.props.language.getProperty("statusbar.saving_headers", "Saving message headers..."));
        this.props.saveHeaders(this.getHeaderLines());
    }
    
    void prefMen_Action(final ActionEvent actionEvent) {
        this.props.propFrame.show();
    }
    
    void prefstoolMen_Action(final ActionEvent actionEvent) {
        this.props.propFrame.show();
    }
    
    void updateBtn_focusGained(final FocusEvent focusEvent) {
        ((Component)this.messageList).requestFocus();
    }
    
    void newBtn_focusGained(final FocusEvent focusEvent) {
        ((Component)this.messageList).requestFocus();
    }
    
    void readBtn_focusGained(final FocusEvent focusEvent) {
        ((Component)this.messageList).requestFocus();
    }
    
    void replyBtn_focusGained(final FocusEvent focusEvent) {
        ((Component)this.messageList).requestFocus();
    }
    
    void fwdBtn_focusGained(final FocusEvent focusEvent) {
        ((Component)this.messageList).requestFocus();
    }
    
    void delBtn_focusGained(final FocusEvent focusEvent) {
        ((Component)this.messageList).requestFocus();
    }
    
    void addressBtn_focusGained(final FocusEvent focusEvent) {
        ((Component)this.messageList).requestFocus();
    }
    
    void helpBtn_focusGained(final FocusEvent focusEvent) {
        ((Component)this.messageList).requestFocus();
    }
    
    void getNewMessages() throws Exception {
        this.setStatusText(this.props.language.getProperty("statusbar.checking", "Checking for new mail..."));
        final int numberOfRows = this.messageList.getNumberOfRows();
        if (numberOfRows > 0 && !this.verifyNumbers()) {
            this.messageList.clear();
            this.displayHeaders(null);
            return;
        }
        int numberOfMessages = -1;
        this.props.confirmConnection();
        for (int n = 0; n <= POP3mboxFrame.STRIKES && numberOfMessages == -1; numberOfMessages = this.props.server.getNumberOfMessages(this.props.password), ++n) {}
        if (numberOfMessages < numberOfRows) {
            this.setStatusText(this.props.language.getProperty("statusbar.error_sync", "Synchronzation error, rebuilding message list..."));
            this.updateMailbox();
            return;
        }
        if (numberOfMessages > numberOfRows) {
            final int n2 = numberOfMessages - numberOfRows;
            final String property = this.props.language.getProperty("statusbar.geting_message", "Found _X_ new messages, getting headers...");
            final int index = property.indexOf("_X_");
            this.setStatusText(String.valueOf(property.substring(0, index)) + n2 + property.substring(index + 3));
            final Vector vector = new Vector<String>();
            for (int length = this.props.uidls.length, i = 0; i < length; ++i) {
                vector.addElement(this.props.uidls[i]);
            }
            this.props.confirmConnection();
            for (int j = numberOfRows; j < numberOfMessages; ++j) {
                String header = null;
                for (int n3 = 0; n3 <= POP3mboxFrame.STRIKES && header == null; header = this.props.server.getHeader(this.props.password, j + 1), ++n3) {}
                if (header == null) {
                    final ErrorDlg errorDlg = new ErrorDlg(this, "ERROR!", this.props.server.getErrorMessage(), true);
                    errorDlg.setBackground(this.props.background);
                    errorDlg.setForeground(this.props.foreground);
                    errorDlg.setFont(this.props.font);
                    errorDlg.show();
                    return;
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(header, POP3mboxFrame.BOUND);
                final String nextToken = stringTokenizer.nextToken();
                stringTokenizer.nextToken();
                final String nextToken2 = stringTokenizer.nextToken();
                final String nextToken3 = stringTokenizer.nextToken();
                final String nextToken4 = stringTokenizer.nextToken();
                final String nextToken5 = stringTokenizer.nextToken();
                final String nextToken6 = stringTokenizer.nextToken();
                this.messageList.addTextCell(j, 0, nextToken);
                this.messageList.addTextCell(j, 1, this.props.statusNew);
                this.messageList.addTextCell(j, 2, nextToken2);
                this.messageList.addTextCell(j, 3, nextToken3);
                this.messageList.addTextCell(j, 4, nextToken4);
                this.messageList.addTextCell(j, 5, nextToken5);
                vector.addElement(nextToken6);
            }
            final int size = vector.size();
            this.props.uidls = new String[size];
            for (int k = 0; k < size; ++k) {
                this.props.uidls[k] = vector.elementAt(k);
            }
            final int countNewMessages = this.countNewMessages();
            final String property2 = this.props.language.getProperty("statusbar.total", "_X_ new messages (_X_ total).");
            final int lastIndex = property2.lastIndexOf("_X_");
            this.setStatusText(String.valueOf(countNewMessages) + property2.substring(3, lastIndex) + numberOfMessages + property2.substring(lastIndex + 3));
            return;
        }
        this.setStatusText(this.props.language.getProperty("statusbar.no_new", "No new messages."));
    }
    
    public void setStatusText(final String s) {
        this.statusLbl.setText(" " + s);
    }
    
    void commitDeletes() {
        this.blockUpdate = true;
        final int numberOfRows = this.messageList.getNumberOfRows();
        final int[] array = new int[numberOfRows];
        int n = 0;
        for (int i = 0; i < numberOfRows; ++i) {
            final int n2 = i;
            if (this.messageList.getCellText(n2, 1).equals(this.props.statusDeleted)) {
                final String cellText = this.messageList.getCellText(n2, 0);
                String s;
                if (cellText.startsWith("  ")) {
                    s = cellText.substring(2);
                }
                else if (cellText.startsWith(" ")) {
                    s = cellText.substring(1);
                }
                else {
                    s = cellText;
                }
                array[n] = new Integer(s);
                ++n;
            }
        }
        if (n > 0) {
            try {
                final int[] array2 = new int[n];
                for (int j = 0; j < n; ++j) {
                    array2[j] = array[j];
                }
                this.setStatusText(this.props.language.getProperty("statusbar.deleting", "Deleting messages from the server..."));
                final int length = this.props.uidls.length;
                final String s2 = this.props.uidls[length - 1];
                this.props.confirmConnection();
                Thread.currentThread();
                while (this.blockMain) {
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (Exception ex) {}
                }
                this.props.server.cleanup(this.props.password, array2, length, s2);
            }
            catch (Exception ex2) {
                System.out.println("ERROR, could not delete messages.");
            }
        }
        this.setStatusText(this.props.language.getProperty("statusbar.done", "Done."));
        this.blockUpdate = false;
    }
    
    boolean verifyNumbers() {
        this.blockUpdate = true;
        final int length = this.props.uidls.length;
        final String s = this.props.uidls[length - 1];
        boolean verifyMessage = false;
        this.props.confirmConnection();
        try {
            for (int i = 0; i <= POP3mboxFrame.STRIKES; ++i) {
                if (verifyMessage) {
                    break;
                }
                verifyMessage = this.props.server.verifyMessage(this.props.password, length, s);
            }
        }
        catch (Exception ex) {}
        this.blockUpdate = false;
        return verifyMessage;
    }
    
    public String[] getHeaderLines() {
        final int numberOfRows = this.messageList.getNumberOfRows();
        final String[] array = new String[numberOfRows];
        for (int i = 0; i < numberOfRows; ++i) {
            final String cellText = this.messageList.getCellText(i, 0);
            final String cellText2 = this.messageList.getCellText(i, 1);
            final String cellText3 = this.messageList.getCellText(i, 2);
            final String cellText4 = this.messageList.getCellText(i, 3);
            final String cellText5 = this.messageList.getCellText(i, 4);
            final String cellText6 = this.messageList.getCellText(i, 5);
            String s;
            if (cellText.startsWith("  ")) {
                s = cellText.substring(2);
            }
            else if (cellText.startsWith(" ")) {
                s = cellText.substring(1);
            }
            else {
                s = cellText;
            }
            final String s2 = this.props.uidls[new Integer(s) - 1];
            final StringBuffer sb = new StringBuffer();
            sb.append(cellText);
            sb.append(POP3mboxFrame.BOUND);
            sb.append(cellText2);
            sb.append(POP3mboxFrame.BOUND);
            sb.append(cellText3);
            sb.append(POP3mboxFrame.BOUND);
            sb.append(cellText4);
            sb.append(POP3mboxFrame.BOUND);
            sb.append(cellText5);
            sb.append(POP3mboxFrame.BOUND);
            sb.append(cellText6);
            sb.append(POP3mboxFrame.BOUND);
            sb.append(s2);
            array[i] = sb.toString();
        }
        return array;
    }
    
    public String getCachedHeaderLine(final int n) {
        final String cellText = this.messageList.getCellText(n, 0);
        final String cellText2 = this.messageList.getCellText(n, 1);
        final String cellText3 = this.messageList.getCellText(n, 2);
        final String cellText4 = this.messageList.getCellText(n, 3);
        final String cellText5 = this.messageList.getCellText(n, 4);
        final String cellText6 = this.messageList.getCellText(n, 5);
        String s;
        if (cellText.startsWith("  ")) {
            s = cellText.substring(2);
        }
        else if (cellText.startsWith(" ")) {
            s = cellText.substring(1);
        }
        else {
            s = cellText;
        }
        final String s2 = this.props.uidls[new Integer(s) - 1];
        final StringBuffer sb = new StringBuffer();
        sb.append(cellText);
        sb.append(POP3mboxFrame.BOUND);
        sb.append(cellText2);
        sb.append(POP3mboxFrame.BOUND);
        sb.append(cellText3);
        sb.append(POP3mboxFrame.BOUND);
        sb.append(cellText4);
        sb.append(POP3mboxFrame.BOUND);
        sb.append(cellText5);
        sb.append(POP3mboxFrame.BOUND);
        sb.append(cellText6);
        sb.append(POP3mboxFrame.BOUND);
        sb.append(s2);
        return sb.toString();
    }
    
    void POP3mboxFrame_ComponentResized(final ComponentEvent componentEvent) {
        try {
            this.messageList.setCellFont(this.props.font);
            this.messageList.setHeadingBg(this.props.background);
            this.messageList.setHeadingFg(this.props.foreground);
            this.messageList.setHeadingFont(this.props.font);
        }
        catch (Exception ex) {}
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
                    imageURL = new URL(this.props.base, "client/images/update24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL = new URL(this.props.base, "client/images/update76.gif");
                }
                else {
                    imageURL = new URL(this.props.base, "client/images/update.gif");
                }
                this.updateBtn.setImageURL(imageURL);
            }
            catch (MalformedURLException ex5) {}
            catch (PropertyVetoException ex6) {}
            ((Component)this.updateBtn).setBounds(0, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.updateBtn);
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
            catch (MalformedURLException ex7) {}
            catch (PropertyVetoException ex8) {}
            ((Component)this.newBtn).setBounds(46, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.newBtn);
            try {
                URL imageURL3;
                if (this.props.buttonSize == 28) {
                    imageURL3 = new URL(this.props.base, "client/images/read24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL3 = new URL(this.props.base, "client/images/read76.gif");
                }
                else {
                    imageURL3 = new URL(this.props.base, "client/images/read.gif");
                }
                this.readBtn.setImageURL(imageURL3);
            }
            catch (MalformedURLException ex9) {}
            catch (PropertyVetoException ex10) {}
            ((Component)this.readBtn).setBounds(0, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.readBtn);
            try {
                URL imageURL4;
                if (this.props.buttonSize == 28) {
                    imageURL4 = new URL(this.props.base, "client/images/reply24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL4 = new URL(this.props.base, "client/images/reply76.gif");
                }
                else {
                    imageURL4 = new URL(this.props.base, "client/images/reply.gif");
                }
                this.replyBtn.setImageURL(imageURL4);
            }
            catch (MalformedURLException ex11) {}
            catch (PropertyVetoException ex12) {}
            ((Component)this.replyBtn).setBounds(102, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.replyBtn);
            try {
                URL imageURL5;
                if (this.props.buttonSize == 28) {
                    imageURL5 = new URL(this.props.base, "client/images/forward24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL5 = new URL(this.props.base, "client/images/forward76.gif");
                }
                else {
                    imageURL5 = new URL(this.props.base, "client/images/forward.gif");
                }
                this.fwdBtn.setImageURL(imageURL5);
            }
            catch (MalformedURLException ex13) {}
            catch (PropertyVetoException ex14) {}
            ((Component)this.fwdBtn).setBounds(148, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.fwdBtn);
            try {
                URL imageURL6;
                if (this.props.buttonSize == 28) {
                    imageURL6 = new URL(this.props.base, "client/images/delete24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL6 = new URL(this.props.base, "client/images/delete76.gif");
                }
                else {
                    imageURL6 = new URL(this.props.base, "client/images/delete.gif");
                }
                this.delBtn.setImageURL(imageURL6);
            }
            catch (MalformedURLException ex15) {}
            catch (PropertyVetoException ex16) {}
            ((Component)this.delBtn).setBounds(194, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.delBtn);
            try {
                URL imageURL7;
                if (this.props.buttonSize == 28) {
                    imageURL7 = new URL(this.props.base, "client/images/address24.gif");
                }
                else if (this.props.buttonSize == 78) {
                    imageURL7 = new URL(this.props.base, "client/images/address76.gif");
                }
                else {
                    imageURL7 = new URL(this.props.base, "client/images/address.gif");
                }
                this.addressBtn.setImageURL(imageURL7);
            }
            catch (MalformedURLException ex17) {}
            catch (PropertyVetoException ex18) {}
            ((Component)this.addressBtn).setBounds(262, 0, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.addressBtn);
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
            ((Component)this.helpBtn).setBounds(308, 20, 46, 46);
            ((Container)this.toolBarPanel1).add((Component)this.helpBtn);
            this.add(this.props.toolbarSide, (Component)this.toolBarPanel1);
            this.placement = this.props.toolbarSide;
            this.buttonSize = this.props.buttonSize;
            return;
        }
        if (!this.placement.equalsIgnoreCase(this.props.toolbarSide)) {
            this.remove((Component)this.toolBarPanel1);
            ((BorderPanel)this.toolBarPanel1).setBounds(0, 0, this.props.buttonSize, this.props.buttonSize);
            this.add(this.props.toolbarSide, (Component)this.toolBarPanel1);
            this.placement = this.props.toolbarSide;
        }
    }
    
    int countNewMessages() {
        final int numberOfRows = this.messageList.getNumberOfRows();
        int n = 0;
        for (int i = 0; i < numberOfRows; ++i) {
            if (this.messageList.getCellText(i, 1).equals(this.props.statusNew)) {
                ++n;
            }
        }
        return n;
    }
    
    String[] cacheMessages() {
        final int numberOfRows = this.messageList.getNumberOfRows();
        final Vector vector = new Vector<Integer>();
        for (int i = 0; i < numberOfRows; ++i) {
            final Integer n = new Integer(i);
            final String cellText = this.messageList.getCellText(i, 1);
            if (cellText.equals(this.props.statusNew) && this.props.cacheNew) {
                vector.addElement(n);
            }
            else if (cellText.equals(this.props.statusUnread) && this.props.cacheUnread) {
                vector.addElement(n);
            }
            else if (cellText.equals(this.props.statusRead) && this.props.cacheRead) {
                vector.addElement(n);
            }
            else if (cellText.equals(this.props.statusRecovered) && this.props.cacheRecovered) {
                vector.addElement(n);
            }
            else if (cellText.trim().equals("") && this.props.cacheUnmarked) {
                vector.addElement(n);
            }
        }
        final int size = vector.size();
        final String[] array = new String[size];
        final Vector vector2 = new Vector<Integer>();
        final JMCache cache = new JMCache();
        this.newUidls = new String[this.props.uidls.length];
        for (int j = 0; j < size; ++j) {
            final int intValue = vector.elementAt(j);
            array[j] = this.getCachedHeaderLine(intValue);
            final String cellText2 = this.messageList.getCellText(intValue, 0);
            String s;
            if (cellText2.startsWith("  ")) {
                s = cellText2.substring(2);
            }
            else if (cellText2.startsWith(" ")) {
                s = cellText2.substring(1);
            }
            else {
                s = cellText2;
            }
            final Integer n2 = new Integer(s);
            final int intValue2 = n2;
            final String s2 = this.props.uidls[intValue2 - 1];
            this.newUidls[intValue2 - 1] = s2;
            if (!this.props.cache.isInCache(s2)) {
                vector2.addElement(n2);
            }
            else {
                cache.addMessage(this.props.cache.getMessage(s2));
            }
        }
        final int size2 = vector2.size();
        if (size2 > 0) {
            try {
                final int[] array2 = new int[size2];
                for (int k = 0; k < size2; ++k) {
                    array2[k] = vector2.elementAt(k);
                }
                final int n3 = size2 / 10 + 1;
                JMessage[] messages;
                if (n3 == 1) {
                    messages = this.props.server.getMessages(this.props.password, array2, false);
                }
                else {
                    final int[][] array3 = new int[n3][10];
                    final JMessage[][] array4 = new JMessage[n3][10];
                    for (int l = 1; l <= n3; ++l) {
                        final int n4 = l - 1;
                        int n5 = n4 * 10;
                        for (int n6 = 0; n6 < 10; ++n6) {
                            if (n5 < size2) {
                                array3[n4][n6] = array2[n5];
                            }
                            ++n5;
                        }
                    }
                    for (int n7 = 0; n7 < n3; ++n7) {
                        final StringBuffer sb = new StringBuffer(this.props.language.getProperty("statusbar.downloading", "Downloading messages for disconnected mode..."));
                        sb.append(' ');
                        sb.append('(');
                        sb.append(n7 * 10);
                        sb.append('/');
                        sb.append(size2);
                        sb.append(')');
                        this.setStatusText(sb.toString());
                        try {
                            array4[n7] = this.props.server.getMessages(this.props.password, array3[n7], false);
                        }
                        catch (Exception ex) {
                            System.out.println("ERROR downloading messages.");
                        }
                    }
                    messages = new JMessage[size2];
                    for (int n8 = 1; n8 <= n3; ++n8) {
                        final int n9 = n8 - 1;
                        int n10 = n9 * 10;
                        for (int n11 = 0; n11 < 10; ++n11) {
                            if (array4[n9][n11] != null) {
                                messages[n10] = array4[n9][n11];
                            }
                            ++n10;
                        }
                    }
                }
                for (final JMessage message : messages) {
                    if (message != null) {
                        cache.addMessage(message);
                    }
                }
            }
            catch (Exception ex2) {}
        }
        this.props.cache = cache;
        return array;
    }
    
    void disableGui() {
        this.disconnectMen.setLabel("Connect");
        ((ButtonBase)this.updateBtn).setEnabled(false);
        ((ButtonBase)this.delBtn).setEnabled(false);
        this.checkMen.setEnabled(false);
        this.rebuildMen.setEnabled(false);
        this.deletemsgMen.setEnabled(false);
        this.undeletemsgMen.setEnabled(false);
    }
    
    public void closeMailbox() {
        if (!this.props.connected) {
            this.hide();
            return;
        }
        this.setCursor(Cursor.getPredefinedCursor(3));
        ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(3));
        this.updater.stop();
        this.saveProps();
        this.saveMessageHeaders();
        this.props.closeMailbox();
        this.hide();
        this.props.mbox = null;
        this.setStatusText(this.props.language.getProperty("statusbar.done", "Done."));
        this.setCursor(Cursor.getPredefinedCursor(0));
        ((Component)this.messageList).setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public int getNextNum(final int n) {
        String s;
        if (n >= 100) {
            s = String.valueOf(n);
        }
        else if (n >= 10) {
            s = " " + n;
        }
        else {
            s = "  " + n;
        }
        final int numberOfRows = this.messageList.getNumberOfRows();
        boolean b = false;
        int n2 = 0;
        for (int n3 = 0; n3 < numberOfRows && !b; ++n3) {
            if (this.messageList.getCellText(n3, 0).equals(s)) {
                b = true;
                n2 = n3;
            }
        }
        if (b && n2 < numberOfRows - 1) {
            final String cellText = this.messageList.getCellText(n2 + 1, 0);
            String s2;
            if (cellText.startsWith("  ")) {
                s2 = cellText.substring(2);
            }
            else if (cellText.startsWith(" ")) {
                s2 = cellText.substring(1);
            }
            else {
                s2 = cellText;
            }
            return new Integer(s2);
        }
        return -1;
    }
    
    public int getPreviousNum(final int n) {
        String s;
        if (n >= 100) {
            s = String.valueOf(n);
        }
        else if (n >= 10) {
            s = " " + n;
        }
        else {
            s = "  " + n;
        }
        final int numberOfRows = this.messageList.getNumberOfRows();
        boolean b = false;
        int n2 = 0;
        for (int n3 = 0; n3 < numberOfRows && !b; ++n3) {
            if (this.messageList.getCellText(n3, 0).equals(s)) {
                b = true;
                n2 = n3;
            }
        }
        if (b && n2 > 0) {
            final String cellText = this.messageList.getCellText(n2 - 1, 0);
            String s2;
            if (cellText.startsWith("  ")) {
                s2 = cellText.substring(2);
            }
            else if (cellText.startsWith(" ")) {
                s2 = cellText.substring(1);
            }
            else {
                s2 = cellText;
            }
            return new Integer(s2);
        }
        return -1;
    }
    
    static {
        POP3mboxFrame.STRIKES = 3;
        POP3mboxFrame.BOUND = "\t";
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowOpened(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == POP3mboxFrame.this) {
                POP3mboxFrame.this.POP3mboxFrame_WindowOpen(windowEvent);
            }
        }
        
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == POP3mboxFrame.this) {
                POP3mboxFrame.this.POP3mboxFrame_WindowClosing(windowEvent);
            }
        }
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == POP3mboxFrame.this.readBtn) {
                POP3mboxFrame.this.readBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.messageList) {
                POP3mboxFrame.this.messageList_actionPerformed(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.newMen) {
                POP3mboxFrame.this.newMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.newmsgMen) {
                POP3mboxFrame.this.newmsgMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.quitMen) {
                POP3mboxFrame.this.quitMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.disconnectMen) {
                POP3mboxFrame.this.disconnect();
                return;
            }
            if (source == POP3mboxFrame.this.newBtn) {
                POP3mboxFrame.this.newBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.replyBtn) {
                POP3mboxFrame.this.replyBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.fwdBtn) {
                POP3mboxFrame.this.fwdBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.delBtn) {
                POP3mboxFrame.this.delBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.replyMen) {
                POP3mboxFrame.this.replyMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.replyallMen) {
                POP3mboxFrame.this.replyallMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.forwardMen) {
                POP3mboxFrame.this.forwardMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.updateBtn) {
                POP3mboxFrame.this.checkMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.checkMen) {
                POP3mboxFrame.this.checkMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.rebuildMen) {
                POP3mboxFrame.this.rebuildMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.selectallMen) {
                POP3mboxFrame.this.selectallMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.addressBtn) {
                POP3mboxFrame.this.addressBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.addressMen) {
                POP3mboxFrame.this.addressMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.undeletemsgMen) {
                POP3mboxFrame.this.undeletemsgMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.deletemsgMen) {
                POP3mboxFrame.this.deletemsgMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.readMen) {
                POP3mboxFrame.this.readMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.markReadMen) {
                POP3mboxFrame.this.markReadMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.markUnreadMen) {
                POP3mboxFrame.this.markUnreadMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.helpBtn) {
                POP3mboxFrame.this.helpBtn_actionPerformed(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.helpMen) {
                POP3mboxFrame.this.helpMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.aboutMen) {
                POP3mboxFrame.this.aboutMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.prefMen) {
                POP3mboxFrame.this.prefMen_Action(actionEvent);
                return;
            }
            if (source == POP3mboxFrame.this.prefstoolMen) {
                POP3mboxFrame.this.prefstoolMen_Action(actionEvent);
            }
        }
    }
    
    class SymKey extends KeyAdapter
    {
        public void keyReleased(final KeyEvent keyEvent) {
            if (keyEvent.getSource() == POP3mboxFrame.this.messageList) {
                POP3mboxFrame.this.messageList_keyReleased(keyEvent);
            }
        }
        
        public void keyPressed(final KeyEvent keyEvent) {
            if (keyEvent.getSource() == POP3mboxFrame.this.messageList) {
                POP3mboxFrame.this.messageList_keyPressed(keyEvent);
            }
        }
    }
    
    class UpdateMbox extends Thread
    {
        public void run() {
            while (true) {
                try {
                    while (true) {
                        Thread.sleep(60000 * POP3mboxFrame.this.props.interval);
                        if (POP3mboxFrame.this.props.useInterval) {
                            while (POP3mboxFrame.this.blockUpdate) {
                                Thread.sleep(5000L);
                            }
                            POP3mboxFrame.this.blockMain = true;
                            POP3mboxFrame.this.getNewMessages();
                            POP3mboxFrame.this.props.server.logout();
                            Thread.sleep(1000L);
                            POP3mboxFrame.this.blockMain = false;
                        }
                    }
                }
                catch (Exception ex) {
                    System.out.println("ERROR: " + ex.toString());
                    POP3mboxFrame.this.blockMain = false;
                    continue;
                }
                break;
            }
        }
    }
    
    class SymFocus extends FocusAdapter
    {
        public void focusGained(final FocusEvent focusEvent) {
            final Object source = focusEvent.getSource();
            if (source == POP3mboxFrame.this.updateBtn) {
                POP3mboxFrame.this.updateBtn_focusGained(focusEvent);
                return;
            }
            if (source == POP3mboxFrame.this.newBtn) {
                POP3mboxFrame.this.newBtn_focusGained(focusEvent);
                return;
            }
            if (source == POP3mboxFrame.this.readBtn) {
                POP3mboxFrame.this.readBtn_focusGained(focusEvent);
                return;
            }
            if (source == POP3mboxFrame.this.replyBtn) {
                POP3mboxFrame.this.replyBtn_focusGained(focusEvent);
                return;
            }
            if (source == POP3mboxFrame.this.fwdBtn) {
                POP3mboxFrame.this.fwdBtn_focusGained(focusEvent);
                return;
            }
            if (source == POP3mboxFrame.this.delBtn) {
                POP3mboxFrame.this.delBtn_focusGained(focusEvent);
                return;
            }
            if (source == POP3mboxFrame.this.addressBtn) {
                POP3mboxFrame.this.addressBtn_focusGained(focusEvent);
                return;
            }
            if (source == POP3mboxFrame.this.helpBtn) {
                POP3mboxFrame.this.helpBtn_focusGained(focusEvent);
            }
        }
    }
    
    class SymComponent extends ComponentAdapter
    {
        public void componentResized(final ComponentEvent componentEvent) {
            if (componentEvent.getSource() == POP3mboxFrame.this) {
                POP3mboxFrame.this.POP3mboxFrame_ComponentResized(componentEvent);
            }
        }
    }
}
