// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class dg implements ActionListener
{
    final /* synthetic */ df a;
    
    dg(final df a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final boolean q = g.q;
        final i i = (i)actionEvent.getSource();
        final i a = df.a(this.a);
        final df a3;
        Label_0076: {
            Label_0055: {
                if (!q) {
                    if (a != null) {
                        final i a2 = df.a(this.a);
                        if (q) {
                            break Label_0055;
                        }
                        if (a2.equals(i)) {
                            return;
                        }
                    }
                    a3 = this.a;
                    if (q) {
                        break Label_0076;
                    }
                    df.a(a3);
                }
            }
            if (a != null) {
                df.a(this.a).e().a(false);
            }
            final df a4 = this.a;
        }
        df.a(a3, i);
    }
}
