import java.io.IOException;
import java.net.SocketException;
import java.awt.Event;
import java.awt.Component;
import java.net.InetAddress;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class IRCQNetTCPClient extends Panel implements Runnable
{
    private Socket socket;
    private String stringToSend;
    private IRCQNetTCPListenThread Listen;
    private Thread ListenThread;
    private IRCQNet theApp;
    private IRCQNetKeepAlive keepAlive;
    public static DataOutputStream socketOut;
    
    public IRCQNetTCPClient(final IRCQNet theApp) {
        this.theApp = theApp;
    }
    
    public void run() {
        this.Connect();
    }
    
    public boolean Connect() {
        try {
            this.socket = new Socket(InetAddress.getByName(this.getParams().server), this.getParams().serverPort());
            IRCQNetTCPClient.socketOut = new DataOutputStream(this.socket.getOutputStream());
            this.theApp.MPanel.getParams().Connected = true;
            this.Listen = new IRCQNetTCPListenThread(this.socket, this.theApp);
            (this.ListenThread = new Thread(this.Listen)).setPriority(1);
            this.ListenThread.start();
            this.add(this.Listen);
            (this.keepAlive = new IRCQNetKeepAlive(this)).setPriority(1);
            this.postEvent(new Event(this, 10000, null));
        }
        catch (SocketException ex) {
            this.postEvent(new Event(this, 10006, null));
            return false;
        }
        catch (IOException ex2) {}
        catch (NullPointerException ex3) {}
        return true;
    }
    
    public boolean Connected() {
        this.keepAlive.start();
        return true;
    }
    
    public void start() {
        if (this.Listen != null) {
            this.ListenThread.resume();
        }
        if (this.keepAlive != null) {
            this.keepAlive.resume();
        }
    }
    
    public void stop() {
        if (this.ListenThread != null && this.ListenThread.isAlive()) {
            this.ListenThread.suspend();
        }
        if (this.keepAlive != null && this.keepAlive.isAlive()) {
            this.keepAlive.suspend();
        }
    }
    
    public void Send(final String s) {
        try {
            IRCQNetTCPClient.socketOut.writeBytes(s);
            IRCQNetTCPClient.socketOut.writeBytes("\n");
            IRCQNetTCPClient.socketOut.flush();
        }
        catch (IOException ex) {}
        catch (NullPointerException ex2) {}
    }
    
    public void cleanUp() {
        try {
            this.theApp.MPanel.getParams().Connected = false;
            this.ListenThread.stop();
            this.keepAlive.stop();
            Thread.currentThread();
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {}
        catch (NullPointerException ex2) {}
        finally {
            try {
                IRCQNetTCPClient.socketOut.close();
            }
            catch (IOException ex3) {}
            catch (NullPointerException ex4) {}
        }
    }
    
    public void Disconnect() {
        try {
            this.theApp.MPanel.getParams().Connected = false;
            this.keepAlive.stop();
            this.ListenThread.stop();
            Thread.currentThread();
            Thread.sleep(500L);
            this.theApp.MPanel.Disconnected(false);
        }
        catch (InterruptedException ex) {}
        catch (NullPointerException ex2) {}
        finally {
            try {
                IRCQNetTCPClient.socketOut.close();
            }
            catch (IOException ex3) {}
            catch (NullPointerException ex4) {}
        }
    }
    
    public IRCQNetParam getParams() {
        try {
            return this.theApp.MPanel.getParams();
        }
        catch (NullPointerException ex) {
            return null;
        }
    }
}
