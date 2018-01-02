// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Font;

public final class bk
{
    public static Font a;
    public static Font b;
    public static Font c;
    public static Font d;
    
    static {
        bk.a = new Font("SansSerif", 0, 12);
        bk.b = new Font("SansSerif", 1, 12);
        bk.c = new Font("Dialog", 0, 12);
        bk.d = new Font("Dialog", 1, 12);
        try {
            if (ce.b && ce.b == 1) {
                bk.a = new Font("Helvetica", 0, 12);
                bk.b = new Font("Helvetica", 1, 12);
                return;
            }
            if (ce.b && ce.b != 1) {
                bk.a = new Font("SansSerif", 0, 12);
                bk.b = new Font("SansSerif", 1, 12);
                bk.c = new Font("Geneva", 0, 10);
                bk.d = new Font("Geneva", 1, 10);
            }
        }
        catch (Throwable t) {}
    }
}
