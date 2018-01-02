import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Polygon;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class OpAmpElm extends CircuitElm
{
    int opsize;
    int opheight;
    int opwidth;
    int opaddtext;
    double maxOut;
    double minOut;
    double gain;
    double gbw;
    boolean reset;
    final int FLAG_SWAP = 1;
    final int FLAG_SMALL = 2;
    final int FLAG_LOWGAIN = 4;
    Point[] in1p;
    Point[] in2p;
    Point[] textp;
    Polygon triangle;
    Font plusFont;
    double lastvd;
    
    public OpAmpElm(final int n, final int n2) {
        super(n, n2);
        this.noDiagonal = true;
        this.maxOut = 15.0;
        this.minOut = -15.0;
        this.gbw = 1000000.0;
        this.setSize(OpAmpElm.sim.smallGridCheckItem.getState() ? 1 : 2);
        this.setGain();
    }
    
    public OpAmpElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.maxOut = 15.0;
        this.minOut = -15.0;
        this.gbw = 1000000.0;
        try {
            this.maxOut = new Double(stringTokenizer.nextToken());
            this.minOut = new Double(stringTokenizer.nextToken());
            this.gbw = new Double(stringTokenizer.nextToken());
        }
        catch (Exception ex) {}
        this.noDiagonal = true;
        this.setSize(((n5 & 0x2) != 0x0) ? 1 : 2);
        this.setGain();
    }
    
    void setGain() {
        this.gain = (((this.flags & 0x4) != 0x0) ? 1000.0 : 100000.0);
    }
    
    String dump() {
        return super.dump() + " " + this.maxOut + " " + this.minOut + " " + this.gbw;
    }
    
    boolean nonLinear() {
        return true;
    }
    
    void draw(final Graphics graphics) {
        this.setBbox(this.point1, this.point2, this.opheight * 2);
        this.setVoltageColor(graphics, this.volts[0]);
        CircuitElm.drawThickLine(graphics, this.in1p[0], this.in1p[1]);
        this.setVoltageColor(graphics, this.volts[1]);
        CircuitElm.drawThickLine(graphics, this.in2p[0], this.in2p[1]);
        graphics.setColor(this.needsHighlight() ? OpAmpElm.selectColor : OpAmpElm.lightGrayColor);
        this.setPowerColor(graphics, true);
        CircuitElm.drawThickPolygon(graphics, this.triangle);
        graphics.setFont(this.plusFont);
        this.drawCenteredText(graphics, "-", this.textp[0].x, this.textp[0].y - 2, true);
        this.drawCenteredText(graphics, "+", this.textp[1].x, this.textp[1].y, true);
        this.setVoltageColor(graphics, this.volts[2]);
        CircuitElm.drawThickLine(graphics, this.lead2, this.point2);
        this.curcount = this.updateDotCount(this.current, this.curcount);
        this.drawDots(graphics, this.point2, this.lead2, this.curcount);
        this.drawPosts(graphics);
    }
    
    double getPower() {
        return this.volts[2] * this.current;
    }
    
    void setSize(final int opsize) {
        this.opsize = opsize;
        this.opheight = 8 * opsize;
        this.opwidth = 13 * opsize;
        this.flags = ((this.flags & 0xFFFFFFFD) | ((opsize == 1) ? 2 : 0));
    }
    
    void setPoints() {
        super.setPoints();
        if (this.dn > 150.0 && this == OpAmpElm.sim.dragElm) {
            this.setSize(2);
        }
        int opwidth = this.opwidth;
        if (opwidth > this.dn / 2.0) {
            opwidth = (int)(this.dn / 2.0);
        }
        this.calcLeads(opwidth * 2);
        int n = this.opheight * this.dsign;
        if ((this.flags & 0x1) != 0x0) {
            n = -n;
        }
        this.in1p = this.newPointArray(2);
        this.in2p = this.newPointArray(2);
        this.textp = this.newPointArray(2);
        this.interpPoint2(this.point1, this.point2, this.in1p[0], this.in2p[0], 0.0, n);
        this.interpPoint2(this.lead1, this.lead2, this.in1p[1], this.in2p[1], 0.0, n);
        this.interpPoint2(this.lead1, this.lead2, this.textp[0], this.textp[1], 0.2, n);
        final Point[] pointArray = this.newPointArray(2);
        this.interpPoint2(this.lead1, this.lead2, pointArray[0], pointArray[1], 0.0, n * 2);
        this.triangle = this.createPolygon(pointArray[0], pointArray[1], this.lead2);
        this.plusFont = new Font("SansSerif", 0, (this.opsize == 2) ? 14 : 10);
    }
    
    int getPostCount() {
        return 3;
    }
    
    Point getPost(final int n) {
        return (n == 0) ? this.in1p[0] : ((n == 1) ? this.in2p[0] : this.point2);
    }
    
    int getVoltageSourceCount() {
        return 1;
    }
    
    void getInfo(final String[] array) {
        array[0] = "op-amp";
        array[1] = "V+ = " + CircuitElm.getVoltageText(this.volts[1]);
        array[2] = "V- = " + CircuitElm.getVoltageText(this.volts[0]);
        array[3] = "Vout = " + CircuitElm.getVoltageText(Math.max(Math.min(this.volts[2], this.maxOut), this.minOut));
        array[4] = "Iout = " + CircuitElm.getCurrentText(this.getCurrent());
        array[5] = "range = " + CircuitElm.getVoltageText(this.minOut) + " to " + CircuitElm.getVoltageText(this.maxOut);
    }
    
    void stamp() {
        final int n = OpAmpElm.sim.nodeList.size() + this.voltSource;
        OpAmpElm.sim.stampNonLinear(n);
        OpAmpElm.sim.stampMatrix(this.nodes[2], n, 1.0);
    }
    
    void doStep() {
        final double lastvd = this.volts[1] - this.volts[0];
        if (Math.abs(this.lastvd - lastvd) > 0.1) {
            OpAmpElm.sim.converged = false;
        }
        else if (this.volts[2] > this.maxOut + 0.1 || this.volts[2] < this.minOut - 0.1) {
            OpAmpElm.sim.converged = false;
        }
        double n = 0.0;
        final int n2 = OpAmpElm.sim.nodeList.size() + this.voltSource;
        double gain;
        if (lastvd >= this.maxOut / this.gain && (this.lastvd >= 0.0 || OpAmpElm.sim.getrand(4) == 1)) {
            gain = 1.0E-4;
            n = this.maxOut - gain * this.maxOut / this.gain;
        }
        else if (lastvd <= this.minOut / this.gain && (this.lastvd <= 0.0 || OpAmpElm.sim.getrand(4) == 1)) {
            gain = 1.0E-4;
            n = this.minOut - gain * this.minOut / this.gain;
        }
        else {
            gain = this.gain;
        }
        OpAmpElm.sim.stampMatrix(n2, this.nodes[0], gain);
        OpAmpElm.sim.stampMatrix(n2, this.nodes[1], -gain);
        OpAmpElm.sim.stampMatrix(n2, this.nodes[2], 1.0);
        OpAmpElm.sim.stampRightSide(n2, n);
        this.lastvd = lastvd;
    }
    
    boolean getConnection(final int n, final int n2) {
        return false;
    }
    
    boolean hasGroundConnection(final int n) {
        return n == 2;
    }
    
    double getVoltageDiff() {
        return this.volts[2] - this.volts[1];
    }
    
    int getDumpType() {
        return 97;
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Max Output (V)", this.maxOut, 1.0, 20.0);
        }
        if (n == 1) {
            return new EditInfo("Min Output (V)", this.minOut, -20.0, 0.0);
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.maxOut = editInfo.value;
        }
        if (n == 1) {
            this.minOut = editInfo.value;
        }
    }
}
