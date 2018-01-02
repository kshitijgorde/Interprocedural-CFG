import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class ResistorElm extends CircuitElm
{
    double resistance;
    Point ps3;
    Point ps4;
    
    public ResistorElm(final int n, final int n2) {
        super(n, n2);
        this.resistance = 100.0;
    }
    
    public ResistorElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.resistance = new Double(stringTokenizer.nextToken());
    }
    
    int getDumpType() {
        return 114;
    }
    
    String dump() {
        return super.dump() + " " + this.resistance;
    }
    
    void setPoints() {
        super.setPoints();
        this.calcLeads(32);
        this.ps3 = new Point();
        this.ps4 = new Point();
    }
    
    void draw(final Graphics graphics) {
        final int n = 16;
        int n2 = 0;
        final int n3 = ResistorElm.sim.euroResistorCheckItem.getState() ? 6 : 8;
        final double n4 = this.volts[0];
        final double n5 = this.volts[1];
        this.setBbox(this.point1, this.point2, n3);
        this.draw2Leads(graphics);
        this.setPowerColor(graphics, true);
        final double n6 = 1.0 / n;
        if (!ResistorElm.sim.euroResistorCheckItem.getState()) {
            for (int i = 0; i != n; ++i) {
                int n7 = 0;
                switch (i & 0x3) {
                    case 0: {
                        n7 = 1;
                        break;
                    }
                    case 2: {
                        n7 = -1;
                        break;
                    }
                    default: {
                        n7 = 0;
                        break;
                    }
                }
                this.setVoltageColor(graphics, n4 + (n5 - n4) * i / n);
                this.interpPoint(this.lead1, this.lead2, ResistorElm.ps1, i * n6, n3 * n2);
                this.interpPoint(this.lead1, this.lead2, ResistorElm.ps2, (i + 1) * n6, n3 * n7);
                CircuitElm.drawThickLine(graphics, ResistorElm.ps1, ResistorElm.ps2);
                n2 = n7;
            }
        }
        else {
            this.setVoltageColor(graphics, n4);
            this.interpPoint2(this.lead1, this.lead2, ResistorElm.ps1, ResistorElm.ps2, 0.0, n3);
            CircuitElm.drawThickLine(graphics, ResistorElm.ps1, ResistorElm.ps2);
            for (int j = 0; j != n; ++j) {
                this.setVoltageColor(graphics, n4 + (n5 - n4) * j / n);
                this.interpPoint2(this.lead1, this.lead2, ResistorElm.ps1, ResistorElm.ps2, j * n6, n3);
                this.interpPoint2(this.lead1, this.lead2, this.ps3, this.ps4, (j + 1) * n6, n3);
                CircuitElm.drawThickLine(graphics, ResistorElm.ps1, this.ps3);
                CircuitElm.drawThickLine(graphics, ResistorElm.ps2, this.ps4);
            }
            this.interpPoint2(this.lead1, this.lead2, ResistorElm.ps1, ResistorElm.ps2, 1.0, n3);
            CircuitElm.drawThickLine(graphics, ResistorElm.ps1, ResistorElm.ps2);
        }
        if (ResistorElm.sim.showValuesCheckItem.getState()) {
            this.drawValues(graphics, CircuitElm.getShortUnitText(this.resistance, ""), n3);
        }
        this.doDots(graphics);
        this.drawPosts(graphics);
    }
    
    void calculateCurrent() {
        this.current = (this.volts[0] - this.volts[1]) / this.resistance;
    }
    
    void stamp() {
        ResistorElm.sim.stampResistor(this.nodes[0], this.nodes[1], this.resistance);
    }
    
    void getInfo(final String[] array) {
        array[0] = "resistor";
        this.getBasicInfo(array);
        final int n = 3;
        final StringBuilder append = new StringBuilder().append("R = ");
        final double resistance = this.resistance;
        final CirSim sim = ResistorElm.sim;
        array[n] = append.append(CircuitElm.getUnitText(resistance, CirSim.ohmString)).toString();
        array[4] = "P = " + CircuitElm.getUnitText(this.getPower(), "W");
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Resistance (ohms)", this.resistance, 0.0, 0.0);
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (editInfo.value > 0.0) {
            this.resistance = editInfo.value;
        }
    }
    
    boolean needsShortcut() {
        return true;
    }
}
