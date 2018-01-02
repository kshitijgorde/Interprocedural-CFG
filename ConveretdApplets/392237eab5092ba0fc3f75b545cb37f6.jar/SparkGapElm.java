import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

class SparkGapElm extends CircuitElm
{
    double resistance;
    double onresistance;
    double offresistance;
    double breakdown;
    double holdcurrent;
    boolean state;
    Polygon arrow1;
    Polygon arrow2;
    
    public SparkGapElm(final int n, final int n2) {
        super(n, n2);
        this.offresistance = 1.0E9;
        this.onresistance = 1000.0;
        this.breakdown = 1000.0;
        this.holdcurrent = 0.001;
        this.state = false;
    }
    
    public SparkGapElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.onresistance = new Double(stringTokenizer.nextToken());
        this.offresistance = new Double(stringTokenizer.nextToken());
        this.breakdown = new Double(stringTokenizer.nextToken());
        this.holdcurrent = new Double(stringTokenizer.nextToken());
    }
    
    boolean nonLinear() {
        return true;
    }
    
    int getDumpType() {
        return 187;
    }
    
    String dump() {
        return super.dump() + " " + this.onresistance + " " + this.offresistance + " " + this.breakdown + " " + this.holdcurrent;
    }
    
    void setPoints() {
        super.setPoints();
        final int n = 16;
        final int n2 = 8;
        this.calcLeads(n + n2);
        this.arrow1 = this.calcArrow(this.point1, this.interpPoint(this.point1, this.point2, (this.dn - n2) / (2.0 * this.dn)), n2, n2);
        this.arrow2 = this.calcArrow(this.point2, this.interpPoint(this.point1, this.point2, (this.dn + n2) / (2.0 * this.dn)), n2, n2);
    }
    
    void draw(final Graphics graphics) {
        final double n = this.volts[0];
        final double n2 = this.volts[1];
        this.setBbox(this.point1, this.point2, 8.0);
        this.draw2Leads(graphics);
        this.setPowerColor(graphics, true);
        this.setVoltageColor(graphics, this.volts[0]);
        graphics.fillPolygon(this.arrow1);
        this.setVoltageColor(graphics, this.volts[1]);
        graphics.fillPolygon(this.arrow2);
        if (this.state) {
            this.doDots(graphics);
        }
        this.drawPosts(graphics);
    }
    
    void calculateCurrent() {
        this.current = (this.volts[0] - this.volts[1]) / this.resistance;
    }
    
    void reset() {
        super.reset();
        this.state = false;
    }
    
    void startIteration() {
        if (Math.abs(this.current) < this.holdcurrent) {
            this.state = false;
        }
        if (Math.abs(this.volts[0] - this.volts[1]) > this.breakdown) {
            this.state = true;
        }
    }
    
    void doStep() {
        this.resistance = (this.state ? this.onresistance : this.offresistance);
        SparkGapElm.sim.stampResistor(this.nodes[0], this.nodes[1], this.resistance);
    }
    
    void stamp() {
        SparkGapElm.sim.stampNonLinear(this.nodes[0]);
        SparkGapElm.sim.stampNonLinear(this.nodes[1]);
    }
    
    void getInfo(final String[] array) {
        array[0] = "spark gap";
        this.getBasicInfo(array);
        array[3] = (this.state ? "on" : "off");
        final int n = 4;
        final StringBuilder append = new StringBuilder().append("Ron = ");
        final double onresistance = this.onresistance;
        final CirSim sim = SparkGapElm.sim;
        array[n] = append.append(CircuitElm.getUnitText(onresistance, CirSim.ohmString)).toString();
        final int n2 = 5;
        final StringBuilder append2 = new StringBuilder().append("Roff = ");
        final double offresistance = this.offresistance;
        final CirSim sim2 = SparkGapElm.sim;
        array[n2] = append2.append(CircuitElm.getUnitText(offresistance, CirSim.ohmString)).toString();
        array[6] = "Vbreakdown = " + CircuitElm.getUnitText(this.breakdown, "V");
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("On resistance (ohms)", this.onresistance, 0.0, 0.0);
        }
        if (n == 1) {
            return new EditInfo("Off resistance (ohms)", this.offresistance, 0.0, 0.0);
        }
        if (n == 2) {
            return new EditInfo("Breakdown voltage", this.breakdown, 0.0, 0.0);
        }
        if (n == 3) {
            return new EditInfo("Holding current (A)", this.holdcurrent, 0.0, 0.0);
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (editInfo.value > 0.0 && n == 0) {
            this.onresistance = editInfo.value;
        }
        if (editInfo.value > 0.0 && n == 1) {
            this.offresistance = editInfo.value;
        }
        if (editInfo.value > 0.0 && n == 2) {
            this.breakdown = editInfo.value;
        }
        if (editInfo.value > 0.0 && n == 3) {
            this.holdcurrent = editInfo.value;
        }
    }
    
    boolean needsShortcut() {
        return false;
    }
}
