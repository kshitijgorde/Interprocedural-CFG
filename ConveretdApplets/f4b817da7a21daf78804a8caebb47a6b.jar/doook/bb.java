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

public class bb extends Canvas
{
    private boolean d;
    protected String a;
    protected String b;
    protected Font d;
    protected Font g;
    protected Color e;
    protected Color g;
    
    public Dimension preferredSize() {
        final Dimension preferredSize = super.preferredSize();
        final int height = this.getFontMetrics(this.d).getHeight();
        final int height2 = this.getFontMetrics(this.g).getHeight();
        if (this.d) {
            return new Dimension(preferredSize.width, Math.max(Math.max(height, height2) + 8, preferredSize.height));
        }
        return new Dimension(preferredSize.width, Math.max(height + height2 + 12, preferredSize.height));
    }
    
    public void a(final String a, final String b) {
        this.a = a;
        this.b = b;
        this.repaint();
    }
    
    public void a(final Font d, final Font g) {
        this.d = d;
        this.g = g;
        this.repaint();
    }
    
    public void a(final Color e, final Color g) {
        this.e = e;
        this.g = g;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.d);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics(this.g);
        final Dimension size = this.size();
        final int n = 5;
        graphics.clipRect(3, 3, size.width - 7, size.height - 7);
        int n2;
        int n3;
        int n4;
        if (this.d) {
            n2 = (size.height + fontMetrics.getAscent()) / 2 - 1;
            n3 = (size.height + fontMetrics2.getAscent()) / 2 - 1;
            n4 = n + fontMetrics.stringWidth(this.a) + 7;
        }
        else {
            final int n5 = (size.height - (fontMetrics.getHeight() + fontMetrics2.getHeight())) / 4;
            n2 = fontMetrics.getAscent() + n5;
            n3 = size.height - 2 * fontMetrics2.getDescent() - n5;
            n4 = n;
        }
        graphics.setColor(this.e);
        graphics.setFont(this.d);
        graphics.drawString(this.a, n, n2);
        if (this.b != null) {
            graphics.setColor(this.g);
            graphics.setFont(this.g);
            graphics.drawString(this.b, n4, n3);
        }
    }
    
    public bb(final boolean d) {
        this.a = "";
        this.b = "";
        this.d = d;
        final Font d2 = aK.d;
        this.g = d2;
        this.d = d2;
        final Color black = Color.black;
        this.g = black;
        this.e = black;
    }
}
