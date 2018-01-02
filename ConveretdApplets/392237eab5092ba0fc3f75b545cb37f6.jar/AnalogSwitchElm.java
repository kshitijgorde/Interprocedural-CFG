import java.awt.Checkbox;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class AnalogSwitchElm extends CircuitElm
{
    final int FLAG_INVERT = 1;
    double resistance;
    double r_on;
    double r_off;
    boolean open;
    Point ps;
    Point point3;
    Point lead3;
    
    public AnalogSwitchElm(final int n, final int n2) {
        super(n, n2);
        this.r_on = 20.0;
        this.r_off = 1.0E10;
    }
    
    public AnalogSwitchElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.r_on = 20.0;
        this.r_off = 1.0E10;
        try {
            this.r_on = new Double(stringTokenizer.nextToken());
            this.r_off = new Double(stringTokenizer.nextToken());
        }
        catch (Exception ex) {}
    }
    
    String dump() {
        return super.dump() + " " + this.r_on + " " + this.r_off;
    }
    
    int getDumpType() {
        return 159;
    }
    
    void setPoints() {
        super.setPoints();
        this.calcLeads(32);
        this.ps = new Point();
        final int n = 16;
        this.point3 = this.interpPoint(this.point1, this.point2, 0.5, -n);
        this.lead3 = this.interpPoint(this.point1, this.point2, 0.5, -n / 2);
    }
    
    void draw(final Graphics graphics) {
        final int n = 16;
        final boolean b = (this.open ? n : false) != 0;
        this.setBbox(this.point1, this.point2, n);
        this.draw2Leads(graphics);
        graphics.setColor(AnalogSwitchElm.lightGrayColor);
        this.interpPoint(this.lead1, this.lead2, this.ps, 1.0, b ? 1 : 0);
        CircuitElm.drawThickLine(graphics, this.lead1, this.ps);
        this.setVoltageColor(graphics, this.volts[2]);
        CircuitElm.drawThickLine(graphics, this.point3, this.lead3);
        if (!this.open) {
            this.doDots(graphics);
        }
        this.drawPosts(graphics);
    }
    
    void calculateCurrent() {
        this.current = (this.volts[0] - this.volts[1]) / this.resistance;
    }
    
    boolean nonLinear() {
        return true;
    }
    
    void stamp() {
        AnalogSwitchElm.sim.stampNonLinear(this.nodes[0]);
        AnalogSwitchElm.sim.stampNonLinear(this.nodes[1]);
    }
    
    void doStep() {
        this.open = (this.volts[2] < 2.5);
        if ((this.flags & 0x1) != 0x0) {
            this.open = !this.open;
        }
        this.resistance = (this.open ? this.r_off : this.r_on);
        AnalogSwitchElm.sim.stampResistor(this.nodes[0], this.nodes[1], this.resistance);
    }
    
    void drag(int x2, int y2) {
        x2 = AnalogSwitchElm.sim.snapGrid(x2);
        y2 = AnalogSwitchElm.sim.snapGrid(y2);
        if (CircuitElm.abs(this.x - x2) < CircuitElm.abs(this.y - y2)) {
            x2 = this.x;
        }
        else {
            y2 = this.y;
        }
        if ((CircuitElm.abs(this.x - x2) + CircuitElm.abs(this.y - y2)) / 2 % AnalogSwitchElm.sim.gridSize != 0) {
            return;
        }
        this.x2 = x2;
        this.y2 = y2;
        this.setPoints();
    }
    
    int getPostCount() {
        return 3;
    }
    
    Point getPost(final int n) {
        return (n == 0) ? this.point1 : ((n == 1) ? this.point2 : this.point3);
    }
    
    void getInfo(final String[] array) {
        array[0] = "analog switch";
        array[1] = (this.open ? "open" : "closed");
        array[2] = "Vd = " + CircuitElm.getVoltageDText(this.getVoltageDiff());
        array[3] = "I = " + CircuitElm.getCurrentDText(this.getCurrent());
        array[4] = "Vc = " + CircuitElm.getVoltageText(this.volts[2]);
    }
    
    boolean getConnection(final int n, final int n2) {
        return n != 2 && n2 != 2;
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            final EditInfo editInfo = new EditInfo("", 0.0, -1.0, -1.0);
            editInfo.checkbox = new Checkbox("Normally closed", (this.flags & 0x1) != 0x0);
            return editInfo;
        }
        if (n == 1) {
            return new EditInfo("On Resistance (ohms)", this.r_on, 0.0, 0.0);
        }
        if (n == 2) {
            return new EditInfo("Off Resistance (ohms)", this.r_off, 0.0, 0.0);
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.flags = (editInfo.checkbox.getState() ? (this.flags | 0x1) : (this.flags & 0xFFFFFFFE));
        }
        if (n == 1 && editInfo.value > 0.0) {
            this.r_on = editInfo.value;
        }
        if (n == 2 && editInfo.value > 0.0) {
            this.r_off = editInfo.value;
        }
    }
}
