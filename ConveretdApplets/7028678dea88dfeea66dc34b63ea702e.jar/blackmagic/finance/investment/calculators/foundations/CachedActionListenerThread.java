// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.foundations;

public class CachedActionListenerThread extends Thread
{
    private CachedActionListener listener;
    
    public void run() {
        this.listener = new CachedActionListener();
    }
    
    public CachedActionListener getListener() {
        return this.listener;
    }
}
