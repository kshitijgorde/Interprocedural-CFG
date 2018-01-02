import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class DecadeElm extends ChipElm
{
    public DecadeElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public DecadeElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
    }
    
    String getChipName() {
        return "decade counter";
    }
    
    boolean needsBits() {
        return true;
    }
    
    void setupPins() {
        this.sizeX = ((this.bits > 2) ? this.bits : 2);
        this.sizeY = 2;
        (this.pins = new Pin[this.getPostCount()])[0] = new Pin(this, 1, 2, "");
        this.pins[0].clock = true;
        this.pins[1] = new Pin(this, this.sizeX - 1, 1, "R");
        this.pins[1].bubble = true;
        for (int i = 0; i != this.bits; ++i) {
            final int n = i + 2;
            this.pins[n] = new Pin(this, i, 0, "Q" + i);
            final Pin pin = this.pins[n];
            final Pin pin2 = this.pins[n];
            final boolean b = true;
            pin2.state = b;
            pin.output = b;
        }
        this.allocNodes();
    }
    
    int getPostCount() {
        return this.bits + 2;
    }
    
    int getVoltageSourceCount() {
        return this.bits;
    }
    
    void execute() {
        if (this.pins[0].value && !this.lastClock) {
            int n;
            for (n = 0; n != this.bits && !this.pins[n + 2].value; ++n) {}
            if (n < this.bits) {
                this.pins[n++ + 2].value = false;
            }
            this.pins[n % this.bits + 2].value = true;
        }
        if (!this.pins[1].value) {
            for (int i = 1; i != this.bits; ++i) {
                this.pins[i + 2].value = false;
            }
            this.pins[2].value = true;
        }
        this.lastClock = this.pins[0].value;
    }
    
    int getDumpType() {
        return 163;
    }
}
