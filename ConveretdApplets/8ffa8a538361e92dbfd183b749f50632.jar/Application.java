// 
// Decompiled by Procyon v0.5.30
// 

public class Application extends PolygonElement
{
    PolygonElement P;
    PointElement C;
    
    Application(final PolygonElement p4, final PointElement pointElement, final PointElement pointElement2, final PointElement c) {
        super.dimension = 2;
        super.n = 4;
        this.P = p4;
        this.C = c;
        (super.V = new PointElement[4])[0] = pointElement;
        super.V[1] = pointElement2;
        super.V[2] = new PointElement();
        super.V[3] = new PointElement();
        if (pointElement.AP == pointElement2.AP && pointElement.AP == this.C.AP) {
            super.V[2].AP = pointElement.AP;
            super.V[3].AP = pointElement.AP;
        }
    }
    
    protected void translate(final double n, final double n2) {
        super.V[2].translate(n, n2);
        super.V[3].translate(n, n2);
    }
    
    protected void rotate(final PointElement pointElement, final double n, final double n2) {
        super.V[2].rotate(pointElement, n, n2);
        super.V[3].rotate(pointElement, n, n2);
    }
    
    protected void update() {
        final double abs = Math.abs(this.P.area() / (2.0 * PointElement.area(super.V[0], super.V[1], this.C)));
        super.V[3].x = super.V[0].x + abs * (this.C.x - super.V[0].x);
        super.V[3].y = super.V[0].y + abs * (this.C.y - super.V[0].y);
        super.V[3].z = super.V[0].z + abs * (this.C.z - super.V[0].z);
        super.V[2].x = super.V[1].x + super.V[3].x - super.V[0].x;
        super.V[2].y = super.V[1].y + super.V[3].y - super.V[0].y;
        super.V[2].z = super.V[1].z + super.V[3].z - super.V[0].z;
    }
}
