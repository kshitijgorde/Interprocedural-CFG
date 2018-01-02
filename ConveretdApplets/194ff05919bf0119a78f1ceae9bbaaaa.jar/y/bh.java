// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Image;
import java.awt.image.ImageObserver;

public class bh extends u implements ImageObserver
{
    private Image a;
    private boolean a;
    
    public bh(final Image image) {
        this(image, image.getWidth(null), image.getHeight(null));
    }
    
    public bh(final Image image, final int n, final int n2) {
        this(image, n, n2, false);
    }
    
    public bh(final Image a, final int n, final int n2, final boolean a2) {
        super(true, n, n2);
        this.a = a;
        this.a = a2;
    }
    
    public final void a(final Image a) {
        this.a = a;
        this.h();
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0 || this.a) {
            this.h();
        }
        return (n & 0xA0) == 0x0 || this.a;
    }
    
    public void a(final ei ei) {
        this.b(ei);
        if (this.a != null) {
            ei.a(this.a, 0, 0, this);
        }
    }
}
