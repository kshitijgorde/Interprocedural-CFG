// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import com.eventim.applet.a.b;

final class f implements Runnable
{
    private final EventimApplet a;
    private final b b;
    
    f(final EventimApplet a, final b b) {
        this.a = a;
        this.b = b;
    }
    
    public final void run() {
        EventimApplet.e(this.a).a(this.b);
    }
}
