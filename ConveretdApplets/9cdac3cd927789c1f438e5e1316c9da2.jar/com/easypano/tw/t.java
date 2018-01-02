// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

final class t extends KeyAdapter
{
    final /* synthetic */ bu a;
    
    t(final bu a) {
        this.a = a;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final boolean q = g.q;
        int n;
        final boolean b = (n = (bu.a(this.a) ? 1 : 0)) != 0;
        if (!q) {
            if (!b) {
                return;
            }
            final boolean v;
            n = ((v = bu.b(this.a).v) ? 1 : 0);
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
                    bu.a(this.a, bu.c(this.a).e() - bu.d(this.a));
                    if (q) {
                        break Label_0432;
                    }
                    break;
                }
                case 17:
                case 90: {
                    bu.a(this.a, bu.c(this.a).e() + bu.d(this.a));
                    if (q) {
                        break Label_0432;
                    }
                    break;
                }
                case 37: {
                    bu.b(this.a, bu.c(this.a).d() - bu.e(this.a));
                    if (q) {
                        break Label_0432;
                    }
                    break;
                }
                case 39: {
                    bu.b(this.a, bu.c(this.a).d() + bu.e(this.a));
                    if (q) {
                        break Label_0432;
                    }
                    break;
                }
                case 38: {
                    final bu a = this.a;
                    if (!q) {
                        switch (bu.f(a)) {
                            case 1: {
                                bu.c(this.a, bu.c(this.a).f() - bu.g(this.a));
                                if (q) {
                                    break;
                                }
                                break Label_0432;
                            }
                        }
                        final bu a2 = this.a;
                    }
                    bu.c(a, bu.c(this.a).f() + bu.g(this.a));
                    if (q) {
                        break Label_0432;
                    }
                    break;
                }
                case 40: {
                    final bu a3 = this.a;
                    if (!q) {
                        switch (bu.f(a3)) {
                            case 1: {
                                bu.c(this.a, bu.c(this.a).f() + bu.g(this.a));
                                if (q) {
                                    break;
                                }
                                break Label_0432;
                            }
                        }
                        final bu a4 = this.a;
                    }
                    bu.c(a3, bu.c(this.a).f() - bu.g(this.a));
                    if (q) {
                        break Label_0432;
                    }
                    break;
                }
                case 34: {
                    bu.h(this.a).n();
                    if (q) {
                        break Label_0432;
                    }
                    break;
                }
                case 33: {
                    bu.h(this.a).o();
                    break;
                }
            }
        }
    }
}
