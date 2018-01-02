// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class v extends MouseAdapter
{
    final /* synthetic */ h a;
    
    v(final h a) {
        this.a = a;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final boolean q = h.q;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        int n3;
        int n2;
        final int n = n2 = (n3 = x);
        if (!q) {
            if (n < 0) {
                return;
            }
            final int n4;
            n2 = (n4 = (n3 = x));
        }
        if (!q) {
            if (n > this.a.getBounds().width) {
                return;
            }
            n3 = (n2 = y);
        }
        if (!q) {
            if (n2 < 0) {
                return;
            }
            n3 = y;
        }
        if (n3 <= this.a.getBounds().height) {
            this.a.a(new ActionEvent(this.a, 1001, "", mouseEvent.getModifiers()));
        }
    }
}
