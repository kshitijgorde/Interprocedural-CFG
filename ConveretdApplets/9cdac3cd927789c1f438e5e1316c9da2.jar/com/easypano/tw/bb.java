// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class bb extends MouseAdapter
{
    final /* synthetic */ bu a;
    
    bb(final bu a) {
        this.a = a;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        bu.h(this.a).b(false);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        int popupTrigger;
        final boolean b = (popupTrigger = (mouseEvent.isPopupTrigger() ? 1 : 0)) != 0;
        Label_0025: {
            if (!g.q) {
                if (b) {
                    break Label_0025;
                }
                popupTrigger = (mouseEvent.getModifiers() & 0x8);
            }
            if (popupTrigger != 8) {
                return;
            }
        }
        dt.e.a(this.a, mouseEvent.getX(), mouseEvent.getY());
    }
}
