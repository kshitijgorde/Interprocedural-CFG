import java.awt.Component;
import java.awt.Dimension;
import java.awt.Choice;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class g extends Panel
{
    private Choice p;
    private final EggApplet d;
    
    public final void p(final int n) {
        this.p.select(n);
    }
    
    public final void p(final String s) {
        if (EggApplet.p(this.d) != null) {
            this.p.insert(s, this.p.countItems() - 1);
        }
        else {
            this.p.addItem(s);
        }
    }
    
    public final void p() {
        this.p.remove(this.p.countItems() - 1);
    }
    
    public final void setLocation(final int n, final int n2) {
        super.setLocation(EggApplet.p(this.d)[4], 4);
    }
    
    public final Dimension getPreferredSize() {
        return EggApplet.b(this.d);
    }
    
    public g(final EggApplet d) {
        this.d = d;
        this.add(this.p = new d(this));
        this.resize(EggApplet.p(d)[5] - EggApplet.p(d)[4], EggApplet.n(d));
        this.setLocation(EggApplet.p(d)[4], 4);
        this.setBackground(EggApplet.p(d));
    }
    
    public static final EggApplet p(final g g) {
        return g.d;
    }
}
