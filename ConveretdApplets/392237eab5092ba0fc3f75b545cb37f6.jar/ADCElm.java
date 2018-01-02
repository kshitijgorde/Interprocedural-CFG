import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class ADCElm extends ChipElm
{
    public ADCElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public ADCElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
    }
    
    String getChipName() {
        return "ADC";
    }
    
    boolean needsBits() {
        return true;
    }
    
    void setupPins() {
        this.sizeX = 2;
        this.sizeY = ((this.bits > 2) ? this.bits : 2);
        this.pins = new Pin[this.getPostCount()];
        for (int i = 0; i != this.bits; ++i) {
            this.pins[i] = new Pin(this, this.bits - 1 - i, 3, "D" + i);
            this.pins[i].output = true;
        }
        this.pins[this.bits] = new Pin(this, 0, 2, "In");
        this.pins[this.bits + 1] = new Pin(this, this.sizeY - 1, 2, "V+");
        this.allocNodes();
    }
    
    void execute() {
        final int n = (1 << this.bits) - 1;
        final int min = CircuitElm.min(n, CircuitElm.max(0, (int)(n * this.volts[this.bits] / this.volts[this.bits + 1])));
        for (int i = 0; i != this.bits; ++i) {
            this.pins[i].value = ((min & 1 << i) != 0x0);
        }
    }
    
    int getVoltageSourceCount() {
        return this.bits;
    }
    
    int getPostCount() {
        return this.bits + 2;
    }
    
    int getDumpType() {
        return 167;
    }
}
