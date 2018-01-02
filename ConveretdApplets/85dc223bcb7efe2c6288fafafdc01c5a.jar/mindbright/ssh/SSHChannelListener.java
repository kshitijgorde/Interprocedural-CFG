// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.IOException;

public interface SSHChannelListener
{
    SSHPdu prepare(final SSHPdu p0) throws IOException;
    
    void transmit(final SSHPdu p0);
    
    void receive(final SSHPdu p0);
    
    void close(final SSHChannel p0);
}
