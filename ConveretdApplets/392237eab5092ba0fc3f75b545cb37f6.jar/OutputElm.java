import java.awt.Checkbox;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class OutputElm extends CircuitElm
{
    final int FLAG_VALUE = 1;
    
    public OutputElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public OutputElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
    }
    
    int getDumpType() {
        return 79;
    }
    
    int getPostCount() {
        return 1;
    }
    
    void setPoints() {
        super.setPoints();
        this.lead1 = new Point();
    }
    
    void draw(final Graphics graphics) {
        final boolean b = this.needsHighlight() || OutputElm.sim.plotYElm == this;
        graphics.setFont(new Font("SansSerif", (int)(b ? 1 : 0), 14));
        graphics.setColor(b ? OutputElm.selectColor : OutputElm.whiteColor);
        String s = ((this.flags & 0x1) != 0x0) ? CircuitElm.getVoltageText(this.volts[0]) : "out";
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        if (this == OutputElm.sim.plotXElm) {
            s = "X";
        }
        if (this == OutputElm.sim.plotYElm) {
            s = "Y";
        }
        this.interpPoint(this.point1, this.point2, this.lead1, 1.0 - (fontMetrics.stringWidth(s) / 2 + 8) / this.dn);
        this.setBbox(this.point1, this.lead1, 0.0);
        this.drawCenteredText(graphics, s, this.x2, this.y2, true);
        this.setVoltageColor(graphics, this.volts[0]);
        if (b) {
            graphics.setColor(OutputElm.selectColor);
        }
        CircuitElm.drawThickLine(graphics, this.point1, this.lead1);
        this.drawPosts(graphics);
    }
    
    double getVoltageDiff() {
        return this.volts[0];
    }
    
    void getInfo(final String[] array) {
        array[0] = "output";
        array[1] = "V = " + CircuitElm.getVoltageText(this.volts[0]);
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            final EditInfo editInfo = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo.checkbox = new Checkbox("Show Voltage", (this.flags & 0x1) != 0x0);
            return editInfo;
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.flags = (editInfo.checkbox.getState() ? (this.flags | 0x1) : (this.flags & 0xFFFFFFFE));
        }
    }
}
