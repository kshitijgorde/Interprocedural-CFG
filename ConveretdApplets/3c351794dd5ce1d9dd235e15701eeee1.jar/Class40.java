// 
// Decompiled by Procyon v0.5.30
// 

final class Class40
{
    long aLong365;
    int[] anIntArray366;
    static int[][] anIntArrayArray367;
    short[] aShortArray368;
    static IncomingOpcode aClass58_369;
    short[] aShortArray370;
    
    static final void method359(final int n, final boolean b) {
        try {
            Class378.method4005(Class39_Sub1.anInt3593, Class15.anInt185, -1, b, Class98_Sub25.anInt4024);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cr.B(" + n + ',' + b + ')');
        }
    }
    
    static final void method360(final byte b) {
        try {
            Class98_Sub10_Sub2.method1009(-63);
            Class128.method2224(22696);
            if (b != 79) {
                Class40.anIntArrayArray367 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cr.F(" + b + ')');
        }
    }
    
    static final void method361(final int n, final int n2) {
        try {
            Class185.method2628(n, -30, 16).method1621(n2 + n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cr.E(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method362(final byte b, final int anInt5536) {
        try {
            Class64_Sub25.anInt3711 = 3;
            Class287.anInt2186 = 100;
            Class98_Sub5_Sub2.anInt5536 = anInt5536;
            Class256.anInt1945 = -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cr.C(" + b + ',' + anInt5536 + ')');
        }
    }
    
    public static void method363(final int n) {
        try {
            if (n >= -55) {
                Class40.anIntArrayArray367 = null;
            }
            Class40.anIntArrayArray367 = null;
            Class40.aClass58_369 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cr.D(" + n + ')');
        }
    }
    
    static final void method364(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            int n7 = 0;
            int i = n;
            final int n8 = n3 * n3;
            final int n9 = n * n;
            final int n10 = n9 << -124913215;
            final int n11 = n8 << -1990005311;
            final int n12 = n << -1924555039;
            int j = n8 * (1 + -n12) - -n10;
            int n13 = n9 - n11 * (n12 - 1);
            final int n14 = n8 << 1567622306;
            final int n15 = n9 << 2060901122;
            int n16 = ((n7 << -27167295) + 3) * n10;
            int n17 = n11 * (-3 + (i << 500964065));
            int n18 = (1 + n7) * n15;
            if (Class98_Sub10_Sub38.anInt5753 <= n4 && ~Class218.anInt1635 <= ~n4) {
                Class333.method3761(n6, Class97.anIntArrayArray814[n4], Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, -n3 + n2), Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n2 + n3), (byte)(-125));
            }
            int n19 = (-1 + i) * n14;
            while (i > 0) {
                if (~j > -1) {
                    while (j < 0) {
                        n13 += n18;
                        j += n16;
                        n16 += n15;
                        ++n7;
                        n18 += n15;
                    }
                }
                if (n13 < 0) {
                    n13 += n18;
                    j += n16;
                    n16 += n15;
                    ++n7;
                    n18 += n15;
                }
                j += -n19;
                n13 += -n17;
                n19 -= n14;
                n17 -= n14;
                --i;
                final int n20 = n4 - i;
                final int n21 = i + n4;
                if (~n21 <= ~Class98_Sub10_Sub38.anInt5753 && ~n20 >= ~Class218.anInt1635) {
                    final int method3219 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, n2 + n7);
                    final int method3220 = Class263.method3219(false, Class3.anInt77, Class76_Sub8.anInt3778, -n7 + n2);
                    if (~n20 <= ~Class98_Sub10_Sub38.anInt5753) {
                        Class333.method3761(n6, Class97.anIntArrayArray814[n20], method3220, method3219, (byte)90);
                    }
                    if (Class218.anInt1635 < n21) {
                        continue;
                    }
                    Class333.method3761(n6, Class97.anIntArrayArray814[n21], method3220, method3219, (byte)2);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cr.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    Class40(final long aLong365, final int[] anIntArray366, final short[] aShortArray370, final short[] aShortArray371) {
        try {
            this.aShortArray368 = aShortArray371;
            this.aLong365 = aLong365;
            this.aShortArray370 = aShortArray370;
            this.anIntArray366 = anIntArray366;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cr.<init>(" + aLong365 + ',' + ((anIntArray366 != null) ? "{...}" : "null") + ',' + ((aShortArray370 != null) ? "{...}" : "null") + ',' + ((aShortArray371 != null) ? "{...}" : "null") + ')');
        }
    }
    
    protected Class40() {
    }
    
    static {
        Class40.aClass58_369 = new IncomingOpcode(11, 28);
    }
}
