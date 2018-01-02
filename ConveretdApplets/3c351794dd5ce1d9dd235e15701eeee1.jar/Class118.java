import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class118
{
    static int anInt975;
    private Class336 aClass336_976;
    private ha_Sub1 aHa_Sub1_977;
    static int anInt978;
    static int anInt979;
    static int anInt980;
    
    static final void method2170(final int n, String substring) {
        try {
            if (substring != null) {
                if (substring.startsWith("*")) {
                    substring = substring.substring(1);
                }
                final String method3867 = Class353.method3867(-1, substring);
                if (method3867 != null) {
                    if (n < 25) {
                        Class118.anInt978 = -32;
                    }
                    for (int n2 = 0; Class314.anInt2692 > n2; ++n2) {
                        String substring2 = Class98_Sub25.aStringArray4026[n2];
                        if (substring2.startsWith("*")) {
                            substring2 = substring2.substring(1);
                        }
                        final String method3868 = Class353.method3867(-1, substring2);
                        if (method3868 != null && method3868.equals(method3867)) {
                            --Class314.anInt2692;
                            for (int n3 = n2; ~Class314.anInt2692 < ~n3; ++n3) {
                                Class98_Sub25.aStringArray4026[n3] = Class98_Sub25.aStringArray4026[1 + n3];
                                Class315.aStringArray3527[n3] = Class315.aStringArray3527[1 + n3];
                                Class98_Sub26.anIntArray4030[n3] = Class98_Sub26.anIntArray4030[1 + n3];
                                Class98_Sub10_Sub17.aStringArray5625[n3] = Class98_Sub10_Sub17.aStringArray5625[n3 + 1];
                                Class69.anIntArray3222[n3] = Class69.anIntArray3222[1 + n3];
                                aa_Sub3.aBooleanArray3575[n3] = aa_Sub3.aBooleanArray3575[1 + n3];
                            }
                            Class363.anInt3099 = Class24.anInt242;
                            final Class98_Sub11 method3869 = Class246_Sub3_Sub4.method3023(260, r_Sub2.aClass171_6330, Class331.aClass117_2811);
                            method3869.aClass98_Sub22_Sub1_3865.method1194(r_Sub2.method1650(substring, (byte)75), 67);
                            method3869.aClass98_Sub22_Sub1_3865.method1188(substring, (byte)113);
                            Class98_Sub10_Sub29.sendPacket(false, method3869);
                            break;
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hk.D(" + n + ',' + ((substring != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class118(final ha_Sub1 aHa_Sub1_977) {
        try {
            this.aHa_Sub1_977 = aHa_Sub1_977;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hk.<init>(" + ((aHa_Sub1_977 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final boolean method2171(final boolean b) {
        try {
            if (!b) {
                Class118.anInt980 = -111;
            }
            if (this.aHa_Sub1_977.aBoolean4460 && this.aHa_Sub1_977.aBoolean4447 && this.aClass336_976 == null) {
                final Class345 method2996 = Class246_Sub3_Sub1_Sub1.method2996(35632, this.aHa_Sub1_977, false, "uniform float rcpRelief;\nuniform vec2 sampleSize;\nuniform sampler3D heightMap;\nvoid main() {\nfloat dx = texture3D(heightMap, vec3(-sampleSize.x, 0.0, 0.0)+gl_TexCoord[0].xyz).r - texture3D(heightMap, vec3(sampleSize.x, 0.0, 0.0)+gl_TexCoord[0].xyz).r;\nfloat dy = texture3D(heightMap, vec3(0.0, -sampleSize.y, 0.0)+gl_TexCoord[0].xyz).r - texture3D(heightMap, vec3(0.0, sampleSize.y, 0.0)+gl_TexCoord[0].xyz).r;\ngl_FragColor = vec4(0.5+normalize(vec3(dx, dy, rcpRelief))*0.5, texture3D(heightMap, gl_TexCoord[0].xyz).r);\n}\n");
                if (method2996 != null) {
                    this.aClass336_976 = Class177.method2584(this.aHa_Sub1_977, new Class345[] { method2996 }, b);
                }
            }
            return this.aClass336_976 != null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hk.C(" + b + ')');
        }
    }
    
    final boolean method2172(final int n, final Class42_Sub4 class42_Sub4, final float n2, final Class42_Sub4 class42_Sub5) {
        try {
            if (!this.method2171(true)) {
                return false;
            }
            final Class288 aClass288_4363 = this.aHa_Sub1_977.aClass288_4363;
            final Class98_Sub46_Sub14 class98_Sub46_Sub14 = new Class98_Sub46_Sub14(this.aHa_Sub1_977, 6408, class42_Sub5.anInt5369, class42_Sub5.anInt5372);
            boolean b = false;
            this.aHa_Sub1_977.method1898(true, aClass288_4363);
            aClass288_4363.method3406((byte)120, 0, class98_Sub46_Sub14);
            if (aClass288_4363.method3403((byte)74)) {
                OpenGL.glPushMatrix();
                OpenGL.glLoadIdentity();
                OpenGL.glMatrixMode(5889);
                OpenGL.glPushMatrix();
                OpenGL.glLoadIdentity();
                OpenGL.glOrtho(0.0, 1.0, 0.0, 1.0, -1.0, 1.0);
                OpenGL.glPushAttrib(2048);
                OpenGL.glViewport(0, 0, class42_Sub5.anInt5369, class42_Sub5.anInt5372);
                OpenGL.glUseProgramObjectARB(this.aClass336_976.aLong2821);
                OpenGL.glUniform1iARB(OpenGL.glGetUniformLocationARB(this.aClass336_976.aLong2821, "heightMap"), 0);
                OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(this.aClass336_976.aLong2821, "rcpRelief"), 1.0f / n2);
                OpenGL.glUniform2fARB(OpenGL.glGetUniformLocationARB(this.aClass336_976.aLong2821, "sampleSize"), 1.0f / class42_Sub4.anInt5369, 1.0f / class42_Sub4.anInt5372);
                for (int i = 0; i < class42_Sub5.anInt5368; ++i) {
                    final float n3 = i / class42_Sub5.anInt5368;
                    this.aHa_Sub1_977.method1863(1, class42_Sub4);
                    OpenGL.glBegin(7);
                    OpenGL.glTexCoord3f(0.0f, 0.0f, n3);
                    OpenGL.glVertex2f(0.0f, 0.0f);
                    OpenGL.glTexCoord3f(1.0f, 0.0f, n3);
                    OpenGL.glVertex2f(1.0f, 0.0f);
                    OpenGL.glTexCoord3f(1.0f, 1.0f, n3);
                    OpenGL.glVertex2f(1.0f, 1.0f);
                    OpenGL.glTexCoord3f(0.0f, 1.0f, n3);
                    OpenGL.glVertex2f(0.0f, 1.0f);
                    OpenGL.glEnd();
                    class42_Sub5.method395(0, 0, i, n, 0, class42_Sub5.anInt5369, class42_Sub5.anInt5372, 0);
                }
                OpenGL.glUseProgramObjectARB(0L);
                OpenGL.glPopAttrib();
                OpenGL.glPopMatrix();
                OpenGL.glMatrixMode(5888);
                b = true;
                OpenGL.glPopMatrix();
            }
            aClass288_4363.method3401(n, true);
            this.aHa_Sub1_977.method1907(aClass288_4363, -1);
            return b;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hk.A(" + n + ',' + ((class42_Sub4 != null) ? "{...}" : "null") + ',' + n2 + ',' + ((class42_Sub5 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method2173(final boolean b, final int n) {
        try {
            Label_0032: {
                if (b && Class278.aClass98_Sub46_Sub10_2056 != null) {
                    Class98_Sub22_Sub1.anInt5789 = Class278.aClass98_Sub46_Sub10_2056.anInt6014;
                    if (!client.aBoolean3553) {
                        break Label_0032;
                    }
                }
                Class98_Sub22_Sub1.anInt5789 = -1;
            }
            Class11.aClass293_120 = null;
            Class212.anInt1600 = 0;
            Class278.aClass98_Sub46_Sub10_2056 = null;
            Class8.aClass148_110 = null;
            Class278.method3299();
            Class278.aClass148_2065.method2422((byte)47);
            Class339_Sub1.aClass326_5308 = null;
            Class339_Sub1.aClass326_5315 = null;
            Class152.aClass332_1221 = null;
            Class224.aClass326_1686 = null;
            Class151_Sub7.aClass326_5009 = null;
            Class278.aClass370_2066 = null;
            Class101.anInt849 = -1;
            Class260.aClass326_3263 = null;
            Class169.anInt1307 = -1;
            Class271.aClass326_2033 = null;
            Class77_Sub1.aClass326_3805 = null;
            Class137.aClass326_1080 = null;
            if (Class278.aClass341_2057 != null) {
                Class278.aClass341_2057.method3808(0);
                Class278.aClass341_2057.method3809(64, -30502, 128);
            }
            if (Class278.aClass335_2059 != null) {
                Class278.aClass335_2059.method3771(109, 64, 64);
            }
            if (Class278.aClass302_2062 != null) {
                Class278.aClass302_2062.method3550(-129, 64);
            }
            Class17.aClass198_205.method2679(64, (byte)(-91));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hk.B(" + b + ',' + n + ')');
        }
    }
    
    static {
        Class118.anInt978 = 0;
        Class118.anInt980 = -1;
    }
}
