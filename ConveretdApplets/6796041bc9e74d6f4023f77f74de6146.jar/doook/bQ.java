// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Canvas;

public class bQ extends Canvas
{
    private boolean a;
    protected String R;
    protected String d;
    protected Font g;
    protected Font h;
    protected Color k;
    protected Color d;
    
    public Dimension preferredSize() {
        final Dimension preferredSize = super.preferredSize();
        final int height = this.getFontMetrics(this.g).getHeight();
        final int height2 = this.getFontMetrics(this.h).getHeight();
        if (this.a) {
            return new Dimension(preferredSize.width, Math.max(Math.max(height, height2) + 8, preferredSize.height));
        }
        return new Dimension(preferredSize.width, Math.max(height + height2 + 12, preferredSize.height));
    }
    
    public void a(final String r, final String d) {
        this.R = r;
        this.d = d;
        this.repaint();
    }
    
    public void a(final Font g, final Font h) {
        this.g = g;
        this.h = h;
        this.repaint();
    }
    
    public void a(final Color k, final Color d) {
        this.k = k;
        this.d = d;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.g);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics(this.h);
        final Dimension size = this.size();
        final int n = 5;
        graphics.clipRect(3, 3, size.width - 7, size.height - 7);
        int n2;
        int n3;
        int n4;
        if (this.a) {
            n2 = (size.height + fontMetrics.getAscent()) / 2 - 1;
            n3 = (size.height + fontMetrics2.getAscent()) / 2 - 1;
            n4 = n + fontMetrics.stringWidth(this.R) + 7;
        }
        else {
            final int n5 = (size.height - (fontMetrics.getHeight() + fontMetrics2.getHeight())) / 4;
            n2 = fontMetrics.getAscent() + n5;
            n3 = size.height - 2 * fontMetrics2.getDescent() - n5;
            n4 = n;
        }
        graphics.setColor(this.k);
        graphics.setFont(this.g);
        graphics.drawString(this.R, n, n2);
        if (this.d != null) {
            graphics.setColor(this.d);
            graphics.setFont(this.h);
            graphics.drawString(this.d, n4, n3);
        }
    }
    
    public bQ(final boolean a) {
        this.R = "";
        this.d = "";
        this.a = a;
        final Font f = bL.f;
        this.h = f;
        this.g = f;
        final Color black = Color.black;
        this.d = black;
        this.k = black;
    }
}
