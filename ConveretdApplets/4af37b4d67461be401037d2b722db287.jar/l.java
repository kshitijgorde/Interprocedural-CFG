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
        return EggApplet.p(this.d);
    }
    
    public final void setLocation(final int n, final int n2) {
        super.setLocation(EggApplet.p(this.d)[1], 0);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.p) {
            graphics.drawImage(EggApplet.g(this.d), 0, 0, null);
        }
        else {
            graphics.drawImage(EggApplet.l(this.d), 0, 0, null);
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        EggApplet.p(this.d);
        if (EggApplet.g(this.d) != null) {
            this.p = true;
            this.repaint();
        }
        this.d.v();
        return true;
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        EggApplet.a(this.d, 0);
        EggApplet.d(this.d, 0);
        this.p = false;
        this.repaint();
        return true;
    }
    
    public l(final EggApplet d) {
        this.d = d;
        this.p = false;
        this.setLocation(EggApplet.p(d)[1], 0);
        this.setSize(EggApplet.l(d).getWidth(null), EggApplet.l(d).getHeight(null));
    }
}
