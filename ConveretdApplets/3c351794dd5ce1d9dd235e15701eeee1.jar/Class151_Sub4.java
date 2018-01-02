import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class151_Sub4 extends Class151
{
    private Class51 aClass51_4984;
    static int[] anIntArray4985;
    private Class336 aClass336_4986;
    private boolean aBoolean4987;
    private boolean aBoolean4988;
    static float aFloat4989;
    
    @Override
    final void method2442(final Class42 class42, final boolean b, final int n) {
        try {
            if (!this.aBoolean4987) {
                super.aHa_Sub1_1215.method1863(1, class42);
                super.aHa_Sub1_1215.method1896(260, n);
            }
            if (b) {
                this.aBoolean4988 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lg.F(" + ((class42 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    Class151_Sub4(final ha_Sub1 ha_Sub1, final Class51 aClass51_4984) {
        super(ha_Sub1);
        this.aBoolean4988 = false;
        this.aBoolean4987 = false;
        try {
            this.aClass51_4984 = aClass51_4984;
            if (this.aClass51_4984.aClass42_Sub4_421 != null && super.aHa_Sub1_1215.aBoolean4399 && super.aHa_Sub1_1215.aBoolean4447) {
                this.aClass336_4986 = Class177.method2584(super.aHa_Sub1_1215, new Class345[] { Class246_Sub3_Sub1_Sub1.method2996(35633, super.aHa_Sub1_1215, false, "uniform float time;\nuniform float scale;\nvarying vec3 wvVertex;\nvarying float waterDepth;\nvoid main() {\nwaterDepth = gl_MultiTexCoord0.z;\nvec4 ecVertex = gl_ModelViewMatrix*gl_Vertex;\nwvVertex.x = dot(gl_NormalMatrix[0], ecVertex.xyz);\nwvVertex.y = dot(gl_NormalMatrix[1], ecVertex.xyz);\nwvVertex.z = dot(gl_NormalMatrix[2], ecVertex.xyz);\ngl_TexCoord[0].x = dot(gl_TextureMatrix[0][0], gl_MultiTexCoord0)*scale;\ngl_TexCoord[0].y = dot(gl_TextureMatrix[0][1], gl_MultiTexCoord0)*scale;\ngl_TexCoord[0].z = time;\ngl_TexCoord[0].w = 1.0;\ngl_FogFragCoord = 1.0-clamp((gl_Fog.end+ecVertex.z)*gl_Fog.scale, 0.0, 1.0);\ngl_Position = ftransform();\n}\n"), Class246_Sub3_Sub1_Sub1.method2996(35632, super.aHa_Sub1_1215, false, "varying vec3 wvVertex;\nvarying float waterDepth;\nuniform vec3 sunDir;\nuniform vec4 sunColour;\nuniform float sunExponent;\nuniform float breakWaterDepth;\nuniform float breakWaterOffset;\nuniform sampler3D normalSampler;\nuniform samplerCube envMapSampler;\nvoid main() {\nvec4 wnNormal = texture3D(normalSampler, gl_TexCoord[0].xyz).rbga;\nwnNormal.xyz = 2.0*wnNormal.xyz-1.0;\nvec3 wnVector = normalize(wvVertex);\nvec3 wnReflection = reflect(wnVector, wnNormal.xyz);\nvec3 envColour = textureCube(envMapSampler, wnReflection).rgb;\nvec4 specularColour = sunColour*pow(clamp(-dot(sunDir, wnReflection), 0.0, 1.0), sunExponent);\nfloat shoreFactor = clamp(waterDepth/breakWaterDepth-breakWaterOffset*wnNormal.w, 0.0, 1.0);\nfloat ndote = dot(wnVector, wnNormal.xyz);\nfloat fresnel = pow(1.0-abs(ndote), 2.0);\nvec4 surfaceColour = vec4(envColour, fresnel*shoreFactor)+specularColour*shoreFactor;\ngl_FragColor = vec4(mix(surfaceColour.rgb, gl_Fog.color.rgb, gl_FogFragCoord), surfaceColour.a);\n}\n") }, true);
                this.aBoolean4988 = (this.aClass336_4986 != null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lg.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + ((aClass51_4984 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2460(final int n) {
        try {
            Class151_Sub4.anIntArray4985 = null;
            if (n != 23777) {
                method2460(68);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lg.B(" + n + ')');
        }
    }
    
    @Override
    final boolean method2439(final int n) {
        try {
            if (n != 31565) {
                this.method2442(null, false, -85);
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lg.A(" + n + ')');
        }
    }
    
    @Override
    final void method2441(final int n, final int n2, final int n3) {
        try {
            if (this.aBoolean4987) {
                final int n4 = 1 << (n & 0x3);
                final float n5 = (1 << (n >> 496943907 & 0x7)) / 32.0f;
                final int n6 = 0xFFFF & n2;
                final float n7 = ((n2 & 0x36757) >> 130557328) / 8.0f;
                final long aLong2821 = this.aClass336_4986.aLong2821;
                OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(aLong2821, "time"), super.aHa_Sub1_1215.anInt4321 * n4 % 40000 / 40000.0f);
                OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(aLong2821, "scale"), n5);
                OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(aLong2821, "breakWaterDepth"), n6);
                OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(aLong2821, "breakWaterOffset"), n7);
            }
            if (n3 >= -2) {
                this.method2439(120);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lg.G(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method2440(final boolean b, final boolean b2) {
        try {
            if (!b) {
                final Class42_Sub2 method1827 = super.aHa_Sub1_1215.method1827(-125);
                if (this.aBoolean4988 && method1827 != null) {
                    super.aHa_Sub1_1215.method1845(1, 847872872);
                    super.aHa_Sub1_1215.method1863(1, method1827);
                    super.aHa_Sub1_1215.method1845(0, 847872872);
                    super.aHa_Sub1_1215.method1863(1, this.aClass51_4984.aClass42_Sub4_421);
                    final long aLong2821 = this.aClass336_4986.aLong2821;
                    OpenGL.glUseProgramObjectARB(aLong2821);
                    OpenGL.glUniform1iARB(OpenGL.glGetUniformLocationARB(aLong2821, "normalSampler"), 0);
                    OpenGL.glUniform1iARB(OpenGL.glGetUniformLocationARB(aLong2821, "envMapSampler"), 1);
                    OpenGL.glUniform3fARB(OpenGL.glGetUniformLocationARB(aLong2821, "sunDir"), -super.aHa_Sub1_1215.aFloatArray4438[0], -super.aHa_Sub1_1215.aFloatArray4438[1], -super.aHa_Sub1_1215.aFloatArray4438[2]);
                    OpenGL.glUniform4fARB(OpenGL.glGetUniformLocationARB(aLong2821, "sunColour"), super.aHa_Sub1_1215.aFloat4433, super.aHa_Sub1_1215.aFloat4420, super.aHa_Sub1_1215.aFloat4458, 1.0f);
                    OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(aLong2821, "sunExponent"), Math.abs(super.aHa_Sub1_1215.aFloatArray4438[1]) * 928.0f + 96.0f);
                    this.aBoolean4987 = true;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lg.D(" + b + ',' + b2 + ')');
        }
    }
    
    @Override
    final void method2443(final boolean b, final int n) {
        try {
            if (n != 255) {
                method2460(-19);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lg.C(" + b + ',' + n + ')');
        }
    }
    
    @Override
    final void method2445(final byte b) {
        try {
            if (this.aBoolean4987) {
                super.aHa_Sub1_1215.method1845(1, 847872872);
                super.aHa_Sub1_1215.method1863(1, null);
                super.aHa_Sub1_1215.method1845(0, 847872872);
                super.aHa_Sub1_1215.method1863(1, null);
                OpenGL.glUseProgramObjectARB(0L);
                this.aBoolean4987 = false;
            }
            if (b <= 25) {
                this.method2439(-51);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lg.E(" + b + ')');
        }
    }
    
    static {
        Class151_Sub4.anIntArray4985 = new int[256];
    }
}
