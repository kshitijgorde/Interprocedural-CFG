import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.ItemListener;
import java.awt.Choice;

// 
// Decompiled by Procyon v0.5.30
// 

public class d extends Choice implements ItemListener, MouseMotionListener
{
    private final g p;
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        EggApplet.p(g.p(this.p), this.getSelectedIndex());
        g.p(this.p).v();
        g.p(this.p).p();
    }
    
    public final void setLocation(final int n, final int n2) {
        super.setLocation(0, 0);
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        if (EggApplet.p(g.p(this.p))) {
            EggApplet.p(g.p(this.p), false);
            EggApplet.b(g.p(this.p)).repaint();
            EggApplet.v(g.p(this.p)).repaint();
        }
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(EggApplet.p(g.p(this.p))[5] - EggApplet.p(g.p(this.p))[4], 20);
    }
    
    public d(final g p) {
        this.p = p;
        this.addItemListener(this);
        this.addMouseMotionListener(this);
        this.resize(EggApplet.p(g.p(p))[5] - EggApplet.p(g.p(p))[4], 20);
    }
}
