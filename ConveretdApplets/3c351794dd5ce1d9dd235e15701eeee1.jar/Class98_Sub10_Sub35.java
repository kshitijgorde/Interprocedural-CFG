// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub35 extends Class98_Sub10
{
    boolean aBoolean5731;
    static boolean aBoolean5732;
    int anInt5733;
    int anInt5734;
    private byte[] aByteArray5735;
    private short[] aShortArray5736;
    int anInt5737;
    private short[] aShortArray5738;
    int anInt5739;
    int anInt5740;
    
    public Class98_Sub10_Sub35() {
        super(0, true);
        this.aBoolean5731 = true;
        this.aByteArray5735 = new byte[512];
        this.anInt5734 = 4;
        this.anInt5733 = 4;
        this.anInt5739 = 1638;
        this.anInt5737 = 4;
        this.anInt5740 = 0;
    }
    
    @Override
    final void method1001(final byte b) {
        try {
            this.aByteArray5735 = Class279.method3323(this.anInt5740, 512);
            this.method1108(-49);
            if (b != 66) {
                this.aByteArray5735 = null;
            }
            for (int n = this.anInt5733 - 1; ~n <= -2; --n) {
                final short n2 = this.aShortArray5736[n];
                if (n2 > 8) {
                    break;
                }
                if (n2 < -8) {
                    break;
                }
                --this.anInt5733;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "u.I(" + b + ')');
        }
    }
    
    final void method1107(final byte b, final int[] array, final int n) {
        try {
            final int n2 = this.anInt5737 * Class352.anIntArray3001[n];
            if (b > -65) {
                this.method1001((byte)102);
            }
            if (~this.anInt5733 == 0xFFFFFFFE) {
                final short n3 = this.aShortArray5736[0];
                final int n4 = this.aShortArray5738[0] << -1366922196;
                final int n5 = n4 * this.anInt5734 >> 1952462732;
                final int n6 = this.anInt5737 * n4 >> 1216263788;
                final int n7 = n4 * n2 >> 1467914284;
                final int n8 = n7 >> -342918580;
                int n9 = n8 + 1;
                if (~n9 <= ~n6) {
                    n9 = 0;
                }
                final int n10 = n7 & 0xFFF;
                final int n11 = 0xFF & this.aByteArray5735[0xFF & n8];
                final int n12 = Class151_Sub8.anIntArray5014[n10];
                final int n13 = this.aByteArray5735[n9 & 0xFF] & 0xFF;
                if (!this.aBoolean5731) {
                    for (int i = 0; i < Class25.anInt268; ++i) {
                        array[i] = this.method1109(n12, n5, 2471, Class64_Sub1.anIntArray3640[i] * this.anInt5734 * n4 >> -1987147444, n13, n11, n10) * n3 >> 907116588;
                    }
                }
                else {
                    for (int n14 = 0; ~Class25.anInt268 < ~n14; ++n14) {
                        array[n14] = (n3 * this.method1109(n12, n5, 2471, Class64_Sub1.anIntArray3640[n14] * this.anInt5734 * n4 >> -1357424628, n13, n11, n10) >> 1056873644 >> 2049191361) + 2048;
                    }
                }
            }
            else {
                final short n15 = this.aShortArray5736[0];
                if (n15 > 8 || n15 < -8) {
                    final int n16 = this.aShortArray5738[0] << -1194900116;
                    final int n17 = this.anInt5734 * n16 >> -964915124;
                    final int n18 = n16 * this.anInt5737 >> 254655212;
                    final int n19 = n2 * n16 >> -285531892;
                    final int n20 = n19 >> -387882484;
                    int n21 = 1 + n20;
                    final int n22 = n19 & 0xFFF;
                    if (~n21 <= ~n18) {
                        n21 = 0;
                    }
                    final int n23 = this.aByteArray5735[0xFF & n20] & 0xFF;
                    final int n24 = Class151_Sub8.anIntArray5014[n22];
                    final int n25 = 0xFF & this.aByteArray5735[0xFF & n21];
                    for (int n26 = 0; ~n26 > ~Class25.anInt268; ++n26) {
                        array[n26] = this.method1109(n24, n17, 2471, this.anInt5734 * Class64_Sub1.anIntArray3640[n26] * n16 >> 969281420, n25, n23, n22) * n15 >> -1725112372;
                    }
                }
                for (int n27 = 1; ~n27 > ~this.anInt5733; ++n27) {
                    final short n28 = this.aShortArray5736[n27];
                    if (n28 > 8 || n28 < -8) {
                        final int n29 = this.aShortArray5738[n27] << -700199092;
                        final int n30 = n29 * this.anInt5734 >> -678909684;
                        final int n31 = n29 * n2 >> -1367687412;
                        final int n32 = this.anInt5737 * n29 >> 773603948;
                        final int n33 = n31 >> 1104410988;
                        int n34 = n33 + 1;
                        final int n35 = n31 & 0xFFF;
                        if (n32 <= n34) {
                            n34 = 0;
                        }
                        final int n36 = 0xFF & this.aByteArray5735[0xFF & n34];
                        final int n37 = Class151_Sub8.anIntArray5014[n35];
                        final int n38 = 0xFF & this.aByteArray5735[n33 & 0xFF];
                        if (this.aBoolean5731 && ~n27 == ~(-1 + this.anInt5733)) {
                            for (int n39 = 0; ~Class25.anInt268 < ~n39; ++n39) {
                                array[n39] = 2048 + (array[n39] - -(this.method1109(n37, n30, 2471, n29 * (this.anInt5734 * Class64_Sub1.anIntArray3640[n39]) >> -1370107188, n36, n38, n35) * n28 >> 1674270924) >> 1891912289);
                            }
                        }
                        else {
                            for (int n40 = 0; ~n40 > ~Class25.anInt268; ++n40) {
                                final int method1109 = this.method1109(n37, n30, 2471, Class64_Sub1.anIntArray3640[n40] * this.anInt5734 * n29 >> 656908716, n36, n38, n35);
                                final int n41 = n40;
                                array[n41] += n28 * method1109 >> 591357100;
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "u.H(" + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (n != 0) {
                if (n != 1) {
                    if (n != 2) {
                        if (n != 3) {
                            if (~n != 0xFFFFFFFB) {
                                if (n != 5) {
                                    if (~n == 0xFFFFFFF9) {
                                        this.anInt5737 = class98_Sub22.readUnsignedByte((byte)(-103));
                                    }
                                }
                                else {
                                    this.anInt5734 = class98_Sub22.readUnsignedByte((byte)(-125));
                                }
                            }
                            else {
                                this.anInt5740 = class98_Sub22.readUnsignedByte((byte)70);
                            }
                        }
                        else {
                            final int unsignedByte = class98_Sub22.readUnsignedByte((byte)90);
                            this.anInt5737 = unsignedByte;
                            this.anInt5734 = unsignedByte;
                        }
                    }
                    else {
                        this.anInt5739 = class98_Sub22.readUShort(false);
                        if (this.anInt5739 < 0) {
                            this.aShortArray5736 = new short[this.anInt5733];
                            for (int n2 = 0; this.anInt5733 > n2; ++n2) {
                                this.aShortArray5736[n2] = (short)class98_Sub22.readUShort(false);
                            }
                        }
                    }
                }
                else {
                    this.anInt5733 = class98_Sub22.readUnsignedByte((byte)(-103));
                }
            }
            else {
                this.aBoolean5731 = (~class98_Sub22.readUnsignedByte((byte)91) == 0xFFFFFFFE);
            }
            if (b > -92) {
                this.aByteArray5735 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "u.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    private final void method1108(final int n) {
        try {
            if (this.anInt5739 <= 0) {
                if (this.aShortArray5736 != null && ~this.anInt5733 == ~this.aShortArray5736.length) {
                    this.aShortArray5738 = new short[this.anInt5733];
                    for (int i = 0; i < this.anInt5733; ++i) {
                        this.aShortArray5738[i] = (short)Math.pow(2.0, i);
                    }
                }
            }
            else {
                this.aShortArray5736 = new short[this.anInt5733];
                this.aShortArray5738 = new short[this.anInt5733];
                for (int n2 = 0; this.anInt5733 > n2; ++n2) {
                    this.aShortArray5736[n2] = (short)(4096.0 * Math.pow(this.anInt5739 / 4096.0f, n2));
                    this.aShortArray5738[n2] = (short)Math.pow(2.0, n2);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "u.K(" + n + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            if (n != 255) {
                this.aByteArray5735 = null;
            }
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                this.method1107((byte)(-81), method237, n2);
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "u.G(" + n + ',' + n2 + ')');
        }
    }
    
    private final int method1109(final int n, final int n2, final int n3, int n4, final int n5, final int n6, final int n7) {
        try {
            final int n8 = n4 >> 1600728396;
            int n9 = 1 + n8;
            if (~n9 <= ~n2) {
                n9 = 0;
            }
            final int n10 = n8 & 0xFF;
            n4 &= 0xFFF;
            final int n11 = n4 - 4096;
            if (n3 != 2471) {
                this.method1109(63, -5, -64, -95, -78, -54, -54);
            }
            final int n12 = n7 - 4096;
            final int n13 = n9 & 0xFF;
            final int n14 = Class151_Sub8.anIntArray5014[n4];
            final byte b = (byte)(0x3 & this.aByteArray5735[n10 + n6]);
            int n15;
            if (~b >= -2) {
                n15 = ((b != 0) ? (-n4 + n7) : (n4 - -n7));
            }
            else {
                n15 = ((b == 2) ? (n4 - n7) : (-n7 + -n4));
            }
            final byte b2 = (byte)(0x3 & this.aByteArray5735[n13 + n6]);
            int n16;
            if (~b2 >= -2) {
                n16 = ((~b2 != -1) ? (-n11 + n7) : (n11 + n7));
            }
            else {
                n16 = ((b2 != 2) ? (-n11 + -n7) : (n11 - n7));
            }
            final int n17 = (n14 * (n16 - n15) >> 911331756) + n15;
            final byte b3 = (byte)(this.aByteArray5735[n5 + n10] & 0x3);
            int n18;
            if (b3 <= 1) {
                n18 = ((~b3 != -1) ? (n12 - n4) : (n4 + n12));
            }
            else {
                n18 = ((b3 == 2) ? (n4 - n12) : (-n12 + -n4));
            }
            final byte b4 = (byte)(0x3 & this.aByteArray5735[n13 + n5]);
            int n19;
            if (b4 > 1) {
                n19 = ((b4 != 2) ? (-n11 + -n12) : (n11 - n12));
            }
            else {
                n19 = ((b4 != 0) ? (n12 - n11) : (n11 + n12));
            }
            return n17 + (n * (n18 + ((n19 - n18) * n14 >> -1060286324) - n17) >> 1203842508);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "u.J(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub35.aBoolean5732 = false;
    }
}
