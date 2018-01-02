// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D.A;

public class E extends J
{
    static final long Q = 2239251672685254626L;
    protected K P;
    public boolean O;
    
    public E() {
        this(new float[9]);
    }
    
    public E(final float[] array) {
        this(new K(3, 3, array));
    }
    
    public E(final int n, final int n2, final float[] array) {
        this(new K(n, n2, array));
    }
    
    public E(final K p) {
        this.P = null;
        this.O = true;
        this.P = p;
    }
    
    public void A(final K p) {
        this.P = p;
    }
    
    public K E() {
        return this.P;
    }
    
    public void imageComplete(final int n) {
        if (n == 1 || n == 4) {
            this.consumer.imageComplete(n);
            return;
        }
        final int width = this.A.width;
        final int height = this.A.height;
        final int[] array = new int[width * height];
        A(this.P, this.C, array, width, height, this.O);
        this.consumer.setPixels(0, 0, width, height, this.B, array, 0, width);
        this.consumer.imageComplete(n);
        this.C = null;
    }
    
    public static void A(final K k, final int[] array, final int[] array2, final int n, final int n2) {
        A(k, array, array2, n, n2, true);
    }
    
    public static void A(final K k, final int[] array, final int[] array2, final int n, final int n2, final boolean b) {
        if (k.P == 1) {
            C(k, array, array2, n, n2, b);
        }
        else if (k.L == 1) {
            D(k, array, array2, n, n2, b);
        }
        else {
            B(k, array, array2, n, n2, b);
        }
    }
    
    public static void B(final K k, final int[] array, final int[] array2, final int n, final int n2, final boolean b) {
        int n3 = 0;
        final float[] n4 = k.N;
        final int p6 = k.P;
        final int l = k.L;
        final int n5 = p6 / 2;
        final int n6 = l / 2;
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                float n7 = 0.0f;
                float n8 = 0.0f;
                float n9 = 0.0f;
                float n10 = 0.0f;
                for (int n11 = -n5; n11 <= n5; ++n11) {
                    final int n12 = i + n11;
                    int n13;
                    if (0 <= n12 && n12 < n2) {
                        n13 = n12 * n;
                    }
                    else {
                        n13 = i * n;
                    }
                    final int n14 = l * (n11 + n5) + n6;
                    for (int n15 = -n6; n15 <= n6; ++n15) {
                        final float n16 = n4[n14 + n15];
                        if (n16 != 0.0f) {
                            int n17 = j + n15;
                            if (0 > n17 || n17 >= n) {
                                n17 = j;
                            }
                            final int n18 = array[n13 + n17];
                            n10 += n16 * (n18 >> 24 & 0xFF);
                            n7 += n16 * (n18 >> 16 & 0xFF);
                            n8 += n16 * (n18 >> 8 & 0xFF);
                            n9 += n16 * (n18 & 0xFF);
                        }
                    }
                }
                array2[n3++] = ((b ? D.B((int)(n10 + 0.5)) : 255) << 24 | D.B((int)(n7 + 0.5)) << 16 | D.B((int)(n8 + 0.5)) << 8 | D.B((int)(n9 + 0.5)));
            }
        }
    }
    
    public static void C(final K k, final int[] array, final int[] array2, final int n, final int n2, final boolean b) {
        int n3 = 0;
        final float[] n4 = k.N;
        final int n5 = k.L / 2;
        for (int i = 0; i < n2; ++i) {
            final int n6 = i * n;
            for (int j = 0; j < n; ++j) {
                float n7 = 0.0f;
                float n8 = 0.0f;
                float n9 = 0.0f;
                float n10 = 0.0f;
                final int n11 = n5;
                for (int l = -n5; l <= n5; ++l) {
                    final float n12 = n4[n11 + l];
                    if (n12 != 0.0f) {
                        int n13 = j + l;
                        if (0 > n13 || n13 >= n) {
                            n13 = j;
                        }
                        final int n14 = array[n6 + n13];
                        n10 += n12 * (n14 >> 24 & 0xFF);
                        n7 += n12 * (n14 >> 16 & 0xFF);
                        n8 += n12 * (n14 >> 8 & 0xFF);
                        n9 += n12 * (n14 & 0xFF);
                    }
                }
                array2[n3++] = ((b ? D.B((int)(n10 + 0.5)) : 255) << 24 | D.B((int)(n7 + 0.5)) << 16 | D.B((int)(n8 + 0.5)) << 8 | D.B((int)(n9 + 0.5)));
            }
        }
    }
    
    public static void D(final K k, final int[] array, final int[] array2, final int n, final int n2, final boolean b) {
        int n3 = 0;
        final float[] n4 = k.N;
        final int n5 = k.P / 2;
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                float n6 = 0.0f;
                float n7 = 0.0f;
                float n8 = 0.0f;
                float n9 = 0.0f;
                for (int l = -n5; l <= n5; ++l) {
                    final int n10 = i + l;
                    int n11;
                    if (0 <= n10 && n10 < n2) {
                        n11 = n10 * n;
                    }
                    else {
                        n11 = i * n;
                    }
                    final float n12 = n4[l + n5];
                    if (n12 != 0.0f) {
                        final int n13 = array[n11 + j];
                        n9 += n12 * (n13 >> 24 & 0xFF);
                        n6 += n12 * (n13 >> 16 & 0xFF);
                        n7 += n12 * (n13 >> 8 & 0xFF);
                        n8 += n12 * (n13 & 0xFF);
                    }
                }
                array2[n3++] = ((b ? D.B((int)(n9 + 0.5)) : 255) << 24 | D.B((int)(n6 + 0.5)) << 16 | D.B((int)(n7 + 0.5)) << 8 | D.B((int)(n8 + 0.5)));
            }
        }
    }
    
    public String toString() {
        return "Blur/Convolve...";
    }
}
