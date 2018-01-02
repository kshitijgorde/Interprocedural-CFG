import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

class TransistorElm extends CircuitElm
{
    int pnp;
    double beta;
    double fgain;
    double gmin;
    final int FLAG_FLIP = 1;
    double ic;
    double ie;
    double ib;
    double curcount_c;
    double curcount_e;
    double curcount_b;
    Polygon rectPoly;
    Polygon arrowPoly;
    Point[] rect;
    Point[] coll;
    Point[] emit;
    Point base;
    static final double leakage = 1.0E-13;
    static final double vt = 0.025;
    static final double vdcoef = 40.0;
    static final double rgain = 0.5;
    double vcrit;
    double lastvbc;
    double lastvbe;
    
    TransistorElm(final int n, final int n2, final boolean b) {
        super(n, n2);
        this.pnp = (b ? -1 : 1);
        this.beta = 100.0;
        this.setup();
    }
    
    public TransistorElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.pnp = new Integer(stringTokenizer.nextToken());
        this.beta = 100.0;
        try {
            this.lastvbe = new Double(stringTokenizer.nextToken());
            this.lastvbc = new Double(stringTokenizer.nextToken());
            this.volts[0] = 0.0;
            this.volts[1] = -this.lastvbe;
            this.volts[2] = -this.lastvbc;
            this.beta = new Double(stringTokenizer.nextToken());
        }
        catch (Exception ex) {}
        this.setup();
    }
    
    void setup() {
        this.vcrit = 0.025 * Math.log(0.025 / (Math.sqrt(2.0) * 1.0E-13));
        this.fgain = this.beta / (this.beta + 1.0);
        this.noDiagonal = true;
    }
    
    boolean nonLinear() {
        return true;
    }
    
    void reset() {
        final double[] volts = this.volts;
        final int n = 0;
        final double[] volts2 = this.volts;
        final int n2 = 1;
        final double[] volts3 = this.volts;
        final int n3 = 2;
        final double n4 = 0.0;
        volts3[n3] = n4;
        volts[n] = (volts2[n2] = n4);
        final double lastvbc = 0.0;
        this.curcount_b = lastvbc;
        this.curcount_e = lastvbc;
        this.curcount_c = lastvbc;
        this.lastvbe = lastvbc;
        this.lastvbc = lastvbc;
    }
    
    int getDumpType() {
        return 116;
    }
    
    String dump() {
        return super.dump() + " " + this.pnp + " " + (this.volts[0] - this.volts[1]) + " " + (this.volts[0] - this.volts[2]) + " " + this.beta;
    }
    
    void draw(final Graphics graphics) {
        this.setBbox(this.point1, this.point2, 16.0);
        this.setPowerColor(graphics, true);
        this.setVoltageColor(graphics, this.volts[1]);
        CircuitElm.drawThickLine(graphics, this.coll[0], this.coll[1]);
        this.setVoltageColor(graphics, this.volts[2]);
        CircuitElm.drawThickLine(graphics, this.emit[0], this.emit[1]);
        graphics.setColor(TransistorElm.lightGrayColor);
        graphics.fillPolygon(this.arrowPoly);
        this.setVoltageColor(graphics, this.volts[0]);
        if (TransistorElm.sim.powerCheckItem.getState()) {
            graphics.setColor(Color.gray);
        }
        CircuitElm.drawThickLine(graphics, this.point1, this.base);
        this.curcount_b = this.updateDotCount(-this.ib, this.curcount_b);
        this.drawDots(graphics, this.base, this.point1, this.curcount_b);
        this.curcount_c = this.updateDotCount(-this.ic, this.curcount_c);
        this.drawDots(graphics, this.coll[1], this.coll[0], this.curcount_c);
        this.curcount_e = this.updateDotCount(-this.ie, this.curcount_e);
        this.drawDots(graphics, this.emit[1], this.emit[0], this.curcount_e);
        this.setVoltageColor(graphics, this.volts[0]);
        this.setPowerColor(graphics, true);
        graphics.fillPolygon(this.rectPoly);
        if ((this.needsHighlight() || TransistorElm.sim.dragElm == this) && this.dy == 0) {
            graphics.setColor(Color.white);
            graphics.setFont(TransistorElm.unitsFont);
            final int sign = CircuitElm.sign(this.dx);
            graphics.drawString("B", this.base.x - 10 * sign, this.base.y - 5);
            graphics.drawString("C", this.coll[0].x - 3 + 9 * sign, this.coll[0].y + 4);
            graphics.drawString("E", this.emit[0].x - 3 + 9 * sign, this.emit[0].y + 4);
        }
        this.drawPosts(graphics);
    }
    
    Point getPost(final int n) {
        return (n == 0) ? this.point1 : ((n == 1) ? this.coll[0] : this.emit[0]);
    }
    
    int getPostCount() {
        return 3;
    }
    
    double getPower() {
        return (this.volts[0] - this.volts[2]) * this.ib + (this.volts[1] - this.volts[2]) * this.ic;
    }
    
    void setPoints() {
        super.setPoints();
        final int n = 16;
        if ((this.flags & 0x1) != 0x0) {
            this.dsign = -this.dsign;
        }
        final int n2 = n * this.dsign * this.pnp;
        this.coll = this.newPointArray(2);
        this.emit = this.newPointArray(2);
        this.interpPoint2(this.point1, this.point2, this.coll[0], this.emit[0], 1.0, n2);
        this.rect = this.newPointArray(4);
        this.interpPoint2(this.point1, this.point2, this.rect[0], this.rect[1], 1.0 - 16.0 / this.dn, n);
        this.interpPoint2(this.point1, this.point2, this.rect[2], this.rect[3], 1.0 - 13.0 / this.dn, n);
        this.interpPoint2(this.point1, this.point2, this.coll[1], this.emit[1], 1.0 - 13.0 / this.dn, 6 * this.dsign * this.pnp);
        this.base = new Point();
        this.interpPoint(this.point1, this.point2, this.base, 1.0 - 16.0 / this.dn);
        this.rectPoly = this.createPolygon(this.rect[0], this.rect[2], this.rect[3], this.rect[1]);
        if (this.pnp == 1) {
            this.arrowPoly = this.calcArrow(this.emit[1], this.emit[0], 8.0, 4.0);
        }
        else {
            this.arrowPoly = this.calcArrow(this.emit[0], this.interpPoint(this.point1, this.point2, 1.0 - 11.0 / this.dn, -5 * this.dsign * this.pnp), 8.0, 4.0);
        }
    }
    
    double limitStep(double vcrit, final double n) {
        if (vcrit > this.vcrit && Math.abs(vcrit - n) > 0.05) {
            if (n > 0.0) {
                final double n2 = 1.0 + (vcrit - n) / 0.025;
                if (n2 > 0.0) {
                    vcrit = n + 0.025 * Math.log(n2);
                }
                else {
                    vcrit = this.vcrit;
                }
            }
            else {
                vcrit = 0.025 * Math.log(vcrit / 0.025);
            }
            TransistorElm.sim.converged = false;
        }
        return vcrit;
    }
    
    void stamp() {
        TransistorElm.sim.stampNonLinear(this.nodes[0]);
        TransistorElm.sim.stampNonLinear(this.nodes[1]);
        TransistorElm.sim.stampNonLinear(this.nodes[2]);
    }
    
    void doStep() {
        final double n = this.volts[0] - this.volts[1];
        final double n2 = this.volts[0] - this.volts[2];
        if (Math.abs(n - this.lastvbc) > 0.01 || Math.abs(n2 - this.lastvbe) > 0.01) {
            TransistorElm.sim.converged = false;
        }
        this.gmin = 0.0;
        if (TransistorElm.sim.subIterations > 100) {
            this.gmin = Math.exp(-9.0 * Math.log(10.0) * (1.0 - TransistorElm.sim.subIterations / 3000.0));
            if (this.gmin > 0.1) {
                this.gmin = 0.1;
            }
        }
        final double lastvbc = this.pnp * this.limitStep(this.pnp * n, this.pnp * this.lastvbc);
        final double lastvbe = this.pnp * this.limitStep(this.pnp * n2, this.pnp * this.lastvbe);
        this.lastvbc = lastvbc;
        this.lastvbe = lastvbe;
        final double n3 = 40.0 * this.pnp;
        final double exp = Math.exp(lastvbc * n3);
        double exp2 = Math.exp(lastvbe * n3);
        if (exp2 < 1.0) {
            exp2 = 1.0;
        }
        this.ie = this.pnp * 1.0E-13 * (-(exp2 - 1.0) + 0.5 * (exp - 1.0));
        this.ic = this.pnp * 1.0E-13 * (this.fgain * (exp2 - 1.0) - (exp - 1.0));
        this.ib = -(this.ie + this.ic);
        final double n4 = -4.0E-12 * exp2;
        final double n5 = 2.0E-12 * exp;
        final double n6 = -n4 * this.fgain;
        final double n7 = -n5 * 2.0;
        TransistorElm.sim.stampMatrix(this.nodes[0], this.nodes[0], -n4 - n5 - n6 - n7 + this.gmin * 2.0);
        TransistorElm.sim.stampMatrix(this.nodes[0], this.nodes[1], n5 + n7 - this.gmin);
        TransistorElm.sim.stampMatrix(this.nodes[0], this.nodes[2], n4 + n6 - this.gmin);
        TransistorElm.sim.stampMatrix(this.nodes[1], this.nodes[0], n6 + n7 - this.gmin);
        TransistorElm.sim.stampMatrix(this.nodes[1], this.nodes[1], -n7 + this.gmin);
        TransistorElm.sim.stampMatrix(this.nodes[1], this.nodes[2], -n6);
        TransistorElm.sim.stampMatrix(this.nodes[2], this.nodes[0], n4 + n5 - this.gmin);
        TransistorElm.sim.stampMatrix(this.nodes[2], this.nodes[1], -n5);
        TransistorElm.sim.stampMatrix(this.nodes[2], this.nodes[2], -n4 + this.gmin);
        TransistorElm.sim.stampRightSide(this.nodes[0], -this.ib - (n5 + n7) * lastvbc - (n4 + n6) * lastvbe);
        TransistorElm.sim.stampRightSide(this.nodes[1], -this.ic + n6 * lastvbe + n7 * lastvbc);
        TransistorElm.sim.stampRightSide(this.nodes[2], -this.ie + n4 * lastvbe + n5 * lastvbc);
    }
    
    void getInfo(final String[] array) {
        array[0] = "transistor (" + ((this.pnp == -1) ? "PNP)" : "NPN)") + " beta=" + TransistorElm.showFormat.format(this.beta);
        final double n = this.volts[0] - this.volts[1];
        final double n2 = this.volts[0] - this.volts[2];
        final double n3 = this.volts[1] - this.volts[2];
        if (n * this.pnp > 0.2) {
            array[1] = ((n2 * this.pnp > 0.2) ? "saturation" : "reverse active");
        }
        else {
            array[1] = ((n2 * this.pnp > 0.2) ? "fwd active" : "cutoff");
        }
        array[2] = "Ic = " + CircuitElm.getCurrentText(this.ic);
        array[3] = "Ib = " + CircuitElm.getCurrentText(this.ib);
        array[4] = "Vbe = " + CircuitElm.getVoltageText(n2);
        array[5] = "Vbc = " + CircuitElm.getVoltageText(n);
        array[6] = "Vce = " + CircuitElm.getVoltageText(n3);
    }
    
    double getScopeValue(final int n) {
        switch (n) {
            case 1: {
                return this.ib;
            }
            case 2: {
                return this.ic;
            }
            case 3: {
                return this.ie;
            }
            case 4: {
                return this.volts[0] - this.volts[2];
            }
            case 5: {
                return this.volts[0] - this.volts[1];
            }
            case 6: {
                return this.volts[1] - this.volts[2];
            }
            default: {
                return 0.0;
            }
        }
    }
    
    String getScopeUnits(final int n) {
        switch (n) {
            case 1:
            case 2:
            case 3: {
                return "A";
            }
            default: {
                return "V";
            }
        }
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Beta/hFE", this.beta, 10.0, 1000.0).setDimensionless();
        }
        if (n == 1) {
            final EditInfo editInfo = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo.checkbox = new Checkbox("Swap E/C", (this.flags & 0x1) != 0x0);
            return editInfo;
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.beta = editInfo.value;
            this.setup();
        }
        if (n == 1) {
            if (editInfo.checkbox.getState()) {
                this.flags |= 0x1;
            }
            else {
                this.flags &= 0xFFFFFFFE;
            }
            this.setPoints();
        }
    }
    
    boolean canViewInScope() {
        return true;
    }
}
