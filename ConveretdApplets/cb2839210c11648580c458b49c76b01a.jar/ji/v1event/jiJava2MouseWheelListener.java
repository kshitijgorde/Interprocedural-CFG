// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import java.lang.reflect.Method;
import ji.util.e;
import ji.util.i;
import java.awt.event.MouseWheelEvent;
import ji.util.m;
import java.awt.event.MouseWheelListener;

public class jiJava2MouseWheelListener implements MouseWheelListener, bq
{
    as a;
    m b;
    
    public jiJava2MouseWheelListener() {
        this.a = null;
        this.b = null;
    }
    
    public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
        if (this.a != null && i.c(7) && e.t()) {
            this.a.mouseWheelMoved(new g9(mouseWheelEvent.getSource(), mouseWheelEvent.getScrollAmount(), mouseWheelEvent.getScrollType(), mouseWheelEvent.getUnitsToScroll(), mouseWheelEvent.getWheelRotation(), mouseWheelEvent.getModifiers()));
        }
    }
    
    public void addMouseWheelListener(final as a) {
        if (a != null) {
            try {
                this.a = a;
                this.b = new m(a);
                final Method b = this.b.b("addMouseWheelListener");
                if (b != null) {
                    this.b.a(b, this);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void removeMouseWheelListener(final as as) {
        if (as != null) {
            try {
                if (as != null) {
                    final Method b = this.b.b("removeMouseWheelListener");
                    if (b != null) {
                        this.b.a(b, this);
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
