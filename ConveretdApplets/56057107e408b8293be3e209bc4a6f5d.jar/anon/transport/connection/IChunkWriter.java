// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection;

import java.io.IOException;

public interface IChunkWriter
{
    void writeChunk(final byte[] p0) throws ConnectionException;
    
    void close() throws IOException;
}
