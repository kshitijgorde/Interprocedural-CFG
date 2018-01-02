// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.management;

public interface ClassCacheMBean
{
    boolean isFull();
    
    int getClassLoadCount();
    
    int getLiveClassCount();
    
    int getClassReuseCount();
    
    void flush();
}
