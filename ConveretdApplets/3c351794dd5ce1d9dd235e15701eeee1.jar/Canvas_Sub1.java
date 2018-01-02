import java.awt.Graphics;
import java.awt.Component;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class Canvas_Sub1 extends Canvas
{
    static int anInt23;
    private Component aComponent24;
    static float aFloat25;
    static int anInt26;
    
    Canvas_Sub1(final Component aComponent24) {
        try {
            this.aComponent24 = aComponent24;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bn.<init>(" + ((aComponent24 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method118(final byte b) {
        try {
            if (~Class177.anInt1376 == 0xFFFFFFF8) {
                Class98_Sub10_Sub1.method1003(false, false);
            }
            else {
                Class318.aClass123_2698 = aa_Sub1.aClass123_3561;
                aa_Sub1.aClass123_3561 = null;
                if (b == 104) {
                    Class61.method538(13, false);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bn.A(" + b + ')');
        }
    }
    
    static final void method119(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        try {
            if (n6 >= ~n7 && n2 >= 1 && ~n7 >= ~(Class165.anInt1276 - 2) && -2 + Class98_Sub10_Sub7.anInt5572 >= n2) {
                int n10 = n5;
                if (~n10 > -4 && Class1.method162(n2, (byte)(-88), n7)) {
                    ++n10;
                }
                if ((Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076.method564((byte)122) != 0 || Class294.method3477(n2, n10, n7, Class115.anInt963, 55)) && Class98_Sub46_Sub1.aClass172ArrayArrayArray5948 != null) {
                    Class146_Sub3.aClass305_Sub1_4952.method3590(n2, n5, Class265.aHa1974, Class167.aClass243Array1281[n5], 1, n3, n7);
                    if (n >= 0) {
                        final int method596 = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub11_4038.method596((byte)122);
                        Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub11_4038);
                        Class146_Sub3.aClass305_Sub1_4952.method3588(n, n2, n8, n10, false, n7, Class265.aHa1974, n4, n9, n5, Class167.aClass243Array1281[n5]);
                        Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), method596, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub11_4038);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bn.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    @Override
    public final void paint(final Graphics graphics) {
        try {
            this.aComponent24.paint(graphics);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bn.paint(" + ((graphics != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final aa_Sub2 method120(final int i, final ha_Sub3 ha_Sub3, final int n, final int[] array, final int n2, final int[] array2) {
        try {
            if (n2 <= 70) {
                method119(108, -86, 41, 17, 85, 52, -105, 11, 108);
            }
            if (!ha_Sub3.method1942(0, Class53_Sub1.aClass164_3633, Class162.aClass162_1266)) {
                final int[] array3 = new int[n * i];
                for (int n3 = 0; i > n3; ++n3) {
                    int n4 = array[n3] + n3 * n;
                    for (int n5 = 0; ~array2[n3] < ~n5; ++n5) {
                        array3[n4++] = -16777216;
                    }
                }
                return new aa_Sub2(ha_Sub3, n, i, array3);
            }
            final byte[] array4 = new byte[n * i];
            for (int n6 = 0; i > n6; ++n6) {
                int n7 = n * n6 + array[n6];
                for (int n8 = 0; array2[n6] > n8; ++n8) {
                    array4[n7++] = -1;
                }
            }
            return new aa_Sub2(ha_Sub3, n, i, array4);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bn.B(" + i + ',' + ((ha_Sub3 != null) ? "{...}" : "null") + ',' + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void update(final Graphics graphics) {
        try {
            this.aComponent24.update(graphics);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bn.update(" + ((graphics != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Canvas_Sub1.anInt26 = -1;
    }
}
