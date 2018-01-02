import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class DotRect
{
    public void draw(final Graphics graphics, final Color color, final Color color2, final Rectangle rectangle, final int n) {
        TocDebug.TraceL3("DotRect::draw1");
        this.draw(graphics, color, color2, rectangle.x, rectangle.y, rectangle.width, rectangle.height, n);
    }
    
    public void draw(final Graphics graphics, final Color color, final Color xorMode, int n, int n2, int n3, int n4, int n5) {
        TocDebug.TraceL3("DotRect::draw2");
        final Color color2 = graphics.getColor();
        graphics.setColor(color);
        graphics.setXORMode(xorMode);
        if (n5 > 0) {
            for (int i = 0; i < n5; ++i) {
                graphics.drawRect(n++, n2++, n3--, n4--);
                --n3;
                --n4;
            }
        }
        else {
            if (n5 < -1) {
                n5 = -1;
            }
            int j = n + 1;
            final int n6 = j + n3 + n5;
            final int n7 = n2 + n4;
            while (j < n6) {
                graphics.drawLine(j, n2, j - n5, n2);
                graphics.drawLine(j, n7, j - n5, n7);
                j += 2 - n5;
            }
            final int n8 = j - 1;
            for (int k = n2 + 1; k < k + n4 + n5; k += 2 - n5) {
                graphics.drawLine(n, k, n, k - n5);
                graphics.drawLine(n8, k, n8, k - n5);
            }
        }
        graphics.setPaintMode();
        graphics.setColor(color2);
    }
}
