// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Vector;
import java.awt.Component;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;

public class NFParamServ
{
    public String host;
    public int port;
    public String args;
    public URL url;
    public Thread thread;
    public Socket socket;
    public InputStream is;
    public OutputStream os;
    public PrintStream ps;
    public boolean closed;
    public Component comp;
    
    public NFParamServ(final String host, final int port, final String args) {
        this.thread = null;
        this.socket = null;
        this.is = null;
        this.os = null;
        this.ps = null;
        this.closed = false;
        this.comp = null;
        this.host = host;
        this.args = args;
        this.port = port;
        this.url = null;
    }
    
    public NFParamServ(final URL url) {
        this.thread = null;
        this.socket = null;
        this.is = null;
        this.os = null;
        this.ps = null;
        this.closed = false;
        this.comp = null;
        this.url = url;
        this.host = url.getHost();
        this.port = url.getPort();
        if (this.port < 0) {
            this.port = 80;
        }
        this.args = null;
    }
    
    public void stop() {
        try {
            this.thread.stop();
        }
        catch (Exception ex) {}
        finally {
            this.thread = null;
        }
        this.close();
    }
    
    public void open() throws Exception {
        String s = "Unable to connect to server";
        try {
            if (this.url != null) {
                this.socket = null;
                this.os = null;
                this.ps = null;
                this.is = this.url.openStream();
                return;
            }
            this.socket = new Socket(this.host, this.port);
            s = "Unable to send arguments to server";
            this.os = this.socket.getOutputStream();
            this.ps = new PrintStream(this.os);
            if (this.args != null) {
                this.ps.println(this.args);
            }
            else {
                this.ps.println("");
            }
            s = "Unable to open input stream from server";
            this.is = this.socket.getInputStream();
        }
        catch (Exception ex) {
            throw new Exception(s);
        }
    }
    
    public void close() {
        try {
            this.socket.close();
        }
        catch (Exception ex) {}
        finally {
            this.socket = null;
        }
        try {
            this.ps.close();
        }
        catch (Exception ex2) {}
        finally {
            this.ps = null;
        }
        try {
            this.os.close();
        }
        catch (Exception ex3) {}
        finally {
            this.os = null;
        }
        try {
            this.is.close();
        }
        catch (Exception ex4) {}
        finally {
            this.is = null;
        }
    }
    
    public void debug(final String s) {
        if (!NFDebug.enabled(4L)) {
            return;
        }
        String s2 = this.host + ":" + this.port;
        if (this.args != null) {
            s2 = s2 + "/" + this.args;
        }
        NFDebug.print(4L, "NFParamServer: <" + s2 + ">: " + s);
    }
    
    public void setComp(final Component comp) {
        this.comp = comp;
    }
    
    public void statusMsg(final String s) {
        this.statusMsg(this.comp, s);
    }
    
    public void statusMsg(final Component component, final String s) {
        this.debug(s);
    }
    
    public boolean parse(final String s, final Vector vector) throws Exception {
        if (s == null || s.length() == 0) {
            return false;
        }
        if (s.equals("CLOSE")) {
            return this.closed = true;
        }
        if (!s.equals("STATUS")) {
            return false;
        }
        if (vector.size() == 0) {
            return true;
        }
        this.statusMsg(vector.elementAt(0));
        return true;
    }
}
