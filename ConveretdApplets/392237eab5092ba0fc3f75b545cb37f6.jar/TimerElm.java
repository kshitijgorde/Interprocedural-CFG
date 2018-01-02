import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class TimerElm extends ChipElm
{
    final int FLAG_RESET = 2;
    final int N_DIS = 0;
    final int N_TRIG = 1;
    final int N_THRES = 2;
    final int N_VIN = 3;
    final int N_CTL = 4;
    final int N_OUT = 5;
    final int N_RST = 6;
    boolean setOut;
    boolean out;
    
    int getDefaultFlags() {
        return 2;
    }
    
    public TimerElm(final int n, final int n2) {
        super(n, n2);
    }
    
    public TimerElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
    }
    
    String getChipName() {
        return "555 Timer";
    }
    
    void setupPins() {
        this.sizeX = 3;
        this.sizeY = 5;
        (this.pins = new Pin[7])[0] = new Pin(this, 1, 2, "dis");
        this.pins[1] = new Pin(this, 3, 2, "tr");
        this.pins[1].lineOver = true;
        this.pins[2] = new Pin(this, 4, 2, "th");
        this.pins[3] = new Pin(this, 1, 0, "Vin");
        this.pins[4] = new Pin(this, 1, 1, "ctl");
        this.pins[5] = new Pin(this, 2, 3, "out");
        final Pin pin = this.pins[5];
        final Pin pin2 = this.pins[5];
        final boolean b = true;
        pin2.state = b;
        pin.output = b;
        this.pins[6] = new Pin(this, 1, 3, "rst");
    }
    
    boolean nonLinear() {
        return true;
    }
    
    boolean hasReset() {
        return (this.flags & 0x2) != 0x0;
    }
    
    void stamp() {
        TimerElm.sim.stampResistor(this.nodes[3], this.nodes[4], 5000.0);
        TimerElm.sim.stampResistor(this.nodes[4], 0, 10000.0);
        TimerElm.sim.stampVoltageSource(0, this.nodes[5], this.pins[5].voltSource);
        TimerElm.sim.stampNonLinear(this.nodes[0]);
    }
    
    void calculateCurrent() {
        this.pins[3].current = (this.volts[4] - this.volts[3]) / 5000.0;
        this.pins[4].current = -this.volts[4] / 10000.0 - this.pins[3].current;
        this.pins[0].current = ((!this.out && !this.setOut) ? (-this.volts[0] / 10.0) : 0.0);
    }
    
    void startIteration() {
        this.out = (this.volts[5] > this.volts[3] / 2.0);
        this.setOut = false;
        if (this.volts[4] / 2.0 > this.volts[1]) {
            final boolean b = true;
            this.out = b;
            this.setOut = b;
        }
        if (this.volts[2] > this.volts[4] || (this.hasReset() && this.volts[6] < 0.7)) {
            this.out = false;
        }
    }
    
    void doStep() {
        if (!this.out && !this.setOut) {
            TimerElm.sim.stampResistor(this.nodes[0], 0, 10.0);
        }
        TimerElm.sim.updateVoltageSource(0, this.nodes[5], this.pins[5].voltSource, this.out ? this.volts[3] : 0.0);
    }
    
    int getPostCount() {
        return this.hasReset() ? 7 : 6;
    }
    
    int getVoltageSourceCount() {
        return 1;
    }
    
    int getDumpType() {
        return 165;
    }
}
