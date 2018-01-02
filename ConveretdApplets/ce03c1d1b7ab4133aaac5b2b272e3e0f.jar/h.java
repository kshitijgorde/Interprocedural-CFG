import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class h extends d
{
    double f;
    double n;
    double o;
    double p;
    double k;
    double m;
    double e;
    
    public void a(final Component component, final Graphics graphics) {
        graphics.setColor(Color.blue);
        graphics.fillOval(this.d - this.b / 2, this.j - this.i / 2, this.b, this.i);
        graphics.setColor(Color.black);
        graphics.drawOval(this.d - this.b / 2, this.j - this.i / 2, this.b, this.i);
        super.a(component, graphics);
    }
    
    private final void a() {
        this.f = 1.0;
    }
    
    public h(final int d, final int j) {
        this.a();
        this.d = d;
        this.j = j;
        this.b(7, 7);
    }
}
