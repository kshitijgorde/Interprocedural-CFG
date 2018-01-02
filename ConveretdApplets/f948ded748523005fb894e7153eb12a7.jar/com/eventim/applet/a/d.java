// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.a;

import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Font;

public final class d extends a
{
    private int[] f;
    private int[] g;
    private Font h;
    private String[] i;
    
    public d(final String[] i, final Font h, final int[] f, final int[] g, final Color color, final Color color2) {
        super(null, null, 11, color, color2);
        this.i = i;
        this.h = h;
        this.f = f;
        this.g = g;
    }
    
    public final void a(final Graphics2D graphics2D) {
        final FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
        graphics2D.setColor(this.b);
        for (int i = 0; i < this.i.length; ++i) {
            final TextLayout textLayout = new TextLayout(this.i[i], this.h, fontRenderContext);
            textLayout.draw(graphics2D, this.f[i] + 3, this.g[i] + textLayout.getAscent() - 1.0f);
        }
    }
}
