// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import mindbright.terminal.Terminal;
import java.awt.Frame;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.datatransfer.Clipboard;
import mindbright.security.Cipher;
import mindbright.terminal.TerminalWin;
import java.awt.Container;
import java.awt.Toolkit;
import mindbright.terminal.TerminalClipboard;
import mindbright.terminal.TerminalListener;

public final class SSHStdIO implements TerminalListener, TerminalClipboard, SSHConsole
{
    static Toolkit toolkit;
    SSHCommandShell commandShell;
    Container ownerContainer;
    SSHChannelController controller;
    SSHInteractiveClient client;
    TerminalWin term;
    Cipher sndCipher;
    String ownerName;
    boolean selectionAvailable;
    boolean escapeForced;
    Boolean readLineLock;
    boolean pressedCtrlD;
    boolean isReadingLine;
    boolean echoStar;
    String readLineStr;
    protected boolean isConnected;
    static Clipboard localClipboard;
    SSHExternalMessage extMsg;
    
    public SSHStdIO() {
        this.selectionAvailable = false;
        this.escapeForced = false;
        this.extMsg = null;
        this.readLineLock = new Boolean(false);
        this.controller = null;
        this.sndCipher = null;
        this.isConnected = false;
        this.commandShell = null;
    }
    
    public boolean isConnected() {
        return this.isConnected;
    }
    
    public void setTerminal(final TerminalWin term) {
        this.term = term;
        if (term != null) {
            term.addTerminalListener(this);
            term.addTerminalClipboard(this);
        }
    }
    
    public void setClient(final SSHInteractiveClient client) {
        this.client = client;
    }
    
    public void setOwnerContainer(final Container ownerContainer) {
        this.ownerContainer = ownerContainer;
        if (SSHStdIO.toolkit == null) {
            SSHStdIO.toolkit = Toolkit.getDefaultToolkit();
        }
    }
    
    public void setOwnerName(final String ownerName) {
        this.ownerName = ownerName;
    }
    
    public boolean hasCommandShell() {
        return this.commandShell != null;
    }
    
    public void enableCommandShell(final boolean enable) {
        if (enable) {
            try {
                final Class c = Class.forName("mindbright.ssh.SSHCommandShellImpl");
                (this.commandShell = c.newInstance()).setStdIO(this);
            }
            catch (Throwable t) {
                this.println("");
                this.println("The local command-shell is not available in this distribution.");
            }
        }
        else {
            this.commandShell = null;
        }
    }
    
    public void wantCommandShell() {
        this.escapeForced = true;
    }
    
    public void breakPromptLine(final String msg) {
        if (this.isReadingLine) {
            synchronized (this.readLineLock) {
                this.extMsg = new SSHExternalMessage(msg);
                this.readLineLock.notify();
            }
        }
    }
    
    public String readLine(final String defaultVal) {
        synchronized (this.readLineLock) {
            if (defaultVal != null) {
                this.readLineStr = defaultVal;
                this.term.write(defaultVal);
            }
            else {
                this.readLineStr = "";
            }
            this.isReadingLine = true;
            try {
                this.readLineLock.wait();
            }
            catch (InterruptedException ex) {}
            this.isReadingLine = false;
        }
        return this.readLineStr;
    }
    
    public String promptLine(final String prompt, final String defaultVal, final boolean echoStar) throws IOException {
        String line = null;
        this.pressedCtrlD = false;
        if (this.term != null) {
            this.term.setAttribute(1, true);
            this.term.write(prompt);
            this.term.setAttribute(1, false);
            this.echoStar = echoStar;
            line = this.readLine(defaultVal);
            this.echoStar = false;
        }
        else {
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(prompt);
            line = br.readLine();
            if (line == null || (line.length() > 0 && line.charAt(0) == '\u0004')) {
                this.pressedCtrlD = true;
            }
        }
        if (this.extMsg != null) {
            final SSHExternalMessage msg = this.extMsg;
            this.extMsg = null;
            throw msg;
        }
        if ((this.commandShell != null || this.client.isDumb()) && this.pressedCtrlD) {
            this.pressedCtrlD = false;
            throw new CtrlDPressedException();
        }
        return line;
    }
    
    public void updateTitle() {
        int rows = 0;
        int cols = 0;
        if (this.term == null || this.ownerContainer == null) {
            return;
        }
        String title = null;
        title = this.term.getTitle();
        if (title == null) {
            rows = this.term.rows();
            cols = this.term.cols();
            if (this.client.isOpened()) {
                title = this.client.propsHandler.getProperty("usrname");
                title = title + "@" + this.client.propsHandler.getProperty("server");
                title = title + " <" + this.client.getServerVersion() + ">";
            }
            else {
                title = this.ownerName;
            }
            title = title + " [" + cols + "x" + rows + "]";
            if (!this.client.activateTunnels) {
                title += " (CLONE)";
            }
        }
        if (this.ownerContainer instanceof Frame) {
            ((Frame)this.ownerContainer).setTitle(title);
        }
    }
    
    public Terminal getTerminal() {
        return this.term;
    }
    
    public void stdoutWriteString(final byte[] str) {
        if (this.isConnected) {
            this.print(new String(str));
        }
    }
    
    public void stderrWriteString(final byte[] str) {
        if (this.isConnected) {
            this.print(new String(str));
        }
    }
    
    public void print(final String str) {
        if (this.term != null) {
            this.term.write(str);
        }
        else {
            System.out.print(str);
        }
    }
    
    public void println(final String str) {
        if (this.term != null) {
            this.term.write(str + "\n\r");
        }
        else {
            System.out.println(str);
        }
    }
    
    public void serverConnect(final SSHChannelController controller, final Cipher sndCipher) {
        this.controller = controller;
        this.sndCipher = sndCipher;
        this.isConnected = true;
    }
    
    public void serverDisconnect(final String reason) {
        this.controller = null;
        this.sndCipher = null;
        this.isConnected = false;
        this.println(reason);
    }
    
    public void typedChar(final char c) throws IOException {
        if (this.isConnected) {
            if (this.escapeForced || (this.commandShell != null && this.commandShell.escapeSequenceTyped(c))) {
                this.escapeForced = false;
                this.commandShell.launchCommandShell();
            }
            else {
                this.client.stdinWriteChar(c);
            }
        }
        else {
            synchronized (this.readLineLock) {
                if (this.isReadingLine) {
                    if (c == '\u0004' && this.commandShell != null) {
                        this.pressedCtrlD = true;
                        this.readLineLock.notify();
                    }
                    else if (c == '\u007f' || c == '\b') {
                        if (this.readLineStr.length() > 0) {
                            boolean ctrlChar = false;
                            if (this.readLineStr.charAt(this.readLineStr.length() - 1) < ' ') {
                                ctrlChar = true;
                            }
                            this.readLineStr = this.readLineStr.substring(0, this.readLineStr.length() - 1);
                            this.term.write('\b');
                            if (ctrlChar) {
                                this.term.write('\b');
                            }
                            this.term.write(' ');
                            if (ctrlChar) {
                                this.term.write(' ');
                            }
                            this.term.write('\b');
                            if (ctrlChar) {
                                this.term.write('\b');
                            }
                        }
                        else {
                            this.term.doBell();
                        }
                    }
                    else if (c == '\r') {
                        this.readLineLock.notify();
                        this.term.write("\n\r");
                    }
                    else {
                        this.readLineStr += c;
                        if (this.echoStar) {
                            this.term.write('*');
                        }
                        else {
                            this.term.write(c);
                        }
                    }
                }
            }
        }
    }
    
    public void sendBytes(final byte[] b) throws IOException {
        if (this.isConnected) {
            this.client.stdinWriteString(b);
        }
        else {
            for (int i = 0; i < b.length; ++i) {
                this.typedChar((char)b[i]);
            }
        }
    }
    
    public void signalWindowChanged(final int rows, final int cols, final int vpixels, final int hpixels) {
        if (this.isConnected) {
            this.client.signalWindowChanged(rows, cols, vpixels, hpixels);
        }
        this.updateTitle();
    }
    
    public void setSelection(String selection) {
        final Clipboard cb = getClipboard();
        if (cb == null || this.term == null) {
            return;
        }
        if (selection == null) {
            selection = "";
        }
        final StringSelection sl = new StringSelection(selection);
        cb.setContents(sl, sl);
    }
    
    public String getSelection() {
        final Clipboard cb = getClipboard();
        String sl = null;
        if (cb == null || this.term == null) {
            return sl;
        }
        final Transferable t = cb.getContents(this);
        if (t != null) {
            try {
                sl = (String)t.getTransferData(DataFlavor.stringFlavor);
            }
            catch (Exception e) {
                try {
                    SSHStdIO.toolkit.beep();
                }
                catch (Throwable t2) {}
            }
        }
        else {
            try {
                SSHStdIO.toolkit.beep();
            }
            catch (Throwable t3) {}
        }
        return sl;
    }
    
    public void selectionAvailable(final boolean val) {
        this.selectionAvailable = val;
        this.client.updateMenus();
    }
    
    static synchronized Clipboard getClipboard() {
        Clipboard cb;
        if (SSHStdIO.localClipboard == null) {
            try {
                cb = SSHStdIO.toolkit.getSystemClipboard();
            }
            catch (Throwable e) {
                cb = (SSHStdIO.localClipboard = new Clipboard("MindTerm-local-clipboard"));
            }
        }
        else {
            cb = SSHStdIO.localClipboard;
        }
        return cb;
    }
    
    static {
        SSHStdIO.localClipboard = null;
    }
    
    public static class CtrlDPressedException extends IOException
    {
    }
    
    public static class SSHExternalMessage extends IOException
    {
        public SSHExternalMessage(final String msg) {
            super(msg);
        }
    }
}
