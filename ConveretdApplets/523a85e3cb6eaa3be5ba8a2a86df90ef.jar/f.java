import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class f extends Panel
{
    private final EggApplet p;
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
    }
    
    public final void setLocation(final int n, final int n2) {
        super.setLocation(0, EggApplet.l(this.p));
    }
    
    public final Dimension getPreferredSize() {
        return EggApplet.e(this.p);
    }
    
    public f(final EggApplet p) {
        super(new FlowLayout(0));
        this.p = p;
        this.add(EggApplet.b(p));
        this.add(EggApplet.d(p));
        this.add(EggApplet.p(p));
        this.add(EggApplet.n(p));
        this.add(EggApplet.i(p));
        this.add(EggApplet.p(p));
        this.add(EggApplet.l(p));
        if (EggApplet.b(p) != null) {
            this.add(EggApplet.v(p));
        }
        this.setLocation(0, EggApplet.l(p));
        this.setSize(EggApplet.e(p));
    }
}
