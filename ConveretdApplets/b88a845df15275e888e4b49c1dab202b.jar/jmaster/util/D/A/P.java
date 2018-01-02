// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D.A;

public class P extends E
{
    static final long Q = 5377089073023183684L;
    protected int S;
    private K[] R;
    
    public P() {
        this(2);
    }
    
    public P(final int n) {
        this.D(n);
    }
    
    public void D(final int s) {
        this.S = s;
        this.R = C(s);
    }
    
    public int F() {
        return this.S;
    }
    
    public void imageComplete(final int n) {
        if (n == 1 || n == 4) {
            this.consumer.imageComplete(n);
            return;
        }
        final int width = this.A.width;
        final int height = this.A.height;
        final int[] array = new int[width * height];
        E.C(this.R[0], this.C, array, width, height, this.O);
        E.D(this.R[1], array, this.C, width, height, this.O);
        this.consumer.setPixels(0, 0, width, height, this.B, this.C, 0, width);
        this.consumer.imageComplete(n);
        this.C = null;
    }
    
    public static K E(final int n) {
        final int n3;
        final int n2 = n3 = n * 2 + 1;
        final float[] array = new float[n2 * n3];
        final float n4 = n / 3.0f;
        final float n5 = 2.0f * n4 * n4;
        final float n6 = 6.2831855f * n4;
        final float n7 = n * n;
        float n8 = 0.0f;
        int n9 = 0;
        for (int i = -n; i <= n; ++i) {
            for (int j = -n; j <= n; ++j) {
                final float n10 = i * i + j * j;
                if (n10 > n7) {
                    array[n9] = 0.0f;
                }
                else {
                    array[n9] = (float)Math.exp(-n10 / n5) / n6;
                }
                n8 += array[n9];
                ++n9;
            }
        }
        for (int k = 0; k < n2 * n3; ++k) {
            final float[] array2 = array;
            final int n11 = k;
            array2[n11] /= n8;
        }
        return new K(n2, n3, array);
    }
    
    public static K[] C(final int n) {
        final int n2 = n * 2 + 1;
        final float[] array = new float[n2];
        final float n3 = n / 3.0f;
        final float n4 = 2.0f * n3 * n3;
        final float n5 = (float)Math.sqrt(6.2831855f * n3);
        final float n6 = n * n;
        float n7 = 0.0f;
        int n8 = 0;
        for (int i = -n; i <= n; ++i) {
            final float n9 = i * i;
            if (n9 > n6) {
                array[n8] = 0.0f;
            }
            else {
                array[n8] = (float)Math.exp(-n9 / n4) / n5;
            }
            n7 += array[n8];
            ++n8;
        }
        for (int j = 0; j < n2; ++j) {
            final float[] array2 = array;
            final int n10 = j;
            array2[n10] /= n7;
        }
        return new K[] { new K(1, n2, array), new K(n2, 1, array) };
    }
    
    public String toString() {
        return "Blur/Gaussian Blur...";
    }
}
