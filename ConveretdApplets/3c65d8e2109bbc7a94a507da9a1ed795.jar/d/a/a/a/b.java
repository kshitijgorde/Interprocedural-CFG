// 
// Decompiled by Procyon v0.5.30
// 

package d.a.a.a;

public class b
{
    public void a(final short[] array, final int n, final short[] array2, final short[] array3, final short[] array4, final int n2, final short[] array5, final short[] array6, final int n3) {
        this.a(array, n, array3, n2, array6, array5, n3);
        a(array6[n3], array5[n3], array3, array, n, array4, array2, n2);
    }
    
    private void a(final short[] array, final int n, final short[] array2, final int n2, final short[] array3, final short[] array4, final int n3) throws IllegalArgumentException {
        final short[] array5 = new short[40];
        int n4 = 0;
        for (int i = 0; i <= 39; ++i) {
            final short a = j.a(array[i + n]);
            if (a > n4) {
                n4 = a;
            }
        }
        int if1 = 0;
        if (n4 != 0) {
            if (n4 <= 0) {
                throw new IllegalArgumentException("Calculation_of_the_LTP_parameters: dmax = " + n4 + " should be > 0.");
            }
            if1 = j.if(n4 << 16);
        }
        int n5;
        if (if1 > 6) {
            n5 = 0;
        }
        else {
            n5 = (short)(6 - if1);
        }
        if (n5 < 0) {
            throw new IllegalArgumentException("Calculation_of_the_LTP_parameters: scal = " + n5 + " should be >= 0.");
        }
        for (int j = 0; j <= 39; ++j) {
            array5[j] = j.if((int)array[j + n], n5);
        }
        int n6 = 0;
        short n7 = 40;
        for (int k = 40; k <= 120; ++k) {
            int n8 = 1;
            final int n9 = this.a(0, array5, array2, n2 - k) + this.a(1, array5, array2, n8 + n2 - k);
            ++n8;
            final int n10 = n9 + this.a(2, array5, array2, n8 + n2 - k);
            ++n8;
            final int n11 = n10 + this.a(3, array5, array2, n8 + n2 - k);
            ++n8;
            final int n12 = n11 + this.a(4, array5, array2, n8 + n2 - k);
            ++n8;
            final int n13 = n12 + this.a(5, array5, array2, n8 + n2 - k);
            ++n8;
            final int n14 = n13 + this.a(6, array5, array2, n8 + n2 - k);
            ++n8;
            final int n15 = n14 + this.a(7, array5, array2, n8 + n2 - k);
            ++n8;
            final int n16 = n15 + this.a(8, array5, array2, n8 + n2 - k);
            ++n8;
            final int n17 = n16 + this.a(9, array5, array2, n8 + n2 - k);
            ++n8;
            final int n18 = n17 + this.a(10, array5, array2, n8 + n2 - k);
            ++n8;
            final int n19 = n18 + this.a(11, array5, array2, n8 + n2 - k);
            ++n8;
            final int n20 = n19 + this.a(12, array5, array2, n8 + n2 - k);
            ++n8;
            final int n21 = n20 + this.a(13, array5, array2, n8 + n2 - k);
            ++n8;
            final int n22 = n21 + this.a(14, array5, array2, n8 + n2 - k);
            ++n8;
            final int n23 = n22 + this.a(15, array5, array2, n8 + n2 - k);
            ++n8;
            final int n24 = n23 + this.a(16, array5, array2, n8 + n2 - k);
            ++n8;
            final int n25 = n24 + this.a(17, array5, array2, n8 + n2 - k);
            ++n8;
            final int n26 = n25 + this.a(18, array5, array2, n8 + n2 - k);
            ++n8;
            final int n27 = n26 + this.a(19, array5, array2, n8 + n2 - k);
            ++n8;
            final int n28 = n27 + this.a(20, array5, array2, n8 + n2 - k);
            ++n8;
            final int n29 = n28 + this.a(21, array5, array2, n8 + n2 - k);
            ++n8;
            final int n30 = n29 + this.a(22, array5, array2, n8 + n2 - k);
            ++n8;
            final int n31 = n30 + this.a(23, array5, array2, n8 + n2 - k);
            ++n8;
            final int n32 = n31 + this.a(24, array5, array2, n8 + n2 - k);
            ++n8;
            final int n33 = n32 + this.a(25, array5, array2, n8 + n2 - k);
            ++n8;
            final int n34 = n33 + this.a(26, array5, array2, n8 + n2 - k);
            ++n8;
            final int n35 = n34 + this.a(27, array5, array2, n8 + n2 - k);
            ++n8;
            final int n36 = n35 + this.a(28, array5, array2, n8 + n2 - k);
            ++n8;
            final int n37 = n36 + this.a(29, array5, array2, n8 + n2 - k);
            ++n8;
            final int n38 = n37 + this.a(30, array5, array2, n8 + n2 - k);
            ++n8;
            final int n39 = n38 + this.a(31, array5, array2, n8 + n2 - k);
            ++n8;
            final int n40 = n39 + this.a(32, array5, array2, n8 + n2 - k);
            ++n8;
            final int n41 = n40 + this.a(33, array5, array2, n8 + n2 - k);
            ++n8;
            final int n42 = n41 + this.a(34, array5, array2, n8 + n2 - k);
            ++n8;
            final int n43 = n42 + this.a(35, array5, array2, n8 + n2 - k);
            ++n8;
            final int n44 = n43 + this.a(36, array5, array2, n8 + n2 - k);
            ++n8;
            final int n45 = n44 + this.a(37, array5, array2, n8 + n2 - k);
            ++n8;
            final int n46 = n45 + this.a(38, array5, array2, n8 + n2 - k);
            ++n8;
            final int n47 = n46 + this.a(39, array5, array2, n8 + n2 - k);
            ++n8;
            if (n47 > n6) {
                n7 = (short)k;
                n6 = n47;
            }
        }
        array4[n3] = n7;
        final int n48 = n6 << 1;
        if (n5 > 100 || n5 < -100) {
            throw new IllegalArgumentException("Calculation_of_the_LTP_parameters: scal = " + n5 + " should be >= -100 and <= 100.");
        }
        final int n49 = n48 >> 6 - n5;
        if (n7 > 120 || n7 < 40) {
            throw new IllegalArgumentException("Calculation_of_the_LTP_parameters: Nc = " + n7 + " should be >= 40 and <= 120.");
        }
        int n50 = 0;
        for (short n51 = 0; n51 <= 39; ++n51) {
            final short if2 = j.if((int)array2[n51 - n7 + n2], 3);
            n50 += if2 * if2;
        }
        final int n52 = n50 << 1;
        if (n49 <= 0) {
            array3[n3] = 0;
            return;
        }
        if (n49 >= n52) {
            array3[n3] = 3;
            return;
        }
        final short if3 = j.if(n52);
        final short if4 = j.if(n49 << if3, 16);
        final short if5 = j.if(n52 << if3, 16);
        for (int n53 = 0; n53 <= 2 && if4 > j.for(if5, i.new[n53]); ++n53) {
            array3[n3] = (short)n53;
        }
    }
    
    private int a(final int n, final short[] array, final short[] array2, final int n2) {
        return array[n] * array2[n2];
    }
    
    static void a(final short n, final short n2, final short[] array, final short[] array2, final int n3, final short[] array3, final short[] array4, final int n4) {
        switch (n) {
            case 0: {
                final short n5 = 3277;
                for (short n6 = 0; n6 <= 39; ++n6) {
                    array3[n6 + n4] = j.do(n5, array[n6 - n2 + n4]);
                    array4[n6 + 5] = j.if(array2[n6 + n3], array3[n6 + n4]);
                }
                break;
            }
            case 1: {
                final short n7 = 11469;
                for (short n8 = 0; n8 <= 39; ++n8) {
                    array3[n8 + n4] = j.do(n7, array[n8 - n2 + n4]);
                    array4[n8 + 5] = j.if(array2[n8 + n3], array3[n8 + n4]);
                }
                break;
            }
            case 2: {
                final short n9 = 21299;
                for (short n10 = 0; n10 <= 39; ++n10) {
                    array3[n10 + n4] = j.do(n9, array[n10 - n2 + n4]);
                    array4[n10 + 5] = j.if(array2[n10 + n3], array3[n10 + n4]);
                }
                break;
            }
            case 3: {
                final short n11 = 32767;
                for (short n12 = 0; n12 <= 39; ++n12) {
                    array3[n12 + n4] = j.do(n11, array[n12 - n2 + n4]);
                    array4[n12 + 5] = j.if(array2[n12 + n3], array3[n12 + n4]);
                }
                break;
            }
        }
    }
    
    public void a(final a a, final short n, final short n2, final short[] array, final int n3) throws IllegalArgumentException {
        final short[] else1 = a.else();
        final short n4 = (n < 40 || n > 120) ? a.case() : n;
        a.if(n4);
        if (n4 < 40 || n4 > 120) {
            throw new IllegalArgumentException("Gsm_Long_Term_Synthesis_Filtering Nr = " + n4 + " is out of range. Should be >= 40 and <= 120");
        }
        final short n5 = i.try[n2];
        if (n5 == -32768) {
            throw new IllegalArgumentException("Gsm_Long_Term_Synthesis_Filtering brp = " + n5 + " is out of range. Should be = " + -32768);
        }
        for (short n6 = 0; n6 <= 39; ++n6) {
            else1[n6 + n3] = j.new(array[n6], j.do(n5, else1[n6 - n4 + n3]));
        }
        System.arraycopy(else1, n3 - 80, else1, n3 - 120, 120);
        a.a(else1);
    }
}
