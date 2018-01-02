// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameAdapter;

final class g extends InternalFrameAdapter
{
    private final b a;
    
    g(final b a) {
        this.a = a;
    }
    
    public final void internalFrameClosing(final InternalFrameEvent internalFrameEvent) {
        b.a(this.a, "ACTION_COMPOSE_CANCEL");
    }
}
