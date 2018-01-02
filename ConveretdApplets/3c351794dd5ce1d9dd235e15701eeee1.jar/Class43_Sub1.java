import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class43_Sub1 extends Class43
{
    private Class42_Sub1_Sub1 aClass42_Sub1_Sub1_3601;
    private Class91 aClass91_3602;
    private boolean aBoolean3603;
    private ha_Sub1 aHa_Sub1_3604;
    
    Class43_Sub1(final ha_Sub1 aHa_Sub1_3604, final Class197 class197, final Class324[] array, final boolean b) {
        super(aHa_Sub1_3604, class197);
        this.aHa_Sub1_3604 = aHa_Sub1_3604;
        int n = 0;
        for (int i = 0; i < 256; ++i) {
            final Class324 class198 = array[i];
            if (class198.anInt2720 > n) {
                n = class198.anInt2720;
            }
            if (class198.anInt2722 > n) {
                n = class198.anInt2722;
            }
        }
        final int n2 = n * 16;
        if (b) {
            final byte[] array2 = new byte[n2 * n2];
            for (int j = 0; j < 256; ++j) {
                final Class324 class199 = array[j];
                final int anInt2720 = class199.anInt2720;
                final int anInt2721 = class199.anInt2722;
                int n3 = j / 16 * n * n2 + j % 16 * n;
                int n4 = 0;
                if (class199.aByteArray2723 == null) {
                    final byte[] aByteArray2717 = class199.aByteArray2717;
                    for (int k = 0; k < anInt2720; ++k) {
                        for (int l = 0; l < anInt2721; ++l) {
                            array2[n3++] = (byte)((aByteArray2717[n4++] == 0) ? 0 : -1);
                        }
                        n3 += n2 - anInt2721;
                    }
                }
                else {
                    final byte[] aByteArray2718 = class199.aByteArray2723;
                    for (int n5 = 0; n5 < anInt2720; ++n5) {
                        for (int n6 = 0; n6 < anInt2721; ++n6) {
                            array2[n3++] = aByteArray2718[n4++];
                        }
                        n3 += n2 - anInt2721;
                    }
                }
            }
            this.aClass42_Sub1_Sub1_3601 = Class284_Sub2.method3374(6406, n2, 14764, 6406, aHa_Sub1_3604, false, array2, n2);
            this.aBoolean3603 = true;
        }
        else {
            final int[] array3 = new int[n2 * n2];
            for (int n7 = 0; n7 < 256; ++n7) {
                final Class324 class200 = array[n7];
                final int[] anIntArray2718 = class200.anIntArray2718;
                final byte[] aByteArray2719 = class200.aByteArray2723;
                final byte[] aByteArray2720 = class200.aByteArray2717;
                final int anInt2722 = class200.anInt2720;
                final int anInt2723 = class200.anInt2722;
                int n8 = n7 / 16 * n * n2 + n7 % 16 * n;
                int n9 = 0;
                if (aByteArray2719 != null) {
                    for (int n10 = 0; n10 < anInt2722; ++n10) {
                        for (int n11 = 0; n11 < anInt2723; ++n11) {
                            array3[n8++] = (aByteArray2719[n9] << 24 | anIntArray2718[aByteArray2720[n9] & 0xFF]);
                            ++n9;
                        }
                        n8 += n2 - anInt2723;
                    }
                }
                else {
                    for (int n12 = 0; n12 < anInt2722; ++n12) {
                        for (int n13 = 0; n13 < anInt2723; ++n13) {
                            final byte b2;
                            if ((b2 = aByteArray2720[n9++]) != 0) {
                                array3[n8++] = (0xFF000000 | anIntArray2718[b2 & 0xFF]);
                            }
                            else {
                                ++n8;
                            }
                        }
                        n8 += n2 - anInt2723;
                    }
                }
            }
            this.aClass42_Sub1_Sub1_3601 = Class246_Sub9.method3136(aHa_Sub1_3604, false, array3, n2, (byte)120, 0, 0, n2);
            this.aBoolean3603 = false;
        }
        this.aClass42_Sub1_Sub1_3601.method372(-28003, false);
        this.aClass91_3602 = new Class91(aHa_Sub1_3604, 256);
        final float n14 = this.aClass42_Sub1_Sub1_3601.aFloat6205 / this.aClass42_Sub1_Sub1_3601.anInt6207;
        final float n15 = this.aClass42_Sub1_Sub1_3601.aFloat6209 / this.aClass42_Sub1_Sub1_3601.anInt6204;
        for (int n16 = 0; n16 < 256; ++n16) {
            final Class324 class201 = array[n16];
            final int anInt2724 = class201.anInt2720;
            final int anInt2725 = class201.anInt2722;
            final int anInt2726 = class201.anInt2721;
            final int anInt2727 = class201.anInt2725;
            final float n17 = n16 % 16 * n;
            final float n18 = n16 / 16 * n;
            final float n19 = n17 * n14;
            final float n20 = n18 * n15;
            final float n21 = (n17 + anInt2725) * n14;
            final float n22 = (n18 + anInt2724) * n15;
            this.aClass91_3602.method887(n16, -30389);
            OpenGL.glBegin(7);
            OpenGL.glTexCoord2f(n19, this.aClass42_Sub1_Sub1_3601.aFloat6209 - n20);
            OpenGL.glVertex2i(anInt2727, anInt2726);
            OpenGL.glTexCoord2f(n19, this.aClass42_Sub1_Sub1_3601.aFloat6209 - n22);
            OpenGL.glVertex2i(anInt2727, anInt2726 + anInt2724);
            OpenGL.glTexCoord2f(n21, this.aClass42_Sub1_Sub1_3601.aFloat6209 - n22);
            OpenGL.glVertex2i(anInt2727 + anInt2725, anInt2726 + anInt2724);
            OpenGL.glTexCoord2f(n21, this.aClass42_Sub1_Sub1_3601.aFloat6209 - n20);
            OpenGL.glVertex2i(anInt2727 + anInt2725, anInt2726);
            OpenGL.glEnd();
            this.aClass91_3602.method886((byte)(-44));
        }
    }
    
    @Override
    final void method409(final char c, final int n, final int n2, final int n3, final boolean b, final aa aa, final int n4, final int n5) {
        final Class42_Sub1_Sub1 aClass42_Sub1_Sub1_3568 = ((aa_Sub3)aa).aClass42_Sub1_Sub1_3568;
        this.aHa_Sub1_3604.method1889(false);
        this.aHa_Sub1_3604.method1863(1, this.aClass42_Sub1_Sub1_3601);
        if (this.aBoolean3603 || b) {
            this.aHa_Sub1_3604.method1899(8448, 8960, 7681);
            this.aHa_Sub1_3604.method1840(0, 768, 104, 34168);
        }
        else {
            this.aHa_Sub1_3604.method1899(7681, 8960, 7681);
        }
        this.aHa_Sub1_3604.method1845(1, 847872872);
        this.aHa_Sub1_3604.method1863(1, aClass42_Sub1_Sub1_3568);
        this.aHa_Sub1_3604.method1899(8448, 8960, 7681);
        this.aHa_Sub1_3604.method1840(0, 768, 110, 34168);
        OpenGL.glTexGeni(8192, 9472, 9216);
        OpenGL.glTexGeni(8193, 9472, 9216);
        final float n6 = aClass42_Sub1_Sub1_3568.aFloat6205 / aClass42_Sub1_Sub1_3568.anInt6207;
        final float n7 = aClass42_Sub1_Sub1_3568.aFloat6209 / aClass42_Sub1_Sub1_3568.anInt6204;
        OpenGL.glTexGenfv(8192, 9474, new float[] { n6, 0.0f, 0.0f, -n4 * n6 }, 0);
        OpenGL.glTexGenfv(8193, 9474, new float[] { 0.0f, n7, 0.0f, -n5 * n7 }, 0);
        OpenGL.glEnable(3168);
        OpenGL.glEnable(3169);
        OpenGL.glColor4ub((byte)(n3 >> 16), (byte)(n3 >> 8), (byte)n3, (byte)(n3 >> 24));
        OpenGL.glTranslatef(n, n2, 0.0f);
        this.aClass91_3602.method888(c, false);
        OpenGL.glLoadIdentity();
        OpenGL.glDisable(3168);
        OpenGL.glDisable(3169);
        this.aHa_Sub1_3604.method1840(0, 768, -91, 5890);
        this.aHa_Sub1_3604.method1899(8448, 8960, 8448);
        this.aHa_Sub1_3604.method1863(1, null);
        this.aHa_Sub1_3604.method1845(0, 847872872);
        if (this.aBoolean3603 || b) {
            this.aHa_Sub1_3604.method1840(0, 768, -102, 5890);
        }
    }
    
    @Override
    final void fa(final char c, final int n, final int n2, final int n3, final boolean b) {
        this.aHa_Sub1_3604.method1889(false);
        this.aHa_Sub1_3604.method1863(1, this.aClass42_Sub1_Sub1_3601);
        if (this.aBoolean3603 || b) {
            this.aHa_Sub1_3604.method1899(8448, 8960, 7681);
            this.aHa_Sub1_3604.method1840(0, 768, 83, 34168);
        }
        else {
            this.aHa_Sub1_3604.method1899(7681, 8960, 7681);
        }
        OpenGL.glColor4ub((byte)(n3 >> 16), (byte)(n3 >> 8), (byte)n3, (byte)(n3 >> 24));
        OpenGL.glTranslatef(n, n2, 0.0f);
        this.aClass91_3602.method888(c, false);
        OpenGL.glLoadIdentity();
        if (this.aBoolean3603 || b) {
            this.aHa_Sub1_3604.method1840(0, 768, 93, 5890);
        }
    }
}
