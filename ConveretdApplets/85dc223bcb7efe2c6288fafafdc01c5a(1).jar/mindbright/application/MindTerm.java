// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.application;

import java.io.IOException;
import java.awt.PopupMenu;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import mindbright.terminal.TerminalMenuListener;
import mindbright.terminal.TerminalMenuHandler;
import mindbright.ssh.SSHMenuHandler;
import mindbright.terminal.TerminalInterpreter;
import mindbright.terminal.TerminalXTerm;
import java.awt.MenuBar;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.Color;
import mindbright.terminal.TerminalDefProps;
import java.util.Enumeration;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import mindbright.ssh.SSHSCPIndicator;
import mindbright.ssh.SSHSCPStdoutIndicator;
import mindbright.ssh.SSHInteractor;
import mindbright.ssh.SSHAuthenticator;
import mindbright.ssh.SSHSCP;
import mindbright.ssh.SSH;
import java.io.File;
import java.awt.Container;
import mindbright.ssh.SSHClient;
import mindbright.ssh.SSHPropertyHandler;
import java.util.Hashtable;
import mindbright.ssh.SSHStdIO;
import mindbright.ssh.SSHInteractiveClient;
import mindbright.terminal.TerminalWin;
import java.awt.Frame;
import java.util.Properties;
import java.applet.Applet;

public class MindTerm extends Applet implements Runnable
{
    static Properties paramTermProps;
    static Properties paramSSHProps;
    public static String javaVersion;
    public static String javaVendor;
    public static String osName;
    public static String osArch;
    public static String osVersion;
    Frame frame;
    TerminalWin term;
    SSHInteractiveClient client;
    SSHInteractiveClient sshClone;
    SSHStdIO console;
    Thread clientThread;
    boolean mergedTermProps;
    Properties sshProps;
    Properties termProps;
    String[] cmdLineArgs;
    String commandLine;
    String sshHomeDir;
    String propsFile;
    boolean usePopMenu;
    boolean haveMenus;
    boolean haveGUI;
    boolean cmdsh;
    boolean quiet;
    boolean helpinfo;
    String startupMsg;
    boolean doSCP;
    boolean recursiveSCP;
    boolean toRemote;
    int firstArg;
    boolean autoSaveProps;
    boolean autoLoadProps;
    boolean savePasswords;
    int popButtonNum;
    boolean isClosing;
    boolean separateFrame;
    boolean weAreAnApplet;
    static Hashtable terminals;
    boolean confirmedClose;
    
    static synchronized boolean isLastTerminal() {
        return MindTerm.terminals.isEmpty();
    }
    
    static synchronized void addTerminal(final MindTerm mindterm) {
        MindTerm.terminals.put(mindterm, mindterm);
    }
    
    static synchronized void removeTerminal(final MindTerm mindterm) {
        MindTerm.terminals.remove(mindterm);
    }
    
    public MindTerm() {
        this.commandLine = null;
        this.sshHomeDir = null;
        this.propsFile = null;
        this.usePopMenu = false;
        this.haveMenus = true;
        this.haveGUI = true;
        this.cmdsh = false;
        this.quiet = true;
        this.helpinfo = true;
        this.startupMsg = null;
        this.doSCP = false;
        this.recursiveSCP = false;
        this.toRemote = true;
        this.firstArg = 0;
        this.autoSaveProps = true;
        this.autoLoadProps = true;
        this.savePasswords = false;
        this.popButtonNum = 3;
        this.isClosing = false;
        this.separateFrame = true;
        this.weAreAnApplet = false;
        this.confirmedClose = false;
        this.sshProps = MindTerm.paramSSHProps;
        this.termProps = MindTerm.paramTermProps;
        addTerminal(this);
    }
    
    public MindTerm(final Properties sshProps, final Properties termProps) {
        this.commandLine = null;
        this.sshHomeDir = null;
        this.propsFile = null;
        this.usePopMenu = false;
        this.haveMenus = true;
        this.haveGUI = true;
        this.cmdsh = false;
        this.quiet = true;
        this.helpinfo = true;
        this.startupMsg = null;
        this.doSCP = false;
        this.recursiveSCP = false;
        this.toRemote = true;
        this.firstArg = 0;
        this.autoSaveProps = true;
        this.autoLoadProps = true;
        this.savePasswords = false;
        this.popButtonNum = 3;
        this.isClosing = false;
        this.separateFrame = true;
        this.weAreAnApplet = false;
        this.confirmedClose = false;
        this.sshProps = sshProps;
        this.termProps = termProps;
        addTerminal(this);
    }
    
    public static void main(final String[] argv) {
        final MindTerm controller = new MindTerm(MindTerm.paramSSHProps, MindTerm.paramTermProps);
        controller.cmdLineArgs = argv;
        try {
            controller.getApplicationParams();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
        try {
            controller.run();
        }
        catch (Exception e) {
            System.out.println("Error, please mail below stack-trace to mats@mindbright.se");
            e.printStackTrace();
        }
    }
    
    public void init() {
        this.weAreAnApplet = true;
        this.autoSaveProps = false;
        this.autoLoadProps = false;
        this.savePasswords = false;
        this.getAppletParams();
        new Thread(this).start();
    }
    
    public void run() {
        try {
            if (this.sshClone != null) {
                this.client = new SSHInteractiveClient(this.sshClone);
                this.sshClone = null;
            }
            else {
                if (this.commandLine != null && this.sshProps.getProperty("forcpty") == null) {
                    ((Hashtable<String, String>)this.sshProps).put("forcpty", "false");
                }
                SSHPropertyHandler propsHandler = new SSHPropertyHandler(this.sshProps);
                if (this.propsFile != null) {
                    try {
                        propsHandler = SSHPropertyHandler.fromFile(this.propsFile, "");
                    }
                    catch (SSHClient.AuthFailException e2) {
                        throw new Exception("Sorry, can only use passwordless settings files for now");
                    }
                    propsHandler.mergeProperties(this.sshProps);
                }
                this.client = new SSHInteractiveClient(this.quiet, this.cmdsh, propsHandler);
            }
            this.console = (SSHStdIO)this.client.getConsole();
            if (this.client.getPropertyHandler().getInitTerminalProperties() != null) {
                final Properties newTermProps = new Properties(this.client.getPropertyHandler().getInitTerminalProperties());
                if (this.termProps != null && !this.termProps.isEmpty()) {
                    final Enumeration enum1 = this.termProps.keys();
                    while (enum1.hasMoreElements()) {
                        final String name = enum1.nextElement();
                        ((Hashtable<String, String>)newTermProps).put(name, this.termProps.getProperty(name));
                    }
                    this.mergedTermProps = true;
                }
                this.termProps = newTermProps;
            }
            if (this.haveGUI) {
                this.initGUI();
                this.console.setTerminal(this.term);
                this.console.setOwnerContainer(this.frame);
                this.console.setOwnerName("MindTerm v1.2.1");
                this.console.updateTitle();
                try {
                    while (!this.frame.isShowing()) {
                        Thread.sleep(50L);
                    }
                }
                catch (InterruptedException ex) {}
                if (!this.separateFrame) {
                    this.term.emulateComponentShown();
                }
            }
            this.client.printCopyright();
            this.client.getPropertyHandler().setSSHHomeDir(this.sshHomeDir);
            this.client.getPropertyHandler().setAutoSaveProps(this.autoLoadProps);
            this.client.getPropertyHandler().setAutoLoadProps(this.autoSaveProps);
            this.client.getPropertyHandler().setSavePasswords(this.savePasswords);
            this.client.updateMenus();
            if (this.commandLine != null) {
                if (!this.doSCP) {
                    this.client.doSingleCommand(this.commandLine, false, 0L);
                }
                else {
                    if (this.cmdLineArgs.length - this.firstArg < 2) {
                        throw new Exception("scp must have at least two arguments (<source> <destination>)");
                    }
                    final String[] fileList = new String[this.cmdLineArgs.length - this.firstArg - 1];
                    final String source = this.commandLine.substring(0, this.commandLine.lastIndexOf(32));
                    for (int i = this.firstArg; i < this.cmdLineArgs.length - 1; ++i) {
                        fileList[i - this.firstArg] = this.cmdLineArgs[i];
                    }
                    final String target = this.cmdLineArgs[this.cmdLineArgs.length - 1];
                    final String srvHost = "eskimo.com";
                    final int srvPort = this.client.getPropertyHandler().getSrvPort();
                    final SSHSCP scp = new SSHSCP(srvHost, srvPort, this.client.getPropertyHandler(), new File("."), SSH.DEBUG, this.recursiveSCP);
                    if (SSH.DEBUG) {
                        scp.setInteractor(this.client);
                        scp.setIndicator(new SSHSCPStdoutIndicator());
                    }
                    if (this.toRemote) {
                        scp.copyToRemote(fileList, target);
                    }
                    else {
                        scp.copyToLocal(target, source);
                    }
                }
            }
            else {
                try {
                    (this.clientThread = new Thread(this.client)).start();
                    this.clientThread.join();
                }
                catch (InterruptedException ex2) {}
            }
        }
        catch (IllegalArgumentException ae) {
            if (this.client != null) {
                this.client.alert(ae.getMessage());
            }
            System.out.println(ae.getMessage());
        }
        catch (FileNotFoundException fe) {
            System.out.println("Settings-file not found: " + fe.getMessage());
        }
        catch (Exception e) {
            if (this.client != null) {
                this.client.alert("Error: " + e.getMessage());
            }
            System.out.println("Error: " + e.getMessage());
            if (SSH.DEBUGMORE) {
                System.out.println("Please send the below stack-trace to mats@mindbright.se");
                e.printStackTrace();
            }
        }
        this.windowClosing(null);
        if (isLastTerminal()) {
            this.doExit();
        }
    }
    
    public void getAppletParams() {
        try {
            this.separateFrame = new Boolean(this.getParameter("sepframe"));
        }
        catch (Exception e) {
            this.separateFrame = true;
        }
        try {
            SSH.DEBUG = new Boolean(this.getParameter("verbose"));
        }
        catch (Exception e) {
            SSH.DEBUG = false;
        }
        try {
            SSH.DEBUGMORE = new Boolean(this.getParameter("debug"));
            SSH.DEBUG = SSH.DEBUGMORE;
        }
        catch (Exception ex) {}
        try {
            this.quiet = new Boolean(this.getParameter("quiet"));
        }
        catch (Exception e) {
            this.quiet = true;
        }
        try {
            this.savePasswords = new Boolean(this.getParameter("savepasswords"));
        }
        catch (Exception e) {
            this.savePasswords = false;
        }
        try {
            this.cmdsh = new Boolean(this.getParameter("cmdsh"));
        }
        catch (Exception e) {
            this.cmdsh = false;
        }
        try {
            this.helpinfo = new Boolean(this.getParameter("helpinfo"));
        }
        catch (Exception e) {
            this.helpinfo = true;
        }
        this.startupMsg = this.getParameter("startmsg");
        String param = this.getParameter("menus");
        if (param != null) {
            if (param.equals("no")) {
                this.haveMenus = false;
            }
            else if (param.startsWith("pop")) {
                this.getPopupButtonNumber(param);
                this.usePopMenu = true;
            }
        }
        param = this.getParameter("autoprops");
        if (param != null) {
            if (param.equals("save")) {
                this.autoSaveProps = true;
                this.autoLoadProps = false;
            }
            else if (param.equals("load")) {
                this.autoSaveProps = false;
                this.autoLoadProps = true;
            }
            else if (param.equals("both")) {
                this.autoSaveProps = true;
                this.autoLoadProps = true;
            }
        }
        this.sshHomeDir = this.getParameter("sshhome");
        this.propsFile = this.getParameter("propsfile");
        this.commandLine = this.getParameter("commandline");
        this.getDefaultParams();
        for (int i = 0; i < SSHPropertyHandler.defaultPropDesc.length; ++i) {
            final String name = SSHPropertyHandler.defaultPropDesc[i][0];
            final String value = this.getParameter(name);
            if (value != null) {
                ((Hashtable<String, String>)MindTerm.paramSSHProps).put(name, value);
            }
        }
        String value;
        for (int i = 0; (value = this.getParameter("local" + i)) != null; ++i) {
            ((Hashtable<String, String>)MindTerm.paramSSHProps).put("local" + i, value);
        }
        for (int i = 0; (value = this.getParameter("remote" + i)) != null; ++i) {
            ((Hashtable<String, String>)MindTerm.paramSSHProps).put("remote" + i, value);
        }
        for (int i = 0; i < TerminalDefProps.defaultPropDesc.length; ++i) {
            final String name = TerminalDefProps.defaultPropDesc[i][0];
            value = this.getParameter(name);
            if (value != null) {
                ((Hashtable<String, String>)this.termProps).put(name, value);
            }
        }
        param = this.getParameter("appletbg");
        if (param != null) {
            Color c;
            try {
                c = TerminalWin.getTermColor(param);
            }
            catch (IllegalArgumentException e2) {
                try {
                    c = TerminalWin.getTermRGBColor(param);
                }
                catch (Throwable t) {
                    c = null;
                }
            }
            if (c != null) {
                this.setBackground(c);
            }
        }
    }
    
    public void getApplicationParams() throws Exception {
        int i;
        try {
            for (i = 0; i < this.cmdLineArgs.length; ++i) {
                final String arg = this.cmdLineArgs[i];
                if (!arg.startsWith("--")) {
                    break;
                }
                switch (arg.charAt(2)) {
                    case 'h': {
                        this.sshHomeDir = this.cmdLineArgs[++i];
                        break;
                    }
                    case 'f': {
                        this.propsFile = this.cmdLineArgs[++i];
                        break;
                    }
                    case 'c': {
                        this.cmdsh = true;
                        break;
                    }
                    case 'd': {
                        this.haveGUI = false;
                        break;
                    }
                    case 'm': {
                        final String typ = this.cmdLineArgs[++i];
                        if (typ.equals("no")) {
                            this.haveMenus = false;
                            break;
                        }
                        if (typ.startsWith("pop")) {
                            this.getPopupButtonNumber(typ);
                            this.usePopMenu = true;
                            break;
                        }
                        throw new Exception("value of '--m' must be 'no', 'pop1', 'pop2', or 'pop3'");
                    }
                    case 'p': {
                        final String typ = this.cmdLineArgs[++i];
                        if (typ.equals("save")) {
                            this.autoSaveProps = true;
                            break;
                        }
                        if (typ.equals("load")) {
                            this.autoLoadProps = true;
                            break;
                        }
                        if (typ.equals("both")) {
                            this.autoSaveProps = true;
                            this.autoLoadProps = true;
                            break;
                        }
                        if (typ.equals("none")) {
                            this.autoSaveProps = false;
                            this.autoLoadProps = false;
                            break;
                        }
                        throw new Exception("value of '--p' must be 'save', 'load', 'both', or 'none'");
                    }
                    case 'q': {
                        final String val = this.cmdLineArgs[++i];
                        if (val.equalsIgnoreCase("true") || val.equalsIgnoreCase("false")) {
                            this.quiet = Boolean.valueOf(val);
                            break;
                        }
                        throw new Exception("value of '--q' must be 'true' or 'false'");
                    }
                    case 'r': {
                        this.recursiveSCP = true;
                        break;
                    }
                    case 's': {
                        this.haveGUI = false;
                        this.doSCP = true;
                        final String direction = this.cmdLineArgs[++i];
                        if (direction.equalsIgnoreCase("toremote")) {
                            this.toRemote = true;
                            break;
                        }
                        if (direction.equalsIgnoreCase("tolocal")) {
                            this.toRemote = false;
                            break;
                        }
                        throw new Exception("value of '--s' must be 'toremote' or 'tolocal'");
                    }
                    case 'v': {
                        System.out.println("verbose mode selected...");
                        SSH.DEBUG = true;
                        break;
                    }
                    case 'x': {
                        this.savePasswords = true;
                        break;
                    }
                    case 'V': {
                        System.out.println("MindTerm v1.2.1");
                        System.out.println("SSH protocol version 1.5");
                        System.exit(0);
                        break;
                    }
                    case 'D': {
                        SSH.DEBUG = true;
                        SSH.DEBUGMORE = true;
                        break;
                    }
                    case '?': {
                        this.printHelp();
                        System.exit(0);
                        throw new Exception("unknown parameter '" + arg + "'");
                    }
                }
            }
        }
        catch (Exception e) {
            this.printHelp();
            throw e;
        }
        this.getDefaultParams();
        for (int numOfOpts = i = i; i < this.cmdLineArgs.length; i += 2) {
            String name = this.cmdLineArgs[i];
            if (name.charAt(0) != '-') {
                break;
            }
            if (i + 1 == this.cmdLineArgs.length) {
                break;
            }
            name = name.substring(1);
            final String value = this.cmdLineArgs[i + 1];
            if (SSHPropertyHandler.isProperty(name)) {
                ((Hashtable<String, String>)MindTerm.paramSSHProps).put(name, value);
            }
            else if (TerminalDefProps.isProperty(name)) {
                ((Hashtable<String, String>)MindTerm.paramTermProps).put(name, value);
            }
            else {
                System.out.println("Unknown property '" + name + "'");
            }
        }
        if (i < this.cmdLineArgs.length) {
            this.firstArg = i;
            this.commandLine = "";
            while (i < this.cmdLineArgs.length) {
                this.commandLine = this.commandLine + this.cmdLineArgs[i] + " ";
                ++i;
            }
            this.commandLine = this.commandLine.trim();
        }
    }
    
    void printHelp() {
        System.out.println("usage: MindTerm [options] [properties] [command]");
        System.out.println("Options:");
        System.out.println("  --c            Enable local command-shell.");
        System.out.println("  --d            No terminal-window, only dumb command-line and port-forwarding.");
        System.out.println("  --f <file>     Use settings from the given file.");
        System.out.println("  --h dir        Name of the MindTerm home-dir (default: ~/mindterm/).");
        System.out.println("  --m <no | pop | popN>");
        System.out.println("                 Use no menus or popup (on mouse-button N) menu instead of menubar.");
        System.out.println("  --p <save | load | both | none>");
        System.out.println("                 Sets automatic save/load flags for property-files.");
        System.out.println("  --q <true | false>");
        System.out.println("                 Quiet; don't query for server/username if given.");
        System.out.println("  --v            Verbose; display verbose messages.");
        System.out.println("  --x            Save passwords in property-files.");
        System.out.println("  --D            Debug; display extra debug info.");
        System.out.println("  --V            Version; display version number only.");
        System.out.println("  --?            Help; display this help.");
    }
    
    void getPopupButtonNumber(final String param) {
        if (param.length() == 4) {
            try {
                this.popButtonNum = Integer.valueOf(param.substring(3));
                if (this.popButtonNum < 1 || this.popButtonNum > 3) {
                    this.popButtonNum = 3;
                }
            }
            catch (NumberFormatException ex) {}
        }
    }
    
    void getDefaultParams() {
        try {
            if (this.sshHomeDir == null) {
                String hDir = System.getProperty("user.home");
                if (hDir == null) {
                    hDir = System.getProperty("user.dir");
                }
                if (hDir == null) {
                    hDir = System.getProperty("java.home");
                }
                this.sshHomeDir = hDir + File.separator + "mindterm" + File.separator;
            }
        }
        catch (Throwable t) {}
        if (this.weAreAnApplet) {
            ((Hashtable<String, String>)MindTerm.paramSSHProps).put("server", "eskimo.com");
        }
        try {
            if (!this.quiet) {
                ((Hashtable<String, String>)MindTerm.paramSSHProps).put("usrname", System.getProperty("user.name", ""));
            }
        }
        catch (Throwable t2) {}
        try {
            MindTerm.javaVersion = System.getProperty("java.version");
            MindTerm.javaVendor = System.getProperty("java.vendor");
            MindTerm.osName = System.getProperty("os.name");
            MindTerm.osArch = System.getProperty("os.arch");
            MindTerm.osVersion = System.getProperty("os.version");
        }
        catch (Throwable t3) {}
    }
    
    public void initGUI() {
        MenuBar menubar = null;
        Container container;
        if (this.separateFrame) {
            (this.frame = new Frame()).addWindowListener(new WindowAdapter() {
                public void windowClosing(final WindowEvent e) {
                    MindTerm.this.windowClosing(e);
                }
                
                public void windowDeiconified(final WindowEvent e) {
                    MindTerm.this.term.requestFocus();
                }
            });
            container = this.frame;
            if (this.haveMenus && !this.usePopMenu) {
                menubar = new MenuBar();
                this.frame.setMenuBar(menubar);
                this.frame.addNotify();
                this.frame.validate();
            }
        }
        else {
            Component comp = this;
            do {
                comp = comp.getParent();
            } while (!(comp instanceof Frame));
            this.frame = (Frame)comp;
            container = this;
        }
        this.term = new TerminalWin(this.frame, new TerminalXTerm(), this.termProps);
        if (this.mergedTermProps) {
            this.term.setPropsChanged(true);
        }
        if (this.haveMenus) {
            try {
                Class c = Class.forName("mindbright.ssh.SSHMenuHandlerFull");
                final SSHMenuHandler menus = c.newInstance();
                menus.init(this, this.client, this.frame, this.term);
                c = Class.forName("mindbright.terminal.TerminalMenuHandlerFull");
                final TerminalMenuHandler tmenus = c.newInstance();
                tmenus.setTerminalWin(this.term);
                this.term.setMenus(tmenus);
                this.client.setMenus(menus);
                if (menubar == null) {
                    final PopupMenu popupmenu = this.term.getPopupMenu("MindTerm Menu");
                    menus.preparePopupMenu(popupmenu);
                    menus.setPopupButton(this.popButtonNum);
                }
                else {
                    menus.prepareMenuBar(menubar);
                }
                tmenus.setTerminalMenuListener(menus);
            }
            catch (Throwable t) {
                System.out.println("Full menus can't be enabled since classes are missing");
                this.term.setMenus(null);
                this.client.setMenus(null);
            }
        }
        container.setLayout(new BorderLayout());
        container.add(this.term.getPanelWithScrollbar(), "Center");
        this.frame.pack();
        this.frame.show();
        this.term.requestFocus();
    }
    
    public synchronized void windowClosing(final WindowEvent e) {
        if (this.isClosing) {
            return;
        }
        this.isClosing = true;
        if (!this.confirmClose()) {
            this.isClosing = false;
            return;
        }
        if (this.separateFrame && this.haveGUI && this.frame != null) {
            this.frame.dispose();
        }
        if (this.clientThread != null && this.clientThread.isAlive()) {
            removeTerminal(this);
        }
    }
    
    public void doExit() {
        System.out.println("Thank you for using MindTerm...");
        if (!this.separateFrame && this.term != null) {
            try {
                this.term.cursorSetPos(this.term.rows() - 1, 0, false);
                for (int i = 0; i < this.term.rows(); ++i) {
                    this.term.write("\n\r");
                    Thread.sleep(50L);
                }
                this.term.cursorSetPos(0, 0, false);
                for (int i = 0; i < this.term.rows() - 1; ++i) {
                    this.term.write(".\n\r");
                    Thread.sleep(50L);
                }
                this.term.write("Thank you for using MindTerm...");
                for (int i = 0; i < this.term.rows() - 1; ++i) {
                    this.term.write("\n\r");
                    Thread.sleep(50L);
                }
                this.term.cursorSetPos(2, 0, false);
                this.term.setAttribute(1, true);
                this.term.write("Visit <http://www.mindbright.se/mindterm> for more information.");
                this.term.cursorSetPos(this.term.rows() - 1, this.term.cols() - 1, false);
            }
            catch (Exception ex) {}
        }
        if (SSH.secureRandom != null && SSH.secureRandom().updater != null && SSH.secureRandom().updater.isAlive() && !this.weAreAnApplet) {
            System.exit(0);
        }
    }
    
    public boolean confirmClose() {
        if (this.client != null && !this.confirmedClose) {
            try {
                this.client.getPropertyHandler().checkSave();
            }
            catch (IOException ee) {
                this.client.alert("Error saving settings: " + ee.getMessage());
            }
            if (this.client.isOpened() && !this.client.askConfirmation("Do you really want to disconnect from " + this.client.getPropertyHandler().getProperty("server") + "?", false)) {
                this.confirmedClose = false;
            }
            else {
                this.confirmedClose = true;
            }
        }
        return this.confirmedClose;
    }
    
    void initParams(final MindTerm mindterm) {
        this.sshHomeDir = mindterm.sshHomeDir;
        this.propsFile = mindterm.propsFile;
        this.usePopMenu = mindterm.usePopMenu;
        this.haveMenus = mindterm.haveMenus;
        this.haveGUI = mindterm.haveGUI;
        this.cmdsh = mindterm.cmdsh;
        this.quiet = mindterm.quiet;
        this.separateFrame = true;
        this.weAreAnApplet = mindterm.weAreAnApplet;
        this.autoLoadProps = mindterm.autoLoadProps;
        this.popButtonNum = mindterm.popButtonNum;
    }
    
    public void cloneWindow() {
        final MindTerm mindterm = new MindTerm(this.sshProps, this.termProps);
        mindterm.initParams(this);
        mindterm.sshClone = this.client;
        new Thread(mindterm).start();
    }
    
    public void newWindow() {
        final MindTerm mindterm = new MindTerm(MindTerm.paramSSHProps, MindTerm.paramTermProps);
        mindterm.initParams(this);
        new Thread(mindterm).start();
    }
    
    public void close() {
        if (!this.confirmClose()) {
            return;
        }
    }
    
    public void exit() {
        if (!this.confirmClose()) {
            return;
        }
        final Enumeration enum1 = MindTerm.terminals.elements();
        while (enum1.hasMoreElements()) {
            final MindTerm mt = enum1.nextElement();
            if (mt.clientThread != null) {}
        }
    }
    
    static {
        MindTerm.paramTermProps = new Properties();
        MindTerm.paramSSHProps = new Properties();
        MindTerm.javaVersion = "<unknown>";
        MindTerm.javaVendor = "<unknown>";
        MindTerm.osName = "<unknown>";
        MindTerm.osArch = "<unknown>";
        MindTerm.osVersion = "<unknown>";
        MindTerm.terminals = new Hashtable();
    }
}
