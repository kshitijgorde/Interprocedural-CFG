import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

class TunnelDiodeElm extends CircuitElm
{
    final int hs = 8;
    Polygon poly;
    Point[] cathode;
    double lastvoltdiff;
    static final double pvp = 0.1;
    static final double pip = 0.0047;
    static final double pvv = 0.37;
    static final double pvt = 0.026;
    static final double pvpp = 0.525;
    static final double piv = 3.7E-4;
    
    public TunnelDiodeElm(final int n, final int n2) {
        super(n, n2);
        this.setup();
    }
    
    public TunnelDiodeElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.setup();
    }
    
    boolean nonLinear() {
        return true;
    }
    
    void setup() {
    }
    
    int getDumpType() {
        return 175;
    }
    
    void setPoints() {
        super.setPoints();
        this.calcLeads(16);
        this.cathode = this.newPointArray(4);
        final Point[] pointArray = this.newPointArray(2);
        this.interpPoint2(this.lead1, this.lead2, pointArray[0], pointArray[1], 0.0, 8.0);
        this.interpPoint2(this.lead1, this.lead2, this.cathode[0], this.cathode[1], 1.0, 8.0);
        this.interpPoint2(this.lead1, this.lead2, this.cathode[2], this.cathode[3], 0.8, 8.0);
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
        CircuitElm.drawThickLine(graphics, this.cathode[2], this.cathode[0]);
        CircuitElm.drawThickLine(graphics, this.cathode[3], this.cathode[1]);
        this.doDots(graphics);
        this.drawPosts(graphics);
    }
    
    void reset() {
        final double[] volts = this.volts;
        final int n = 0;
        final double[] volts2 = this.volts;
        final int n2 = 1;
        final double n3 = 0.0;
        this.curcount = n3;
        volts[n] = (volts2[n2] = n3);
        this.lastvoltdiff = n3;
    }
    
    double limitStep(final double n, final double n2) {
        if (n > n2 + 1.0) {
            return n2 + 1.0;
        }
        if (n < n2 - 1.0) {
            return n2 - 1.0;
        }
        return n;
    }
    
    void stamp() {
        TunnelDiodeElm.sim.stampNonLinear(this.nodes[0]);
        TunnelDiodeElm.sim.stampNonLinear(this.nodes[1]);
    }
    
    void doStep() {
        final double n = this.volts[0] - this.volts[1];
        if (Math.abs(n - this.lastvoltdiff) > 0.01) {
            TunnelDiodeElm.sim.converged = false;
        }
        final double limitStep = this.limitStep(n, this.lastvoltdiff);
        this.lastvoltdiff = limitStep;
        final double n2 = 0.0047 * Math.exp(-20.192307692307693) * (Math.exp(limitStep / 0.026) - 1.0) + 0.0047 * (limitStep / 0.1) * Math.exp(1.0 - limitStep / 0.1) + 3.7E-4 * Math.exp(limitStep - 0.37);
        final double n3 = 0.0047 * Math.exp(-20.192307692307693) * Math.exp(limitStep / 0.026) / 0.026 + 0.0047 * Math.exp(1.0 - limitStep / 0.1) / 0.1 - Math.exp(1.0 - limitStep / 0.1) * 0.0047 * limitStep / 0.010000000000000002 + Math.exp(limitStep - 0.37) * 3.7E-4;
        final double n4 = n2 - n3 * limitStep;
        TunnelDiodeElm.sim.stampConductance(this.nodes[0], this.nodes[1], n3);
        TunnelDiodeElm.sim.stampCurrentSource(this.nodes[0], this.nodes[1], n4);
    }
    
    void calculateCurrent() {
        final double n = this.volts[0] - this.volts[1];
        this.current = 0.0047 * Math.exp(-20.192307692307693) * (Math.exp(n / 0.026) - 1.0) + 0.0047 * (n / 0.1) * Math.exp(1.0 - n / 0.1) + 3.7E-4 * Math.exp(n - 0.37);
    }
    
    void getInfo(final String[] array) {
        array[0] = "tunnel diode";
        array[1] = "I = " + CircuitElm.getCurrentText(this.getCurrent());
        array[2] = "Vd = " + CircuitElm.getVoltageText(this.getVoltageDiff());
        array[3] = "P = " + CircuitElm.getUnitText(this.getPower(), "W");
    }
}
