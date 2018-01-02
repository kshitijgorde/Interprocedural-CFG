// 
// Decompiled by Procyon v0.5.30
// 

final class Class29
{
    private Class207 aClass207_296;
    private Class79 aClass79_297;
    static Class17 aClass17_298;
    
    static final boolean method300(final boolean b, final int n, final int n2) {
        try {
            if (b) {
                Class29.aClass17_298 = null;
            }
            return Class98_Sub5_Sub2.method969(n, n2, 83) || Class228.method2864(55, n2, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cc.A(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    public static void method301(final int n) {
        try {
            if (n != -23881) {
                method301(-97);
            }
            Class29.aClass17_298 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cc.D(" + n + ')');
        }
    }
    
    final Class306 method302(final int n, final int n2) {
        try {
            final Class306 class306;
            synchronized (this.aClass79_297) {
                class306 = (Class306)this.aClass79_297.method802(-123, n);
            }
            if (class306 != null) {
                return class306;
            }
            final byte[] method2745 = this.aClass207_296.method2745(Class98_Sub10_Sub9.method1032(n, (byte)39), Class153.method2490(n, false), false);
            final Class306 class307 = new Class306();
            if (method2745 != null) {
                class307.method3593(new Class98_Sub22(method2745), 4);
            }
            synchronized (this.aClass79_297) {
                this.aClass79_297.method805(n, class307, (byte)(-80));
                if (n2 != 1028602529) {
                    method303(46, 67, -18, (byte)67, 54, 98, -114);
                }
            }
            return class307;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cc.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method303(final int n, final int n2, final int n3, final byte b, final int n4, final int n5, final int n6) {
        try {
            Class231.method2875(256, n);
            int n7 = 0;
            int n8 = -n6 + n;
            if (n8 < 0) {
                n8 = 0;
            }
            int i = n;
            int n9 = -n;
            int n10 = n8;
            int n11 = -n8;
            int n12 = -1;
            int n13 = -1;
            if (~Class98_Sub10_Sub38.anInt5753 >= ~n4 && ~Class218.anInt1635 <= ~n4) {
                final int[] array = Class97.anIntArrayArray814[n4];
                final int method3219 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n5 + -n);
                final int method3220 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n + n5);
                final int method3221 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, -n8 + n5);
                final int method3222 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n5 + n8);
                Class333.method3761(n2, array, method3219, method3221, (byte)(-126));
                Class333.method3761(n3, array, method3221, method3222, (byte)(-123));
                Class333.method3761(n2, array, method3222, method3220, (byte)(-125));
            }
            while (i > n7) {
                n13 += 2;
                n12 += 2;
                n11 += n13;
                n9 += n12;
                if (~n11 <= -1 && ~n10 <= -2) {
                    --n10;
                    n11 -= n10 << 1028602529;
                    Class331.anIntArray2810[n10] = n7;
                }
                ++n7;
                if (n9 >= 0) {
                    --i;
                    n9 -= i << 173127649;
                    final int n14 = n4 + -i;
                    final int n15 = i + n4;
                    if (Class98_Sub10_Sub38.anInt5753 <= n15 && Class218.anInt1635 >= n14) {
                        if (n8 <= i) {
                            final int method3223 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n5 - -n7);
                            final int method3224 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n5 - n7);
                            if (~n15 >= ~Class218.anInt1635) {
                                Class333.method3761(n2, Class97.anIntArrayArray814[n15], method3224, method3223, (byte)50);
                            }
                            if (~n14 <= ~Class98_Sub10_Sub38.anInt5753) {
                                Class333.method3761(n2, Class97.anIntArrayArray814[n14], method3224, method3223, (byte)14);
                            }
                        }
                        else {
                            final int n16 = Class331.anIntArray2810[i];
                            final int method3225 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n5 - -n7);
                            final int method3226 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n5 + -n7);
                            final int method3227 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n16 + n5);
                            final int method3228 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, -n16 + n5);
                            if (Class218.anInt1635 >= n15) {
                                final int[] array2 = Class97.anIntArrayArray814[n15];
                                Class333.method3761(n2, array2, method3226, method3228, (byte)(-127));
                                Class333.method3761(n3, array2, method3228, method3227, (byte)24);
                                Class333.method3761(n2, array2, method3227, method3225, (byte)(-127));
                            }
                            if (~n14 <= ~Class98_Sub10_Sub38.anInt5753) {
                                final int[] array3 = Class97.anIntArrayArray814[n14];
                                Class333.method3761(n2, array3, method3226, method3228, (byte)(-123));
                                Class333.method3761(n3, array3, method3228, method3227, (byte)81);
                                Class333.method3761(n2, array3, method3227, method3225, (byte)(-126));
                            }
                        }
                    }
                }
                final int n17 = n4 + -n7;
                final int n18 = n7 + n4;
                if (n18 >= Class98_Sub10_Sub38.anInt5753 && ~Class218.anInt1635 <= ~n17) {
                    final int n19 = n5 - -i;
                    final int n20 = -i + n5;
                    if (Class76_Sub8.anInt3778 > n19 || ~n20 < ~Class3.anInt77) {
                        continue;
                    }
                    final int method3229 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n19);
                    final int method3230 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n20);
                    if (~n7 <= ~n8) {
                        if (~n18 >= ~Class218.anInt1635) {
                            Class333.method3761(n2, Class97.anIntArrayArray814[n18], method3230, method3229, (byte)122);
                        }
                        if (Class98_Sub10_Sub38.anInt5753 > n17) {
                            continue;
                        }
                        Class333.method3761(n2, Class97.anIntArrayArray814[n17], method3230, method3229, (byte)(-128));
                    }
                    else {
                        final int n21 = (n7 <= n10) ? n10 : Class331.anIntArray2810[n7];
                        final int method3231 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n5 + n21);
                        final int method3232 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n5 + -n21);
                        if (~Class218.anInt1635 <= ~n18) {
                            final int[] array4 = Class97.anIntArrayArray814[n18];
                            Class333.method3761(n2, array4, method3230, method3232, (byte)(-123));
                            Class333.method3761(n3, array4, method3232, method3231, (byte)(-127));
                            Class333.method3761(n2, array4, method3231, method3229, (byte)57);
                        }
                        if (n17 < Class98_Sub10_Sub38.anInt5753) {
                            continue;
                        }
                        final int[] array5 = Class97.anIntArrayArray814[n17];
                        Class333.method3761(n2, array5, method3230, method3232, (byte)(-128));
                        Class333.method3761(n3, array5, method3232, method3231, (byte)15);
                        Class333.method3761(n2, array5, method3231, method3229, (byte)73);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cc.B(" + n + ',' + n2 + ',' + n3 + ',' + b + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    Class29(final Class279 class279, final int n, final Class207 aClass207_296) {
        this.aClass79_297 = new Class79(128);
        try {
            this.aClass207_296 = aClass207_296;
            if (this.aClass207_296 != null) {
                this.aClass207_296.method2761(0, -1 + this.aClass207_296.method2752((byte)(-11)));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cc.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_296 != null) ? "{...}" : "null") + ')');
        }
    }
}
