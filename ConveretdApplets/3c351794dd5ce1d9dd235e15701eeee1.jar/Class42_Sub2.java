import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class42_Sub2 extends Class42
{
    int anInt5357;
    private int anInt5358;
    static Class324 aClass324_5359;
    private int anInt5360;
    
    Class42_Sub2(final ha_Sub1 ha_Sub1, final int n, final int anInt5357) {
        super(ha_Sub1, 34067, n, 6 * anInt5357 * anInt5357, false);
        this.anInt5358 = -1;
        this.anInt5360 = -1;
        try {
            this.anInt5357 = anInt5357;
            super.aHa_Sub1_3227.method1863(1, this);
            for (int i = 0; i < 6; ++i) {
                OpenGL.glTexImage2Dub(i + 34069, 0, super.anInt3230, anInt5357, anInt5357, 0, Class98_Sub31_Sub2.method1339(super.anInt3230, 126), 5121, null, 0);
            }
            this.method372(-28003, true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hw.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + anInt5357 + ')');
        }
    }
    
    static final void method388(final boolean b) {
        try {
            int n = 1024;
            int n2 = 3072;
            if (Class239.aBoolean1839) {
                n2 = 4096;
                if (Class69.aBoolean3223) {
                    n = 2048;
                }
            }
            if (n > Class119_Sub4.aFloat4740) {
                Class119_Sub4.aFloat4740 = n;
            }
            if (Class119_Sub4.aFloat4740 > n2) {
                Class119_Sub4.aFloat4740 = n2;
            }
            while (Class98_Sub22_Sub2.aFloat5794 >= 16384.0f) {
                Class98_Sub22_Sub2.aFloat5794 -= 16384.0f;
            }
            while (Class98_Sub22_Sub2.aFloat5794 < 0.0f) {
                Class98_Sub22_Sub2.aFloat5794 += 16384.0f;
            }
            final int n3 = Class201.anInt1545 >> 698948297;
            final int n4 = Class224_Sub3_Sub1.anInt6147 >> -35654487;
            final int method1538 = Class98_Sub46_Sub2_Sub2.method1538(Class43.anInt377, Class224_Sub3_Sub1.anInt6147, Class201.anInt1545, 24111);
            if (b) {
                int n5 = 0;
                if (~n3 < -4 && ~n4 < -4 && ~(Class165.anInt1276 - 4) < ~n3 && ~(Class98_Sub10_Sub7.anInt5572 - 4) < ~n4) {
                    for (int n6 = -4 + n3; ~(4 + n3) <= ~n6; ++n6) {
                        for (int n7 = n4 - 4; 4 + n4 >= n7; ++n7) {
                            int anInt377 = Class43.anInt377;
                            if (anInt377 < 3 && Class1.method162(n7, (byte)(-104), n6)) {
                                ++anInt377;
                            }
                            int n8 = 0;
                            if (Class146_Sub3.aClass305_Sub1_4952.aByteArrayArrayArray2554 != null && Class146_Sub3.aClass305_Sub1_4952.aByteArrayArrayArray2554[anInt377] != null) {
                                n8 = (0xFF & Class146_Sub3.aClass305_Sub1_4952.aByteArrayArrayArray2554[anInt377][n6][n7]) * 8 << 976034466;
                            }
                            if (Class78.aSArray594 != null && Class78.aSArray594[anInt377] != null) {
                                final int n9 = method1538 - Class78.aSArray594[anInt377].method3420(n7, -12639, n6) - -n8;
                                if (~n9 < ~n5) {
                                    n5 = n9;
                                }
                            }
                        }
                    }
                }
                int n10 = (n5 >> 142317154) * 1536;
                if (n10 > 786432) {
                    n10 = 786432;
                }
                if (n10 < 262144) {
                    n10 = 262144;
                }
                if (Class43.anInt372 < n10) {
                    Class43.anInt372 += (-Class43.anInt372 + n10) / 24;
                }
                else if (~Class43.anInt372 < ~n10) {
                    Class43.anInt372 += (n10 + -Class43.anInt372) / 80;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hw.C(" + b + ')');
        }
    }
    
    Class42_Sub2(final ha_Sub1 ha_Sub1, final int n, final int anInt5357, final boolean b, final byte[][] array, final int n2) {
        super(ha_Sub1, 34067, n, 6 * (anInt5357 * anInt5357), b);
        this.anInt5358 = -1;
        this.anInt5360 = -1;
        try {
            this.anInt5357 = anInt5357;
            super.aHa_Sub1_3227.method1863(1, this);
            for (int n3 = 0; ~n3 > -7; ++n3) {
                OpenGL.glTexImage2Dub(34069 - -n3, 0, super.anInt3230, anInt5357, anInt5357, 0, n2, 5121, array[n3], 0);
            }
            this.method372(-28003, true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hw.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + anInt5357 + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    Class42_Sub2(final ha_Sub1 ha_Sub1, final int n, final int anInt5357, final boolean b, final int[][] array) {
        super(ha_Sub1, 34067, n, anInt5357 * (anInt5357 * 6), b);
        this.anInt5358 = -1;
        this.anInt5360 = -1;
        try {
            this.anInt5357 = anInt5357;
            super.aHa_Sub1_3227.method1863(1, this);
            if (b) {
                for (int i = 0; i < 6; ++i) {
                    Class336.method3773(super.anInt3230, 53, super.aHa_Sub1_3227.anInt4425, 34069 + i, anInt5357, 32993, anInt5357, array[i]);
                }
            }
            else {
                for (int n2 = 0; ~n2 > -7; ++n2) {
                    OpenGL.glTexImage2Di(34069 - -n2, 0, super.anInt3230, anInt5357, anInt5357, 0, 32993, super.aHa_Sub1_3227.anInt4425, array[n2], 0);
                }
            }
            this.method372(-28003, true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hw.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + anInt5357 + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void method3(final byte b) {
        try {
            if (b > -117) {
                this.anInt5360 = -45;
            }
            OpenGL.glFramebufferTexture2DEXT(this.anInt5358, this.anInt5360, 3553, 0, 0);
            this.anInt5358 = -1;
            this.anInt5360 = -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hw.B(" + b + ')');
        }
    }
    
    static final int[] method389(final int n, final Class98_Sub11 class98_Sub11) {
        try {
            final Class98_Sub22 class98_Sub12 = new Class98_Sub22(518);
            if (n != 12206) {
                method388(true);
            }
            final int[] array = new int[4];
            for (int n2 = 0; ~n2 > -5; ++n2) {
                array[n2] = (int)(9.9999999E7 * Math.random());
            }
            class98_Sub12.method1194(10, 86);
            class98_Sub12.writeInt(1571862888, array[0]);
            class98_Sub12.writeInt(1571862888, array[1]);
            class98_Sub12.writeInt(n ^ 0x5DB096C6, array[2]);
            class98_Sub12.writeInt(1571862888, array[3]);
            for (int n3 = 0; ~n3 > -11; ++n3) {
                class98_Sub12.writeInt(1571862888, (int)(9.9999999E7 * Math.random()));
            }
            class98_Sub12.writeShort((int)(Math.random() * 9.9999999E7), n ^ 0x5DB096C6);
            class98_Sub12.method1205(Class98_Sub45.aBigInteger4253, true, Class138.aBigInteger1082);
            class98_Sub11.aClass98_Sub22_Sub1_3865.method1217(class98_Sub12.aByteArray3992, class98_Sub12.anInt3991, n - 12207, 0);
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hw.D(" + n + ',' + ((class98_Sub11 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method390(final boolean b) {
        try {
            if (b) {
                method389(103, null);
            }
            Class42_Sub2.aClass324_5359 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hw.E(" + b + ')');
        }
    }
    
    final void method391(final int n, final int anInt5360, final int n2, final int anInt5361, final byte b) {
        try {
            OpenGL.glFramebufferTexture2DEXT(anInt5361, anInt5360, n2, super.anInt3229, n);
            this.anInt5360 = anInt5360;
            this.anInt5358 = anInt5361;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hw.A(" + n + ',' + anInt5360 + ',' + n2 + ',' + anInt5361 + ',' + b + ')');
        }
    }
}
