import java.awt.Checkbox;
import java.awt.Font;
import java.awt.Graphics;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class LogicInputElm extends SwitchElm
{
    final int FLAG_TERNARY = 1;
    final int FLAG_NUMERIC = 2;
    double hiV;
    double loV;
    
    public LogicInputElm(final int n, final int n2) {
        super(n, n2, false);
        this.hiV = 5.0;
        this.loV = 0.0;
    }
    
    public LogicInputElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
        try {
            this.hiV = new Double(stringTokenizer.nextToken());
            this.loV = new Double(stringTokenizer.nextToken());
        }
        catch (Exception ex) {
            this.hiV = 5.0;
            this.loV = 0.0;
        }
        if (this.isTernary()) {
            this.posCount = 3;
        }
    }
    
    boolean isTernary() {
        return (this.flags & 0x1) != 0x0;
    }
    
    boolean isNumeric() {
        return (this.flags & 0x3) != 0x0;
    }
    
    int getDumpType() {
        return 76;
    }
    
    String dump() {
        return super.dump() + " " + this.hiV + " " + this.loV;
    }
    
    int getPostCount() {
        return 1;
    }
    
    void setPoints() {
        super.setPoints();
        this.lead1 = this.interpPoint(this.point1, this.point2, 1.0 - 12.0 / this.dn);
    }
    
    void draw(final Graphics graphics) {
        graphics.setFont(new Font("SansSerif", 1, 20));
        graphics.setColor(this.needsHighlight() ? LogicInputElm.selectColor : LogicInputElm.whiteColor);
        String string = (this.position == 0) ? "L" : "H";
        if (this.isNumeric()) {
            string = "" + this.position;
        }
        this.setBbox(this.point1, this.lead1, 0.0);
        this.drawCenteredText(graphics, string, this.x2, this.y2, true);
        this.setVoltageColor(graphics, this.volts[0]);
        CircuitElm.drawThickLine(graphics, this.point1, this.lead1);
        this.updateDotCount();
        this.drawDots(graphics, this.point1, this.lead1, this.curcount);
        this.drawPosts(graphics);
    }
    
    void setCurrent(final int n, final double n2) {
        this.current = -n2;
    }
    
    void stamp() {
        double n = (this.position == 0) ? this.loV : this.hiV;
        if (this.isTernary()) {
            n = this.position * 2.5;
        }
        LogicInputElm.sim.stampVoltageSource(0, this.nodes[0], this.voltSource, n);
    }
    
    int getVoltageSourceCount() {
        return 1;
    }
    
    double getVoltageDiff() {
        return this.volts[0];
    }
    
    void getInfo(final String[] array) {
        array[0] = "logic input";
        array[1] = ((this.position == 0) ? "low" : "high");
        if (this.isNumeric()) {
            array[1] = "" + this.position;
        }
        final StringBuilder sb = new StringBuilder();
        final int n = 1;
        array[n] = sb.append(array[n]).append(" (").append(CircuitElm.getVoltageText(this.volts[0])).append(")").toString();
        array[2] = "I = " + CircuitElm.getCurrentText(this.getCurrent());
    }
    
    boolean hasGroundConnection(final int n) {
        return true;
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            final EditInfo editInfo = new EditInfo("", 0.0, 0.0, 0.0);
            editInfo.checkbox = new Checkbox("Momentary Switch", this.momentary);
            return editInfo;
        }
        if (n == 1) {
            return new EditInfo("High Voltage", this.hiV, 10.0, -10.0);
        }
        if (n == 2) {
            return new EditInfo("Low Voltage", this.loV, 10.0, -10.0);
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.momentary = editInfo.checkbox.getState();
        }
        if (n == 1) {
            this.hiV = editInfo.value;
        }
        if (n == 2) {
            this.loV = editInfo.value;
        }
    }
}
