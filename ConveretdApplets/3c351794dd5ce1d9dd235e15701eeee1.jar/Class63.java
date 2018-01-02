import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class63
{
    static int[] anIntArray491;
    static int anInt492;
    static int anInt493;
    
    final int method545(final int n, final int n2, final byte b) {
        try {
            final int n3 = (Class39_Sub1.anInt3593 > n) ? Class39_Sub1.anInt3593 : n;
            if (this == Class98_Sub35.aClass63_4151) {
                return 0;
            }
            if (this == za.aClass63_4296) {
                return -n2 + n3;
            }
            if (this == Class368.aClass63_3126) {
                return (n3 - n2) / 2;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ee.C(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    static final int method546(final int n, final int n2, final Random random) {
        try {
            if (n2 <= 0) {
                throw new IllegalArgumentException();
            }
            if (Class81.method815(n2, 0)) {
                return (int)((0xFFFFFFFFL & random.nextInt()) * n2 >> -1163578144);
            }
            if (n != -28737) {
                method549(null, false, (byte)77);
            }
            int nextInt;
            do {
                nextInt = random.nextInt();
            } while (-(int)(4294967296L % n2) + Integer.MIN_VALUE <= nextInt);
            return Class198.method2678((byte)6, nextInt, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ee.D(" + n + ',' + n2 + ',' + ((random != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final boolean method547(final int n, final int n2, final Class228 class228, final int n3, final int n4, final int n5, final int n6) {
        try {
            if (n3 != 0) {
                Class63.anInt492 = 76;
            }
            if (!Class98_Sub17.aBoolean3942 || !Class135.aBoolean1052) {
                return false;
            }
            if (~Class4.anInt81 > -101) {
                return false;
            }
            if (~n4 == ~n && n5 == n2) {
                if (!Class76_Sub5.method758((byte)105, n6, n2, n)) {
                    return false;
                }
                if (Class98_Sub46_Sub14.method1607(class228, (byte)86)) {
                    ++Class151_Sub7.anInt5006;
                    return true;
                }
                return false;
            }
            else {
                for (int n7 = n; ~n4 <= ~n7; ++n7) {
                    for (int n8 = n2; ~n8 >= ~n5; ++n8) {
                        if (Class98_Sub46_Sub13_Sub2.anIntArrayArrayArray6311[n6][n7][n8] == -Class356.anInt3020) {
                            return false;
                        }
                    }
                }
                if (!Class98_Sub46_Sub14.method1607(class228, (byte)112)) {
                    return false;
                }
                ++Class151_Sub7.anInt5006;
                return true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ee.A(" + n + ',' + n2 + ',' + ((class228 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    public static void method548(final byte b) {
        try {
            if (b != -60) {
                Class63.anInt492 = 107;
            }
            Class63.anIntArray491 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ee.B(" + b + ')');
        }
    }
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ee.toString()");
        }
    }
    
    static final void method549(final Class293 class293, final boolean b, final byte b2) {
        try {
            final int n = (class293.anInt2290 != 0) ? class293.anInt2290 : class293.anInt2311;
            final int n2 = (~class293.anInt2228 == -1) ? class293.anInt2258 : class293.anInt2228;
            Class224_Sub2.method2837(b, Class159.aClass293ArrayArray1252[class293.anInt2248 >> 949187376], n, true, n2, class293.anInt2248);
            if (class293.aClass293Array2339 != null) {
                Class224_Sub2.method2837(b, class293.aClass293Array2339, n, true, n2, class293.anInt2248);
            }
            final Class98_Sub18 class98_Sub18 = (Class98_Sub18)Class116.aClass377_964.method3990(class293.anInt2248, -1);
            if (class98_Sub18 != null) {
                Class378.method4005(n, class98_Sub18.anInt3945, -1, b, n2);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ee.E(" + ((class293 != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ')');
        }
    }
    
    static {
        Class63.anIntArray491 = new int[6];
    }
}
