// 
// Decompiled by Procyon v0.5.30
// 

final class Class140 implements Interface6
{
    static Class47 aClass47_3241;
    private Class377 aClass377_3242;
    static int anInt3243;
    int[] anIntArray3244;
    static Class48 aClass48_3245;
    static boolean[] aBooleanArray3246;
    private int[] anIntArray3247;
    static OutgoingOpcode aClass171_3248;
    
    public static void method2285(final int n) {
        try {
            Class140.aClass171_3248 = null;
            Class140.aClass48_3245 = null;
            if (n != -4492) {
                method2287(-125, -67, -94);
            }
            Class140.aBooleanArray3246 = null;
            Class140.aClass47_3241 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jk.B(" + n + ')');
        }
    }
    
    final void method2286(final int n, final int n2, final int n3) {
        try {
            this.anIntArray3247[n3] = n2;
            final Class98_Sub50 class98_Sub50 = (Class98_Sub50)this.aClass377_3242.method3990(n3, -1);
            if (class98_Sub50 != null) {
                if (~class98_Sub50.aLong4288 != 0xBFFFFFFFFFFFFFFEL) {
                    class98_Sub50.aLong4288 = (0x4000000000000000L | Class343.method3819(-47) + 500L);
                }
            }
            else {
                this.aClass377_3242.method3996(new Class98_Sub50(4611686018427387905L), n3, -1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jk.G(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    public final int method6(final int n, final int n2) {
        try {
            return this.anIntArray3244[n];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jk.H(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    public final int method7(final int n, final int n2) {
        try {
            final Class366 method2680 = Class17.aClass198_205.method2680(n, (byte)40);
            final int anInt3115 = method2680.anInt3115;
            final int anInt3116 = method2680.anInt3116;
            final int anInt3117 = method2680.anInt3118;
            if (n2 != 7373) {
                return -48;
            }
            return this.anIntArray3244[anInt3115] >> anInt3116 & Class98_Sub46_Sub20.anIntArray6070[anInt3117 + -anInt3116];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jk.I(" + n + ',' + n2 + ')');
        }
    }
    
    static final boolean method2287(final int n, final int n2, final int n3) {
        try {
            return n3 == 2048 && ~(0x800 & n) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jk.D(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final void method2288(final byte b) {
        try {
            for (int n = 0; ~Class134.aClass139_3465.anInt1086 < ~n; ++n) {
                final Class167 method2282 = Class134.aClass139_3465.method2282(n, 16);
                if (method2282 != null && method2282.anInt1283 == 0) {
                    this.anIntArray3247[n] = 0;
                    this.anIntArray3244[n] = 0;
                }
            }
            this.aClass377_3242 = new Class377(128);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jk.F(" + b + ')');
        }
    }
    
    final void method2289(int n, final int n2, final int n3) {
        try {
            final Class366 method2680 = Class17.aClass198_205.method2680(n2, (byte)68);
            final int anInt3115 = method2680.anInt3115;
            final int anInt3116 = method2680.anInt3116;
            final int n4 = Class98_Sub46_Sub20.anIntArray6070[method2680.anInt3118 - anInt3116];
            if (n < n3 || ~n < ~n4) {
                n = 0;
            }
            final int n5 = n4 << anInt3116;
            this.method2291(anInt3115, 86, (n5 & n << anInt3116) | (~n5 & this.anIntArray3244[anInt3115]));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jk.E(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final int method2290(final int n, final boolean b) {
        try {
            final long method3819 = Class343.method3819(-47);
            for (Class98_Sub50 class98_Sub50 = (Class98_Sub50)(b ? this.aClass377_3242.method3998(117) : ((Class98_Sub50)this.aClass377_3242.method3995(-1))); class98_Sub50 != null; class98_Sub50 = (Class98_Sub50)this.aClass377_3242.method3995(-1)) {
                if (~(0x3FFFFFFFFFFFFFFFL & class98_Sub50.aLong4288) > ~method3819) {
                    if ((0x4000000000000000L & class98_Sub50.aLong4288) != 0x0L) {
                        final int n2 = (int)class98_Sub50.aLong832;
                        this.anIntArray3244[n2] = this.anIntArray3247[n2];
                        class98_Sub50.method942(45);
                        return n2;
                    }
                    class98_Sub50.method942(59);
                }
            }
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jk.A(" + n + ',' + b + ')');
        }
    }
    
    public Class140() {
        this.aClass377_3242 = new Class377(128);
        try {
            this.anIntArray3244 = new int[Class134.aClass139_3465.anInt1086];
            this.anIntArray3247 = new int[Class134.aClass139_3465.anInt1086];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jk.<init>()");
        }
    }
    
    final void method2291(final int n, final int n2, final int n3) {
        try {
            this.anIntArray3244[n] = n3;
            Class98_Sub50 class98_Sub50 = (Class98_Sub50)this.aClass377_3242.method3990(n, -1);
            Label_0080: {
                if (class98_Sub50 == null) {
                    class98_Sub50 = new Class98_Sub50(500L + Class343.method3819(-47));
                    this.aClass377_3242.method3996(class98_Sub50, n, -1);
                    if (!client.aBoolean3553) {
                        break Label_0080;
                    }
                }
                class98_Sub50.aLong4288 = Class343.method3819(-47) + 500L;
            }
            if (n2 <= 58) {
                this.method2286(36, 108, 47);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jk.J(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final void method2292(final int n, int n2, final int n3) {
        try {
            final Class366 method2680 = Class17.aClass198_205.method2680(n, (byte)98);
            final int anInt3115 = method2680.anInt3115;
            if (n3 == -32727) {
                final int anInt3116 = method2680.anInt3116;
                final int n4 = Class98_Sub46_Sub20.anIntArray6070[method2680.anInt3118 + -anInt3116];
                if (~n2 > -1 || n4 < n2) {
                    n2 = 0;
                }
                final int n5 = n4 << anInt3116;
                this.method2286(n3 + 25258, (n2 << anInt3116 & n5) | (this.anIntArray3247[anInt3115] & ~n5), anInt3115);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jk.C(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static {
        Class140.aClass171_3248 = new OutgoingOpcode(42, 4);
    }
}
