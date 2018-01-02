// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util;

public class dx
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
    
    static {
        dx.d = false;
        dx.f = false;
        dx.g = false;
        dx.h = false;
        dx.i = false;
        dx.j = false;
        dx.e = false;
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
        dx.c = c;
        if (lowerCase.startsWith("windows")) {
            dx.f = true;
            if (lowerCase.startsWith("windows nt")) {
                dx.g = true;
            }
            else if (lowerCase.startsWith("windows 98")) {
                dx.i = true;
            }
            else if (lowerCase.startsWith("windows 95")) {
                dx.h = true;
            }
        }
        else if (lowerCase.startsWith("mac")) {
            dx.e = true;
        }
        else if (lowerCase.startsWith("solaris")) {
            dx.j = true;
        }
        if (dx.e) {
            a = 3;
        }
        else {
            a = -1;
        }
        if (lowerCase2.startsWith("apple")) {
            dx.b = 3;
        }
        else if (lowerCase2.startsWith("netscape")) {
            dx.b = 1;
        }
        else if (lowerCase2.startsWith("microsoft")) {
            dx.b = 2;
        }
        else if (lowerCase2.startsWith("sun")) {
            dx.b = 4;
        }
        else {
            dx.b = -1;
        }
        dx.d = (dx.b != 1 || !dx.e || (dx.c & 0xFFFFFF00) != 0x10100);
    }
}
