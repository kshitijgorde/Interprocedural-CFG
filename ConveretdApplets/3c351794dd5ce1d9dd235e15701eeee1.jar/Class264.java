import java.io.FileOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class264
{
    private Class79 aClass79_1968;
    static FileOutputStream aFileOutputStream1969;
    private Class207 aClass207_1970;
    static int anInt1971;
    static int anInt1972;
    
    static final void method3222(final byte b) {
        try {
            Class253.anInt1934 = Class98_Sub46_Sub10.aClass197_6019.anInt1517 - (-Class98_Sub46_Sub10.aClass197_6019.anInt1514 - 2);
            Class98_Sub46_Sub20.aStringArray6073 = new String[500];
            Class98_Sub10_Sub12.anInt5598 = 2 + Class42_Sub1.aClass197_5354.anInt1517 + Class42_Sub1.aClass197_5354.anInt1514;
            int n = 0;
            if (b != -43) {
                Class264.anInt1971 = 15;
            }
            while (~Class98_Sub46_Sub20.aStringArray6073.length < ~n) {
                Class98_Sub46_Sub20.aStringArray6073[n] = "";
                ++n;
            }
            Class98_Sub46.method1525(Class309.aClass309_2586.method3615(Class374.anInt3159, (byte)25), b ^ 0xFFFFFF84);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qi.C(" + b + ')');
        }
    }
    
    final void method3223(final byte b) {
        try {
            if (b != 17) {
                this.method3223((byte)86);
            }
            synchronized (this.aClass79_1968) {
                this.aClass79_1968.method794(b + 58);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qi.D(" + b + ')');
        }
    }
    
    final Class98_Sub46_Sub12 method3224(final int n, final int n2) {
        try {
            final Class98_Sub46_Sub12 class98_Sub46_Sub12;
            synchronized (this.aClass79_1968) {
                class98_Sub46_Sub12 = (Class98_Sub46_Sub12)this.aClass79_1968.method802(-127, n2);
            }
            if (class98_Sub46_Sub12 != null) {
                return class98_Sub46_Sub12;
            }
            final byte[] method2745;
            synchronized (this.aClass207_1970) {
                method2745 = this.aClass207_1970.method2745(n2, n, false);
            }
            final Class98_Sub46_Sub12 class98_Sub46_Sub13 = new Class98_Sub46_Sub12();
            if (method2745 != null) {
                class98_Sub46_Sub13.method1588(0, new Class98_Sub22(method2745));
            }
            synchronized (this.aClass79_1968) {
                this.aClass79_1968.method805(n2, class98_Sub46_Sub13, (byte)(-80));
            }
            return class98_Sub46_Sub13;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qi.E(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method3225(final boolean b) {
        try {
            Class264.aFileOutputStream1969 = null;
            if (!b) {
                Class264.anInt1971 = 51;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qi.B(" + b + ')');
        }
    }
    
    final void method3226(final int n) {
        try {
            synchronized (this.aClass79_1968) {
                this.aClass79_1968.method806((byte)(-115));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qi.A(" + n + ')');
        }
    }
    
    final void method3227(final int n, final int n2) {
        try {
            synchronized (this.aClass79_1968) {
                this.aClass79_1968.method800((byte)62, n);
            }
            if (n2 > -28) {
                this.aClass207_1970 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qi.F(" + n + ',' + n2 + ')');
        }
    }
    
    Class264(final Class279 class279, final int n, final Class207 aClass207_1970) {
        this.aClass79_1968 = new Class79(256);
        try {
            (this.aClass207_1970 = aClass207_1970).method2761(0, 26);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qi.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_1970 != null) ? "{...}" : "null") + ')');
        }
    }
}
