import java.awt.Cursor;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class k extends Panel
{
    private final EggApplet p;
    
    public k(final EggApplet p) {
        this.p = p;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final Dimension getPreferredSize() {
        return EggApplet.v(this.p);
    }
    
    public final void setLocation(final int n, final int n2) {
        super.setLocation(0, 0);
    }
    
    public final void paint(final Graphics graphics) {
        EggApplet.p(this.p, EggApplet.p(this.p).p(EggApplet.d(this.p), EggApplet.p(this.p), EggApplet.d(this.p), EggApplet.b(this.p), EggApplet.d(this.p)));
        EggApplet.p(this.p).drawImage(this.createImage(EggApplet.p(this.p)), 0, 0, null);
        if (EggApplet.p(this.p)) {
            EggApplet.p(this.p).drawImage(EggApplet.d(this.p), EggApplet.c(this.p), EggApplet.d(this.p), null);
            EggApplet.p(this.p).drawString("v1.3", EggApplet.e(this.p), EggApplet.i(this.p));
        }
        graphics.drawImage(EggApplet.n(this.p), 0, 0, null);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!EggApplet.a(this.p)) {
            EggApplet.p(this.p).setCursor(Cursor.getPredefinedCursor(13));
        }
        else {
            EggApplet.p(this.p, Cursor.getPredefinedCursor(13));
        }
        this.p.v();
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        if (!EggApplet.a(this.p)) {
            EggApplet.p(this.p).setCursor(Cursor.getDefaultCursor());
        }
        else {
            EggApplet.p(this.p, Cursor.getDefaultCursor());
        }
        this.p.v();
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        EggApplet.d(this.p, ((this.size().width >> 1) - n) / 50);
        EggApplet.a(this.p, (n2 - (this.size().height >> 1)) / 20);
        EggApplet.p(this.p, 0);
        this.p.v();
        return true;
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        EggApplet.p(this.p, 12);
        this.p.v();
        return true;
    }
    
    public final boolean mouseDrag(final Event event, final int n, final int n2) {
        EggApplet.d(this.p, ((this.size().width >> 1) - n) / 50);
        EggApplet.a(this.p, (n2 - (this.size().height >> 1)) / 20);
        EggApplet.p(this.p, 0);
        this.p.v();
        return true;
    }
}
