import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class l extends Panel
{
    private boolean p;
    private final EggApplet d;
    
    public final Dimension getPreferredSize() {
        return EggApplet.l(this.d);
    }
    
    public final void setLocation(final int n, final int n2) {
        super.setLocation(EggApplet.p(this.d)[1], 0);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.p) {
            graphics.drawImage(EggApplet.v(this.d), 0, 0, null);
        }
        else {
            graphics.drawImage(EggApplet.j(this.d), 0, 0, null);
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        EggApplet.v(this.d);
        if (EggApplet.v(this.d) != null) {
            this.p = true;
            this.repaint();
        }
        this.d.p();
        return true;
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        EggApplet.a(this.d, 0);
        EggApplet.d(this.d, 0);
        this.p = false;
        this.repaint();
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        if (EggApplet.p(this.d)) {
            EggApplet.p(this.d, false);
            EggApplet.b(this.d).repaint();
            EggApplet.v(this.d).repaint();
        }
        return true;
    }
    
    public l(final EggApplet d) {
        this.d = d;
        this.p = false;
        this.setLocation(EggApplet.p(d)[1], 0);
        this.setSize(EggApplet.j(d).getWidth(null), EggApplet.j(d).getHeight(null));
    }
}
