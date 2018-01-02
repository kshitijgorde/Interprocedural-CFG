// 
// Decompiled by Procyon v0.5.30
// 

public class BPolygon3 implements BConstants
{
    static final int DEFAULT_SIZE = 64;
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
    boolean interpRGBA;
    int rgba;
    int r2;
    int g2;
    int b2;
    int a2;
    int a2orig;
    float[] zbuffer;
    boolean noDepthTest;
    BGraphics parent;
    int[] pixels;
    int width;
    int height;
    int width1;
    int height1;
    BImage timage;
    int[] tpixels;
    int theight;
    int twidth;
    int theight1;
    int twidth1;
    int tformat;
    boolean smoothing;
    int firstModY;
    int[] aaleft;
    int[] aaright;
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
        this.interpRGBA = true;
        this.timage = null;
    }
    
    public float[] nextVertex() {
        if (this.vertexCount == this.vertices.length) {
            this.parent.message(0, "re-allocating for " + this.vertexCount * 2 + " vertices");
            final float[][] vertices = new float[this.vertexCount << 1][19];
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
    
    public void texture(final BImage timage) {
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
        this.noDepthTest = !this.parent.depthTest;
        this.smoothing = this.parent.smooth;
        this.width = (this.smoothing ? (this.parent.width * 8) : this.parent.width);
        this.height = (this.smoothing ? (this.parent.height * 8) : this.parent.height);
        this.width1 = this.width - 1;
        this.height1 = this.height - 1;
        if (!this.interpRGBA) {
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
        if (this.smoothing) {
            for (int j = 0; j < this.vertexCount; ++j) {
                final float[] array = this.vertices[j];
                final int n = 0;
                array[n] *= 8.0f;
                final float[] array2 = this.vertices[j];
                final int n2 = 1;
                array2[n2] *= 8.0f;
            }
            this.firstModY = -1;
        }
        int n3 = 0;
        float n4 = this.vertices[0][1];
        for (int k = 1; k < this.vertexCount; ++k) {
            if (this.vertices[k][1] < n4) {
                n4 = this.vertices[k][1];
                n3 = k;
            }
        }
        int n5 = n3;
        int n6 = n3;
        int n7 = (int)(n4 + 0.5f);
        int n8 = n7 - 1;
        int n9 = n7 - 1;
        this.interpX = true;
        int l = this.vertexCount;
        while (l > 0) {
            while (n8 <= n7 && l > 0) {
                --l;
                final int n10 = (n5 != 0) ? (n5 - 1) : (this.vertexCount - 1);
                this.incrementalize_y(this.vertices[n5], this.vertices[n10], this.l, this.dl, n7);
                n8 = (int)(this.vertices[n10][1] + 0.5f);
                n5 = n10;
            }
            while (n9 <= n7 && l > 0) {
                --l;
                final int n11 = (n6 != this.vertexCount - 1) ? (n6 + 1) : 0;
                this.incrementalize_y(this.vertices[n6], this.vertices[n11], this.r, this.dr, n7);
                n9 = (int)(this.vertices[n11][1] + 0.5f);
                n6 = n11;
            }
            while (n7 < n8 && n7 < n9) {
                if (n7 >= 0 && n7 < this.height) {
                    try {
                        if (this.l[0] <= this.r[0]) {
                            this.scanline(n7, this.l, this.r);
                        }
                        else {
                            this.scanline(n7, this.r, this.l);
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {
                        ex.printStackTrace();
                    }
                }
                ++n7;
                this.increment(this.l, this.dl);
                this.increment(this.r, this.dr);
            }
        }
    }
    
    private void scanline(final int n, final float[] array, final float[] array2) {
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
            if (this.firstModY == -1) {
                this.firstModY = modyres;
            }
            this.aaleft[modyres] = n2;
            this.aaright[modyres] = n3;
            if (modyres != 7) {
                return;
            }
            int n4 = this.aaleft[this.firstModY];
            int n5 = this.aaright[this.firstModY];
            for (int j = this.firstModY + 1; j < 8; ++j) {
                if (n4 < this.aaleft[j]) {
                    n4 = this.aaleft[j];
                }
                if (n5 > this.aaright[j]) {
                    n5 = this.aaright[j];
                }
            }
            this.aaleftfull = n4 / 8 + 1;
            this.aarightfull = n5 / 8 - 1;
        }
        this.incrementalize_x(array, array2, this.sp, this.sdp, n2);
        final int n6 = this.smoothing ? (this.parent.width * (n / 8)) : (this.parent.width * n);
        int n7 = 0;
        int n8 = 0;
        if (this.smoothing) {
            n2 /= 8;
            n3 /= 8;
            n7 = n2;
            n8 = n3;
            n2 -= 16;
            if (n2 < 0) {
                n2 = 0;
            }
            n3 += 16;
            if (n3 > this.parent.width1) {
                n3 = this.parent.width1;
            }
        }
        this.interpX = false;
        for (int k = n2; k <= n3; ++k) {
            if (this.noDepthTest || this.sp[2] <= this.zbuffer[n6 + k]) {
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
                    final int n9 = theight1 * this.twidth + twidth1;
                    int n18;
                    int n19;
                    int n20;
                    int n21;
                    if (this.smoothing || this.parent.hints[0]) {
                        final int n10 = (int)(255.0f * (this.sp[7] - twidth1));
                        final int n11 = (int)(255.0f * (this.sp[8] - theight1));
                        final int n12 = 255 - n10;
                        final int n13 = 255 - n11;
                        final int n14 = this.tpixels[n9];
                        final int n15 = (theight1 != this.theight - 1) ? this.tpixels[n9 + this.twidth] : this.tpixels[n9];
                        final int n16 = (twidth1 != this.twidth - 1) ? this.tpixels[n9 + 1] : this.tpixels[n9];
                        final int n17 = (twidth1 != this.theight - 1 && theight1 != this.twidth - 1) ? this.tpixels[n9 + this.twidth + 1] : this.tpixels[n9];
                        if (this.tformat == 4) {
                            n18 = ((n14 * n12 + n16 * n10 >> 8) * n13 + (n15 * n12 + n17 * n10 >> 8) * n11 >> 8) * (this.interpRGBA ? ((int)(this.sp[6] * 255.0f)) : this.a2orig) >> 8;
                        }
                        else if (this.tformat == 2) {
                            n18 = (((n14 >> 24 & 0xFF) * n12 + (n16 >> 24 & 0xFF) * n10 >> 8) * n13 + ((n15 >> 24 & 0xFF) * n12 + (n17 >> 24 & 0xFF) * n10 >> 8) * n11 >> 8) * (this.interpRGBA ? ((int)(this.sp[6] * 255.0f)) : this.a2orig) >> 8;
                        }
                        else {
                            n18 = (this.interpRGBA ? ((int)(this.sp[6] * 255.0f)) : this.a2orig);
                        }
                        if (this.tformat == 1 || this.tformat == 2) {
                            n19 = (((n14 >> 16 & 0xFF) * n12 + (n16 >> 16 & 0xFF) * n10 >> 8) * n13 + ((n15 >> 16 & 0xFF) * n12 + (n17 >> 16 & 0xFF) * n10 >> 8) * n11 >> 8) * (this.interpRGBA ? ((int)this.sp[3] * 255) : this.r2) >> 8;
                            n20 = (((n14 >> 8 & 0xFF) * n12 + (n16 >> 8 & 0xFF) * n10 >> 8) * n13 + ((n15 >> 8 & 0xFF) * n12 + (n17 >> 8 & 0xFF) * n10 >> 8) * n11 >> 8) * (this.interpRGBA ? ((int)this.sp[4] * 255) : this.g2) >> 8;
                            n21 = (((n14 & 0xFF) * n12 + (n16 & 0xFF) * n10 >> 8) * n13 + ((n15 & 0xFF) * n12 + (n17 & 0xFF) * n10 >> 8) * n11 >> 8) * (this.interpRGBA ? ((int)this.sp[5] * 255) : this.b2) >> 8;
                        }
                        else if (this.interpRGBA) {
                            n19 = (int)(this.sp[3] * 255.0f);
                            n20 = (int)(this.sp[4] * 255.0f);
                            n21 = (int)(this.sp[5] * 255.0f);
                        }
                        else {
                            n19 = this.r2;
                            n20 = this.g2;
                            n21 = this.b2;
                        }
                        final int n22 = this.smoothing ? this.coverage(k) : 255;
                        if (n22 != 255) {
                            n18 = n18 * n22 >> 8;
                        }
                    }
                    else {
                        final int n23 = this.tpixels[n9];
                        if (this.tformat == 4) {
                            n18 = n23;
                            if (this.interpRGBA) {
                                n19 = (int)this.sp[3] * 255;
                                n20 = (int)this.sp[4] * 255;
                                n21 = (int)this.sp[5] * 255;
                                if (this.sp[6] != 1.0f) {
                                    n18 = (int)this.sp[6] * 255 * n18 >> 8;
                                }
                            }
                            else {
                                n19 = this.r2;
                                n20 = this.g2;
                                n21 = this.b2;
                                n18 = this.a2orig * n18 >> 8;
                            }
                        }
                        else {
                            final int n24 = (this.tformat == 1) ? 255 : (n23 >> 24 & 0xFF);
                            if (this.interpRGBA) {
                                n19 = (int)this.sp[3] * 255 * (n23 >> 16 & 0xFF) >> 8;
                                n20 = (int)this.sp[4] * 255 * (n23 >> 8 & 0xFF) >> 8;
                                n21 = (int)this.sp[5] * 255 * (n23 & 0xFF) >> 8;
                                n18 = (int)this.sp[6] * 255 * n24 >> 8;
                            }
                            else {
                                n19 = this.r2 * (n23 >> 16 & 0xFF) >> 8;
                                n20 = this.g2 * (n23 >> 8 & 0xFF) >> 8;
                                n21 = this.b2 * (n23 & 0xFF) >> 8;
                                n18 = this.a2orig * n24 >> 8;
                            }
                        }
                    }
                    if (n18 == 254 || n18 == 255) {
                        this.pixels[n6 + k] = (0xFF000000 | n19 << 16 | n20 << 8 | n21);
                        this.zbuffer[n6 + k] = this.sp[2];
                    }
                    else {
                        final int n25 = 255 - n18;
                        this.pixels[n6 + k] = (0xFF000000 | n19 * n18 + (this.pixels[n6 + k] >> 16 & 0xFF) * n25 >> 8 << 16 | (n20 * n18 + (this.pixels[n6 + k] >> 8 & 0xFF) * n25 & 0xFF00) | n21 * n18 + (this.pixels[n6 + k] & 0xFF) * n25 >> 8);
                        if (n18 > 127) {
                            this.zbuffer[n6 + k] = this.sp[2];
                        }
                    }
                }
                else {
                    int a2 = this.smoothing ? this.coverage(k) : 255;
                    if (this.interpRGBA) {
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
                        this.pixels[n6 + k] = this.rgba;
                        this.zbuffer[n6 + k] = this.sp[2];
                    }
                    else {
                        final int n26 = this.pixels[n6 + k] >> 16 & 0xFF;
                        final int n27 = this.pixels[n6 + k] >> 8 & 0xFF;
                        final int n28 = this.pixels[n6 + k] & 0xFF;
                        this.a2 = a2;
                        final int n29 = 255 - this.a2;
                        this.pixels[n6 + k] = (0xFF000000 | n26 * n29 + this.r2 * this.a2 >> 8 << 16 | n27 * n29 + this.g2 * this.a2 >> 8 << 8 | n28 * n29 + this.b2 * this.a2 >> 8);
                        if (this.a2 > 127) {
                            this.zbuffer[n6 + k] = this.sp[2];
                        }
                    }
                }
            }
            if (!this.smoothing || (k >= n7 && k <= n8)) {
                this.increment(this.sp, this.sdp);
            }
        }
        this.firstModY = -1;
        this.interpX = true;
    }
    
    private static final int MIN(final int n, final int n2) {
        return (n < n2) ? n : n2;
    }
    
    private static final int MAX(final int n, final int n2) {
        return (n > n2) ? n : n2;
    }
    
    private int coverage(final int n) {
        if (n >= this.aaleftfull && n <= this.aarightfull) {
            return 255;
        }
        final int n2 = n * 8;
        final int n3 = n2 + 8;
        int n4 = 0;
        for (int i = this.firstModY; i < 8; ++i) {
            if (this.aaleft[i] <= n3 && this.aaright[i] >= n2) {
                n4 += MIN(this.aaright[i], n3) - MAX(this.aaleft[i], n2);
            }
        }
        final int n5 = n4 << 2;
        return (n5 == 256) ? 255 : n5;
    }
    
    private void incrementalize_y(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int n) {
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
        if (this.interpRGBA) {
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
    
    private void incrementalize_x(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int n) {
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
        if (this.interpRGBA) {
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
    
    private void increment(final float[] array, final float[] array2) {
        if (this.interpX) {
            final int n = 0;
            array[n] += array2[0];
        }
        if (this.interpZ) {
            final int n2 = 2;
            array[n2] += array2[2];
        }
        if (this.interpRGBA) {
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
    
    public BPolygon3(final BGraphics parent) {
        this.vertices = new float[64][19];
        this.r = new float[64];
        this.dr = new float[64];
        this.l = new float[64];
        this.dl = new float[64];
        this.sp = new float[64];
        this.sdp = new float[64];
        this.aaleft = new int[8];
        this.aaright = new int[8];
        this.parent = parent;
        this.pixels = this.parent.pixels;
        this.zbuffer = this.parent.zbuffer;
        this.reset(0);
    }
}
