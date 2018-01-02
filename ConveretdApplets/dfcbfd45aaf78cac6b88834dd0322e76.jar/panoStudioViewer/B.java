// 
// Decompiled by Procyon v0.5.30
// 

package panoStudioViewer;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

class B implements MouseWheelListener
{
    private PanoStudioViewer A;
    
    public B(final PanoStudioViewer a) {
        (this.A = a).addMouseWheelListener(this);
    }
    
    public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
        if (this.A.\u00f6 == null || this.A.\u00c7) {
            return;
        }
        if (System.currentTimeMillis() - mouseWheelEvent.getWhen() > 3000L) {
            return;
        }
        final int wheelRotation = mouseWheelEvent.getWheelRotation();
        final double t = this.A.\u00f6.T;
        double \u00f5;
        if (wheelRotation > 0) {
            \u00f5 = t * 1.06;
        }
        else {
            \u00f5 = t / 1.06;
        }
        this.A.c = true;
        this.A.\u00fe = this.A.\u00f6.I;
        this.A.\u00dd = this.A.\u00f6.£;
        this.A.\u00f5 = \u00f5;
        this.A.g = System.currentTimeMillis();
    }
}
