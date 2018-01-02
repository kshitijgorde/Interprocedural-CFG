// 
// Decompiled by Procyon v0.5.30
// 

class ClockElm extends RailElm
{
    public ClockElm(final int n, final int n2) {
        super(n, n2, 2);
        this.maxVoltage = 2.5;
        this.bias = 2.5;
        this.frequency = 100.0;
        this.flags |= 0x1;
    }
    
    Class getDumpClass() {
        return RailElm.class;
    }
}
