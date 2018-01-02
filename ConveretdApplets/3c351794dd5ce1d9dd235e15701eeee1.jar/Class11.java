// 
// Decompiled by Procyon v0.5.30
// 

final class Class11
{
    static Class293 aClass293_120;
    static long aLong121;
    private Class207 aClass207_122;
    static int[] anIntArray123;
    Class207 aClass207_124;
    private Class79 aClass79_125;
    Class79 aClass79_126;
    
    public static void method199(final byte b) {
        try {
            Class11.aClass293_120 = null;
            Class11.anIntArray123 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aq.E(" + b + ')');
        }
    }
    
    final void method200(final int n) {
        try {
            synchronized (this.aClass79_125) {
                this.aClass79_125.method806((byte)(-108));
            }
            synchronized (this.aClass79_126) {
                this.aClass79_126.method806((byte)(-100));
            }
            if (n != 1) {
                this.method203(false, 10);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aq.B(" + n + ')');
        }
    }
    
    final void method201(final byte b) {
        try {
            synchronized (this.aClass79_125) {
                this.aClass79_125.method794(123);
            }
            synchronized (this.aClass79_126) {
                this.aClass79_126.method794(42);
            }
            if (b <= 121) {
                method199((byte)127);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aq.A(" + b + ')');
        }
    }
    
    final Class231 method202(final int n, final int n2) {
        try {
            if (n2 != 25930) {
                this.aClass207_122 = null;
            }
            final Class231 class231;
            synchronized (this.aClass79_125) {
                class231 = (Class231)this.aClass79_125.method802(-123, n);
            }
            if (class231 != null) {
                return class231;
            }
            final byte[] method2745;
            synchronized (this.aClass207_122) {
                method2745 = this.aClass207_122.method2745(n, 33, false);
            }
            final Class231 class232 = new Class231();
            class232.aClass11_1737 = this;
            if (method2745 != null) {
                class232.method2880(true, new Class98_Sub22(method2745));
            }
            synchronized (this.aClass79_125) {
                this.aClass79_125.method805(n, class232, (byte)(-80));
            }
            return class232;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aq.D(" + n + ',' + n2 + ')');
        }
    }
    
    final void method203(final boolean b, final int n) {
        try {
            synchronized (this.aClass79_125) {
                this.aClass79_125.method800((byte)62, n);
            }
            if (b) {
                this.method203(true, 93);
            }
            synchronized (this.aClass79_126) {
                this.aClass79_126.method800((byte)62, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aq.C(" + b + ',' + n + ')');
        }
    }
    
    Class11(final Class279 class279, final int n, final Class207 aClass207_122, final Class207 aClass207_123) {
        this.aClass79_125 = new Class79(64);
        this.aClass79_126 = new Class79(2);
        try {
            this.aClass207_122 = aClass207_122;
            this.aClass207_124 = aClass207_123;
            this.aClass207_122.method2761(0, 33);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aq.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_122 != null) ? "{...}" : "null") + ',' + ((aClass207_123 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class11.anIntArray123 = new int[1];
    }
}
