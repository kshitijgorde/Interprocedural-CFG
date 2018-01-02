// 
// Decompiled by Procyon v0.5.30
// 

public final class v
{
    public int[] a;
    public boolean a;
    
    public v() {
        this.a = new int[65];
        this.a = true;
    }
    
    public final byte[] a() {
        final byte[] array = new byte[this.a.length * 3];
        for (int i = 0; i < this.a.length; ++i) {
            array[i * 3 + 0] = (byte)((this.a[i] & 0xFF0000) >> 16);
            array[i * 3 + 1] = (byte)((this.a[i] & 0xFF00) >> 8);
            array[i * 3 + 2] = (byte)((this.a[i] & 0xFF) >> 0);
        }
        return array;
    }
    
    public final void a(final float n, final float n2, final boolean b, final int n3) {
        final float n4 = n / 256.0f;
        final float n5 = 332.0f + (n2 - 128.0f) * 0.078125f;
        final int[] array = { 0, 240, 210, 180, 150, 120, 90, 60, 30, 0, 330, 300, 270, 0, 0, 0 };
        final float[] array2 = { 0.5f, 0.75f, 1.0f, 1.0f };
        final float[] array3 = { 0.29f, 0.45f, 0.73f, 0.9f };
        final float[] array4 = { 0.0f, 0.24f, 0.47f, 0.77f };
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 16; ++j) {
                float n6 = n4;
                float n7 = array3[i];
                if (j == 0) {
                    n6 = 0.0f;
                    n7 = array2[i];
                }
                else if (j == 13) {
                    n6 = 0.0f;
                    n7 = array4[i];
                }
                else if (j == 14 || j == 15) {
                    n6 = 0.0f;
                    n7 = 0.0f;
                }
                final float n8 = 3.1415927f * ((array[j] + n5) / 180.0f);
                final float n9 = n7 + n6 * (float)Math.sin(n8);
                final float n10 = n7 - 0.509434f * n6 * (float)Math.sin(n8) + 0.18867925f * n6 * (float)Math.cos(n8);
                final float n11 = n7 - n6 * (float)Math.cos(n8);
                float n12 = n9 * 256.0f;
                float n13 = n10 * 256.0f;
                float n14 = n11 * 256.0f;
                if (n12 > 255.0f) {
                    n12 = 255.0f;
                }
                if (n13 > 255.0f) {
                    n13 = 255.0f;
                }
                if (n14 > 255.0f) {
                    n14 = 255.0f;
                }
                if (n12 < 0.0f) {
                    n12 = 0.0f;
                }
                if (n13 < 0.0f) {
                    n13 = 0.0f;
                }
                if (n14 < 0.0f) {
                    n14 = 0.0f;
                }
                final int n15 = i * 16 + j;
                this.a[n15] = -16777216;
                final int[] a = this.a;
                final int n16 = n15;
                a[n16] |= (int)n12 << 16;
                final int[] a2 = this.a;
                final int n17 = n15;
                a2[n17] |= (int)n13 << 8;
                final int[] a3 = this.a;
                final int n18 = n15;
                a3[n18] |= (int)n14 << 0;
            }
        }
        if ((n3 & 0xE0) != 0x0) {
            for (int k = 0; k < 64; ++k) {
                float n19 = (this.a[k] & 0xFF0000) >> 16;
                float n20 = (this.a[k] & 0xFF00) >> 8;
                float n21 = (this.a[k] & 0xFF) >> 0;
                switch (n3 & 0xE0) {
                    case 32: {
                        n19 *= 1.0;
                        n20 *= 0.8;
                        n21 *= 0.73;
                        break;
                    }
                    case 64: {
                        n19 *= 0.73;
                        n20 *= 1.0;
                        n21 *= 0.7;
                        break;
                    }
                    case 96: {
                        n19 *= 0.76;
                        n20 *= 0.78;
                        n21 *= 0.58;
                        break;
                    }
                    case 128: {
                        n19 *= 0.86;
                        n20 *= 0.8;
                        n21 *= 1.0;
                        break;
                    }
                    case 160: {
                        n19 *= 0.83;
                        n20 *= 0.68;
                        n21 *= 0.85;
                        break;
                    }
                    case 192: {
                        n19 *= 0.67;
                        n20 *= 0.77;
                        n21 *= 0.83;
                        break;
                    }
                    case 224: {
                        n19 *= 0.68;
                        n20 *= 0.68;
                        n21 *= 0.68;
                        break;
                    }
                }
                this.a[k] = -16777216;
                final int[] a4 = this.a;
                final int n22 = k;
                a4[n22] |= (int)n19 << 16;
                final int[] a5 = this.a;
                final int n23 = k;
                a5[n23] |= (int)n20 << 8;
                final int[] a6 = this.a;
                final int n24 = k;
                a6[n24] |= (int)n21 << 0;
            }
        }
        if (b) {
            for (int l = 0; l < 64; ++l) {
                final int n25 = (int)((float)(((this.a[l] & 0xFF0000) >> 16) * 0.299) + (float)(((this.a[l] & 0xFF00) >> 8) * 0.587) + (float)(((this.a[l] & 0xFF) >> 0) * 0.114));
                this.a[l] = -16777216;
                final int[] a7 = this.a;
                final int n26 = l;
                a7[n26] |= n25 << 16;
                final int[] a8 = this.a;
                final int n27 = l;
                a8[n27] |= n25 << 8;
                final int[] a9 = this.a;
                final int n28 = l;
                a9[n28] |= n25 << 0;
            }
        }
        this.a[64] = -16777216;
        this.a = true;
    }
}
