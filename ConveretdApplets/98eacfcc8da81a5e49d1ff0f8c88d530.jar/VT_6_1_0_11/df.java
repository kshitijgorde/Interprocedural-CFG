// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class df
{
    public static final float[][] a;
    public static final float[][] b;
    private float c;
    private float d;
    private float e;
    private float[] f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    private int l;
    
    public df() {
        this.d = 0.0f;
        this.e = 1.0f;
        this.c = 0.1f;
        this.g = 0.0f;
        this.h = 0.0f;
        this.j = (float)(0.05 * Math.pow(6000.0, 0.30000001192092896));
        this.k = 0.05f;
        this.i = this.j / this.k;
        this.l = 0;
        this.f = new float[5];
        for (int i = 0; i < 5; ++i) {
            this.f[i] = (float)Math.log(6000.0);
        }
    }
    
    public final float a(final float[] array, final int n, final float n2) {
        float n3 = 0.0f;
        float n4 = 0.0f;
        float n5 = 7.0f;
        float n6 = 0.0f;
        for (int i = 0; i < n >> 1; ++i) {
            n3 += array[i] * array[i];
        }
        for (int j = n >> 1; j < n; ++j) {
            n4 += array[j] * array[j];
        }
        final float e;
        final float n7 = (float)Math.log((e = n3 + n4) + 6000.0f);
        for (int k = 0; k < 5; ++k) {
            n6 += (n7 - this.f[k]) * (n7 - this.f[k]);
        }
        float n8;
        if ((n8 = n6 / 150.0f) > 1.0f) {
            n8 = 1.0f;
        }
        final float n9 = 3.0f * (n2 - 0.4f) * Math.abs(n2 - 0.4f);
        this.d = (1.0f - this.c) * this.d + this.c * e;
        this.i = this.j / this.k;
        final float n10 = (float)Math.pow(e, 0.30000001192092896);
        if (this.k < 0.06f && e > 6000.0f) {
            this.j = 0.05f * n10;
        }
        if ((n9 < 0.3f && n8 < 0.2f && n10 < 1.2f * this.i) || (n9 < 0.3f && n8 < 0.05f && n10 < 1.5f * this.i) || (n9 < 0.4f && n8 < 0.05f && n10 < 1.2f * this.i) || (n9 < 0.0f && n8 < 0.05f)) {
            ++this.l;
            float n11;
            if (n10 > 3.0f * this.i) {
                n11 = 3.0f * this.i;
            }
            else {
                n11 = n10;
            }
            if (this.l >= 4) {
                this.j = 0.95f * this.j + 0.05f * n11;
                this.k = 0.95f * this.k + 0.05f;
            }
        }
        else {
            this.l = 0;
        }
        if (n10 < this.i && e > 6000.0f) {
            this.j = 0.95f * this.j + 0.05f * n10;
            this.k = 0.95f * this.k + 0.05f;
        }
        if (e < 30000.0f) {
            n5 = 6.3f;
            if (e < 10000.0f) {
                n5 = 5.6000004f;
            }
            if (e < 3000.0f) {
                n5 -= 0.7f;
            }
        }
        else {
            float n12 = (float)Math.log((e + 1.0f) / (1.0f + this.e));
            float n13;
            if ((n13 = (float)Math.log((e + 1.0f) / (1.0f + this.d))) < -5.0f) {
                n13 = -5.0f;
            }
            if (n13 > 2.0f) {
                n13 = 2.0f;
            }
            if (n13 > 0.0f) {
                n5 = 7.0f + 0.6f * n13;
            }
            if (n13 < 0.0f) {
                n5 += 0.5f * n13;
            }
            if (n12 > 0.0f) {
                if (n12 > 5.0f) {
                    n12 = 5.0f;
                }
                n5 += 0.5f * n12;
            }
            if (n4 > 1.6f * n3) {
                n5 += 0.5f;
            }
        }
        this.e = e;
        this.g = 0.6f * this.g + 0.4f * n2;
        float h;
        if ((h = (float)(n5 + 2.200000047683716 * (n2 - 0.4 + (this.g - 0.4)))) < this.h) {
            h = 0.5f * h + 0.5f * this.h;
        }
        if (h < 4.0f) {
            h = 4.0f;
        }
        if (h > 10.0f) {
            h = 10.0f;
        }
        if (this.l >= 3) {
            h = 4.0f;
        }
        if (this.l != 0) {
            h -= (float)(1.0 * (Math.log(3.0 + this.l) - Math.log(3.0)));
        }
        if (h < 0.0f) {
            h = 0.0f;
        }
        if (e < 60000.0f) {
            if (this.l > 2) {
                h -= (float)(0.5 * (Math.log(3.0 + this.l) - Math.log(3.0)));
            }
            if (e < 10000.0f && this.l > 2) {
                h -= (float)(0.5 * (Math.log(3.0 + this.l) - Math.log(3.0)));
            }
            if (h < 0.0f) {
                h = 0.0f;
            }
            h += (float)(0.3 * Math.log(e / 60000.0));
        }
        if (h < -1.0f) {
            h = -1.0f;
        }
        this.h = h;
        for (int l = 4; l > 0; --l) {
            this.f[l] = this.f[l - 1];
        }
        this.f[0] = n7;
        return h;
    }
    
    static {
        a = new float[][] { { -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f }, { 3.5f, 2.5f, 2.0f, 1.2f, 0.5f, 0.0f, -0.5f, -0.7f, -0.8f, -0.9f, -1.0f }, { 10.0f, 6.5f, 5.2f, 4.5f, 3.9f, 3.5f, 3.0f, 2.5f, 2.3f, 1.8f, 1.0f }, { 11.0f, 8.8f, 7.5f, 6.5f, 5.0f, 3.9f, 3.9f, 3.9f, 3.5f, 3.0f, 1.0f }, { 11.0f, 11.0f, 9.9f, 9.0f, 8.0f, 7.0f, 6.5f, 6.0f, 5.0f, 4.0f, 2.0f }, { 11.0f, 11.0f, 11.0f, 11.0f, 9.5f, 9.0f, 8.0f, 7.0f, 6.5f, 5.0f, 3.0f }, { 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 9.5f, 8.5f, 8.0f, 6.5f, 4.0f }, { 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 9.8f, 7.5f, 5.5f }, { 8.0f, 5.0f, 3.7f, 3.0f, 2.5f, 2.0f, 1.8f, 1.5f, 1.0f, 0.0f, 0.0f } };
        b = new float[][] { { -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f }, { -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f }, { 11.0f, 11.0f, 9.5f, 8.5f, 7.5f, 6.0f, 5.0f, 3.9f, 3.0f, 2.0f, 1.0f }, { 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 9.5f, 8.7f, 7.8f, 7.0f, 6.5f, 4.0f }, { 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 11.0f, 9.8f, 7.5f, 5.5f } };
        final float[][] array = { { -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f }, { 3.9f, 2.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f } };
    }
}
