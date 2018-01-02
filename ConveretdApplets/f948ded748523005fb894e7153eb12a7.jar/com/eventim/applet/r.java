// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

final class r implements Runnable
{
    private final EventimApplet a;
    private final Runnable b;
    private final Integer c;
    
    r(final EventimApplet a, final Integer c, final Runnable b) {
        this.a = a;
        this.c = c;
        this.b = b;
    }
    
    public final void run() {
        this.a.a(this.c);
        EventimApplet.f(this.a);
        EventimApplet.a(this.a, this.c, this.b);
    }
}
