// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import com.diginet.digichat.util.c3;
import java.awt.Font;

public class m
{
    public static Font a;
    public static Font b;
    public static Font c;
    public static Font d;
    
    public static void a(final String s) {
        if (s != null) {
            m.a = Font.decode(s);
        }
    }
    
    public static void b(final String s) {
        if (s != null) {
            m.b = Font.decode(s);
        }
    }
    
    public static void c(final String s) {
        if (s != null) {
            m.c = Font.decode(s);
        }
    }
    
    public static void d(final String s) {
        if (s != null) {
            m.d = Font.decode(s);
        }
    }
    
    static {
        m.a = new Font("SansSerif", 0, 12);
        m.b = new Font("SansSerif", 1, 12);
        m.c = new Font("Dialog", 0, 12);
        m.d = new Font("Dialog", 1, 12);
        try {
            if (c3.e && c3.b == 1) {
                m.a = new Font("Helvetica", 0, 12);
                m.b = new Font("Helvetica", 1, 12);
            }
            else if (c3.e && c3.b != 1) {
                m.a = new Font("SansSerif", 0, 12);
                m.b = new Font("SansSerif", 1, 12);
                m.c = new Font("Geneva", 0, 10);
                m.d = new Font("Geneva", 1, 10);
            }
        }
        catch (Throwable t) {}
    }
}
