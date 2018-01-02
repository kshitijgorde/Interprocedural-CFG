import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class257
{
    static int anInt1946;
    static Class207 aClass207_1947;
    static int anInt1948;
    private Class207 aClass207_1949;
    private Class79 aClass79_1950;
    
    final void method3197(final byte b, final int n) {
        try {
            synchronized (this.aClass79_1950) {
                if (b != 30) {
                    this.method3199(true, 112);
                }
                this.aClass79_1950.method800((byte)62, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qb.D(" + b + ',' + n + ')');
        }
    }
    
    public static void method3198(final int n) {
        try {
            if (n < 48) {
                Class257.anInt1946 = -4;
            }
            Class257.aClass207_1947 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qb.C(" + n + ')');
        }
    }
    
    final Class294 method3199(final boolean b, final int n) {
        try {
            final Class294 class294;
            synchronized (this.aClass79_1950) {
                class294 = (Class294)this.aClass79_1950.method802(-123, n);
            }
            if (class294 != null) {
                return class294;
            }
            final byte[] method2745;
            synchronized (this.aClass207_1949) {
                method2745 = this.aClass207_1949.method2745(n, 32, b);
            }
            final Class294 class295 = new Class294();
            if (method2745 != null) {
                class295.method3475(-22400, new Class98_Sub22(method2745));
            }
            synchronized (this.aClass79_1950) {
                this.aClass79_1950.method805(n, class295, (byte)(-80));
            }
            return class295;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qb.A(" + b + ',' + n + ')');
        }
    }
    
    final void method3200(final byte b) {
        try {
            synchronized (this.aClass79_1950) {
                this.aClass79_1950.method794(102);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qb.F(" + b + ')');
        }
    }
    
    static final void method3201(final byte b, final boolean b2) {
        try {
            Class128.method2224(22696);
            if (za_Sub2.method1683(-11297, Class177.anInt1376)) {
                ++Class196.anInt1511;
                if (~Class196.anInt1511 <= -51 || b2) {
                    if (b < 45) {
                        method3198(75);
                    }
                    Class196.anInt1511 = 0;
                    if (!Class76_Sub9.aBoolean3788 && aa_Sub1.aClass123_3561 != null) {
                        ++Class76_Sub5.anInt3746;
                        Class98_Sub10_Sub29.sendPacket(false, Class246_Sub3_Sub4.method3023(260, Class98_Sub40.aClass171_4193, Class331.aClass117_2811));
                        try {
                            Class95.method920((byte)81);
                        }
                        catch (IOException ex2) {
                            Class76_Sub9.aBoolean3788 = true;
                        }
                    }
                    Class128.method2224(22696);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qb.E(" + b + ',' + b2 + ')');
        }
    }
    
    final void method3202(final byte b) {
        try {
            synchronized (this.aClass79_1950) {
                if (b != 96) {
                    this.aClass207_1949 = null;
                }
                this.aClass79_1950.method806((byte)(-121));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qb.B(" + b + ')');
        }
    }
    
    Class257(final Class279 class279, final int n, final Class207 aClass207_1949) {
        this.aClass79_1950 = new Class79(64);
        try {
            (this.aClass207_1949 = aClass207_1949).method2761(0, 32);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qb.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_1949 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class257.anInt1948 = 0;
        Class257.anInt1946 = -2;
    }
}
