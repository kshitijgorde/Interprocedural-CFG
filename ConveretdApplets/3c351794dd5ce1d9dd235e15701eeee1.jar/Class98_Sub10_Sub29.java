// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub29 extends Class98_Sub10
{
    private int anInt5707;
    private int anInt5708;
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            if (n > -76) {
                this.method991(84, null, (byte)25);
            }
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (super.aClass223_3859.aBoolean1683) {
                final int n3 = Class25.anInt268 / this.anInt5707;
                final int n4 = Class63.anInt492 / this.anInt5708;
                int[][] array;
                if (n4 <= 0) {
                    array = this.method994(0, 24431, 0);
                }
                else {
                    array = this.method994(Class63.anInt492 * (n2 % n4) / n4, 24431, 0);
                }
                final int[] array2 = array[0];
                final int[] array3 = array[1];
                final int[] array4 = array[2];
                final int[] array5 = method2828[0];
                final int[] array6 = method2828[1];
                final int[] array7 = method2828[2];
                for (int n5 = 0; Class25.anInt268 > n5; ++n5) {
                    int n6;
                    if (n3 > 0) {
                        n6 = n5 % n3 * Class25.anInt268 / n3;
                    }
                    else {
                        n6 = 0;
                    }
                    array5[n5] = array2[n6];
                    array6[n5] = array3[n6];
                    array7[n5] = array4[n6];
                }
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qaa.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method1090(final int n, final aa aa, final int n2, final int n3, final Class293 class293, final int n4, final long n5, final int n6, final int n7) {
        try {
            final int n8 = n2 * n2 + n3 * n3;
            if (n8 <= n5) {
                if (n7 != 4) {
                    method1092(-62, -95, (byte)36);
                }
                int min = Math.min(class293.anInt2311 / 2, class293.anInt2258 / 2);
                if (~n8 >= ~(min * min)) {
                    Class4.method173(n4, n2, n6, class293, aa, n3, (byte)(-24), Class98_Sub46_Sub11.aClass332Array6032[n]);
                }
                else {
                    min -= 10;
                    int n9 = 0;
                    Label_0132: {
                        if (Class98_Sub46_Sub20_Sub2.anInt6319 == 4) {
                            n9 = (0x3FFF & (int)Class98_Sub22_Sub2.aFloat5794);
                            if (!client.aBoolean3553) {
                                break Label_0132;
                            }
                        }
                        n9 = (0x3FFF & Class204.anInt1553 + (int)Class98_Sub22_Sub2.aFloat5794);
                    }
                    int n10 = Class284_Sub2_Sub2.anIntArray6200[n9];
                    int n11 = Class284_Sub2_Sub2.anIntArray6202[n9];
                    if (Class98_Sub46_Sub20_Sub2.anInt6319 != 4) {
                        n10 = n10 * 256 / (Class151.anInt1213 + 256);
                        n11 = n11 * 256 / (256 + Class151.anInt1213);
                    }
                    final double atan2 = Math.atan2(n10 * n2 - -(n3 * n11) >> -1569017266, -(n3 * n10) + n11 * n2 >> 813721934);
                    Class352.aClass332Array3000[n].method3730(n6 + class293.anInt2311 / 2.0f + (int)(Math.sin(atan2) * min), -(int)(Math.cos(atan2) * min) + (n4 + class293.anInt2258 / 2.0f), 4096, (int)(-atan2 / 6.283185307179586 * 65535.0));
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qaa.B(" + n + ',' + ((aa != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + ((class293 != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    public Class98_Sub10_Sub29() {
        super(1, false);
        this.anInt5707 = 4;
        this.anInt5708 = 4;
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (n != 0) {
                if (~n == 0xFFFFFFFE) {
                    this.anInt5708 = class98_Sub22.readUnsignedByte((byte)37);
                }
            }
            else {
                this.anInt5707 = class98_Sub22.readUnsignedByte((byte)9);
            }
            if (b > -92) {
                this.anInt5708 = -70;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qaa.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final void sendPacket(final boolean b, final Class98_Sub11 class98_Sub11) {
        try {
            Class336.aClass148_2827.method2419(class98_Sub11, -20911);
            if (b) {
                method1092(-65, -93, (byte)41);
            }
            class98_Sub11.anInt3869 = class98_Sub11.aClass98_Sub22_Sub1_3865.anInt3991;
            Class62.anInt490 += class98_Sub11.anInt3869;
            class98_Sub11.aClass98_Sub22_Sub1_3865.anInt3991 = 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qaa.E(" + b + ',' + ((class98_Sub11 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                final int n3 = Class25.anInt268 / this.anInt5707;
                final int n4 = Class63.anInt492 / this.anInt5708;
                int[] array;
                if (n4 > 0) {
                    array = this.method1000(n2 % n4 * Class63.anInt492 / n4, 0, 0);
                }
                else {
                    array = this.method1000(0, 0, n - 255);
                }
                for (int n5 = 0; Class25.anInt268 > n5; ++n5) {
                    if (n3 > 0) {
                        method237[n5] = array[Class25.anInt268 * (n5 % n3) / n3];
                    }
                    else {
                        method237[n5] = array[0];
                    }
                }
            }
            if (n != 255) {
                this.anInt5708 = 9;
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qaa.G(" + n + ',' + n2 + ')');
        }
    }
    
    static final boolean method1092(final int n, final int n2, final byte b) {
        try {
            return b >= -64 || ~(n & 0x20) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qaa.D(" + n + ',' + n2 + ',' + b + ')');
        }
    }
}
