import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_cT
{
    public double a;
    public double b;
    private double c;
    private double d;
    private double e;
    private Point a;
    
    public rp_cT(final Point a, final Point point) {
        this.a = a;
        this.a = a.x - point.x;
        this.b = a.y - point.y;
        this.c = this.a * this.a + this.b * this.b;
        this.d = this.a / this.c;
        this.e = this.b / this.c;
    }
    
    public final boolean a() {
        return !Double.isNaN(this.d) && !Double.isNaN(this.e);
    }
    
    public final double a(final Point point) {
        return this.d * (this.a.x - point.x) + this.e * (this.a.y - point.y);
    }
    
    public final Point a(final double n) {
        return new Point((int)Math.round(this.a.x - n * this.a), (int)Math.round(this.a.y - n * this.b));
    }
    
    public final double a(final Point point, final double n) {
        final double n2 = this.a.x - n * this.a - point.x;
        final double n3 = this.a.y - n * this.b - point.y;
        return Math.sqrt(n2 * n2 + n3 * n3);
    }
}
