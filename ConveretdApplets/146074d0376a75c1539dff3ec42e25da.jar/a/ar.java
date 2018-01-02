// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;

public final class ar extends ap
{
    private int q;
    private Color q;
    private Color w;
    private Color e;
    private Color r;
    
    private ar(final int q) {
        this.q = q;
    }
    
    public ar(final int n, final Color color, final Color color2) {
        this(1, color.brighter(), color, color2, color2.brighter());
    }
    
    private ar(final int n, final Color q, final Color w, final Color r, final Color e) {
        this(n);
        this.q = q;
        this.w = w;
        this.r = r;
        this.e = e;
    }
    
    public final void q(final Component component, final Graphics graphics, final int n, int n2, int n3, int n4) {
        if (this.q == 0) {
            final int n5 = n2;
            final int n6 = n3;
            final int n7 = n4;
            n4 = n6;
            n3 = n5;
            n2 = n;
            final Color color = graphics.getColor();
            graphics.translate(n2, n3);
            graphics.setColor(this.q(component));
            graphics.drawLine(0, 0, 0, n7 - 2);
            graphics.drawLine(1, 0, n4 - 2, 0);
            graphics.setColor(this.w(component));
            graphics.drawLine(1, 1, 1, n7 - 3);
            graphics.drawLine(2, 1, n4 - 3, 1);
            graphics.setColor(this.r(component));
            graphics.drawLine(0, n7 - 1, n4 - 1, n7 - 1);
            graphics.drawLine(n4 - 1, 0, n4 - 1, n7 - 2);
            graphics.setColor(this.e(component));
            graphics.drawLine(1, n7 - 2, n4 - 2, n7 - 2);
            graphics.drawLine(n4 - 2, 1, n4 - 2, n7 - 3);
            graphics.translate(-n2, -n3);
            graphics.setColor(color);
            return;
        }
        if (this.q == 1) {
            final int n8 = n2;
            final int n9 = n3;
            final int n10 = n4;
            n4 = n9;
            n3 = n8;
            n2 = n;
            final Color color2 = graphics.getColor();
            graphics.translate(n2, n3);
            graphics.setColor(this.e(component));
            graphics.drawLine(0, 0, 0, n10 - 1);
            graphics.drawLine(1, 0, n4 - 1, 0);
            graphics.setColor(this.r(component));
            graphics.drawLine(1, 1, 1, n10 - 2);
            graphics.drawLine(2, 1, n4 - 2, 1);
            graphics.setColor(this.q(component));
            graphics.drawLine(1, n10 - 1, n4 - 1, n10 - 1);
            graphics.drawLine(n4 - 1, 1, n4 - 1, n10 - 2);
            graphics.setColor(this.w(component));
            graphics.drawLine(2, n10 - 2, n4 - 2, n10 - 2);
            graphics.drawLine(n4 - 2, 2, n4 - 2, n10 - 3);
            graphics.translate(-n2, -n3);
            graphics.setColor(color2);
        }
    }
    
    public final Insets q(final Component component) {
        return new Insets(2, 2, 2, 2);
    }
    
    private Color q(final Component component) {
        final Color q;
        if ((q = this.q) == null) {
            return component.getBackground().brighter().brighter();
        }
        return q;
    }
    
    private Color w(final Component component) {
        final Color w;
        if ((w = this.w) == null) {
            return component.getBackground().brighter();
        }
        return w;
    }
    
    private Color e(final Component component) {
        final Color e;
        if ((e = this.e) == null) {
            return component.getBackground().darker();
        }
        return e;
    }
    
    private Color r(final Component component) {
        final Color r;
        if ((r = this.r) == null) {
            return component.getBackground().darker().darker();
        }
        return r;
    }
}
