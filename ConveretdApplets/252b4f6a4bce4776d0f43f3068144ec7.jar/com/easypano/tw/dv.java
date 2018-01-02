// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class dv implements ActionListener
{
    final /* synthetic */ TWViewer a;
    
    dv(final TWViewer a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.s();
    }
}
