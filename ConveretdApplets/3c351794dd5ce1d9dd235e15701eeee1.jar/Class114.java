// 
// Decompiled by Procyon v0.5.30
// 

final class Class114
{
    int anInt956;
    String aString957;
    static int[] anIntArray958;
    
    static final boolean method2147(final char c, final int n) {
        try {
            return n <= 104 || (~c <= -49 && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hga.A(" + c + ',' + n + ')');
        }
    }
    
    static final void method2148() {
        for (int i = 0; i < Class347.anInt2907; ++i) {
            Class99.method1687(Class246_Sub3_Sub5_Sub2.aClass246_Sub3_Sub4Array6273[i], true);
            Class246_Sub3_Sub5_Sub2.aClass246_Sub3_Sub4Array6273[i] = null;
        }
        Class347.anInt2907 = 0;
    }
    
    static final Class267 method2149(final int n, final int n2) {
        try {
            return new Class267(n, false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hga.C(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method2150(final int n) {
        try {
            Class114.anIntArray958 = null;
            if (n != 0) {
                Class114.anIntArray958 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hga.E(" + n + ')');
        }
    }
    
    static final Class244 method2151(final int n, final boolean b, final ha ha, final boolean b2) {
        try {
            if (~n == 0x0) {
                return null;
            }
            if (Class2.anIntArray70 != null) {
                for (int n2 = 0; ~n2 > ~Class2.anIntArray70.length; ++n2) {
                    if (Class2.anIntArray70[n2] == n) {
                        return Class242.aClass244Array1851[n2];
                    }
                }
            }
            final Class244 class244 = (Class244)Class232.aClass79_1740.method802(-123, n);
            if (class244 != null) {
                if (b && class244.aClass197_1858 == null) {
                    final Class197 method2182 = Class119_Sub1.method2182(Class64_Sub17.aClass207_3687, true, n);
                    if (method2182 == null) {
                        return null;
                    }
                    class244.aClass197_1858 = method2182;
                }
                return class244;
            }
            final Class324[] method2183 = Class324.method3684(Class64_Sub16.aClass207_3683, n);
            if (method2183 == null) {
                return null;
            }
            if (!b2) {
                method2149(-104, 72);
            }
            final Class197 method2184 = Class119_Sub1.method2182(Class64_Sub17.aClass207_3687, true, n);
            if (method2184 == null) {
                return null;
            }
            Class244 class245;
            if (!b) {
                class245 = new Class244(ha.method1804(method2184, method2183, true));
            }
            else {
                class245 = new Class244(ha.method1804(method2184, method2183, true), method2184);
            }
            Class232.aClass79_1740.method805(n, class245, (byte)(-80));
            return class245;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hga.B(" + n + ',' + b + ',' + ((ha != null) ? "{...}" : "null") + ',' + b2 + ')');
        }
    }
    
    static {
        Class114.anIntArray958 = new int[3];
    }
}
