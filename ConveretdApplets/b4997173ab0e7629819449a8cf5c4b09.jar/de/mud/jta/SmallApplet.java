// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.io.IOException;
import de.mud.telnet.TelnetProtocolHandler;
import de.mud.terminal.vt320;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.applet.Applet;

public class SmallApplet extends Applet implements Runnable
{
    private static final int debug = 0;
    private String host;
    private String port;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private Thread reader;
    private vt320 terminal;
    private TelnetProtocolHandler telnet;
    private boolean localecho;
    
    public SmallApplet() {
        this.localecho = false;
    }
    
    public void init() {
        this.host = this.getParameter("host");
        this.port = this.getParameter("port");
        this.terminal = new vt320() {
            public void write(final byte[] b) {
                try {
                    SmallApplet.this.telnet.transpose(b);
                }
                catch (IOException e) {
                    System.err.println("jta: error sending data: " + e);
                }
            }
        };
        this.setLayout(new BorderLayout());
        this.add("Center", this.terminal);
        this.telnet = new TelnetProtocolHandler() {
            public String getTerminalType() {
                return SmallApplet.this.terminal.getTerminalID();
            }
            
            public Dimension getWindowSize() {
                return SmallApplet.this.terminal.getScreenSize();
            }
            
            public void setLocalEcho(final boolean echo) {
                SmallApplet.this.localecho = true;
            }
            
            public void notifyEndOfRecord() {
            }
            
            public void write(final byte[] b) throws IOException {
                SmallApplet.this.os.write(b);
            }
        };
    }
    
    public void start() {
        if (this.socket != null) {
            this.stop();
        }
        try {
            this.socket = new Socket(this.host, Integer.parseInt(this.port));
            this.is = this.socket.getInputStream();
            this.os = this.socket.getOutputStream();
            (this.reader = new Thread(this)).start();
        }
        catch (Exception e) {
            System.err.println("jta: error connecting: " + e);
            this.stop();
        }
    }
    
    public void stop() {
        if (this.socket != null) {
            try {
                this.socket.close();
            }
            catch (Exception e) {
                System.err.println("jta: could not cleanly disconnect: " + e);
            }
            this.socket = null;
            try {
                this.reader.stop();
            }
            catch (Exception ex) {}
            this.reader = null;
        }
    }
    
    public void run() {
        final byte[] b = new byte[256];
        int n = 0;
        while (n >= 0) {
            try {
                n = this.is.read(b);
                n = this.telnet.negotiate(b, n);
                if (n <= 0) {
                    continue;
                }
                this.terminal.putString(new String(b, 0, n));
            }
            catch (IOException e) {
                this.stop();
                break;
            }
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
