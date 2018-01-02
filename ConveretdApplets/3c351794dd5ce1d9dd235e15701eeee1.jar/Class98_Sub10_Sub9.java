// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub9 extends Class98_Sub10
{
    static int anInt5580;
    static int anInt5581;
    private int[] anIntArray5582;
    private int[] anIntArray5583;
    private short[] aShortArray5584;
    static boolean aBoolean5585;
    private int anInt5586;
    private int[][] anIntArrayArray5587;
    
    private final void method1031(final int n) {
        try {
            if (n != -1) {
                Class98_Sub10_Sub9.anInt5580 = 81;
            }
            final int anInt5586 = this.anInt5586;
            if (~anInt5586 != 0xFFFFFFFD) {
                if (anInt5586 != 1) {
                    for (int n2 = 0; ~n2 > -258; ++n2) {
                        int n3;
                        int n4;
                        for (n3 = n2 << -1497970108, n4 = 1; ~(this.anIntArrayArray5587.length - 1) < ~n4 && n3 >= this.anIntArrayArray5587[n4][0]; ++n4) {}
                        final int[] array = this.anIntArrayArray5587[n4 - 1];
                        final int[] array2 = this.anIntArrayArray5587[n4];
                        final int n5 = (-array[0] + n3 << -1962608884) / (array2[0] + -array[0]);
                        int n6 = array2[1] * n5 + (-n5 + 4096) * array[1] >> -443005428;
                        if (~n6 >= 32767) {
                            n6 = -32767;
                        }
                        if (n6 >= 32768) {
                            n6 = 32767;
                        }
                        this.aShortArray5584[n2] = (short)n6;
                    }
                }
                else {
                    for (int n7 = 0; ~n7 > -258; ++n7) {
                        int n8;
                        int n9;
                        for (n8 = n7 << 1182163748, n9 = 1; ~n9 > ~(-1 + this.anIntArrayArray5587.length) && ~this.anIntArrayArray5587[n9][0] >= ~n8; ++n9) {}
                        final int[] array3 = this.anIntArrayArray5587[-1 + n9];
                        final int[] array4 = this.anIntArrayArray5587[n9];
                        final int n10 = 4096 - Class278_Sub1.anIntArray5168[((n8 - array3[0] << 1736703948) / (-array3[0] + array4[0]) & 0x1FE0) >> 1491723813] >> -2016979391;
                        int n11 = array4[1] * n10 + (-n10 + 4096) * array3[1] >> 797288268;
                        if (~n11 >= 32767) {
                            n11 = -32767;
                        }
                        if (~n11 <= -32769) {
                            n11 = 32767;
                        }
                        this.aShortArray5584[n7] = (short)n11;
                    }
                }
            }
            else {
                for (int n12 = 0; ~n12 > -258; ++n12) {
                    int n13;
                    int n14;
                    for (n13 = n12 << 1844896324, n14 = 1; this.anIntArrayArray5587.length - 1 > n14 && ~n13 <= ~this.anIntArrayArray5587[n14][0]; ++n14) {}
                    final int[] array5 = this.anIntArrayArray5587[n14 - 1];
                    final int[] array6 = this.anIntArrayArray5587[n14];
                    final int n15 = this.method1035(6435, -2 + n14)[1];
                    final int n16 = array5[1];
                    final int n17 = array6[1];
                    final int n18 = this.method1035(n + 6436, n14 + 1)[1];
                    final int n19 = (-array5[0] + n13 << -1903518196) / (-array5[0] + array6[0]);
                    final int n20 = n19 * n19 >> 1577884364;
                    final int n21 = -n17 + n18 - n15 - -n16;
                    int n22 = n16 + (n20 * (-n21 + n15 - n16) >> 1675912716) + ((n21 * n19 >> -1740957524) * n20 >> -747637524) + ((n17 + -n15) * n19 >> -1525582676);
                    if (~n22 >= 32767) {
                        n22 = -32767;
                    }
                    if (n22 >= 32768) {
                        n22 = 32767;
                    }
                    this.aShortArray5584[n12] = (short)n22;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "et.H(" + n + ')');
        }
    }
    
    static final int method1032(final int n, final byte b) {
        try {
            if (b < 8) {
                Class98_Sub10_Sub9.aBoolean5585 = true;
            }
            return n & 0xFF;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "et.D(" + n + ',' + b + ')');
        }
    }
    
    public Class98_Sub10_Sub9() {
        super(1, true);
        this.anInt5586 = 0;
        this.aShortArray5584 = new short[257];
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                final int[] method238 = this.method1000(n2, 0, 0);
                for (int i = 0; i < Class25.anInt268; ++i) {
                    int n3 = method238[i] >> -880337692;
                    if (~n3 > -1) {
                        n3 = 0;
                    }
                    if (~n3 < -257) {
                        n3 = 256;
                    }
                    method237[i] = this.aShortArray5584[n3];
                }
            }
            if (n != 255) {
                Class98_Sub10_Sub9.aBoolean5585 = true;
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "et.G(" + n + ',' + n2 + ')');
        }
    }
    
    static final boolean method1033(final int n, final int n2, final int n3) {
        try {
            return n3 != 16 || ~(0x10 & n) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "et.B(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    private final void method1034(final int n) {
        try {
            final int[] array = this.anIntArrayArray5587[0];
            final int[] array2 = this.anIntArrayArray5587[1];
            final int[] array3 = this.anIntArrayArray5587[n + this.anIntArrayArray5587.length];
            final int[] array4 = this.anIntArrayArray5587[-1 + this.anIntArrayArray5587.length];
            this.anIntArray5582 = new int[] { array3[0] + (-array4[0] + array3[0]), array3[1] + (array3[1] + -array4[1]) };
            this.anIntArray5583 = new int[] { array[0] - array2[0] - -array[0], array[1] - array2[1] - -array[1] };
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "et.F(" + n + ')');
        }
    }
    
    private final int[] method1035(final int n, final int n2) {
        try {
            if (~n2 > -1) {
                return this.anIntArray5583;
            }
            if (n != 6435) {
                Class98_Sub10_Sub9.aBoolean5585 = false;
            }
            if (~n2 <= ~this.anIntArrayArray5587.length) {
                return this.anIntArray5582;
            }
            return this.anIntArrayArray5587[n2];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "et.J(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method1001(final byte b) {
        try {
            if (b != 66) {
                this.anIntArray5582 = null;
            }
            if (this.anIntArrayArray5587 == null) {
                this.anIntArrayArray5587 = new int[][] { new int[2], { 4096, 4096 } };
            }
            if (this.anIntArrayArray5587.length < 2) {
                throw new RuntimeException("Curve operation requires at least two markers");
            }
            if (this.anInt5586 == 2) {
                this.method1034(-2);
            }
            Class98_Sub31_Sub4.method1386(0);
            this.method1031(b - 67);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "et.I(" + b + ')');
        }
    }
    
    static final void method1036(final int n, final int n2, final int n3, final int n4, final boolean b, final int n5, final int n6) {
        try {
            if (n == -1962608884) {
                if ((b ? Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4054.method641((byte)123) : Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4066.method641((byte)125)) != 0 && n3 != 0 && Class306.anInt2566 < 50 && n4 != -1) {
                    Class245.aClass338Array1865[Class306.anInt2566++] = new Class338((byte)(b ? 3 : 2), n4, n3, n5, n2, 0, n6, null);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "et.E(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b > -92) {
                this.method990(117, -9);
            }
            if (~n == -1) {
                this.anInt5586 = class98_Sub22.readUnsignedByte((byte)(-120));
                this.anIntArrayArray5587 = new int[class98_Sub22.readUnsignedByte((byte)(-123))][2];
                for (int n2 = 0; ~this.anIntArrayArray5587.length < ~n2; ++n2) {
                    this.anIntArrayArray5587[n2][0] = class98_Sub22.readShort((byte)127);
                    this.anIntArrayArray5587[n2][1] = class98_Sub22.readShort((byte)127);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "et.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub9.anInt5580 = 0;
        Class98_Sub10_Sub9.aBoolean5585 = false;
        Class98_Sub10_Sub9.anInt5581 = 0;
    }
}
