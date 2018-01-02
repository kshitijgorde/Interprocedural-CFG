// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

final class x implements MouseListener
{
    private final z a;
    
    x(final z a) {
        this.a = a;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.a.a.a(-1.0f);
        this.a.repaint();
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
}
