// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class cp implements ActionListener
{
    public String a;
    final /* synthetic */ cl b;
    
    public cp(final cl b, final String a) {
        this.b = b;
        this.a = null;
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        cl.a(this.b).a(this.a);
    }
}
