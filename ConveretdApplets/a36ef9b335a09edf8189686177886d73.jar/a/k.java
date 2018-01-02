// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Font;

public final class k
{
    public static Font q;
    public static Font w;
    public static Font e;
    public static Font r;
    public static Font t;
    public static Font y;
    
    public static void q(final String s) {
        if (s != null) {
            k.q = Font.decode(s);
        }
    }
    
    public static void w(final String s) {
        if (s != null) {
            k.w = Font.decode(s);
        }
    }
    
    public static void e(final String s) {
        if (s != null) {
            k.r = Font.decode(s);
        }
    }
    
    public static void r(final String s) {
        if (s != null) {
            k.t = Font.decode(s);
        }
    }
    
    static {
        k.q = new Font("SansSerif", 0, 12);
        k.w = new Font("SansSerif", 1, 12);
        k.e = new Font("SansSerif", 1, 11);
        k.r = new Font("Dialog", 0, 12);
        k.t = new Font("Dialog", 1, 12);
        new Font("Geneva", 0, 10);
        new Font("Geneva", 0, 11);
        k.y = new Font("Tahoma", 0, 12);
        new Font("Tahoma", 0, 14);
        try {
            if (!cx.w || cx.w != 1) {
                if (cx.w && cx.w != 1) {
                    k.q = new Font("SansSerif", 0, 12);
                    k.w = new Font("SansSerif", 1, 12);
                    k.r = new Font("Geneva", 0, 10);
                    k.t = new Font("Geneva", 1, 10);
                }
                return;
            }
            k.q = new Font("Helvetica", 0, 12);
            k.w = new Font("Helvetica", 1, 12);
        }
        catch (Throwable t) {}
    }
}
