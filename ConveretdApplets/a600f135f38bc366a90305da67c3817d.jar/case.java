import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class case
{
    private u y;
    private u z;
    private v A;
    private int width;
    private int x;
    private boolean B;
    private Object C;
    
    public case(final u y, final v a, final Object c) {
        this.y = y;
        this.A = a;
        this.C = c;
        this.a();
    }
    
    public void a(final u u, final boolean b) {
        this.z = null;
        if (b || u.getWidth() == this.y.getWidth()) {
            this.y = u;
            this.a();
        }
        else {
            this.z = u;
        }
        this.B = false;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void b(final int x) {
        this.x = x;
    }
    
    public void b(final Component component, final Graphics graphics, int n, final boolean b) {
        if (b && this.y.a()) {
            final Image b2 = this.y.b();
            if (b2 != null) {
                graphics.drawImage(b2, n, 0, component);
            }
        }
        else {
            final Image _ = this.y._();
            if (_ != null) {
                graphics.drawImage(_, n, 0, component);
            }
        }
        if (this.A != null) {
            n += this.y.getWidth();
            this.A.a(component, graphics, n);
        }
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void validate() {
        if (this.z != null) {
            this.y = this.z;
            this.a();
        }
    }
    
    public void _(final int n) {
        this.x += n;
    }
    
    public boolean contains(final int n, final int n2) {
        return this.y.contains(n, n2);
    }
    
    public boolean b() {
        return this.B;
    }
    
    public void b() {
        this.B = true;
    }
    
    public Object b() {
        return this.C;
    }
    
    private void a() {
        if (this.y != null) {
            this.width = this.y.getWidth();
        }
        if (this.A != null) {
            this.width += this.A.getWidth();
        }
    }
    
    public String a() {
        return this.y.a();
    }
}
