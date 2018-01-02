// 
// Decompiled by Procyon v0.5.30
// 

public class ap extends an
{
    public int a(final v v, final Object o, final float[][] array, final int[] array2, final int n) {
        int n2;
        for (n2 = 0; n2 < n && array2[n2] == 0; ++n2) {}
        if (n2 == n) {
            return 0;
        }
        final a1 a1 = (a1)o;
        final aw a2 = a1.a;
        final int c = a2.c;
        final int a3 = a1.f.a;
        final int n3 = (a2.b - a2.a) / c;
        final int[][] array3 = new int[(n3 + a3 - 1) / a3][];
        for (int i = 0; i < a1.d; ++i) {
            int j = 0;
            int n4 = 0;
            while (j < n3) {
                if (i == 0) {
                    final int a4 = a1.f.a(v.b);
                    if (a4 == -1) {
                        return 0;
                    }
                    array3[n4] = a1.i[a4];
                    if (array3[n4] == null) {
                        return 0;
                    }
                }
                for (int n5 = 0; n5 < a3 && j < n3; ++n5, ++j) {
                    final int n6 = a2.a + j * c;
                    if ((a2.f[array3[n4][n5]] & 1 << i) != 0x0) {
                        final ah ah = a1.e[a1.g[array3[n4][n5]][i]];
                        if (ah != null && ah.a(array, n6, n, v.b, c) == -1) {
                            return 0;
                        }
                    }
                }
                ++n4;
            }
        }
        return 0;
    }
}
