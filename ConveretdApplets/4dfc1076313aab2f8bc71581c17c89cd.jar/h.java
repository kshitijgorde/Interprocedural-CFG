import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class h extends Panel
{
    private boolean p;
    private final EggApplet d;
    
    public final Dimension getPreferredSize() {
        return EggApplet.e(this.d);
    }
    
    public final void setLocation(final int n, final int n2) {
        super.setLocation(EggApplet.p(this.d)[3], 0);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.p) {
            graphics.drawImage(EggApplet.i(this.d), 0, 0, null);
        }
        else {
            graphics.drawImage(EggApplet.c(this.d), 0, 0, null);
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        EggApplet.b(this.d);
        EggApplet.p(this.d, null);
        System.gc();
        EggApplet.p(this.d, new a(this.d));
        if (EggApplet.i(this.d) != null) {
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
    
    public h(final EggApplet d) {
        this.d = d;
        this.p = false;
        this.setLocation(EggApplet.p(d)[3], 0);
        this.setSize(EggApplet.c(d).getWidth(null), EggApplet.c(d).getHeight(null));
    }
}
