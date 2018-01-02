// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.util;

public abstract class ExceptionMonitor
{
    private static ExceptionMonitor instance;
    
    public static ExceptionMonitor getInstance() {
        return ExceptionMonitor.instance;
    }
    
    public abstract void exceptionCaught(final Throwable p0);
    
    static {
        ExceptionMonitor.instance = new DefaultExceptionMonitor();
    }
}
