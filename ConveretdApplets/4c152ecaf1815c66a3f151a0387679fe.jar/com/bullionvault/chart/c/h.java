// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.c;

import java.util.Date;
import java.text.SimpleDateFormat;

public final class h
{
    public static int a;
    private static boolean b;
    private static boolean c;
    private static String[] d;
    
    public static void a(final int a) {
        a(0, "Setting log.level to " + a + " (was: " + h.a + ")");
        h.a = a;
    }
    
    private static void a(final int n, final String s) {
        if (n <= h.a) {
            String s2 = "";
            if (h.b) {
                s2 = s2 + new SimpleDateFormat("dd/MM/yyy HH:mm:ss").format(new Date()) + " : ";
            }
            if (h.c) {
                s2 = s2 + h.d[n + 6] + " : ";
            }
            System.err.println(s2 + s);
        }
    }
    
    public static void a(final String s) {
        a(-6, s);
    }
    
    public static void b(final String s) {
        a(-3, s);
    }
    
    public static void c(final String s) {
        a(-2, s);
    }
    
    public static void d(final String s) {
        a(-1, s);
    }
    
    public static void e(final String s) {
        a(0, s);
    }
    
    public static void f(final String s) {
        a(1, s);
    }
    
    public static void g(final String s) {
        a(2, s);
    }
    
    static {
        h.a = 0;
        h.b = true;
        h.c = true;
        h.d = new String[] { "Fatal", "Emergency", "Alert", "Critical", "Error", "Warning", "Notice", "Info", "Debug" };
    }
}
