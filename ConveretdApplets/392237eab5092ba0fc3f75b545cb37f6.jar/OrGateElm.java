import java.awt.Point;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class OrGateElm extends GateElm
{
    public OrGateElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public OrGateElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
    }
    
    String getGateName() {
        return "OR gate";
    }
    
    void setPoints() {
        super.setPoints();
        final Point[] pointArray = this.newPointArray(38);
        if (this instanceof XorGateElm) {
            this.linePoints = new Point[5];
        }
        for (int i = 0; i != 16; ++i) {
            final double n = i / 16.0;
            this.interpPoint2(this.lead1, this.lead2, pointArray[i], pointArray[32 - i], 0.5 + n / 2.0, (1.0 - n * n) * this.hs2);
        }
        final double n2 = (this.ww == 0) ? (this.dn * 2.0) : (this.ww * 2);
        for (int j = 0; j != 5; ++j) {
            final double n3 = (j - 2) / 2.0;
            final double n4 = 4.0 * (1.0 - n3 * n3) - 2.0;
            this.interpPoint(this.lead1, this.lead2, pointArray[33 + j], n4 / n2, n3 * this.hs2);
            if (this instanceof XorGateElm) {
                this.linePoints[j] = this.interpPoint(this.lead1, this.lead2, (n4 - 5.0) / n2, n3 * this.hs2);
            }
        }
        pointArray[16] = new Point(this.lead2);
        if (this.isInverting()) {
            this.pcircle = this.interpPoint(this.point1, this.point2, 0.5 + (this.ww + 4) / this.dn);
            this.lead2 = this.interpPoint(this.point1, this.point2, 0.5 + (this.ww + 8) / this.dn);
        }
        this.gatePoly = this.createPolygon(pointArray);
    }
    
    boolean calcFunction() {
        boolean b = false;
        for (int i = 0; i != this.inputCount; ++i) {
            b |= this.getInput(i);
        }
        return b;
    }
    
    int getDumpType() {
        return 152;
    }
}
