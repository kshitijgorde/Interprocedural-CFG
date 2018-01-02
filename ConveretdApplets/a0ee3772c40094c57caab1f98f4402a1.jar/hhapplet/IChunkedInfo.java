// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

public interface IChunkedInfo
{
    IChunkedData getChunkByIdx(final int p0);
    
    IChunkedData getChunkedData(final boolean p0, final String p1);
    
    int getTotalNum();
}
