// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub21 extends Class98_Sub10
{
    static IncomingOpcode aClass58_5641;
    static byte[] aByteArray5642;
    static int anInt5643;
    private int anInt5644;
    private int anInt5645;
    static boolean aBoolean5646;
    private int anInt5647;
    private int anInt5648;
    private int anInt5649;
    private int anInt5650;
    private int anInt5651;
    
    public Class98_Sub10_Sub21() {
        super(0, true);
        this.anInt5645 = 2048;
        this.anInt5644 = 4096;
        this.anInt5647 = 8192;
        this.anInt5648 = 12288;
        this.anInt5650 = 0;
        this.anInt5649 = 2048;
        this.anInt5651 = 0;
    }
    
    static final void method1063(final byte b) {
        try {
            synchronized (Class299.aClass79_2493) {
                if (b >= -11) {
                    method1068((byte)49);
                }
                Class299.aClass79_2493.method794(99);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mba.E(" + b + ')');
        }
    }
    
    @Override
    final void method1001(final byte b) {
        try {
            Class98_Sub31_Sub4.method1386(b ^ 0x42);
            if (b != 66) {
                this.method990(-105, 15);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mba.I(" + b + ')');
        }
    }
    
    static final boolean method1064(final int n, final boolean b) {
        try {
            if (~n == 0xFFFFFFF0 || ~n == 0xFFFFFFFB || ~n == 0xFFFFFFF7 || n == 16 || n == 1007) {
                return true;
            }
            if (~n == 0xFFFFFFCD || ~n == 0xFFFFFC0E) {
                return true;
            }
            if (b) {
                method1066(-107, null);
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mba.D(" + n + ',' + b + ')');
        }
    }
    
    private final boolean method1065(final byte b, final int n, final int n2) {
        try {
            if (b != -15) {
                return false;
            }
            final int n3 = this.anInt5644 * (((Class278_Sub1.anIntArray5168[(255 * (this.anInt5648 * (n + n2) >> 387809964) & 0xFF4A7) >> 2005561068] << -2091824980) / this.anInt5648 << 431388332) / this.anInt5647) >> 1509067820;
            return n3 > -n + n2 && n2 - n > -n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mba.F(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    static final void method1066(final int n, final Class293 class293) {
        try {
            if (class293.anInt2354 == 5 && ~class293.anInt2302 != 0x0) {
                Class372.method3957(Class265.aHa1974, true, class293);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mba.J(" + n + ',' + ((class293 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final boolean method1067(final int n, final int n2, final int n3) {
        try {
            if (n != -13306) {
                method1068((byte)(-36));
            }
            final int n4 = ((Class278_Sub1.anIntArray5168[(0xFFCE8 & 255 * ((n3 - n2) * this.anInt5648 >> 1378239116)) >> 970817420] << -1235031796) / this.anInt5648 << 1531907340) / this.anInt5647 * this.anInt5644 >> 9457036;
            return n4 > n3 + n2 && ~(-n4) > ~(n2 + n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mba.H(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (n != 255) {
                this.method1067(91, 52, -49);
            }
            if (super.aClass16_3863.aBoolean198) {
                final int n3 = Class352.anIntArray3001[n2] - 2048;
                for (int n4 = 0; Class25.anInt268 > n4; ++n4) {
                    final int n5 = -2048 + Class64_Sub1.anIntArray3640[n4];
                    final int n6 = n5 - -this.anInt5645;
                    final int n7 = (n6 >= -2048) ? n6 : (n6 + 4096);
                    final int n8 = (~n7 >= -2049) ? n7 : (-4096 + n7);
                    final int n9 = n3 - -this.anInt5651;
                    final int n10 = (n9 < -2048) ? (n9 + 4096) : n9;
                    final int n11 = (n10 <= 2048) ? n10 : (-4096 + n10);
                    final int n12 = this.anInt5650 + n5;
                    final int n13 = (n12 >= -2048) ? n12 : (n12 + 4096);
                    final int n14 = (~n13 < -2049) ? (n13 - 4096) : n13;
                    final int n15 = this.anInt5649 + n3;
                    final int n16 = (~n15 <= 2047) ? n15 : (4096 + n15);
                    final int n17 = (n16 <= 2048) ? n16 : (n16 - 4096);
                    method237[n4] = ((this.method1067(-13306, n8, n11) || this.method1065((byte)(-15), n14, n17)) ? 4096 : 0);
                }
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mba.G(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b > -92) {
                this.anInt5648 = 53;
            }
            if (n != 0) {
                if (n == 1) {
                    this.anInt5651 = class98_Sub22.readShort((byte)127);
                    return;
                }
                if (n == 2) {
                    this.anInt5650 = class98_Sub22.readShort((byte)127);
                    return;
                }
                if (n == 3) {
                    this.anInt5649 = class98_Sub22.readShort((byte)127);
                    return;
                }
                if (n == 4) {
                    this.anInt5648 = class98_Sub22.readShort((byte)127);
                    return;
                }
                if (~n == 0xFFFFFFFA) {
                    this.anInt5644 = class98_Sub22.readShort((byte)127);
                    return;
                }
                if (~n != 0xFFFFFFF9) {
                    return;
                }
                if (!client.aBoolean3553) {
                    this.anInt5647 = class98_Sub22.readShort((byte)127);
                    return;
                }
            }
            this.anInt5645 = class98_Sub22.readShort((byte)127);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mba.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public static void method1068(final byte b) {
        try {
            if (b != -48) {
                Class98_Sub10_Sub21.aClass58_5641 = null;
            }
            Class98_Sub10_Sub21.aClass58_5641 = null;
            Class98_Sub10_Sub21.aByteArray5642 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mba.B(" + b + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub21.aByteArray5642 = new byte[2048];
        Class98_Sub10_Sub21.aBoolean5646 = false;
    }
}
