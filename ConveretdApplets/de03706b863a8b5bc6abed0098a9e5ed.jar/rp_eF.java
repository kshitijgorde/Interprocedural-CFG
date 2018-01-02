import java.awt.Rectangle;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class rp_eF extends rp_dC
{
    int a;
    int b;
    public double a;
    double b;
    double c;
    double d;
    protected int c;
    protected int d;
    
    rp_eF(final int h, final int a, final int b, final double n) {
        this.a = 0.0;
        this.b = 0.0;
        this.c = 1.0;
        this.d = 0.0;
        this.h = h;
        this.a = a;
        this.b = b;
        this.a(n);
    }
    
    void a(final double a) {
        this.a = a;
        while (this.a > 360.0) {
            this.a -= 360.0;
        }
        while (this.a < 0.0) {
            this.a += 360.0;
        }
        this.b = Math.sin(this.a * 0.017453292519943295);
        this.c = Math.cos(this.a * 0.017453292519943295);
    }
    
    public final Point a() {
        return new Point(this.a, this.b);
    }
    
    public Rectangle a(final int n) {
        final Point[] a = this.a();
        final Rectangle rectangle;
        (rectangle = new Rectangle()).setLocation(a[0]);
        rectangle.add(a[1]);
        rectangle.add(a[2]);
        rectangle.add(a[3]);
        return rectangle;
    }
    
    public final Point[] a() {
        final Point[] array = new Point[4];
        final int n = this.b() / 2 + 1;
        final int n2 = this.c() / 2 + 1;
        this.d(-n, -n2);
        array[0] = new Point(this.a + this.c, this.b + this.d);
        this.d(n, -n2);
        array[1] = new Point(this.a + this.c, this.b + this.d);
        this.d(n, n2);
        array[2] = new Point(this.a + this.c, this.b + this.d);
        this.d(-n, n2);
        array[3] = new Point(this.a + this.c, this.b + this.d);
        return array;
    }
    
    public void a(final int n, final int n2) {
        this.a += n;
        this.b += n2;
    }
    
    public final void b(final int n, final int n2) {
        this.a(n, n2);
    }
    
    public void a(final int n, final int n2, final double n3) {
        this.a += n3;
        while (this.a > 360.0) {
            this.a -= 360.0;
        }
        while (this.a < 0.0) {
            this.a += 360.0;
        }
        this.b = Math.sin(this.a * 0.017453292519943295);
        this.c = Math.cos(this.a * 0.017453292519943295);
        final int n4 = this.a - n;
        final int n5 = this.b - n2;
        this.c = (int)Math.round(n4 * Math.cos(n3 * 0.017453292519943295) - n5 * Math.sin(n3 * 0.017453292519943295));
        this.d = (int)Math.round(n5 * Math.cos(n3 * 0.017453292519943295) + n4 * Math.sin(n3 * 0.017453292519943295));
        if (n + this.c < 0 || n2 + this.d < 0) {
            System.out.println("!!! rotating out of paper");
            return;
        }
        this.a = n + this.c;
        this.b = n2 + this.d;
    }
    
    public void a(final int a, final int b, final double d, final boolean b2) {
        if (b2) {
            this.a = a;
            this.b = b;
            this.a(this.a);
            this.d = 0.0;
            return;
        }
        this.d = d;
        double n;
        for (n = this.a + d; n > 360.0; n -= 360.0) {}
        while (n < 0.0) {
            n += 360.0;
        }
        this.b = Math.sin(n * 0.017453292519943295);
        this.c = Math.cos(n * 0.017453292519943295);
        final int n2 = this.a - a;
        final int n3 = this.b - b;
        this.c = (int)Math.round(n2 * Math.cos(d * 0.017453292519943295) - n3 * Math.sin(d * 0.017453292519943295));
        this.d = (int)Math.round(n3 * Math.cos(d * 0.017453292519943295) + n2 * Math.sin(d * 0.017453292519943295));
        this.a = a + this.c;
        this.b = b + this.d;
    }
    
    protected final void d(final int n, final int n2) {
        this.c = (int)Math.round(n * this.c - n2 * this.b);
        this.d = (int)Math.round(n2 * this.c + n * this.b);
    }
}
