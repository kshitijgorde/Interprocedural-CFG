// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import com.hw.client.util.a;
import javax.swing.BoundedRangeModel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.Component;
import com.hw.client.util.c;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

final class dC implements MouseListener, MouseMotionListener, ChangeListener
{
    private bs a;
    private int b;
    private final bs c;
    
    public dC(final bs c, final bs a) {
        this.c = c;
        this.a = a;
    }
    
    public final void stateChanged(final ChangeEvent changeEvent) {
        com.hw.client.util.c.a(this.a);
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        if (bs.a(this.c)) {
            this.c.c().setValueIsAdjusting(true);
            final Rectangle a;
            if (!(a = this.c.a()).contains(mouseEvent.getPoint())) {
                this.b = 0;
                this.a(mouseEvent.getPoint());
            }
            else if (bs.b(this.c) == 0) {
                this.b = mouseEvent.getPoint().x - a.x - a.width / 2;
            }
            else {
                this.b = mouseEvent.getPoint().y - a.y - a.height / 2;
            }
        }
        else {
            this.c.c().setValueIsAdjusting(false);
        }
        this.c.repaint();
    }
    
    private void a(final Point point) {
        final bs c = this.c;
        final BoundedRangeModel c2 = this.c.c();
        final BoundedRangeModel c3 = this.c.c();
        this.c.c().setValue(c.a(c2, (bs.b(this.c) == 0) ? (c3.getMinimum() + (point.x - this.b - bs.c(this.c).width / 2) * (c3.getMaximum() - c3.getMinimum()) / (this.c.getWidth() - bs.c(this.c).width)) : (c3.getMinimum() + (point.y - this.b - bs.c(this.c).height / 2) * (c3.getMaximum() - c3.getMinimum()) / (this.c.getHeight() - bs.c(this.c).height))));
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        this.c.c().setValueIsAdjusting(false);
        this.c.repaint();
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
        if (com.hw.client.util.a.c()) {
            com.hw.client.util.a.a("HWBar$MyListener.mouseDragged: " + mouseEvent);
        }
        if (bs.a(this.c) && this.c.c().getValueIsAdjusting()) {
            this.a(mouseEvent.getPoint());
            return;
        }
        this.c.c().setValueIsAdjusting(false);
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        if (com.hw.client.util.a.c()) {
            com.hw.client.util.a.a("HWBar$MyListener.mouseEntered: " + mouseEvent);
        }
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        if (com.hw.client.util.a.c()) {
            com.hw.client.util.a.a("HWBar$MyListener.mouseExited: " + mouseEvent);
        }
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        if (com.hw.client.util.a.c()) {
            com.hw.client.util.a.a("HWBar$MyListener.mouseMoved: " + mouseEvent);
        }
    }
}
