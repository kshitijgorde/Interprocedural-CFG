// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class aa
{
    public static void a(final float[] array, final int n, final int n2, int n3, final int n4) {
        int n5 = -n2;
        n3 = -n3;
        if (n3 < 0) {
            n3 += 3;
            --n5;
        }
        for (int i = 0; i < n4; ++i) {
            final int n6 = n5++;
            final int n7 = n5;
            final int n8 = n3;
            final int n9 = 3 - n3;
            float n10 = 0.0f;
            for (int j = 0, n11 = 0; j < 10; ++j, n11 += 3) {
                n10 += array[n + n6 - j] * c.char[n8 + n11] + array[n + n7 + j] * c.char[n9 + n11];
            }
            array[n + i] = n10;
        }
    }
}
