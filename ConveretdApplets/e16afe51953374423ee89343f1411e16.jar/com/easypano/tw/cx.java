// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class cx implements ActionListener
{
    final /* synthetic */ k a;
    
    cx(final k a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final k a = this.a;
        final boolean visible = this.a.m.isVisible();
        if (!h.q && !visible) {}
        a.d(visible);
    }
}
