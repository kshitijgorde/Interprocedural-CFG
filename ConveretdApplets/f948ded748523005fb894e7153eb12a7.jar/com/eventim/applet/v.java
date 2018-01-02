// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

final class v implements Runnable
{
    private final EventimApplet a;
    
    v(final EventimApplet a) {
        this.a = a;
    }
    
    public final void run() {
        this.a.a();
    }
}
