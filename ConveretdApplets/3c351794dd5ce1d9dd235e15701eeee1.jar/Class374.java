// 
// Decompiled by Procyon v0.5.30
// 

final class Class374
{
    private byte[] aByteArray3154;
    private long[] aLongArray3155;
    static int[] anIntArray3156;
    static Class147[] aClass147Array3157;
    private long[] aLongArray3158;
    static int anInt3159;
    private int anInt3160;
    private long[] aLongArray3161;
    private long[] aLongArray3162;
    private long[] aLongArray3163;
    static long[] aLongArray3164;
    private int anInt3165;
    private byte[] aByteArray3166;
    
    static final void method3980(final byte b) {
        try {
            if (b >= 120) {
                Class222.aBoolean1667 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wn.C(" + b + ')');
        }
    }
    
    final void method3981(final byte[] array, final int n, long n2) {
        try {
            int n3 = 0;
            final int n4 = 0x7 & -((int)n2 & 0x7) + 8;
            if (n != 64) {
                method3980((byte)113);
            }
            final int n5 = this.anInt3165 & 0x7;
            long n6 = n2;
            int i = 31;
            int n7 = 0;
            while (i >= 0) {
                final int n8 = n7 + ((0xFF & this.aByteArray3166[i]) - -((int)n6 & 0xFF));
                this.aByteArray3166[i] = (byte)n8;
                n6 >>>= 8;
                n7 = n8 >>> 8;
                --i;
            }
            while (n2 > 8L) {
                final int n9 = (0xFF & array[n3] << n4) | (array[1 + n3] & 0xFF) >>> 8 - n4;
                if (~n9 > -1 || n9 >= 256) {
                    throw new RuntimeException("LOGIC ERROR");
                }
                this.aByteArray3154[this.anInt3160] = (byte)Class41.method366(this.aByteArray3154[this.anInt3160], n9 >>> n5);
                ++this.anInt3160;
                this.anInt3165 += 8 - n5;
                if (~this.anInt3165 == 0xFFFFFDFF) {
                    this.method3984((byte)105);
                    final boolean b = false;
                    this.anInt3160 = (b ? 1 : 0);
                    this.anInt3165 = (b ? 1 : 0);
                }
                this.aByteArray3154[this.anInt3160] = (byte)Class202.method2702(n9 << 8 + -n5, 255);
                this.anInt3165 += n5;
                n2 -= 8L;
                ++n3;
            }
            int n10;
            if (n2 > 0L) {
                n10 = (array[n3] << n4 & 0xFF);
                this.aByteArray3154[this.anInt3160] = (byte)Class41.method366(this.aByteArray3154[this.anInt3160], n10 >>> n5);
            }
            else {
                n10 = 0;
            }
            if (n2 + n5 >= 8L) {
                ++this.anInt3160;
                n2 -= 8 - n5;
                this.anInt3165 += -n5 + 8;
                if (this.anInt3165 == 512) {
                    this.method3984((byte)125);
                    final boolean b2 = false;
                    this.anInt3160 = (b2 ? 1 : 0);
                    this.anInt3165 = (b2 ? 1 : 0);
                }
                this.aByteArray3154[this.anInt3160] = (byte)Class202.method2702(255, n10 << 8 + -n5);
                this.anInt3165 += (int)n2;
            }
            else {
                this.anInt3165 += (int)n2;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wn.F(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    final void method3982(final byte b, final int n, final byte[] array) {
        try {
            this.aByteArray3154[this.anInt3160] = (byte)Class41.method366(this.aByteArray3154[this.anInt3160], 128 >>> Class202.method2702(7, this.anInt3165));
            ++this.anInt3160;
            if (~this.anInt3160 < -33) {
                while (~this.anInt3160 > -65) {
                    this.aByteArray3154[this.anInt3160++] = 0;
                }
                this.method3984((byte)99);
                this.anInt3160 = 0;
            }
            while (this.anInt3160 < 32) {
                this.aByteArray3154[this.anInt3160++] = 0;
            }
            Class236.method2894(this.aByteArray3166, 0, this.aByteArray3154, 32, 32);
            this.method3984((byte)103);
            int n2 = 0;
            int n3 = n;
            while (~n2 > -9) {
                final long n4 = this.aLongArray3158[n2];
                array[n3] = (byte)(n4 >>> -933636680);
                array[n3 + 1] = (byte)(n4 >>> 67716592);
                array[n3 + 2] = (byte)(n4 >>> -1541782744);
                array[3 + n3] = (byte)(n4 >>> 1121681056);
                array[4 + n3] = (byte)(n4 >>> 1704348888);
                array[5 + n3] = (byte)(n4 >>> 266681360);
                array[n3 + 6] = (byte)(n4 >>> 2131746888);
                array[7 + n3] = (byte)n4;
                n3 += 8;
                ++n2;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wn.A(" + b + ',' + n + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3983(final byte b) {
        try {
            for (int n = 0; ~n > -33; ++n) {
                this.aByteArray3166[n] = 0;
            }
            this.aByteArray3154[0] = 0;
            final boolean b2 = false;
            this.anInt3160 = (b2 ? 1 : 0);
            this.anInt3165 = (b2 ? 1 : 0);
            int i = 0;
            if (b != -77) {
                method3980((byte)86);
            }
            while (i < 8) {
                this.aLongArray3158[i] = 0L;
                ++i;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wn.D(" + b + ')');
        }
    }
    
    private final void method3984(final byte b) {
        try {
            for (int i = 0, n = 0; i < 8; ++i, n += 8) {
                this.aLongArray3161[i] = Class284_Sub1_Sub1.method3367(Class35.method335(this.aByteArray3154[7 + n], 255L), Class284_Sub1_Sub1.method3367(Class35.method335(65280L, this.aByteArray3154[n + 6] << -1568727992), Class284_Sub1_Sub1.method3367(Class35.method335(255L, this.aByteArray3154[n + 5]) << 149608080, Class284_Sub1_Sub1.method3367(Class35.method335(this.aByteArray3154[4 + n], 255L) << 770901656, Class284_Sub1_Sub1.method3367(Class284_Sub1_Sub1.method3367(Class35.method335(280375465082880L, this.aByteArray3154[2 + n] << 1260466408), Class284_Sub1_Sub1.method3367(Class35.method335(255L, this.aByteArray3154[1 + n]) << 88187824, this.aByteArray3154[n] << -1365521608)), Class35.method335(1095216660480L, this.aByteArray3154[3 + n] << 1040697696))))));
            }
            for (int n2 = 0; ~n2 > -9; ++n2) {
                final long[] aLongArray3163 = this.aLongArray3163;
                final int n3 = n2;
                final long n4 = this.aLongArray3161[n2];
                final long[] aLongArray3164 = this.aLongArray3155;
                final int n5 = n2;
                final long n6 = this.aLongArray3158[n2];
                aLongArray3164[n5] = n6;
                aLongArray3163[n3] = Class284_Sub1_Sub1.method3367(n4, n6);
            }
            for (int n7 = 1; ~n7 >= -11; ++n7) {
                for (int n8 = 0; ~n8 > -9; ++n8) {
                    this.aLongArray3162[n8] = 0L;
                    for (int j = 0, n9 = 56; j < 8; ++j, n9 -= 8) {
                        this.aLongArray3162[n8] = Class284_Sub1_Sub1.method3367(this.aLongArray3162[n8], Class27.aLongArrayArray279[j][Class202.method2702(255, (int)(this.aLongArray3155[Class202.method2702(7, -j + n8)] >>> n9))]);
                    }
                }
                for (int k = 0; k < 8; ++k) {
                    this.aLongArray3155[k] = this.aLongArray3162[k];
                }
                this.aLongArray3155[0] = Class284_Sub1_Sub1.method3367(this.aLongArray3155[0], Class27.aLongArray280[n7]);
                for (int n10 = 0; ~n10 > -9; ++n10) {
                    this.aLongArray3162[n10] = this.aLongArray3155[n10];
                    int n11 = 0;
                    int n12 = 56;
                    while (~n11 > -9) {
                        this.aLongArray3162[n10] = Class284_Sub1_Sub1.method3367(this.aLongArray3162[n10], Class27.aLongArrayArray279[n11][Class202.method2702(255, (int)(this.aLongArray3163[Class202.method2702(n10 - n11, 7)] >>> n12))]);
                        n12 -= 8;
                        ++n11;
                    }
                }
                for (int n13 = 0; ~n13 > -9; ++n13) {
                    this.aLongArray3163[n13] = this.aLongArray3162[n13];
                }
            }
            for (int l = 0; l < 8; ++l) {
                this.aLongArray3158[l] = Class284_Sub1_Sub1.method3367(this.aLongArray3158[l], Class284_Sub1_Sub1.method3367(this.aLongArray3161[l], this.aLongArray3163[l]));
            }
            if (b < 98) {
                this.method3982((byte)104, -78, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wn.B(" + b + ')');
        }
    }
    
    public static void method3985(final int n) {
        try {
            Class374.aClass147Array3157 = null;
            Class374.anIntArray3156 = null;
            Class374.aLongArray3164 = null;
            if (n != 0) {
                Class374.aClass147Array3157 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wn.E(" + n + ')');
        }
    }
    
    public Class374() {
        this.anInt3160 = 0;
        this.aLongArray3163 = new long[8];
        this.aLongArray3158 = new long[8];
        this.aLongArray3162 = new long[8];
        this.aByteArray3154 = new byte[64];
        this.aLongArray3155 = new long[8];
        this.anInt3165 = 0;
        this.aLongArray3161 = new long[8];
        this.aByteArray3166 = new byte[32];
    }
    
    static {
        Class374.anIntArray3156 = new int[1];
        Class374.aLongArray3164 = new long[256];
        Class374.anInt3159 = 0;
        for (int i = 0; i < 256; ++i) {
            long n = i;
            for (int j = 0; j < 8; ++j) {
                if ((n & 0x1L) == 0x1L) {
                    n = (n >>> 1524723841 ^ 0xC96C5795D7870F42L);
                }
                else {
                    n >>>= 1;
                }
            }
            Class374.aLongArray3164[i] = n;
        }
    }
}
