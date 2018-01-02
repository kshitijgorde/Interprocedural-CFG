// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Font;

public class aK
{
    public static Font e;
    public static Font f;
    public static Font d;
    public static Font g;
    
    public static void a(final String s) {
        if (s != null) {
            aK.e = Font.decode(s);
        }
    }
    
    public static void b(final String s) {
        if (s != null) {
            aK.f = Font.decode(s);
        }
    }
    
    public static void c(final String s) {
        if (s != null) {
            aK.d = Font.decode(s);
        }
    }
    
    public static void d(final String s) {
        if (s != null) {
            aK.g = Font.decode(s);
        }
    }
    
    static {
        aK.e = new Font("SansSerif", 0, 12);
        aK.f = new Font("SansSerif", 1, 12);
        aK.d = new Font("Dialog", 0, 12);
        aK.g = new Font("Dialog", 1, 12);
        try {
            if (F.b && F.f == 1) {
                aK.e = new Font("Helvetica", 0, 12);
                aK.f = new Font("Helvetica", 1, 12);
            }
            else if (F.b && F.f != 1) {
                aK.e = new Font("SansSerif", 0, 12);
                aK.f = new Font("SansSerif", 1, 12);
                aK.d = new Font("Geneva", 0, 10);
                aK.g = new Font("Geneva", 1, 10);
            }
        }
        catch (Throwable t) {}
    }
}
