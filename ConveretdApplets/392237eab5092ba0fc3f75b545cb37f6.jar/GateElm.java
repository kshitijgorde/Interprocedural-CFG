import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Polygon;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class GateElm extends CircuitElm
{
    final int FLAG_SMALL = 1;
    int inputCount;
    boolean lastOutput;
    int gsize;
    int gwidth;
    int gwidth2;
    int gheight;
    int hs2;
    Point[] inPosts;
    Point[] inGates;
    int ww;
    Polygon gatePoly;
    Point pcircle;
    Point[] linePoints;
    
    public GateElm(final int n, final int n2) {
        super(n, n2);
        this.inputCount = 2;
        this.noDiagonal = true;
        this.inputCount = 2;
        this.setSize(GateElm.sim.smallGridCheckItem.getState() ? 1 : 2);
    }
    
    public GateElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.inputCount = 2;
        this.inputCount = new Integer(stringTokenizer.nextToken());
        this.lastOutput = (new Double(stringTokenizer.nextToken()) > 2.5);
        this.noDiagonal = true;
        this.setSize(((n5 & 0x1) != 0x0) ? 1 : 2);
    }
    
    boolean isInverting() {
        return false;
    }
    
    void setSize(final int gsize) {
        this.gsize = gsize;
        this.gwidth = 7 * gsize;
        this.gwidth2 = 14 * gsize;
        this.gheight = 8 * gsize;
        this.flags = ((gsize == 1) ? 1 : 0);
    }
    
    String dump() {
        return super.dump() + " " + this.inputCount + " " + this.volts[this.inputCount];
    }
    
    void setPoints() {
        super.setPoints();
        if (this.dn > 150.0 && this == GateElm.sim.dragElm) {
            this.setSize(2);
        }
        final int gheight = this.gheight;
        this.ww = this.gwidth2;
        if (this.ww > this.dn / 2.0) {
            this.ww = (int)(this.dn / 2.0);
        }
        if (this.isInverting() && this.ww + 8 > this.dn / 2.0) {
            this.ww = (int)(this.dn / 2.0 - 8.0);
        }
        this.calcLeads(this.ww * 2);
        this.inPosts = new Point[this.inputCount];
        this.inGates = new Point[this.inputCount];
        this.allocNodes();
        for (int n = -this.inputCount / 2, i = 0; i != this.inputCount; ++i, ++n) {
            if (n == 0 && (this.inputCount & 0x1) == 0x0) {
                ++n;
            }
            this.inPosts[i] = this.interpPoint(this.point1, this.point2, 0.0, gheight * n);
            this.inGates[i] = this.interpPoint(this.lead1, this.lead2, 0.0, gheight * n);
            this.volts[i] = ((this.lastOutput ^ this.isInverting()) ? 5.0 : 0.0);
        }
        this.hs2 = this.gwidth * (this.inputCount / 2 + 1);
        this.setBbox(this.point1, this.point2, this.hs2);
    }
    
    void draw(final Graphics graphics) {
        for (int i = 0; i != this.inputCount; ++i) {
            this.setVoltageColor(graphics, this.volts[i]);
            CircuitElm.drawThickLine(graphics, this.inPosts[i], this.inGates[i]);
        }
        this.setVoltageColor(graphics, this.volts[this.inputCount]);
        CircuitElm.drawThickLine(graphics, this.lead2, this.point2);
        graphics.setColor(this.needsHighlight() ? GateElm.selectColor : GateElm.lightGrayColor);
        CircuitElm.drawThickPolygon(graphics, this.gatePoly);
        if (this.linePoints != null) {
            for (int j = 0; j != this.linePoints.length - 1; ++j) {
                CircuitElm.drawThickLine(graphics, this.linePoints[j], this.linePoints[j + 1]);
            }
        }
        if (this.isInverting()) {
            CircuitElm.drawThickCircle(graphics, this.pcircle.x, this.pcircle.y, 3);
        }
        this.curcount = this.updateDotCount(this.current, this.curcount);
        this.drawDots(graphics, this.lead2, this.point2, this.curcount);
        this.drawPosts(graphics);
    }
    
    int getPostCount() {
        return this.inputCount + 1;
    }
    
    Point getPost(final int n) {
        if (n == this.inputCount) {
            return this.point2;
        }
        return this.inPosts[n];
    }
    
    int getVoltageSourceCount() {
        return 1;
    }
    
    abstract String getGateName();
    
    void getInfo(final String[] array) {
        array[0] = this.getGateName();
        array[1] = "Vout = " + CircuitElm.getVoltageText(this.volts[this.inputCount]);
        array[2] = "Iout = " + CircuitElm.getCurrentText(this.getCurrent());
    }
    
    void stamp() {
        GateElm.sim.stampVoltageSource(0, this.nodes[this.inputCount], this.voltSource);
    }
    
    boolean getInput(final int n) {
        return this.volts[n] > 2.5;
    }
    
    abstract boolean calcFunction();
    
    void doStep() {
        boolean calcFunction = this.calcFunction();
        if (this.isInverting()) {
            calcFunction = !calcFunction;
        }
        this.lastOutput = calcFunction;
        GateElm.sim.updateVoltageSource(0, this.nodes[this.inputCount], this.voltSource, calcFunction ? 5.0 : 0.0);
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("# of Inputs", this.inputCount, 1.0, 8.0).setDimensionless();
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        this.inputCount = (int)editInfo.value;
        this.setPoints();
    }
    
    boolean getConnection(final int n, final int n2) {
        return false;
    }
    
    boolean hasGroundConnection(final int n) {
        return n == this.inputCount;
    }
}
