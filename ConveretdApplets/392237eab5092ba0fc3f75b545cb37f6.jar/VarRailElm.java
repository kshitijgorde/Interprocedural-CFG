import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.Label;
import java.awt.Scrollbar;

// 
// Decompiled by Procyon v0.5.30
// 

class VarRailElm extends RailElm
{
    Scrollbar slider;
    Label label;
    String sliderText;
    
    public VarRailElm(final int n, final int n2) {
        super(n, n2, 6);
        this.sliderText = "Voltage";
        this.frequency = this.maxVoltage;
        this.createSlider();
    }
    
    public VarRailElm(final int n, final int n2, final int n3, final int n4, final int n5, final StringTokenizer stringTokenizer) {
        super(n, n2, n3, n4, n5, stringTokenizer);
        this.sliderText = stringTokenizer.nextToken();
        while (stringTokenizer.hasMoreTokens()) {
            this.sliderText = this.sliderText + ' ' + stringTokenizer.nextToken();
        }
        this.createSlider();
    }
    
    String dump() {
        return super.dump() + " " + this.sliderText;
    }
    
    int getDumpType() {
        return 172;
    }
    
    void createSlider() {
        this.waveform = 6;
        final CirSim sim = VarRailElm.sim;
        CirSim.main.add(this.label = new Label(this.sliderText, 1));
        final int n = (int)((this.frequency - this.bias) * 100.0 / (this.maxVoltage - this.bias));
        final CirSim sim2 = VarRailElm.sim;
        CirSim.main.add(this.slider = new Scrollbar(0, n, 1, 0, 101));
        final CirSim sim3 = VarRailElm.sim;
        CirSim.main.validate();
    }
    
    double getVoltage() {
        return this.frequency = this.slider.getValue() * (this.maxVoltage - this.bias) / 100.0 + this.bias;
    }
    
    void delete() {
        final CirSim sim = VarRailElm.sim;
        CirSim.main.remove(this.label);
        final CirSim sim2 = VarRailElm.sim;
        CirSim.main.remove(this.slider);
    }
    
    public EditInfo getEditInfo(final int n) {
        if (n == 0) {
            return new EditInfo("Min Voltage", this.bias, -20.0, 20.0);
        }
        if (n == 1) {
            return new EditInfo("Max Voltage", this.maxVoltage, -20.0, 20.0);
        }
        if (n == 2) {
            final EditInfo editInfo = new EditInfo("Slider Text", 0.0, -1.0, -1.0);
            editInfo.text = this.sliderText;
            return editInfo;
        }
        return null;
    }
    
    public void setEditValue(final int n, final EditInfo editInfo) {
        if (n == 0) {
            this.bias = editInfo.value;
        }
        if (n == 1) {
            this.maxVoltage = editInfo.value;
        }
        if (n == 2) {
            this.sliderText = editInfo.textf.getText();
            this.label.setText(this.sliderText);
        }
    }
}
