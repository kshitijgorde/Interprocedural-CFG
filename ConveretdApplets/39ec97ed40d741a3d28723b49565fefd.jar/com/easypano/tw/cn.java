// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class cn implements ActionListener
{
    final /* synthetic */ cl a;
    
    cn(final cl a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        cl.a(this.a).switchToScene(((j)actionEvent.getSource()).g().e().c());
    }
}
