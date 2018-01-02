// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.foundations;

import javax.swing.JComponent;
import blackmagic.swing.JFormattedNumField;

public class NumberFieldCalculatorModelListener implements CalculatorModelListener
{
    protected JFormattedNumField vField;
    static /* synthetic */ Class class$blackmagic$finance$investment$calculators$foundations$NumberFieldCalculatorModelListener;
    
    public NumberFieldCalculatorModelListener(final JComponent component) {
        this.setComponent(component);
    }
    
    public void setComponent(final JComponent component) {
        assert component instanceof JFormattedNumField;
        this.vField = (JFormattedNumField)component;
    }
    
    public void updateComponent(final CalculatorModel calculatorModel, final int n, final Object o) {
        this.vField.setDouble((double)o);
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
        $assertionsDisabled = !((NumberFieldCalculatorModelListener.class$blackmagic$finance$investment$calculators$foundations$NumberFieldCalculatorModelListener == null) ? (NumberFieldCalculatorModelListener.class$blackmagic$finance$investment$calculators$foundations$NumberFieldCalculatorModelListener = class$("blackmagic.finance.investment.calculators.foundations.NumberFieldCalculatorModelListener")) : NumberFieldCalculatorModelListener.class$blackmagic$finance$investment$calculators$foundations$NumberFieldCalculatorModelListener).desiredAssertionStatus();
    }
}
