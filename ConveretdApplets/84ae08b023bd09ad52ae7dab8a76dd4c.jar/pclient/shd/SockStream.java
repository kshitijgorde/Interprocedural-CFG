// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import java.io.IOException;

public interface SockStream
{
    void setInit(final Config p0, final SessionEnclosure p1);
    
    void connect(final String p0, final int p1) throws IOException;
    
    long getConnTime();
    
    void send(final byte[] p0) throws IOException;
    
    void disconnect();
    
    boolean isConnected();
    
    boolean isTunnel();
    
    void setListener(final NetStreamListener p0);
    
    Object secSession();
}
