// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.compound;

import javax.swing.JComponent;

class ResetListener implements BasicCompoundFormListener
{
    protected JComponent vComponent;
    
    public ResetListener(final JComponent component) {
        this.setComponent(component);
    }
    
    public void setComponent(final JComponent vComponent) {
        this.vComponent = vComponent;
    }
    
    public void updateComponent(final BasicCompoundForm basicCompoundForm, final int n) {
        if (n == 0) {
            this.vComponent.setEnabled(false);
        }
        if (n == 1) {
            this.vComponent.setEnabled(true);
        }
    }
}
