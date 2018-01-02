// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.log;

public final class ConsoleSink implements ILogSink
{
    @Override
    public void log(final Log log, final int n, final Object o) {
        System.out.println(o);
    }
    
    @Override
    public void log(final Log log, final int n, final Throwable t) {
        System.out.println(t.toString());
    }
    
    @Override
    public void log(final Log log, final int n, final Object o, final Throwable t) {
        System.out.println(o);
        System.out.println(t.toString());
    }
}
