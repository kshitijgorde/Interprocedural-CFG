// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.recorder;

import VT_6_1_0_11.dE;
import java.awt.event.ActionEvent;
import VT_6_1_0_11.au;
import java.awt.event.ActionListener;

final class b implements ActionListener
{
    private au a;
    private final e b;
    
    b(final e b, final au a) {
        this.b = b;
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        e.a(this.b).setVisible(false);
        this.a.a(null, actionEvent.getActionCommand());
    }
}
