// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class246_Sub3 extends Class246
{
    boolean aBoolean5078;
    int anInt5079;
    int anInt5080;
    byte aByte5081;
    boolean aBoolean5082;
    int anInt5083;
    int anInt5084;
    int anInt5085;
    static IncomingOpcode aClass58_5086;
    static Class207 aClass207_5087;
    byte aByte5088;
    int anInt5089;
    Class246_Sub3 aClass246_Sub3_5090;
    
    abstract Class228 method2974(final byte p0, final ha p1);
    
    abstract Class246_Sub1 method2975(final ha p0, final int p1);
    
    abstract boolean method2976(final int p0, final ha p1, final byte p2, final int p3);
    
    abstract boolean method2977(final ha p0, final byte p1);
    
    abstract boolean method2978(final int p0);
    
    static final Class98_Sub47 method2979(final int n) {
        try {
            if (Class278.aClass148_2065 == null || Class98_Sub5.aClass157_3835 == null) {
                return null;
            }
            for (Class98_Sub47 class98_Sub47 = (Class98_Sub47)Class98_Sub5.aClass157_3835.method2503(1000); class98_Sub47 != null; class98_Sub47 = (Class98_Sub47)Class98_Sub5.aClass157_3835.method2503(1000)) {
                final Class24 method3807 = Class278.aClass341_2057.method3807(123, class98_Sub47.anInt4268);
                if (method3807 != null && method3807.aBoolean241 && method3807.method284(35, Class278.anInterface6_2060)) {
                    return class98_Sub47;
                }
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gg.LB(" + n + ')');
        }
    }
    
    abstract int method2980(final int p0, final Class98_Sub5[] p1);
    
    abstract void method2981(final Class246_Sub3 p0, final byte p1, final boolean p2, final int p3, final ha p4, final int p5, final int p6);
    
    abstract boolean method2982(final byte p0);
    
    public static void method2983(final byte b) {
        try {
            Class246_Sub3.aClass58_5086 = null;
            if (b <= -45) {
                Class246_Sub3.aClass207_5087 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gg.NB(" + b + ')');
        }
    }
    
    static final void method2984(final int anInt6051, final byte b, final int anInt6052, final int n) {
        try {
            final Class98_Sub46_Sub17 method2628 = Class185.method2628(n, b + 2, 11);
            method2628.method1626((byte)(-103));
            method2628.anInt6054 = anInt6052;
            method2628.anInt6051 = anInt6051;
            if (b != -105) {
                method2983((byte)8);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gg.OB(" + anInt6051 + ',' + b + ',' + anInt6052 + ',' + n + ')');
        }
    }
    
    abstract int method2985(final boolean p0);
    
    int method2986(final int n) {
        try {
            if (n != -14240) {
                this.method2976(82, null, (byte)(-22), -7);
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gg.FB(" + n + ')');
        }
    }
    
    abstract boolean method2987(final int p0);
    
    abstract void method2988(final ha p0, final int p1);
    
    final int method2989(final int n, final boolean b, final Class98_Sub5[] array, final int n2) {
        try {
            if (b) {
                this.method2988(null, -104);
            }
            final long n3 = Class373_Sub3.aLongArrayArrayArray5476[this.aByte5088][n][n2];
            long n4;
            int n5;
            int n6;
            for (n4 = 0L, n5 = 0; n4 <= 48L; n4 += 16L, array[n5++] = Class98_Sub10_Sub31.aClass1Array5717[-1 + n6].aClass98_Sub5_55) {
                n6 = (int)(0xFFFFL & n3 >> (int)n4);
                if (n6 <= 0) {
                    break;
                }
            }
            for (int n7 = n5; ~n7 > -5; ++n7) {
                array[n7] = null;
            }
            return n5;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gg.MB(" + n + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    abstract int method2990(final int p0);
    
    abstract boolean method2991(final boolean p0);
    
    abstract void method2992(final byte p0);
    
    public Class246_Sub3() {
        this.aBoolean5082 = false;
    }
    
    static {
        Class246_Sub3.aClass58_5086 = new IncomingOpcode(83, 20);
    }
}
