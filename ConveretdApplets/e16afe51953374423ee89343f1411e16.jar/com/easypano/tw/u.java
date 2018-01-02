// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

final class u extends KeyAdapter
{
    final /* synthetic */ bt a;
    
    u(final bt a) {
        this.a = a;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final boolean q = h.q;
        int n;
        final boolean b = (n = (bt.a(this.a) ? 1 : 0)) != 0;
        if (!q) {
            if (!b) {
                return;
            }
            final boolean x;
            n = ((x = bt.b(this.a).x) ? 1 : 0);
        }
        if (!q) {
            if (!b) {
                return;
            }
            n = keyEvent.getKeyCode();
        }
        Label_0432: {
            switch (n) {
                case 16:
                case 65: {
                    bt.a(this.a, bt.c(this.a).e() - bt.d(this.a));
                    if (q) {
                        break Label_0432;
                    }
                    break;
                }
                case 17:
                case 90: {
                    bt.a(this.a, bt.c(this.a).e() + bt.d(this.a));
                    if (q) {
                        break Label_0432;
                    }
                    break;
                }
                case 37: {
                    bt.b(this.a, bt.c(this.a).d() - bt.e(this.a));
                    if (q) {
                        break Label_0432;
                    }
                    break;
                }
                case 39: {
                    bt.b(this.a, bt.c(this.a).d() + bt.e(this.a));
                    if (q) {
                        break Label_0432;
                    }
                    break;
                }
                case 38: {
                    final bt a = this.a;
                    if (!q) {
                        switch (bt.f(a)) {
                            case 1: {
                                bt.c(this.a, bt.c(this.a).f() - bt.g(this.a));
                                if (q) {
                                    break;
                                }
                                break Label_0432;
                            }
                        }
                        final bt a2 = this.a;
                    }
                    bt.c(a, bt.c(this.a).f() + bt.g(this.a));
                    if (q) {
                        break Label_0432;
                    }
                    break;
                }
                case 40: {
                    final bt a3 = this.a;
                    if (!q) {
                        switch (bt.f(a3)) {
                            case 1: {
                                bt.c(this.a, bt.c(this.a).f() + bt.g(this.a));
                                if (q) {
                                    break;
                                }
                                break Label_0432;
                            }
                        }
                        final bt a4 = this.a;
                    }
                    bt.c(a3, bt.c(this.a).f() - bt.g(this.a));
                    if (q) {
                        break Label_0432;
                    }
                    break;
                }
                case 34: {
                    bt.h(this.a).nextScene();
                    if (q) {
                        break Label_0432;
                    }
                    break;
                }
                case 33: {
                    bt.h(this.a).previousScene();
                    break;
                }
            }
        }
    }
}
