import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

class InverterElm extends CircuitElm
{
    double slewRate;
    Polygon gatePoly;
    Point pcircle;
    
    public InverterElm(final int n, final int n2) {
        super(n, n2);
        this.noDiagonal = true;
        this.slewRate = 0.5;
    }
    
    public InverterElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.noDiagonal = true;
        try {
            this.slewRate = new Double(stringTokenizer.nextToken());
        }
        catch (Exception ex) {
            this.slewRate = 0.5;
        }
    }
    
    String dump() {
        return super.dump() + " " + this.slewRate;
    }
    
    int getDumpType() {
        return 73;
    }
    
    void draw(final Graphics graphics) {
        this.drawPosts(graphics);
        this.draw2Leads(graphics);
        graphics.setColor(this.needsHighlight() ? InverterElm.selectColor : InverterElm.lightGrayColor);
        CircuitElm.drawThickPolygon(graphics, this.gatePoly);
        CircuitElm.drawThickCircle(graphics, this.pcircle.x, this.pcircle.y, 3);
        this.curcount = this.updateDotCount(this.current, this.curcount);
        this.drawDots(graphics, this.lead2, this.point2, this.curcount);
    }
    
    void setPoints() {
        super.setPoints();
        final int n = 16;
        int n2 = 16;
        if (n2 > this.dn / 2.0) {
            n2 = (int)(this.dn / 2.0);
        }
        this.lead1 = this.interpPoint(this.point1, this.point2, 0.5 - n2 / this.dn);
        this.lead2 = this.interpPoint(this.point1, this.point2, 0.5 + (n2 + 2) / this.dn);
        this.pcircle = this.interpPoint(this.point1, this.point2, 0.5 + (n2 - 2) / this.dn);
        final Point[] pointArray = this.newPointArray(3);
        this.interpPoint2(this.lead1, this.lead2, pointArray[0], pointArray[1], 0.0, n);
        pointArray[2] = this.interpPoint(this.point1, this.point2, 0.5 + (n2 - 5) / this.dn);
        this.gatePoly = this.createPolygon(pointArray);
        this.setBbox(this.point1, this.point2, n);
    }
    
    int getVoltageSourceCount() {
        return 1;
    }
    
    void stamp() {
        InverterElm.sim.stampVoltageSource(0, this.nodes[1], this.voltSource);
    }
    
    void doStep() {
        final double n = this.volts[1];
        final double n2 = (this.volts[0] > 2.5) ? 0.0 : 5.0;
        final double n3 = this.slewRate * InverterElm.sim.timeStep * 1.0E9;
        InverterElm.sim.updateVoltageSource(0, this.nodes[1], this.voltSource, Math.max(Math.min(n + n3, n2), n - n3));
    }
    
    double getVoltageDiff() {
        return this.volts[0];
    }
    
    void getInfo(final String[] array) {
        array[0] = "inverter";
        array[1] = "Vi = " + CircuitElm.getVoltageText(this.volts[0]);
        array[2] = "Vo = " + CircuitElm.getVoltageText(this.volts[1]);
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Slew Rate (V/ns)", this.slewRate, 0.0, 0.0);
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        this.slewRate = editInfo.value;
    }
    
    boolean getConnection(final int n, final int n2) {
        return false;
    }
    
    boolean hasGroundConnection(final int n) {
        return n == 1;
    }
}
