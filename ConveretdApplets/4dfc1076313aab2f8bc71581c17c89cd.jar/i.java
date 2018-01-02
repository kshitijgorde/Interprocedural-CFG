import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class i extends Panel
{
    private final EggApplet p;
    
    public final Dimension getPreferredSize() {
        return EggApplet.b(this.p);
    }
    
    public final void setLocation(final int n, final int n2) {
        super.setLocation(EggApplet.p(this.p)[2], 0);
    }
    
    public final void paint(final Graphics graphics) {
        if (EggApplet.p(this.p) && EggApplet.l(this.p) != null) {
            graphics.drawImage(EggApplet.l(this.p), 0, 0, null);
        }
        else {
            graphics.drawImage(EggApplet.e(this.p), 0, 0, null);
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        EggApplet.p(this.p, true);
        this.repaint();
        EggApplet.v(this.p).repaint();
        this.p.p();
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        if (EggApplet.p(this.p)) {
            EggApplet.p(this.p, false);
            this.repaint();
            EggApplet.v(this.p).repaint();
        }
        return true;
    }
    
    public i(final EggApplet p) {
        this.p = p;
        this.setLocation(EggApplet.p(p)[2], 0);
        this.setSize(EggApplet.e(p).getWidth(null), EggApplet.e(p).getHeight(null));
    }
}
