// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Font;

public class ay
{
    public static Font d;
    public static Font a;
    public static Font e;
    public static Font b;
    
    public static void a(final String s) {
        if (s != null) {
            ay.d = Font.decode(s);
        }
    }
    
    public static void d(final String s) {
        if (s != null) {
            ay.a = Font.decode(s);
        }
    }
    
    public static void e(final String s) {
        if (s != null) {
            ay.e = Font.decode(s);
        }
    }
    
    public static void b(final String s) {
        if (s != null) {
            ay.b = Font.decode(s);
        }
    }
    
    static {
        ay.d = new Font("SansSerif", 0, 12);
        ay.a = new Font("SansSerif", 1, 12);
        ay.e = new Font("Dialog", 0, 12);
        ay.b = new Font("Dialog", 1, 12);
        try {
            if (bs.d && bs.t == 1) {
                ay.d = new Font("Helvetica", 0, 12);
                ay.a = new Font("Helvetica", 1, 12);
            }
            else if (bs.d && bs.t != 1) {
                ay.d = new Font("SansSerif", 0, 12);
                ay.a = new Font("SansSerif", 1, 12);
                ay.e = new Font("Geneva", 0, 10);
                ay.b = new Font("Geneva", 1, 10);
            }
        }
        catch (Throwable t) {}
    }
}
