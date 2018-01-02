// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.foundations;

import java.awt.Window;
import blackmagic.swing.JUtilities;
import javax.swing.JFrame;

public class CachedCalculatorFactory
{
    private JFrame vCachedCalculator;
    private CalculatorFactory vFactoryInstance;
    private Class vFactoryClass;
    
    public CachedCalculatorFactory(final Class vFactoryClass) {
        this.vFactoryClass = vFactoryClass;
        this.initialise();
    }
    
    private void initialise() {
        try {
            this.vFactoryInstance = this.vFactoryClass.newInstance();
        }
        catch (Exception ex) {
            this.processException(ex);
        }
        this.getNewCalculator();
    }
    
    public void deliverCalculator() {
        this.vCachedCalculator.setVisible(true);
        this.getNewCalculator();
    }
    
    private void getNewCalculator() {
        JUtilities.centerWindowRandomly(this.vCachedCalculator = this.vFactoryInstance.getCalculatorFrame(), 0.666);
    }
    
    private void processException(final Exception ex) {
        ex.printStackTrace();
        System.exit(1);
    }
}
