import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class151_Sub9 extends Class151
{
    private Class202 aClass202_5017;
    private boolean aBoolean5018;
    static int[] anIntArray5019;
    static int anInt5020;
    private boolean aBoolean5021;
    private Class202 aClass202_5022;
    static Class348 aClass348_5023;
    private boolean aBoolean5024;
    private Class202 aClass202_5025;
    private boolean aBoolean5026;
    private Class42_Sub1 aClass42_Sub1_5027;
    static int anInt5028;
    private Class202 aClass202_5029;
    static Class246_Sub3_Sub4_Sub2_Sub2[] aClass246_Sub3_Sub4_Sub2_Sub2Array5030;
    
    Class151_Sub9(final ha_Sub1 ha_Sub1) {
        super(ha_Sub1);
        this.aBoolean5024 = false;
        try {
            if (super.aHa_Sub1_1215.aBoolean4431) {
                this.aClass202_5022 = Class347.method3835(0, super.aHa_Sub1_1215, 34336, "!!ARBvp1.0\nATTRIB  iPos         = vertex.position;\nATTRIB  iColour      = vertex.color;\nATTRIB  iTexCoord    = vertex.texcoord[0];\nOUTPUT  oPos         = result.position;\nOUTPUT  oColour      = result.color;\nOUTPUT  oTexCoord0   = result.texcoord[0];\nOUTPUT  oTexCoord1   = result.texcoord[1];\nOUTPUT  oFogCoord    = result.fogcoord;\nPARAM   fogParams    = program.local[0];\nPARAM   waterPlane   = program.local[1];\nPARAM   tMatrix[4]   = { state.matrix.texture[0] };\nPARAM   pMatrix[4]   = { state.matrix.projection };\nPARAM   mvMatrix[4]  = { state.matrix.modelview };\nTEMP    viewPos, fogFactor;\nDP4   viewPos.x, mvMatrix[0], iPos;\nDP4   viewPos.y, mvMatrix[1], iPos;\nDP4   viewPos.z, mvMatrix[2], iPos;\nDP4   viewPos.w, mvMatrix[3], iPos;\nSUB   fogFactor.x, -viewPos.z, fogParams.x;\nMUL   fogFactor.x, fogFactor.x, 0.001953125;\nMAD   fogFactor.y, iTexCoord.z, fogParams.z, fogParams.w;\nSUB   fogFactor.z, -viewPos.z, fogParams.y;\nMUL   fogFactor.z, fogFactor.z, 0.00390625;\nMUL   fogFactor.x, fogFactor.x, fogFactor.y;\nMIN   fogFactor, fogFactor, 1;\nMAX   fogFactor, fogFactor, 0;\nMUL   fogFactor.z, fogFactor.z, iTexCoord.z;\nMAD   viewPos.xyz, waterPlane.xyzw, fogFactor.zzzz, viewPos.xyzw;\nMAX   oTexCoord1.xyz, fogFactor.xxxx, fogFactor.yyyy;\nMOV   oTexCoord1.w, 1;\nMOV   oColour, iColour;\nDP4   oPos.x, pMatrix[0], viewPos;\nDP4   oPos.y, pMatrix[1], viewPos;\nDP4   oPos.z, pMatrix[2], viewPos;\nDP4   oPos.w, pMatrix[3], viewPos;\nMOV   oFogCoord.x, viewPos.z;\nDP3   oTexCoord0.x, tMatrix[0], iTexCoord;\nDP3   oTexCoord0.y, tMatrix[1], iTexCoord;\nMOV   oTexCoord0.zw, iTexCoord;\nEND\n");
                this.aClass202_5029 = Class347.method3835(0, super.aHa_Sub1_1215, 34336, "!!ARBvp1.0\nATTRIB  iPos         = vertex.position;\nATTRIB  iNormal      = vertex.normal;\nATTRIB  iColour      = vertex.color;\nATTRIB  iTexCoord    = vertex.texcoord[0];\nOUTPUT  oPos         = result.position;\nOUTPUT  oColour      = result.color;\nOUTPUT  oTexCoord0   = result.texcoord[0];\nOUTPUT  oTexCoord1   = result.texcoord[1];\nOUTPUT  oFogCoord    = result.fogcoord;\nPARAM   fogParams    = program.local[0];\nPARAM   waterPlane   = program.local[1];\nPARAM   tMatrix[4]   = { state.matrix.texture[0] };\nPARAM   pMatrix[4]   = { state.matrix.projection };\nPARAM   mvMatrix[4]  = { state.matrix.modelview };\nTEMP    viewPos, viewNormal, fogFactor, colour, ndotl;\nDP4   viewPos.x, mvMatrix[0], iPos;\nDP4   viewPos.y, mvMatrix[1], iPos;\nDP4   viewPos.z, mvMatrix[2], iPos;\nDP4   viewPos.w, mvMatrix[3], iPos;\nSUB   fogFactor.x, -viewPos.z, fogParams.x;\nMUL   fogFactor.x, fogFactor.x, 0.001953125;\nMAD   fogFactor.y, iTexCoord.z, fogParams.z, fogParams.w;\nSUB   fogFactor.z, -viewPos.z, fogParams.y;\nMUL   fogFactor.z, fogFactor.z, 0.00390625;\nMUL   fogFactor.x, fogFactor.x, fogFactor.y;\nMIN   fogFactor, fogFactor, 1;\nMAX   fogFactor, fogFactor, 0;\nMUL   fogFactor.z, fogFactor.z, iTexCoord.z;\nMAD   viewPos.xyz, waterPlane.xyzw, fogFactor.zzzz, viewPos.xyzw;\nMAX   oTexCoord1.xyz, fogFactor.xxxx, fogFactor.yyyy;\nMOV   oTexCoord1.w, 1;\nDP3   viewNormal.x, mvMatrix[0], iNormal;\nDP3   viewNormal.y, mvMatrix[1], iNormal;\nDP3   viewNormal.z, mvMatrix[2], iNormal;\nDP3   ndotl.x, viewNormal, state.light[0].position;\nDP3   ndotl.y, viewNormal, state.light[1].position;\nMAX   ndotl, ndotl, 0;\nMOV   colour, state.lightmodel.ambient;\nMAD   colour, state.light[0].diffuse, ndotl.xxxx, colour;\nMAD   colour, state.light[1].diffuse, ndotl.yyyy, colour;\nMUL   oColour, iColour, colour;\nDP4   oPos.x, pMatrix[0], viewPos;\nDP4   oPos.y, pMatrix[1], viewPos;\nDP4   oPos.z, pMatrix[2], viewPos;\nDP4   oPos.w, pMatrix[3], viewPos;\nMOV   oFogCoord.x, viewPos.z;\nDP3   oTexCoord0.x, tMatrix[0], iTexCoord;\nDP3   oTexCoord0.y, tMatrix[1], iTexCoord;\nMOV   oTexCoord0.zw, iTexCoord;\nEND\n");
                this.aClass202_5025 = Class347.method3835(0, super.aHa_Sub1_1215, 34336, "!!ARBvp1.0\nATTRIB  iPos         = vertex.position;\nATTRIB  iColour      = vertex.color;\nATTRIB  iTexCoord    = vertex.texcoord[0];\nOUTPUT  oPos         = result.position;\nOUTPUT  oColour      = result.color;\nOUTPUT  oTexCoord0   = result.texcoord[0];\nOUTPUT  oTexCoord1   = result.texcoord[1];\nOUTPUT  oFogCoord    = result.fogcoord;\nPARAM   fogParams    = program.local[0];\nPARAM   waterPlane   = program.local[1];\nPARAM   pMatrix[4]   = { state.matrix.projection };\nPARAM   mvMatrix[4]  = { state.matrix.modelview };\nPARAM   texMatrix[4] = { state.matrix.texture[0] };\nTEMP    viewPos, fogFactor, depth;\nDP4   viewPos.x, mvMatrix[0], iPos;\nDP4   viewPos.y, mvMatrix[1], iPos;\nDP4   viewPos.z, mvMatrix[2], iPos;\nDP4   viewPos.w, mvMatrix[3], iPos;\nSUB   fogFactor.x, -viewPos.z, fogParams.x;\nMUL   fogFactor.x, fogFactor.x, 0.001953125;\nDP4   depth, waterPlane, viewPos;\nMAD   fogFactor.y, -depth, fogParams.z, fogParams.w;\nSUB   fogFactor.z, -viewPos.z, fogParams.y;\nMUL   fogFactor.z, fogFactor.z, 0.00390625;\nMIN   fogFactor, fogFactor, 1;\nMAX   fogFactor, fogFactor, 0;\nMUL   fogFactor.z, fogFactor.z, -depth;\nMAD   viewPos.xyz, waterPlane.xyzw, fogFactor.zzzz, viewPos.xyzw;\nMAX   oTexCoord1.xyz, fogFactor.xxxx, fogFactor.yyyy;\nMOV   oTexCoord1.w, 1;\nMOV   oColour, iColour;\nDP4   oPos.x, pMatrix[0], viewPos;\nDP4   oPos.y, pMatrix[1], viewPos;\nDP4   oPos.z, pMatrix[2], viewPos;\nDP4   oPos.w, pMatrix[3], viewPos;\nMOV   oFogCoord.x, viewPos.z;\nDP4   oTexCoord0.x, texMatrix[0], iTexCoord;\nDP4   oTexCoord0.y, texMatrix[1], iTexCoord;\nDP4   oTexCoord0.z, texMatrix[2], iTexCoord;\nMOV   oTexCoord0.w, 1;\nEND\n");
                this.aClass202_5017 = Class347.method3835(0, super.aHa_Sub1_1215, 34336, "!!ARBvp1.0\nATTRIB  iPos         = vertex.position;\nATTRIB  iNormal      = vertex.normal;\nATTRIB  iColour      = vertex.color;\nATTRIB  iTexCoord    = vertex.texcoord[0];\nOUTPUT  oPos         = result.position;\nOUTPUT  oColour      = result.color;\nOUTPUT  oTexCoord0   = result.texcoord[0];\nOUTPUT  oTexCoord1   = result.texcoord[1];\nOUTPUT  oFogCoord    = result.fogcoord;\nPARAM   fogParams    = program.local[0];\nPARAM   waterPlane   = program.local[1];\nPARAM   pMatrix[4]   = { state.matrix.projection };\nPARAM   mvMatrix[4]  = { state.matrix.modelview };\nPARAM   texMatrix[4] = { state.matrix.texture[0] };\nTEMP    viewPos, viewNormal, fogFactor, depth, colour, ndotl;\nDP4   viewPos.x, mvMatrix[0], iPos;\nDP4   viewPos.y, mvMatrix[1], iPos;\nDP4   viewPos.z, mvMatrix[2], iPos;\nDP4   viewPos.w, mvMatrix[3], iPos;\nSUB   fogFactor.x, -viewPos.z, fogParams.x;\nMUL   fogFactor.x, fogFactor.x, 0.001953125;\nDP4   depth, waterPlane, viewPos;\nMAD   fogFactor.y, -depth, fogParams.z, fogParams.w;\nSUB   fogFactor.z, -viewPos.z, fogParams.y;\nMUL   fogFactor.z, fogFactor.z, 0.00390625;\nMIN   fogFactor, fogFactor, 1;\nMAX   fogFactor, fogFactor, 0;\nMUL   fogFactor.z, fogFactor.z, -depth;\nMAD   viewPos.xyz, waterPlane.xyzw, fogFactor.zzzz, viewPos.xyzw;\nMAX   oTexCoord1.xyz, fogFactor.xxxx, fogFactor.yyyy;\nMOV   oTexCoord1.w, 1;\nDP3   viewNormal.x, mvMatrix[0], iNormal;\nDP3   viewNormal.y, mvMatrix[1], iNormal;\nDP3   viewNormal.z, mvMatrix[2], iNormal;\nDP3   ndotl.x, viewNormal, state.light[0].position;\nDP3   ndotl.y, viewNormal, state.light[1].position;\nMAX   ndotl, ndotl, 0;\nMOV   colour, state.lightmodel.ambient;\nMAD   colour, state.light[0].diffuse, ndotl.xxxx, colour;\nMAD   colour, state.light[1].diffuse, ndotl.yyyy, colour;\nMUL   oColour, iColour, colour;\nDP4   oPos.x, pMatrix[0], viewPos;\nDP4   oPos.y, pMatrix[1], viewPos;\nDP4   oPos.z, pMatrix[2], viewPos;\nDP4   oPos.w, pMatrix[3], viewPos;\nMOV   oFogCoord.x, viewPos.z;\nDP4   oTexCoord0.x, texMatrix[0], iTexCoord;\nDP4   oTexCoord0.y, texMatrix[1], iTexCoord;\nDP4   oTexCoord0.z, texMatrix[2], iTexCoord;\nMOV   oTexCoord0.w, 1;\nEND\n");
                if (!(this.aClass202_5017 != null & (this.aClass202_5025 != null & (this.aClass202_5029 != null & this.aClass202_5022 != null)))) {
                    this.aBoolean5018 = false;
                }
                else {
                    (this.aClass42_Sub1_5027 = new Class42_Sub1(ha_Sub1, 3553, 6406, 2, 1, false, new byte[] { 0, -1 }, 6406, false)).method383(false, 10242, false);
                    this.aBoolean5018 = true;
                }
            }
            else {
                this.aBoolean5018 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ws.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method2439(final int n) {
        try {
            if (n != 31565) {
                this.aBoolean5021 = true;
            }
            return this.aBoolean5018;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ws.A(" + n + ')');
        }
    }
    
    @Override
    final void method2441(final int n, final int n2, final int n3) {
        try {
            if (n3 > -2) {
                this.method2445((byte)(-6));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ws.G(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    public static void method2469(final int n) {
        try {
            Class151_Sub9.anIntArray5019 = null;
            Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030 = null;
            if (n == Integer.MAX_VALUE) {
                Class151_Sub9.aClass348_5023 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ws.B(" + n + ')');
        }
    }
    
    final void method2470(final int n) {
        try {
            if (n != -16661) {
                Class151_Sub9.anInt5020 = 117;
            }
            if (this.aBoolean5026) {
                final int xa = super.aHa_Sub1_1215.XA();
                final int i = super.aHa_Sub1_1215.i();
                OpenGL.glProgramLocalParameter4fARB(34336, 0, -((xa + -i) * 0.25f) + xa, xa - 0.125f * (-i + xa), 1.0f / super.aHa_Sub1_1215.anInt4454, super.aHa_Sub1_1215.anInt4453 / 255.0f);
                super.aHa_Sub1_1215.method1845(1, 847872872);
                super.aHa_Sub1_1215.method1882(super.aHa_Sub1_1215.anInt4423, -104);
                super.aHa_Sub1_1215.method1845(0, n ^ 0xCD763D83);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ws.I(" + n + ')');
        }
    }
    
    final void method2471(final byte b) {
        try {
            final Class111_Sub1 aClass111_Sub1_4353 = super.aHa_Sub1_1215.aClass111_Sub1_4353;
            Label_0092: {
                if (!this.aBoolean5021) {
                    OpenGL.glBindProgramARB(34336, (super.aHa_Sub1_1215.anInt4362 == Integer.MAX_VALUE) ? this.aClass202_5022.anInt1549 : this.aClass202_5025.anInt1549);
                    if (!client.aBoolean3553) {
                        break Label_0092;
                    }
                }
                OpenGL.glBindProgramARB(34336, (super.aHa_Sub1_1215.anInt4362 != Integer.MAX_VALUE) ? this.aClass202_5017.anInt1549 : this.aClass202_5029.anInt1549);
            }
            aClass111_Sub1_4353.method2109(-29834, 0.0f, super.aHa_Sub1_1215.anInt4362, 0.0f, -1.0f, Class222.aFloatArray1671);
            if (b == 34) {
                OpenGL.glProgramLocalParameter4fARB(34336, 1, Class222.aFloatArray1671[0], Class222.aFloatArray1671[1], Class222.aFloatArray1671[2], Class222.aFloatArray1671[3]);
                OpenGL.glEnable(34336);
                this.aBoolean5026 = true;
                this.method2470(-16661);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ws.J(" + b + ')');
        }
    }
    
    @Override
    final void method2443(final boolean b, final int n) {
        try {
            if (n != 255) {
                this.method2442(null, false, -47);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ws.C(" + b + ',' + n + ')');
        }
    }
    
    @Override
    final void method2440(final boolean b, final boolean aBoolean5021) {
        try {
            this.aBoolean5021 = aBoolean5021;
            super.aHa_Sub1_1215.method1845(1, 847872872);
            super.aHa_Sub1_1215.method1863(1, this.aClass42_Sub1_5027);
            if (b) {
                this.aBoolean5018 = false;
            }
            super.aHa_Sub1_1215.method1899(7681, 8960, 34165);
            super.aHa_Sub1_1215.method1840(0, 768, 108, 34166);
            super.aHa_Sub1_1215.method1840(2, 770, 113, 5890);
            super.aHa_Sub1_1215.method1886(770, 0, 34200, 34168);
            super.aHa_Sub1_1215.method1845(0, 847872872);
            this.method2471((byte)34);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ws.D(" + b + ',' + aBoolean5021 + ')');
        }
    }
    
    @Override
    final void method2445(final byte b) {
        try {
            if (this.aBoolean5026) {
                OpenGL.glBindProgramARB(34336, 0);
                OpenGL.glDisable(34820);
                OpenGL.glDisable(34336);
                this.aBoolean5026 = false;
            }
            super.aHa_Sub1_1215.method1845(1, 847872872);
            super.aHa_Sub1_1215.method1863(1, null);
            super.aHa_Sub1_1215.method1899(8448, 8960, 8448);
            super.aHa_Sub1_1215.method1840(0, 768, -107, 5890);
            super.aHa_Sub1_1215.method1840(2, 770, 104, 34166);
            super.aHa_Sub1_1215.method1886(770, 0, 34200, 5890);
            if (b <= 25) {
                Class151_Sub9.anInt5020 = -23;
            }
            super.aHa_Sub1_1215.method1845(0, 847872872);
            if (this.aBoolean5024) {
                super.aHa_Sub1_1215.method1840(0, 768, 117, 5890);
                super.aHa_Sub1_1215.method1886(770, 0, 34200, 5890);
                this.aBoolean5024 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ws.E(" + b + ')');
        }
    }
    
    static final void method2472(final boolean b, final String s, final int n, final boolean b2) {
        try {
            Class98_Sub43_Sub1.method1493(57);
            Class98_Sub36.method1458(-24580);
            Class98_Sub43_Sub4.method1510(28837);
            Class320.method3665((byte)69, s, n, b2);
            Class378.method4001((byte)(-34));
            Class98_Sub10_Sub19.method1058((byte)(-85), Class265.aHa1974);
            Class93_Sub2.method910(Class265.aHa1974, (byte)(-48));
            Class141.method2303(Class265.aHa1974, (byte)(-124), Class332_Sub2.aClass207_5423);
            Class48_Sub1_Sub2.method466(b);
            Class303.method3556(Class64_Sub18.aClass332Array3689, 0);
            Class98_Sub43.method1481(2);
            Class98_Sub46_Sub13_Sub1.method1593((byte)115);
            if (~Class177.anInt1376 != 0xFFFFFFFC) {
                if (~Class177.anInt1376 != 0xFFFFFFF8) {
                    if (Class177.anInt1376 != 10) {
                        if (Class177.anInt1376 == 1 || ~Class177.anInt1376 == 0xFFFFFFFD) {
                            Class266.method3235((byte)(-121));
                        }
                    }
                    else {
                        Class61.method538(11, false);
                    }
                }
                else {
                    Class61.method538(8, false);
                }
            }
            else {
                Class61.method538(4, false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ws.H(" + b + ',' + ((s != null) ? "{...}" : "null") + ',' + n + ',' + b2 + ')');
        }
    }
    
    @Override
    final void method2442(final Class42 class42, final boolean b, final int n) {
        try {
            if (!b) {
                if (class42 != null) {
                    if (this.aBoolean5024) {
                        super.aHa_Sub1_1215.method1840(0, 768, 74, 5890);
                        super.aHa_Sub1_1215.method1886(770, 0, 34200, 5890);
                        this.aBoolean5024 = false;
                    }
                    super.aHa_Sub1_1215.method1863(1, class42);
                    super.aHa_Sub1_1215.method1896(260, n);
                }
                else if (!this.aBoolean5024) {
                    super.aHa_Sub1_1215.method1863(1, super.aHa_Sub1_1215.aClass42_Sub1_4358);
                    super.aHa_Sub1_1215.method1896(260, 1);
                    super.aHa_Sub1_1215.method1840(0, 768, -50, 34168);
                    super.aHa_Sub1_1215.method1886(770, 0, 34200, 34168);
                    this.aBoolean5024 = true;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ws.F(" + ((class42 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    static {
        Class151_Sub9.anInt5020 = 0;
        Class151_Sub9.anIntArray5019 = new int[8];
        Class151_Sub9.aClass348_5023 = new Class348(0, 2, 2, 1);
        Class151_Sub9.anInt5028 = 0;
        Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030 = new Class246_Sub3_Sub4_Sub2_Sub2[2048];
    }
}
