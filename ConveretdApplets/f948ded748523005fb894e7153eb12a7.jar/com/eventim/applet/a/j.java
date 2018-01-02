// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.a;

import java.awt.Paint;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.Shape;

public final class j extends a
{
    j(final Shape shape, final Stroke stroke, final int n, final Color color, final Color color2) {
        super(shape, stroke, n, color, color2);
    }
    
    public final void a(final Graphics2D graphics2D) {
        graphics2D.setStroke(this.e);
        graphics2D.setPaint(this.a);
        graphics2D.fill(this.d);
        graphics2D.setPaint(this.b);
        graphics2D.draw(this.d);
    }
}
