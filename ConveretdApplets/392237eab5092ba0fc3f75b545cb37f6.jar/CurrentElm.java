import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

class CurrentElm extends CircuitElm
{
    double currentValue;
    Polygon arrow;
    Point ashaft1;
    Point ashaft2;
    Point center;
    
    public CurrentElm(final int n, final int n2) {
        super(n, n2);
        this.currentValue = 0.01;
    }
    
    public CurrentElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        try {
            this.currentValue = new Double(stringTokenizer.nextToken());
        }
        catch (Exception ex) {
            this.currentValue = 0.01;
        }
    }
    
    String dump() {
        return super.dump() + " " + this.currentValue;
    }
    
    int getDumpType() {
        return 105;
    }
    
    void setPoints() {
        super.setPoints();
        this.calcLeads(26);
        this.ashaft1 = this.interpPoint(this.lead1, this.lead2, 0.25);
        this.ashaft2 = this.interpPoint(this.lead1, this.lead2, 0.6);
        this.center = this.interpPoint(this.lead1, this.lead2, 0.5);
        this.arrow = this.calcArrow(this.center, this.interpPoint(this.lead1, this.lead2, 0.75), 4.0, 4.0);
    }
    
    void draw(final Graphics graphics) {
        final int n = 12;
        this.draw2Leads(graphics);
        this.setVoltageColor(graphics, (this.volts[0] + this.volts[1]) / 2.0);
        this.setPowerColor(graphics, false);
        CircuitElm.drawThickCircle(graphics, this.center.x, this.center.y, n);
        CircuitElm.drawThickLine(graphics, this.ashaft1, this.ashaft2);
        graphics.fillPolygon(this.arrow);
        this.setBbox(this.point1, this.point2, n);
        this.doDots(graphics);
        if (CurrentElm.sim.showValuesCheckItem.getState()) {
            final String shortUnitText = CircuitElm.getShortUnitText(this.currentValue, "A");
            if (this.dx == 0 || this.dy == 0) {
                this.drawValues(graphics, shortUnitText, n);
            }
        }
        this.drawPosts(graphics);
    }
    
    void stamp() {
        this.current = this.currentValue;
        CurrentElm.sim.stampCurrentSource(this.nodes[0], this.nodes[1], this.current);
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Current (A)", this.currentValue, 0.0, 0.1);
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        this.currentValue = editInfo.value;
    }
    
    void getInfo(final String[] array) {
        array[0] = "current source";
        this.getBasicInfo(array);
    }
    
    double getVoltageDiff() {
        return this.volts[1] - this.volts[0];
    }
}
