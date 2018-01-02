// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.foundations;

import javax.swing.InputVerifier;
import java.util.GregorianCalendar;
import javax.swing.JComponent;
import java.awt.event.FocusEvent;
import blackmagic.swing.JFormattedDateField;
import java.awt.event.ActionEvent;

public class CalculatorModelDateActionFocusListener extends CalculatorModelActionFocusListener
{
    static /* synthetic */ Class class$blackmagic$finance$investment$calculators$foundations$CalculatorModelDateActionFocusListener;
    
    public CalculatorModelDateActionFocusListener(final int n, final CalculatorModel calculatorModel) {
        super(n, calculatorModel);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.processChange((JFormattedDateField)actionEvent.getSource());
    }
    
    public void focusGained(final FocusEvent focusEvent) {
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.processChange((JFormattedDateField)focusEvent.getSource());
    }
    
    protected void processChange(final JFormattedDateField formattedDateField) {
        final InputVerifier inputVerifier = formattedDateField.getInputVerifier();
        assert inputVerifier != null : "No InputVerifier tied to this FormattedTextField";
        if (!inputVerifier.shouldYieldFocus(formattedDateField)) {
            return;
        }
        try {
            formattedDateField.commitEdit();
        }
        catch (Exception ex) {}
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(formattedDateField.getDate());
        this.vCalculatorModel.updateVariable(this.vModelVariable, gregorianCalendar);
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
        $assertionsDisabled = !((CalculatorModelDateActionFocusListener.class$blackmagic$finance$investment$calculators$foundations$CalculatorModelDateActionFocusListener == null) ? (CalculatorModelDateActionFocusListener.class$blackmagic$finance$investment$calculators$foundations$CalculatorModelDateActionFocusListener = class$("blackmagic.finance.investment.calculators.foundations.CalculatorModelDateActionFocusListener")) : CalculatorModelDateActionFocusListener.class$blackmagic$finance$investment$calculators$foundations$CalculatorModelDateActionFocusListener).desiredAssertionStatus();
    }
}
