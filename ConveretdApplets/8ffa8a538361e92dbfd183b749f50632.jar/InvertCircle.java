// 
// Decompiled by Procyon v0.5.30
// 

public class InvertCircle extends CircleElement
{
    CircleElement C;
    CircleElement D;
    
    InvertCircle(final CircleElement c, final CircleElement d) {
        super.dimension = 2;
        super.AP = this.C.AP;
        super.Center = new PointElement(super.AP);
        super.A = super.Center;
        super.B = new PointElement(super.AP);
        this.C = c;
        this.D = d;
    }
    
    protected void update() {
        final double distance2 = this.C.Center.distance2(this.D.Center);
        final double radius2 = this.C.radius2();
        super.Center.to(this.C.Center).minus(this.D.Center).times(this.D.radius2() / (distance2 - radius2)).plus(this.D.Center);
        super.B.to(super.Center).minus(this.D.Center).times(1.0 + Math.sqrt(radius2 / distance2)).plus(this.D.Center);
    }
    
    protected void translate(final double n, final double n2) {
        super.Center.translate(n, n2);
        super.B.translate(n, n2);
    }
    
    protected void rotate(final PointElement pointElement, final double n, final double n2) {
        super.Center.rotate(pointElement, n, n2);
        super.B.rotate(pointElement, n, n2);
    }
}
