import java.awt.Choice;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

class VoltageElm extends CircuitElm
{
    static final int FLAG_COS = 2;
    int waveform;
    static final int WF_DC = 0;
    static final int WF_AC = 1;
    static final int WF_SQUARE = 2;
    static final int WF_TRIANGLE = 3;
    static final int WF_SAWTOOTH = 4;
    static final int WF_PULSE = 5;
    static final int WF_VAR = 6;
    double frequency;
    double maxVoltage;
    double freqTimeZero;
    double bias;
    double phaseShift;
    double dutyCycle;
    final int circleSize = 17;
    
    VoltageElm(final int n, final int n2, final int waveform) {
        super(n, n2);
        this.waveform = waveform;
        this.maxVoltage = 5.0;
        this.frequency = 40.0;
        this.dutyCycle = 0.5;
        this.reset();
    }
    
    public VoltageElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5);
        this.maxVoltage = 5.0;
        this.frequency = 40.0;
        this.waveform = 0;
        this.dutyCycle = 0.5;
        try {
            this.waveform = new Integer(stringTokenizer.nextToken());
            this.frequency = new Double(stringTokenizer.nextToken());
            this.maxVoltage = new Double(stringTokenizer.nextToken());
            this.bias = new Double(stringTokenizer.nextToken());
            this.phaseShift = new Double(stringTokenizer.nextToken());
            this.dutyCycle = new Double(stringTokenizer.nextToken());
        }
        catch (Exception ex) {}
        if ((this.flags & 0x2) != 0x0) {
            this.flags &= 0xFFFFFFFD;
            this.phaseShift = 1.5707963267948966;
        }
        this.reset();
    }
    
    int getDumpType() {
        return 118;
    }
    
    String dump() {
        return super.dump() + " " + this.waveform + " " + this.frequency + " " + this.maxVoltage + " " + this.bias + " " + this.phaseShift + " " + this.dutyCycle;
    }
    
    void reset() {
        this.freqTimeZero = 0.0;
        this.curcount = 0.0;
    }
    
    double triangleFunc(final double n) {
        if (n < 3.141592653589793) {
            return n * 0.6366197723675814 - 1.0;
        }
        return 1.0 - (n - 3.141592653589793) * 0.6366197723675814;
    }
    
    void stamp() {
        if (this.waveform == 0) {
            VoltageElm.sim.stampVoltageSource(this.nodes[0], this.nodes[1], this.voltSource, this.getVoltage());
        }
        else {
            VoltageElm.sim.stampVoltageSource(this.nodes[0], this.nodes[1], this.voltSource);
        }
    }
    
    void doStep() {
        if (this.waveform != 0) {
            VoltageElm.sim.updateVoltageSource(this.nodes[0], this.nodes[1], this.voltSource, this.getVoltage());
        }
    }
    
    double getVoltage() {
        final double n = 6.283185307179586 * (VoltageElm.sim.t - this.freqTimeZero) * this.frequency + this.phaseShift;
        switch (this.waveform) {
            case 0: {
                return this.maxVoltage + this.bias;
            }
            case 1: {
                return Math.sin(n) * this.maxVoltage + this.bias;
            }
            case 2: {
                return this.bias + ((n % 6.283185307179586 > 6.283185307179586 * this.dutyCycle) ? (-this.maxVoltage) : this.maxVoltage);
            }
            case 3: {
                return this.bias + this.triangleFunc(n % 6.283185307179586) * this.maxVoltage;
            }
            case 4: {
                return this.bias + n % 6.283185307179586 * (this.maxVoltage / 3.141592653589793) - this.maxVoltage;
            }
            case 5: {
                return (n % 6.283185307179586 < 1.0) ? (this.maxVoltage + this.bias) : this.bias;
            }
            default: {
                return 0.0;
            }
        }
    }
    
    void setPoints() {
        super.setPoints();
        this.calcLeads((this.waveform == 0 || this.waveform == 6) ? 8 : 34);
    }
    
    void draw(final Graphics graphics) {
        this.setBbox(this.x, this.y, this.x2, this.y2);
        this.draw2Leads(graphics);
        if (this.waveform == 0) {
            this.setPowerColor(graphics, false);
            this.setVoltageColor(graphics, this.volts[0]);
            this.interpPoint2(this.lead1, this.lead2, VoltageElm.ps1, VoltageElm.ps2, 0.0, 10.0);
            CircuitElm.drawThickLine(graphics, VoltageElm.ps1, VoltageElm.ps2);
            this.setVoltageColor(graphics, this.volts[1]);
            final int n = 16;
            this.setBbox(this.point1, this.point2, n);
            this.interpPoint2(this.lead1, this.lead2, VoltageElm.ps1, VoltageElm.ps2, 1.0, n);
            CircuitElm.drawThickLine(graphics, VoltageElm.ps1, VoltageElm.ps2);
        }
        else {
            this.setBbox(this.point1, this.point2, 17.0);
            this.interpPoint(this.lead1, this.lead2, VoltageElm.ps1, 0.5);
            this.drawWaveform(graphics, VoltageElm.ps1);
        }
        this.updateDotCount();
        if (VoltageElm.sim.dragElm != this) {
            if (this.waveform == 0) {
                this.drawDots(graphics, this.point1, this.point2, this.curcount);
            }
            else {
                this.drawDots(graphics, this.point1, this.lead1, this.curcount);
                this.drawDots(graphics, this.point2, this.lead2, -this.curcount);
            }
        }
        this.drawPosts(graphics);
    }
    
    void drawWaveform(final Graphics graphics, final Point point) {
        graphics.setColor(this.needsHighlight() ? VoltageElm.selectColor : Color.gray);
        this.setPowerColor(graphics, false);
        final int x = point.x;
        final int y = point.y;
        CircuitElm.drawThickCircle(graphics, x, y, 17);
        final int n = 8;
        this.adjustBbox(x - 17, y - 17, x + 17, y + 17);
        switch (this.waveform) {
            case 2: {
                final int max = CircuitElm.max(x - n + 3, CircuitElm.min(x + n - 3, (int)(n * 2 * this.dutyCycle - n + x)));
                CircuitElm.drawThickLine(graphics, x - n, y - n, x - n, y);
                CircuitElm.drawThickLine(graphics, x - n, y - n, max, y - n);
                CircuitElm.drawThickLine(graphics, max, y - n, max, y + n);
                CircuitElm.drawThickLine(graphics, x + n, y + n, max, y + n);
                CircuitElm.drawThickLine(graphics, x + n, y, x + n, y + n);
                break;
            }
            case 5: {
                final int n2 = y + n / 2;
                CircuitElm.drawThickLine(graphics, x - n, n2 - n, x - n, n2);
                CircuitElm.drawThickLine(graphics, x - n, n2 - n, x - n / 2, n2 - n);
                CircuitElm.drawThickLine(graphics, x - n / 2, n2 - n, x - n / 2, n2);
                CircuitElm.drawThickLine(graphics, x - n / 2, n2, x + n, n2);
                break;
            }
            case 4: {
                CircuitElm.drawThickLine(graphics, x, y - n, x - n, y);
                CircuitElm.drawThickLine(graphics, x, y - n, x, y + n);
                CircuitElm.drawThickLine(graphics, x, y + n, x + n, y);
                break;
            }
            case 3: {
                final int n3 = 5;
                CircuitElm.drawThickLine(graphics, x - n3 * 2, y, x - n3, y - n);
                CircuitElm.drawThickLine(graphics, x - n3, y - n, x, y);
                CircuitElm.drawThickLine(graphics, x, y, x + n3, y + n);
                CircuitElm.drawThickLine(graphics, x + n3, y + n, x + n3 * 2, y);
                break;
            }
            case 1: {
                final int n4 = 10;
                int n5 = -1;
                int n6 = -1;
                for (int i = -n4; i <= n4; ++i) {
                    final int n7 = y + (int)(0.95 * Math.sin(i * 3.141592653589793 / n4) * n);
                    if (n5 != -1) {
                        CircuitElm.drawThickLine(graphics, n5, n6, x + i, n7);
                    }
                    n5 = x + i;
                    n6 = n7;
                }
                break;
            }
        }
        if (VoltageElm.sim.showValuesCheckItem.getState()) {
            final String shortUnitText = CircuitElm.getShortUnitText(this.frequency, "Hz");
            if (this.dx == 0 || this.dy == 0) {
                this.drawValues(graphics, shortUnitText, 17.0);
            }
        }
    }
    
    int getVoltageSourceCount() {
        return 1;
    }
    
    double getPower() {
        return -this.getVoltageDiff() * this.current;
    }
    
    double getVoltageDiff() {
        return this.volts[1] - this.volts[0];
    }
    
    void getInfo(final String[] array) {
        switch (this.waveform) {
            case 0:
            case 6: {
                array[0] = "voltage source";
                break;
            }
            case 1: {
                array[0] = "A/C source";
                break;
            }
            case 2: {
                array[0] = "square wave gen";
                break;
            }
            case 5: {
                array[0] = "pulse gen";
                break;
            }
            case 4: {
                array[0] = "sawtooth gen";
                break;
            }
            case 3: {
                array[0] = "triangle gen";
                break;
            }
        }
        array[1] = "I = " + CircuitElm.getCurrentText(this.getCurrent());
        array[2] = ((this instanceof RailElm) ? "V = " : "Vd = ") + CircuitElm.getVoltageText(this.getVoltageDiff());
        if (this.waveform != 0 && this.waveform != 6) {
            array[3] = "f = " + CircuitElm.getUnitText(this.frequency, "Hz");
            array[4] = "Vmax = " + CircuitElm.getVoltageText(this.maxVoltage);
            int n = 5;
            if (this.bias != 0.0) {
                array[n++] = "Voff = " + CircuitElm.getVoltageText(this.bias);
            }
            else if (this.frequency > 500.0) {
                array[n++] = "wavelength = " + CircuitElm.getUnitText(2.9979E8 / this.frequency, "m");
            }
            array[n++] = "P = " + CircuitElm.getUnitText(this.getPower(), "W");
        }
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo((this.waveform == 0) ? "Voltage" : "Max Voltage", this.maxVoltage, -20.0, 20.0);
        }
        if (n == 1) {
            final EditInfo editInfo = new EditInfo("Waveform", this.waveform, -1.0, -1.0);
            (editInfo.choice = new Choice()).add("D/C");
            editInfo.choice.add("A/C");
            editInfo.choice.add("Square Wave");
            editInfo.choice.add("Triangle");
            editInfo.choice.add("Sawtooth");
            editInfo.choice.add("Pulse");
            editInfo.choice.select(this.waveform);
            return editInfo;
        }
        if (this.waveform == 0) {
            return null;
        }
        if (n == 2) {
            return new EditInfo("Frequency (Hz)", this.frequency, 4.0, 500.0);
        }
        if (n == 3) {
            return new EditInfo("DC Offset (V)", this.bias, -20.0, 20.0);
        }
        if (n == 4) {
            return new EditInfo("Phase Offset (degrees)", this.phaseShift * 180.0 / 3.141592653589793, -180.0, 180.0).setDimensionless();
        }
        if (n == 5 && this.waveform == 2) {
            return new EditInfo("Duty Cycle", this.dutyCycle * 100.0, 0.0, 100.0).setDimensionless();
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.maxVoltage = editInfo.value;
        }
        if (n == 3) {
            this.bias = editInfo.value;
        }
        if (n == 2) {
            final double frequency = this.frequency;
            this.frequency = editInfo.value;
            final double frequency2 = 1.0 / (8.0 * VoltageElm.sim.timeStep);
            if (this.frequency > frequency2) {
                this.frequency = frequency2;
            }
            final double n2 = this.frequency - frequency;
            this.freqTimeZero = VoltageElm.sim.t - frequency * (VoltageElm.sim.t - this.freqTimeZero) / this.frequency;
        }
        if (n == 1) {
            final int waveform = this.waveform;
            this.waveform = editInfo.choice.getSelectedIndex();
            if (this.waveform == 0 && waveform != 0) {
                editInfo.newDialog = true;
                this.bias = 0.0;
            }
            else if (this.waveform != 0 && waveform == 0) {
                editInfo.newDialog = true;
            }
            if ((this.waveform == 2 || waveform == 2) && this.waveform != waveform) {
                editInfo.newDialog = true;
            }
            this.setPoints();
        }
        if (n == 4) {
            this.phaseShift = editInfo.value * 3.141592653589793 / 180.0;
        }
        if (n == 5) {
            this.dutyCycle = editInfo.value * 0.01;
        }
    }
}
