import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class instanceof
{
    private Rectangle Fa;
    private String Ga;
    private Image Ha;
    private Image Ia;
    private int width;
    
    public instanceof(final Image image) {
        this(image, null, null);
    }
    
    public instanceof(final Image image, final Image image2) {
        this(image, image2, null);
    }
    
    public instanceof(final Image image, final Image image2, final boolean b) {
        this(image, image2, null);
        if (b) {
            int n = 0;
            if (image != null) {
                n = Math.max(n, image.getHeight(null));
            }
            if (image2 != null) {
                n = Math.max(n, image2.getHeight(null));
            }
            this.Fa = new Rectangle(0, 0, this.width, n);
        }
    }
    
    public instanceof(final Image ha, final Image ia, final Rectangle fa) {
        this.Ha = ha;
        this.Ia = ia;
        this.Fa = fa;
        this.width = 0;
        if (ha != null) {
            this.width = Math.max(this.width, ha.getWidth(null));
        }
        if (ia != null) {
            this.width = Math.max(this.width, ia.getWidth(null));
        }
    }
    
    public void _(final String ga) {
        this.Ga = ga;
    }
    
    public String a() {
        return this.Ga;
    }
    
    public boolean b() {
        return this.Ia != null;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public Image b() {
        return this.Ha;
    }
    
    public Image a() {
        return this.Ia;
    }
    
    public boolean contains(final int n, final int n2) {
        return this.Fa != null && this.Fa.contains(n, n2);
    }
    
    public Rectangle a() {
        return this.Fa;
    }
}
