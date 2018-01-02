// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Font;

public final class bf
{
    public static Font q;
    public static Font w;
    public static Font e;
    public static Font r;
    public static Font t;
    public static Font y;
    
    public static void q(final String s) {
        if (s != null) {
            bf.q = Font.decode(s);
        }
    }
    
    public static void w(final String s) {
        if (s != null) {
            bf.w = Font.decode(s);
        }
    }
    
    public static void e(final String s) {
        if (s != null) {
            bf.r = Font.decode(s);
        }
    }
    
    public static void r(final String s) {
        if (s != null) {
            bf.t = Font.decode(s);
        }
    }
    
    static {
        bf.q = new Font("SansSerif", 0, 12);
        bf.w = new Font("SansSerif", 1, 12);
        bf.e = new Font("SansSerif", 1, 11);
        bf.r = new Font("Dialog", 0, 12);
        bf.t = new Font("Dialog", 1, 12);
        new Font("Geneva", 0, 10);
        new Font("Geneva", 0, 11);
        bf.y = new Font("Tahoma", 0, 12);
        new Font("Tahoma", 0, 14);
        try {
            if (!bF.w || bF.w != 1) {
                if (bF.w && bF.w != 1) {
                    bf.q = new Font("SansSerif", 0, 12);
                    bf.w = new Font("SansSerif", 1, 12);
                    bf.r = new Font("Geneva", 0, 10);
                    bf.t = new Font("Geneva", 1, 10);
                }
                return;
            }
            bf.q = new Font("Helvetica", 0, 12);
            bf.w = new Font("Helvetica", 1, 12);
        }
        catch (Throwable t) {}
    }
}
