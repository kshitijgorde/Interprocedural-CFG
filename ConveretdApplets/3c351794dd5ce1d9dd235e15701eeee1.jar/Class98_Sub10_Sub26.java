import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub26 extends Class98_Sub10
{
    private int anInt5681;
    private int anInt5682;
    static int anInt5683;
    static Class105 aClass105_5684;
    private byte[] aByteArray5685;
    private int anInt5686;
    private int anInt5687;
    private int anInt5688;
    private short[] aShortArray5689;
    static Class98_Sub46_Sub16[] aClass98_Sub46_Sub16Array5690;
    private int anInt5691;
    
    public static void method1082(final int n) {
        try {
            Class98_Sub10_Sub26.aClass98_Sub46_Sub16Array5690 = null;
            Class98_Sub10_Sub26.aClass105_5684 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oo.B(" + n + ')');
        }
    }
    
    private final void method1083(final int n) {
        try {
            final Random random = new Random(this.anInt5686);
            this.aShortArray5689 = new short[512];
            if (n > ~this.anInt5682) {
                for (int n2 = 0; ~n2 > -513; ++n2) {
                    this.aShortArray5689[n2] = (short)Class63.method546(-28737, this.anInt5682, random);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oo.E(" + n + ')');
        }
    }
    
    @Override
    final void method1001(final byte b) {
        try {
            this.aByteArray5685 = Class279.method3323(this.anInt5686, 512);
            this.method1083(-1);
            if (b != 66) {
                method1084(false, null, 81);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oo.I(" + b + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (n != 255) {
                this.anInt5686 = -69;
            }
            if (super.aClass16_3863.aBoolean198) {
                final int n3 = 2048 - -(this.anInt5691 * Class352.anIntArray3001[n2]);
                final int n4 = n3 >> -1160351668;
                final int n5 = 1 + n4;
                for (int n6 = 0; ~n6 > ~Class25.anInt268; ++n6) {
                    Class163.anInt3517 = (Class93_Sub1_Sub1.anInt6291 = (Class252.anInt1924 = (Class206.anInt1567 = Integer.MAX_VALUE)));
                    final int n7 = 2048 - -(this.anInt5688 * Class64_Sub1.anIntArray3640[n6]);
                    final int n8 = n7 >> -275339860;
                    final int n9 = 1 + n8;
                    for (int n10 = n4 - 1; ~n5 <= ~n10; ++n10) {
                        final int n11 = this.aByteArray5685[0xFF & ((this.anInt5691 <= n10) ? (-this.anInt5691 + n10) : n10)] & 0xFF;
                        for (int i = n8 - 1; i <= n9; ++i) {
                            int n12 = 2 * (0xFF & this.aByteArray5685[0xFF & ((i >= this.anInt5688) ? (i + -this.anInt5688) : i) + n11]);
                            final int n13 = -(i << -1047229364) - this.aShortArray5689[n12++] + n7;
                            final int n14 = -(n10 << -1193947764) - (this.aShortArray5689[n12] - n3);
                            final int anInt5681 = this.anInt5681;
                            int n15;
                            if (anInt5681 != 1) {
                                if (~anInt5681 != 0xFFFFFFFC) {
                                    if (anInt5681 != 4) {
                                        if (anInt5681 != 5) {
                                            if (anInt5681 == 2) {
                                                n15 = ((~n14 > -1) ? (-n14) : n14) + ((~n13 > -1) ? (-n13) : n13);
                                            }
                                            else {
                                                n15 = (int)(4096.0 * Math.sqrt((n13 * n13 - -(n14 * n14)) / 1.6777216E7f));
                                            }
                                        }
                                        else {
                                            n15 = (int)(Math.sqrt(Math.sqrt((n14 * n14 + n13 * n13) / 1.6777216E7f)) * 4096.0);
                                        }
                                    }
                                    else {
                                        final int n16 = (int)(Math.sqrt(((~n13 > -1) ? (-n13) : n13) / 4096.0f) * 4096.0) + (int)(4096.0 * Math.sqrt(((n14 >= 0) ? n14 : (-n14)) / 4096.0f));
                                        n15 = n16 * n16 >> 1226834796;
                                    }
                                }
                                else {
                                    final int n17 = (n14 < 0) ? (-n14) : n14;
                                    final int n18 = (n13 >= 0) ? n13 : (-n13);
                                    n15 = ((n18 > n17) ? n18 : n17);
                                }
                            }
                            else {
                                n15 = n13 * n13 - -(n14 * n14) >> -1987789620;
                            }
                            if (~Class163.anInt3517 < ~n15) {
                                Class206.anInt1567 = Class252.anInt1924;
                                Class252.anInt1924 = Class93_Sub1_Sub1.anInt6291;
                                Class93_Sub1_Sub1.anInt6291 = Class163.anInt3517;
                                Class163.anInt3517 = n15;
                            }
                            else if (~n15 > ~Class93_Sub1_Sub1.anInt6291) {
                                Class206.anInt1567 = Class252.anInt1924;
                                Class252.anInt1924 = Class93_Sub1_Sub1.anInt6291;
                                Class93_Sub1_Sub1.anInt6291 = n15;
                            }
                            else if (~n15 <= ~Class252.anInt1924) {
                                if (~Class206.anInt1567 < ~n15) {
                                    Class206.anInt1567 = n15;
                                }
                            }
                            else {
                                Class206.anInt1567 = Class252.anInt1924;
                                Class252.anInt1924 = n15;
                            }
                        }
                    }
                    final int anInt5682 = this.anInt5687;
                    if (~anInt5682 != -1) {
                        if (anInt5682 != 1) {
                            if (~anInt5682 != 0xFFFFFFFC) {
                                if (~anInt5682 != 0xFFFFFFFB) {
                                    if (anInt5682 == 2) {
                                        method237[n6] = Class93_Sub1_Sub1.anInt6291 + -Class163.anInt3517;
                                    }
                                }
                                else {
                                    method237[n6] = Class206.anInt1567;
                                }
                            }
                            else {
                                method237[n6] = Class252.anInt1924;
                            }
                        }
                        else {
                            method237[n6] = Class93_Sub1_Sub1.anInt6291;
                        }
                    }
                    else {
                        method237[n6] = Class163.anInt3517;
                    }
                }
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oo.G(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (~n != -1) {
                if (~n != 0xFFFFFFFE) {
                    if (~n != 0xFFFFFFFD) {
                        if (n != 3) {
                            if (~n != 0xFFFFFFFB) {
                                if (n != 5) {
                                    if (n == 6) {
                                        this.anInt5691 = class98_Sub22.readUnsignedByte((byte)16);
                                    }
                                }
                                else {
                                    this.anInt5688 = class98_Sub22.readUnsignedByte((byte)(-103));
                                }
                            }
                            else {
                                this.anInt5681 = class98_Sub22.readUnsignedByte((byte)124);
                            }
                        }
                        else {
                            this.anInt5687 = class98_Sub22.readUnsignedByte((byte)0);
                        }
                    }
                    else {
                        this.anInt5682 = class98_Sub22.readShort((byte)127);
                    }
                }
                else {
                    this.anInt5686 = class98_Sub22.readUnsignedByte((byte)27);
                }
            }
            else {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-103));
                this.anInt5691 = unsignedByte;
                this.anInt5688 = unsignedByte;
            }
            if (b > -92) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oo.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public Class98_Sub10_Sub26() {
        super(0, true);
        this.anInt5681 = 1;
        this.anInt5682 = 2048;
        this.aByteArray5685 = new byte[512];
        this.anInt5688 = 5;
        this.aShortArray5689 = new short[512];
        this.anInt5686 = 0;
        this.anInt5691 = 5;
        this.anInt5687 = 2;
    }
    
    static final String method1084(final boolean b, final Class98_Sub22 class98_Sub22, final int n) {
        try {
            try {
                int smart = class98_Sub22.readSmart(1689622712);
                if (~smart < ~n) {
                    smart = n;
                }
                final byte[] array = new byte[smart];
                class98_Sub22.anInt3991 += Class146_Sub3.aClass213_4949.method2782(class98_Sub22.anInt3991, array, -69, smart, class98_Sub22.aByteArray3992, 0);
                final String method1546 = Class98_Sub46_Sub6.method1546(smart, 0, (byte)(-101), array);
                if (b) {
                    return null;
                }
                return method1546;
            }
            catch (Exception ex2) {
                return "Cabbage";
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oo.D(" + b + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub26.anInt5683 = -1;
        Class98_Sub10_Sub26.aClass105_5684 = new Class105("", 16);
        Class98_Sub10_Sub26.aClass98_Sub46_Sub16Array5690 = new Class98_Sub46_Sub16[14];
    }
}
