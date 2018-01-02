// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class cq implements ActionListener
{
    public String a;
    final /* synthetic */ cm b;
    
    public cq(final cm b, final String a) {
        this.b = b;
        this.a = null;
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        cm.a(this.b).a(this.a);
    }
}
