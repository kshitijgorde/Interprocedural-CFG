// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.compound;

import java.awt.Container;
import javax.swing.JFrame;
import blackmagic.finance.investment.calculators.foundations.BasicCalculatorFactory;

public final class CompounderFactory extends BasicCalculatorFactory
{
    public CompounderFactory() {
        this.setTitle("The Compounder");
    }
    
    protected void addContent(final JFrame frame) {
        final CompounderPanel contentPane = new CompounderPanel();
        frame.setContentPane(contentPane);
        frame.setJMenuBar(CompounderMenuFactory.getCompounderMenuBar(frame, contentPane));
    }
}
