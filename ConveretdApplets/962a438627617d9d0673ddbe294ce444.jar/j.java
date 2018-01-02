// 
// Decompiled by Procyon v0.5.30
// 

public class j extends f
{
    protected int byte;
    protected static final int try = 30;
    protected static final int case = 50;
    
    public String getName() {
        return "Image Peel Plugin";
    }
    
    public void a() {
        this.byte = 47;
    }
    
    public void transform(final int[] array, final int[] array2, final int n, final int n2, final int n3, final int n4, final int[] array3) {
        final int n5 = n3 * (((n > n2) ? n : n2) * 2) / (n4 - 1);
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                final int n6 = i * n + j;
                if (j + i <= n5 + this.byte - 30) {
                    array3[n6] = array[n6];
                }
                else if (j + i <= n5 + this.byte) {
                    final int n7 = n5 + this.byte - i - j;
                    final int n8 = (int)(30.0 * Math.asin(n7 / 30.0)) - n7;
                    final int n9 = j - n8;
                    final int n10 = i - n8;
                    final int n11 = j;
                    final int n12 = j;
                    if ((n9 >= 0 && n10 >= n5) || (n9 >= n5 && n10 >= 0)) {
                        array3[i * n + n11] = array2[n10 * n + n12];
                    }
                    else if (n9 < 0 || n10 < 0) {
                        array3[i * n + n11] = array[i * n + n11];
                    }
                    else {
                        f.a(array3, array2, array2, n, n11, i, n9, n10, n - 1 - (n5 - n10), n5 - n9, 50);
                    }
                }
                else if (j > n5 || i > n5) {
                    array3[n6] = array2[n6];
                }
                else {
                    f.a(array3, array2, array2, n, j, i, j, i, n5 - i, n5 - j, 50);
                }
            }
        }
    }
}
