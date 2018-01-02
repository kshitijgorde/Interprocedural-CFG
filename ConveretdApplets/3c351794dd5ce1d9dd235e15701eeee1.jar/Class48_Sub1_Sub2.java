import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class48_Sub1_Sub2 extends Class48_Sub1
{
    static Class98_Sub22_Sub1 aClass98_Sub22_Sub1_5514;
    static float aFloat5515;
    private Class42_Sub2 aClass42_Sub2_5516;
    private ha_Sub1 aHa_Sub1_5517;
    static int[] anIntArray5518;
    static int anInt5519;
    static IncomingOpcode aClass58_5520;
    
    @Override
    final Class42_Sub2 method456(final byte b) {
        try {
            return this.aClass42_Sub2_5516;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cv.E(" + b + ')');
        }
    }
    
    final boolean method464(final byte b, final Class42_Sub2 class42_Sub2, final float n, final Class42_Sub2 class42_Sub3) {
        try {
            boolean b2 = true;
            final Class288 aClass288_4363 = this.aHa_Sub1_5517.aClass288_4363;
            this.aHa_Sub1_5517.K(Class21.anIntArray3232);
            this.aHa_Sub1_5517.la();
            this.aHa_Sub1_5517.method1867(b + 29479);
            OpenGL.glMatrixMode(5889);
            OpenGL.glLoadIdentity();
            OpenGL.glOrtho(0.0, 1.0, 0.0, 1.0, -1.0, 1.0);
            OpenGL.glMatrixMode(5888);
            OpenGL.glLoadIdentity();
            OpenGL.glPushAttrib(2048);
            OpenGL.glViewport(0, 0, this.aClass42_Sub2_5516.anInt5357, this.aClass42_Sub2_5516.anInt5357);
            this.aHa_Sub1_5517.method1856(false, b ^ 0xFFFFE4E9);
            this.aHa_Sub1_5517.method1851(false, false);
            this.aHa_Sub1_5517.method1881(false, false);
            this.aHa_Sub1_5517.method1875((byte)(-123), false);
            this.aHa_Sub1_5517.method1834(69, -2);
            this.aHa_Sub1_5517.method1845(1, b ^ 0xCD767C83);
            this.aHa_Sub1_5517.method1858(0.0f, 0.0f, 0.0f, n, -77);
            this.aHa_Sub1_5517.method1899(34165, 8960, 34165);
            this.aHa_Sub1_5517.method1863(1, class42_Sub3);
            this.aHa_Sub1_5517.method1845(0, 847872872);
            this.aHa_Sub1_5517.method1896(260, 1);
            this.aHa_Sub1_5517.method1863(1, class42_Sub2);
            this.aHa_Sub1_5517.method1898(true, aClass288_4363);
            for (int n2 = 0; ~n2 > -7; ++n2) {
                final int n3 = n2 + 34069;
                aClass288_4363.method3402(n3, b + 21, 0, this.aClass42_Sub2_5516);
                aClass288_4363.method3404(0, 0);
                if (!aClass288_4363.method3403((byte)74)) {
                    b2 = false;
                    break;
                }
                OpenGL.glBegin(7);
                final int n4 = n3;
                if (~n4 != 0xFFFF7AEA) {
                    if (~n4 != 0xFFFF7AE9) {
                        if (n4 != 34071) {
                            if (n4 != 34072) {
                                if (n4 != 34073) {
                                    if (~n4 == 0xFFFF7AE5) {
                                        OpenGL.glTexCoord3i(65534, 65534, -65535);
                                        OpenGL.glMultiTexCoord3i(33985, 65534, 65534, -65535);
                                        OpenGL.glVertex2f(0.0f, 0.0f);
                                        OpenGL.glTexCoord3i(-65534, 65534, -65535);
                                        OpenGL.glMultiTexCoord3i(33985, -65534, 65534, -65535);
                                        OpenGL.glVertex2f(1.0f, 0.0f);
                                        OpenGL.glTexCoord3i(-65534, -65534, -65535);
                                        OpenGL.glMultiTexCoord3i(33985, -65534, -65534, -65535);
                                        OpenGL.glVertex2f(1.0f, 1.0f);
                                        OpenGL.glTexCoord3i(65534, -65534, -65535);
                                        OpenGL.glMultiTexCoord3i(33985, 65534, -65534, -65535);
                                        OpenGL.glVertex2f(0.0f, 1.0f);
                                    }
                                }
                                else {
                                    OpenGL.glTexCoord3i(-65534, 65534, 65535);
                                    OpenGL.glMultiTexCoord3i(33985, -65534, 65534, 65535);
                                    OpenGL.glVertex2f(0.0f, 0.0f);
                                    OpenGL.glTexCoord3i(65534, 65534, 65535);
                                    OpenGL.glMultiTexCoord3i(33985, 65534, 65534, 65535);
                                    OpenGL.glVertex2f(1.0f, 0.0f);
                                    OpenGL.glTexCoord3i(65534, -65534, 65535);
                                    OpenGL.glMultiTexCoord3i(33985, 65534, -65534, 65535);
                                    OpenGL.glVertex2f(1.0f, 1.0f);
                                    OpenGL.glTexCoord3i(-65534, -65534, 65535);
                                    OpenGL.glMultiTexCoord3i(33985, -65534, -65534, 65535);
                                    OpenGL.glVertex2f(0.0f, 1.0f);
                                }
                            }
                            else {
                                OpenGL.glTexCoord3i(-65534, -65535, 65534);
                                OpenGL.glMultiTexCoord3i(33985, -65534, -65535, 65534);
                                OpenGL.glVertex2f(0.0f, 0.0f);
                                OpenGL.glTexCoord3i(65534, -65535, 65534);
                                OpenGL.glMultiTexCoord3i(33985, 65534, -65535, 65534);
                                OpenGL.glVertex2f(1.0f, 0.0f);
                                OpenGL.glTexCoord3i(65534, -65535, -65534);
                                OpenGL.glMultiTexCoord3i(33985, 65534, -65535, -65534);
                                OpenGL.glVertex2f(1.0f, 1.0f);
                                OpenGL.glTexCoord3i(-65534, -65535, -65534);
                                OpenGL.glMultiTexCoord3i(33985, -65534, -65535, -65534);
                                OpenGL.glVertex2f(0.0f, 1.0f);
                            }
                        }
                        else {
                            OpenGL.glTexCoord3i(-65534, 65535, -65534);
                            OpenGL.glMultiTexCoord3i(33985, -65534, 65535, -65534);
                            OpenGL.glVertex2f(0.0f, 0.0f);
                            OpenGL.glTexCoord3i(65534, 65535, -65534);
                            OpenGL.glMultiTexCoord3i(33985, 65534, 65535, -65534);
                            OpenGL.glVertex2f(1.0f, 0.0f);
                            OpenGL.glTexCoord3i(65534, 65535, 65534);
                            OpenGL.glMultiTexCoord3i(33985, 65534, 65535, 65534);
                            OpenGL.glVertex2f(1.0f, 1.0f);
                            OpenGL.glTexCoord3i(-65534, 65535, 65534);
                            OpenGL.glMultiTexCoord3i(33985, -65534, 65535, 65534);
                            OpenGL.glVertex2f(0.0f, 1.0f);
                        }
                    }
                    else {
                        OpenGL.glTexCoord3i(-65535, 65534, -65534);
                        OpenGL.glMultiTexCoord3i(33985, -65535, 65534, -65534);
                        OpenGL.glVertex2f(0.0f, 0.0f);
                        OpenGL.glTexCoord3i(-65535, 65534, 65534);
                        OpenGL.glMultiTexCoord3i(33985, -65535, 65534, 65534);
                        OpenGL.glVertex2f(1.0f, 0.0f);
                        OpenGL.glTexCoord3i(-65535, -65534, 65534);
                        OpenGL.glMultiTexCoord3i(33985, -65535, -65534, 65534);
                        OpenGL.glVertex2f(1.0f, 1.0f);
                        OpenGL.glTexCoord3i(-65535, -65534, -65534);
                        OpenGL.glMultiTexCoord3i(33985, -65535, -65534, -65534);
                        OpenGL.glVertex2f(0.0f, 1.0f);
                    }
                }
                else {
                    OpenGL.glTexCoord3i(65535, 65534, 65534);
                    OpenGL.glMultiTexCoord3i(33985, 65535, 65534, 65534);
                    OpenGL.glVertex2f(0.0f, 0.0f);
                    OpenGL.glTexCoord3i(65535, 65534, -65534);
                    OpenGL.glMultiTexCoord3i(33985, 65535, 65534, -65534);
                    OpenGL.glVertex2f(1.0f, 0.0f);
                    OpenGL.glTexCoord3i(65535, -65534, -65534);
                    OpenGL.glMultiTexCoord3i(33985, 65535, -65534, -65534);
                    OpenGL.glVertex2f(1.0f, 1.0f);
                    OpenGL.glTexCoord3i(65535, -65534, 65534);
                    OpenGL.glMultiTexCoord3i(33985, 65535, -65534, 65534);
                    OpenGL.glVertex2f(0.0f, 1.0f);
                }
                OpenGL.glEnd();
            }
            aClass288_4363.method3401(0, true);
            this.aHa_Sub1_5517.method1907(aClass288_4363, b ^ 0x14);
            this.aHa_Sub1_5517.method1845(1, 847872872);
            this.aHa_Sub1_5517.method1863(1, null);
            this.aHa_Sub1_5517.method1899(8448, b ^ 0xFFFFDCEB, 8448);
            this.aHa_Sub1_5517.method1845(0, 847872872);
            this.aHa_Sub1_5517.method1863(1, null);
            if (b != -21) {
                method466(true);
            }
            OpenGL.glPopAttrib();
            this.aHa_Sub1_5517.KA(Class21.anIntArray3232[0], Class21.anIntArray3232[1], Class21.anIntArray3232[2], Class21.anIntArray3232[3]);
            if (b2 && !this.aHa_Sub1_5517.aBoolean4406) {
                this.aClass42_Sub2_5516.method371(69);
            }
            return b2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cv.F(" + b + ',' + ((class42_Sub2 != null) ? "{...}" : "null") + ',' + n + ',' + ((class42_Sub3 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final int method465(final int n) {
        try {
            if (n != -65534) {
                method467(-59);
            }
            return this.aClass42_Sub2_5516.anInt5357;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cv.J(" + n + ')');
        }
    }
    
    Class48_Sub1_Sub2(final ha_Sub1 aHa_Sub1_5517, final int n) {
        try {
            this.aHa_Sub1_5517 = aHa_Sub1_5517;
            this.aClass42_Sub2_5516 = new Class42_Sub2(aHa_Sub1_5517, 6408, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cv.<init>(" + ((aHa_Sub1_5517 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final void method466(final boolean b) {
        try {
            Class246_Sub3_Sub4_Sub2_Sub1.aClass332_6508 = null;
            if (b) {
                Class98_Sub10_Sub28.aClass332_5704 = null;
                Class300.aClass332_2500 = null;
                Class221.aClass332_1666 = null;
                Class98_Sub50.aClass332_4287 = null;
                Class98_Sub47.aClass332_4273 = null;
                Class45.aClass332_383 = null;
                Class76_Sub11.aClass332_3795 = null;
                Class217.aClass332Array3408 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cv.G(" + b + ')');
        }
    }
    
    public static void method467(final int n) {
        try {
            Class48_Sub1_Sub2.anIntArray5518 = null;
            if (n == 1) {
                Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514 = null;
                Class48_Sub1_Sub2.aClass58_5520 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cv.H(" + n + ')');
        }
    }
    
    static final boolean method468(final boolean b, final int n, final int n2, final String s) {
        try {
            if (~n2 > -3 || ~n2 < -37) {
                throw new IllegalArgumentException("Invalid radix:" + n2);
            }
            if (n != 30883) {
                return false;
            }
            boolean b2 = false;
            boolean b3 = false;
            int n3 = 0;
            for (int length = s.length(), n4 = 0; ~length < ~n4; ++n4) {
                int char1 = s.charAt(n4);
                if (n4 == 0) {
                    if (char1 == 45) {
                        b2 = true;
                        continue;
                    }
                    if (char1 == 43 && b) {
                        continue;
                    }
                }
                if (char1 >= 48 && char1 <= 57) {
                    char1 -= 48;
                }
                else if (~char1 <= -66 && char1 <= 90) {
                    char1 -= 55;
                }
                else {
                    if (~char1 > -98 || ~char1 < -123) {
                        return false;
                    }
                    char1 -= 87;
                }
                if (~char1 <= ~n2) {
                    return false;
                }
                if (b2) {
                    char1 = -char1;
                }
                final int n5 = n2 * n3 + char1;
                if (n3 != n5 / n2) {
                    return false;
                }
                n3 = n5;
                b3 = true;
            }
            return b3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cv.I(" + b + ',' + n + ',' + n2 + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class48_Sub1_Sub2.anIntArray5518 = new int[2];
        Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514 = new Class98_Sub22_Sub1(5000);
        Class48_Sub1_Sub2.aClass58_5520 = new IncomingOpcode(21, 3);
    }
}
