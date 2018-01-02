// 
// Decompiled by Procyon v0.5.30
// 

final class RuntimeException_Sub1 extends RuntimeException
{
    Throwable aThrowable3199;
    static int[] anIntArray3200;
    static int anInt3201;
    String aString3202;
    static Class111 aClass111_3203;
    static Class79 aClass79_3204;
    static int anInt3205;
    
    static final void method4010(final int n, int i, int n2, int anInt5753, int n3, final int n4) {
        try {
            if (~Class218.anInt1635 <= ~anInt5753 && i >= Class98_Sub10_Sub38.anInt5753) {
                boolean b;
                if (~Class76_Sub8.anInt3778 < ~n2) {
                    b = false;
                    n2 = Class76_Sub8.anInt3778;
                }
                else if (Class3.anInt77 >= n2) {
                    b = true;
                }
                else {
                    n2 = Class3.anInt77;
                    b = false;
                }
                boolean b2;
                if (n3 < Class76_Sub8.anInt3778) {
                    b2 = false;
                    n3 = Class76_Sub8.anInt3778;
                }
                else if (n3 <= Class3.anInt77) {
                    b2 = true;
                }
                else {
                    n3 = Class3.anInt77;
                    b2 = false;
                }
                if (anInt5753 < Class98_Sub10_Sub38.anInt5753) {
                    anInt5753 = Class98_Sub10_Sub38.anInt5753;
                }
                else {
                    Class333.method3761(n4, Class97.anIntArrayArray814[anInt5753++], n2, n3, (byte)113);
                }
                if (~i < ~Class218.anInt1635) {
                    i = Class218.anInt1635;
                }
                else {
                    Class333.method3761(n4, Class97.anIntArrayArray814[i--], n2, n3, (byte)26);
                }
                if (b && b2) {
                    for (int n5 = anInt5753; i >= n5; ++n5) {
                        final int[] array = Class97.anIntArrayArray814[n5];
                        array[n2] = (array[n3] = n4);
                    }
                }
                else if (!b) {
                    if (b2) {
                        for (int j = anInt5753; j <= i; ++j) {
                            Class97.anIntArrayArray814[j][n3] = n4;
                        }
                    }
                }
                else {
                    for (int k = anInt5753; k <= i; ++k) {
                        Class97.anIntArrayArray814[k][n2] = n4;
                    }
                }
            }
            if (n != -31085) {
                method4012(false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ru.B(" + n + ',' + i + ',' + n2 + ',' + anInt5753 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static final void method4011(final int n) {
        try {
            if (~Class98_Sub10_Sub6.anInt5570 <= -1) {
                final long method3819 = Class343.method3819(-47);
                Class98_Sub10_Sub6.anInt5570 -= (int)(-Class98_Sub46_Sub12.aLong6035 + method3819);
                Label_0462: {
                    if (Class98_Sub10_Sub6.anInt5570 <= 0) {
                        Class98_Sub46_Sub6.anInt5979 = aa.aClass28_50.anInt290;
                        Class159.aFloat1254 = aa.aClass28_50.aFloat281;
                        Class215.aFloat1613 = aa.aClass28_50.aFloat289;
                        Class263.anInt1965 = aa.aClass28_50.anInt283;
                        Class141.aFloat1150 = aa.aClass28_50.aFloat288;
                        Class98_Sub10_Sub6.anInt5570 = -1;
                        Class284_Sub1_Sub2.anInt6192 = aa.aClass28_50.anInt285;
                        Class46.aFloat388 = aa.aClass28_50.aFloat293;
                        Class98_Sub46_Sub4.aClass48_5962 = aa.aClass28_50.aClass48_287;
                        Class260.aFloat3260 = aa.aClass28_50.aFloat295;
                        Class97.aFloat831 = aa.aClass28_50.aFloat291;
                        if (!client.aBoolean3553) {
                            break Label_0462;
                        }
                    }
                    final int n2 = (Class98_Sub10_Sub6.anInt5570 << -869608184) / Class287.anInt2196;
                    final int n3 = -n2 + 255;
                    final float n4 = 1.0f - n2 / 255.0f;
                    Class98_Sub46_Sub6.anInt5979 = (0xFF0000 & n2 * (Class98_Sub28_Sub1.anInt5811 & 0xFF00) - -(n3 * (aa.aClass28_50.anInt290 & 0xFF00))) + (n3 * (0xFF00FF & aa.aClass28_50.anInt290) + (Class98_Sub28_Sub1.anInt5811 & 0xFF00FF) * n2 & 0xFF00FF00) >>> -1850804568;
                    Class46.aFloat388 = n4 * (-IOException_Sub1.aFloat31 + aa.aClass28_50.aFloat293) + IOException_Sub1.aFloat31;
                    Class215.aFloat1613 = Class3.aFloat78 + n4 * (aa.aClass28_50.aFloat289 - Class3.aFloat78);
                    Class97.aFloat831 = Class157.aFloat1249 + n4 * (-Class157.aFloat1249 + aa.aClass28_50.aFloat291);
                    Class260.aFloat3260 = (aa.aClass28_50.aFloat295 - Class346.aFloat2900) * n4 + Class346.aFloat2900;
                    Class159.aFloat1254 = (-Class135.aFloat1053 + aa.aClass28_50.aFloat281) * n4 + Class135.aFloat1053;
                    Class284_Sub1_Sub2.anInt6192 = (n2 * (Class162.anInt1271 & 0xFF00) - -(n3 * (0xFF00 & aa.aClass28_50.anInt285)) & 0xFF0000) + (0xFF00FF00 & (Class162.anInt1271 & 0xFF00FF) * n2 + (aa.aClass28_50.anInt285 & 0xFF00FF) * n3) >>> 1466852232;
                    Class263.anInt1965 = n2 * Class98_Sub12.anInt3872 + aa.aClass28_50.anInt283 * n3 >> 40165512;
                    Class141.aFloat1150 = Class234.aFloat1749 + (aa.aClass28_50.aFloat288 - Class234.aFloat1749) * n4;
                    if (aa.aClass28_50.aClass48_287 != Class140.aClass48_3245) {
                        Class98_Sub46_Sub4.aClass48_5962 = Class98_Sub37.aHa4185.method1769(Class140.aClass48_3245, aa.aClass28_50.aClass48_287, n4, Class98_Sub46_Sub4.aClass48_5962);
                    }
                }
                Class98_Sub46_Sub12.aLong6035 = method3819;
            }
            if (n > -36) {
                method4010(24, 114, 12, -24, 33, -8);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ru.A(" + n + ')');
        }
    }
    
    RuntimeException_Sub1(final Throwable aThrowable3199, final String aString3202) {
        try {
            this.aThrowable3199 = aThrowable3199;
            this.aString3202 = aString3202;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static final int method4012(final boolean b) {
        try {
            if (!b) {
                method4010(46, -81, -49, 16, 90, -120);
            }
            return Class140.aClass47_3241.method450((byte)3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ru.D(" + b + ')');
        }
    }
    
    public static void method4013(final int n) {
        try {
            RuntimeException_Sub1.anIntArray3200 = null;
            RuntimeException_Sub1.aClass111_3203 = null;
            if (n == 16711935) {
                RuntimeException_Sub1.aClass79_3204 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static {
        RuntimeException_Sub1.anIntArray3200 = new int[1];
        RuntimeException_Sub1.anInt3205 = -1;
        RuntimeException_Sub1.aClass79_3204 = new Class79(64);
    }
}
