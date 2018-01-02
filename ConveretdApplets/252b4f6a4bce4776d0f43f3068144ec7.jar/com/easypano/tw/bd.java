// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class bd extends MouseAdapter
{
    final /* synthetic */ o a;
    
    bd(final o a) {
        this.a = a;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int q = h.q;
        this.a.r.requestFocus();
        final int n = this.a.n;
        Label_0130: {
            if (q == 0) {
                switch (n) {
                    case 42: {
                        mouseEvent.getX();
                        break;
                    }
                    case 41: {
                        break Label_0130;
                    }
                }
            }
            if (n > this.a.r.getBounds().x) {
                this.a.a(o.a(this.a) + this.a.s.a());
                if (q == 0) {
                    return;
                }
            }
            this.a.a(o.a(this.a) - this.a.s.a());
            if (q == 0) {
                return;
            }
        }
        if (mouseEvent.getY() > this.a.r.getBounds().y) {
            this.a.a(o.a(this.a) + this.a.s.a());
            if (q == 0) {
                return;
            }
        }
        this.a.a(o.a(this.a) - this.a.s.a());
    }
}
