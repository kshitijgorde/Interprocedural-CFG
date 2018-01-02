import jagtheora.ogg.OggPacket;
import jagtheora.ogg.OggStreamState;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class98_Sub43 extends Class98
{
    int anInt4240;
    OggStreamState anOggStreamState4241;
    static int anInt4242;
    static boolean aBoolean4243;
    
    static final void method1481(final int n) {
        try {
            for (int n2 = 0; ~n2 > -101; ++n2) {
                aa_Sub3.aBooleanArray3574[n2] = true;
            }
            if (n != 2) {
                Class98_Sub43.aBoolean4243 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rd.N(" + n + ')');
        }
    }
    
    abstract void method1482(final OggPacket p0, final boolean p1);
    
    static final void method1483(final int anInt6400, final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub1, final int n, final int[] array) {
        try {
            if (class246_Sub3_Sub4_Sub2_Sub1.anIntArray6373 != null) {
                boolean b = true;
                for (int n2 = 0; ~n2 > ~class246_Sub3_Sub4_Sub2_Sub1.anIntArray6373.length; ++n2) {
                    if (class246_Sub3_Sub4_Sub2_Sub1.anIntArray6373[n2] != array[n2]) {
                        b = false;
                        break;
                    }
                }
                if (b && class246_Sub3_Sub4_Sub2_Sub1.anInt6413 != -1) {
                    final Class97 method2623 = Class151_Sub7.aClass183_5001.method2623(class246_Sub3_Sub4_Sub2_Sub1.anInt6413, 16383);
                    final int anInt6401 = method2623.anInt819;
                    if (anInt6401 == 1) {
                        class246_Sub3_Sub4_Sub2_Sub1.anInt6405 = 0;
                        class246_Sub3_Sub4_Sub2_Sub1.anInt6400 = anInt6400;
                        class246_Sub3_Sub4_Sub2_Sub1.anInt6393 = 0;
                        class246_Sub3_Sub4_Sub2_Sub1.anInt6366 = 0;
                        class246_Sub3_Sub4_Sub2_Sub1.anInt6361 = 1;
                        if (!class246_Sub3_Sub4_Sub2_Sub1.aBoolean6371) {
                            Class349.method3840((byte)(-126), class246_Sub3_Sub4_Sub2_Sub1, class246_Sub3_Sub4_Sub2_Sub1.anInt6393, method2623);
                        }
                    }
                    if (anInt6401 == 2) {
                        class246_Sub3_Sub4_Sub2_Sub1.anInt6405 = 0;
                    }
                }
            }
            if (n != 1) {
                method1483(58, null, 36, null);
            }
            boolean b2 = true;
            for (int n3 = 0; ~array.length < ~n3; ++n3) {
                if (array[n3] != -1) {
                    b2 = false;
                }
                if (class246_Sub3_Sub4_Sub2_Sub1.anIntArray6373 == null || class246_Sub3_Sub4_Sub2_Sub1.anIntArray6373[n3] == -1 || ~Class151_Sub7.aClass183_5001.method2623(array[n3], 16383).anInt829 <= ~Class151_Sub7.aClass183_5001.method2623(class246_Sub3_Sub4_Sub2_Sub1.anIntArray6373[n3], n ^ 0x3FFE).anInt829) {
                    class246_Sub3_Sub4_Sub2_Sub1.anIntArray6373 = array;
                    class246_Sub3_Sub4_Sub2_Sub1.anInt6436 = class246_Sub3_Sub4_Sub2_Sub1.anInt6434;
                    class246_Sub3_Sub4_Sub2_Sub1.anInt6400 = anInt6400;
                }
            }
            if (b2) {
                class246_Sub3_Sub4_Sub2_Sub1.anInt6400 = anInt6400;
                class246_Sub3_Sub4_Sub2_Sub1.anInt6436 = class246_Sub3_Sub4_Sub2_Sub1.anInt6434;
                class246_Sub3_Sub4_Sub2_Sub1.anIntArray6373 = array;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rd.P(" + anInt6400 + ',' + ((class246_Sub3_Sub4_Sub2_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final Class222[] method1484(final int n) {
        try {
            if (n != 0) {
                method1481(96);
            }
            return new Class222[] { Class298.aClass222_2478, Class298.aClass222_2480, Class298.aClass222_2481, Class298.aClass222_2482, Class298.aClass222_2483, Class298.aClass222_2484, Class298.aClass222_2485, Class298.aClass222_2486, Class298.aClass222_2487, Class298.aClass222_2488, Class298.aClass222_2489, Class298.aClass222_2490 };
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rd.M(" + n + ')');
        }
    }
    
    Class98_Sub43(final OggStreamState anOggStreamState4241) {
        try {
            this.anOggStreamState4241 = anOggStreamState4241;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rd.<init>(" + ((anOggStreamState4241 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method1485(final int n) {
        try {
            if (n != -1) {
                method1484(-108);
            }
            if (!PacketSender.aBoolean2575) {
                PacketSender.aBoolean2575 = true;
                Class64_Sub6.aBoolean3656 = true;
                Class305.aFloat2545 += (-12.0f - Class305.aFloat2545) / 2.0f;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rd.O(" + n + ')');
        }
    }
    
    final void method1486(final OggPacket oggPacket, final int n) {
        try {
            this.method1482(oggPacket, false);
            if (n != 21000) {
                Class98_Sub43.aBoolean4243 = false;
            }
            ++this.anInt4240;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rd.L(" + ((oggPacket != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    abstract void method1487(final int p0);
    
    static {
        Class98_Sub43.aBoolean4243 = true;
    }
}
