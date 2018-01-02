// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.clock;

class ClockRefresher implements TimerListener
{
    private IClock clock;
    
    public ClockRefresher(final IClock clock) {
        this.clock = clock;
    }
    
    public void timeChanged(final TimerEvent timerEvent) {
        this.clock.refresh();
    }
}
