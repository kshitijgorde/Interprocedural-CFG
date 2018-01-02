// 
// Decompiled by Procyon v0.5.30
// 

abstract class za extends Class98
{
    static Class63 aClass63_4296;
    
    static final int method1674(final int n, final int n2) {
        try {
            if (n != -1035933944) {
                return -95;
            }
            return n2 >>> -1035933944;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "za.B(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method1675(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final byte b) {
        try {
            if (n3 == n) {
                Class267.method3247(n4, n7, n6, n5, n, n2, 1333849452);
            }
            else if (-n + n6 >= Class76_Sub8.anInt3778 && n6 + n <= Class3.anInt77 && ~Class98_Sub10_Sub38.anInt5753 >= ~(n4 + -n3) && n4 + n3 <= Class218.anInt1635) {
                Class64_Sub18.method624(n7, -2211, n6, n2, n, n4, n5, n3);
            }
            else {
                Class48_Sub2_Sub1.method473(n4, n2, n6, n3, n5, n, -25682, n7);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "za.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + b + ')');
        }
    }
    
    public static void method1676(final int n) {
        try {
            za.aClass63_4296 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "za.C(" + n + ')');
        }
    }
    
    static {
        za.aClass63_4296 = new Class63();
    }
}
