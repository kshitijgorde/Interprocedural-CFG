// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;

public class Background extends Bindable
{
    public final float[] defaultColor;
    public final FloatArrayField color;
    public final NodeField texture;
    public final BooleanField stretchToFit;
    protected Texture a;
    protected static final short b = 1;
    protected static final short c = 2;
    protected static final short d = 4;
    protected short e;
    protected boolean f;
    protected int g;
    protected int h;
    protected Component i;
    protected Graphics j;
    protected int[] k;
    protected int[] l;
    protected int m;
    protected int n;
    protected int o;
    protected int p;
    protected int q;
    
    public Background() {
        this(null);
    }
    
    public Background(final Shout3DViewer shout3DViewer) {
        super(shout3DViewer);
        this.defaultColor = new float[3];
        this.color = new FloatArrayField(this, "color", 2, this.defaultColor);
        this.texture = new NodeField(this, "texture", 24, null);
        this.stretchToFit = new BooleanField(this, "stretchToFit", 0, false);
        this.a = null;
        this.e = -1;
        this.f = false;
        this.g = -1;
        this.h = -1;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = -1;
        this.n = -1;
        this.o = -1;
        this.p = -1;
        this.q = -1;
        super.d.addFieldObserver(this, new Integer(1));
        this.texture.addFieldObserver(this, new Integer(2));
    }
    
    public void onFieldChange(final Field field, final Object o) {
        super.onFieldChange(field, o);
        if (o == null) {
            return;
        }
        final short n = (short)(int)o;
        this.e |= n;
        if (n == 2 && this.texture.a != this.a) {
            if (this.a != null) {
                this.a.d.removeFieldObserver(this);
            }
            if (this.texture.a != null && this.texture.a instanceof Texture) {
                this.a = (Texture)this.texture.a;
                this.a.d.addFieldObserver(this, new Integer(4));
                return;
            }
            this.a = null;
        }
    }
    
    private int a(final short[][] array, final int n, final int n2, final int n3, final int n4, final float n5, final float n6) {
        final float n7 = array[n][n2] + n5 * (array[n3][n2] - array[n][n2]);
        int n8 = (int)(n7 + n6 * (array[n][n4] + n5 * (array[n3][n4] - array[n][n4]) - n7) + 0.4999f);
        if (n8 < 0) {
            n8 = 0;
        }
        else if (n8 > 255) {
            n8 = 255;
        }
        return n8;
    }
    
    protected boolean c(final g g) {
        return (this.e & 0x1) != 0x0 || (this.h != g.bl || this.g != g.bk || this.i != g.y || this.j != g.L || this.k != g.F || this.l != g.O || this.m != g.bo || this.n != g.bp || this.o != g.bn || this.p != g.bm || this.q != g.bq);
    }
    
    public void b(final g g) {
        if (!super.isBound.g) {
            return;
        }
        if (this.e != 0) {
            this.f = true;
        }
        if (((this.e & 0x2) != 0x0 || (this.e & 0x4) != 0x0) && this.texture.a != null && this.texture.a instanceof Texture) {
            g.bc = null;
            this.texture.a.b(g);
        }
        if (!(this.f = this.c(g))) {
            return;
        }
        this.g = g.bk;
        this.h = g.bl;
        this.i = g.y;
        this.j = g.L;
        this.k = g.F;
        this.l = g.O;
        this.m = g.bo;
        this.n = g.bp;
        this.o = g.bn;
        this.p = g.bm;
        this.q = g.bq;
        if (this.texture.a != null && this.texture.a instanceof ImageTexture) {
            if (((ImageTexture)this.texture.a).g.r) {
                return;
            }
            final int n = (int)(this.color.a[0] * 255.0f);
            final int n2 = (int)(this.color.a[1] * 255.0f);
            final int n3 = (int)(this.color.a[2] * 255.0f);
            final int g2 = -16777216 + (n << 16) + (n2 << 8) + n3;
            g.G = g2;
            for (int i = 0; i < g.bq; ++i) {
                this.k[i] = g2;
            }
            final ImageTexture imageTexture = (ImageTexture)this.texture.a;
            final short k = imageTexture.g.k;
            final short l = imageTexture.g.l;
            if (k == this.g && l == this.h) {
                if (imageTexture.g.j == 2 || imageTexture.g.j == 4) {
                    final short[][] q = imageTexture.g.q();
                    final short[][] j = imageTexture.g.i();
                    final short[][] n4 = imageTexture.g.n();
                    final short[][] m = imageTexture.g.j();
                    int n5 = 0;
                    for (int n6 = this.n; n6 < this.n + this.o; ++n6) {
                        for (int m2 = this.m; m2 < this.m + this.p; ++m2) {
                            final float n7 = (255 - q[m2][n6]) / 255.0f;
                            this.k[n5++] = -16777216 + ((int)(n * n7 + j[m2][n6]) << 16) + ((int)(n2 * n7 + n4[m2][n6]) << 8) + ((int)(n3 * n7) + m[m2][n6]);
                        }
                    }
                }
                else {
                    final int[][] e = imageTexture.g.e;
                    int n8 = 0;
                    for (int n9 = this.n; n9 < this.n + this.o; ++n9) {
                        for (int m3 = this.m; m3 < this.m + this.p; ++m3) {
                            this.k[n8++] = e[m3][n9];
                        }
                    }
                }
            }
            else if (this.stretchToFit.g) {
                final short[][] q2 = imageTexture.g.q();
                final short[][] i2 = imageTexture.g.i();
                final short[][] n10 = imageTexture.g.n();
                final short[][] j2 = imageTexture.g.j();
                final int[][] array = new int[this.g][this.h];
                final int[] array2 = new int[this.g * this.h];
                final float n11 = k / this.g;
                final float n12 = l / this.h;
                int n13 = 0;
                for (int n14 = 0; n14 < this.h; ++n14) {
                    final float n15 = n14 * n12;
                    int n16 = (int)n15;
                    int n17 = n16 + 1;
                    final float n18 = n16 - n15;
                    if (n16 < 0) {
                        n16 = 0;
                    }
                    else if (n16 > l - 1) {
                        n16 = l - 1;
                    }
                    if (n17 < 0) {
                        n17 = 0;
                    }
                    else if (n17 > l - 1) {
                        n17 = l - 1;
                    }
                    for (int n19 = 0; n19 < this.g; ++n19) {
                        final float n20 = n19 * n11;
                        int n21 = (int)n20;
                        int n22 = n21 + 1;
                        final float n23 = n20 - n21;
                        if (n21 < 0) {
                            n21 = 0;
                        }
                        else if (n21 > k - 1) {
                            n21 = k - 1;
                        }
                        if (n22 < 0) {
                            n22 = 0;
                        }
                        else if (n22 > k - 1) {
                            n22 = k - 1;
                        }
                        int a = 0;
                        if (q2 != null) {
                            a = this.a(q2, n21, n16, n22, n17, n23, n18);
                        }
                        final int a2 = this.a(i2, n21, n16, n22, n17, n23, n18);
                        final int a3 = this.a(n10, n21, n16, n22, n17, n23, n18);
                        final int a4 = this.a(j2, n21, n16, n22, n17, n23, n18);
                        final float n24 = (255 - a) / 255.0f;
                        array[n19][n14] = -16777216 + ((int)(n * n24 + a2) << 16) + ((int)(n2 * n24 + a3) << 8) + ((int)(n3 * n24) + a4);
                        array2[n13++] = array[n19][n14];
                    }
                }
                int n25 = 0;
                for (int n26 = this.n; n26 < this.n + this.o; ++n26) {
                    for (int m4 = this.m; m4 < this.m + this.p; ++m4) {
                        this.k[n25++] = array[m4][n26];
                    }
                }
            }
            else {
                final int n27 = (this.g - k) / 2;
                final int n28 = (this.h - l) / 2;
                final int[][] array3 = new int[this.g][this.h];
                final int[] array4 = new int[this.g * this.h];
                int n29 = 0;
                int n30 = 0;
                final int[][] e2 = imageTexture.g.e;
                final short[][] q3 = imageTexture.g.q();
                final short[][] i3 = imageTexture.g.i();
                final short[][] n31 = imageTexture.g.n();
                final short[][] j3 = imageTexture.g.j();
                for (int n32 = 0; n32 < this.h; ++n32) {
                    final int n33 = n32 - n28;
                    for (int n34 = 0; n34 < this.g; ++n34) {
                        final int n35 = n34 - n27;
                        if (n35 < 0 || n35 > k - 1 || n33 < 0 || n33 > l - 1) {
                            array3[n34][n32] = g2;
                        }
                        else if (q3 != null && q3[n35][n33] != 255) {
                            final float n36 = (255 - q3[n35][n33]) / 255.0f;
                            array3[n34][n32] = -16777216 + ((int)(n * n36 + i3[n35][n33]) << 16) + ((int)(n2 * n36 + n31[n35][n33]) << 8) + ((int)(n3 * n36) + j3[n35][n33]);
                        }
                        else {
                            array3[n34][n32] = e2[n35][n33];
                        }
                        array4[n30++] = array3[n34][n32];
                    }
                }
                for (int n37 = this.n; n37 < this.n + this.o; ++n37) {
                    for (int m5 = this.m; m5 < this.m + this.p; ++m5) {
                        this.k[n29++] = array3[m5][n37];
                    }
                }
            }
            g.C = true;
        }
        else {
            final int n38 = (int)(this.color.a[0] * 255.0f);
            final int n39 = (int)(this.color.a[1] * 255.0f);
            final int n40 = (int)(this.color.a[2] * 255.0f);
            final int g3 = -16777216 + (n38 << 16) + (n39 << 8) + n40;
            g.G = g3;
            for (int n41 = 0; n41 < this.k.length; ++n41) {
                this.k[n41] = g3;
            }
            this.j.setColor(new Color(n38, n39, n40));
            this.j.fillRect(0, 0, this.g, this.h);
            g.C = false;
        }
        System.arraycopy(this.k, 0, this.l, 0, g.bq);
        this.f = false;
        this.e = 0;
    }
    
    protected void finalize() throws Throwable {
        super.d.removeFieldObserver(this);
        this.texture.removeFieldObserver(this);
        if (this.a != null) {
            this.a.d.removeFieldObserver(this);
        }
        super.finalize();
    }
}
