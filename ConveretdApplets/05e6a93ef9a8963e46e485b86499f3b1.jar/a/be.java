// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Font;

public final class be
{
    public static Font q;
    public static Font w;
    public static Font e;
    public static Font r;
    public static Font t;
    public static Font y;
    
    public static void q(final String s) {
        if (s != null) {
            be.q = Font.decode(s);
        }
    }
    
    public static void w(final String s) {
        if (s != null) {
            be.w = Font.decode(s);
        }
    }
    
    public static void e(final String s) {
        if (s != null) {
            be.r = Font.decode(s);
        }
    }
    
    public static void r(final String s) {
        if (s != null) {
            be.t = Font.decode(s);
        }
    }
    
    static {
        be.q = new Font("SansSerif", 0, 12);
        be.w = new Font("SansSerif", 1, 12);
        be.e = new Font("SansSerif", 1, 11);
        be.r = new Font("Dialog", 0, 12);
        be.t = new Font("Dialog", 1, 12);
        new Font("Geneva", 0, 10);
        new Font("Geneva", 0, 11);
        be.y = new Font("Tahoma", 0, 12);
        new Font("Tahoma", 0, 14);
        try {
            if (!bE.w || bE.w != 1) {
                if (bE.w && bE.w != 1) {
                    be.q = new Font("SansSerif", 0, 12);
                    be.w = new Font("SansSerif", 1, 12);
                    be.r = new Font("Geneva", 0, 10);
                    be.t = new Font("Geneva", 1, 10);
                }
                return;
            }
            be.q = new Font("Helvetica", 0, 12);
            be.w = new Font("Helvetica", 1, 12);
        }
        catch (Throwable t) {}
    }
}
