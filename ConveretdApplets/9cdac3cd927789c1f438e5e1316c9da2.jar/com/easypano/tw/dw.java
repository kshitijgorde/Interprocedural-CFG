// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class dw implements ActionListener
{
    final /* synthetic */ TWViewer a;
    
    dw(final TWViewer a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.u();
    }
}
