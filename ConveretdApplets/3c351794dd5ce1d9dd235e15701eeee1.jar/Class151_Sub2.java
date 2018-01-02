import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class151_Sub2 extends Class151
{
    private Class336 aClass336_4971;
    private boolean aBoolean4972;
    static Class218 aClass218_4973;
    private boolean aBoolean4974;
    private Class51 aClass51_4975;
    
    static final boolean method2451(final int n, final int n2, final int n3) {
        try {
            if (n2 != 544) {
                method2452(-28);
            }
            return ~(n & 0x220) == 0xFFFFFDDF | (n & 0x18) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "b.B(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method2442(final Class42 class42, final boolean b, final int n) {
        try {
            if (!this.aBoolean4972) {
                super.aHa_Sub1_1215.method1863(1, class42);
                super.aHa_Sub1_1215.method1896(260, n);
            }
            if (b) {
                this.aClass51_4975 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "b.F(" + ((class42 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    Class151_Sub2(final ha_Sub1 ha_Sub1, final Class51 aClass51_4975) {
        super(ha_Sub1);
        this.aBoolean4972 = false;
        this.aBoolean4974 = false;
        try {
            this.aClass51_4975 = aClass51_4975;
            if (this.aClass51_4975.aClass42_Sub4_421 != null && super.aHa_Sub1_1215.aBoolean4399 && super.aHa_Sub1_1215.aBoolean4447) {
                this.aClass336_4971 = Class177.method2584(super.aHa_Sub1_1215, new Class345[] { Class246_Sub3_Sub1_Sub1.method2996(35633, super.aHa_Sub1_1215, false, "uniform float time;\nuniform float scale;\nvarying vec3 wvVertex;\nvarying float waterDepth;\nvoid main() {\nwaterDepth = gl_MultiTexCoord0.z;\nvec4 ecVertex = gl_ModelViewMatrix*gl_Vertex;\nwvVertex.x = dot(gl_NormalMatrix[0], ecVertex.xyz);\nwvVertex.y = dot(gl_NormalMatrix[1], ecVertex.xyz);\nwvVertex.z = dot(gl_NormalMatrix[2], ecVertex.xyz);\ngl_TexCoord[0].x = dot(gl_TextureMatrix[0][0], gl_MultiTexCoord0)*scale;\ngl_TexCoord[0].y = dot(gl_TextureMatrix[0][1], gl_MultiTexCoord0)*scale;\ngl_TexCoord[0].z = time;\ngl_TexCoord[0].w = 1.0;\ngl_FogFragCoord = 1.0-clamp((gl_Fog.end+ecVertex.z)*gl_Fog.scale, 0.0, 1.0);\ngl_Position = ftransform();\n}\n"), Class246_Sub3_Sub1_Sub1.method2996(35632, super.aHa_Sub1_1215, false, "varying vec3 wvVertex;\nvarying float waterDepth;\nuniform vec3 sunDir;\nuniform vec4 sunColour;\nuniform float sunExponent;\nuniform vec2 waveIntensity;\nuniform float waveExponent;\nuniform float breakWaterDepth;\nuniform float breakWaterOffset;\nuniform sampler3D normalSampler;\nuniform samplerCube envMapSampler;\nvoid main() {\nvec4 wnNormal = texture3D(normalSampler, gl_TexCoord[0].xyz).rbga;\nwnNormal.xyz = 2.0*wnNormal.xyz-1.0;\nvec3 wnVector = normalize(wvVertex);\nvec3 wnReflection = reflect(wnVector, wnNormal.xyz);\nvec3 envColour = textureCube(envMapSampler, wnReflection).rgb;\nvec4 specularColour = sunColour*pow(clamp(-dot(sunDir, wnReflection), 0.0, 1.0), sunExponent);\nfloat shoreFactor = clamp(waterDepth/breakWaterDepth-breakWaterOffset*wnNormal.w, 0.0, 1.0);\nfloat waveFactor = pow(1.0-shoreFactor, waveExponent)-0.5;\nwaveFactor = -4.0*waveFactor*waveFactor+1.0;\nfloat ndote = dot(wnVector, wnNormal.xyz);\nfloat fresnel = pow(1.0-abs(ndote), 1.0);\nvec4 surfaceColour = mix(vec4(envColour, fresnel*shoreFactor), (waveIntensity.x*wnNormal.wwww)+waveIntensity.y, waveFactor)+specularColour*shoreFactor;\ngl_FragColor = vec4(mix(surfaceColour.rgb, gl_Fog.color.rgb, gl_FogFragCoord), surfaceColour.a);\n}\n") }, true);
                this.aBoolean4974 = (this.aClass336_4971 != null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "b.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + ((aClass51_4975 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2445(final byte b) {
        try {
            if (b >= 25 && this.aBoolean4972) {
                super.aHa_Sub1_1215.method1845(1, 847872872);
                super.aHa_Sub1_1215.method1863(1, null);
                super.aHa_Sub1_1215.method1845(0, 847872872);
                super.aHa_Sub1_1215.method1863(1, null);
                OpenGL.glUseProgramObjectARB(0L);
                this.aBoolean4972 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "b.E(" + b + ')');
        }
    }
    
    @Override
    final void method2443(final boolean b, final int n) {
        try {
            if (n != 255) {
                this.aBoolean4972 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "b.C(" + b + ',' + n + ')');
        }
    }
    
    @Override
    final void method2440(final boolean b, final boolean b2) {
        try {
            if (!b) {
                final Class42_Sub2 method1827 = super.aHa_Sub1_1215.method1827(-122);
                if (this.aBoolean4974 && method1827 != null) {
                    final float n = 2.0f * (1.0f - Math.abs(super.aHa_Sub1_1215.aFloatArray4438[1])) + 1.0f;
                    super.aHa_Sub1_1215.method1845(1, 847872872);
                    super.aHa_Sub1_1215.method1863(1, method1827);
                    super.aHa_Sub1_1215.method1845(0, 847872872);
                    super.aHa_Sub1_1215.method1863(1, this.aClass51_4975.aClass42_Sub4_421);
                    final long aLong2821 = this.aClass336_4971.aLong2821;
                    OpenGL.glUseProgramObjectARB(aLong2821);
                    OpenGL.glUniform1iARB(OpenGL.glGetUniformLocationARB(aLong2821, "normalSampler"), 0);
                    OpenGL.glUniform1iARB(OpenGL.glGetUniformLocationARB(aLong2821, "envMapSampler"), 1);
                    OpenGL.glUniform3fARB(OpenGL.glGetUniformLocationARB(aLong2821, "sunDir"), -super.aHa_Sub1_1215.aFloatArray4438[0], -super.aHa_Sub1_1215.aFloatArray4438[1], -super.aHa_Sub1_1215.aFloatArray4438[2]);
                    OpenGL.glUniform4fARB(OpenGL.glGetUniformLocationARB(aLong2821, "sunColour"), n * super.aHa_Sub1_1215.aFloat4433, n * super.aHa_Sub1_1215.aFloat4420, n * super.aHa_Sub1_1215.aFloat4458, 1.0f);
                    OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(aLong2821, "sunExponent"), 64.0f + 928.0f * Math.abs(super.aHa_Sub1_1215.aFloatArray4438[1]));
                    this.aBoolean4972 = true;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "b.D(" + b + ',' + b2 + ')');
        }
    }
    
    @Override
    final boolean method2439(final int n) {
        try {
            return n != 31565 || this.aBoolean4974;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "b.A(" + n + ')');
        }
    }
    
    @Override
    final void method2441(final int n, final int n2, final int n3) {
        try {
            if (this.aBoolean4972) {
                final int n4 = 1 << (0x3 & n);
                final float n5 = (1 << (0x7 & n >> 1374656835)) / 32.0f;
                final int n6 = n2 & 0xFFFF;
                final float n7 = ((n2 & 0x37EB0) >> -558078544) / 8.0f;
                final float n8 = (0xF & n2 >> -311770061) / 16.0f;
                final float n9 = (0xF & n2 >> -1813636489) / 16.0f;
                final int n10 = (0x7F9B2E24 & n2) >> 1741992827;
                final long aLong2821 = this.aClass336_4971.aLong2821;
                OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(aLong2821, "time"), n4 * super.aHa_Sub1_1215.anInt4321 % 40000 / 40000.0f);
                OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(aLong2821, "scale"), n5);
                OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(aLong2821, "breakWaterDepth"), n6);
                OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(aLong2821, "breakWaterOffset"), n7);
                OpenGL.glUniform2fARB(OpenGL.glGetUniformLocationARB(aLong2821, "waveIntensity"), n9, n8);
                OpenGL.glUniform1fARB(OpenGL.glGetUniformLocationARB(aLong2821, "waveExponent"), n10);
            }
            if (n3 >= -2) {
                Class151_Sub2.aClass218_4973 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "b.G(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    public static void method2452(final int n) {
        try {
            Class151_Sub2.aClass218_4973 = null;
            if (n != 928) {
                method2452(84);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "b.H(" + n + ')');
        }
    }
    
    static {
        Class151_Sub2.aClass218_4973 = new Class218();
    }
}
