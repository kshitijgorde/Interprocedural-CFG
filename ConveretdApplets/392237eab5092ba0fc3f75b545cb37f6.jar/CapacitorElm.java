import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class CapacitorElm extends CircuitElm
{
    double capacitance;
    double compResistance;
    double voltdiff;
    Point[] plate1;
    Point[] plate2;
    public static final int FLAG_BACK_EULER = 2;
    double curSourceValue;
    
    public CapacitorElm(final int n, final int n2) {
        super(n, n2);
        this.capacitance = 1.0E-5;
    }
    
    public CapacitorElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.capacitance = new Double(stringTokenizer.nextToken());
        this.voltdiff = new Double(stringTokenizer.nextToken());
    }
    
    boolean isTrapezoidal() {
        return (this.flags & 0x2) == 0x0;
    }
    
    void setNodeVoltage(final int n, final double n2) {
        super.setNodeVoltage(n, n2);
        this.voltdiff = this.volts[0] - this.volts[1];
    }
    
    void reset() {
        final double n = 0.0;
        this.curcount = n;
        this.current = n;
        this.voltdiff = 0.001;
    }
    
    int getDumpType() {
        return 99;
    }
    
    String dump() {
        return super.dump() + " " + this.capacitance + " " + this.voltdiff;
    }
    
    void setPoints() {
        super.setPoints();
        final double n = (this.dn / 2.0 - 4.0) / this.dn;
        this.lead1 = this.interpPoint(this.point1, this.point2, n);
        this.lead2 = this.interpPoint(this.point1, this.point2, 1.0 - n);
        this.plate1 = this.newPointArray(2);
        this.plate2 = this.newPointArray(2);
        this.interpPoint2(this.point1, this.point2, this.plate1[0], this.plate1[1], n, 12.0);
        this.interpPoint2(this.point1, this.point2, this.plate2[0], this.plate2[1], 1.0 - n, 12.0);
    }
    
    void draw(final Graphics graphics) {
        final int n = 12;
        this.setBbox(this.point1, this.point2, n);
        this.setVoltageColor(graphics, this.volts[0]);
        CircuitElm.drawThickLine(graphics, this.point1, this.lead1);
        this.setPowerColor(graphics, false);
        CircuitElm.drawThickLine(graphics, this.plate1[0], this.plate1[1]);
        if (CapacitorElm.sim.powerCheckItem.getState()) {
            graphics.setColor(Color.gray);
        }
        this.setVoltageColor(graphics, this.volts[1]);
        CircuitElm.drawThickLine(graphics, this.point2, this.lead2);
        this.setPowerColor(graphics, false);
        CircuitElm.drawThickLine(graphics, this.plate2[0], this.plate2[1]);
        this.updateDotCount();
        if (CapacitorElm.sim.dragElm != this) {
            this.drawDots(graphics, this.point1, this.lead1, this.curcount);
            this.drawDots(graphics, this.point2, this.lead2, -this.curcount);
        }
        this.drawPosts(graphics);
        if (CapacitorElm.sim.showValuesCheckItem.getState()) {
            this.drawValues(graphics, CircuitElm.getShortUnitText(this.capacitance, "F"), n);
        }
    }
    
    void stamp() {
        if (this.isTrapezoidal()) {
            this.compResistance = CapacitorElm.sim.timeStep / (2.0 * this.capacitance);
        }
        else {
            this.compResistance = CapacitorElm.sim.timeStep / this.capacitance;
        }
        CapacitorElm.sim.stampResistor(this.nodes[0], this.nodes[1], this.compResistance);
        CapacitorElm.sim.stampRightSide(this.nodes[0]);
        CapacitorElm.sim.stampRightSide(this.nodes[1]);
    }
    
    void startIteration() {
        if (this.isTrapezoidal()) {
            this.curSourceValue = -this.voltdiff / this.compResistance - this.current;
        }
        else {
            this.curSourceValue = -this.voltdiff / this.compResistance;
        }
    }
    
    void calculateCurrent() {
        final double n = this.volts[0] - this.volts[1];
        if (this.compResistance > 0.0) {
            this.current = n / this.compResistance + this.curSourceValue;
        }
    }
    
    void doStep() {
        CapacitorElm.sim.stampCurrentSource(this.nodes[0], this.nodes[1], this.curSourceValue);
    }
    
    void getInfo(final String[] array) {
        array[0] = "capacitor";
        this.getBasicInfo(array);
        array[3] = "C = " + CircuitElm.getUnitText(this.capacitance, "F");
        array[4] = "P = " + CircuitElm.getUnitText(this.getPower(), "W");
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Capacitance (F)", this.capacitance, 0.0, 0.0);
        }
        if (n == 1) {
            final EditInfo editInfo = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo.checkbox = new Checkbox("Trapezoidal Approximation", this.isTrapezoidal());
            return editInfo;
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0 && editInfo.value > 0.0) {
            this.capacitance = editInfo.value;
        }
        if (n == 1) {
            if (editInfo.checkbox.getState()) {
                this.flags &= 0xFFFFFFFD;
            }
            else {
                this.flags |= 0x2;
            }
        }
    }
    
    boolean needsShortcut() {
        return true;
    }
}
