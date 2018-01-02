// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Graphics;
import java.awt.Canvas;

public class x extends Canvas
{
    public final Graphics a(int n, final int n2, int n3, final int n4) {
        final Graphics graphics;
        if ((graphics = this.getGraphics()) != null) {
            if (n4 < 0) {
                n -= n4;
                n3 += n4;
                graphics.copyArea(0, n, n2, n3, 0, n4);
                graphics.clipRect(0, n + n3 + n4, n2, -n4 + 1);
            }
            else if (n4 > 0) {
                n3 -= n4 - 1;
                graphics.copyArea(0, n, n2, n3, 0, n4);
                graphics.clipRect(0, n, n2, n4 + 1);
            }
        }
        return graphics;
    }
}
