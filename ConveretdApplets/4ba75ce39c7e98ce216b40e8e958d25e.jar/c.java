import java.awt.image.ColorModel;
import java.awt.image.ImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

public class c extends ImageFilter
{
    protected float a;
    protected bp b;
    
    public c(final bp b) {
        this.b = null;
        this.b = b;
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
        super.setPixels(n, n2, n3, n4, colorModel, array, n5, n6);
        this.b.a(new f(3, n2 * this.a));
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        super.setPixels(n, n2, n3, n4, colorModel, array, n5, n6);
        this.b.a(new f(3, n2 * this.a));
    }
    
    public void setDimensions(final int n, final int n2) {
        this.a = 1.0f / n2;
        super.setDimensions(n, n2);
    }
}
