import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

class Class268
{
    private Class98_Sub31 aClass98_Sub31_2003;
    private int anInt2004;
    int[] anIntArray2005;
    private long aLong2006;
    static int anInt2007;
    private boolean aBoolean2008;
    private long aLong2009;
    int anInt2010;
    private boolean aBoolean2011;
    private int anInt2012;
    private int anInt2013;
    private int anInt2014;
    private int anInt2015;
    int anInt2016;
    private long aLong2017;
    private int anInt2018;
    private Class98_Sub31[] aClass98_Sub31Array2019;
    private Class98_Sub31[] aClass98_Sub31Array2020;
    
    static final void method3248(final int n) {
        try {
            if (n != 0) {
                Class268.anInt2007 = -86;
            }
            Class76_Sub4.aClass377_3738.method3994(-71);
            Class98_Sub10_Sub34.aClass215_5728.method2786(16711680);
            Class367.aClass215_3545.method2786(16711680);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qn.F(" + n + ')');
        }
    }
    
    final synchronized void method3249(final byte b) {
        try {
            this.aBoolean2011 = true;
            try {
                this.method3259();
            }
            catch (Exception ex2) {
                this.method3262();
                this.aLong2017 = 2000L + Class343.method3819(-47);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qn.H(" + b + ')');
        }
    }
    
    void method3250(final int n) throws Exception {
    }
    
    private final void method3251(final int n, final Class98_Sub31 aClass98_Sub31_4101, final int anInt4103) {
        try {
            final int n2 = anInt4103 >> -630782779;
            final Class98_Sub31 class98_Sub31 = this.aClass98_Sub31Array2020[n2];
            Label_0043: {
                if (class98_Sub31 == null) {
                    this.aClass98_Sub31Array2019[n2] = aClass98_Sub31_4101;
                    if (!client.aBoolean3553) {
                        break Label_0043;
                    }
                }
                class98_Sub31.aClass98_Sub31_4101 = aClass98_Sub31_4101;
            }
            this.aClass98_Sub31Array2020[n2] = aClass98_Sub31_4101;
            aClass98_Sub31_4101.anInt4103 = anInt4103;
            if (n != -16680) {
                this.anInt2014 = 29;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qn.M(" + n + ',' + ((aClass98_Sub31_4101 != null) ? "{...}" : "null") + ',' + anInt4103 + ')');
        }
    }
    
    final synchronized void method3252(final int n, final Class98_Sub31 aClass98_Sub31_2003) {
        try {
            this.aClass98_Sub31_2003 = aClass98_Sub31_2003;
            if (n != 0) {
                this.anIntArray2005 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qn.A(" + n + ',' + ((aClass98_Sub31_2003 != null) ? "{...}" : "null") + ')');
        }
    }
    
    void method3253(final Component component) throws Exception {
    }
    
    static final void method3254(final r r, final int n, final int n2, final int n3, final boolean[] array) {
        if (Class78.aSArray594 != Class81.aSArray618) {
            final int method3417 = Class98_Sub46_Sub2_Sub2.aSArray6298[n].method3417(n2, n3, true);
            for (int i = 0; i <= n; ++i) {
                if (array == null || array[i]) {
                    final s s = Class98_Sub46_Sub2_Sub2.aSArray6298[i];
                    if (s != null) {
                        s.wa(r, n2, method3417 - s.method3417(n2, n3, true), n3, 0, false);
                    }
                }
            }
        }
    }
    
    private final void method3255(final int[] array, final int n) {
        int n2 = n;
        if (Class151_Sub7.aBoolean5007) {
            n2 <<= 1;
        }
        Class236.method2893(array, 0, n2);
        this.anInt2015 -= n;
        if (this.aClass98_Sub31_2003 != null && this.anInt2015 <= 0) {
            this.anInt2015 += Class64_Sub15.anInt3678 >> 4;
            Class250.method3167((byte)(-32), this.aClass98_Sub31_2003);
            this.method3251(-16680, this.aClass98_Sub31_2003, this.aClass98_Sub31_2003.method1323());
            int n3 = 0;
            int i = 255;
            int n4 = 7;
        Label_0405:
            while (i != 0) {
                int n5;
                int n6;
                if (n4 < 0) {
                    n5 = (n4 & 0x3);
                    n6 = -(n4 >> 2);
                }
                else {
                    n5 = n4;
                    n6 = 0;
                }
                for (int j = i >>> n5 & 0x11111111; j != 0; j >>>= 4) {
                    if ((j & 0x1) != 0x0) {
                        i &= ~(1 << n5);
                        Class98_Sub31 class98_Sub31 = null;
                        Class98_Sub31 aClass98_Sub31_4101 = this.aClass98_Sub31Array2019[n5];
                        while (aClass98_Sub31_4101 != null) {
                            final Class98_Sub24 aClass98_Sub24_4104 = aClass98_Sub31_4101.aClass98_Sub24_4104;
                            if (aClass98_Sub24_4104 != null && aClass98_Sub24_4104.anInt4008 > n6) {
                                i |= 1 << n5;
                                class98_Sub31 = aClass98_Sub31_4101;
                                aClass98_Sub31_4101 = aClass98_Sub31_4101.aClass98_Sub31_4101;
                            }
                            else {
                                aClass98_Sub31_4101.aBoolean4102 = true;
                                final int method1326 = aClass98_Sub31_4101.method1326();
                                n3 += method1326;
                                if (aClass98_Sub24_4104 != null) {
                                    final Class98_Sub24 class98_Sub32 = aClass98_Sub24_4104;
                                    class98_Sub32.anInt4008 += method1326;
                                }
                                if (n3 >= this.anInt2004) {
                                    break Label_0405;
                                }
                                Class98_Sub31 class98_Sub33 = aClass98_Sub31_4101.method1322();
                                if (class98_Sub33 != null) {
                                    final int anInt4103 = aClass98_Sub31_4101.anInt4103;
                                    while (class98_Sub33 != null) {
                                        this.method3251(-16680, class98_Sub33, anInt4103 * class98_Sub33.method1323() >> 8);
                                        class98_Sub33 = aClass98_Sub31_4101.method1327();
                                    }
                                }
                                final Class98_Sub31 aClass98_Sub31_4102 = aClass98_Sub31_4101.aClass98_Sub31_4101;
                                aClass98_Sub31_4101.aClass98_Sub31_4101 = null;
                                if (class98_Sub31 == null) {
                                    this.aClass98_Sub31Array2019[n5] = aClass98_Sub31_4102;
                                }
                                else {
                                    class98_Sub31.aClass98_Sub31_4101 = aClass98_Sub31_4102;
                                }
                                if (aClass98_Sub31_4102 == null) {
                                    this.aClass98_Sub31Array2020[n5] = class98_Sub31;
                                }
                                aClass98_Sub31_4101 = aClass98_Sub31_4102;
                            }
                        }
                    }
                    n5 += 4;
                    ++n6;
                }
                --n4;
            }
            for (int k = 0; k < 8; ++k) {
                Class98_Sub31 class98_Sub34 = this.aClass98_Sub31Array2019[k];
                this.aClass98_Sub31Array2019[k] = (this.aClass98_Sub31Array2020[k] = null);
                while (class98_Sub34 != null) {
                    final Class98_Sub31 aClass98_Sub31_4103 = class98_Sub34.aClass98_Sub31_4101;
                    class98_Sub34.aClass98_Sub31_4101 = null;
                    class98_Sub34 = aClass98_Sub31_4103;
                }
            }
        }
        if (this.anInt2015 < 0) {
            this.anInt2015 = 0;
        }
        if (this.aClass98_Sub31_2003 != null) {
            this.aClass98_Sub31_2003.method1325(array, 0, n);
        }
        this.aLong2006 = Class343.method3819(-47);
    }
    
    final synchronized void method3256(final byte b) {
        try {
            if (Class177.aClass103_1375 != null) {
                boolean b2 = true;
                for (int n = 0; ~n > -3; ++n) {
                    if (Class177.aClass103_1375.aClass268Array894[n] == this) {
                        Class177.aClass103_1375.aClass268Array894[n] = null;
                    }
                    if (Class177.aClass103_1375.aClass268Array894[n] != null) {
                        b2 = false;
                    }
                }
                if (b2) {
                    Class177.aClass103_1375.aBoolean893 = true;
                    while (Class177.aClass103_1375.aBoolean895) {
                        Class246_Sub7.method3131(0, 50L);
                    }
                    Class177.aClass103_1375 = null;
                }
            }
            this.method3262();
            this.anIntArray2005 = null;
            this.aBoolean2008 = true;
            if (b <= 24) {
                this.anInt2013 = -23;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qn.G(" + b + ')');
        }
    }
    
    void method3257() throws Exception {
    }
    
    int method3258() throws Exception {
        try {
            return this.anInt2010;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qn.D()");
        }
    }
    
    void method3259() throws Exception {
    }
    
    private final void method3260(final int n, final int n2) {
        try {
            this.anInt2015 -= n;
            if (this.anInt2015 < 0) {
                this.anInt2015 = 0;
            }
            if (this.aClass98_Sub31_2003 != null) {
                this.aClass98_Sub31_2003.method1321(n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qn.O(" + n + ',' + n2 + ')');
        }
    }
    
    final synchronized void method3261(final byte b) {
        try {
            if (!this.aBoolean2008) {
                long aLong2006 = Class343.method3819(-47);
                try {
                    if (this.aLong2006 + 6000L < aLong2006) {
                        this.aLong2006 = aLong2006 - 6000L;
                    }
                    while (5000L + this.aLong2006 < aLong2006) {
                        this.method3260(256, -113);
                        this.aLong2006 += 256000 / Class64_Sub15.anInt3678;
                        aLong2006 = Class343.method3819(-47);
                    }
                }
                catch (Exception ex2) {
                    this.aLong2006 = aLong2006;
                }
                if (this.anIntArray2005 != null) {
                    try {
                        if (this.aLong2017 != 0L) {
                            if (~aLong2006 > ~this.aLong2017) {
                                return;
                            }
                            this.method3250(this.anInt2010);
                            this.aLong2017 = 0L;
                            this.aBoolean2011 = true;
                        }
                        if (b >= -66) {
                            method3248(122);
                        }
                        int method3258 = this.method3258();
                        if (~(-method3258 + this.anInt2013) < ~this.anInt2012) {
                            this.anInt2012 = this.anInt2013 - method3258;
                        }
                        int i = this.anInt2018 + this.anInt2016;
                        if (i + 256 > 16384) {
                            i = 16128;
                        }
                        if (this.anInt2010 < i + 256) {
                            this.anInt2010 += 1024;
                            if (~this.anInt2010 < -16385) {
                                this.anInt2010 = 16384;
                            }
                            this.method3262();
                            this.method3250(this.anInt2010);
                            method3258 = 0;
                            this.aBoolean2011 = true;
                            if (~this.anInt2010 > ~(i + 256)) {
                                i = this.anInt2010 - 256;
                                this.anInt2018 = i + -this.anInt2016;
                            }
                        }
                        while (i > method3258) {
                            this.method3255(this.anIntArray2005, 256);
                            this.method3257();
                            method3258 += 256;
                        }
                        if (~this.aLong2009 > ~aLong2006) {
                            if (!this.aBoolean2011) {
                                if (this.anInt2012 == 0 && ~this.anInt2014 == -1) {
                                    this.method3262();
                                    this.aLong2017 = 2000L + aLong2006;
                                    return;
                                }
                                this.anInt2018 = Math.min(this.anInt2014, this.anInt2012);
                                this.anInt2014 = this.anInt2012;
                            }
                            else {
                                this.aBoolean2011 = false;
                            }
                            this.anInt2012 = 0;
                            this.aLong2009 = aLong2006 + 2000L;
                        }
                        this.anInt2013 = method3258;
                    }
                    catch (Exception ex3) {
                        this.method3262();
                        this.aLong2017 = aLong2006 + 2000L;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qn.N(" + b + ')');
        }
    }
    
    void method3262() {
    }
    
    public Class268() {
        this.aBoolean2008 = false;
        this.anInt2004 = 32;
        this.aLong2006 = Class343.method3819(-47);
        this.anInt2014 = 0;
        this.anInt2013 = 0;
        this.aClass98_Sub31Array2019 = new Class98_Sub31[8];
        this.aLong2017 = 0L;
        this.anInt2012 = 0;
        this.aBoolean2011 = true;
        this.aLong2009 = 0L;
        this.aClass98_Sub31Array2020 = new Class98_Sub31[8];
        this.anInt2015 = 0;
    }
}
