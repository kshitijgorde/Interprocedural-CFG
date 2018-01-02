// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Font;

public class bL
{
    public static Font e;
    public static Font a;
    public static Font f;
    public static Font g;
    
    static {
        bL.e = new Font("SansSerif", 0, 12);
        bL.a = new Font("SansSerif", 1, 12);
        bL.f = new Font("Dialog", 0, 12);
        bL.g = new Font("Dialog", 1, 12);
        try {
            if (doook.f.d && doook.f.h == 1) {
                bL.e = new Font("Helvetica", 0, 12);
                bL.a = new Font("Helvetica", 1, 12);
            }
            else if (doook.f.d && doook.f.h != 1) {
                bL.e = new Font("SansSerif", 0, 12);
                bL.a = new Font("SansSerif", 1, 12);
                bL.f = new Font("Geneva", 0, 10);
                bL.g = new Font("Geneva", 1, 10);
            }
        }
        catch (Throwable t) {}
    }
}
