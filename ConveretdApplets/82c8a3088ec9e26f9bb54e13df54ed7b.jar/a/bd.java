// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Font;

public final class bd
{
    public static Font q;
    public static Font w;
    public static Font e;
    public static Font r;
    public static Font t;
    public static Font y;
    
    public static void q(final String s) {
        if (s != null) {
            bd.q = Font.decode(s);
        }
    }
    
    public static void w(final String s) {
        if (s != null) {
            bd.w = Font.decode(s);
        }
    }
    
    public static void e(final String s) {
        if (s != null) {
            bd.r = Font.decode(s);
        }
    }
    
    public static void r(final String s) {
        if (s != null) {
            bd.t = Font.decode(s);
        }
    }
    
    static {
        bd.q = new Font("SansSerif", 0, 12);
        bd.w = new Font("SansSerif", 1, 12);
        bd.e = new Font("SansSerif", 1, 11);
        bd.r = new Font("Dialog", 0, 12);
        bd.t = new Font("Dialog", 1, 12);
        new Font("Geneva", 0, 10);
        new Font("Geneva", 0, 11);
        bd.y = new Font("Tahoma", 0, 12);
        new Font("Tahoma", 0, 14);
        try {
            if (!bD.w || bD.w != 1) {
                if (bD.w && bD.w != 1) {
                    bd.q = new Font("SansSerif", 0, 12);
                    bd.w = new Font("SansSerif", 1, 12);
                    bd.r = new Font("Geneva", 0, 10);
                    bd.t = new Font("Geneva", 1, 10);
                }
                return;
            }
            bd.q = new Font("Helvetica", 0, 12);
            bd.w = new Font("Helvetica", 1, 12);
        }
        catch (Throwable t) {}
    }
}
