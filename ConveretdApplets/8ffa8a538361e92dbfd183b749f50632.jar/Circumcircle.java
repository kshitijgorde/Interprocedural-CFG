// 
// Decompiled by Procyon v0.5.30
// 

public class Circumcircle extends CircleElement
{
    PointElement C;
    PointElement D;
    
    Circumcircle(final PointElement b, final PointElement c, final PointElement d, final PlaneElement ap) {
        super.dimension = 2;
        super.B = b;
        this.C = c;
        this.D = d;
        super.AP = ap;
        super.Center = new PointElement(super.AP);
        super.A = super.Center;
    }
    
    protected void update() {
        super.Center.toCircumcenter(super.B, this.C, this.D);
    }
    
    protected void translate(final double n, final double n2) {
        super.Center.translate(n, n2);
    }
    
    protected void rotate(final PointElement pointElement, final double n, final double n2) {
        super.Center.rotate(pointElement, n, n2);
    }
}
