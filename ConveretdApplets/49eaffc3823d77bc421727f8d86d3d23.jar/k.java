// 
// Decompiled by Procyon v0.5.30
// 

public class k extends f
{
    public String getName() {
        return "Expansion Morph Plugin";
    }
    
    public void transform(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4, final int[] array3) {
        final int n5 = 20;
        final int n6 = n3 * (n - 2 * n5) / (n4 - 1) + n5;
        final int n7 = n3 * 256 / (n4 - 1);
        for (int i = 0; i < n; ++i) {
            final int n8 = i;
            final int n9 = i - n;
            final int n10 = i - n6;
            final double pow = Math.pow(1.0 / (1.0E-4 + Math.abs(n8)), 0.5);
            final double pow2 = Math.pow(1.0 / (1.0E-4 + Math.abs(n9)), 0.5);
            final double pow3 = Math.pow(1.0 / (1.0E-4 + Math.abs(n10)), 0.5);
            int n11 = i - (int)((n6 - n5) * pow3 / (pow + pow2 + pow3));
            int n12 = i + (int)((n - n6 - n5) * pow3 / (pow + pow2 + pow3));
            if (n11 < 0) {
                n11 = 0;
            }
            if (n11 >= n) {
                n11 = n - 1;
            }
            if (n12 < 0) {
                n12 = 0;
            }
            if (n12 >= n) {
                n12 = n - 1;
            }
            for (int j = 0; j < n2; ++j) {
                f.a(array3, array, array2, n, i, j, n12, j, n11, j, n7);
            }
        }
    }
}
