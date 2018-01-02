// 
// Decompiled by Procyon v0.5.30
// 

final class Class373_Sub3 extends Class373
{
    static Class85 aClass85_5474;
    private Class332 aClass332_5475;
    static long[][][] aLongArrayArrayArray5476;
    
    static final void method3976(final int n, final byte b, final String s) {
        try {
            final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(260, PlayerUpdateMask.aClass171_524, Class331.aClass117_2811);
            method3023.aClass98_Sub22_Sub1_3865.method1194(1 + r_Sub2.method1650(s, (byte)97), -38);
            if (b < 65) {
                Class373_Sub3.aLongArrayArrayArray5476 = null;
            }
            method3023.aClass98_Sub22_Sub1_3865.method1188(s, (byte)113);
            method3023.aClass98_Sub22_Sub1_3865.method1231(n, (byte)(-108));
            Class98_Sub10_Sub29.sendPacket(false, method3023);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sq.I(" + n + ',' + b + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method3960(final int n, final int n2, final byte b, final boolean b2) {
        try {
            Class265.aHa1974.method1779(n2 - 2, n, 4 + super.aClass93_3478.anInt3514, super.aClass93_3478.anInt3504 + 2, ((Class93_Sub2)super.aClass93_3478).anInt5492, 0);
            if (b == -36) {
                Class265.aHa1974.method1779(-1 + n2, n + 1, super.aClass93_3478.anInt3514 + 2, super.aClass93_3478.anInt3504, 0, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sq.D(" + n + ',' + n2 + ',' + b + ',' + b2 + ')');
        }
    }
    
    @Override
    public final boolean method59(final int n) {
        try {
            return super.method59(n) && super.aClass207_3473.method2742(-87, ((Class93_Sub2)super.aClass93_3478).anInt5490);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sq.A(" + n + ')');
        }
    }
    
    static final void method3977(final boolean b) {
        try {
            if (!b) {
                Class373_Sub3.aClass85_5474 = null;
            }
            Class118.method2173(false, -125);
            if (~Class132.anInt1050 <= -1 && ~Class132.anInt1050 != -1) {
                Class76_Sub4.method754(Class132.anInt1050, false, -117);
                Class132.anInt1050 = -1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sq.G(" + b + ')');
        }
    }
    
    Class373_Sub3(final Class207 class207, final Class207 class208, final Class93_Sub2 class93_Sub2) {
        super(class207, class208, class93_Sub2);
    }
    
    @Override
    public final void method58(final byte b) {
        try {
            super.method58(b);
            this.aClass332_5475 = Class237_Sub1.method2915(((Class93_Sub2)super.aClass93_3478).anInt5490, super.aClass207_3473, (byte)(-89));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sq.C(" + b + ')');
        }
    }
    
    @Override
    final void method3965(final int n, final int n2, final int n3, final boolean b) {
        try {
            final int n4 = this.method3963(n + 98) * super.aClass93_3478.anInt3514 / 10000;
            final int[] array = new int[4];
            Class265.aHa1974.K(array);
            Class265.aHa1974.KA(n3, n2 + n, n3 + n4, n2 - -super.aClass93_3478.anInt3504);
            this.aClass332_5475.method3738(n3, 2 + n2, super.aClass93_3478.anInt3514, super.aClass93_3478.anInt3504);
            Class265.aHa1974.KA(array[0], array[1], array[2], array[3]);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sq.F(" + n + ',' + n2 + ',' + n3 + ',' + b + ')');
        }
    }
    
    static final boolean method3978(final int n, final int n2, final byte b) {
        try {
            if (b <= 53) {
                Class373_Sub3.aLongArrayArrayArray5476 = null;
            }
            return ~(0x220 & n2) == 0xFFFFFDDF | ~(0x18 & n2) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sq.H(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    public static void method3979(final boolean b) {
        try {
            if (b) {
                Class373_Sub3.aLongArrayArrayArray5476 = null;
            }
            Class373_Sub3.aLongArrayArrayArray5476 = null;
            Class373_Sub3.aClass85_5474 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sq.E(" + b + ')');
        }
    }
    
    static {
        Class373_Sub3.aClass85_5474 = new Class85(1, 7);
    }
}
