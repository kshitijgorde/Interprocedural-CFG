import java.awt.Checkbox;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class SwitchElm extends CircuitElm
{
    boolean momentary;
    int position;
    int posCount;
    Point ps;
    Point ps2;
    
    public SwitchElm(final int n, final int n2) {
        super(n, n2);
        this.momentary = false;
        this.position = 0;
        this.posCount = 2;
    }
    
    SwitchElm(final int n, final int n2, final boolean b) {
        super(n, n2);
        this.position = (b ? 1 : 0);
        this.momentary = b;
        this.posCount = 2;
    }
    
    public SwitchElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        final String nextToken = stringTokenizer.nextToken();
        if (nextToken.compareTo("true") == 0) {
            this.position = ((this instanceof LogicInputElm) ? 0 : 1);
        }
        else if (nextToken.compareTo("false") == 0) {
            this.position = ((this instanceof LogicInputElm) ? 1 : 0);
        }
        else {
            this.position = new Integer(nextToken);
        }
        this.momentary = new Boolean(stringTokenizer.nextToken());
        this.posCount = 2;
    }
    
    int getDumpType() {
        return 115;
    }
    
    String dump() {
        return super.dump() + " " + this.position + " " + this.momentary;
    }
    
    void setPoints() {
        super.setPoints();
        this.calcLeads(32);
        this.ps = new Point();
        this.ps2 = new Point();
    }
    
    void draw(final Graphics graphics) {
        final int n = 16;
        final int n2 = (this.position == 1) ? 0 : 2;
        final int n3 = (this.position == 1) ? n : 2;
        this.setBbox(this.point1, this.point2, n);
        this.draw2Leads(graphics);
        if (this.position == 0) {
            this.doDots(graphics);
        }
        if (!this.needsHighlight()) {
            graphics.setColor(SwitchElm.whiteColor);
        }
        this.interpPoint(this.lead1, this.lead2, this.ps, 0.0, n2);
        this.interpPoint(this.lead1, this.lead2, this.ps2, 1.0, n3);
        CircuitElm.drawThickLine(graphics, this.ps, this.ps2);
        this.drawPosts(graphics);
    }
    
    void calculateCurrent() {
        if (this.position == 1) {
            this.current = 0.0;
        }
    }
    
    void stamp() {
        if (this.position == 0) {
            SwitchElm.sim.stampVoltageSource(this.nodes[0], this.nodes[1], this.voltSource, 0.0);
        }
    }
    
    int getVoltageSourceCount() {
        return (this.position != 1) ? 1 : 0;
    }
    
    void mouseUp() {
        if (this.momentary) {
            this.toggle();
        }
    }
    
    void toggle() {
        ++this.position;
        if (this.position >= this.posCount) {
            this.position = 0;
        }
    }
    
    void getInfo(final String[] array) {
        array[0] = (this.momentary ? "push switch (SPST)" : "switch (SPST)");
        if (this.position == 1) {
            array[1] = "open";
            array[2] = "Vd = " + CircuitElm.getVoltageDText(this.getVoltageDiff());
        }
        else {
            array[1] = "closed";
            array[2] = "V = " + CircuitElm.getVoltageText(this.volts[0]);
            array[3] = "I = " + CircuitElm.getCurrentDText(this.getCurrent());
        }
    }
    
    boolean getConnection(final int n, final int n2) {
        return this.position == 0;
    }
    
    boolean isWire() {
        return true;
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            final EditInfo editInfo = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo.checkbox = new Checkbox("Momentary Switch", this.momentary);
            return editInfo;
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.momentary = editInfo.checkbox.getState();
        }
    }
}
