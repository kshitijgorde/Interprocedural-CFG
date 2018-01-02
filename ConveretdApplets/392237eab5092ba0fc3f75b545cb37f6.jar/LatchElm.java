import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class LatchElm extends ChipElm
{
    int loadPin;
    boolean lastLoad;
    
    public LatchElm(final int n, final int n2) {
        super(n, n2);
        this.lastLoad = false;
    }
    
    public LatchElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
        this.lastLoad = false;
    }
    
    String getChipName() {
        return "Latch";
    }
    
    boolean needsBits() {
        return true;
    }
    
    void setupPins() {
        this.sizeX = 2;
        this.sizeY = this.bits + 1;
        this.pins = new Pin[this.getPostCount()];
        for (int i = 0; i != this.bits; ++i) {
            this.pins[i] = new Pin(this, this.bits - 1 - i, 2, "I" + i);
        }
        for (int j = 0; j != this.bits; ++j) {
            this.pins[j + this.bits] = new Pin(this, this.bits - 1 - j, 3, "O");
            this.pins[j + this.bits].output = true;
        }
        this.pins[this.loadPin = this.bits * 2] = new Pin(this, this.bits, 2, "Ld");
        this.allocNodes();
    }
    
    void execute() {
        if (this.pins[this.loadPin].value && !this.lastLoad) {
            for (int i = 0; i != this.bits; ++i) {
                this.pins[i + this.bits].value = this.pins[i].value;
            }
        }
        this.lastLoad = this.pins[this.loadPin].value;
    }
    
    int getVoltageSourceCount() {
        return this.bits;
    }
    
    int getPostCount() {
        return this.bits * 2 + 1;
    }
    
    int getDumpType() {
        return 168;
    }
}
