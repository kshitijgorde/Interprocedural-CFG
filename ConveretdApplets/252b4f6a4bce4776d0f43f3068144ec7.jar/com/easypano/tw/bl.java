// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

final class bl extends MouseMotionAdapter
{
    final /* synthetic */ o a;
    
    bl(final o a) {
        this.a = a;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int q = h.q;
        final int b = o.b(this.a);
        if (q != 0 || b > o.c(this.a)) {
            int n = b;
            int n2 = 1;
            final o a = this.a;
            if (q == 0) {
                Label_0131: {
                    switch (a.n) {
                        case 42: {
                            final int n3 = mouseEvent.getX() - o.d(this.a).x;
                            n = this.a.getBounds().width - this.a.r.getBounds().width;
                            n2 = this.a.r.getBounds().x + n3;
                            if (q != 0) {
                                break Label_0131;
                            }
                            break;
                        }
                        case 41: {
                            final int n4 = mouseEvent.getY() - o.d(this.a).y;
                            n = this.a.getBounds().height - this.a.r.getBounds().height;
                            n2 = this.a.r.getBounds().y + n4;
                            break;
                        }
                    }
                }
                o.a(this.a, o.c(this.a) + n2 / n * (o.b(this.a) - o.c(this.a)));
                final o a2 = this.a;
            }
            a.a(o.a(this.a));
        }
    }
}
