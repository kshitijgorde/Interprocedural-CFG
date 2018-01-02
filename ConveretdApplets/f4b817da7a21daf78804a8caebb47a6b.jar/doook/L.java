// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Graphics;
import java.awt.Canvas;

public class L extends Canvas
{
    public Graphics a(final int n, int n2, final int n3, int n4, final int n5) {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            if (n5 < 0) {
                n2 -= n5;
                n4 += n5;
                graphics.copyArea(n, n2, n3, n4, 0, n5);
                graphics.clipRect(n, n2 + n4 + n5, n3, -n5 + 1);
            }
            else if (n5 > 0) {
                n4 -= n5 - 1;
                graphics.copyArea(n, n2, n3, n4, 0, n5);
                graphics.clipRect(n, n2, n3, n5 + 1);
            }
        }
        return graphics;
    }
}
