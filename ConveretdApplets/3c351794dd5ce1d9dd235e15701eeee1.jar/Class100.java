// 
// Decompiled by Procyon v0.5.30
// 

final class Class100
{
    private Class98_Sub46 aClass98_Sub46_839;
    static Class113 aClass113_840;
    private int anInt841;
    private Class215 aClass215_842;
    private int anInt843;
    static Class339_Sub1[] aClass339_Sub1Array844;
    static float aFloat845;
    private Class377 aClass377_846;
    
    static final boolean method1688(final int n, final int n2, final int n3, final Class246_Sub3_Sub3 class246_Sub3_Sub3, final byte b) {
        try {
            if (!Class98_Sub17.aBoolean3942 || !Class135.aBoolean1052) {
                return false;
            }
            if (Class4.anInt81 < 100) {
                return false;
            }
            if (!Class76_Sub5.method758((byte)(-78), n, n3, n2)) {
                return false;
            }
            final int n4 = n2 << Class151_Sub8.anInt5015;
            final int n5 = n3 << Class151_Sub8.anInt5015;
            final int n6 = Class78.aSArray594[n].method3420(n3, -12639, n2) - 1;
            final int n7 = n6 + class246_Sub3_Sub3.method2990(0);
            if (~class246_Sub3_Sub3.aShort6153 == 0xFFFFFFFE) {
                if (!Class254.method3187(n7, n4, n5, (byte)82, n6, r_Sub2.anInt6333 + n5, n7, n5, n4, n4)) {
                    return false;
                }
                if (!Class254.method3187(n7, n4, n5, (byte)82, n6, r_Sub2.anInt6333 + n5, n6, n5 + r_Sub2.anInt6333, n4, n4)) {
                    return false;
                }
                ++Class98_Sub16.anInt3927;
                return true;
            }
            else if (class246_Sub3_Sub3.aShort6153 == 2) {
                if (!Class254.method3187(n7, n4, r_Sub2.anInt6333 + n5, (byte)82, n6, r_Sub2.anInt6333 + n5, n7, r_Sub2.anInt6333 + n5, n4 - -r_Sub2.anInt6333, n4)) {
                    return false;
                }
                if (!Class254.method3187(n6, r_Sub2.anInt6333 + n4, n5 + r_Sub2.anInt6333, (byte)82, n6, n5 + r_Sub2.anInt6333, n7, n5 - -r_Sub2.anInt6333, n4 - -r_Sub2.anInt6333, n4)) {
                    return false;
                }
                ++Class98_Sub16.anInt3927;
                return true;
            }
            else if (class246_Sub3_Sub3.aShort6153 == 4) {
                if (!Class254.method3187(n7, n4 - -r_Sub2.anInt6333, n5, (byte)82, n6, r_Sub2.anInt6333 + n5, n7, n5, r_Sub2.anInt6333 + n4, r_Sub2.anInt6333 + n4)) {
                    return false;
                }
                if (!Class254.method3187(n7, r_Sub2.anInt6333 + n4, n5, (byte)82, n6, n5 + r_Sub2.anInt6333, n6, r_Sub2.anInt6333 + n5, n4 - -r_Sub2.anInt6333, r_Sub2.anInt6333 + n4)) {
                    return false;
                }
                ++Class98_Sub16.anInt3927;
                return true;
            }
            else if (~class246_Sub3_Sub3.aShort6153 == 0xFFFFFFF7) {
                if (!Class254.method3187(n7, n4, n5, (byte)82, n6, n5, n7, n5, r_Sub2.anInt6333 + n4, n4)) {
                    return false;
                }
                if (!Class254.method3187(n6, n4 + r_Sub2.anInt6333, n5, (byte)82, n6, n5, n7, n5, r_Sub2.anInt6333 + n4, n4)) {
                    return false;
                }
                ++Class98_Sub16.anInt3927;
                return true;
            }
            else if (~class246_Sub3_Sub3.aShort6153 == 0xFFFFFFEF) {
                if (!s_Sub1.method3427(n6, Class207.anInt1577, n7, Class207.anInt1577, (byte)16, Class207.anInt1577 + n5, n4)) {
                    return false;
                }
                ++Class98_Sub16.anInt3927;
                return true;
            }
            else if (class246_Sub3_Sub3.aShort6153 == 32) {
                if (!s_Sub1.method3427(n6, Class207.anInt1577, n7, Class207.anInt1577, (byte)16, n5 - -Class207.anInt1577, Class207.anInt1577 + n4)) {
                    return false;
                }
                ++Class98_Sub16.anInt3927;
                return true;
            }
            else if (class246_Sub3_Sub3.aShort6153 == 64) {
                if (!s_Sub1.method3427(n6, Class207.anInt1577, n7, Class207.anInt1577, (byte)16, n5, n4 - -Class207.anInt1577)) {
                    return false;
                }
                ++Class98_Sub16.anInt3927;
                return true;
            }
            else {
                if (class246_Sub3_Sub3.aShort6153 != 128) {
                    return true;
                }
                if (!s_Sub1.method3427(n6, Class207.anInt1577, n7, Class207.anInt1577, (byte)16, n5, n4)) {
                    return false;
                }
                ++Class98_Sub16.anInt3927;
                return true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gea.A(" + n + ',' + n2 + ',' + n3 + ',' + ((class246_Sub3_Sub3 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method1689(final long n, final byte b) {
        try {
            final Class98_Sub46 class98_Sub46 = (Class98_Sub46)this.aClass377_846.method3990(n, -1);
            if (class98_Sub46 != null) {
                class98_Sub46.method942(98);
                class98_Sub46.method1524((byte)(-90));
                ++this.anInt843;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gea.D(" + n + ',' + b + ')');
        }
    }
    
    final void method1690(final int n) {
        try {
            this.aClass215_842.method2786(16711680);
            if (n == 1) {
                this.aClass377_846.method3994(-84);
                this.aClass98_Sub46_839 = new Class98_Sub46();
                this.anInt843 = this.anInt841;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gea.C(" + n + ')');
        }
    }
    
    public static void method1691(final int n) {
        try {
            Class100.aClass339_Sub1Array844 = null;
            if (n != -9) {
                method1691(-108);
            }
            Class100.aClass113_840 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gea.B(" + n + ')');
        }
    }
    
    static final void method1692(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            if (n5 != 64) {
                method1688(-20, 71, 72, null, (byte)127);
            }
            if (~n == 0xFFFFFFF7 || ~n == 0xFFFFFFEF) {
                for (int n6 = 0; ~n6 > ~ha.anInt936; ++n6) {
                    final Class155 class155 = Class98_Sub32_Sub1.aClass155Array5889[n6];
                    if ((n == class155.aByte1242 && ~n3 == ~class155.aShort1236 && class155.aShort1239 == n4) || (n3 == class155.aShort1243 && ~class155.aShort1239 == ~n4)) {
                        if (ha.anInt936 != n6) {
                            Class236.method2892(Class98_Sub32_Sub1.aClass155Array5889, 1 + n6, Class98_Sub32_Sub1.aClass155Array5889, n6, Class98_Sub32_Sub1.aClass155Array5889.length - (1 + n6));
                        }
                        --ha.anInt936;
                        break;
                    }
                }
            }
            else {
                final Class172 class156 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n2][n3][n4];
                if (class156 != null) {
                    if (~n == 0xFFFFFFFE) {
                        class156.aShort1335 = 0;
                    }
                    else if (n == 2) {
                        class156.aShort1328 = 0;
                    }
                }
                Class64_Sub22.method644(n5 - 107);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gea.H(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    static final Class172 method1693(final int n, final int n2, final int n3) {
        if (Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2][n3] == null) {
            if (Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[0][n2][n3] != null && Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[0][n2][n3].aClass172_1330 != null && n >= Class364.anInt3103 - 1) {
                return null;
            }
            Class224_Sub2_Sub1.method2839(n, n2, n3);
        }
        return Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2][n3];
    }
    
    final Class98_Sub46 method1694(final byte b, final long n) {
        try {
            final Class98_Sub46 class98_Sub46 = (Class98_Sub46)this.aClass377_846.method3990(n, -1);
            if (class98_Sub46 != null) {
                this.aClass215_842.method2785(class98_Sub46, -54);
            }
            return class98_Sub46;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gea.E(" + b + ',' + n + ')');
        }
    }
    
    final void method1695(final int n, final Class98_Sub46 class98_Sub46, final long n2) {
        try {
            Label_0094: {
                if (~this.anInt843 == -1) {
                    final Class98_Sub46 method2789 = this.aClass215_842.method2789(-16711936);
                    method2789.method942(n - 26312);
                    method2789.method1524((byte)(-90));
                    if (this.aClass98_Sub46_839 != method2789) {
                        break Label_0094;
                    }
                    final Class98_Sub46 method2790 = this.aClass215_842.method2789(-16711936);
                    method2790.method942(116);
                    method2790.method1524((byte)(-90));
                    if (!client.aBoolean3553) {
                        break Label_0094;
                    }
                }
                --this.anInt843;
            }
            this.aClass377_846.method3996(class98_Sub46, n2, n - 26405);
            this.aClass215_842.method2785(class98_Sub46, -101);
            if (n != 26404) {
                method1688(-123, -119, 55, null, (byte)68);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gea.G(" + n + ',' + ((class98_Sub46 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    Class100(final int n) {
        this.aClass98_Sub46_839 = new Class98_Sub46();
        this.aClass215_842 = new Class215();
        try {
            this.anInt843 = n;
            this.anInt841 = n;
            int n2;
            for (n2 = 1; n2 + n2 < n; n2 += n2) {}
            this.aClass377_846 = new Class377(n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gea.<init>(" + n + ')');
        }
    }
    
    static {
        Class100.aClass113_840 = new Class113(0, 1);
        Class100.aClass339_Sub1Array844 = new Class339_Sub1[37];
    }
}
