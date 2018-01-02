// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util;

public class c3
{
    public static final int a;
    public static int b;
    public static int c;
    public static boolean d;
    public static boolean e;
    public static boolean f;
    public static boolean g;
    public static boolean h;
    public static boolean i;
    public static boolean j;
    public static boolean k;
    
    static {
        c3.d = false;
        c3.e = false;
        c3.f = false;
        c3.g = false;
        c3.h = false;
        c3.i = false;
        c3.j = false;
        c3.k = false;
        final String lowerCase = System.getProperty("os.name").toLowerCase();
        final String lowerCase2 = System.getProperty("java.vendor").toLowerCase();
        final String property = System.getProperty("java.version");
        int index = 0;
        int n = 0;
        int n2 = 2;
        int c = 0;
        if ("1.02".equals(property)) {
            c = 65538;
        }
        else {
            while (n2 >= 0 && index != -1) {
                index = property.indexOf(46, n);
                try {
                    if (index == -1) {
                        c += Integer.parseInt(property.substring(n)) << n2 * 8;
                    }
                    else {
                        c += Integer.parseInt(property.substring(n, index)) << n2 * 8;
                    }
                }
                catch (NumberFormatException ex) {}
                n = index + 1;
                --n2;
            }
        }
        c3.c = c;
        if (lowerCase.startsWith("windows")) {
            c3.g = true;
            if (lowerCase.startsWith("windows nt")) {
                c3.h = true;
            }
            else if (lowerCase.startsWith("windows 98")) {
                c3.j = true;
            }
            else if (lowerCase.startsWith("windows 95")) {
                c3.i = true;
            }
        }
        else if (lowerCase.startsWith("mac")) {
            c3.e = true;
        }
        else if (lowerCase.startsWith("solaris")) {
            c3.k = true;
        }
        c3.f = (lowerCase.indexOf("os x") != -1);
        if (c3.e) {
            a = 3;
        }
        else {
            a = -1;
        }
        if (lowerCase2.startsWith("apple")) {
            c3.b = 3;
        }
        else if (lowerCase2.startsWith("netscape")) {
            c3.b = 1;
        }
        else if (lowerCase2.startsWith("microsoft")) {
            c3.b = 2;
        }
        else if (lowerCase2.startsWith("sun")) {
            c3.b = 4;
        }
        else {
            c3.b = -1;
        }
        c3.d = (c3.b != 1 || !c3.e || (c3.c & 0xFFFFFF00) != 0x10100);
    }
}
