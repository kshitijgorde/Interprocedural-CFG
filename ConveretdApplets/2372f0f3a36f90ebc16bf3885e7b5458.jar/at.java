// 
// Decompiled by Procyon v0.5.30
// 

public class at
{
    public static void a(final float[] array, final int[] array2, final int n, final int n2, final float[] array3, final int n3, final float n4, final float n5) {
        final float n6 = 3.1415927f / n2;
        for (int i = 0; i < n3; ++i) {
            array3[i] = au.a(array3[i]);
        }
        final int n7 = n3 / 2 * 2;
        int j = 0;
        while (j < n) {
            final int n8 = array2[j];
            float n9 = 0.70710677f;
            float n10 = 0.70710677f;
            final float a = au.a(n6 * n8);
            for (int k = 0; k < n7; k += 2) {
                n10 *= array3[k] - a;
                n9 *= array3[k + 1] - a;
            }
            float n12;
            float n13;
            if ((n3 & 0x1) != 0x0) {
                final float n11 = n10 * (array3[n3 - 1] - a);
                n12 = n11 * n11;
                n13 = n9 * (n9 * (1.0f - a * a));
            }
            else {
                n12 = n10 * (n10 * (1.0f + a));
                n13 = n9 * (n9 * (1.0f - a));
            }
            float intBitsToFloat = n13 + n12;
            int n14 = Float.floatToIntBits(intBitsToFloat);
            int n15 = Integer.MAX_VALUE & n14;
            int n16 = 0;
            if (n15 < 2139095040) {
                if (n15 != 0) {
                    if (n15 < 8388608) {
                        n14 = Float.floatToIntBits((float)(intBitsToFloat * 3.3554432E7));
                        n15 = (Integer.MAX_VALUE & n14);
                        n16 = -25;
                    }
                    n16 += (n15 >>> 23) - 126;
                    intBitsToFloat = Float.intBitsToFloat((n14 & 0x807FFFFF) | 0x3F000000);
                }
            }
            final float c = au.c(n4 * au.b(intBitsToFloat) * au.a(n16 + n3) - n5);
            do {
                final int n17 = j++;
                array[n17] *= c;
            } while (j < n && array2[j] == n8);
        }
    }
}
