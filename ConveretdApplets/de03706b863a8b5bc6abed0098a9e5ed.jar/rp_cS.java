import java.lang.ref.SoftReference;
import java.awt.image.ColorModel;
import java.awt.geom.AffineTransform;
import java.awt.Color;
import java.awt.Paint;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class rp_cS implements Paint
{
    private int b;
    final float[] a;
    final Color[] a;
    final AffineTransform a;
    final rp_fP a;
    final rp_cN a;
    ColorModel a;
    float[] b;
    boolean a;
    SoftReference a;
    SoftReference b;
    int a;
    
    rp_cS(final float[] array, final Color[] array2, final rp_fP a, final rp_cN a2, final AffineTransform affineTransform) {
        if (array == null) {
            throw new NullPointerException("Fractions array cannot be null");
        }
        if (array2 == null) {
            throw new NullPointerException("Colors array cannot be null");
        }
        if (a == null) {
            throw new NullPointerException("Cycle method cannot be null");
        }
        if (a2 == null) {
            throw new NullPointerException("Color space cannot be null");
        }
        if (affineTransform == null) {
            throw new NullPointerException("Gradient transform cannot be null");
        }
        if (array.length != array2.length) {
            throw new IllegalArgumentException("Colors and fractions must have equal size");
        }
        if (array2.length < 2) {
            throw new IllegalArgumentException("User must specify at least 2 colors");
        }
        float n = -1.0f;
        for (int length = array.length, i = 0; i < length; ++i) {
            final float n2;
            if ((n2 = array[i]) < 0.0f || n2 > 1.0f) {
                throw new IllegalArgumentException("Fraction values must be in the range 0 to 1: " + n2);
            }
            if (n2 <= n) {
                throw new IllegalArgumentException("Keyframe fractions must be increasing: " + n2);
            }
            n = n2;
        }
        boolean b = false;
        boolean b2 = false;
        int length2 = array.length;
        int n3 = 0;
        if (array[0] != 0.0f) {
            b = true;
            ++length2;
            ++n3;
        }
        if (array[array.length - 1] != 1.0f) {
            b2 = true;
            ++length2;
        }
        System.arraycopy(array, 0, this.a = new float[length2], n3, array.length);
        System.arraycopy(array2, 0, this.a = new Color[length2], n3, array2.length);
        if (b) {
            this.a[0] = 0.0f;
            this.a[0] = array2[0];
        }
        if (b2) {
            this.a[length2 - 1] = 1.0f;
            this.a[length2 - 1] = array2[array2.length - 1];
        }
        this.a = a2;
        this.a = a;
        this.a = new AffineTransform(affineTransform);
        boolean b3 = true;
        for (int j = 0; j < array2.length; ++j) {
            b3 = (b3 && array2[j].getAlpha() == 255);
        }
        this.b = (b3 ? 1 : 3);
    }
    
    public final int getTransparency() {
        return this.b;
    }
}
