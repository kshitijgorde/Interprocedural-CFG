// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.compound;

import javax.swing.JComponent;

class SyncListener implements DiffCompoundFormListener
{
    protected JComponent vComponent;
    
    public SyncListener(final JComponent component) {
        this.setComponent(component);
    }
    
    public void setComponent(final JComponent vComponent) {
        this.vComponent = vComponent;
    }
    
    public void updateComponent(final DiffCompoundForm diffCompoundForm, final int n) {
        if (n == 0) {
            this.vComponent.setEnabled(false);
        }
        if (n == 1) {
            this.vComponent.setEnabled(true);
        }
    }
}
