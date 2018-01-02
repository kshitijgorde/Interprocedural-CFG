// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha;

public interface StoreEntry
{
    StoreLocation getKeyDataItem();
    
    StoreLocation getValueDataItem();
    
    long getNextItem();
    
    int getKeyFile();
    
    int getValueFile();
    
    long getValueOffset();
    
    long getOffset();
    
    int getKeySize();
    
    int getValueSize();
}
