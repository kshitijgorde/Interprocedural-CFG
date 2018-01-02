// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

final class cz implements ItemListener
{
    final /* synthetic */ j a;
    
    cz(final j a) {
        this.a = a;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.a.q.e().a(this.a.n.e().f().elementAt(this.a.n.e().c()));
        this.a.a(new ActionEvent(this.a, 1001, ""));
    }
}
