import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class a extends Canvas
{
    public void paint(final Graphics graphics) {
        final k k = (k)this.getParent();
        final Dimension size = this.getSize();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
        graphics.setColor(this.getForeground());
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        for (int i = 0; i < 8; ++i) {
            char c;
            if (k.af()) {
                c = (char)(56 - i);
            }
            else {
                c = (char)(49 + i);
            }
            graphics.drawString(String.valueOf(c), k.ab() - fontMetrics.charWidth(c) - 10, k.ac() + k.ae() * i + k.ae() / 2 + 5);
        }
    }
}
