// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

final class cn implements ItemListener
{
    final /* synthetic */ cm a;
    
    cn(final cm a) {
        this.a = a;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        cm.a(this.a).switchToScene(((k)itemEvent.getSource()).e().c());
    }
}
