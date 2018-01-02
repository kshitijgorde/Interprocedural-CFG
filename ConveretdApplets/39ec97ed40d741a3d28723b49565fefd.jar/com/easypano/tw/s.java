// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

final class s extends KeyAdapter
{
    final /* synthetic */ dc a;
    
    s(final dc a) {
        this.a = a;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final boolean q = g.q;
        final int keyCode = keyEvent.getKeyCode();
        Label_0106: {
            Label_0077: {
                if (!q) {
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
                if (!q) {
                    return;
                }
            }
            this.a.b(dc.a(this.a, this.a.c + 1));
            if (!q) {
                return;
            }
        }
        this.a.d(dc.a(this.a, this.a.c));
    }
}
