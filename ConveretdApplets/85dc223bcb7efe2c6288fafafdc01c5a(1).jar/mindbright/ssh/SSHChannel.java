// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

public abstract class SSHChannel extends Thread
{
    protected int channelId;
    protected SSHChannelListener listener;
    
    public SSHChannel(final int channelId) {
        this.channelId = channelId;
        this.listener = null;
    }
    
    public void setSSHChannelListener(final SSHChannelListener listener) {
        this.listener = listener;
    }
    
    public int getId() {
        return this.channelId;
    }
    
    public abstract void serviceLoop() throws Exception;
    
    public void close() {
    }
    
    public void run() {
        try {
            this.serviceLoop();
        }
        catch (Exception e) {
            if (SSH.DEBUGMORE) {
                System.out.println("--- channel exit (exception is not an error):");
                e.printStackTrace();
                System.out.println("---");
            }
            this.close();
            if (this.listener != null) {
                this.listener.close(this);
            }
        }
        catch (ThreadDeath death) {
            SSH.logExtra("Channel killed " + this.channelId);
            throw death;
        }
    }
}
