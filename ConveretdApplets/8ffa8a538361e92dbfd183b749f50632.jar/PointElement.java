import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class PointElement extends Element
{
    double x;
    double y;
    double z;
    PlaneElement AP;
    
    PointElement() {
    }
    
    PointElement(final PlaneElement ap) {
        this.AP = ap;
    }
    
    PointElement(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    protected boolean defined() {
        return !Double.isNaN(this.x) && !Double.isNaN(this.y) && !Double.isNaN(this.z);
    }
    
    public String toString() {
        return "[" + super.name + "=(" + this.x + "," + this.y + "," + this.z + ")]";
    }
    
    PointElement to(final PointElement pointElement) {
        this.x = pointElement.x;
        this.y = pointElement.y;
        this.z = pointElement.z;
        return this;
    }
    
    PointElement plus(final PointElement pointElement) {
        this.x += pointElement.x;
        this.y += pointElement.y;
        this.z += pointElement.z;
        return this;
    }
    
    PointElement minus(final PointElement pointElement) {
        this.x -= pointElement.x;
        this.y -= pointElement.y;
        this.z -= pointElement.z;
        return this;
    }
    
    PointElement times(final double n) {
        this.x *= n;
        this.y *= n;
        this.z *= n;
        return this;
    }
    
    static PointElement sum(final PointElement pointElement, final PointElement pointElement2) {
        return new PointElement(pointElement.x + pointElement2.x, pointElement.y + pointElement2.y, pointElement.z + pointElement2.z);
    }
    
    static PointElement difference(final PointElement pointElement, final PointElement pointElement2) {
        return new PointElement(pointElement.x - pointElement2.x, pointElement.y - pointElement2.y, pointElement.z - pointElement2.z);
    }
    
    static PointElement product(final double n, final PointElement pointElement) {
        return new PointElement(n * pointElement.x, n * pointElement.y, n * pointElement.z);
    }
    
    public static double dot(final PointElement pointElement, final PointElement pointElement2) {
        return pointElement.x * pointElement2.x + pointElement.y * pointElement2.y + pointElement.z * pointElement2.z;
    }
    
    public double length2() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }
    
    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }
    
    public double distance2(final PointElement pointElement) {
        return (this.x - pointElement.x) * (this.x - pointElement.x) + (this.y - pointElement.y) * (this.y - pointElement.y) + (this.z - pointElement.z) * (this.z - pointElement.z);
    }
    
    public double distance(final PointElement pointElement) {
        return Math.sqrt((this.x - pointElement.x) * (this.x - pointElement.x) + (this.y - pointElement.y) * (this.y - pointElement.y) + (this.z - pointElement.z) * (this.z - pointElement.z));
    }
    
    public PointElement toCross(final PointElement pointElement, final PointElement pointElement2) {
        this.x = pointElement.y * pointElement2.z - pointElement.z * pointElement2.y;
        this.y = pointElement.z * pointElement2.x - pointElement.x * pointElement2.z;
        this.z = pointElement.x * pointElement2.y - pointElement.y * pointElement2.x;
        return this;
    }
    
    public static PointElement cross(final PointElement pointElement, final PointElement pointElement2) {
        return new PointElement(pointElement.y * pointElement2.z - pointElement.z * pointElement2.y, pointElement.z * pointElement2.x - pointElement.x * pointElement2.z, pointElement.x * pointElement2.y - pointElement.y * pointElement2.x);
    }
    
    public static double triple(final PointElement pointElement, final PointElement pointElement2, final PointElement pointElement3) {
        return pointElement.x * (pointElement2.y * pointElement3.z - pointElement2.z * pointElement3.y) + pointElement2.x * (pointElement3.y * pointElement.z - pointElement3.z * pointElement.y) + pointElement3.x * (pointElement.y * pointElement2.z - pointElement.z * pointElement2.y);
    }
    
    PointElement toLine(final PointElement pointElement, final PointElement pointElement2, final boolean b) {
        final PointElement difference = difference(pointElement2, pointElement);
        this.minus(pointElement);
        double n = dot(difference, this) / difference.length2();
        if (b) {
            if (n < 0.0) {
                n = 0.0;
            }
            else if (n > 1.0) {
                n = 1.0;
            }
        }
        difference.times(n);
        return this.to(difference).plus(pointElement);
    }
    
    PointElement toPlane(final PlaneElement planeElement) {
        if (planeElement.isScreen) {
            this.z = 0.0;
        }
        else {
            this.minus(planeElement.A);
            this.to(planeElement.S).times(dot(this, planeElement.S)).plus(product(dot(this, planeElement.T), planeElement.T)).plus(planeElement.A);
        }
        return this;
    }
    
    PointElement uptoPlane(final PlaneElement planeElement) {
        if (planeElement.isScreen) {
            this.z = 0.0;
        }
        else {
            this.minus(planeElement.A);
            final double n = planeElement.S.x * planeElement.T.y - planeElement.S.y * planeElement.T.x;
            this.to(planeElement.S).times((this.x * planeElement.T.y - this.y * planeElement.T.x) / n).plus(product((this.y * planeElement.S.x - this.x * planeElement.S.y) / n, planeElement.T)).plus(planeElement.A);
        }
        return this;
    }
    
    PointElement toCircumcenter(final PointElement pointElement, final PointElement pointElement2, final PointElement pointElement3) {
        if (pointElement.z == 0.0 && pointElement2.z == 0.0 && pointElement3.z == 0.0) {
            final double n = ((pointElement.x - pointElement2.x) * (pointElement.x + pointElement2.x) + (pointElement.y - pointElement2.y) * (pointElement.y + pointElement2.y)) / 2.0;
            final double n2 = ((pointElement2.x - pointElement3.x) * (pointElement2.x + pointElement3.x) + (pointElement2.y - pointElement3.y) * (pointElement2.y + pointElement3.y)) / 2.0;
            final double n3 = (pointElement.x - pointElement2.x) * (pointElement2.y - pointElement3.y) - (pointElement2.x - pointElement3.x) * (pointElement.y - pointElement2.y);
            this.x = (n * (pointElement2.y - pointElement3.y) - n2 * (pointElement.y - pointElement2.y)) / n3;
            this.y = (n2 * (pointElement.x - pointElement2.x) - n * (pointElement2.x - pointElement3.x)) / n3;
            this.z = 0.0;
        }
        else {
            final PointElement difference = difference(pointElement2, pointElement);
            final PointElement difference2 = difference(pointElement3, pointElement);
            final double dot = dot(difference, difference2);
            final double length2 = difference.length2();
            final double length3 = difference2.length2();
            final double n4 = 2.0 * (length2 * length3 - dot * dot);
            this.to(pointElement).plus(difference.times(length3 * (length2 - dot) / n4)).plus(difference2.times(length2 * (length3 - dot) / n4));
        }
        return this;
    }
    
    PointElement toCircle(final CircleElement circleElement) {
        if (circleElement.AP.isScreen) {
            final double n = circleElement.radius() / this.distance(circleElement.Center);
            this.x = circleElement.Center.x + n * (this.x - circleElement.Center.x);
            this.y = circleElement.Center.y + n * (this.y - circleElement.Center.y);
            this.z = 0.0;
        }
        else {
            this.toPlane(circleElement.AP);
            this.toSphere(circleElement.Center, circleElement.radius());
        }
        return this;
    }
    
    PointElement toSphere(final PointElement pointElement, final double n) {
        final double n2 = n / this.distance(pointElement);
        this.x = pointElement.x + n2 * (this.x - pointElement.x);
        this.y = pointElement.y + n2 * (this.y - pointElement.y);
        this.z = pointElement.z + n2 * (this.z - pointElement.z);
        return this;
    }
    
    public static double area(final PointElement pointElement, final PointElement pointElement2, final PointElement pointElement3) {
        return cross(difference(pointElement2, pointElement), difference(pointElement3, pointElement)).length() / 2.0;
    }
    
    public double angle(final PointElement pointElement, final PointElement pointElement2, final PlaneElement planeElement) {
        final double n = pointElement.x - this.x;
        final double n2 = pointElement2.x - this.x;
        final double n3 = pointElement.y - this.y;
        final double n4 = pointElement2.y - this.y;
        if (planeElement.isScreen) {
            return Math.atan2(n * n4 - n3 * n2, n * n2 + n3 * n4);
        }
        final double n5 = pointElement.z - this.z;
        final double n6 = pointElement2.z - this.z;
        final double n7 = n * planeElement.S.x + n3 * planeElement.S.y + n5 * planeElement.S.z;
        final double n8 = n * planeElement.T.x + n3 * planeElement.T.y + n5 * planeElement.T.z;
        final double n9 = n2 * planeElement.S.x + n4 * planeElement.S.y + n6 * planeElement.S.z;
        final double n10 = n2 * planeElement.T.x + n4 * planeElement.T.y + n6 * planeElement.T.z;
        return Math.atan2(n7 * n10 - n8 * n9, n7 * n9 + n8 * n10);
    }
    
    protected void translate(final double n, final double n2) {
        this.x += n;
        this.y += n2;
    }
    
    PointElement toIntersection(final PointElement pointElement, final PointElement pointElement2, final PointElement pointElement3, final PointElement pointElement4, final PlaneElement planeElement) {
        if (planeElement.isScreen) {
            final double n = pointElement.x * pointElement2.y - pointElement.y * pointElement2.x;
            final double n2 = pointElement3.x * pointElement4.y - pointElement3.y * pointElement4.x;
            final double n3 = (pointElement2.y - pointElement.y) * (pointElement3.x - pointElement4.x) - (pointElement.x - pointElement2.x) * (pointElement4.y - pointElement3.y);
            this.x = (n * (pointElement3.x - pointElement4.x) - n2 * (pointElement.x - pointElement2.x)) / n3;
            this.y = (n2 * (pointElement2.y - pointElement.y) - n * (pointElement4.y - pointElement3.y)) / n3;
        }
        else {
            final PointElement difference = difference(pointElement, planeElement.A);
            final PointElement difference2 = difference(pointElement2, planeElement.A);
            final PointElement difference3 = difference(pointElement3, planeElement.A);
            final PointElement difference4 = difference(pointElement4, planeElement.A);
            final double dot = dot(difference, planeElement.S);
            final double dot2 = dot(difference, planeElement.T);
            final double dot3 = dot(difference2, planeElement.S);
            final double dot4 = dot(difference2, planeElement.T);
            final double dot5 = dot(difference3, planeElement.S);
            final double dot6 = dot(difference3, planeElement.T);
            final double dot7 = dot(difference4, planeElement.S);
            final double dot8 = dot(difference4, planeElement.T);
            final double n4 = dot * dot4 - dot2 * dot3;
            final double n5 = dot5 * dot8 - dot6 * dot7;
            final double n6 = (dot4 - dot2) * (dot5 - dot7) - (dot - dot3) * (dot8 - dot6);
            this.to(planeElement.S).times((n4 * (dot5 - dot7) - n5 * (dot - dot3)) / n6).plus(product((n5 * (dot4 - dot2) - n4 * (dot8 - dot6)) / n6, planeElement.T)).plus(planeElement.A);
        }
        return this;
    }
    
    PointElement toIntersectionPL(final PlaneElement planeElement, final PointElement pointElement, final PointElement pointElement2) {
        this.to(pointElement2).minus(pointElement);
        return this.times(-triple(planeElement.S, planeElement.T, difference(pointElement, planeElement.A)) / triple(planeElement.S, planeElement.T, this)).plus(pointElement);
    }
    
    PointElement toInvertPoint(final PointElement pointElement, final CircleElement circleElement) {
        return this.to(pointElement).minus(circleElement.Center).times(circleElement.radius2() / pointElement.distance2(circleElement.Center)).plus(circleElement.Center);
    }
    
    PointElement toSimilar(final PointElement pointElement, final PointElement pointElement2, final PlaneElement planeElement, final PointElement pointElement3, final PointElement pointElement4, final PointElement pointElement5, final PlaneElement planeElement2) {
        final double angle = pointElement3.angle(pointElement4, pointElement5, planeElement2);
        final double cos = Math.cos(angle);
        final double sin = Math.sin(angle);
        final double n = pointElement3.distance(pointElement5) / pointElement3.distance(pointElement4);
        if (planeElement.isScreen) {
            this.x = pointElement2.x;
            this.y = pointElement2.y;
            this.rotate(pointElement, cos, sin, planeElement);
            this.x = pointElement.x + n * (this.x - pointElement.x);
            this.y = pointElement.y + n * (this.y - pointElement.y);
            this.z = 0.0;
        }
        else {
            final PointElement difference = difference(pointElement2, pointElement);
            final double dot = dot(difference, planeElement.S);
            final double dot2 = dot(difference, planeElement.T);
            final double n2 = n * (cos * dot - sin * dot2);
            final double n3 = n * (sin * dot + cos * dot2);
            this.x = n2 * planeElement.S.x + n3 * planeElement.T.x + pointElement.x;
            this.y = n2 * planeElement.S.y + n3 * planeElement.T.y + pointElement.y;
            this.z = n2 * planeElement.S.z + n3 * planeElement.T.z + pointElement.z;
        }
        return this;
    }
    
    protected void rotate(final PointElement pointElement, final double n, final double n2) {
        this.rotate(pointElement, n, n2, pointElement.AP);
    }
    
    protected void rotate(final PointElement pointElement, final double n, final double n2, final PlaneElement planeElement) {
        if (this == pointElement) {
            return;
        }
        if (planeElement.isScreen) {
            final double n3 = this.x - pointElement.x;
            final double n4 = this.y - pointElement.y;
            this.x = pointElement.x + n * n3 - n2 * n4;
            this.y = pointElement.y + n2 * n3 + n * n4;
            return;
        }
        this.minus(pointElement);
        final PointElement s = planeElement.S;
        final PointElement t = planeElement.T;
        final PointElement u = planeElement.U;
        final double dot = dot(this, s);
        final double dot2 = dot(this, t);
        final double dot3 = dot(this, u);
        final double n5 = n * dot - n2 * dot2;
        final double n6 = n2 * dot + n * dot2;
        this.x = pointElement.x + n5 * s.x + n6 * t.x + dot3 * u.x;
        this.y = pointElement.y + n5 * s.y + n6 * t.y + dot3 * u.y;
        this.z = pointElement.z + n5 * s.z + n6 * t.z + dot3 * u.z;
    }
    
    protected void drawName(final Graphics graphics, final Dimension dimension) {
        if (super.nameColor != null && super.name != null && this.defined()) {
            this.drawString((int)Math.round(this.x), (int)Math.round(this.y), graphics, dimension);
        }
    }
    
    protected void drawVertex(final Graphics graphics) {
        this.drawVertex(graphics, super.vertexColor);
    }
    
    public void drawVertex(final Graphics graphics, final Color color) {
        if (color != null && this.defined()) {
            graphics.setColor(color);
            graphics.fillOval((int)Math.round(this.x) - 2, (int)Math.round(this.y) - 2, 4, 4);
        }
    }
}
