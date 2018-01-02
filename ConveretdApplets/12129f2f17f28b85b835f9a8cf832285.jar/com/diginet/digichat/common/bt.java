// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

import java.util.Enumeration;
import java.awt.FontMetrics;
import java.awt.Image;
import java.util.Hashtable;

public class bt extends k
{
    private static Hashtable a;
    public Image b;
    public String c;
    
    public static boolean a(final String s, final int n) {
        return n < 0 || n >= s.length() || Character.isSpace(s.charAt(n));
    }
    
    public Object e(final String s) {
        if ("image".equals(s)) {
            return this.b;
        }
        return super.e(s);
    }
    
    public String f(final String s) {
        return null;
    }
    
    public static int a(final String s, final FontMetrics fontMetrics) {
        final Enumeration a = a();
        int n = 0;
        while (a.hasMoreElements()) {
            final bt bt = a.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(bt.r(), 0); i != -1; i = s.indexOf(bt.r(), i + 1)) {
                if (a(s, i - 1) && a(s, i + bt.r().length())) {
                    ++n2;
                }
            }
            n += n2 * fontMetrics.stringWidth(bt.r());
        }
        return n;
    }
    
    public static int a(final String s) {
        final Enumeration a = a();
        int n = 0;
        while (a.hasMoreElements()) {
            final bt bt = a.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(bt.r(), 0); i != -1; i = s.indexOf(bt.r(), i + 1)) {
                if (a(s, i - 1) && a(s, i + bt.r().length())) {
                    ++n2;
                }
            }
            n += n2;
        }
        return n;
    }
    
    public static int b(final String s, final int n) {
        final Enumeration a = a();
        int length = s.length();
        while (a.hasMoreElements()) {
            final bt bt = a.nextElement();
            int n2 = s.indexOf(bt.r(), n);
            while (n2 != -1 && n2 < length) {
                if (a(s, n2 - 1) && a(s, n2 + bt.r().length())) {
                    length = n2;
                    n2 = -1;
                }
                else {
                    n2 = s.indexOf(bt.r(), n2 + 1);
                }
            }
        }
        return (length == s.length()) ? -1 : length;
    }
    
    public static synchronized void a(final bt bt) {
        final String r = bt.r();
        if (bt.i(63)) {
            bt.a.remove(r);
        }
        else if (r != null && bt.c != null) {
            bt.a.put(r, bt);
        }
    }
    
    public static synchronized bt b(final String s) {
        return bt.a.get(s);
    }
    
    public static synchronized Enumeration a() {
        return bt.a.elements();
    }
    
    public bt(final int n, final String s) {
        super(n, s);
    }
    
    static {
        bt.a = new Hashtable();
    }
}
