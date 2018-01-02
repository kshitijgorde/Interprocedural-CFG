// 
// Decompiled by Procyon v0.5.30
// 

package sGraphics;

class HintThread extends Thread
{
    HintPanel hp;
    
    public HintThread(final HintPanel hp) {
        this.hp = hp;
    }
    
    public void run() {
        final long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i == 0; i = 1) {
            if (System.currentTimeMillis() - currentTimeMillis > this.hp.getBubbleInterval()) {
                this.hp.showBubbleHelp();
            }
        }
    }
}
