// 
// Decompiled by Procyon v0.5.30
// 

package socket;

import java.util.Date;
import java.io.IOException;

public class TelnetWrapper
{
    TelnetIO tio;
    public boolean debug;
    private String prompt;
    private static String defaultPrompt;
    private static String defaultLogin;
    private static String defaultPassword;
    
    static {
        TelnetWrapper.defaultPrompt = "$ ";
        TelnetWrapper.defaultLogin = null;
        TelnetWrapper.defaultPassword = null;
    }
    
    public TelnetWrapper(final String host) throws IOException {
        this.debug = false;
        this.tio = new TelnetIO();
        this.setPrompt(TelnetWrapper.defaultPrompt);
        this.tio.connect(host);
        if (TelnetWrapper.defaultLogin != null && TelnetWrapper.defaultPassword != null) {
            this.login(TelnetWrapper.defaultLogin, TelnetWrapper.defaultPassword);
        }
    }
    
    public TelnetWrapper(final String host, final int port) throws IOException {
        this.debug = false;
        this.tio = new TelnetIO();
        this.setPrompt(TelnetWrapper.defaultPrompt);
        this.tio.connect(host, port);
        if (TelnetWrapper.defaultLogin != null && TelnetWrapper.defaultPassword != null) {
            this.login(TelnetWrapper.defaultLogin, TelnetWrapper.defaultPassword);
        }
    }
    
    public int available() throws IOException {
        return this.tio.available();
    }
    
    public void disconnect() throws IOException {
        if (this.tio != null) {
            this.tio.disconnect();
        }
        this.tio = null;
    }
    
    public void finalize() {
        try {
            this.disconnect();
        }
        catch (IOException ex) {}
    }
    
    public void login(final String loginName, final String password) throws IOException {
        this.wait("login:");
        this.send(String.valueOf(loginName) + "\r");
        this.wait("Password:");
        this.sendLine(String.valueOf(password) + "\r");
    }
    
    public static void main(final String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Usage: TelnetWrapper host login password prompt");
        }
        final String host = args[0];
        final String login = args[1];
        final String password = args[2];
        final String prompt = args[3];
        final Date now = new Date();
        final String timestamp = String.valueOf(now.getYear()) + "-" + (now.getMonth() + 1) + "-" + now.getDate() + "-" + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
        final TelnetWrapper telnet = new TelnetWrapper(host);
        telnet.debug = true;
        telnet.setPrompt(prompt);
        telnet.login(login, password);
        telnet.send("touch /tmp/TELNET_WRAPPER-" + timestamp + "\r");
        telnet.wait(prompt);
        final String ls = telnet.sendLine("ls /tmp");
        System.out.println(ls);
        telnet.disconnect();
    }
    
    public String receive() throws IOException {
        final String s = new String(this.receiveBytes(), 0);
        if (this.debug) {
            System.out.println(s);
        }
        return s;
    }
    
    public byte[] receiveBytes() throws IOException {
        return this.tio.receive();
    }
    
    public String receiveUntil(final String token) throws IOException {
        return this.receiveUntil(token, -1L);
    }
    
    public String receiveUntil(final String token, final long timeout) throws IOException, TimedOutException {
        final StringBuffer buf = new StringBuffer();
        long deadline = 0L;
        if (timeout >= 0L) {
            deadline = new Date().getTime() + timeout;
        }
        do {
            if (timeout >= 0L) {
                while (this.available() <= 0) {
                    if (new Date().getTime() > deadline) {
                        throw new TimedOutException();
                    }
                    try {
                        Thread.currentThread();
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex) {}
                }
            }
            buf.append(this.receive());
        } while (buf.toString().indexOf(token) == -1);
        return buf.toString();
    }
    
    public void send(final String s) throws IOException {
        if (this.debug) {
            System.out.println(s);
        }
        final byte[] buf = new byte[s.length()];
        s.getBytes(0, buf.length, buf, 0);
        this.tio.send(buf);
    }
    
    public void send(final byte[] buf) throws IOException {
        this.tio.send(buf);
    }
    
    public String sendLine(String command) throws IOException {
        if (command.charAt(command.length() - 1) != '\r') {
            command = String.valueOf(command) + "\r";
        }
        this.send(command);
        final String s = this.receiveUntil(this.prompt);
        return s.substring(command.length() + 1, s.indexOf(this.prompt));
    }
    
    public static void setDefaultPrompt(final String prompt) {
        if (prompt == null) {
            throw new IllegalArgumentException("null prompt.");
        }
        TelnetWrapper.defaultPrompt = prompt;
    }
    
    public static void setLogin(final String login, final String password) {
        if (login == null || password == null) {
            throw new IllegalArgumentException("null login or password.");
        }
        TelnetWrapper.defaultLogin = login;
        TelnetWrapper.defaultPassword = password;
    }
    
    public void setPrompt(final String prompt) {
        if (prompt == null) {
            throw new IllegalArgumentException("null prompt.");
        }
        this.prompt = prompt;
    }
    
    public static void unsetLogin() {
        TelnetWrapper.defaultLogin = (TelnetWrapper.defaultPassword = null);
    }
    
    public void wait(final String token) throws IOException {
        this.wait(token, -1L);
    }
    
    public void wait(final String token, final long timeout) throws IOException, TimedOutException {
        if (this.debug) {
            System.out.println("wait(" + token + ", " + timeout + ")...");
        }
        String tmp = "";
        long deadline = 0L;
        if (timeout >= 0L) {
            deadline = new Date().getTime() + timeout;
        }
        do {
            if (timeout >= 0L) {
                while (this.available() <= 0) {
                    if (new Date().getTime() > deadline) {
                        throw new TimedOutException();
                    }
                    try {
                        Thread.currentThread();
                        Thread.sleep(100L);
                    }
                    catch (InterruptedException ex) {}
                }
            }
            tmp = this.receive();
        } while (tmp.indexOf(token) == -1);
        if (this.debug) {
            System.out.println("wait(" + token + ", " + timeout + ") successful.");
        }
    }
}
