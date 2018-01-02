// 
// Decompiled by Procyon v0.5.30
// 

public class g extends f
{
    public String getName() {
        return "Blinds Plugin";
    }
    
    public void transform(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4, final int[] array3) {
        final int n6;
        final int n5 = n6 = 8;
        final int n7 = n / n5;
        final int n8 = 2 * n7 + (n5 - 1) * (2 * n7) / n6;
        for (int i = 0; i < n2; ++i) {
            final int n9 = i * n;
            for (int j = 0; j < n; ++j) {
                final int n10 = n9 + j;
                final int n11 = j / n7;
                final int n12 = j % n7;
                int n13 = n3 * n8 / (n4 - 1) - (n7 + n11 * 2 * n7 / n6);
                if (n13 < -n7) {
                    n13 = -n7;
                }
                else if (n13 > n7) {
                    n13 = n7;
                }
                int[] array4;
                if (n13 < 0) {
                    array4 = array2;
                    n13 *= -1;
                }
                else {
                    if (n13 <= 0) {
                        array3[n10] = 0;
                        continue;
                    }
                    array4 = array;
                }
                final int n14 = (n12 - (n7 - n13) / 2) * n7 / n13;
                if (n14 < 0 || n14 >= n7) {
                    array3[n10] = 0;
                }
                else {
                    array3[n10] = array4[n9 + (n11 * n7 + n14)];
                }
            }
        }
    }
}
