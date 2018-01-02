import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class RelayElm extends CircuitElm
{
    double inductance;
    Inductor ind;
    double r_on;
    double r_off;
    double onCurrent;
    Point[] coilPosts;
    Point[] coilLeads;
    Point[][] swposts;
    Point[][] swpoles;
    Point[] ptSwitch;
    Point[] lines;
    double coilCurrent;
    double[] switchCurrent;
    double coilCurCount;
    double[] switchCurCount;
    double d_position;
    double coilR;
    int i_position;
    int poleCount;
    int openhs;
    final int nSwitch0 = 0;
    final int nSwitch1 = 1;
    final int nSwitch2 = 2;
    int nCoil1;
    int nCoil2;
    int nCoil3;
    final int FLAG_SWAP_COIL = 1;
    double a1;
    double a2;
    double a3;
    double a4;
    
    public RelayElm(final int n, final int n2) {
        super(n, n2);
        this.ind = new Inductor(RelayElm.sim);
        this.inductance = 0.2;
        this.ind.setup(this.inductance, 0.0, 2);
        this.noDiagonal = true;
        this.onCurrent = 0.02;
        this.r_on = 0.05;
        this.r_off = 1000000.0;
        this.coilR = 20.0;
        final double n3 = 0.0;
        this.coilCurCount = n3;
        this.coilCurrent = n3;
        this.poleCount = 1;
        this.setupPoles();
    }
    
    public RelayElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.poleCount = new Integer(stringTokenizer.nextToken());
        this.inductance = new Double(stringTokenizer.nextToken());
        this.coilCurrent = new Double(stringTokenizer.nextToken());
        this.r_on = new Double(stringTokenizer.nextToken());
        this.r_off = new Double(stringTokenizer.nextToken());
        this.onCurrent = new Double(stringTokenizer.nextToken());
        this.coilR = new Double(stringTokenizer.nextToken());
        this.noDiagonal = true;
        (this.ind = new Inductor(RelayElm.sim)).setup(this.inductance, this.coilCurrent, 2);
        this.setupPoles();
    }
    
    void setupPoles() {
        this.nCoil1 = 3 * this.poleCount;
        this.nCoil2 = this.nCoil1 + 1;
        this.nCoil3 = this.nCoil1 + 2;
        if (this.switchCurrent == null || this.switchCurrent.length != this.poleCount) {
            this.switchCurrent = new double[this.poleCount];
            this.switchCurCount = new double[this.poleCount];
        }
    }
    
    int getDumpType() {
        return 178;
    }
    
    String dump() {
        return super.dump() + " " + this.poleCount + " " + this.inductance + " " + this.coilCurrent + " " + this.r_on + " " + this.r_off + " " + this.onCurrent + " " + this.coilR;
    }
    
    void draw(final Graphics graphics) {
        for (int i = 0; i != 2; ++i) {
            this.setVoltageColor(graphics, this.volts[this.nCoil1 + i]);
            CircuitElm.drawThickLine(graphics, this.coilLeads[i], this.coilPosts[i]);
        }
        final int n = ((this.flags & 0x1) != 0x0) ? 1 : 0;
        this.drawCoil(graphics, this.dsign * 6, this.coilLeads[n], this.coilLeads[1 - n], this.volts[this.nCoil1 + n], this.volts[this.nCoil2 - n]);
        graphics.setColor(Color.darkGray);
        for (int j = 0; j != this.poleCount; ++j) {
            if (j == 0) {
                this.interpPoint(this.point1, this.point2, this.lines[j * 2], 0.5, this.openhs * 2 + 5 * this.dsign - j * this.openhs * 3);
            }
            else {
                this.interpPoint(this.point1, this.point2, this.lines[j * 2], 0.5, (int)(this.openhs * (-j * 3 + 3 - 0.5 + this.d_position)) + 5 * this.dsign);
            }
            this.interpPoint(this.point1, this.point2, this.lines[j * 2 + 1], 0.5, (int)(this.openhs * (-j * 3 - 0.5 + this.d_position)) - 5 * this.dsign);
            graphics.drawLine(this.lines[j * 2].x, this.lines[j * 2].y, this.lines[j * 2 + 1].x, this.lines[j * 2 + 1].y);
        }
        for (int k = 0; k != this.poleCount; ++k) {
            final int n2 = k * 3;
            for (int l = 0; l != 3; ++l) {
                this.setVoltageColor(graphics, this.volts[0 + n2 + l]);
                CircuitElm.drawThickLine(graphics, this.swposts[k][l], this.swpoles[k][l]);
            }
            this.interpPoint(this.swpoles[k][1], this.swpoles[k][2], this.ptSwitch[k], this.d_position);
            graphics.setColor(Color.lightGray);
            CircuitElm.drawThickLine(graphics, this.swpoles[k][0], this.ptSwitch[k]);
            this.switchCurCount[k] = this.updateDotCount(this.switchCurrent[k], this.switchCurCount[k]);
            this.drawDots(graphics, this.swposts[k][0], this.swpoles[k][0], this.switchCurCount[k]);
            if (this.i_position != 2) {
                this.drawDots(graphics, this.swpoles[k][this.i_position + 1], this.swposts[k][this.i_position + 1], this.switchCurCount[k]);
            }
        }
        this.coilCurCount = this.updateDotCount(this.coilCurrent, this.coilCurCount);
        this.drawDots(graphics, this.coilPosts[0], this.coilLeads[0], this.coilCurCount);
        this.drawDots(graphics, this.coilLeads[0], this.coilLeads[1], this.coilCurCount);
        this.drawDots(graphics, this.coilLeads[1], this.coilPosts[1], this.coilCurCount);
        this.drawPosts(graphics);
        this.setBbox(this.coilPosts[0], this.coilLeads[1], 0.0);
        this.adjustBbox(this.swpoles[this.poleCount - 1][0], this.swposts[this.poleCount - 1][1]);
    }
    
    void setPoints() {
        super.setPoints();
        this.setupPoles();
        this.allocNodes();
        this.openhs = -this.dsign * 16;
        this.calcLeads(32);
        this.swposts = new Point[this.poleCount][3];
        this.swpoles = new Point[this.poleCount][3];
        for (int i = 0; i != this.poleCount; ++i) {
            for (int j = 0; j != 3; ++j) {
                this.swposts[i][j] = new Point();
                this.swpoles[i][j] = new Point();
            }
            this.interpPoint(this.lead1, this.lead2, this.swpoles[i][0], 0.0, -this.openhs * 3 * i);
            this.interpPoint(this.lead1, this.lead2, this.swpoles[i][1], 1.0, -this.openhs * 3 * i - this.openhs);
            this.interpPoint(this.lead1, this.lead2, this.swpoles[i][2], 1.0, -this.openhs * 3 * i + this.openhs);
            this.interpPoint(this.point1, this.point2, this.swposts[i][0], 0.0, -this.openhs * 3 * i);
            this.interpPoint(this.point1, this.point2, this.swposts[i][1], 1.0, -this.openhs * 3 * i - this.openhs);
            this.interpPoint(this.point1, this.point2, this.swposts[i][2], 1.0, -this.openhs * 3 * i + this.openhs);
        }
        this.coilPosts = this.newPointArray(2);
        this.coilLeads = this.newPointArray(2);
        this.ptSwitch = this.newPointArray(this.poleCount);
        final boolean b = (this.flags & 0x1) != 0x0;
        this.interpPoint(this.point1, this.point2, this.coilPosts[0], b ? 1 : 0, this.openhs * 2);
        this.interpPoint(this.point1, this.point2, this.coilPosts[1], b ? 1 : 0, this.openhs * 3);
        this.interpPoint(this.point1, this.point2, this.coilLeads[0], 0.5, this.openhs * 2);
        this.interpPoint(this.point1, this.point2, this.coilLeads[1], 0.5, this.openhs * 3);
        this.lines = this.newPointArray(this.poleCount * 2);
    }
    
    Point getPost(final int n) {
        if (n < 3 * this.poleCount) {
            return this.swposts[n / 3][n % 3];
        }
        return this.coilPosts[n - 3 * this.poleCount];
    }
    
    int getPostCount() {
        return 2 + this.poleCount * 3;
    }
    
    int getInternalNodeCount() {
        return 1;
    }
    
    void reset() {
        super.reset();
        this.ind.reset();
        final double n = 0.0;
        this.coilCurCount = n;
        this.coilCurrent = n;
        for (int i = 0; i != this.poleCount; ++i) {
            this.switchCurrent[i] = (this.switchCurCount[i] = 0.0);
        }
    }
    
    void stamp() {
        this.ind.stamp(this.nodes[this.nCoil1], this.nodes[this.nCoil3]);
        RelayElm.sim.stampResistor(this.nodes[this.nCoil3], this.nodes[this.nCoil2], this.coilR);
        for (int i = 0; i != this.poleCount * 3; ++i) {
            RelayElm.sim.stampNonLinear(this.nodes[0 + i]);
        }
    }
    
    void startIteration() {
        this.ind.startIteration(this.volts[this.nCoil1] - this.volts[this.nCoil3]);
        final double n = this.coilCurrent * Math.sqrt(1.3 + 1.0) / this.onCurrent;
        this.d_position = Math.abs(n * n) - 1.3;
        if (this.d_position < 0.0) {
            this.d_position = 0.0;
        }
        if (this.d_position > 1.0) {
            this.d_position = 1.0;
        }
        if (this.d_position < 0.1) {
            this.i_position = 0;
        }
        else if (this.d_position > 0.9) {
            this.i_position = 1;
        }
        else {
            this.i_position = 2;
        }
    }
    
    boolean nonLinear() {
        return true;
    }
    
    void doStep() {
        this.ind.doStep(this.volts[this.nCoil1] - this.volts[this.nCoil3]);
        for (int i = 0; i != this.poleCount * 3; i += 3) {
            RelayElm.sim.stampResistor(this.nodes[0 + i], this.nodes[1 + i], (this.i_position == 0) ? this.r_on : this.r_off);
            RelayElm.sim.stampResistor(this.nodes[0 + i], this.nodes[2 + i], (this.i_position == 1) ? this.r_on : this.r_off);
        }
    }
    
    void calculateCurrent() {
        this.coilCurrent = this.ind.calculateCurrent(this.volts[this.nCoil1] - this.volts[this.nCoil3]);
        for (int i = 0; i != this.poleCount; ++i) {
            if (this.i_position == 2) {
                this.switchCurrent[i] = 0.0;
            }
            else {
                this.switchCurrent[i] = (this.volts[0 + i * 3] - this.volts[1 + i * 3 + this.i_position]) / this.r_on;
            }
        }
    }
    
    void getInfo(final String[] array) {
        array[0] = ((this.i_position == 0) ? "relay (off)" : ((this.i_position == 1) ? "relay (on)" : "relay"));
        int n = 1;
        for (int i = 0; i != this.poleCount; ++i) {
            array[n++] = "I" + (i + 1) + " = " + CircuitElm.getCurrentDText(this.switchCurrent[i]);
        }
        array[n++] = "coil I = " + CircuitElm.getCurrentDText(this.coilCurrent);
        array[n++] = "coil Vd = " + CircuitElm.getVoltageDText(this.volts[this.nCoil1] - this.volts[this.nCoil2]);
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Inductance (H)", this.inductance, 0.0, 0.0);
        }
        if (n == 1) {
            return new EditInfo("On Resistance (ohms)", this.r_on, 0.0, 0.0);
        }
        if (n == 2) {
            return new EditInfo("Off Resistance (ohms)", this.r_off, 0.0, 0.0);
        }
        if (n == 3) {
            return new EditInfo("On Current (A)", this.onCurrent, 0.0, 0.0);
        }
        if (n == 4) {
            return new EditInfo("Number of Poles", this.poleCount, 1.0, 4.0).setDimensionless();
        }
        if (n == 5) {
            return new EditInfo("Coil Resistance (ohms)", this.coilR, 0.0, 0.0);
        }
        if (n == 6) {
            final EditInfo editInfo = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo.checkbox = new Checkbox("Swap Coil Direction", (this.flags & 0x1) != 0x0);
            return editInfo;
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0 && editInfo.value > 0.0) {
            this.inductance = editInfo.value;
            this.ind.setup(this.inductance, this.coilCurrent, 2);
        }
        if (n == 1 && editInfo.value > 0.0) {
            this.r_on = editInfo.value;
        }
        if (n == 2 && editInfo.value > 0.0) {
            this.r_off = editInfo.value;
        }
        if (n == 3 && editInfo.value > 0.0) {
            this.onCurrent = editInfo.value;
        }
        if (n == 4 && editInfo.value >= 1.0) {
            this.poleCount = (int)editInfo.value;
            this.setPoints();
        }
        if (n == 5 && editInfo.value > 0.0) {
            this.coilR = editInfo.value;
        }
        if (n == 6) {
            if (editInfo.checkbox.getState()) {
                this.flags |= 0x1;
            }
            else {
                this.flags &= 0xFFFFFFFE;
            }
            this.setPoints();
        }
    }
    
    boolean getConnection(final int n, final int n2) {
        return n / 3 == n2 / 3;
    }
}
