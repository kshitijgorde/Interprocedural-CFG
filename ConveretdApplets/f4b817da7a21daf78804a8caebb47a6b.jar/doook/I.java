// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;

public class I extends R
{
    protected int e;
    protected Color b;
    protected Color c;
    protected Color d;
    protected Color e;
    
    public I(final int e) {
        this.e = e;
    }
    
    public I(final int n, final Color color, final Color color2) {
        this(n, color.brighter(), color, color2, color2.brighter());
    }
    
    public I(final int n, final Color b, final Color c, final Color e, final Color d) {
        this(n);
        this.b = b;
        this.c = c;
        this.e = e;
        this.d = d;
    }
    
    public void a(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (this.e == 0) {
            this.b(component, graphics, n, n2, n3, n4);
        }
        else if (this.e == 1) {
            this.c(component, graphics, n, n2, n3, n4);
        }
    }
    
    public Insets a(final Component component) {
        return new Insets(2, 2, 2, 2);
    }
    
    public Color a(final Component component) {
        final Color a = this.a();
        return (a != null) ? a : component.getBackground().brighter().brighter();
    }
    
    public Color b(final Component component) {
        final Color b = this.b();
        return (b != null) ? b : component.getBackground().brighter();
    }
    
    public Color c(final Component component) {
        final Color c = this.c();
        return (c != null) ? c : component.getBackground().darker();
    }
    
    public Color d(final Component component) {
        final Color d = this.d();
        return (d != null) ? d : component.getBackground().darker().darker();
    }
    
    public Color a() {
        return this.b;
    }
    
    public Color b() {
        return this.c;
    }
    
    public Color c() {
        return this.d;
    }
    
    public Color d() {
        return this.e;
    }
    
    protected void b(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
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
    }
    
    protected void c(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Color color = graphics.getColor();
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
        graphics.setColor(color);
    }
}
