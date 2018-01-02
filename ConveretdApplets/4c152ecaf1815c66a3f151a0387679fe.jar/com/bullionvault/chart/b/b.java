// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.b;

import java.awt.FontMetrics;
import com.bullionvault.chart.c.h;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

public final class b implements a
{
    private String a;
    private Dimension b;
    private Dimension c;
    private int d;
    private int e;
    private int f;
    private boolean g;
    private Color h;
    private Color i;
    private int j;
    private String k;
    private Font l;
    
    public b() {
        this.a = "Watermark";
        this.f = 0;
        this.g = true;
        this.h = new Color(255, 255, 199);
        this.i = a(this.h, 0.95);
        this.j = 1;
        this.k = "SansSerif";
        this.l = new Font(this.k, this.j, 1);
    }
    
    public b(final String a, final String k, final int j) {
        this.a = "Watermark";
        this.f = 0;
        this.g = true;
        this.h = new Color(255, 255, 199);
        this.i = a(this.h, 0.95);
        this.j = 1;
        this.k = "SansSerif";
        this.l = new Font(this.k, this.j, 1);
        this.a = a;
        this.k = k;
        this.j = j;
    }
    
    public final void a(final Color h) {
        this.h = h;
    }
    
    public final void a(final Dimension b) {
        this.b = b;
    }
    
    public final void b(final Dimension c) {
        if (!c.equals(this.c)) {
            this.c = c;
            this.g = true;
        }
    }
    
    private static Font a(final Graphics graphics, final String s, final String s2, final int n, final Dimension dimension) {
        int n2 = 10;
        Font font = new Font(s2, n, 10);
        int i = 0;
        while (i == 0) {
            final int n3 = n2 + 1;
            font = new Font(s2, n, n3);
            final FontMetrics fontMetrics;
            if ((fontMetrics = graphics.getFontMetrics(font)).stringWidth(s) > dimension.width || fontMetrics.getHeight() > dimension.height) {
                i = 1;
            }
            n2 = n3;
        }
        h.e("Largest Font is : " + n2);
        return font;
    }
    
    private static Color a(Color color, final double n) {
        return color = new Color((int)(color.getRed() * n), (int)(color.getGreen() * n), (int)(color.getBlue() * n));
    }
    
    public final void a(final Graphics graphics) {
        if (this.g) {
            final Dimension dimension2;
            final Dimension dimension = dimension2 = new Dimension(this.c);
            dimension.width = dimension.width;
            final Dimension dimension3 = dimension2;
            dimension3.height = dimension3.height;
            this.l = a(graphics, this.a, this.k, this.j, dimension2);
            if (this.l.getSize() > 50) {
                this.i = a(this.h, 0.95);
            }
            else {
                this.i = a(this.h, 0.9);
            }
            final FontMetrics fontMetrics;
            final int height = (fontMetrics = graphics.getFontMetrics(this.l)).getHeight();
            final int ascent = fontMetrics.getAscent();
            this.d = this.b.width + (this.c.width - fontMetrics.stringWidth(this.a)) / 2;
            this.e = this.b.height + this.c.height / 2 + (height - ascent);
            this.g = false;
        }
        graphics.setFont(this.l);
        graphics.setColor(this.i);
        graphics.drawString(this.a, this.d, this.e);
    }
}
