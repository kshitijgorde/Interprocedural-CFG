// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.foundations;

import java.applet.Applet;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CachedActionListener implements ActionListener
{
    private CachedCalculatorFactory factory;
    static /* synthetic */ Class class$blackmagic$finance$investment$calculators$foundations$CachedActionListener;
    
    public void setFactoryType(final Class clazz) {
        this.factory = new CachedCalculatorFactory(clazz);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        assert this.factory != null : "CachedCalculatorFactory has not been initialised.";
        this.factory.deliverCalculator();
        this.requestFocusIfNotApplet((JButton)actionEvent.getSource());
    }
    
    private void requestFocusIfNotApplet(final JButton button) {
        if (!(button.getTopLevelAncestor() instanceof Applet)) {
            button.requestFocus();
        }
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
        $assertionsDisabled = !((CachedActionListener.class$blackmagic$finance$investment$calculators$foundations$CachedActionListener == null) ? (CachedActionListener.class$blackmagic$finance$investment$calculators$foundations$CachedActionListener = class$("blackmagic.finance.investment.calculators.foundations.CachedActionListener")) : CachedActionListener.class$blackmagic$finance$investment$calculators$foundations$CachedActionListener).desiredAssertionStatus();
    }
}
