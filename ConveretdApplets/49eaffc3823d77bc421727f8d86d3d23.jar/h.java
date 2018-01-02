// 
// Decompiled by Procyon v0.5.30
// 

public class h extends f
{
    public String getName() {
        return "ZoomIn Plugin";
    }
    
    public void transform(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4, final int[] array3) {
        final int n5 = n3 * 256 / (n4 - 1);
        final int n6 = (4 * n - n3 * 3 * n / (n4 - 1)) / 4;
        final int n7 = (4 * n2 - n3 * 3 * n2 / (n4 - 1)) / 4;
        final int n8 = (n - n6) / 2;
        final int n9 = (n2 - n7) / 2;
        for (int i = 0; i < n2; ++i) {
            final int n10 = i * n7 / n2 + n9;
            for (int j = 0; j < n; ++j) {
                f.a(array3, array, array2, n, j, i, j, i, j * n6 / n + n8, n10, n5);
            }
        }
    }
}
