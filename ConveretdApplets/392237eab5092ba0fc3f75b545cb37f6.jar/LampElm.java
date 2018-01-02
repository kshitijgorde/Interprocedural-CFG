import java.awt.Graphics;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class LampElm extends CircuitElm
{
    double resistance;
    final double roomTemp = 300.0;
    double temp;
    double nom_pow;
    double nom_v;
    double warmTime;
    double coolTime;
    Point[] bulbLead;
    Point[] filament;
    Point bulb;
    int bulbR;
    final int filament_len = 24;
    
    public LampElm(final int n, final int n2) {
        super(n, n2);
        this.temp = 300.0;
        this.nom_pow = 100.0;
        this.nom_v = 120.0;
        this.warmTime = 0.4;
        this.coolTime = 0.4;
    }
    
    public LampElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.temp = new Double(stringTokenizer.nextToken());
        this.nom_pow = new Double(stringTokenizer.nextToken());
        this.nom_v = new Double(stringTokenizer.nextToken());
        this.warmTime = new Double(stringTokenizer.nextToken());
        this.coolTime = new Double(stringTokenizer.nextToken());
    }
    
    String dump() {
        return super.dump() + " " + this.temp + " " + this.nom_pow + " " + this.nom_v + " " + this.warmTime + " " + this.coolTime;
    }
    
    int getDumpType() {
        return 181;
    }
    
    void reset() {
        super.reset();
        this.temp = 300.0;
    }
    
    void setPoints() {
        super.setPoints();
        final int n = 16;
        this.calcLeads(n);
        this.bulbLead = this.newPointArray(2);
        this.filament = this.newPointArray(2);
        this.bulbR = 20;
        this.filament[0] = this.interpPoint(this.lead1, this.lead2, 0.0, 24.0);
        this.filament[1] = this.interpPoint(this.lead1, this.lead2, 1.0, 24.0);
        final double n2 = 24.0 - Math.sqrt(this.bulbR * this.bulbR - n * n);
        this.bulbLead[0] = this.interpPoint(this.lead1, this.lead2, 0.0, n2);
        this.bulbLead[1] = this.interpPoint(this.lead1, this.lead2, 1.0, n2);
        this.bulb = this.interpPoint(this.filament[0], this.filament[1], 0.5);
    }
    
    Color getTempColor() {
        if (this.temp < 1200.0) {
            int n = (int)(255.0 * (this.temp - 800.0) / 400.0);
            if (n < 0) {
                n = 0;
            }
            return new Color(n, 0, 0);
        }
        if (this.temp < 1700.0) {
            int n2 = (int)(255.0 * (this.temp - 1200.0) / 500.0);
            if (n2 < 0) {
                n2 = 0;
            }
            return new Color(255, n2, 0);
        }
        if (this.temp < 2400.0) {
            int n3 = (int)(255.0 * (this.temp - 1700.0) / 700.0);
            if (n3 < 0) {
                n3 = 0;
            }
            return new Color(255, 255, n3);
        }
        return Color.white;
    }
    
    void draw(final Graphics graphics) {
        final double n = this.volts[0];
        final double n2 = this.volts[1];
        this.setBbox(this.point1, this.point2, 4.0);
        this.adjustBbox(this.bulb.x - this.bulbR, this.bulb.y - this.bulbR, this.bulb.x + this.bulbR, this.bulb.y + this.bulbR);
        this.draw2Leads(graphics);
        this.setPowerColor(graphics, true);
        graphics.setColor(this.getTempColor());
        graphics.fillOval(this.bulb.x - this.bulbR, this.bulb.y - this.bulbR, this.bulbR * 2, this.bulbR * 2);
        graphics.setColor(Color.white);
        CircuitElm.drawThickCircle(graphics, this.bulb.x, this.bulb.y, this.bulbR);
        this.setVoltageColor(graphics, n);
        CircuitElm.drawThickLine(graphics, this.lead1, this.filament[0]);
        this.setVoltageColor(graphics, n2);
        CircuitElm.drawThickLine(graphics, this.lead2, this.filament[1]);
        this.setVoltageColor(graphics, (n + n2) * 0.5);
        CircuitElm.drawThickLine(graphics, this.filament[0], this.filament[1]);
        this.updateDotCount();
        if (LampElm.sim.dragElm != this) {
            this.drawDots(graphics, this.point1, this.lead1, this.curcount);
            final double n3 = this.curcount + (this.dn - 16.0) / 2.0;
            this.drawDots(graphics, this.lead1, this.filament[0], n3);
            final double n4 = n3 + 24.0;
            this.drawDots(graphics, this.filament[0], this.filament[1], n4);
            this.drawDots(graphics, this.filament[1], this.lead2, n4 + 16.0);
            this.drawDots(graphics, this.lead2, this.point2, this.curcount);
        }
        this.drawPosts(graphics);
    }
    
    void calculateCurrent() {
        this.current = (this.volts[0] - this.volts[1]) / this.resistance;
    }
    
    void stamp() {
        LampElm.sim.stampNonLinear(this.nodes[0]);
        LampElm.sim.stampNonLinear(this.nodes[1]);
    }
    
    boolean nonLinear() {
        return true;
    }
    
    void startIteration() {
        final double n = this.nom_v * this.nom_v / this.nom_pow;
        final double n2 = (this.temp > 5390.0) ? 5390.0 : this.temp;
        this.resistance = n * (1.26104 - 4.90662 * Math.sqrt(17.1839 / n2 - 0.00318794) - 7.8569 / (n2 - 187.56));
        final double n3 = 1.57E-4 * this.nom_pow;
        final double n4 = n3 * this.warmTime / 0.4;
        final double n5 = n3 * this.coolTime / 0.4;
        this.temp += this.getPower() * LampElm.sim.timeStep / n4;
        this.temp -= LampElm.sim.timeStep * (this.temp - 300.0) / (n5 * (2600.0 / this.nom_pow));
    }
    
    void doStep() {
        LampElm.sim.stampResistor(this.nodes[0], this.nodes[1], this.resistance);
    }
    
    void getInfo(final String[] array) {
        array[0] = "lamp";
        this.getBasicInfo(array);
        final int n = 3;
        final StringBuilder append = new StringBuilder().append("R = ");
        final double resistance = this.resistance;
        final CirSim sim = LampElm.sim;
        array[n] = append.append(CircuitElm.getUnitText(resistance, CirSim.ohmString)).toString();
        array[4] = "P = " + CircuitElm.getUnitText(this.getPower(), "W");
        array[5] = "T = " + (int)this.temp + " K";
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Nominal Power", this.nom_pow, 0.0, 0.0);
        }
        if (n == 1) {
            return new EditInfo("Nominal Voltage", this.nom_v, 0.0, 0.0);
        }
        if (n == 2) {
            return new EditInfo("Warmup Time (s)", this.warmTime, 0.0, 0.0);
        }
        if (n == 3) {
            return new EditInfo("Cooldown Time (s)", this.coolTime, 0.0, 0.0);
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0 && editInfo.value > 0.0) {
            this.nom_pow = editInfo.value;
        }
        if (n == 1 && editInfo.value > 0.0) {
            this.nom_v = editInfo.value;
        }
        if (n == 2 && editInfo.value > 0.0) {
            this.warmTime = editInfo.value;
        }
        if (n == 3 && editInfo.value > 0.0) {
            this.coolTime = editInfo.value;
        }
    }
}
