// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class cy implements ActionListener
{
    final /* synthetic */ k a;
    
    cy(final k a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final k a = this.a;
        final boolean visible = this.a.m.isVisible();
        if (!h.q && !visible) {}
        a.d(visible);
    }
}
