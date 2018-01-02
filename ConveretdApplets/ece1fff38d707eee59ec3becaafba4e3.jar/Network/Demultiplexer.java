// 
// Decompiled by Procyon v0.5.30
// 

package Network;

import java.util.Enumeration;
import java.io.IOException;
import MudFE.MudFrame;
import java.util.Hashtable;

public class Demultiplexer extends Thread
{
    protected MultiplexInputStream i;
    protected Hashtable routes;
    private static int plexerNumber;
    ConnectionNotifier cn;
    boolean connected;
    private MudFrame theframe;
    
    public Demultiplexer(final MultiplexInputStream i, final MudFrame tf) {
        super("Demultiplexer-" + nextPlexerNum());
        this.connected = true;
        this.theframe = tf;
        this.i = i;
        this.routes = new Hashtable();
    }
    
    public void start() {
        System.out.println("Demultiplexer has just started.");
        super.start();
    }
    
    public void deregister(final String label) {
        this.routes.remove(label);
    }
    
    public void register(final String label, final MessageOutput o) {
        this.routes.put(label, o);
    }
    
    public void setConnectionNotifier(final ConnectionNotifier cn) {
        this.cn = cn;
    }
    
    public void run() {
        try {
            while (this.connected) {
                this.i.receive();
                final MessageOutput o = this.routes.get(this.i.label);
                if (o != null) {
                    final byte[] message = new byte[this.i.available()];
                    this.i.readFully(message);
                    synchronized (o) {
                        o.write(message);
                        o.send();
                    }
                    // monitorexit(o)
                }
            }
        }
        catch (Exception ex) {
            this.theframe.noconnect();
            final Enumeration en = this.routes.elements();
            try {
                while (en.hasMoreElements()) {
                    en.nextElement().close();
                }
            }
            catch (IOException ex2) {}
        }
        catch (ThreadDeath td) {
            System.err.println("<" + Thread.currentThread() + ">" + "Demultiplexer has been killed.");
        }
    }
    
    private static synchronized int nextPlexerNum() {
        return Demultiplexer.plexerNumber++;
    }
}
