// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util;

public class ch
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
        ch.d = false;
        ch.e = false;
        ch.f = false;
        ch.g = false;
        ch.h = false;
        ch.i = false;
        ch.j = false;
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
        ch.c = c;
        if (lowerCase.startsWith("windows")) {
            ch.f = true;
            if (lowerCase.startsWith("windows nt")) {
                ch.g = true;
            }
            else if (lowerCase.startsWith("windows 98")) {
                ch.i = true;
            }
            else if (lowerCase.startsWith("windows 95")) {
                ch.h = true;
            }
        }
        else if (lowerCase.startsWith("mac")) {
            ch.e = true;
        }
        else if (lowerCase.startsWith("solaris")) {
            ch.j = true;
        }
        if (ch.e) {
            a = 3;
        }
        else {
            a = -1;
        }
        if (lowerCase2.startsWith("apple")) {
            ch.b = 3;
        }
        else if (lowerCase2.startsWith("netscape")) {
            ch.b = 1;
        }
        else if (lowerCase2.startsWith("microsoft")) {
            ch.b = 2;
        }
        else if (lowerCase2.startsWith("sun")) {
            ch.b = 4;
        }
        else {
            ch.b = -1;
        }
        ch.d = (ch.b != 1 || !ch.e || (ch.c & 0xFFFFFF00) != 0x10100);
    }
}
