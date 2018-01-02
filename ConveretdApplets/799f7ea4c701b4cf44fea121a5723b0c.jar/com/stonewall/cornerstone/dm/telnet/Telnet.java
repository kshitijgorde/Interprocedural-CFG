// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.telnet;

import java.awt.Dimension;
import java.util.List;
import java.util.Collections;
import java.io.IOException;
import java.net.SocketException;
import java.util.Vector;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;
import com.stonewall.cornerstone.dm.cli.ScriptHandler;
import org.xmodel.log.Log;

public class Telnet extends TelnetProtocolHandler
{
    static final Log log;
    protected ScriptHandler scriptHandler;
    protected InputStream in;
    protected OutputStream out;
    protected Socket socket;
    protected String host;
    protected int port;
    protected int timeout;
    protected Vector script;
    
    static {
        log = Log.getLog(Telnet.class);
    }
    
    public Telnet() {
        this.scriptHandler = new ScriptHandler();
        this.timeout = 30000;
        this.script = new Vector();
        this.host = "";
        this.port = 23;
        final String p = System.getProperty("cornerstone.net.telnet.timeout", "30000");
        this.timeout = Integer.valueOf(p);
    }
    
    public void setTimeout(final int timeout) {
        this.timeout = timeout;
    }
    
    public void connect(final String host, final int port) throws IOException {
        Telnet.log.debug("Telnet: connect(" + host + "," + port + ")");
        try {
            this.socket = new Socket(host, port);
            this.host = host;
            this.port = port;
            Telnet.log.debug("Telnet: created socket:" + this.socket);
            try {
                this.socket.setSoTimeout(this.timeout);
            }
            catch (SocketException se) {
                Telnet.log.error("Unable to set socket option SO_TIMEOUT");
            }
            this.in = this.socket.getInputStream();
            this.out = this.socket.getOutputStream();
            this.reset();
        }
        catch (Exception e) {
            Telnet.log.error(this, e);
            this.disconnect();
            throw (IOException)e;
        }
    }
    
    public void disconnect() {
        try {
            Telnet.log.debug("Telnet: disconnect()");
            if (this.socket != null) {
                this.socket.close();
            }
        }
        catch (Exception e) {
            Telnet.log.error(this, e);
        }
        this.socket = null;
    }
    
    public boolean isDisconnected() {
        return this.socket == null;
    }
    
    public void notifyEndOfRecord() {
    }
    
    public void login(final String user, final String pwd) throws IOException {
        final String regex = this.loginRegex("Login", "Password", this.prompt);
        if (user != null && !user.equals("")) {
            final String s = this.waitfor(Collections.singletonList(regex));
            if (s == null || s.contains("Login")) {}
            this.send(user);
        }
        if (this.waitfor(Collections.singletonList("Password")) == null) {
            throw new IOException("No prompt");
        }
        this.send(pwd);
    }
    
    public void login(final String loginPrompt, final String passwdPrompt, final String user, final String pwd) throws IOException {
        final String regex = this.loginRegex(loginPrompt, passwdPrompt, this.prompt);
        if (user != null && !user.equals("")) {
            final String s = this.waitfor(Collections.singletonList(regex));
            if (s == null) {
                throw new IOException("No prompt");
            }
            if (s.contains(loginPrompt)) {
                this.send(user);
            }
            else {
                if (s.contains(passwdPrompt)) {
                    this.send(pwd);
                    return;
                }
                throw new IOException("No prompt");
            }
        }
        final String s = this.waitfor(Collections.singletonList(regex));
        if (s != null && s.contains(passwdPrompt)) {
            this.send(pwd);
            return;
        }
        throw new IOException("No prompt");
    }
    
    public String loginResult(final String loginPrompt, final String passwdPrompt, final String user, final String pwd) {
        final String regex = this.loginRegex(loginPrompt, passwdPrompt, this.prompt);
        try {
            this.login(loginPrompt, passwdPrompt, user, pwd);
            return this.waitfor(Collections.singletonList(regex));
        }
        catch (Exception e) {
            Telnet.log.error(this, e);
            return null;
        }
    }
    
    public void send(final String cmd) throws IOException {
        Telnet.log.debug("send: execute command(" + this.host + "," + this.port + ")" + this.socket);
        this.write((String.valueOf(cmd) + "\n").getBytes());
    }
    
    public String executeCommand(final String cmd, final List<String> cmdWaits) throws Exception {
        Telnet.log.debug("Telnet: execute command: " + cmd + " (" + this.host + "," + this.port + ")" + this.socket);
        this.write((String.valueOf(cmd) + "\n").getBytes());
        if (cmdWaits != null && !cmdWaits.isEmpty()) {
            return this.waitfor(cmdWaits);
        }
        return this.waitfor();
    }
    
    @Override
    public int read(final byte[] b) throws IOException {
        int n = this.negotiate(b);
        if (n > 0) {
            return n;
        }
        while (n <= 0) {
            do {
                n = this.negotiate(b);
                if (n > 0) {
                    return n;
                }
            } while (n == 0);
            n = this.in.read(b);
            if (n < 0) {
                return n;
            }
            this.inputfeed(b, n);
            n = this.negotiate(b);
        }
        return n;
    }
    
    public void write(final byte[] b) throws IOException {
        this.out.write(b);
    }
    
    public String getTerminalType() {
        return "dumb";
    }
    
    public Dimension getWindowSize() {
        return new Dimension(80, 24);
    }
    
    public void setLocalEcho(final boolean echo) {
        Telnet.log.debug("local echo " + (echo ? "on" : "off"));
    }
}
