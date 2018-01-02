// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import com.diginet.digichat.util.dx;
import java.awt.Font;

public class dw
{
    public static Font a;
    public static Font b;
    public static Font c;
    public static Font d;
    
    public static void a(final String s) {
        if (s != null) {
            dw.a = Font.decode(s);
        }
    }
    
    public static void b(final String s) {
        if (s != null) {
            dw.b = Font.decode(s);
        }
    }
    
    public static void c(final String s) {
        if (s != null) {
            dw.c = Font.decode(s);
        }
    }
    
    public static void d(final String s) {
        if (s != null) {
            dw.d = Font.decode(s);
        }
    }
    
    static {
        dw.a = new Font("SansSerif", 0, 12);
        dw.b = new Font("SansSerif", 1, 12);
        dw.c = new Font("Dialog", 0, 12);
        dw.d = new Font("Dialog", 1, 12);
        try {
            if (dx.e && dx.b == 1) {
                dw.a = new Font("Helvetica", 0, 12);
                dw.b = new Font("Helvetica", 1, 12);
            }
            else if (dx.e && dx.b != 1) {
                dw.a = new Font("SansSerif", 0, 12);
                dw.b = new Font("SansSerif", 1, 12);
                dw.c = new Font("Geneva", 0, 10);
                dw.d = new Font("Geneva", 1, 10);
            }
        }
        catch (Throwable t) {}
    }
}
