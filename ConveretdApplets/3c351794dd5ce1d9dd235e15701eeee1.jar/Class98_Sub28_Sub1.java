import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub28_Sub1 extends Class98_Sub28
{
    private Class336 aClass336_5800;
    private int anInt5801;
    private Class288 aClass288_5802;
    private int anInt5803;
    private Class42_Sub1 aClass42_Sub1_5804;
    static Class53_Sub1[] aClass53_Sub1Array5805;
    private int anInt5806;
    private int anInt5807;
    private Class42_Sub1 aClass42_Sub1_5808;
    private Class288 aClass288_5809;
    private Class336 aClass336_5810;
    static int anInt5811;
    private Class42_Sub1[] aClass42_Sub1Array5812;
    private Class336 aClass336_5813;
    private Class336 aClass336_5814;
    
    @Override
    final boolean method1294(final byte b) {
        try {
            if (!super.aHa_Sub1_4079.aBoolean4460 || !super.aHa_Sub1_4079.aBoolean4447 || !super.aHa_Sub1_4079.aBoolean4383) {
                return false;
            }
            this.aClass288_5802 = new Class288(super.aHa_Sub1_4079);
            (this.aClass42_Sub1_5804 = new Class42_Sub1(super.aHa_Sub1_4079, 3553, 34842, 256, 256)).method383(false, 10242, false);
            (this.aClass42_Sub1_5808 = new Class42_Sub1(super.aHa_Sub1_4079, 3553, 34842, 256, 256)).method383(false, 10242, false);
            super.aHa_Sub1_4079.method1898(true, this.aClass288_5802);
            this.aClass288_5802.method3395(0, this.aClass42_Sub1_5804, 119);
            this.aClass288_5802.method3395(1, this.aClass42_Sub1_5808, 109);
            this.aClass288_5802.method3404(0, 0);
            if (!this.aClass288_5802.method3403((byte)74)) {
                super.aHa_Sub1_4079.method1907(this.aClass288_5802, -1);
                return false;
            }
            super.aHa_Sub1_4079.method1907(this.aClass288_5802, -1);
            this.aClass336_5813 = Class177.method2584(super.aHa_Sub1_4079, new Class345[] { Class246_Sub3_Sub1_Sub1.method2996(35632, super.aHa_Sub1_4079, false, "#extension GL_ARB_texture_rectangle : enable\nuniform vec3 params;\nuniform sampler2DRect sceneTex;\nconst vec3 lumCoef = vec3(0.2126, 0.7152, 0.0722);\nvoid main() {\n    vec4 col = texture2DRect(sceneTex, gl_TexCoord[0].xy);\n    gl_FragColor = col*step(params.x, dot(lumCoef, col.rgb));\n}\n") }, true);
            this.aClass336_5810 = Class177.method2584(super.aHa_Sub1_4079, new Class345[] { Class246_Sub3_Sub1_Sub1.method2996(35632, super.aHa_Sub1_4079, false, "uniform vec3 params;\nuniform sampler2D sceneTex;\nconst vec3 lumCoef = vec3(0.2126, 0.7152, 0.0722);\nvoid main() {\n    vec4 col = texture2D(sceneTex, gl_TexCoord[0].xy);\n    gl_FragColor = col*step(params.x, dot(lumCoef, col.rgb));\n}\n") }, true);
            this.aClass336_5814 = Class177.method2584(super.aHa_Sub1_4079, new Class345[] { Class246_Sub3_Sub1_Sub1.method2996(35632, super.aHa_Sub1_4079, false, "#extension GL_ARB_texture_rectangle : enable\nuniform vec3 params;\nuniform vec3 dimScale;\nuniform sampler2D bloomTex;\nuniform sampler2DRect sceneTex;\nconst vec3 lumCoef = vec3(0.2126, 0.7152, 0.0722);\nvoid main() {\n\t vec4 bloomCol = texture2D(bloomTex, gl_TexCoord[1].xy);\n\t vec4 sceneCol = texture2DRect(sceneTex, gl_TexCoord[0].xy);\n\t float preLum = 0.99*dot(lumCoef, sceneCol.rgb)+0.01;\n    float postLum = preLum*(1.0+(preLum/params.y))/(preLum+1.0);\n\t gl_FragColor = sceneCol*(postLum/preLum)+bloomCol*params.x;\n}\n") }, true);
            this.aClass336_5800 = Class177.method2584(super.aHa_Sub1_4079, new Class345[] { Class246_Sub3_Sub1_Sub1.method2996(35632, super.aHa_Sub1_4079, false, "uniform vec3 step;\nuniform sampler2D baseTex;\nvoid main() {\n\tvec4 fragCol = texture2D(baseTex, gl_TexCoord[0].xy)*0.091396265;\n\tfragCol += texture2D(baseTex, gl_TexCoord[0].xy+(-1.0*step.xy))*0.088584304;\n\tfragCol += texture2D(baseTex, gl_TexCoord[0].xy+( 1.0*step.xy))*0.088584304;\n\tfragCol += texture2D(baseTex, gl_TexCoord[0].xy+(-2.0*step.xy))*0.08065692;\n\tfragCol += texture2D(baseTex, gl_TexCoord[0].xy+( 2.0*step.xy))*0.08065692;\n\tfragCol += texture2D(baseTex, gl_TexCoord[0].xy+(-3.0*step.xy))*0.068989515;\n\tfragCol += texture2D(baseTex, gl_TexCoord[0].xy+( 3.0*step.xy))*0.068989515;\n\tfragCol += texture2D(baseTex, gl_TexCoord[0].xy+(-4.0*step.xy))*0.055434637;\n\tfragCol += texture2D(baseTex, gl_TexCoord[0].xy+( 4.0*step.xy))*0.055434637;\n\tfragCol += texture2D(baseTex, gl_TexCoord[0].xy+(-5.0*step.xy))*0.04184426;\n\tfragCol += texture2D(baseTex, gl_TexCoord[0].xy+( 5.0*step.xy))*0.04184426;\n\tfragCol += texture2D(baseTex, gl_TexCoord[0].xy+(-6.0*step.xy))*0.029672023;\n\tfragCol += texture2D(baseTex, gl_TexCoord[0].xy+( 6.0*step.xy))*0.029672023;\n\tfragCol += texture2D(baseTex, gl_TexCoord[0].xy+(-7.0*step.xy))*0.019765828;\n\tfragCol += texture2D(baseTex, gl_TexCoord[0].xy+( 7.0*step.xy))*0.019765828;\n\tfragCol += texture2D(baseTex, gl_TexCoord[0].xy+(-8.0*step.xy))*0.012369139;\n\tfragCol += texture2D(baseTex, gl_TexCoord[0].xy+( 8.0*step.xy))*0.012369139;\n\tgl_FragColor = fragCol;\n}\n") }, true);
            return this.aClass336_5810 != null && this.aClass336_5813 != null && this.aClass336_5814 != null && this.aClass336_5800 != null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bw.H(" + b + ')');
        }
    }
    
    public static void method1308(final byte b) {
        try {
            Class98_Sub28_Sub1.aClass53_Sub1Array5805 = null;
            if (b <= 102) {
                method1310(false, null, false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bw.J(" + b + ')');
        }
    }
    
    @Override
    final void method1297(final int n, final byte b) {
        try {
            OpenGL.glUseProgramObjectARB(0L);
            super.aHa_Sub1_4079.method1845(1, 847872872);
            super.aHa_Sub1_4079.method1863(1, null);
            if (b == 115) {
                super.aHa_Sub1_4079.method1845(0, 847872872);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bw.D(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final void method1304(final byte b) {
        try {
            if (b < -31) {
                this.aClass336_5813 = null;
                this.aClass42_Sub1Array5812 = null;
                this.aClass288_5802 = null;
                this.aClass42_Sub1_5808 = null;
                this.aClass336_5800 = null;
                this.aClass336_5810 = null;
                this.aClass336_5814 = null;
                this.aClass288_5809 = null;
                this.aClass42_Sub1_5804 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bw.C(" + b + ')');
        }
    }
    
    final boolean method1309(final byte b) {
        try {
            return b == 41 && super.aHa_Sub1_4079.aBoolean4460 && super.aHa_Sub1_4079.aBoolean4447 && super.aHa_Sub1_4079.aBoolean4383;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bw.E(" + b + ')');
        }
    }
    
    Class98_Sub28_Sub1(final ha_Sub1 ha_Sub1) {
        super(ha_Sub1);
    }
    
    @Override
    final boolean method1298(final int n) {
        try {
            if (n <= 10) {
                this.method1298(6);
            }
            return this.aClass288_5802 != null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bw.F(" + n + ')');
        }
    }
    
    @Override
    final int method1299(final boolean b) {
        try {
            if (b) {
                this.method1297(88, (byte)126);
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bw.B(" + b + ')');
        }
    }
    
    static final byte[] method1310(final boolean b, final Object o, final boolean b2) {
        try {
            if (o == null) {
                return null;
            }
            if (o instanceof byte[]) {
                final byte[] array = (byte[])o;
                if (!b2) {
                    return array;
                }
                return Class246_Sub10.method3140(array, 0);
            }
            else {
                if (o instanceof Class317) {
                    return ((Class317)o).method3654(false);
                }
                if (b) {
                    return null;
                }
                throw new IllegalArgumentException();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bw.I(" + b + ',' + ((o != null) ? "{...}" : "null") + ',' + b2 + ')');
        }
    }
    
    @Override
    final void method1295(final int anInt5806, final int anInt5807, final boolean b) {
        try {
            this.anInt5806 = anInt5806;
            this.anInt5801 = anInt5807;
            final int method282 = Class23.method282(96, this.anInt5801);
            final int method283 = Class23.method282(-97, this.anInt5806);
            if (!b && (this.anInt5807 != method282 || ~this.anInt5803 != ~method283)) {
                if (this.aClass42_Sub1Array5812 != null) {
                    for (int n = 0; ~this.aClass42_Sub1Array5812.length < ~n; ++n) {
                        this.aClass42_Sub1Array5812[n].method375(true);
                    }
                    this.aClass42_Sub1Array5812 = null;
                }
                if (method282 <= 256 && ~method283 >= -257) {
                    this.aClass288_5809 = null;
                }
                else {
                    int n2 = method282;
                    int n3 = method283;
                    int n4 = 0;
                    while (n2 > 256 || ~n3 < -257) {
                        if (n3 > 256) {
                            n3 >>= 1;
                        }
                        if (n2 > 256) {
                            n2 >>= 1;
                        }
                        ++n4;
                    }
                    if (this.aClass288_5809 == null) {
                        this.aClass288_5809 = new Class288(super.aHa_Sub1_4079);
                    }
                    this.aClass42_Sub1Array5812 = new Class42_Sub1[n4];
                    int n5 = method282;
                    int n6 = method283;
                    int n7 = 0;
                    while (n5 > 256 || ~n6 < -257) {
                        this.aClass42_Sub1Array5812[n7++] = new Class42_Sub1(super.aHa_Sub1_4079, 3553, 34842, n5, n6);
                        if (~n6 < -257) {
                            n6 >>= 1;
                        }
                        if (n5 > 256) {
                            n5 >>= 1;
                        }
                    }
                }
                this.anInt5807 = method282;
                this.anInt5803 = method283;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bw.A(" + anInt5806 + ',' + anInt5807 + ',' + b + ')');
        }
    }
    
    @Override
    final void method1302(final Class42_Sub1 class42_Sub1, final int n, final byte b, final Class42_Sub1 class42_Sub2) {
        try {
            OpenGL.glPushAttrib(2048);
            OpenGL.glMatrixMode(5889);
            OpenGL.glPushMatrix();
            OpenGL.glLoadIdentity();
            if (b != -64) {
                this.method1309((byte)(-68));
            }
            OpenGL.glOrtho(0.0, 1.0, 0.0, 1.0, -1.0, 1.0);
            if (this.aClass42_Sub1Array5812 == null) {
                super.aHa_Sub1_4079.method1863(1, class42_Sub2);
                super.aHa_Sub1_4079.method1898(true, this.aClass288_5802);
                this.aClass288_5802.method3404(b + 64, 0);
                OpenGL.glViewport(0, 0, 256, 256);
                final long aLong2821 = this.aClass336_5813.aLong2821;
                OpenGL.glUseProgramObjectARB(aLong2821);
                OpenGL.glUniform1iARB(OpenGL.glGetUniformLocationARB(aLong2821, "sceneTex"), 0);
                OpenGL.glUniform3fARB(OpenGL.glGetUniformLocationARB(aLong2821, "params"), Class246_Sub3_Sub3_Sub1.aFloat6257, 0.0f, 0.0f);
                OpenGL.glBegin(7);
                OpenGL.glTexCoord2f(0.0f, 0.0f);
                OpenGL.glVertex2i(0, 0);
                OpenGL.glTexCoord2f(this.anInt5801, 0.0f);
                OpenGL.glVertex2i(1, 0);
                OpenGL.glTexCoord2f(this.anInt5801, this.anInt5806);
                OpenGL.glVertex2i(1, 1);
                OpenGL.glTexCoord2f(0.0f, this.anInt5806);
                OpenGL.glVertex2i(0, 1);
                OpenGL.glEnd();
            }
            else {
                super.aHa_Sub1_4079.method1898(true, this.aClass288_5809);
                int method282 = Class23.method282(76, this.anInt5801);
                int method283 = Class23.method282(104, this.anInt5806);
                int n2 = 0;
                while (method282 > 256 || method283 > 256) {
                    OpenGL.glViewport(0, 0, method282, method283);
                    this.aClass288_5809.method3395(0, this.aClass42_Sub1Array5812[n2], 116);
                    if (method282 > 256) {
                        method282 >>= 1;
                    }
                    if (~method283 < -257) {
                        method283 >>= 1;
                    }
                    if (~n2 == -1) {
                        super.aHa_Sub1_4079.method1863(b + 65, class42_Sub2);
                        OpenGL.glBegin(7);
                        OpenGL.glTexCoord2f(0.0f, 0.0f);
                        OpenGL.glVertex2i(0, 0);
                        OpenGL.glTexCoord2f(this.anInt5801, 0.0f);
                        OpenGL.glVertex2i(1, 0);
                        OpenGL.glTexCoord2f(this.anInt5801, this.anInt5806);
                        OpenGL.glVertex2i(1, 1);
                        OpenGL.glTexCoord2f(0.0f, this.anInt5806);
                        OpenGL.glVertex2i(0, 1);
                        OpenGL.glEnd();
                    }
                    else {
                        super.aHa_Sub1_4079.method1863(1, this.aClass42_Sub1Array5812[n2 - 1]);
                        OpenGL.glBegin(7);
                        OpenGL.glTexCoord2f(0.0f, 0.0f);
                        OpenGL.glVertex2i(0, 0);
                        OpenGL.glTexCoord2f(1.0f, 0.0f);
                        OpenGL.glVertex2i(1, 0);
                        OpenGL.glTexCoord2f(1.0f, 1.0f);
                        OpenGL.glVertex2i(1, 1);
                        OpenGL.glTexCoord2f(0.0f, 1.0f);
                        OpenGL.glVertex2i(0, 1);
                        OpenGL.glEnd();
                    }
                    ++n2;
                }
                super.aHa_Sub1_4079.method1907(this.aClass288_5809, -1);
                super.aHa_Sub1_4079.method1863(1, this.aClass42_Sub1Array5812[-1 + n2]);
                super.aHa_Sub1_4079.method1898(true, this.aClass288_5802);
                this.aClass288_5802.method3404(b ^ 0xFFFFFFC0, 0);
                OpenGL.glViewport(0, 0, 256, 256);
                final long aLong2822 = this.aClass336_5810.aLong2821;
                OpenGL.glUseProgramObjectARB(aLong2822);
                OpenGL.glUniform1iARB(OpenGL.glGetUniformLocationARB(aLong2822, "sceneTex"), 0);
                OpenGL.glUniform3fARB(OpenGL.glGetUniformLocationARB(aLong2822, "params"), Class246_Sub3_Sub3_Sub1.aFloat6257, 0.0f, 0.0f);
                OpenGL.glBegin(7);
                OpenGL.glTexCoord2f(0.0f, 0.0f);
                OpenGL.glVertex2i(0, 0);
                OpenGL.glTexCoord2f(1.0f, 0.0f);
                OpenGL.glVertex2i(1, 0);
                OpenGL.glTexCoord2f(1.0f, 1.0f);
                OpenGL.glVertex2i(1, 1);
                OpenGL.glTexCoord2f(0.0f, 1.0f);
                OpenGL.glVertex2i(0, 1);
                OpenGL.glEnd();
            }
            this.aClass288_5802.method3404(b + 64, 1);
            super.aHa_Sub1_4079.method1863(1, this.aClass42_Sub1_5804);
            final long aLong2823 = this.aClass336_5800.aLong2821;
            OpenGL.glUseProgramObjectARB(aLong2823);
            OpenGL.glUniform1iARB(OpenGL.glGetUniformLocationARB(aLong2823, "baseTex"), 0);
            OpenGL.glUniform3fARB(OpenGL.glGetUniformLocationARB(aLong2823, "step"), 0.00390625f, 0.0f, 0.0f);
            OpenGL.glBegin(7);
            OpenGL.glTexCoord2f(0.0f, 0.0f);
            OpenGL.glVertex2i(0, 0);
            OpenGL.glTexCoord2f(1.0f, 0.0f);
            OpenGL.glVertex2i(1, 0);
            OpenGL.glTexCoord2f(1.0f, 1.0f);
            OpenGL.glVertex2i(1, 1);
            OpenGL.glTexCoord2f(0.0f, 1.0f);
            OpenGL.glVertex2i(0, 1);
            OpenGL.glEnd();
            this.aClass288_5802.method3404(b + 64, 0);
            super.aHa_Sub1_4079.method1863(b ^ 0xFFFFFFC1, this.aClass42_Sub1_5808);
            OpenGL.glUniform3fARB(OpenGL.glGetUniformLocationARB(aLong2823, "step"), 0.0f, 0.00390625f, 0.0f);
            OpenGL.glBegin(7);
            OpenGL.glTexCoord2f(0.0f, 0.0f);
            OpenGL.glVertex2i(0, 0);
            OpenGL.glTexCoord2f(1.0f, 0.0f);
            OpenGL.glVertex2i(1, 0);
            OpenGL.glTexCoord2f(1.0f, 1.0f);
            OpenGL.glVertex2i(1, 1);
            OpenGL.glTexCoord2f(0.0f, 1.0f);
            OpenGL.glVertex2i(0, 1);
            OpenGL.glEnd();
            OpenGL.glPopAttrib();
            OpenGL.glPopMatrix();
            OpenGL.glMatrixMode(5888);
            super.aHa_Sub1_4079.method1907(this.aClass288_5802, -1);
            final long aLong2824 = this.aClass336_5814.aLong2821;
            OpenGL.glUseProgramObjectARB(aLong2824);
            OpenGL.glUniform1iARB(OpenGL.glGetUniformLocationARB(aLong2824, "sceneTex"), 0);
            OpenGL.glUniform1iARB(OpenGL.glGetUniformLocationARB(aLong2824, "bloomTex"), 1);
            OpenGL.glUniform3fARB(OpenGL.glGetUniformLocationARB(aLong2824, "params"), Class313.aFloat2680, Class177.aFloat1378, 0.0f);
            super.aHa_Sub1_4079.method1845(1, 847872872);
            super.aHa_Sub1_4079.method1863(1, this.aClass42_Sub1_5804);
            super.aHa_Sub1_4079.method1845(0, 847872872);
            super.aHa_Sub1_4079.method1863(1, class42_Sub2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bw.G(" + ((class42_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + b + ',' + ((class42_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub28_Sub1.aClass53_Sub1Array5805 = new Class53_Sub1[0];
    }
}
