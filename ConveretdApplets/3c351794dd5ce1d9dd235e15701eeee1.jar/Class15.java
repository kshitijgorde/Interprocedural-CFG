import jaclib.memory.Buffer;
import jaclib.memory.Stream;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class15
{
    static boolean aBoolean169;
    static int anInt170;
    private ha_Sub3 aHa_Sub3_171;
    static int anInt172;
    private s_Sub2 aS_Sub2_173;
    private int anInt174;
    private int anInt175;
    byte[] aByteArray176;
    static OutgoingOpcode aClass171_177;
    private int anInt178;
    private int anInt179;
    int anInt180;
    private Class190[][] aClass190ArrayArray181;
    static int[] anIntArray182;
    static OutgoingOpcode aClass171_183;
    static IncomingOpcode aClass58_184;
    static volatile int anInt185;
    static boolean aBoolean186;
    
    final void method227(final int n, final boolean b, final boolean[][] array, final int n2, final int n3, final int n4) {
        try {
            this.aHa_Sub3_171.method1979(false, -89);
            this.aHa_Sub3_171.method1997(0, false);
            this.aHa_Sub3_171.method2001(1, 106);
            this.aHa_Sub3_171.method2015(1, (byte)76);
            this.aHa_Sub3_171.method2039(false, 0, -2, false);
            if (n4 <= 15) {
                Class15.anInt172 = -11;
            }
            final float n5 = 1.0f / (this.aHa_Sub3_171.anInt4553 * 128);
            if (b) {
                for (int n6 = 0; ~this.anInt175 < ~n6; ++n6) {
                    final int n7 = n6 << this.anInt174;
                    final int n8 = n6 + 1 << this.anInt174;
                Label_0336:
                    for (int i = 0; i < this.anInt178; ++i) {
                        if (this.aClass190ArrayArray181[i][n6] != null) {
                            final int n9 = i << this.anInt174;
                            for (int n10 = 1 + i << this.anInt174, n11 = n9; ~n10 < ~n11; ++n11) {
                                if (-n3 <= -n + n11 && n3 >= n11 - n) {
                                    for (int n12 = n7; ~n12 > ~n8; ++n12) {
                                        if (-n2 + n12 >= -n3 && n3 >= n12 - n2 && array[n3 + (-n + n11)][n3 + (n12 - n2)]) {
                                            final Class111_Sub3 method1957 = this.aHa_Sub3_171.method1957((byte)(-89));
                                            method1957.method2137(n5, (byte)(-113), n5, 1.0f);
                                            method1957.method2106(-i, -n6, 0);
                                            this.aHa_Sub3_171.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)81);
                                            this.aClass190ArrayArray181[i][n6].method2643(30925);
                                            continue Label_0336;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else {
                for (int n13 = 0; ~n13 > ~this.anInt175; ++n13) {
                    final int n14 = n13 << this.anInt174;
                    final int j = 1 + n13 << this.anInt174;
                    for (int n15 = 0; ~n15 > ~this.anInt178; ++n15) {
                        final Class190 class190 = this.aClass190ArrayArray181[n15][n13];
                        if (class190 != null) {
                            final Interface2_Impl2 method1958 = this.aHa_Sub3_171.method1963(61, 3 * class190.anInt1460);
                            final Buffer method1959 = method1958.method78(true, -116);
                            if (method1959 != null) {
                                final Stream method1960 = this.aHa_Sub3_171.method2043(24022, method1959);
                                int n16 = 0;
                                final int n17 = n15 << this.anInt174;
                                final int n18 = n15 + 1 << this.anInt174;
                                for (int n19 = n14; j > n19; ++n19) {
                                    if (-n3 <= n19 + -n2 && -n2 + n19 <= n3) {
                                        int n20 = this.aS_Sub2_173.anInt2203 * n19 + n17;
                                        for (int n21 = n17; ~n18 < ~n21; ++n21) {
                                            if (~(-n + n21) <= ~(-n3) && n3 >= -n + n21 && array[n3 + (-n + n21)][n19 - n2 - -n3]) {
                                                final short[] array2 = this.aS_Sub2_173.aShortArrayArray5230[n20];
                                                if (array2 != null) {
                                                    if (!Stream.a()) {
                                                        for (int n22 = 0; array2.length > n22; ++n22) {
                                                            ++n16;
                                                            method1960.d(array2[n22] & 0xFFFF);
                                                        }
                                                    }
                                                    else {
                                                        for (int k = 0; k < array2.length; ++k) {
                                                            ++n16;
                                                            method1960.c(0xFFFF & array2[k]);
                                                        }
                                                    }
                                                }
                                            }
                                            ++n20;
                                        }
                                    }
                                }
                                method1960.c();
                                if (method1958.method79((byte)(-99)) && ~n16 < -1) {
                                    final Class111_Sub3 method1961 = this.aHa_Sub3_171.method1957((byte)(-78));
                                    method1961.method2137(n5, (byte)(-124), n5, 1.0f);
                                    method1961.method2106(-n15, -n13, 0);
                                    this.aHa_Sub3_171.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)94);
                                    class190.method2645(-18732, method1958, n16 / 3);
                                }
                            }
                        }
                    }
                }
            }
            this.aHa_Sub3_171.method1985(2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bba.G(" + n + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static final boolean method228(final int n, final int n2, final int n3) {
        try {
            if (n2 != 1) {
                method233(-41);
            }
            return s_Sub1.method3432(n3, (byte)(-49), n) || r_Sub2.method1655(n3, (byte)(-124), n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bba.F(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final void method229(final byte b) {
        try {
            this.aClass190ArrayArray181 = new Class190[this.anInt178][this.anInt175];
            if (b >= -9) {
                this.anInt174 = 95;
            }
            for (int n = 0; ~n > ~this.anInt175; ++n) {
                for (int i = 0; i < this.anInt178; ++i) {
                    this.aClass190ArrayArray181[i][n] = new Class190(this.aHa_Sub3_171, this, this.aS_Sub2_173, i, n, this.anInt174, 1 + i * 128, 1 + n * 128);
                    if (~this.aClass190ArrayArray181[i][n].anInt1460 == -1) {
                        this.aClass190ArrayArray181[i][n] = null;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bba.E(" + b + ')');
        }
    }
    
    private final void method230(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            if (this.aClass190ArrayArray181 != null) {
                final int n6 = n3 - 1 >> 1264444711;
                final int n7 = n4 + (n3 - 1 - 1) >> 1674907143;
                final int n8 = -1 + n2 >> -1840220025;
                if (n != -30590) {
                    this.method227(80, true, null, -46, -43, -39);
                }
                final int n9 = -1 + n2 + (-1 - -n5) >> -724193337;
                for (int n10 = n6; ~n7 <= ~n10; ++n10) {
                    final Class190[] array = this.aClass190ArrayArray181[n10];
                    for (int i = n8; i <= n9; ++i) {
                        if (array[i] != null) {
                            array[i].aBoolean1470 = true;
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bba.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    final void method231(final r r, int n, final byte b, int n2) {
        try {
            if (b <= 46) {
                Class15.anInt172 = -82;
            }
            final r_Sub2 r_Sub2 = (r_Sub2)r;
            n += r_Sub2.anInt6329 + 1;
            n2 += 1 + r_Sub2.anInt6327;
            int n3 = n2 * this.anInt180 + n;
            int n4 = 0;
            int anInt6331 = r_Sub2.anInt6331;
            int anInt6332 = r_Sub2.anInt6328;
            int n5 = -anInt6332 + this.anInt180;
            int n6 = 0;
            if (~n2 >= -1) {
                final int n7 = -n2 + 1;
                n2 = 1;
                anInt6331 -= n7;
                n4 += anInt6332 * n7;
                n3 += n7 * this.anInt180;
            }
            if (~this.anInt179 >= ~(anInt6331 + n2)) {
                anInt6331 -= -this.anInt179 + anInt6331 + (n2 + 1);
            }
            if (~n >= -1) {
                final int n8 = 1 + -n;
                anInt6332 -= n8;
                n3 += n8;
                n4 += n8;
                n6 += n8;
                n = 1;
                n5 += n8;
            }
            if (~this.anInt180 >= ~(anInt6332 + n)) {
                final int n9 = 1 + (anInt6332 + n - this.anInt180);
                n6 += n9;
                n5 += n9;
                anInt6332 -= n9;
            }
            if (~anInt6332 < -1 && ~anInt6331 < -1) {
                r_Sub2.method1657(n4, this.aByteArray176, anInt6331, 1230470320, n5, anInt6332, n6, r_Sub2.aByteArray6332, n3);
                this.method230(-30590, n2, n, anInt6332, anInt6331);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bba.H(" + ((r != null) ? "{...}" : "null") + ',' + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    final void method232(final r r, final byte b, int n, int n2) {
        try {
            final r_Sub2 r_Sub2 = (r_Sub2)r;
            n2 += r_Sub2.anInt6327 + 1;
            n += 1 + r_Sub2.anInt6329;
            int n3 = this.anInt180 * n2 + n;
            int n4 = 0;
            int anInt6331 = r_Sub2.anInt6331;
            if (b > -84) {
                method228(23, 21, -126);
            }
            int anInt6332 = r_Sub2.anInt6328;
            int n5 = -anInt6332 + this.anInt180;
            if (n2 <= 0) {
                final int n6 = 1 - n2;
                n2 = 1;
                n4 += n6 * anInt6332;
                n3 += this.anInt180 * n6;
                anInt6331 -= n6;
            }
            int n7 = 0;
            if (~(n2 - -anInt6331) <= ~this.anInt179) {
                anInt6331 -= -this.anInt179 + (anInt6331 + (n2 + 1));
            }
            if (~n >= -1) {
                final int n8 = -n + 1;
                n3 += n8;
                anInt6332 -= n8;
                n = 1;
                n7 += n8;
                n5 += n8;
                n4 += n8;
            }
            if (~this.anInt180 >= ~(anInt6332 + n)) {
                final int n9 = -this.anInt180 + (1 + (anInt6332 + n));
                anInt6332 -= n9;
                n5 += n9;
                n7 += n9;
            }
            if (~anInt6332 < -1 && ~anInt6331 < -1) {
                Class323.method3676(anInt6331, n5, n7, anInt6332, this.aByteArray176, false, n4, r_Sub2.aByteArray6332, n3);
                this.method230(-30590, n2, n, anInt6332, anInt6331);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bba.C(" + ((r != null) ? "{...}" : "null") + ',' + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    public static void method233(final int n) {
        try {
            Class15.aClass58_184 = null;
            Class15.aClass171_183 = null;
            if (n != 8) {
                Class15.anInt185 = -128;
            }
            Class15.anIntArray182 = null;
            Class15.aClass171_177 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bba.A(" + n + ')');
        }
    }
    
    Class15(final ha_Sub3 aHa_Sub3_171, final s_Sub2 as_Sub2_173) {
        try {
            this.aS_Sub2_173 = as_Sub2_173;
            this.aHa_Sub3_171 = aHa_Sub3_171;
            this.anInt180 = 2 - -(this.aS_Sub2_173.anInt2206 * this.aS_Sub2_173.anInt2203 >> this.aHa_Sub3_171.anInt4573);
            this.anInt179 = (this.aS_Sub2_173.anInt2204 * this.aS_Sub2_173.anInt2206 >> this.aHa_Sub3_171.anInt4573) + 2;
            this.anInt174 = -this.aS_Sub2_173.anInt2200 + 7 - -this.aHa_Sub3_171.anInt4573;
            this.aByteArray176 = new byte[this.anInt179 * this.anInt180];
            this.anInt178 = this.aS_Sub2_173.anInt2203 >> this.anInt174;
            this.anInt175 = this.aS_Sub2_173.anInt2204 >> this.anInt174;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bba.<init>(" + ((aHa_Sub3_171 != null) ? "{...}" : "null") + ',' + ((as_Sub2_173 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final boolean method234(int n, final r r, final int n2, int n3) {
        try {
            final r_Sub2 r_Sub2 = (r_Sub2)r;
            n3 += 1 + r_Sub2.anInt6329;
            n += 1 + r_Sub2.anInt6327;
            int n4 = this.anInt180 * n + n3;
            int anInt6331 = r_Sub2.anInt6331;
            int anInt6332 = r_Sub2.anInt6328;
            if (~n >= -1) {
                final int n5 = 1 + -n;
                n4 += n5 * this.anInt180;
                n = 1;
                anInt6331 -= n5;
            }
            int n6 = -anInt6332 + this.anInt180;
            if (n - -anInt6331 >= this.anInt179) {
                anInt6331 -= 1 + n + anInt6331 - this.anInt179;
            }
            if (~n3 >= -1) {
                final int n7 = 1 - n3;
                n3 = 1;
                n6 += n7;
                anInt6332 -= n7;
                n4 += n7;
            }
            if (this.anInt180 <= anInt6332 + n3) {
                final int n8 = 1 + (anInt6332 + n3) + -this.anInt180;
                n6 += n8;
                anInt6332 -= n8;
            }
            return anInt6332 > 0 && ~anInt6331 < -1 && Class121.method2198(n4, n6 + (-1 + n2) * this.anInt180, anInt6332, this.aByteArray176, n2, 14849, anInt6331);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bba.D(" + n + ',' + ((r != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static {
        Class15.aBoolean169 = false;
        Class15.aClass171_177 = new OutgoingOpcode(39, 8);
        Class15.aClass171_183 = new OutgoingOpcode(32, 0);
        Class15.aClass58_184 = new IncomingOpcode(103, 7);
        Class15.anInt185 = -1;
        Class15.aBoolean186 = false;
    }
}
