import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class283
{
    private int anInt2133;
    private Class288 aClass288_2134;
    private ha_Sub1 aHa_Sub1_2135;
    private Class288 aClass288_2136;
    private Class148 aClass148_2137;
    private int anInt2138;
    static IncomingOpcode aClass58_2139;
    private int anInt2140;
    private int anInt2141;
    private Class288 aClass288_2142;
    static IncomingOpcode aClass58_2143;
    private boolean aBoolean2144;
    static double aDouble2145;
    static OutgoingOpcode aClass171_2146;
    private Class98_Sub46_Sub14 aClass98_Sub46_Sub14_2147;
    private boolean aBoolean2148;
    private Class42_Sub1 aClass42_Sub1_2149;
    private int anInt2150;
    private boolean aBoolean2151;
    private boolean aBoolean2152;
    private boolean aBoolean2153;
    private Class42_Sub1[] aClass42_Sub1Array2154;
    private int anInt2155;
    private Class98_Sub46_Sub14 aClass98_Sub46_Sub14_2156;
    private boolean aBoolean2157;
    static Class aClass2158;
    
    private final boolean method3339(final int n) {
        try {
            if (this.aBoolean2151) {
                if (this.aClass98_Sub46_Sub14_2147 != null) {
                    this.aClass98_Sub46_Sub14_2147.method1603(n ^ 0x5042);
                    this.aClass98_Sub46_Sub14_2147 = null;
                }
                if (this.aClass42_Sub1_2149 != null) {
                    this.aClass42_Sub1_2149.method375(true);
                    this.aClass42_Sub1_2149 = null;
                }
                if (this.aClass288_2142 != null) {
                    this.aClass98_Sub46_Sub14_2147 = new Class98_Sub46_Sub14(this.aHa_Sub1_2135, 6402, this.anInt2141, this.anInt2140, this.aHa_Sub1_2135.anInt4310);
                }
                if (this.aBoolean2152) {
                    this.aClass42_Sub1_2149 = new Class42_Sub1(this.aHa_Sub1_2135, 34037, 6402, this.anInt2141, this.anInt2140);
                }
                else if (this.aClass98_Sub46_Sub14_2147 == null) {
                    this.aClass98_Sub46_Sub14_2147 = new Class98_Sub46_Sub14(this.aHa_Sub1_2135, 6402, this.anInt2141, this.anInt2140);
                }
                this.aBoolean2151 = false;
                this.aBoolean2148 = true;
                this.aBoolean2157 = true;
            }
            if (this.aBoolean2153) {
                if (this.aClass98_Sub46_Sub14_2156 != null) {
                    this.aClass98_Sub46_Sub14_2156.method1603(0);
                    this.aClass98_Sub46_Sub14_2156 = null;
                }
                if (this.aClass42_Sub1Array2154[0] != null) {
                    this.aClass42_Sub1Array2154[0].method375(true);
                    this.aClass42_Sub1Array2154[0] = null;
                }
                if (this.aClass42_Sub1Array2154[1] != null) {
                    this.aClass42_Sub1Array2154[1].method375(true);
                    this.aClass42_Sub1Array2154[1] = null;
                }
                if (this.aClass288_2142 != null) {
                    this.aClass98_Sub46_Sub14_2156 = new Class98_Sub46_Sub14(this.aHa_Sub1_2135, this.anInt2150, this.anInt2141, this.anInt2140, this.aHa_Sub1_2135.anInt4310);
                }
                this.aClass42_Sub1Array2154[0] = new Class42_Sub1(this.aHa_Sub1_2135, 34037, this.anInt2150, this.anInt2141, this.anInt2140);
                this.aClass42_Sub1Array2154[1] = ((this.anInt2155 <= 1) ? null : new Class42_Sub1(this.aHa_Sub1_2135, 34037, this.anInt2150, this.anInt2141, this.anInt2140));
                this.aBoolean2157 = true;
                this.aBoolean2153 = false;
                this.aBoolean2148 = true;
            }
            if (this.aBoolean2148) {
                if (this.aClass288_2142 == null) {
                    this.aHa_Sub1_2135.method1898(true, this.aClass288_2136);
                    this.aClass288_2136.method3401(0, true);
                    this.aClass288_2136.method3401(1, true);
                    this.aClass288_2136.method3401(8, true);
                    this.aClass288_2136.method3395(0, this.aClass42_Sub1Array2154[0], 119);
                    if (this.anInt2155 > 1) {
                        this.aClass288_2136.method3395(1, this.aClass42_Sub1Array2154[1], n - 20431);
                    }
                    if (this.aBoolean2152) {
                        this.aClass288_2136.method3395(8, this.aClass42_Sub1_2149, n ^ 0x503B);
                    }
                    else {
                        this.aClass288_2136.method3406((byte)115, 8, this.aClass98_Sub46_Sub14_2147);
                    }
                    this.aHa_Sub1_2135.method1907(this.aClass288_2136, -1);
                }
                else {
                    this.aHa_Sub1_2135.method1898(true, this.aClass288_2136);
                    this.aClass288_2136.method3401(0, true);
                    this.aClass288_2136.method3401(1, true);
                    this.aClass288_2136.method3401(8, true);
                    this.aClass288_2136.method3395(0, this.aClass42_Sub1Array2154[0], n ^ 0x5034);
                    if (this.anInt2155 > 1) {
                        this.aClass288_2136.method3395(1, this.aClass42_Sub1Array2154[1], 127);
                    }
                    if (this.aBoolean2152) {
                        this.aClass288_2136.method3395(8, this.aClass42_Sub1_2149, 113);
                    }
                    this.aHa_Sub1_2135.method1907(this.aClass288_2136, -1);
                    this.aHa_Sub1_2135.method1898(true, this.aClass288_2142);
                    this.aClass288_2142.method3401(0, true);
                    this.aClass288_2142.method3401(8, true);
                    this.aClass288_2142.method3406((byte)78, 0, this.aClass98_Sub46_Sub14_2156);
                    this.aClass288_2142.method3406((byte)66, 8, this.aClass98_Sub46_Sub14_2147);
                    this.aHa_Sub1_2135.method1907(this.aClass288_2142, -1);
                }
                this.aBoolean2157 = true;
                this.aBoolean2148 = false;
            }
            if (this.aBoolean2157) {
                this.aHa_Sub1_2135.method1898(true, this.aClass288_2134);
                this.aBoolean2157 = !this.aClass288_2134.method3403((byte)74);
                this.aHa_Sub1_2135.method1907(this.aClass288_2134, -1);
            }
            return n == 20546 && !this.aBoolean2157;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rm.K(" + n + ')');
        }
    }
    
    private final void method3340(final int n) {
        try {
            if (n != 6408) {
                this.anInt2141 = 125;
            }
            boolean aBoolean2152 = false;
            int n2 = 0;
            int anInt2155 = 0;
            for (Class98_Sub28 class98_Sub28 = (Class98_Sub28)this.aClass148_2137.method2418(32); class98_Sub28 != null; class98_Sub28 = (Class98_Sub28)this.aClass148_2137.method2417(118)) {
                final int method1299 = class98_Sub28.method1299(false);
                anInt2155 += class98_Sub28.method1303(n - 6408);
                if (n2 < method1299) {
                    n2 = method1299;
                }
                aBoolean2152 |= class98_Sub28.method1301(-11);
            }
            int anInt2156;
            if (n2 == 2) {
                anInt2156 = 34836;
            }
            else if (n2 == 1) {
                anInt2156 = 34842;
            }
            else {
                anInt2156 = 6408;
            }
            if (~anInt2156 != ~this.anInt2150) {
                this.anInt2150 = anInt2156;
                this.aBoolean2153 = true;
            }
            final int n3 = (~this.anInt2155 < -3) ? 2 : this.anInt2155;
            final int n4 = (anInt2155 > 2) ? 2 : anInt2155;
            this.anInt2155 = anInt2155;
            if (!aBoolean2152 == this.aBoolean2152) {
                this.aBoolean2152 = aBoolean2152;
                this.aBoolean2151 = true;
            }
            if (n4 != n3) {
                final boolean b = true;
                this.aBoolean2153 = b;
                this.aBoolean2148 = b;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rm.B(" + n + ')');
        }
    }
    
    final void method3341(final Class98_Sub28 class98_Sub28, final int n) {
        try {
            class98_Sub28.aBoolean4081 = false;
            class98_Sub28.method1304((byte)(-49));
            if (n != -17722) {
                this.method3339(-66);
            }
            class98_Sub28.method942(59);
            this.method3340(n + 24130);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rm.G(" + ((class98_Sub28 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final void method3342(final int n) {
        try {
            if (this.aBoolean2144) {
                if (this.aClass288_2142 != null) {
                    this.aHa_Sub1_2135.method1869(0, this.aClass288_2142);
                    int n2 = 16384;
                    this.aHa_Sub1_2135.method1835(this.aClass288_2136, 0);
                    this.aClass288_2142.method3398(-63, 0);
                    this.aClass288_2136.method3404(0, 0);
                    if (this.aBoolean2152) {
                        n2 |= 0x100;
                    }
                    OpenGL.glBlitFramebufferEXT(0, 0, this.anInt2141, this.anInt2140, 0, 0, this.anInt2141, this.anInt2140, n2, 9728);
                    this.aHa_Sub1_2135.method1832(this.aClass288_2142, 19);
                    this.aHa_Sub1_2135.method1880(122, this.aClass288_2136);
                }
                this.aHa_Sub1_2135.method1829((byte)(-99));
                if (n < -114) {
                    this.aHa_Sub1_2135.method1870((byte)(-99), 0);
                    this.aHa_Sub1_2135.method1896(260, 1);
                    this.aHa_Sub1_2135.la();
                    int n3 = 0;
                    int n4 = 1;
                    Class98_Sub28 class98_Sub29;
                    for (Class98_Sub28 class98_Sub28 = (Class98_Sub28)this.aClass148_2137.method2418(32); class98_Sub28 != null; class98_Sub28 = class98_Sub29) {
                        class98_Sub29 = (Class98_Sub28)this.aClass148_2137.method2417(127);
                        for (int method1303 = class98_Sub28.method1303(0), n5 = 0; ~n5 > ~method1303; ++n5) {
                            class98_Sub28.method1302(this.aClass42_Sub1_2149, n5, (byte)(-64), this.aClass42_Sub1Array2154[n3]);
                            if (class98_Sub29 != null || method1303 - 1 != n5) {
                                this.aClass288_2136.method3404(0, n4);
                                OpenGL.glBegin(7);
                                OpenGL.glTexCoord2f(0.0f, this.anInt2140);
                                OpenGL.glMultiTexCoord2f(33985, 0.0f, 1.0f);
                                OpenGL.glVertex2i(0, 0);
                                OpenGL.glTexCoord2f(0.0f, 0.0f);
                                OpenGL.glMultiTexCoord2f(33985, 0.0f, 0.0f);
                                OpenGL.glVertex2i(0, this.anInt2140);
                                OpenGL.glTexCoord2f(this.anInt2141, 0.0f);
                                OpenGL.glMultiTexCoord2f(33985, 1.0f, 0.0f);
                                OpenGL.glVertex2i(this.anInt2141, this.anInt2140);
                                OpenGL.glTexCoord2f(this.anInt2141, this.anInt2140);
                                OpenGL.glMultiTexCoord2f(33985, 1.0f, 1.0f);
                                OpenGL.glVertex2i(this.anInt2141, 0);
                                OpenGL.glEnd();
                            }
                            else {
                                this.aHa_Sub1_2135.method1907(this.aClass288_2136, -1);
                                this.aHa_Sub1_2135.method1888(0, 78, 0);
                                OpenGL.glBegin(7);
                                OpenGL.glTexCoord2f(0.0f, this.anInt2140);
                                OpenGL.glMultiTexCoord2f(33985, 0.0f, 1.0f);
                                OpenGL.glVertex2i(this.anInt2138, this.anInt2133);
                                OpenGL.glTexCoord2f(0.0f, 0.0f);
                                OpenGL.glMultiTexCoord2f(33985, 0.0f, 0.0f);
                                OpenGL.glVertex2i(this.anInt2138, this.anInt2140 + this.anInt2133);
                                OpenGL.glTexCoord2f(this.anInt2141, 0.0f);
                                OpenGL.glMultiTexCoord2f(33985, 1.0f, 0.0f);
                                OpenGL.glVertex2i(this.anInt2141 + this.anInt2138, this.anInt2133 + this.anInt2140);
                                OpenGL.glTexCoord2f(this.anInt2141, this.anInt2140);
                                OpenGL.glMultiTexCoord2f(33985, 1.0f, 1.0f);
                                OpenGL.glVertex2i(this.anInt2138 + this.anInt2141, this.anInt2133);
                                OpenGL.glEnd();
                            }
                            class98_Sub28.method1297(n5, (byte)115);
                            n3 = (1 + n3 & 0x1);
                            n4 = (0x1 & n4 + 1);
                        }
                    }
                    this.aBoolean2144 = false;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rm.E(" + n + ')');
        }
    }
    
    static final void method3343(final Class246_Sub3[] array, final int n, final int n2) {
        if (n < n2) {
            final int n3 = (n + n2) / 2;
            int n4 = n;
            final Class246_Sub3 class246_Sub3 = array[n3];
            array[n3] = array[n2];
            array[n2] = class246_Sub3;
            final int anInt5083 = class246_Sub3.anInt5083;
            for (int i = n; i < n2; ++i) {
                if (array[i].anInt5083 < anInt5083 + (i & 0x1)) {
                    final Class246_Sub3 class246_Sub4 = array[i];
                    array[i] = array[n4];
                    array[n4++] = class246_Sub4;
                }
            }
            array[n2] = array[n4];
            array[n4] = class246_Sub3;
            method3343(array, n, n4 - 1);
            method3343(array, n4 + 1, n2);
        }
    }
    
    final boolean method3344(final Class98_Sub28 class98_Sub28, final int n) {
        try {
            if (this.aClass288_2134 != null) {
                if (class98_Sub28.method1298(72) || class98_Sub28.method1294((byte)81)) {
                    this.aClass148_2137.method2419(class98_Sub28, -20911);
                    this.method3340(6408);
                    if (this.method3339(20546)) {
                        if (~this.anInt2141 != 0x0 && ~this.anInt2140 != 0x0) {
                            class98_Sub28.method1295(this.anInt2140, this.anInt2141, false);
                        }
                        return class98_Sub28.aBoolean4081 = true;
                    }
                }
                this.method3341(class98_Sub28, -17722);
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rm.L(" + ((class98_Sub28 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final boolean method3345(final int anInt2141, final int anInt2142, final int anInt2143, final int anInt2144, final int n) {
        try {
            if (n != 0) {
                return false;
            }
            if (this.aClass288_2134 == null || this.aClass148_2137.method2420(-128)) {
                return false;
            }
            if (anInt2141 != this.anInt2141 || ~this.anInt2140 != ~anInt2143) {
                this.anInt2141 = anInt2141;
                this.anInt2140 = anInt2143;
                for (Class98 class98 = this.aClass148_2137.method2418(32); this.aClass148_2137.aClass98_1198 != class98; class98 = class98.aClass98_836) {
                    ((Class98_Sub28)class98).method1295(this.anInt2140, this.anInt2141, false);
                }
                this.aBoolean2151 = true;
                this.aBoolean2148 = true;
                this.aBoolean2153 = true;
            }
            if (this.method3339(20546)) {
                this.aBoolean2144 = true;
                this.anInt2138 = anInt2144;
                this.anInt2133 = anInt2142;
                this.aHa_Sub1_2135.method1898(true, this.aClass288_2134);
                this.aClass288_2134.method3404(0, 0);
                this.aHa_Sub1_2135.method1888(this.anInt2140 + (this.anInt2133 - this.aHa_Sub1_2135.anInt4304), 61, -this.anInt2138);
                return true;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rm.A(" + anInt2141 + ',' + anInt2142 + ',' + anInt2143 + ',' + anInt2144 + ',' + n + ')');
        }
    }
    
    final void method3346(final byte b) {
        try {
            if (b >= -116) {
                method3352(21);
            }
            final Class288 aClass288_2134 = null;
            this.aClass288_2136 = aClass288_2134;
            this.aClass288_2142 = aClass288_2134;
            this.aClass288_2134 = aClass288_2134;
            this.aClass98_Sub46_Sub14_2147 = null;
            this.aClass98_Sub46_Sub14_2156 = null;
            this.aClass42_Sub1_2149 = null;
            this.aClass42_Sub1Array2154 = null;
            if (!this.aClass148_2137.method2420(-128)) {
                for (Class98 class98 = this.aClass148_2137.method2418(32); class98 != this.aClass148_2137.aClass98_1198; class98 = class98.aClass98_836) {
                    ((Class98_Sub28)class98).method1304((byte)(-93));
                }
            }
            final boolean b2 = true;
            this.anInt2140 = (b2 ? 1 : 0);
            this.anInt2141 = (b2 ? 1 : 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rm.H(" + b + ')');
        }
    }
    
    static final void method3347(final int n, Color color, Color color2, Color color3, final String s, final int n2) {
        try {
            try {
                final Graphics graphics = Class42_Sub3.aCanvas5361.getGraphics();
                if (Class98_Sub10_Sub7.aFont5576 == null) {
                    Class98_Sub10_Sub7.aFont5576 = new Font("Helvetica", 1, 13);
                }
                if (color2 == null) {
                    color2 = new Color(140, 17, 17);
                }
                if (color == null) {
                    color = new Color(140, 17, 17);
                }
                if (color3 == null) {
                    color3 = new Color(255, 255, 255);
                }
                try {
                    if (Class3.anImage74 == null) {
                        Class3.anImage74 = Class42_Sub3.aCanvas5361.createImage(Class39_Sub1.anInt3593, Class98_Sub25.anInt4024);
                    }
                    final Graphics graphics2 = Class3.anImage74.getGraphics();
                    graphics2.setColor(Color.black);
                    graphics2.fillRect(0, 0, Class39_Sub1.anInt3593, Class98_Sub25.anInt4024);
                    final int n3 = Class39_Sub1.anInt3593 / 2 - 152;
                    final int n4 = Class98_Sub25.anInt4024 / 2 - 18;
                    graphics2.setColor(color);
                    if (n2 == 90) {
                        graphics2.drawRect(n3, n4, 303, 33);
                        graphics2.setColor(color2);
                        graphics2.fillRect(n3 + 2, n4 + 2, 3 * n, 30);
                        graphics2.setColor(Color.black);
                        graphics2.drawRect(1 + n3, 1 + n4, 301, 31);
                        graphics2.fillRect(2 + (n3 - -(n * 3)), n4 + 2, -(n * 3) + 300, 30);
                        graphics2.setFont(Class98_Sub10_Sub7.aFont5576);
                        graphics2.setColor(color3);
                        graphics2.drawString(s, n3 - -((-(s.length() * 6) + 304) / 2), n4 + 22);
                        if (Class265.aString1978 != null) {
                            graphics2.setFont(Class98_Sub10_Sub7.aFont5576);
                            graphics2.setColor(color3);
                            graphics2.drawString(Class265.aString1978, Class39_Sub1.anInt3593 / 2 - Class265.aString1978.length() * 6 / 2, Class98_Sub25.anInt4024 / 2 - 26);
                        }
                        graphics.drawImage(Class3.anImage74, 0, 0, null);
                    }
                }
                catch (Exception ex2) {
                    graphics.setColor(Color.black);
                    graphics.fillRect(0, 0, Class39_Sub1.anInt3593, Class98_Sub25.anInt4024);
                    final int n5 = Class39_Sub1.anInt3593 / 2 - 152;
                    final int n6 = Class98_Sub25.anInt4024 / 2 - 18;
                    graphics.setColor(color);
                    graphics.drawRect(n5, n6, 303, 33);
                    graphics.setColor(color2);
                    graphics.fillRect(n5 + 2, 2 + n6, 3 * n, 30);
                    graphics.setColor(Color.black);
                    graphics.drawRect(n5 + 1, n6 + 1, 301, 31);
                    graphics.fillRect(3 * n + n5 + 2, 2 + n6, 300 + -(n * 3), 30);
                    graphics.setFont(Class98_Sub10_Sub7.aFont5576);
                    graphics.setColor(color3);
                    if (Class265.aString1978 != null) {
                        graphics.setFont(Class98_Sub10_Sub7.aFont5576);
                        graphics.setColor(color3);
                        graphics.drawString(Class265.aString1978, Class39_Sub1.anInt3593 / 2 + -(Class265.aString1978.length() * 6 / 2), Class98_Sub25.anInt4024 / 2 - 26);
                    }
                    graphics.drawString(s, n5 - -((304 + -(s.length() * 6)) / 2), n6 + 22);
                }
            }
            catch (Exception ex3) {
                Class42_Sub3.aCanvas5361.repaint();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rm.J(" + n + ',' + ((color != null) ? "{...}" : "null") + ',' + ((color2 != null) ? "{...}" : "null") + ',' + ((color3 != null) ? "{...}" : "null") + ',' + ((s != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    static final int method3348(final Class53_Sub1 class53_Sub1, final int n, final boolean b, final Class53_Sub1 class53_Sub2, final boolean b2, final int n2, final int n3) {
        try {
            final int method2700 = Class202.method2700(n2, class53_Sub1, b, n - 18, class53_Sub2);
            if (~method2700 != -1) {
                if (b) {
                    return -method2700;
                }
                return method2700;
            }
            else {
                if (n3 == -1) {
                    return 0;
                }
                if (n != 17) {
                    method3350(4, 118, -28, 12, null, null);
                }
                final int method2701 = Class202.method2700(n3, class53_Sub1, b2, -1, class53_Sub2);
                if (!b2) {
                    return method2701;
                }
                return -method2701;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rm.N(" + ((class53_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + b + ',' + ((class53_Sub2 != null) ? "{...}" : "null") + ',' + b2 + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final boolean method3349(final boolean b) {
        try {
            if (!b) {
                this.method3342(26);
            }
            return this.aClass288_2134 != null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rm.I(" + b + ')');
        }
    }
    
    static final void method3350(final int n, final int n2, final int n3, final int n4, final Class146 class146, final Class228 class147) {
        try {
            if (n3 != 18) {
                method3350(-82, 96, 36, 122, null, null);
            }
            if (class146 != null) {
                class147.method2860(n2, n3 - 7427, class146.RA(), class146.na(), class146.fa(), class146.V(), n4, class146.G(), class146.HA(), class146.EA(), n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rm.D(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((class146 != null) ? "{...}" : "null") + ',' + ((class147 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method3351(int n, final int n2, final boolean b, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            if (!b) {
                method3347(-3, null, null, null, null, -95);
            }
            if (~n3 <= -1 && n4 >= 0 && -1 + Class165.anInt1276 > n3 && -1 + Class98_Sub10_Sub7.anInt5572 > n4) {
                if (Class98_Sub46_Sub1.aClass172ArrayArrayArray5948 != null) {
                    if (~n7 != -1) {
                        if (~n7 != 0xFFFFFFFE) {
                            if (~n7 != 0xFFFFFFFD) {
                                if (~n7 == 0xFFFFFFFC) {
                                    final Interface19 interface19 = (Interface19)Class253.method3177(n5, n3, n4);
                                    if (interface19 != null) {
                                        if (!(interface19 instanceof Class246_Sub3_Sub1_Sub2)) {
                                            Canvas_Sub1.method119(interface19.method64(30472), n4, n7, n2, n5, -2, n3, n6, n);
                                        }
                                        else {
                                            ((Class246_Sub3_Sub1_Sub2)interface19).aClass359_6249.method3900(n6, -53);
                                        }
                                    }
                                }
                            }
                            else {
                                final Interface19 interface20 = (Interface19)Class97.method931(n5, n3, n4, (Class283.aClass2158 != null) ? Class283.aClass2158 : (Class283.aClass2158 = method3353("Interface19")));
                                if (interface20 != null) {
                                    if (~n == 0xFFFFFFF4) {
                                        n = 10;
                                    }
                                    if (interface20 instanceof Class246_Sub3_Sub4_Sub5) {
                                        ((Class246_Sub3_Sub4_Sub5)interface20).aClass359_6261.method3900(n6, -102);
                                    }
                                    else {
                                        Canvas_Sub1.method119(interface20.method64(30472), n4, n7, n2, n5, -2, n3, n6, n);
                                    }
                                }
                            }
                        }
                        else {
                            final Interface19 interface21 = (Interface19)Class101.method1701(n5, n3, n4);
                            if (interface21 != null) {
                                if (!(interface21 instanceof Class246_Sub3_Sub5_Sub1)) {
                                    final int method64 = interface21.method64(30472);
                                    if (~n != 0xFFFFFFFB && ~n != 0xFFFFFFFA) {
                                        if (n != 6) {
                                            if (~n != 0xFFFFFFF8) {
                                                if (~n == 0xFFFFFFF7) {
                                                    Canvas_Sub1.method119(method64, n4, n7, 4 + n2, n5, -2, n3, n6, 4);
                                                    Canvas_Sub1.method119(method64, n4, n7, (n2 + 2 & 0x3) + 4, n5, -2, n3, n6, 4);
                                                }
                                            }
                                            else {
                                                Canvas_Sub1.method119(method64, n4, n7, (n2 + 2 & 0x3) + 4, n5, -2, n3, n6, 4);
                                            }
                                        }
                                        else {
                                            Canvas_Sub1.method119(method64, n4, n7, n2 + 4, n5, -2, n3, n6, 4);
                                        }
                                    }
                                    else {
                                        Canvas_Sub1.method119(method64, n4, n7, n2, n5, -2, n3, n6, 4);
                                    }
                                }
                                else {
                                    ((Class246_Sub3_Sub5_Sub1)interface21).aClass359_6219.method3900(n6, -86);
                                }
                            }
                        }
                    }
                    else {
                        final Interface19 interface22 = (Interface19)Class21_Sub1.method268(n5, n3, n4);
                        final Interface19 interface23 = (Interface19)Class205.method2711(n5, n3, n4);
                        Label_0590: {
                            if (interface22 != null && n != 2) {
                                if (interface22 instanceof Class246_Sub3_Sub3_Sub1) {
                                    ((Class246_Sub3_Sub3_Sub1)interface22).aClass359_6258.method3900(n6, -76);
                                    if (!client.aBoolean3553) {
                                        break Label_0590;
                                    }
                                }
                                Canvas_Sub1.method119(interface22.method64(30472), n4, n7, n2, n5, -2, n3, n6, n);
                            }
                        }
                        if (interface23 != null) {
                            if (!(interface23 instanceof Class246_Sub3_Sub3_Sub1)) {
                                Canvas_Sub1.method119(interface23.method64(30472), n4, n7, n2, n5, -2, n3, n6, n);
                            }
                            else {
                                ((Class246_Sub3_Sub3_Sub1)interface23).aClass359_6258.method3900(n6, -42);
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rm.C(" + n + ',' + n2 + ',' + b + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    public static void method3352(final int n) {
        try {
            Class283.aClass58_2139 = null;
            Class283.aClass171_2146 = null;
            Class283.aClass58_2143 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rm.M(" + n + ')');
        }
    }
    
    Class283(final ha_Sub1 aHa_Sub1_2135) {
        this.anInt2138 = 0;
        this.anInt2133 = 0;
        this.anInt2140 = 1;
        this.anInt2141 = 1;
        this.aClass148_2137 = new Class148();
        this.aBoolean2148 = true;
        this.anInt2150 = -1;
        this.aBoolean2153 = true;
        this.aBoolean2151 = true;
        this.aClass42_Sub1Array2154 = new Class42_Sub1[2];
        this.aBoolean2157 = true;
        this.aBoolean2152 = false;
        this.anInt2155 = 0;
        try {
            this.aHa_Sub1_2135 = aHa_Sub1_2135;
            if (this.aHa_Sub1_2135.aBoolean4460 && this.aHa_Sub1_2135.aBoolean4378) {
                final Class288 class288 = new Class288(this.aHa_Sub1_2135);
                this.aClass288_2136 = class288;
                this.aClass288_2134 = class288;
                if (this.aHa_Sub1_2135.anInt4310 > 1 && this.aHa_Sub1_2135.aBoolean4375 && this.aHa_Sub1_2135.aBoolean4424) {
                    final Class288 class289 = new Class288(this.aHa_Sub1_2135);
                    this.aClass288_2142 = class289;
                    this.aClass288_2134 = class289;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rm.<init>(" + ((aHa_Sub1_2135 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static Class method3353(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Class283.aClass58_2139 = new IncomingOpcode(63, 7);
        Class283.aClass58_2143 = new IncomingOpcode(90, 10);
        Class283.aClass171_2146 = new OutgoingOpcode(55, 7);
    }
}
