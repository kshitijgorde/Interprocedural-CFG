import java.awt.Color;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class TriodeElm extends CircuitElm
{
    double mu;
    double kg1;
    double curcountp;
    double curcountc;
    double curcountg;
    double currentp;
    double currentg;
    double currentc;
    final double gridCurrentR = 6000.0;
    Point[] plate;
    Point[] grid;
    Point[] cath;
    Point midgrid;
    Point midcath;
    int circler;
    double lastv0;
    double lastv1;
    double lastv2;
    
    public TriodeElm(final int n, final int n2) {
        super(n, n2);
        this.mu = 93.0;
        this.kg1 = 680.0;
        this.setup();
    }
    
    public TriodeElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.mu = new Double(stringTokenizer.nextToken());
        this.kg1 = new Double(stringTokenizer.nextToken());
        this.setup();
    }
    
    void setup() {
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
        this.curcount = 0.0;
    }
    
    String dump() {
        return super.dump() + " " + this.mu + " " + this.kg1;
    }
    
    int getDumpType() {
        return 173;
    }
    
    void setPoints() {
        super.setPoints();
        this.plate = this.newPointArray(4);
        this.grid = this.newPointArray(8);
        this.cath = this.newPointArray(4);
        this.grid[0] = this.point1;
        final int n = 8;
        this.interpPoint(this.point1, this.point2, this.plate[1], 1.0, n);
        final int n2 = 32;
        this.interpPoint(this.point1, this.point2, this.plate[0], 1.0, n2);
        this.interpPoint2(this.point2, this.plate[1], this.plate[2], this.plate[3], 1.0, 18);
        this.circler = 24;
        this.interpPoint(this.point1, this.point2, this.grid[1], (this.dn - this.circler) / this.dn, 0.0);
        for (int i = 0; i != 3; ++i) {
            this.interpPoint(this.grid[1], this.point2, this.grid[2 + i * 2], (i * 3 + 1) / 4.5, 0.0);
            this.interpPoint(this.grid[1], this.point2, this.grid[3 + i * 2], (i * 3 + 2) / 4.5, 0.0);
        }
        this.midgrid = this.point2;
        final int n3 = 16;
        this.midcath = this.interpPoint(this.point1, this.point2, 1.0, -n);
        this.interpPoint2(this.point2, this.plate[1], this.cath[1], this.cath[2], -1.0, n3);
        this.interpPoint(this.point2, this.plate[1], this.cath[3], -1.2, -n3);
        this.interpPoint(this.point2, this.plate[1], this.cath[0], -n2 / n, n3);
    }
    
    void draw(final Graphics graphics) {
        graphics.setColor(Color.gray);
        CircuitElm.drawThickCircle(graphics, this.point2.x, this.point2.y, this.circler);
        this.setBbox(this.point1, this.plate[0], 16.0);
        this.adjustBbox(this.cath[0].x, this.cath[1].y, this.point2.x + this.circler, this.point2.y + this.circler);
        this.setPowerColor(graphics, true);
        this.setVoltageColor(graphics, this.volts[0]);
        CircuitElm.drawThickLine(graphics, this.plate[0], this.plate[1]);
        CircuitElm.drawThickLine(graphics, this.plate[2], this.plate[3]);
        this.setVoltageColor(graphics, this.volts[1]);
        for (int i = 0; i != 8; i += 2) {
            CircuitElm.drawThickLine(graphics, this.grid[i], this.grid[i + 1]);
        }
        this.setVoltageColor(graphics, this.volts[2]);
        for (int j = 0; j != 3; ++j) {
            CircuitElm.drawThickLine(graphics, this.cath[j], this.cath[j + 1]);
        }
        this.curcountp = this.updateDotCount(this.currentp, this.curcountp);
        this.curcountc = this.updateDotCount(this.currentc, this.curcountc);
        this.curcountg = this.updateDotCount(this.currentg, this.curcountg);
        if (TriodeElm.sim.dragElm != this) {
            this.drawDots(graphics, this.plate[0], this.midgrid, this.curcountp);
            this.drawDots(graphics, this.midgrid, this.midcath, this.curcountc);
            this.drawDots(graphics, this.midcath, this.cath[1], this.curcountc + 8.0);
            this.drawDots(graphics, this.cath[1], this.cath[0], this.curcountc + 8.0);
            this.drawDots(graphics, this.point1, this.midgrid, this.curcountg);
        }
        this.drawPosts(graphics);
    }
    
    Point getPost(final int n) {
        return (n == 0) ? this.plate[0] : ((n == 1) ? this.grid[0] : this.cath[0]);
    }
    
    int getPostCount() {
        return 3;
    }
    
    double getPower() {
        return (this.volts[0] - this.volts[2]) * this.current;
    }
    
    void doStep() {
        final double[] array = { this.volts[0], this.volts[1], this.volts[2] };
        if (array[1] > this.lastv1 + 0.5) {
            array[1] = this.lastv1 + 0.5;
        }
        if (array[1] < this.lastv1 - 0.5) {
            array[1] = this.lastv1 - 0.5;
        }
        if (array[2] > this.lastv2 + 0.5) {
            array[2] = this.lastv2 + 0.5;
        }
        if (array[2] < this.lastv2 - 0.5) {
            array[2] = this.lastv2 - 0.5;
        }
        final int n = 1;
        final int n2 = 2;
        final int n3 = 0;
        final double n4 = array[n] - array[n2];
        final double n5 = array[n3] - array[n2];
        if (Math.abs(this.lastv0 - array[0]) > 0.01 || Math.abs(this.lastv1 - array[1]) > 0.01 || Math.abs(this.lastv2 - array[2]) > 0.01) {
            TriodeElm.sim.converged = false;
        }
        this.lastv0 = array[0];
        this.lastv1 = array[1];
        this.lastv2 = array[2];
        double n6 = 0.0;
        final double n7 = n4 + n5 / this.mu;
        this.currentg = 0.0;
        if (n4 > 0.01) {
            TriodeElm.sim.stampResistor(this.nodes[n], this.nodes[n2], 6000.0);
            this.currentg = n4 / 6000.0;
        }
        double n8;
        double currentp;
        if (n7 < 0.0) {
            n8 = 1.0E-8;
            currentp = n5 * n8;
        }
        else {
            currentp = Math.pow(n7, 1.5) / this.kg1;
            n6 = (n8 = 1.5 * Math.sqrt(n7) / this.kg1) / this.mu;
        }
        this.currentp = currentp;
        this.currentc = currentp + this.currentg;
        final double n9 = -currentp + n8 * n5 + n6 * n4;
        TriodeElm.sim.stampMatrix(this.nodes[n3], this.nodes[n3], n8);
        TriodeElm.sim.stampMatrix(this.nodes[n3], this.nodes[n2], -n8 - n6);
        TriodeElm.sim.stampMatrix(this.nodes[n3], this.nodes[n], n6);
        TriodeElm.sim.stampMatrix(this.nodes[n2], this.nodes[n3], -n8);
        TriodeElm.sim.stampMatrix(this.nodes[n2], this.nodes[n2], n8 + n6);
        TriodeElm.sim.stampMatrix(this.nodes[n2], this.nodes[n], -n6);
        TriodeElm.sim.stampRightSide(this.nodes[n3], n9);
        TriodeElm.sim.stampRightSide(this.nodes[n2], -n9);
    }
    
    void stamp() {
        TriodeElm.sim.stampNonLinear(this.nodes[0]);
        TriodeElm.sim.stampNonLinear(this.nodes[1]);
        TriodeElm.sim.stampNonLinear(this.nodes[2]);
    }
    
    void getInfo(final String[] array) {
        array[0] = "triode";
        final double n = this.volts[0] - this.volts[1];
        final double n2 = this.volts[0] - this.volts[2];
        final double n3 = this.volts[1] - this.volts[2];
        array[1] = "Vbe = " + CircuitElm.getVoltageText(n2);
        array[2] = "Vbc = " + CircuitElm.getVoltageText(n);
        array[3] = "Vce = " + CircuitElm.getVoltageText(n3);
    }
    
    boolean getConnection(final int n, final int n2) {
        return n != 1 && n2 != 1;
    }
}
