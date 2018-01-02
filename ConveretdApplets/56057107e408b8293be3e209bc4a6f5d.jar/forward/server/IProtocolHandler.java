// 
// Decompiled by Procyon v0.5.30
// 

package forward.server;

public interface IProtocolHandler
{
    int available() throws Exception;
    
    int read(final byte[] p0) throws Exception;
    
    void write(final byte[] p0) throws Exception;
    
    void close();
}
