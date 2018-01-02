// 
// Decompiled by Procyon v0.5.30
// 

final class Class318
{
    static Class123 aClass123_2698;
    
    public static void method3655(final boolean b) {
        try {
            if (b) {
                Class318.aClass123_2698 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tk.B(" + b + ')');
        }
    }
    
    static final void method3656(final int n, int method3219, final byte b, int method3220, final int n2) {
        try {
            if (n2 >= Class98_Sub10_Sub38.anInt5753 && Class218.anInt1635 >= n2) {
                method3219 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, method3219);
                method3220 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, method3220);
                Class160.method2513((byte)(-125), n, method3219, method3220, n2);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tk.C(" + n + ',' + method3219 + ',' + b + ',' + method3220 + ',' + n2 + ')');
        }
    }
    
    static final void method3657(final byte b) {
        try {
            Class69_Sub2.aClass79_5334.method794(77);
            Class64_Sub5.aClass79_3650.method794(119);
            Class76_Sub11.aClass79_3797.method794(47);
            if (b > -20) {
                method3655(true);
            }
            Class151_Sub7.aClass79_5004.method794(108);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tk.A(" + b + ')');
        }
    }
}
