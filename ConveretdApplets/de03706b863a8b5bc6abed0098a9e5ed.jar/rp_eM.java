import java.awt.image.DirectColorModel;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.DataBufferInt;
import java.lang.ref.SoftReference;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.image.Raster;
import java.lang.ref.WeakReference;
import java.awt.image.ColorModel;
import java.awt.PaintContext;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class rp_eM implements PaintContext
{
    private ColorModel a;
    private static ColorModel b;
    private static ColorModel c;
    private static WeakReference a;
    private Raster a;
    private rp_fP a;
    private rp_cN a;
    protected float a;
    protected float b;
    protected float c;
    protected float d;
    protected float e;
    protected float f;
    protected boolean a;
    protected int a;
    protected int[] a;
    private int[][] a;
    private float[] a;
    private float[] b;
    private int b;
    private static final int[] b;
    private static final int[] c;
    
    protected rp_eM(final rp_cS rp_cS, final Rectangle rectangle, final Rectangle2D rectangle2D, final AffineTransform affineTransform, final RenderingHints renderingHints, final float[] b, final Color[] array, final rp_fP a, final rp_cN a2) {
        if (rectangle == null) {
            throw new NullPointerException("Device bounds cannot be null");
        }
        if (rectangle2D == null) {
            throw new NullPointerException("User bounds cannot be null");
        }
        if (affineTransform == null) {
            throw new NullPointerException("Transform cannot be null");
        }
        if (renderingHints == null) {
            throw new NullPointerException("RenderingHints cannot be null");
        }
        AffineTransform inverse;
        try {
            inverse = affineTransform.createInverse();
        }
        catch (NoninvertibleTransformException ex) {
            inverse = new AffineTransform();
        }
        final double[] array2 = new double[6];
        inverse.getMatrix(array2);
        this.a = (float)array2[0];
        this.c = (float)array2[1];
        this.b = (float)array2[2];
        this.d = (float)array2[3];
        this.e = (float)array2[4];
        this.f = (float)array2[5];
        this.a = a;
        this.a = a2;
        this.b = b;
        final int[] a3 = (int[])((rp_cS.b != null) ? ((int[])rp_cS.b.get()) : null);
        final int[][] a4 = (int[][])((rp_cS.a != null) ? ((int[][])rp_cS.a.get()) : null);
        if (a3 != null || a4 != null) {
            this.a = rp_cS.a;
            this.a = rp_cS.b;
            this.a = rp_cS.a;
            this.a = a3;
            this.a = rp_cS.a;
            this.a = a4;
            return;
        }
        this.a(array);
        rp_cS.a = this.a;
        rp_cS.b = this.a;
        rp_cS.a = this.a;
        if (this.a) {
            rp_cS.a = this.a;
            rp_cS.b = new SoftReference((T)(Object)this.a);
            return;
        }
        rp_cS.a = new SoftReference((T)(Object)this.a);
    }
    
    private void a(final Color[] array) {
        Color[] array2;
        if (this.a == rp_cN.b) {
            array2 = new Color[array.length];
            for (int i = 0; i < array.length; ++i) {
                final int rgb;
                array2[i] = new Color(rp_eM.b[rgb >> 16 & 0xFF], rp_eM.b[rgb >> 8 & 0xFF], rp_eM.b[rgb & 0xFF], (rgb = array[i].getRGB()) >>> 24);
            }
        }
        else {
            array2 = array;
        }
        this.a = new float[this.b.length - 1];
        for (int j = 0; j < this.a.length; ++j) {
            this.a[j] = this.b[j + 1] - this.b[j];
        }
        this.b = -16777216;
        this.a = new int[this.a.length][];
        float n = 1.0f;
        for (int k = 0; k < this.a.length; ++k) {
            n = ((n > this.a[k]) ? this.a[k] : n);
        }
        int n2 = 0;
        for (int l = 0; l < this.a.length; ++l) {
            n2 += (int)(this.a[l] / n * 256.0f);
        }
        if (n2 > 5000) {
            this.b(array2);
        }
        else {
            this.a(array2, n);
        }
        if (this.b >>> 24 == 255) {
            this.a = rp_eM.b;
            return;
        }
        this.a = ColorModel.getRGBdefault();
    }
    
    private void a(final Color[] array, final float n) {
        this.a = true;
        int n2 = 1;
        for (int i = 0; i < this.a.length; ++i) {
            final int n3 = (int)(this.a[i] / n * 255.0f);
            n2 += n3;
            this.a[i] = new int[n3];
            final int rgb = array[i].getRGB();
            final int rgb2 = array[i + 1].getRGB();
            a(rgb, rgb2, this.a[i]);
            this.b &= rgb;
            this.b &= rgb2;
        }
        this.a = new int[n2];
        int n4 = 0;
        for (int j = 0; j < this.a.length; ++j) {
            System.arraycopy(this.a[j], 0, this.a, n4, this.a[j].length);
            n4 += this.a[j].length;
        }
        this.a[this.a.length - 1] = array[array.length - 1].getRGB();
        if (this.a == rp_cN.b) {
            for (int k = 0; k < this.a.length; ++k) {
                this.a[k] = a(this.a[k]);
            }
        }
        this.a = this.a.length - 1;
    }
    
    private void b(final Color[] array) {
        this.a = false;
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = new int[256];
            final int rgb = array[i].getRGB();
            final int rgb2 = array[i + 1].getRGB();
            a(rgb, rgb2, this.a[i]);
            this.b &= rgb;
            this.b &= rgb2;
        }
        if (this.a == rp_cN.b) {
            for (int j = 0; j < this.a.length; ++j) {
                for (int k = 0; k < this.a[j].length; ++k) {
                    this.a[j][k] = a(this.a[j][k]);
                }
            }
        }
    }
    
    private static void a(int n, int n2, final int[] array) {
        final float n3 = 1.0f / array.length;
        final int n4 = n >>> 24;
        final int n5 = n >> 16 & 0xFF;
        final int n6 = n >> 8 & 0xFF;
        n &= 0xFF;
        final int n7 = (n2 >>> 24) - n4;
        final int n8 = (n2 >> 16 & 0xFF) - n5;
        final int n9 = (n2 >> 8 & 0xFF) - n6;
        n2 = (n2 & 0xFF) - n;
        for (int i = 0; i < array.length; ++i) {
            array[i] = ((int)(n4 + i * n7 * n3 + 0.5) << 24 | (int)(n5 + i * n8 * n3 + 0.5) << 16 | (int)(n6 + i * n9 * n3 + 0.5) << 8 | (int)(n + i * n2 * n3 + 0.5));
        }
    }
    
    private static int a(int n) {
        final int n2 = n >>> 24;
        final int n3 = n >> 16 & 0xFF;
        final int n4 = n >> 8 & 0xFF;
        n &= 0xFF;
        final int n5 = rp_eM.c[n3];
        final int n6 = rp_eM.c[n4];
        n = rp_eM.c[n];
        return n2 << 24 | n5 << 16 | n6 << 8 | n;
    }
    
    protected final int a(float n) {
        if (this.a == rp_fP.a) {
            if (n > 1.0f) {
                n = 1.0f;
            }
            else if (n < 0.0f) {
                n = 0.0f;
            }
        }
        else if (this.a == rp_fP.b) {
            if ((n -= (int)n) < 0.0f) {
                ++n;
            }
        }
        else {
            if (n < 0.0f) {
                n = -n;
            }
            final int n2 = (int)n;
            n -= n2;
            if ((n2 & 0x1) == 0x1) {
                n = 1.0f - n;
            }
        }
        if (this.a) {
            return this.a[(int)(n * this.a)];
        }
        for (int i = 0; i < this.a.length; ++i) {
            if (n < this.b[i + 1]) {
                return this.a[i][(int)((n -= this.b[i]) / this.a[i] * 255.0f)];
            }
        }
        return this.a[this.a.length - 1][255];
    }
    
    public final Raster getRaster(final int n, final int n2, final int n3, final int n4) {
        Raster a;
        if ((a = this.a) == null || a.getWidth() < n3 || a.getHeight() < n4) {
            a = a(this.a, n3, n4);
            this.a = a;
        }
        final DataBufferInt dataBufferInt;
        this.a((dataBufferInt = (DataBufferInt)a.getDataBuffer()).getBankData()[0], dataBufferInt.getOffset(), ((SinglePixelPackedSampleModel)a.getSampleModel()).getScanlineStride() - n3, n, n2, n3, n4);
        return a;
    }
    
    protected abstract void a(final int[] p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    private static synchronized Raster a(final ColorModel colorModel, final int n, final int n2) {
        final Raster raster;
        if (colorModel == rp_eM.c && rp_eM.a != null && (raster = (Raster)rp_eM.a.get()) != null && raster.getWidth() >= n && raster.getHeight() >= n2) {
            rp_eM.a = null;
            return raster;
        }
        return colorModel.createCompatibleWritableRaster(n, n2);
    }
    
    private static synchronized void a(final ColorModel c, final Raster raster) {
        final Raster raster2;
        if (rp_eM.a != null && (raster2 = (Raster)rp_eM.a.get()) != null) {
            final int width = raster2.getWidth();
            final int height = raster2.getHeight();
            final int width2 = raster.getWidth();
            final int height2 = raster.getHeight();
            if (width >= width2 && height >= height2) {
                return;
            }
            if (width * height >= width2 * height2) {
                return;
            }
        }
        rp_eM.c = c;
        rp_eM.a = new WeakReference((T)raster);
    }
    
    public final void dispose() {
        if (this.a != null) {
            a(this.a, this.a);
            this.a = null;
        }
    }
    
    public final ColorModel getColorModel() {
        return this.a;
    }
    
    static {
        rp_eM.b = new DirectColorModel(24, 16711680, 65280, 255);
        b = new int[256];
        c = new int[256];
        for (int i = 0; i < 256; ++i) {
            final int[] b2 = rp_eM.b;
            final int n = i;
            final float n2;
            float n3;
            if ((n2 = i / 255.0f) <= 0.04045f) {
                n3 = n2 / 12.92f;
            }
            else {
                n3 = (float)Math.pow((n2 + 0.055) / 1.055, 2.4);
            }
            b2[n] = Math.round(n3 * 255.0f);
            final int[] c2 = rp_eM.c;
            final int n4 = i;
            final float n5;
            float n6;
            if ((n5 = i / 255.0f) <= 0.0031308) {
                n6 = n5 * 12.92f;
            }
            else {
                n6 = 1.055f * (float)Math.pow(n5, 0.4166666666666667) - 0.055f;
            }
            c2[n4] = Math.round(n6 * 255.0f);
        }
    }
}
