// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import XMLConsumer.IEntry;

public interface INumChunkedData extends IChunkedData
{
    int getKeyPosition(final boolean p0, final String p1);
    
    String getKeyByPosition(final int p0);
    
    int getNum();
    
    IEntry getEntry(final int p0);
    
    String getLast();
    
    String getFirst();
    
    UsedItems getUsedItems();
    
    int getLastIndex();
    
    void setDone(final boolean p0);
}
