// 
// Decompiled by Procyon v0.5.30
// 

public class RegularPolygon extends PolygonElement
{
    double cos;
    double sin;
    PlaneElement P;
    
    RegularPolygon(final PointElement pointElement, final PointElement pointElement2, final PlaneElement p4, final int n) {
        super.dimension = 2;
        super.n = n;
        this.P = p4;
        super.V = new PointElement[super.n];
        final double n2 = 3.141592653589793 * (super.n - 2.0) / super.n;
        this.cos = Math.cos(n2);
        this.sin = Math.sin(n2);
        super.V[0] = pointElement;
        super.V[1] = pointElement2;
        for (int i = 2; i < super.n; ++i) {
            super.V[i] = new PointElement(this.P);
        }
    }
    
    RegularPolygon(final PointElement pointElement, final PointElement pointElement2, final PlaneElement p5, final int n, final int n2) {
        super.dimension = 2;
        super.n = n;
        this.P = p5;
        super.V = new PointElement[super.n];
        final double n3 = 3.141592653589793 * n2 * (super.n - 2.0) / super.n;
        this.cos = Math.cos(n3);
        this.sin = Math.sin(n3);
        super.V[0] = pointElement;
        super.V[1] = pointElement2;
        for (int i = 2; i < super.n; ++i) {
            super.V[i] = new PointElement(this.P);
        }
    }
    
    protected void translate(final double n, final double n2) {
        for (int i = 2; i < super.n; ++i) {
            super.V[i].translate(n, n2);
        }
    }
    
    protected void rotate(final PointElement pointElement, final double n, final double n2) {
        for (int i = 2; i < super.n; ++i) {
            super.V[i].rotate(pointElement, n, n2, this.P);
        }
    }
    
    protected void update() {
        for (int i = 2; i < super.n; ++i) {
            super.V[i].to(super.V[i - 2]).rotate(super.V[i - 1], this.cos, this.sin, this.P);
        }
    }
}
