// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Font;

public final class ay
{
    public static Font a;
    public static Font b;
    public static Font c;
    public static Font d;
    
    static {
        ay.a = new Font("SansSerif", 0, 12);
        ay.b = new Font("SansSerif", 1, 12);
        ay.c = new Font("Dialog", 0, 12);
        ay.d = new Font("Dialog", 1, 12);
        try {
            if (aZ.b && aZ.b == 1) {
                ay.a = new Font("Helvetica", 0, 12);
                ay.b = new Font("Helvetica", 1, 12);
                return;
            }
            if (aZ.b && aZ.b != 1) {
                ay.a = new Font("SansSerif", 0, 12);
                ay.b = new Font("SansSerif", 1, 12);
                ay.c = new Font("Geneva", 0, 10);
                ay.d = new Font("Geneva", 1, 10);
            }
        }
        catch (Throwable t) {}
    }
}
