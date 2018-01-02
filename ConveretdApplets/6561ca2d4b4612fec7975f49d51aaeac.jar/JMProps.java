import java.util.StringTokenizer;
import java.awt.Frame;
import java.rmi.Remote;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.rmi.Naming;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;
import java.util.Vector;
import java.applet.Applet;
import java.util.Properties;

// 
// Decompiled by Procyon v0.5.30
// 

public class JMProps extends Properties
{
    public boolean NETSCAPE;
    public AddressBookFrame aBook;
    public TocApplet helpToc;
    public String username;
    public String password;
    public String smtpHost;
    public String popHost;
    public String fromAddress;
    public Applet parentApp;
    public PropsFrame propFrame;
    public String signature;
    public PopMailServer server;
    public BrokerServer broker;
    public POP3mboxFrame mbox;
    Vector currentWindows;
    JMCache cache;
    URL base;
    URL codeBase;
    public String[] uidls;
    public boolean connected;
    Dimension screen;
    public Properties language;
    public boolean appletviewer;
    public boolean allowAttachments;
    public boolean serverSend;
    public boolean proxyMode;
    String statusRead;
    String statusUnread;
    String statusDeleted;
    String statusRecovered;
    String statusNew;
    public Color background;
    public Color foreground;
    public Font font;
    public Font messageFont;
    public String replyTo;
    public boolean toolbars;
    public int buttonSize;
    public String toolbarSide;
    public int aging;
    public boolean useAging;
    public int interval;
    public boolean useInterval;
    public boolean checkOnLoad;
    public boolean saveSent;
    public boolean showHtml;
    public boolean cacheNew;
    public boolean cacheUnread;
    public boolean cacheRead;
    public boolean cacheDraft;
    public boolean cacheRecovered;
    public boolean cacheUnmarked;
    public String mailboxName;
    public String trashMbox;
    public String sentMbox;
    public String draftMbox;
    public boolean emptyTrash;
    public int mailboxPosX;
    public int mailboxPosY;
    public int mailboxWidth;
    public int mailboxHeight;
    public String[] mailboxColumns;
    public int messagePosX;
    public int messagePosY;
    public int messageWidth;
    public int messageHeight;
    public int newPosX;
    public int newPosY;
    public int newWidth;
    public int newHeight;
    
    public JMProps(final Applet parentApp, final String username, final String password, final String smtpHost, final String popHost, final PopMailServer server, final BrokerServer broker, final Properties language, final boolean appletviewer, final boolean allowAttachments, final boolean serverSend, final boolean proxyMode) {
        this.NETSCAPE = false;
        if (System.getProperty("java.vendor").toLowerCase().startsWith("netscape")) {
            this.NETSCAPE = true;
        }
        this.parentApp = parentApp;
        this.username = username;
        this.password = password;
        this.smtpHost = smtpHost;
        this.popHost = popHost;
        this.server = server;
        this.broker = broker;
        this.language = language;
        this.appletviewer = appletviewer;
        this.allowAttachments = allowAttachments;
        this.serverSend = serverSend;
        this.proxyMode = proxyMode;
        if (this.proxyMode) {
            System.out.println("==PROXY MODE==");
            this.serverSend = true;
            this.allowAttachments = false;
        }
        this.base = this.parentApp.getDocumentBase();
        this.codeBase = this.parentApp.getCodeBase();
        this.currentWindows = new Vector();
        this.cache = new JMCache();
        this.connected = true;
        this.screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.statusRead = this.language.getProperty("status.read", "Read");
        this.statusUnread = this.language.getProperty("status.unread", "Unread");
        this.statusRecovered = this.language.getProperty("status.recovered", "Recovered");
        this.statusNew = this.language.getProperty("status.new", "NEW!");
        this.statusDeleted = this.language.getProperty("status.deleted", "Deleted");
        try {
            String s = this.smtpHost;
            if (this.proxyMode) {
                s = String.valueOf(s) + ":1100";
            }
            final Remote lookup = Naming.lookup("//" + s + "/PropertiesServerImpl1");
            if (lookup instanceof PropertiesServer) {
                final JMUserData userProps = ((PropertiesServer)lookup).getUserProps(this.username);
                if (userProps.props != null) {
                    this.load(new StringBufferInputStream(userProps.props.toString()));
                    this.setAllProps();
                    this.signature = userProps.signature;
                    if (this.signature == null) {
                        this.signature = "";
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println("ERROR: Could not access properties server.\n" + ex.toString());
        }
        this.propFrame = new PropsFrame(this);
        this.aBook = new AddressBookFrame(this);
    }
    
    public void addWindow(final Frame frame) {
        this.currentWindows.addElement(frame);
    }
    
    public void removeWindow(final Frame frame) {
        this.currentWindows.removeElement(frame);
    }
    
    public void updateProps() {
        for (int size = this.currentWindows.size(), i = 0; i < size; ++i) {
            final Frame frame = this.currentWindows.elementAt(i);
            frame.setFont(this.font);
            frame.setBackground(this.background);
            frame.setForeground(this.foreground);
            final Dimension size2 = frame.size();
            ++size2.width;
            size2.height += 5;
            frame.resize(size2);
            --size2.width;
            size2.height -= 5;
            frame.resize(size2);
            frame.repaint();
        }
    }
    
    public void closeAllWindows() {
        for (int size = this.currentWindows.size(), i = 0; i < size; ++i) {
            ((Frame)this.currentWindows.elementAt(i)).setVisible(false);
        }
    }
    
    void setAllProps() {
        final String property = this.getProperty("mocha.color.background");
        if (property == null) {
            this.background = Color.lightGray;
        }
        else {
            this.background = new Color(new Integer(property));
        }
        final String property2 = this.getProperty("mocha.color.foreground");
        if (property2 == null) {
            this.foreground = Color.black;
        }
        else {
            this.foreground = new Color(new Integer(property2));
        }
        final String property3 = this.getProperty("mocha.font.general");
        if (property3 == null) {
            this.font = new Font("Dialog", 0, 12);
        }
        else {
            final StringTokenizer stringTokenizer = new StringTokenizer(property3, "-");
            final String nextToken = stringTokenizer.nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            int n = 0;
            if (nextToken2.equalsIgnoreCase("plain")) {
                n = 0;
            }
            else if (nextToken2.equalsIgnoreCase("bold")) {
                n = 1;
            }
            else if (nextToken2.equalsIgnoreCase("italic")) {
                n = 2;
            }
            else if (nextToken2.equalsIgnoreCase("bolditalic")) {
                n = 3;
            }
            this.font = new Font(nextToken, n, new Integer(stringTokenizer.nextToken()));
        }
        final String property4 = this.getProperty("mocha.font.message");
        if (property4 == null) {
            this.messageFont = new Font("Courier", 0, 12);
        }
        else {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(property4, "-");
            final String nextToken3 = stringTokenizer2.nextToken();
            final String nextToken4 = stringTokenizer2.nextToken();
            int n2 = 0;
            if (nextToken4.equalsIgnoreCase("plain")) {
                n2 = 0;
            }
            else if (nextToken4.equalsIgnoreCase("bold")) {
                n2 = 1;
            }
            else if (nextToken4.equalsIgnoreCase("italic")) {
                n2 = 2;
            }
            else if (nextToken4.equalsIgnoreCase("bolditalic")) {
                n2 = 3;
            }
            this.messageFont = new Font(nextToken3, n2, new Integer(stringTokenizer2.nextToken()));
        }
        final String property5 = this.getProperty("mocha.mailbox.useinterval");
        if (property5 == null) {
            this.useInterval = true;
        }
        else if (property5.equalsIgnoreCase("false")) {
            this.useInterval = false;
        }
        else {
            this.useInterval = true;
        }
        final String property6 = this.getProperty("mocha.mailbox.checkonload");
        if (property6 == null) {
            this.checkOnLoad = true;
        }
        else if (property6.equalsIgnoreCase("false")) {
            this.checkOnLoad = false;
        }
        else {
            this.checkOnLoad = true;
        }
        final String property7 = this.getProperty("mocha.mailbox.useaging");
        if (property7 == null) {
            this.useAging = false;
        }
        else if (property7.equalsIgnoreCase("true")) {
            this.useAging = true;
        }
        else {
            this.useAging = false;
        }
        final String property8 = this.getProperty("mocha.message.html");
        if (property8 == null) {
            this.showHtml = true;
        }
        else if (property8.equalsIgnoreCase("false")) {
            this.showHtml = false;
        }
        else {
            this.showHtml = true;
        }
        final String property9 = this.getProperty("mocha.cache.new");
        if (property9 == null) {
            this.cacheNew = true;
        }
        else if (property9.equalsIgnoreCase("false")) {
            this.cacheNew = false;
        }
        else {
            this.cacheNew = true;
        }
        final String property10 = this.getProperty("mocha.cache.unread");
        if (property10 == null) {
            this.cacheUnread = true;
        }
        else if (property10.equalsIgnoreCase("false")) {
            this.cacheUnread = false;
        }
        else {
            this.cacheUnread = true;
        }
        final String property11 = this.getProperty("mocha.cache.read");
        if (property11 == null) {
            this.cacheRead = false;
        }
        else if (property11.equalsIgnoreCase("true")) {
            this.cacheRead = true;
        }
        else {
            this.cacheRead = false;
        }
        final String property12 = this.getProperty("mocha.cache.draft");
        if (property12 == null) {
            this.cacheDraft = false;
        }
        else if (property12.equalsIgnoreCase("true")) {
            this.cacheDraft = true;
        }
        else {
            this.cacheDraft = false;
        }
        final String property13 = this.getProperty("mocha.cache.recovered");
        if (property13 == null) {
            this.cacheRecovered = false;
        }
        else if (property13.equalsIgnoreCase("true")) {
            this.cacheRecovered = true;
        }
        else {
            this.cacheRecovered = false;
        }
        final String property14 = this.getProperty("mocha.cache.unmarked");
        if (property14 == null) {
            this.cacheUnmarked = false;
        }
        else if (property14.equalsIgnoreCase("true")) {
            this.cacheUnmarked = true;
        }
        else {
            this.cacheUnmarked = false;
        }
        final String property15 = this.getProperty("mocha.toolbar.size");
        if (property15 == null) {
            this.buttonSize = 48;
        }
        else {
            this.buttonSize = new Integer(property15);
        }
        final String property16 = this.getProperty("mocha.toolbar");
        if (property16 == null) {
            this.toolbars = true;
        }
        else if (property16.equalsIgnoreCase("false")) {
            this.toolbars = false;
        }
        else {
            this.toolbars = true;
        }
        this.toolbarSide = this.getProperty("mocha.toolbar.side");
        if (this.toolbarSide == null) {
            this.toolbarSide = "North";
        }
        this.replyTo = this.getProperty("mocha.header.replyto");
        if (this.replyTo == null) {
            this.replyTo = "";
        }
        this.mailboxName = this.getProperty("mocha.mailbox.name");
        if (this.mailboxName == null) {
            this.mailboxName = "Inbox";
        }
        this.trashMbox = this.getProperty("mocha.mailbox.trash");
        if (this.trashMbox == null) {
            this.trashMbox = "Trash";
        }
        this.sentMbox = this.getProperty("mocha.mailbox.sent");
        if (this.sentMbox == null) {
            this.sentMbox = "Sent Mail";
        }
        this.draftMbox = this.getProperty("mocha.mailbox.draft");
        if (this.draftMbox == null) {
            this.draftMbox = "Draft";
        }
        final String property17 = this.getProperty("mocha.mailbox.emptytrash");
        if (property17 == null) {
            this.emptyTrash = true;
        }
        else if (property17.equalsIgnoreCase("false")) {
            this.emptyTrash = false;
        }
        else {
            this.emptyTrash = true;
        }
        final String property18 = this.getProperty("mocha.mailbox.aging");
        if (property18 == null) {
            this.aging = 7;
        }
        else {
            this.aging = new Integer(property18);
        }
        final String property19 = this.getProperty("mocha.mailbox.interval");
        if (property19 == null) {
            this.interval = 10;
        }
        else {
            this.interval = new Integer(property19);
        }
        final String property20 = this.getProperty("mocha.mailbox.posx");
        if (property20 == null) {
            this.mailboxPosX = 25;
        }
        else {
            this.mailboxPosX = new Integer(property20);
        }
        if (this.mailboxPosX > this.screen.width - 200) {
            this.mailboxPosX = 25;
        }
        else if (this.mailboxPosX < 0) {
            this.mailboxPosX = 0;
        }
        final String property21 = this.getProperty("mocha.mailbox.posy");
        if (property21 == null) {
            this.mailboxPosY = 25;
        }
        else {
            this.mailboxPosY = new Integer(property21);
        }
        if (this.mailboxPosY > this.screen.height - 200) {
            this.mailboxPosY = 25;
        }
        else if (this.mailboxPosY < 0) {
            this.mailboxPosY = 0;
        }
        final String property22 = this.getProperty("mocha.mailbox.width");
        if (property22 == null) {
            this.mailboxWidth = 640;
        }
        else {
            this.mailboxWidth = new Integer(property22);
        }
        final String property23 = this.getProperty("mocha.mailbox.height");
        if (property23 == null) {
            this.mailboxHeight = 480;
        }
        else {
            this.mailboxHeight = new Integer(property23);
        }
        final String property24 = this.getProperty("mocha.message.posx");
        if (property24 == null) {
            this.messagePosX = 50;
        }
        else {
            this.messagePosX = new Integer(property24);
        }
        if (this.messagePosX > this.screen.width - 200) {
            this.messagePosX = 50;
        }
        else if (this.messagePosX < 0) {
            this.messagePosX = 0;
        }
        final String property25 = this.getProperty("mocha.message.posy");
        if (property25 == null) {
            this.messagePosY = 50;
        }
        else {
            this.messagePosY = new Integer(property25);
        }
        if (this.messagePosY > this.screen.height - 200) {
            this.messagePosY = 50;
        }
        else if (this.messagePosY < 0) {
            this.messagePosY = 0;
        }
        final String property26 = this.getProperty("mocha.message.width");
        if (property26 == null) {
            this.messageWidth = 500;
        }
        else {
            this.messageWidth = new Integer(property26);
        }
        final String property27 = this.getProperty("mocha.message.height");
        if (property27 == null) {
            this.messageHeight = 400;
        }
        else {
            this.messageHeight = new Integer(property27);
        }
        final String property28 = this.getProperty("mocha.newmessage.posx");
        if (property28 == null) {
            this.newPosX = 10;
        }
        else {
            this.newPosX = new Integer(property28);
        }
        if (this.newPosX > this.screen.width - 200) {
            this.newPosX = 10;
        }
        else if (this.newPosX < 0) {
            this.newPosX = 0;
        }
        final String property29 = this.getProperty("mocha.newmessage.posy");
        if (property29 == null) {
            this.newPosY = 10;
        }
        else {
            this.newPosY = new Integer(property29);
        }
        if (this.newPosY > this.screen.height - 200) {
            this.newPosY = 10;
        }
        else if (this.newPosY < 0) {
            this.newPosY = 0;
        }
        final String property30 = this.getProperty("mocha.newmessage.width");
        if (property30 == null) {
            this.newWidth = 500;
        }
        else {
            this.newWidth = new Integer(property30);
        }
        final String property31 = this.getProperty("mocha.newmessage.height");
        if (property31 == null) {
            this.newHeight = 400;
        }
        else {
            this.newHeight = new Integer(property31);
        }
        (this.mailboxColumns = new String[7])[0] = this.getProperty("mocha.mailbox.column0");
        if (this.mailboxColumns[0] == null) {
            this.mailboxColumns[0] = "35";
        }
        this.mailboxColumns[1] = this.getProperty("mocha.mailbox.column1");
        if (this.mailboxColumns[1] == null) {
            this.mailboxColumns[1] = "60";
        }
        this.mailboxColumns[2] = this.getProperty("mocha.mailbox.column2");
        if (this.mailboxColumns[2] == null) {
            this.mailboxColumns[2] = "100";
        }
        this.mailboxColumns[3] = this.getProperty("mocha.mailbox.column3");
        if (this.mailboxColumns[3] == null) {
            this.mailboxColumns[3] = "125";
        }
        this.mailboxColumns[4] = this.getProperty("mocha.mailbox.column4");
        if (this.mailboxColumns[4] == null) {
            this.mailboxColumns[4] = "50";
        }
        this.mailboxColumns[5] = this.getProperty("mocha.mailbox.column5");
        if (this.mailboxColumns[5] == null) {
            this.mailboxColumns[5] = "135";
        }
        this.mailboxColumns[6] = this.getProperty("mocha.mailbox.column6");
        if (this.mailboxColumns[6] == null) {
            this.mailboxColumns[6] = "1";
        }
    }
    
    public void saveProperties() {
        final Vector vector = new Vector<String>();
        vector.addElement("mocha.color.background=" + this.background.getRGB());
        vector.addElement("mocha.color.foreground=" + this.foreground.getRGB());
        if (this.font.getStyle() == 0) {
            vector.addElement("mocha.font.general=" + this.font.getName() + "-plain-" + this.font.getSize());
        }
        else if (this.font.getStyle() == 1) {
            vector.addElement("mocha.font.general=" + this.font.getName() + "-bold-" + this.font.getSize());
        }
        else if (this.font.getStyle() == 2) {
            vector.addElement("mocha.font.general=" + this.font.getName() + "-italic-" + this.font.getSize());
        }
        else if (this.font.getStyle() == 3) {
            vector.addElement("mocha.font.general=" + this.font.getName() + "-bolditalic-" + this.font.getSize());
        }
        if (this.messageFont.getStyle() == 0) {
            vector.addElement("mocha.font.message=" + this.messageFont.getName() + "-plain-" + this.messageFont.getSize());
        }
        else if (this.messageFont.getStyle() == 1) {
            vector.addElement("mocha.font.message=" + this.messageFont.getName() + "-bold-" + this.messageFont.getSize());
        }
        else if (this.messageFont.getStyle() == 2) {
            vector.addElement("mocha.font.message=" + this.messageFont.getName() + "-italic-" + this.messageFont.getSize());
        }
        else if (this.messageFont.getStyle() == 3) {
            vector.addElement("mocha.font.message=" + this.messageFont.getName() + "-bolditalic-" + this.messageFont.getSize());
        }
        vector.addElement("mocha.toolbar.size=" + this.buttonSize);
        vector.addElement("mocha.toolbar.side=" + this.toolbarSide);
        vector.addElement("mocha.toolbar=" + this.toolbars);
        vector.addElement("mocha.header.replyto=" + this.replyTo);
        vector.addElement("mocha.mailbox.name=" + this.mailboxName);
        vector.addElement("mocha.mailbox.draft=" + this.draftMbox);
        vector.addElement("mocha.mailbox.sent=" + this.sentMbox);
        vector.addElement("mocha.mailbox.trash=" + this.trashMbox);
        vector.addElement("mocha.mailbox.emptytrash=" + this.emptyTrash);
        vector.addElement("mocha.mailbox.useinterval=" + this.useInterval);
        vector.addElement("mocha.mailbox.checkonload=" + this.checkOnLoad);
        vector.addElement("mocha.message.html=" + this.showHtml);
        vector.addElement("mocha.mailbox.useaging=" + this.useAging);
        vector.addElement("mocha.message.save=" + this.saveSent);
        vector.addElement("mocha.mailbox.aging=" + this.aging);
        vector.addElement("mocha.mailbox.interval=" + this.interval);
        vector.addElement("mocha.cache.new=" + this.cacheNew);
        vector.addElement("mocha.cache.unread=" + this.cacheUnread);
        vector.addElement("mocha.cache.read=" + this.cacheRead);
        vector.addElement("mocha.cache.draft=" + this.cacheRead);
        vector.addElement("mocha.cache.recovered=" + this.cacheRecovered);
        vector.addElement("mocha.cache.unmarked=" + this.cacheUnmarked);
        vector.addElement("mocha.mailbox.posx=" + this.mailboxPosX);
        vector.addElement("mocha.mailbox.posy=" + this.mailboxPosY);
        vector.addElement("mocha.mailbox.width=" + this.mailboxWidth);
        vector.addElement("mocha.mailbox.height=" + this.mailboxHeight);
        vector.addElement("mocha.mailbox.column0=" + this.mailboxColumns[0]);
        vector.addElement("mocha.mailbox.column1=" + this.mailboxColumns[1]);
        vector.addElement("mocha.mailbox.column2=" + this.mailboxColumns[2]);
        vector.addElement("mocha.mailbox.column3=" + this.mailboxColumns[3]);
        vector.addElement("mocha.mailbox.column4=" + this.mailboxColumns[4]);
        vector.addElement("mocha.mailbox.column5=" + this.mailboxColumns[5]);
        vector.addElement("mocha.message.posx=" + this.messagePosX);
        vector.addElement("mocha.message.posy=" + this.messagePosY);
        vector.addElement("mocha.message.width=" + this.messageWidth);
        vector.addElement("mocha.message.height=" + this.messageHeight);
        vector.addElement("mocha.newmessage.posx=" + this.newPosX);
        vector.addElement("mocha.newmessage.posy=" + this.newPosY);
        vector.addElement("mocha.newmessage.width=" + this.newWidth);
        vector.addElement("mocha.newmessage.height=" + this.newHeight);
        final int size = vector.size();
        final String[] array = new String[size];
        for (int i = 0; i < size; ++i) {
            array[i] = vector.elementAt(i);
        }
        try {
            String s = this.smtpHost;
            if (this.proxyMode) {
                s = String.valueOf(s) + ":1100";
            }
            final Remote lookup = Naming.lookup("//" + s + "/PropertiesServerImpl1");
            if (lookup instanceof PropertiesServer) {
                ((PropertiesServer)lookup).saveProps(this.username, array, this.signature);
            }
        }
        catch (Exception ex) {
            System.out.println("ERROR: Could not access properties server.");
        }
    }
    
    public void dumpProperties() {
        System.out.println("mocha.color.background=" + this.background.getRGB());
        System.out.println("mocha.color.foreground=" + this.foreground.getRGB());
        if (this.font.getStyle() == 0) {
            System.out.println("mocha.font.general=" + this.font.getName() + "-" + this.font.getSize());
        }
        else if (this.font.getStyle() == 1) {
            System.out.println("mocha.font.general=" + this.font.getName() + "-bold-" + this.font.getSize());
        }
        else if (this.font.getStyle() == 2) {
            System.out.println("mocha.font.general=" + this.font.getName() + "-italic-" + this.font.getSize());
        }
        else if (this.font.getStyle() == 3) {
            System.out.println("mocha.font.general=" + this.font.getName() + "-bolditalic-" + this.font.getSize());
        }
        if (this.messageFont.getStyle() == 0) {
            System.out.println("mocha.font.message=" + this.messageFont.getName() + "-" + this.messageFont.getSize());
        }
        else if (this.messageFont.getStyle() == 1) {
            System.out.println("mocha.font.message=" + this.messageFont.getName() + "-bold-" + this.messageFont.getSize());
        }
        else if (this.messageFont.getStyle() == 2) {
            System.out.println("mocha.font.message=" + this.messageFont.getName() + "-italic-" + this.messageFont.getSize());
        }
        else if (this.messageFont.getStyle() == 3) {
            System.out.println("mocha.font.message=" + this.messageFont.getName() + "-bolditalic-" + this.messageFont.getSize());
        }
        System.out.println("mocha.toolbar.size=" + this.buttonSize);
        System.out.println("mocha.toolbar.side=" + this.toolbarSide);
        System.out.println("mocha.toolbar=" + this.toolbars);
        System.out.println("mocha.header.replyto=" + this.replyTo);
        System.out.println("mocha.mailbox.name=" + this.mailboxName);
        System.out.println("mocha.mailbox.draft=" + this.draftMbox);
        System.out.println("mocha.mailbox.sent=" + this.sentMbox);
        System.out.println("mocha.mailbox.trash=" + this.trashMbox);
        System.out.println("mocha.mailbox.emptytrash=" + this.emptyTrash);
        System.out.println("mocha.mailbox.posx=" + this.mailboxPosX);
        System.out.println("mocha.mailbox.posy=" + this.mailboxPosY);
        System.out.println("mocha.mailbox.width=" + this.mailboxWidth);
        System.out.println("mocha.mailbox.height=" + this.mailboxHeight);
        System.out.println("mocha.mailbox.column0=" + this.mailboxColumns[0]);
        System.out.println("mocha.mailbox.column1=" + this.mailboxColumns[1]);
        System.out.println("mocha.mailbox.column2=" + this.mailboxColumns[2]);
        System.out.println("mocha.mailbox.column3=" + this.mailboxColumns[3]);
        System.out.println("mocha.mailbox.column4=" + this.mailboxColumns[4]);
        System.out.println("mocha.mailbox.column5=" + this.mailboxColumns[5]);
        System.out.println("mocha.message.posx=" + this.messagePosX);
        System.out.println("mocha.message.posy=" + this.messagePosY);
        System.out.println("mocha.message.width=" + this.messageWidth);
        System.out.println("mocha.message.height=" + this.messageHeight);
        System.out.println("mocha.newmessage.posx=" + this.newPosX);
        System.out.println("mocha.newmessage.posy=" + this.newPosY);
        System.out.println("mocha.newmessage.width=" + this.newWidth);
        System.out.println("mocha.newmessage.height=" + this.newHeight);
        System.out.println("mocha.mailbox.aging=" + this.aging);
        System.out.println("mocha.mailbox.interval=" + this.interval);
        System.out.println("mocha.mailbox.useinterval=" + this.useInterval);
        System.out.println("mocha.mailbox.checkonload=" + this.checkOnLoad);
        System.out.println("mocha.message.html=" + this.showHtml);
        System.out.println("mocha.mailbox.useaging=" + this.useAging);
        System.out.println("mocha.message.save=" + this.saveSent);
        System.out.println("mocha.cache.new=" + this.cacheNew);
        System.out.println("mocha.cache.unread=" + this.cacheUnread);
        System.out.println("mocha.cache.read=" + this.cacheRead);
        System.out.println("mocha.cache.draft=" + this.cacheDraft);
        System.out.println("mocha.cache.recovered=" + this.cacheRecovered);
        System.out.println("mocha.cache.unmarked=" + this.cacheUnmarked);
    }
    
    public void showHelp() {
        if (this.helpToc == null) {
            this.helpToc = new TocApplet(this.parentApp, null, "MochaMail Online Help", this);
        }
        this.helpToc.show();
    }
    
    public void showAddressBook() {
        if (this.aBook == null) {
            this.aBook = new AddressBookFrame(this);
        }
        this.aBook.show();
    }
    
    public void showMailbox() {
        if (this.server == null) {
            try {
                String s = this.smtpHost;
                if (this.proxyMode) {
                    s = String.valueOf(s) + ":1100";
                }
                String s2;
                if (this.popHost == null) {
                    s2 = "//" + s + "/" + this.broker.getPopServer(this.username, this.password);
                }
                else {
                    s2 = "//" + s + "/" + this.broker.getPopServer(this.username, this.password, this.popHost);
                }
                final Remote lookup = Naming.lookup(s2);
                if (lookup instanceof PopMailServer) {
                    (this.server = (PopMailServer)lookup).unbind();
                }
            }
            catch (Exception ex) {}
        }
        if (this.mbox == null) {
            this.mbox = new POP3mboxFrame(this);
        }
        this.mbox.show();
    }
    
    public void saveHeaders(final String[] array) {
        if (!this.useAging) {
            try {
                this.server.saveHeaders(this.password, array, this.mailboxName, -1, this.statusRead, this.statusDeleted);
                return;
            }
            catch (Exception ex) {
                System.out.println("ERROR: Could not save headers.");
                return;
            }
        }
        try {
            this.server.saveHeaders(this.password, array, this.mailboxName, this.aging, this.statusRead, this.statusDeleted);
        }
        catch (Exception ex2) {
            System.out.println("ERROR: Could not save headers.");
        }
    }
    
    public void disconnect() {
        this.closeMailbox();
        this.broker = null;
        this.connected = false;
        this.mbox.setTitle(String.valueOf(this.mbox.getTitle()) + " [DISCONNECTED]");
    }
    
    public void reconnect() throws Exception {
        String s = this.smtpHost;
        if (this.proxyMode) {
            s = String.valueOf(s) + ":1100";
        }
        final Remote lookup = Naming.lookup("//" + s + "/JMBroker1");
        if (lookup instanceof BrokerServer) {
            this.broker = (BrokerServer)lookup;
            this.connected = true;
            this.cache = new JMCache();
        }
    }
    
    public void confirmConnection() {
        boolean confirm;
        try {
            confirm = this.broker.confirm();
        }
        catch (Exception ex) {
            confirm = false;
        }
        if (!confirm) {
            try {
                String s = this.smtpHost;
                if (this.proxyMode) {
                    s = String.valueOf(s) + ":1100";
                }
                final Remote lookup = Naming.lookup("//" + s + "/JMBroker1");
                if (lookup instanceof BrokerServer) {
                    this.broker = (BrokerServer)lookup;
                }
            }
            catch (Exception ex2) {}
        }
        boolean confirm2;
        try {
            confirm2 = this.server.confirm();
        }
        catch (Exception ex3) {
            confirm2 = false;
        }
        if (!confirm2) {
            try {
                String s2 = this.smtpHost;
                if (this.proxyMode) {
                    s2 = String.valueOf(s2) + ":1100";
                }
                String s3;
                if (this.popHost == null) {
                    s3 = "//" + s2 + "/" + this.broker.getPopServer(this.username, this.password);
                }
                else {
                    s3 = "//" + s2 + "/" + this.broker.getPopServer(this.username, this.password, this.popHost);
                }
                final Remote lookup2 = Naming.lookup(s3);
                if (lookup2 instanceof PopMailServer) {
                    this.server = (PopMailServer)lookup2;
                }
            }
            catch (Exception ex4) {}
        }
    }
    
    public void closeMailbox() {
        try {
            this.server.close();
        }
        catch (Exception ex) {
            System.out.println("CLOSE ERROR:" + ex.toString());
        }
        this.server = null;
    }
}
