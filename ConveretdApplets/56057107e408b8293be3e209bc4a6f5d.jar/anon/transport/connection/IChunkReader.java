// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection;

import java.io.IOException;

public interface IChunkReader
{
    byte[] readChunk() throws ConnectionException;
    
    int availableChunks() throws ConnectionException;
    
    void close() throws IOException;
}
