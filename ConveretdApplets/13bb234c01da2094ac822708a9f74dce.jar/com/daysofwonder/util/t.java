// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.util;

import java.util.Properties;

public class t
{
    private static boolean a;
    private static F b;
    private static M c;
    
    public static M a() {
        return t.c;
    }
    
    public static void b() {
        a(new F(1));
    }
    
    public static void a(final F b) {
        t.b = b;
        t.a = true;
    }
    
    public static boolean a(final Properties properties) {
        b();
        return t.b.a(properties);
    }
    
    private static void c() {
        if (!t.a) {
            b();
        }
    }
    
    public static void a(final String s, final String s2) {
        c();
        t.b.a(1, s, s2);
    }
    
    public static void a(final String s) {
        c();
        t.b.a(1, s);
    }
    
    public static void a(final Throwable t) {
        c();
        t.b.a(1, t);
    }
    
    public static void a(final String s, final byte[] array) {
        c();
        a(s, array, 0, array.length);
    }
    
    public static void a(final String s, final byte[] array, final int n, final int n2) {
        c();
        t.b.a(1, array, n, n2);
    }
    
    public static void b(final String s) {
        c();
        t.b.a(2, s);
    }
    
    public static void c(final String s) {
        c();
        t.b.a(3, s);
    }
    
    public static void d(final String s) {
        c();
        t.b.a(4, s);
    }
    
    public static void e(final String s) {
        c();
        t.b.a(5, s);
    }
    
    static {
        t.a = false;
    }
}
