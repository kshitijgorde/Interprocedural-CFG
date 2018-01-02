// 
// Decompiled by Procyon v0.5.30
// 

final class Class183
{
    private Class207 aClass207_1441;
    private Class79 aClass79_1442;
    private Class79 aClass79_1443;
    
    final void method2618(final boolean b) {
        try {
            synchronized (this.aClass79_1442) {
                this.aClass79_1442.method794(109);
            }
            synchronized (this.aClass79_1443) {
                if (!b) {
                    this.method2624(-55, 58);
                }
                this.aClass79_1443.method794(86);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "me.B(" + b + ')');
        }
    }
    
    final void method2619(final int n) {
        try {
            synchronized (this.aClass79_1442) {
                this.aClass79_1442.method806((byte)18);
            }
            synchronized (this.aClass79_1443) {
                this.aClass79_1443.method806((byte)109);
                if (n != -2118) {
                    this.aClass79_1443 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "me.G(" + n + ')');
        }
    }
    
    static final void method2620(final int n) {
        try {
            if (Class368.anInt3128 != -1 && ~Class53_Sub1.anInt3636 != 0x0) {
                Class54.anInt3394 += Class246.anInt1872 + (Class54.anInt3394 * (-Class246.anInt1872 + Class98_Sub10_Sub32.anInt5718) >> 1233746160);
                if (~Class54.anInt3394 <= -65536) {
                    if (!Class319.aBoolean2700) {
                        Class187.aBoolean1451 = true;
                    }
                    else {
                        Class187.aBoolean1451 = false;
                    }
                    Class54.anInt3394 = 65535;
                    Class319.aBoolean2700 = true;
                }
                else {
                    Class187.aBoolean1451 = false;
                    Class319.aBoolean2700 = false;
                }
                final float n2 = Class54.anInt3394 / 65535.0f;
                final float[] array = new float[3];
                final int n3 = 2 * Class50.anInt418;
                for (int i = 0; i < 3; ++i) {
                    final int n4 = 3 * InputStream_Sub1.anIntArrayArrayArray27[Class368.anInt3128][n3][i];
                    final int n5 = 3 * InputStream_Sub1.anIntArrayArrayArray27[Class368.anInt3128][n3 + 1][i];
                    final int n6 = 3 * (InputStream_Sub1.anIntArrayArrayArray27[Class368.anInt3128][n3 + 2][i] - (-InputStream_Sub1.anIntArrayArrayArray27[Class368.anInt3128][n3 + 2][i] + InputStream_Sub1.anIntArrayArrayArray27[Class368.anInt3128][n3 + 3][i]));
                    final int n7 = InputStream_Sub1.anIntArrayArrayArray27[Class368.anInt3128][n3][i];
                    array[i] = (n5 + -n4 + n2 * (n6 + (n4 - 2 * n5) + n2 * (n5 + -n7 + (InputStream_Sub1.anIntArrayArrayArray27[Class368.anInt3128][n3 + 2][i] - n6)))) * n2 + n7;
                }
                Class79.anInt601 = -1 * (int)array[1];
                Class134.anInt3461 = (int)array[2] - 512 * aa_Sub2.anInt3562;
                Class98_Sub46_Sub10.anInt6020 = -(Class272.anInt2038 * 512) + (int)array[0];
                final float[] array2 = new float[3];
                final int n8 = 2 * ha.anInt943;
                for (int j = 0; j < 3; ++j) {
                    final int n9 = InputStream_Sub1.anIntArrayArrayArray27[Class53_Sub1.anInt3636][n8][j] * 3;
                    final int n10 = 3 * InputStream_Sub1.anIntArrayArrayArray27[Class53_Sub1.anInt3636][n8 + 1][j];
                    final int n11 = 3 * (InputStream_Sub1.anIntArrayArrayArray27[Class53_Sub1.anInt3636][n8 + 2][j] - (InputStream_Sub1.anIntArrayArrayArray27[Class53_Sub1.anInt3636][n8 + 3][j] + -InputStream_Sub1.anIntArrayArrayArray27[Class53_Sub1.anInt3636][n8 + 2][j]));
                    final int n12 = InputStream_Sub1.anIntArrayArrayArray27[Class53_Sub1.anInt3636][n8][j];
                    array2[j] = n12 + (n10 - n9 + (n2 * (-n11 + (n10 + InputStream_Sub1.anIntArrayArrayArray27[Class53_Sub1.anInt3636][2 + n8][j] + -n12)) + (n11 + (-(n10 * 2) + n9))) * n2) * n2;
                }
                final float n13 = array2[0] - array[n];
                final float n14 = (-array[1] + array2[1]) * -1.0f;
                final float n15 = array2[2] - array[2];
                Class246_Sub3_Sub4_Sub2.anInt6357 = ((int)(2607.5945876176133 * Math.atan2(n14, Math.sqrt(n15 * n15 + n13 * n13))) & 0x3FFF);
                Class186.anInt3424 = (0x3FFF & (int)(2607.5945876176133 * -Math.atan2(n13, n15)));
                Class308.anInt2584 = InputStream_Sub1.anIntArrayArrayArray27[Class368.anInt3128][n3][3] - -((-InputStream_Sub1.anIntArrayArrayArray27[Class368.anInt3128][n3][3] + InputStream_Sub1.anIntArrayArrayArray27[Class368.anInt3128][2 + n3][3]) * Class54.anInt3394 >> -1587239088);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "me.C(" + n + ')');
        }
    }
    
    final void method2621(final int n, final int n2) {
        try {
            synchronized (this.aClass79_1442) {
                this.aClass79_1442.method800((byte)62, n);
            }
            if (n2 == 3) {
                synchronized (this.aClass79_1443) {
                    this.aClass79_1443.method800((byte)62, n);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "me.F(" + n + ',' + n2 + ')');
        }
    }
    
    static final byte[] method2622(final byte[] array, final byte b, final int n) {
        try {
            final byte[] array2 = new byte[n];
            Class236.method2894(array, 0, array2, 0, n);
            if (b > -100) {
                method2622(null, (byte)61, 124);
            }
            return array2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "me.D(" + ((array != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    final Class97 method2623(final int anInt826, final int n) {
        try {
            final Class97 class97;
            synchronized (this.aClass79_1442) {
                class97 = (Class97)this.aClass79_1442.method802(-128, anInt826);
            }
            if (class97 != null) {
                return class97;
            }
            final byte[] method2745;
            synchronized (this.aClass207_1441) {
                method2745 = this.aClass207_1441.method2745(Class98_Sub10_Sub32.method1096(anInt826, 127), Class299_Sub2.method3527(anInt826, n ^ 0x2DEA8938), false);
            }
            final Class97 class98 = new Class97();
            if (n != 16383) {
                return null;
            }
            class98.aClass183_824 = this;
            class98.anInt826 = anInt826;
            if (method2745 != null) {
                class98.method933(new Class98_Sub22(method2745), -125);
            }
            class98.method938(n - 16508);
            synchronized (this.aClass79_1442) {
                this.aClass79_1442.method805(anInt826, class98, (byte)(-80));
            }
            return class98;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "me.E(" + anInt826 + ',' + n + ')');
        }
    }
    
    final Class98_Sub46_Sub16 method2624(final int n, final int n2) {
        try {
            if (n != 2) {
                this.method2623(20, 104);
            }
            Class98_Sub46_Sub16 class98_Sub46_Sub16;
            synchronized (this.aClass79_1443) {
                class98_Sub46_Sub16 = (Class98_Sub46_Sub16)this.aClass79_1443.method802(-120, n2);
                if (class98_Sub46_Sub16 == null) {
                    class98_Sub46_Sub16 = new Class98_Sub46_Sub16(n2);
                    this.aClass79_1443.method805(n2, class98_Sub46_Sub16, (byte)(-80));
                }
                if (!class98_Sub46_Sub16.method1614((byte)66)) {
                    return null;
                }
            }
            return class98_Sub46_Sub16;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "me.H(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method2625(final boolean b, final int n) {
        try {
            if (b) {
                method2620(104);
            }
            Class185.method2628(n, -105, 4).method1621(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "me.A(" + b + ',' + n + ')');
        }
    }
    
    Class183(final Class279 class279, final int n, final Class207 aClass207_1441, final Class207 class280, final Class207 class281) {
        this.aClass79_1442 = new Class79(64);
        this.aClass79_1443 = new Class79(100);
        try {
            this.aClass207_1441 = aClass207_1441;
            if (this.aClass207_1441 != null) {
                this.aClass207_1441.method2761(0, this.aClass207_1441.method2752((byte)(-11)) - 1);
            }
            Class246_Sub3_Sub4_Sub4.method3079((byte)33, class281, 2, class280);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "me.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_1441 != null) ? "{...}" : "null") + ',' + ((class280 != null) ? "{...}" : "null") + ',' + ((class281 != null) ? "{...}" : "null") + ')');
        }
    }
}
