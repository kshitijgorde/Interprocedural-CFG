// 
// Decompiled by Procyon v0.5.30
// 

package core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseAdapter;

final class G extends MouseAdapter implements MouseWheelListener
{
    private final RE a;
    
    G(final RE a) {
        (this.a = a).addMouseListener(this);
    }
    
    public final void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
        if (mouseWheelEvent.getX() > 0 && mouseWheelEvent.getX() < this.a.c.a << 1 && mouseWheelEvent.getY() > 0 && mouseWheelEvent.getY() < this.a.c.b << 1) {
            this.a.c.d(-10 * mouseWheelEvent.getWheelRotation());
        }
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        this.a.requestFocus();
    }
}
