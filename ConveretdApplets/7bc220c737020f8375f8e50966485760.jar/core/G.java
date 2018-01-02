// 
// Decompiled by Procyon v0.5.30
// 

package core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;

public final class G extends MouseAdapter implements MouseMotionListener
{
    private final RevolverEngine a;
    private int b;
    private int c;
    
    public G(final RevolverEngine a) {
        (this.a = a).addMouseListener(this);
        a.addMouseMotionListener(this);
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.a.c.a(false);
        this.b = mouseEvent.getX();
        this.c = mouseEvent.getY();
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        this.a.c.a(true);
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.a.c.a(this.b, this.c, x, y);
        this.b = x;
        this.c = y;
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
    }
}
