// 
// Decompiled by Procyon v0.5.30
// 

package Z;

class F
{
    private static int[] cos;
    private static float sin;
    private int n;
    private float[] I;
    private int[] Z;
    
    final void I(final int n) {
        this.n = n;
        this.I = new float[3 * n];
        this.Z = new int[32];
        sin(n, this.I, this.Z);
    }
    
    private static void cos(final int n, final float[] array, final int n2, final int[] array2) {
        int n3 = -1;
        int n4 = n;
        int n5 = 101;
        int n7;
        int n6 = n7 = 0;
    Label_0197:
        while (true) {
            Label_0075: {
                switch (n5) {
                    case 101: {
                        if (++n3 < 4) {
                            n7 = F.cos[n3];
                            break Label_0075;
                        }
                        n7 += 2;
                        break Label_0075;
                    }
                    case 104: {
                        final int n8 = n4 / n7;
                        if (n4 - n7 * n8 != 0) {
                            n5 = 101;
                            continue;
                        }
                        ++n6;
                        array2[n6 + 1] = n7;
                        n4 = n8;
                        if (n7 != 2) {
                            n5 = 107;
                            continue;
                        }
                        if (n6 == 1) {
                            n5 = 107;
                            continue;
                        }
                        for (int i = 1; i < n6; ++i) {
                            final int n9 = n6 - i + 1;
                            array2[n9 + 1] = array2[n9];
                        }
                        array2[2] = 2;
                    }
                    case 107: {
                        if (n4 != 1) {
                            n5 = 104;
                            continue;
                        }
                        break Label_0197;
                    }
                    default: {
                        continue;
                    }
                }
            }
        }
        array2[0] = n;
        array2[1] = n6;
        final float n10 = F.sin / n;
        int n11 = 0;
        final int n12 = n6 - 1;
        int n13 = 1;
        if (n12 == 0) {
            return;
        }
        for (int j = 0; j < n12; ++j) {
            final int n14 = array2[j + 2];
            int n15 = 0;
            final int n16 = n13 * n14;
            final int n17 = n / n16;
            for (int n18 = n14 - 1, k = 0; k < n18; ++k) {
                n15 += n13;
                int n19 = n11;
                final float n20 = n15 * n10;
                float n21 = 0.0f;
                for (int l = 2; l < n17; l += 2) {
                    ++n21;
                    final float n22 = n21 * n20;
                    array[n2 + n19++] = (float)Math.cos(n22);
                    array[n2 + n19++] = (float)Math.sin(n22);
                }
                n11 += n17;
            }
            n13 = n16;
        }
    }
    
    private static void sin(final int n, final float[] array, final int[] array2) {
        if (n == 1) {
            return;
        }
        cos(n, array, n, array2);
    }
    
    static {
        F.cos = new int[] { 4, 2, 3, 5 };
        F.sin = 6.2831855f;
    }
}
