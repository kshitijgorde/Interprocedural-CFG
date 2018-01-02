// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class G implements MouseListener
{
    final /* synthetic */ PlayApplet a;
    
    G(final PlayApplet a) {
        this.a = a;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.a.m_display.f()) {
            this.a.m_display.b(false);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
}
