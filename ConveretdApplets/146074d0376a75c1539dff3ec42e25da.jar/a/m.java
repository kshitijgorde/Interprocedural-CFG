// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Font;

public final class m
{
    public static Font q;
    public static Font w;
    public static Font e;
    public static Font r;
    public static Font t;
    public static Font y;
    
    static {
        m.q = new Font("SansSerif", 0, 12);
        m.w = new Font("SansSerif", 1, 12);
        m.e = new Font("SansSerif", 1, 11);
        m.r = new Font("Dialog", 0, 12);
        m.t = new Font("Dialog", 1, 12);
        new Font("Geneva", 0, 10);
        new Font("Geneva", 0, 11);
        m.y = new Font("Tahoma", 0, 12);
        new Font("Tahoma", 0, 14);
        try {
            if (!ef.w || ef.w != 1) {
                if (ef.w && ef.w != 1) {
                    m.q = new Font("SansSerif", 0, 12);
                    m.w = new Font("SansSerif", 1, 12);
                    m.r = new Font("Geneva", 0, 10);
                    m.t = new Font("Geneva", 1, 10);
                }
                return;
            }
            m.q = new Font("Helvetica", 0, 12);
            m.w = new Font("Helvetica", 1, 12);
        }
        catch (Throwable t) {}
    }
}
