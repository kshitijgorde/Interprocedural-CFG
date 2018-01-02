// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub39 extends Class98_Sub10
{
    static long[] aLongArray5772;
    static Class207 aClass207_5773;
    
    public static void method1119(final int n) {
        try {
            Class98_Sub10_Sub39.aLongArray5772 = null;
            if (n != 0) {
                Class98_Sub10_Sub39.aClass207_5773 = null;
            }
            Class98_Sub10_Sub39.aClass207_5773 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wm.B(" + n + ')');
        }
    }
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (n >= -76) {
                Class98_Sub10_Sub39.aClass207_5773 = null;
            }
            if (super.aClass223_3859.aBoolean1683) {
                final int[][] method2829 = this.method994(n2, 24431, 0);
                final int[] array = method2829[0];
                final int[] array2 = method2829[1];
                final int[] array3 = method2829[2];
                final int[] array4 = method2828[0];
                final int[] array5 = method2828[1];
                final int[] array6 = method2828[2];
                for (int n3 = 0; ~Class25.anInt268 < ~n3; ++n3) {
                    array4[n3] = -array[n3] + 4096;
                    array5[n3] = 4096 + -array2[n3];
                    array6[n3] = -array3[n3] + 4096;
                }
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wm.C(" + n + ',' + n2 + ')');
        }
    }
    
    public Class98_Sub10_Sub39() {
        super(1, false);
    }
    
    static final long method1120(final Interface19 interface19, final byte b, final int n, final int n2) {
        try {
            if (b != 113) {
                return 76L;
            }
            final long n3 = 4194304L;
            final long n4 = Long.MIN_VALUE;
            final Class352 method3546 = Class130.aClass302_1028.method3546(interface19.method64(30472), (byte)119);
            long n5 = interface19.method63((byte)20) << 1932511150 | (n2 << -804407641 | n) | interface19.method66(b ^ 0x1240) << -221685036 | 0x40000000;
            if (method3546.anInt2998 == 0) {
                n5 |= n4;
            }
            if (method3546.anInt2975 == 1) {
                n5 |= n3;
            }
            return n5 | interface19.method64(b ^ 0x7779) << -1810428832;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wm.F(" + ((interface19 != null) ? "{...}" : "null") + ',' + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (n != 255) {
                method1120(null, (byte)69, -45, -45);
            }
            if (super.aClass16_3863.aBoolean198) {
                final int[] method238 = this.method1000(n2, 0, 0);
                for (int n3 = 0; ~n3 > ~Class25.anInt268; ++n3) {
                    method237[n3] = -method238[n3] + 4096;
                }
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wm.G(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method1121(final byte b, final ha aHa1231, final int n, final int n2, final int anInt3183, final int anInt3184) {
        try {
            Class154.aHa1231 = aHa1231;
            Class98_Sub5_Sub3.aClass111_5540 = Class154.aHa1231.method1821();
            Class42_Sub3.aClass111_5364 = Class154.aHa1231.method1821();
            Class200.aClass111_1543 = Class154.aHa1231.method1821();
            Class146_Sub2.anIntArray4873 = null;
            Class111_Sub3.anInt4701 = anInt3184;
            Class377.anInt3183 = anInt3183;
            Class172.anInterface17Array1327 = null;
            Class98_Sub46.anInt4261 = 0;
            Class287_Sub2.method3391(n, n2, 2);
            Class224_Sub2_Sub1.anInt6141 = -1;
            aa_Sub1.anInt3558 = -1;
            Class109.anInt926 = -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wm.D(" + b + ',' + ((aHa1231 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + anInt3183 + ',' + anInt3184 + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b < -92) {
                if (~n == -1) {
                    super.aBoolean3861 = (class98_Sub22.readUnsignedByte((byte)84) == 1);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wm.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final String method1122(final int n, final byte b) {
        try {
            if (b >= -11) {
                return null;
            }
            return String.valueOf(n >> 1735606296 & 0xFF) + "." + (0xFF & n >> -889935376) + "." + (n >> 1298758568 & 0xFF) + "." + (0xFF & n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wm.E(" + n + ',' + b + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub39.aLongArray5772 = new long[10];
    }
}
