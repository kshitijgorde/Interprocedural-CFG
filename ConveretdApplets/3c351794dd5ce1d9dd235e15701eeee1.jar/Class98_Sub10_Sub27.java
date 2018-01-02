// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub27 extends Class98_Sub10
{
    static Class84 aClass84_5692;
    static Class84 aClass84_5693;
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            if (n > -76) {
                return null;
            }
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (super.aClass223_3859.aBoolean1683) {
                final int[] array = method2828[0];
                final int[] array2 = method2828[1];
                final int[] array3 = method2828[2];
                for (int i = 0; i < Class25.anInt268; ++i) {
                    this.method1087(-2048, n2, i);
                    final int[][] method2829 = this.method994(Class134.anInt3464, 24431, 0);
                    array[i] = method2829[0][Class98_Sub10_Sub23.anInt5661];
                    array2[i] = method2829[1][Class98_Sub10_Sub23.anInt5661];
                    array3[i] = method2829[2][Class98_Sub10_Sub23.anInt5661];
                }
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ow.C(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            if (n != 255) {
                return null;
            }
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                for (int n3 = 0; ~n3 > ~Class25.anInt268; ++n3) {
                    this.method1087(-2048, n2, n3);
                    method237[n3] = this.method1000(Class134.anInt3464, 0, n ^ 0xFF)[Class98_Sub10_Sub23.anInt5661];
                }
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ow.G(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b > -92) {
                this.method990(-69, 63);
            }
            if (~n == -1) {
                super.aBoolean3861 = (~class98_Sub22.readUnsignedByte((byte)14) == 0xFFFFFFFE);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ow.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public static void method1085(final int n) {
        try {
            Class98_Sub10_Sub27.aClass84_5693 = null;
            if (n < 40) {
                method1085(-125);
            }
            Class98_Sub10_Sub27.aClass84_5692 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ow.B(" + n + ')');
        }
    }
    
    static final int method1086(final byte b) {
        try {
            if (b != -4) {
                return 72;
            }
            return Class133.anInt3452;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ow.E(" + b + ')');
        }
    }
    
    private final void method1087(final int n, final int n2, final int n3) {
        try {
            final float n4 = (float)Math.atan2(Class64_Sub1.anIntArray3640[n3] + n, Class352.anIntArray3001[n2] - 2048);
            if (n4 >= -3.141592653589793 && n4 <= -2.356194490192345) {
                Class134.anInt3464 = n2;
                Class98_Sub10_Sub23.anInt5661 = n3;
            }
            else if (n4 > -1.5707963267948966 || n4 < -2.356194490192345) {
                if (n4 <= -0.7853981633974483 && n4 >= -1.5707963267948966) {
                    Class134.anInt3464 = n3;
                    Class98_Sub10_Sub23.anInt5661 = -n2 + Class25.anInt268;
                }
                else if (n4 > 0.0f || n4 < -0.7853981633974483) {
                    if (n4 < 0.0f || n4 > 0.7853981633974483) {
                        if (n4 < 0.7853981633974483 || n4 > 1.5707963267948966) {
                            if (n4 < 1.5707963267948966 || n4 > 2.356194490192345) {
                                if (n4 >= 2.356194490192345 && n4 <= 3.141592653589793) {
                                    Class98_Sub10_Sub23.anInt5661 = -n3 + Class25.anInt268;
                                    Class134.anInt3464 = n2;
                                }
                            }
                            else {
                                Class134.anInt3464 = Class63.anInt492 - n3;
                                Class98_Sub10_Sub23.anInt5661 = n2;
                            }
                        }
                        else {
                            Class98_Sub10_Sub23.anInt5661 = -n2 + Class25.anInt268;
                            Class134.anInt3464 = Class63.anInt492 + -n3;
                        }
                    }
                    else {
                        Class98_Sub10_Sub23.anInt5661 = Class25.anInt268 + -n3;
                        Class134.anInt3464 = -n2 + Class63.anInt492;
                    }
                }
                else {
                    Class98_Sub10_Sub23.anInt5661 = n3;
                    Class134.anInt3464 = -n2 + Class63.anInt492;
                }
            }
            else {
                Class134.anInt3464 = n3;
                Class98_Sub10_Sub23.anInt5661 = n2;
            }
            Class134.anInt3464 &= za_Sub1.anInt6075;
            Class98_Sub10_Sub23.anInt5661 &= Class329.anInt2761;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ow.D(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    public Class98_Sub10_Sub27() {
        super(1, false);
    }
    
    static {
        Class98_Sub10_Sub27.aClass84_5692 = (Class98_Sub10_Sub27.aClass84_5693 = new Class84(false));
    }
}
