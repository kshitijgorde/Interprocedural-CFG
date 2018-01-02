import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class u
{
    private Rectangle F;
    private String G;
    private Image H;
    private Image I;
    private int width;
    
    public u(final Image image) {
        this(image, null, null);
    }
    
    public u(final Image image, final Image image2) {
        this(image, image2, null);
    }
    
    public u(final Image image, final Image image2, final boolean b) {
        this(image, image2, null);
        if (b) {
            int n = 0;
            if (image != null) {
                n = Math.max(n, image.getHeight(null));
            }
            if (image2 != null) {
                n = Math.max(n, image2.getHeight(null));
            }
            this.F = new Rectangle(0, 0, this.width, n);
        }
    }
    
    public u(final Image h, final Image i, final Rectangle f) {
        this.H = h;
        this.I = i;
        this.F = f;
        this.width = 0;
        if (h != null) {
            this.width = Math.max(this.width, h.getWidth(null));
        }
        if (i != null) {
            this.width = Math.max(this.width, i.getWidth(null));
        }
    }
    
    public void _(final String g) {
        this.G = g;
    }
    
    public String a() {
        return this.G;
    }
    
    public boolean a() {
        return this.I != null;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public Image _() {
        return this.H;
    }
    
    public Image b() {
        return this.I;
    }
    
    public boolean contains(final int n, final int n2) {
        return this.F != null && this.F.contains(n, n2);
    }
    
    public Rectangle a() {
        return this.F;
    }
}
