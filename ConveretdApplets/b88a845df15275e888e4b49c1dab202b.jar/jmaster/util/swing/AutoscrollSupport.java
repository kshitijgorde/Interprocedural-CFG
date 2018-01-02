// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing;

import javax.swing.SwingUtilities;
import javax.swing.JViewport;
import java.awt.Point;
import java.awt.Insets;
import java.awt.Component;
import java.awt.dnd.Autoscroll;

public class AutoscrollSupport implements Autoscroll
{
    Component C;
    Insets B;
    Insets A;
    static /* synthetic */ Class class$javax$swing$JViewport;
    
    public AutoscrollSupport(final Component component, final Insets insets) {
        this(component, insets, insets);
    }
    
    public AutoscrollSupport(final Component c, final Insets b, final Insets a) {
        this.C = c;
        this.B = b;
        this.A = a;
    }
    
    public void autoscroll(final Point point) {
        final JViewport a = this.A();
        if (a == null) {
            return;
        }
        final Point viewPosition = a.getViewPosition();
        final int height = a.getExtentSize().height;
        final int width = a.getExtentSize().width;
        if (point.y - viewPosition.y < this.B.top) {
            a.setViewPosition(new Point(viewPosition.x, Math.max(viewPosition.y - this.A.top, 0)));
        }
        else if (viewPosition.y + height - point.y < this.B.bottom) {
            a.setViewPosition(new Point(viewPosition.x, Math.min(viewPosition.y + this.A.bottom, this.C.getHeight() - height)));
        }
        else if (point.x - viewPosition.x < this.B.left) {
            a.setViewPosition(new Point(Math.max(viewPosition.x - this.A.left, 0), viewPosition.y));
        }
        else if (viewPosition.x + width - point.x < this.B.right) {
            a.setViewPosition(new Point(Math.min(viewPosition.x + this.A.right, this.C.getWidth() - width), viewPosition.y));
        }
    }
    
    public Insets getAutoscrollInsets() {
        final int height = this.C.getHeight();
        final int width = this.C.getWidth();
        return new Insets(height, width, height, width);
    }
    
    JViewport A() {
        return (JViewport)SwingUtilities.getAncestorOfClass((AutoscrollSupport.class$javax$swing$JViewport == null) ? (AutoscrollSupport.class$javax$swing$JViewport = class$("javax.swing.JViewport")) : AutoscrollSupport.class$javax$swing$JViewport, this.C);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
