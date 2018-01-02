// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ck implements ActionListener
{
    public String a;
    final /* synthetic */ cj b;
    
    public ck(final cj b, final String a) {
        this.b = b;
        this.a = null;
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.b.b.a(this.a);
    }
}
