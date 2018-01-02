import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Choice;
import java.awt.event.MouseMotionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class g extends Panel implements MouseMotionListener
{
    private Choice p;
    private final EggApplet d;
    
    public final void p(final int n) {
        this.p.select(n);
    }
    
    public final void p(final String s) {
        this.p.addItem(s);
    }
    
    public final void setLocation(final int n, final int n2) {
        super.setLocation(EggApplet.p(this.d)[4], 4);
    }
    
    public final Dimension getPreferredSize() {
        return EggApplet.d(this.d);
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        if (EggApplet.p(this.d)) {
            EggApplet.p(this.d, false);
            EggApplet.b(this.d).repaint();
            EggApplet.v(this.d).repaint();
        }
    }
    
    public g(final EggApplet d) {
        this.d = d;
        this.add(this.p = new d(this));
        this.addMouseMotionListener(this);
        this.resize(EggApplet.p(d)[5] - EggApplet.p(d)[4], EggApplet.d(d));
        this.setLocation(EggApplet.p(d)[4], 4);
        this.setBackground(Color.white);
    }
    
    public static final EggApplet p(final g g) {
        return g.d;
    }
}
