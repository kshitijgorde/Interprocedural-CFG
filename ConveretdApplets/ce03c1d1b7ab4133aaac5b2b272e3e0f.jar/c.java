import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class c extends d
{
    double f;
    Image k;
    Image e;
    
    public void a(final Component component, final Graphics graphics) {
        graphics.drawImage(this.k, this.d - this.k.getWidth(component) / 2, this.j - this.k.getHeight(component) / 2, component);
        final int width = this.k.getWidth(component);
        this.k.getHeight(component);
        graphics.drawImage(this.e, this.d - width / 2 + 3, this.j - 31, component);
        super.a(component, graphics);
    }
    
    public void a(final h h, final b b) {
        final double n = h.n - this.d;
        final double n2 = h.o - this.j;
        final double sqrt = Math.sqrt(n * n + n2 * n2);
        final double n3 = sqrt * sqrt * sqrt;
        final double n4 = this.f * c.c * c.g * h.f * n / n3;
        final double n5 = this.f * c.c * c.g * h.f * n2 / n3;
        h.p += n4;
        h.k += n5;
    }
    
    private final void a() {
        this.f = 0.0;
    }
    
    public c(final int d, final int j, final int n, final Image e, final Image k) {
        this.a();
        this.d = d;
        this.j = j;
        this.k = k;
        this.e = e;
        this.f = ((n < 0) ? -1 : 1);
        this.b(55, 60);
    }
}
