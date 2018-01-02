// 
// Decompiled by Procyon v0.5.30
// 

final class Class46
{
    private Class156_Sub1 aClass156_Sub1_386;
    private Interface8 anInterface8_387;
    static float aFloat388;
    private Class33 aClass33_389;
    private int anInt390;
    private Class42_Sub1 aClass42_Sub1_391;
    boolean aBoolean392;
    private int anInt393;
    private ha_Sub1 aHa_Sub1_394;
    private int anInt395;
    private int anInt396;
    
    private final void method433(final byte b, final int n, final Interface8 interface8) {
        try {
            if (b <= 35) {
                this.method438(-46, -60, 86, null);
            }
            if (n != 0) {
                this.method434(0);
                this.aHa_Sub1_394.method1863(1, this.aClass42_Sub1_391);
                this.aHa_Sub1_394.method1865(n, 4, interface8, false, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "df.E(" + b + ',' + n + ',' + ((interface8 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method434(final int n) {
        try {
            if (this.aBoolean392) {
                this.aBoolean392 = false;
                final byte[] aByteArray321 = this.aClass33_389.aByteArray321;
                final byte[] aByteArray322 = this.aHa_Sub1_394.aByteArray4469;
                int anInt396 = 0;
                final int anInt397 = this.aClass33_389.anInt314;
                int n2 = this.anInt390 - -(this.aClass33_389.anInt314 * this.anInt395);
                for (int n3 = -128; ~n3 > -1; ++n3) {
                    anInt396 = -anInt396 + (anInt396 << -525962840);
                    for (int i = -128; i < 0; ++i) {
                        if (~aByteArray321[n2++] != -1) {
                            ++anInt396;
                        }
                    }
                    n2 += -128 + anInt397;
                }
                if (this.aClass42_Sub1_391 != null && anInt396 == this.anInt396) {
                    this.aBoolean392 = false;
                }
                else {
                    this.anInt396 = anInt396;
                    int n4 = this.anInt395 * anInt397 + this.anInt390;
                    int n5 = n;
                    for (int j = -128; j < 0; ++j) {
                        for (int k = -128; k < 0; ++k) {
                            if (aByteArray321[n4] == 0) {
                                int n6 = 0;
                                if (aByteArray321[-1 + n4] != 0) {
                                    ++n6;
                                }
                                if (~aByteArray321[n4 + 1] != -1) {
                                    ++n6;
                                }
                                if (~aByteArray321[n4 + -anInt397] != -1) {
                                    ++n6;
                                }
                                if (~aByteArray321[n4 + anInt397] != -1) {
                                    ++n6;
                                }
                                aByteArray322[n5++] = (byte)(17 * n6);
                            }
                            else {
                                aByteArray322[n5++] = 68;
                            }
                            ++n4;
                        }
                        n4 += this.aClass33_389.anInt314 - 128;
                    }
                    if (this.aClass42_Sub1_391 == null) {
                        (this.aClass42_Sub1_391 = new Class42_Sub1(this.aHa_Sub1_394, 3553, 6406, 128, 128, false, this.aHa_Sub1_394.aByteArray4469, 6406, false)).method383(false, n + 10242, false);
                        this.aClass42_Sub1_391.method372(n ^ 0xFFFF929D, true);
                    }
                    else {
                        this.aClass42_Sub1_391.method378(128, 6406, false, 0, this.aHa_Sub1_394.aByteArray4469, 0, (byte)(-80), 0, 0, 128);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "df.D(" + n + ')');
        }
    }
    
    static final void method435(final byte b) {
        try {
            for (Class98_Sub46_Sub9 class98_Sub46_Sub9 = (Class98_Sub46_Sub9)Class98_Sub18.aClass215_3949.method2792(-1); class98_Sub46_Sub9 != null; class98_Sub46_Sub9 = (Class98_Sub46_Sub9)Class98_Sub18.aClass215_3949.method2787(0)) {
                if (~class98_Sub46_Sub9.anInt6001 < -2) {
                    class98_Sub46_Sub9.anInt6001 = 0;
                    Class98_Sub46_Sub16.aClass79_6046.method805(((Class98_Sub46_Sub8)class98_Sub46_Sub9.aClass215_5999.aClass98_Sub46_1615.aClass98_Sub46_4262).aLong5991, class98_Sub46_Sub9, (byte)(-80));
                    class98_Sub46_Sub9.aClass215_5999.method2786(16711680);
                }
            }
            Class64_Sub12.anInt3672 = 0;
            if (b >= 70) {
                Class359.anInt3058 = 0;
                Class33.aClass148_315.method2422((byte)47);
                Class98_Sub47.aClass377_4274.method3994(-96);
                Class98_Sub18.aClass215_3949.method2786(16711680);
                Class157.method2506(127, Class331.aClass98_Sub46_Sub8_2803);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "df.B(" + b + ')');
        }
    }
    
    static final void method436(final Object[] array, final boolean b, final long[] array2) {
        try {
            Class98_Sub46_Sub10.method1566(array2, 0, (byte)(-116), array2.length - 1, array);
            if (b) {
                Class46.aFloat388 = 1.1995486f;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "df.F(" + ((array != null) ? "{...}" : "null") + ',' + b + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method437(final int n) {
        try {
            if (n != 6401) {
                Class46.aFloat388 = 0.89933157f;
            }
            this.method433((byte)87, this.anInt393, this.anInterface8_387);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "df.C(" + n + ')');
        }
    }
    
    final void method438(final int n, final int n2, final int n3, final byte[] array) {
        try {
            if (n2 != 0) {
                this.anInt396 = -35;
            }
            this.aClass156_Sub1_386.method20((byte)(-47), array, n * this.aHa_Sub1_394.method1839(n3, -5122), n3);
            this.method433((byte)63, n, this.aClass156_Sub1_386);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "df.G(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method439(final int anInt305) {
        Class32.anInt305 = anInt305;
        for (int i = 0; i < Class366.anInt3112; ++i) {
            for (int j = 0; j < Class64_Sub9.anInt3662; ++j) {
                if (Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[anInt305][i][j] == null) {
                    Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[anInt305][i][j] = new Class172(anInt305);
                }
            }
        }
    }
    
    Class46(final ha_Sub1 aHa_Sub1_394, final Class33 aClass33_389, final s_Sub1 s_Sub1, final int n, final int n2, final int n3, final int anInt390, final int anInt391) {
        this.aBoolean392 = true;
        this.anInt396 = -1;
        try {
            this.anInt390 = anInt390;
            this.anInt395 = anInt391;
            this.aHa_Sub1_394 = aHa_Sub1_394;
            this.aClass33_389 = aClass33_389;
            final int i = 1 << n3;
            int anInt392 = 0;
            final int n4 = n << n3;
            final int n5 = n2 << n3;
            for (int n6 = 0; ~i < ~n6; ++n6) {
                int n7 = s_Sub1.anInt2203 * (n6 + n5) + n4;
                for (int n8 = 0; i > n8; ++n8) {
                    final short[] array = s_Sub1.aShortArrayArray5196[n7++];
                    if (array != null) {
                        anInt392 += array.length;
                    }
                }
            }
            this.anInt393 = anInt392;
            if (~anInt392 < -1) {
                final Class98_Sub22 class98_Sub22 = new Class98_Sub22(2 * anInt392);
                if (!this.aHa_Sub1_394.aBoolean4397) {
                    for (int n9 = 0; i > n9; ++n9) {
                        int n10 = n4 + s_Sub1.anInt2203 * (n9 + n5);
                        for (int j = 0; j < i; ++j) {
                            final short[] array2 = s_Sub1.aShortArrayArray5196[n10++];
                            if (array2 != null) {
                                for (int n11 = 0; ~array2.length < ~n11; ++n11) {
                                    class98_Sub22.method1247(array2[n11] & 0xFFFF, 4);
                                }
                            }
                        }
                    }
                }
                else {
                    for (int n12 = 0; i > n12; ++n12) {
                        int n13 = n4 + s_Sub1.anInt2203 * (n12 + n5);
                        for (int n14 = 0; ~n14 > ~i; ++n14) {
                            final short[] array3 = s_Sub1.aShortArrayArray5196[n13++];
                            if (array3 != null) {
                                for (int k = 0; k < array3.length; ++k) {
                                    class98_Sub22.writeShort(array3[k] & 0xFFFF, 1571862888);
                                }
                            }
                        }
                    }
                }
                this.anInterface8_387 = this.aHa_Sub1_394.method1838(5123, class98_Sub22.aByteArray3992, 7, false, class98_Sub22.anInt3991);
                this.aClass156_Sub1_386 = new Class156_Sub1(this.aHa_Sub1_394, 5123, null, 1);
            }
            else {
                this.aClass42_Sub1_391 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "df.<init>(" + ((aHa_Sub1_394 != null) ? "{...}" : "null") + ',' + ((aClass33_389 != null) ? "{...}" : "null") + ',' + ((s_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + anInt390 + ',' + anInt391 + ')');
        }
    }
}
