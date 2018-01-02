import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class final
{
    private Rectangle nb;
    private String ob;
    private Image pb;
    private Image qb;
    private int width;
    
    public final(final Image image) {
        this(image, null, null);
    }
    
    public final(final Image image, final Image image2) {
        this(image, image2, null);
    }
    
    public final(final Image image, final Image image2, final boolean b) {
        this(image, image2, null);
        if (b) {
            int n = 0;
            if (image != null) {
                n = Math.max(n, image.getHeight(null));
            }
            if (image2 != null) {
                n = Math.max(n, image2.getHeight(null));
            }
            this.nb = new Rectangle(0, 0, this.width, n);
        }
    }
    
    public final(final Image pb, final Image qb, final Rectangle nb) {
        this.pb = pb;
        this.qb = qb;
        this.nb = nb;
        this.width = 0;
        if (pb != null) {
            this.width = Math.max(this.width, pb.getWidth(null));
        }
        if (qb != null) {
            this.width = Math.max(this.width, qb.getWidth(null));
        }
    }
    
    public void a(final String ob) {
        this.ob = ob;
    }
    
    public String b() {
        return this.ob;
    }
    
    public boolean _() {
        return this.qb != null;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public Image a() {
        return this.pb;
    }
    
    public Image _() {
        return this.qb;
    }
    
    public boolean contains(final int n, final int n2) {
        return this.nb != null && this.nb.contains(n, n2);
    }
    
    public Rectangle b() {
        return this.nb;
    }
}
