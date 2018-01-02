import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class278_Sub1 extends Class278
{
    static int[] anIntArray5168;
    static int[][] anIntArrayArray5169;
    static int anInt5170;
    static boolean[][] aBooleanArrayArray5171;
    
    public static void method3319(final int n) {
        try {
            Class278_Sub1.anIntArrayArray5169 = null;
            Class278_Sub1.aBooleanArrayArray5171 = null;
            if (n == 1204) {
                Class278_Sub1.anIntArray5168 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "paa.A(" + n + ')');
        }
    }
    
    static final int method3320(final int n) {
        try {
            ha ha = Class265.aHa1974;
            boolean b = false;
            if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)123) != -1) {
                final Canvas canvas = new Canvas();
                canvas.setSize(100, 100);
                b = true;
                ha = Class76_Sub11.method771(0, canvas, 127, null, 0, null);
            }
            final long method3819 = Class343.method3819(-47);
            for (int i = 0; i < 10000; ++i) {
                ha.method1751(5, 10, 100, 75, 50, 100, 15, 90, 100, -65536, -65536, -65536, 1);
            }
            final int n2 = (int)(Class343.method3819(-47) - method3819);
            ha.method1760(100, 100, 0, -16777216, (byte)(-66), 0);
            if (b) {
                ha.method1743(-1);
            }
            if (n != 12) {
                method3319(-59);
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "paa.B(" + n + ')');
        }
    }
    
    static {
        Class278_Sub1.anIntArrayArray5169 = new int[][] { { 12, 12, 12, 12 }, { 12, 12, 12, 12 }, { 5, 5, 5 }, { 5, 5, 5 }, { 5, 5, 5 }, { 5, 5, 5 }, { 12, 12, 12, 12, 12, 12 }, { 1, 1, 1, 7 }, { 1, 1, 7, 1 }, { 8, 9, 9, 8, 8, 9 }, { 8, 8, 9, 8, 9, 9 }, { 10, 10, 11, 11, 11, 10 }, { 12, 12, 12, 12 } };
        Class278_Sub1.aBooleanArrayArray5171 = new boolean[][] { new boolean[13], { false, false, true, true, true, true, true, false, false, false, false, false, true }, { true, true, true, true, true, true, false, false, false, false, false, false, false }, { true, true, true, false, false, true, true, true, false, false, false, false, false }, { true, false, false, false, false, true, true, true, false, false, false, false, false }, { false, false, true, true, true, true, false, false, false, false, false, false, false }, { false, true, true, true, true, true, false, false, false, false, false, false, true }, { false, true, true, true, true, true, true, true, false, false, false, false, true }, { true, true, false, false, false, false, false, true, false, false, false, false, false }, { true, true, true, true, true, false, false, false, true, true, false, false, false }, { true, false, false, false, true, true, true, true, true, true, false, false, false }, { true, false, true, true, true, true, true, true, false, false, true, true, false }, { true, true, true, true, true, true, true, true, true, true, true, true, true }, new boolean[13], { true, true, true, true, true, true, true, true, true, true, true, true, true } };
        Class278_Sub1.anInt5170 = 0;
    }
}
