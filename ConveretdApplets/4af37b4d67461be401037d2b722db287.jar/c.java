import java.awt.Color;
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
        return EggApplet.a(this.p);
    }
    
    public final void setLocation(final int n, final int n2) {
        super.setLocation(EggApplet.p(this.p)[6], 0);
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(EggApplet.b(this.p), 0, 0, null);
    }
    
    public c(final EggApplet p) {
        this.p = p;
        this.setBackground(Color.white);
        this.setLocation(EggApplet.p(p)[6], 0);
        this.setSize(EggApplet.b(p).getWidth(null), EggApplet.b(p).getHeight(null));
    }
}
