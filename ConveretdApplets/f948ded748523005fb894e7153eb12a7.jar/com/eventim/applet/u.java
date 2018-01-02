// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import java.awt.Cursor;

final class u implements Runnable
{
    private final EventimApplet a;
    
    u(final EventimApplet a) {
        this.a = a;
    }
    
    public final void run() {
        this.a.setCursor(Cursor.getDefaultCursor());
    }
}
