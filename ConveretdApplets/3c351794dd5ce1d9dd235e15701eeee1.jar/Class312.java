// 
// Decompiled by Procyon v0.5.30
// 

final class Class312
{
    static IncomingOpcode aClass58_2661;
    int[] anIntArray2662;
    private byte[] aByteArray2663;
    int[] anIntArray2664;
    int anInt2665;
    Class122 aClass122_2666;
    int[] anIntArray2667;
    int anInt2668;
    int[][] anIntArrayArray2669;
    int[] anIntArray2670;
    int[] anIntArray2671;
    Class122[] aClass122Array2672;
    int[] anIntArray2673;
    int[][] anIntArrayArray2674;
    byte[][] aByteArrayArray2675;
    int anInt2676;
    int anInt2677;
    
    static final void method3620(final Class352 class352, final int n, final int n2, final int n3, final int n4) {
        try {
            Class98_Sub42 class98_Sub42 = (Class98_Sub42)Class98_Sub10_Sub37.aClass148_5748.method2418(32);
            if (n != -22015) {
                Class312.aClass58_2661 = null;
            }
            while (class98_Sub42 != null) {
                if (n3 == class98_Sub42.anInt4220 && n4 << -172547319 == class98_Sub42.anInt4229 && class98_Sub42.anInt4225 == n2 << -244251415 && ~class352.id == ~class98_Sub42.aClass352_4233.id) {
                    if (class98_Sub42.aClass98_Sub31_Sub5_4232 != null) {
                        Class81.aClass98_Sub31_Sub3_619.method1374(class98_Sub42.aClass98_Sub31_Sub5_4232);
                        class98_Sub42.aClass98_Sub31_Sub5_4232 = null;
                    }
                    if (class98_Sub42.aClass98_Sub31_Sub5_4230 != null) {
                        Class81.aClass98_Sub31_Sub3_619.method1374(class98_Sub42.aClass98_Sub31_Sub5_4230);
                        class98_Sub42.aClass98_Sub31_Sub5_4230 = null;
                    }
                    class98_Sub42.method942(87);
                    break;
                }
                class98_Sub42 = (Class98_Sub42)Class98_Sub10_Sub37.aClass148_5748.method2417(97);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "te.D(" + ((class352 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    public static void method3621(final byte b) {
        try {
            if (b != 7) {
                method3620(null, -45, -88, 50, 116);
            }
            Class312.aClass58_2661 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "te.B(" + b + ')');
        }
    }
    
    private final void method3622(final byte[] array, final int n) {
        try {
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(Class98_Sub46_Sub10.method1571(array, (byte)(-84)));
            final int unsignedByte = class98_Sub22.readUnsignedByte((byte)23);
            if (unsignedByte < 5 || unsignedByte > 6) {
                throw new RuntimeException();
            }
            if (~unsignedByte > -7) {
                this.anInt2676 = 0;
            }
            else {
                this.anInt2676 = class98_Sub22.readInt(-2);
            }
            final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-127));
            final boolean b = (0x1 & unsignedByte2) != 0x0;
            this.anInt2665 = class98_Sub22.readShort((byte)127);
            final boolean b2 = ~(0x2 & unsignedByte2) != -1;
            int n2 = 0;
            this.anIntArray2664 = new int[this.anInt2665];
            int n3 = -1;
            for (int n4 = 0; this.anInt2665 > n4; ++n4) {
                n2 = (this.anIntArray2664[n4] = n2 + class98_Sub22.readShort((byte)127));
                if (this.anIntArray2664[n4] > n3) {
                    n3 = this.anIntArray2664[n4];
                }
            }
            this.anInt2668 = 1 + n3;
            if (b2) {
                this.aByteArrayArray2675 = new byte[this.anInt2668][];
            }
            this.anIntArray2670 = new int[this.anInt2668];
            this.anIntArrayArray2669 = new int[this.anInt2668][];
            this.anIntArray2671 = new int[this.anInt2668];
            this.anIntArray2667 = new int[this.anInt2668];
            this.anIntArray2673 = new int[this.anInt2668];
            if (b) {
                this.anIntArray2662 = new int[this.anInt2668];
                for (int n5 = 0; ~n5 > ~this.anInt2668; ++n5) {
                    this.anIntArray2662[n5] = -1;
                }
                for (int i = 0; i < this.anInt2665; ++i) {
                    this.anIntArray2662[this.anIntArray2664[i]] = class98_Sub22.readInt(-2);
                }
                this.aClass122_2666 = new Class122(this.anIntArray2662);
            }
            for (int n6 = 0; this.anInt2665 > n6; ++n6) {
                this.anIntArray2673[this.anIntArray2664[n6]] = class98_Sub22.readInt(-2);
            }
            if (b2) {
                for (int n7 = 0; ~n7 > ~this.anInt2665; ++n7) {
                    final byte[] array2 = new byte[64];
                    class98_Sub22.method1190(array2, true, 64, 0);
                    this.aByteArrayArray2675[this.anIntArray2664[n7]] = array2;
                }
            }
            if (n == -7572) {
                for (int n8 = 0; this.anInt2665 > n8; ++n8) {
                    this.anIntArray2667[this.anIntArray2664[n8]] = class98_Sub22.readInt(-2);
                }
                for (int n9 = 0; this.anInt2665 > n9; ++n9) {
                    this.anIntArray2670[this.anIntArray2664[n9]] = class98_Sub22.readShort((byte)127);
                }
                for (int n10 = 0; ~this.anInt2665 < ~n10; ++n10) {
                    final int n11 = this.anIntArray2664[n10];
                    final int n12 = this.anIntArray2670[n11];
                    int n13 = 0;
                    this.anIntArrayArray2669[n11] = new int[n12];
                    int n14 = -1;
                    for (int n15 = 0; ~n15 > ~n12; ++n15) {
                        final int[] array3 = this.anIntArrayArray2669[n11];
                        final int n16 = n15;
                        final int n17 = n13 += class98_Sub22.readShort((byte)127);
                        array3[n16] = n17;
                        final int n18 = n17;
                        if (~n18 < ~n14) {
                            n14 = n18;
                        }
                    }
                    this.anIntArray2671[n11] = 1 + n14;
                    if (~n12 == ~(1 + n14)) {
                        this.anIntArrayArray2669[n11] = null;
                    }
                }
                if (b) {
                    this.aClass122Array2672 = new Class122[1 + n3];
                    this.anIntArrayArray2674 = new int[1 + n3][];
                    for (int n19 = 0; this.anInt2665 > n19; ++n19) {
                        final int n20 = this.anIntArray2664[n19];
                        final int n21 = this.anIntArray2670[n20];
                        this.anIntArrayArray2674[n20] = new int[this.anIntArray2671[n20]];
                        for (int n22 = 0; ~n22 > ~this.anIntArray2671[n20]; ++n22) {
                            this.anIntArrayArray2674[n20][n22] = -1;
                        }
                        for (int n23 = 0; ~n23 > ~n21; ++n23) {
                            int n24;
                            if (this.anIntArrayArray2669[n20] != null) {
                                n24 = this.anIntArrayArray2669[n20][n23];
                            }
                            else {
                                n24 = n23;
                            }
                            this.anIntArrayArray2674[n20][n24] = class98_Sub22.readInt(n + 7570);
                        }
                        this.aClass122Array2672[n20] = new Class122(this.anIntArrayArray2674[n20]);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "te.C(" + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final boolean method3623(final int n, final int n2) {
        try {
            if (n > -68) {
                Class312.aClass58_2661 = null;
            }
            return ~n2 == 0xFFFFFFFC || n2 == 4;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "te.A(" + n + ',' + n2 + ')');
        }
    }
    
    Class312(final byte[] array, final int n, final byte[] array2) {
        try {
            this.anInt2677 = Class31.method309(array.length, array, -30091);
            if (n != this.anInt2677) {
                throw new RuntimeException();
            }
            if (array2 != null) {
                if (array2.length != 64) {
                    throw new RuntimeException();
                }
                this.aByteArray2663 = Class64_Sub11.method595(0, (byte)(-121), array, array.length);
                for (int i = 0; i < 64; ++i) {
                    if (~this.aByteArray2663[i] != ~array2[i]) {
                        throw new RuntimeException();
                    }
                }
            }
            this.method3622(array, -7572);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "te.<init>(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class312.aClass58_2661 = new IncomingOpcode(89, 0);
    }
}
