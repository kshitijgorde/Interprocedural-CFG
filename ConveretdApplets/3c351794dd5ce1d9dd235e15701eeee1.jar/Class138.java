import java.math.BigInteger;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class138
{
    static BigInteger aBigInteger1082;
    static int[] anIntArray1083;
    static Class218 aClass218_1084;
    static int anInt1085;
    
    static final Class337_Sub1 method2277(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            if (n < 12) {
                Class138.anInt1085 = -116;
            }
            final Class337 method1796 = ha.method1796(9342, class98_Sub22);
            return new Class337_Sub1(method1796.anInt3535, method1796.aClass63_3538, method1796.aClass110_3540, method1796.anInt3541, method1796.anInt3542, class98_Sub22.method1227((byte)(-1)));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jg.C(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final void method2278(final int n, final s s) {
        Class78.aSArray594[n] = s;
    }
    
    public static void method2279(final int n) {
        try {
            Class138.aClass218_1084 = null;
            Class138.anIntArray1083 = null;
            Class138.aBigInteger1082 = null;
            if (n != 100) {
                Class138.anInt1085 = 77;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jg.B(" + n + ')');
        }
    }
    
    static final Class131 method2280(final byte b, final int n) {
        try {
            if (b != 49) {
                Class138.aClass218_1084 = null;
            }
            if (~n > -1 || n >= 100) {
                return null;
            }
            return Class98_Sub46_Sub3.aClass131Array5953[n];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jg.A(" + b + ',' + n + ')');
        }
    }
    
    static {
        Class138.aBigInteger1082 = new BigInteger("10001", 16);
        Class138.aClass218_1084 = new Class218();
    }
}
