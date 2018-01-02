// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.foundations;

import javax.swing.JComponent;

public interface CalculatorModelListener
{
    void setComponent(final JComponent p0);
    
    void updateComponent(final CalculatorModel p0, final int p1, final Object p2);
}
