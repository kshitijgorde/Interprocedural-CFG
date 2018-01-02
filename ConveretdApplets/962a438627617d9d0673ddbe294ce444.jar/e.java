// 
// Decompiled by Procyon v0.5.30
// 

public class e extends f
{
    public String getName() {
        return "RotateIn Plugin";
    }
    
    public void transform(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4, final int[] array3) {
        for (int i = 0; i < n * n2; ++i) {
            array3[i] = array2[i];
        }
        final int n5 = n3 * n / (n4 - 1);
        final int n6 = (n - n5) / 2;
        final int n7 = n2 * (n6 + n5) / n;
        final int n8 = n2 - n7;
        for (int j = 0; j < n2; ++j) {
            final int n9 = j * n;
            for (int k = n6; k < n6 + n5; ++k) {
                final int n10 = (n5 - (k - n6)) * n8 / n5 + n7;
                final int n11 = (j - (n2 - n10) / 2) * n2 / n10;
                if (n11 >= 0) {
                    if (n11 < n2) {
                        array3[n9 + k] = array[n11 * n + (k - n6) * n / n5];
                    }
                }
            }
        }
    }
}
