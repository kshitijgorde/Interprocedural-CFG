// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.c;

import java.util.Hashtable;

public final class k
{
    private static int a;
    private static int b;
    private static Hashtable c;
    private static Hashtable d;
    private static Hashtable e;
    
    public static void a() {
        k.a = 0;
        k.b = 0;
        k.c = new Hashtable();
        k.d = new Hashtable();
        k.e = new Hashtable();
    }
    
    public static Thread a(Runnable runnable, String b) {
        final Runnable runnable2 = runnable;
        b = b;
        runnable = runnable2;
        ++k.a;
        final a a;
        (a = new a()).b = b;
        a.a = k.b++;
        h.e("ThreadManager - creating thread [" + a.a + "] => [" + a.b + "]");
        final Thread thread = new Thread(runnable);
        k.c.put(thread, runnable);
        k.e.put(runnable, thread);
        k.d.put(thread, a);
        final Thread thread2 = thread;
        final a a2 = k.d.get(thread2);
        h.e("ThreadManager - starting thread [" + a2.a + "] => [" + a2.b + "]");
        thread2.start();
        return thread2;
    }
    
    public static void a(final Runnable runnable) {
        final Thread thread;
        if ((thread = k.e.get(runnable)) == null) {
            h.e("ThreadManager - requested to kill null thread.  Ignoring.");
            return;
        }
        final a a = k.d.get(thread);
        h.e("ThreadManager - killThread interrupting thread [" + a.a + "] => [" + a.b + "]");
        thread.interrupt();
        if (thread == null) {
            h.e("ThreadManager - requested to stop null thread.  Ignoring.");
        }
        h.e("ThreadManager - stopThread cleanup after thread [" + a.a + "] => [" + a.b + "]");
    }
    
    public static void b(final Runnable runnable) {
        final Thread thread = k.e.get(runnable);
        final a a = k.d.get(thread);
        h.e("ThreadManager - Removing thread [" + a.a + "] => [" + a.b + "]");
        final Runnable runnable2 = k.c.get(thread);
        k.c.remove(thread);
        k.e.remove(runnable2);
        k.d.remove(thread);
        --k.a;
    }
    
    public static boolean c(final Runnable runnable) {
        final Thread thread = k.e.get(runnable);
        h.e("ThreadManager - Checking Thread is Alive()");
        if (thread == null) {
            return false;
        }
        final a a = k.d.get(thread);
        final boolean alive = thread.isAlive();
        h.e("ThreadManager - [" + a.a + "] => [" + a.b + "] isAlive? = [" + alive + "]");
        return alive;
    }
}
