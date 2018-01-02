import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class221
{
    static int anInt1664;
    static int[] anIntArray1665;
    static Class332 aClass332_1666;
    
    static final void method2821(final File file, final String s, final int n) {
        try {
            Class124.aHashtable1015.put(s, file);
            if (n != -320) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oda.C(" + ((file != null) ? "{...}" : "null") + ',' + ((s != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public static void method2822(final int n) {
        try {
            Class221.anIntArray1665 = null;
            Class221.aClass332_1666 = null;
            if (n > -110) {
                method2822(-121);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oda.A(" + n + ')');
        }
    }
    
    static final void method2823(final byte b, final int[] array, final int[] array2, final int n, final int i) {
        try {
            if (b <= -100) {
                if (i > n) {
                    final int n2 = (i + n) / 2;
                    int n3 = n;
                    final int n4 = array2[n2];
                    array2[n2] = array2[i];
                    array2[i] = n4;
                    final int n5 = array[n2];
                    array[n2] = array[i];
                    array[i] = n5;
                    final boolean b2 = ~n4 != Integer.MIN_VALUE;
                    for (int n6 = n; i > n6; ++n6) {
                        if (~array2[n6] > ~(((b2 ? 1 : 0) & n6) + n4)) {
                            final int n7 = array2[n6];
                            array2[n6] = array2[n3];
                            array2[n3] = n7;
                            final int n8 = array[n6];
                            array[n6] = array[n3];
                            array[n3++] = n8;
                        }
                    }
                    array2[i] = array2[n3];
                    array2[n3] = n4;
                    array[i] = array[n3];
                    array[n3] = n5;
                    method2823((byte)(-109), array, array2, n, n3 - 1);
                    method2823((byte)(-125), array, array2, 1 + n3, i);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oda.B(" + b + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n + ',' + i + ')');
        }
    }
    
    static {
        Class221.anInt1664 = 0;
    }
}
