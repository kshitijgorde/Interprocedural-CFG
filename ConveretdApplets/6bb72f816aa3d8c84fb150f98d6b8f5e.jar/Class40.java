// 
// Decompiled by Procyon v0.5.30
// 

final class Class40
{
    final int[] anIntArray673;
    final int[] anIntArray674;
    final int[] anIntArray675;
    final int[] anIntArray676;
    final int[] anIntArray677;
    final int[] anIntArray678;
    final int[] anIntArray679;
    final int[] anIntArray680;
    final int[] anIntArray681;
    int[] anIntArray682;
    final boolean aBoolean683;
    final int anInt684;
    final int anInt685;
    final int anInt686;
    final int anInt687;
    static final int[] anIntArray688;
    static final int[] anIntArray689;
    static final int[] anIntArray690;
    static final int[] anIntArray691;
    static final int[] anIntArray692;
    static final int[] anIntArray693;
    static final int[] anIntArray694;
    static final int[] anIntArray695;
    private static final int[][] anIntArrayArray696;
    private static final int[][] anIntArrayArray697;
    
    public Class40(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int anInt685, final int n7, final int anInt686, final int n8, final int n9, final int n10, final int n11, final int anInt687, final int n12, final int n13, final int n14, final int n15, final int anInt688) {
        this.aBoolean683 = (n11 == n10 && n11 == n4 && n11 == n9);
        this.anInt684 = anInt687;
        this.anInt685 = anInt685;
        this.anInt686 = anInt686;
        this.anInt687 = anInt688;
        final int n16 = 128;
        final int n17 = n16 / 2;
        final int n18 = n16 / 4;
        final int n19 = n16 * 3 / 4;
        final int[] array = Class40.anIntArrayArray696[anInt687];
        final int length = array.length;
        this.anIntArray673 = new int[length];
        this.anIntArray674 = new int[length];
        this.anIntArray675 = new int[length];
        final int[] array2 = new int[length];
        final int[] array3 = new int[length];
        final int n20 = n15 * n16;
        final int n21 = n * n16;
        for (int i = 0; i < length; ++i) {
            int n22 = array[i];
            if ((n22 & 0x1) == 0x0 && n22 <= 8) {
                n22 = (n22 - anInt685 - anInt685 - 1 & 0x7) + 1;
            }
            if (n22 > 8 && n22 <= 12) {
                n22 = (n22 - 9 - anInt685 & 0x3) + 9;
            }
            if (n22 > 12 && n22 <= 16) {
                n22 = (n22 - 13 - anInt685 & 0x3) + 13;
            }
            int n23;
            int n24;
            int n25;
            int n26;
            int n27;
            if (n22 == 1) {
                n23 = n20;
                n24 = n21;
                n25 = n11;
                n26 = n7;
                n27 = n2;
            }
            else if (n22 == 2) {
                n23 = n20 + n17;
                n24 = n21;
                n25 = n11 + n10 >> 1;
                n26 = n7 + n14 >> 1;
                n27 = n2 + n13 >> 1;
            }
            else if (n22 == 3) {
                n23 = n20 + n16;
                n24 = n21;
                n25 = n10;
                n26 = n14;
                n27 = n13;
            }
            else if (n22 == 4) {
                n23 = n20 + n16;
                n24 = n21 + n17;
                n25 = n10 + n4 >> 1;
                n26 = n14 + n8 >> 1;
                n27 = n13 + n6 >> 1;
            }
            else if (n22 == 5) {
                n23 = n20 + n16;
                n24 = n21 + n16;
                n25 = n4;
                n26 = n8;
                n27 = n6;
            }
            else if (n22 == 6) {
                n23 = n20 + n17;
                n24 = n21 + n16;
                n25 = n4 + n9 >> 1;
                n26 = n8 + n3 >> 1;
                n27 = n6 + n12 >> 1;
            }
            else if (n22 == 7) {
                n23 = n20;
                n24 = n21 + n16;
                n25 = n9;
                n26 = n3;
                n27 = n12;
            }
            else if (n22 == 8) {
                n23 = n20;
                n24 = n21 + n17;
                n25 = n9 + n11 >> 1;
                n26 = n3 + n7 >> 1;
                n27 = n12 + n2 >> 1;
            }
            else if (n22 == 9) {
                n23 = n20 + n17;
                n24 = n21 + n18;
                n25 = n11 + n10 >> 1;
                n26 = n7 + n14 >> 1;
                n27 = n2 + n13 >> 1;
            }
            else if (n22 == 10) {
                n23 = n20 + n19;
                n24 = n21 + n17;
                n25 = n10 + n4 >> 1;
                n26 = n14 + n8 >> 1;
                n27 = n13 + n6 >> 1;
            }
            else if (n22 == 11) {
                n23 = n20 + n17;
                n24 = n21 + n19;
                n25 = n4 + n9 >> 1;
                n26 = n8 + n3 >> 1;
                n27 = n6 + n12 >> 1;
            }
            else if (n22 == 12) {
                n23 = n20 + n18;
                n24 = n21 + n17;
                n25 = n9 + n11 >> 1;
                n26 = n3 + n7 >> 1;
                n27 = n12 + n2 >> 1;
            }
            else if (n22 == 13) {
                n23 = n20 + n18;
                n24 = n21 + n18;
                n25 = n11;
                n26 = n7;
                n27 = n2;
            }
            else if (n22 == 14) {
                n23 = n20 + n19;
                n24 = n21 + n18;
                n25 = n10;
                n26 = n14;
                n27 = n13;
            }
            else if (n22 == 15) {
                n23 = n20 + n19;
                n24 = n21 + n19;
                n25 = n4;
                n26 = n8;
                n27 = n6;
            }
            else {
                n23 = n20 + n18;
                n24 = n21 + n19;
                n25 = n9;
                n26 = n3;
                n27 = n12;
            }
            this.anIntArray673[i] = n23;
            this.anIntArray674[i] = n25;
            this.anIntArray675[i] = n24;
            array2[i] = n26;
            array3[i] = n27;
        }
        final int[] array4 = Class40.anIntArrayArray697[anInt687];
        final int n28 = array4.length / 4;
        this.anIntArray679 = new int[n28];
        this.anIntArray680 = new int[n28];
        this.anIntArray681 = new int[n28];
        this.anIntArray676 = new int[n28];
        this.anIntArray677 = new int[n28];
        this.anIntArray678 = new int[n28];
        if (n5 != -1) {
            this.anIntArray682 = new int[n28];
        }
        int n29 = 0;
        for (int j = 0; j < n28; ++j) {
            final int n30 = array4[n29];
            int n31 = array4[n29 + 1];
            int n32 = array4[n29 + 2];
            int n33 = array4[n29 + 3];
            n29 += 4;
            if (n31 < 4) {
                n31 = (n31 - anInt685 & 0x3);
            }
            if (n32 < 4) {
                n32 = (n32 - anInt685 & 0x3);
            }
            if (n33 < 4) {
                n33 = (n33 - anInt685 & 0x3);
            }
            this.anIntArray679[j] = n31;
            this.anIntArray680[j] = n32;
            this.anIntArray681[j] = n33;
            if (n30 == 0) {
                this.anIntArray676[j] = array2[n31];
                this.anIntArray677[j] = array2[n32];
                this.anIntArray678[j] = array2[n33];
                if (this.anIntArray682 != null) {
                    this.anIntArray682[j] = -1;
                }
            }
            else {
                this.anIntArray676[j] = array3[n31];
                this.anIntArray677[j] = array3[n32];
                this.anIntArray678[j] = array3[n33];
                if (this.anIntArray682 != null) {
                    this.anIntArray682[j] = n5;
                }
            }
        }
        int n34 = n11;
        int n35;
        if ((n35 = n10) < n34) {
            n34 = n10;
        }
        if (n10 > n35) {
            n35 = n10;
        }
        if (n4 < n34) {
            n34 = n4;
        }
        if (n4 > n35) {
            n35 = n4;
        }
        if (n9 < n34) {
            n34 = n9;
        }
        if (n9 > n35) {
            n35 = n9;
        }
    }
    
    static {
        anIntArray688 = new int[6];
        anIntArray689 = new int[6];
        anIntArray690 = new int[6];
        anIntArray691 = new int[6];
        anIntArray692 = new int[6];
        anIntArray693 = new int[] { 1, 0 };
        anIntArray694 = new int[] { 2, 1 };
        anIntArray695 = new int[] { 3, 3 };
        anIntArrayArray696 = new int[][] { { 1, 3, 5, 7 }, { 1, 3, 5, 7 }, { 1, 3, 5, 7 }, { 1, 3, 5, 7, 6 }, { 1, 3, 5, 7, 6 }, { 1, 3, 5, 7, 6 }, { 1, 3, 5, 7, 6 }, { 1, 3, 5, 7, 2, 6 }, { 1, 3, 5, 7, 2, 8 }, { 1, 3, 5, 7, 2, 8 }, { 1, 3, 5, 7, 11, 12 }, { 1, 3, 5, 7, 11, 12 }, { 1, 3, 5, 7, 13, 14 } };
        anIntArrayArray697 = new int[][] { { 0, 1, 2, 3, 0, 0, 1, 3 }, { 1, 1, 2, 3, 1, 0, 1, 3 }, { 0, 1, 2, 3, 1, 0, 1, 3 }, { 0, 0, 1, 2, 0, 0, 2, 4, 1, 0, 4, 3 }, { 0, 0, 1, 4, 0, 0, 4, 3, 1, 1, 2, 4 }, { 0, 0, 4, 3, 1, 0, 1, 2, 1, 0, 2, 4 }, { 0, 1, 2, 4, 1, 0, 1, 4, 1, 0, 4, 3 }, { 0, 4, 1, 2, 0, 4, 2, 5, 1, 0, 4, 5, 1, 0, 5, 3 }, { 0, 4, 1, 2, 0, 4, 2, 3, 0, 4, 3, 5, 1, 0, 4, 5 }, { 0, 0, 4, 5, 1, 4, 1, 2, 1, 4, 2, 3, 1, 4, 3, 5 }, { 0, 0, 1, 5, 0, 1, 4, 5, 0, 1, 2, 4, 1, 0, 5, 3, 1, 5, 4, 3, 1, 4, 2, 3 }, { 1, 0, 1, 5, 1, 1, 4, 5, 1, 1, 2, 4, 0, 0, 5, 3, 0, 5, 4, 3, 0, 4, 2, 3 }, { 1, 0, 5, 4, 1, 0, 1, 5, 0, 0, 4, 3, 0, 4, 5, 3, 0, 5, 2, 3, 0, 1, 2, 5 } };
    }
}
