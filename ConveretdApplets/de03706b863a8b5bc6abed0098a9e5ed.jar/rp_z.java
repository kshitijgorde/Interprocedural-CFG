import java.awt.event.MouseWheelEvent;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JScrollBar;
import java.awt.event.MouseWheelListener;
import java.awt.event.ComponentListener;
import java.awt.event.AdjustmentListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_z extends JPanel implements AdjustmentListener, ComponentListener, MouseWheelListener
{
    private JScrollBar a;
    private JScrollBar b;
    private rp_bO a;
    
    public rp_z(final rp_bO a) {
        this.a = new JScrollBar(0);
        this.b = new JScrollBar(1);
        this.a = a;
        (a.a = this).setLayout(new BorderLayout());
        this.add(a, "Center");
        this.add(this.b, "East");
        this.add(this.a, "South");
        this.validate();
        this.b.addAdjustmentListener(this);
        this.a.addAdjustmentListener(this);
        this.addComponentListener(this);
        a.addMouseWheelListener(this);
    }
    
    final void a() {
        if (this.a.a() == null) {
            return;
        }
        final Dimension a = this.a.a();
        final Point a2 = this.a.a();
        final Dimension b = this.a.b();
        this.b.setValues(a2.y, b.height, 0, a.height);
        this.b.setUnitIncrement(b.height / 10);
        this.b.setBlockIncrement(b.height);
        this.a.setValues(a2.x, b.width, 0, a.width);
        this.a.setUnitIncrement(b.width / 10);
        this.a.setBlockIncrement(b.width);
    }
    
    public final void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final rp_z rp_z = this;
        final int value = this.b.getValue();
        final int value2 = this.a.getValue();
        final int n = value;
        this = rp_z;
        rp_z.a.a(new Point(value2, n));
        this.a.repaint();
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
        final Dimension size;
        if ((size = this.a.getSize()).width <= 0 || size.height <= 0) {
            return;
        }
        if (this.a.a() == null) {
            this.a.b();
        }
        this.a.a().a = new Rectangle(0, 0, size.width, size.height);
        this.a();
    }
    
    public final void componentShown(final ComponentEvent componentEvent) {
    }
    
    public final void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public final void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public final void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
        if (this.a.a()) {
            this.b.setValue(this.b.getValue() + mouseWheelEvent.getWheelRotation() * this.b.getUnitIncrement());
        }
    }
}
