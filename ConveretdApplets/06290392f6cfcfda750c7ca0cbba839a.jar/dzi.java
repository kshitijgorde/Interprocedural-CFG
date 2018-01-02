import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class dzi extends Canvas
{
    public Image p;
    public Image d;
    public Image a;
    public uPixScreen n;
    public int v;
    private String i;
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.n.a(this.i);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.n.f();
        return true;
    }
    
    public dzi(final Image p5, final Image image, final uPixScreen n, final int v, final String i) {
        this.d = image;
        this.p = p5;
        this.v = v;
        this.a = image;
        this.n = n;
        this.i = i;
        this.resize(20, 20);
        this.repaint();
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(this.a, 0, 0, this);
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        this.a = this.d;
        this.n.p(event.id, this.v);
        this.repaint();
        return true;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        this.a = this.p;
        this.n.p(event.id, this.v);
        this.repaint();
        return true;
    }
}
