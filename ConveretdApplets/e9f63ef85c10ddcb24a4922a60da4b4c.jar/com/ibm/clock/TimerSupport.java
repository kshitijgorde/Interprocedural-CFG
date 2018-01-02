// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.clock;

import java.util.Vector;

class TimerSupport
{
    private Vector listeners;
    private Object source;
    
    public TimerSupport(final Object source) {
        this.source = source;
    }
    
    public synchronized void addTimerListener(final TimerListener timerListener) {
        if (this.listeners == null) {
            this.listeners = new Vector();
        }
        else if (this.listeners.contains(timerListener)) {
            return;
        }
        this.listeners.addElement(timerListener);
    }
    
    public synchronized void removeTimerListener(final TimerListener timerListener) {
        if (this.listeners == null) {
            return;
        }
        this.listeners.removeElement(timerListener);
    }
    
    public void fireTimeChangedEvent() {
        final TimerEvent timerEvent = new TimerEvent(this.source);
        for (int i = 0; i < this.listeners.size(); ++i) {
            ((TimerListener)this.listeners.elementAt(i)).timeChanged(timerEvent);
        }
    }
}
