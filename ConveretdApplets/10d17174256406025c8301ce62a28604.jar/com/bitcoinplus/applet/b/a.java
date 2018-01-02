// 
// Decompiled by Procyon v0.5.30
// 

package com.bitcoinplus.applet.b;

public class a
{
    public String a;
    public String b;
    public String c;
    public final int d;
    public final int e;
    public long f;
    public long g;
    public String h;
    public long i;
    
    public a() {
    }
    
    public static String a(final int[] array) {
        final StringBuilder sb = new StringBuilder(array.length << 3);
        for (int i = 0; i < array.length; ++i) {
            sb.append(String.format("%08x", Integer.reverseBytes(array[i])));
        }
        return sb.toString();
    }
    
    public static int[] a(final int[] array, final String s) {
        for (int i = 0; i < s.length() / 8; ++i) {
            array[i] = Integer.reverseBytes((int)Long.parseLong(s.substring(i << 3, i + 1 << 3), 16));
        }
        return array;
    }
    
    public a(final String s) {
        this(a(s, "d"), a(s, "e"), a(s, "f"), Integer.parseInt(a(s, "g")), Integer.parseInt(a(s, "h")), Long.parseLong(a(s, "a")), Long.parseLong(a(s, "b")), a(s, "c"));
    }
    
    public a(final String a, final String b, final String c, final int d, final int e, final long f, final long g, final String h) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = System.currentTimeMillis();
    }
    
    public a(final a a, final int n, final int n2) {
        this(a.a, a.b, a.c, n, n2, a.f, a.g, a.h);
    }
    
    public static String a(final String s, final String s2) {
        return a.a.a.a.a.a(s, "\"" + s2 + "\":\"", "\"");
    }
    
    public String a() {
        return this.b;
    }
    
    public String b() {
        return this.c;
    }
    
    public String c() {
        return this.a.substring(128);
    }
    
    public String d() {
        return this.a.substring(0, 128);
    }
    
    public int e() {
        return this.d;
    }
    
    public int f() {
        return this.e;
    }
    
    public long g() {
        return this.f;
    }
    
    public long h() {
        return this.g;
    }
    
    public String i() {
        return this.h;
    }
    
    public long j() {
        return this.i;
    }
}
