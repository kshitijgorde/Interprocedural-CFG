import jaclib.memory.Buffer;
import jaggl.OpenGL;
import jaclib.memory.Stream;
import jaclib.memory.heap.NativeHeapBuffer;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub20 extends Class98
{
    private Class156_Sub1 aClass156_Sub1_3963;
    private NativeHeapBuffer aNativeHeapBuffer3964;
    private s_Sub1 aS_Sub1_3965;
    private Class104 aClass104_3966;
    static Class53_Sub1[] aClass53_Sub1Array3967;
    int anInt3968;
    private ha_Sub1 aHa_Sub1_3969;
    private Stream aStream3970;
    int anInt3971;
    float aFloat3972;
    private int[] anIntArray3973;
    int anInt3974;
    int anInt3975;
    
    public static void method1166(final int n) {
        try {
            if (n == -22268) {
                Class98_Sub20.aClass53_Sub1Array3967 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iw.B(" + n + ')');
        }
    }
    
    final void method1167(final int n, final boolean b) {
        try {
            if (!b) {
                method1171(-17);
            }
            this.aStream3970.b(3 + n * 4);
            this.aStream3970.e(-1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iw.H(" + n + ',' + b + ')');
        }
    }
    
    final void method1168(final int n, final byte b, final float n2, int n3, final int n4) {
        try {
            if (b > 64) {
                if (~this.anInt3968 != 0x0) {
                    final Class238 method11 = this.aHa_Sub1_3969.aD938.method11(this.anInt3968, -28755);
                    final int n5 = 0xFF & method11.aByte1830;
                    if (~n5 != -1 && method11.aByte1820 != 4) {
                        int n6;
                        if (n >= 0) {
                            if (n > 127) {
                                n6 = 16777215;
                            }
                            else {
                                n6 = 131586 * n;
                            }
                        }
                        else {
                            n6 = 0;
                        }
                        if (~n5 == 0xFFFFFEFF) {
                            n3 = n6;
                        }
                        else {
                            final int n7 = n5;
                            final int n8 = 256 - n5;
                            n3 = (0xFF0000 & n8 * (n3 & 0xFF00) + (n6 & 0xFF00) * n7) + ((0xFF00FF & n3) * n8 + n7 * (0xFF00FF & n6) & 0xFF00FF00) >> -191668280;
                        }
                    }
                    int n9 = 0xFF & method11.aByte1829;
                    if (~n9 != -1) {
                        n9 += 256;
                        int n10 = (n3 >> -1266724752 & 0xFF) * n9;
                        if (~n10 < -65536) {
                            n10 = 65535;
                        }
                        int n11 = n9 * ((n3 & 0xFF00) >> 1126487880);
                        if (~n11 < -65536) {
                            n11 = 65535;
                        }
                        int n12 = n9 * (n3 & 0xFF);
                        if (n12 > 65535) {
                            n12 = 65535;
                        }
                        n3 = (n12 >> 2022380040) + (n11 & 0xFF00) + (0xFF00CA & n10 << -1067840792);
                    }
                }
                this.aStream3970.b(4 * n4);
                if (n2 != 1.0f) {
                    final int n13 = n3 >> -523059280 & 0xFF;
                    final int n14 = (n3 & 0xFF54) >> -1129896280;
                    int n15 = (int)(n13 * n2);
                    final int n16 = 0xFF & n3;
                    int n17 = (int)(n14 * n2);
                    if (n15 < 0) {
                        n15 = 0;
                    }
                    else if (n15 > 255) {
                        n15 = 255;
                    }
                    int n18 = (int)(n16 * n2);
                    if (~n17 <= -1) {
                        if (~n17 < -256) {
                            n17 = 255;
                        }
                    }
                    else {
                        n17 = 0;
                    }
                    if (~n18 <= -1) {
                        if (n18 > 255) {
                            n18 = 255;
                        }
                    }
                    else {
                        n18 = 0;
                    }
                    n3 = (n17 << 1129793576 | n15 << 1969555312 | n18);
                }
                this.aStream3970.e((byte)(n3 >> 367830512));
                this.aStream3970.e((byte)(n3 >> 1207926120));
                this.aStream3970.e((byte)n3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iw.F(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    final void method1169(final int[] array, final byte b, final int i) {
        try {
            if (b == 98) {
                int n = 0;
                final Class98_Sub22_Sub2 aClass98_Sub22_Sub2_4446 = this.aHa_Sub1_3969.aClass98_Sub22_Sub2_4446;
                aClass98_Sub22_Sub2_4446.anInt3991 = 0;
                if (!this.aHa_Sub1_3969.aBoolean4397) {
                    for (int n2 = 0; i > n2; ++n2) {
                        final int n3 = array[n2];
                        final int n4 = this.anIntArray3973[n3];
                        final short[] array2 = this.aS_Sub1_3965.aShortArrayArray5196[n3];
                        if (n4 != 0 && array2 != null) {
                            int n5 = 0;
                            int n6 = 0;
                            while (~n6 > ~array2.length) {
                                if (~(1 << n5++ & n4) != -1) {
                                    aClass98_Sub22_Sub2_4446.method1247(array2[n6++] & 0xFFFF, 4);
                                    ++n;
                                    ++n;
                                    aClass98_Sub22_Sub2_4446.method1247(array2[n6++] & 0xFFFF, 4);
                                    aClass98_Sub22_Sub2_4446.method1247(array2[n6++] & 0xFFFF, 4);
                                    ++n;
                                }
                                else {
                                    n6 += 3;
                                }
                            }
                        }
                    }
                }
                else {
                    for (int n7 = 0; ~i < ~n7; ++n7) {
                        final int n8 = array[n7];
                        final short[] array3 = this.aS_Sub1_3965.aShortArrayArray5196[n8];
                        final int n9 = this.anIntArray3973[n8];
                        if (~n9 != -1 && array3 != null) {
                            int n10 = 0;
                            int n11 = 0;
                            while (array3.length > n11) {
                                if (~(1 << n10++ & n9) != -1) {
                                    ++n;
                                    aClass98_Sub22_Sub2_4446.writeShort(0xFFFF & array3[n11++], b + 1571862790);
                                    ++n;
                                    aClass98_Sub22_Sub2_4446.writeShort(array3[n11++] & 0xFFFF, b + 1571862790);
                                    ++n;
                                    aClass98_Sub22_Sub2_4446.writeShort(0xFFFF & array3[n11++], 1571862888);
                                }
                                else {
                                    n11 += 3;
                                }
                            }
                        }
                    }
                }
                if (n > 0) {
                    this.aClass156_Sub1_3963.method20((byte)(-47), aClass98_Sub22_Sub2_4446.aByteArray3992, aClass98_Sub22_Sub2_4446.anInt3991, 5123);
                    this.aHa_Sub1_3969.method1868(this.aClass104_3966, this.aS_Sub1_3965.aClass104_5215, this.aS_Sub1_3965.aClass104_5224, this.aS_Sub1_3965.aClass104_5222, b - 98);
                    this.aHa_Sub1_3969.method1897(this.anInt3968, ~(0x8 & this.aS_Sub1_3965.anInt5198) != -1, ~(this.aS_Sub1_3965.anInt5198 & 0x7) != -1, (byte)(-70));
                    if (this.aHa_Sub1_3969.aBoolean4366) {
                        this.aHa_Sub1_3969.EA(Integer.MAX_VALUE, this.anInt3971, this.anInt3974, this.anInt3975);
                    }
                    OpenGL.glMatrixMode(5890);
                    OpenGL.glPushMatrix();
                    OpenGL.glScalef(1.0f / this.aFloat3972, 1.0f / this.aFloat3972, 1.0f);
                    OpenGL.glMatrixMode(5888);
                    this.aHa_Sub1_3969.method1868(this.aClass104_3966, this.aS_Sub1_3965.aClass104_5215, this.aS_Sub1_3965.aClass104_5224, this.aS_Sub1_3965.aClass104_5222, 0);
                    this.aHa_Sub1_3969.method1865(n, 4, this.aClass156_Sub1_3963, false, 0);
                    OpenGL.glMatrixMode(5890);
                    OpenGL.glPopMatrix();
                    OpenGL.glMatrixMode(5888);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iw.I(" + ((array != null) ? "{...}" : "null") + ',' + b + ',' + i + ')');
        }
    }
    
    final void method1170(final boolean b, final int n) {
        try {
            if (b) {
                this.aStream3970.c();
                this.aClass104_3966 = new Class104(this.aHa_Sub1_3969.method1843(4, (byte)78, this.aNativeHeapBuffer3964, false, n * 4), 5121, 4, 0);
                this.aNativeHeapBuffer3964 = null;
                this.aStream3970 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iw.C(" + b + ',' + n + ')');
        }
    }
    
    static final void method1171(final int anInt2849) {
        try {
            Class246_Sub3_Sub3.aClass254_6152 = new Class254(8);
            Class340.anInt2849 = anInt2849;
            for (Class246_Sub5 class246_Sub5 = (Class246_Sub5)Class267.aClass218_2002.method2803((byte)15); class246_Sub5 != null; class246_Sub5 = (Class246_Sub5)Class267.aClass218_2002.method2809(false)) {
                class246_Sub5.method3129();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iw.E(" + anInt2849 + ')');
        }
    }
    
    static final boolean method1172(final byte b, final int n, final int n2) {
        try {
            if (b != 1) {
                method1166(46);
            }
            return ((n & 0x60000) != 0x0 | Class98_Sub10_Sub9.method1033(n, n2, 16)) || Class276.method3286((byte)115, n, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iw.D(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    static final void method1173(final int n) {
        try {
            if (Class146_Sub3.method2408((byte)57)) {
                if (Class98_Sub46_Sub20.aStringArray6073 == null) {
                    Class264.method3222((byte)(-43));
                }
                Class98_Sub17_Sub1.aBoolean5778 = true;
                Class98_Sub10_Sub15.anInt5618 = 0;
            }
            if (n != 0) {
                Class98_Sub20.aClass53_Sub1Array3967 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iw.G(" + n + ')');
        }
    }
    
    final void method1174(final int n, final byte b, final int n2, final int n3) {
        try {
            if (b > 117) {
                this.anIntArray3973[n3 + this.aS_Sub1_3965.anInt2203 * n2] = Class41.method366(this.anIntArray3973[n3 + this.aS_Sub1_3965.anInt2203 * n2], 1 << n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iw.A(" + n + ',' + b + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final void method1175(final int n, final byte b) {
        try {
            this.aNativeHeapBuffer3964 = this.aHa_Sub1_3969.aNativeHeap4323.a(4 * n, true);
            if (b == -64) {
                this.aStream3970 = new Stream(this.aNativeHeapBuffer3964);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iw.J(" + n + ',' + b + ')');
        }
    }
    
    Class98_Sub20(final s_Sub1 as_Sub1_3965, final int anInt3968, final int n, final int anInt3969, final int anInt3970, final int anInt3971) {
        try {
            this.aS_Sub1_3965 = as_Sub1_3965;
            this.aHa_Sub1_3969 = this.aS_Sub1_3965.aHa_Sub1_5206;
            this.anInt3974 = anInt3970;
            this.anIntArray3973 = new int[this.aS_Sub1_3965.anInt2203 * this.aS_Sub1_3965.anInt2204];
            this.anInt3968 = anInt3968;
            this.anInt3975 = anInt3971;
            this.anInt3971 = anInt3969;
            this.aFloat3972 = n;
            this.aClass156_Sub1_3963 = new Class156_Sub1(this.aHa_Sub1_3969, 5123, null, 1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "iw.<init>(" + ((as_Sub1_3965 != null) ? "{...}" : "null") + ',' + anInt3968 + ',' + n + ',' + anInt3969 + ',' + anInt3970 + ',' + anInt3971 + ')');
        }
    }
}
