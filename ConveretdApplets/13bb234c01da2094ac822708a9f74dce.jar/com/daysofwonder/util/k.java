// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

public class k extends ThreadGroup
{
    protected static ThreadGroup a;
    private static q b;
    
    public k() {
        super(Thread.currentThread().getThreadGroup(), "DebugThreadGroup");
    }
    
    public void uncaughtException(final Thread thread, final Throwable t) {
        t.a(thread.getName() + " generated an exception: ");
        t.a(t);
        if (k.b != null && !(t instanceof ThreadDeath)) {
            k.b.a(thread, t);
        }
    }
    
    public static void a(final ThreadGroup a) {
        k.a = a;
    }
    
    public static void a(final q b) {
        k.b = b;
    }
    
    public static ThreadGroup a() {
        return k.a;
    }
    
    static {
        k.a = null;
        k.b = null;
    }
}
