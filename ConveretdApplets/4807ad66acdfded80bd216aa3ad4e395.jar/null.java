import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class null
{
    private instanceof ya;
    private instanceof za;
    private interface Aa;
    private int width;
    private int x;
    private boolean Ba;
    private Object Ca;
    
    public null(final instanceof ya, final interface aa, final Object ca) {
        this.ya = ya;
        this.Aa = aa;
        this.Ca = ca;
        this.b();
    }
    
    public void _(final instanceof instanceof1, final boolean b) {
        this.za = null;
        if (b || instanceof1.getWidth() == this.ya.getWidth()) {
            this.ya = instanceof1;
            this.b();
        }
        else {
            this.za = instanceof1;
        }
        this.Ba = false;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void b(final int x) {
        this.x = x;
    }
    
    public void a(final Component component, final Graphics graphics, int n, final boolean b) {
        if (b && this.ya.b()) {
            final Image a = this.ya.a();
            if (a != null) {
                graphics.drawImage(a, n, 0, component);
            }
        }
        else {
            final Image b2 = this.ya.b();
            if (b2 != null) {
                graphics.drawImage(b2, n, 0, component);
            }
        }
        if (this.Aa != null) {
            n += this.ya.getWidth();
            this.Aa.b(component, graphics, n);
        }
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void validate() {
        if (this.za != null) {
            this.ya = this.za;
            this.b();
        }
    }
    
    public void _(final int n) {
        this.x += n;
    }
    
    public boolean contains(final int n, final int n2) {
        return this.ya.contains(n, n2);
    }
    
    public boolean _() {
        return this.Ba;
    }
    
    public void _() {
        this.Ba = true;
    }
    
    public Object a() {
        return this.Ca;
    }
    
    private void b() {
        if (this.ya != null) {
            this.width = this.ya.getWidth();
        }
        if (this.Aa != null) {
            this.width += this.Aa.getWidth();
        }
    }
    
    public String a() {
        return this.ya.a();
    }
}
