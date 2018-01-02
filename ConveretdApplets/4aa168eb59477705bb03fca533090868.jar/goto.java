import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class goto
{
    private final gb;
    private final hb;
    private finally ib;
    private int width;
    private int x;
    private boolean jb;
    private Object kb;
    
    public goto(final final gb, final finally ib, final Object kb) {
        this.gb = gb;
        this.ib = ib;
        this.kb = kb;
        this._();
    }
    
    public void a(final final final1, final boolean b) {
        this.hb = null;
        if (b || final1.getWidth() == this.gb.getWidth()) {
            this.gb = final1;
            this._();
        }
        else {
            this.hb = final1;
        }
        this.jb = false;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void b(final int x) {
        this.x = x;
    }
    
    public void b(final Component component, final Graphics graphics, int n, final boolean b) {
        if (b && this.gb._()) {
            final Image _ = this.gb._();
            if (_ != null) {
                graphics.drawImage(_, n, 0, component);
            }
        }
        else {
            final Image a = this.gb.a();
            if (a != null) {
                graphics.drawImage(a, n, 0, component);
            }
        }
        if (this.ib != null) {
            n += this.gb.getWidth();
            this.ib._(component, graphics, n);
        }
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void validate() {
        if (this.hb != null) {
            this.gb = this.hb;
            this._();
        }
    }
    
    public void _(final int n) {
        this.x += n;
    }
    
    public boolean contains(final int n, final int n2) {
        return this.gb.contains(n, n2);
    }
    
    public boolean a() {
        return this.jb;
    }
    
    public void a() {
        this.jb = true;
    }
    
    public Object _() {
        return this.kb;
    }
    
    private void _() {
        if (this.gb != null) {
            this.width = this.gb.getWidth();
        }
        if (this.ib != null) {
            this.width += this.ib.getWidth();
        }
    }
    
    public String b() {
        return this.gb.b();
    }
}
