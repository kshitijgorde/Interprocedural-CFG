// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

final class bm extends MouseMotionAdapter
{
    final /* synthetic */ n a;
    
    bm(final n a) {
        this.a = a;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final boolean q = g.q;
        final int b = n.b(this.a);
        if (q || b > n.c(this.a)) {
            int n = b;
            int n2 = 1;
            final n a = this.a;
            if (!q) {
                Label_0131: {
                    switch (a.n) {
                        case 42: {
                            final int n3 = mouseEvent.getX() - com.easypano.tw.n.d(this.a).x;
                            n = this.a.getBounds().width - this.a.r.getBounds().width;
                            n2 = this.a.r.getBounds().x + n3;
                            if (q) {
                                break Label_0131;
                            }
                            break;
                        }
                        case 41: {
                            final int n4 = mouseEvent.getY() - com.easypano.tw.n.d(this.a).y;
                            n = this.a.getBounds().height - this.a.r.getBounds().height;
                            n2 = this.a.r.getBounds().y + n4;
                            break;
                        }
                    }
                }
                com.easypano.tw.n.a(this.a, com.easypano.tw.n.c(this.a) + n2 / n * (com.easypano.tw.n.b(this.a) - com.easypano.tw.n.c(this.a)));
                final n a2 = this.a;
            }
            a.a(com.easypano.tw.n.a(this.a));
        }
    }
}
