// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

import java.util.Enumeration;
import java.awt.FontMetrics;
import java.awt.Image;
import java.util.Hashtable;

public class b5 extends DigiItem
{
    private static Hashtable a;
    public Image b;
    public String c;
    
    public static boolean a(final String s, final int n) {
        return n < 0 || n >= s.length() || Character.isSpace(s.charAt(n));
    }
    
    public final Object h(final String s) {
        if ("image".equals(s)) {
            return this.b;
        }
        return super.h(s);
    }
    
    public final String i(final String s) {
        return null;
    }
    
    public static int a(final String s, final FontMetrics fontMetrics) {
        final Enumeration a = a();
        int n = 0;
        while (a.hasMoreElements()) {
            final b5 b5 = a.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(b5.getName(), 0); i != -1; i = s.indexOf(b5.getName(), i + 1)) {
                if (a(s, i - 1) && a(s, i + b5.getName().length())) {
                    ++n2;
                }
            }
            n += n2 * fontMetrics.stringWidth(b5.getName());
        }
        return n;
    }
    
    public static int a(final String s) {
        final Enumeration a = a();
        int n = 0;
        while (a.hasMoreElements()) {
            final b5 b5 = a.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(b5.getName(), 0); i != -1; i = s.indexOf(b5.getName(), i + 1)) {
                if (a(s, i - 1) && a(s, i + b5.getName().length())) {
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
            final b5 b5 = a.nextElement();
            int n2 = s.indexOf(b5.getName(), n);
            while (n2 != -1 && n2 < length) {
                if (a(s, n2 - 1) && a(s, n2 + b5.getName().length())) {
                    length = n2;
                    n2 = -1;
                }
                else {
                    n2 = s.indexOf(b5.getName(), n2 + 1);
                }
            }
        }
        return (length == s.length()) ? -1 : length;
    }
    
    public static synchronized void a(final b5 b5) {
        final String name = b5.getName();
        if (b5.u(63)) {
            b5.a.remove(name);
        }
        else if (name != null && b5.c != null) {
            b5.a.put(name, b5);
        }
    }
    
    public static synchronized b5 b(final String s) {
        return b5.a.get(s);
    }
    
    public static synchronized Enumeration a() {
        return b5.a.elements();
    }
    
    public b5(final int n, final String s) {
        super(n, s);
    }
    
    static {
        b5.a = new Hashtable();
    }
}
