import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class PhaseCompElm extends ChipElm
{
    boolean ff1;
    boolean ff2;
    
    public PhaseCompElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public PhaseCompElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
    }
    
    String getChipName() {
        return "phase comparator";
    }
    
    void setupPins() {
        this.sizeX = 2;
        this.sizeY = 2;
        (this.pins = new Pin[3])[0] = new Pin(this, 0, 2, "I1");
        this.pins[1] = new Pin(this, 1, 2, "I2");
        this.pins[2] = new Pin(this, 0, 3, "O");
        this.pins[2].output = true;
    }
    
    boolean nonLinear() {
        return true;
    }
    
    void stamp() {
        PhaseCompElm.sim.stampNonLinear(PhaseCompElm.sim.nodeList.size() + this.pins[2].voltSource);
        PhaseCompElm.sim.stampNonLinear(0);
        PhaseCompElm.sim.stampNonLinear(this.nodes[2]);
    }
    
    void doStep() {
        final boolean value = this.volts[0] > 2.5;
        final boolean value2 = this.volts[1] > 2.5;
        if (value && !this.pins[0].value) {
            this.ff1 = true;
        }
        if (value2 && !this.pins[1].value) {
            this.ff2 = true;
        }
        if (this.ff1 && this.ff2) {
            final boolean b = false;
            this.ff2 = b;
            this.ff1 = b;
        }
        final double n = this.ff1 ? 5.0 : (this.ff2 ? 0.0 : -1.0);
        if (n != -1.0) {
            PhaseCompElm.sim.stampVoltageSource(0, this.nodes[2], this.pins[2].voltSource, n);
        }
        else {
            final int n2 = PhaseCompElm.sim.nodeList.size() + this.pins[2].voltSource;
            PhaseCompElm.sim.stampMatrix(n2, n2, 1.0);
        }
        this.pins[0].value = value;
        this.pins[1].value = value2;
    }
    
    int getPostCount() {
        return 3;
    }
    
    int getVoltageSourceCount() {
        return 1;
    }
    
    int getDumpType() {
        return 161;
    }
}
