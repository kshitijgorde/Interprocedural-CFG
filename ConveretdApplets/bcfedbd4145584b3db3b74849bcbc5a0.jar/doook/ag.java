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

public class ag extends Canvas
{
    private boolean a;
    protected String o;
    protected String b;
    protected Font b;
    protected Font c;
    protected Color h;
    protected Color d;
    
    public Dimension preferredSize() {
        final Dimension preferredSize = super.preferredSize();
        final int height = this.getFontMetrics(this.b).getHeight();
        final int height2 = this.getFontMetrics(this.c).getHeight();
        if (this.a) {
            return new Dimension(preferredSize.width, Math.max(Math.max(height, height2) + 8, preferredSize.height));
        }
        return new Dimension(preferredSize.width, Math.max(height + height2 + 12, preferredSize.height));
    }
    
    public void a(final String o, final String b) {
        this.o = o;
        this.b = b;
        this.repaint();
    }
    
    public void a(final Font b, final Font c) {
        this.b = b;
        this.c = c;
        this.repaint();
    }
    
    public void a(final Color h, final Color d) {
        this.h = h;
        this.d = d;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.b);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics(this.c);
        final Dimension size = this.size();
        final int n = 5;
        graphics.clipRect(3, 3, size.width - 7, size.height - 7);
        int n2;
        int n3;
        int n4;
        if (this.a) {
            n2 = (size.height + fontMetrics.getAscent()) / 2 - 1;
            n3 = (size.height + fontMetrics2.getAscent()) / 2 - 1;
            n4 = n + fontMetrics.stringWidth(this.o) + 7;
        }
        else {
            final int n5 = (size.height - (fontMetrics.getHeight() + fontMetrics2.getHeight())) / 4;
            n2 = fontMetrics.getAscent() + n5;
            n3 = size.height - 2 * fontMetrics2.getDescent() - n5;
            n4 = n;
        }
        graphics.setColor(this.h);
        graphics.setFont(this.b);
        graphics.drawString(this.o, n, n2);
        if (this.b != null) {
            graphics.setColor(this.d);
            graphics.setFont(this.c);
            graphics.drawString(this.b, n4, n3);
        }
    }
    
    public ag(final boolean a) {
        this.o = "";
        this.b = "";
        this.a = a;
        final Font e = ay.e;
        this.c = e;
        this.b = e;
        final Color black = Color.black;
        this.d = black;
        this.h = black;
    }
}
