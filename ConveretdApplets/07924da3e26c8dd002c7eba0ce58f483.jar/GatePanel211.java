import javax.swing.JScrollPane;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class GatePanel211 extends GatePanel
{
    public GatePanel211(final int x, final int y, final int width, final int height) {
        super(x, y, width, height, true);
    }
    
    protected void initComponents() {
        this._bitRadioInputs = new BitRadioButton[2];
        this._gateComponents = new GateComponent[1];
        this._bitRadioOutputs = new BitRadioButton[1];
        this.add(this._gateComponents[0] = new GateComponent(150, 100));
        (this._bitRadioInputs[0] = new BitRadioButton(true)).setBounds(this._gateComponents[0].getInput0().getTerminal().x - this._bitRadioInputs[0].getWidth() * 3, this._gateComponents[0].getInput0().getTerminal().y - this._bitRadioInputs[0].getHeight() / 2 * 3, this._bitRadioInputs[0].getWidth(), this._bitRadioInputs[0].getHeight());
        this.add(this._bitRadioInputs[0]);
        BitLine line = new BitLine(this._bitRadioInputs[0], this._gateComponents[0].getInput0(), true, false);
        this.add(line, 0);
        (this._bitRadioInputs[1] = new BitRadioButton(true)).setBounds(this._gateComponents[0].getInput1().getTerminal().x - this._bitRadioInputs[1].getWidth() * 3, this._gateComponents[0].getInput1().getTerminal().y + this._bitRadioInputs[1].getHeight() / 2, this._bitRadioInputs[1].getWidth(), this._bitRadioInputs[1].getHeight());
        this.add(this._bitRadioInputs[1]);
        line = new BitLine(this._bitRadioInputs[1], this._gateComponents[0].getInput1(), true, false);
        this.add(line, 0);
        (this._bitRadioOutputs[0] = new BitRadioButton(false)).setBounds(this._gateComponents[0].getOutput().getTerminal().x + this._bitRadioOutputs[0].getWidth() * 2, this._gateComponents[0].getOutput().getTerminal().y - this._bitRadioOutputs[0].getHeight() / 2, this._bitRadioOutputs[0].getWidth(), this._bitRadioOutputs[0].getHeight());
        this._bitRadioOutputs[0].setTermLeft(true);
        this._bitRadioOutputs[0].setEnabled(false);
        this.add(this._bitRadioOutputs[0]);
        line = new BitLine(this._gateComponents[0].getOutput(), this._bitRadioOutputs[0], false, true);
        this.add(line, 0);
        if (this.hasTable()) {
            this._truthTable = new TruthTable(this._bitRadioInputs, this._bitRadioOutputs);
            this._gateComponents[0].setDownStream(this._truthTable);
            final JScrollPane scrollTable = new JScrollPane(this._truthTable.getTruthTable());
            scrollTable.setBounds(this.getWidth() / 2 + 40, 10, this.getWidth() / 2 - 50, this.getHeight() - 20);
            this.add(scrollTable);
        }
    }
}
