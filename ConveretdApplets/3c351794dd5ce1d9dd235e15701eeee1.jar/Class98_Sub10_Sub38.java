import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub38 extends Class98_Sub10
{
    static int anInt5751;
    static int anInt5752;
    static int anInt5753;
    private int anInt5754;
    private int anInt5755;
    static boolean aBoolean5756;
    private int anInt5757;
    private int anInt5758;
    static boolean[] aBooleanArray5759;
    private int anInt5760;
    static int anInt5761;
    private int[][] anIntArrayArray5762;
    private int anInt5763;
    private int[][] anIntArrayArray5764;
    static Class135 aClass135_5765;
    private int anInt5766;
    private int[] anIntArray5767;
    private int anInt5768;
    private int anInt5769;
    private int anInt5770;
    private int anInt5771;
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b < -92) {
                if (~n != -1) {
                    if (n != 1) {
                        if (n != 2) {
                            if (n != 3) {
                                if (n != 4) {
                                    if (~n != 0xFFFFFFFA) {
                                        if (~n != 0xFFFFFFF9) {
                                            if (n == 7) {
                                                this.anInt5757 = class98_Sub22.readShort((byte)127);
                                            }
                                        }
                                        else {
                                            this.anInt5766 = class98_Sub22.readShort((byte)127);
                                        }
                                    }
                                    else {
                                        this.anInt5755 = class98_Sub22.readShort((byte)127);
                                    }
                                }
                                else {
                                    this.anInt5760 = class98_Sub22.readShort((byte)127);
                                }
                            }
                            else {
                                this.anInt5769 = class98_Sub22.readShort((byte)127);
                            }
                        }
                        else {
                            this.anInt5754 = class98_Sub22.readShort((byte)127);
                        }
                    }
                    else {
                        this.anInt5763 = class98_Sub22.readUnsignedByte((byte)79);
                    }
                }
                else {
                    this.anInt5771 = class98_Sub22.readUnsignedByte((byte)(-123));
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wia.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final int method1116(final int n, final int n2, int n3) {
        try {
            if (n2 != 1391) {
                return 124;
            }
            n3 = n3 * (n & 0x7F) >> -1626377081;
            if (n3 >= 2) {
                if (~n3 >= -127) {
                    return n3 + (n & 0xFF80);
                }
                n3 = 126;
                if (!client.aBoolean3553) {
                    return n3 + (n & 0xFF80);
                }
            }
            n3 = 2;
            return n3 + (n & 0xFF80);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wia.B(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                int n3 = 0;
                int n4;
                for (n4 = this.anInt5755 + Class352.anIntArray3001[n2]; ~n4 > -1; n4 += 4096) {}
                while (~n4 < -4097) {
                    n4 -= 4096;
                }
                while (this.anInt5763 > n3 && n4 >= this.anIntArray5767[n3]) {
                    ++n3;
                }
                final int n5 = n3 - 1;
                final boolean b = ~(n3 & 0x1) == -1;
                final int n6 = this.anIntArray5767[n3];
                if (this.anIntArray5767[n3 - 1] + this.anInt5768 >= n4 || ~n4 <= ~(-this.anInt5768 + n6)) {
                    Class236.method2896(method237, 0, Class25.anInt268, 0);
                }
                else {
                    for (int i = 0; i < Class25.anInt268; ++i) {
                        int n7 = 0;
                        int j;
                        for (j = (this.anInt5758 * (b ? this.anInt5760 : (-this.anInt5760)) >> 1198328172) + Class64_Sub1.anIntArray3640[i]; j < 0; j += 4096) {}
                        while (j > 4096) {
                            j -= 4096;
                        }
                        while (n7 < this.anInt5771 && this.anIntArrayArray5764[n5][n7] <= j) {
                            ++n7;
                        }
                        final int n8 = n7 - 1;
                        final int n9 = this.anIntArrayArray5764[n5][n8];
                        final int n10 = this.anIntArrayArray5764[n5][n7];
                        if (n9 - -this.anInt5768 < j && ~(n10 + -this.anInt5768) < ~j) {
                            method237[i] = this.anIntArrayArray5762[n5][n8];
                        }
                        else {
                            method237[i] = 0;
                        }
                    }
                }
            }
            if (n != 255) {
                this.anInt5769 = 39;
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wia.G(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method1001(final byte b) {
        try {
            this.method1117(64);
            if (b != 66) {
                this.anInt5758 = -87;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wia.I(" + b + ')');
        }
    }
    
    private final void method1117(final int n) {
        try {
            final Random random = new Random(this.anInt5763);
            this.anInt5768 = this.anInt5766 / 2;
            this.anInt5758 = 4096 / this.anInt5771;
            this.anInt5770 = 4096 / this.anInt5763;
            final int n2 = this.anInt5758 / 2;
            this.anIntArrayArray5764 = new int[this.anInt5763][1 + this.anInt5771];
            this.anIntArray5767 = new int[1 + this.anInt5763];
            this.anIntArrayArray5762 = new int[this.anInt5763][this.anInt5771];
            final int n3 = this.anInt5770 / 2;
            this.anIntArray5767[0] = 0;
            for (int n4 = 0; ~n4 > ~this.anInt5763; ++n4) {
                if (n4 > 0) {
                    this.anIntArray5767[n4] = this.anInt5770 + (((Class63.method546(-28737, 4096, random) - 2048) * this.anInt5769 >> -533582260) * n3 >> -1942924724) + this.anIntArray5767[n4 - 1];
                }
                this.anIntArrayArray5764[n4][0] = 0;
                for (int n5 = 0; ~n5 > ~this.anInt5771; ++n5) {
                    if (~n5 < -1) {
                        this.anIntArrayArray5764[n4][n5] = this.anInt5758 + (n2 * ((Class63.method546(-28737, 4096, random) - 2048) * this.anInt5754 >> 1772484460) >> -1938743028) + this.anIntArrayArray5764[n4][n5 - 1];
                    }
                    this.anIntArrayArray5762[n4][n5] = ((~this.anInt5757 >= -1) ? 4096 : (4096 + -Class63.method546(-28737, this.anInt5757, random)));
                }
                this.anIntArrayArray5764[n4][this.anInt5771] = 4096;
            }
            if (n < 62) {
                Class98_Sub10_Sub38.anInt5752 = -113;
            }
            this.anIntArray5767[this.anInt5763] = 4096;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wia.E(" + n + ')');
        }
    }
    
    public Class98_Sub10_Sub38() {
        super(0, true);
        this.anInt5754 = 409;
        this.anInt5755 = 0;
        this.anInt5763 = 8;
        this.anInt5760 = 1024;
        this.anInt5757 = 1024;
        this.anInt5771 = 4;
        this.anInt5769 = 204;
        this.anInt5766 = 81;
    }
    
    public static void method1118(final boolean b) {
        try {
            Class98_Sub10_Sub38.aClass135_5765 = null;
            Class98_Sub10_Sub38.aBooleanArray5759 = null;
            if (!b) {
                Class98_Sub10_Sub38.aClass135_5765 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wia.D(" + b + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub38.aBooleanArray5759 = new boolean[100];
        Class98_Sub10_Sub38.anInt5753 = 0;
        Class98_Sub10_Sub38.aBoolean5756 = false;
    }
}
