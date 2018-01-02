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
        this.a(this.a(mouseEvent.getPoint()));
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
        this.a.a(this.b.c, this.b.f, this.b.g);
        this.b.b = false;
        this.b.a = false;
        this.a.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    private g a(final Point point) {
        final boolean f = h.f;
        int n = 0;
        while (true) {
            Label_0061: {
                if (!f) {
                    break Label_0061;
                }
                final Object element = d.a(this.a).elementAt(n);
                if (!(element instanceof a)) {
                    final g g = (g)element;
                    if (g.g.contains(point)) {
                        return g;
                    }
                }
                ++n;
            }
            if (n >= d.a(this.a).size()) {
                return null;
            }
            continue;
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (d.a(this.a) == null) {
            return;
        }
        this.a(this.a(mouseEvent.getPoint()));
    }
    
    private void a(final g g) {
        final boolean f = h.f;
        Label_0062: {
            if (g == null) {
                if (this.b == null) {
                    return;
                }
                this.b.b = false;
                if (!f) {
                    break Label_0062;
                }
            }
            if (this.b == null) {
                this.b = g;
                if (!f) {
                    break Label_0062;
                }
            }
            this.b.b = false;
            g.b = true;
            this.b = g;
        }
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
