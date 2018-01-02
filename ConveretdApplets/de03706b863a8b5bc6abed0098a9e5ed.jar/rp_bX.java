import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.image.ColorModel;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_bX extends rp_eM
{
    private boolean b;
    private boolean c;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private static float[] a;
    
    rp_bX(final rp_en rp_en, final ColorModel colorModel, final Rectangle rectangle, final Rectangle2D rectangle2D, final AffineTransform affineTransform, final RenderingHints renderingHints, final float h, final float i, final float g, final float j, final float k, final float[] array, final Color[] array2, final rp_fP rp_fP, final rp_cN rp_cN) {
        super(rp_en, rectangle, rectangle2D, affineTransform, renderingHints, array, array2, rp_fP, rp_cN);
        this.b = false;
        this.c = false;
        this.h = h;
        this.i = i;
        this.j = j;
        this.k = k;
        this.g = g;
        this.b = (this.j == this.h && this.k == this.i);
        this.c = (rp_fP == rp_fP.a);
        this.l = this.g * this.g;
        float n = this.j - this.h;
        final float n2 = this.k - this.i;
        final double n3;
        if ((n3 = n * n + n2 * n2) > this.l * 0.99f) {
            final float n4 = (float)Math.sqrt(this.l * 0.99f / n3);
            n *= n4;
            final float n5 = n2 * n4;
            this.j = this.h + n;
            this.k = this.i + n5;
        }
        this.p = (float)Math.sqrt(this.l - n * n);
        this.m = this.e - this.h;
        this.n = this.f - this.i;
        this.o = 2.0f * (this.a * this.a + this.c * this.c) / this.l;
    }
    
    protected final void a(int[] array, int n, int n2, int n3, int n4, int n5, int n6) {
        if (this.b && this.c && this.a) {
            final rp_bX rp_bX = this;
            final int[] array2 = array;
            final int n7 = n;
            final int n8 = n2;
            final int n9 = n3;
            final int n10 = n4;
            final int n11 = n5;
            n6 = n6;
            n5 = n11;
            n4 = n10;
            n3 = n9;
            n2 = n8;
            n = n7;
            array = array2;
            this = rp_bX;
            float n12 = rp_bX.a * n3 + this.b * n4 + this.m;
            float n13 = this.c * n3 + this.d * n4 + this.n;
            final float o = this.o;
            n2 += n5;
            final int n14 = this.a[this.a];
            for (int i = 0; i < n6; ++i) {
                float n15;
                float n16;
                int j;
                for (n15 = (n12 * n12 + n13 * n13) / this.l, n16 = 2.0f * (this.a * n12 + this.c * n13) / this.l + o / 2.0f, j = 0; j < n5 && n15 >= 1.0f; n15 += n16, n16 += o, ++j) {
                    array[n + j] = n14;
                }
                while (j < n5 && n15 < 1.0f) {
                    int n17;
                    if (n15 <= 0.0f) {
                        n17 = 0;
                    }
                    else {
                        final float n19;
                        final int n18 = (int)(n19 = n15 * 2048.0f);
                        final float n20 = rp_bX.a[n18];
                        n17 = (int)((n20 + (n19 - n18) * (rp_bX.a[n18 + 1] - n20)) * this.a);
                    }
                    array[n + j] = this.a[n17];
                    n15 += n16;
                    n16 += o;
                    ++j;
                }
                while (j < n5) {
                    array[n + j] = n14;
                    ++j;
                }
                n += n2;
                n12 += this.b;
                n13 += this.d;
            }
            return;
        }
        final rp_bX rp_bX2 = this;
        final int[] array3 = array;
        final int n21 = n;
        final int n22 = n2;
        final int n23 = n3;
        final int n24 = n4;
        final int n25 = n5;
        n6 = n6;
        n5 = n25;
        n4 = n24;
        n3 = n23;
        n2 = n22;
        n = n21;
        array = array3;
        this = rp_bX2;
        final double n26 = -rp_bX2.l + this.h * this.h + this.i * this.i;
        final float n27 = this.a * n3 + this.b * n4 + this.e;
        final float n28 = this.c * n3 + this.d * n4 + this.f;
        final float n29 = 2.0f * this.i;
        final float n30 = -2.0f * this.h;
        int n31 = n;
        final int n32 = n5 + n2;
        for (int k = 0; k < n6; ++k) {
            float n33 = this.b * k + n27;
            float n34 = this.d * k + n28;
            for (int l = 0; l < n5; ++l) {
                double n35;
                double n36;
                if (n33 == this.j) {
                    n35 = this.j;
                    n36 = this.i + ((n34 > this.k) ? this.p : ((double)(-this.p)));
                }
                else {
                    final double n37 = (n34 - this.k) / (n33 - this.j);
                    final double n38 = n34 - n37 * n33;
                    final double n39 = n37 * n37 + 1.0;
                    final double n40 = n30 + n37 * -2.0 * (this.i - n38);
                    final float n41 = (float)Math.sqrt(n40 * n40 - n39 * 4.0 * (n26 + n38 * (n38 - n29)));
                    n35 = (-n40 + ((n33 < this.j) ? (-n41) : ((double)n41))) / (n39 * 2.0);
                    n36 = n37 * n35 + n38;
                }
                final float n42 = n33 - this.j;
                final float n43 = n42 * n42;
                final float n44 = n34 - this.k;
                final float n45 = n43 + n44 * n44;
                final float n46 = (float)n35 - this.j;
                final float n47 = n46 * n46;
                final float n48 = (float)n36 - this.k;
                array[n31 + l] = this.a((float)Math.sqrt(n45 / (n47 + n48 * n48)));
                n33 += this.a;
                n34 += this.c;
            }
            n31 += n32;
        }
    }
    
    static {
        rp_bX.a = new float[2049];
        for (int i = 0; i < rp_bX.a.length; ++i) {
            rp_bX.a[i] = (float)Math.sqrt(i / 2048.0f);
        }
    }
}
