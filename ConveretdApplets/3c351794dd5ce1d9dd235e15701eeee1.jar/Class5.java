// 
// Decompiled by Procyon v0.5.30
// 

final class Class5 implements Interface18
{
    private Class207 aClass207_3437;
    private Class332 aClass332_3438;
    static int anInt3439;
    static String aString3440;
    private Class367 aClass367_3441;
    
    @Override
    public final void method58(final byte b) {
        try {
            if (b == -43) {
                this.aClass332_3438 = Class237_Sub1.method2915(this.aClass367_3441.anInt3544, this.aClass207_3437, (byte)(-89));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "af.C(" + b + ')');
        }
    }
    
    @Override
    public final boolean method59(final int n) {
        try {
            if (n != 14017) {
                this.aClass207_3437 = null;
            }
            return this.aClass207_3437.method2742(-77, this.aClass367_3441.anInt3544);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "af.A(" + n + ')');
        }
    }
    
    static final boolean method176(final int n, final int n2, final int n3) {
        try {
            if (n != 24578) {
                method176(-22, 97, 98);
            }
            return ~(n2 & 0x800) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "af.F(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    public static void method177(final int n) {
        try {
            if (n != 7681) {
                method176(-63, 99, -33);
            }
            Class5.aString3440 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "af.E(" + n + ')');
        }
    }
    
    static final int method178(final Class128 class128, final int n) {
        try {
            if (n != 0) {
                method178(null, 3);
            }
            if (class128 == Class288.aClass128_3381) {
                return 7681;
            }
            if (Class249.aClass128_1903 == class128) {
                return 8448;
            }
            if (Class323.aClass128_2715 == class128) {
                return 34165;
            }
            if (class128 == Class1.aClass128_64) {
                return 260;
            }
            if (Class28.aClass128_286 == class128) {
                return 34023;
            }
            throw new IllegalArgumentException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "af.D(" + ((class128 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    public final void method60(final boolean b, final byte b2) {
        try {
            if (b) {
                final int n = (~Class39_Sub1.anInt3593 < ~Class98_Sub17_Sub1.anInt5782) ? Class39_Sub1.anInt3593 : Class98_Sub17_Sub1.anInt5782;
                final int n2 = (Class246_Sub2.anInt5072 >= Class98_Sub25.anInt4024) ? Class246_Sub2.anInt5072 : Class98_Sub25.anInt4024;
                final int method3737 = this.aClass332_3438.method3737();
                final int method3738 = this.aClass332_3438.method3749();
                int n3 = 0;
                int n4 = n;
                int n5 = n * method3738 / method3737;
                int n6 = (n2 - n5) / 2;
                if (~n2 > ~n5) {
                    n4 = method3737 * n2 / method3738;
                    n6 = 0;
                    n5 = n2;
                    n3 = (-n4 + n) / 2;
                }
                this.aClass332_3438.method3726(n3, n6, n4, n5);
            }
            if (b2 >= -81) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "af.B(" + b + ',' + b2 + ')');
        }
    }
    
    Class5(final Class207 aClass207_3437, final Class367 aClass367_3441) {
        try {
            this.aClass367_3441 = aClass367_3441;
            this.aClass207_3437 = aClass207_3437;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "af.<init>(" + ((aClass207_3437 != null) ? "{...}" : "null") + ',' + ((aClass367_3441 != null) ? "{...}" : "null") + ')');
        }
    }
}
