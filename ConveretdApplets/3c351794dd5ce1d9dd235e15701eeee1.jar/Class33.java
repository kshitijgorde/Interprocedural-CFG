import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class33
{
    private Class46[][] aClass46ArrayArray313;
    int anInt314;
    static Class148 aClass148_315;
    static boolean aBoolean316;
    private int anInt317;
    private ha_Sub1 aHa_Sub1_318;
    private int anInt319;
    private s_Sub1 aS_Sub1_320;
    byte[] aByteArray321;
    private int anInt322;
    private int anInt323;
    
    final void method320(final boolean b, final int n, final int n2, final boolean[][] array, final byte b2, final int n3) {
        try {
            if (b2 >= 8) {
                this.aHa_Sub1_318.method1851(false, false);
                this.aHa_Sub1_318.method1875((byte)(-127), false);
                this.aHa_Sub1_318.method1834(104, -2);
                this.aHa_Sub1_318.method1896(260, 1);
                this.aHa_Sub1_318.method1870((byte)(-93), 1);
                final float n4 = 1.0f / (this.aHa_Sub1_318.anInt4318 * 128);
                if (!b) {
                    for (int n5 = 0; ~n5 > ~this.anInt317; ++n5) {
                        final int n6 = n5 << this.anInt319;
                        final int n7 = n5 + 1 << this.anInt319;
                        for (int n8 = 0; ~n8 > ~this.anInt323; ++n8) {
                            int n9 = 0;
                            final int n10 = n8 << this.anInt319;
                            final int n11 = 1 + n8 << this.anInt319;
                            final Class98_Sub22_Sub2 aClass98_Sub22_Sub2_4446 = this.aHa_Sub1_318.aClass98_Sub22_Sub2_4446;
                            aClass98_Sub22_Sub2_4446.anInt3991 = 0;
                            for (int n12 = n6; ~n7 < ~n12; ++n12) {
                                if (~(n12 - n3) <= ~(-n) && n12 - n3 <= n) {
                                    int n13 = n10 + n12 * this.aS_Sub1_320.anInt2203;
                                    for (int i = n10; i < n11; ++i) {
                                        if (-n <= -n2 + i && ~(-n2 + i) >= ~n && array[i + (-n2 + n)][n + (-n3 + n12)]) {
                                            final short[] array2 = this.aS_Sub1_320.aShortArrayArray5196[n13];
                                            if (array2 != null) {
                                                if (!this.aHa_Sub1_318.aBoolean4397) {
                                                    for (int j = 0; j < array2.length; ++j) {
                                                        ++n9;
                                                        aClass98_Sub22_Sub2_4446.method1247(array2[j] & 0xFFFF, 4);
                                                    }
                                                }
                                                else {
                                                    for (int n14 = 0; ~n14 > ~array2.length; ++n14) {
                                                        ++n9;
                                                        aClass98_Sub22_Sub2_4446.writeShort(0xFFFF & array2[n14], 1571862888);
                                                    }
                                                }
                                            }
                                        }
                                        ++n13;
                                    }
                                }
                            }
                            if (~n9 < -1) {
                                OpenGL.glMatrixMode(5890);
                                OpenGL.glLoadIdentity();
                                OpenGL.glScalef(n4, n4, 1.0f);
                                OpenGL.glTranslatef(-n8 / n4, -n5 / n4, 1.0f);
                                OpenGL.glMatrixMode(5888);
                                this.aClass46ArrayArray313[n8][n5].method438(n9, 0, 5123, aClass98_Sub22_Sub2_4446.aByteArray3992);
                            }
                        }
                    }
                }
                else {
                    for (int k = 0; k < this.anInt317; ++k) {
                        final int n15 = k << this.anInt319;
                        final int n16 = k + 1 << this.anInt319;
                    Label_0713:
                        for (int n17 = 0; this.anInt323 > n17; ++n17) {
                            final int n18 = n17 << this.anInt319;
                            for (int l = 1 + n17 << this.anInt319, n19 = n18; l > n19; ++n19) {
                                if (~(-n2 + n19) <= ~(-n) && ~n <= ~(n19 - n2)) {
                                    for (int n20 = n15; n20 < n16; ++n20) {
                                        if (~(-n3 + n20) <= ~(-n) && n >= n20 + -n3 && array[n + n19 - n2][n + (-n3 + n20)]) {
                                            OpenGL.glMatrixMode(5890);
                                            OpenGL.glLoadIdentity();
                                            OpenGL.glScalef(n4, n4, 1.0f);
                                            OpenGL.glTranslatef(-n17 / n4, -k / n4, 1.0f);
                                            OpenGL.glMatrixMode(5888);
                                            this.aClass46ArrayArray313[n17][k].method437(6401);
                                            continue Label_0713;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                OpenGL.glMatrixMode(5890);
                OpenGL.glLoadIdentity();
                OpenGL.glMatrixMode(5888);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cg.F(" + b + ',' + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + b2 + ',' + n3 + ')');
        }
    }
    
    final void method321(final int n) {
        try {
            this.aClass46ArrayArray313 = new Class46[this.anInt323][this.anInt317];
            for (int n2 = 0; ~this.anInt317 < ~n2; ++n2) {
                for (int n3 = 0; ~n3 > ~this.anInt323; ++n3) {
                    this.aClass46ArrayArray313[n3][n2] = new Class46(this.aHa_Sub1_318, this, this.aS_Sub1_320, n3, n2, this.anInt319, 1 + 128 * n3, 128 * n2 + 1);
                }
            }
            if (n < 103) {
                this.aClass46ArrayArray313 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cg.G(" + n + ')');
        }
    }
    
    final void method322(int n, int n2, final boolean b, final r r) {
        try {
            final r_Sub1 r_Sub1 = (r_Sub1)r;
            n2 += 1 + r_Sub1.anInt6324;
            n += 1 + r_Sub1.anInt6320;
            int n3 = this.anInt314 * n + n2;
            int n4 = 0;
            int anInt6326 = r_Sub1.anInt6326;
            int anInt6327 = r_Sub1.anInt6323;
            int n5 = -anInt6327 + this.anInt314;
            int n6 = 0;
            if (n <= 0) {
                final int n7 = 1 + -n;
                anInt6326 -= n7;
                n = 1;
                n3 += n7 * this.anInt314;
                n4 += anInt6327 * n7;
            }
            if (this.anInt322 <= anInt6326 + n) {
                anInt6326 -= n - -anInt6326 - (-1 - -this.anInt322);
            }
            if (n2 <= 0) {
                final int n8 = -n2 + 1;
                anInt6327 -= n8;
                n3 += n8;
                n6 += n8;
                n4 += n8;
                n2 = 1;
                n5 += n8;
            }
            if (~(n2 + anInt6327) <= ~this.anInt314) {
                final int n9 = -this.anInt314 + 1 + (n2 - -anInt6327);
                anInt6327 -= n9;
                n5 += n9;
                n6 += n9;
            }
            if (~anInt6327 < -1 && anInt6326 > 0) {
                Class187.method2637(anInt6326, n3, n4, r_Sub1.aByteArray6325, this.aByteArray321, -18305, n6, n5, anInt6327);
                this.method326(anInt6326, anInt6327, n2, n, b);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cg.E(" + n + ',' + n2 + ',' + b + ',' + ((r != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method323(final int[] array, final Object[] array2, final int n, final int i, final int n2) {
        try {
            if (n != 0) {
                Class33.aClass148_315 = null;
            }
            if (~i < ~n2) {
                final int n3 = (i + n2) / 2;
                int n4 = n2;
                final int n5 = array[n3];
                array[n3] = array[i];
                array[i] = n5;
                final Object o = array2[n3];
                array2[n3] = array2[i];
                array2[i] = o;
                final boolean b = n5 != Integer.MAX_VALUE;
                for (int n6 = n2; i > n6; ++n6) {
                    if (array[n6] < ((b ? 1 : 0) & n6) + n5) {
                        final int n7 = array[n6];
                        array[n6] = array[n4];
                        array[n4] = n7;
                        final Object o2 = array2[n6];
                        array2[n6] = array2[n4];
                        array2[n4++] = o2;
                    }
                }
                array[i] = array[n4];
                array[n4] = n5;
                array2[i] = array2[n4];
                array2[n4] = o;
                method323(array, array2, n, n4 - 1, n2);
                method323(array, array2, 0, i, n4 + 1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cg.H(" + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n + ',' + i + ',' + n2 + ')');
        }
    }
    
    final void method324(final byte b, int n, int n2, final r r) {
        try {
            if (b <= 87) {
                Class33.aBoolean316 = true;
            }
            final r_Sub1 r_Sub1 = (r_Sub1)r;
            n2 += r_Sub1.anInt6324 + 1;
            n += r_Sub1.anInt6320 + 1;
            int n3 = n2 + this.anInt314 * n;
            int n4 = 0;
            int anInt6326 = r_Sub1.anInt6326;
            int anInt6327 = r_Sub1.anInt6323;
            int n5 = this.anInt314 + -anInt6327;
            int n6 = 0;
            if (~n >= -1) {
                final int n7 = 1 - n;
                n4 += anInt6327 * n7;
                n = 1;
                anInt6326 -= n7;
                n3 += this.anInt314 * n7;
            }
            if (~(anInt6326 + n) <= ~this.anInt322) {
                anInt6326 -= -this.anInt322 + anInt6326 + (n + 1);
            }
            if (n2 <= 0) {
                final int n8 = -n2 + 1;
                n2 = 1;
                n6 += n8;
                n3 += n8;
                anInt6327 -= n8;
                n4 += n8;
                n5 += n8;
            }
            if (this.anInt314 <= n2 - -anInt6327) {
                final int n9 = anInt6327 + (n2 - (-1 + this.anInt314));
                anInt6327 -= n9;
                n5 += n9;
                n6 += n9;
            }
            if (~anInt6327 < -1 && anInt6326 > 0) {
                Class76_Sub2.method751(n6, this.aByteArray321, anInt6326, r_Sub1.aByteArray6325, n4, n5, n3, anInt6327, 0);
                this.method326(anInt6326, anInt6327, n2, n, false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cg.C(" + b + ',' + n + ',' + n2 + ',' + ((r != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class33(final ha_Sub1 aHa_Sub1_318, final s_Sub1 as_Sub1_320) {
        try {
            this.aHa_Sub1_318 = aHa_Sub1_318;
            this.aS_Sub1_320 = as_Sub1_320;
            this.anInt314 = (this.aS_Sub1_320.anInt2206 * this.aS_Sub1_320.anInt2203 >> this.aHa_Sub1_318.anInt4319) + 2;
            this.anInt322 = 2 + (this.aS_Sub1_320.anInt2204 * this.aS_Sub1_320.anInt2206 >> this.aHa_Sub1_318.anInt4319);
            this.anInt319 = this.aHa_Sub1_318.anInt4319 + (7 + -this.aS_Sub1_320.anInt2200);
            this.aByteArray321 = new byte[this.anInt314 * this.anInt322];
            this.anInt323 = this.aS_Sub1_320.anInt2203 >> this.anInt319;
            this.anInt317 = this.aS_Sub1_320.anInt2204 >> this.anInt319;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cg.<init>(" + ((aHa_Sub1_318 != null) ? "{...}" : "null") + ',' + ((as_Sub1_320 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final boolean method325(final r r, final int n, int n2, int n3) {
        try {
            final r_Sub1 r_Sub1 = (r_Sub1)r;
            n2 += 1 + r_Sub1.anInt6324;
            n3 += r_Sub1.anInt6320 + 1;
            int n4 = n3 * this.anInt314 + n2;
            int anInt6326 = r_Sub1.anInt6326;
            int anInt6327 = r_Sub1.anInt6323;
            int n5 = -anInt6327 + this.anInt314;
            if (n3 <= 0) {
                final int n6 = 1 - n3;
                n3 = 1;
                n4 += this.anInt314 * n6;
                anInt6326 -= n6;
            }
            if (~(n3 + anInt6326) <= ~this.anInt322) {
                anInt6326 -= anInt6326 + n3 + (1 + -this.anInt322);
            }
            if (n2 <= 0) {
                final int n7 = 1 - n2;
                anInt6327 -= n7;
                n4 += n7;
                n5 += n7;
                n2 = 1;
            }
            if (~(n2 - -anInt6327) <= ~this.anInt314) {
                final int n8 = anInt6327 + (n2 + 1 + -this.anInt314);
                n5 += n8;
                anInt6327 -= n8;
            }
            if (~anInt6327 >= -1 || ~anInt6326 >= -1) {
                return false;
            }
            final int n9 = 8;
            if (n > -109) {
                this.aS_Sub1_320 = null;
            }
            return Class130.method2230(n9, n4, 1, n5 + this.anInt314 * (-1 + n9), anInt6327, this.aByteArray321, anInt6326);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cg.B(" + ((r != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    private final void method326(final int n, final int n2, final int n3, final int n4, final boolean b) {
        try {
            if (!b && this.aClass46ArrayArray313 != null) {
                final int n5 = -1 + n3 >> -1633322617;
                final int n6 = n2 + n3 - 2 >> -1182142265;
                final int n7 = -1 + n4 >> -1931383033;
                final int n8 = -1 + (n4 - (1 + -n)) >> 950325127;
                for (int n9 = n5; ~n6 <= ~n9; ++n9) {
                    final Class46[] array = this.aClass46ArrayArray313[n9];
                    for (int n10 = n7; ~n10 >= ~n8; ++n10) {
                        array[n10].aBoolean392 = true;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cg.D(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    public static void method327(final byte b) {
        try {
            Class33.aClass148_315 = null;
            if (b > -124) {
                Class33.aBoolean316 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cg.A(" + b + ')');
        }
    }
    
    static {
        Class33.aClass148_315 = new Class148();
        Class33.aBoolean316 = false;
    }
}
