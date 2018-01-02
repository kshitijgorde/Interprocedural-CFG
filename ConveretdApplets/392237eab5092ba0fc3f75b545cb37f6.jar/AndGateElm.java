import java.awt.Point;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class AndGateElm extends GateElm
{
    public AndGateElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public AndGateElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
    }
    
    void setPoints() {
        super.setPoints();
        final Point[] pointArray = this.newPointArray(23);
        this.interpPoint2(this.lead1, this.lead2, pointArray[0], pointArray[22], 0.0, this.hs2);
        for (int i = 0; i != 10; ++i) {
            final double n = i * 0.1;
            this.interpPoint2(this.lead1, this.lead2, pointArray[i + 1], pointArray[21 - i], 0.5 + n / 2.0, Math.sqrt(1.0 - n * n) * this.hs2);
        }
        pointArray[11] = new Point(this.lead2);
        if (this.isInverting()) {
            this.pcircle = this.interpPoint(this.point1, this.point2, 0.5 + (this.ww + 4) / this.dn);
            this.lead2 = this.interpPoint(this.point1, this.point2, 0.5 + (this.ww + 8) / this.dn);
        }
        this.gatePoly = this.createPolygon(pointArray);
    }
    
    String getGateName() {
        return "AND gate";
    }
    
    boolean calcFunction() {
        boolean b = true;
        for (int i = 0; i != this.inputCount; ++i) {
            b &= this.getInput(i);
        }
        return b;
    }
    
    int getDumpType() {
        return 150;
    }
}
