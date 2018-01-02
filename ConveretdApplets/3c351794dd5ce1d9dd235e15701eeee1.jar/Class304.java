// 
// Decompiled by Procyon v0.5.30
// 

final class Class304
{
    private Class207 aClass207_2532;
    static int anInt2533;
    static short[][] aShortArrayArray2534;
    private Class79 aClass79_2535;
    Class207 aClass207_2536;
    Class79 aClass79_2537;
    static int[] anIntArray2538;
    int anInt2539;
    
    final void method3559(final int n) {
        try {
            synchronized (this.aClass79_2535) {
                this.aClass79_2535.method806((byte)(-126));
            }
            synchronized (this.aClass79_2537) {
                this.aClass79_2537.method806((byte)23);
            }
            if (n != 4) {
                this.anInt2539 = -6;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sk.E(" + n + ')');
        }
    }
    
    final void method3560(final int anInt2539, final int n) {
        try {
            this.anInt2539 = anInt2539;
            synchronized (this.aClass79_2537) {
                if (n != 0) {
                    this.aClass207_2532 = null;
                }
                this.aClass79_2537.method794(49);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sk.A(" + anInt2539 + ',' + n + ')');
        }
    }
    
    public static void method3561(final int n) {
        try {
            Class304.anIntArray2538 = null;
            Class304.aShortArrayArray2534 = null;
            if (n != -19357) {
                method3562(-126, -89, -59, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sk.C(" + n + ')');
        }
    }
    
    static final void method3562(final int n, final int n2, final int n3, final Class293 class293) {
        try {
            final aa method3469 = class293.method3469(Class265.aHa1974, n - 3999);
            if (method3469 != null && n == 4096) {
                Class265.aHa1974.KA(n2, n3, n2 - -class293.anInt2311, class293.anInt2258 + n3);
                if (Class333.anInt3386 < 3) {
                    Class334.aClass332_3471.method3739(n2 + class293.anInt2311 / 2.0f, n3 + class293.anInt2258 / 2.0f, 4096, ((int)(-Class98_Sub22_Sub2.aFloat5794) & 0x3FFF) << -1645068286, method3469, n2, n3);
                }
                else {
                    Class265.aHa1974.A(-16777216, method3469, n2, n3);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sk.B(" + n + ',' + n2 + ',' + n3 + ',' + ((class293 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method3563(final int anInt2690, final Class293 aClass293_2129, final int anInt2691, final int n) {
        try {
            Class341.anInt2858 = anInt2691;
            if (n == 60) {
                Class314.anInt2690 = anInt2690;
                Class282.aClass293_2129 = aClass293_2129;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sk.G(" + anInt2690 + ',' + ((aClass293_2129 != null) ? "{...}" : "null") + ',' + anInt2691 + ',' + n + ')');
        }
    }
    
    final Class107 method3564(final int n, final int anInt925) {
        try {
            final Class107 class107;
            synchronized (this.aClass79_2535) {
                class107 = (Class107)this.aClass79_2535.method802(-123, anInt925);
            }
            if (class107 != null) {
                return class107;
            }
            final byte[] method2745;
            synchronized (this.aClass207_2532) {
                method2745 = this.aClass207_2532.method2745(Class314.method3637(n ^ 0xFFFFC578, anInt925), Class329.method3711((byte)117, anInt925), false);
                if (n != 2) {
                    this.anInt2539 = 42;
                }
            }
            final Class107 class108 = new Class107();
            class108.aClass304_921 = this;
            class108.anInt925 = anInt925;
            if (method2745 != null) {
                class108.method1725(n - 2, new Class98_Sub22(method2745));
            }
            synchronized (this.aClass79_2535) {
                this.aClass79_2535.method805(anInt925, class108, (byte)(-80));
            }
            return class108;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sk.F(" + n + ',' + anInt925 + ')');
        }
    }
    
    final void method3565(final byte b) {
        try {
            synchronized (this.aClass79_2535) {
                this.aClass79_2535.method794(46);
            }
            if (b == 0) {
                synchronized (this.aClass79_2537) {
                    this.aClass79_2537.method794(89);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sk.D(" + b + ')');
        }
    }
    
    final void method3566(final byte b, final int n) {
        try {
            synchronized (this.aClass79_2535) {
                this.aClass79_2535.method800((byte)62, n);
                if (b != -53) {
                    method3563(-6, null, -120, 48);
                }
            }
            synchronized (this.aClass79_2537) {
                this.aClass79_2537.method800((byte)62, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sk.H(" + b + ',' + n + ')');
        }
    }
    
    Class304(final Class279 class279, final int n, final Class207 aClass207_2532, final Class207 aClass207_2533) {
        this.aClass79_2535 = new Class79(64);
        this.aClass79_2537 = new Class79(60);
        try {
            this.aClass207_2536 = aClass207_2533;
            (this.aClass207_2532 = aClass207_2532).method2761(0, this.aClass207_2532.method2752((byte)(-11)) - 1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sk.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_2532 != null) ? "{...}" : "null") + ',' + ((aClass207_2533 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class304.anInt2533 = 0;
        Class304.anIntArray2538 = new int[] { 0, 1, 2, 3, 4, 5, 6, 14 };
    }
}
