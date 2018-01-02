import jaggl.OpenGL;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class151_Sub3 extends Class151
{
    private Class51 aClass51_4976;
    static Class148 aClass148_4977;
    private int anInt4978;
    private float aFloat4979;
    private Class91 aClass91_4980;
    private Class202 aClass202_4981;
    private float[] aFloatArray4982;
    static Class aClass4983;
    
    @Override
    final void method2442(final Class42 class42, final boolean b, final int n) {
        try {
            if (b) {
                method2457(null, true, -76);
            }
            super.aHa_Sub1_1215.method1863(1, class42);
            super.aHa_Sub1_1215.method1896(260, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eq.F(" + ((class42 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    @Override
    final void method2443(final boolean b, final int n) {
        try {
            if (n != 255) {
                this.aClass91_4980 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eq.C(" + b + ',' + n + ')');
        }
    }
    
    @Override
    final boolean method2439(final int n) {
        try {
            if (n != 31565) {
                this.aFloatArray4982 = null;
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eq.A(" + n + ')');
        }
    }
    
    @Override
    final void method2440(final boolean b, final boolean b2) {
        try {
            if (this.aClass91_4980 != null) {
                this.aClass91_4980.method888('\0', b);
                super.aHa_Sub1_1215.method1845(1, 847872872);
                OpenGL.glMatrixMode(5890);
                OpenGL.glLoadMatrixf(super.aHa_Sub1_1215.aClass111_Sub1_4354.method2113(-110), 0);
                OpenGL.glMatrixMode(5888);
                super.aHa_Sub1_1215.method1845(0, 847872872);
                if (~this.anInt4978 != ~super.aHa_Sub1_1215.anInt4321) {
                    int n = 128 * (super.aHa_Sub1_1215.anInt4321 % 5000) / 5000;
                    for (int n2 = 0; ~n2 > -65; ++n2) {
                        OpenGL.glProgramLocalParameter4fvARB(34336, n2, this.aFloatArray4982, n);
                        n += 2;
                    }
                    if (this.aClass51_4976.aBoolean424) {
                        this.aFloat4979 = super.aHa_Sub1_1215.anInt4321 % 4000 / 4000.0f;
                    }
                    else {
                        OpenGL.glProgramLocalParameter4fARB(34336, 65, 0.0f, 0.0f, 0.0f, 1.0f);
                    }
                    this.anInt4978 = super.aHa_Sub1_1215.anInt4321;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eq.D(" + b + ',' + b2 + ')');
        }
    }
    
    @Override
    final void method2441(final int n, final int n2, final int n3) {
        try {
            if (n3 > -2) {
                this.method2440(true, true);
            }
            if (this.aClass91_4980 != null) {
                super.aHa_Sub1_1215.method1845(1, 847872872);
                if ((0x80 & n) == 0x0) {
                    if (~(0x1 & n2) == 0xFFFFFFFE) {
                        if (!this.aClass51_4976.aBoolean424) {
                            super.aHa_Sub1_1215.method1863(1, this.aClass51_4976.aClass42_Sub1Array423[super.aHa_Sub1_1215.anInt4321 % 4000 * 16 / 4000]);
                            OpenGL.glProgramLocalParameter4fARB(34336, 65, 0.0f, 0.0f, 0.0f, 1.0f);
                        }
                        else {
                            super.aHa_Sub1_1215.method1863(1, this.aClass51_4976.aClass42_Sub4_422);
                            OpenGL.glProgramLocalParameter4fARB(34336, 65, this.aFloat4979, 0.0f, 0.0f, 1.0f);
                        }
                    }
                    else {
                        if (!this.aClass51_4976.aBoolean424) {
                            super.aHa_Sub1_1215.method1863(1, this.aClass51_4976.aClass42_Sub1Array423[0]);
                        }
                        else {
                            super.aHa_Sub1_1215.method1863(1, this.aClass51_4976.aClass42_Sub4_422);
                        }
                        OpenGL.glProgramLocalParameter4fARB(34336, 65, 0.0f, 0.0f, 0.0f, 1.0f);
                    }
                }
                else {
                    super.aHa_Sub1_1215.method1863(1, null);
                }
                super.aHa_Sub1_1215.method1845(0, 847872872);
                if (~(0x40 & n) == -1) {
                    Class269.aFloatArray2027[0] = super.aHa_Sub1_1215.aFloat4413 * super.aHa_Sub1_1215.aFloat4433;
                    Class269.aFloatArray2027[2] = super.aHa_Sub1_1215.aFloat4413 * super.aHa_Sub1_1215.aFloat4458;
                    Class269.aFloatArray2027[1] = super.aHa_Sub1_1215.aFloat4413 * super.aHa_Sub1_1215.aFloat4420;
                    OpenGL.glProgramLocalParameter4fvARB(34336, 66, Class269.aFloatArray2027, 0);
                }
                else {
                    OpenGL.glProgramLocalParameter4fARB(34336, 66, 1.0f, 1.0f, 1.0f, 1.0f);
                }
                final int n4 = n & 0x3;
                if (n4 == 2) {
                    OpenGL.glProgramLocalParameter4fARB(34336, 64, 0.05f, 1.0f, 1.0f, 1.0f);
                }
                else if (n4 == 3) {
                    OpenGL.glProgramLocalParameter4fARB(34336, 64, 0.1f, 1.0f, 1.0f, 1.0f);
                }
                else {
                    OpenGL.glProgramLocalParameter4fARB(34336, 64, 0.025f, 1.0f, 1.0f, 1.0f);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eq.G(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method2453(int anInt5824, final boolean b, int n, final int n2, int n3, int anInt5825) {
        try {
            if (~n > -2) {
                n = 1;
            }
            if (n3 < 1) {
                n3 = 1;
            }
            int n4 = -334 + n3;
            if (~n4 <= -1) {
                if (n4 > 100) {
                    n4 = 100;
                }
            }
            else {
                n4 = 0;
            }
            int n5 = n4 * (-Class265.aShort1973 + Class98_Sub43_Sub4.aShort5934) / n2 + Class265.aShort1973;
            if (Class284_Sub2_Sub2.aShort6201 <= n5) {
                if (n5 > Class112.aShort948) {
                    n5 = Class112.aShort948;
                }
            }
            else {
                n5 = Class284_Sub2_Sub2.aShort6201;
            }
            final int n6 = n5 * n3 * 512 / (n * 334);
            if (~n6 > ~Class42.aShort3231) {
                final short aShort3231 = Class42.aShort3231;
                n5 = 334 * (aShort3231 * n) / (512 * n3);
                if (Class112.aShort948 < n5) {
                    n5 = Class112.aShort948;
                    final int n7 = (n - n5 * n3 * 512 / (aShort3231 * 334)) / 2;
                    if (b) {
                        Class265.aHa1974.la();
                        Class265.aHa1974.method1760(n7, n3, anInt5824, -16777216, (byte)(-66), anInt5825);
                        Class265.aHa1974.method1760(n7, n3, anInt5824, -16777216, (byte)(-66), -n7 + (anInt5825 + n));
                    }
                    anInt5825 += n7;
                    n -= n7 * 2;
                }
            }
            else if (Class260.aShort3256 < n6) {
                final short aShort3232 = Class260.aShort3256;
                n5 = 334 * (aShort3232 * n) / (512 * n3);
                if (~Class284_Sub2_Sub2.aShort6201 < ~n5) {
                    n5 = Class284_Sub2_Sub2.aShort6201;
                    final int n8 = (n3 - n * aShort3232 * 334 / (n5 * 512)) / 2;
                    if (b) {
                        Class265.aHa1974.la();
                        Class265.aHa1974.method1760(n, n8, anInt5824, -16777216, (byte)(-66), anInt5825);
                        Class265.aHa1974.method1760(n, n8, anInt5824 - -n3 + -n8, -16777216, (byte)(-66), anInt5825);
                    }
                    anInt5824 += n8;
                    n3 -= 2 * n8;
                }
            }
            Class215.anInt1612 = (short)n;
            Class151_Sub8.anInt5016 = anInt5825;
            Class98_Sub31_Sub2.anInt5824 = anInt5824;
            Class98_Sub10_Sub14.anInt5610 = n5 * n3 / 334;
            Class332_Sub2.anInt5421 = (short)n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eq.I(" + anInt5824 + ',' + b + ',' + n + ',' + n2 + ',' + n3 + ',' + anInt5825 + ')');
        }
    }
    
    Class151_Sub3(final ha_Sub1 ha_Sub1, final Class51 aClass51_4976) {
        super(ha_Sub1);
        try {
            this.aClass51_4976 = aClass51_4976;
            if (super.aHa_Sub1_1215.aBoolean4431 && ~super.aHa_Sub1_1215.anInt4410 <= -3) {
                this.aClass202_4981 = Class347.method3835(0, super.aHa_Sub1_1215, 34336, "!!ARBvp1.0\nOPTION  ARB_position_invariant;\nATTRIB  iPos         = vertex.position;\nATTRIB  iColour      = vertex.color;\nATTRIB  iTexCoord    = vertex.texcoord[0];\nOUTPUT  oColour      = result.color;\nOUTPUT  oTexCoord0   = result.texcoord[0];\nOUTPUT  oTexCoord1   = result.texcoord[1];\nOUTPUT  oFogCoord    = result.fogcoord;\nPARAM   time         = program.local[65];\nPARAM   turbulence   = program.local[64];\nPARAM   lightAmbient = program.local[66]; \nPARAM   pMatrix[4]   = { state.matrix.projection };\nPARAM   mvMatrix[4]  = { state.matrix.modelview };\nPARAM   ivMatrix[4]  = { state.matrix.texture[1] };\nPARAM   texMatrix[4]  = { state.matrix.texture[0] };\nPARAM   fNoise[64]   = { program.local[0..63] };\nTEMP    noise, viewPos, worldPos, texCoord;\nADDRESS noiseAddr;\nDP4   viewPos.x, mvMatrix[0], iPos;\nDP4   viewPos.y, mvMatrix[1], iPos;\nDP4   viewPos.z, mvMatrix[2], iPos;\nDP4   viewPos.w, mvMatrix[3], iPos;\nMOV   oFogCoord.x, -viewPos.z;\nDP4   worldPos.x, ivMatrix[0], viewPos;\nDP4   worldPos.y, ivMatrix[1], viewPos;\nDP4   worldPos.z, ivMatrix[2], viewPos;\nDP4   worldPos.w, ivMatrix[3], viewPos;\nADD   noise.x, worldPos.x, worldPos.z;SUB   noise.y, worldPos.z, worldPos.x;MUL   noise, noise, 0.0001220703125;\nFRC   noise, noise;\nMUL   noise, noise, 64;\nARL   noiseAddr.x, noise.x;\nMOV   noise.x, fNoise[noiseAddr.x].x;\nARL   noiseAddr.x, noise.y;\nMOV   noise.y, fNoise[noiseAddr.x].y;\nMUL   noise, noise, turbulence.x;\nDP4   texCoord.x, texMatrix[0], iTexCoord;\nDP4   texCoord.y, texMatrix[1], iTexCoord;\nADD   oTexCoord0.xy, texCoord, noise;\nMOV   oTexCoord0.z, 0;\nMOV   oTexCoord0.w, 1;\nMUL   oTexCoord1.xy, texCoord, 0.125;\nMOV   oTexCoord1.zw, time.xxxw;\nMUL   oColour.xyz, iColour, lightAmbient;\nMOV   oColour.w, iColour.w;\nEND");
                if (this.aClass202_4981 != null) {
                    final int[][] method2258 = Class135.method2258(3, 256, false, 64, 0.4f, 0, 4, (byte)(-63), 4);
                    final int[][] method2259 = Class135.method2258(3, 256, false, 64, 0.4f, 8, 4, (byte)(-63), 4);
                    this.aFloatArray4982 = new float[32768];
                    int n = 0;
                    for (int i = 0; i < 256; ++i) {
                        final int[] array = method2258[i];
                        final int[] array2 = method2259[i];
                        for (int j = 0; j < 64; ++j) {
                            this.aFloatArray4982[n++] = array[j] / 4096.0f;
                            this.aFloatArray4982[n++] = array2[j] / 4096.0f;
                        }
                    }
                    this.method2454(13402);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eq.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + ((aClass51_4976 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method2454(final int n) {
        try {
            (this.aClass91_4980 = new Class91(super.aHa_Sub1_1215, 2)).method887(0, -30389);
            super.aHa_Sub1_1215.method1845(1, 847872872);
            super.aHa_Sub1_1215.method1882(-16777216, n - 13509);
            super.aHa_Sub1_1215.method1899(7681, n - 4442, 260);
            super.aHa_Sub1_1215.method1886(770, 0, 34200, 34166);
            super.aHa_Sub1_1215.method1845(0, 847872872);
            OpenGL.glBindProgramARB(34336, this.aClass202_4981.anInt1549);
            OpenGL.glEnable(34336);
            this.aClass91_4980.method886((byte)(-56));
            this.aClass91_4980.method887(1, n - 43791);
            super.aHa_Sub1_1215.method1845(1, n ^ 0x3289B732);
            OpenGL.glMatrixMode(5890);
            OpenGL.glLoadIdentity();
            if (n != 13402) {
                method2458(null, null, (byte)(-62));
            }
            OpenGL.glMatrixMode(5888);
            super.aHa_Sub1_1215.method1896(260, 0);
            super.aHa_Sub1_1215.method1886(770, 0, n ^ 0xB1C2, 5890);
            super.aHa_Sub1_1215.method1845(0, 847872872);
            OpenGL.glBindProgramARB(34336, 0);
            OpenGL.glDisable(34336);
            OpenGL.glDisable(34820);
            this.aClass91_4980.method886((byte)60);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eq.H(" + n + ')');
        }
    }
    
    static final short[][] method2455(final float[][] array, final byte b, final short[][] array2) {
        try {
            if (b != -78) {
                return null;
            }
            for (int i = 0; i < array.length; ++i) {
                for (int n = 0; ~array2[i].length < ~n; ++n) {
                    array2[i][n] = (short)(16383.0f * array[i][n]);
                }
            }
            return array2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eq.N(" + ((array != null) ? "{...}" : "null") + ',' + b + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2456(final int n) {
        try {
            if (n >= 28) {
                Class151_Sub3.aClass148_4977 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eq.B(" + n + ')');
        }
    }
    
    static final Class299 method2457(final Component component, final boolean b, final int n) {
        try {
            try {
                if (n != -16777216) {
                    method2455(null, (byte)(-127), null);
                }
                return (Class299)Class.forName("Class299_Sub1").getDeclaredConstructor((Class151_Sub3.aClass4983 != null) ? Class151_Sub3.aClass4983 : (Class151_Sub3.aClass4983 = method2459("java.awt.Component")), Boolean.TYPE).newInstance(component, new Boolean(b));
            }
            catch (Throwable t) {
                return new Class299_Sub2(component, b);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eq.J(" + ((component != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    @Override
    final void method2445(final byte b) {
        try {
            if (this.aClass91_4980 != null) {
                this.aClass91_4980.method888('\u0001', false);
                if (b <= 25) {
                    this.method2442(null, false, 69);
                }
                super.aHa_Sub1_1215.method1845(1, 847872872);
                super.aHa_Sub1_1215.method1863(1, null);
                super.aHa_Sub1_1215.method1845(0, 847872872);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eq.E(" + b + ')');
        }
    }
    
    static final void method2458(final Class246 class246, final Class246 aClass246_1874, final byte b) {
        try {
            if (class246.aClass246_1873 != null) {
                class246.method2965((byte)127);
            }
            if (b != 27) {
                method2458(null, null, (byte)98);
            }
            class246.aClass246_1873 = aClass246_1874.aClass246_1873;
            class246.aClass246_1874 = aClass246_1874;
            class246.aClass246_1873.aClass246_1874 = class246;
            class246.aClass246_1874.aClass246_1873 = class246;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "eq.K(" + ((class246 != null) ? "{...}" : "null") + ',' + ((aClass246_1874 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static Class method2459(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Class151_Sub3.aClass148_4977 = new Class148();
    }
}
