// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection;

public interface IChunkConnection extends IConnection
{
    IChunkReader getChunkReader();
    
    IChunkWriter getChunkWriter();
}
