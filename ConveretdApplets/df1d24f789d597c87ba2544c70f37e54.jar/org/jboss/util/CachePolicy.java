// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

public interface CachePolicy
{
    Object get(final Object p0);
    
    Object peek(final Object p0);
    
    void insert(final Object p0, final Object p1);
    
    void remove(final Object p0);
    
    void flush();
    
    int size();
    
    void create() throws Exception;
    
    void start() throws Exception;
    
    void stop();
    
    void destroy();
}
