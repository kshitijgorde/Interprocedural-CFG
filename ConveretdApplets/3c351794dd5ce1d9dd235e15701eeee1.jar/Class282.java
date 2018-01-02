import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class282
{
    private int anInt2124;
    static int anInt2125;
    private boolean[] aBooleanArray2126;
    private Class207 aClass207_2127;
    static int anInt2128;
    static Class293 aClass293_2129;
    static int[] anIntArray2130;
    private int[][] anIntArrayArray2131;
    static int anInt2132;
    
    static final void method3334(final byte b, final int n, final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2) {
        try {
            if (b != 37) {
                Class282.aClass293_2129 = null;
            }
            if (class246_Sub3_Sub4_Sub2.anIntArray6373 != null) {
                final int anInt6413 = class246_Sub3_Sub4_Sub2.anIntArray6373[n + 1];
                if (~class246_Sub3_Sub4_Sub2.anInt6413 != ~anInt6413) {
                    class246_Sub3_Sub4_Sub2.anInt6413 = anInt6413;
                    class246_Sub3_Sub4_Sub2.anInt6366 = 0;
                    class246_Sub3_Sub4_Sub2.anInt6436 = class246_Sub3_Sub4_Sub2.anInt6434;
                    class246_Sub3_Sub4_Sub2.anInt6361 = 1;
                    class246_Sub3_Sub4_Sub2.anInt6405 = 0;
                    class246_Sub3_Sub4_Sub2.anInt6393 = 0;
                    if (class246_Sub3_Sub4_Sub2.anInt6413 != -1) {
                        Class349.method3840((byte)(-127), class246_Sub3_Sub4_Sub2, class246_Sub3_Sub4_Sub2.anInt6393, Class151_Sub7.aClass183_5001.method2623(class246_Sub3_Sub4_Sub2.anInt6413, b + 16346));
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rj.E(" + b + ',' + n + ',' + ((class246_Sub3_Sub4_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final int[] method3335(final int n, final byte b) {
        try {
            if (n < 0 || ~n <= ~this.anIntArrayArray2131.length) {
                if (this.anInt2124 != -1) {
                    return new int[] { this.anInt2124 };
                }
                return new int[0];
            }
            else {
                if (!this.aBooleanArray2126[n] || this.anIntArrayArray2131[n].length <= 1) {
                    return this.anIntArrayArray2131[n];
                }
                if (b < 86) {
                    this.method3335(9, (byte)114);
                }
                final int n2 = (~this.anInt2124 != 0x0) ? 1 : 0;
                final Random random = new Random();
                final int[] array = new int[this.anIntArrayArray2131[n].length];
                Class236.method2891(this.anIntArrayArray2131[n], 0, array, 0, array.length);
                for (int n3 = n2; ~n3 > ~array.length; ++n3) {
                    final int n4 = Class63.method546(-28737, -n2 + array.length, random) - -n2;
                    final int n5 = array[n3];
                    array[n3] = array[n4];
                    array[n4] = n5;
                }
                return array;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rj.A(" + n + ',' + b + ')');
        }
    }
    
    final Class124 method3336(final int n, final int n2) {
        try {
            final byte[] method2745 = this.aClass207_2127.method2745(n2, 1, false);
            final Class124 class124 = new Class124();
            class124.method2215(0, new Class98_Sub22(method2745));
            return class124;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rj.D(" + n + ',' + n2 + ')');
        }
    }
    
    final boolean method3337(final int n) {
        try {
            if (n != 1) {
                this.aBooleanArray2126 = null;
            }
            return this.anInt2124 != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rj.C(" + n + ')');
        }
    }
    
    public static void method3338(final int n) {
        try {
            Class282.anIntArray2130 = null;
            if (n != 0) {
                method3334((byte)(-59), -38, null);
            }
            Class282.aClass293_2129 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rj.B(" + n + ')');
        }
    }
    
    Class282(final Class279 class279, final int n, final Class207 aClass207_2127) {
        try {
            (this.aClass207_2127 = aClass207_2127).method2761(0, 1);
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(this.aClass207_2127.method2745(0, 0, false));
            final int unsignedByte = class98_Sub22.readUnsignedByte((byte)67);
            if (~unsignedByte >= -4) {
                final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)37);
                final Class113[] method476 = Class48_Sub2_Sub1.method476(false);
                boolean b = true;
                if (~method476.length != ~unsignedByte2) {
                    b = false;
                }
                else {
                    for (int n2 = 0; method476.length > n2; ++n2) {
                        if (~class98_Sub22.readUnsignedByte((byte)9) != ~method476[n2].anInt955) {
                            b = false;
                            break;
                        }
                    }
                }
                if (!b) {
                    this.anInt2124 = -1;
                    this.aBooleanArray2126 = new boolean[0];
                    this.anIntArrayArray2131 = new int[0][];
                }
                else {
                    final int unsignedByte3 = class98_Sub22.readUnsignedByte((byte)(-110));
                    final int unsignedByte4 = class98_Sub22.readUnsignedByte((byte)49);
                    if (unsignedByte <= 2) {
                        this.anInt2124 = -1;
                    }
                    else {
                        this.anInt2124 = class98_Sub22.readUShort(false);
                    }
                    this.aBooleanArray2126 = new boolean[1 + unsignedByte4];
                    this.anIntArrayArray2131 = new int[1 + unsignedByte4][];
                    for (int n3 = 0; ~n3 > ~unsignedByte3; ++n3) {
                        final int unsignedByte5 = class98_Sub22.readUnsignedByte((byte)(-108));
                        this.aBooleanArray2126[unsignedByte5] = (~class98_Sub22.readUnsignedByte((byte)(-120)) == 0xFFFFFFFE);
                        final int i = class98_Sub22.readShort((byte)127);
                        if (this.anInt2124 == -1) {
                            this.anIntArrayArray2131[unsignedByte5] = new int[i];
                            for (int n4 = 0; ~n4 > ~i; ++n4) {
                                this.anIntArrayArray2131[unsignedByte5][n4] = class98_Sub22.readShort((byte)127);
                            }
                        }
                        else {
                            (this.anIntArrayArray2131[unsignedByte5] = new int[i + 1])[0] = this.anInt2124;
                            for (int n5 = 0; i > n5; ++n5) {
                                this.anIntArrayArray2131[unsignedByte5][1 + n5] = class98_Sub22.readShort((byte)127);
                            }
                        }
                    }
                    for (int n6 = 0; unsignedByte4 + 1 > n6; ++n6) {
                        if (this.anIntArrayArray2131[n6] == null) {
                            if (this.anInt2124 != -1) {
                                this.anIntArrayArray2131[n6] = new int[] { this.anInt2124 };
                            }
                            else {
                                this.anIntArrayArray2131[n6] = new int[0];
                            }
                        }
                    }
                }
            }
            else {
                this.aBooleanArray2126 = new boolean[0];
                this.anIntArrayArray2131 = new int[0][];
                this.anInt2124 = -1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rj.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_2127 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class282.anInt2125 = 0;
        Class282.aClass293_2129 = null;
        Class282.anIntArray2130 = new int[] { 1, 4, 1, 2 };
    }
}
