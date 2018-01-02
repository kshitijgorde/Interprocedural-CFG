// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub5_Sub1 extends Class98_Sub10_Sub5
{
    static int anInt6292;
    
    static final void method1018(float n, final boolean b, final int n2, final float n3, final int n4, float n5, final Class39 class39, final int i, float n6, final int n7, final byte[] array, float n8, final int n9, final int n10) {
        try {
            final int n11 = n9 * n7;
            final float[] array2 = new float[n11];
            for (int n12 = 0; i > n12; ++n12) {
                int n13 = n2;
                class39.method352(n4, n7, n5 / n4, n10, n6 * 127.0f, 0, n / n9, n8 / n7, array2, n9, 1);
                n8 *= 2.0f;
                n5 *= 2.0f;
                n6 *= n3;
                n *= 2.0f;
                for (int j = 0; j < n11; ++j) {
                    final int n14 = n13;
                    array[n14] += (byte)array2[j];
                    ++n13;
                }
            }
            int n15 = n2;
            for (int n16 = 0; ~n16 > ~n11; ++n16) {
                array[n15] += 127;
                ++n15;
            }
            if (b) {
                method1018(-1.6836507f, true, 101, -0.5326127f, 104, -0.6777266f, null, -49, -1.3125609f, 51, null, 0.05877325f, -34, -101);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dc.B(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + ((class39 != null) ? "{...}" : "null") + ',' + i + ',' + n6 + ',' + n7 + ',' + ((array != null) ? "{...}" : "null") + ',' + n8 + ',' + n9 + ',' + n10 + ')');
        }
    }
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (super.aClass223_3859.aBoolean1683 && this.method1016(-1)) {
                final int[] array = method2828[0];
                final int[] array2 = method2828[1];
                final int[] array3 = method2828[2];
                final int n3 = super.anInt5555 * (n2 % super.anInt5555);
                for (int n4 = 0; ~n4 > ~Class25.anInt268; ++n4) {
                    final int n5 = super.anIntArray5552[n4 % super.anInt5556 + n3];
                    array3[n4] = Class202.method2702(n5 << 1432308676, 4080);
                    array2[n4] = Class202.method2702(4080, n5 >> -1121818652);
                    array[n4] = Class202.method2702(n5, 16711680) >> 1364421580;
                }
            }
            if (n >= -76) {
                this.method997(54, -2);
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dc.C(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub5_Sub1.anInt6292 = -1;
    }
}
