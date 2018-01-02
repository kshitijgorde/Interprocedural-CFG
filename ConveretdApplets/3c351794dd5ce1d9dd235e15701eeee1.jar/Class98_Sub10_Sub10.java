// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub10 extends Class98_Sub10
{
    private int anInt5588;
    static int[] anIntArray5589;
    static int[] anIntArray5590;
    private int anInt5591;
    private int anInt5592;
    static String aString5593;
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            if (n != 255) {
                this.method1001((byte)(-46));
            }
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                final int[] method238 = this.method1000(n2, 0, 0);
                for (int i = 0; i < Class25.anInt268; ++i) {
                    method237[i] = this.anInt5591 + (method238[i] * this.anInt5588 >> 806237676);
                }
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ev.G(" + n + ',' + n2 + ')');
        }
    }
    
    static final Class method1037(final int n, final String s) throws ClassNotFoundException {
        try {
            if (s.equals("B")) {
                return Byte.TYPE;
            }
            if (s.equals("I")) {
                return Integer.TYPE;
            }
            if (s.equals("S")) {
                return Short.TYPE;
            }
            if (s.equals("J")) {
                return Long.TYPE;
            }
            if (s.equals("Z")) {
                return Boolean.TYPE;
            }
            if (s.equals("F")) {
                return Float.TYPE;
            }
            if (s.equals("D")) {
                return Double.TYPE;
            }
            if (s.equals("C")) {
                return Character.TYPE;
            }
            return Class.forName(s);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ev.E(" + n + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method1038(final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2, final int n, final int n2) {
        try {
            int anInt3708 = -1;
            int anInt3709 = 0;
            if (~Class215.anInt1614 <= ~class246_Sub3_Sub4_Sub2.anInt6390) {
                if (class246_Sub3_Sub4_Sub2.anInt6424 >= Class215.anInt1614) {
                    Class293.method3466((byte)20, class246_Sub3_Sub4_Sub2);
                }
                else {
                    Class333.method3762((byte)38, false, class246_Sub3_Sub4_Sub2);
                    anInt3709 = Class366.anInt3121;
                    anInt3708 = Class64_Sub23.anInt3708;
                }
            }
            else {
                Class165.method2523(n2 + 24500, class246_Sub3_Sub4_Sub2);
            }
            if (class246_Sub3_Sub4_Sub2.anInt5084 < 512 || class246_Sub3_Sub4_Sub2.anInt5079 < 512 || class246_Sub3_Sub4_Sub2.anInt5084 >= -512 + 512 * Class165.anInt1276 || ~class246_Sub3_Sub4_Sub2.anInt5079 <= ~(512 * Class98_Sub10_Sub7.anInt5572 - 512)) {
                class246_Sub3_Sub4_Sub2.anInt6379 = -1;
                class246_Sub3_Sub4_Sub2.anIntArray6373 = null;
                class246_Sub3_Sub4_Sub2.anInt6365 = -1;
                anInt3708 = -1;
                anInt3709 = 0;
                class246_Sub3_Sub4_Sub2.anInt6424 = 0;
                class246_Sub3_Sub4_Sub2.anInt6413 = -1;
                class246_Sub3_Sub4_Sub2.anInt6390 = 0;
                class246_Sub3_Sub4_Sub2.anInt5084 = class246_Sub3_Sub4_Sub2.anIntArray6437[0] * 512 - -(256 * class246_Sub3_Sub4_Sub2.method3034(0));
                class246_Sub3_Sub4_Sub2.anInt5079 = class246_Sub3_Sub4_Sub2.anIntArray6438[0] * 512 + 256 * class246_Sub3_Sub4_Sub2.method3034(0);
                class246_Sub3_Sub4_Sub2.method3031(0);
            }
            if (n2 != -12212) {
                Class98_Sub10_Sub10.anIntArray5589 = null;
            }
            if (class246_Sub3_Sub4_Sub2 == Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660 && (~class246_Sub3_Sub4_Sub2.anInt5084 > -6145 || ~class246_Sub3_Sub4_Sub2.anInt5079 > -6145 || class246_Sub3_Sub4_Sub2.anInt5084 >= (-12 + Class165.anInt1276) * 512 || (-12 + Class98_Sub10_Sub7.anInt5572) * 512 <= class246_Sub3_Sub4_Sub2.anInt5079)) {
                class246_Sub3_Sub4_Sub2.anIntArray6373 = null;
                class246_Sub3_Sub4_Sub2.anInt6424 = 0;
                anInt3708 = -1;
                class246_Sub3_Sub4_Sub2.anInt6413 = -1;
                class246_Sub3_Sub4_Sub2.anInt6379 = -1;
                class246_Sub3_Sub4_Sub2.anInt6365 = -1;
                anInt3709 = 0;
                class246_Sub3_Sub4_Sub2.anInt6390 = 0;
                class246_Sub3_Sub4_Sub2.anInt5084 = 512 * class246_Sub3_Sub4_Sub2.anIntArray6437[0] + 256 * class246_Sub3_Sub4_Sub2.method3034(n2 ^ 0xFFFFD04C);
                class246_Sub3_Sub4_Sub2.anInt5079 = 512 * class246_Sub3_Sub4_Sub2.anIntArray6438[0] + 256 * class246_Sub3_Sub4_Sub2.method3034(0);
                class246_Sub3_Sub4_Sub2.method3031(0);
            }
            final int method1041 = Class98_Sub10_Sub13.method1041(class246_Sub3_Sub4_Sub2, 0);
            Class108.method1729(n2 + 12114, class246_Sub3_Sub4_Sub2);
            Class284_Sub1_Sub2.method3370(anInt3709, n2 + 18356, class246_Sub3_Sub4_Sub2, anInt3708, method1041);
            Class282.method3334((byte)37, anInt3708, class246_Sub3_Sub4_Sub2);
            Class340.method3801(class246_Sub3_Sub4_Sub2, -28111);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ev.D(" + ((class246_Sub3_Sub4_Sub2 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    public Class98_Sub10_Sub10() {
        super(1, false);
        this.anInt5591 = 1024;
        this.anInt5588 = 2048;
        this.anInt5592 = 3072;
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b > -92) {
                this.method997(64, -121);
            }
            if (n != 0) {
                if (n != 1) {
                    if (~n == 0xFFFFFFFD) {
                        super.aBoolean3861 = (class98_Sub22.readUnsignedByte((byte)40) == 1);
                    }
                }
                else {
                    this.anInt5592 = class98_Sub22.readShort((byte)127);
                }
            }
            else {
                this.anInt5591 = class98_Sub22.readShort((byte)127);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ev.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public static void method1039(final int n) {
        try {
            Class98_Sub10_Sub10.anIntArray5589 = null;
            Class98_Sub10_Sub10.aString5593 = null;
            Class98_Sub10_Sub10.anIntArray5590 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ev.B(" + n + ')');
        }
    }
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (super.aClass223_3859.aBoolean1683) {
                final int[][] method2829 = this.method994(n2, 24431, 0);
                final int[] array = method2829[0];
                final int[] array2 = method2829[1];
                final int[] array3 = method2829[2];
                final int[] array4 = method2828[0];
                final int[] array5 = method2828[1];
                final int[] array6 = method2828[2];
                for (int n3 = 0; ~n3 > ~Class25.anInt268; ++n3) {
                    array4[n3] = this.anInt5591 - -(array[n3] * this.anInt5588 >> 1994769356);
                    array5[n3] = (this.anInt5588 * array2[n3] >> -1205225780) + this.anInt5591;
                    array6[n3] = (array3[n3] * this.anInt5588 >> 859422188) + this.anInt5591;
                }
            }
            if (n >= -76) {
                method1038(null, 10, -90);
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ev.C(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method1001(final byte b) {
        try {
            this.anInt5588 = -this.anInt5591 + this.anInt5592;
            if (b != 66) {
                this.method990(-42, 80);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ev.I(" + b + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub10.anIntArray5590 = new int[] { 4, 4, 1, 2, 6, 4, 2, 44, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1 };
        Class98_Sub10_Sub10.aString5593 = null;
    }
}
