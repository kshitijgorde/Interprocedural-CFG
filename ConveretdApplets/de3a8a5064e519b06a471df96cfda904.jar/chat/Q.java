// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;

public final class Q extends V
{
    private int a;
    private Color a;
    private Color b;
    private Color c;
    private Color d;
    
    private Q() {
        this.a = 1;
    }
    
    public Q(final Color color, final Color color2) {
        this(color.brighter(), color, color2, color2.brighter());
    }
    
    private Q(final Color a, final Color b, final Color d, final Color c) {
        this();
        this.a = a;
        this.b = b;
        this.d = d;
        this.c = c;
    }
    
    public final void a(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (this.a == 0) {
            final Color color = graphics.getColor();
            graphics.translate(n, n2);
            graphics.setColor(this.a(component));
            graphics.drawLine(0, 0, 0, n4 - 2);
            graphics.drawLine(1, 0, n3 - 2, 0);
            graphics.setColor(this.b(component));
            graphics.drawLine(1, 1, 1, n4 - 3);
            graphics.drawLine(2, 1, n3 - 3, 1);
            graphics.setColor(this.d(component));
            graphics.drawLine(0, n4 - 1, n3 - 1, n4 - 1);
            graphics.drawLine(n3 - 1, 0, n3 - 1, n4 - 2);
            graphics.setColor(this.c(component));
            graphics.drawLine(1, n4 - 2, n3 - 2, n4 - 2);
            graphics.drawLine(n3 - 2, 1, n3 - 2, n4 - 3);
            graphics.translate(-n, -n2);
            graphics.setColor(color);
            return;
        }
        if (this.a == 1) {
            final Color color2 = graphics.getColor();
            graphics.translate(n, n2);
            graphics.setColor(this.c(component));
            graphics.drawLine(0, 0, 0, n4 - 1);
            graphics.drawLine(1, 0, n3 - 1, 0);
            graphics.setColor(this.d(component));
            graphics.drawLine(1, 1, 1, n4 - 2);
            graphics.drawLine(2, 1, n3 - 2, 1);
            graphics.setColor(this.a(component));
            graphics.drawLine(1, n4 - 1, n3 - 1, n4 - 1);
            graphics.drawLine(n3 - 1, 1, n3 - 1, n4 - 2);
            graphics.setColor(this.b(component));
            graphics.drawLine(2, n4 - 2, n3 - 2, n4 - 2);
            graphics.drawLine(n3 - 2, 2, n3 - 2, n4 - 3);
            graphics.translate(-n, -n2);
            graphics.setColor(color2);
        }
    }
    
    public final Insets a(final Component component) {
        return new Insets(2, 2, 2, 2);
    }
    
    private Color a(final Component component) {
        final Color a;
        if ((a = this.a) != null) {
            return a;
        }
        return component.getBackground().brighter().brighter();
    }
    
    private Color b(final Component component) {
        final Color b;
        if ((b = this.b) != null) {
            return b;
        }
        return component.getBackground().brighter();
    }
    
    private Color c(final Component component) {
        final Color c;
        if ((c = this.c) != null) {
            return c;
        }
        return component.getBackground().darker();
    }
    
    private Color d(final Component component) {
        final Color d;
        if ((d = this.d) != null) {
            return d;
        }
        return component.getBackground().darker().darker();
    }
}
