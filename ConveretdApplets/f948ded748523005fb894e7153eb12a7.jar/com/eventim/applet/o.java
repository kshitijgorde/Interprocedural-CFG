// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

final class o implements AdjustmentListener
{
    private final EventimApplet a;
    
    o(final EventimApplet a) {
        this.a = a;
    }
    
    public final void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        EventimApplet.d(this.a);
    }
}
