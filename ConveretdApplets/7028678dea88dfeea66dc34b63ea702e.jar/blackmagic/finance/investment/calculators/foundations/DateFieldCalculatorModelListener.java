// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.foundations;

import java.util.GregorianCalendar;
import javax.swing.JComponent;
import blackmagic.swing.JFormattedDateField;

public class DateFieldCalculatorModelListener implements CalculatorModelListener
{
    protected JFormattedDateField vDateField;
    static /* synthetic */ Class class$blackmagic$finance$investment$calculators$foundations$DateFieldCalculatorModelListener;
    
    public DateFieldCalculatorModelListener(final JComponent component) {
        this.setComponent(component);
    }
    
    public void setComponent(final JComponent component) {
        assert component instanceof JFormattedDateField;
        this.vDateField = (JFormattedDateField)component;
    }
    
    public void updateComponent(final CalculatorModel calculatorModel, final int n, final Object o) {
        this.vDateField.setDate(((GregorianCalendar)o).getTime());
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
        $assertionsDisabled = !((DateFieldCalculatorModelListener.class$blackmagic$finance$investment$calculators$foundations$DateFieldCalculatorModelListener == null) ? (DateFieldCalculatorModelListener.class$blackmagic$finance$investment$calculators$foundations$DateFieldCalculatorModelListener = class$("blackmagic.finance.investment.calculators.foundations.DateFieldCalculatorModelListener")) : DateFieldCalculatorModelListener.class$blackmagic$finance$investment$calculators$foundations$DateFieldCalculatorModelListener).desiredAssertionStatus();
    }
}
