import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

class Qtil
{
    private String name;
    private Dimension area;
    private Dimension sz;
    private Point loc;
    private Color base;
    private Color alt;
    private Color[] grad;
    private boolean brightup;
    private int current;
    private int fr;
    
    Qtil(final String name, final Dimension area, final Dimension sz, final Color base, final Color alt, final int fr) {
        this.name = name;
        this.area = area;
        this.sz = sz;
        this.base = base;
        this.alt = alt;
        this.fr = fr;
        this.grad = new Color[fr];
        this.init();
    }
    
    private void init() {
        this.current = 0;
        if (this.fr > 1) {
            final int n = (this.alt != null) ? this.alt.getRed() : ((int)(Math.random() * 256.0));
            final int n2 = (this.alt != null) ? this.alt.getGreen() : ((int)(Math.random() * 256.0));
            final int n3 = (this.alt != null) ? this.alt.getBlue() : ((int)(Math.random() * 256.0));
            for (int i = 0; i < this.grad.length; ++i) {
                this.grad[i] = new Color((n * i + this.base.getRed() * (this.fr - i)) / this.fr, (n2 * i + this.base.getGreen() * (this.fr - i)) / this.fr, (n3 * i + this.base.getBlue() * (this.fr - i)) / this.fr);
            }
            this.brightup = true;
            this.loc = new Point((this.area.width - this.sz.width) / 2, (this.area.height + this.sz.height) / 2);
        }
    }
    
    void draw(final Graphics graphics) {
        graphics.setColor(this.grad[this.current]);
        graphics.drawString(this.name, this.loc.x, this.loc.y);
        if (this.fr > 1) {
            if (this.brightup) {
                if (++this.current >= this.fr - 1) {
                    this.brightup = false;
                }
            }
            else if (--this.current <= 0) {
                this.init();
            }
        }
    }
}
