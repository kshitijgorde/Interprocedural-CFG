// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub13_Sub1 extends Class98_Sub46_Sub13
{
    int anInt6304;
    Class98_Sub22 aClass98_Sub22_6305;
    byte aByte6306;
    static Class258 aClass258_6307;
    static int[] anIntArray6308;
    
    @Override
    final int method1590(final int n) {
        try {
            if (n != 100) {
                return -60;
            }
            if (this.aClass98_Sub22_6305 == null) {
                return 0;
            }
            return this.aClass98_Sub22_6305.anInt3991 * 100 / (-this.aByte6306 + this.aClass98_Sub22_6305.aByteArray3992.length);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gb.B(" + n + ')');
        }
    }
    
    static final void method1593(final byte b) {
        try {
            int n = 0;
            if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub10_4070.method592((byte)127) == 0xFFFFFFFE) {
                n = (n | 0x1 | 0x10 | 0x20 | 0x2 | 0x4);
            }
            if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub20_4056.method634((byte)124) == 0) {
                n |= 0x40;
            }
            Class98_Sub46_Sub16.method1618(n, (byte)(-85));
            Class130.aClass302_1028.method3554(true, n);
            Class98_Sub46_Sub19.aClass205_6068.method2712(60, n);
            Class4.aClass301_85.method3541(true, n);
            Class196.aClass304_1509.method3560(n, 0);
            Class64_Sub13.method606(18279, n);
            Class97.method936(n, 1024);
            Class65.method678(n, false);
            Class148.method2429(117, n);
            if (b <= 44) {
                method1595(85);
            }
            Class98_Sub10.method999((byte)124);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gb.G(" + b + ')');
        }
    }
    
    @Override
    final byte[] method1591(final int n) {
        try {
            if (n < 5) {
                return null;
            }
            if (super.aBoolean6038 || this.aClass98_Sub22_6305.aByteArray3992.length - this.aByte6306 > this.aClass98_Sub22_6305.anInt3991) {
                throw new RuntimeException();
            }
            return this.aClass98_Sub22_6305.aByteArray3992;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gb.C(" + n + ')');
        }
    }
    
    static final void method1594(final int n, final int n2, final int n3, final int n4, final byte b) {
        try {
            int anInt3117 = Class366.anInt3117;
            int anInt3118 = Class98_Sub49.anInt4286;
            if (za_Sub2.aBoolean6079) {
                anInt3117 += Class189.method2642((byte)42);
                anInt3118 += Class335.method3765(false);
            }
            if (b <= 115) {
                Class98_Sub46_Sub13_Sub1.anIntArray6308 = null;
            }
            if (~Class55.anInt440 == 0xFFFFFFFE) {
                final Class332 class332 = Class76_Sub7.aClass332Array3764[Class98_Sub10_Sub32.anInt5720 / 100];
                class332.method3735(anInt3117 - 8, -8 + anInt3118);
                Class93_Sub1_Sub1.method908(class332.method3749() - 8 + anInt3118, -8 + anInt3118, false, anInt3117 - 8, -8 + (anInt3117 - -class332.method3737()));
            }
            if (Class55.anInt440 == 2) {
                final Class332 class333 = Class76_Sub7.aClass332Array3764[4 - -(Class98_Sub10_Sub32.anInt5720 / 100)];
                class333.method3735(-8 + anInt3117, -8 + anInt3118);
                Class93_Sub1_Sub1.method908(class333.method3749() - 8 + anInt3118, -8 + anInt3118, false, anInt3117 - 8, -8 + anInt3117 + class333.method3737());
            }
            Class64_Sub18.method623(-23196);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gb.D(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    static final Class63[] method1595(final int n) {
        try {
            if (n <= 102) {
                method1596((byte)125);
            }
            return new Class63[] { Class98_Sub35.aClass63_4151, Class368.aClass63_3126, za.aClass63_4296 };
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gb.F(" + n + ')');
        }
    }
    
    public static void method1596(final byte b) {
        try {
            if (b > 6) {
                Class98_Sub46_Sub13_Sub1.anIntArray6308 = null;
                Class98_Sub46_Sub13_Sub1.aClass258_6307 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gb.A(" + b + ')');
        }
    }
    
    static final void method1597(final int[] array, final Object[] array2, final int n) {
        try {
            Class33.method323(array, array2, n, -1 + array.length, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gb.E(" + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static {
        Class98_Sub46_Sub13_Sub1.aClass258_6307 = new Class258();
    }
}
