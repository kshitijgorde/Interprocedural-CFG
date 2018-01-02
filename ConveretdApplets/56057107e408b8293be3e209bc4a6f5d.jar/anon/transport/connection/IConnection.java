// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection;

import java.io.IOException;
import anon.transport.address.IAddress;

public interface IConnection
{
    public static final int ConnectionState_OPEN = 1;
    public static final int ConnectionState_CLOSE = 2;
    
    void setTimeout(final int p0) throws ConnectionException;
    
    int getTimeout() throws ConnectionException;
    
    IAddress getLocalAddress();
    
    IAddress getRemoteAddress();
    
    int getCurrentState();
    
    void close() throws IOException;
}
