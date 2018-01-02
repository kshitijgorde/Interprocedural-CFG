import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class switch
{
    private public ka;
    private public la;
    private return ma;
    private int width;
    private int x;
    private boolean na;
    private Object oa;
    
    public switch(final public ka, final return ma, final Object oa) {
        this.ka = ka;
        this.ma = ma;
        this.oa = oa;
        this.a();
    }
    
    public void _(final public public1, final boolean b) {
        this.la = null;
        if (b || public1.getWidth() == this.ka.getWidth()) {
            this.ka = public1;
            this.a();
        }
        else {
            this.la = public1;
        }
        this.na = false;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void _(final int x) {
        this.x = x;
    }
    
    public void b(final Component component, final Graphics graphics, int n, final boolean b) {
        if (b && this.ka.b()) {
            final Image _ = this.ka._();
            if (_ != null) {
                graphics.drawImage(_, n, 0, component);
            }
        }
        else {
            final Image a = this.ka.a();
            if (a != null) {
                graphics.drawImage(a, n, 0, component);
            }
        }
        if (this.ma != null) {
            n += this.ka.getWidth();
            this.ma.b(component, graphics, n);
        }
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void validate() {
        if (this.la != null) {
            this.ka = this.la;
            this.a();
        }
    }
    
    public void a(final int n) {
        this.x += n;
    }
    
    public boolean contains(final int n, final int n2) {
        return this.ka.contains(n, n2);
    }
    
    public boolean _() {
        return this.na;
    }
    
    public void b() {
        this.na = true;
    }
    
    public Object b() {
        return this.oa;
    }
    
    private void a() {
        if (this.ka != null) {
            this.width = this.ka.getWidth();
        }
        if (this.ma != null) {
            this.width += this.ma.getWidth();
        }
    }
    
    public String _() {
        return this.ka._();
    }
}
