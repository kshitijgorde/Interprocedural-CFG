// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import Network.ConnectionChangeEvent;
import java.io.IOException;
import Network.QueueOutputStream;
import Network.QueueInputStream;
import Network.MessageOutput;
import Network.Queue;
import Network.ConnectionChangeListener;

public abstract class CommHandler implements Runnable, ConnectionChangeListener
{
    Thread exec;
    protected static int thread_no;
    protected Queue q;
    protected MessageOutput o;
    protected boolean connected;
    protected MudFrame theframe;
    protected QueueInputStream qI;
    protected QueueOutputStream qO;
    protected String th;
    byte[] inbuffer;
    
    public void stop() {
        if (this.exec != null) {
            this.exec.stop();
        }
    }
    
    public void setMessageOutput(final MessageOutput o) {
        if (this.o != null) {
            try {
                this.o.close();
            }
            catch (IOException e) {
                System.err.println("Error closing old message output stream");
                e.printStackTrace();
            }
        }
        this.o = o;
    }
    
    public void connectionChanged(final ConnectionChangeEvent evt) {
        this.connected = evt.getConnected();
    }
    
    public MessageOutput getMessageOutput() {
        if (this.qO == null) {
            this.qO = new QueueOutputStream(this.q);
        }
        return new QueueOutputStream(this.q);
    }
    
    abstract void received(final String p0);
    
    public CommHandler(final MudFrame f, final String th) {
        this.connected = true;
        this.q = new Queue();
        this.th = th;
        this.exec = new Thread(this, "Communication Thread: " + th + ": " + CommHandler.thread_no++);
        this.theframe = f;
        this.qI = new QueueInputStream(this.q);
    }
    
    CommHandler() {
        this.connected = true;
        this.q = new Queue();
        this.qI = new QueueInputStream(this.q);
    }
    
    public void go() {
        if (this.exec == null) {
            System.err.println("Commhandler attempting to go on null exec");
        }
        else if (!this.exec.isAlive()) {
            this.exec.start();
        }
    }
    
    String myreadUTF() throws IOException {
        this.inbuffer = new byte[this.qI.available()];
        final int count = this.qI.read(this.inbuffer);
        return new String(this.inbuffer, 0);
    }
    
    static {
        CommHandler.thread_no = 0;
    }
    
    public void run() {
        try {
            while (true) {
                this.qI.receive();
                this.inbuffer = new byte[this.qI.available()];
                final int count = this.qI.read(this.inbuffer);
                if (count > 0) {
                    final String msg = new String(this.inbuffer, 0);
                    this.received(msg);
                }
                else {
                    System.out.println("Count was " + count + " so ignoring message");
                }
            }
        }
        catch (IOException e) {}
        catch (ThreadDeath td) {}
        finally {
            System.out.println("<" + Thread.currentThread() + ">" + "Commthread " + this.th + ": " + CommHandler.thread_no + " has terminated");
        }
    }
}
