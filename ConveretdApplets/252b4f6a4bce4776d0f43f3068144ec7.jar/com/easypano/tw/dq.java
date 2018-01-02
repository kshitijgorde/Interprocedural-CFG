// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Component;

class dq implements dp
{
    final /* synthetic */ p a;
    
    dq(final p a) {
        this.a = a;
    }
    
    public void a(final double n) {
        final Component a = p.a(this.a);
        if (h.q == 0) {
            if (a == null) {
                return;
            }
            p.a(this.a);
        }
        final int n2 = a.getBounds().width - p.b(this.a).getBounds().width;
        if (n2 > 0) {
            p.a(this.a).setLocation(-(int)(n2 * n / 100.0), p.a(this.a).getBounds().y);
        }
    }
    
    public double a() {
        final int q = h.q;
        final Component a = p.a(this.a);
        if (q == 0) {
            if (a == null) {
                return 0.0;
            }
            p.a(this.a);
        }
        final int n2;
        final int n = n2 = a.getBounds().width - p.b(this.a).getBounds().width;
        if (q == 0) {
            if (n2 <= 0) {
                return 0.0;
            }
            final int width = p.b(this.a).getBounds().width;
        }
        return n2 / 2.0 / n * 100.0;
    }
}
