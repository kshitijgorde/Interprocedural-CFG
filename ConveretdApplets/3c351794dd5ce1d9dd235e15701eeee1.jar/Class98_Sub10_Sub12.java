// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub12 extends Class98_Sub10
{
    static int anInt5598;
    static boolean aBoolean5599;
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (super.aClass223_3859.aBoolean1683) {
                final int[] method2829 = this.method1000(n2, 2, 0);
                final int[][] method2830 = this.method994(n2, 24431, 0);
                final int[][] method2831 = this.method994(n2, 24431, 1);
                final int[] array = method2828[0];
                final int[] array2 = method2828[1];
                final int[] array3 = method2828[2];
                final int[] array4 = method2830[0];
                final int[] array5 = method2830[1];
                final int[] array6 = method2830[2];
                final int[] array7 = method2831[0];
                final int[] array8 = method2831[1];
                final int[] array9 = method2831[2];
                for (int n3 = 0; ~Class25.anInt268 < ~n3; ++n3) {
                    final int n4 = method2829[n3];
                    if (n4 != 4096) {
                        if (n4 == 0) {
                            array[n3] = array7[n3];
                            array2[n3] = array8[n3];
                            array3[n3] = array9[n3];
                        }
                        else {
                            final int n5 = -n4 + 4096;
                            array[n3] = n4 * array4[n3] - -(array7[n3] * n5) >> 327350924;
                            array2[n3] = array5[n3] * n4 + n5 * array8[n3] >> 428197644;
                            array3[n3] = array9[n3] * n5 + array6[n3] * n4 >> 1391664972;
                        }
                    }
                    else {
                        array[n3] = array4[n3];
                        array2[n3] = array5[n3];
                        array3[n3] = array6[n3];
                    }
                }
            }
            if (n > -76) {
                Class98_Sub10_Sub12.aBoolean5599 = false;
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gr.C(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            if (n != 255) {
                this.method991(92, null, (byte)(-19));
            }
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                final int[] method238 = this.method1000(n2, 0, 0);
                final int[] method239 = this.method1000(n2, 1, 0);
                final int[] method240 = this.method1000(n2, 2, 0);
                for (int n3 = 0; Class25.anInt268 > n3; ++n3) {
                    final int n4 = method240[n3];
                    if (n4 != 4096) {
                        if (n4 != 0) {
                            method237[n3] = method239[n3] * (4096 - n4) + n4 * method238[n3] >> 1486833164;
                        }
                        else {
                            method237[n3] = method239[n3];
                        }
                    }
                    else {
                        method237[n3] = method238[n3];
                    }
                }
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gr.G(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b >= -92) {
                Class98_Sub10_Sub12.aBoolean5599 = false;
            }
            if (~n == -1) {
                super.aBoolean3861 = (~class98_Sub22.readUnsignedByte((byte)(-115)) == 0xFFFFFFFE);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gr.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public Class98_Sub10_Sub12() {
        super(3, false);
    }
    
    static {
        Class98_Sub10_Sub12.aBoolean5599 = true;
    }
}
