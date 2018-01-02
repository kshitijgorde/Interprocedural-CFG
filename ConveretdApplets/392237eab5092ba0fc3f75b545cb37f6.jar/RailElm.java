import java.awt.Font;
import java.awt.Graphics;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class RailElm extends VoltageElm
{
    final int FLAG_CLOCK = 1;
    
    public RailElm(final int n, final int n2) {
        super(n, n2, 0);
    }
    
    RailElm(final int n, final int n2, final int n3) {
        super(n, n2, n3);
    }
    
    public RailElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
    }
    
    int getDumpType() {
        return 82;
    }
    
    int getPostCount() {
        return 1;
    }
    
    void setPoints() {
        super.setPoints();
        this.lead1 = this.interpPoint(this.point1, this.point2, 1.0 - 17.0 / this.dn);
    }
    
    void draw(final Graphics graphics) {
        this.setBbox(this.point1, this.point2, 17.0);
        this.setVoltageColor(graphics, this.volts[0]);
        CircuitElm.drawThickLine(graphics, this.point1, this.lead1);
        final boolean b = this.waveform == 2 && (this.flags & 0x1) != 0x0;
        if (this.waveform == 0 || this.waveform == 6 || b) {
            graphics.setFont(new Font("SansSerif", 0, 12));
            graphics.setColor(this.needsHighlight() ? RailElm.selectColor : RailElm.whiteColor);
            this.setPowerColor(graphics, false);
            final double voltage = this.getVoltage();
            String s = CircuitElm.getShortUnitText(voltage, "V");
            if (Math.abs(voltage) < 1.0) {
                s = RailElm.showFormat.format(voltage) + "V";
            }
            if (this.getVoltage() > 0.0) {
                s = "+" + s;
            }
            if (this instanceof AntennaElm) {
                s = "Ant";
            }
            if (b) {
                s = "CLK";
            }
            this.drawCenteredText(graphics, s, this.x2, this.y2, true);
        }
        else {
            this.drawWaveform(graphics, this.point2);
        }
        this.drawPosts(graphics);
        this.curcount = this.updateDotCount(-this.current, this.curcount);
        if (RailElm.sim.dragElm != this) {
            this.drawDots(graphics, this.point1, this.lead1, this.curcount);
        }
    }
    
    double getVoltageDiff() {
        return this.volts[0];
    }
    
    void stamp() {
        if (this.waveform == 0) {
            RailElm.sim.stampVoltageSource(0, this.nodes[0], this.voltSource, this.getVoltage());
        }
        else {
            RailElm.sim.stampVoltageSource(0, this.nodes[0], this.voltSource);
        }
    }
    
    void doStep() {
        if (this.waveform != 0) {
            RailElm.sim.updateVoltageSource(0, this.nodes[0], this.voltSource, this.getVoltage());
        }
    }
    
    boolean hasGroundConnection(final int n) {
        return true;
    }
}
