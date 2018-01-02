// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import com.bullionvault.chart.c.k;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

final class h implements MouseMotionListener
{
    private final i a;
    
    h(final i a) {
        this.a = a;
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        i.a(this.a).setState(this.a.d.c != null && k.c(this.a.d.c));
    }
}
