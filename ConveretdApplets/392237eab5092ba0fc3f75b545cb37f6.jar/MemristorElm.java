import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class MemristorElm extends CircuitElm
{
    double r_on;
    double r_off;
    double dopeWidth;
    double totalWidth;
    double mobility;
    double resistance;
    Point ps3;
    Point ps4;
    
    public MemristorElm(final int n, final int n2) {
        super(n, n2);
        this.r_on = 100.0;
        this.r_off = 160.0 * this.r_on;
        this.dopeWidth = 0.0;
        this.totalWidth = 1.0E-8;
        this.mobility = 1.0E-10;
        this.resistance = 100.0;
    }
    
    public MemristorElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.r_on = new Double(stringTokenizer.nextToken());
        this.r_off = new Double(stringTokenizer.nextToken());
        this.dopeWidth = new Double(stringTokenizer.nextToken());
        this.totalWidth = new Double(stringTokenizer.nextToken());
        this.mobility = new Double(stringTokenizer.nextToken());
        this.resistance = 100.0;
    }
    
    int getDumpType() {
        return 109;
    }
    
    String dump() {
        return super.dump() + " " + this.r_on + " " + this.r_off + " " + this.dopeWidth + " " + this.totalWidth + " " + this.mobility;
    }
    
    void setPoints() {
        super.setPoints();
        this.calcLeads(32);
        this.ps3 = new Point();
        this.ps4 = new Point();
    }
    
    void draw(final Graphics graphics) {
        final int n = 6;
        int n2 = 0;
        final double n3 = this.volts[0];
        final double n4 = this.volts[1];
        final int n5 = 2 + (int)(8.0 * (1.0 - this.dopeWidth / this.totalWidth));
        this.setBbox(this.point1, this.point2, n5);
        this.draw2Leads(graphics);
        this.setPowerColor(graphics, true);
        final double n6 = 1.0 / n;
        for (int i = 0; i <= n; ++i) {
            int n7 = ((i & 0x1) == 0x0) ? 1 : -1;
            if (i == n) {
                n7 = 0;
            }
            this.setVoltageColor(graphics, n3 + (n4 - n3) * i / n);
            this.interpPoint(this.lead1, this.lead2, MemristorElm.ps1, i * n6, n5 * n2);
            this.interpPoint(this.lead1, this.lead2, MemristorElm.ps2, i * n6, n5 * n7);
            CircuitElm.drawThickLine(graphics, MemristorElm.ps1, MemristorElm.ps2);
            if (i == n) {
                break;
            }
            this.interpPoint(this.lead1, this.lead2, MemristorElm.ps1, (i + 1) * n6, n5 * n7);
            CircuitElm.drawThickLine(graphics, MemristorElm.ps1, MemristorElm.ps2);
            n2 = n7;
        }
        this.doDots(graphics);
        this.drawPosts(graphics);
    }
    
    boolean nonLinear() {
        return true;
    }
    
    void calculateCurrent() {
        this.current = (this.volts[0] - this.volts[1]) / this.resistance;
    }
    
    void reset() {
        this.dopeWidth = 0.0;
    }
    
    void startIteration() {
        final double n = this.dopeWidth / this.totalWidth;
        this.dopeWidth += MemristorElm.sim.timeStep * this.mobility * this.r_on * this.current / this.totalWidth;
        if (this.dopeWidth < 0.0) {
            this.dopeWidth = 0.0;
        }
        if (this.dopeWidth > this.totalWidth) {
            this.dopeWidth = this.totalWidth;
        }
        this.resistance = this.r_on * n + this.r_off * (1.0 - n);
    }
    
    void stamp() {
        MemristorElm.sim.stampNonLinear(this.nodes[0]);
        MemristorElm.sim.stampNonLinear(this.nodes[1]);
    }
    
    void doStep() {
        MemristorElm.sim.stampResistor(this.nodes[0], this.nodes[1], this.resistance);
    }
    
    void getInfo(final String[] array) {
        array[0] = "memristor";
        this.getBasicInfo(array);
        final int n = 3;
        final StringBuilder append = new StringBuilder().append("R = ");
        final double resistance = this.resistance;
        final CirSim sim = MemristorElm.sim;
        array[n] = append.append(CircuitElm.getUnitText(resistance, CirSim.ohmString)).toString();
        array[4] = "P = " + CircuitElm.getUnitText(this.getPower(), "W");
    }
    
    double getScopeValue(final int n) {
        return (n == 2) ? this.resistance : ((n == 1) ? this.getPower() : this.getVoltageDiff());
    }
    
    String getScopeUnits(final int n) {
        String ohmString;
        if (n == 2) {
            final CirSim sim = MemristorElm.sim;
            ohmString = CirSim.ohmString;
        }
        else {
            ohmString = ((n == 1) ? "W" : "V");
        }
        return ohmString;
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Max Resistance (ohms)", this.r_on, 0.0, 0.0);
        }
        if (n == 1) {
            return new EditInfo("Min Resistance (ohms)", this.r_off, 0.0, 0.0);
        }
        if (n == 2) {
            return new EditInfo("Width of Doped Region (nm)", this.dopeWidth * 1.0E9, 0.0, 0.0);
        }
        if (n == 3) {
            return new EditInfo("Total Width (nm)", this.totalWidth * 1.0E9, 0.0, 0.0);
        }
        if (n == 4) {
            return new EditInfo("Mobility (um^2/(s*V))", this.mobility * 1.0E12, 0.0, 0.0);
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.r_on = editInfo.value;
        }
        if (n == 1) {
            this.r_off = editInfo.value;
        }
        if (n == 2) {
            this.dopeWidth = editInfo.value * 1.0E-9;
        }
        if (n == 3) {
            this.totalWidth = editInfo.value * 1.0E-9;
        }
        if (n == 4) {
            this.mobility = editInfo.value * 1.0E-12;
        }
    }
}
