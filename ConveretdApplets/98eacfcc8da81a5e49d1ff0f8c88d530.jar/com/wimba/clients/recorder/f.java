// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.recorder;

import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class f extends MouseAdapter
{
    private final c a;
    
    private f(final c a, final byte b) {
        this.a = a;
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof JButton && !((JButton)mouseEvent.getSource()).isEnabled()) {
            return;
        }
        if (this.a.isVisible()) {
            this.a.setVisible(false);
            return;
        }
        this.a.setVisible(true);
        final Component component = (Component)mouseEvent.getSource();
        c.a(this.a, component.getLocationOnScreen());
        final Point locationOnScreen = component.getLocationOnScreen();
        final Rectangle a = this.a.a().a();
        locationOnScreen.translate(component.getWidth(), component.getHeight() / 2 - a.y - a.height / 2);
        this.a.setLocation(locationOnScreen);
        c.a(this.a).restart();
    }
    
    f(final c c) {
        this(c, (byte)0);
    }
}
