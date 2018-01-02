import java.awt.Checkbox;
import java.awt.Graphics;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class WireElm extends CircuitElm
{
    static final int FLAG_SHOWCURRENT = 1;
    static final int FLAG_SHOWVOLTAGE = 2;
    
    public WireElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public WireElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
    }
    
    void draw(final Graphics graphics) {
        this.setVoltageColor(graphics, this.volts[0]);
        CircuitElm.drawThickLine(graphics, this.point1, this.point2);
        this.doDots(graphics);
        this.setBbox(this.point1, this.point2, 3.0);
        if (this.mustShowCurrent()) {
            this.drawValues(graphics, CircuitElm.getShortUnitText(Math.abs(this.getCurrent()), "A"), 4.0);
        }
        else if (this.mustShowVoltage()) {
            this.drawValues(graphics, CircuitElm.getShortUnitText(this.volts[0], "V"), 4.0);
        }
        this.drawPosts(graphics);
    }
    
    void stamp() {
        WireElm.sim.stampVoltageSource(this.nodes[0], this.nodes[1], this.voltSource, 0.0);
    }
    
    boolean mustShowCurrent() {
        return (this.flags & 0x1) != 0x0;
    }
    
    boolean mustShowVoltage() {
        return (this.flags & 0x2) != 0x0;
    }
    
    int getVoltageSourceCount() {
        return 1;
    }
    
    void getInfo(final String[] array) {
        array[0] = "wire";
        array[1] = "I = " + CircuitElm.getCurrentDText(this.getCurrent());
        array[2] = "V = " + CircuitElm.getVoltageText(this.volts[0]);
    }
    
    int getDumpType() {
        return 119;
    }
    
    double getPower() {
        return 0.0;
    }
    
    double getVoltageDiff() {
        return this.volts[0];
    }
    
    boolean isWire() {
        return true;
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            final EditInfo editInfo = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo.checkbox = new Checkbox("Show Current", this.mustShowCurrent());
            return editInfo;
        }
        if (n == 1) {
            final EditInfo editInfo2 = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo2.checkbox = new Checkbox("Show Voltage", this.mustShowVoltage());
            return editInfo2;
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            if (editInfo.checkbox.getState()) {
                this.flags = 1;
            }
            else {
                this.flags &= 0xFFFFFFFE;
            }
        }
        if (n == 1) {
            if (editInfo.checkbox.getState()) {
                this.flags = 2;
            }
            else {
                this.flags &= 0xFFFFFFFD;
            }
        }
    }
    
    boolean needsShortcut() {
        return true;
    }
}
