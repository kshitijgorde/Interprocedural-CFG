import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class JKFlipFlopElm extends ChipElm
{
    public JKFlipFlopElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public JKFlipFlopElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
        this.pins[4].value = !this.pins[3].value;
    }
    
    String getChipName() {
        return "JK flip-flop";
    }
    
    void setupPins() {
        this.sizeX = 2;
        this.sizeY = 3;
        (this.pins = new Pin[5])[0] = new Pin(this, 0, 2, "J");
        this.pins[1] = new Pin(this, 1, 2, "");
        this.pins[1].clock = true;
        this.pins[1].bubble = true;
        this.pins[2] = new Pin(this, 2, 2, "K");
        this.pins[3] = new Pin(this, 0, 3, "Q");
        final Pin pin = this.pins[3];
        final Pin pin2 = this.pins[3];
        final boolean b = true;
        pin2.state = b;
        pin.output = b;
        this.pins[4] = new Pin(this, 2, 3, "Q");
        this.pins[4].output = true;
        this.pins[4].lineOver = true;
    }
    
    int getPostCount() {
        return 5;
    }
    
    int getVoltageSourceCount() {
        return 2;
    }
    
    void execute() {
        if (!this.pins[1].value && this.lastClock) {
            boolean value = this.pins[3].value;
            if (this.pins[0].value) {
                value = (!this.pins[2].value || !value);
            }
            else if (this.pins[2].value) {
                value = false;
            }
            this.pins[3].value = value;
            this.pins[4].value = !value;
        }
        this.lastClock = this.pins[1].value;
    }
    
    int getDumpType() {
        return 156;
    }
}
