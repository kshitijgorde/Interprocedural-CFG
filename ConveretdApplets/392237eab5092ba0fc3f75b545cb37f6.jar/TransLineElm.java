import java.awt.Color;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class TransLineElm extends CircuitElm
{
    double delay;
    double imped;
    double[] voltageL;
    double[] voltageR;
    int lenSteps;
    int ptr;
    int width;
    Point[] posts;
    Point[] inner;
    int voltSource1;
    int voltSource2;
    double current1;
    double current2;
    double curCount1;
    double curCount2;
    
    public TransLineElm(final int n, final int n2) {
        super(n, n2);
        this.delay = 1000.0 * TransLineElm.sim.timeStep;
        this.imped = 75.0;
        this.noDiagonal = true;
        this.reset();
    }
    
    public TransLineElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.delay = new Double(stringTokenizer.nextToken());
        this.imped = new Double(stringTokenizer.nextToken());
        this.width = new Integer(stringTokenizer.nextToken());
        stringTokenizer.nextToken();
        this.noDiagonal = true;
        this.reset();
    }
    
    int getDumpType() {
        return 171;
    }
    
    int getPostCount() {
        return 4;
    }
    
    int getInternalNodeCount() {
        return 2;
    }
    
    String dump() {
        return super.dump() + " " + this.delay + " " + this.imped + " " + this.width + " " + 0.0;
    }
    
    void drag(int x2, int y2) {
        x2 = TransLineElm.sim.snapGrid(x2);
        y2 = TransLineElm.sim.snapGrid(y2);
        final int max = CircuitElm.max(TransLineElm.sim.gridSize, CircuitElm.abs(y2 - this.y));
        final int max2 = CircuitElm.max(TransLineElm.sim.gridSize, CircuitElm.abs(x2 - this.x));
        if (max > max2) {
            x2 = this.x;
            this.width = max2;
        }
        else {
            y2 = this.y;
            this.width = max;
        }
        this.x2 = x2;
        this.y2 = y2;
        this.setPoints();
    }
    
    void reset() {
        if (TransLineElm.sim.timeStep == 0.0) {
            return;
        }
        this.lenSteps = (int)(this.delay / TransLineElm.sim.timeStep);
        System.out.println(this.lenSteps + " steps");
        if (this.lenSteps > 100000) {
            final double[] array = null;
            this.voltageR = array;
            this.voltageL = array;
        }
        else {
            this.voltageL = new double[this.lenSteps];
            this.voltageR = new double[this.lenSteps];
        }
        this.ptr = 0;
        super.reset();
    }
    
    void setPoints() {
        super.setPoints();
        final int n = (this.dy == 0) ? CircuitElm.sign(this.dx) : (-CircuitElm.sign(this.dy));
        final Point interpPoint = this.interpPoint(this.point1, this.point2, 0.0, -this.width * n);
        final Point interpPoint2 = this.interpPoint(this.point1, this.point2, 1.0, -this.width * n);
        final int n2 = TransLineElm.sim.gridSize / 2;
        final Point interpPoint3 = this.interpPoint(this.point1, this.point2, 0.0, -(this.width / 2 - n2) * n);
        final Point interpPoint4 = this.interpPoint(this.point1, this.point2, 1.0, -(this.width / 2 - n2) * n);
        final Point interpPoint5 = this.interpPoint(this.point1, this.point2, 0.0, -(this.width / 2 + n2) * n);
        final Point interpPoint6 = this.interpPoint(this.point1, this.point2, 1.0, -(this.width / 2 + n2) * n);
        this.posts = new Point[] { interpPoint, interpPoint2, this.point1, this.point2 };
        this.inner = new Point[] { interpPoint5, interpPoint6, interpPoint3, interpPoint4 };
    }
    
    void draw(final Graphics graphics) {
        this.setBbox(this.posts[0], this.posts[3], 0.0);
        final int n = (int)(this.dn / 2.0);
        final int n2 = this.ptr - 1 + this.lenSteps;
        final double n3 = 1.0 / n;
        graphics.setColor(Color.darkGray);
        graphics.fillRect(this.inner[2].x, this.inner[2].y, this.inner[1].x - this.inner[2].x + 2, this.inner[1].y - this.inner[2].y + 2);
        for (int i = 0; i != 4; ++i) {
            this.setVoltageColor(graphics, this.volts[i]);
            CircuitElm.drawThickLine(graphics, this.posts[i], this.inner[i]);
        }
        if (this.voltageL != null) {
            for (int j = 0; j != n; ++j) {
                this.setVoltageColor(graphics, (this.voltageL[(n2 - this.lenSteps * j / n) % this.lenSteps] + this.voltageR[(n2 - this.lenSteps * (n - 1 - j) / n) % this.lenSteps]) / 2.0);
                this.interpPoint(this.inner[0], this.inner[1], TransLineElm.ps1, j * n3);
                this.interpPoint(this.inner[2], this.inner[3], TransLineElm.ps2, j * n3);
                graphics.drawLine(TransLineElm.ps1.x, TransLineElm.ps1.y, TransLineElm.ps2.x, TransLineElm.ps2.y);
                this.interpPoint(this.inner[2], this.inner[3], TransLineElm.ps1, (j + 1) * n3);
                CircuitElm.drawThickLine(graphics, TransLineElm.ps1, TransLineElm.ps2);
            }
        }
        this.setVoltageColor(graphics, this.volts[0]);
        CircuitElm.drawThickLine(graphics, this.inner[0], this.inner[1]);
        this.drawPosts(graphics);
        this.curCount1 = this.updateDotCount(-this.current1, this.curCount1);
        this.curCount2 = this.updateDotCount(this.current2, this.curCount2);
        if (TransLineElm.sim.dragElm != this) {
            this.drawDots(graphics, this.posts[0], this.inner[0], this.curCount1);
            this.drawDots(graphics, this.posts[2], this.inner[2], -this.curCount1);
            this.drawDots(graphics, this.posts[1], this.inner[1], -this.curCount2);
            this.drawDots(graphics, this.posts[3], this.inner[3], this.curCount2);
        }
    }
    
    void setVoltageSource(final int n, final int n2) {
        if (n == 0) {
            this.voltSource1 = n2;
        }
        else {
            this.voltSource2 = n2;
        }
    }
    
    void setCurrent(final int n, final double n2) {
        if (n == this.voltSource1) {
            this.current1 = n2;
        }
        else {
            this.current2 = n2;
        }
    }
    
    void stamp() {
        TransLineElm.sim.stampVoltageSource(this.nodes[4], this.nodes[0], this.voltSource1);
        TransLineElm.sim.stampVoltageSource(this.nodes[5], this.nodes[1], this.voltSource2);
        TransLineElm.sim.stampResistor(this.nodes[2], this.nodes[4], this.imped);
        TransLineElm.sim.stampResistor(this.nodes[3], this.nodes[5], this.imped);
    }
    
    void startIteration() {
        if (this.voltageL == null) {
            TransLineElm.sim.stop("Transmission line delay too large!", this);
            return;
        }
        this.voltageL[this.ptr] = this.volts[2] - this.volts[0] + this.volts[2] - this.volts[4];
        this.voltageR[this.ptr] = this.volts[3] - this.volts[1] + this.volts[3] - this.volts[5];
        this.ptr = (this.ptr + 1) % this.lenSteps;
    }
    
    void doStep() {
        if (this.voltageL == null) {
            TransLineElm.sim.stop("Transmission line delay too large!", this);
            return;
        }
        TransLineElm.sim.updateVoltageSource(this.nodes[4], this.nodes[0], this.voltSource1, -this.voltageR[this.ptr]);
        TransLineElm.sim.updateVoltageSource(this.nodes[5], this.nodes[1], this.voltSource2, -this.voltageL[this.ptr]);
        if (Math.abs(this.volts[0]) > 1.0E-5 || Math.abs(this.volts[1]) > 1.0E-5) {
            TransLineElm.sim.stop("Need to ground transmission line!", this);
        }
    }
    
    Point getPost(final int n) {
        return this.posts[n];
    }
    
    int getVoltageSourceCount() {
        return 2;
    }
    
    boolean hasGroundConnection(final int n) {
        return false;
    }
    
    boolean getConnection(final int n, final int n2) {
        return false;
    }
    
    void getInfo(final String[] array) {
        array[0] = "transmission line";
        final int n = 1;
        final double imped = this.imped;
        final CirSim sim = TransLineElm.sim;
        array[n] = CircuitElm.getUnitText(imped, CirSim.ohmString);
        array[2] = "length = " + CircuitElm.getUnitText(2.9979E8 * this.delay, "m");
        array[3] = "delay = " + CircuitElm.getUnitText(this.delay, "s");
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Delay (s)", this.delay, 0.0, 0.0);
        }
        if (n == 1) {
            return new EditInfo("Impedance (ohms)", this.imped, 0.0, 0.0);
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.delay = editInfo.value;
            this.reset();
        }
        if (n == 1) {
            this.imped = editInfo.value;
            this.reset();
        }
    }
}
