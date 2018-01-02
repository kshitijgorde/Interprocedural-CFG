// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.graphics;

class HintThread extends Thread
{
    HintPanel hp;
    boolean interrupted;
    
    public HintThread(final HintPanel hp) {
        this.interrupted = false;
        this.hp = hp;
    }
    
    public void run() {
        final long currentTimeMillis = System.currentTimeMillis();
        for (int n = 0; n == 0 && !this.interrupted; n = 1) {
            if (System.currentTimeMillis() - currentTimeMillis > this.hp.getBubbleInterval()) {
                this.hp.showBubbleHelp();
            }
        }
    }
}
