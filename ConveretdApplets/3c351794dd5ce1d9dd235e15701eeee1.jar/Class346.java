// 
// Decompiled by Procyon v0.5.30
// 

final class Class346
{
    private int anInt2892;
    static int[][] anIntArrayArray2893;
    private Class332 aClass332_2894;
    private boolean aBoolean2895;
    private int anInt2896;
    private int anInt2897;
    private int anInt2898;
    private Class175[] aClass175Array2899;
    static float aFloat2900;
    private Class175 aClass175_2901;
    private int anInt2902;
    private Class175[] aClass175Array2903;
    private int anInt2904;
    private int anInt2905;
    
    final void method3827(final byte b) {
        try {
            if (b == -87) {
                if (this.aClass175Array2903 != null) {
                    for (int i = 0; i < this.aClass175Array2903.length; ++i) {
                        this.aClass175Array2903[i].method2570();
                    }
                }
                this.aClass332_2894 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vaa.C(" + b + ')');
        }
    }
    
    final boolean method3828(final int anInt2896, final int n, final ha ha) {
        try {
            if (~anInt2896 != ~this.anInt2896) {
                this.anInt2896 = anInt2896;
                int method282 = Class23.method282(31, anInt2896);
                if (method282 > 512) {
                    method282 = 512;
                }
                if (~method282 >= -1) {
                    method282 = 1;
                }
                if (~this.anInt2897 != ~method282) {
                    this.anInt2897 = method282;
                    this.aClass332_2894 = null;
                }
                if (this.aClass175Array2903 != null) {
                    this.anInt2904 = 0;
                    final int[] array = new int[this.aClass175Array2903.length];
                    for (int n2 = 0; this.aClass175Array2903.length > n2; ++n2) {
                        final Class175 class175 = this.aClass175Array2903[n2];
                        if (class175.method2575(this.anInt2898, this.anInt2892, this.anInt2905, this.anInt2896)) {
                            array[this.anInt2904] = class175.anInt1361;
                            this.aClass175Array2899[this.anInt2904++] = class175;
                        }
                    }
                    Class33.method323(array, this.aClass175Array2899, 0, -1 + this.anInt2904, 0);
                }
                this.aBoolean2895 = true;
            }
            if (n != 6) {
                this.method3828(-20, 36, null);
            }
            boolean b = false;
            if (this.aBoolean2895) {
                this.aBoolean2895 = false;
                for (int n3 = this.anInt2904 - 1; ~n3 <= -1; --n3) {
                    final boolean method283 = this.aClass175Array2899[n3].method2577(ha, this.aClass175_2901);
                    b |= method283;
                    this.aBoolean2895 |= !method283;
                }
            }
            return b;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vaa.D(" + anInt2896 + ',' + n + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final char method3829(final char c, final int n) {
        try {
            if (n != 0) {
                return '\uff8e';
            }
            if (c != ' ' && c != ' ' && ~c != 0xFFFFFFA0 && ~c != 0xFFFFFFD2) {
                if (c == '[' || ~c == 0xFFFFFFA2 || c == '#') {
                    return c;
                }
                if (c == '\u00e0' || ~c == 0xFFFFFF1E || ~c == 0xFFFFFF1D || c == '\u00e4' || c == '\u00e3' || ~c == 0xFFFFFF3F || ~c == 0xFFFFFF3E || ~c == 0xFFFFFF3D || c == '\u00c4' || c == '\u00c3') {
                    return 'a';
                }
                if (~c == 0xFFFFFF17 || c == '\u00e9' || ~c == 0xFFFFFF15 || c == '\u00eb' || ~c == 0xFFFFFF37 || c == '\u00c9' || c == '\u00ca' || c == '\u00cb') {
                    return 'e';
                }
                if (c == '\u00ed' || ~c == 0xFFFFFF11 || ~c == 0xFFFFFF10 || c == '\u00cd' || ~c == 0xFFFFFF31 || c == '\u00cf') {
                    return 'i';
                }
                if (~c == 0xFFFFFF0D || c == '\u00f3' || ~c == 0xFFFFFF0B || c == '\u00f6' || ~c == 0xFFFFFF0A || ~c == 0xFFFFFF2D || ~c == 0xFFFFFF2C || ~c == 0xFFFFFF2B || c == '\u00d6' || c == '\u00d5') {
                    return 'o';
                }
                if (c == '\u00f9' || ~c == 0xFFFFFF05 || ~c == 0xFFFFFF04 || ~c == 0xFFFFFF03 || ~c == 0xFFFFFF26 || ~c == 0xFFFFFF25 || c == '\u00db' || c == '\u00dc') {
                    return 'u';
                }
                if (~c == 0xFFFFFF18 || ~c == 0xFFFFFF38) {
                    return 'c';
                }
                if (c == '\u00ff' || ~c == 0xFFFFFE87) {
                    return 'y';
                }
                if (c == '\u00f1' || ~c == 0xFFFFFF2E) {
                    return 'n';
                }
                if (~c != 0xFFFFFF20) {
                    return Character.toLowerCase(c);
                }
                if (!client.aBoolean3553) {
                    return 'b';
                }
            }
            return '_';
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vaa.B(" + c + ',' + n + ')');
        }
    }
    
    public static void method3830(final int n) {
        try {
            Class346.anIntArrayArray2893 = null;
            if (n != -211) {
                Class346.aFloat2900 = -0.43294057f;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vaa.E(" + n + ')');
        }
    }
    
    final void method3831(final int n, final int n2, int n3, final int n4, final int n5, final int n6, final ha ha, final int i, final int n7, final int n8) {
        try {
            n3 = (0x3FFF & n + n3);
            if (~this.anInt2902 != 0x0 && this.anInt2897 != 0) {
                final Class238 method11 = Class98_Sub10_Sub8.aD5578.method11(this.anInt2902, n5 ^ 0x2F2F);
                if (this.aClass332_2894 == null && Class98_Sub10_Sub8.aD5578.method8(115, this.anInt2902)) {
                    this.aClass332_2894 = ha.method1748(-7962, 0, this.anInt2897, this.anInt2897, (~method11.anInt1818 != 0xFFFFFFFD) ? Class98_Sub10_Sub8.aD5578.method9(this.anInt2902, (byte)(-112), this.anInt2897, 0.7f, false, this.anInt2897) : Class98_Sub10_Sub8.aD5578.method13(111, this.anInt2897, this.anInt2902, 0.7f, false, this.anInt2897), this.anInt2897);
                }
                if (~method11.anInt1818 == 0xFFFFFFFD) {
                    ha.aa(n6, n4, n7, i, n8, 0);
                }
                if (this.aClass332_2894 != null) {
                    final int n9 = (~method11.anInt1818 == 0xFFFFFFFD) ? 1 : 0;
                    int n10 = n2 * i / -4096;
                    int j;
                    for (j = i * n3 / 4096 + (n7 - i) / 2; ~j < ~i; j -= i) {}
                    while (j < 0) {
                        j += i;
                    }
                    while (i < n10) {
                        n10 -= i;
                    }
                    while (~n10 > -1) {
                        n10 += i;
                    }
                    for (int n11 = j + -i; ~n7 < ~n11; n11 += i) {
                        for (int k = n10 - i; k < i; k += i) {
                            this.aClass332_2894.method3727(n11 - -n6, k - -n4, i, i, 1, 0, n9);
                        }
                    }
                }
            }
            else {
                ha.aa(n6, n4, n7, i, n8, 0);
            }
            if (n5 != -24446) {
                this.method3831(-115, -58, 66, 67, 6, -61, null, -35, -42, 87);
            }
            for (int n12 = this.anInt2904 - 1; ~n12 <= -1; --n12) {
                this.aClass175Array2899[n12].method2573(ha, n6, n4, n7, i, n2, n3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vaa.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + ((ha != null) ? "{...}" : "null") + ',' + i + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    Class346(final int anInt2902, final Class175[] aClass175Array2903, final int n, final int anInt2903, final int anInt2904, final int anInt2905) {
        this.anInt2896 = -1;
        this.aBoolean2895 = true;
        try {
            this.anInt2905 = anInt2905;
            this.aClass175Array2903 = aClass175Array2903;
            this.anInt2902 = anInt2902;
            this.anInt2898 = anInt2903;
            this.anInt2892 = anInt2904;
            if (aClass175Array2903 == null) {
                this.aClass175Array2899 = null;
                this.aClass175_2901 = null;
            }
            else {
                this.aClass175Array2899 = new Class175[aClass175Array2903.length];
                this.aClass175_2901 = ((~n <= -1) ? aClass175Array2903[n] : null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vaa.<init>(" + anInt2902 + ',' + ((aClass175Array2903 != null) ? "{...}" : "null") + ',' + n + ',' + anInt2903 + ',' + anInt2904 + ',' + anInt2905 + ')');
        }
    }
    
    static {
        Class346.anIntArrayArray2893 = new int[][] { { 2, 4, 6, 0 }, { 0, 2, 3, 5, 6, 4 }, { 0, 1, 4, 5 }, { 4, 6, 0, 2 }, { 2, 4, 0 }, { 0, 2, 4 }, { 6, 0, 1, 2, 4, 5 }, { 0, 1, 2, 4, 6, 7 }, { 4, 7, 6, 0 }, { 0, 8, 6, 1, 9, 2, 9, 4 }, { 2, 9, 4, 0, 8, 6 }, { 2, 11, 3, 7, 10, 10, 6, 6 }, { 2, 4, 6, 0 } };
    }
}
