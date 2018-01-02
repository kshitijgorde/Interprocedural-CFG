// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class cw implements ActionListener
{
    final /* synthetic */ j a;
    
    cw(final j a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final j a = this.a;
        final boolean visible = this.a.m.isVisible();
        if (!g.q && !visible) {}
        a.d(visible);
    }
}
