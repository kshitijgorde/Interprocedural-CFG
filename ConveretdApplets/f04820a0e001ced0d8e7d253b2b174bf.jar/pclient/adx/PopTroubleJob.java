// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import pclient.shd.ClientUtil;

public class PopTroubleJob implements Runnable
{
    private PopTrouble parentPanel;
    private boolean keepGoing;
    
    public PopTroubleJob(final PopTrouble parentPanel) {
        this.keepGoing = true;
        this.parentPanel = parentPanel;
    }
    
    public void stopIt() {
        this.keepGoing = false;
    }
    
    public void run() {
        this.keepGoing = true;
        while (this.keepGoing) {
            this.parentPanel.checkPing();
            ClientUtil.doze(543);
        }
    }
}
