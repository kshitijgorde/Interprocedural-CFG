// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.io.InputStream;
import java.io.OutputStream;
import Network.Demultiplexer;
import Network.MessageInput;
import Network.MultiplexInputStream;
import Network.MessageOutput;
import Network.MultiplexOutputStream;
import Network.MessageInputStream;
import Network.MessageOutputStream;
import java.net.Socket;
import java.io.IOException;
import Network.ConnectionNotifier;

public class Connect implements Runnable
{
    MudFrame theframe;
    private static int connect_no;
    public static final boolean debug = false;
    private DisplayInterface display;
    private ConnectionNotifier connect;
    Thread exec;
    String u1;
    String u2;
    String p1;
    String p2;
    
    void stop() {
        synchronized (MudFrame.remotelock) {
            MudFrame.remote = null;
            if (MudFrame.remote != null) {
                try {
                    MudFrame.remote.close();
                    System.out.println("Closing remote socket");
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("No remote socket to close.");
            }
            MudFrame.remote = null;
            if (this.exec != null) {
                this.exec.stop();
            }
        }
        // monitorexit(MudFrame.remotelock)
    }
    
    void doconnect() {
        if (this.exec != null) {
            this.showText("Stopping current logon procedure.");
            System.out.println("We should already have stopped current threads");
        }
        synchronized (MudFrame.remotelock) {
            if (MudFrame.remote != null) {
                try {
                    System.out.println("I have been asked to reconnect, but still connected.");
                    MudFrame.remote.close();
                }
                catch (IOException e) {
                    System.out.println("There, was however an error when I tried to close the rougue connection");
                    e.printStackTrace();
                }
            }
            MudFrame.remote = null;
        }
        // monitorexit(MudFrame.remotelock)
        this.exec = new Thread(this, "Connect no: " + Connect.connect_no++);
        System.out.println("Commencing logon no " + Connect.connect_no + "new connection thread started");
        this.exec.setDaemon(true);
        this.exec.start();
    }
    
    public Connect(final MudFrame frame, final String u1, final String p1, final String u2, final String p2) {
        try {
            this.theframe = frame;
            this.display = this.theframe.defaultDisplay;
            this.u1 = u1;
            this.p1 = p1;
            this.u2 = u2;
            this.p2 = p2;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean dologin(final String u1, final String p1, final String u2, final String p2) {
        return this.theframe.mudbox.login(u1, p1, u2, p2);
    }
    
    static {
        Connect.connect_no = 0;
    }
    
    public void run() {
        try {
            this.showText("Trying to connect...");
            if (MudFrame.remote != null) {
                try {
                    MudFrame.remote.close();
                    MudFrame.remote = null;
                }
                catch (IOException er) {
                    System.err.println("Error trying to close socket.");
                    this.showText("Already connected, couldn't disconnect.");
                    return;
                }
            }
            try {
                synchronized (MudFrame.remotelock) {
                    System.out.println("Host is, " + this.theframe.getHost());
                    MudFrame.remote = new Socket(this.theframe.getHost(), this.theframe.getHostPort());
                    System.out.println("remote socket now open.");
                    if (MudFrame.remote == null) {
                        System.out.println("Remote is null!@!!");
                    }
                    this.showText("Contacted remote computer.");
                }
                // monitorexit(MudFrame.remotelock)
            }
            catch (Exception er2) {
                this.showText("Failed to contact mudhost.\r\nConnection may be resetting, will give it 25 seconds...\n\r");
                try {
                    Thread.sleep(15000L);
                    this.showText("Its amazing how long 25 seconds can feel, 15 gone...\r\n");
                    Thread.sleep(10000L);
                    synchronized (MudFrame.remotelock) {
                        MudFrame.remote = new Socket(this.theframe.getHost(), this.theframe.getHostPort());
                        System.out.println("remote socket now open.");
                        if (MudFrame.remote == null) {
                            System.out.println("Remote is null!@!!");
                        }
                        this.showText("Contacted remote computer.");
                    }
                    // monitorexit(MudFrame.remotelock)
                }
                catch (Exception err) {
                    System.out.println("Failed to open remote host, teminating all");
                    this.theframe.noconnect();
                    this.showText("Still failed to contact mudhost.\r\nMaybe you should try again later.\n\r");
                    return;
                }
            }
            if (!this.setUpStreams()) {
                this.theframe.noconnect();
                return;
            }
            final boolean success = this.dologin(this.u1, this.p1, this.u2, this.p2);
            if (success) {
                System.out.println("<" + Thread.currentThread() + ">" + "Logged in");
                this.theframe.didconnect();
            }
            else {
                System.out.println("<" + Thread.currentThread() + ">" + "Failed to log in.");
                this.theframe.noconnect();
            }
        }
        catch (ThreadDeath td) {
            System.out.println("<" + Thread.currentThread() + ">" + "Connect: I have been killed!");
        }
        finally {
            System.out.println("<" + Thread.currentThread() + ">" + "Connect: Terminated (hopefully gracefully)");
        }
    }
    
    public boolean setUpStreams() {
        try {
            if (MudFrame.remote == null) {
                System.out.println("We need a remote!");
            }
            final OutputStream o = MudFrame.remote.getOutputStream();
            final InputStream i = MudFrame.remote.getInputStream();
            if (o == null) {
                System.out.println("setUpStreams, o was null");
            }
            if (i == null) {
                System.out.println("setUpStreams, i was null");
            }
            this.theframe.mudbox = new MudBox(this.theframe);
            this.theframe.coloursaver = new ColourSaver(this.theframe);
            final MessageOutputStream mO = new MessageOutputStream(o);
            final MessageInputStream mI = new MessageInputStream(i);
            if (mO == null) {
                System.out.println("setUpStreams, mo was null");
            }
            if (mI == null) {
                System.out.println("setUpStreams, mi was null");
            }
            this.theframe.mudbox.setMessageOutput(new MultiplexOutputStream(mO, "mud"));
            this.theframe.coloursaver.setMessageOutput(new MultiplexOutputStream(mO, "com"));
            final Demultiplexer d = new Demultiplexer(new MultiplexInputStream(mI), this.theframe);
            (this.theframe.demultiplex = d).register("mud", this.theframe.mudbox.getMessageOutput());
            d.register("com", this.theframe.coloursaver.getMessageOutput());
            d.start();
            return true;
        }
        catch (IOException e) {
            System.err.println("Error setting up communication streams.");
            this.connect.setConnection(false);
            return false;
        }
    }
    
    public void showText(final String s) {
        this.display.addString(s + "\n");
    }
}
