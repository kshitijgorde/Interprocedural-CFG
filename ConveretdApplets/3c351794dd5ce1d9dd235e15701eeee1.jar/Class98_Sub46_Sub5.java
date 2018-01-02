// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub5 extends Class98_Sub46
{
    Class246_Sub3_Sub4_Sub4 aClass246_Sub3_Sub4_Sub4_5969;
    static Class174[] aClass174Array5970;
    
    static final boolean method1543(final int n, final int n2, final byte b) {
        try {
            if (!Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540) {
                return false;
            }
            if (b != 6) {
                method1543(-33, 90, (byte)(-77));
            }
            final int n3 = n >> -831084784;
            final int n4 = n & 0xFFFF;
            if (Class159.aClass293ArrayArray1252[n3] == null || Class159.aClass293ArrayArray1252[n3][n4] == null) {
                return false;
            }
            final Class293 class293 = Class159.aClass293ArrayArray1252[n3][n4];
            if (~n2 == 0x0 && ~class293.anInt2354 == -1) {
                for (Class98_Sub46_Sub8 class98_Sub46_Sub8 = (Class98_Sub46_Sub8)Class33.aClass148_315.method2418(32); class98_Sub46_Sub8 != null; class98_Sub46_Sub8 = (Class98_Sub46_Sub8)Class33.aClass148_315.method2417(b + 99)) {
                    if (class98_Sub46_Sub8.anInt5990 == 59 || ~class98_Sub46_Sub8.anInt5990 == 0xFFFFFC11 || class98_Sub46_Sub8.anInt5990 == 21 || class98_Sub46_Sub8.anInt5990 == 49 || class98_Sub46_Sub8.anInt5990 == 9) {
                        for (Class293 class294 = Class159.method2509(class98_Sub46_Sub8.anInt5993, b ^ 0xFFFFD9A2); class294 != null; class294 = Class360.method3910(true, class294)) {
                            if (~class294.anInt2248 == ~class293.anInt2248) {
                                return true;
                            }
                        }
                    }
                }
            }
            else {
                for (Class98_Sub46_Sub8 class98_Sub46_Sub9 = (Class98_Sub46_Sub8)Class33.aClass148_315.method2418(32); class98_Sub46_Sub9 != null; class98_Sub46_Sub9 = (Class98_Sub46_Sub8)Class33.aClass148_315.method2417(112)) {
                    if (n2 == class98_Sub46_Sub9.anInt5995 && class98_Sub46_Sub9.anInt5993 == class293.anInt2248 && (~class98_Sub46_Sub9.anInt5990 == 0xFFFFFFC4 || class98_Sub46_Sub9.anInt5990 == 1006 || class98_Sub46_Sub9.anInt5990 == 21 || class98_Sub46_Sub9.anInt5990 == 49 || class98_Sub46_Sub9.anInt5990 == 9)) {
                        return true;
                    }
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "de.A(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    static final int method1544(int n, final byte b, final int n2) {
        try {
            if (b <= 103) {
                return 86;
            }
            final int n3 = n >>> -280491016;
            n = ((n3 * (0xFF00 & n) & 0xFF0000) | (0xFF00FF00 & n3 * (0xFF00FF & n))) >>> 591549448;
            final int n4 = 255 + -n3;
            return (((n4 * (n2 & 0xFF00) & 0xFF0000) | (0xFF00FF00 & (0xFF00FF & n2) * n4)) >>> -1712218008) + n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "de.B(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    public static void method1545(final byte b) {
        try {
            Class98_Sub46_Sub5.aClass174Array5970 = null;
            if (b != 78) {
                Class98_Sub46_Sub5.aClass174Array5970 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "de.C(" + b + ')');
        }
    }
    
    Class98_Sub46_Sub5(final Class246_Sub3_Sub4_Sub4 aClass246_Sub3_Sub4_Sub4_5969) {
        try {
            this.aClass246_Sub3_Sub4_Sub4_5969 = aClass246_Sub3_Sub4_Sub4_5969;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "de.<init>(" + ((aClass246_Sub3_Sub4_Sub4_5969 != null) ? "{...}" : "null") + ')');
        }
    }
}
