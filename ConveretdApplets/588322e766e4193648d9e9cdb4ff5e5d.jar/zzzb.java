import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class zzzb extends Canvas
{
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        final int width = size.width;
        final int height = size.height;
        final int n = (width - 250) / 2;
        final int n2 = (height - 25) / 2;
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.getFont());
        final String s = "Chargement du dictionnaire... ";
        final int stringWidth = fontMetrics.stringWidth(s);
        final int height2 = fontMetrics.getHeight();
        if (zzzwf.zzzvf) {
            if (zzzwf.zzzuf > 100) {
                zzzwf.zzzuf %= 100;
            }
            final int n3 = 249 * zzzwf.zzzuf / 100;
            graphics.setColor(zzznc.zzzac);
            graphics.drawString(s, (width - stringWidth) / 2, n2 - height2);
            graphics.drawRect(n, n2, 250, 25);
            graphics.setColor(Color.yellow);
            graphics.fillRect(n + 1, n2 + 1, n3, 24);
            graphics.setColor(Color.white);
            graphics.fillRect(n + 1 + n3, n2 + 1, 249 - n3, 24);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
