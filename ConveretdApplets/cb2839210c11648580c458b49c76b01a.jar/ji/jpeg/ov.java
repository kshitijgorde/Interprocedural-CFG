// 
// Decompiled by Procyon v0.5.30
// 

package ji.jpeg;

public final class ov
{
    private int[] a;
    
    public ov() {
        this.a = new int[64];
    }
    
    final void a(final int[] array, final int[][] array2) {
        final int[] a = this.a;
        for (int i = 0; i < 8; i += 8) {
            for (int j = 0; j < 8; ++j) {
                final int n = j << 3;
                final int n2 = j * 8 + i;
                for (int k = 0; k < 8; ++k) {
                    a[n + k] = array[n2 + k];
                }
            }
            this.a(0);
            this.a(8);
            this.a(16);
            this.a(24);
            this.a(32);
            this.a(40);
            this.a(48);
            this.a(56);
            this.b(0);
            this.b(1);
            this.b(2);
            this.b(3);
            this.b(4);
            this.b(5);
            this.b(6);
            this.b(7);
            for (int l = 0; l < 8; ++l) {
                final int n3 = l << 3;
                for (int n4 = 0; n4 < 8; ++n4) {
                    final int n5 = (int)(a[n3 + n4] + 0.5 + 128);
                    array2[n4][l] = ((n5 > 255) ? 255 : ((n5 < 0) ? 0 : n5));
                }
            }
        }
    }
    
    private final void a(final int n) {
        final int[] a = this.a;
        final int n2 = (a[0 + n] << 11) + 128;
        final int n3 = a[4 + n] << 11;
        final int n4 = a[6 + n];
        final int n5 = a[2 + n];
        final int n6 = a[1 + n];
        final int n7 = a[7 + n];
        final int n8 = a[5 + n];
        final int n9 = a[3 + n];
        final int n10 = n2 + n3;
        final int n11 = n2 - n3;
        final int n12 = 1108 * (n4 + n5);
        final int n13 = n12 - 3784 * n4;
        final int n14 = n12 + 1568 * n5;
        final int n15 = n10 + n14;
        final int n16 = n10 - n14;
        final int n17 = n11 + n13;
        final int n18 = n11 - n13;
        final int n19 = 565 * (n6 + n7);
        final int n20 = n19 - 3406 * n7;
        final int n21 = n19 + 2276 * n6;
        final int n22 = 2408 * (n9 + n8);
        final int n23 = n22 - 4017 * n9;
        final int n24 = n22 - 799 * n8;
        final int n25 = n21 + n24;
        final int n26 = n21 - n24;
        final int n27 = n20 + n23;
        final int n28 = n20 - n23;
        final int n29 = 181 * (n26 + n28) + 128 >> 8;
        final int n30 = 181 * (n26 - n28) + 128 >> 8;
        a[0 + n] = n15 + n25;
        a[1 + n] = n17 + n29;
        a[2 + n] = n18 + n30;
        a[3 + n] = n16 + n27;
        a[7 + n] = n15 - n25;
        a[6 + n] = n17 - n29;
        a[5 + n] = n18 - n30;
        a[4 + n] = n16 - n27;
        final int[] array = a;
        final int n31 = 0 + n;
        array[n31] >>= 8;
        final int[] array2 = a;
        final int n32 = 1 + n;
        array2[n32] >>= 8;
        final int[] array3 = a;
        final int n33 = 2 + n;
        array3[n33] >>= 8;
        final int[] array4 = a;
        final int n34 = 3 + n;
        array4[n34] >>= 8;
        final int[] array5 = a;
        final int n35 = 4 + n;
        array5[n35] >>= 8;
        final int[] array6 = a;
        final int n36 = 5 + n;
        array6[n36] >>= 8;
        final int[] array7 = a;
        final int n37 = 6 + n;
        array7[n37] >>= 8;
        final int[] array8 = a;
        final int n38 = 7 + n;
        array8[n38] >>= 8;
    }
    
    private final void b(final int n) {
        final int[] a = this.a;
        final int n2 = (a[0 + n] << 8) + 8192;
        final int n3 = a[32 + n] << 8;
        final int n4 = a[48 + n];
        final int n5 = a[16 + n];
        final int n6 = a[8 + n];
        final int n7 = a[56 + n];
        final int n8 = a[40 + n];
        final int n9 = a[24 + n];
        final int n10 = n2 + n3;
        final int n11 = n2 - n3;
        final int n12 = 1108 * (n4 + n5) + 4;
        final int n13 = n12 - 3784 * n4 >> 3;
        final int n14 = n12 + 1568 * n5 >> 3;
        final int n15 = n10 + n14;
        final int n16 = n10 - n14;
        final int n17 = n11 + n13;
        final int n18 = n11 - n13;
        final int n19 = 565 * (n6 + n7) + 4;
        final int n20 = n19 - 3406 * n7 >> 3;
        final int n21 = n19 + 2276 * n6 >> 3;
        final int n22 = 2408 * (n9 + n8) + 4;
        final int n23 = n22 - 4017 * n9 >> 3;
        final int n24 = n22 - 799 * n8 >> 3;
        final int n25 = n21 + n24;
        final int n26 = n21 - n24;
        final int n27 = n20 + n23;
        final int n28 = n20 - n23;
        final int n29 = 181 * (n26 + n28) + 128 >> 8;
        final int n30 = 181 * (n26 - n28) + 128 >> 8;
        a[0 + n] = n15 + n25;
        a[8 + n] = n17 + n29;
        a[16 + n] = n18 + n30;
        a[24 + n] = n16 + n27;
        a[56 + n] = n15 - n25;
        a[48 + n] = n17 - n29;
        a[40 + n] = n18 - n30;
        a[32 + n] = n16 - n27;
        final int[] array = a;
        final int n31 = 0 + n;
        array[n31] >>= 14;
        final int[] array2 = a;
        final int n32 = 8 + n;
        array2[n32] >>= 14;
        final int[] array3 = a;
        final int n33 = 16 + n;
        array3[n33] >>= 14;
        final int[] array4 = a;
        final int n34 = 24 + n;
        array4[n34] >>= 14;
        final int[] array5 = a;
        final int n35 = 32 + n;
        array5[n35] >>= 14;
        final int[] array6 = a;
        final int n36 = 40 + n;
        array6[n36] >>= 14;
        final int[] array7 = a;
        final int n37 = 48 + n;
        array7[n37] >>= 14;
        final int[] array8 = a;
        final int n38 = 56 + n;
        array8[n38] >>= 14;
    }
}
