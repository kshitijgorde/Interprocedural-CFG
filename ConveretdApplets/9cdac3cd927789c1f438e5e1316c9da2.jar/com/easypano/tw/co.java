// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class co implements ActionListener
{
    final /* synthetic */ cm a;
    
    co(final cm a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        cm.a(this.a).switchToScene(((j)actionEvent.getSource()).g().e().c());
    }
}
