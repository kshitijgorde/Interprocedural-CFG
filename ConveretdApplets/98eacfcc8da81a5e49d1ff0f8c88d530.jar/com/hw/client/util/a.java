// 
// Decompiled by Procyon v0.5.30
// 

package com.hw.client.util;

import java.applet.Applet;
import java.util.Date;

public final class a
{
    private static String[] a;
    private static final g b;
    private static int c;
    private static boolean d;
    
    public static final void a(final String s) {
        a(0, s);
    }
    
    public static final void b(final String s) {
        a(1, s);
    }
    
    public static final void c(final String s) {
        a(2, s);
    }
    
    public static final void d(final String s) {
        a(3, s);
    }
    
    public static final void a(final String s, final Exception ex) {
        a(3, s, ex);
    }
    
    public static final void e(final String s) {
        a(4, s);
    }
    
    public static final void b(final String s, final Exception ex) {
        a(4, s, ex);
    }
    
    public static final void c(final String s, final Exception ex) {
        a(5, s, ex);
    }
    
    private static synchronized void a(final int n, final String s) {
        a(n, s, null);
    }
    
    private static synchronized void a(final int n, final String s, final Exception ex) {
        if (com.hw.client.util.a.c <= n) {
            final StringBuffer sb = new StringBuffer();
            if (com.hw.client.util.a.d) {
                sb.append(new Date().toString()).append(" - ");
            }
            sb.append("[").append(com.hw.client.util.a.a[n]).append("] ");
            sb.append(s);
            System.err.println(sb.toString());
            if (ex != null) {
                ex.printStackTrace();
            }
            com.hw.client.util.a.b.a(new h(com.hw.client.util.a.b, n, sb.toString(), ex), true);
        }
    }
    
    public static final void a(final Applet applet, final int c) {
        String parameter;
        if (applet == null) {
            parameter = null;
        }
        else {
            parameter = applet.getParameter("loglevel");
        }
        if (parameter != null) {
            if (parameter.equalsIgnoreCase("trace")) {
                com.hw.client.util.a.c = 0;
                return;
            }
            if (parameter.equalsIgnoreCase("debug")) {
                com.hw.client.util.a.c = 1;
                return;
            }
            if (parameter.equalsIgnoreCase("info")) {
                com.hw.client.util.a.c = 2;
                return;
            }
            if (parameter.equalsIgnoreCase("warn")) {
                com.hw.client.util.a.c = 3;
                return;
            }
            if (parameter.equalsIgnoreCase("error")) {
                com.hw.client.util.a.c = 4;
                return;
            }
            if (parameter.equalsIgnoreCase("fatal")) {
                com.hw.client.util.a.c = 5;
                return;
            }
        }
        com.hw.client.util.a.c = c;
    }
    
    public static synchronized void a(final d d) {
        com.hw.client.util.a.b.a(d);
    }
    
    public static synchronized void b(final d d) {
        com.hw.client.util.a.b.b(d);
    }
    
    public static final boolean a() {
        return com.hw.client.util.a.c <= 1;
    }
    
    public static final boolean b() {
        return com.hw.client.util.a.c <= 2;
    }
    
    public static final boolean c() {
        return com.hw.client.util.a.c <= 0;
    }
    
    public static void a(final boolean b) {
        com.hw.client.util.a.d = true;
    }
    
    static {
        com.hw.client.util.a.a = new String[] { "trace", "debug", "info", "warn", "error", "fatal" };
        b = new g();
        com.hw.client.util.a.c = 2;
        com.hw.client.util.a.d = false;
    }
}
