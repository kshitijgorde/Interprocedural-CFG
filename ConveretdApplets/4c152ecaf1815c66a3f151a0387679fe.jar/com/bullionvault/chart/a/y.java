// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

final class y implements MouseMotionListener
{
    private final z a;
    
    y(final z a) {
        this.a = a;
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        if (mouseEvent.getPoint().x < this.a.a.o) {
            this.a.a.a(-1.0f);
        }
        else if (mouseEvent.getPoint().x > this.a.a.p) {
            this.a.a.a(-1.0f);
        }
        else {
            this.a.a.a(this.a.a.m.c + (this.a.a.m.d - this.a.a.m.c) * ((mouseEvent.getPoint().x - this.a.a.o) / this.a.a.n));
        }
        this.a.repaint();
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
    }
}
