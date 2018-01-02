// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import java.util.Iterator;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import wordle.core.b.c;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

final class e implements MouseListener, MouseMotionListener
{
    private final n a;
    private final J b;
    private volatile String c;
    private volatile c d;
    private volatile boolean e;
    
    e(final n a, final J b) {
        this.c = null;
        this.d = null;
        this.e = false;
        this.a = a;
        this.b = b;
        a.addMouseListener(this);
        a.addMouseMotionListener(this);
    }
    
    final c a() {
        return this.d;
    }
    
    final boolean b() {
        return this.e;
    }
    
    public final void mouseMoved(MouseEvent mouseEvent) {
        final e e = this;
        mouseEvent = mouseEvent;
        this = e;
        final c d = e.d;
        final c a = this.a.a(mouseEvent.getX(), mouseEvent.getY());
        if (d != a) {
            if (d != null) {
                this.c = null;
                this.a.a(d);
            }
            if (a != null) {
                this.d = a;
                this.c = this.d.k().b;
                this.a.a(this.d);
            }
            final Iterator iterator = this.a.d().iterator();
            while (iterator.hasNext()) {
                final c d2;
                if ((d2 = iterator.next()) == this.d && (this.c == null || !d2.k().b.equalsIgnoreCase(this.c))) {
                    this.d = null;
                    this.a.a(d2);
                }
                else {
                    if (this.d == d2 || this.c == null || !d2.k().b.equalsIgnoreCase(this.c)) {
                        continue;
                    }
                    this.d = d2;
                    this.a.a(d2);
                }
            }
            this.d = a;
            this.a.setCursor(Cursor.getPredefinedCursor((this.d == null) ? 0 : 12));
        }
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        final boolean b = mouseEvent.isPopupTrigger() || mouseEvent.isControlDown() || mouseEvent.isMetaDown();
        if (this.d == null) {
            if (b) {
                this.b.a(mouseEvent.getX(), mouseEvent.getY());
            }
            return;
        }
        if (b) {
            this.b.a(this.d, mouseEvent.getX(), mouseEvent.getY());
            return;
        }
        if (mouseEvent.getButton() == 1) {
            if (this.e) {
                return;
            }
            this.e = true;
            this.a.a(this.d);
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (this.d == null) {
            if (mouseEvent.isPopupTrigger()) {
                this.b.a(mouseEvent.getX(), mouseEvent.getY());
            }
            return;
        }
        if (mouseEvent.isPopupTrigger()) {
            this.b.a(this.d, mouseEvent.getX(), mouseEvent.getY());
            return;
        }
        if (!this.e) {
            return;
        }
        this.e = false;
        this.a.a(this.d);
    }
}
