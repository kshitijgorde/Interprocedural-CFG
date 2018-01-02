// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

final class q extends ComponentAdapter
{
    final /* synthetic */ o a;
    
    q(final o a) {
        this.a = a;
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.a.f(false);
    }
}
