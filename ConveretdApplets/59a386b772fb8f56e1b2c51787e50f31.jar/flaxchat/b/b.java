// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.b;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;

class b extends MouseAdapter implements MouseMotionListener
{
    private final d a;
    private g b;
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        if (this.b == null) {
            return;
        }
        this.b.b = false;
        this.b.a = false;
        this.b = null;
        this.a.repaint();
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        if (this.b == null) {
            return;
        }
        this.b.b = true;
        this.b.a = true;
        this.a.repaint();
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (this.b == null) {
            return;
        }
        this.a.a(this.b.c, this.b.f);
        this.b.b = false;
        this.b.a = false;
        this.a.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int f = h.f;
        if (d.a(this.a) == null) {
            return;
        }
        g g = null;
        final Point point = mouseEvent.getPoint();
        int n = 0;
    Label_0145:
        while (true) {
            Label_0130: {
                if (f == 0) {
                    break Label_0130;
                }
                final Object element = d.a(this.a).elementAt(n);
                if (!(element instanceof a)) {
                    g = (g)element;
                    if (g.g.contains(point)) {
                        if (this.b == null) {
                            this.b = g;
                            this.a.repaint();
                            if (f == 0) {
                                break Label_0145;
                            }
                        }
                        this.b.b = false;
                        g.b = true;
                        this.b = g;
                        this.a.repaint();
                        break Label_0145;
                    }
                    g = null;
                }
                ++n;
            }
            if (n < d.a(this.a).size()) {
                continue;
            }
            break;
        }
        if (g != null) {
            return;
        }
        if (this.b == null) {
            return;
        }
        this.b.b = false;
        this.a.repaint();
    }
    
    b(final d d) {
        this.a = d;
        this.a = d;
    }
    
    static g a(final b b) {
        return b.b;
    }
}
