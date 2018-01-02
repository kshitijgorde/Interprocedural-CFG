// 
// Decompiled by Procyon v0.5.30
// 

final class Class332_Sub2 extends Class332
{
    private boolean aBoolean5410;
    private boolean aBoolean5411;
    private int anInt5412;
    private boolean aBoolean5413;
    private int anInt5414;
    private ha_Sub3 aHa_Sub3_5415;
    private int anInt5416;
    private boolean aBoolean5417;
    private int anInt5418;
    private int anInt5419;
    private boolean aBoolean5420;
    static int anInt5421;
    private int anInt5422;
    static Class207 aClass207_5423;
    private Interface4_Impl2 anInterface4_Impl2_5424;
    
    @Override
    final void method3736(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            final int[] na = this.aHa_Sub3_5415.na(n5, n6, n3, n4);
            if (na != null) {
                for (int n7 = 0; ~na.length < ~n7; ++n7) {
                    na[n7] = Class41.method366(na[n7], -16777216);
                }
                this.method3756(n, n2, n3, n4, na, 0, n3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.J(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    @Override
    final int method3749() {
        try {
            return this.anInt5418 + this.anInt5419 + this.anInt5416;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.T()");
        }
    }
    
    public static void method3755(final int n) {
        try {
            if (n == -14320) {
                Class332_Sub2.aClass207_5423 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.A(" + n + ')');
        }
    }
    
    private final void method3756(final int n, final int n2, final int n3, final int n4, final int[] array, final int n5, final int n6) {
        try {
            this.anInterface4_Impl2_5424.method49(17779, n2, n4, array, n, n5, n6, n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((array != null) ? "{...}" : "null") + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    @Override
    final int method3737() {
        try {
            return this.anInt5414 + (this.anInt5412 + this.anInt5422);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.O()");
        }
    }
    
    @Override
    final void method3741(final int[] array) {
        try {
            array[3] = this.anInt5418;
            array[2] = this.anInt5422;
            array[1] = this.anInt5416;
            array[0] = this.anInt5414;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.S(" + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method3733(float n, float n2, float n3, float n4, float n5, float n6, final int n7, final int n8, final int n9, final int n10) {
        try {
            final Class111_Sub3 method1978 = this.aHa_Sub3_5415.method1978((byte)(-47));
            final Class111_Sub3 method1979 = this.aHa_Sub3_5415.method1957((byte)63);
            this.anInterface4_Impl2_5424.method4((byte)(-81), (!this.aBoolean5410 && !this.aBoolean5411 && (n10 & 0x1) != 0x0) ? Class284_Sub1_Sub1.aClass200_6187 : Class342.aClass200_2861);
            this.aHa_Sub3_5415.method2052(false);
            this.aHa_Sub3_5415.method2005(this.anInterface4_Impl2_5424, 118);
            this.aHa_Sub3_5415.method2001(n9, 69);
            this.aHa_Sub3_5415.method2015(n7, (byte)(-115));
            this.aHa_Sub3_5415.method2051(1, -93, Class64_Sub16.aClass65_3681);
            this.aHa_Sub3_5415.method1953(-92, Class64_Sub16.aClass65_3681, 1);
            this.aHa_Sub3_5415.method1984(2, n8);
            if (this.aBoolean5420) {
                final float n11 = this.method3737();
                final float n12 = this.method3749();
                final float n13 = (-n + n3) / n11;
                final float n14 = (n4 - n2) / n11;
                final float n15 = (-n + n5) / n12;
                final float n16 = (n6 - n2) / n12;
                final float n17 = n15 * this.anInt5416;
                final float n18 = n16 * this.anInt5416;
                final float n19 = n13 * this.anInt5414;
                final float n20 = n14 * this.anInt5414;
                final float n21 = this.anInt5422 * -n13;
                final float n22 = -n14 * this.anInt5422;
                final float n23 = -n15 * this.anInt5418;
                n = n + n19 + n17;
                n2 = n2 + n20 + n18;
                n5 = n5 + n19 + n23;
                n4 = n22 + n4 + n18;
                n3 = n17 + (n21 + n3);
                n6 = this.anInt5418 * -n16 + (n20 + n6);
            }
            method1978.method2123(1.0f, -n + n5, -n2 + n4, 0.0f, 0.0f, 0.0f, n6 - n2, -n + n3, 0.0f, (byte)123);
            method1978.method2141(-99, n2, 0.0f, n);
            method1979.method2137(this.anInterface4_Impl2_5424.method45(-8473, this.anInt5419), (byte)(-123), this.anInterface4_Impl2_5424.method42((byte)53, this.anInt5412), 1.0f);
            this.aHa_Sub3_5415.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)98);
            this.aHa_Sub3_5415.method1935(1);
            this.aHa_Sub3_5415.method2002((byte)(-88));
            this.aHa_Sub3_5415.method1985(2);
            this.aHa_Sub3_5415.method2051(1, -107, IncomingOpcode.aClass65_459);
            this.aHa_Sub3_5415.method1953(-89, IncomingOpcode.aClass65_459, 1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.BA(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ')');
        }
    }
    
    @Override
    final void method3742(final int n, final int n2, final int n3) {
        try {
            final int[] na = this.aHa_Sub3_5415.na(n, n2, this.anInt5412, this.anInt5419);
            final int[] array = new int[this.anInt5419 * this.anInt5412];
            this.anInterface4_Impl2_5424.method44(this.anInt5412, 0, (byte)87, this.anInt5419, 0, array, 0);
            if (~n3 != -1) {
                if (~n3 != 0xFFFFFFFE) {
                    if (~n3 == 0xFFFFFFFD) {
                        for (int i = 0; i < this.anInt5419; ++i) {
                            final int n4 = i * this.anInt5412;
                            for (int n5 = 0; ~this.anInt5412 < ~n5; ++n5) {
                                array[n5 + n4] = Class41.method366(Class202.method2702(na[n5 + n4], -1286409473) << 1475591704, Class202.method2702(16777215, array[n5 + n4]));
                            }
                        }
                    }
                    else if (~n3 == 0xFFFFFFFC) {
                        for (int n6 = 0; ~n6 > ~this.anInt5419; ++n6) {
                            final int n7 = n6 * this.anInt5412;
                            for (int n8 = 0; ~n8 > ~this.anInt5412; ++n8) {
                                array[n7 + n8] = Class41.method366((na[n8 + n7] != 0) ? -16777216 : 0, Class202.method2702(16777215, array[n7 - -n8]));
                            }
                        }
                    }
                }
                else {
                    for (int j = 0; j < this.anInt5419; ++j) {
                        final int n9 = j * this.anInt5412;
                        for (int k = 0; k < this.anInt5412; ++k) {
                            array[n9 + k] = Class41.method366(Class202.method2702(16777215, array[k + n9]), Class202.method2702(1304231680, na[k + n9]) << -1936896592);
                        }
                    }
                }
            }
            else {
                for (int n10 = 0; ~this.anInt5419 < ~n10; ++n10) {
                    final int n11 = n10 * this.anInt5412;
                    for (int n12 = 0; this.anInt5412 > n12; ++n12) {
                        array[n12 + n11] = Class41.method366(Class202.method2702(-16776984, na[n11 - -n12] << -1575239864), Class202.method2702(16777215, array[n12 + n11]));
                    }
                }
            }
            this.method3756(0, 0, this.anInt5412, this.anInt5419, array, 0, this.anInt5412);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.F(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method3740(final int anInt5414, final int anInt5415, final int anInt5416, final int anInt5417) {
        try {
            this.anInt5418 = anInt5417;
            this.anInt5414 = anInt5414;
            this.anInt5422 = anInt5416;
            this.anInt5416 = anInt5415;
            this.aBoolean5420 = (this.anInt5414 != 0 || ~this.anInt5416 != -1 || ~this.anInt5422 != -1 || ~this.anInt5418 != -1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.Q(" + anInt5414 + ',' + anInt5415 + ',' + anInt5416 + ',' + anInt5417 + ')');
        }
    }
    
    @Override
    final void method3728(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            final Class111_Sub3 method1978 = this.aHa_Sub3_5415.method1978((byte)111);
            final Class111_Sub3 method1979 = this.aHa_Sub3_5415.method1957((byte)(-88));
            this.anInterface4_Impl2_5424.method4((byte)(-81), Class342.aClass200_2861);
            this.aHa_Sub3_5415.method2052(false);
            this.aHa_Sub3_5415.method2005(this.anInterface4_Impl2_5424, -115);
            this.aHa_Sub3_5415.method2001(n7, 110);
            this.aHa_Sub3_5415.method2015(n5, (byte)77);
            this.aHa_Sub3_5415.method2051(1, -98, Class64_Sub16.aClass65_3681);
            this.aHa_Sub3_5415.method1953(-67, Class64_Sub16.aClass65_3681, 1);
            this.aHa_Sub3_5415.method1984(2, n6);
            final boolean b = this.aBoolean5417 && ~this.anInt5416 == -1 && ~this.anInt5418 == -1;
            final boolean b2 = this.aBoolean5413 && this.anInt5414 == 0 && this.anInt5422 == 0;
            if (!(b2 & b)) {
                if (!b2) {
                    if (b) {
                        final int i = n3 + n;
                        final int method1980 = this.method3737();
                        method1979.method2137(this.anInterface4_Impl2_5424.method45(-8473, n4), (byte)(-128), this.anInterface4_Impl2_5424.method42((byte)111, this.anInt5412), 1.0f);
                        this.aHa_Sub3_5415.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)69);
                        int n8 = n + this.anInt5414;
                        int n9 = this.anInt5412 + n8;
                        while (i >= n9) {
                            method1978.method2137(n4, (byte)(-119), this.anInt5412, 0.0f);
                            method1978.method2106(n8, n2, 0);
                            this.aHa_Sub3_5415.method1935(1);
                            n8 += method1980;
                            n9 += method1980;
                            this.aHa_Sub3_5415.method2002((byte)(-124));
                        }
                        if (~n8 > ~i) {
                            final int n10 = i - n8;
                            method1979.method2137(this.anInterface4_Impl2_5424.method45(-8473, n4), (byte)(-121), this.anInterface4_Impl2_5424.method42((byte)(-86), n10), 1.0f);
                            this.aHa_Sub3_5415.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)107);
                            method1978.method2137(n4, (byte)(-125), n10, 0.0f);
                            method1978.method2106(n8, n2, 0);
                            this.aHa_Sub3_5415.method1935(1);
                            this.aHa_Sub3_5415.method2002((byte)(-119));
                        }
                    }
                    else {
                        final int j = n2 - -n4;
                        final int n11 = n3 + n;
                        final int method1981 = this.method3737();
                        final int method1982 = this.method3749();
                        int n12 = this.anInt5416 + n2;
                        for (int n13 = this.anInt5419 + n12; j >= n13; n13 += method1982, n12 += method1982) {
                            method1979.method2137(this.anInterface4_Impl2_5424.method45(-8473, this.anInt5419), (byte)(-114), this.anInterface4_Impl2_5424.method42((byte)(-85), this.anInt5412), 1.0f);
                            this.aHa_Sub3_5415.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)64);
                            int n14 = this.anInt5414 + n;
                            int n15 = n14 - -this.anInt5412;
                            while (~n11 <= ~n15) {
                                method1978.method2137(this.anInt5419, (byte)(-125), this.anInt5412, 0.0f);
                                method1978.method2106(n14, n12, 0);
                                this.aHa_Sub3_5415.method1935(1);
                                n15 += method1981;
                                this.aHa_Sub3_5415.method2002((byte)(-128));
                                n14 += method1981;
                            }
                            if (~n14 > ~n11) {
                                final int n16 = -n14 + n11;
                                method1979.method2137(this.anInterface4_Impl2_5424.method45(-8473, this.anInt5419), (byte)(-110), this.anInterface4_Impl2_5424.method42((byte)(-55), n16), 1.0f);
                                this.aHa_Sub3_5415.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)26);
                                method1978.method2137(this.anInt5419, (byte)(-115), n16, 0.0f);
                                method1978.method2106(n14, n12, 0);
                                this.aHa_Sub3_5415.method1935(1);
                                this.aHa_Sub3_5415.method2002((byte)53);
                            }
                        }
                        if (n12 < j) {
                            final int n17 = j - n12;
                            method1979.method2137(this.anInterface4_Impl2_5424.method45(-8473, n17), (byte)(-122), this.anInterface4_Impl2_5424.method42((byte)(-100), this.anInt5412), 1.0f);
                            this.aHa_Sub3_5415.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)105);
                            int n18 = n - -this.anInt5414;
                            for (int n19 = n18 - -this.anInt5412; ~n11 <= ~n19; n19 += method1981) {
                                method1978.method2137(n17, (byte)(-119), this.anInt5412, 0.0f);
                                method1978.method2106(n18, n12, 0);
                                this.aHa_Sub3_5415.method1935(1);
                                this.aHa_Sub3_5415.method2002((byte)(-113));
                                n18 += method1981;
                            }
                            if (n18 < n11) {
                                final int n20 = n11 + -n18;
                                method1979.method2137(this.anInterface4_Impl2_5424.method45(-8473, n17), (byte)(-109), this.anInterface4_Impl2_5424.method42((byte)101, n20), 1.0f);
                                this.aHa_Sub3_5415.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)125);
                                method1978.method2137(n17, (byte)(-123), n20, 0.0f);
                                method1978.method2106(n18, n12, 0);
                                this.aHa_Sub3_5415.method1935(1);
                                this.aHa_Sub3_5415.method2002((byte)(-105));
                            }
                        }
                    }
                }
                else {
                    final int n21 = n4 + n2;
                    final int method1983 = this.method3749();
                    method1979.method2137(this.anInterface4_Impl2_5424.method45(-8473, this.anInt5419), (byte)(-119), this.anInterface4_Impl2_5424.method42((byte)(-69), n3), 1.0f);
                    this.aHa_Sub3_5415.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)49);
                    int n22 = this.anInt5416 + n2;
                    int k = n22 - -this.anInt5419;
                    while (k <= n21) {
                        method1978.method2137(this.anInt5419, (byte)(-114), n3, 0.0f);
                        method1978.method2106(n, n22, 0);
                        this.aHa_Sub3_5415.method1935(1);
                        k += method1983;
                        this.aHa_Sub3_5415.method2002((byte)(-108));
                        n22 += method1983;
                    }
                    if (~n22 > ~n21) {
                        final int n23 = -n22 + n21;
                        method1979.method2137(this.anInterface4_Impl2_5424.method45(-8473, n23), (byte)(-122), this.anInterface4_Impl2_5424.method42((byte)79, n3), 1.0f);
                        this.aHa_Sub3_5415.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)97);
                        method1978.method2137(n23, (byte)(-118), n3, 0.0f);
                        method1978.method2106(n, n22, 0);
                        this.aHa_Sub3_5415.method1935(1);
                        this.aHa_Sub3_5415.method2002((byte)(-117));
                    }
                }
            }
            else {
                method1979.method2137(this.anInterface4_Impl2_5424.method45(-8473, n4), (byte)(-123), this.anInterface4_Impl2_5424.method42((byte)119, n3), 1.0f);
                method1978.method2137(n4, (byte)(-120), n3, 0.0f);
                method1978.method2106(n, n2, 0);
                this.aHa_Sub3_5415.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)53);
                this.aHa_Sub3_5415.method1935(1);
                this.aHa_Sub3_5415.method2002((byte)(-100));
            }
            this.aHa_Sub3_5415.method1985(2);
            this.aHa_Sub3_5415.method2051(1, -79, IncomingOpcode.aClass65_459);
            this.aHa_Sub3_5415.method1953(-86, IncomingOpcode.aClass65_459, 1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.K(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    @Override
    final void method3729(int n, int n2, final aa aa, final int n3, final int n4) {
        try {
            final aa_Sub2 aa_Sub2 = (aa_Sub2)aa;
            n2 += this.anInt5416;
            n += this.anInt5414;
            final Interface4_Impl2 anInterface4_Impl2_3563 = aa_Sub2.anInterface4_Impl2_3563;
            this.anInterface4_Impl2_5424.method4((byte)(-81), Class342.aClass200_2861);
            this.aHa_Sub3_5415.method2052(false);
            this.aHa_Sub3_5415.method2005(this.anInterface4_Impl2_5424, 43);
            this.aHa_Sub3_5415.method2001(1, 117);
            this.aHa_Sub3_5415.method2015(1, (byte)(-122));
            final Class111_Sub3 method1978 = this.aHa_Sub3_5415.method1978((byte)123);
            method1978.method2137(this.anInt5419, (byte)(-109), this.anInt5412, 0.0f);
            method1978.method2106(n, n2, 0);
            this.aHa_Sub3_5415.method1935(1);
            this.aHa_Sub3_5415.method1957((byte)(-96)).method2137(this.anInterface4_Impl2_5424.method45(-8473, this.anInt5419), (byte)(-123), this.anInterface4_Impl2_5424.method42((byte)(-62), this.anInt5412), 1.0f);
            this.aHa_Sub3_5415.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)30);
            this.aHa_Sub3_5415.method1951((byte)120, 1);
            this.aHa_Sub3_5415.method2005(anInterface4_Impl2_3563, 102);
            this.aHa_Sub3_5415.method2019(Class249.aClass128_1903, Class288.aClass128_3381, 22831);
            this.aHa_Sub3_5415.method2051(0, -86, IncomingOpcode.aClass65_459);
            final Class111_Sub3 method1979 = this.aHa_Sub3_5415.method1957((byte)93);
            method1979.method2137(anInterface4_Impl2_3563.method45(-8473, this.anInt5419), (byte)(-125), anInterface4_Impl2_3563.method42((byte)103, this.anInt5412), 1.0f);
            method1979.method2141(-118, anInterface4_Impl2_3563.method45(-8473, -n4 + n2), 0.0f, anInterface4_Impl2_3563.method42((byte)58, -n3 + n));
            this.aHa_Sub3_5415.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)57);
            this.aHa_Sub3_5415.method2002((byte)(-120));
            this.aHa_Sub3_5415.method1985(2);
            this.aHa_Sub3_5415.method2051(0, -121, Class300.aClass65_2499);
            this.aHa_Sub3_5415.method2019(Class249.aClass128_1903, Class249.aClass128_1903, 22831);
            this.aHa_Sub3_5415.method2005(null, 104);
            this.aHa_Sub3_5415.method1951((byte)120, 0);
            this.aHa_Sub3_5415.method1985(2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.D(" + n + ',' + n2 + ',' + ((aa != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final void method3747(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7, final aa aa, final int n8, final int n9) {
        try {
            final Class111_Sub3 method1978 = this.aHa_Sub3_5415.method1978((byte)102);
            final Class111_Sub3 method1979 = this.aHa_Sub3_5415.method1957((byte)(-80));
            final Interface4_Impl2 anInterface4_Impl2_3563 = ((aa_Sub2)aa).anInterface4_Impl2_3563;
            this.anInterface4_Impl2_5424.method4((byte)(-81), (this.aBoolean5410 || this.aBoolean5411 || (0x1 & n7) == 0x0) ? Class342.aClass200_2861 : Class284_Sub1_Sub1.aClass200_6187);
            this.aHa_Sub3_5415.method2052(false);
            this.aHa_Sub3_5415.method2005(this.anInterface4_Impl2_5424, -120);
            this.aHa_Sub3_5415.method2001(1, 83);
            this.aHa_Sub3_5415.method2015(1, (byte)118);
            Label_0267: {
                if (!this.aBoolean5420) {
                    method1978.method2123(1.0f, n5 - n, -n2 + n4, 0.0f, 0.0f, 0.0f, n6 - n2, -n + n3, 0.0f, (byte)104);
                    method1978.method2141(-97, n2, 0.0f, n);
                    if (!client.aBoolean3553) {
                        break Label_0267;
                    }
                }
                final float n10 = this.anInt5412 / this.method3737();
                final float n11 = this.anInt5419 / this.method3749();
                method1978.method2123(1.0f, n11 * (n5 - n), (n4 - n2) * n10, 0.0f, 0.0f, 0.0f, n11 * (-n2 + n6), (n3 - n) * n10, 0.0f, (byte)92);
                method1978.method2141(-118, (this.anInt5416 + n2) * n11, 0.0f, (n + this.anInt5414) * n10);
            }
            method1979.method2137(this.anInterface4_Impl2_5424.method45(-8473, this.anInt5419), (byte)(-110), this.anInterface4_Impl2_5424.method42((byte)(-128), this.anInt5412), 1.0f);
            this.aHa_Sub3_5415.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)39);
            this.aHa_Sub3_5415.method1951((byte)120, 1);
            this.aHa_Sub3_5415.method2005(anInterface4_Impl2_3563, 73);
            this.aHa_Sub3_5415.method2019(Class249.aClass128_1903, Class288.aClass128_3381, 22831);
            this.aHa_Sub3_5415.method2051(0, -59, IncomingOpcode.aClass65_459);
            final Class111_Sub3 method1980 = this.aHa_Sub3_5415.method1957((byte)(-128));
            method1980.method2092(method1978);
            method1980.method2106(-n8, -n9, 0);
            method1980.method2138(1.0f, anInterface4_Impl2_3563.method45(-8473, 1.0f), anInterface4_Impl2_3563.method42((byte)62, 1.0f), -76);
            this.aHa_Sub3_5415.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)89);
            this.aHa_Sub3_5415.method1935(1);
            this.aHa_Sub3_5415.method2002((byte)88);
            this.aHa_Sub3_5415.method1985(2);
            this.aHa_Sub3_5415.method2051(0, -63, Class300.aClass65_2499);
            this.aHa_Sub3_5415.method2019(Class249.aClass128_1903, Class249.aClass128_1903, 22831);
            this.aHa_Sub3_5415.method2005(null, 2);
            this.aHa_Sub3_5415.method1951((byte)120, 0);
            this.aHa_Sub3_5415.method1985(2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.L(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + ((aa != null) ? "{...}" : "null") + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    @Override
    final int method3734() {
        try {
            return this.anInt5412;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.AA()");
        }
    }
    
    @Override
    final void method3745(int n, int n2, int n3, int n4, final int n5, final int n6, final int n7, final int n8) {
        try {
            final Class111_Sub3 method1978 = this.aHa_Sub3_5415.method1978((byte)4);
            final Class111_Sub3 method1979 = this.aHa_Sub3_5415.method1957((byte)103);
            this.anInterface4_Impl2_5424.method4((byte)(-81), (this.aBoolean5410 || this.aBoolean5411 || ~(0x1 & n8) == -1) ? Class342.aClass200_2861 : Class284_Sub1_Sub1.aClass200_6187);
            this.aHa_Sub3_5415.method2052(false);
            this.aHa_Sub3_5415.method2005(this.anInterface4_Impl2_5424, -123);
            this.aHa_Sub3_5415.method2001(n7, 96);
            this.aHa_Sub3_5415.method2015(n5, (byte)28);
            this.aHa_Sub3_5415.method2051(1, -121, Class64_Sub16.aClass65_3681);
            this.aHa_Sub3_5415.method1953(-111, Class64_Sub16.aClass65_3681, 1);
            this.aHa_Sub3_5415.method1984(2, n6);
            method1979.method2137(this.anInterface4_Impl2_5424.method45(-8473, this.anInt5419), (byte)(-112), this.anInterface4_Impl2_5424.method42((byte)(-52), this.anInt5412), 1.0f);
            if (this.aBoolean5420) {
                n3 = this.anInt5412 * n3 / this.method3737();
                n4 = this.anInt5419 * n4 / this.method3749();
                n += this.anInt5414 * n3 / this.anInt5412;
                n2 += n4 * this.anInt5416 / this.anInt5419;
            }
            method1978.method2137(n4, (byte)(-113), n3, 0.0f);
            method1978.method2106(n, n2, 0);
            this.aHa_Sub3_5415.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)52);
            this.aHa_Sub3_5415.method1935(1);
            this.aHa_Sub3_5415.method2002((byte)(-104));
            this.aHa_Sub3_5415.method1985(2);
            this.aHa_Sub3_5415.method2051(1, -84, IncomingOpcode.aClass65_459);
            this.aHa_Sub3_5415.method1953(-69, IncomingOpcode.aClass65_459, 1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    @Override
    final int method3731() {
        try {
            return this.anInt5419;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.U()");
        }
    }
    
    @Override
    final void method3748(int n, int n2, final int n3, final int n4, final int n5) {
        try {
            final Class111_Sub3 method1978 = this.aHa_Sub3_5415.method1978((byte)95);
            final Class111_Sub3 method1979 = this.aHa_Sub3_5415.method1957((byte)(-94));
            n2 += this.anInt5416;
            n += this.anInt5414;
            this.anInterface4_Impl2_5424.method4((byte)(-81), Class342.aClass200_2861);
            this.aHa_Sub3_5415.method2052(false);
            this.aHa_Sub3_5415.method2005(this.anInterface4_Impl2_5424, 57);
            this.aHa_Sub3_5415.method2001(n5, 91);
            this.aHa_Sub3_5415.method2015(n3, (byte)(-119));
            this.aHa_Sub3_5415.method2051(1, -73, Class64_Sub16.aClass65_3681);
            this.aHa_Sub3_5415.method1953(-107, Class64_Sub16.aClass65_3681, 1);
            this.aHa_Sub3_5415.method1984(2, n4);
            method1978.method2137(this.anInt5419, (byte)(-120), this.anInt5412, 0.0f);
            method1978.method2106(n, n2, 0);
            method1979.method2137(this.anInterface4_Impl2_5424.method45(-8473, this.anInt5419), (byte)(-111), this.anInterface4_Impl2_5424.method42((byte)52, this.anInt5412), 1.0f);
            this.aHa_Sub3_5415.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)28);
            this.aHa_Sub3_5415.method1935(1);
            this.aHa_Sub3_5415.method2002((byte)(-113));
            this.aHa_Sub3_5415.method1985(2);
            this.aHa_Sub3_5415.method2051(1, -112, IncomingOpcode.aClass65_459);
            this.aHa_Sub3_5415.method1953(-102, IncomingOpcode.aClass65_459, 1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.E(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    Class332_Sub2(final ha_Sub3 aHa_Sub3_5415, final int anInt5412, final int anInt5413, final boolean b) {
        this.anInt5416 = 0;
        this.aBoolean5420 = false;
        this.anInt5418 = 0;
        this.anInt5414 = 0;
        this.anInt5422 = 0;
        try {
            this.aHa_Sub3_5415 = aHa_Sub3_5415;
            this.anInt5412 = anInt5412;
            this.anInt5419 = anInt5413;
            (this.anInterface4_Impl2_5424 = aHa_Sub3_5415.method2006(anInt5413, anInt5412, b ? Class62.aClass164_486 : Class98_Sub40.aClass164_4190, (byte)45, Class162.aClass162_1266)).method46(true, true, 116);
            this.aBoolean5410 = (this.anInterface4_Impl2_5424.method47(12941) != anInt5412);
            this.aBoolean5411 = (~this.anInterface4_Impl2_5424.method43(116) != ~anInt5413);
            this.aBoolean5413 = (!this.aBoolean5410 && this.anInterface4_Impl2_5424.method48(-62));
            this.aBoolean5417 = (!this.aBoolean5411 && this.anInterface4_Impl2_5424.method48(-33));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.<init>(" + ((aHa_Sub3_5415 != null) ? "{...}" : "null") + ',' + anInt5412 + ',' + anInt5413 + ',' + b + ')');
        }
    }
    
    Class332_Sub2(final ha_Sub3 aHa_Sub3_5415, final int anInt5412, final int anInt5413, final int[] array, final int n, final int n2) {
        this.anInt5416 = 0;
        this.aBoolean5420 = false;
        this.anInt5418 = 0;
        this.anInt5414 = 0;
        this.anInt5422 = 0;
        try {
            this.anInt5419 = anInt5413;
            this.anInt5412 = anInt5412;
            this.aHa_Sub3_5415 = aHa_Sub3_5415;
            (this.anInterface4_Impl2_5424 = aHa_Sub3_5415.method2063(n, (byte)124, array, false, anInt5412, anInt5413, n2)).method46(true, true, 102);
            this.aBoolean5410 = (~anInt5412 != ~this.anInterface4_Impl2_5424.method47(12941));
            this.aBoolean5411 = (this.anInterface4_Impl2_5424.method43(124) != anInt5413);
            this.aBoolean5413 = (!this.aBoolean5410 && this.anInterface4_Impl2_5424.method48(-116));
            this.aBoolean5417 = (!this.aBoolean5411 && this.anInterface4_Impl2_5424.method48(-111));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pia.<init>(" + ((aHa_Sub3_5415 != null) ? "{...}" : "null") + ',' + anInt5412 + ',' + anInt5413 + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class332_Sub2.anInt5421 = 0;
    }
}
