import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

class ZenerElm extends DiodeElm
{
    final int hs = 8;
    Polygon poly;
    Point[] cathode;
    Point[] wing;
    final double default_zvoltage = 5.6;
    
    public ZenerElm(final int n, final int n2) {
        super(n, n2);
        this.zvoltage = 5.6;
        this.setup();
    }
    
    public ZenerElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
        this.zvoltage = new Double(stringTokenizer.nextToken());
        this.setup();
    }
    
    void setup() {
        this.diode.leakage = 5.0E-6;
        super.setup();
    }
    
    int getDumpType() {
        return 122;
    }
    
    String dump() {
        return super.dump() + " " + this.zvoltage;
    }
    
    void setPoints() {
        super.setPoints();
        this.calcLeads(16);
        this.cathode = this.newPointArray(2);
        this.wing = this.newPointArray(2);
        final Point[] pointArray = this.newPointArray(2);
        this.interpPoint2(this.lead1, this.lead2, pointArray[0], pointArray[1], 0.0, 8.0);
        this.interpPoint2(this.lead1, this.lead2, this.cathode[0], this.cathode[1], 1.0, 8.0);
        this.interpPoint(this.cathode[0], this.cathode[1], this.wing[0], -0.2, -8.0);
        this.interpPoint(this.cathode[1], this.cathode[0], this.wing[1], -0.2, -8.0);
        this.poly = this.createPolygon(pointArray[0], pointArray[1], this.lead2);
    }
    
    void draw(final Graphics graphics) {
        this.setBbox(this.point1, this.point2, 8.0);
        final double n = this.volts[0];
        final double n2 = this.volts[1];
        this.draw2Leads(graphics);
        this.setPowerColor(graphics, true);
        this.setVoltageColor(graphics, n);
        graphics.fillPolygon(this.poly);
        this.setVoltageColor(graphics, n2);
        CircuitElm.drawThickLine(graphics, this.cathode[0], this.cathode[1]);
        CircuitElm.drawThickLine(graphics, this.wing[0], this.cathode[0]);
        CircuitElm.drawThickLine(graphics, this.wing[1], this.cathode[1]);
        this.doDots(graphics);
        this.drawPosts(graphics);
    }
    
    void getInfo(final String[] array) {
        super.getInfo(array);
        array[0] = "Zener diode";
        array[5] = "Vz = " + CircuitElm.getVoltageText(this.zvoltage);
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Fwd Voltage @ 1A", this.fwdrop, 10.0, 1000.0);
        }
        if (n == 1) {
            return new EditInfo("Zener Voltage @ 5mA", this.zvoltage, 1.0, 25.0);
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.fwdrop = editInfo.value;
        }
        if (n == 1) {
            this.zvoltage = editInfo.value;
        }
        this.setup();
    }
}
