import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class j extends d
{
    static final Random n;
    static final Color m;
    double f;
    double k;
    double e;
    
    public void a() {
        final double n = Math.abs(j.n.nextInt()) % 314 / 100.0;
        this.k = this.f * Math.cos(n);
        this.e = this.f * Math.sin(n);
    }
    
    public void c() {
        final double sqrt = Math.sqrt(this.k * this.k + this.e * this.e);
        final double n = this.k / sqrt;
        final double n2 = this.e / sqrt;
        this.k = n * this.f;
        this.e = n2 * this.f;
    }
    
    public void a(final Component component, final Graphics graphics) {
        graphics.setColor(Color.blue);
        final double sqrt = Math.sqrt(this.k * this.k + this.e * this.e);
        final double n = this.k / sqrt;
        final double n2 = this.e / sqrt;
        graphics.drawLine(this.d, this.j, (int)(this.d + n * 15.0 + 0.5), (int)(this.j + n2 * 15.0 + 0.5));
        graphics.setColor(Color.green);
        graphics.drawLine(this.d, this.j, (int)(this.d - n2 * 10.0 + 0.5), (int)(this.j + n * 10.0 + 0.5));
        graphics.drawLine(this.d, this.j, (int)(this.d + n2 * 10.0 + 0.5), (int)(this.j - n * 10.0 + 0.5));
        graphics.setColor(j.m);
        graphics.drawRect(this.d - 50, this.j - 50, 100, 100);
        super.a(component, graphics);
    }
    
    public j(final int d, final int j, final double f) {
        this.d = d;
        this.j = j;
        this.f = f;
        this.a();
        this.b(20, 20);
    }
    
    static {
        n = new Random(System.currentTimeMillis());
        m = new Color(0, 0, 151);
    }
}
