// 
// Decompiled by Procyon v0.5.30
// 

package org.slf4j.impl;

import org.slf4j.helpers.MarkerIgnoringBase;

public class SimpleLogger extends MarkerIgnoringBase
{
    private static long c;
    public static final String b;
    private static String d;
    private static String e;
    private static String f;
    
    SimpleLogger(final String a) {
        this.a = a;
    }
    
    public void a(final String s) {
    }
    
    private void a(final String s, final String s2, final Throwable t) {
        final StringBuffer sb = new StringBuffer();
        sb.append(System.currentTimeMillis() - SimpleLogger.c);
        sb.append(" [");
        sb.append(Thread.currentThread().getName());
        sb.append("] ");
        sb.append(s);
        sb.append(" ");
        sb.append(this.a);
        sb.append(" - ");
        sb.append(s2);
        sb.append(SimpleLogger.b);
        System.err.print(sb.toString());
        if (t != null) {
            t.printStackTrace(System.err);
        }
        System.err.flush();
    }
    
    public void b(final String s) {
        this.a(SimpleLogger.d, s, null);
    }
    
    public void c(final String s) {
        this.a(SimpleLogger.e, s, null);
    }
    
    public void d(final String s) {
        this.a(SimpleLogger.f, s, null);
    }
    
    public void a(final String s, final Throwable t) {
        this.a(SimpleLogger.f, s, t);
    }
    
    static {
        SimpleLogger.c = System.currentTimeMillis();
        b = System.getProperty("line.separator");
        SimpleLogger.d = "INFO";
        SimpleLogger.e = "WARN";
        SimpleLogger.f = "ERROR";
    }
}
