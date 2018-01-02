import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class x extends Panel
{
    Image a;
    Image b;
    Image c;
    Image d;
    Graphics e;
    Graphics f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    Color o;
    p p;
    
    x(final p p12, final Image a, final Image b, final int g, final int h, final int i, final int j, final int k, final int l, final int m, final int n, final Color o) {
        this.c = null;
        this.d = null;
        this.p = p12;
        this.a = a;
        this.b = b;
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
        this.m = m;
        this.n = n;
        this.setBackground(this.o = o);
    }
    
    public void paint(final Graphics graphics) {
        if (this.a != null) {
            if (this.g == 0) {
                return;
            }
            if (this.c == null) {
                this.c = this.createImage(this.g, this.h);
                this.e = this.c.getGraphics();
            }
            this.e.setColor(this.o);
            this.e.fillRect(0, 0, this.g, this.h);
            this.e.drawImage(this.a, 0, 0, this.g, this.h, this);
            graphics.drawImage(this.c, this.k, this.l, this);
        }
        if (this.b != null) {
            if (this.i == 0) {
                return;
            }
            if (this.d == null) {
                this.d = this.createImage(this.i, this.j);
                this.f = this.d.getGraphics();
            }
            this.f.setColor(this.o);
            this.f.fillRect(0, 0, this.i, this.j);
            this.f.drawImage(this.b, 0, 0, this.i, this.j, this);
            graphics.drawImage(this.d, this.m, this.n, this);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean action(final Event event, final Object o) {
        this.p.action(event, o);
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        return super.handleEvent(event);
    }
}
