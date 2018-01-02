// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub25 extends Class98_Sub10
{
    static double aDouble5675;
    private int anInt5676;
    static long aLong5677;
    private int anInt5678;
    private int anInt5679;
    private int[] anIntArray5680;
    
    static final int method1079(final Class98_Sub46_Sub9 class98_Sub46_Sub9, final int n) {
        try {
            if (n != 21) {
                return -125;
            }
            return Class42_Sub1.aClass197_5354.method2676((byte)108, Class217.aClass332Array3408, Class21.method262(class98_Sub46_Sub9, (byte)103));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "om.B(" + ((class98_Sub46_Sub9 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final int method992(final int n) {
        try {
            return this.anInt5676;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "om.S(" + n + ')');
        }
    }
    
    static final void method1080(final byte b) {
        try {
            if (Class98_Sub43_Sub2.aClass88_5907.aBoolean682 && ~Class289.aClass354_2197.anInt3011 != 0x0) {
                Class98_Sub12.method1131(-8804, Class289.aClass354_2197.anInt3011, Class289.aClass354_2197.aString3016);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "om.E(" + b + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (~n == -1) {
                this.anInt5676 = class98_Sub22.readShort((byte)127);
            }
            if (b >= -92) {
                this.anIntArray5680 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "om.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final void method993(final int n) {
        try {
            super.method993((short)n);
            this.anIntArray5680 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "om.P(" + n + ')');
        }
    }
    
    @Override
    final void method998(final int n, final int n2, final int n3) {
        try {
            super.method998(n, n2, n3);
            if (~this.anInt5676 <= -1 && Class38.aD356 != null) {
                final int n4 = Class38.aD356.method11(this.anInt5676, -28755).aBoolean1822 ? 64 : 128;
                this.anIntArray5680 = Class38.aD356.method9(this.anInt5676, (byte)(-121), n4, 1.0f, false, n4);
                this.anInt5679 = n4;
                this.anInt5678 = n4;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "om.O(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method1081(final int n, final Class98_Sub46_Sub9 aClass98_Sub46_Sub9_2583, final boolean b, final int n2) {
        try {
            if (Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540) {
                int anInt3439 = 0;
                for (Class98_Sub46_Sub8 class98_Sub46_Sub8 = (Class98_Sub46_Sub8)aClass98_Sub46_Sub9_2583.aClass215_5999.method2792(-1); class98_Sub46_Sub8 != null; class98_Sub46_Sub8 = (Class98_Sub46_Sub8)aClass98_Sub46_Sub9_2583.aClass215_5999.method2787(0)) {
                    final int method2824 = Class222.method2824((byte)85, class98_Sub46_Sub8);
                    if (method2824 > anInt3439) {
                        anInt3439 = method2824;
                    }
                }
                anInt3439 += 8;
                final int n3 = 21 + aClass98_Sub46_Sub9_2583.anInt6001 * 16;
                Class98_Sub43_Sub4.anInt5938 = (Class98_Sub5_Sub3.aBoolean5539 ? 26 : 22) + 16 * aClass98_Sub46_Sub9_2583.anInt6001;
                int anInt3440 = Class246_Sub3_Sub4_Sub4.anInt6488 + Class38.anInt355;
                if (anInt3440 + anInt3439 > Class39_Sub1.anInt3593) {
                    anInt3440 = Class38.anInt355 - anInt3439;
                }
                if (b) {
                    if (~anInt3440 > -1) {
                        anInt3440 = 0;
                    }
                    int anInt3441 = n + -(Class98_Sub5_Sub3.aBoolean5539 ? 33 : 31) + 13;
                    if (Class98_Sub25.anInt4024 < n3 + anInt3441) {
                        anInt3441 = Class98_Sub25.anInt4024 - n3;
                    }
                    Class282.anInt2128 = anInt3440;
                    if (anInt3441 < 0) {
                        anInt3441 = 0;
                    }
                    Class163.anInt3518 = anInt3441;
                    Class308.aClass98_Sub46_Sub9_2583 = aClass98_Sub46_Sub9_2583;
                    Class5.anInt3439 = anInt3439;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "om.D(" + n + ',' + ((aClass98_Sub46_Sub9_2583 != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ')');
        }
    }
    
    public Class98_Sub10_Sub25() {
        super(0, false);
        this.anInt5676 = -1;
    }
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            if (n >= -76) {
                this.anIntArray5680 = null;
            }
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (super.aClass223_3859.aBoolean1683) {
                int n3 = ((~Class63.anInt492 == ~this.anInt5678) ? n2 : (this.anInt5678 * n2 / Class63.anInt492)) * this.anInt5679;
                final int[] array = method2828[0];
                final int[] array2 = method2828[1];
                final int[] array3 = method2828[2];
                if (Class25.anInt268 != this.anInt5679) {
                    for (int n4 = 0; Class25.anInt268 > n4; ++n4) {
                        final int n5 = this.anIntArray5680[n4 * this.anInt5679 / Class25.anInt268 + n3];
                        array3[n4] = Class202.method2702(n5, 255) << 1263100004;
                        array2[n4] = Class202.method2702(4080, n5 >> 136339012);
                        array[n4] = Class202.method2702(4080, n5 >> 1712612364);
                    }
                }
                else {
                    for (int n6 = 0; Class25.anInt268 > n6; ++n6) {
                        final int n7 = this.anIntArray5680[n3++];
                        array3[n6] = Class202.method2702(255, n7) << 1450547076;
                        array2[n6] = Class202.method2702(4080, n7 >> 365050084);
                        array[n6] = Class202.method2702(4080, n7 >> 1685547052);
                    }
                }
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "om.C(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub25.aLong5677 = 0L;
    }
}
