import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub3_Sub4_Sub3 extends Class246_Sub3_Sub4
{
    private int anInt6444;
    int anInt6445;
    static OutgoingOpcode aClass171_6446;
    private int anInt6447;
    private Class246_Sub5 aClass246_Sub5_6448;
    private boolean aBoolean6449;
    boolean aBoolean6450;
    static int[] anIntArray6451;
    private int anInt6452;
    private int anInt6453;
    private int anInt6454;
    private int anInt6455;
    private int anInt6456;
    static IncomingOpcode aClass58_6457;
    private Class97 aClass97_6458;
    
    Class246_Sub3_Sub4_Sub3(final int anInt6455, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int anInt6456) {
        super(n3, n4, n5, n6, n7, n8, n9, n10, n11, false, (byte)0);
        this.anInt6447 = 0;
        this.aBoolean6449 = true;
        this.anInt6444 = 0;
        this.anInt6454 = -1;
        this.anInt6456 = 0;
        this.aBoolean6450 = false;
        this.anInt6452 = 0;
        this.anInt6453 = 0;
        try {
            this.anInt6453 = anInt6456;
            this.anInt6445 = n2 - -n;
            this.anInt6455 = anInt6455;
            final int anInt6457 = Class196.aClass304_1509.method3564(2, this.anInt6455).anInt910;
            Label_0143: {
                if (anInt6457 != -1) {
                    this.aClass97_6458 = Class151_Sub7.aClass183_5001.method2623(anInt6457, 16383);
                    this.aBoolean6450 = false;
                    if (!client.aBoolean3553) {
                        break Label_0143;
                    }
                }
                this.aBoolean6450 = true;
            }
            if (~n2 == ~this.anInt6445) {
                Class349.method3840((byte)(-127), this, this.anInt6456, this.aClass97_6458);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.<init>(" + anInt6455 + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ',' + n11 + ',' + anInt6456 + ')');
        }
    }
    
    @Override
    final boolean method2982(final byte b) {
        try {
            if (b >= -70) {
                this.anInt6453 = -72;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.HA(" + b + ')');
        }
    }
    
    final void method3067(final int n) {
        try {
            if (this.aClass246_Sub5_6448 != null) {
                this.aClass246_Sub5_6448.method3114();
            }
            if (n <= 71) {
                this.method2981(null, (byte)(-50), false, 126, null, 99, 56);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.B(" + n + ')');
        }
    }
    
    private final void method3068(final Class146 class146, final ha ha, final int n, final Class111 class147) {
        try {
            class146.method2343(class147);
            final Class87[] method2320 = class146.method2320();
            final Class35[] method2321 = class146.method2322();
            if ((this.aClass246_Sub5_6448 == null || this.aClass246_Sub5_6448.aBoolean5099) && (method2320 != null || method2321 != null)) {
                this.aClass246_Sub5_6448 = Class246_Sub5.method3117(Class215.anInt1614, true);
            }
            if (n != -17770) {
                this.method2975(null, 84);
            }
            if (this.aClass246_Sub5_6448 != null) {
                this.aClass246_Sub5_6448.method3120(ha, Class215.anInt1614, method2320, method2321, false);
                this.aClass246_Sub5_6448.method3123(super.aByte5088, super.aShort6158, super.aShort6160, super.aShort6157, super.aShort6159);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.E(" + ((class146 != null) ? "{...}" : "null") + ',' + ((ha != null) ? "{...}" : "null") + ',' + n + ',' + ((class147 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final Class146 method3069(final ha ha, final int n, final int n2, final int n3) {
        try {
            final Class107 method3564 = Class196.aClass304_1509.method3564(2, n3);
            final s s = Class98_Sub46_Sub2_Sub2.aSArray6298[super.aByte5088];
            final s s2 = (super.aByte5081 < 3) ? Class98_Sub46_Sub2_Sub2.aSArray6298[super.aByte5081 + 1] : null;
            if (n2 != -18981) {
                this.method3067(92);
            }
            if (this.aBoolean6450) {
                return method3564.method1722(ha, Class151_Sub7.aClass183_5001, -1, n, super.anInt5084, true, s2, super.anInt5089, 0, s, super.anInt5079, -1, (byte)2);
            }
            return method3564.method1722(ha, Class151_Sub7.aClass183_5001, this.anInt6456, n, super.anInt5084, true, s2, super.anInt5089, this.anInt6452, s, super.anInt5079, this.anInt6454, (byte)2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.C(" + ((ha != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method2981(final Class246_Sub3 class246_Sub3, final byte b, final boolean b2, final int n, final ha ha, final int n2, final int n3) {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.CA(" + ((class246_Sub3 != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ',' + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    protected final void finalize() {
        try {
            if (this.aClass246_Sub5_6448 != null) {
                this.aClass246_Sub5_6448.method3114();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.finalize()");
        }
    }
    
    @Override
    final int method2990(final int n) {
        try {
            if (n != 0) {
                return -58;
            }
            return this.anInt6444;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.J(" + n + ')');
        }
    }
    
    static final void method3070(final int n) {
        try {
            if (n > -2) {
                Class246_Sub3_Sub4_Sub3.aClass171_6446 = null;
            }
            final Class98_Sub17 class98_Sub17 = (Class98_Sub17)Class167.aClass148_1284.method2418(32);
            final boolean b = Class255.aClass293_3208 != null || ~Class156_Sub2.anInt3423 < -1;
            final int method1155 = class98_Sub17.method1155(-115);
            final int method1156 = class98_Sub17.method1151(112);
            if (b) {
                Class21_Sub4.anInt5396 = 1;
            }
            if (b) {
                Class347.aClass98_Sub46_Sub8_2908 = Class266.aClass98_Sub46_Sub8_1994;
            }
            else {
                PacketSender.method3604(method1156, (byte)78, method1155, Class266.aClass98_Sub46_Sub8_1994);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.D(" + n + ')');
        }
    }
    
    @Override
    final Class246_Sub1 method2975(final ha ha, final int n) {
        try {
            final Class146 method3069 = this.method3069(ha, 0x800 | ((~this.anInt6453 != -1) ? 5 : 0), -18981, this.anInt6455);
            if (method3069 == null) {
                return null;
            }
            if (~this.anInt6453 != -1) {
                method3069.a(2048 * this.anInt6453);
            }
            if (n > -12) {
                this.anInt6452 = -121;
            }
            final Class111 method3070 = ha.method1793();
            method3070.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
            this.method3068(method3069, ha, -17770, method3070);
            final Class246_Sub1 method3071 = Class94.method915(1, (byte)(-47), false);
            if (Class239.aBoolean1839) {
                method3069.method2329(method3070, method3071.aClass246_Sub6Array5067[0], Class16.anInt197, 0);
            }
            else {
                method3069.method2325(method3070, method3071.aClass246_Sub6Array5067[0], 0);
            }
            if (this.aClass246_Sub5_6448 != null) {
                final Class242 method3072 = this.aClass246_Sub5_6448.method3116();
                if (!Class239.aBoolean1839) {
                    ha.method1820(method3072);
                }
                else {
                    ha.method1785(method3072, Class16.anInt197);
                }
            }
            this.aBoolean6449 = method3069.F();
            this.anInt6444 = method3069.fa();
            this.anInt6447 = method3069.ma();
            return method3071;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.QA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final void method3071(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            if (n2 == -1) {
                for (int n6 = 0; ~Class69_Sub2.anInt5335 < ~n6; ++n6) {
                    final Rectangle rectangle = Class98_Sub35.aRectangleArray4144[n6];
                    if (n4 < rectangle.width + rectangle.x && ~rectangle.x > ~(n3 + n4) && ~n5 > ~(rectangle.y - -rectangle.height) && n + n5 > rectangle.y) {
                        aa_Sub3.aBooleanArray3574[n6] = true;
                    }
                }
                Class93_Sub1_Sub1.method908(n + n5, n5, false, n4, n3 + n4);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    @Override
    final int method2985(final boolean b) {
        try {
            if (b) {
                this.anInt6447 = 45;
            }
            return this.anInt6447;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.O(" + b + ')');
        }
    }
    
    @Override
    final boolean method2976(final int n, final ha ha, final byte b, final int n2) {
        try {
            return b < 59;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.TA(" + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + n2 + ')');
        }
    }
    
    public static void method3072(final int n) {
        try {
            Class246_Sub3_Sub4_Sub3.aClass171_6446 = null;
            Class246_Sub3_Sub4_Sub3.anIntArray6451 = null;
            Class246_Sub3_Sub4_Sub3.aClass58_6457 = null;
            if (n != 0) {
                Class246_Sub3_Sub4_Sub3.aClass58_6457 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.K(" + n + ')');
        }
    }
    
    final void method3073(final byte b, final int n) {
        try {
            if (!this.aBoolean6450) {
                this.anInt6452 += n;
                while (~this.anInt6452 < ~this.aClass97_6458.anIntArray811[this.anInt6456]) {
                    this.anInt6452 -= this.aClass97_6458.anIntArray811[this.anInt6456];
                    ++this.anInt6456;
                    if (this.anInt6456 >= this.aClass97_6458.anIntArray818.length) {
                        this.aBoolean6450 = true;
                        break;
                    }
                }
                if (!this.aBoolean6450) {
                    Class349.method3840((byte)(-126), this, this.anInt6456, this.aClass97_6458);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.G(" + b + ',' + n + ')');
        }
    }
    
    @Override
    final void method2988(final ha ha, final int n) {
        try {
            final Class146 method3069 = this.method3069(ha, 0, -18981, this.anInt6455);
            if (method3069 != null) {
                final Class111 method3070 = ha.method1793();
                method3070.method2100(super.anInt5084, super.anInt5089, super.anInt5079);
                this.method3068(method3069, ha, -17770, method3070);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.MA(" + ((ha != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final boolean method2978(final int n) {
        try {
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.H(" + n + ')');
        }
    }
    
    @Override
    final void method2992(final byte b) {
        try {
            if (b == -73) {
                throw new IllegalStateException();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.DA(" + b + ')');
        }
    }
    
    @Override
    final Class228 method2974(final byte b, final ha ha) {
        try {
            if (b != -53) {
                Class246_Sub3_Sub4_Sub3.anIntArray6451 = null;
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.KA(" + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method2987(final int n) {
        try {
            return this.aBoolean6449;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "or.I(" + n + ')');
        }
    }
    
    static {
        Class246_Sub3_Sub4_Sub3.anIntArray6451 = new int[1000];
        Class246_Sub3_Sub4_Sub3.aClass171_6446 = new OutgoingOpcode(2, -1);
        Class246_Sub3_Sub4_Sub3.aClass58_6457 = new IncomingOpcode(55, -1);
    }
}
