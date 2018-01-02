import java.awt.Graphics;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class GroundElm extends CircuitElm
{
    public GroundElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public GroundElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
    }
    
    int getDumpType() {
        return 103;
    }
    
    int getPostCount() {
        return 1;
    }
    
    void draw(final Graphics graphics) {
        this.setVoltageColor(graphics, 0.0);
        CircuitElm.drawThickLine(graphics, this.point1, this.point2);
        for (int i = 0; i != 3; ++i) {
            this.interpPoint2(this.point1, this.point2, GroundElm.ps1, GroundElm.ps2, 1.0 + i * 5 / this.dn, 10 - i * 4);
            CircuitElm.drawThickLine(graphics, GroundElm.ps1, GroundElm.ps2);
        }
        this.doDots(graphics);
        this.interpPoint(this.point1, this.point2, GroundElm.ps2, 1.0 + 11.0 / this.dn);
        this.setBbox(this.point1, GroundElm.ps2, 11.0);
        this.drawPost(graphics, this.x, this.y, this.nodes[0]);
    }
    
    void setCurrent(final int n, final double n2) {
        this.current = -n2;
    }
    
    void stamp() {
        GroundElm.sim.stampVoltageSource(0, this.nodes[0], this.voltSource, 0.0);
    }
    
    double getVoltageDiff() {
        return 0.0;
    }
    
    int getVoltageSourceCount() {
        return 1;
    }
    
    void getInfo(final String[] array) {
        array[0] = "ground";
        array[1] = "I = " + CircuitElm.getCurrentText(this.getCurrent());
    }
    
    boolean hasGroundConnection(final int n) {
        return true;
    }
    
    boolean needsShortcut() {
        return true;
    }
}
