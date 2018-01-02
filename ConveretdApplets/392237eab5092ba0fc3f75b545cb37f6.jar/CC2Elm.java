import java.awt.Graphics;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class CC2Elm extends ChipElm
{
    double gain;
    
    public CC2Elm(final int n, final int n2) {
        super(n, n2);
        this.gain = 1.0;
    }
    
    public CC2Elm(final int n, final int n2, final int n3) {
        super(n, n2);
        this.gain = n3;
    }
    
    public CC2Elm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
        this.gain = new Double(stringTokenizer.nextToken());
    }
    
    String dump() {
        return super.dump() + " " + this.gain;
    }
    
    String getChipName() {
        return "CC2";
    }
    
    void setupPins() {
        this.sizeX = 2;
        this.sizeY = 3;
        (this.pins = new Pin[3])[0] = new Pin(this, 0, 2, "X");
        this.pins[0].output = true;
        this.pins[1] = new Pin(this, 2, 2, "Y");
        this.pins[2] = new Pin(this, 1, 3, "Z");
    }
    
    void getInfo(final String[] array) {
        array[0] = ((this.gain == 1.0) ? "CCII+" : "CCII-");
        array[1] = "X,Y = " + CircuitElm.getVoltageText(this.volts[0]);
        array[2] = "Z = " + CircuitElm.getVoltageText(this.volts[2]);
        array[3] = "I = " + CircuitElm.getCurrentText(this.pins[0].current);
    }
    
    void stamp() {
        CC2Elm.sim.stampVoltageSource(0, this.nodes[0], this.pins[0].voltSource);
        CC2Elm.sim.stampVCVS(0, this.nodes[1], 1.0, this.pins[0].voltSource);
        CC2Elm.sim.stampCCCS(0, this.nodes[2], this.pins[0].voltSource, this.gain);
    }
    
    void draw(final Graphics graphics) {
        this.pins[2].current = this.pins[0].current * this.gain;
        this.drawChip(graphics);
    }
    
    int getPostCount() {
        return 3;
    }
    
    int getVoltageSourceCount() {
        return 1;
    }
    
    int getDumpType() {
        return 179;
    }
}
