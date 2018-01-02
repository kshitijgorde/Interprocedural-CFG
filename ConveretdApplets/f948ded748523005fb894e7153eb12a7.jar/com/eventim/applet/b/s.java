// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.Point;
import javax.swing.JViewport;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Container;
import javax.swing.event.MouseInputAdapter;

final class s extends MouseInputAdapter
{
    private Container a;
    private final t b;
    private int c;
    private int d;
    
    s(final t b) {
        this.b = b;
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
        this.b.setCursor(Cursor.getPredefinedCursor(13));
        this.a = this.b.getParent();
        if (this.a instanceof JViewport) {
            final JViewport viewport;
            final Point viewPosition;
            int n = (viewPosition = (viewport = (JViewport)this.a).getViewPosition()).x - (mouseEvent.getX() - this.c);
            int n2 = viewPosition.y - (mouseEvent.getY() - this.d);
            final int n3 = this.b.getWidth() - viewport.getWidth();
            final int n4 = this.b.getHeight() - viewport.getHeight();
            if (n < 0) {
                n = 0;
            }
            if (n > n3) {
                n = n3;
            }
            if (n2 < 0) {
                n2 = 0;
            }
            if (n2 > n4) {
                n2 = n4;
            }
            viewport.setViewPosition(new Point(n, n2));
        }
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.c = mouseEvent.getX();
        this.d = mouseEvent.getY();
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        this.b.setCursor(Cursor.getPredefinedCursor(0));
    }
}
