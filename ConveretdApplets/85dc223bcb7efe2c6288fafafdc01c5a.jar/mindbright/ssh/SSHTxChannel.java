// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.OutputStream;

public class SSHTxChannel extends SSHChannel
{
    protected OutputStream out;
    protected SSHPduQueue queue;
    boolean closePending;
    
    public SSHTxChannel(final OutputStream out, final int channelId) {
        super(channelId);
        this.out = out;
        this.closePending = false;
        this.queue = new SSHPduQueue();
    }
    
    public SSHPduQueue getQueue() {
        return this.queue;
    }
    
    public void setClosePending() {
        this.closePending = true;
        this.queue.release();
    }
    
    public synchronized boolean isClosePending() {
        return this.closePending;
    }
    
    public void serviceLoop() throws Exception {
        SSH.logExtra("Starting tx-chan: " + this.channelId);
        while (!this.closePending || !this.queue.isEmpty()) {
            final SSHPdu pdu = this.queue.getFirst();
            pdu.writeTo(this.out);
        }
        throw new Exception("CLOSE");
    }
}
