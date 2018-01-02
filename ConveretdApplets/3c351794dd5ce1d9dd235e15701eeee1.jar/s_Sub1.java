import jaclib.memory.heap.NativeHeapBuffer;
import jaclib.memory.Buffer;
import jaclib.memory.Stream;
import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class s_Sub1 extends s
{
    private Class98_Sub20[][][] aClass98_Sub20ArrayArrayArray5190;
    private int anInt5191;
    int[][][] anIntArrayArrayArray5192;
    private int[][][] anIntArrayArrayArray5193;
    int[][][] anIntArrayArrayArray5194;
    private int[][][] anIntArrayArrayArray5195;
    short[][] aShortArrayArray5196;
    static Class279 aClass279_5197;
    int anInt5198;
    private Class148 aClass148_5199;
    static boolean aBoolean5200;
    private byte[][] aByteArrayArray5201;
    static Class346 aClass346_5202;
    private int anInt5203;
    private int anInt5204;
    static IncomingOpcode aClass58_5205;
    ha_Sub1 aHa_Sub1_5206;
    static boolean aBoolean5207;
    private int[][][] anIntArrayArrayArray5208;
    private Class33 aClass33_5209;
    int[][][] anIntArrayArrayArray5210;
    private int anInt5211;
    private byte[][] aByteArrayArray5212;
    private int anInt5213;
    private float[][] aFloatArrayArray5214;
    Class104 aClass104_5215;
    private Class104 aClass104_5216;
    private int anInt5217;
    private float[][] aFloatArrayArray5218;
    private Class377 aClass377_5219;
    private Interface16 anInterface16_5220;
    private Class98_Sub20[] aClass98_Sub20Array5221;
    Class104 aClass104_5222;
    private float[][] aFloatArrayArray5223;
    Class104 aClass104_5224;
    
    @Override
    final void ka(final int n, final int n2, final int n3) {
        try {
            if ((this.aByteArrayArray5212[n][n2] & 0xFF) < n3) {
                this.aByteArrayArray5212[n][n2] = (byte)n3;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.ka(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void wa(final r r, final int n, final int n2, final int n3, final int n4, final boolean b) {
        try {
            if (this.aClass33_5209 != null && r != null) {
                this.aClass33_5209.method322(n3 + -(n2 * this.aHa_Sub1_5206.anInt4377 >> -391620984) >> this.aHa_Sub1_5206.anInt4319, n - (this.aHa_Sub1_5206.anInt4398 * n2 >> 404453736) >> this.aHa_Sub1_5206.anInt4319, false, r);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.wa(" + ((r != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    @Override
    final void method3424(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6, final int[] array7, final int[] array8, final int[] array9, final int[] array10, final int[] array11, final int n3, final int n4, final int n5, final boolean b) {
        try {
            final int length = array8.length;
            final int[] array12 = new int[3 * length];
            final int[] array13 = new int[length * 3];
            final int[] array14 = new int[3 * length];
            final int[] array15 = new int[length * 3];
            final int[] array16 = new int[3 * length];
            final int[] array17 = new int[3 * length];
            final int[] array18 = (int[])((array2 == null) ? null : new int[3 * length]);
            final int[] array19 = (int[])((array4 == null) ? null : new int[length * 3]);
            int n6 = 0;
            for (int i = 0; i < length; ++i) {
                final int n7 = array5[i];
                final int n8 = array6[i];
                final int n9 = array7[i];
                array12[n6] = array[n7];
                array13[n6] = array3[n7];
                array14[n6] = array8[i];
                array16[n6] = array10[i];
                array17[n6] = array11[i];
                array15[n6] = ((array9 == null) ? array8[i] : array9[i]);
                if (array2 != null) {
                    array18[n6] = array2[n7];
                }
                if (array4 != null) {
                    array19[n6] = array4[n7];
                }
                ++n6;
                array12[n6] = array[n8];
                array13[n6] = array3[n8];
                array14[n6] = array8[i];
                array16[n6] = array10[i];
                array17[n6] = array11[i];
                array15[n6] = ((array9 != null) ? array9[i] : array8[i]);
                if (array2 != null) {
                    array18[n6] = array2[n8];
                }
                if (array4 != null) {
                    array19[n6] = array4[n8];
                }
                ++n6;
                array12[n6] = array[n9];
                array13[n6] = array3[n9];
                array14[n6] = array8[i];
                array16[n6] = array10[i];
                array17[n6] = array11[i];
                array15[n6] = ((array9 != null) ? array9[i] : array8[i]);
                if (array2 != null) {
                    array18[n6] = array2[n9];
                }
                if (array4 != null) {
                    array19[n6] = array4[n9];
                }
                ++n6;
            }
            this.U(n, n2, array12, array18, array13, array19, array14, array15, array16, array17, n3, n4, n5, b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.H(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + ((array3 != null) ? "{...}" : "null") + ',' + ((array4 != null) ? "{...}" : "null") + ',' + ((array5 != null) ? "{...}" : "null") + ',' + ((array6 != null) ? "{...}" : "null") + ',' + ((array7 != null) ? "{...}" : "null") + ',' + ((array8 != null) ? "{...}" : "null") + ',' + ((array9 != null) ? "{...}" : "null") + ',' + ((array10 != null) ? "{...}" : "null") + ',' + ((array11 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ',' + b + ')');
        }
    }
    
    static final boolean method3427(final int n, final int n2, final int n3, final int n4, final byte b, final int n5, final int n6) {
        try {
            final int n7 = n2 + n6;
            final int n8 = n - -n3;
            final int n9 = n4 + n5;
            if (!Class254.method3187(n8, n6, n5, (byte)82, n8, n9, n8, n9, n7, n6)) {
                return false;
            }
            if (!Class254.method3187(n8, n7, n5, (byte)82, n8, n9, n8, n5, n7, n6)) {
                return false;
            }
            if (n6 < Class127.anInt1018) {
                if (!Class254.method3187(n8, n6, n9, (byte)82, n, n9, n8, n5, n6, n6)) {
                    return false;
                }
                if (!Class254.method3187(n, n6, n9, (byte)82, n, n5, n8, n5, n6, n6)) {
                    return false;
                }
            }
            else {
                if (!Class254.method3187(n8, n7, n9, (byte)82, n, n9, n8, n5, n7, n7)) {
                    return false;
                }
                if (!Class254.method3187(n, n7, n9, (byte)82, n, n5, n8, n5, n7, n7)) {
                    return false;
                }
            }
            if (b != 16) {
                return true;
            }
            if (Class98_Sub48.anInt4280 <= n5) {
                if (!Class254.method3187(n8, n6, n9, (byte)82, n, n9, n8, n9, n7, n6)) {
                    return false;
                }
                if (!Class254.method3187(n, n7, n9, (byte)82, n, n9, n8, n9, n7, n6)) {
                    return false;
                }
            }
            else {
                if (!Class254.method3187(n8, n6, n5, (byte)82, n, n5, n8, n5, n7, n6)) {
                    return false;
                }
                if (!Class254.method3187(n, n7, n5, (byte)82, n, n5, n8, n5, n7, n6)) {
                    return false;
                }
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.J(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    private final void method3428(final int n, final int n2, final boolean b, final int n3, final int n4, final boolean[][] array, final int n5, final int n6) {
        try {
            if (this.aClass98_Sub20Array5221 != null) {
                final int n7 = 1 + n3 + n3;
                final int n8 = n7 * n7;
                if (~n8 < ~this.aHa_Sub1_5206.anIntArray4468.length) {
                    this.aHa_Sub1_5206.anIntArray4468 = new int[n8];
                }
                if (this.anInt5211 * 2 > this.aHa_Sub1_5206.aClass98_Sub22_Sub2_4446.aByteArray3992.length) {
                    this.aHa_Sub1_5206.aClass98_Sub22_Sub2_4446 = new Class98_Sub22_Sub2(2 * this.anInt5211);
                }
                int n9 = -n3 + n4;
                final int n10;
                if ((n10 = n9) < 0) {
                    n9 = 0;
                }
                int n11 = n6 - n3;
                final int n12;
                if ((n12 = n11) < 0) {
                    n11 = 0;
                }
                int n13 = n4 + n3;
                if (n13 > -1 + super.anInt2203) {
                    n13 = -1 + super.anInt2203;
                }
                int n14 = n3 + n6;
                if (super.anInt2204 - 1 < n14) {
                    n14 = super.anInt2204 - 1;
                }
                int n15 = 0;
                final int[] anIntArray4468 = this.aHa_Sub1_5206.anIntArray4468;
                for (int n16 = n9; ~n13 <= ~n16; ++n16) {
                    final boolean[] array2 = array[n16 - n10];
                    for (int n17 = n11; ~n14 <= ~n17; ++n17) {
                        if (array2[-n12 + n17]) {
                            anIntArray4468[n15++] = n17 * super.anInt2203 - -n16;
                        }
                    }
                }
                if (n2 == -1) {
                    this.aHa_Sub1_5206.method1861(19330);
                }
                else {
                    this.aHa_Sub1_5206.method1890(n2, true);
                    this.aHa_Sub1_5206.method1901((byte)(-35));
                }
                this.aHa_Sub1_5206.method1851(~(this.anInt5198 & 0x7) != -1, false);
                for (int n18 = 0; ~n18 > ~this.aClass98_Sub20Array5221.length; ++n18) {
                    this.aClass98_Sub20Array5221[n18].method1169(anIntArray4468, (byte)98, n15);
                }
                if (!this.aClass148_5199.method2420(-126)) {
                    final int anInt4455 = this.aHa_Sub1_5206.anInt4455;
                    final int anInt4456 = this.aHa_Sub1_5206.anInt4441;
                    this.aHa_Sub1_5206.L(0, anInt4456, this.aHa_Sub1_5206.anInt4427);
                    this.aHa_Sub1_5206.method1851(false, false);
                    this.aHa_Sub1_5206.method1875((byte)(-123), false);
                    this.aHa_Sub1_5206.method1870((byte)(-58), 128);
                    this.aHa_Sub1_5206.method1834(-99, -2);
                    this.aHa_Sub1_5206.method1863(1, this.aHa_Sub1_5206.aClass42_Sub1_4358);
                    this.aHa_Sub1_5206.method1899(7681, n5 ^ 0xFE2, 8448);
                    this.aHa_Sub1_5206.method1840(0, 770, 116, 34166);
                    this.aHa_Sub1_5206.method1886(770, 0, 34200, 34167);
                    for (Class98 class98 = this.aClass148_5199.method2418(32); class98 != null; class98 = this.aClass148_5199.method2417(91)) {
                        ((Class98_Sub37)class98).method1461(n6, n3, n4, array, (byte)38);
                    }
                    this.aHa_Sub1_5206.method1840(0, 768, n5 ^ 0x2C88, 5890);
                    this.aHa_Sub1_5206.method1886(770, 0, 34200, 5890);
                    this.aHa_Sub1_5206.method1863(1, null);
                    this.aHa_Sub1_5206.L(anInt4455, anInt4456, this.aHa_Sub1_5206.anInt4427);
                }
                if (this.aClass33_5209 != null) {
                    OpenGL.glPushMatrix();
                    OpenGL.glTranslatef(0.0f, -1.0f, 0.0f);
                    this.aHa_Sub1_5206.method1868(null, null, this.aClass104_5224, this.aClass104_5222, n5 - 11490);
                    this.aClass33_5209.method320(b, n3, n4, array, (byte)93, n6);
                    OpenGL.glPopMatrix();
                }
            }
            if (n5 != 11490) {
                s_Sub1.aBoolean5200 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.A(" + n + ',' + n2 + ',' + b + ',' + n3 + ',' + n4 + ',' + ((array != null) ? "{...}" : "null") + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    @Override
    final void method3422(final int n, final int n2, final int n3, final int n4, final int n5, final int i, final int j, final boolean[][] array) {
        try {
            if (~this.anInt5217 < -1) {
                this.aHa_Sub1_5206.method1867(29458);
                this.aHa_Sub1_5206.method1856(false, 6914);
                this.aHa_Sub1_5206.method1851(false, false);
                this.aHa_Sub1_5206.method1881(false, false);
                this.aHa_Sub1_5206.method1875((byte)27, false);
                this.aHa_Sub1_5206.method1870((byte)(-35), 0);
                this.aHa_Sub1_5206.method1834(-62, -2);
                this.aHa_Sub1_5206.method1863(1, null);
                Class86.aFloatArray640[4] = 0.0f;
                Class86.aFloatArray640[13] = 1.0f - (n2 * 2 + n3 * j / 128.0f) / this.aHa_Sub1_5206.anInt4304;
                Class86.aFloatArray640[9] = 0.0f;
                Class86.aFloatArray640[3] = 0.0f;
                Class86.aFloatArray640[7] = 0.0f;
                Class86.aFloatArray640[11] = 0.0f;
                Class86.aFloatArray640[12] = -1.0f - (-(2 * n) + n4 * n3 / 128.0f) / this.aHa_Sub1_5206.anInt4305;
                Class86.aFloatArray640[15] = 1.0f;
                Class86.aFloatArray640[14] = 0.0f;
                Class86.aFloatArray640[10] = 0.0f;
                Class86.aFloatArray640[0] = n3 / (this.aHa_Sub1_5206.anInt4305 * (128.0f * super.anInt2206));
                Class86.aFloatArray640[6] = 0.0f;
                Class86.aFloatArray640[8] = 0.0f;
                Class86.aFloatArray640[1] = 0.0f;
                Class86.aFloatArray640[2] = 0.0f;
                Class86.aFloatArray640[5] = n3 / (super.anInt2206 * 128.0f * this.aHa_Sub1_5206.anInt4304);
                OpenGL.glMatrixMode(5889);
                OpenGL.glLoadMatrixf(Class86.aFloatArray640, 0);
                Class86.aFloatArray640[10] = 0.0f;
                Class86.aFloatArray640[7] = 0.0f;
                Class86.aFloatArray640[12] = 0.0f;
                Class86.aFloatArray640[15] = 1.0f;
                Class86.aFloatArray640[4] = 0.0f;
                Class86.aFloatArray640[5] = 0.0f;
                Class86.aFloatArray640[14] = 0.0f;
                Class86.aFloatArray640[11] = 0.0f;
                Class86.aFloatArray640[2] = 0.0f;
                Class86.aFloatArray640[8] = 0.0f;
                Class86.aFloatArray640[3] = 0.0f;
                Class86.aFloatArray640[0] = 1.0f;
                Class86.aFloatArray640[6] = 1.0f;
                Class86.aFloatArray640[1] = 0.0f;
                Class86.aFloatArray640[9] = 1.0f;
                Class86.aFloatArray640[13] = 0.0f;
                OpenGL.glMatrixMode(5888);
                OpenGL.glLoadMatrixf(Class86.aFloatArray640, 0);
                if (~(this.anInt5198 & 0x7) != -1) {
                    this.aHa_Sub1_5206.method1851(true, false);
                    this.aHa_Sub1_5206.method1831(126);
                }
                else {
                    this.aHa_Sub1_5206.method1851(false, false);
                }
                this.aHa_Sub1_5206.method1868(this.aClass104_5216, this.aClass104_5215, this.aClass104_5224, this.aClass104_5222, 0);
                if (this.aHa_Sub1_5206.aClass98_Sub22_Sub2_4446.aByteArray3992.length < this.anInt5211 * 2) {
                    this.aHa_Sub1_5206.aClass98_Sub22_Sub2_4446 = new Class98_Sub22_Sub2(this.anInt5211 * 2);
                }
                else {
                    this.aHa_Sub1_5206.aClass98_Sub22_Sub2_4446.anInt3991 = 0;
                }
                int n6 = 0;
                final Class98_Sub22_Sub2 aClass98_Sub22_Sub2_4446 = this.aHa_Sub1_5206.aClass98_Sub22_Sub2_4446;
                if (this.aHa_Sub1_5206.aBoolean4397) {
                    for (int k = n5; k < j; ++k) {
                        int n7 = super.anInt2203 * k - -n4;
                        for (int n8 = n4; ~n8 > ~i; ++n8) {
                            if (array[-n4 + n8][-n5 + k]) {
                                final short[] array2 = this.aShortArrayArray5196[n7];
                                if (array2 != null) {
                                    for (int n9 = 0; ~array2.length < ~n9; ++n9) {
                                        ++n6;
                                        aClass98_Sub22_Sub2_4446.writeShort(array2[n9] & 0xFFFF, 1571862888);
                                    }
                                }
                            }
                            ++n7;
                        }
                    }
                }
                else {
                    for (int n10 = n5; j > n10; ++n10) {
                        int n11 = n4 + n10 * super.anInt2203;
                        for (int n12 = n4; i > n12; ++n12) {
                            if (array[n12 + -n4][-n5 + n10]) {
                                final short[] array3 = this.aShortArrayArray5196[n11];
                                if (array3 != null) {
                                    for (int n13 = 0; array3.length > n13; ++n13) {
                                        aClass98_Sub22_Sub2_4446.method1247(array3[n13] & 0xFFFF, 4);
                                        ++n6;
                                    }
                                }
                            }
                            ++n11;
                        }
                    }
                }
                if (n6 > 0) {
                    this.aHa_Sub1_5206.method1865(n6, 4, new Class156_Sub1(this.aHa_Sub1_5206, 5123, aClass98_Sub22_Sub2_4446.aByteArray3992, aClass98_Sub22_Sub2_4446.anInt3991), false, 0);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.F(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + i + ',' + j + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    s_Sub1(final ha_Sub1 aHa_Sub1_5206, final int n, final int anInt5198, final int n2, final int n3, final int[][] array, final int[][] array2, final int n4) {
        super(n2, n3, n4, array);
        this.aClass148_5199 = new Class148();
        try {
            this.anInt5191 = -2 + super.anInt2200;
            this.aHa_Sub1_5206 = aHa_Sub1_5206;
            this.anIntArrayArrayArray5194 = new int[n2][n3][];
            this.anIntArrayArrayArray5210 = new int[n2][n3][];
            this.anIntArrayArrayArray5192 = new int[n2][n3][];
            this.aShortArrayArray5196 = new short[n3 * n2][];
            this.aByteArrayArray5212 = new byte[1 + n2][1 + n3];
            this.anIntArrayArrayArray5193 = new int[n2][n3][];
            this.anInt5204 = 1 << this.anInt5191;
            this.anIntArrayArrayArray5195 = new int[n2][n3][];
            this.aFloatArrayArray5218 = new float[super.anInt2203 + 1][super.anInt2204 + 1];
            this.anInt5198 = anInt5198;
            this.aFloatArrayArray5214 = new float[super.anInt2203 + 1][super.anInt2204 + 1];
            this.aClass98_Sub20ArrayArrayArray5190 = new Class98_Sub20[n2][n3][];
            this.aByteArrayArray5201 = new byte[n2][n3];
            this.aFloatArrayArray5223 = new float[super.anInt2203 + 1][1 + super.anInt2204];
            for (int n5 = 1; super.anInt2204 > n5; ++n5) {
                for (int n6 = 1; ~n6 > ~super.anInt2203; ++n6) {
                    final int n7 = -array2[-1 + n6][n5] + array2[1 + n6][n5];
                    final int n8 = array2[n6][1 + n5] + -array2[n6][-1 + n5];
                    final float n9 = (float)(1.0 / Math.sqrt(n8 * n8 + (4 * n4 * n4 + n7 * n7)));
                    this.aFloatArrayArray5218[n6][n5] = n9 * n7;
                    this.aFloatArrayArray5214[n6][n5] = 2 * -n4 * n9;
                    this.aFloatArrayArray5223[n6][n5] = n8 * n9;
                }
            }
            this.aClass377_5219 = new Class377(128);
            if ((this.anInt5198 & 0x10) != 0x0) {
                this.aClass33_5209 = new Class33(this.aHa_Sub1_5206, this);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.<init>(" + ((aHa_Sub1_5206 != null) ? "{...}" : "null") + ',' + n + ',' + anInt5198 + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n4 + ')');
        }
    }
    
    @Override
    final boolean method3418(final r r, final int n, final int n2, final int n3, final int n4, final boolean b) {
        try {
            return this.aClass33_5209 != null && r != null && this.aClass33_5209.method325(r, -115, n - (n2 * this.aHa_Sub1_5206.anInt4398 >> 2105336840) >> this.aHa_Sub1_5206.anInt4319, n3 + -(n2 * this.aHa_Sub1_5206.anInt4377 >> 1767505640) >> this.aHa_Sub1_5206.anInt4319);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.O(" + ((r != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    @Override
    final void CA(final r r, final int n, final int n2, final int n3, final int n4, final boolean b) {
        try {
            if (this.aClass33_5209 != null && r != null) {
                this.aClass33_5209.method324((byte)94, n3 - (n2 * this.aHa_Sub1_5206.anInt4377 >> -1852845016) >> this.aHa_Sub1_5206.anInt4319, -(this.aHa_Sub1_5206.anInt4398 * n2 >> -1029072408) + n >> this.aHa_Sub1_5206.anInt4319, r);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.CA(" + ((r != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    private final void method3429(final int n, final int n2, final int n3, final r_Sub1 r_Sub1) {
        try {
            final int[] array = this.anIntArrayArrayArray5192[n][n2];
            final int[] array2 = this.anIntArrayArrayArray5194[n][n2];
            final int i = array.length;
            if (~i < ~this.aHa_Sub1_5206.anIntArray4471.length) {
                this.aHa_Sub1_5206.anIntArray4471 = new int[i];
                this.aHa_Sub1_5206.anIntArray4470 = new int[i];
            }
            final int[] anIntArray4471 = this.aHa_Sub1_5206.anIntArray4471;
            final int[] anIntArray4472 = this.aHa_Sub1_5206.anIntArray4470;
            for (int n4 = 0; i > n4; ++n4) {
                anIntArray4471[n4] = array[n4] >> this.aHa_Sub1_5206.anInt4319;
                anIntArray4472[n4] = array2[n4] >> this.aHa_Sub1_5206.anInt4319;
            }
            int n5 = n3;
            while (i > n5) {
                final int n6 = anIntArray4471[n5];
                final int n7 = anIntArray4472[n5++];
                final int n8 = anIntArray4471[n5];
                final int n9 = anIntArray4472[n5++];
                final int n10 = anIntArray4471[n5];
                final int n11 = anIntArray4472[n5++];
                if ((-n8 + n6) * (-n11 + n9) - (n10 - n8) * (-n7 + n9) > 0) {
                    r_Sub1.method1648(n7, n8, n6, n11, n9, n3 - 119, n10);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.B(" + n + ',' + n2 + ',' + n3 + ',' + ((r_Sub1 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method3430(String substring, final int n) {
        try {
            if (substring != null) {
                if (substring.startsWith("*")) {
                    substring = substring.substring(1);
                }
                final String method3867 = Class353.method3867(-1, substring);
                if (method3867 != null) {
                    if (n != -23995) {
                        method3430(null, 68);
                    }
                    for (int i = 0; i < Class248.anInt1897; ++i) {
                        String substring2 = Class246_Sub4_Sub1.aStringArray6171[i];
                        if (substring2.startsWith("*")) {
                            substring2 = substring2.substring(1);
                        }
                        final String method3868 = Class353.method3867(-1, substring2);
                        if (method3868 != null && method3868.equals(method3867)) {
                            --Class248.anInt1897;
                            for (int n2 = i; ~Class248.anInt1897 < ~n2; ++n2) {
                                Class246_Sub4_Sub1.aStringArray6171[n2] = Class246_Sub4_Sub1.aStringArray6171[1 + n2];
                                Class255.aStringArray3209[n2] = Class255.aStringArray3209[1 + n2];
                                Class98_Sub45.aStringArray4255[n2] = Class98_Sub45.aStringArray4255[n2 + 1];
                                Class110.aStringArray945[n2] = Class110.aStringArray945[1 + n2];
                                Class98_Sub10_Sub38.aBooleanArray5759[n2] = Class98_Sub10_Sub38.aBooleanArray5759[n2 + 1];
                            }
                            Class363.anInt3099 = Class24.anInt242;
                            final Class98_Sub11 method3869 = Class246_Sub3_Sub4.method3023(n + 24255, Class179.aClass171_1424, Class331.aClass117_2811);
                            method3869.aClass98_Sub22_Sub1_3865.method1194(r_Sub2.method1650(substring, (byte)106), n + 24104);
                            method3869.aClass98_Sub22_Sub1_3865.method1188(substring, (byte)113);
                            Class98_Sub10_Sub29.sendPacket(false, method3869);
                            break;
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.L(" + ((substring != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final void method3421(final Class98_Sub5 class98_Sub5, final int[] array) {
        try {
            this.aClass148_5199.method2419(new Class98_Sub37(this.aHa_Sub1_5206, this, class98_Sub5, array), -20911);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.K(" + ((class98_Sub5 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method3431(final int n) {
        try {
            s_Sub1.aClass279_5197 = null;
            s_Sub1.aClass58_5205 = null;
            s_Sub1.aClass346_5202 = null;
            if (n >= -116) {
                s_Sub1.aBoolean5207 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.M(" + n + ')');
        }
    }
    
    @Override
    final r fa(final int n, final int n2, final r r) {
        try {
            if (~(0x1 & this.aByteArrayArray5201[n][n2]) == -1) {
                return null;
            }
            final int n3 = super.anInt2206 >> this.aHa_Sub1_5206.anInt4319;
            final r_Sub1 r_Sub1 = (r_Sub1)r;
            r_Sub1 r_Sub2 = null;
            Label_0094: {
                if (r_Sub1 != null && r_Sub1.method1647(n3, (byte)(-122), n3)) {
                    r_Sub2 = r_Sub1;
                    r_Sub2.method1646(93);
                    if (!client.aBoolean3553) {
                        break Label_0094;
                    }
                }
                r_Sub2 = new r_Sub1(this.aHa_Sub1_5206, n3, n3);
            }
            r_Sub2.method1643(0, n3, n3, 0, -1);
            this.method3429(n, n2, 0, r_Sub2);
            return r_Sub2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.fa(" + n + ',' + n2 + ',' + ((r != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method3416(final int n, final int n2, final int n3, final boolean[][] array, final boolean b, final int n4, final int n5) {
        try {
            this.method3428(n5, n4, b, n3, n, array, 11490, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.N(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    @Override
    final void YA() {
        try {
            if (~this.anInt5217 >= -1) {
                this.aClass33_5209 = null;
            }
            else {
                final byte[][] array = new byte[1 + super.anInt2203][super.anInt2204 + 1];
                for (int n = 1; ~n > ~super.anInt2203; ++n) {
                    for (int n2 = 1; ~n2 > ~super.anInt2204; ++n2) {
                        array[n][n2] = (byte)((this.aByteArrayArray5212[n][n2] >> -170204799) + (this.aByteArrayArray5212[n][1 + n2] >> 928359555) + ((this.aByteArrayArray5212[n][n2 - 1] >> 2018246786) + (this.aByteArrayArray5212[1 + n][n2] >> -1261459069) + (this.aByteArrayArray5212[n - 1][n2] >> 471859554)));
                    }
                }
                this.aClass98_Sub20Array5221 = new Class98_Sub20[this.aClass377_5219.method3999((byte)(-6))];
                this.aClass377_5219.method3992(this.aClass98_Sub20Array5221, (byte)74);
                for (int n3 = 0; ~this.aClass98_Sub20Array5221.length < ~n3; ++n3) {
                    this.aClass98_Sub20Array5221[n3].method1175(this.anInt5217, (byte)(-64));
                }
                int n4 = 24;
                if (this.anIntArrayArrayArray5208 != null) {
                    n4 += 4;
                }
                if ((this.anInt5198 & 0x7) != 0x0) {
                    n4 += 12;
                }
                final NativeHeapBuffer a = this.aHa_Sub1_5206.aNativeHeap4323.a(n4 * this.anInt5217, false);
                final Stream stream = new Stream(a);
                final Class98_Sub20[] array2 = new Class98_Sub20[this.anInt5217];
                int method282 = Class23.method282(47, this.anInt5217 / 4);
                if (method282 < 1) {
                    method282 = 1;
                }
                final Class377 class377 = new Class377(method282);
                final Class98_Sub20[] array3 = new Class98_Sub20[this.anInt5213];
                for (int n5 = 0; super.anInt2203 > n5; ++n5) {
                    for (int i = 0; i < super.anInt2204; ++i) {
                        if (this.anIntArrayArrayArray5210[n5][i] != null) {
                            final Class98_Sub20[] array4 = this.aClass98_Sub20ArrayArrayArray5190[n5][i];
                            final int[] array5 = this.anIntArrayArrayArray5192[n5][i];
                            final int[] array6 = this.anIntArrayArrayArray5194[n5][i];
                            int[] array7 = this.anIntArrayArrayArray5195[n5][i];
                            final int[] array8 = this.anIntArrayArrayArray5210[n5][i];
                            final int[] array9 = (int[])((this.anIntArrayArrayArray5193 == null) ? null : this.anIntArrayArrayArray5193[n5][i]);
                            final int[] array10 = (int[])((this.anIntArrayArrayArray5208 == null) ? null : this.anIntArrayArrayArray5208[n5][i]);
                            if (array7 == null) {
                                array7 = array8;
                            }
                            final float n6 = this.aFloatArrayArray5218[n5][i];
                            final float n7 = this.aFloatArrayArray5214[n5][i];
                            final float n8 = this.aFloatArrayArray5223[n5][i];
                            final float n9 = this.aFloatArrayArray5218[n5][1 + i];
                            final float n10 = this.aFloatArrayArray5214[n5][i + 1];
                            final float n11 = this.aFloatArrayArray5223[n5][1 + i];
                            final float n12 = this.aFloatArrayArray5218[1 + n5][i + 1];
                            final float n13 = this.aFloatArrayArray5214[n5 + 1][1 + i];
                            final float n14 = this.aFloatArrayArray5223[n5 + 1][i + 1];
                            final float n15 = this.aFloatArrayArray5218[1 + n5][i];
                            final float n16 = this.aFloatArrayArray5214[1 + n5][i];
                            final float n17 = this.aFloatArrayArray5223[n5 + 1][i];
                            final int n18 = 0xFF & array[n5][i];
                            final int n19 = 0xFF & array[n5][i + 1];
                            final int n20 = array[1 + n5][i + 1] & 0xFF;
                            final int n21 = array[n5 + 1][i] & 0xFF;
                            int j = 0;
                            int n22 = 0;
                        Label_0705:
                            while (~n22 > ~array8.length) {
                                final Class98_Sub20 class98_Sub20 = array4[n22];
                                while (true) {
                                    for (int n23 = 0; j > n23; ++n23) {
                                        if (class98_Sub20 == array3[n23]) {
                                            ++n22;
                                            continue Label_0705;
                                        }
                                    }
                                    array3[j++] = class98_Sub20;
                                    continue;
                                }
                            }
                            final short[][] aShortArrayArray5196 = this.aShortArrayArray5196;
                            final int n24 = super.anInt2203 * i - -n5;
                            final short[] array11 = new short[array8.length];
                            aShortArrayArray5196[n24] = array11;
                            final short[] array12 = array11;
                            for (int n25 = 0; ~array8.length < ~n25; ++n25) {
                                final int n26 = (n5 << super.anInt2200) + array5[n25];
                                final int n27 = (i << super.anInt2200) - -array6[n25];
                                final int n28 = n26 >> this.anInt5191;
                                final int n29 = n27 >> this.anInt5191;
                                final int n30 = array8[n25];
                                final int n31 = array7[n25];
                                final int n32 = (array9 != null) ? array9[n25] : 0;
                                final long n33 = n29 | (n31 << -838159056 | n30 << -1104277280 | n28 << -2101067952);
                                final int n34 = array5[n25];
                                final int n35 = array6[n25];
                                final int n36 = 74;
                                int n37 = 0;
                                float n38 = 1.0f;
                                float n39;
                                int n40;
                                float n41;
                                float n42;
                                if (~n34 != -1 || ~n35 != -1) {
                                    if (~n34 != -1 || ~n35 != ~super.anInt2206) {
                                        if (~super.anInt2206 != ~n34 || ~n35 != ~super.anInt2206) {
                                            if (n34 == super.anInt2206 && n35 == 0) {
                                                n39 = n15;
                                                n40 = n36 - n21;
                                                n41 = n17;
                                                n42 = n16;
                                            }
                                            else {
                                                final float n43 = n34 / super.anInt2206;
                                                final float n44 = n35 / super.anInt2206;
                                                final float n45 = n6 + (-n6 + n15) * n43;
                                                final float n46 = n43 * (n16 - n7) + n7;
                                                final float n47 = (n17 - n8) * n43 + n8;
                                                final float n48 = n43 * (n12 - n9) + n9;
                                                final float n49 = n10 + (n13 - n10) * n43;
                                                final float n50 = (-n11 + n14) * n43 + n11;
                                                n42 = (n49 - n46) * n44 + n46;
                                                n39 = n44 * (-n45 + n48) + n45;
                                                n41 = (n50 - n47) * n44 + n47;
                                                final int n51 = n18 + (n34 * (-n18 + n21) >> super.anInt2200);
                                                n40 = n36 - (n51 - -((-n51 + (n19 + (n34 * (n20 - n19) >> super.anInt2200))) * n35 >> super.anInt2200));
                                            }
                                        }
                                        else {
                                            n42 = n13;
                                            n41 = n14;
                                            n40 = n36 - n20;
                                            n39 = n12;
                                        }
                                    }
                                    else {
                                        n42 = n10;
                                        n39 = n9;
                                        n41 = n11;
                                        n40 = n36 - n19;
                                    }
                                }
                                else {
                                    n42 = n7;
                                    n40 = n36 - n18;
                                    n41 = n8;
                                    n39 = n6;
                                }
                                if (n30 != -1) {
                                    int n52 = (0x7F & n30) * n40 >> 633955783;
                                    if (n52 < 2) {
                                        n52 = 2;
                                    }
                                    else if (n52 > 126) {
                                        n52 = 126;
                                    }
                                    if (~(this.anInt5198 & 0x7) == -1) {
                                        final float n53 = n41 * this.aHa_Sub1_5206.aFloatArray4438[2] + (n39 * this.aHa_Sub1_5206.aFloatArray4438[0] + n42 * this.aHa_Sub1_5206.aFloatArray4438[1]);
                                        n38 = ((n53 > 0.0f) ? this.aHa_Sub1_5206.aFloat4435 : this.aHa_Sub1_5206.aFloat4407) * n53 + this.aHa_Sub1_5206.aFloat4413;
                                    }
                                    n37 = Class208.anIntArray1579[n52 | (0xFF80 & n30)];
                                }
                                Class98 method283 = null;
                                if (~(n26 & -1 + this.anInt5204) == -1 && ~(n27 & -1 + this.anInt5204) == -1) {
                                    method283 = class377.method3990(n33, -1);
                                }
                                int n54;
                                if (method283 != null) {
                                    array12[n25] = ((Class98_Sub40)method283).aShort4191;
                                    n54 = (0xFFFF & array12[n25]);
                                    if (~n30 != 0x0 && array2[n54].aLong832 > array4[n25].aLong832) {
                                        array2[n54] = array4[n25];
                                    }
                                }
                                else {
                                    int n56;
                                    if (n31 != n30) {
                                        int n55 = (0x7F & n31) * n40 >> 378317159;
                                        if (n55 < 2) {
                                            n55 = 2;
                                        }
                                        else if (n55 > 126) {
                                            n55 = 126;
                                        }
                                        n56 = Class208.anIntArray1579[(n31 & 0xFF80) | n55];
                                        if ((0x7 & this.anInt5198) == 0x0) {
                                            final float n57 = n42 * this.aHa_Sub1_5206.aFloatArray4438[1] + this.aHa_Sub1_5206.aFloatArray4438[0] * n39 + n41 * this.aHa_Sub1_5206.aFloatArray4438[2];
                                            final float n58 = n38 * ((n38 <= 0.0f) ? this.aHa_Sub1_5206.aFloat4407 : this.aHa_Sub1_5206.aFloat4435) + this.aHa_Sub1_5206.aFloat4413;
                                            final int n59 = (0xFF0CB1 & n56) >> 1379966064;
                                            final int n60 = (n56 & 0xFFC7) >> 704482856;
                                            final int n61 = 0xFF & n56;
                                            int n62 = (int)(n59 * n58);
                                            if (n62 >= 0) {
                                                if (n62 > 255) {
                                                    n62 = 255;
                                                }
                                            }
                                            else {
                                                n62 = 0;
                                            }
                                            int n63 = (int)(n60 * n58);
                                            int n64 = (int)(n61 * n58);
                                            if (~n63 > -1) {
                                                n63 = 0;
                                            }
                                            else if (n63 > 255) {
                                                n63 = 255;
                                            }
                                            if (~n64 <= -1) {
                                                if (~n64 < -256) {
                                                    n64 = 255;
                                                }
                                            }
                                            else {
                                                n64 = 0;
                                            }
                                            n56 = (n62 << -630144720 | n63 << 1834767400 | n64);
                                        }
                                    }
                                    else {
                                        n56 = n37;
                                    }
                                    if (this.aHa_Sub1_5206.aBoolean4397) {
                                        stream.b((float)n26);
                                        stream.b((float)(n32 + this.method3417(n26, n27, true)));
                                        stream.b((float)n27);
                                        stream.e((byte)(n56 >> -274242992));
                                        stream.e((byte)(n56 >> -438976888));
                                        stream.e((byte)n56);
                                        stream.e(-1);
                                        stream.b((float)n26);
                                        stream.b((float)n27);
                                        if (this.anIntArrayArrayArray5208 != null) {
                                            stream.b((float)((array10 != null) ? (-1 + array10[n25]) : 0));
                                        }
                                        if ((0x7 & this.anInt5198) != 0x0) {
                                            stream.b(n39);
                                            stream.b(n42);
                                            stream.b(n41);
                                        }
                                    }
                                    else {
                                        stream.a((float)n26);
                                        stream.a((float)(n32 + this.method3417(n26, n27, true)));
                                        stream.a((float)n27);
                                        stream.e((byte)(n56 >> -177734704));
                                        stream.e((byte)(n56 >> 1011400392));
                                        stream.e((byte)n56);
                                        stream.e(-1);
                                        stream.a((float)n26);
                                        stream.a((float)n27);
                                        if (this.anIntArrayArrayArray5208 != null) {
                                            stream.a((float)((array10 == null) ? 0 : (-1 + array10[n25])));
                                        }
                                        if (~(this.anInt5198 & 0x7) != -1) {
                                            stream.a(n39);
                                            stream.a(n42);
                                            stream.a(n41);
                                        }
                                    }
                                    n54 = this.anInt5203++;
                                    array12[n25] = (short)n54;
                                    if (~n30 != 0x0) {
                                        array2[n54] = array4[n25];
                                    }
                                    class377.method3996(new Class98_Sub40(array12[n25]), n33, -1);
                                }
                                for (int n65 = 0; ~j < ~n65; ++n65) {
                                    array3[n65].method1168(n40, (byte)77, n38, n37, n54);
                                }
                                ++this.anInt5211;
                            }
                        }
                    }
                }
                for (int k = 0; k < this.anInt5203; ++k) {
                    final Class98_Sub20 class98_Sub21 = array2[k];
                    if (class98_Sub21 != null) {
                        class98_Sub21.method1167(k, true);
                    }
                }
                for (int n66 = 0; ~super.anInt2203 < ~n66; ++n66) {
                    for (int n67 = 0; super.anInt2204 > n67; ++n67) {
                        final short[] array13 = this.aShortArrayArray5196[n66 + super.anInt2203 * n67];
                        if (array13 != null) {
                            int n68 = 0;
                            int l = 0;
                            while (l < array13.length) {
                                final int n69 = array13[l++] & 0xFFFF;
                                final int n70 = 0xFFFF & array13[l++];
                                final int n71 = array13[l++] & 0xFFFF;
                                final Class98_Sub20 class98_Sub22 = array2[n69];
                                final Class98_Sub20 class98_Sub23 = array2[n70];
                                final Class98_Sub20 class98_Sub24 = array2[n71];
                                Class98_Sub20 class98_Sub25 = null;
                                if (class98_Sub22 != null) {
                                    class98_Sub22.method1174(n68, (byte)118, n67, n66);
                                    class98_Sub25 = class98_Sub22;
                                }
                                if (class98_Sub23 != null) {
                                    class98_Sub23.method1174(n68, (byte)123, n67, n66);
                                    if (class98_Sub25 == null || class98_Sub23.aLong832 < class98_Sub25.aLong832) {
                                        class98_Sub25 = class98_Sub23;
                                    }
                                }
                                if (class98_Sub24 != null) {
                                    class98_Sub24.method1174(n68, (byte)125, n67, n66);
                                    if (class98_Sub25 == null || ~class98_Sub25.aLong832 < ~class98_Sub24.aLong832) {
                                        class98_Sub25 = class98_Sub24;
                                    }
                                }
                                if (class98_Sub25 != null) {
                                    if (class98_Sub22 != null) {
                                        class98_Sub25.method1167(n69, true);
                                    }
                                    if (class98_Sub23 != null) {
                                        class98_Sub25.method1167(n70, true);
                                    }
                                    if (class98_Sub24 != null) {
                                        class98_Sub25.method1167(n71, true);
                                    }
                                    class98_Sub25.method1174(n68, (byte)126, n67, n66);
                                }
                                ++n68;
                            }
                        }
                    }
                }
                stream.c();
                this.anInterface16_5220 = this.aHa_Sub1_5206.method1843(n4, (byte)86, a, false, stream.b());
                this.aClass104_5222 = new Class104(this.anInterface16_5220, 5126, 3, 0);
                this.aClass104_5216 = new Class104(this.anInterface16_5220, 5121, 4, 12);
                int n72;
                if (this.anIntArrayArrayArray5208 == null) {
                    this.aClass104_5224 = new Class104(this.anInterface16_5220, 5126, 2, 16);
                    n72 = 24;
                }
                else {
                    this.aClass104_5224 = new Class104(this.anInterface16_5220, 5126, 3, 16);
                    n72 = 28;
                }
                if (~(this.anInt5198 & 0x7) != -1) {
                    this.aClass104_5215 = new Class104(this.anInterface16_5220, 5126, 3, n72);
                }
                final long[] array14 = new long[this.aClass98_Sub20Array5221.length];
                for (int n73 = 0; ~this.aClass98_Sub20Array5221.length < ~n73; ++n73) {
                    final Class98_Sub20 class98_Sub26 = this.aClass98_Sub20Array5221[n73];
                    array14[n73] = class98_Sub26.aLong832;
                    class98_Sub26.method1170(true, this.anInt5203);
                }
                Class46.method436(this.aClass98_Sub20Array5221, false, array14);
                if (this.aClass33_5209 != null) {
                    this.aClass33_5209.method321(119);
                }
            }
            this.anIntArrayArrayArray5210 = null;
            this.aByteArrayArray5212 = null;
            this.anIntArrayArrayArray5208 = null;
            this.anIntArrayArrayArray5195 = null;
            final int[][][] array15 = null;
            this.anIntArrayArrayArray5194 = array15;
            this.anIntArrayArrayArray5192 = array15;
            this.anIntArrayArrayArray5193 = null;
            this.aClass98_Sub20ArrayArrayArray5190 = null;
            this.aClass377_5219 = null;
            final float[][] aFloatArrayArray5218 = null;
            this.aFloatArrayArray5223 = aFloatArrayArray5218;
            this.aFloatArrayArray5214 = aFloatArrayArray5218;
            this.aFloatArrayArray5218 = aFloatArrayArray5218;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.YA()");
        }
    }
    
    @Override
    final void method3425(final int n, final int n2) {
    }
    
    @Override
    final void U(final int n, final int n2, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6, final int[] array7, final int[] array8, final int n3, final int n4, final int n5, final boolean b) {
        try {
            final d ad938 = this.aHa_Sub1_5206.aD938;
            if (array2 != null && this.anIntArrayArrayArray5193 == null) {
                this.anIntArrayArrayArray5193 = new int[super.anInt2203][super.anInt2204][];
            }
            if (array4 != null && this.anIntArrayArrayArray5208 == null) {
                this.anIntArrayArrayArray5208 = new int[super.anInt2203][super.anInt2204][];
            }
            this.anIntArrayArrayArray5192[n][n2] = array;
            this.anIntArrayArrayArray5194[n][n2] = array3;
            this.anIntArrayArrayArray5210[n][n2] = array5;
            this.anIntArrayArrayArray5195[n][n2] = array6;
            if (this.anIntArrayArrayArray5208 != null) {
                this.anIntArrayArrayArray5208[n][n2] = array4;
            }
            if (this.anIntArrayArrayArray5193 != null) {
                this.anIntArrayArrayArray5193[n][n2] = array2;
            }
            final Class98_Sub20[][] array9 = this.aClass98_Sub20ArrayArrayArray5190[n];
            final Class98_Sub20[] array10 = new Class98_Sub20[array5.length];
            array9[n2] = array10;
            final Class98_Sub20[] array11 = array10;
            for (int n6 = 0; ~array5.length < ~n6; ++n6) {
                int n7 = array7[n6];
                int n8 = array8[n6];
                if ((this.anInt5198 & 0x20) != 0x0 && ~n7 != 0x0 && ad938.method11(n7, -28755).aBoolean1825) {
                    n7 = -1;
                    n8 = 128;
                }
                final long n9 = n4 << 1267155370 | n5 << 1046159152 | n3 << -194355108 | n8 << -1435014610 | n7;
                Class98 class98;
                for (class98 = this.aClass377_5219.method3990(n9, -1); class98 != null; class98 = this.aClass377_5219.method3993(122)) {
                    final Class98_Sub20 class98_Sub20 = (Class98_Sub20)class98;
                    if (n7 == class98_Sub20.anInt3968 && class98_Sub20.aFloat3972 == n8 && class98_Sub20.anInt3971 == n3 && ~n4 == ~class98_Sub20.anInt3974 && ~n5 == ~class98_Sub20.anInt3975) {
                        break;
                    }
                }
                if (class98 != null) {
                    array11[n6] = (Class98_Sub20)class98;
                }
                else {
                    array11[n6] = new Class98_Sub20(this, n7, n8, n3, n4, n5);
                    this.aClass377_5219.method3996(array11[n6], n9, -1);
                }
            }
            if (b) {
                this.aByteArrayArray5201[n][n2] = (byte)Class41.method366(this.aByteArrayArray5201[n][n2], 1);
            }
            if (~array5.length < ~this.anInt5213) {
                this.anInt5213 = array5.length;
            }
            this.anInt5217 += array5.length;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.U(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + ((array3 != null) ? "{...}" : "null") + ',' + ((array4 != null) ? "{...}" : "null") + ',' + ((array5 != null) ? "{...}" : "null") + ',' + ((array6 != null) ? "{...}" : "null") + ',' + ((array7 != null) ? "{...}" : "null") + ',' + ((array8 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ',' + b + ')');
        }
    }
    
    static final boolean method3432(final int n, final byte b, final int n2) {
        try {
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.I(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    static final boolean method3433(final int n, final int n2, final int n3) {
        try {
            return n2 != 15849 && false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.E(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method3434(final Class207 class207, final boolean b, final int n, final int n2, final int n3, final int n4) {
        try {
            Class98_Sub15.method1144(n, n4 + 16527, b, n3, class207, n2, 0L);
            if (n4 != -16523) {
                method3427(26, 118, -88, -119, (byte)(-81), -59, -59);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.G(" + ((class207 != null) ? "{...}" : "null") + ',' + b + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final void method3426(final int n, final int n2, final int n3, final boolean[][] array, final boolean b, final int n4) {
        try {
            this.method3428(n4, -1, b, n3, n, array, 11490, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ok.C(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ',' + n4 + ')');
        }
    }
    
    static {
        s_Sub1.aClass279_5197 = new Class279("runescape", 0);
        s_Sub1.aBoolean5207 = true;
        s_Sub1.aClass58_5205 = new IncomingOpcode(101, 6);
    }
}
