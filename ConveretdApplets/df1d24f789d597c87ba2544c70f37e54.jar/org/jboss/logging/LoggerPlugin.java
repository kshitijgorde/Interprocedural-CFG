// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.logging;

public interface LoggerPlugin
{
    void init(final String p0);
    
    boolean isTraceEnabled();
    
    void trace(final Object p0);
    
    void trace(final Object p0, final Throwable p1);
    
    boolean isDebugEnabled();
    
    void debug(final Object p0);
    
    void debug(final Object p0, final Throwable p1);
    
    boolean isInfoEnabled();
    
    void info(final Object p0);
    
    void info(final Object p0, final Throwable p1);
    
    void warn(final Object p0);
    
    void warn(final Object p0, final Throwable p1);
    
    void error(final Object p0);
    
    void error(final Object p0, final Throwable p1);
    
    void fatal(final Object p0);
    
    void fatal(final Object p0, final Throwable p1);
}
