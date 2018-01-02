// 
// Decompiled by Procyon v0.5.30
// 

class Class246_Sub4 extends Class246
{
    Class246_Sub4 aClass246_Sub4_5091;
    Class246_Sub4 aClass246_Sub4_5092;
    
    static final void method3099(final int n, final int n2, final byte b) {
        try {
            if (Class278.aFloat2064 >= Class278.aFloat2068) {
                if (Class278.aFloat2068 < Class278.aFloat2064) {
                    Class278.aFloat2064 -= (float)(Class278.aFloat2064 / 30.0);
                    if (Class278.aFloat2068 > Class278.aFloat2064) {
                        Class278.aFloat2064 = Class278.aFloat2068;
                    }
                    aa_Sub1.method155(-1);
                    Class278.anInt2069 = (int)Class278.aFloat2064 >> 9275809;
                    Class278.aByteArrayArrayArray2072 = Class287_Sub2.method3392(Class278.anInt2069, (byte)(-86));
                }
            }
            else {
                Class278.aFloat2064 += (float)(Class278.aFloat2064 / 30.0);
                if (Class278.aFloat2064 > Class278.aFloat2068) {
                    Class278.aFloat2064 = Class278.aFloat2068;
                }
                aa_Sub1.method155(-1);
                Class278.anInt2069 = (int)Class278.aFloat2064 >> 2089293857;
                Class278.aByteArrayArrayArray2072 = Class287_Sub2.method3392(Class278.anInt2069, (byte)112);
            }
            if (Class101.anInt849 != -1 && Class169.anInt1307 != -1) {
                int n3 = -Class42_Sub4.anInt5371 + Class101.anInt849;
                if (n3 < 2 || ~n3 < -3) {
                    n3 /= 8;
                }
                int n4 = -Class98_Sub40.anInt4197 + Class169.anInt1307;
                Class42_Sub4.anInt5371 -= -n3;
                if (~n4 > -3 || n4 > 2) {
                    n4 /= 8;
                }
                if (n3 == 0 && ~n4 == -1) {
                    Class101.anInt849 = -1;
                    Class169.anInt1307 = -1;
                }
                Class98_Sub40.anInt4197 += n4;
                aa_Sub1.method155(-1);
            }
            if (b != 2) {
                method3099(33, 96, (byte)(-1));
            }
            if (~Class64_Sub25.anInt3711 >= -1) {
                Class98_Sub5_Sub2.anInt5536 = -1;
                Class256.anInt1945 = -1;
            }
            else {
                --Class287.anInt2186;
                if (Class287.anInt2186 == 0) {
                    Class287.anInt2186 = 100;
                    --Class64_Sub25.anInt3711;
                }
            }
            if (Class253.aBoolean1930 && Class8.aClass148_110 != null) {
                for (Class98_Sub23 class98_Sub23 = (Class98_Sub23)Class8.aClass148_110.method2418(32); class98_Sub23 != null; class98_Sub23 = (Class98_Sub23)Class8.aClass148_110.method2417(b ^ 0x73)) {
                    final Class24 method3807 = Class278.aClass341_2057.method3807(b ^ 0xFFFFFFDF, class98_Sub23.aClass98_Sub47_3997.anInt4268);
                    if (!class98_Sub23.method1267(Integer.MIN_VALUE, n2, n)) {
                        if (class98_Sub23.aClass98_Sub47_3997.aBoolean4275) {
                            class98_Sub23.aClass98_Sub47_3997.aBoolean4275 = false;
                            Class247.method3152(Class98_Sub10_Sub26.aClass105_5684, class98_Sub23.aClass98_Sub47_3997.anInt4268, method3807.anInt246);
                        }
                    }
                    else {
                        if (method3807.aStringArray237 != null) {
                            if (method3807.aStringArray237[4] != null) {
                                Class293.method3470(false, true, class98_Sub23.aClass98_Sub47_3997.anInt4268, -1, method3807.anInt246, method3807.aString232, false, 0, 1004, class98_Sub23.aClass98_Sub47_3997.anInt4268, -1, false, method3807.aStringArray237[4]);
                            }
                            if (method3807.aStringArray237[3] != null) {
                                Class293.method3470(false, true, class98_Sub23.aClass98_Sub47_3997.anInt4268, -1, method3807.anInt246, method3807.aString232, false, 0, 1010, class98_Sub23.aClass98_Sub47_3997.anInt4268, -1, false, method3807.aStringArray237[3]);
                            }
                            if (method3807.aStringArray237[2] != null) {
                                Class293.method3470(false, true, class98_Sub23.aClass98_Sub47_3997.anInt4268, -1, method3807.anInt246, method3807.aString232, false, 0, 1001, class98_Sub23.aClass98_Sub47_3997.anInt4268, -1, false, method3807.aStringArray237[2]);
                            }
                            if (method3807.aStringArray237[1] != null) {
                                Class293.method3470(false, true, class98_Sub23.aClass98_Sub47_3997.anInt4268, -1, method3807.anInt246, method3807.aString232, false, 0, 1003, class98_Sub23.aClass98_Sub47_3997.anInt4268, -1, false, method3807.aStringArray237[1]);
                            }
                            if (method3807.aStringArray237[0] != null) {
                                Class293.method3470(false, true, class98_Sub23.aClass98_Sub47_3997.anInt4268, -1, method3807.anInt246, method3807.aString232, false, 0, 1011, class98_Sub23.aClass98_Sub47_3997.anInt4268, -1, false, method3807.aStringArray237[0]);
                            }
                        }
                        if (!class98_Sub23.aClass98_Sub47_3997.aBoolean4275) {
                            class98_Sub23.aClass98_Sub47_3997.aBoolean4275 = true;
                            Class247.method3152(Class90.aClass105_719, class98_Sub23.aClass98_Sub47_3997.anInt4268, method3807.anInt246);
                        }
                        if (class98_Sub23.aClass98_Sub47_3997.aBoolean4275) {
                            Class247.method3152(Class331.aClass105_2792, class98_Sub23.aClass98_Sub47_3997.anInt4268, method3807.anInt246);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hs.F(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    static final boolean method3100(final int n, final byte b) {
        try {
            return b >= 41 && ~n <= -13 && ~n >= -18;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hs.E(" + n + ',' + b + ')');
        }
    }
    
    final void method3101(final int n) {
        try {
            if (this.aClass246_Sub4_5092 != null) {
                this.aClass246_Sub4_5092.aClass246_Sub4_5091 = this.aClass246_Sub4_5091;
                this.aClass246_Sub4_5091.aClass246_Sub4_5092 = this.aClass246_Sub4_5092;
                this.aClass246_Sub4_5091 = null;
                this.aClass246_Sub4_5092 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hs.G(" + n + ')');
        }
    }
}
