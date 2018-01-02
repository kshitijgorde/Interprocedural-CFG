import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.util.StringTokenizer;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.CardLayout;
import java.util.Properties;
import COM.jscape.mailwidgets.JSConnection;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MMLapplet extends Applet
{
    static boolean DEBUG;
    static int STRIKES;
    Panel panel1;
    Panel panel3;
    Panel panel5;
    Label usernameLbl;
    Label passwordLbl;
    Panel panel6;
    TextField usernameTxt;
    TextField passwordTxt;
    Panel panel4;
    Button loginBtn;
    Label statusLbl;
    Panel panel2;
    Label mailLbl;
    Panel panel7;
    Button newBtn;
    Button mboxBtn;
    Button addressBtn;
    Button propsBtn;
    Button helpBtn;
    Button logoutBtn;
    JSConnection connection;
    String smtpHost;
    String popHost;
    String username;
    String password;
    String fromAddress;
    JMProps props;
    PopMailServer server;
    BrokerServer broker;
    boolean pop3;
    boolean imap4;
    POP3mboxFrame mbox;
    Properties language;
    boolean bypassLogin;
    boolean appletViewer;
    boolean allowAttachments;
    boolean serverSend;
    boolean proxyMode;
    String defUsername;
    String defPassword;
    String hostDotDomain;
    String languageFile;
    String poUrl;
    
    public void init() {
        this.setLayout(new CardLayout(0, 0));
        this.setSize(325, 135);
        this.setBackground(new Color(16777215));
        (this.panel1 = new Panel()).setLayout(new BorderLayout(0, 0));
        this.panel1.setBounds(0, 0, 325, 116);
        this.add("card5", this.panel1);
        (this.panel3 = new Panel()).setLayout(new BorderLayout(0, 0));
        this.panel3.setBounds(0, 0, 325, 0);
        this.panel1.add("North", this.panel3);
        (this.panel5 = new Panel()).setLayout(new GridLayout(2, 1, 0, 0));
        this.panel5.setBounds(0, 0, 0, 10);
        this.panel3.add("West", this.panel5);
        (this.usernameLbl = new Label("Username:")).setBounds(0, 0, 14, 24);
        this.panel5.add(this.usernameLbl);
        (this.passwordLbl = new Label("Password:")).setBounds(0, 24, 76, 24);
        this.panel5.add(this.passwordLbl);
        (this.panel6 = new Panel()).setLayout(new GridLayout(2, 1, 0, 0));
        this.panel6.setBounds(76, 0, 249, 48);
        this.panel3.add("Center", this.panel6);
        (this.usernameTxt = new TextField()).setBounds(0, 0, 249, 24);
        this.panel6.add(this.usernameTxt);
        (this.passwordTxt = new TextField()).setEchoChar('*');
        this.passwordTxt.setBounds(0, 24, 249, 24);
        this.panel6.add(this.passwordTxt);
        (this.panel4 = new Panel()).setLayout(new FlowLayout(1, 5, 5));
        this.panel4.setBounds(0, 82, 325, 10);
        this.panel1.add("South", this.panel4);
        (this.loginBtn = new Button()).setActionCommand("button");
        this.loginBtn.setLabel("Log in and begin ...");
        this.loginBtn.setBounds(97, 5, 130, 24);
        this.loginBtn.setBackground(new Color(12632256));
        this.panel4.add(this.loginBtn);
        (this.statusLbl = new Label(" ", 1)).setBounds(0, 48, 325, 34);
        this.panel1.add("Center", this.statusLbl);
        (this.panel2 = new Panel()).setLayout(new BorderLayout(0, 0));
        this.panel2.setVisible(false);
        this.panel2.setBounds(0, 0, 325, 135);
        this.add("card6", this.panel2);
        (this.mailLbl = new Label("")).setBounds(0, 111, 325, 24);
        this.panel2.add("South", this.mailLbl);
        (this.panel7 = new Panel()).setLayout(new GridLayout(2, 3, 0, 0));
        this.panel7.setBounds(0, 0, 325, 111);
        this.panel2.add("Center", this.panel7);
        (this.newBtn = new Button()).setActionCommand("button");
        this.newBtn.setLabel("New Message");
        this.newBtn.setBounds(0, 0, 108, 55);
        this.newBtn.setFont(new Font("Dialog", 0, 12));
        this.newBtn.setBackground(new Color(12632256));
        this.panel7.add(this.newBtn);
        (this.mboxBtn = new Button()).setActionCommand("button");
        this.mboxBtn.setLabel("Mailbox");
        this.mboxBtn.setBounds(108, 0, 108, 55);
        this.mboxBtn.setFont(new Font("Dialog", 0, 12));
        this.mboxBtn.setBackground(new Color(12632256));
        this.panel7.add(this.mboxBtn);
        (this.addressBtn = new Button()).setActionCommand("button");
        this.addressBtn.setLabel("Address Book");
        this.addressBtn.setBounds(108, 0, 108, 55);
        this.addressBtn.setBackground(new Color(12632256));
        this.panel7.add(this.addressBtn);
        (this.propsBtn = new Button()).setActionCommand("button");
        this.propsBtn.setLabel("Preferences");
        this.propsBtn.setBounds(0, 55, 108, 55);
        this.propsBtn.setBackground(new Color(12632256));
        this.panel7.add(this.propsBtn);
        (this.helpBtn = new Button()).setActionCommand("button");
        this.helpBtn.setLabel("Help");
        this.helpBtn.setBounds(108, 55, 108, 55);
        this.helpBtn.setBackground(new Color(12632256));
        this.panel7.add(this.helpBtn);
        (this.logoutBtn = new Button()).setActionCommand("button");
        this.logoutBtn.setLabel("Logout");
        this.logoutBtn.setBounds(0, 0, 60, 40);
        this.logoutBtn.setBackground(new Color(12632256));
        this.panel7.add(this.logoutBtn);
        ((CardLayout)this.getLayout()).show(this, "card5");
        final SymAction symAction = new SymAction();
        this.newBtn.addActionListener(symAction);
        this.mboxBtn.addActionListener(symAction);
        this.addressBtn.addActionListener(symAction);
        this.helpBtn.addActionListener(symAction);
        this.loginBtn.addActionListener(symAction);
        this.propsBtn.addActionListener(symAction);
        final SymKey symKey = new SymKey();
        this.passwordTxt.addKeyListener(symKey);
        this.loginBtn.addKeyListener(symKey);
        this.logoutBtn.addActionListener(symAction);
        this.defUsername = this.getParameter("username");
        this.defPassword = this.getParameter("password");
        this.popHost = this.getParameter("popHost");
        this.hostDotDomain = this.getParameter("addressDomain");
        final String parameter = this.getParameter("skipLogin");
        final String parameter2 = this.getParameter("appletViewer");
        this.languageFile = this.getParameter("languageFile");
        final String parameter3 = this.getParameter("allowAttachments");
        final String parameter4 = this.getParameter("serverSend");
        this.poUrl = this.getParameter("poUrl");
        this.language = new Properties();
        if (this.languageFile != null) {
            try {
                this.language.load(new URL(this.getDocumentBase(), this.languageFile).openStream());
            }
            catch (Exception ex) {}
        }
        if (this.language != null) {
            this.usernameLbl.setText(this.language.getProperty("labels.username", "Username:"));
            this.passwordLbl.setText(this.language.getProperty("labels.password", "Password:"));
            this.loginBtn.setLabel(this.language.getProperty("button.log_in_and_begin", "Log in and begin..."));
            this.newBtn.setLabel(this.language.getProperty("button.new_message", "New message"));
            this.mboxBtn.setLabel(this.language.getProperty("button.mailbox", "Mailbox"));
            this.addressBtn.setLabel(this.language.getProperty("button.address_book", "Address book"));
            this.propsBtn.setLabel(this.language.getProperty("button.preferences", "Preferences"));
            this.helpBtn.setLabel(this.language.getProperty("button.help", "Help"));
            this.logoutBtn.setLabel(this.language.getProperty("button.logout", "Logout"));
        }
        if (parameter2 != null) {
            if (parameter2.equalsIgnoreCase("true")) {
                this.appletViewer = true;
                System.out.println("-> Operating in applet viewer mode.");
            }
            else {
                this.appletViewer = false;
            }
        }
        if (parameter3 != null) {
            if (parameter3.equalsIgnoreCase("false")) {
                this.allowAttachments = false;
            }
            else {
                this.allowAttachments = true;
            }
        }
        if (parameter4 != null) {
            if (parameter4.equalsIgnoreCase("false")) {
                this.serverSend = false;
            }
            else {
                this.serverSend = true;
                this.allowAttachments = false;
            }
        }
        if (this.defUsername != null) {
            this.usernameTxt.setText(this.defUsername);
        }
        if (this.defPassword != null) {
            this.passwordTxt.setText(this.defPassword);
        }
        if (parameter != null) {
            if (parameter.equalsIgnoreCase("true")) {
                this.bypassLogin = true;
            }
            else {
                this.bypassLogin = false;
            }
        }
        if (this.bypassLogin) {
            this.loginBtn.setVisible(false);
            this.usernameTxt.setVisible(false);
            this.passwordTxt.setVisible(false);
            this.usernameLbl.setVisible(false);
            this.passwordLbl.setVisible(false);
            this.login();
        }
    }
    
    protected void finalize() {
    }
    
    void button1_Action(final ActionEvent actionEvent) {
        final sendFrame sendFrame = new sendFrame(this.props);
        sendFrame.setBackground(this.props.background);
        sendFrame.setForeground(this.props.foreground);
        sendFrame.setFont(this.props.font);
        sendFrame.show();
    }
    
    void mailboxBtn_Action(final ActionEvent actionEvent) {
        this.setCursor(Cursor.getPredefinedCursor(3));
        this.props.showMailbox();
        this.setCursor(Cursor.getPredefinedCursor(0));
        this.mailLbl.setText("");
    }
    
    void addressBtn_Action(final ActionEvent actionEvent) {
        this.props.aBook.show();
    }
    
    void button4_Action(final ActionEvent actionEvent) {
        if (!this.props.connected) {
            final NotHereFrame notHereFrame = new NotHereFrame("Disconnected", this.props.language.getProperty("error.disconnected_help", "Online help is not available when disconnected."));
            notHereFrame.setBackground(this.props.background);
            notHereFrame.setForeground(this.props.foreground);
            notHereFrame.setFont(this.props.font);
            notHereFrame.show();
            return;
        }
        this.props.showHelp();
    }
    
    void loginBtn_Action(final ActionEvent actionEvent) {
        this.login();
    }
    
    void login() {
        this.setCursor(Cursor.getPredefinedCursor(3));
        this.statusLbl.setText(this.language.getProperty("statusbar.contacting", "Contacting server..."));
        final URL documentBase = this.getDocumentBase();
        this.smtpHost = documentBase.getHost();
        this.username = this.usernameTxt.getText().trim();
        this.password = this.passwordTxt.getText();
        if (this.username.indexOf("@") != -1) {
            this.statusLbl.setText(this.language.getProperty("statusbar.login_failed", "Login failed."));
            new NotHereFrame("Error!", this.language.getProperty("error.address", "The 'Username' field should contain only your login name, not a complete address. For example, instead of 'johndoe@mochamail.com', enter only 'johndoe'.")).show();
            return;
        }
        if (this.hostDotDomain == null || this.hostDotDomain.equals("")) {
            final StringTokenizer stringTokenizer = new StringTokenizer(documentBase.toString(), "//:");
            stringTokenizer.nextToken();
            this.hostDotDomain = new StringTokenizer(stringTokenizer.nextToken(), "/").nextToken();
        }
        this.fromAddress = String.valueOf(this.username) + "@" + this.hostDotDomain;
        Label_0699: {
            try {
                final String host = this.getDocumentBase().getHost();
                Remote remote;
                try {
                    remote = Naming.lookup("//" + host + "/JMBroker1");
                }
                catch (RemoteException ex3) {
                    this.statusLbl.setText(this.language.getProperty("statusbar.contacting_proxy", "Direct attempt failed, trying proxy connection ..."));
                    remote = Naming.lookup("//" + host + ":1100/JMBroker1");
                    this.proxyMode = true;
                }
                if (remote instanceof BrokerServer) {
                    this.broker = (BrokerServer)remote;
                    if (MMLapplet.DEBUG) {
                        System.out.println("Found Broker.");
                    }
                    String s;
                    if (this.popHost == null) {
                        s = this.broker.authenticatePop(this.username, this.password);
                    }
                    else {
                        s = this.broker.authenticatePop(this.username, this.password, this.popHost);
                    }
                    if (!s.startsWith("ERROR")) {
                        try {
                            if (MMLapplet.DEBUG) {
                                System.out.println("Successful login.");
                            }
                            final int intValue = new Integer(s);
                            this.statusLbl.setText(this.language.getProperty("statusbar.getting_prefs", "Getting your preferences..."));
                            this.initComponents();
                            final String property = this.language.getProperty("statusbar.you_have");
                            if (property != null) {
                                final int index = property.indexOf("_X_");
                                this.mailLbl.setText(String.valueOf(property.substring(0, index)) + intValue + property.substring(index + 3));
                            }
                            else {
                                this.mailLbl.setText("You have " + intValue + " messages.");
                            }
                            try {
                                ((CardLayout)this.getLayout()).show(this, "card6");
                            }
                            catch (ClassCastException ex4) {}
                            break Label_0699;
                        }
                        catch (Exception ex) {
                            this.statusLbl.setText(this.language.getProperty("statusbar.error_broker", "ERROR: Could not connect with Broker."));
                            System.out.println(ex.toString());
                            break Label_0699;
                        }
                    }
                    this.statusLbl.setText(this.language.getProperty("statusbar.login_failed", "Login failed."));
                    new NotHereFrame("Error!", s).show();
                }
            }
            catch (Exception ex2) {
                this.statusLbl.setText(this.language.getProperty("statusbar.error_broker", "ERROR: Could not connect with Broker."));
                System.out.println(ex2.toString());
            }
        }
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    void propsBtn_Action(final ActionEvent actionEvent) {
        this.props.propFrame.show();
    }
    
    void initComponents() {
        this.props = new JMProps(this, this.username, this.password, this.smtpHost, this.popHost, this.server, this.broker, this.language, this.appletViewer, this.allowAttachments, this.serverSend, this.proxyMode);
        this.props.fromAddress = new String(this.fromAddress);
    }
    
    void passwordTxt_KeyPress(final KeyEvent keyEvent) {
        if (KeyEvent.getKeyText(keyEvent.getKeyCode()).equalsIgnoreCase("Enter")) {
            this.login();
        }
    }
    
    void loginBtn_KeyPress(final KeyEvent keyEvent) {
        if (KeyEvent.getKeyText(keyEvent.getKeyCode()).equalsIgnoreCase("Enter")) {
            this.login();
        }
    }
    
    void logoutBtn_Action(final ActionEvent actionEvent) {
        this.setCursor(Cursor.getPredefinedCursor(3));
        if (this.props.mbox != null) {
            this.props.mbox.closeMailbox();
        }
        if (this.props.server != null) {
            this.props.closeMailbox();
        }
        this.usernameTxt.setText("");
        this.passwordTxt.setText("");
        this.statusLbl.setText("");
        this.broker = null;
        this.props.closeAllWindows();
        this.props = null;
        try {
            ((CardLayout)this.getLayout()).show(this, "card5");
        }
        catch (ClassCastException ex) {}
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public MMLapplet() {
        this.pop3 = false;
        this.imap4 = false;
        this.bypassLogin = false;
        this.appletViewer = false;
        this.allowAttachments = true;
        this.serverSend = false;
        this.proxyMode = false;
    }
    
    static {
        MMLapplet.STRIKES = 3;
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == MMLapplet.this.newBtn) {
                MMLapplet.this.button1_Action(actionEvent);
                return;
            }
            if (source == MMLapplet.this.mboxBtn) {
                MMLapplet.this.mailboxBtn_Action(actionEvent);
                return;
            }
            if (source == MMLapplet.this.addressBtn) {
                MMLapplet.this.addressBtn_Action(actionEvent);
                return;
            }
            if (source == MMLapplet.this.helpBtn) {
                MMLapplet.this.button4_Action(actionEvent);
                return;
            }
            if (source == MMLapplet.this.loginBtn) {
                MMLapplet.this.loginBtn_Action(actionEvent);
                return;
            }
            if (source == MMLapplet.this.propsBtn) {
                MMLapplet.this.propsBtn_Action(actionEvent);
                return;
            }
            if (source == MMLapplet.this.logoutBtn) {
                MMLapplet.this.logoutBtn_Action(actionEvent);
            }
        }
    }
    
    class SymKey extends KeyAdapter
    {
        public void keyPressed(final KeyEvent keyEvent) {
            final Object source = keyEvent.getSource();
            if (source == MMLapplet.this.passwordTxt) {
                MMLapplet.this.passwordTxt_KeyPress(keyEvent);
                return;
            }
            if (source == MMLapplet.this.loginBtn) {
                MMLapplet.this.loginBtn_KeyPress(keyEvent);
            }
        }
    }
}
