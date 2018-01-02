import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Toolkit;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class271
{
    static Class326 aClass326_2033;
    static long[] aLongArray2034;
    static int anInt2035;
    
    public static void method3276(final byte b) {
        try {
            Class271.aLongArray2034 = null;
            Class271.aClass326_2033 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qt.B(" + b + ')');
        }
    }
    
    static final Class332 method3277(final byte[] array, final int n) {
        try {
            if (n != 1) {
                Class271.aLongArray2034 = null;
            }
            if (array == null) {
                throw new RuntimeException("");
            }
            try {
                final Image image = Toolkit.getDefaultToolkit().createImage(array);
                final MediaTracker mediaTracker = new MediaTracker(Class315.aClient3529);
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForAll();
                final int width = image.getWidth(Class315.aClient3529);
                final int height = image.getHeight(Class315.aClient3529);
                if (mediaTracker.isErrorAny() || ~width > -1 || height < 0) {
                    throw new RuntimeException("");
                }
                final int[] array2 = new int[height * width];
                new PixelGrabber(image, 0, 0, width, height, array2, 0, width).grabPixels();
                return Class265.aHa1974.method1748(n ^ 0xFFFFE0E7, 0, width, height, array2, width);
            }
            catch (InterruptedException ex2) {}
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qt.C(" + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final void method3278(final int n, final int n2, final ha ha, final Class155 class155) {
        try {
            if (Class132.anIntArray1044 != null && ~n2 >= ~class155.aByte1238) {
                for (int n3 = 0; Class132.anIntArray1044.length > n3; ++n3) {
                    if (~Class132.anIntArray1044[n3] != 0xF423F && (~Class132.anIntArray1044[n3] <= ~class155.anIntArray1237[0] || ~Class132.anIntArray1044[n3] <= ~class155.anIntArray1237[1] || ~class155.anIntArray1237[2] >= ~Class132.anIntArray1044[n3] || ~Class132.anIntArray1044[n3] <= ~class155.anIntArray1237[3]) && (~Class98_Sub46_Sub10.anIntArray6015[n3] <= ~class155.anIntArray1240[0] || Class98_Sub46_Sub10.anIntArray6015[n3] >= class155.anIntArray1240[1] || ~Class98_Sub46_Sub10.anIntArray6015[n3] <= ~class155.anIntArray1240[2] || Class98_Sub46_Sub10.anIntArray6015[n3] >= class155.anIntArray1240[3]) && (~Class288.anIntArray3376[n3] >= ~class155.anIntArray1240[0] || ~Class288.anIntArray3376[n3] >= ~class155.anIntArray1240[1] || ~Class288.anIntArray3376[n3] >= ~class155.anIntArray1240[2] || ~class155.anIntArray1240[3] <= ~Class288.anIntArray3376[n3]) && (Class98_Sub10_Sub10.anIntArray5589[n3] >= class155.anIntArray1241[0] || ~class155.anIntArray1241[1] >= ~Class98_Sub10_Sub10.anIntArray5589[n3] || ~class155.anIntArray1241[2] >= ~Class98_Sub10_Sub10.anIntArray5589[n3] || ~class155.anIntArray1241[3] >= ~Class98_Sub10_Sub10.anIntArray5589[n3]) && (class155.anIntArray1241[0] >= Class364.anIntArray3102[n3] || ~class155.anIntArray1241[1] <= ~Class364.anIntArray3102[n3] || Class364.anIntArray3102[n3] <= class155.anIntArray1241[2] || ~Class364.anIntArray3102[n3] >= ~class155.anIntArray1241[3])) {
                        return;
                    }
                }
            }
            if (~class155.aByte1242 == 0xFFFFFFFE) {
                final int n4 = -Class241.anInt1845 + (class155.aShort1236 - -Class259.anInt1959);
                if (~n4 <= -1 && ~n4 >= ~(Class259.anInt1959 - -Class259.anInt1959)) {
                    int n5 = -Class64_Sub26.anInt3714 + (class155.aShort1239 + Class259.anInt1959);
                    if (n5 >= 0) {
                        if (~(Class259.anInt1959 + Class259.anInt1959) > ~n5) {
                            return;
                        }
                    }
                    else {
                        n5 = 0;
                    }
                    int n6 = -Class64_Sub26.anInt3714 + class155.aShort1245 + Class259.anInt1959;
                    if (n6 > Class259.anInt1959 + Class259.anInt1959) {
                        n6 = Class259.anInt1959 + Class259.anInt1959;
                    }
                    else if (n6 < 0) {
                        return;
                    }
                    boolean b = false;
                    while (~n5 >= ~n6) {
                        if (Class74.aBooleanArrayArray551[n4][n5++]) {
                            b = true;
                            break;
                        }
                    }
                    if (b) {
                        float n7 = Class127.anInt1018 - class155.anIntArray1240[0];
                        if (n7 < 0.0f) {
                            n7 *= -1.0f;
                        }
                        if (Class370.anInt3139 <= n7 && Class134.method2242(0, n + 5538, class155) && Class134.method2242(1, 5541, class155) && Class134.method2242(2, 5541, class155) && Class134.method2242(3, 5541, class155)) {
                            Class213.aClass155Array1611[Class59.anInt464++] = class155;
                        }
                    }
                }
            }
            else {
                if (n != 3) {
                    method3276((byte)(-55));
                }
                if (~class155.aByte1242 == 0xFFFFFFFD) {
                    final int n8 = -Class64_Sub26.anInt3714 + (class155.aShort1239 - -Class259.anInt1959);
                    if (~n8 <= -1 && Class259.anInt1959 + Class259.anInt1959 >= n8) {
                        int n9 = -Class241.anInt1845 + (class155.aShort1236 + Class259.anInt1959);
                        if (~n9 > -1) {
                            n9 = 0;
                        }
                        else if (n9 > Class259.anInt1959 + Class259.anInt1959) {
                            return;
                        }
                        int i = Class259.anInt1959 + -Class241.anInt1845 + class155.aShort1243;
                        if (~i >= ~(Class259.anInt1959 + Class259.anInt1959)) {
                            if (i < 0) {
                                return;
                            }
                        }
                        else {
                            i = Class259.anInt1959 + Class259.anInt1959;
                        }
                        boolean b2 = false;
                        while (i >= n9) {
                            if (Class74.aBooleanArrayArray551[n9++][n8]) {
                                b2 = true;
                                break;
                            }
                        }
                        if (b2) {
                            float n10 = -class155.anIntArray1241[0] + Class98_Sub48.anInt4280;
                            if (n10 < 0.0f) {
                                n10 *= -1.0f;
                            }
                            if (Class370.anInt3139 <= n10 && Class134.method2242(0, 5541, class155) && Class134.method2242(1, 5541, class155) && Class134.method2242(2, 5541, class155) && Class134.method2242(3, 5541, class155)) {
                                Class213.aClass155Array1611[Class59.anInt464++] = class155;
                            }
                        }
                    }
                }
                else if (class155.aByte1242 == 16 || class155.aByte1242 == 8) {
                    final int n11 = Class259.anInt1959 + -Class241.anInt1845 + class155.aShort1236;
                    if (~n11 <= -1 && ~(Class259.anInt1959 - -Class259.anInt1959) <= ~n11) {
                        final int n12 = -Class64_Sub26.anInt3714 + class155.aShort1239 - -Class259.anInt1959;
                        if (~n12 <= -1 && ~n12 >= ~(Class259.anInt1959 + Class259.anInt1959) && Class74.aBooleanArrayArray551[n11][n12]) {
                            float n13 = -class155.anIntArray1240[0] + Class127.anInt1018;
                            if (n13 < 0.0f) {
                                n13 *= -1.0f;
                            }
                            float n14 = -class155.anIntArray1241[0] + Class98_Sub48.anInt4280;
                            if (n14 < 0.0f) {
                                n14 *= -1.0f;
                            }
                            if ((Class370.anInt3139 <= n13 || Class370.anInt3139 <= n14) && Class134.method2242(0, n ^ 0x15A6, class155) && Class134.method2242(1, 5541, class155) && Class134.method2242(2, 5541, class155) && Class134.method2242(3, 5541, class155)) {
                                Class213.aClass155Array1611[Class59.anInt464++] = class155;
                            }
                        }
                    }
                }
                else if (class155.aByte1242 == 4 && -Class42_Sub1_Sub1.anInt6208 + class155.anIntArray1237[0] > Class119_Sub1.anInt4716) {
                    int n15 = Class259.anInt1959 + class155.aShort1239 - Class64_Sub26.anInt3714;
                    if (~n15 <= -1) {
                        if (Class259.anInt1959 + Class259.anInt1959 < n15) {
                            return;
                        }
                    }
                    else {
                        n15 = 0;
                    }
                    int n16 = -Class64_Sub26.anInt3714 + class155.aShort1245 - -Class259.anInt1959;
                    if (n16 > Class259.anInt1959 + Class259.anInt1959) {
                        n16 = Class259.anInt1959 + Class259.anInt1959;
                    }
                    else if (~n16 > -1) {
                        return;
                    }
                    int n17 = Class259.anInt1959 + -Class241.anInt1845 + class155.aShort1236;
                    if (~n17 <= -1) {
                        if (~n17 < ~(Class259.anInt1959 - -Class259.anInt1959)) {
                            return;
                        }
                    }
                    else {
                        n17 = 0;
                    }
                    int n18 = Class259.anInt1959 + class155.aShort1243 - Class241.anInt1845;
                    if (n18 > Class259.anInt1959 - -Class259.anInt1959) {
                        n18 = Class259.anInt1959 + Class259.anInt1959;
                    }
                    else if (~n18 > -1) {
                        return;
                    }
                    boolean b3 = false;
                Label_1490:
                    for (int n19 = n17; ~n18 <= ~n19; ++n19) {
                        for (int n20 = n15; ~n20 >= ~n16; ++n20) {
                            if (Class74.aBooleanArrayArray551[n19][n20]) {
                                b3 = true;
                                break Label_1490;
                            }
                        }
                    }
                    if (b3 && Class134.method2242(0, 5541, class155) && Class134.method2242(1, n + 5538, class155) && Class134.method2242(2, n ^ 0x15A6, class155) && Class134.method2242(3, 5541, class155)) {
                        Class213.aClass155Array1611[Class59.anInt464++] = class155;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qt.A(" + n + ',' + n2 + ',' + ((ha != null) ? "{...}" : "null") + ',' + ((class155 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class271.aLongArray2034 = new long[32];
    }
}
