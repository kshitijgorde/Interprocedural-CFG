// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class cw implements ActionListener
{
    final /* synthetic */ k a;
    
    cw(final k a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final k a = this.a;
        final boolean visible = this.a.m.isVisible();
        if (h.q == 0 && !visible) {}
        a.d(visible);
    }
}
