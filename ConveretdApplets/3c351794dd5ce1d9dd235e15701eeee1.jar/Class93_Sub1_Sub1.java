import java.awt.Component;
import java.awt.Insets;
import javax.accessibility.Accessible;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class93_Sub1_Sub1 extends Class93_Sub1
{
    static int anInt6289;
    int anInt6290;
    static int anInt6291;
    
    Class93_Sub1_Sub1(final Class63 class63, final Class110 class64, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13, final int anInt6290) {
        super(class63, class64, n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13);
        try {
            this.anInt6290 = anInt6290;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hi.<init>(" + ((class63 != null) ? "{...}" : "null") + ',' + ((class64 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ',' + n11 + ',' + n12 + ',' + n13 + ',' + anInt6290 + ')');
        }
    }
    
    static final void method908(final int n, final int n2, final boolean b, final int n3, final int n4) {
        try {
            if (Class98_Sub46.anInt4261 == 1) {
                int n5 = n3 / Class197.anInt1513;
                int n6 = n4 / Class197.anInt1513;
                int n7 = n2 / Class98_Sub10_Sub38.anInt5761;
                int n8 = n / Class98_Sub10_Sub38.anInt5761;
                if (b) {
                    Class93_Sub1_Sub1.anInt6291 = 95;
                }
                if (n5 < Class191.anInt1477 && ~n6 <= -1 && ~n7 > ~Class63.anInt493 && ~n8 <= -1) {
                    if (~n5 > -1) {
                        n5 = 0;
                    }
                    if (n7 < 0) {
                        n7 = 0;
                    }
                    if (~Class63.anInt493 >= ~n8) {
                        n8 = -1 + Class63.anInt493;
                    }
                    if (~n6 <= ~Class191.anInt1477) {
                        n6 = Class191.anInt1477 - 1;
                    }
                    for (int i = n7; i <= n8; ++i) {
                        final int n9 = Class198.method2678((byte)6, i + Class268.anInt2007, Class63.anInt493) * Class191.anInt1477;
                        for (int j = n5; j <= n6; ++j) {
                            Class146_Sub2.anIntArray4873[n9 + Class198.method2678((byte)6, j - -Class76_Sub8.anInt3780, Class191.anInt1477)] = Class230.anInt1732;
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hi.C(" + n + ',' + n2 + ',' + b + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    public final Class113 method70(final int n) {
        try {
            if (n != 30778) {
                return null;
            }
            return Class18.aClass113_210;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hi.A(" + n + ')');
        }
    }
    
    static final void method909(final int n, final int n2, final boolean b, final int n3, final int n4, final int n5) {
        try {
            if (Class98_Sub18.aFrame3950 != null && (n != 3 || ~Class110.anInt946 != ~n4 || ~n5 != ~Class112.anInt949)) {
                Class281.method3331(false, Class98_Sub18.aFrame3950, Class98_Sub43_Sub2.aClass88_5907);
                Class98_Sub18.aFrame3950 = null;
            }
            if (~n == 0xFFFFFFFC && Class98_Sub18.aFrame3950 == null) {
                Class98_Sub18.aFrame3950 = Class54.method503(0, (byte)105, n4, n5, 0, Class98_Sub43_Sub2.aClass88_5907);
                if (Class98_Sub18.aFrame3950 != null) {
                    Class110.anInt946 = n4;
                    Class112.anInt949 = n5;
                    Class310.method3618(-5964);
                }
            }
            if (~n == 0xFFFFFFFC && Class98_Sub18.aFrame3950 == null) {
                method909(Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub27_4052.method666((byte)120), n2, true, n3, -1, -1);
            }
            else {
                Accessible accessible;
                if (Class98_Sub18.aFrame3950 == null) {
                    if (Class284.aFrame2168 == null) {
                        if (Class76_Sub11.anApplet3799 != null) {
                            accessible = Class76_Sub11.anApplet3799;
                        }
                        else {
                            accessible = Class246_Sub3_Sub5_Sub2.anApplet_Sub1_6271;
                        }
                        Class188.anInt1453 = ((Component)accessible).getSize().width;
                        Class123_Sub1.anInt4744 = ((Component)accessible).getSize().height;
                    }
                    else {
                        final Insets insets = Class284.aFrame2168.getInsets();
                        Class188.anInt1453 = Class284.aFrame2168.getSize().width + (-insets.left + -insets.right);
                        Class123_Sub1.anInt4744 = Class284.aFrame2168.getSize().height - (insets.bottom + insets.top);
                        accessible = Class284.aFrame2168;
                    }
                }
                else {
                    Class188.anInt1453 = n4;
                    accessible = Class98_Sub18.aFrame3950;
                    Class123_Sub1.anInt4744 = n5;
                }
                if (~n != 0xFFFFFFFE) {
                    Class299.method3513(false);
                }
                else {
                    Class191.anInt1479 = 0;
                    Class39_Sub1.anInt3593 = Class98_Sub17_Sub1.anInt5782;
                    Class98_Sub25.anInt4024 = Class246_Sub2.anInt5072;
                    Class98_Sub46_Sub10.anInt6022 = (Class188.anInt1453 + -Class98_Sub17_Sub1.anInt5782) / 2;
                }
                if (Class43.aClass196_375 == Class64_Sub29.aClass196_3720 || Class39_Sub1.anInt3593 >= 1024 || Class98_Sub25.anInt4024 < 768) {}
                if (!b) {
                    Class42_Sub3.aCanvas5361.setSize(Class39_Sub1.anInt3593, Class98_Sub25.anInt4024);
                    if (za_Sub2.aBoolean6079) {
                        Class293.method3471(Class42_Sub3.aCanvas5361, 0);
                    }
                    else {
                        Class265.aHa1974.method1782(Class42_Sub3.aCanvas5361, Class39_Sub1.anInt3593, Class98_Sub25.anInt4024);
                    }
                    if (Class284.aFrame2168 != accessible) {
                        Class42_Sub3.aCanvas5361.setLocation(Class98_Sub46_Sub10.anInt6022, Class191.anInt1479);
                    }
                    else {
                        final Insets insets2 = Class284.aFrame2168.getInsets();
                        Class42_Sub3.aCanvas5361.setLocation(insets2.left + Class98_Sub46_Sub10.anInt6022, insets2.top + Class191.anInt1479);
                    }
                }
                else {
                    Class277.method3292((byte)107);
                }
                if (n < 2) {
                    Class134.aBoolean3457 = false;
                }
                else {
                    Class134.aBoolean3457 = true;
                }
                if (~Class15.anInt185 != 0x0) {
                    Class40.method359(n2 ^ 0x7448, true);
                }
                if (aa_Sub1.aClass123_3561 != null && za_Sub2.method1683(-11297, Class177.anInt1376)) {
                    Class98_Sub17_Sub1.method1158(-2);
                }
                if (n2 != -29758) {
                    Class93_Sub1_Sub1.anInt6289 = 13;
                }
                for (int i = 0; i < 100; ++i) {
                    aa_Sub3.aBooleanArray3574[i] = true;
                }
                Class246_Sub10.aBoolean5152 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hi.B(" + n + ',' + n2 + ',' + b + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
}
