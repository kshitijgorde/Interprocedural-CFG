// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import com.daysofwonder.util.UIProperties;
import com.daysofwonder.b.a;
import com.daysofwonder.b.b;
import java.awt.Image;
import java.util.Hashtable;

public class y
{
    private static K b;
    public static boolean a;
    
    public static void a(final K b) {
        y.b = b;
        y.a = y.b.d();
    }
    
    public static Hashtable a(final Class clazz, final String s, final aF af) {
        return y.b.a(clazz, s, af);
    }
    
    public static void a() {
        y.b.e();
    }
    
    public static void a(final String s, final Throwable t) {
        y.b.a(s, t);
    }
    
    public static Image a(final int n, final int n2) {
        return y.b.createImage(n, n2);
    }
    
    public static Image a(final byte[] array) {
        return y.b.a(array);
    }
    
    public static b a(final String s) {
        return y.b.a(s);
    }
    
    public static Image b(final String s) {
        return y.b.b(s);
    }
    
    public static boolean b() {
        return y.b.f();
    }
    
    public static String c() {
        return y.b.g();
    }
    
    public static String d() {
        return y.b.h();
    }
    
    public static b a(final b b) {
        return y.b.a(b);
    }
    
    public static b b(final b b) {
        return y.b.b(b);
    }
    
    public static z a(final a a, final ap ap) {
        return y.b.a(a, ap);
    }
    
    public static UIProperties c(final String s) {
        return y.b.c(s);
    }
    
    public static String e() {
        return y.b.i();
    }
    
    public static aE a(final Object o, final ap ap, final int n, final int n2) {
        return y.b.a(o, ap, n, n2);
    }
}
