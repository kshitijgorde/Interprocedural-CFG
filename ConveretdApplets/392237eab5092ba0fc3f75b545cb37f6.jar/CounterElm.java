import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class CounterElm extends ChipElm
{
    final int FLAG_ENABLE = 2;
    
    public CounterElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public CounterElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
    }
    
    boolean needsBits() {
        return true;
    }
    
    String getChipName() {
        return "Counter";
    }
    
    void setupPins() {
        this.sizeX = 2;
        this.sizeY = ((this.bits > 2) ? this.bits : 2);
        (this.pins = new Pin[this.getPostCount()])[0] = new Pin(this, 0, 2, "");
        this.pins[0].clock = true;
        this.pins[1] = new Pin(this, this.sizeY - 1, 2, "R");
        this.pins[1].bubble = true;
        for (int i = 0; i != this.bits; ++i) {
            final int n = i + 2;
            this.pins[n] = new Pin(this, i, 3, "Q" + (this.bits - i - 1));
            final Pin pin = this.pins[n];
            final Pin pin2 = this.pins[n];
            final boolean b = true;
            pin2.state = b;
            pin.output = b;
        }
        if (this.hasEnable()) {
            this.pins[this.bits + 2] = new Pin(this, this.sizeY - 2, 2, "En");
        }
        this.allocNodes();
    }
    
    int getPostCount() {
        if (this.hasEnable()) {
            return this.bits + 3;
        }
        return this.bits + 2;
    }
    
    boolean hasEnable() {
        return (this.flags & 0x2) != 0x0;
    }
    
    int getVoltageSourceCount() {
        return this.bits;
    }
    
    void execute() {
        boolean value = true;
        if (this.hasEnable()) {
            value = this.pins[this.bits + 2].value;
        }
        if (this.pins[0].value && !this.lastClock && value) {
            for (int i = this.bits - 1; i >= 0; --i) {
                final int n = i + 2;
                if (!this.pins[n].value) {
                    this.pins[n].value = true;
                    break;
                }
                this.pins[n].value = false;
            }
        }
        if (!this.pins[1].value) {
            for (int j = 0; j != this.bits; ++j) {
                this.pins[j + 2].value = false;
            }
        }
        this.lastClock = this.pins[0].value;
    }
    
    int getDumpType() {
        return 164;
    }
}
