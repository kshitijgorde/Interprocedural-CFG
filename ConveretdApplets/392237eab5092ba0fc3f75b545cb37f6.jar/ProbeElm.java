import java.awt.Checkbox;
import java.awt.Font;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class ProbeElm extends CircuitElm
{
    static final int FLAG_SHOWVOLTAGE = 1;
    Point center;
    
    public ProbeElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public ProbeElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
    }
    
    int getDumpType() {
        return 112;
    }
    
    void setPoints() {
        super.setPoints();
        if (this.point2.y < this.point1.y) {
            final Point point1 = this.point1;
            this.point1 = this.point2;
            this.point2 = point1;
        }
        this.center = this.interpPoint(this.point1, this.point2, 0.5);
    }
    
    void draw(final Graphics graphics) {
        this.setBbox(this.point1, this.point2, 8);
        final boolean b = this.needsHighlight() || ProbeElm.sim.plotYElm == this;
        this.calcLeads((int)((b || ProbeElm.sim.dragElm == this) ? 16.0 : (this.dn - 32.0)));
        this.setVoltageColor(graphics, this.volts[0]);
        if (b) {
            graphics.setColor(ProbeElm.selectColor);
        }
        CircuitElm.drawThickLine(graphics, this.point1, this.lead1);
        this.setVoltageColor(graphics, this.volts[1]);
        if (b) {
            graphics.setColor(ProbeElm.selectColor);
        }
        CircuitElm.drawThickLine(graphics, this.lead2, this.point2);
        graphics.setFont(new Font("SansSerif", 1, 14));
        if (this == ProbeElm.sim.plotXElm) {
            this.drawCenteredText(graphics, "X", this.center.x, this.center.y, true);
        }
        if (this == ProbeElm.sim.plotYElm) {
            this.drawCenteredText(graphics, "Y", this.center.x, this.center.y, true);
        }
        if (this.mustShowVoltage()) {
            this.drawValues(graphics, CircuitElm.getShortUnitText(this.volts[0], "V"), 4.0);
        }
        this.drawPosts(graphics);
    }
    
    boolean mustShowVoltage() {
        return (this.flags & 0x1) != 0x0;
    }
    
    void getInfo(final String[] array) {
        array[0] = "scope probe";
        array[1] = "Vd = " + CircuitElm.getVoltageText(this.getVoltageDiff());
    }
    
    boolean getConnection(final int n, final int n2) {
        return false;
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            final EditInfo editInfo = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo.checkbox = new Checkbox("Show Voltage", this.mustShowVoltage());
            return editInfo;
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
    }
}
