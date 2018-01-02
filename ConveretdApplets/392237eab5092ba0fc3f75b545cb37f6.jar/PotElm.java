import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.Point;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentListener;

// 
// Decompiled by Procyon v0.5.30
// 

class PotElm extends CircuitElm implements AdjustmentListener
{
    double position;
    double maxResistance;
    double resistance1;
    double resistance2;
    double current1;
    double current2;
    double current3;
    double curcount1;
    double curcount2;
    double curcount3;
    Scrollbar slider;
    Label label;
    String sliderText;
    Point post3;
    Point corner2;
    Point arrowPoint;
    Point midpoint;
    Point arrow1;
    Point arrow2;
    Point ps3;
    Point ps4;
    int bodyLen;
    
    public PotElm(final int n, final int n2) {
        super(n, n2);
        this.setup();
        this.maxResistance = 1000.0;
        this.position = 0.5;
        this.sliderText = "Resistance";
        this.createSlider();
    }
    
    public PotElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.maxResistance = new Double(stringTokenizer.nextToken());
        this.position = new Double(stringTokenizer.nextToken());
        this.sliderText = stringTokenizer.nextToken();
        while (stringTokenizer.hasMoreTokens()) {
            this.sliderText = this.sliderText + ' ' + stringTokenizer.nextToken();
        }
        this.createSlider();
    }
    
    void setup() {
    }
    
    int getPostCount() {
        return 3;
    }
    
    int getDumpType() {
        return 174;
    }
    
    Point getPost(final int n) {
        return (n == 0) ? this.point1 : ((n == 1) ? this.point2 : this.post3);
    }
    
    String dump() {
        return super.dump() + " " + this.maxResistance + " " + this.position + " " + this.sliderText;
    }
    
    void createSlider() {
        final CirSim sim = PotElm.sim;
        CirSim.main.add(this.label = new Label(this.sliderText, 1));
        final int n = (int)(this.position * 100.0);
        final CirSim sim2 = PotElm.sim;
        CirSim.main.add(this.slider = new Scrollbar(0, n, 1, 0, 101));
        final CirSim sim3 = PotElm.sim;
        CirSim.main.validate();
        this.slider.addAdjustmentListener(this);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        PotElm.sim.analyzeFlag = true;
        this.setPoints();
    }
    
    void delete() {
        final CirSim sim = PotElm.sim;
        CirSim.main.remove(this.label);
        final CirSim sim2 = PotElm.sim;
        CirSim.main.remove(this.slider);
    }
    
    void setPoints() {
        super.setPoints();
        int gridSize;
        if (CircuitElm.abs(this.dx) > CircuitElm.abs(this.dy)) {
            this.dx = PotElm.sim.snapGrid(this.dx / 2) * 2;
            final Point point2 = this.point2;
            final int n = this.point1.x + this.dx;
            this.x2 = n;
            point2.x = n;
            gridSize = ((this.dx < 0) ? this.dy : (-this.dy));
            this.point2.y = this.point1.y;
        }
        else {
            this.dy = PotElm.sim.snapGrid(this.dy / 2) * 2;
            final Point point3 = this.point2;
            final int n2 = this.point1.y + this.dy;
            this.y2 = n2;
            point3.y = n2;
            gridSize = ((this.dy > 0) ? this.dx : (-this.dx));
            this.point2.x = this.point1.x;
        }
        if (gridSize == 0) {
            gridSize = PotElm.sim.gridSize;
        }
        this.dn = CircuitElm.distance(this.point1, this.point2);
        final int n3 = 32;
        this.calcLeads(n3);
        this.position = this.slider.getValue() * 0.0099 + 0.005;
        final int n4 = (int)((this.position - 0.5) * n3);
        this.post3 = this.interpPoint(this.point1, this.point2, 0.5, gridSize);
        this.corner2 = this.interpPoint(this.point1, this.point2, n4 / this.dn + 0.5, gridSize);
        this.arrowPoint = this.interpPoint(this.point1, this.point2, n4 / this.dn + 0.5, 8 * CircuitElm.sign(gridSize));
        this.midpoint = this.interpPoint(this.point1, this.point2, n4 / this.dn + 0.5);
        this.arrow1 = new Point();
        this.arrow2 = new Point();
        final double n5 = CircuitElm.abs(gridSize) - 8;
        this.interpPoint2(this.corner2, this.arrowPoint, this.arrow1, this.arrow2, (n5 - 8.0) / n5, 8.0);
        this.ps3 = new Point();
        this.ps4 = new Point();
    }
    
    void draw(final Graphics graphics) {
        final int n = 16;
        int n2 = 0;
        final int n3 = PotElm.sim.euroResistorCheckItem.getState() ? 6 : 8;
        final double n4 = this.volts[0];
        final double n5 = this.volts[1];
        final double n6 = this.volts[2];
        this.setBbox(this.point1, this.point2, n3);
        this.draw2Leads(graphics);
        this.setPowerColor(graphics, true);
        final double n7 = 1.0 / n;
        final int n8 = (int)(n * this.position);
        if (!PotElm.sim.euroResistorCheckItem.getState()) {
            for (int i = 0; i != n; ++i) {
                int n9 = 0;
                switch (i & 0x3) {
                    case 0: {
                        n9 = 1;
                        break;
                    }
                    case 2: {
                        n9 = -1;
                        break;
                    }
                    default: {
                        n9 = 0;
                        break;
                    }
                }
                double n10 = n4 + (n6 - n4) * i / n8;
                if (i >= n8) {
                    n10 = n6 + (n5 - n6) * (i - n8) / (n - n8);
                }
                this.setVoltageColor(graphics, n10);
                this.interpPoint(this.lead1, this.lead2, PotElm.ps1, i * n7, n3 * n2);
                this.interpPoint(this.lead1, this.lead2, PotElm.ps2, (i + 1) * n7, n3 * n9);
                CircuitElm.drawThickLine(graphics, PotElm.ps1, PotElm.ps2);
                n2 = n9;
            }
        }
        else {
            this.setVoltageColor(graphics, n4);
            this.interpPoint2(this.lead1, this.lead2, PotElm.ps1, PotElm.ps2, 0.0, n3);
            CircuitElm.drawThickLine(graphics, PotElm.ps1, PotElm.ps2);
            for (int j = 0; j != n; ++j) {
                double n11 = n4 + (n6 - n4) * j / n8;
                if (j >= n8) {
                    n11 = n6 + (n5 - n6) * (j - n8) / (n - n8);
                }
                this.setVoltageColor(graphics, n11);
                this.interpPoint2(this.lead1, this.lead2, PotElm.ps1, PotElm.ps2, j * n7, n3);
                this.interpPoint2(this.lead1, this.lead2, this.ps3, this.ps4, (j + 1) * n7, n3);
                CircuitElm.drawThickLine(graphics, PotElm.ps1, this.ps3);
                CircuitElm.drawThickLine(graphics, PotElm.ps2, this.ps4);
            }
            this.interpPoint2(this.lead1, this.lead2, PotElm.ps1, PotElm.ps2, 1.0, n3);
            CircuitElm.drawThickLine(graphics, PotElm.ps1, PotElm.ps2);
        }
        this.setVoltageColor(graphics, n6);
        CircuitElm.drawThickLine(graphics, this.post3, this.corner2);
        CircuitElm.drawThickLine(graphics, this.corner2, this.arrowPoint);
        CircuitElm.drawThickLine(graphics, this.arrow1, this.arrowPoint);
        CircuitElm.drawThickLine(graphics, this.arrow2, this.arrowPoint);
        this.curcount1 = this.updateDotCount(this.current1, this.curcount1);
        this.curcount2 = this.updateDotCount(this.current2, this.curcount2);
        this.curcount3 = this.updateDotCount(this.current3, this.curcount3);
        if (PotElm.sim.dragElm != this) {
            this.drawDots(graphics, this.point1, this.midpoint, this.curcount1);
            this.drawDots(graphics, this.point2, this.midpoint, this.curcount2);
            this.drawDots(graphics, this.post3, this.corner2, this.curcount3);
            this.drawDots(graphics, this.corner2, this.midpoint, this.curcount3 + CircuitElm.distance(this.post3, this.corner2));
        }
        this.drawPosts(graphics);
    }
    
    void calculateCurrent() {
        this.current1 = (this.volts[0] - this.volts[2]) / this.resistance1;
        this.current2 = (this.volts[1] - this.volts[2]) / this.resistance2;
        this.current3 = -this.current1 - this.current2;
    }
    
    void stamp() {
        this.resistance1 = this.maxResistance * this.position;
        this.resistance2 = this.maxResistance * (1.0 - this.position);
        PotElm.sim.stampResistor(this.nodes[0], this.nodes[2], this.resistance1);
        PotElm.sim.stampResistor(this.nodes[2], this.nodes[1], this.resistance2);
    }
    
    void getInfo(final String[] array) {
        array[0] = "potentiometer";
        array[1] = "Vd = " + CircuitElm.getVoltageDText(this.getVoltageDiff());
        final int n = 2;
        final StringBuilder append = new StringBuilder().append("R1 = ");
        final double resistance1 = this.resistance1;
        final CirSim sim = PotElm.sim;
        array[n] = append.append(CircuitElm.getUnitText(resistance1, CirSim.ohmString)).toString();
        final int n2 = 3;
        final StringBuilder append2 = new StringBuilder().append("R2 = ");
        final double resistance2 = this.resistance2;
        final CirSim sim2 = PotElm.sim;
        array[n2] = append2.append(CircuitElm.getUnitText(resistance2, CirSim.ohmString)).toString();
        array[4] = "I1 = " + CircuitElm.getCurrentDText(this.current1);
        array[5] = "I2 = " + CircuitElm.getCurrentDText(this.current2);
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Resistance (ohms)", this.maxResistance, 0.0, 0.0);
        }
        if (n == 1) {
            final EditInfo editInfo = new EditInfo("Slider Text", 0.0, -1.0, -1.0);
            editInfo.text = this.sliderText;
            return editInfo;
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.maxResistance = editInfo.value;
        }
        if (n == 1) {
            this.sliderText = editInfo.textf.getText();
            this.label.setText(this.sliderText);
        }
    }
}
