import java.awt.Graphics;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class VCOElm extends ChipElm
{
    final double cResistance = 1000000.0;
    double cCurrent;
    int cDir;
    
    public VCOElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public VCOElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
    }
    
    String getChipName() {
        return "VCO";
    }
    
    void setupPins() {
        this.sizeX = 2;
        this.sizeY = 4;
        (this.pins = new Pin[6])[0] = new Pin(this, 0, 2, "Vi");
        this.pins[1] = new Pin(this, 3, 2, "Vo");
        this.pins[1].output = true;
        this.pins[2] = new Pin(this, 0, 3, "C");
        this.pins[3] = new Pin(this, 1, 3, "C");
        this.pins[4] = new Pin(this, 2, 3, "R1");
        this.pins[4].output = true;
        this.pins[5] = new Pin(this, 3, 3, "R2");
        this.pins[5].output = true;
    }
    
    boolean nonLinear() {
        return true;
    }
    
    void stamp() {
        VCOElm.sim.stampVoltageSource(0, this.nodes[1], this.pins[1].voltSource);
        VCOElm.sim.stampVoltageSource(this.nodes[0], this.nodes[4], this.pins[4].voltSource, 0.0);
        VCOElm.sim.stampVoltageSource(0, this.nodes[5], this.pins[5].voltSource, 5.0);
        VCOElm.sim.stampResistor(this.nodes[2], this.nodes[3], 1000000.0);
        VCOElm.sim.stampNonLinear(this.nodes[2]);
        VCOElm.sim.stampNonLinear(this.nodes[3]);
    }
    
    void doStep() {
        final double n = this.volts[3] - this.volts[2];
        double n2 = this.volts[1];
        int cDir = (n2 < 2.5) ? 1 : -1;
        if (n2 < 2.5 && n > 4.5) {
            n2 = 5.0;
            cDir = -1;
        }
        if (n2 > 2.5 && n < 0.5) {
            n2 = 0.0;
            cDir = 1;
        }
        VCOElm.sim.updateVoltageSource(0, this.nodes[1], this.pins[1].voltSource, n2);
        final int n3 = VCOElm.sim.nodeList.size() + this.pins[4].voltSource;
        final int n4 = VCOElm.sim.nodeList.size() + this.pins[5].voltSource;
        VCOElm.sim.stampMatrix(this.nodes[2], n3, cDir);
        VCOElm.sim.stampMatrix(this.nodes[2], n4, cDir);
        VCOElm.sim.stampMatrix(this.nodes[3], n3, -cDir);
        VCOElm.sim.stampMatrix(this.nodes[3], n4, -cDir);
        this.cDir = cDir;
    }
    
    void computeCurrent() {
        final double current = this.cDir * (this.pins[4].current + this.pins[5].current) + (this.volts[3] - this.volts[2]) / 1000000.0;
        this.pins[2].current = -current;
        this.pins[3].current = current;
        this.pins[0].current = -this.pins[4].current;
    }
    
    void draw(final Graphics graphics) {
        this.computeCurrent();
        this.drawChip(graphics);
    }
    
    int getPostCount() {
        return 6;
    }
    
    int getVoltageSourceCount() {
        return 3;
    }
    
    int getDumpType() {
        return 158;
    }
}
