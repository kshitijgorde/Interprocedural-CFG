import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class AnalogSwitch2Elm extends AnalogSwitchElm
{
    final int openhs = 16;
    Point[] swposts;
    Point[] swpoles;
    Point ctlPoint;
    
    public AnalogSwitch2Elm(final int n, final int n2) {
        super(n, n2);
    }
    
    public AnalogSwitch2Elm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
    }
    
    void setPoints() {
        super.setPoints();
        this.calcLeads(32);
        this.swposts = this.newPointArray(2);
        this.swpoles = this.newPointArray(2);
        this.interpPoint2(this.lead1, this.lead2, this.swpoles[0], this.swpoles[1], 1.0, 16.0);
        this.interpPoint2(this.point1, this.point2, this.swposts[0], this.swposts[1], 1.0, 16.0);
        this.ctlPoint = this.interpPoint(this.point1, this.point2, 0.5, 16.0);
    }
    
    int getPostCount() {
        return 4;
    }
    
    void draw(final Graphics graphics) {
        this.setBbox(this.point1, this.point2, 16.0);
        this.setVoltageColor(graphics, this.volts[0]);
        CircuitElm.drawThickLine(graphics, this.point1, this.lead1);
        this.setVoltageColor(graphics, this.volts[1]);
        CircuitElm.drawThickLine(graphics, this.swpoles[0], this.swposts[0]);
        this.setVoltageColor(graphics, this.volts[2]);
        CircuitElm.drawThickLine(graphics, this.swpoles[1], this.swposts[1]);
        graphics.setColor(AnalogSwitch2Elm.lightGrayColor);
        final int open = this.open ? 1 : 0;
        CircuitElm.drawThickLine(graphics, this.lead1, this.swpoles[open]);
        this.updateDotCount();
        this.drawDots(graphics, this.point1, this.lead1, this.curcount);
        this.drawDots(graphics, this.swpoles[open], this.swposts[open], this.curcount);
        this.drawPosts(graphics);
    }
    
    Point getPost(final int n) {
        return (n == 0) ? this.point1 : ((n == 3) ? this.ctlPoint : this.swposts[n - 1]);
    }
    
    int getDumpType() {
        return 160;
    }
    
    void calculateCurrent() {
        if (this.open) {
            this.current = (this.volts[0] - this.volts[2]) / this.r_on;
        }
        else {
            this.current = (this.volts[0] - this.volts[1]) / this.r_on;
        }
    }
    
    void stamp() {
        AnalogSwitch2Elm.sim.stampNonLinear(this.nodes[0]);
        AnalogSwitch2Elm.sim.stampNonLinear(this.nodes[1]);
        AnalogSwitch2Elm.sim.stampNonLinear(this.nodes[2]);
    }
    
    void doStep() {
        this.open = (this.volts[3] < 2.5);
        if ((this.flags & 0x1) != 0x0) {
            this.open = !this.open;
        }
        if (this.open) {
            AnalogSwitch2Elm.sim.stampResistor(this.nodes[0], this.nodes[2], this.r_on);
            AnalogSwitch2Elm.sim.stampResistor(this.nodes[0], this.nodes[1], this.r_off);
        }
        else {
            AnalogSwitch2Elm.sim.stampResistor(this.nodes[0], this.nodes[1], this.r_on);
            AnalogSwitch2Elm.sim.stampResistor(this.nodes[0], this.nodes[2], this.r_off);
        }
    }
    
    boolean getConnection(final int n, final int n2) {
        return n != 3 && n2 != 3;
    }
    
    void getInfo(final String[] array) {
        array[0] = "analog switch (SPDT)";
        array[1] = "I = " + CircuitElm.getCurrentDText(this.getCurrent());
    }
}
