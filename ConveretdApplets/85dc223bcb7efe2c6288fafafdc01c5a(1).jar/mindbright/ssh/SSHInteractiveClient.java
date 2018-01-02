// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import mindbright.terminal.Terminal;
import mindbright.terminal.TerminalWin;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import mindbright.net.WebProxyException;
import java.io.IOException;

public final class SSHInteractiveClient extends SSHClient implements Runnable, SSHInteractor
{
    public static final boolean expires = false;
    public static final boolean licensed = false;
    public static final String licenseMessage = "This copy of MindTerm is licensed to ";
    public static final String licensee = "nobody";
    public static final long validFrom = 965157940452L;
    public static final long validTime = 2851200000L;
    public static boolean wantHelpInfo;
    public static String customStartMessage;
    Thread dumbConsoleThread;
    SSHMenuHandler menus;
    SSHStdIO sshStdIO;
    SSHPropertyHandler propsHandler;
    public boolean quiet;
    boolean initQuiet;
    
    public static String copyright() {
        return "Copyright (c) 1998-2000 by Mindbright Technology AB, Stockholm, Sweden";
    }
    
    public SSHInteractiveClient(final boolean quiet, final boolean cmdsh, final SSHPropertyHandler propsHandler) {
        super(propsHandler, propsHandler);
        (this.propsHandler = propsHandler).setInteractor(this.interactor = this);
        propsHandler.setClient(this);
        this.quiet = quiet;
        this.initQuiet = quiet;
        this.setConsole(new SSHStdIO());
        (this.sshStdIO = (SSHStdIO)this.console).setClient(this);
        this.sshStdIO.enableCommandShell(cmdsh);
    }
    
    public SSHInteractiveClient(final SSHInteractiveClient clone) {
        this(true, clone.sshStdIO.hasCommandShell(), new SSHPropertyHandler(clone.propsHandler));
        this.activateTunnels = false;
        SSHInteractiveClient.wantHelpInfo = SSHInteractiveClient.wantHelpInfo;
        SSHInteractiveClient.customStartMessage = SSHInteractiveClient.customStartMessage;
    }
    
    public void setMenus(final SSHMenuHandler menus) {
        this.menus = menus;
    }
    
    public SSHPropertyHandler getPropertyHandler() {
        return this.propsHandler;
    }
    
    public void updateMenus() {
        if (this.menus != null) {
            this.menus.update();
        }
    }
    
    public void printCopyright() {
        this.console.println(copyright());
        if (SSHInteractiveClient.customStartMessage != null) {
            this.console.println(SSHInteractiveClient.customStartMessage);
        }
    }
    
    void printHelpInfo() {
        if (!SSHInteractiveClient.wantHelpInfo) {
            return;
        }
        if (this.propsHandler.getSSHHomeDir() != null) {
            this.console.println("MindTerm home: " + this.propsHandler.getSSHHomeDir());
        }
        if (this.sshStdIO.hasCommandShell()) {
            this.console.println("\tpress <ctrl> + 'D' to enter local command shell");
            if (this.isDumb()) {
                this.console.println("\t(...you might have to press ENTER also...)");
            }
        }
        if (this.menus != null && this.menus.havePopupMenu) {
            this.console.println("\tpress <ctrl> + <mouse-" + this.menus.getPopupButton() + "> for main-menu");
        }
        this.console.println("");
    }
    
    boolean hasExpired() {
        final boolean expired = false;
        final long now = System.currentTimeMillis();
        return false;
    }
    
    void initRandomSeed() {
        this.console.print("Initializing random generator, please wait...");
        SSH.initSeedGenerator();
        this.console.println("done");
    }
    
    public void doSingleCommand(final String commandLine, final boolean background, final long msTimeout) throws IOException {
        final boolean haveDumbConsole = this.propsHandler.wantPTY() && this.isDumb();
        this.initRandomSeed();
        this.console.println("");
        this.printHelpInfo();
        this.commandLine = commandLine;
        this.bootSSH(false);
        if (haveDumbConsole) {
            this.startDumbConsole();
        }
        if (background) {
            this.startExitMonitor(msTimeout);
        }
        else {
            this.waitForExit(msTimeout);
        }
        if (haveDumbConsole) {
            this.stopDumbConsole();
        }
    }
    
    public void run() {
        this.initRandomSeed();
        if (!this.hasExpired()) {
            for (boolean keepRunning = true; keepRunning; keepRunning = this.sshStdIO.commandShell.doCommandShell()) {
                boolean doCommandShell = false;
                boolean gotExtMsg = false;
                try {
                    this.console.println("");
                    this.printHelpInfo();
                    this.bootSSH(true);
                    if (this.isDumb()) {
                        this.startDumbConsole();
                    }
                    this.controller.waitForExit();
                    if (this.isDumb()) {
                        this.stopDumbConsole();
                    }
                    if (this.sshStdIO.isConnected()) {
                        this.sshStdIO.serverDisconnect("\n\r\n\rServer died or connection lost");
                    }
                    Thread.sleep(1000L);
                    try {
                        this.propsHandler.checkSave();
                    }
                    catch (IOException e7) {
                        this.alert("Error saving settings!");
                    }
                }
                catch (AuthFailException e) {
                    this.console.println("");
                    this.console.println(e.getMessage());
                    this.propsHandler.clearPasswords();
                }
                catch (WebProxyException e2) {
                    this.console.println("");
                    this.console.println(e2.getMessage());
                    this.propsHandler.clearPasswords();
                }
                catch (SSHStdIO.CtrlDPressedException e8) {
                    doCommandShell = true;
                }
                catch (SSHStdIO.SSHExternalMessage e3) {
                    gotExtMsg = true;
                    this.console.println("");
                    this.console.println(e3.getMessage());
                }
                catch (UnknownHostException e4) {
                    final String host = e4.getMessage();
                    if (this.propsHandler.getProperty("proxytype").equals("none")) {
                        this.console.println("Unknown host: " + host);
                    }
                    else {
                        this.console.println("Unknown proxy host: " + host);
                    }
                    this.propsHandler.clearServerSetting();
                }
                catch (FileNotFoundException e5) {
                    this.console.println("File not found: " + e5.getMessage());
                }
                catch (Exception e6) {
                    String msg = e6.getMessage();
                    if (msg == null || msg.trim().length() == 0) {
                        msg = e6.toString();
                    }
                    this.console.println("");
                    this.console.println("Error connecting to " + this.propsHandler.getProperty("server") + ", reason:");
                    this.console.println("-> " + msg);
                    if (SSH.DEBUGMORE) {
                        System.out.println("If an error occured, please send the below stacktrace to mats@mindbright.se");
                        e6.printStackTrace();
                    }
                }
                catch (ThreadDeath death) {
                    if (this.controller != null) {
                        this.controller.killAll();
                    }
                    this.controller = null;
                    throw death;
                }
                this.propsHandler.passivateProperties();
                this.activateTunnels = true;
                this.propsHandler.currentPropsFile = null;
                if (!this.propsHandler.savePasswords || this.usedOTP) {
                    this.propsHandler.clearPasswords();
                }
                if (!gotExtMsg) {
                    if (!this.propsHandler.autoLoadProps) {
                        this.propsHandler.clearPasswords();
                        this.initQuiet = false;
                    }
                    this.quiet = false;
                }
                this.controller = null;
                final TerminalWin t = this.getTerminalWin();
                if (t != null) {
                    t.setTitle(null);
                }
                if (doCommandShell && this.sshStdIO.hasCommandShell()) {}
            }
            return;
        }
    Label_0011_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        Thread.sleep(100000L);
                    }
                }
                catch (InterruptedException e9) {
                    continue Label_0011_Outer;
                }
                continue;
            }
        }
    }
    
    public boolean isDumb() {
        return this.console.getTerminal() == null;
    }
    
    public TerminalWin getTerminalWin() {
        final Terminal term = this.console.getTerminal();
        if (term != null && term instanceof TerminalWin) {
            return (TerminalWin)term;
        }
        return null;
    }
    
    public void startDumbConsole() {
        final Runnable dumbConsole = new DumbConsoleThread(this.controller, this.sshStdIO);
        (this.dumbConsoleThread = new Thread(dumbConsole)).start();
    }
    
    public void stopDumbConsole() {
    }
    
    public void updateTitle() {
        this.sshStdIO.updateTitle();
    }
    
    public void propsStateChanged(final SSHPropertyHandler props) {
        this.updateMenus();
    }
    
    public void startNewSession(final SSHClient client) {
    }
    
    public void sessionStarted(final SSHClient client) {
        this.quiet = this.initQuiet;
    }
    
    public boolean quietPrompts() {
        return this.commandLine != null || this.quiet;
    }
    
    public boolean isVerbose() {
        return SSHInteractiveClient.wantHelpInfo;
    }
    
    public String promptLine(final String prompt, final String defaultVal) throws IOException {
        return this.sshStdIO.promptLine(prompt, defaultVal, false);
    }
    
    public String promptPassword(final String prompt) throws IOException {
        return this.sshStdIO.promptLine(prompt, "", true);
    }
    
    public boolean askConfirmation(final String message, final boolean defAnswer) {
        boolean confirm = false;
        try {
            confirm = this.askConfirmation(message, true, defAnswer);
        }
        catch (IOException ex) {}
        return confirm;
    }
    
    public boolean askConfirmation(final String message, final boolean preferDialog, final boolean defAnswer) throws IOException {
        boolean confirm = false;
        if (this.menus != null && preferDialog) {
            confirm = this.menus.confirmDialog(message, defAnswer);
        }
        else {
            final String answer = this.promptLine(message + (defAnswer ? " ([yes]/no) " : "(yes/[no]) "), "");
            if (answer.equalsIgnoreCase("yes") || answer.equals("y")) {
                confirm = true;
            }
            else if (answer.equals("")) {
                confirm = defAnswer;
            }
        }
        return confirm;
    }
    
    public void connected(final SSHClient client) {
        this.updateMenus();
        if (SSHInteractiveClient.wantHelpInfo) {
            this.console.println("Connected to server running " + this.srvVersionStr);
            if (this.sshStdIO.hasCommandShell()) {
                this.console.println("(command shell escape-sequence is '" + this.sshStdIO.commandShell.escapeString() + "')");
            }
            this.console.println("");
        }
    }
    
    public void open(final SSHClient client) {
        this.updateMenus();
        this.updateTitle();
    }
    
    public void disconnected(final SSHClient client, final boolean graceful) {
        this.sshStdIO.breakPromptLine("Login aborted by user");
        this.updateMenus();
        this.updateTitle();
    }
    
    public void report(final String msg) {
        this.console.println(msg);
        this.console.println("");
    }
    
    public void alert(final String msg) {
        if (this.menus != null) {
            if (msg.length() < 35) {
                this.menus.alertDialog(msg);
            }
            else {
                this.menus.textDialog("MindTerm - Alert", msg, 4, 38, true);
            }
        }
        else {
            this.report(msg);
        }
    }
    
    static {
        SSHInteractiveClient.wantHelpInfo = true;
        SSHInteractiveClient.customStartMessage = null;
    }
    
    public static class DumbConsoleThread implements Runnable
    {
        SSHChannelController controller;
        SSHStdIO console;
        
        public DumbConsoleThread(final SSHChannelController controller, final SSHStdIO console) {
            this.controller = controller;
            this.console = console;
        }
        
        public void run() {
            try {
                while (true) {
                    final String line = this.console.promptLine("", "", false);
                    final SSHPduOutputStream stdinPdu = new SSHPduOutputStream(16, this.console.sndCipher);
                    stdinPdu.writeString(line + "\n");
                    this.controller.transmit(stdinPdu);
                    Thread.sleep(400L);
                }
            }
            catch (SSHStdIO.CtrlDPressedException e2) {
                this.controller.sendDisconnect("exit");
            }
            catch (Exception e) {
                this.controller.alert("Error in console-thread: " + e.toString());
            }
        }
    }
}
