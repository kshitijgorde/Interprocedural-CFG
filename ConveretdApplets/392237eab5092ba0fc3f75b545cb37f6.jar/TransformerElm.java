import java.awt.Checkbox;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class TransformerElm extends CircuitElm
{
    double inductance;
    double ratio;
    double couplingCoef;
    Point[] ptEnds;
    Point[] ptCoil;
    Point[] ptCore;
    double[] current;
    double[] curcount;
    int width;
    public static final int FLAG_BACK_EULER = 2;
    double a1;
    double a2;
    double a3;
    double a4;
    double curSourceValue1;
    double curSourceValue2;
    
    public TransformerElm(final int n, final int n2) {
        super(n, n2);
        this.inductance = 4.0;
        this.ratio = 1.0;
        this.width = 32;
        this.noDiagonal = true;
        this.couplingCoef = 0.999;
        this.current = new double[2];
        this.curcount = new double[2];
    }
    
    public TransformerElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.width = CircuitElm.max(32, CircuitElm.abs(n4 - n2));
        this.inductance = new Double(stringTokenizer.nextToken());
        this.ratio = new Double(stringTokenizer.nextToken());
        this.current = new double[2];
        this.curcount = new double[2];
        this.current[0] = new Double(stringTokenizer.nextToken());
        this.current[1] = new Double(stringTokenizer.nextToken());
        this.couplingCoef = 0.999;
        try {
            this.couplingCoef = new Double(stringTokenizer.nextToken());
        }
        catch (Exception ex) {}
        this.noDiagonal = true;
    }
    
    void drag(int snapGrid, int y2) {
        snapGrid = TransformerElm.sim.snapGrid(snapGrid);
        y2 = TransformerElm.sim.snapGrid(y2);
        this.width = CircuitElm.max(32, CircuitElm.abs(y2 - this.y));
        if (snapGrid == this.x) {
            y2 = this.y;
        }
        this.x2 = snapGrid;
        this.y2 = y2;
        this.setPoints();
    }
    
    int getDumpType() {
        return 84;
    }
    
    String dump() {
        return super.dump() + " " + this.inductance + " " + this.ratio + " " + this.current[0] + " " + this.current[1] + " " + this.couplingCoef;
    }
    
    boolean isTrapezoidal() {
        return (this.flags & 0x2) == 0x0;
    }
    
    void draw(final Graphics graphics) {
        for (int i = 0; i != 4; ++i) {
            this.setVoltageColor(graphics, this.volts[i]);
            CircuitElm.drawThickLine(graphics, this.ptEnds[i], this.ptCoil[i]);
        }
        for (int j = 0; j != 2; ++j) {
            this.setPowerColor(graphics, this.current[j] * (this.volts[j] - this.volts[j + 2]));
            this.drawCoil(graphics, this.dsign * ((j == 1) ? -6 : 6), this.ptCoil[j], this.ptCoil[j + 2], this.volts[j], this.volts[j + 2]);
        }
        graphics.setColor(this.needsHighlight() ? TransformerElm.selectColor : TransformerElm.lightGrayColor);
        for (int k = 0; k != 2; ++k) {
            CircuitElm.drawThickLine(graphics, this.ptCore[k], this.ptCore[k + 2]);
            this.curcount[k] = this.updateDotCount(this.current[k], this.curcount[k]);
        }
        for (int l = 0; l != 2; ++l) {
            this.drawDots(graphics, this.ptEnds[l], this.ptCoil[l], this.curcount[l]);
            this.drawDots(graphics, this.ptCoil[l], this.ptCoil[l + 2], this.curcount[l]);
            this.drawDots(graphics, this.ptEnds[l + 2], this.ptCoil[l + 2], -this.curcount[l]);
        }
        this.drawPosts(graphics);
        this.setBbox(this.ptEnds[0], this.ptEnds[3], 0.0);
    }
    
    void setPoints() {
        super.setPoints();
        this.point2.y = this.point1.y;
        this.ptEnds = this.newPointArray(4);
        this.ptCoil = this.newPointArray(4);
        this.ptCore = this.newPointArray(4);
        this.ptEnds[0] = this.point1;
        this.ptEnds[1] = this.point2;
        this.interpPoint(this.point1, this.point2, this.ptEnds[2], 0.0, -this.dsign * this.width);
        this.interpPoint(this.point1, this.point2, this.ptEnds[3], 1.0, -this.dsign * this.width);
        final double n = 0.5 - 12.0 / this.dn;
        final double n2 = 0.5 - 2.0 / this.dn;
        for (int i = 0; i != 4; i += 2) {
            this.interpPoint(this.ptEnds[i], this.ptEnds[i + 1], this.ptCoil[i], n);
            this.interpPoint(this.ptEnds[i], this.ptEnds[i + 1], this.ptCoil[i + 1], 1.0 - n);
            this.interpPoint(this.ptEnds[i], this.ptEnds[i + 1], this.ptCore[i], n2);
            this.interpPoint(this.ptEnds[i], this.ptEnds[i + 1], this.ptCore[i + 1], 1.0 - n2);
        }
    }
    
    Point getPost(final int n) {
        return this.ptEnds[n];
    }
    
    int getPostCount() {
        return 4;
    }
    
    void reset() {
        final double[] current = this.current;
        final int n = 0;
        final double[] current2 = this.current;
        final int n2 = 1;
        final double[] volts = this.volts;
        final int n3 = 0;
        final double[] volts2 = this.volts;
        final int n4 = 1;
        final double[] volts3 = this.volts;
        final int n5 = 2;
        final double[] volts4 = this.volts;
        final int n6 = 3;
        final double[] curcount = this.curcount;
        final int n7 = 0;
        final double[] curcount2 = this.curcount;
        final int n8 = 1;
        final double n9 = 0.0;
        curcount[n7] = (curcount2[n8] = n9);
        volts3[n5] = (volts4[n6] = n9);
        volts[n3] = (volts2[n4] = n9);
        current[n] = (current2[n2] = n9);
    }
    
    void stamp() {
        final double inductance = this.inductance;
        final double n = this.inductance * this.ratio * this.ratio;
        final double n2 = this.couplingCoef * Math.sqrt(inductance * n);
        final double n3 = 1.0 / (inductance * n - n2 * n2);
        final double n4 = this.isTrapezoidal() ? (TransformerElm.sim.timeStep / 2.0) : TransformerElm.sim.timeStep;
        this.a1 = n * n3 * n4;
        this.a2 = -n2 * n3 * n4;
        this.a3 = -n2 * n3 * n4;
        this.a4 = inductance * n3 * n4;
        TransformerElm.sim.stampConductance(this.nodes[0], this.nodes[2], this.a1);
        TransformerElm.sim.stampVCCurrentSource(this.nodes[0], this.nodes[2], this.nodes[1], this.nodes[3], this.a2);
        TransformerElm.sim.stampVCCurrentSource(this.nodes[1], this.nodes[3], this.nodes[0], this.nodes[2], this.a3);
        TransformerElm.sim.stampConductance(this.nodes[1], this.nodes[3], this.a4);
        TransformerElm.sim.stampRightSide(this.nodes[0]);
        TransformerElm.sim.stampRightSide(this.nodes[1]);
        TransformerElm.sim.stampRightSide(this.nodes[2]);
        TransformerElm.sim.stampRightSide(this.nodes[3]);
    }
    
    void startIteration() {
        final double n = this.volts[0] - this.volts[2];
        final double n2 = this.volts[1] - this.volts[3];
        if (this.isTrapezoidal()) {
            this.curSourceValue1 = n * this.a1 + n2 * this.a2 + this.current[0];
            this.curSourceValue2 = n * this.a3 + n2 * this.a4 + this.current[1];
        }
        else {
            this.curSourceValue1 = this.current[0];
            this.curSourceValue2 = this.current[1];
        }
    }
    
    void doStep() {
        TransformerElm.sim.stampCurrentSource(this.nodes[0], this.nodes[2], this.curSourceValue1);
        TransformerElm.sim.stampCurrentSource(this.nodes[1], this.nodes[3], this.curSourceValue2);
    }
    
    void calculateCurrent() {
        final double n = this.volts[0] - this.volts[2];
        final double n2 = this.volts[1] - this.volts[3];
        this.current[0] = n * this.a1 + n2 * this.a2 + this.curSourceValue1;
        this.current[1] = n * this.a3 + n2 * this.a4 + this.curSourceValue2;
    }
    
    void getInfo(final String[] array) {
        array[0] = "transformer";
        array[1] = "L = " + CircuitElm.getUnitText(this.inductance, "H");
        array[2] = "Ratio = 1:" + this.ratio;
        array[3] = "Vd1 = " + CircuitElm.getVoltageText(this.volts[0] - this.volts[2]);
        array[4] = "Vd2 = " + CircuitElm.getVoltageText(this.volts[1] - this.volts[3]);
        array[5] = "I1 = " + CircuitElm.getCurrentText(this.current[0]);
        array[6] = "I2 = " + CircuitElm.getCurrentText(this.current[1]);
    }
    
    boolean getConnection(final int n, final int n2) {
        return this.comparePair(n, n2, 0, 2) || this.comparePair(n, n2, 1, 3);
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Primary Inductance (H)", this.inductance, 0.01, 5.0);
        }
        if (n == 1) {
            return new EditInfo("Ratio", this.ratio, 1.0, 10.0).setDimensionless();
        }
        if (n == 2) {
            return new EditInfo("Coupling Coefficient", this.couplingCoef, 0.0, 1.0).setDimensionless();
        }
        if (n == 3) {
            final EditInfo editInfo = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo.checkbox = new Checkbox("Trapezoidal Approximation", this.isTrapezoidal());
            return editInfo;
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.inductance = editInfo.value;
        }
        if (n == 1) {
            this.ratio = editInfo.value;
        }
        if (n == 2 && editInfo.value > 0.0 && editInfo.value < 1.0) {
            this.couplingCoef = editInfo.value;
        }
        if (n == 3) {
            if (editInfo.checkbox.getState()) {
                this.flags &= 0xFFFFFFFD;
            }
            else {
                this.flags |= 0x2;
            }
        }
    }
}
