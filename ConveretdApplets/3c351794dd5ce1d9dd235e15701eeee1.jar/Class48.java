// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class48
{
    static int anInt409;
    static int anInt410;
    
    static final int method453(final int n, int n2) {
        try {
            n2 = (--n2 | n2 >>> 423660257);
            n2 |= n2 >>> -329257566;
            n2 |= n2 >>> -1109629244;
            n2 |= n2 >>> 1115354056;
            n2 |= n2 >>> -1953896048;
            if (n != 423660257) {
                Class48.anInt409 = -77;
            }
            return n2 + 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "di.K(" + n + ',' + n2 + ')');
        }
    }
    
    static final String method454(final int n, final boolean b) {
        try {
            if (!b) {
                method453(94, -10);
            }
            final Class98_Sub36 class98_Sub36 = (Class98_Sub36)Class156_Sub1.aClass377_3277.method3990(n, -1);
            if (class98_Sub36 != null) {
                final Class98_Sub43_Sub4 method2913 = class98_Sub36.aClass237_Sub1_4157.method2913(1);
                if (method2913 != null) {
                    final double method2914 = class98_Sub36.aClass237_Sub1_4157.method2901((byte)(-100));
                    if (method2913.method1511(-48) <= method2914 && method2914 <= method2913.method1509(-6085)) {
                        return method2913.method1503(22875);
                    }
                }
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "di.L(" + n + ',' + b + ')');
        }
    }
    
    static {
        Class48.anInt410 = -60;
        Class48.anInt409 = 0;
    }
}
