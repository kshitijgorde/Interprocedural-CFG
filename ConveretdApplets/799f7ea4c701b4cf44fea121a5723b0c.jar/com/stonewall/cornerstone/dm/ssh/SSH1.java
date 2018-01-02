// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.ssh;

import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.awt.Dimension;
import java.util.Vector;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;
import com.stonewall.cornerstone.dm.cli.ScriptHandler;
import org.xmodel.log.Log;

public class SSH1 extends SshIO
{
    static final Log log;
    protected String user;
    protected String pass;
    private boolean auth;
    protected SshIO handler;
    protected ScriptHandler scriptHandler;
    protected InputStream in;
    protected OutputStream out;
    protected Socket socket;
    protected String host;
    protected int port;
    protected int timeout;
    protected Vector script;
    private final String waitMarkers = "(.|\\s)*(>|#|\\$|(p|P)assword:)(.|\\s)*";
    private byte[] buffer;
    private int pos;
    
    static {
        log = Log.getLog(SSH1.class);
    }
    
    public SSH1() {
        this.auth = false;
        this.scriptHandler = new ScriptHandler();
        this.timeout = 6000;
        this.script = new Vector();
        this.handler = new SshIO() {
            @Override
            public String getTerminalType() {
                return "dumb";
            }
            
            public Dimension getWindowSize() {
                return new Dimension(80, 25);
            }
            
            public void setLocalEcho(final boolean echo) {
            }
            
            public void write(final byte[] b) throws IOException {
                SSH1.this.out.write(b);
            }
            
            @Override
            public int read(final byte[] b) throws IOException {
                return 0;
            }
        };
    }
    
    public void setTimeout(final int timeout) {
        this.timeout = timeout;
    }
    
    public void connect(final String host, final int port) throws IOException {
        SSH1.log.debug("SSH: connect(" + host + "," + port + ")");
        try {
            this.socket = new Socket(host, port);
            this.in = this.socket.getInputStream();
            this.out = this.socket.getOutputStream();
        }
        catch (Exception e) {
            SSH1.log.error(this, e);
            this.disconnect();
            throw (IOException)e;
        }
    }
    
    public boolean isDisconnected() {
        return this.socket == null;
    }
    
    @Override
    public void disconnect() {
        try {
            this.executeCommand("exit", null);
            if (this.socket != null) {
                this.socket.close();
            }
        }
        catch (Exception ex) {}
        this.socket = null;
        super.disconnect();
    }
    
    public void notifyEndOfRecord() {
    }
    
    public void login(final String user, final String pwd) throws IOException {
        if (user != null && !user.equals("")) {
            this.setLogin(user);
        }
        this.setPassword(pwd);
    }
    
    public String loginResult(final String user, final String pwd) {
        try {
            this.login(user, pwd);
            return this.waitfor(Collections.singletonList("(.|\\s)*(>|#|\\$|(p|P)assword:)(.|\\s)*"));
        }
        catch (Exception e) {
            return "";
        }
    }
    
    public String loginResult(final String loginPrompt, final String passwdPrompt, final String user, final String pwd) {
        try {
            this.login(loginPrompt, passwdPrompt, user, pwd);
            return this.waitfor(Collections.singletonList("(.|\\s)*(>|#|\\$|(p|P)assword:)(.|\\s)*"));
        }
        catch (Exception e) {
            return "";
        }
    }
    
    public void login(final String loginPrompt, final String passwdPrompt, final String user, final String pwd) throws IOException {
        this.setLogin(user);
        this.setPassword(pwd);
    }
    
    public String executeCommand(final String cmd, final List<String> cmdWaits) throws Exception {
        this.write((String.valueOf(cmd) + "\n").getBytes());
        if (cmdWaits != null && !cmdWaits.isEmpty()) {
            return this.waitfor(cmdWaits);
        }
        return this.waitfor();
    }
    
    public void write(final byte[] b) throws IOException {
        for (int i = 0; i < b.length; ++i) {
            switch (b[i]) {
                case 10: {
                    b[i] = 13;
                    break;
                }
            }
        }
        this.handler.sendData(new String(b));
    }
    
    @Override
    public String getTerminalType() {
        return "dumb";
    }
    
    public Dimension getWindowSize() {
        return new Dimension(80, 24);
    }
    
    public void setLocalEcho(final boolean echo) {
        SSH1.log.debug("local echo " + (echo ? "on" : "off"));
    }
    
    public String send(final String cmd) throws IOException {
        this.write((String.valueOf(cmd) + "\n").getBytes());
        if (this.getPrompt() != null) {
            return this.waitfor(Collections.singletonList("(.|\\s)*(>|#|\\$|(p|P)assword:)(.|\\s)*"));
        }
        return this.waitfor();
    }
    
    @Override
    public int read(final byte[] b) throws IOException {
        if (this.buffer != null) {
            final int amount = (this.buffer.length - this.pos <= b.length) ? (this.buffer.length - this.pos) : b.length;
            System.arraycopy(this.buffer, this.pos, b, 0, amount);
            if (this.pos + amount < this.buffer.length) {
                this.pos += amount;
            }
            else {
                this.buffer = null;
            }
            return amount;
        }
        int n = this.in.read(b);
        if (n > 0) {
            final byte[] tmp = new byte[n];
            System.arraycopy(b, 0, tmp, 0, n);
            this.pos = 0;
            this.buffer = this.handler.handleSSH(tmp);
            if (this.buffer != null && this.buffer.length > 0) {
                SSH1.log.debug("ssh: " + this.buffer);
            }
            if (this.buffer == null || this.buffer.length <= 0) {
                return 0;
            }
            SSH1.log.debug("ssh: incoming=" + n + " now=" + this.buffer.length);
            final int amount2 = (this.buffer.length <= b.length) ? this.buffer.length : b.length;
            System.arraycopy(this.buffer, 0, b, 0, amount2);
            n = (this.pos = amount2);
            if (amount2 == this.buffer.length) {
                this.buffer = null;
                this.pos = 0;
            }
        }
        return n;
    }
}
