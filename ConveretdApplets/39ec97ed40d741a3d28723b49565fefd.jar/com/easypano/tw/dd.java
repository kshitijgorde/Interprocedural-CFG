// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class dd implements ActionListener
{
    final /* synthetic */ i a;
    
    dd(final i a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.a(new ActionEvent(this.a, 1001, ""));
    }
}