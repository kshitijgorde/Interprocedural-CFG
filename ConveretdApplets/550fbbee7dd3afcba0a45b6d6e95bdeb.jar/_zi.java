import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class _zi extends Canvas
{
    public Image p;
    public Image d;
    public Image a;
    public Image n;
    public boolean v;
    public PixScreen i;
    public byte l;
    private String b;
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.i.n(this.b);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.i.l();
        return true;
    }
    
    public _zi(final Image p5, final Image image, final PixScreen i, final int n, final String b) {
        this.n = null;
        this.d = image;
        this.p = p5;
        this.l = (byte)n;
        this.a = image;
        this.i = i;
        this.b = b;
        this.resize(20, 20);
        this.repaint();
    }
    
    public final void paint(final Graphics graphics) {
        graphics.drawImage(this.a, 0, 0, this);
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.n != null && !this.v) {
            this.a = this.n;
        }
        else {
            this.a = this.d;
        }
        this.i.p(event.id, this.l);
        this.repaint();
        return true;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        this.a = this.p;
        this.i.p(event.id, this.l);
        this.repaint();
        return true;
    }
}
