// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Vector;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;
import de.mud.telnet.ScriptHandler;

public class Wrapper
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
    
    public Wrapper() {
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
        }
        catch (Exception e) {
            System.err.println("Wrapper: " + e);
            this.disconnect();
            throw (IOException)e;
        }
    }
    
    public void disconnect() throws IOException {
        if (this.socket != null) {
            this.socket.close();
        }
    }
    
    public void login(final String user, final String pwd) throws IOException {
        final String[] loginstrings = { "login:", "username:" };
        this.waitfor(loginstrings);
        this.send(user);
        this.waitfor("Password:");
        this.send(pwd);
    }
    
    public void setPrompt(final String prompt) {
        this.prompt = prompt;
    }
    
    public String getPrompt() {
        return this.prompt;
    }
    
    public String send(final String cmd) throws IOException {
        return null;
    }
    
    public String waitfor(final String[] searchElements) throws IOException {
        final ScriptHandler[] handlers = new ScriptHandler[searchElements.length];
        for (int i = 0; i < searchElements.length; ++i) {
            (handlers[i] = new ScriptHandler()).setup(searchElements[i]);
        }
        final byte[] b1 = { 0 };
        int n = 0;
        final StringBuffer ret = new StringBuffer();
        while (n >= 0) {
            n = this.read(b1);
            if (n > 0) {
                final String current = new String(b1, 0, n);
                ret.append(current);
                for (int j = 0; j < handlers.length; ++j) {
                    if (handlers[j].match(ret.toString().getBytes(), ret.length())) {
                        return ret.toString();
                    }
                }
            }
        }
        return null;
    }
    
    public String waitfor(final String match) throws IOException {
        final String[] matches = { match };
        return this.waitfor(matches);
    }
    
    public int read(final byte[] b) throws IOException {
        return -1;
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
