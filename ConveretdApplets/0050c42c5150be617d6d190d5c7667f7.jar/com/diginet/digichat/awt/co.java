// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Canvas;

public class co extends Canvas
{
    private boolean a;
    protected String b;
    protected String c;
    protected Font d;
    protected Font e;
    protected Color f;
    protected Color g;
    
    public final Dimension preferredSize() {
        final Dimension preferredSize = super.preferredSize();
        final int height = this.getFontMetrics(this.d).getHeight();
        final int height2 = this.getFontMetrics(this.e).getHeight();
        if (this.a) {
            return new Dimension(preferredSize.width, Math.max(Math.max(height, height2) + 8, preferredSize.height));
        }
        return new Dimension(preferredSize.width, Math.max(height + height2 + 12, preferredSize.height));
    }
    
    public final void a(final String b, final String c) {
        this.b = b;
        this.c = c;
        this.repaint();
    }
    
    public final void a(final Font d, final Font e) {
        this.d = d;
        this.e = e;
        this.repaint();
    }
    
    public final void a(final Color f, final Color g) {
        this.f = f;
        this.g = g;
        this.repaint();
    }
    
    public final void paint(final Graphics graphics) {
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.d);
        final FontMetrics fontMetrics2 = graphics.getFontMetrics(this.e);
        final Dimension size = this.size();
        final int n = 5;
        graphics.clipRect(3, 3, size.width - 7, size.height - 7);
        int n2;
        int n3;
        int n4;
        if (this.a) {
            n2 = (size.height + fontMetrics.getAscent()) / 2 - 1;
            n3 = (size.height + fontMetrics2.getAscent()) / 2 - 1;
            n4 = n + fontMetrics.stringWidth(this.b) + 7;
        }
        else {
            final int n5 = (size.height - (fontMetrics.getHeight() + fontMetrics2.getHeight())) / 4;
            n2 = fontMetrics.getAscent() + n5;
            n3 = size.height - 2 * fontMetrics2.getDescent() - n5;
            n4 = n;
        }
        graphics.setColor(this.f);
        graphics.setFont(this.d);
        graphics.drawString(this.b, n, n2);
        if (this.c != null) {
            graphics.setColor(this.g);
            graphics.setFont(this.e);
            graphics.drawString(this.c, n4, n3);
        }
    }
    
    public co(final boolean a) {
        this.b = "";
        this.c = "";
        this.a = a;
        final Font c = m.c;
        this.e = c;
        this.d = c;
        final Color black = Color.black;
        this.g = black;
        this.f = black;
    }
}
