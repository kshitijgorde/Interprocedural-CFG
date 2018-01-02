import java.awt.event.ItemEvent;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.awt.List;

// 
// Decompiled by Procyon v0.5.30
// 

public class q extends List implements ItemListener
{
    private final EggApplet p;
    
    public final void setLocation(final int n, final int n2) {
        super.setLocation(EggApplet.a(this.p) - 150, 0);
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(150, EggApplet.l(this.p) + EggApplet.n(this.p));
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        if (EggApplet.p(this.p) == null || this.getSelectedIndex() != this.getItemCount() - 1) {
            EggApplet.n(this.p, this.getSelectedIndex());
            this.p.a();
            this.p.v();
        }
        else {
            this.select(EggApplet.v(this.p));
        }
    }
    
    public final void addItem(final String s) {
        if (EggApplet.p(this.p) != null) {
            super.addItem(s, this.countItems() - 1);
        }
        else {
            super.addItem(s);
        }
    }
    
    public final void p() {
        this.remove(this.countItems() - 1);
    }
    
    public q(final EggApplet p) {
        this.p = p;
        this.setMultipleSelections(false);
        this.addItemListener(this);
        super.addItem("Loading next image...");
    }
}
