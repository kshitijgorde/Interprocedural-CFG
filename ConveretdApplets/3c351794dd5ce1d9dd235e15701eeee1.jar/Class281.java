import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class281
{
    byte aByte2113;
    short aShort2114;
    int anInt2115;
    int anInt2116;
    static byte[][][] aByteArrayArrayArray2117;
    boolean aBoolean2118;
    short aShort2119;
    int anInt2120;
    int anInt2121;
    int anInt2122;
    short aShort2123;
    
    static final void method3331(final boolean visible, final Frame frame, final Class88 class88) {
        try {
            while (true) {
                final Class143 method862 = class88.method862(-3470, frame);
                while (~method862.anInt1163 == -1) {
                    Class246_Sub7.method3131(0, 10L);
                }
                if (method862.anInt1163 == 1) {
                    break;
                }
                Class246_Sub7.method3131(0, 100L);
            }
            frame.setVisible(visible);
            frame.dispose();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ri.C(" + visible + ',' + ((frame != null) ? "{...}" : "null") + ',' + ((class88 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method3332(final int n) {
        try {
            if (n != 22011) {
                Class281.aByteArrayArrayArray2117 = null;
            }
            Class281.aByteArrayArrayArray2117 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ri.B(" + n + ')');
        }
    }
    
    Class281(final int anInt2121, final int anInt2122, final int anInt2123, final int anInt2124, final int n, final int n2, final int n3, final int n4, final int n5, final boolean b, final boolean aBoolean2118, final int anInt2125) {
        try {
            this.aShort2119 = (short)n2;
            this.anInt2115 = anInt2124;
            this.anInt2122 = anInt2122;
            this.aByte2113 = (byte)n5;
            this.aShort2114 = (short)n3;
            this.anInt2121 = anInt2121;
            this.aBoolean2118 = aBoolean2118;
            this.aShort2123 = (short)n;
            this.anInt2120 = anInt2123;
            this.anInt2116 = anInt2125;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ri.<init>(" + anInt2121 + ',' + anInt2122 + ',' + anInt2123 + ',' + anInt2124 + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + b + ',' + aBoolean2118 + ',' + anInt2125 + ')');
        }
    }
    
    static final Class246_Sub3_Sub2 method3333(final int n, final int n2, final int n3) {
        final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2][n3];
        if (class172 == null) {
            return null;
        }
        final Class246_Sub3_Sub2 aClass246_Sub3_Sub2_1331 = class172.aClass246_Sub3_Sub2_1331;
        class172.aClass246_Sub3_Sub2_1331 = null;
        Class129.method2227(aClass246_Sub3_Sub2_1331);
        return aClass246_Sub3_Sub2_1331;
    }
}
