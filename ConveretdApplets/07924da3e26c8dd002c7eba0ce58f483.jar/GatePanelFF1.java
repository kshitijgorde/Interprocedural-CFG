import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class GatePanelFF1 extends GatePanel
{
    public GatePanelFF1(final int x, final int y, final int width, final int height) {
        super(x, y, width, height, false);
    }
    
    protected void initComponents() {
        this._bitRadioInputs = new BitRadioButton[2];
        this._gateComponents = new GateComponent[3];
        this._bitRadioOutputs = new BitRadioButton[1];
        this.add(this._gateComponents[0] = new GateComponent(225, 75));
        (this._bitRadioInputs[0] = new BitPushButton(true, false)).setBounds(this._gateComponents[0].getInput0().getTerminal().x - this._bitRadioInputs[0].getWidth() * 4, this._gateComponents[0].getInput0().getTerminal().y - this._bitRadioInputs[0].getHeight() / 2, this._bitRadioInputs[0].getWidth(), this._bitRadioInputs[0].getHeight());
        this.add(this._bitRadioInputs[0]);
        BitLine line = new BitLine(this._bitRadioInputs[0], this._gateComponents[0].getInput0(), true, false);
        this.add(line, 0);
        this.add(this._gateComponents[1] = new GateComponent(225, 150));
        this.add(this._gateComponents[2] = new GateComponent(165, this._gateComponents[1].getInput1().getTerminal().y - 23));
        (this._bitRadioInputs[1] = new BitPushButton(true, false)).setBounds(this._bitRadioInputs[0].getX(), this._gateComponents[2].getOutput().getTerminal().y - this._bitRadioInputs[1].getHeight() / 2, this._bitRadioInputs[1].getWidth(), this._bitRadioInputs[1].getHeight());
        this.add(this._bitRadioInputs[1]);
        line = new BitLine(this._bitRadioInputs[1], this._gateComponents[2].getInput0(), true, false);
        this.add(line, 0);
        line = new BitLine(this._gateComponents[2].getOutput(), this._gateComponents[1].getInput1(), false, false);
        this.add(line, 0);
        final BitTerminal term0 = new BitTerminal(true, this._gateComponents[1].getOutput().getTerminal().x + 30, this._gateComponents[1].getOutput().getTerminal().y);
        line = new BitLine(this._gateComponents[1].getOutput(), term0, false, true, false, false, true);
        this.add(line, 0);
        final BitTerminal term2 = new BitTerminal(true, this._gateComponents[0].getInput1().getTerminal().x - 35, this._gateComponents[0].getInput1().getTerminal().y);
        line = new BitLine(term0, term2, false, false, true, true, true);
        this.add(line, 0);
        line = new BitLine(term2, this._gateComponents[0].getInput1(), false, false, false, true, false);
        this.add(line, 0);
        (this._bitRadioOutputs[0] = new BitRadioButton(false)).setBounds(this._gateComponents[0].getOutput().getTerminal().x + this._bitRadioOutputs[0].getWidth() * 2, this._gateComponents[0].getOutput().getTerminal().y - this._bitRadioOutputs[0].getHeight() / 2, this._bitRadioOutputs[0].getWidth(), this._bitRadioOutputs[0].getHeight());
        this._bitRadioOutputs[0].setTermLeft(true);
        this._bitRadioOutputs[0].setEnabled(false);
        this.add(this._bitRadioOutputs[0]);
        final BitTerminal term3 = new BitTerminal(true, this._gateComponents[0].getOutput().getTerminal().x + 23, this._gateComponents[0].getOutput().getTerminal().y);
        line = new BitLine(this._gateComponents[0].getOutput(), term3, false, false);
        this.add(line, 0);
        line = new BitLine(term3, this._bitRadioOutputs[0], false, true);
        this.add(line, 0);
        final BitTerminal term4 = new BitTerminal(true, this._gateComponents[1].getInput0().getTerminal().x - 20, this._gateComponents[1].getInput0().getTerminal().y);
        final BitTerminal term5 = new BitTerminal(true, term4.getTerminal().x, term4.getTerminal().y - 15);
        final BitTerminal term6 = new BitTerminal(true, term3.getTerminal().x + 6, term3.getTerminal().y + 15);
        line = new BitLine(term3, term6, true, false, true, false, false);
        this.add(line, 0);
        line = new BitLine(term6, term5, false, false, true, false, false);
        this.add(line, 0);
        line = new BitLine(term5, term4, false, false, true, false, true);
        this.add(line, 0);
        line = new BitLine(term4, this._gateComponents[1].getInput0(), false, false, false, true, false);
        this.add(line, 0);
        this._gateComponents[0].setGateType(2);
        this._gateComponents[0].removeMouseListener(this._gateComponents[0].getMouseListeners()[0]);
        this._gateComponents[1].setGateType(1);
        this._gateComponents[1].removeMouseListener(this._gateComponents[1].getMouseListeners()[0]);
        this._gateComponents[2].setGateType(4);
    }
}
