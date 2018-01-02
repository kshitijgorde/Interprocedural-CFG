// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.foundations;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import java.awt.event.FocusEvent;
import blackmagic.swing.JFormattedNumField;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;

public class CalculatorModelActionFocusListener implements ActionListener, FocusListener
{
    protected int vModelVariable;
    protected CalculatorModel vCalculatorModel;
    static /* synthetic */ Class class$blackmagic$finance$investment$calculators$foundations$CalculatorModelActionFocusListener;
    
    public CalculatorModelActionFocusListener(final int vModelVariable, final CalculatorModel vCalculatorModel) {
        this.vModelVariable = vModelVariable;
        this.vCalculatorModel = vCalculatorModel;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.processChange((JFormattedNumField)actionEvent.getSource());
    }
    
    public void focusGained(final FocusEvent focusEvent) {
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.processChange((JFormattedNumField)focusEvent.getSource());
    }
    
    protected void processChange(final JFormattedNumField formattedNumField) {
        final InputVerifier inputVerifier = formattedNumField.getInputVerifier();
        assert inputVerifier != null : "No InputVerifier tied to this FormattedTextField";
        if (!inputVerifier.shouldYieldFocus(formattedNumField)) {
            return;
        }
        this.vCalculatorModel.updateVariable(this.vModelVariable, formattedNumField.getDouble());
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        $assertionsDisabled = !((CalculatorModelActionFocusListener.class$blackmagic$finance$investment$calculators$foundations$CalculatorModelActionFocusListener == null) ? (CalculatorModelActionFocusListener.class$blackmagic$finance$investment$calculators$foundations$CalculatorModelActionFocusListener = class$("blackmagic.finance.investment.calculators.foundations.CalculatorModelActionFocusListener")) : CalculatorModelActionFocusListener.class$blackmagic$finance$investment$calculators$foundations$CalculatorModelActionFocusListener).desiredAssertionStatus();
    }
}
