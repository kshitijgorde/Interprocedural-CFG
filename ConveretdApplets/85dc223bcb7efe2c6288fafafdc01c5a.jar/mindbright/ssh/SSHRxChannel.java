// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.IOException;
import java.io.InputStream;

public class SSHRxChannel extends SSHChannel
{
    protected InputStream in;
    protected SSHPdu pduFactory;
    
    public SSHRxChannel(final InputStream in, final int channelId) {
        super(channelId);
        this.in = in;
    }
    
    public void setSSHPduFactory(final SSHPdu pduFactory) {
        this.pduFactory = pduFactory;
    }
    
    public void serviceLoop() throws Exception {
        SSH.logExtra("Starting rx-chan: " + this.channelId);
        while (true) {
            SSHPdu pdu = this.pduFactory.createPdu();
            pdu = this.listener.prepare(pdu);
            pdu.readFrom(this.in);
            this.listener.receive(pdu);
        }
    }
    
    public void forceClose() {
        try {
            this.in.close();
        }
        catch (IOException ex) {}
    }
}
