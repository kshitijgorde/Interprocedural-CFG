// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.log;

public interface ILogSink
{
    void log(final Log p0, final int p1, final Object p2);
    
    void log(final Log p0, final int p1, final Throwable p2);
    
    void log(final Log p0, final int p1, final Object p2, final Throwable p3);
}
