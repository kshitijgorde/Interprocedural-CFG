import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Polygon;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class MosfetElm extends CircuitElm
{
    int pnp;
    int FLAG_PNP;
    int FLAG_SHOWVT;
    int FLAG_DIGITAL;
    double vt;
    final int hs = 16;
    int pcircler;
    Point[] src;
    Point[] drn;
    Point[] gate;
    Point pcircle;
    Polygon arrowPoly;
    double lastv1;
    double lastv2;
    double ids;
    int mode;
    double gm;
    
    MosfetElm(final int n, final int n2, final boolean b) {
        super(n, n2);
        this.FLAG_PNP = 1;
        this.FLAG_SHOWVT = 2;
        this.FLAG_DIGITAL = 4;
        this.mode = 0;
        this.gm = 0.0;
        this.pnp = (b ? -1 : 1);
        this.flags = (b ? this.FLAG_PNP : 0);
        this.noDiagonal = true;
        this.vt = this.getDefaultThreshold();
    }
    
    public MosfetElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.FLAG_PNP = 1;
        this.FLAG_SHOWVT = 2;
        this.FLAG_DIGITAL = 4;
        this.mode = 0;
        this.gm = 0.0;
        this.pnp = (((n5 & this.FLAG_PNP) != 0x0) ? -1 : 1);
        this.noDiagonal = true;
        this.vt = this.getDefaultThreshold();
        try {
            this.vt = new Double(stringTokenizer.nextToken());
        }
        catch (Exception ex) {}
    }
    
    double getDefaultThreshold() {
        return 1.5;
    }
    
    double getBeta() {
        return 0.02;
    }
    
    boolean nonLinear() {
        return true;
    }
    
    boolean drawDigital() {
        return (this.flags & this.FLAG_DIGITAL) != 0x0;
    }
    
    void reset() {
        final double[] volts = this.volts;
        final int n = 0;
        final double[] volts2 = this.volts;
        final int n2 = 1;
        final double[] volts3 = this.volts;
        final int n3 = 2;
        final double lastv1 = 0.0;
        volts3[n3] = (this.curcount = lastv1);
        volts[n] = (volts2[n2] = lastv1);
        this.lastv2 = lastv1;
        this.lastv1 = lastv1;
    }
    
    String dump() {
        return super.dump() + " " + this.vt;
    }
    
    int getDumpType() {
        return 102;
    }
    
    void draw(final Graphics graphics) {
        this.setBbox(this.point1, this.point2, 16.0);
        this.setVoltageColor(graphics, this.volts[1]);
        CircuitElm.drawThickLine(graphics, this.src[0], this.src[1]);
        this.setVoltageColor(graphics, this.volts[2]);
        CircuitElm.drawThickLine(graphics, this.drn[0], this.drn[1]);
        final int n = 6;
        this.setPowerColor(graphics, true);
        final double n2 = 1.0 / n;
        for (int i = 0; i != n; ++i) {
            this.setVoltageColor(graphics, this.volts[1] + (this.volts[2] - this.volts[1]) * i / n);
            this.interpPoint(this.src[1], this.drn[1], MosfetElm.ps1, i * n2);
            this.interpPoint(this.src[1], this.drn[1], MosfetElm.ps2, (i + 1) * n2);
            CircuitElm.drawThickLine(graphics, MosfetElm.ps1, MosfetElm.ps2);
        }
        this.setVoltageColor(graphics, this.volts[1]);
        CircuitElm.drawThickLine(graphics, this.src[1], this.src[2]);
        this.setVoltageColor(graphics, this.volts[2]);
        CircuitElm.drawThickLine(graphics, this.drn[1], this.drn[2]);
        if (!this.drawDigital()) {
            this.setVoltageColor(graphics, (this.pnp == 1) ? this.volts[1] : this.volts[2]);
            graphics.fillPolygon(this.arrowPoly);
        }
        if (MosfetElm.sim.powerCheckItem.getState()) {
            graphics.setColor(Color.gray);
        }
        this.setVoltageColor(graphics, this.volts[0]);
        CircuitElm.drawThickLine(graphics, this.point1, this.gate[1]);
        CircuitElm.drawThickLine(graphics, this.gate[0], this.gate[2]);
        if (this.drawDigital() && this.pnp == -1) {
            CircuitElm.drawThickCircle(graphics, this.pcircle.x, this.pcircle.y, this.pcircler);
        }
        if ((this.flags & this.FLAG_SHOWVT) != 0x0) {
            final String string = "" + this.vt * this.pnp;
            graphics.setColor(MosfetElm.whiteColor);
            graphics.setFont(MosfetElm.unitsFont);
            this.drawCenteredText(graphics, string, this.x2 + 2, this.y2, false);
        }
        if ((this.needsHighlight() || MosfetElm.sim.dragElm == this) && this.dy == 0) {
            graphics.setColor(Color.white);
            graphics.setFont(MosfetElm.unitsFont);
            final int sign = CircuitElm.sign(this.dx);
            graphics.drawString("G", this.gate[1].x - 10 * sign, this.gate[1].y - 5);
            graphics.drawString((this.pnp == -1) ? "D" : "S", this.src[0].x - 3 + 9 * sign, this.src[0].y + 4);
            graphics.drawString((this.pnp == -1) ? "S" : "D", this.drn[0].x - 3 + 9 * sign, this.drn[0].y + 4);
        }
        this.curcount = this.updateDotCount(-this.ids, this.curcount);
        this.drawDots(graphics, this.src[0], this.src[1], this.curcount);
        this.drawDots(graphics, this.src[1], this.drn[1], this.curcount);
        this.drawDots(graphics, this.drn[1], this.drn[0], this.curcount);
        this.drawPosts(graphics);
    }
    
    Point getPost(final int n) {
        return (n == 0) ? this.point1 : ((n == 1) ? this.src[0] : this.drn[0]);
    }
    
    double getCurrent() {
        return this.ids;
    }
    
    double getPower() {
        return this.ids * (this.volts[2] - this.volts[1]);
    }
    
    int getPostCount() {
        return 3;
    }
    
    void setPoints() {
        super.setPoints();
        final int n = 16 * this.dsign;
        this.src = this.newPointArray(3);
        this.drn = this.newPointArray(3);
        this.interpPoint2(this.point1, this.point2, this.src[0], this.drn[0], 1.0, -n);
        this.interpPoint2(this.point1, this.point2, this.src[1], this.drn[1], 1.0 - 22.0 / this.dn, -n);
        this.interpPoint2(this.point1, this.point2, this.src[2], this.drn[2], 1.0 - 22.0 / this.dn, -n * 4 / 3);
        this.gate = this.newPointArray(3);
        this.interpPoint2(this.point1, this.point2, this.gate[0], this.gate[2], 1.0 - 28.0 / this.dn, n / 2);
        this.interpPoint(this.gate[0], this.gate[2], this.gate[1], 0.5);
        if (!this.drawDigital()) {
            if (this.pnp == 1) {
                this.arrowPoly = this.calcArrow(this.src[1], this.src[0], 10.0, 4.0);
            }
            else {
                this.arrowPoly = this.calcArrow(this.drn[0], this.drn[1], 12.0, 5.0);
            }
        }
        else if (this.pnp == -1) {
            this.interpPoint(this.point1, this.point2, this.gate[1], 1.0 - 36.0 / this.dn);
            this.pcircle = this.interpPoint(this.point1, this.point2, 1.0 - ((this.dsign < 0) ? 32 : 31) / this.dn);
            this.pcircler = 3;
        }
    }
    
    void stamp() {
        MosfetElm.sim.stampNonLinear(this.nodes[1]);
        MosfetElm.sim.stampNonLinear(this.nodes[2]);
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
        int n = 1;
        int n2 = 2;
        if (this.pnp * array[1] > this.pnp * array[2]) {
            n = 2;
            n2 = 1;
        }
        final int n3 = 0;
        final double n4 = array[n3] - array[n];
        final double n5 = array[n2] - array[n];
        if (Math.abs(this.lastv1 - array[1]) > 0.01 || Math.abs(this.lastv2 - array[2]) > 0.01) {
            MosfetElm.sim.converged = false;
        }
        this.lastv1 = array[1];
        this.lastv2 = array[2];
        final double n6 = n4;
        final double n7 = n5;
        final double n8 = n4 * this.pnp;
        final double n9 = n5 * this.pnp;
        this.ids = 0.0;
        this.gm = 0.0;
        final double beta = this.getBeta();
        if (n8 > 0.5 && this instanceof JfetElm) {
            MosfetElm.sim.stop("JFET is reverse biased!", this);
            return;
        }
        double n10;
        if (n8 < this.vt) {
            n10 = 1.0E-8;
            this.ids = n9 * n10;
            this.mode = 0;
        }
        else if (n9 < n8 - this.vt) {
            this.ids = beta * ((n8 - this.vt) * n9 - n9 * n9 * 0.5);
            this.gm = beta * n9;
            n10 = beta * (n8 - n9 - this.vt);
            this.mode = 1;
        }
        else {
            this.gm = beta * (n8 - this.vt);
            n10 = 1.0E-8;
            this.ids = 0.5 * beta * (n8 - this.vt) * (n8 - this.vt) + (n9 - (n8 - this.vt)) * n10;
            this.mode = 2;
        }
        final double n11 = -this.pnp * this.ids + n10 * n7 + this.gm * n6;
        MosfetElm.sim.stampMatrix(this.nodes[n2], this.nodes[n2], n10);
        MosfetElm.sim.stampMatrix(this.nodes[n2], this.nodes[n], -n10 - this.gm);
        MosfetElm.sim.stampMatrix(this.nodes[n2], this.nodes[n3], this.gm);
        MosfetElm.sim.stampMatrix(this.nodes[n], this.nodes[n2], -n10);
        MosfetElm.sim.stampMatrix(this.nodes[n], this.nodes[n], n10 + this.gm);
        MosfetElm.sim.stampMatrix(this.nodes[n], this.nodes[n3], -this.gm);
        MosfetElm.sim.stampRightSide(this.nodes[n2], n11);
        MosfetElm.sim.stampRightSide(this.nodes[n], -n11);
        if ((n == 2 && this.pnp == 1) || (n == 1 && this.pnp == -1)) {
            this.ids = -this.ids;
        }
    }
    
    void getFetInfo(final String[] array, final String s) {
        array[0] = ((this.pnp == -1) ? "p-" : "n-") + s;
        final StringBuilder sb = new StringBuilder();
        final int n = 0;
        array[n] = sb.append(array[n]).append(" (Vt = ").append(CircuitElm.getVoltageText(this.pnp * this.vt)).append(")").toString();
        array[1] = ((this.pnp == 1) ? "Ids = " : "Isd = ") + CircuitElm.getCurrentText(this.ids);
        array[2] = "Vgs = " + CircuitElm.getVoltageText(this.volts[0] - this.volts[(this.pnp == -1) ? 2 : 1]);
        array[3] = ((this.pnp == 1) ? "Vds = " : "Vsd = ") + CircuitElm.getVoltageText(this.volts[2] - this.volts[1]);
        array[4] = ((this.mode == 0) ? "off" : ((this.mode == 1) ? "linear" : "saturation"));
        array[5] = "gm = " + CircuitElm.getUnitText(this.gm, "A/V");
    }
    
    void getInfo(final String[] array) {
        this.getFetInfo(array, "MOSFET");
    }
    
    boolean canViewInScope() {
        return true;
    }
    
    double getVoltageDiff() {
        return this.volts[2] - this.volts[1];
    }
    
    boolean getConnection(final int n, final int n2) {
        return n != 0 && n2 != 0;
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Threshold Voltage", this.pnp * this.vt, 0.01, 5.0);
        }
        if (n == 1) {
            final EditInfo editInfo = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo.checkbox = new Checkbox("Digital Symbol", this.drawDigital());
            return editInfo;
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.vt = this.pnp * editInfo.value;
        }
        if (n == 1) {
            this.flags = (editInfo.checkbox.getState() ? (this.flags | this.FLAG_DIGITAL) : (this.flags & ~this.FLAG_DIGITAL));
            this.setPoints();
        }
    }
}
