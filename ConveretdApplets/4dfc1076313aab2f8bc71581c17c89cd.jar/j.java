import java.awt.Color;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class j extends Panel
{
    private boolean p;
    private final EggApplet d;
    
    public final Dimension getPreferredSize() {
        return EggApplet.v(this.d);
    }
    
    public final void setLocation(final int n, final int n2) {
        super.setLocation(EggApplet.p(this.d)[5], 0);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.p) {
            graphics.drawImage(EggApplet.a(this.d), 0, 0, null);
        }
        else {
            graphics.drawImage(EggApplet.n(this.d), 0, 0, null);
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        EggApplet.i(this.d);
        EggApplet.p(this.d, null);
        System.gc();
        EggApplet.p(this.d, new a(this.d));
        if (EggApplet.a(this.d) != null) {
            this.p = true;
            this.repaint();
        }
        return true;
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        this.p = false;
        this.repaint();
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        if (EggApplet.p(this.d)) {
            EggApplet.p(this.d, false);
            this.repaint();
            EggApplet.v(this.d).repaint();
        }
        return true;
    }
    
    public j(final EggApplet d) {
        this.d = d;
        this.p = false;
        this.setBackground(Color.white);
        this.setLocation(EggApplet.p(d)[5], 0);
        this.setSize(EggApplet.n(d).getWidth(null), EggApplet.n(d).getHeight(null));
    }
}
