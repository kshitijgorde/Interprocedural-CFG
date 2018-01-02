import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

class g
{
    int[] b;
    Dimension c;
    j d;
    Image e;
    public static boolean a;
    
    g() {
        this.b = null;
        this.c = new Dimension(0, 0);
        this.d = new j(0.0f, 0.0f);
        this.e = null;
    }
    
    g(final Dimension dimension) {
        this.b = null;
        this.c = new Dimension(0, 0);
        this.d = new j(0.0f, 0.0f);
        this.e = null;
        this.a(dimension);
        this.e = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(this.c.width, this.c.height, this.b, 0, this.c.width));
    }
    
    protected boolean a(final Dimension c) {
        try {
            this.c = c;
            this.b = new int[this.c.width * this.c.height];
            this.a(this.a());
            return true;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            return false;
        }
    }
    
    void a(final j d) {
        this.d = d;
    }
    
    j a() {
        if (this.b == null) {
            return new j(0.0f, 0.0f);
        }
        return new j(this.c.width / 2.0f - 0.5f, this.c.height / 2.0f - 0.5f);
    }
    
    void b(final j j) {
        j.a += this.d.a;
        j.b += this.d.b;
    }
    
    void c(final j j) {
        j.a -= this.d.a;
        j.b -= this.d.b;
    }
}
