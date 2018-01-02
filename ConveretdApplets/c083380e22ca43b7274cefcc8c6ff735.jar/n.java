import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.IndexColorModel;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class n
{
    private static Component Qa;
    private Image Ra;
    private Image Sa;
    private s Ta;
    private int x;
    private int y;
    private int q;
    private int r;
    private o Ua;
    
    public n(final Image ra, final Rectangle rectangle, final o ua) {
        this.Ra = ra;
        this.Ua = ua;
        this.q = this.Ra.getWidth(n.Qa);
        if (this.q > rectangle.width) {
            this.x = rectangle.x;
        }
        else {
            this.x = rectangle.x + (rectangle.width - this.q) / 2;
        }
        this.r = this.Ra.getHeight(n.Qa);
        if (this.r > rectangle.height) {
            this.y = rectangle.y;
            return;
        }
        this.y = rectangle.y + (rectangle.height - this.r) / 2;
    }
    
    public void a() {
        final byte[] array = new byte[16];
        final byte[] array2 = new byte[16];
        final byte[] array3 = new byte[16];
        final Color[] ka = this.Ua.Ka;
        for (int i = 0; i < 16; ++i) {
            array[i] = (byte)ka[i].getRed();
            array2[i] = (byte)ka[i].getGreen();
            array3[i] = (byte)ka[i].getBlue();
        }
        this.Ta = new s(this.Ra, this.q, this.r, new IndexColorModel(8, 16, array, array2, array3));
        this.Sa = n.Qa.createImage(this.Ta.a());
        this.Ra.flush();
        this.Ra = null;
    }
    
    public void _(final int n, final int n2, final int n3) {
        if (this.Ta == null) {
            this.a();
        }
        this.Sa.flush();
        this.Ta.a(this.Ua.b());
        this.Ta._(n, n2);
        this.Sa = n.Qa.createImage(this.Ta.a());
    }
    
    public void _(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (this.Ta == null) {
            this.a();
        }
        this.Sa.flush();
        this.Ta.a(this.Ua.b());
        this.Ta.b(n, n2, n3, n4, n5);
        this.Sa = n.Qa.createImage(this.Ta.a());
    }
    
    public Image b() {
        if (this.Sa == null) {
            return this.Ra;
        }
        return this.Sa;
    }
    
    public void clear() {
        if (this.Ta != null) {
            this.Ta.clear();
            this.Sa = n.Qa.createImage(this.Ta.a());
        }
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void dispose() {
    }
    
    static {
        n.Qa = new Canvas();
    }
}
