import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

class DiodeElm extends CircuitElm
{
    Diode diode;
    static final int FLAG_FWDROP = 1;
    final double defaultdrop = 0.805904783;
    double fwdrop;
    double zvoltage;
    final int hs = 8;
    Polygon poly;
    Point[] cathode;
    
    public DiodeElm(final int n, final int n2) {
        super(n, n2);
        this.diode = new Diode(DiodeElm.sim);
        this.fwdrop = 0.805904783;
        this.zvoltage = 0.0;
        this.setup();
    }
    
    public DiodeElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.diode = new Diode(DiodeElm.sim);
        this.fwdrop = 0.805904783;
        this.zvoltage = 0.0;
        if ((n5 & 0x1) > 0) {
            try {
                this.fwdrop = new Double(stringTokenizer.nextToken());
            }
            catch (Exception ex) {}
        }
        this.setup();
    }
    
    boolean nonLinear() {
        return true;
    }
    
    void setup() {
        this.diode.setup(this.fwdrop, this.zvoltage);
    }
    
    int getDumpType() {
        return 100;
    }
    
    String dump() {
        this.flags |= 0x1;
        return super.dump() + " " + this.fwdrop;
    }
    
    void setPoints() {
        super.setPoints();
        this.calcLeads(16);
        this.cathode = this.newPointArray(2);
        final Point[] pointArray = this.newPointArray(2);
        this.interpPoint2(this.lead1, this.lead2, pointArray[0], pointArray[1], 0.0, 8.0);
        this.interpPoint2(this.lead1, this.lead2, this.cathode[0], this.cathode[1], 1.0, 8.0);
        this.poly = this.createPolygon(pointArray[0], pointArray[1], this.lead2);
    }
    
    void draw(final Graphics graphics) {
        this.drawDiode(graphics);
        this.doDots(graphics);
        this.drawPosts(graphics);
    }
    
    void reset() {
        this.diode.reset();
        final double[] volts = this.volts;
        final int n = 0;
        final double[] volts2 = this.volts;
        final int n2 = 1;
        final double curcount = 0.0;
        this.curcount = curcount;
        volts[n] = (volts2[n2] = curcount);
    }
    
    void drawDiode(final Graphics graphics) {
        this.setBbox(this.point1, this.point2, 8.0);
        final double n = this.volts[0];
        final double n2 = this.volts[1];
        this.draw2Leads(graphics);
        this.setPowerColor(graphics, true);
        this.setVoltageColor(graphics, n);
        graphics.fillPolygon(this.poly);
        this.setVoltageColor(graphics, n2);
        CircuitElm.drawThickLine(graphics, this.cathode[0], this.cathode[1]);
    }
    
    void stamp() {
        this.diode.stamp(this.nodes[0], this.nodes[1]);
    }
    
    void doStep() {
        this.diode.doStep(this.volts[0] - this.volts[1]);
    }
    
    void calculateCurrent() {
        this.current = this.diode.calculateCurrent(this.volts[0] - this.volts[1]);
    }
    
    void getInfo(final String[] array) {
        array[0] = "diode";
        array[1] = "I = " + CircuitElm.getCurrentText(this.getCurrent());
        array[2] = "Vd = " + CircuitElm.getVoltageText(this.getVoltageDiff());
        array[3] = "P = " + CircuitElm.getUnitText(this.getPower(), "W");
        array[4] = "Vf = " + CircuitElm.getVoltageText(this.fwdrop);
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Fwd Voltage @ 1A", this.fwdrop, 10.0, 1000.0);
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        this.fwdrop = editInfo.value;
        this.setup();
    }
    
    boolean needsShortcut() {
        return this.getClass() == DiodeElm.class;
    }
}
