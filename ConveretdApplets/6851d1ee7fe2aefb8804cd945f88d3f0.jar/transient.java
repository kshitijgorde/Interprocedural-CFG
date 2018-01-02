import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class transient
{
    public static final int HEIGHT = 5;
    
    public static void a(final Graphics graphics, final int n, final int n2) {
        graphics.drawLine(n, n2, n, n2);
        for (int i = 1; i < 5; ++i) {
            graphics.drawLine(n - i, n2 + i, n + i, n2 + i);
        }
    }
    
    public static void f(final Graphics graphics, final int n, final int n2) {
        graphics.drawLine(n, n2, n, n2);
        for (int i = 1; i < 5; ++i) {
            graphics.drawLine(n - i, n2 - i, n + i, n2 - i);
        }
    }
}
