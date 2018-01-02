// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub6 extends Class98_Sub10
{
    private int anInt5558;
    static int[] anIntArray5559;
    private int anInt5560;
    private int anInt5561;
    private int anInt5562;
    private int anInt5563;
    private int anInt5564;
    private int anInt5565;
    private int anInt5566;
    private int anInt5567;
    static Class258 aClass258_5568;
    static int anInt5569;
    static int anInt5570;
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (n > -76) {
                this.anInt5565 = 5;
            }
            if (super.aClass223_3859.aBoolean1683) {
                final int[][] method2829 = this.method994(n2, 24431, 0);
                final int[] array = method2829[0];
                final int[] array2 = method2829[1];
                final int[] array3 = method2829[2];
                final int[] array4 = method2828[0];
                final int[] array5 = method2828[1];
                final int[] array6 = method2828[2];
                for (int n3 = 0; ~n3 > ~Class25.anInt268; ++n3) {
                    this.method1019(4096, array[n3], array3[n3], array2[n3]);
                    this.anInt5558 += this.anInt5563;
                    this.anInt5565 += this.anInt5560;
                    this.anInt5564 += this.anInt5561;
                    while (this.anInt5558 < 0) {
                        this.anInt5558 += 4096;
                    }
                    if (~this.anInt5565 > -1) {
                        this.anInt5565 = 0;
                    }
                    while (this.anInt5558 > 4096) {
                        this.anInt5558 -= 4096;
                    }
                    if (~this.anInt5564 > -1) {
                        this.anInt5564 = 0;
                    }
                    if (~this.anInt5565 < -4097) {
                        this.anInt5565 = 4096;
                    }
                    if (~this.anInt5564 < -4097) {
                        this.anInt5564 = 4096;
                    }
                    this.method1021(-1824307956, this.anInt5565, this.anInt5564, this.anInt5558);
                    array4[n3] = this.anInt5562;
                    array5[n3] = this.anInt5567;
                    array6[n3] = this.anInt5566;
                }
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dfa.C(" + n + ',' + n2 + ')');
        }
    }
    
    private final void method1019(final int n, final int n2, final int n3, final int n4) {
        try {
            final int n5 = (~n2 < ~n4) ? n2 : n4;
            if (n == 4096) {
                final int n6 = (~n5 <= ~n3) ? n5 : n3;
                final int n7 = (~n2 <= ~n4) ? n4 : n2;
                final int n8 = (n3 >= n7) ? n7 : n3;
                final int n9 = -n8 + n6;
                this.anInt5564 = (n8 - -n6) / 2;
                if (~this.anInt5564 >= -1 || this.anInt5564 >= 4096) {
                    this.anInt5565 = 0;
                }
                else {
                    this.anInt5565 = (n9 << 624417068) / ((~this.anInt5564 >= -2049) ? (2 * this.anInt5564) : (8192 + -(2 * this.anInt5564)));
                }
                if (~n9 >= -1) {
                    this.anInt5558 = 0;
                }
                else {
                    final int n10 = (n6 - n2 << 132649516) / n9;
                    final int n11 = (n6 + -n4 << -1824307956) / n9;
                    final int n12 = (n6 - n3 << -1453213364) / n9;
                    if (n6 == n2) {
                        this.anInt5558 = ((~n8 == ~n4) ? (n12 + 20480) : (-n11 + 4096));
                    }
                    else if (n6 != n4) {
                        this.anInt5558 = ((n8 != n2) ? (-n10 + 20480) : (12288 + n11));
                    }
                    else {
                        this.anInt5558 = ((n8 != n3) ? (12288 - n12) : (4096 + n10));
                    }
                    this.anInt5558 /= 6;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dfa.D(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    public static void method1020(final int n) {
        try {
            Class98_Sub10_Sub6.anIntArray5559 = null;
            Class98_Sub10_Sub6.aClass258_5568 = null;
            if (n != 2048) {
                method1020(17);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dfa.F(" + n + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (~n != -1) {
                if (~n != 0xFFFFFFFE) {
                    if (~n == 0xFFFFFFFD) {
                        this.anInt5561 = (class98_Sub22.readSignedByte((byte)(-19)) << 311376012) / 100;
                    }
                }
                else {
                    this.anInt5560 = (class98_Sub22.readSignedByte((byte)(-19)) << -132224564) / 100;
                }
            }
            else {
                this.anInt5563 = class98_Sub22.readUShort(false);
            }
            if (b > -92) {
                this.method997(-105, 125);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dfa.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    private final void method1021(final int n, final int n2, final int anInt5562, int n3) {
        try {
            final int n4 = (anInt5562 <= 2048) ? ((4096 - -n2) * anInt5562 >> -1702681940) : (n2 + (anInt5562 - (anInt5562 * n2 >> 1984077964)));
            if (n != -1824307956) {
                this.anInt5558 = -107;
            }
            if (n4 > 0) {
                n3 *= 6;
                final int n5 = anInt5562 + (anInt5562 - n4);
                final int n6 = (n4 - n5 << 1290213260) / n4;
                final int n7 = n3 >> -1706048404;
                final int n8 = (n6 * n4 >> 1703367340) * (-(n7 << 39515372) + n3) >> 1479023980;
                final int anInt5563 = n5 + n8;
                final int anInt5564 = n4 - n8;
                final int n9 = n7;
                if (~n9 != -1) {
                    if (n9 == 1) {
                        this.anInt5562 = anInt5564;
                        this.anInt5566 = n5;
                        this.anInt5567 = n4;
                        return;
                    }
                    if (n9 == 2) {
                        this.anInt5566 = anInt5563;
                        this.anInt5567 = n4;
                        this.anInt5562 = n5;
                        return;
                    }
                    if (~n9 == 0xFFFFFFFC) {
                        this.anInt5566 = n4;
                        this.anInt5567 = anInt5564;
                        this.anInt5562 = n5;
                        return;
                    }
                    if (n9 == 4) {
                        this.anInt5567 = n5;
                        this.anInt5562 = anInt5563;
                        this.anInt5566 = n4;
                        return;
                    }
                    if (~n9 != 0xFFFFFFFA) {
                        return;
                    }
                    if (!client.aBoolean3553) {
                        this.anInt5567 = n5;
                        this.anInt5566 = anInt5564;
                        this.anInt5562 = n4;
                        return;
                    }
                }
                this.anInt5562 = n4;
                this.anInt5567 = anInt5563;
                this.anInt5566 = n5;
                return;
            }
            this.anInt5566 = anInt5562;
            this.anInt5567 = anInt5562;
            this.anInt5562 = anInt5562;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dfa.E(" + n + ',' + n2 + ',' + anInt5562 + ',' + n3 + ')');
        }
    }
    
    static final String[] method1022(final int n, final String[] array) {
        try {
            final String[] array2 = new String[5];
            if (n != -845) {
                return null;
            }
            for (int i = 0; i < 5; ++i) {
                array2[i] = String.valueOf(i) + ": ";
                if (array != null && array[i] != null) {
                    array2[i] += array[i];
                }
            }
            return array2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dfa.B(" + n + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    public Class98_Sub10_Sub6() {
        super(1, false);
        this.anInt5561 = 0;
        this.anInt5560 = 0;
        this.anInt5563 = 0;
    }
    
    static {
        Class98_Sub10_Sub6.anIntArray5559 = new int[] { 16, 32, 64, 128 };
        Class98_Sub10_Sub6.aClass258_5568 = new Class258();
        Class98_Sub10_Sub6.anInt5570 = -1;
        Class98_Sub10_Sub6.anInt5569 = 0;
    }
}
