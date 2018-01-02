import java.net.InetAddress;
import java.awt.Event;
import java.io.IOException;
import java.net.SocketException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetCookieImage extends Canvas implements Runnable
{
    IRCQNet theApp;
    private String magicNum;
    private Socket socket;
    private String serverAddress;
    private int serverPort;
    private DataOutputStream socketOut;
    private DataInputStream socketIn;
    private Thread m_Cookie;
    private String Cookie;
    private String adLocation;
    private boolean firsRun;
    
    public IRCQNetCookieImage(final String serverAddress, final int serverPort, final String magicNum) {
        this.serverAddress = "ads.web.aol.com";
        this.serverPort = 80;
        this.Cookie = "";
        this.adLocation = "";
        this.firsRun = true;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.magicNum = magicNum;
    }
    
    private void GetCookie() {
        final String string = "GET /image/" + this.magicNum + "/icq/ HTTP/1.0";
        if (this.Connect()) {
            this.Send(string);
            this.ParaseCookie("Set-Cookie", "Location");
        }
    }
    
    public void ParaseCookie(final String s, final String s2) {
        this.Cookie = null;
        this.adLocation = null;
        String read;
        while ((read = this.Read()) != null) {
            if (read.length() > 0) {
                if (read.indexOf(s) != -1) {
                    this.Cookie = read.substring(read.indexOf(s) + 2 + s.length());
                }
                else {
                    if (read.indexOf(s2) == -1) {
                        continue;
                    }
                    this.adLocation = read.substring(read.indexOf(s2) + 2 + s2.length());
                }
            }
        }
        try {
            this.socket.close();
            this.socketOut.close();
            this.socketIn.close();
        }
        catch (SocketException ex) {}
        catch (IOException ex2) {}
        catch (NullPointerException ex3) {}
        if (this.Cookie != null && this.adLocation != null) {
            int n = 0;
            int i;
            for (i = 0; i < this.adLocation.length(); ++i) {
                if (this.adLocation.charAt(i) == '/') {
                    ++n;
                }
                if (n == 3) {
                    break;
                }
            }
            if (n == 3) {
                this.postEvent(new Event(this, 10010, "Cookie" + this.Cookie + "Location" + this.adLocation.substring(i)));
            }
        }
    }
    
    public void start() {
        (this.m_Cookie = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.m_Cookie != null) {
            this.m_Cookie.stop();
            this.m_Cookie = null;
        }
    }
    
    private boolean Connect() {
        try {
            this.socket = new Socket(InetAddress.getByName(this.serverAddress), this.serverPort);
            this.socketOut = new DataOutputStream(this.socket.getOutputStream());
            this.socketIn = new DataInputStream(this.socket.getInputStream());
        }
        catch (SocketException ex) {
            return false;
        }
        catch (IOException ex2) {
            return false;
        }
        catch (NullPointerException ex3) {
            return false;
        }
        return true;
    }
    
    private void Send(final String s) {
        try {
            this.socketOut.writeBytes(s);
            this.socketOut.writeBytes("\r\n");
            this.socketOut.writeBytes("\r\n");
            this.socketOut.flush();
        }
        catch (IOException ex) {}
        catch (NullPointerException ex2) {}
    }
    
    private String Read() {
        String line;
        try {
            line = this.socketIn.readLine();
        }
        catch (IOException ex) {
            return null;
        }
        catch (NullPointerException ex2) {
            return null;
        }
        return line;
    }
    
    public void run() {
        try {
            while (true) {
                if (this.firsRun) {
                    try {
                        Thread.sleep(40000L);
                    }
                    catch (InterruptedException ex) {
                        return;
                    }
                    this.firsRun = false;
                }
                this.GetCookie();
                try {
                    Thread.sleep(180000L);
                }
                catch (InterruptedException ex2) {}
            }
        }
        catch (ThreadDeath threadDeath) {}
    }
}
