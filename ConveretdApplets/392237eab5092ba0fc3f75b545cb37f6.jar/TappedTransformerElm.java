import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class TappedTransformerElm extends CircuitElm
{
    double inductance;
    double ratio;
    Point[] ptEnds;
    Point[] ptCoil;
    Point[] ptCore;
    double[] current;
    double[] curcount;
    double[] a;
    double[] curSourceValue;
    double[] voltdiff;
    
    public TappedTransformerElm(final int n, final int n2) {
        super(n, n2);
        this.inductance = 4.0;
        this.ratio = 1.0;
        this.noDiagonal = true;
        this.current = new double[4];
        this.curcount = new double[4];
    }
    
    public TappedTransformerElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.inductance = new Double(stringTokenizer.nextToken());
        this.ratio = new Double(stringTokenizer.nextToken());
        this.current = new double[4];
        this.curcount = new double[4];
        this.current[0] = new Double(stringTokenizer.nextToken());
        this.current[1] = new Double(stringTokenizer.nextToken());
        try {
            this.current[2] = new Double(stringTokenizer.nextToken());
        }
        catch (Exception ex) {}
        this.noDiagonal = true;
    }
    
    int getDumpType() {
        return 169;
    }
    
    String dump() {
        return super.dump() + " " + this.inductance + " " + this.ratio + " " + this.current[0] + " " + this.current[1] + " " + this.current[2];
    }
    
    void draw(final Graphics graphics) {
        for (int i = 0; i != 5; ++i) {
            this.setVoltageColor(graphics, this.volts[i]);
            CircuitElm.drawThickLine(graphics, this.ptEnds[i], this.ptCoil[i]);
        }
        for (int j = 0; j != 4; ++j) {
            if (j != 1) {
                this.setPowerColor(graphics, this.current[j] * (this.volts[j] - this.volts[j + 1]));
                this.drawCoil(graphics, (j > 1) ? -6 : 6, this.ptCoil[j], this.ptCoil[j + 1], this.volts[j], this.volts[j + 1]);
            }
        }
        graphics.setColor(this.needsHighlight() ? TappedTransformerElm.selectColor : TappedTransformerElm.lightGrayColor);
        for (int k = 0; k != 4; k += 2) {
            CircuitElm.drawThickLine(graphics, this.ptCore[k], this.ptCore[k + 1]);
        }
        this.current[3] = this.current[1] - this.current[2];
        for (int l = 0; l != 4; ++l) {
            this.curcount[l] = this.updateDotCount(this.current[l], this.curcount[l]);
        }
        this.drawDots(graphics, this.ptEnds[0], this.ptCoil[0], this.curcount[0]);
        this.drawDots(graphics, this.ptCoil[0], this.ptCoil[1], this.curcount[0]);
        this.drawDots(graphics, this.ptCoil[1], this.ptEnds[1], this.curcount[0]);
        this.drawDots(graphics, this.ptEnds[2], this.ptCoil[2], this.curcount[1]);
        this.drawDots(graphics, this.ptCoil[2], this.ptCoil[3], this.curcount[1]);
        this.drawDots(graphics, this.ptCoil[3], this.ptEnds[3], this.curcount[3]);
        this.drawDots(graphics, this.ptCoil[3], this.ptCoil[4], this.curcount[2]);
        this.drawDots(graphics, this.ptCoil[4], this.ptEnds[4], this.curcount[2]);
        this.drawPosts(graphics);
        this.setBbox(this.ptEnds[0], this.ptEnds[4], 0.0);
    }
    
    void setPoints() {
        super.setPoints();
        final int n = 32;
        this.ptEnds = this.newPointArray(5);
        this.ptCoil = this.newPointArray(5);
        this.ptCore = this.newPointArray(4);
        this.ptEnds[0] = this.point1;
        this.ptEnds[2] = this.point2;
        this.interpPoint(this.point1, this.point2, this.ptEnds[1], 0.0, -n * 2);
        this.interpPoint(this.point1, this.point2, this.ptEnds[3], 1.0, -n);
        this.interpPoint(this.point1, this.point2, this.ptEnds[4], 1.0, -n * 2);
        final double n2 = 0.5 - 12.0 / this.dn;
        final double n3 = 0.5 - 2.0 / this.dn;
        this.interpPoint(this.ptEnds[0], this.ptEnds[2], this.ptCoil[0], n2);
        this.interpPoint(this.ptEnds[0], this.ptEnds[2], this.ptCoil[1], n2, -n * 2);
        this.interpPoint(this.ptEnds[0], this.ptEnds[2], this.ptCoil[2], 1.0 - n2);
        this.interpPoint(this.ptEnds[0], this.ptEnds[2], this.ptCoil[3], 1.0 - n2, -n);
        this.interpPoint(this.ptEnds[0], this.ptEnds[2], this.ptCoil[4], 1.0 - n2, -n * 2);
        for (int i = 0; i != 2; ++i) {
            final int n4 = -n * i * 2;
            this.interpPoint(this.ptEnds[0], this.ptEnds[2], this.ptCore[i], n3, n4);
            this.interpPoint(this.ptEnds[0], this.ptEnds[2], this.ptCore[i + 2], 1.0 - n3, n4);
        }
    }
    
    Point getPost(final int n) {
        return this.ptEnds[n];
    }
    
    int getPostCount() {
        return 5;
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
        final double n = this.inductance * this.ratio * this.ratio / 4.0;
        final double n2 = 0.99;
        (this.a = new double[9])[0] = (1.0 + n2) / (inductance * (1.0 + n2 - 2.0 * n2 * n2));
        final double[] a = this.a;
        final int n3 = 1;
        final double[] a2 = this.a;
        final int n4 = 2;
        final double[] a3 = this.a;
        final int n5 = 3;
        final double[] a4 = this.a;
        final int n6 = 6;
        final double n7 = 2.0 * n2 / ((2.0 * n2 * n2 - n2 - 1.0) * this.inductance * this.ratio);
        a3[n5] = (a4[n6] = n7);
        a[n3] = (a2[n4] = n7);
        this.a[4] = (this.a[8] = -4.0 * (1.0 + n2) / ((2.0 * n2 * n2 - n2 - 1.0) * inductance * this.ratio * this.ratio));
        this.a[5] = (this.a[7] = 4.0 * n2 / ((2.0 * n2 * n2 - n2 - 1.0) * inductance * this.ratio * this.ratio));
        for (int i = 0; i != 9; ++i) {
            final double[] a5 = this.a;
            final int n8 = i;
            a5[n8] *= TappedTransformerElm.sim.timeStep / 2.0;
        }
        TappedTransformerElm.sim.stampConductance(this.nodes[0], this.nodes[1], this.a[0]);
        TappedTransformerElm.sim.stampVCCurrentSource(this.nodes[0], this.nodes[1], this.nodes[2], this.nodes[3], this.a[1]);
        TappedTransformerElm.sim.stampVCCurrentSource(this.nodes[0], this.nodes[1], this.nodes[3], this.nodes[4], this.a[2]);
        TappedTransformerElm.sim.stampVCCurrentSource(this.nodes[2], this.nodes[3], this.nodes[0], this.nodes[1], this.a[3]);
        TappedTransformerElm.sim.stampConductance(this.nodes[2], this.nodes[3], this.a[4]);
        TappedTransformerElm.sim.stampVCCurrentSource(this.nodes[2], this.nodes[3], this.nodes[3], this.nodes[4], this.a[5]);
        TappedTransformerElm.sim.stampVCCurrentSource(this.nodes[3], this.nodes[4], this.nodes[0], this.nodes[1], this.a[6]);
        TappedTransformerElm.sim.stampVCCurrentSource(this.nodes[3], this.nodes[4], this.nodes[2], this.nodes[3], this.a[7]);
        TappedTransformerElm.sim.stampConductance(this.nodes[3], this.nodes[4], this.a[8]);
        for (int j = 0; j != 5; ++j) {
            TappedTransformerElm.sim.stampRightSide(this.nodes[j]);
        }
        this.voltdiff = new double[3];
        this.curSourceValue = new double[3];
    }
    
    void startIteration() {
        this.voltdiff[0] = this.volts[0] - this.volts[1];
        this.voltdiff[1] = this.volts[2] - this.volts[3];
        this.voltdiff[2] = this.volts[3] - this.volts[4];
        for (int i = 0; i != 3; ++i) {
            this.curSourceValue[i] = this.current[i];
            for (int j = 0; j != 3; ++j) {
                final double[] curSourceValue = this.curSourceValue;
                final int n = i;
                curSourceValue[n] += this.a[i * 3 + j] * this.voltdiff[j];
            }
        }
    }
    
    void doStep() {
        TappedTransformerElm.sim.stampCurrentSource(this.nodes[0], this.nodes[1], this.curSourceValue[0]);
        TappedTransformerElm.sim.stampCurrentSource(this.nodes[2], this.nodes[3], this.curSourceValue[1]);
        TappedTransformerElm.sim.stampCurrentSource(this.nodes[3], this.nodes[4], this.curSourceValue[2]);
    }
    
    void calculateCurrent() {
        this.voltdiff[0] = this.volts[0] - this.volts[1];
        this.voltdiff[1] = this.volts[2] - this.volts[3];
        this.voltdiff[2] = this.volts[3] - this.volts[4];
        for (int i = 0; i != 3; ++i) {
            this.current[i] = this.curSourceValue[i];
            for (int j = 0; j != 3; ++j) {
                final double[] current = this.current;
                final int n = i;
                current[n] += this.a[i * 3 + j] * this.voltdiff[j];
            }
        }
    }
    
    void getInfo(final String[] array) {
        array[0] = "transformer";
        array[1] = "L = " + CircuitElm.getUnitText(this.inductance, "H");
        array[2] = "Ratio = " + this.ratio;
        array[3] = "Vd1 = " + CircuitElm.getVoltageText(this.volts[0] - this.volts[2]);
        array[4] = "Vd2 = " + CircuitElm.getVoltageText(this.volts[1] - this.volts[3]);
    }
    
    boolean getConnection(final int n, final int n2) {
        return this.comparePair(n, n2, 0, 1) || this.comparePair(n, n2, 2, 3) || this.comparePair(n, n2, 3, 4) || this.comparePair(n, n2, 2, 4);
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Primary Inductance (H)", this.inductance, 0.01, 5.0);
        }
        if (n == 1) {
            return new EditInfo("Ratio", this.ratio, 1.0, 10.0).setDimensionless();
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
    }
}
