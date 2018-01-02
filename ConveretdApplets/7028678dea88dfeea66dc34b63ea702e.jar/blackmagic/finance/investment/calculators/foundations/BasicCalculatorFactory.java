// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.foundations;

import blackmagic.swing.JFrameFactory;
import javax.swing.JFrame;

public abstract class BasicCalculatorFactory implements CalculatorFactory
{
    protected String vTitle;
    protected int vCalculatorsProduced;
    
    public BasicCalculatorFactory() {
        this.vCalculatorsProduced = 0;
    }
    
    public void setTitle(final String vTitle) {
        this.vTitle = vTitle;
    }
    
    protected String getTitle() {
        ++this.vCalculatorsProduced;
        if (this.vCalculatorsProduced == 1) {
            return this.vTitle;
        }
        return this.vTitle + " (" + this.vCalculatorsProduced + ")";
    }
    
    public JFrame getCalculatorFrame() {
        final JFrame closeableJFrame = JFrameFactory.getCloseableJFrame(this.getTitle(), 1);
        this.addContent(closeableJFrame);
        closeableJFrame.pack();
        closeableJFrame.setResizable(false);
        return closeableJFrame;
    }
    
    protected abstract void addContent(final JFrame p0);
}
