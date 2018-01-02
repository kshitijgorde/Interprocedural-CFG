// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Canvas;

public final class E extends Canvas
{
    private boolean q;
    private String q;
    private String w;
    private Font q;
    private Font w;
    private Color q;
    private Color w;
    
    public final Dimension preferredSize() {
        final Dimension preferredSize = super.preferredSize();
        final int height = this.getFontMetrics(this.q).getHeight();
        final int height2 = this.getFontMetrics(this.w).getHeight();
        if (this.q) {
            return new Dimension(preferredSize.width, Math.max(Math.max(height, height2) + 8, preferredSize.height));
        }
        return new Dimension(preferredSize.width, Math.max(height + height2 + 12, preferredSize.height));
    }
    
    public final void q(final String q, final String s) {
        this.q = q;
        this.w = null;
        this.repaint();
    }
    
    public final void q(final Font q, final Font w) {
        this.q = q;
        this.w = w;
        this.repaint();
    }
    
    public final void q(final Color q, final Color w) {
        this.q = q;
        this.w = w;
        this.repaint();
    }
    
    public final void paint(final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.q);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics(this.w);
        final Dimension size = this.size();
        graphics.clipRect(3, 3, size.width - 7, size.height - 7);
        int n;
        int n2;
        int n3;
        if (this.q) {
            n = (size.height + fontMetrics.getAscent()) / 2 - 1;
            n2 = (size.height + fontMetrics2.getAscent()) / 2 - 1;
            n3 = 5 + fontMetrics.stringWidth(this.q) + 7;
        }
        else {
            final int n4 = (size.height - (fontMetrics.getHeight() + fontMetrics2.getHeight())) / 4;
            n = fontMetrics.getAscent() + n4;
            n2 = size.height - 2 * fontMetrics2.getDescent() - n4;
            n3 = 5;
        }
        graphics.setColor(this.q);
        graphics.setFont(this.q);
        graphics.drawString(this.q, 5, n);
        if (this.w != null) {
            graphics.setColor(this.w);
            graphics.setFont(this.w);
            graphics.drawString(this.w, n3, n2);
        }
    }
    
    public E(final boolean b) {
        this.q = "";
        this.w = "";
        this.q = true;
        final Font r = cb.r;
        this.w = r;
        this.q = r;
        final Color black = Color.black;
        this.w = black;
        this.q = black;
    }
}
