import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class p extends Panel
{
    private boolean p;
    private final EggApplet d;
    
    public final Dimension getPreferredSize() {
        return EggApplet.i(this.d);
    }
    
    public final void setLocation(final int n, final int n2) {
        super.setLocation(EggApplet.p(this.d)[0], 0);
    }
    
    public final void paint(final Graphics graphics) {
        this.setLocation(EggApplet.p(this.d)[0], 0);
        if (this.p) {
            graphics.drawImage(EggApplet.i(this.d), 0, 0, this);
        }
        else {
            graphics.drawImage(EggApplet.e(this.d), 0, 0, this);
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        EggApplet.f(this.d);
        if (EggApplet.i(this.d) != null) {
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
    
    public p(final EggApplet d) {
        this.d = d;
        this.p = false;
        this.setLocation(EggApplet.p(d)[0], 0);
        this.setSize(EggApplet.e(d).getWidth(null), EggApplet.e(d).getHeight(null));
    }
}
