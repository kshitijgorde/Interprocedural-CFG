import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class DACElm extends ChipElm
{
    public DACElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public DACElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
    }
    
    String getChipName() {
        return "DAC";
    }
    
    boolean needsBits() {
        return true;
    }
    
    void setupPins() {
        this.sizeX = 2;
        this.sizeY = ((this.bits > 2) ? this.bits : 2);
        this.pins = new Pin[this.getPostCount()];
        for (int i = 0; i != this.bits; ++i) {
            this.pins[i] = new Pin(this, this.bits - 1 - i, 2, "D" + i);
        }
        this.pins[this.bits] = new Pin(this, 0, 3, "O");
        this.pins[this.bits].output = true;
        this.pins[this.bits + 1] = new Pin(this, this.sizeY - 1, 3, "V+");
        this.allocNodes();
    }
    
    void doStep() {
        int n = 0;
        for (int i = 0; i != this.bits; ++i) {
            if (this.volts[i] > 2.5) {
                n |= 1 << i;
            }
        }
        DACElm.sim.updateVoltageSource(0, this.nodes[this.bits], this.pins[this.bits].voltSource, n * this.volts[this.bits + 1] / ((1 << this.bits) - 1));
    }
    
    int getVoltageSourceCount() {
        return 1;
    }
    
    int getPostCount() {
        return this.bits + 2;
    }
    
    int getDumpType() {
        return 166;
    }
}
