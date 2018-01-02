// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Canvas;

public final class bR extends Canvas
{
    private int q;
    private Font q;
    private Image q;
    private Graphics q;
    
    public final void q(final int q) {
        if (this.q == q) {
            return;
        }
        this.q = q;
        this.paint(this.getGraphics());
    }
    
    public final Dimension preferredSize() {
        return new Dimension(200, Toolkit.getDefaultToolkit().getFontMetrics(this.getFont()).getHeight() + 4);
    }
    
    public final void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        if (this.q == null) {
            this.q = this.createImage(this.size().width, this.size().height);
            this.q = this.q.getGraphics();
        }
        final String string = this.q + "%";
        final FontMetrics fontMetrics;
        final int stringWidth = (fontMetrics = graphics.getFontMetrics()).stringWidth(string);
        final int height = fontMetrics.getHeight();
        final float n = this.q / 100.0f;
        final Color foreground = this.getForeground();
        final Color background = this.getBackground();
        final Dimension size = this.size();
        final int n2 = (int)(n * (size.width - 2));
        this.q.setColor(background);
        this.q.fillRect(n2 + 1 + 1, 1, size.width - 2 - n2, size.height - 2);
        this.q.setColor(Color.black);
        this.q.drawRect(0, 0, size.width - 1, size.height - 1);
        this.q.setColor(foreground);
        this.q.fillRect(1, 1, n2, size.height - 2);
        this.q.setColor(foreground);
        this.q.drawString(string, (size.width - stringWidth) / 2, fontMetrics.getAscent() + (size.height - height) / 2);
        this.q.clipRect(0, 0, n2, this.size().height);
        this.q.setColor(background);
        this.q.drawString(string, (size.width - stringWidth) / 2, fontMetrics.getAscent() + (size.height - height) / 2);
        graphics.drawImage((Image)(this.q = this.q.getGraphics()), 0, 0, null);
    }
    
    public bR() {
        this.q = 0;
        this.q = new Font("dialog", 1, 12);
        this.q = null;
        this.setFont((Font)(this.q = null));
    }
}
