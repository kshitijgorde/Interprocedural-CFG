import java.awt.Checkbox;
import java.awt.Font;
import java.awt.Graphics;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class LogicOutputElm extends CircuitElm
{
    final int FLAG_TERNARY = 1;
    final int FLAG_NUMERIC = 2;
    final int FLAG_PULLDOWN = 4;
    double threshold;
    String value;
    
    public LogicOutputElm(final int n, final int n2) {
        super(n, n2);
        this.threshold = 2.5;
    }
    
    public LogicOutputElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        try {
            this.threshold = new Double(stringTokenizer.nextToken());
        }
        catch (Exception ex) {
            this.threshold = 2.5;
        }
    }
    
    String dump() {
        return super.dump() + " " + this.threshold;
    }
    
    int getDumpType() {
        return 77;
    }
    
    int getPostCount() {
        return 1;
    }
    
    boolean isTernary() {
        return (this.flags & 0x1) != 0x0;
    }
    
    boolean isNumeric() {
        return (this.flags & 0x3) != 0x0;
    }
    
    boolean needsPullDown() {
        return (this.flags & 0x4) != 0x0;
    }
    
    void setPoints() {
        super.setPoints();
        this.lead1 = this.interpPoint(this.point1, this.point2, 1.0 - 12.0 / this.dn);
    }
    
    void draw(final Graphics graphics) {
        graphics.setFont(new Font("SansSerif", 1, 20));
        graphics.setColor(LogicOutputElm.lightGrayColor);
        String value = (this.volts[0] < this.threshold) ? "L" : "H";
        if (this.isTernary()) {
            if (this.volts[0] > 3.75) {
                value = "2";
            }
            else if (this.volts[0] > 1.25) {
                value = "1";
            }
            else {
                value = "0";
            }
        }
        else if (this.isNumeric()) {
            value = ((this.volts[0] < this.threshold) ? "0" : "1");
        }
        this.value = value;
        this.setBbox(this.point1, this.lead1, 0.0);
        this.drawCenteredText(graphics, value, this.x2, this.y2, true);
        this.setVoltageColor(graphics, this.volts[0]);
        CircuitElm.drawThickLine(graphics, this.point1, this.lead1);
        this.drawPosts(graphics);
    }
    
    void stamp() {
        if (this.needsPullDown()) {
            LogicOutputElm.sim.stampResistor(this.nodes[0], 0, 1000000.0);
        }
    }
    
    double getVoltageDiff() {
        return this.volts[0];
    }
    
    void getInfo(final String[] array) {
        array[0] = "logic output";
        array[1] = ((this.volts[0] < this.threshold) ? "low" : "high");
        if (this.isNumeric()) {
            array[1] = this.value;
        }
        array[2] = "V = " + CircuitElm.getVoltageText(this.volts[0]);
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Threshold", this.threshold, 10.0, -10.0);
        }
        if (n == 1) {
            final EditInfo editInfo = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo.checkbox = new Checkbox("Current Required", this.needsPullDown());
            return editInfo;
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.threshold = editInfo.value;
        }
        if (n == 1) {
            if (editInfo.checkbox.getState()) {
                this.flags = 4;
            }
            else {
                this.flags &= 0xFFFFFFFB;
            }
        }
    }
}
