import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

class SCRElm extends CircuitElm
{
    final int anode = 0;
    final int cnode = 1;
    final int gnode = 2;
    final int inode = 3;
    Diode diode;
    double ia;
    double ic;
    double ig;
    double curcount_a;
    double curcount_c;
    double curcount_g;
    double lastvac;
    double lastvag;
    double cresistance;
    double triggerI;
    double holdingI;
    final int hs = 8;
    Polygon poly;
    Point[] cathode;
    Point[] gate;
    double aresistance;
    
    public SCRElm(final int n, final int n2) {
        super(n, n2);
        this.setDefaults();
        this.setup();
    }
    
    public SCRElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.setDefaults();
        try {
            this.lastvac = new Double(stringTokenizer.nextToken());
            this.lastvag = new Double(stringTokenizer.nextToken());
            this.volts[0] = 0.0;
            this.volts[1] = -this.lastvac;
            this.volts[2] = -this.lastvag;
            this.triggerI = new Double(stringTokenizer.nextToken());
            this.holdingI = new Double(stringTokenizer.nextToken());
            this.cresistance = new Double(stringTokenizer.nextToken());
        }
        catch (Exception ex) {}
        this.setup();
    }
    
    void setDefaults() {
        this.cresistance = 50.0;
        this.holdingI = 0.0082;
        this.triggerI = 0.01;
    }
    
    void setup() {
        (this.diode = new Diode(SCRElm.sim)).setup(0.8, 0.0);
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
        this.diode.reset();
        final double lastvag = 0.0;
        this.curcount_g = lastvag;
        this.curcount_c = lastvag;
        this.curcount_a = lastvag;
        this.lastvac = lastvag;
        this.lastvag = lastvag;
    }
    
    int getDumpType() {
        return 177;
    }
    
    String dump() {
        return super.dump() + " " + (this.volts[0] - this.volts[1]) + " " + (this.volts[0] - this.volts[2]) + " " + this.triggerI + " " + this.holdingI + " " + this.cresistance;
    }
    
    void setPoints() {
        super.setPoints();
        int n;
        if (CircuitElm.abs(this.dx) > CircuitElm.abs(this.dy)) {
            n = -CircuitElm.sign(this.dx) * CircuitElm.sign(this.dy);
            this.point2.y = this.point1.y;
        }
        else {
            n = CircuitElm.sign(this.dy) * CircuitElm.sign(this.dx);
            this.point2.x = this.point1.x;
        }
        if (n == 0) {
            n = 1;
        }
        this.calcLeads(16);
        this.cathode = this.newPointArray(2);
        final Point[] pointArray = this.newPointArray(2);
        this.interpPoint2(this.lead1, this.lead2, pointArray[0], pointArray[1], 0.0, 8.0);
        this.interpPoint2(this.lead1, this.lead2, this.cathode[0], this.cathode[1], 1.0, 8.0);
        this.poly = this.createPolygon(pointArray[0], pointArray[1], this.lead2);
        this.gate = this.newPointArray(2);
        final double n2 = (this.dn - 16.0) / 2.0;
        final int n3 = (int)(SCRElm.sim.gridSize + n2 % SCRElm.sim.gridSize);
        if (n2 < n3) {
            this.x2 = this.x;
            this.y2 = this.y;
            return;
        }
        this.interpPoint(this.lead2, this.point2, this.gate[0], n3 / n2, n3 * n);
        this.interpPoint(this.lead2, this.point2, this.gate[1], n3 / n2, SCRElm.sim.gridSize * 2 * n);
    }
    
    void draw(final Graphics graphics) {
        this.setBbox(this.point1, this.point2, 8.0);
        this.adjustBbox(this.gate[0], this.gate[1]);
        final double n = this.volts[0];
        final double n2 = this.volts[1];
        this.draw2Leads(graphics);
        this.setPowerColor(graphics, true);
        this.setVoltageColor(graphics, n);
        graphics.fillPolygon(this.poly);
        this.setVoltageColor(graphics, n2);
        CircuitElm.drawThickLine(graphics, this.cathode[0], this.cathode[1]);
        CircuitElm.drawThickLine(graphics, this.lead2, this.gate[0]);
        CircuitElm.drawThickLine(graphics, this.gate[0], this.gate[1]);
        this.curcount_a = this.updateDotCount(this.ia, this.curcount_a);
        this.curcount_c = this.updateDotCount(this.ic, this.curcount_c);
        this.curcount_g = this.updateDotCount(this.ig, this.curcount_g);
        if (SCRElm.sim.dragElm != this) {
            this.drawDots(graphics, this.point1, this.lead2, this.curcount_a);
            this.drawDots(graphics, this.point2, this.lead2, this.curcount_c);
            this.drawDots(graphics, this.gate[1], this.gate[0], this.curcount_g);
            this.drawDots(graphics, this.gate[0], this.lead2, this.curcount_g + CircuitElm.distance(this.gate[1], this.gate[0]));
        }
        this.drawPosts(graphics);
    }
    
    Point getPost(final int n) {
        return (n == 0) ? this.point1 : ((n == 1) ? this.point2 : this.gate[1]);
    }
    
    int getPostCount() {
        return 3;
    }
    
    int getInternalNodeCount() {
        return 1;
    }
    
    double getPower() {
        return (this.volts[0] - this.volts[2]) * this.ia + (this.volts[1] - this.volts[2]) * this.ic;
    }
    
    void stamp() {
        SCRElm.sim.stampNonLinear(this.nodes[0]);
        SCRElm.sim.stampNonLinear(this.nodes[1]);
        SCRElm.sim.stampNonLinear(this.nodes[2]);
        SCRElm.sim.stampNonLinear(this.nodes[3]);
        SCRElm.sim.stampResistor(this.nodes[2], this.nodes[1], this.cresistance);
        this.diode.stamp(this.nodes[3], this.nodes[2]);
    }
    
    void doStep() {
        final double lastvac = this.volts[0] - this.volts[1];
        final double lastvag = this.volts[0] - this.volts[2];
        if (Math.abs(lastvac - this.lastvac) > 0.01 || Math.abs(lastvag - this.lastvag) > 0.01) {
            SCRElm.sim.converged = false;
        }
        this.lastvac = lastvac;
        this.lastvag = lastvag;
        this.diode.doStep(this.volts[3] - this.volts[2]);
        final double n = 1.0 / this.triggerI;
        this.aresistance = ((-n * this.ic + this.ia * (1.0 / this.holdingI - n) > 1.0) ? 0.0105 : 1000000.0);
        SCRElm.sim.stampResistor(this.nodes[0], this.nodes[3], this.aresistance);
    }
    
    void getInfo(final String[] array) {
        array[0] = "SCR";
        final double n = this.volts[0] - this.volts[1];
        final double n2 = this.volts[0] - this.volts[2];
        final double n3 = this.volts[2] - this.volts[1];
        array[1] = "Ia = " + CircuitElm.getCurrentText(this.ia);
        array[2] = "Ig = " + CircuitElm.getCurrentText(this.ig);
        array[3] = "Vac = " + CircuitElm.getVoltageText(n);
        array[4] = "Vag = " + CircuitElm.getVoltageText(n2);
        array[5] = "Vgc = " + CircuitElm.getVoltageText(n3);
    }
    
    void calculateCurrent() {
        this.ic = (this.volts[1] - this.volts[2]) / this.cresistance;
        this.ia = (this.volts[0] - this.volts[3]) / this.aresistance;
        this.ig = -this.ic - this.ia;
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Trigger Current (A)", this.triggerI, 0.0, 0.0);
        }
        if (n == 1) {
            return new EditInfo("Holding Current (A)", this.holdingI, 0.0, 0.0);
        }
        if (n == 2) {
            return new EditInfo("Gate-Cathode Resistance (ohms)", this.cresistance, 0.0, 0.0);
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0 && editInfo.value > 0.0) {
            this.triggerI = editInfo.value;
        }
        if (n == 1 && editInfo.value > 0.0) {
            this.holdingI = editInfo.value;
        }
        if (n == 2 && editInfo.value > 0.0) {
            this.cresistance = editInfo.value;
        }
    }
}
