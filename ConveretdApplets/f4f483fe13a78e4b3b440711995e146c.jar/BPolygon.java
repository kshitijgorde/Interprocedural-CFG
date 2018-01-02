// 
// Decompiled by Procyon v0.5.30
// 

public class BPolygon implements BConstants
{
    static final int DEFAULT_SIZE = 64;
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
    boolean interpRGB;
    boolean interpA;
    int rgba;
    int r2;
    int g2;
    int b2;
    BGraphics parent;
    int[] pixels;
    float[] zbuffer;
    boolean noDepthTest;
    BImage timage;
    int[] tpixels;
    int theight;
    int twidth;
    int theight1;
    int twidth1;
    int tformat;
    
    public void reset(final int vertexCount) {
        this.vertexCount = vertexCount;
        this.interpX = true;
        this.interpZ = true;
        this.interpUV = false;
        this.interpRGB = true;
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
        if (!this.interpRGB) {
            this.r2 = (int)(this.vertices[0][3] * 255.0f);
            this.g2 = (int)(this.vertices[0][4] * 255.0f);
            this.b2 = (int)(this.vertices[0][5] * 255.0f);
            this.rgba = (0xFF000000 | this.r2 << 16 | this.g2 << 8 | this.b2);
        }
        for (int i = 0; i < this.vertexCount; ++i) {
            this.r[i] = 0.0f;
            this.dr[i] = 0.0f;
            this.l[i] = 0.0f;
            this.dl[i] = 0.0f;
        }
        int n = 0;
        float n2 = this.vertices[0][1];
        for (int j = 1; j < this.vertexCount; ++j) {
            if (this.vertices[j][1] < n2) {
                n2 = this.vertices[j][1];
                n = j;
            }
        }
        int n3 = n;
        int n4 = n;
        int n5 = (int)(n2 + 0.5f);
        int n6 = n5 - 1;
        int n7 = n5 - 1;
        this.interpX = true;
        int k = this.vertexCount;
        while (k > 0) {
            while (n6 <= n5 && k > 0) {
                --k;
                final int n8 = (n3 != 0) ? (n3 - 1) : (this.vertexCount - 1);
                this.incrementalize_y(this.vertices[n3], this.vertices[n8], this.l, this.dl, n5);
                n6 = (int)(this.vertices[n8][1] + 0.5f);
                n3 = n8;
            }
            while (n7 <= n5 && k > 0) {
                --k;
                final int n9 = (n4 != this.vertexCount - 1) ? (n4 + 1) : 0;
                this.incrementalize_y(this.vertices[n4], this.vertices[n9], this.r, this.dr, n5);
                n7 = (int)(this.vertices[n9][1] + 0.5f);
                n4 = n9;
            }
            while (n5 < n6 && n5 < n7) {
                if (n5 >= 0 && n5 < this.parent.height) {
                    try {
                        if (this.l[0] <= this.r[0]) {
                            this.scanline(n5, this.l, this.r);
                        }
                        else {
                            this.scanline(n5, this.r, this.l);
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {
                        ex.printStackTrace();
                    }
                }
                ++n5;
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
        int n2 = (int)(array[0] + 0.5f);
        if (n2 < 0) {
            n2 = 0;
        }
        int width1 = (int)(array2[0] - 0.5f);
        if (width1 > this.parent.width1) {
            width1 = this.parent.width1;
        }
        if (n2 > width1) {
            return;
        }
        this.incrementalize_x(array, array2, this.sp, this.sdp, n2);
        this.interpX = false;
        final int n3 = this.parent.width * n;
        for (int j = n2; j <= width1; ++j) {
            if (this.noDepthTest || this.sp[2] <= this.zbuffer[n3 + j]) {
                if (this.interpUV) {
                    int n4 = (int)this.sp[8] * this.twidth + (int)this.sp[7];
                    if (this.sp[8] < 0.0f) {
                        n4 = (int)this.sp[7];
                    }
                    switch (this.tformat) {
                        case 1: {
                            if (this.parent.hints[0]) {
                                final int n5 = (int)this.sp[7];
                                final int n6 = (int)this.sp[8];
                                final int n7 = (int)(255.0f * (this.sp[7] - n5));
                                final int n8 = (int)(255.0f * (this.sp[8] - n6));
                                final int n9 = 255 - n7;
                                final int n10 = 255 - n8;
                                final int n11 = this.tpixels[n4];
                                final int n12 = (n6 != this.theight - 1) ? this.tpixels[n4 + this.twidth] : this.tpixels[n4];
                                final int n13 = (n5 != this.twidth - 1) ? this.tpixels[n4 + 1] : this.tpixels[n4];
                                final int n14 = (n5 != this.theight - 1 && n6 != this.twidth - 1) ? this.tpixels[n4 + this.twidth + 1] : this.tpixels[n4];
                                this.pixels[n3 + j] = (0xFF000000 | (((n11 >> 16 & 0xFF) * n9 + (n13 >> 16 & 0xFF) * n7 >> 8) * n10 + ((n12 >> 16 & 0xFF) * n9 + (n14 >> 16 & 0xFF) * n7 >> 8) * n8 >> 8 << 16 | ((n11 >> 8 & 0xFF) * n9 + (n13 >> 8 & 0xFF) * n7 >> 8) * n10 + ((n12 >> 8 & 0xFF) * n9 + (n14 >> 8 & 0xFF) * n7 >> 8) * n8 >> 8 << 8) | ((n11 & 0xFF) * n9 + (n13 & 0xFF) * n7 >> 8) * n10 + ((n12 & 0xFF) * n9 + (n14 & 0xFF) * n7 >> 8) * n8 >> 8);
                                this.zbuffer[n3 + j] = this.sp[2];
                            }
                            else {
                                this.pixels[n3 + j] = this.tpixels[n4];
                                this.zbuffer[n3 + j] = this.sp[2];
                            }
                            break;
                        }
                        case 4: {
                            final int n15 = this.pixels[n3 + j] >> 16 & 0xFF;
                            final int n16 = this.pixels[n3 + j] >> 8 & 0xFF;
                            final int n17 = this.pixels[n3 + j] & 0xFF;
                            if (this.interpRGB) {
                                this.r2 = (int)(this.sp[3] * 255.0f);
                                this.g2 = (int)(this.sp[4] * 255.0f);
                                this.b2 = (int)(this.sp[5] * 255.0f);
                            }
                            int n18 = this.tpixels[n4];
                            if (this.parent.hints[0]) {
                                final int n19 = (int)this.sp[7];
                                final int n20 = (int)this.sp[8];
                                final int n21 = (int)(255.0f * (this.sp[7] - n19));
                                final int n22 = (int)(255.0f * (this.sp[8] - n20));
                                final int n23 = 255 - n21;
                                n18 = ((this.tpixels[n4] & 0xFF) * n23 + (((n19 != this.twidth - 1) ? this.tpixels[n4 + 1] : this.tpixels[n4]) & 0xFF) * n21 >> 8) * (255 - n22) + ((((n20 != this.theight - 1) ? this.tpixels[n4 + this.twidth] : this.tpixels[n4]) & 0xFF) * n23 + (((n19 != this.theight - 1 && n20 != this.twidth - 1) ? this.tpixels[n4 + this.twidth + 1] : this.tpixels[n4]) & 0xFF) * n21 >> 8) * n22 >> 8;
                            }
                            final int n24 = 255 - n18;
                            this.pixels[n3 + j] = (0xFF000000 | n15 * n24 + this.r2 * n18 >> 8 << 16 | n16 * n24 + this.g2 * n18 >> 8 << 8 | n17 * n24 + this.b2 * n18 >> 8);
                            if (n18 > 127) {
                                this.zbuffer[n3 + j] = this.sp[2];
                                break;
                            }
                            break;
                        }
                    }
                }
                else {
                    this.zbuffer[n3 + j] = this.sp[2];
                    this.pixels[n3 + j] = (this.interpRGB ? (0xFF000000 | (int)(this.sp[3] * 255.0f) << 16 | (int)(this.sp[4] * 255.0f) << 8 | (int)(this.sp[5] * 255.0f)) : this.rgba);
                }
            }
            this.increment(this.sp, this.sdp);
        }
        this.interpX = true;
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
        if (this.interpRGB) {
            array4[3] = (array2[3] - array[3]) / n2;
            array4[4] = (array2[4] - array[4]) / n2;
            array4[5] = (array2[5] - array[5]) / n2;
            array3[3] = array[3] + array4[3] * n3;
            array3[4] = array[4] + array4[4] * n3;
            array3[5] = array[5] + array4[5] * n3;
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
        final float n3 = n + 0.5f - array[0];
        if (this.interpX) {
            array4[0] = (array2[0] - array[0]) / n2;
            array3[0] = array[0] + array4[0] * n3;
        }
        if (this.interpZ) {
            array4[2] = (array2[2] - array[2]) / n2;
            array3[2] = array[2] + array4[2] * n3;
        }
        if (this.interpRGB) {
            array4[3] = (array2[3] - array[3]) / n2;
            array4[4] = (array2[4] - array[4]) / n2;
            array4[5] = (array2[5] - array[5]) / n2;
            array3[3] = array[3] + array4[3] * n3;
            array3[4] = array[4] + array4[4] * n3;
            array3[5] = array[5] + array4[5] * n3;
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
        if (this.interpRGB) {
            final int n3 = 3;
            array[n3] += array2[3];
            final int n4 = 4;
            array[n4] += array2[4];
            final int n5 = 5;
            array[n5] += array2[5];
        }
        if (this.interpUV) {
            final int n6 = 7;
            array[n6] += array2[7];
            final int n7 = 8;
            array[n7] += array2[8];
        }
    }
    
    public BPolygon(final BGraphics parent) {
        this.vertices = new float[64][19];
        this.r = new float[64];
        this.dr = new float[64];
        this.l = new float[64];
        this.dl = new float[64];
        this.sp = new float[64];
        this.sdp = new float[64];
        this.parent = parent;
        this.pixels = this.parent.pixels;
        this.zbuffer = this.parent.zbuffer;
        this.reset(0);
    }
}
