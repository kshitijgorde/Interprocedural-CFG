// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub11 extends Class98_Sub10
{
    private boolean aBoolean5594;
    private boolean aBoolean5595;
    static Class148 aClass148_5596;
    static short[][] aShortArrayArray5597;
    
    public static void method1040(final int n) {
        try {
            Class98_Sub10_Sub11.aShortArrayArray5597 = null;
            Class98_Sub10_Sub11.aClass148_5596 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fia.B(" + n + ')');
        }
    }
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (n > -76) {
                return null;
            }
            if (super.aClass223_3859.aBoolean1683) {
                final int[][] method2829 = this.method994(this.aBoolean5595 ? (za_Sub1.anInt6075 - n2) : n2, 24431, 0);
                final int[] array = method2829[0];
                final int[] array2 = method2829[1];
                final int[] array3 = method2829[2];
                final int[] array4 = method2828[0];
                final int[] array5 = method2828[1];
                final int[] array6 = method2828[2];
                if (!this.aBoolean5594) {
                    for (int i = 0; i < Class25.anInt268; ++i) {
                        array4[i] = array[i];
                        array5[i] = array2[i];
                        array6[i] = array3[i];
                    }
                }
                else {
                    for (int n3 = 0; ~Class25.anInt268 < ~n3; ++n3) {
                        array4[n3] = array[Class329.anInt2761 - n3];
                        array5[n3] = array2[-n3 + Class329.anInt2761];
                        array6[n3] = array3[-n3 + Class329.anInt2761];
                    }
                }
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fia.C(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (n != 0) {
                if (~n != 0xFFFFFFFE) {
                    if (~n == 0xFFFFFFFD) {
                        super.aBoolean3861 = (class98_Sub22.readUnsignedByte((byte)(-107)) == 1);
                    }
                }
                else {
                    this.aBoolean5595 = (class98_Sub22.readUnsignedByte((byte)(-116)) == 1);
                }
            }
            else {
                this.aBoolean5594 = (class98_Sub22.readUnsignedByte((byte)43) == 1);
            }
            if (b > -92) {
                this.method991(7, null, (byte)(-105));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fia.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public Class98_Sub10_Sub11() {
        super(1, false);
        this.aBoolean5594 = true;
        this.aBoolean5595 = true;
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            if (n != 255) {
                this.aBoolean5594 = true;
            }
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                final int[] method238 = this.method1000(this.aBoolean5595 ? (za_Sub1.anInt6075 - n2) : n2, 0, n ^ 0xFF);
                if (this.aBoolean5594) {
                    for (int n3 = 0; ~n3 > ~Class25.anInt268; ++n3) {
                        method237[n3] = method238[-n3 + Class329.anInt2761];
                    }
                }
                else {
                    Class236.method2891(method238, 0, method237, 0, Class25.anInt268);
                }
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fia.G(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub11.aClass148_5596 = new Class148();
    }
}
