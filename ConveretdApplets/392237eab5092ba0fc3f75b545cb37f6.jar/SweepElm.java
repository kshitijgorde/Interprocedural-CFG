import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Graphics;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class SweepElm extends CircuitElm
{
    double maxV;
    double maxF;
    double minF;
    double sweepTime;
    double frequency;
    final int FLAG_LOG = 1;
    final int FLAG_BIDIR = 2;
    final int circleSize = 17;
    double fadd;
    double fmul;
    double freqTime;
    double savedTimeStep;
    int dir;
    double v;
    
    public SweepElm(final int n, final int n2) {
        super(n, n2);
        this.dir = 1;
        this.minF = 20.0;
        this.maxF = 4000.0;
        this.maxV = 5.0;
        this.sweepTime = 0.1;
        this.flags = 2;
        this.reset();
    }
    
    public SweepElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.dir = 1;
        this.minF = new Double(stringTokenizer.nextToken());
        this.maxF = new Double(stringTokenizer.nextToken());
        this.maxV = new Double(stringTokenizer.nextToken());
        this.sweepTime = new Double(stringTokenizer.nextToken());
        this.reset();
    }
    
    int getDumpType() {
        return 170;
    }
    
    int getPostCount() {
        return 1;
    }
    
    String dump() {
        return super.dump() + " " + this.minF + " " + this.maxF + " " + this.maxV + " " + this.sweepTime;
    }
    
    void setPoints() {
        super.setPoints();
        this.lead1 = this.interpPoint(this.point1, this.point2, 1.0 - 17.0 / this.dn);
    }
    
    void draw(final Graphics graphics) {
        this.setBbox(this.point1, this.point2, 17.0);
        this.setVoltageColor(graphics, this.volts[0]);
        CircuitElm.drawThickLine(graphics, this.point1, this.lead1);
        graphics.setColor(this.needsHighlight() ? SweepElm.selectColor : Color.gray);
        this.setPowerColor(graphics, false);
        final int x = this.point2.x;
        final int y = this.point2.y;
        CircuitElm.drawThickCircle(graphics, x, y, 17);
        final int n = 8;
        this.adjustBbox(x - 17, y - 17, x + 17, y + 17);
        final int n2 = 10;
        int n3 = -1;
        int n4 = -1;
        long n5 = System.currentTimeMillis() % 2000L;
        if (n5 > 1000L) {
            n5 = 2000L - n5;
        }
        double n6 = 1.0 + n5 * 0.002;
        if (!SweepElm.sim.stoppedCheck.getState()) {
            n6 = 1.0 + 2.0 * (this.frequency - this.minF) / (this.maxF - this.minF);
        }
        for (int i = -n2; i <= n2; ++i) {
            final int n7 = y + (int)(0.95 * Math.sin(i * 3.141592653589793 * n6 / n2) * n);
            if (n3 != -1) {
                CircuitElm.drawThickLine(graphics, n3, n4, x + i, n7);
            }
            n3 = x + i;
            n4 = n7;
        }
        if (SweepElm.sim.showValuesCheckItem.getState()) {
            final String shortUnitText = CircuitElm.getShortUnitText(this.frequency, "Hz");
            if (this.dx == 0 || this.dy == 0) {
                this.drawValues(graphics, shortUnitText, 17.0);
            }
        }
        this.drawPosts(graphics);
        this.curcount = this.updateDotCount(-this.current, this.curcount);
        if (SweepElm.sim.dragElm != this) {
            this.drawDots(graphics, this.point1, this.lead1, this.curcount);
        }
    }
    
    void stamp() {
        SweepElm.sim.stampVoltageSource(0, this.nodes[0], this.voltSource);
    }
    
    void setParams() {
        if (this.frequency < this.minF || this.frequency > this.maxF) {
            this.frequency = this.minF;
            this.freqTime = 0.0;
            this.dir = 1;
        }
        if ((this.flags & 0x1) == 0x0) {
            this.fadd = this.dir * SweepElm.sim.timeStep * (this.maxF - this.minF) / this.sweepTime;
            this.fmul = 1.0;
        }
        else {
            this.fadd = 0.0;
            this.fmul = Math.pow(this.maxF / this.minF, this.dir * SweepElm.sim.timeStep / this.sweepTime);
        }
        this.savedTimeStep = SweepElm.sim.timeStep;
    }
    
    void reset() {
        this.frequency = this.minF;
        this.freqTime = 0.0;
        this.dir = 1;
        this.setParams();
    }
    
    void startIteration() {
        if (SweepElm.sim.timeStep != this.savedTimeStep) {
            this.setParams();
        }
        this.v = Math.sin(this.freqTime) * this.maxV;
        this.freqTime += this.frequency * 2.0 * 3.141592653589793 * SweepElm.sim.timeStep;
        this.frequency = this.frequency * this.fmul + this.fadd;
        if (this.frequency >= this.maxF && this.dir == 1) {
            if ((this.flags & 0x2) != 0x0) {
                this.fadd = -this.fadd;
                this.fmul = 1.0 / this.fmul;
                this.dir = -1;
            }
            else {
                this.frequency = this.minF;
            }
        }
        if (this.frequency <= this.minF && this.dir == -1) {
            this.fadd = -this.fadd;
            this.fmul = 1.0 / this.fmul;
            this.dir = 1;
        }
    }
    
    void doStep() {
        SweepElm.sim.updateVoltageSource(0, this.nodes[0], this.voltSource, this.v);
    }
    
    double getVoltageDiff() {
        return this.volts[0];
    }
    
    int getVoltageSourceCount() {
        return 1;
    }
    
    boolean hasGroundConnection(final int n) {
        return true;
    }
    
    void getInfo(final String[] array) {
        array[0] = "sweep " + (((this.flags & 0x1) == 0x0) ? "(linear)" : "(log)");
        array[1] = "I = " + CircuitElm.getCurrentDText(this.getCurrent());
        array[2] = "V = " + CircuitElm.getVoltageText(this.volts[0]);
        array[3] = "f = " + CircuitElm.getUnitText(this.frequency, "Hz");
        array[4] = "range = " + CircuitElm.getUnitText(this.minF, "Hz") + " .. " + CircuitElm.getUnitText(this.maxF, "Hz");
        array[5] = "time = " + CircuitElm.getUnitText(this.sweepTime, "s");
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Min Frequency (Hz)", this.minF, 0.0, 0.0);
        }
        if (n == 1) {
            return new EditInfo("Max Frequency (Hz)", this.maxF, 0.0, 0.0);
        }
        if (n == 2) {
            return new EditInfo("Sweep Time (s)", this.sweepTime, 0.0, 0.0);
        }
        if (n == 3) {
            final EditInfo editInfo = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo.checkbox = new Checkbox("Logarithmic", (this.flags & 0x1) != 0x0);
            return editInfo;
        }
        if (n == 4) {
            return new EditInfo("Max Voltage", this.maxV, 0.0, 0.0);
        }
        if (n == 5) {
            final EditInfo editInfo2 = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo2.checkbox = new Checkbox("Bidirectional", (this.flags & 0x2) != 0x0);
            return editInfo2;
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        final double n2 = 1.0 / (8.0 * SweepElm.sim.timeStep);
        if (n == 0) {
            this.minF = editInfo.value;
            if (this.minF > n2) {
                this.minF = n2;
            }
        }
        if (n == 1) {
            this.maxF = editInfo.value;
            if (this.maxF > n2) {
                this.maxF = n2;
            }
        }
        if (n == 2) {
            this.sweepTime = editInfo.value;
        }
        if (n == 3) {
            this.flags &= 0xFFFFFFFE;
            if (editInfo.checkbox.getState()) {
                this.flags |= 0x1;
            }
        }
        if (n == 4) {
            this.maxV = editInfo.value;
        }
        if (n == 5) {
            this.flags &= 0xFFFFFFFD;
            if (editInfo.checkbox.getState()) {
                this.flags |= 0x2;
            }
        }
        this.setParams();
    }
}
