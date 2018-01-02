import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class j extends Canvas
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
                c = (char)(97 + i);
            }
            else {
                c = (char)(104 - i);
            }
            graphics.drawString(String.valueOf(c), k.ab() + k.ad() * i + k.ad() / 2 - fontMetrics.charWidth(c) / 2 + 1, 16);
        }
        graphics.setFont(new Font("Serif", 0, 9));
        graphics.setColor(Color.white);
        graphics.drawString("\u25b2", 11, 16);
        graphics.drawString("\u25bc", 5, 15);
    }
}
