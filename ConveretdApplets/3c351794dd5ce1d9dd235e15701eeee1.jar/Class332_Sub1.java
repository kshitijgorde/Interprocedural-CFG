import java.net.Socket;
import java.io.IOException;
import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class332_Sub1 extends Class332
{
    private int anInt5400;
    private boolean aBoolean5401;
    private int anInt5402;
    private Class42_Sub1_Sub1 aClass42_Sub1_Sub1_5403;
    private int anInt5404;
    private int anInt5405;
    private ha_Sub1 aHa_Sub1_5406;
    private Class42_Sub1_Sub1 aClass42_Sub1_Sub1_5407;
    private int anInt5408;
    static Object anObject5409;
    
    @Override
    final void method3728(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            final int n8 = n3 + n;
            final int n9 = n2 - -n4;
            this.aClass42_Sub1_Sub1_5407.method372(-28003, false);
            this.aHa_Sub1_5406.method1829((byte)(-117));
            this.aHa_Sub1_5406.method1863(1, this.aClass42_Sub1_Sub1_5407);
            this.aHa_Sub1_5406.method1870((byte)(-90), n7);
            this.aHa_Sub1_5406.method1896(260, n5);
            OpenGL.glColor4ub((byte)(n6 >> 1904370224), (byte)(n6 >> 1918206088), (byte)n6, (byte)(n6 >> -31071336));
            if (!this.aClass42_Sub1_Sub1_5407.aBoolean6211 || this.aBoolean5401) {
                OpenGL.glPushMatrix();
                OpenGL.glTranslatef(this.anInt5404, this.anInt5405, 0.0f);
                final int method3737 = this.method3737();
                final int method3738 = this.method3749();
                int n10 = n2 + this.aClass42_Sub1_Sub1_5407.anInt6204;
                OpenGL.glBegin(7);
                int n11;
                for (n11 = n2; ~n10 >= ~n9; n10 += method3738, n11 += method3738) {
                    int n12 = this.aClass42_Sub1_Sub1_5407.anInt6207 + n;
                    int n13 = n;
                    while (~n12 >= ~n8) {
                        OpenGL.glTexCoord2f(0.0f, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                        OpenGL.glVertex2i(n13, n11);
                        OpenGL.glTexCoord2f(0.0f, 0.0f);
                        OpenGL.glVertex2i(n13, n10);
                        OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, 0.0f);
                        OpenGL.glVertex2i(n12, n10);
                        OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                        OpenGL.glVertex2i(n12, n11);
                        n13 += method3737;
                        n12 += method3737;
                    }
                    if (n13 < n8) {
                        final float n14 = (n8 - n13) * this.aClass42_Sub1_Sub1_5407.aFloat6205 / this.aClass42_Sub1_Sub1_5407.anInt6207;
                        OpenGL.glTexCoord2f(0.0f, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                        OpenGL.glVertex2i(n13, n11);
                        OpenGL.glTexCoord2f(0.0f, 0.0f);
                        OpenGL.glVertex2i(n13, n10);
                        OpenGL.glTexCoord2f(n14, 0.0f);
                        OpenGL.glVertex2i(n8, n10);
                        OpenGL.glTexCoord2f(n14, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                        OpenGL.glVertex2i(n8, n11);
                    }
                }
                if (~n9 < ~n11) {
                    final float n15 = (this.aClass42_Sub1_Sub1_5407.anInt6204 + n11 + -n9) * this.aClass42_Sub1_Sub1_5407.aFloat6209 / this.aClass42_Sub1_Sub1_5407.anInt6204;
                    int i;
                    int n16;
                    for (i = this.aClass42_Sub1_Sub1_5407.anInt6207 + n, n16 = n; i <= n8; i += method3737, n16 += method3737) {
                        OpenGL.glTexCoord2f(0.0f, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                        OpenGL.glVertex2i(n16, n11);
                        OpenGL.glTexCoord2f(0.0f, n15);
                        OpenGL.glVertex2i(n16, n9);
                        OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, n15);
                        OpenGL.glVertex2i(i, n9);
                        OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                        OpenGL.glVertex2i(i, n11);
                    }
                    if (~n16 > ~n8) {
                        final float n17 = (n8 + -n16) * this.aClass42_Sub1_Sub1_5407.aFloat6205 / this.aClass42_Sub1_Sub1_5407.anInt6207;
                        OpenGL.glTexCoord2f(0.0f, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                        OpenGL.glVertex2i(n16, n11);
                        OpenGL.glTexCoord2f(0.0f, n15);
                        OpenGL.glVertex2i(n16, n9);
                        OpenGL.glTexCoord2f(n17, n15);
                        OpenGL.glVertex2i(n8, n9);
                        OpenGL.glTexCoord2f(n17, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                        OpenGL.glVertex2i(n8, n11);
                    }
                }
                OpenGL.glEnd();
                OpenGL.glPopMatrix();
            }
            else {
                final float n18 = n4 * this.aClass42_Sub1_Sub1_5407.aFloat6209 / this.aClass42_Sub1_Sub1_5407.anInt6204;
                final float n19 = this.aClass42_Sub1_Sub1_5407.aFloat6205 * n3 / this.aClass42_Sub1_Sub1_5407.anInt6207;
                OpenGL.glBegin(7);
                OpenGL.glTexCoord2f(0.0f, n18);
                OpenGL.glVertex2i(n, n2);
                OpenGL.glTexCoord2f(0.0f, 0.0f);
                OpenGL.glVertex2i(n, n9);
                OpenGL.glTexCoord2f(n19, 0.0f);
                OpenGL.glVertex2i(n8, n9);
                OpenGL.glTexCoord2f(n19, n18);
                OpenGL.glVertex2i(n8, n2);
                OpenGL.glEnd();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.K(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    private final void method3750(final boolean b, final int n) {
        try {
            if (b) {
                this.method3752(-23);
            }
            this.aHa_Sub1_5406.method1845(1, 847872872);
            this.aHa_Sub1_5406.method1863(1, this.aClass42_Sub1_Sub1_5407);
            this.aHa_Sub1_5406.method1899(7681, 8960, this.aHa_Sub1_5406.method1892(n, 596294056));
            this.aHa_Sub1_5406.method1840(1, 768, -58, 34167);
            this.aHa_Sub1_5406.method1886(770, 0, 34200, 34168);
            this.aHa_Sub1_5406.method1845(0, 847872872);
            this.aHa_Sub1_5406.method1863(1, this.aClass42_Sub1_Sub1_5403);
            this.aHa_Sub1_5406.method1899(7681, 8960, 34479);
            this.aHa_Sub1_5406.method1840(1, 768, 122, 34166);
            if (~this.anInt5408 != -1) {
                if (~this.anInt5408 != 0xFFFFFFFE) {
                    if (~this.anInt5408 == 0xFFFFFFFD) {
                        this.aHa_Sub1_5406.method1858(0.5f, 1.0f, 0.5f, 0.0f, -89);
                    }
                    else if (~this.anInt5408 == 0xFFFFFFFC) {
                        this.aHa_Sub1_5406.method1858(128.5f, 128.5f, 128.5f, 0.0f, -40);
                    }
                }
                else {
                    this.aHa_Sub1_5406.method1858(1.0f, 0.5f, 0.5f, 0.0f, -59);
                }
            }
            else {
                this.aHa_Sub1_5406.method1858(0.5f, 0.5f, 1.0f, 0.0f, 108);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.H(" + b + ',' + n + ')');
        }
    }
    
    @Override
    final void method3748(int n, int n2, final int n3, final int n4, final int n5) {
        try {
            this.aClass42_Sub1_Sub1_5407.method372(-28003, false);
            this.aHa_Sub1_5406.method1829((byte)(-104));
            this.aHa_Sub1_5406.method1870((byte)(-110), n5);
            OpenGL.glColor4ub((byte)(n4 >> -599196016), (byte)(n4 >> 983865512), (byte)n4, (byte)(n4 >> -1342849480));
            n2 += this.anInt5405;
            n += this.anInt5404;
            if (this.aClass42_Sub1_Sub1_5403 != null) {
                this.method3750(false, n3);
                this.aClass42_Sub1_Sub1_5403.method372(-28003, false);
                OpenGL.glBegin(7);
                OpenGL.glMultiTexCoord2f(33985, 0.0f, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                OpenGL.glTexCoord2f(0.0f, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                OpenGL.glVertex2i(n, n2);
                OpenGL.glMultiTexCoord2f(33985, 0.0f, 0.0f);
                OpenGL.glTexCoord2f(0.0f, 0.0f);
                OpenGL.glVertex2i(n, n2 + this.aClass42_Sub1_Sub1_5407.anInt6204);
                OpenGL.glMultiTexCoord2f(33985, this.aClass42_Sub1_Sub1_5407.aFloat6205, 0.0f);
                OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, 0.0f);
                OpenGL.glVertex2i(this.aClass42_Sub1_Sub1_5407.anInt6207 + n, n2 + this.aClass42_Sub1_Sub1_5407.anInt6204);
                OpenGL.glMultiTexCoord2f(33985, this.aClass42_Sub1_Sub1_5407.aFloat6205, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                OpenGL.glVertex2i(this.aClass42_Sub1_Sub1_5407.anInt6207 + n, n2);
                OpenGL.glEnd();
                this.method3752(-22);
            }
            else {
                this.aHa_Sub1_5406.method1863(1, this.aClass42_Sub1_Sub1_5407);
                this.aHa_Sub1_5406.method1896(260, n3);
                OpenGL.glBegin(7);
                OpenGL.glTexCoord2f(0.0f, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                OpenGL.glVertex2i(n, n2);
                OpenGL.glTexCoord2f(0.0f, 0.0f);
                OpenGL.glVertex2i(n, this.aClass42_Sub1_Sub1_5407.anInt6204 + n2);
                OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, 0.0f);
                OpenGL.glVertex2i(this.aClass42_Sub1_Sub1_5407.anInt6207 + n, this.aClass42_Sub1_Sub1_5407.anInt6204 + n2);
                OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                OpenGL.glVertex2i(this.aClass42_Sub1_Sub1_5407.anInt6207 + n, n2);
                OpenGL.glEnd();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.E(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    @Override
    final int method3731() {
        try {
            return this.aClass42_Sub1_Sub1_5407.anInt6204;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.U()");
        }
    }
    
    public static void method3751(final int n) {
        try {
            if (n != 5890) {
                Class332_Sub1.anObject5409 = null;
            }
            Class332_Sub1.anObject5409 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.G(" + n + ')');
        }
    }
    
    @Override
    final void method3745(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        try {
            this.aClass42_Sub1_Sub1_5407.method372(-28003, (n8 & 0x1) != 0x0);
            this.aHa_Sub1_5406.method1829((byte)(-126));
            this.aHa_Sub1_5406.method1870((byte)(-75), n7);
            OpenGL.glColor4ub((byte)(n6 >> -1746196464), (byte)(n6 >> 1440844808), (byte)n6, (byte)(n6 >> -2121300072));
            if (!this.aBoolean5401) {
                if (this.aClass42_Sub1_Sub1_5403 != null) {
                    this.method3750(false, n5);
                    this.aClass42_Sub1_Sub1_5403.method372(-28003, true);
                    OpenGL.glBegin(7);
                    OpenGL.glMultiTexCoord2f(33985, 0.0f, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                    OpenGL.glTexCoord2f(0.0f, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                    OpenGL.glVertex2i(n, n2);
                    OpenGL.glMultiTexCoord2f(33985, 0.0f, 0.0f);
                    OpenGL.glTexCoord2f(0.0f, 0.0f);
                    OpenGL.glVertex2i(n, n2 - -n4);
                    OpenGL.glMultiTexCoord2f(33985, this.aClass42_Sub1_Sub1_5407.aFloat6205, 0.0f);
                    OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, 0.0f);
                    OpenGL.glVertex2i(n3 + n, n4 + n2);
                    OpenGL.glMultiTexCoord2f(33985, this.aClass42_Sub1_Sub1_5407.aFloat6205, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                    OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                    OpenGL.glVertex2i(n3 + n, n2);
                    OpenGL.glEnd();
                    this.method3752(-119);
                }
                else {
                    this.aHa_Sub1_5406.method1863(1, this.aClass42_Sub1_Sub1_5407);
                    this.aHa_Sub1_5406.method1896(260, n5);
                    OpenGL.glBegin(7);
                    OpenGL.glTexCoord2f(0.0f, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                    OpenGL.glVertex2i(n, n2);
                    OpenGL.glTexCoord2f(0.0f, 0.0f);
                    OpenGL.glVertex2i(n, n4 + n2);
                    OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, 0.0f);
                    OpenGL.glVertex2i(n + n3, n4 + n2);
                    OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                    OpenGL.glVertex2i(n3 + n, n2);
                    OpenGL.glEnd();
                }
            }
            else {
                final float n9 = n3 / this.method3737();
                final float n10 = n4 / this.method3749();
                final float n11 = n9 * this.anInt5404 + n;
                final float n12 = this.anInt5405 * n10 + n2;
                final float n13 = n11 + this.aClass42_Sub1_Sub1_5407.anInt6207 * n9;
                final float n14 = n12 + n10 * this.aClass42_Sub1_Sub1_5407.anInt6204;
                if (this.aClass42_Sub1_Sub1_5403 != null) {
                    this.method3750(false, n5);
                    this.aClass42_Sub1_Sub1_5403.method372(-28003, true);
                    OpenGL.glBegin(7);
                    OpenGL.glMultiTexCoord2f(33985, 0.0f, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                    OpenGL.glTexCoord2f(0.0f, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                    OpenGL.glVertex2f(n11, n12);
                    OpenGL.glMultiTexCoord2f(33985, 0.0f, 0.0f);
                    OpenGL.glTexCoord2f(0.0f, 0.0f);
                    OpenGL.glVertex2f(n11, n14);
                    OpenGL.glMultiTexCoord2f(33985, this.aClass42_Sub1_Sub1_5407.aFloat6205, 0.0f);
                    OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, 0.0f);
                    OpenGL.glVertex2f(n13, n14);
                    OpenGL.glMultiTexCoord2f(33985, this.aClass42_Sub1_Sub1_5407.aFloat6205, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                    OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                    OpenGL.glVertex2f(n13, n12);
                    OpenGL.glEnd();
                    this.method3752(-69);
                }
                else {
                    this.aHa_Sub1_5406.method1863(1, this.aClass42_Sub1_Sub1_5407);
                    this.aHa_Sub1_5406.method1896(260, n5);
                    OpenGL.glBegin(7);
                    OpenGL.glTexCoord2f(0.0f, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                    OpenGL.glVertex2f(n11, n12);
                    OpenGL.glTexCoord2f(0.0f, 0.0f);
                    OpenGL.glVertex2f(n11, n14);
                    OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, 0.0f);
                    OpenGL.glVertex2f(n13, n14);
                    OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, this.aClass42_Sub1_Sub1_5407.aFloat6209);
                    OpenGL.glVertex2f(n13, n12);
                    OpenGL.glEnd();
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    @Override
    final void method3740(final int anInt5404, final int anInt5405, final int anInt5406, final int anInt5407) {
        try {
            this.anInt5404 = anInt5404;
            this.anInt5405 = anInt5405;
            this.anInt5402 = anInt5406;
            this.anInt5400 = anInt5407;
            this.aBoolean5401 = (this.anInt5404 != 0 || this.anInt5405 != 0 || this.anInt5402 != 0 || this.anInt5400 != 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.Q(" + anInt5404 + ',' + anInt5405 + ',' + anInt5406 + ',' + anInt5407 + ')');
        }
    }
    
    @Override
    final int method3737() {
        try {
            return this.aClass42_Sub1_Sub1_5407.anInt6207 - (-this.anInt5404 - this.anInt5402);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.O()");
        }
    }
    
    private final void method3752(final int n) {
        try {
            this.aHa_Sub1_5406.method1845(1, 847872872);
            this.aHa_Sub1_5406.method1863(1, null);
            this.aHa_Sub1_5406.method1899(8448, 8960, 8448);
            this.aHa_Sub1_5406.method1840(1, 768, -54, 34168);
            this.aHa_Sub1_5406.method1886(770, 0, 34200, 5890);
            this.aHa_Sub1_5406.method1845(0, 847872872);
            this.aHa_Sub1_5406.method1840(1, 768, -71, 34168);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.I(" + n + ')');
        }
    }
    
    @Override
    final void method3742(final int n, final int n2, final int anInt5408) {
        try {
            OpenGL.glPixelTransferf(3348, 0.5f);
            OpenGL.glPixelTransferf(3349, 0.499f);
            OpenGL.glPixelTransferf(3352, 0.5f);
            OpenGL.glPixelTransferf(3353, 0.499f);
            OpenGL.glPixelTransferf(3354, 0.5f);
            OpenGL.glPixelTransferf(3355, 0.499f);
            this.aClass42_Sub1_Sub1_5403 = aa_Sub1.method153((byte)30, this.aClass42_Sub1_Sub1_5407.anInt6204, this.aHa_Sub1_5406, this.aClass42_Sub1_Sub1_5407.anInt6207, n, n2);
            this.anInt5408 = anInt5408;
            OpenGL.glPixelTransferf(3348, 1.0f);
            OpenGL.glPixelTransferf(3349, 0.0f);
            OpenGL.glPixelTransferf(3352, 1.0f);
            OpenGL.glPixelTransferf(3353, 0.0f);
            OpenGL.glPixelTransferf(3354, 1.0f);
            OpenGL.glPixelTransferf(3355, 0.0f);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.F(" + n + ',' + n2 + ',' + anInt5408 + ')');
        }
    }
    
    static final void method3753(final int n) {
        try {
            if (n <= 36) {
                Class332_Sub1.anObject5409 = null;
            }
            if (Class64_Sub16.anInt3680 != 0 && ~Class64_Sub16.anInt3680 != 0xFFFFFFFA) {
                try {
                    int n2;
                    if (Class151_Sub9.anInt5020 != 0) {
                        n2 = 2000;
                    }
                    else {
                        n2 = 250;
                    }
                    if (n2 < ++Class139.anInt1087) {
                        if (aa_Sub1.aClass123_3561 != null) {
                            aa_Sub1.aClass123_3561.method2207(-93);
                            aa_Sub1.aClass123_3561 = null;
                        }
                        if (Class151_Sub9.anInt5020 >= 3) {
                            Class64_Sub16.anInt3680 = 0;
                            Class369.method3952(-5, (byte)(-55));
                            return;
                        }
                        if (Class98_Sub46_Sub20_Sub2.anInt6317 != 2) {
                            Class299_Sub2.aClass354_5297.method3874(0);
                        }
                        else {
                            Class98_Sub46_Sub10.aClass354_6011.method3874(0);
                        }
                        Class64_Sub16.anInt3680 = 1;
                        Class139.anInt1087 = 0;
                        ++Class151_Sub9.anInt5020;
                    }
                    if (~Class64_Sub16.anInt3680 == 0xFFFFFFFE) {
                        if (~Class98_Sub46_Sub20_Sub2.anInt6317 != 0xFFFFFFFD) {
                            Class246_Sub3_Sub3.aClass143_6155 = Class299_Sub2.aClass354_5297.method3870(-115, Class98_Sub43_Sub2.aClass88_5907);
                        }
                        else {
                            Class246_Sub3_Sub3.aClass143_6155 = Class98_Sub46_Sub10.aClass354_6011.method3870(51, Class98_Sub43_Sub2.aClass88_5907);
                        }
                        Class64_Sub16.anInt3680 = 2;
                    }
                    if (Class64_Sub16.anInt3680 == 2) {
                        if (Class246_Sub3_Sub3.aClass143_6155.anInt1163 == 2) {
                            throw new IOException();
                        }
                        if (~Class246_Sub3_Sub3.aClass143_6155.anInt1163 != 0xFFFFFFFE) {
                            return;
                        }
                        aa_Sub1.aClass123_3561 = Class196.method2668((Socket)Class246_Sub3_Sub3.aClass143_6155.anObject1162, (byte)11, 7500);
                        Class246_Sub3_Sub3.aClass143_6155 = null;
                        Class49.method477(-5788);
                        final Class98_Sub11 method1556 = Class98_Sub46_Sub9.method1556(false);
                        method1556.aClass98_Sub22_Sub1_3865.method1194(Class298.aClass222_2478.anInt1668, 43);
                        Class98_Sub10_Sub29.sendPacket(false, method1556);
                        Class95.method920((byte)115);
                        Class64_Sub16.anInt3680 = 3;
                    }
                    if (Class64_Sub16.anInt3680 == 3) {
                        if (!aa_Sub1.aClass123_3561.method2203(-1949, 1)) {
                            return;
                        }
                        aa_Sub1.aClass123_3561.method2208(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992, 0, 2047, 1);
                        final int n3 = 0xFF & Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992[0];
                        if (~n3 != -1) {
                            Class64_Sub16.anInt3680 = 0;
                            Class369.method3952(n3, (byte)(-55));
                            aa_Sub1.aClass123_3561.method2207(-63);
                            aa_Sub1.aClass123_3561 = null;
                            Class98_Sub12.method1130(27089);
                            return;
                        }
                        Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 = 0;
                        final Class98_Sub22 class98_Sub22 = new Class98_Sub22(518);
                        final int[] array = { (int)(Math.random() * 9.9999999E7), (int)(9.9999999E7 * Math.random()), (int)(Math.random() * 9.9999999E7), (int)(Math.random() * 9.9999999E7) };
                        class98_Sub22.method1194(10, 116);
                        class98_Sub22.writeInt(1571862888, array[0]);
                        class98_Sub22.writeInt(1571862888, array[1]);
                        class98_Sub22.writeInt(1571862888, array[2]);
                        class98_Sub22.writeInt(1571862888, array[3]);
                        class98_Sub22.method1221(-68, 0L);
                        class98_Sub22.method1188(Class360.aString3064, (byte)113);
                        class98_Sub22.method1221(-96, Class98_Sub10_Sub19.aLong5631);
                        class98_Sub22.method1221(-71, Class98_Sub42.aLong4238);
                        class98_Sub22.method1205(Class98_Sub45.aBigInteger4253, true, Class138.aBigInteger1082);
                        Class49.method477(-5788);
                        final Class98_Sub11 method1557 = Class98_Sub46_Sub9.method1556(false);
                        final Class98_Sub22_Sub1 aClass98_Sub22_Sub1_3865 = method1557.aClass98_Sub22_Sub1_3865;
                        if (~Class98_Sub46_Sub20_Sub2.anInt6317 != 0xFFFFFFFD) {
                            aClass98_Sub22_Sub1_3865.method1194(Class298.aClass222_2484.anInt1668, -63);
                            aClass98_Sub22_Sub1_3865.writeShort(0, 1571862888);
                            final int anInt3991 = aClass98_Sub22_Sub1_3865.anInt3991;
                            aClass98_Sub22_Sub1_3865.writeInt(1571862888, 637);
                            aClass98_Sub22_Sub1_3865.method1217(class98_Sub22.aByteArray3992, class98_Sub22.anInt3991, -1, 0);
                            final int anInt3992 = aClass98_Sub22_Sub1_3865.anInt3991;
                            aClass98_Sub22_Sub1_3865.method1188(Class98_Sub5.aString3837, (byte)113);
                            aClass98_Sub22_Sub1_3865.method1194(Class4.aClass279_86.anInt2095, 58);
                            aClass98_Sub22_Sub1_3865.method1194(Class374.anInt3159, -103);
                            Class149.method2430(aClass98_Sub22_Sub1_3865, (byte)0);
                            aClass98_Sub22_Sub1_3865.method1188(Class89.aString716, (byte)113);
                            aClass98_Sub22_Sub1_3865.writeInt(1571862888, Class98_Sub10_Sub15.anInt5619);
                            Class98_Sub9.method989(aClass98_Sub22_Sub1_3865, (byte)122);
                            aClass98_Sub22_Sub1_3865.method1235(true, array, anInt3992, aClass98_Sub22_Sub1_3865.anInt3991);
                            aClass98_Sub22_Sub1_3865.method1207((byte)90, -anInt3991 + aClass98_Sub22_Sub1_3865.anInt3991);
                        }
                        else {
                            if (Class177.anInt1376 == 13) {
                                aClass98_Sub22_Sub1_3865.method1194(Class298.aClass222_2483.anInt1668, 51);
                            }
                            else {
                                aClass98_Sub22_Sub1_3865.method1194(Class298.aClass222_2481.anInt1668, -72);
                            }
                            aClass98_Sub22_Sub1_3865.writeShort(0, 1571862888);
                            final int anInt3993 = aClass98_Sub22_Sub1_3865.anInt3991;
                            aClass98_Sub22_Sub1_3865.writeInt(1571862888, 637);
                            aClass98_Sub22_Sub1_3865.method1217(class98_Sub22.aByteArray3992, class98_Sub22.anInt3991, -1, 0);
                            final int anInt3994 = aClass98_Sub22_Sub1_3865.anInt3991;
                            aClass98_Sub22_Sub1_3865.method1188(Class98_Sub5.aString3837, (byte)113);
                            aClass98_Sub22_Sub1_3865.method1194(Class146_Sub2.anInt4855, 99);
                            aClass98_Sub22_Sub1_3865.method1194(Class146_Sub2.method2391((byte)127), 107);
                            aClass98_Sub22_Sub1_3865.writeShort(Class39_Sub1.anInt3593, 1571862888);
                            aClass98_Sub22_Sub1_3865.writeShort(Class98_Sub25.anInt4024, 1571862888);
                            aClass98_Sub22_Sub1_3865.method1194(Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4055.method648((byte)124), -58);
                            Class149.method2430(aClass98_Sub22_Sub1_3865, (byte)0);
                            aClass98_Sub22_Sub1_3865.method1188(Class89.aString716, (byte)113);
                            aClass98_Sub22_Sub1_3865.writeInt(1571862888, Class98_Sub10_Sub15.anInt5619);
                            final Class98_Sub22 method1558 = Class98_Sub9.aClass98_Sub27_3856.method1288(true);
                            aClass98_Sub22_Sub1_3865.method1194(method1558.anInt3991, -109);
                            aClass98_Sub22_Sub1_3865.method1217(method1558.aByteArray3992, method1558.anInt3991, -1, 0);
                            s_Sub1.aBoolean5207 = true;
                            final Class98_Sub22 class98_Sub23 = new Class98_Sub22(Exception_Sub1.aClass98_Sub35_47.method1455((byte)116));
                            Exception_Sub1.aClass98_Sub35_47.method1453((byte)17, class98_Sub23);
                            aClass98_Sub22_Sub1_3865.method1217(class98_Sub23.aByteArray3992, class98_Sub23.aByteArray3992.length, -1, 0);
                            aClass98_Sub22_Sub1_3865.writeShort(Class75.anInt581, 1571862888);
                            aClass98_Sub22_Sub1_3865.method1221(-104, Class197.aLong1515);
                            aClass98_Sub22_Sub1_3865.method1194((Class98_Sub10_Sub7.aString5573 != null) ? 1 : 0, -34);
                            if (Class98_Sub10_Sub7.aString5573 != null) {
                                aClass98_Sub22_Sub1_3865.method1188(Class98_Sub10_Sub7.aString5573, (byte)113);
                            }
                            aClass98_Sub22_Sub1_3865.method1194(Class98_Sub42.method1479("jagtheora", 0) ? 1 : 0, 47);
                            Class98_Sub9.method989(aClass98_Sub22_Sub1_3865, (byte)100);
                            aClass98_Sub22_Sub1_3865.method1235(true, array, anInt3994, aClass98_Sub22_Sub1_3865.anInt3991);
                            aClass98_Sub22_Sub1_3865.method1207((byte)90, aClass98_Sub22_Sub1_3865.anInt3991 + -anInt3993);
                        }
                        Class98_Sub10_Sub29.sendPacket(false, method1557);
                        Class95.method920((byte)80);
                        Class331.aClass117_2811 = new Class117(array);
                        for (int i = 0; i < 4; ++i) {
                            final int[] array2 = array;
                            final int n4 = i;
                            array2[n4] += 50;
                        }
                        Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1259(array, 255);
                        Class64_Sub16.anInt3680 = 4;
                    }
                    if (Class64_Sub16.anInt3680 == 4) {
                        if (!aa_Sub1.aClass123_3561.method2203(-1949, 1)) {
                            return;
                        }
                        aa_Sub1.aClass123_3561.method2208(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992, 0, 2047, 1);
                        final int n5 = 0xFF & Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992[0];
                        if (~n5 != 0xFFFFFFEA) {
                            if (~n5 == 0xFFFFFFE2) {
                                Class64_Sub16.anInt3680 = 13;
                            }
                            else {
                                if (n5 == 1) {
                                    Class64_Sub16.anInt3680 = 5;
                                    Class369.method3952(n5, (byte)(-55));
                                    return;
                                }
                                if (n5 != 2) {
                                    if (~n5 == 0xFFFFFFF0) {
                                        Class65.anInt496 = -2;
                                        Class64_Sub16.anInt3680 = 14;
                                    }
                                    else {
                                        if (n5 == 23 && Class151_Sub9.anInt5020 < 3) {
                                            ++Class151_Sub9.anInt5020;
                                            Class64_Sub16.anInt3680 = 1;
                                            Class139.anInt1087 = 0;
                                            aa_Sub1.aClass123_3561.method2207(-114);
                                            aa_Sub1.aClass123_3561 = null;
                                            return;
                                        }
                                        Class64_Sub16.anInt3680 = 0;
                                        Class369.method3952(n5, (byte)(-55));
                                        aa_Sub1.aClass123_3561.method2207(-113);
                                        aa_Sub1.aClass123_3561 = null;
                                        Class98_Sub12.method1130(27089);
                                        return;
                                    }
                                }
                                else {
                                    Class64_Sub16.anInt3680 = 8;
                                }
                            }
                        }
                        else {
                            Class64_Sub16.anInt3680 = 7;
                        }
                    }
                    if (Class64_Sub16.anInt3680 == 6) {
                        Class49.method477(-5788);
                        final Class98_Sub11 method1559 = Class98_Sub46_Sub9.method1556(false);
                        final Class98_Sub22_Sub1 aClass98_Sub22_Sub1_3866 = method1559.aClass98_Sub22_Sub1_3865;
                        aClass98_Sub22_Sub1_3866.method1252((byte)(-104), Class331.aClass117_2811);
                        aClass98_Sub22_Sub1_3866.method1261(false, Class298.aClass222_2488.anInt1668);
                        Class98_Sub10_Sub29.sendPacket(false, method1559);
                        Class95.method920((byte)115);
                        Class64_Sub16.anInt3680 = 4;
                    }
                    else if (~Class64_Sub16.anInt3680 == 0xFFFFFFF8) {
                        if (aa_Sub1.aClass123_3561.method2203(-1949, 1)) {
                            aa_Sub1.aClass123_3561.method2208(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992, 0, 2047, 1);
                            Class98_Sub48.anInt4277 = 180 + (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992[0] & 0xFF) * 60;
                            Class64_Sub16.anInt3680 = 0;
                            Class369.method3952(21, (byte)(-55));
                            aa_Sub1.aClass123_3561.method2207(-13);
                            aa_Sub1.aClass123_3561 = null;
                            Class98_Sub12.method1130(27089);
                        }
                    }
                    else if (~Class64_Sub16.anInt3680 == 0xFFFFFFF2) {
                        if (aa_Sub1.aClass123_3561.method2203(-1949, 1)) {
                            aa_Sub1.aClass123_3561.method2208(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992, 0, 2047, 1);
                            Class64_Sub16.anInt3680 = 0;
                            Class69_Sub1.anInt5330 = (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992[0] & 0xFF);
                            Class369.method3952(29, (byte)(-55));
                            aa_Sub1.aClass123_3561.method2207(-71);
                            aa_Sub1.aClass123_3561 = null;
                            Class98_Sub12.method1130(27089);
                        }
                    }
                    else if (~Class64_Sub16.anInt3680 == 0xFFFFFFF7) {
                        if (aa_Sub1.aClass123_3561.method2203(-1949, 1)) {
                            aa_Sub1.aClass123_3561.method2208(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992, 0, 2047, 1);
                            Class98_Sub46_Sub19.anInt6069 = (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992[0] & 0xFF);
                            Class64_Sub16.anInt3680 = 9;
                        }
                    }
                    else {
                        if (~Class64_Sub16.anInt3680 == 0xFFFFFFF6) {
                            final Class98_Sub22_Sub1 aClass98_Sub22_Sub1_3867 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514;
                            if (Class98_Sub46_Sub20_Sub2.anInt6317 == 2) {
                                if (!aa_Sub1.aClass123_3561.method2203(-1949, Class98_Sub46_Sub19.anInt6069)) {
                                    return;
                                }
                                aa_Sub1.aClass123_3561.method2208(aClass98_Sub22_Sub1_3867.aByteArray3992, 0, 2047, Class98_Sub46_Sub19.anInt6069);
                                aClass98_Sub22_Sub1_3867.anInt3991 = 0;
                                Class282.anInt2125 = aClass98_Sub22_Sub1_3867.readUnsignedByte((byte)14);
                                Class47.anInt407 = aClass98_Sub22_Sub1_3867.readUnsignedByte((byte)89);
                                Class109.aBoolean933 = (~aClass98_Sub22_Sub1_3867.readUnsignedByte((byte)126) == 0xFFFFFFFE);
                                Class98_Sub10_Sub35.aBoolean5732 = (aClass98_Sub22_Sub1_3867.readUnsignedByte((byte)55) == 1);
                                Class234.aBoolean1750 = (aClass98_Sub22_Sub1_3867.readUnsignedByte((byte)(-123)) == 1);
                                Class178.aBoolean1401 = (aClass98_Sub22_Sub1_3867.readUnsignedByte((byte)(-104)) == 1);
                                za_Sub2.anInt6080 = aClass98_Sub22_Sub1_3867.readShort((byte)127);
                                Class64_Sub18.aBoolean3690 = (aClass98_Sub22_Sub1_3867.readUnsignedByte((byte)(-111)) == 1);
                                Class98_Sub46_Sub9.anInt6003 = aClass98_Sub22_Sub1_3867.method1227((byte)(-1));
                                Class79.aBoolean602 = (~aClass98_Sub22_Sub1_3867.readUnsignedByte((byte)74) == 0xFFFFFFFE);
                                Class130.aClass302_1028.method3555((byte)(-123), Class79.aBoolean602);
                                Class98_Sub46_Sub19.aClass205_6068.method2718(-71, Class79.aBoolean602);
                                Class4.aClass301_85.method3545(9179409, Class79.aBoolean602);
                            }
                            else {
                                if (!aa_Sub1.aClass123_3561.method2203(-1949, Class98_Sub46_Sub19.anInt6069)) {
                                    return;
                                }
                                aa_Sub1.aClass123_3561.method2208(aClass98_Sub22_Sub1_3867.aByteArray3992, 0, 2047, Class98_Sub46_Sub19.anInt6069);
                                aClass98_Sub22_Sub1_3867.anInt3991 = 0;
                                Class282.anInt2125 = aClass98_Sub22_Sub1_3867.readUnsignedByte((byte)(-8));
                                Class47.anInt407 = aClass98_Sub22_Sub1_3867.readUnsignedByte((byte)75);
                                Class109.aBoolean933 = (~aClass98_Sub22_Sub1_3867.readUnsignedByte((byte)(-123)) == 0xFFFFFFFE);
                                Class98_Sub10_Sub35.aBoolean5732 = (aClass98_Sub22_Sub1_3867.readUnsignedByte((byte)46) == 1);
                                Class234.aBoolean1750 = (~aClass98_Sub22_Sub1_3867.readUnsignedByte((byte)64) == 0xFFFFFFFE);
                                Class48.anInt409 = aClass98_Sub22_Sub1_3867.readUShort(false);
                                Class64_Sub18.aBoolean3690 = (~Class48.anInt409 < -1);
                                Class98_Sub1.anInt3814 = aClass98_Sub22_Sub1_3867.readShort((byte)127);
                                Class93_Sub1.anInt5489 = aClass98_Sub22_Sub1_3867.readShort((byte)127);
                                Class98_Sub10_Sub19.anInt5630 = aClass98_Sub22_Sub1_3867.readShort((byte)127);
                                Class151_Sub5.anInt4990 = aClass98_Sub22_Sub1_3867.readInt(-2);
                                Class187.aClass143_1449 = Class98_Sub43_Sub2.aClass88_5907.method868(Class151_Sub5.anInt4990, 113);
                                Class17.anInt203 = aClass98_Sub22_Sub1_3867.readUnsignedByte((byte)(-124));
                                Class98_Sub46.anInt4264 = aClass98_Sub22_Sub1_3867.readShort((byte)127);
                                Class93_Sub2.anInt5491 = aClass98_Sub22_Sub1_3867.readShort((byte)127);
                                s_Sub1.aBoolean5200 = (~aClass98_Sub22_Sub1_3867.readUnsignedByte((byte)61) == 0xFFFFFFFE);
                                final Class246_Sub3_Sub4_Sub2_Sub2 aClass246_Sub3_Sub4_Sub2_Sub2_660 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660;
                                final Class246_Sub3_Sub4_Sub2_Sub2 aClass246_Sub3_Sub4_Sub2_Sub2_661 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660;
                                final String s = Class256_Sub1.aString5157 = aClass98_Sub22_Sub1_3867.method1223(-1);
                                aClass246_Sub3_Sub4_Sub2_Sub2_661.aString6536 = s;
                                aClass246_Sub3_Sub4_Sub2_Sub2_660.aString6537 = s;
                                Class98_Sub43_Sub2.anInt5910 = aClass98_Sub22_Sub1_3867.readUnsignedByte((byte)(-120));
                                Class36.anInt349 = aClass98_Sub22_Sub1_3867.readInt(-2);
                                Class289.aClass354_2197 = new Class354();
                                Class289.aClass354_2197.anInt3011 = aClass98_Sub22_Sub1_3867.readShort((byte)127);
                                if (Class289.aClass354_2197.anInt3011 == 65535) {
                                    Class289.aClass354_2197.anInt3011 = -1;
                                }
                                aClass98_Sub22_Sub1_3867.method1223(-1);
                                Class289.aClass354_2197.aString3016 = RunClient.mainurl;
                                if (Class64_Sub29.aClass196_3720 != Class43.aClass196_375) {
                                    Class289.aClass354_2197.anInt3012 = 50000 + Class289.aClass354_2197.anInt3011;
                                    Class289.aClass354_2197.anInt3015 = 40000 + Class289.aClass354_2197.anInt3011;
                                }
                                if (Class64_Sub29.aClass196_3720 != Class98_Sub43_Sub2.aClass196_5908 && (Class98_Sub46_Sub10.aClass354_6011.method3873(120, Class98_Sub46_Sub17.aClass354_6050) || Class98_Sub46_Sub10.aClass354_6011.method3873(113, Class250.aClass354_1914))) {
                                    Class98_Sub10_Sub25.method1080((byte)96);
                                }
                            }
                            if ((!Class109.aBoolean933 || Class234.aBoolean1750) && !Class64_Sub18.aBoolean3690) {
                                try {
                                    Class203.method2704("unzap", Class76_Sub11.anApplet3799, -26978);
                                }
                                catch (Throwable t) {}
                            }
                            else {
                                try {
                                    Class203.method2704("zap", Class76_Sub11.anApplet3799, -26978);
                                }
                                catch (Throwable t2) {
                                    if (Class172.aBoolean1321) {}
                                }
                            }
                            if (Class43.aClass196_375 == Class64_Sub29.aClass196_3720) {
                                try {
                                    Class203.method2704("loggedin", Class76_Sub11.anApplet3799, -26978);
                                }
                                catch (Throwable t3) {}
                            }
                            if (~Class98_Sub46_Sub20_Sub2.anInt6317 != 0xFFFFFFFD) {
                                Class64_Sub16.anInt3680 = 0;
                                Class369.method3952(2, (byte)(-55));
                                Class98_Sub44.method1515(2);
                                Class61.method538(7, false);
                                Class92.currentIncommingOpcode = null;
                                return;
                            }
                            Class64_Sub16.anInt3680 = 11;
                        }
                        if (Class64_Sub16.anInt3680 == 11) {
                            if (!aa_Sub1.aClass123_3561.method2203(-1949, 3)) {
                                return;
                            }
                            aa_Sub1.aClass123_3561.method2208(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992, 0, 2047, 3);
                            Class64_Sub16.anInt3680 = 12;
                        }
                        if (Class64_Sub16.anInt3680 == 12) {
                            final Class98_Sub22_Sub1 aClass98_Sub22_Sub1_3868 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514;
                            aClass98_Sub22_Sub1_3868.anInt3991 = 0;
                            if (aClass98_Sub22_Sub1_3868.method1260((byte)54)) {
                                if (!aa_Sub1.aClass123_3561.method2203(-1949, 1)) {
                                    return;
                                }
                                aa_Sub1.aClass123_3561.method2208(aClass98_Sub22_Sub1_3868.aByteArray3992, 3, 2047, 1);
                            }
                            Class92.currentIncommingOpcode = Class313.method3629(101)[aClass98_Sub22_Sub1_3868.method1255(0)];
                            Class65.anInt496 = aClass98_Sub22_Sub1_3868.readShort((byte)127);
                            Class64_Sub16.anInt3680 = 10;
                        }
                        if (Class64_Sub16.anInt3680 == 10) {
                            if (aa_Sub1.aClass123_3561.method2203(-1949, Class65.anInt496)) {
                                aa_Sub1.aClass123_3561.method2208(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992, 0, 2047, Class65.anInt496);
                                Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 = 0;
                                final int anInt3995 = Class65.anInt496;
                                Class64_Sub16.anInt3680 = 0;
                                Class369.method3952(2, (byte)(-55));
                                Class134_Sub1.method2247(-104);
                                Class373.method3964(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514, 15811816);
                                Class160.anInt1258 = -1;
                                if (Class92.currentIncommingOpcode == Class150.aClass58_1212) {
                                    Class98_Sub36.method1459(-1048016408);
                                }
                                else {
                                    Class98_Sub41.method1475(68);
                                }
                                if (~anInt3995 != ~Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991) {
                                    throw new RuntimeException("lswp pos:" + Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 + " psize:" + anInt3995);
                                }
                                Class92.currentIncommingOpcode = null;
                            }
                        }
                        else if (Class64_Sub16.anInt3680 == 14) {
                            if (~Class65.anInt496 == 0x1) {
                                if (!aa_Sub1.aClass123_3561.method2203(-1949, 2)) {
                                    return;
                                }
                                aa_Sub1.aClass123_3561.method2208(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992, 0, 2047, 2);
                                Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 = 0;
                                Class65.anInt496 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                            }
                            if (aa_Sub1.aClass123_3561.method2203(-1949, Class65.anInt496)) {
                                aa_Sub1.aClass123_3561.method2208(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992, 0, 2047, Class65.anInt496);
                                Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 = 0;
                                Class64_Sub16.anInt3680 = 0;
                                final int anInt3996 = Class65.anInt496;
                                Class369.method3952(15, (byte)(-55));
                                Class147.method2412(-108);
                                Class373.method3964(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514, 15811816);
                                if (~anInt3996 != ~Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991) {
                                    throw new RuntimeException("lswpr pos:" + Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 + " psize:" + anInt3996);
                                }
                                Class92.currentIncommingOpcode = null;
                            }
                        }
                    }
                }
                catch (IOException ex2) {
                    if (aa_Sub1.aClass123_3561 != null) {
                        aa_Sub1.aClass123_3561.method2207(-86);
                        aa_Sub1.aClass123_3561 = null;
                    }
                    if (~Class151_Sub9.anInt5020 <= -4) {
                        Class64_Sub16.anInt3680 = 0;
                        Class369.method3952(-4, (byte)(-55));
                        Class98_Sub12.method1130(27089);
                    }
                    else {
                        if (~Class98_Sub46_Sub20_Sub2.anInt6317 != 0xFFFFFFFD) {
                            Class299_Sub2.aClass354_5297.method3874(0);
                        }
                        else {
                            Class98_Sub46_Sub10.aClass354_6011.method3874(0);
                        }
                        ++Class151_Sub9.anInt5020;
                        Class64_Sub16.anInt3680 = 1;
                        Class139.anInt1087 = 0;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.A(" + n + ')');
        }
    }
    
    @Override
    final int method3749() {
        try {
            return this.anInt5405 + (this.aClass42_Sub1_Sub1_5407.anInt6204 + this.anInt5400);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.T()");
        }
    }
    
    @Override
    final void method3733(float n, float n2, float n3, float n4, float n5, float n6, final int n7, final int n8, final int n9, final int n10) {
        try {
            if (this.aBoolean5401) {
                final float n11 = this.method3737();
                final float n12 = this.method3749();
                final float n13 = (-n + n3) / n11;
                final float n14 = (n4 - n2) / n11;
                final float n15 = (-n + n5) / n12;
                final float n16 = (-n2 + n6) / n12;
                final float n17 = n15 * this.anInt5405;
                final float n18 = n16 * this.anInt5405;
                final float n19 = this.anInt5404 * n13;
                final float n20 = this.anInt5404 * n14;
                final float n21 = -n13 * this.anInt5402;
                final float n22 = this.anInt5402 * -n14;
                final float n23 = this.anInt5400 * -n15;
                final float n24 = -n16 * this.anInt5400;
                n2 = n20 + n2 + n18;
                n4 = n18 + (n4 + n22);
                n = n19 + n + n17;
                n5 = n23 + (n5 + n19);
                n3 = n17 + (n21 + n3);
                n6 = n24 + (n20 + n6);
            }
            final float n25 = n5 + (-n + n3);
            final float n26 = n4 + (-n2 + n6);
            this.aClass42_Sub1_Sub1_5407.method372(-28003, (n10 & 0x1) != 0x0);
            this.aHa_Sub1_5406.method1829((byte)(-114));
            this.aHa_Sub1_5406.method1863(1, this.aClass42_Sub1_Sub1_5407);
            this.aHa_Sub1_5406.method1870((byte)(-117), n9);
            this.aHa_Sub1_5406.method1896(260, n7);
            OpenGL.glColor4ub((byte)(n8 >> 651809520), (byte)(n8 >> -1535919032), (byte)n8, (byte)(n8 >> 1835121464));
            OpenGL.glBegin(7);
            OpenGL.glTexCoord2f(0.0f, this.aClass42_Sub1_Sub1_5407.aFloat6209);
            OpenGL.glVertex2f(n, n2);
            OpenGL.glTexCoord2f(0.0f, 0.0f);
            OpenGL.glVertex2f(n5, n6);
            OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, 0.0f);
            OpenGL.glVertex2f(n25, n26);
            OpenGL.glTexCoord2f(this.aClass42_Sub1_Sub1_5407.aFloat6205, this.aClass42_Sub1_Sub1_5407.aFloat6209);
            OpenGL.glVertex2f(n3, n4);
            OpenGL.glEnd();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.BA(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ')');
        }
    }
    
    @Override
    final void method3747(float n, float n2, float n3, float n4, float n5, float n6, final int n7, final aa aa, final int n8, final int n9) {
        try {
            final Class42_Sub1_Sub1 aClass42_Sub1_Sub1_3568 = ((aa_Sub3)aa).aClass42_Sub1_Sub1_3568;
            if (this.aBoolean5401) {
                final float n10 = this.method3737();
                final float n11 = this.method3749();
                final float n12 = (n3 - n) / n10;
                final float n13 = (-n2 + n4) / n10;
                final float n14 = (n5 - n) / n11;
                final float n15 = (-n2 + n6) / n11;
                final float n16 = this.anInt5405 * n14;
                final float n17 = this.anInt5405 * n15;
                final float n18 = n12 * this.anInt5404;
                final float n19 = this.anInt5404 * n13;
                final float n20 = this.anInt5402 * -n12;
                final float n21 = this.anInt5402 * -n13;
                final float n22 = -n14 * this.anInt5400;
                final float n23 = this.anInt5400 * -n15;
                n = n + n18 + n16;
                n4 = n21 + n4 + n17;
                n5 = n18 + n5 + n22;
                n3 = n3 + n20 + n16;
                n2 = n17 + (n19 + n2);
                n6 = n23 + (n6 + n19);
            }
            final float n24 = n3 - n + n5;
            final float n25 = n4 + (n6 - n2);
            this.aClass42_Sub1_Sub1_5407.method372(-28003, ~(n7 & 0x1) != -1);
            this.aHa_Sub1_5406.method1829((byte)(-98));
            this.aHa_Sub1_5406.method1863(1, this.aClass42_Sub1_Sub1_5407);
            this.aHa_Sub1_5406.method1896(260, 1);
            this.aHa_Sub1_5406.method1845(1, 847872872);
            this.aHa_Sub1_5406.method1863(1, aClass42_Sub1_Sub1_3568);
            this.aHa_Sub1_5406.method1899(8448, 8960, 7681);
            this.aHa_Sub1_5406.method1840(0, 768, -65, 34168);
            this.aHa_Sub1_5406.method1870((byte)(-24), 1);
            final float n26 = aClass42_Sub1_Sub1_3568.aFloat6205 / aClass42_Sub1_Sub1_3568.anInt6207;
            final float n27 = aClass42_Sub1_Sub1_3568.aFloat6209 / aClass42_Sub1_Sub1_3568.anInt6204;
            OpenGL.glBegin(7);
            OpenGL.glColor3f(1.0f, 1.0f, 1.0f);
            OpenGL.glMultiTexCoord2f(33984, 0.0f, this.aClass42_Sub1_Sub1_5407.aFloat6209);
            OpenGL.glMultiTexCoord2f(33985, n26 * (-n8 + n), aClass42_Sub1_Sub1_3568.aFloat6209 - n27 * (n2 - n9));
            OpenGL.glVertex2f(n, n2);
            OpenGL.glMultiTexCoord2f(33984, 0.0f, 0.0f);
            OpenGL.glMultiTexCoord2f(33985, (n5 - n8) * n26, -(n27 * (-n9 + n6)) + aClass42_Sub1_Sub1_3568.aFloat6209);
            OpenGL.glVertex2f(n5, n6);
            OpenGL.glMultiTexCoord2f(33984, this.aClass42_Sub1_Sub1_5407.aFloat6205, 0.0f);
            OpenGL.glMultiTexCoord2f(33985, (-n8 + n24) * n26, -((n25 - n9) * n27) + aClass42_Sub1_Sub1_3568.aFloat6209);
            OpenGL.glVertex2f(n24, n25);
            OpenGL.glMultiTexCoord2f(33984, this.aClass42_Sub1_Sub1_5407.aFloat6205, this.aClass42_Sub1_Sub1_5407.aFloat6209);
            OpenGL.glMultiTexCoord2f(33985, n26 * (n3 - n8), -((-n9 + n4) * n27) + aClass42_Sub1_Sub1_3568.aFloat6209);
            OpenGL.glVertex2f(n3, n4);
            OpenGL.glEnd();
            this.aHa_Sub1_5406.method1840(0, 768, 91, 5890);
            this.aHa_Sub1_5406.method1896(260, 0);
            this.aHa_Sub1_5406.method1863(1, null);
            this.aHa_Sub1_5406.method1845(0, 847872872);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.L(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + ((aa != null) ? "{...}" : "null") + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    @Override
    final void method3741(final int[] array) {
        try {
            array[0] = this.anInt5404;
            array[3] = this.anInt5400;
            array[1] = this.anInt5405;
            array[2] = this.anInt5402;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.S(" + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method3729(int n, int n2, final aa aa, final int n3, final int n4) {
        try {
            final Class42_Sub1_Sub1 aClass42_Sub1_Sub1_3568 = ((aa_Sub3)aa).aClass42_Sub1_Sub1_3568;
            this.aClass42_Sub1_Sub1_5407.method372(-28003, false);
            this.aHa_Sub1_5406.method1829((byte)(-80));
            this.aHa_Sub1_5406.method1863(1, this.aClass42_Sub1_Sub1_5407);
            this.aHa_Sub1_5406.method1896(260, 1);
            this.aHa_Sub1_5406.method1845(1, 847872872);
            this.aHa_Sub1_5406.method1863(1, aClass42_Sub1_Sub1_3568);
            this.aHa_Sub1_5406.method1899(8448, 8960, 7681);
            this.aHa_Sub1_5406.method1840(0, 768, 102, 34168);
            this.aHa_Sub1_5406.method1870((byte)(-59), 1);
            n += this.anInt5404;
            n2 += this.anInt5405;
            final int n5 = n - -this.aClass42_Sub1_Sub1_5407.anInt6207;
            final int n6 = n2 - -this.aClass42_Sub1_Sub1_5407.anInt6204;
            final float n7 = aClass42_Sub1_Sub1_3568.aFloat6205 / aClass42_Sub1_Sub1_3568.anInt6207;
            final float n8 = aClass42_Sub1_Sub1_3568.aFloat6209 / aClass42_Sub1_Sub1_3568.anInt6204;
            final float n9 = (n + -n3) * n7;
            final float n10 = (-n3 + n5) * n7;
            final float n11 = -((-n4 + n2) * n8) + aClass42_Sub1_Sub1_3568.aFloat6209;
            final float n12 = aClass42_Sub1_Sub1_3568.aFloat6209 - n8 * (-n4 + n6);
            OpenGL.glBegin(7);
            OpenGL.glColor3f(1.0f, 1.0f, 1.0f);
            OpenGL.glMultiTexCoord2f(33984, 0.0f, this.aClass42_Sub1_Sub1_5407.aFloat6209);
            OpenGL.glMultiTexCoord2f(33985, n9, n11);
            OpenGL.glVertex2i(n, n2);
            OpenGL.glMultiTexCoord2f(33984, 0.0f, 0.0f);
            OpenGL.glMultiTexCoord2f(33985, n9, n12);
            OpenGL.glVertex2i(n, this.aClass42_Sub1_Sub1_5407.anInt6204 + n2);
            OpenGL.glMultiTexCoord2f(33984, this.aClass42_Sub1_Sub1_5407.aFloat6205, 0.0f);
            OpenGL.glMultiTexCoord2f(33985, n10, n12);
            OpenGL.glVertex2i(this.aClass42_Sub1_Sub1_5407.anInt6207 + n, this.aClass42_Sub1_Sub1_5407.anInt6204 + n2);
            OpenGL.glMultiTexCoord2f(33984, this.aClass42_Sub1_Sub1_5407.aFloat6205, this.aClass42_Sub1_Sub1_5407.aFloat6209);
            OpenGL.glMultiTexCoord2f(33985, n10, n11);
            OpenGL.glVertex2i(n - -this.aClass42_Sub1_Sub1_5407.anInt6207, n2);
            OpenGL.glEnd();
            this.aHa_Sub1_5406.method1840(0, 768, 87, 5890);
            this.aHa_Sub1_5406.method1896(260, 0);
            this.aHa_Sub1_5406.method1863(1, null);
            this.aHa_Sub1_5406.method1845(0, 847872872);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.D(" + n + ',' + n2 + ',' + ((aa != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final void method3736(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            if (this.aHa_Sub1_5406.aBoolean4367) {
                final int[] na = this.aHa_Sub1_5406.na(n5, n6, n3, n4);
                if (na != null) {
                    for (int n7 = 0; ~na.length < ~n7; ++n7) {
                        na[n7] = Class41.method366(na[n7], -16777216);
                    }
                    this.method3754(n, n2, n3, n4, na, 0, n3);
                }
            }
            else {
                this.aClass42_Sub1_Sub1_5407.method380(n3, n, n2, n6, n5, 120, n4);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.J(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    private final void method3754(final int n, final int n2, final int n3, final int n4, final int[] array, final int n5, final int n6) {
        try {
            this.aClass42_Sub1_Sub1_5407.method379(n4, array, n5, 3656, true, n2, n6, n3, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((array != null) ? "{...}" : "null") + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    @Override
    final int method3734() {
        try {
            return this.aClass42_Sub1_Sub1_5407.anInt6207;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.AA()");
        }
    }
    
    Class332_Sub1(final ha_Sub1 aHa_Sub1_5406, final int n, final int n2, final boolean b) {
        this.anInt5400 = 0;
        this.anInt5402 = 0;
        this.aBoolean5401 = false;
        this.anInt5405 = 0;
        this.anInt5404 = 0;
        this.anInt5408 = 0;
        try {
            this.aHa_Sub1_5406 = aHa_Sub1_5406;
            this.aClass42_Sub1_Sub1_5407 = Class82.method823(n2, aHa_Sub1_5406, n, -97, b ? 6408 : 6407);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.<init>(" + ((aHa_Sub1_5406 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    Class332_Sub1(final ha_Sub1 aHa_Sub1_5406, final int n, final int n2, final int n3, final int n4) {
        this.anInt5400 = 0;
        this.anInt5402 = 0;
        this.aBoolean5401 = false;
        this.anInt5405 = 0;
        this.anInt5404 = 0;
        this.anInt5408 = 0;
        try {
            this.aHa_Sub1_5406 = aHa_Sub1_5406;
            this.aClass42_Sub1_Sub1_5407 = aa_Sub1.method153((byte)(-125), n4, aHa_Sub1_5406, n3, n, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.<init>(" + ((aHa_Sub1_5406 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    Class332_Sub1(final ha_Sub1 aHa_Sub1_5406, final int n, final int n2, final int[] array, final int n3, final int n4) {
        this.anInt5400 = 0;
        this.anInt5402 = 0;
        this.aBoolean5401 = false;
        this.anInt5405 = 0;
        this.anInt5404 = 0;
        this.anInt5408 = 0;
        try {
            this.aHa_Sub1_5406 = aHa_Sub1_5406;
            this.aClass42_Sub1_Sub1_5407 = Class246_Sub9.method3136(aHa_Sub1_5406, false, array, n, (byte)120, n4, n3, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ol.<init>(" + ((aHa_Sub1_5406 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ')');
        }
    }
}
