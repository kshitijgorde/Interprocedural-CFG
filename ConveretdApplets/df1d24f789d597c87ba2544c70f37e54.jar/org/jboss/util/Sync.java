// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

public interface Sync
{
    void acquire() throws InterruptedException;
    
    void release();
}
