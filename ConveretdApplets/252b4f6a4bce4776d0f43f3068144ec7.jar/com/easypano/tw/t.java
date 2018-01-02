// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

final class t extends KeyAdapter
{
    final /* synthetic */ dc a;
    
    t(final dc a) {
        this.a = a;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final int q = h.q;
        final int keyCode = keyEvent.getKeyCode();
        Label_0106: {
            Label_0077: {
                if (q == 0) {
                    switch (keyCode) {
                        case 38: {
                            dc.a(this.a, this.a.c - 1);
                            break;
                        }
                        case 40: {
                            break Label_0077;
                        }
                        case 10: {
                            break Label_0106;
                        }
                    }
                }
                this.a.b(keyCode);
                if (q == 0) {
                    return;
                }
            }
            this.a.b(dc.a(this.a, this.a.c + 1));
            if (q == 0) {
                return;
            }
        }
        this.a.d(dc.a(this.a, this.a.c));
    }
}
