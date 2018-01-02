import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class2
{
    static Class6 aClass6_68;
    static int anInt69;
    static int[] anIntArray70;
    static int anInt71;
    static Class332[] aClass332Array72;
    static Class299 aClass299_73;
    
    static final void method168(int n, final int n2, final int n3, final int n4, final float[] array, int n5, final int n6) {
        try {
            if (~n < -1 && !Class81.method815(n, 0)) {
                throw new IllegalArgumentException("");
            }
            if (n5 > 0 && !Class81.method815(n5, 0)) {
                throw new IllegalArgumentException("");
            }
            final int i = Class246_Sub3_Sub3.method3014(1, n3);
            int n7 = 0;
            int n8 = (~n <= ~n5) ? n5 : n;
            int n9 = n >> -928318015;
            int n10 = n5 >> -786638463;
            float[] array2 = array;
            float[] array3 = new float[n10 * (n9 * i)];
            while (true) {
                OpenGL.glTexImage2Df(n2, n7, n4, n, n5, 0, n3, 5126, array2, 0);
                if (~n8 >= -2) {
                    break;
                }
                final int n11 = n * i;
                for (int n12 = 0; i > n12; ++n12) {
                    int n13 = n12;
                    int n14 = n12;
                    int n15 = n11 + n14;
                    for (int j = 0; j < n10; ++j) {
                        for (int n16 = 0; ~n16 > ~n9; ++n16) {
                            final float n17 = array2[n14];
                            final int n18 = n14 + i;
                            final float n19 = n17 + array2[n18] + array2[n15];
                            n14 = n18 + i;
                            final int n20 = n15 + i;
                            final float n21 = n19 + array2[n20];
                            n15 = n20 + i;
                            array3[n13] = 0.25f * n21;
                            n13 += i;
                        }
                        n14 += n11;
                        n15 += n11;
                    }
                }
                final float[] array4 = array3;
                array3 = array2;
                n = n9;
                n5 = n10;
                array2 = array4;
                ++n7;
                n9 >>= 1;
                n8 >>= 1;
                n10 >>= 1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ad.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((array != null) ? "{...}" : "null") + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    static final boolean method169(final boolean b, final int n, final int n2) {
        try {
            return b || (0x400 & n) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ad.C(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    public static void method170(final byte b) {
        try {
            Class2.aClass299_73 = null;
            Class2.aClass332Array72 = null;
            if (b < 5) {
                Class2.aClass332Array72 = null;
            }
            Class2.aClass6_68 = null;
            Class2.anIntArray70 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ad.B(" + b + ')');
        }
    }
    
    static {
        Class2.aClass6_68 = new Class6("LIVE", 0);
        Class2.anInt71 = 0;
        Class2.anIntArray70 = null;
    }
}
