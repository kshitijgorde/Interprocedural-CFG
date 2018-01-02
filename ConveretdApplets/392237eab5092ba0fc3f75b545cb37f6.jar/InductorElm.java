import java.awt.Checkbox;
import java.awt.Graphics;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class InductorElm extends CircuitElm
{
    Inductor ind;
    double inductance;
    
    public InductorElm(final int n, final int n2) {
        super(n, n2);
        this.ind = new Inductor(InductorElm.sim);
        this.inductance = 1.0;
        this.ind.setup(this.inductance, this.current, this.flags);
    }
    
    public InductorElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.ind = new Inductor(InductorElm.sim);
        this.inductance = new Double(stringTokenizer.nextToken());
        this.current = new Double(stringTokenizer.nextToken());
        this.ind.setup(this.inductance, this.current, this.flags);
    }
    
    int getDumpType() {
        return 108;
    }
    
    String dump() {
        return super.dump() + " " + this.inductance + " " + this.current;
    }
    
    void setPoints() {
        super.setPoints();
        this.calcLeads(32);
    }
    
    void draw(final Graphics graphics) {
        final double n = this.volts[0];
        final double n2 = this.volts[1];
        final int n3 = 8;
        this.setBbox(this.point1, this.point2, n3);
        this.draw2Leads(graphics);
        this.setPowerColor(graphics, false);
        this.drawCoil(graphics, 8, this.lead1, this.lead2, n, n2);
        if (InductorElm.sim.showValuesCheckItem.getState()) {
            this.drawValues(graphics, CircuitElm.getShortUnitText(this.inductance, "H"), n3);
        }
        this.doDots(graphics);
        this.drawPosts(graphics);
    }
    
    void reset() {
        final double[] volts = this.volts;
        final int n = 0;
        final double[] volts2 = this.volts;
        final int n2 = 1;
        final double n3 = 0.0;
        this.curcount = n3;
        volts[n] = (volts2[n2] = n3);
        this.current = n3;
        this.ind.reset();
    }
    
    void stamp() {
        this.ind.stamp(this.nodes[0], this.nodes[1]);
    }
    
    void startIteration() {
        this.ind.startIteration(this.volts[0] - this.volts[1]);
    }
    
    boolean nonLinear() {
        return this.ind.nonLinear();
    }
    
    void calculateCurrent() {
        this.current = this.ind.calculateCurrent(this.volts[0] - this.volts[1]);
    }
    
    void doStep() {
        this.ind.doStep(this.volts[0] - this.volts[1]);
    }
    
    void getInfo(final String[] array) {
        array[0] = "inductor";
        this.getBasicInfo(array);
        array[3] = "L = " + CircuitElm.getUnitText(this.inductance, "H");
        array[4] = "P = " + CircuitElm.getUnitText(this.getPower(), "W");
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Inductance (H)", this.inductance, 0.0, 0.0);
        }
        if (n == 1) {
            final EditInfo editInfo = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo.checkbox = new Checkbox("Trapezoidal Approximation", this.ind.isTrapezoidal());
            return editInfo;
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.inductance = editInfo.value;
        }
        if (n == 1) {
            if (editInfo.checkbox.getState()) {
                this.flags &= 0xFFFFFFFD;
            }
            else {
                this.flags |= 0x2;
            }
        }
        this.ind.setup(this.inductance, this.current, this.flags);
    }
}
