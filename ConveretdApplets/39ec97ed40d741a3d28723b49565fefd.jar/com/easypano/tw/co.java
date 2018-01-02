// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

final class co implements ItemListener
{
    final /* synthetic */ cl a;
    
    co(final cl a) {
        this.a = a;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        cl.a(this.a).switchToScene(((df)itemEvent.getSource()).b());
    }
}
