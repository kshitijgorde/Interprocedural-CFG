// 
// Decompiled by Procyon v0.5.30
// 

package processing.core;

public class PPolygon implements PConstants
{
    static final int DEFAULT_SIZE = 64;
    static final boolean FRY = false;
    static final int SUBXRES = 8;
    static final int SUBXRES1 = 7;
    static final int SUBYRES = 8;
    static final int SUBYRES1 = 7;
    static final int MAX_COVERAGE = 64;
    float[][] vertices;
    int vertexCount;
    float[] r;
    float[] dr;
    float[] l;
    float[] dl;
    float[] sp;
    float[] sdp;
    boolean interpX;
    boolean interpZ;
    boolean interpUV;
    boolean interpARGB;
    int rgba;
    int r2;
    int g2;
    int b2;
    int a2;
    int a2orig;
    boolean noDepthTest;
    PGraphics parent;
    int[] pixels;
    int width;
    int height;
    int width1;
    int height1;
    PImage timage;
    int[] tpixels;
    int theight;
    int twidth;
    int theight1;
    int twidth1;
    int tformat;
    boolean texture_smooth;
    boolean smoothing;
    int firstModY;
    int lastModY;
    int lastY;
    int[] aaleft;
    int[] aaright;
    int aaleftmin;
    int aarightmin;
    int aaleftmax;
    int aarightmax;
    int aaleftfull;
    int aarightfull;
    
    private final int MODYRES(final int n) {
        return n & 0x7;
    }
    
    public void reset(final int vertexCount) {
        this.vertexCount = vertexCount;
        this.interpX = true;
        this.interpZ = true;
        this.interpUV = false;
        this.interpARGB = true;
        this.timage = null;
    }
    
    public float[] nextVertex() {
        if (this.vertexCount == this.vertices.length) {
            final float[][] vertices = new float[this.vertexCount << 1][36];
            System.arraycopy(this.vertices, 0, vertices, 0, this.vertexCount);
            this.vertices = vertices;
            this.r = new float[this.vertices.length];
            this.dr = new float[this.vertices.length];
            this.l = new float[this.vertices.length];
            this.dl = new float[this.vertices.length];
            this.sp = new float[this.vertices.length];
            this.sdp = new float[this.vertices.length];
        }
        return this.vertices[this.vertexCount++];
    }
    
    public void texture(final PImage timage) {
        this.timage = timage;
        this.tpixels = timage.pixels;
        this.twidth = timage.width;
        this.theight = timage.height;
        this.tformat = timage.format;
        this.twidth1 = this.twidth - 1;
        this.theight1 = this.theight - 1;
        this.interpUV = true;
    }
    
    public void render() {
        if (this.vertexCount < 3) {
            return;
        }
        this.pixels = this.parent.pixels;
        this.noDepthTest = this.parent.hints[5];
        this.smoothing = this.parent.smooth;
        this.texture_smooth = (this.parent.hints[3] ^ true);
        this.width = (this.smoothing ? (this.parent.width * 8) : this.parent.width);
        this.height = (this.smoothing ? (this.parent.height * 8) : this.parent.height);
        this.width1 = this.width - 1;
        this.height1 = this.height - 1;
        if (!this.interpARGB) {
            this.r2 = (int)(this.vertices[0][3] * 255.0f);
            this.g2 = (int)(this.vertices[0][4] * 255.0f);
            this.b2 = (int)(this.vertices[0][5] * 255.0f);
            this.a2 = (int)(this.vertices[0][6] * 255.0f);
            this.a2orig = this.a2;
            this.rgba = (0xFF000000 | this.r2 << 16 | this.g2 << 8 | this.b2);
        }
        for (int i = 0; i < this.vertexCount; ++i) {
            this.r[i] = 0.0f;
            this.dr[i] = 0.0f;
            this.l[i] = 0.0f;
            this.dl[i] = 0.0f;
        }
        if (this.parent.hints[6]) {
            final float n = -this.width * 2;
            final float n2 = -this.height * 2;
            final float n3 = this.width * 2;
            final float n4 = this.height * 2;
            for (int j = 0; j < this.vertexCount; ++j) {
                if (this.vertices[j][0] < n || this.vertices[j][0] > n3 || this.vertices[j][1] < n2 || this.vertices[j][1] > n4) {
                    return;
                }
            }
        }
        if (this.smoothing) {
            for (int k = 0; k < this.vertexCount; ++k) {
                final float[] array = this.vertices[k];
                final int n5 = 0;
                array[n5] *= 8.0f;
                final float[] array2 = this.vertices[k];
                final int n6 = 1;
                array2[n6] *= 8.0f;
            }
            this.firstModY = -1;
        }
        int n7 = 0;
        float n8 = this.vertices[0][1];
        float n9 = this.vertices[0][1];
        for (int l = 1; l < this.vertexCount; ++l) {
            if (this.vertices[l][1] < n8) {
                n8 = this.vertices[l][1];
                n7 = l;
            }
            if (this.vertices[l][1] > n9) {
                n9 = this.vertices[l][1];
            }
        }
        this.lastY = (int)(n9 - 0.5f);
        int n10 = n7;
        int n11 = n7;
        int n12 = (int)(n8 + 0.5f);
        int n13 = n12 - 1;
        int n14 = n12 - 1;
        this.interpX = true;
        int vertexCount = this.vertexCount;
        while (vertexCount > 0) {
            while (n13 <= n12 && vertexCount > 0) {
                --vertexCount;
                final int n15 = (n10 != 0) ? (n10 - 1) : (this.vertexCount - 1);
                this.incrementalize_y(this.vertices[n10], this.vertices[n15], this.l, this.dl, n12);
                n13 = (int)(this.vertices[n15][1] + 0.5f);
                n10 = n15;
            }
            while (n14 <= n12 && vertexCount > 0) {
                --vertexCount;
                int n16 = 0;
                if (n11 != this.vertexCount - 1) {
                    n16 = n11 + 1;
                }
                final int n17 = n16;
                this.incrementalize_y(this.vertices[n11], this.vertices[n17], this.r, this.dr, n12);
                n14 = (int)(this.vertices[n17][1] + 0.5f);
                n11 = n17;
            }
            while (n12 < n13 && n12 < n14) {
                if (n12 >= 0 && n12 < this.height) {
                    if (this.l[0] <= this.r[0]) {
                        this.scanline(n12, this.l, this.r);
                    }
                    else {
                        this.scanline(n12, this.r, this.l);
                    }
                }
                ++n12;
                this.increment(this.l, this.dl);
                this.increment(this.r, this.dr);
            }
        }
    }
    
    public void unexpand() {
        if (this.smoothing) {
            for (int i = 0; i < this.vertexCount; ++i) {
                final float[] array = this.vertices[i];
                final int n = 0;
                array[n] /= 8.0f;
                final float[] array2 = this.vertices[i];
                final int n2 = 1;
                array2[n2] /= 8.0f;
            }
        }
    }
    
    private final void scanline(final int n, final float[] array, final float[] array2) {
        for (int i = 0; i < this.vertexCount; ++i) {
            this.sp[i] = 0.0f;
            this.sdp[i] = 0.0f;
        }
        int n2 = (int)(array[0] + 0.49999f);
        if (n2 < 0) {
            n2 = 0;
        }
        int n3 = (int)(array2[0] - 0.5f);
        if (n3 > this.width1) {
            n3 = this.width1;
        }
        if (n2 > n3) {
            return;
        }
        if (this.smoothing) {
            final int modyres = this.MODYRES(n);
            this.aaleft[modyres] = n2;
            this.aaright[modyres] = n3;
            if (this.firstModY == -1) {
                this.firstModY = modyres;
                this.aaleftmin = n2;
                this.aaleftmax = n2;
                this.aarightmin = n3;
                this.aarightmax = n3;
            }
            else {
                if (this.aaleftmin > this.aaleft[modyres]) {
                    this.aaleftmin = this.aaleft[modyres];
                }
                if (this.aaleftmax < this.aaleft[modyres]) {
                    this.aaleftmax = this.aaleft[modyres];
                }
                if (this.aarightmin > this.aaright[modyres]) {
                    this.aarightmin = this.aaright[modyres];
                }
                if (this.aarightmax < this.aaright[modyres]) {
                    this.aarightmax = this.aaright[modyres];
                }
            }
            this.lastModY = modyres;
            if (modyres != 7 && n != this.lastY) {
                return;
            }
            this.aaleftfull = this.aaleftmax / 8 + 1;
            this.aarightfull = this.aarightmin / 8 - 1;
        }
        this.incrementalize_x(array, array2, this.sp, this.sdp, n2);
        final int n4 = this.smoothing ? (this.parent.width * (n / 8)) : (this.parent.width * n);
        int n5 = 0;
        int n6 = 0;
        if (this.smoothing) {
            n5 = n2 / 8;
            n6 = (n3 + 7) / 8;
            n2 = this.aaleftmin / 8;
            n3 = (this.aarightmax + 7) / 8;
            if (n2 < 0) {
                n2 = 0;
            }
            if (n3 > this.parent.width1) {
                n3 = this.parent.width1;
            }
        }
        this.interpX = false;
        for (int j = n2; j <= n3; ++j) {
            if (this.interpUV) {
                int twidth1 = (int)this.sp[7];
                int theight1 = (int)this.sp[8];
                if (twidth1 > this.twidth1) {
                    twidth1 = this.twidth1;
                }
                if (theight1 > this.theight1) {
                    theight1 = this.theight1;
                }
                if (twidth1 < 0) {
                    twidth1 = 0;
                }
                if (theight1 < 0) {
                    theight1 = 0;
                }
                final int n7 = theight1 * this.twidth + twidth1;
                int n16;
                int n17;
                int n18;
                int n19;
                if (this.smoothing || this.texture_smooth) {
                    final int n8 = (int)(255.0f * (this.sp[7] - twidth1));
                    final int n9 = (int)(255.0f * (this.sp[8] - theight1));
                    final int n10 = 255 - n8;
                    final int n11 = 255 - n9;
                    final int n12 = this.tpixels[n7];
                    final int n13 = (theight1 < this.theight1) ? this.tpixels[n7 + this.twidth] : this.tpixels[n7];
                    final int n14 = (twidth1 < this.twidth1) ? this.tpixels[n7 + 1] : this.tpixels[n7];
                    final int n15 = (theight1 < this.theight1 && twidth1 < this.twidth1) ? this.tpixels[n7 + this.twidth + 1] : this.tpixels[n7];
                    if (this.tformat == 4) {
                        n16 = ((n12 * n10 + n14 * n8 >> 8) * n11 + (n13 * n10 + n15 * n8 >> 8) * n9 >> 8) * (this.interpARGB ? ((int)(this.sp[6] * 255.0f)) : this.a2orig) >> 8;
                    }
                    else if (this.tformat == 2) {
                        n16 = (((n12 >> 24 & 0xFF) * n10 + (n14 >> 24 & 0xFF) * n8 >> 8) * n11 + ((n13 >> 24 & 0xFF) * n10 + (n15 >> 24 & 0xFF) * n8 >> 8) * n9 >> 8) * (this.interpARGB ? ((int)(this.sp[6] * 255.0f)) : this.a2orig) >> 8;
                    }
                    else {
                        n16 = (this.interpARGB ? ((int)(this.sp[6] * 255.0f)) : this.a2orig);
                    }
                    if (this.tformat == 1 || this.tformat == 2) {
                        n17 = (((n12 >> 16 & 0xFF) * n10 + (n14 >> 16 & 0xFF) * n8 >> 8) * n11 + ((n13 >> 16 & 0xFF) * n10 + (n15 >> 16 & 0xFF) * n8 >> 8) * n9 >> 8) * (this.interpARGB ? ((int)this.sp[3] * 255) : this.r2) >> 8;
                        n18 = (((n12 >> 8 & 0xFF) * n10 + (n14 >> 8 & 0xFF) * n8 >> 8) * n11 + ((n13 >> 8 & 0xFF) * n10 + (n15 >> 8 & 0xFF) * n8 >> 8) * n9 >> 8) * (this.interpARGB ? ((int)this.sp[4] * 255) : this.g2) >> 8;
                        n19 = (((n12 & 0xFF) * n10 + (n14 & 0xFF) * n8 >> 8) * n11 + ((n13 & 0xFF) * n10 + (n15 & 0xFF) * n8 >> 8) * n9 >> 8) * (this.interpARGB ? ((int)this.sp[5] * 255) : this.b2) >> 8;
                    }
                    else if (this.interpARGB) {
                        n17 = (int)(this.sp[3] * 255.0f);
                        n18 = (int)(this.sp[4] * 255.0f);
                        n19 = (int)(this.sp[5] * 255.0f);
                    }
                    else {
                        n17 = this.r2;
                        n18 = this.g2;
                        n19 = this.b2;
                    }
                    final int n20 = this.smoothing ? this.coverage(j) : 255;
                    if (n20 != 255) {
                        n16 = n16 * n20 >> 8;
                    }
                }
                else {
                    final int n21 = this.tpixels[n7];
                    if (this.tformat == 4) {
                        n16 = n21;
                        if (this.interpARGB) {
                            n17 = (int)this.sp[3] * 255;
                            n18 = (int)this.sp[4] * 255;
                            n19 = (int)this.sp[5] * 255;
                            if (this.sp[6] != 1.0f) {
                                n16 = (int)this.sp[6] * 255 * n16 >> 8;
                            }
                        }
                        else {
                            n17 = this.r2;
                            n18 = this.g2;
                            n19 = this.b2;
                            n16 = this.a2orig * n16 >> 8;
                        }
                    }
                    else {
                        final int n22 = (this.tformat == 1) ? 255 : (n21 >> 24 & 0xFF);
                        if (this.interpARGB) {
                            n17 = (int)this.sp[3] * 255 * (n21 >> 16 & 0xFF) >> 8;
                            n18 = (int)this.sp[4] * 255 * (n21 >> 8 & 0xFF) >> 8;
                            n19 = (int)this.sp[5] * 255 * (n21 & 0xFF) >> 8;
                            n16 = (int)this.sp[6] * 255 * n22 >> 8;
                        }
                        else {
                            n17 = this.r2 * (n21 >> 16 & 0xFF) >> 8;
                            n18 = this.g2 * (n21 >> 8 & 0xFF) >> 8;
                            n19 = this.b2 * (n21 & 0xFF) >> 8;
                            n16 = this.a2orig * n22 >> 8;
                        }
                    }
                }
                if (n16 == 254 || n16 == 255) {
                    this.pixels[n4 + j] = (0xFF000000 | n17 << 16 | n18 << 8 | n19);
                }
                else {
                    final int n23 = 255 - n16;
                    this.pixels[n4 + j] = (0xFF000000 | n17 * n16 + (this.pixels[n4 + j] >> 16 & 0xFF) * n23 >> 8 << 16 | (n18 * n16 + (this.pixels[n4 + j] >> 8 & 0xFF) * n23 & 0xFF00) | n19 * n16 + (this.pixels[n4 + j] & 0xFF) * n23 >> 8);
                }
            }
            else {
                int a2 = this.smoothing ? this.coverage(j) : 255;
                if (this.interpARGB) {
                    this.r2 = (int)(this.sp[3] * 255.0f);
                    this.g2 = (int)(this.sp[4] * 255.0f);
                    this.b2 = (int)(this.sp[5] * 255.0f);
                    if (this.sp[6] != 1.0f) {
                        a2 = a2 * (int)(this.sp[6] * 255.0f) >> 8;
                    }
                    if (a2 == 255) {
                        this.rgba = (0xFF000000 | this.r2 << 16 | this.g2 << 8 | this.b2);
                    }
                }
                else if (this.a2orig != 255) {
                    a2 = a2 * this.a2orig >> 8;
                }
                if (a2 == 255) {
                    this.pixels[n4 + j] = this.rgba;
                }
                else {
                    final int n24 = this.pixels[n4 + j] >> 16 & 0xFF;
                    final int n25 = this.pixels[n4 + j] >> 8 & 0xFF;
                    final int n26 = this.pixels[n4 + j] & 0xFF;
                    this.a2 = a2;
                    final int n27 = 255 - this.a2;
                    this.pixels[n4 + j] = (0xFF000000 | n24 * n27 + this.r2 * this.a2 >> 8 << 16 | n25 * n27 + this.g2 * this.a2 >> 8 << 8 | n26 * n27 + this.b2 * this.a2 >> 8);
                }
            }
            if (!this.smoothing || (j >= n5 && j <= n6)) {
                this.increment(this.sp, this.sdp);
            }
        }
        this.firstModY = -1;
        this.interpX = true;
    }
    
    private final int coverage(final int n) {
        if (n >= this.aaleftfull && n <= this.aarightfull && this.firstModY == 0 && this.lastModY == 7) {
            return 255;
        }
        final int n2 = n * 8;
        final int n3 = n2 + 8;
        int n4 = 0;
        for (int i = this.firstModY; i <= this.lastModY; ++i) {
            if (this.aaleft[i] <= n3 && this.aaright[i] >= n2) {
                n4 += ((this.aaright[i] < n3) ? this.aaright[i] : n3) - ((this.aaleft[i] > n2) ? this.aaleft[i] : n2);
            }
        }
        final int n5 = n4 << 2;
        return (n5 == 256) ? 255 : n5;
    }
    
    private final void incrementalize_y(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int n) {
        float n2 = array2[1] - array[1];
        if (n2 == 0.0f) {
            n2 = 1.0f;
        }
        final float n3 = n + 0.5f - array[1];
        if (this.interpX) {
            array4[0] = (array2[0] - array[0]) / n2;
            array3[0] = array[0] + array4[0] * n3;
        }
        if (this.interpZ) {
            array4[2] = (array2[2] - array[2]) / n2;
            array3[2] = array[2] + array4[2] * n3;
        }
        if (this.interpARGB) {
            array4[3] = (array2[3] - array[3]) / n2;
            array4[4] = (array2[4] - array[4]) / n2;
            array4[5] = (array2[5] - array[5]) / n2;
            array4[6] = (array2[6] - array[6]) / n2;
            array3[3] = array[3] + array4[3] * n3;
            array3[4] = array[4] + array4[4] * n3;
            array3[5] = array[5] + array4[5] * n3;
            array3[6] = array[6] + array4[6] * n3;
        }
        if (this.interpUV) {
            array4[7] = (array2[7] - array[7]) / n2;
            array4[8] = (array2[8] - array[8]) / n2;
            array3[7] = array[7] + array4[7] * n3;
            array3[8] = array[8] + array4[8] * n3;
        }
    }
    
    private final void incrementalize_x(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int n) {
        float n2 = array2[0] - array[0];
        if (n2 == 0.0f) {
            n2 = 1.0f;
        }
        float n3 = n + 0.5f - array[0];
        if (this.smoothing) {
            n2 /= 8.0f;
            n3 /= 8.0f;
        }
        if (this.interpX) {
            array4[0] = (array2[0] - array[0]) / n2;
            array3[0] = array[0] + array4[0] * n3;
        }
        if (this.interpZ) {
            array4[2] = (array2[2] - array[2]) / n2;
            array3[2] = array[2] + array4[2] * n3;
        }
        if (this.interpARGB) {
            array4[3] = (array2[3] - array[3]) / n2;
            array4[4] = (array2[4] - array[4]) / n2;
            array4[5] = (array2[5] - array[5]) / n2;
            array4[6] = (array2[6] - array[6]) / n2;
            array3[3] = array[3] + array4[3] * n3;
            array3[4] = array[4] + array4[4] * n3;
            array3[5] = array[5] + array4[5] * n3;
            array3[6] = array[6] + array4[6] * n3;
        }
        if (this.interpUV) {
            array4[7] = (array2[7] - array[7]) / n2;
            array4[8] = (array2[8] - array[8]) / n2;
            array3[7] = array[7] + array4[7] * n3;
            array3[8] = array[8] + array4[8] * n3;
        }
    }
    
    private final void increment(final float[] array, final float[] array2) {
        if (this.interpX) {
            final int n = 0;
            array[n] += array2[0];
        }
        if (this.interpZ) {
            final int n2 = 2;
            array[n2] += array2[2];
        }
        if (this.interpARGB) {
            final int n3 = 3;
            array[n3] += array2[3];
            final int n4 = 4;
            array[n4] += array2[4];
            final int n5 = 5;
            array[n5] += array2[5];
            final int n6 = 6;
            array[n6] += array2[6];
        }
        if (this.interpUV) {
            final int n7 = 7;
            array[n7] += array2[7];
            final int n8 = 8;
            array[n8] += array2[8];
        }
    }
    
    private final /* synthetic */ void this() {
        this.vertices = new float[64][36];
        this.r = new float[64];
        this.dr = new float[64];
        this.l = new float[64];
        this.dl = new float[64];
        this.sp = new float[64];
        this.sdp = new float[64];
        this.aaleft = new int[8];
        this.aaright = new int[8];
    }
    
    public PPolygon(final PGraphics parent) {
        this.this();
        this.parent = parent;
        this.reset(0);
    }
}
