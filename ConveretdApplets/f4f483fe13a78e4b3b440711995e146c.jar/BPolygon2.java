// 
// Decompiled by Procyon v0.5.30
// 

public class BPolygon2 extends BPolygon
{
    static final int SUBYRES = 8;
    static final int SUBXRES = 8;
    static final int MAX_AREA = 64;
    static final int MAX_X = 32767;
    float[] vscanLeft;
    float[] vscanRight;
    float[] vpixel;
    int vright;
    int vnextRight;
    int endPoly;
    int[] SPY;
    int[] spxLeft;
    int[] spxRight;
    int xLmin;
    int xLmax;
    int xRmax;
    int xRmin;
    int[] mask;
    
    private final int MODRES(final int n) {
        return n & 0x7;
    }
    
    float LERP(final float n, final float n2, final float n3) {
        return n2 + (n3 - n2) * n;
    }
    
    void VLERP(final float n, final float[] array, final float[] array2, final float[] array3) {
        final float n2 = 1.0f - n;
        array3[0] = array[0] * n + array2[0] * n2;
        array3[1] = array[1] * n + array2[1] * n2;
        array3[2] = array[2] * n + array2[2] * n2;
        if (super.interpRGB) {
            array3[3] = array[3] * n + array2[3] * n2;
            array3[4] = array[4] * n + array2[4] * n2;
            array3[5] = array[5] * n + array2[5] * n2;
        }
        if (super.interpA) {
            array3[6] = array[6] * n + array2[6] * n2;
        }
        if (super.interpUV) {
            array3[7] = array[7] * n + array2[7] * n2;
            array3[8] = array[8] * n + array2[8] * n2;
        }
    }
    
    public void render() {
        this.SPY = new int[super.vertexCount];
        for (int i = 0; i < super.vertexCount; ++i) {
            this.SPY[i] = (int)(super.vertices[i][1] * 8.0f);
        }
        int n = 0;
        for (int j = 1; j < super.vertexCount; ++j) {
            if (this.SPY[j] < this.SPY[n]) {
                n = j;
            }
        }
        System.out.println("initial vleft is " + n);
        this.endPoly = super.vertexCount - 1;
        int n2 = n - 1;
        if (n2 < 0) {
            n2 = this.endPoly;
        }
        int n3 = n;
        int n4 = (n3 + 1) % super.vertexCount;
        System.out.println("initial vnextLeft = " + n2);
        System.out.println("vright, nextright = " + n3 + " " + n4);
        for (int k = 0; k < 8; ++k) {
            this.spxLeft[k] = -1;
            this.spxRight[k] = -1;
        }
        final int n5 = 32767;
        this.xRmin = n5;
        this.xLmin = n5;
        final int n6 = -1;
        this.xRmax = n6;
        this.xLmax = n6;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        int l = this.SPY[n];
        while (true) {
            System.out.println("down to " + l + " (" + l / 8 + ")");
            while (l == this.SPY[n2]) {
                n = n2;
                if (--n2 < 0) {
                    n2 = this.endPoly;
                }
                System.out.println("vleft changed to " + n + " " + n2);
                if (n2 == n3) {
                    return;
                }
                n7 = (int)(super.vertices[n][0] * 8.0f);
                n8 = (int)(super.vertices[n2][0] * 8.0f);
                System.out.println("xLeft=" + n7 + " xNextLeft=" + n8);
            }
            while (l == this.SPY[n4]) {
                n3 = n4;
                if (++n4 == super.vertexCount) {
                    n4 = 0;
                }
                System.out.println("vright changed to " + n3 + " " + n4);
                n9 = (int)(super.vertices[n3][0] * 8.0f);
                n10 = (int)(super.vertices[n4][0] * 8.0f);
                System.out.println("xRight=" + n9 + " xNextRight=" + n10);
            }
            if (l > this.SPY[n2] || l > this.SPY[n4]) {
                while (this.MODRES(l) != 0) {
                    this.spxLeft[this.MODRES(l)] = (this.spxRight[this.MODRES(l)] = -1);
                    ++l;
                }
                System.out.println("scanline 1");
                this.scanline(super.vertices[n], super.vertices[n3], l / 8);
                return;
            }
            final int modres = this.MODRES(l);
            final float n11 = (l - this.SPY[n]) / (this.SPY[n2] - this.SPY[n]);
            this.spxLeft[modres] = (int)this.LERP(n11, n7, n8);
            if (this.spxLeft[modres] < this.xLmin) {
                this.xLmin = this.spxLeft[modres];
            }
            if (this.spxLeft[modres] > this.xLmax) {
                this.xLmax = this.spxLeft[modres];
            }
            final float n12 = (l - this.SPY[n3]) / (this.SPY[n4] - this.SPY[n3]);
            this.spxRight[modres] = (int)this.LERP(n12, n9, n10);
            if (this.spxRight[modres] < this.xRmin) {
                this.xRmin = this.spxRight[modres];
            }
            if (this.spxRight[modres] > this.xRmax) {
                this.xRmax = this.spxRight[modres];
            }
            System.out.println("aleft, aright = " + n11 + ", " + n12);
            if (this.MODRES(l) == 7) {
                for (int n13 = 0; n13 < 8; ++n13) {
                    System.out.println("spxLeft/Right " + n13 + " " + this.spxLeft[n13] + " " + this.spxRight[n13]);
                }
                this.VLERP(n11, super.vertices[n], super.vertices[n2], this.vscanLeft);
                this.VLERP(n12, super.vertices[n3], super.vertices[n4], this.vscanRight);
                this.scanline(this.vscanLeft, this.vscanRight, l / 8);
                final int n14 = 32767;
                this.xRmin = n14;
                this.xLmin = n14;
                final int n15 = -1;
                this.xRmax = n15;
                this.xLmax = n15;
            }
            ++l;
        }
    }
    
    private void scanline(final float[] array, final float[] array2, final int n) {
        System.out.println("scanline " + n + " " + array[0] + " " + array2[0]);
        for (int i = 8 * (int)(this.xLmin / 8.0f); i <= this.xRmax; i += 8) {
            this.VLERP((i - this.xLmin) / (this.xRmax - this.xLmin), array, array2, this.vpixel);
            this.pixel(i / 8, n, this.vpixel, this.coverage(i));
        }
    }
    
    private void pixel(final int n, final int n2, final float[] array, final int n3) {
        final int n4 = 255 - ((n3 << 2) - 1);
        super.pixels[n2 * super.parent.width + n] = (0xFF000000 | n4 << 16 | n4 << 8 | n4);
    }
    
    private int coverage(final int n) {
        final int n2 = n + 8 - 1;
        if (n > this.xLmax && n < this.xRmin) {
            return 64;
        }
        int n3;
        for (int i = n3 = 0; i < 8; ++i) {
            final int n4 = Math.min(this.spxRight[i], n2) - Math.max(this.spxLeft[i], n) + 1;
            if (n4 > 0) {
                n3 += n4;
            }
        }
        return n3;
    }
    
    public BPolygon2(final BGraphics bGraphics) {
        super(bGraphics);
        this.vscanLeft = new float[19];
        this.vscanRight = new float[19];
        this.vpixel = new float[19];
        this.spxLeft = new int[8];
        this.spxRight = new int[8];
        this.mask = new int[8];
    }
}
