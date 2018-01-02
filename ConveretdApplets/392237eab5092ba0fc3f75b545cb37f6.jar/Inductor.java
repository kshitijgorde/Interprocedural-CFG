// 
// Decompiled by Procyon v0.5.30
// 

class Inductor
{
    public static final int FLAG_BACK_EULER = 2;
    int[] nodes;
    int flags;
    CirSim sim;
    double inductance;
    double compResistance;
    double current;
    double curSourceValue;
    
    Inductor(final CirSim sim) {
        this.sim = sim;
        this.nodes = new int[2];
    }
    
    void setup(final double inductance, final double current, final int flags) {
        this.inductance = inductance;
        this.current = current;
        this.flags = flags;
    }
    
    boolean isTrapezoidal() {
        return (this.flags & 0x2) == 0x0;
    }
    
    void reset() {
        this.current = 0.0;
    }
    
    void stamp(final int n, final int n2) {
        this.nodes[0] = n;
        this.nodes[1] = n2;
        if (this.isTrapezoidal()) {
            this.compResistance = 2.0 * this.inductance / this.sim.timeStep;
        }
        else {
            this.compResistance = this.inductance / this.sim.timeStep;
        }
        this.sim.stampResistor(this.nodes[0], this.nodes[1], this.compResistance);
        this.sim.stampRightSide(this.nodes[0]);
        this.sim.stampRightSide(this.nodes[1]);
    }
    
    boolean nonLinear() {
        return false;
    }
    
    void startIteration(final double n) {
        if (this.isTrapezoidal()) {
            this.curSourceValue = n / this.compResistance + this.current;
        }
        else {
            this.curSourceValue = this.current;
        }
    }
    
    double calculateCurrent(final double n) {
        if (this.compResistance > 0.0) {
            this.current = n / this.compResistance + this.curSourceValue;
        }
        return this.current;
    }
    
    void doStep(final double n) {
        this.sim.stampCurrentSource(this.nodes[0], this.nodes[1], this.curSourceValue);
    }
}
