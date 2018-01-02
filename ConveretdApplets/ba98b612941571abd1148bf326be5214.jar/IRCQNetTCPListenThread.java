import java.io.IOException;
import java.awt.Event;
import java.net.Socket;
import java.io.DataInputStream;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class IRCQNetTCPListenThread extends Panel implements Runnable
{
    private DataInputStream socketIn;
    private Socket socket;
    private IRCQNet theApp;
    
    public IRCQNetTCPListenThread(final Socket socket, final IRCQNet theApp) {
        this.theApp = theApp;
        this.socket = socket;
        try {
            this.socketIn = new DataInputStream(socket.getInputStream());
        }
        catch (IOException ex) {
            try {
                this.postEvent(new Event(this, 10005, "Disconnect"));
            }
            catch (NullPointerException ex2) {}
        }
    }
    
    public void run() {
        try {
            while (true) {
                final String line = this.socketIn.readLine();
                if (line == null) {
                    break;
                }
                this.postEvent(new Event(this, 10007, line));
            }
            try {
                this.postEvent(new Event(this, 10005, "Disconnect"));
            }
            catch (NullPointerException ex) {}
        }
        catch (IOException ex2) {}
        catch (NullPointerException ex3) {}
        catch (ThreadDeath threadDeath) {}
        finally {
            try {
                this.socketIn.close();
                this.socket.close();
            }
            catch (IOException ex4) {}
        }
    }
}
