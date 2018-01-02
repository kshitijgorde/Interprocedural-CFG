// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import com.eventim.applet.b.ae;

final class c implements Runnable
{
    private final EventimApplet a;
    private final boolean b;
    
    c(final EventimApplet a, final boolean b) {
        this.a = a;
        this.b = b;
    }
    
    public final void run() {
        EventimApplet.a(this.a, (ae)this.a.b());
        if (this.b) {
            this.a.b(-1, -1);
        }
    }
}
