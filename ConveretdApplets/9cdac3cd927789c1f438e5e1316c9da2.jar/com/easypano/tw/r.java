// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

final class r extends KeyAdapter
{
    final /* synthetic */ j a;
    
    r(final j a) {
        this.a = a;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final boolean q = g.q;
        Label_0108: {
            switch (keyEvent.getKeyCode()) {
                case 38: {
                    this.a.n.e().c(this.a.n.e().b() - 1);
                    if (q) {
                        break Label_0108;
                    }
                    break;
                }
                case 40: {
                    this.a.n.e().c(this.a.n.e().b() + 1);
                    if (q) {
                        break Label_0108;
                    }
                    break;
                }
                case 10: {
                    this.a.a(new ActionEvent(this.a, 1001, ""));
                    break;
                }
            }
        }
    }
}
