// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub6 extends Class98
{
    int anInt3838;
    int anInt3839;
    private int anInt3840;
    private int anInt3841;
    private int anInt3842;
    int anInt3843;
    static IncomingOpcode aClass58_3844;
    int anInt3845;
    private int anInt3846;
    static Class79 aClass79_3847;
    private int anInt3848;
    
    public static void method975(final int n) {
        try {
            Class98_Sub6.aClass58_3844 = null;
            Class98_Sub6.aClass79_3847 = null;
            if (n != 1) {
                method978(true, true, 19, true, 86);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cd.B(" + n + ')');
        }
    }
    
    final boolean method976(final int n, final int n2, final int n3, final int n4) {
        try {
            if (n2 < 104) {
                this.anInt3848 = 122;
            }
            return ~this.anInt3842 == ~n4 && n3 >= this.anInt3840 && n3 <= this.anInt3848 && n >= this.anInt3846 && n <= this.anInt3841;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cd.E(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    final boolean method977(final int n, final byte b, final int n2) {
        try {
            if (b <= 32) {
                this.anInt3848 = 89;
            }
            return ~this.anInt3839 >= ~n && n <= this.anInt3838 && this.anInt3843 <= n2 && this.anInt3845 >= n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cd.D(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    static final int method978(final boolean b, final boolean b2, final int n, final boolean b3, final int n2) {
        try {
            final Class98_Sub3 method669 = Class64_Sub28.method669(n, b, 6);
            if (method669 == null) {
                return 0;
            }
            int n3 = 0;
            for (int n4 = 0; ~n4 > ~method669.anIntArray3824.length; ++n4) {
                if (~method669.anIntArray3824[n4] <= -1 && ~Class98_Sub46_Sub19.aClass205_6068.anInt1554 < ~method669.anIntArray3824[n4]) {
                    final int method670 = Class98_Sub46_Sub19.aClass205_6068.method2714(method669.anIntArray3824[n4], (byte)(-121)).method3494(n2, (byte)(-128), Class98_Sub43_Sub1.aClass365_5897.method3940((byte)31, n2).anInt1202);
                    if (b2) {
                        n3 += method669.anIntArray3823[n4] * method670;
                    }
                    else {
                        n3 += method670;
                    }
                }
            }
            if (!b3) {
                method975(-10);
            }
            return n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cd.G(" + b + ',' + b2 + ',' + n + ',' + b3 + ',' + n2 + ')');
        }
    }
    
    final void method979(final int n, final int n2, final int n3, final int[] array) {
        try {
            array[1] = n3 - this.anInt3839 - -this.anInt3840;
            array[n2] = this.anInt3842;
            array[2] = n - -this.anInt3846 + -this.anInt3843;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cd.H(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final boolean method980(final int n, final int n2, final int n3) {
        try {
            if (n > -94) {
                this.method980(104, -50, -22);
            }
            return ~n2 <= ~this.anInt3840 && ~n2 >= ~this.anInt3848 && ~this.anInt3846 >= ~n3 && this.anInt3841 >= n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cd.C(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final Class178 method981(final int n, final int n2, final Class207 class207, final int n3) {
        try {
            final byte[] method2745 = class207.method2745(n, n3, false);
            if (n2 != -9252) {
                Class98_Sub6.aClass79_3847 = null;
            }
            if (method2745 == null) {
                return null;
            }
            return new Class178(method2745);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cd.F(" + n + ',' + n2 + ',' + ((class207 != null) ? "{...}" : "null") + ',' + n3 + ')');
        }
    }
    
    Class98_Sub6(final int anInt3842, final int anInt3843, final int anInt3844, final int anInt3845, final int anInt3846, final int anInt3847, final int anInt3848, final int anInt3849, final int anInt3850) {
        try {
            this.anInt3842 = anInt3842;
            this.anInt3840 = anInt3843;
            this.anInt3845 = anInt3850;
            this.anInt3846 = anInt3844;
            this.anInt3841 = anInt3846;
            this.anInt3839 = anInt3847;
            this.anInt3848 = anInt3845;
            this.anInt3838 = anInt3849;
            this.anInt3843 = anInt3848;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cd.<init>(" + anInt3842 + ',' + anInt3843 + ',' + anInt3844 + ',' + anInt3845 + ',' + anInt3846 + ',' + anInt3847 + ',' + anInt3848 + ',' + anInt3849 + ',' + anInt3850 + ')');
        }
    }
    
    final void method982(final int n, final int n2, final int n3, final int[] array) {
        try {
            array[0] = 0;
            array[2] = this.anInt3843 - this.anInt3846 + n;
            array[1] = -this.anInt3840 - (-this.anInt3839 - n2);
            if (n3 <= 43) {
                this.anInt3842 = 27;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cd.A(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub6.aClass58_3844 = new IncomingOpcode(121, -1);
        Class98_Sub6.aClass79_3847 = new Class79(8);
    }
}
