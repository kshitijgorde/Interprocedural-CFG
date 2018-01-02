// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Font;

public final class cb
{
    public static Font q;
    public static Font w;
    public static Font e;
    public static Font r;
    public static Font t;
    public static Font y;
    
    static {
        cb.q = new Font("SansSerif", 0, 12);
        cb.w = new Font("SansSerif", 1, 12);
        cb.e = new Font("SansSerif", 1, 11);
        cb.r = new Font("Dialog", 0, 12);
        cb.t = new Font("Dialog", 1, 12);
        new Font("Geneva", 0, 10);
        new Font("Geneva", 0, 11);
        cb.y = new Font("Tahoma", 0, 12);
        new Font("Tahoma", 0, 14);
        try {
            if (!cK.w || cK.w != 1) {
                if (cK.w && cK.w != 1) {
                    cb.q = new Font("SansSerif", 0, 12);
                    cb.w = new Font("SansSerif", 1, 12);
                    cb.r = new Font("Geneva", 0, 10);
                    cb.t = new Font("Geneva", 1, 10);
                }
                return;
            }
            cb.q = new Font("Helvetica", 0, 12);
            cb.w = new Font("Helvetica", 1, 12);
        }
        catch (Throwable t) {}
    }
}
