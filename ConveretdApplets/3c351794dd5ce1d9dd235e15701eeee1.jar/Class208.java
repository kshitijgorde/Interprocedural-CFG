// 
// Decompiled by Procyon v0.5.30
// 

final class Class208
{
    static int[] anIntArray1579;
    static int[] anIntArray1580;
    static Class207 aClass207_1581;
    
    static final void method2767() {
        final int n = 10;
        final int n2 = 30;
        if (Class64_Sub21.anInt3702 != 0 && Class284_Sub1.aClass43_5177 != null) {
            Class98_Sub10_Sub30.aHa5709.K(Class98_Sub16.anIntArray3933);
            for (int i = 0; i < s.anIntArray2205.length; ++i) {
                Class98_Sub10_Sub30.aHa5709.method1755(8479, Class98_Sub16.anIntArray3933[1], s.anIntArray2205[i] + Class15.anIntArray182[i], -256, Class98_Sub16.anIntArray3933[3] - Class98_Sub16.anIntArray3933[1]);
            }
            for (int j = 0; j < Class59.anInt464; ++j) {
                final Class155 class155 = Class213.aClass155Array1611[j];
                Class98_Sub10_Sub30.aHa5709.H(class155.anIntArray1240[0], class155.anIntArray1237[0], class155.anIntArray1241[0], Class237.anIntArray1809);
                Class98_Sub10_Sub30.aHa5709.H(class155.anIntArray1240[1], class155.anIntArray1237[1], class155.anIntArray1241[1], Class314.anIntArray2691);
                Class98_Sub10_Sub30.aHa5709.H(class155.anIntArray1240[2], class155.anIntArray1237[2], class155.anIntArray1241[2], Class98_Sub46_Sub6.anIntArray5980);
                Class98_Sub10_Sub30.aHa5709.H(class155.anIntArray1240[3], class155.anIntArray1237[3], class155.anIntArray1241[3], Class262.anIntArray1962);
                if (Class237.anIntArray1809[2] != -1 && Class314.anIntArray2691[2] != -1 && Class98_Sub46_Sub6.anIntArray5980[2] != -1 && Class262.anIntArray1962[2] != -1) {
                    int n3 = -65536;
                    if (class155.aByte1242 == 4) {
                        n3 = -16776961;
                    }
                    Class98_Sub10_Sub30.aHa5709.method1789(Class237.anIntArray1809[1], n3, Class314.anIntArray2691[1], Class314.anIntArray2691[0], -10550, Class237.anIntArray1809[0]);
                    Class98_Sub10_Sub30.aHa5709.method1789(Class314.anIntArray2691[1], n3, Class98_Sub46_Sub6.anIntArray5980[1], Class98_Sub46_Sub6.anIntArray5980[0], -10550, Class314.anIntArray2691[0]);
                    Class98_Sub10_Sub30.aHa5709.method1789(Class98_Sub46_Sub6.anIntArray5980[1], n3, Class262.anIntArray1962[1], Class262.anIntArray1962[0], -10550, Class98_Sub46_Sub6.anIntArray5980[0]);
                    Class98_Sub10_Sub30.aHa5709.method1789(Class262.anIntArray1962[1], n3, Class237.anIntArray1809[1], Class237.anIntArray1809[0], -10550, Class262.anIntArray1962[0]);
                    Class98_Sub10_Sub30.aHa5709.method1789(Class237.anIntArray1809[1], n3, Class98_Sub46_Sub6.anIntArray5980[1], Class98_Sub46_Sub6.anIntArray5980[0], -10550, Class237.anIntArray1809[0]);
                }
            }
            Class284_Sub1.aClass43_5177.method411((byte)(-118), n2 + 45, "Dynamic: " + Class347.anInt2907 + "/" + 5000, -256, -16777216, n);
            Class284_Sub1.aClass43_5177.method411((byte)62, n2 + 60, "Total Opaque Onscreen: " + Class302.anInt2523 + "/" + 10000, -256, -16777216, n);
            Class284_Sub1.aClass43_5177.method411((byte)46, n2 + 75, "Total Trans Onscreen: " + Class353.anInt3009 + "/" + 5000, -256, -16777216, n);
            Class284_Sub1.aClass43_5177.method411((byte)78, n2 + 90, "Occluders: " + (Class21_Sub3.anInt5389 + Class336.anInt2820) + " Active: " + Class59.anInt464, -256, -16777216, n);
            Class284_Sub1.aClass43_5177.method411((byte)84, n2 + 105, "Occluded: Ground:" + Class302.anInt2518 + " Walls: " + Class98_Sub16.anInt3927 + " CPs: " + Class151_Sub7.anInt5006 + " Pixels: " + Class4.anInt81, -256, -16777216, n);
            Class284_Sub1.aClass43_5177.method411((byte)78, n2 + 120, "Occlude Calc Took: " + Class249.aLong1898 / 1000L + "us", -256, -16777216, n);
            if (Class64_Sub21.anInt3702 == 2 && Class111_Sub3.anIntArray4707 != null) {
                for (int k = 0; k < Class111_Sub3.anIntArray4707.length; ++k) {
                    float n4 = Class111_Sub3.anIntArray4707[k] / 4194304.0f;
                    if (n4 > 1.0f) {
                        n4 = 1.0f;
                    }
                    final int n5 = (int)(255.0f - n4 * 255.0f);
                    Class111_Sub3.anIntArray4707[k] = (n5 | n5 << 8 | n5 << 16 | 0xFF000000);
                }
                Class98_Sub10_Sub30.aHa5709.method1748(-7962, 0, Class64_Sub3.anInt3646, IncomingOpcode.anInt461, Class111_Sub3.anIntArray4707, Class64_Sub3.anInt3646).method3748(n, 170, 1, 0, 0);
            }
        }
    }
    
    public static void method2768(final int n) {
        try {
            Class208.anIntArray1579 = null;
            if (n <= 97) {
                method2767();
            }
            Class208.anIntArray1580 = null;
            Class208.aClass207_1581 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nia.B(" + n + ')');
        }
    }
    
    static {
        Class208.anIntArray1580 = new int[8];
    }
}
