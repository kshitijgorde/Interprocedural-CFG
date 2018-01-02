import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Choice;

// 
// Decompiled by Procyon v0.5.30
// 

public class d extends Choice implements ItemListener
{
    private final g p;
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        if (EggApplet.p(g.p(this.p)) == null || this.getSelectedIndex() != this.getItemCount() - 1) {
            EggApplet.n(g.p(this.p), this.getSelectedIndex());
            g.p(this.p).a();
            g.p(this.p).v();
        }
        else {
            this.select(EggApplet.v(g.p(this.p)));
        }
    }
    
    public final void setLocation(final int n, final int n2) {
        super.setLocation(0, 0);
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(EggApplet.p(g.p(this.p))[5] - EggApplet.p(g.p(this.p))[4], 20);
    }
    
    public d(final g p) {
        this.p = p;
        this.addItemListener(this);
        this.resize(EggApplet.p(g.p(p))[5] - EggApplet.p(g.p(p))[4], 20);
        this.add("Loading next image...");
    }
}
