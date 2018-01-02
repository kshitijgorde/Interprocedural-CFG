import java.awt.Color;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class c extends Panel
{
    private final EggApplet p;
    
    public final Dimension getPreferredSize() {
        return EggApplet.p(this.p);
    }
    
    public final void setLocation(final int n, final int n2) {
        super.setLocation(EggApplet.p(this.p)[6], 0);
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(EggApplet.k(this.p), 0, 0, null);
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        if (EggApplet.p(this.p)) {
            EggApplet.p(this.p, false);
            EggApplet.b(this.p).repaint();
            EggApplet.v(this.p).repaint();
        }
        return true;
    }
    
    public c(final EggApplet p) {
        this.p = p;
        this.setBackground(Color.white);
        this.setLocation(EggApplet.p(p)[6], 0);
        this.setSize(EggApplet.k(p).getWidth(null), EggApplet.k(p).getHeight(null));
    }
}
