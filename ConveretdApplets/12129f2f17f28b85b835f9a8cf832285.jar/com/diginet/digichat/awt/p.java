// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import com.diginet.digichat.util.ch;
import java.awt.Font;

public class p
{
    public static Font a;
    public static Font b;
    public static Font c;
    public static Font d;
    
    public static void a(final String s) {
        if (s != null) {
            p.a = Font.decode(s);
        }
    }
    
    public static void b(final String s) {
        if (s != null) {
            p.b = Font.decode(s);
        }
    }
    
    public static void c(final String s) {
        if (s != null) {
            p.c = Font.decode(s);
        }
    }
    
    public static void d(final String s) {
        if (s != null) {
            p.d = Font.decode(s);
        }
    }
    
    static {
        p.a = new Font("SansSerif", 0, 12);
        p.b = new Font("SansSerif", 1, 12);
        p.c = new Font("Dialog", 0, 12);
        p.d = new Font("Dialog", 1, 12);
        try {
            if (ch.e && ch.b == 1) {
                p.a = new Font("Helvetica", 0, 12);
                p.b = new Font("Helvetica", 1, 12);
            }
            else if (ch.e && ch.b != 1) {
                p.a = new Font("SansSerif", 0, 12);
                p.b = new Font("SansSerif", 1, 12);
                p.c = new Font("Geneva", 0, 10);
                p.d = new Font("Geneva", 1, 10);
            }
        }
        catch (Throwable t) {}
    }
}
