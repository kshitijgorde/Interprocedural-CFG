// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha;

public interface StoreLocation
{
    int getSize();
    
    long getOffset();
    
    int getFile();
}
