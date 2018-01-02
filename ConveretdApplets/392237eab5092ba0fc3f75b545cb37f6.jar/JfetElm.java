import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

class JfetElm extends MosfetElm
{
    Polygon gatePoly;
    Polygon arrowPoly;
    Point gatePt;
    
    JfetElm(final int n, final int n2, final boolean b) {
        super(n, n2, b);
        this.noDiagonal = true;
    }
    
    public JfetElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
        this.noDiagonal = true;
    }
    
    void draw(final Graphics graphics) {
        this.setBbox(this.point1, this.point2, 16.0);
        this.setVoltageColor(graphics, this.volts[1]);
        CircuitElm.drawThickLine(graphics, this.src[0], this.src[1]);
        CircuitElm.drawThickLine(graphics, this.src[1], this.src[2]);
        this.setVoltageColor(graphics, this.volts[2]);
        CircuitElm.drawThickLine(graphics, this.drn[0], this.drn[1]);
        CircuitElm.drawThickLine(graphics, this.drn[1], this.drn[2]);
        this.setVoltageColor(graphics, this.volts[0]);
        CircuitElm.drawThickLine(graphics, this.point1, this.gatePt);
        graphics.fillPolygon(this.arrowPoly);
        this.setPowerColor(graphics, true);
        graphics.fillPolygon(this.gatePoly);
        this.curcount = this.updateDotCount(-this.ids, this.curcount);
        if (this.curcount != 0.0) {
            this.drawDots(graphics, this.src[0], this.src[1], this.curcount);
            this.drawDots(graphics, this.src[1], this.src[2], this.curcount + 8.0);
            this.drawDots(graphics, this.drn[0], this.drn[1], -this.curcount);
            this.drawDots(graphics, this.drn[1], this.drn[2], -(this.curcount + 8.0));
        }
        this.drawPosts(graphics);
    }
    
    void setPoints() {
        super.setPoints();
        final int n = 16 * this.dsign;
        this.src = this.newPointArray(3);
        this.drn = this.newPointArray(3);
        this.interpPoint2(this.point1, this.point2, this.src[0], this.drn[0], 1.0, n);
        this.interpPoint2(this.point1, this.point2, this.src[1], this.drn[1], 1.0, n / 2);
        this.interpPoint2(this.point1, this.point2, this.src[2], this.drn[2], 1.0 - 10.0 / this.dn, n / 2);
        this.gatePt = this.interpPoint(this.point1, this.point2, 1.0 - 14.0 / this.dn);
        final Point[] pointArray = this.newPointArray(4);
        this.interpPoint2(this.point1, this.point2, pointArray[0], pointArray[1], 1.0 - 13.0 / this.dn, 16.0);
        this.interpPoint2(this.point1, this.point2, pointArray[2], pointArray[3], 1.0 - 10.0 / this.dn, 16.0);
        this.gatePoly = this.createPolygon(pointArray[0], pointArray[1], pointArray[3], pointArray[2]);
        if (this.pnp == -1) {
            this.arrowPoly = this.calcArrow(this.gatePt, this.interpPoint(this.gatePt, this.point1, 18.0 / this.dn), 8.0, 3.0);
        }
        else {
            this.arrowPoly = this.calcArrow(this.point1, this.gatePt, 8.0, 3.0);
        }
    }
    
    int getDumpType() {
        return 106;
    }
    
    double getDefaultThreshold() {
        return -4.0;
    }
    
    double getBeta() {
        return 0.00125;
    }
    
    void getInfo(final String[] array) {
        this.getFetInfo(array, "JFET");
    }
}
