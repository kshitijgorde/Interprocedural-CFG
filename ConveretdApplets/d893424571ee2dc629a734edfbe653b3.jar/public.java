import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class public
{
    private Rectangle ra;
    private String sa;
    private Image ta;
    private Image ua;
    private int width;
    
    public public(final Image image) {
        this(image, null, null);
    }
    
    public public(final Image image, final Image image2) {
        this(image, image2, null);
    }
    
    public public(final Image image, final Image image2, final boolean b) {
        this(image, image2, null);
        if (b) {
            int n = 0;
            if (image != null) {
                n = Math.max(n, image.getHeight(null));
            }
            if (image2 != null) {
                n = Math.max(n, image2.getHeight(null));
            }
            this.ra = new Rectangle(0, 0, this.width, n);
        }
    }
    
    public public(final Image ta, final Image ua, final Rectangle ra) {
        this.ta = ta;
        this.ua = ua;
        this.ra = ra;
        this.width = 0;
        if (ta != null) {
            this.width = Math.max(this.width, ta.getWidth(null));
        }
        if (ua != null) {
            this.width = Math.max(this.width, ua.getWidth(null));
        }
    }
    
    public void b(final String sa) {
        this.sa = sa;
    }
    
    public String _() {
        return this.sa;
    }
    
    public boolean b() {
        return this.ua != null;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public Image a() {
        return this.ta;
    }
    
    public Image _() {
        return this.ua;
    }
    
    public boolean contains(final int n, final int n2) {
        return this.ra != null && this.ra.contains(n, n2);
    }
    
    public Rectangle b() {
        return this.ra;
    }
}
