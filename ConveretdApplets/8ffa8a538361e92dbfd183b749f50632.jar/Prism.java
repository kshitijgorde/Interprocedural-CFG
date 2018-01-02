// 
// Decompiled by Procyon v0.5.30
// 

public class Prism extends PolyhedronElement
{
    PointElement C;
    PointElement D;
    
    Prism(final PolygonElement polygonElement, final PointElement c, final PointElement d) {
        super.dimension = 2;
        this.C = c;
        this.D = d;
        super.n = 2 + polygonElement.n;
        (super.P = new PolygonElement[super.n])[0] = polygonElement;
        super.P[1] = new PolygonElement(polygonElement.n);
        for (int i = 0; i < polygonElement.n; ++i) {
            super.P[1].V[i] = new PointElement();
            super.P[1].V[i].to(polygonElement.V[i]).plus(this.D).minus(this.C);
        }
        for (int j = 2; j < super.n; ++j) {
            super.P[j] = new PolygonElement(polygonElement.V[j - 2], polygonElement.V[(j - 1) % polygonElement.n], super.P[1].V[(j - 1) % polygonElement.n], super.P[1].V[j - 2]);
        }
    }
    
    protected void translate(final double n, final double n2) {
        for (int i = 0; i < super.P[1].n; ++i) {
            super.P[1].V[i].translate(n, n2);
        }
    }
    
    protected void rotate(final PointElement pointElement, final double n, final double n2) {
        for (int i = 0; i < super.P[1].n; ++i) {
            super.P[1].V[i].rotate(pointElement, n, n2);
        }
    }
    
    protected void update() {
        for (int i = 0; i < super.P[1].n; ++i) {
            super.P[1].V[i].to(super.P[0].V[i]).plus(this.D).minus(this.C);
        }
    }
}
