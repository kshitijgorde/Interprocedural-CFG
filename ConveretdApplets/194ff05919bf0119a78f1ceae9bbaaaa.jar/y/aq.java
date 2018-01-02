// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Color;

public final class aq
{
    public static final Color a;
    private static Color b;
    
    public static void a(final ei ei, final u u) {
        final int c = u.c();
        final int d = u.d();
        ei.a(u.a());
        ei.c(0, 0, c - 1, d - 1);
        ei.a(aq.a);
        ei.c(3, 3, c - 7, d - 7);
        ei.a(aq.b);
        ei.c(1, 1, c - 3, d - 3);
        ei.a(new Color(102, 102, 0));
        ei.c(2, 2, c - 5, d - 5);
        ei.c(4, 4, c - 9, d - 9);
        ei.a(c, d, 1, 1, 4, 1);
        ei.a(aq.b);
        ei.a(c, d, 0, 0, 5, 0);
        ei.a(aq.a);
        ei.a(c, d, 2, 2, 2, 2);
    }
    
    static {
        a = new Color(10066278);
        aq.b = new Color(3342336);
    }
}
