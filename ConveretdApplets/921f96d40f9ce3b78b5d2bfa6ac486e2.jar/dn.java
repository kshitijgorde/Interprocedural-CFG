import java.util.Vector;
import java.awt.Color;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class dn extends Panel
{
    private y p;
    private Panel d;
    
    public dn(final y p) {
        this.p = p;
        (this.d = new Panel()).setFont(dw.l);
        this.setLayout(new BorderLayout(0, 0));
        this.add("Center", p.p());
        this.add("South", this.d);
    }
    
    public final void p() {
        final Color[] array = dw.p[this.p.p()];
        this.p.p();
        final Vector p = du.p(this);
        for (int i = 0; i < p.size(); ++i) {
            final Component component = p.elementAt(i);
            if (component instanceof dl) {
                component.setFont(dw.i);
                component.setForeground(array[5]);
                component.setBackground(array[4]);
            }
            else if (component instanceof Panel) {
                component.setBackground(array[6]);
            }
        }
    }
    
    public final void d() {
        this.d.removeAll();
        this.p.p().p(this.d);
        this.p();
        this.d.invalidate();
        this.invalidate();
        this.validate();
    }
}
