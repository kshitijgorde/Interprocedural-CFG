// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameAdapter;

final class j extends InternalFrameAdapter
{
    private final f a;
    
    j(final f a) {
        this.a = a;
    }
    
    public final void internalFrameClosing(final InternalFrameEvent internalFrameEvent) {
        f.a(this.a, "ACTION_EMAIL_CANCEL");
    }
}
