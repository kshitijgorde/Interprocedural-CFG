// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.telnet;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Vector;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;

public class TelnetWrapper extends TelnetProtocolHandler
{
    private static final int debug = 0;
    protected ScriptHandler scriptHandler;
    private Thread reader;
    protected InputStream in;
    protected OutputStream out;
    protected Socket socket;
    protected String host;
    protected int port;
    protected Vector script;
    private String prompt;
    
    public TelnetWrapper() {
        this.scriptHandler = new ScriptHandler();
        this.port = 23;
        this.script = new Vector();
        this.prompt = null;
    }
    
    public void connect(final String host, final int port) throws IOException {
        try {
            this.socket = new Socket(host, port);
            this.in = this.socket.getInputStream();
            this.out = this.socket.getOutputStream();
            this.reset();
        }
        catch (Exception e) {
            System.err.println("TelnetWrapper: " + e);
            this.disconnect();
        }
    }
    
    public void disconnect() throws IOException {
        this.socket.close();
    }
    
    public void notifyEndOfRecord() {
    }
    
    public void login(final String user, final String pwd) throws IOException {
        this.waitfor("login:");
        this.send(user);
        this.waitfor("Password:");
        this.send(pwd);
    }
    
    public void setPrompt(final String prompt) {
        this.prompt = prompt;
    }
    
    public String send(final String cmd) throws IOException {
        this.write((cmd + "\n").getBytes());
        if (this.prompt != null) {
            return this.waitfor(this.prompt);
        }
        return null;
    }
    
    public String waitfor(final String match) throws IOException {
        this.scriptHandler.setup(match);
        final byte[] b = new byte[256];
        int n = 0;
        String ret = "";
        while (n >= 0) {
            n = this.read(b);
            if (n > 0) {
                ret += new String(b, 0, n);
                if (this.scriptHandler.match(b, n)) {
                    return ret;
                }
                continue;
            }
        }
        return null;
    }
    
    public int read(final byte[] b) throws IOException {
        int n = this.in.read(b);
        if (n > 0) {
            n = this.negotiate(b, n);
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
    }
}
