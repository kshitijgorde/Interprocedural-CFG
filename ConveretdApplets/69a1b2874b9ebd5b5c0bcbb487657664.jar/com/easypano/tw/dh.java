// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class dh implements ActionListener
{
    final /* synthetic */ dg a;
    
    dh(final dg a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final boolean q = h.q;
        final j j = (j)actionEvent.getSource();
        final j a = dg.a(this.a);
        final dg a3;
        Label_0076: {
            Label_0055: {
                if (!q) {
                    if (a != null) {
                        final j a2 = dg.a(this.a);
                        if (q) {
                            break Label_0055;
                        }
                        if (a2.equals(j)) {
                            return;
                        }
                    }
                    a3 = this.a;
                    if (q) {
                        break Label_0076;
                    }
                    dg.a(a3);
                }
            }
            if (a != null) {
                dg.a(this.a).e().a(false);
            }
            final dg a4 = this.a;
        }
        dg.a(a3, j);
    }
}
