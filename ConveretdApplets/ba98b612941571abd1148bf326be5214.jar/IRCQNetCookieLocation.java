import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.net.SocketException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetCookieLocation extends Canvas implements Runnable
{
    IRCQNet theApp;
    private String magicNum;
    private Socket socket;
    private String serverAddress;
    private DataOutputStream socketOut;
    private DataInputStream socketIn;
    private Thread m_Cookie;
    private String Cookie;
    private String adLinkLocation;
    
    public IRCQNetCookieLocation(final IRCQNet theApp, final String magicNum, final String cookie) {
        this.serverAddress = "ads.web.aol.com";
        this.Cookie = "";
        this.adLinkLocation = "";
        this.theApp = theApp;
        this.magicNum = magicNum;
        this.Cookie = cookie;
        this.serverAddress = theApp.MPanel.getParams().adsServer;
    }
    
    private void GetCookie() {
        final String string = "GET /link/" + this.magicNum + "/icq HTTP/1.0";
        if (this.Connect()) {
            this.Send(string);
            this.Send("Cookie: " + this.Cookie + "\r\n");
            this.ParaseCookie("Location");
        }
    }
    
    public void ParaseCookie(final String s) {
        String s2 = this.Read();
        if (s2 == null) {
            return;
        }
        while (s2.length() > 0) {
            final int index;
            if ((index = s2.indexOf(s)) != -1 && s2.length() > index + 2 + s.length() && index >= 0) {
                this.adLinkLocation = s2.substring(index + 2 + s.length());
            }
            s2 = this.Read();
        }
        try {
            this.socket.close();
            this.socketOut.close();
            this.socketIn.close();
        }
        catch (SocketException ex) {}
        catch (IOException ex2) {}
        catch (NullPointerException ex3) {}
        if (this.adLinkLocation != null && this.adLinkLocation.length() > 0) {
            try {
                this.theApp.getAppletContext().showDocument(new URL(this.adLinkLocation), "_blank");
            }
            catch (MalformedURLException ex4) {}
            this.stop();
        }
    }
    
    public void start() {
        if (this.m_Cookie == null) {
            (this.m_Cookie = new Thread(this)).setPriority(1);
            this.m_Cookie.start();
        }
    }
    
    public void stop() {
        if (this.m_Cookie != null) {
            this.m_Cookie.stop();
            this.m_Cookie = null;
        }
    }
    
    private boolean Connect() {
        try {
            this.socket = new Socket(InetAddress.getByName(this.serverAddress), this.theApp.MPanel.getParams().getAdsPort());
            this.socketOut = new DataOutputStream(this.socket.getOutputStream());
            this.socketIn = new DataInputStream(this.socket.getInputStream());
        }
        catch (SocketException ex) {
            return false;
        }
        catch (IOException ex2) {}
        catch (NullPointerException ex3) {}
        return true;
    }
    
    private void Send(final String s) {
        try {
            this.socketOut.writeBytes(s);
            this.socketOut.writeBytes("\r\n");
            this.socketOut.flush();
        }
        catch (IOException ex) {}
        catch (NullPointerException ex2) {}
    }
    
    private String Read() {
        String line;
        try {
            if ((line = this.socketIn.readLine()) == null) {
                return "";
            }
        }
        catch (IOException ex) {
            return "";
        }
        catch (NullPointerException ex2) {
            return "";
        }
        return line;
    }
    
    public void run() {
        try {
            while (true) {
                this.GetCookie();
                try {
                    Thread.sleep(6000L);
                }
                catch (InterruptedException ex) {}
            }
        }
        catch (ThreadDeath threadDeath) {}
    }
}
