import java.awt.Rectangle;
import java.awt.Dimension;
import jagdx.IDirect3DBaseTexture;
import jagdx.jc;
import jagdx.IDirect3DPixelShader;
import java.awt.Canvas;
import jagdx.IDirect3DSurface;
import jagdx.D3DDISPLAYMODE;
import jagdx.IDirect3DEventQuery;
import jagdx.kg;
import jagdx.D3DADAPTER_IDENTIFIER;
import jaclib.peer.jaa;
import jagdx.IDirect3DDevice;
import jagdx.PixelBuffer;
import jagdx.D3DCAPS;
import jagdx.D3DLIGHT;
import jagdx.IDirect3DVertexShader;
import jagdx.IDirect3D;
import jagdx.GeometryBuffer;
import jagdx.D3DPRESENT_PARAMETERS;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ha_Sub3_Sub1 extends ha_Sub3
{
    private int anInt6083;
    private static int[] anIntArray6084;
    private D3DPRESENT_PARAMETERS aD3DPRESENT_PARAMETERS6085;
    GeometryBuffer aGeometryBuffer6086;
    private int[] anIntArray6087;
    private boolean[] aBooleanArray6088;
    private IDirect3D anIDirect3D6089;
    private int anInt6090;
    private IDirect3DVertexShader anIDirect3DVertexShader6091;
    private D3DLIGHT aD3DLIGHT6092;
    D3DCAPS aD3DCAPS6093;
    private boolean[] aBooleanArray6094;
    PixelBuffer aPixelBuffer6095;
    private boolean aBoolean6096;
    boolean aBoolean6097;
    IDirect3DDevice anIDirect3DDevice6098;
    private D3DLIGHT aD3DLIGHT6099;
    private boolean[] aBooleanArray6100;
    private static float[] aFloatArray6101;
    private Class200[] aClass200Array6102;
    private int anInt6103;
    private D3DLIGHT aD3DLIGHT6104;
    private boolean[] aBooleanArray6105;
    private static int[] anIntArray6106;
    boolean aBoolean6107;
    jaa aJaa6108;
    boolean aBoolean6109;
    private Class229 aClass229_6110;
    
    @Override
    final Interface4_Impl2 method1968(final int n, final int n2, final boolean b, final Class164 class164, final int n3, final int n4, final int n5, final float[] array) {
        Interface4_Impl2 interface4_Impl2;
        try {
            interface4_Impl2 = null;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return interface4_Impl2;
    }
    
    @Override
    final void method1746(final int n, final int n2, final int n3, final int n4) {
    }
    
    @Override
    final void F(final int n, final int n2) {
    }
    
    @Override
    final void method2007(final boolean b) {
        try {
            this.aFloat4592 = this.anInt4605 + -this.anInt4601;
            this.aFloat4615 = -this.anInt4581 + this.aFloat4592;
            if (b) {
                ha_Sub3_Sub1.anIntArray6106 = null;
            }
            if (this.aFloat4615 < this.anInt4640) {
                this.aFloat4615 = this.anInt4640;
            }
            this.anIDirect3DDevice6098.a(36, this.aFloat4615);
            this.anIDirect3DDevice6098.a(37, this.aFloat4592);
            this.anIDirect3DDevice6098.SetRenderState(34, this.anInt4636);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method2010(final int n) {
        try {
            if (null != this.anIDirect3DVertexShader6091 || Class98_Sub46_Sub19.aClass258_6062 == this.aClass258Array4644[this.anInt4579]) {
                this.anIDirect3DDevice6098.SetTextureStageState(this.anInt4579, 24, 0);
                this.anIntArray6087[this.anInt4579] = 0;
            }
            else {
                if (Class246_Sub3_Sub4_Sub5.aClass258_6260 != this.aClass258Array4644[this.anInt4579]) {
                    this.anIDirect3DDevice6098.SetTransform(this.anInt4579 + 16, this.aClass111_Sub3Array4609[this.anInt4579].method2121((byte)(-91), ha_Sub3_Sub1.aFloatArray6101));
                }
                else {
                    this.anIDirect3DDevice6098.SetTransform(this.anInt4579 + 16, this.aClass111_Sub3Array4609[this.anInt4579].method2122(ha_Sub3_Sub1.aFloatArray6101, 22));
                }
                final int method2071 = method2071(this.aClass258Array4644[this.anInt4579], 2474);
                if (method2071 != this.anIntArray6087[this.anInt4579]) {
                    this.anIDirect3DDevice6098.SetTextureStageState(this.anInt4579, 24, method2071);
                    this.anIntArray6087[this.anInt4579] = method2071;
                }
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final Interface2_Impl2 method1990(final byte b, final boolean b2) {
        Class125 class125;
        try {
            if (b != 84) {
                this.aBooleanArray6094 = null;
            }
            class125 = new Class125(this, Class162.aClass162_1267, b2);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return class125;
    }
    
    @Override
    final void method1773() {
        try {
            this.aJaa6108.b((byte)(-125));
            super.method1773();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1761(final boolean b) {
    }
    
    @Override
    final void method2021(final int n) {
        try {
            if (!this.aClass204_4551.method2708(98)) {
                ha_Sub3_Sub1.aFloatArray6101[15] = 1.0f;
                ha_Sub3_Sub1.aFloatArray6101[1] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[11] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[12] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[8] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[13] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[4] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[5] = 1.0f;
                ha_Sub3_Sub1.aFloatArray6101[6] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[14] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[7] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[2] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[3] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[0] = 1.0f;
                ha_Sub3_Sub1.aFloatArray6101[9] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[10] = 1.0f;
            }
            else {
                this.aClass111_Sub3_4545.method2121((byte)(-111), ha_Sub3_Sub1.aFloatArray6101);
            }
            if (n == 0) {
                this.anIDirect3DDevice6098.SetTransform(2, ha_Sub3_Sub1.aFloatArray6101);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final float method2050(final byte b) {
        float n;
        try {
            if (b != 56) {
                return 0.34527656f;
            }
            n = -0.5f;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return n;
    }
    
    private static final int method2068(final Class65 class65, final byte b) {
        try {
            if (b <= 125) {
                return -28;
            }
            if (class65 == Class300.aClass65_2499) {
                return 2;
            }
            if (class65 == Class98_Sub43_Sub3.aClass65_5926) {
                return 0;
            }
            if (class65 == IncomingOpcode.aClass65_459) {
                return 1;
            }
            if (Class64_Sub16.aClass65_3681 == class65) {
                return 3;
            }
            throw new IllegalArgumentException();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1776() {
    }
    
    @Override
    final Class62 method1799() {
        Class62 class62;
        try {
            final D3DADAPTER_IDENTIFIER b = this.anIDirect3D6089.b(this.anInt6103, 0);
            class62 = new Class62(b.VendorID, "Direct3D", 9, b.Description, b.DriverVersion);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return class62;
    }
    
    private static final int method2069(final int n, final Class232 class232) {
        try {
            if (class232 == Class334.aClass232_3468) {
                return 2;
            }
            if (class232 == Class287_Sub1.aClass232_3420) {
                return 3;
            }
            if (class232 == Class224.aClass232_1685) {
                return 1;
            }
            if (Class336.aClass232_2822 == class232) {
                return 4;
            }
            if (Class98_Sub46_Sub15.aClass232_6043 == class232) {
                return 6;
            }
            if (class232 == Class97.aClass232_806) {
                return 5;
            }
            throw new IllegalArgumentException("");
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1989(final int n) {
        try {
            if (!this.aBoolean6096) {
                this.anIDirect3DDevice6098.LightEnable(0, false);
                this.anIDirect3DDevice6098.LightEnable(1, false);
                this.anIDirect3DDevice6098.SetLight(0, this.aD3DLIGHT6099);
                this.anIDirect3DDevice6098.SetLight(1, this.aD3DLIGHT6104);
                this.anIDirect3DDevice6098.LightEnable(0, true);
                this.anIDirect3DDevice6098.LightEnable(1, true);
                this.aBoolean6096 = true;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final void method2070(final IDirect3DVertexShader anIDirect3DVertexShader6091, final boolean b) {
        try {
            this.anIDirect3DVertexShader6091 = anIDirect3DVertexShader6091;
            this.anIDirect3DDevice6098.SetVertexShader(anIDirect3DVertexShader6091);
            this.method2010(-114);
            if (!b) {
                this.anInt6090 = -60;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1740(final Interface17 interface17) {
    }
    
    @Override
    final void method2046(final int n) {
        try {
            this.anIDirect3DDevice6098.a(15, this.aBoolean4599);
            if (n != 0) {
                this.aBooleanArray6088 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private static final int method2071(final Class258 class258, final int n) {
        boolean b;
        try {
            if (n != 2474) {
                ha_Sub3_Sub1.anIntArray6106 = null;
            }
            if (Class98_Sub10_Sub6.aClass258_5568 == class258) {
                return 1;
            }
            if (class258 == Class246_Sub3_Sub4_Sub5.aClass258_6260) {
                return 2;
            }
            if (class258 == Class144.aClass258_1168) {
                return 3;
            }
            if (Class98_Sub46_Sub13_Sub1.aClass258_6307 == class258) {
                return 4;
            }
            if (Class246_Sub4_Sub1.aClass258_6169 == class258) {
                return 256;
            }
            b = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b ? 1 : 0;
    }
    
    @Override
    final void method1825() {
        try {
            final IDirect3DEventQuery c = this.anIDirect3DDevice6098.c();
            if (kg.a(-21593, c.Issue())) {
                while (c.IsSignaled() == 1) {
                    Thread.yield();
                }
            }
            c.b(99);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final int I() {
        boolean b;
        try {
            b = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b ? 1 : 0;
    }
    
    @Override
    final void method2037(final Class232 class232, final int n, final byte b, final int n2) {
        try {
            this.anIDirect3DDevice6098.DrawPrimitive(method2069(-101, class232), n, n2);
            if (b > 8) {}
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final Interface5 method1813(final int n, final int n2) {
        Interface5 interface5;
        try {
            interface5 = null;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return interface5;
    }
    
    @Override
    final void method1814() {
    }
    
    @Override
    final void method2036(final int n) {
        try {
            this.anIDirect3DDevice6098.SetViewport(this.anInt4578, this.anInt4622, this.anInt4527, this.anInt4531, 0.0f, 1.0f);
            if (n != -11155) {
                this.aBoolean6109 = true;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final Interface2_Impl1 method2060(final boolean b, final int n) {
        Class80 class80;
        try {
            if (n <= 40) {
                this.method1972(-48);
            }
            class80 = new Class80(this, b);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return class80;
    }
    
    @Override
    final boolean method1977(final Class162 class162, final boolean b, final Class164 class163) {
        boolean b2;
        try {
            if (!b) {
                method2074(19, null, null);
            }
            final D3DDISPLAYMODE d3DDISPLAYMODE = new D3DDISPLAYMODE();
            if (kg.a(-21593, this.anIDirect3D6089.a(this.anInt6103, d3DDISPLAYMODE)) && kg.a(-21593, this.anIDirect3D6089.CheckDeviceFormat(this.anInt6103, this.anInt6090, d3DDISPLAYMODE.Format, 0, 4, method2074(-1935, class163, class162)))) {
                return true;
            }
            b2 = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b2;
    }
    
    @Override
    final void method1955(final int n) {
        try {
            if (n != -5668) {
                ha_Sub3_Sub1.anIntArray6084 = null;
            }
            this.anIDirect3DDevice6098.a(137, this.aBoolean4643 && !this.aBoolean4637);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final synchronized void method1806(final int n) {
        try {
            this.aJaa6108.a((byte)126);
            super.method1806(n);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method2009(final int n) {
        try {
            if (n != 28976) {
                this.aBoolean6096 = false;
            }
            this.anIDirect3DDevice6098.SetScissorRect(this.anInt4602 + this.anInt4578, this.anInt4558 + this.anInt4622, this.anInt4575, this.anInt4638);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final int[] na(final int n, final int n2, final int n3, final int n4) {
        int[] array2;
        try {
            int[] array = null;
            final IDirect3DSurface d = this.anIDirect3DDevice6098.d(0);
            final IDirect3DSurface a = this.anIDirect3DDevice6098.a(n3, n4, 21, 0, 0, true);
            if (kg.a(-21593, this.anIDirect3DDevice6098.StretchRect(d, n, n2, n3, n4, a, 0, 0, n3, n4, 0)) && kg.a(-21593, a.LockRect(0, 0, n3, n4, 16, this.aPixelBuffer6095))) {
                array = new int[n3 * n4];
                final int rowPitch = this.aPixelBuffer6095.getRowPitch();
                if (~(4 * n3) == ~rowPitch) {
                    this.aPixelBuffer6095.b(array, 0, 0, n3 * n4);
                }
                else {
                    for (int n5 = 0; ~n5 > ~n4; ++n5) {
                        this.aPixelBuffer6095.b(array, n3 * n5, n5 * rowPitch, n3);
                    }
                }
                a.UnlockRect();
            }
            d.b(71);
            a.b(80);
            array2 = array;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return array2;
    }
    
    @Override
    final void method1756() {
    }
    
    @Override
    final void method2057(final int n) {
        try {
            if (n == 12362) {
                this.anIDirect3DDevice6098.SetRenderState(60, this.anInt4648);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private static final int method2072(final boolean b, final Class128 class128) {
        try {
            if (b) {
                ha_Sub3_Sub1.aFloatArray6101 = null;
            }
            if (class128 == Class288.aClass128_3381) {
                return 2;
            }
            if (class128 == Class249.aClass128_1903) {
                return 4;
            }
            if (Class323.aClass128_2715 == class128) {
                return 26;
            }
            if (Class1.aClass128_64 == class128) {
                return 7;
            }
            if (class128 == Class28.aClass128_286) {
                return 10;
            }
            throw new IllegalArgumentException();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final Class76 method2067(final int n, final byte b) {
        Class76 method2067;
        try {
            if (3 == n) {
                return new Class76_Sub6(this, this.aClass207_4535);
            }
            if (0xFFFFFFFB == ~n) {
                return new Class76_Sub3(this, this.aClass207_4535, this.aClass195_4529);
            }
            if (n == 8) {
                return new Class76_Sub1(this, this.aClass207_4535, this.aClass195_4529);
            }
            method2067 = super.method2067(n, (byte)102);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return method2067;
    }
    
    @Override
    final boolean method1768() {
        boolean b;
        try {
            b = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b;
    }
    
    @Override
    final void method2025(final byte b) {
        try {
            this.method2004((byte)(-118));
            this.method1989(73);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private static final int method2073(final int n, final Class200 class200) {
        try {
            if (Class284_Sub1_Sub1.aClass200_6187 == class200) {
                return 2;
            }
            if (class200 == Class342.aClass200_2861) {
                return 1;
            }
            if (n != 26476) {
                return -49;
            }
            throw new IllegalArgumentException();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final Class48 method1769(final Class48 class48, final Class48 class49, final float n, final Class48 class50) {
        try {
            if (n < 0.5f) {
                return class48;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return class49;
    }
    
    @Override
    final void method1964(final Class38 class38, final byte b) {
        try {
            if (b != 26) {
                this.method1942(-76, null, null);
            }
            int n = 0;
            if (class38 != Class357.aClass38_3026) {
                if (Class98.aClass38_834 == class38) {
                    n = 131072;
                }
                else if (Class204.aClass38_1552 == class38) {
                    n = 196608;
                }
            }
            else {
                n = 65536;
            }
            this.anIDirect3DDevice6098.SetTextureStageState(this.anInt4579, 11, n | this.anInt4579);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static final int method2074(final int n, final Class164 class164, final Class162 class165) {
        try {
            if (Class162.aClass162_1266 == class165) {
                if (class164 == Class98_Sub40.aClass164_4190) {
                    return 22;
                }
                if (Class62.aClass164_486 == class164) {
                    return 21;
                }
                if (class164 == Class53_Sub1.aClass164_3633) {
                    return 28;
                }
                if (class164 == Class98_Sub30.aClass164_4088) {
                    return 50;
                }
                if (Class74.aClass164_547 == class164) {
                    return 51;
                }
                if (class164 == Class280.aClass164_2101) {
                    return 77;
                }
            }
            if (n != -1935) {
                method2069(119, null);
            }
            throw new IllegalArgumentException("");
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1983(final byte b) {
        try {
            if (this.aBoolean4540) {
                ha_Sub3_Sub1.aFloatArray6101[5] = 1.0f;
                ha_Sub3_Sub1.aFloatArray6101[9] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[7] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[3] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[11] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[10] = 1.0f;
                ha_Sub3_Sub1.aFloatArray6101[4] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[13] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[2] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[6] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[12] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[0] = 1.0f;
                ha_Sub3_Sub1.aFloatArray6101[14] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[1] = 0.0f;
                ha_Sub3_Sub1.aFloatArray6101[15] = 1.0f;
                ha_Sub3_Sub1.aFloatArray6101[8] = 0.0f;
            }
            else {
                this.aClass111_Sub3_4542.method2121((byte)(-90), ha_Sub3_Sub1.aFloatArray6101);
            }
            this.anIDirect3DDevice6098.SetTransform(256, ha_Sub3_Sub1.aFloatArray6101);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void ya() {
        try {
            this.method1997(0, true);
            this.anIDirect3DDevice6098.Clear(2, 0, 1.0f, 0);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1973(final Class232 class232, final int n, final int n2, final int n3, final Interface2_Impl2 interface2_Impl2, final int n4, final int n5) {
        try {
            this.anIDirect3DDevice6098.SetIndices(((Class125)interface2_Impl2).anIDirect3DIndexBuffer5350);
            if (n3 != 26810) {
                this.aBooleanArray6094 = null;
            }
            this.anIDirect3DDevice6098.DrawIndexedPrimitive(method2069(-85, class232), 0, n4, n, n2, n5);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1936(final int n, final Object o, final Canvas canvas) {
        try {
            if (n != 0) {
                method2080(4, -101, null, -18, null, 124);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final void method2075(final int n, final IDirect3DPixelShader direct3DPixelShader) {
        try {
            if (n != 28) {
                method2072(true, null);
            }
            this.anIDirect3DDevice6098.SetPixelShader(direct3DPixelShader);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private ha_Sub3_Sub1(final int anInt6103, final int anInt6104, final Canvas canvas, final jaa aJaa6108, final IDirect3D anIDirect3D6089, final IDirect3DDevice anIDirect3DDevice6098, final Class229 aClass229_6110, final D3DPRESENT_PARAMETERS ad3DPRESENT_PARAMETERS6085, final D3DCAPS ad3DCAPS6093, final d d, final Class207 class207, final int n) {
        super(canvas, aClass229_6110, d, class207, n, 0);
        this.anInt6083 = 0;
        this.aBoolean6096 = false;
        try {
            try {
                this.anInt6103 = anInt6103;
                this.aJaa6108 = aJaa6108;
                this.anIDirect3DDevice6098 = anIDirect3DDevice6098;
                this.anInt6090 = anInt6104;
                this.aD3DPRESENT_PARAMETERS6085 = ad3DPRESENT_PARAMETERS6085;
                this.aClass229_6110 = aClass229_6110;
                this.aD3DCAPS6093 = ad3DCAPS6093;
                this.anIDirect3D6089 = anIDirect3D6089;
                this.aD3DLIGHT6099 = new D3DLIGHT(this.aJaa6108);
                this.aD3DLIGHT6104 = new D3DLIGHT(this.aJaa6108);
                this.aD3DLIGHT6092 = new D3DLIGHT(this.aJaa6108);
                this.aPixelBuffer6095 = new PixelBuffer(this.aJaa6108);
                this.aGeometryBuffer6086 = new GeometryBuffer(this.aJaa6108);
                new GeometryBuffer(this.aJaa6108);
                this.anInt4608 = this.aD3DCAPS6093.MaxSimultaneousTextures;
                this.aBoolean6097 = ((0x10000 & this.aD3DCAPS6093.TextureCaps) != 0x0);
                this.aBoolean4569 = (-1 != ~(this.aD3DCAPS6093.TextureCaps & 0x800));
                this.anInt4565 = ((this.aD3DCAPS6093.MaxActiveLights > 0) ? this.aD3DCAPS6093.MaxActiveLights : 8);
                this.aBoolean6107 = (-1 != ~(this.aD3DCAPS6093.TextureCaps & 0x4000));
                this.aBoolean4588 = (-1 != ~(0x2000 & this.aD3DCAPS6093.TextureCaps));
                this.aBoolean6109 = ((this.aD3DCAPS6093.TextureCaps & 0x2) == 0x0);
                this.aBoolean4559 = (-1 > ~this.anInt4616 || this.anIDirect3D6089.CheckDeviceMultiSampleType(this.anInt6103, this.anInt6090, this.aD3DPRESENT_PARAMETERS6085.BackBufferFormat, true, 2) == 0);
                this.aBooleanArray6100 = new boolean[this.anInt4608];
                this.aBooleanArray6088 = new boolean[this.anInt4608];
                this.aBooleanArray6094 = new boolean[this.anInt4608];
                this.aBooleanArray6105 = new boolean[this.anInt4608];
                this.anIntArray6087 = new int[this.anInt4608];
                this.aClass200Array6102 = new Class200[this.anInt4608];
                this.anIDirect3DDevice6098.BeginScene();
            }
            catch (Throwable t) {
                t.printStackTrace();
                this.method1743(-1);
                throw new RuntimeException("");
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final Interface4_Impl2 method2006(final int n, final int n2, final Class164 class164, final byte b, final Class162 class165) {
        Class26_Sub3 class26_Sub3;
        try {
            if (b != 45) {
                this.method1802();
            }
            class26_Sub3 = new Class26_Sub3(this, class164, class165, n2, n);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return class26_Sub3;
    }
    
    @Override
    final Interface13 method1744(final int n, final int n2) {
        Interface13 interface13;
        try {
            interface13 = null;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return interface13;
    }
    
    private final boolean method2076(final int n) {
        boolean b;
        try {
            if (n >= -127) {
                return true;
            }
            final int testCooperativeLevel = this.anIDirect3DDevice6098.TestCooperativeLevel();
            if (testCooperativeLevel == 0 || ~testCooperativeLevel == 0x7789F796) {
                final Class229 class229 = (Class229)this.anObject4530;
                this.method1981((byte)(-105));
                class229.method2868((byte)88);
                this.aD3DPRESENT_PARAMETERS6085.BackBufferWidth = 0;
                this.aD3DPRESENT_PARAMETERS6085.BackBufferHeight = 0;
                if (method2080(this.anInt4616, this.anInt6103, this.anIDirect3D6089, 0, this.aD3DPRESENT_PARAMETERS6085, this.anInt6090) && kg.a(-21593, this.anIDirect3DDevice6098.Reset(this.aD3DPRESENT_PARAMETERS6085))) {
                    class229.method2866(this.anIDirect3DDevice6098.b(), (byte)(-85), this.anIDirect3DDevice6098.c(0));
                    this.method2064((byte)(-61));
                    this.method1941(1);
                    return true;
                }
            }
            b = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b;
    }
    
    final void method2077(final boolean b, final Class26_Sub2 class26_Sub2) {
        try {
            this.method2078(class26_Sub2, 0);
            if (!this.aBooleanArray6088[this.anInt4579]) {
                this.anIDirect3DDevice6098.SetSamplerState(this.anInt4579, 1, 1);
                this.aBooleanArray6088[this.anInt4579] = true;
            }
            if (!this.aBooleanArray6100[this.anInt4579]) {
                this.anIDirect3DDevice6098.SetSamplerState(this.anInt4579, 2, 1);
                this.aBooleanArray6100[this.anInt4579] = true;
            }
            if (b) {
                this.aBoolean6097 = true;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1817() {
    }
    
    @Override
    final boolean method1802() {
        boolean b;
        try {
            b = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b;
    }
    
    final void method2078(final Class26 class26, final int n) {
        try {
            this.anIDirect3DDevice6098.SetTexture(this.anInt4579, class26.method293((byte)18));
            if (class26.aClass200_272 != this.aClass200Array6102[this.anInt4579]) {
                final int method2073 = method2073(26476, class26.aClass200_272);
                this.anIDirect3DDevice6098.SetSamplerState(this.anInt4579, 6, method2073);
                this.anIDirect3DDevice6098.SetSamplerState(this.anInt4579, 5, method2073);
                this.aClass200Array6102[this.anInt4579] = class26.aClass200_272;
                if (!this.aBooleanArray6105[this.anInt4579] != !class26.aBoolean269) {
                    this.anIDirect3DDevice6098.SetSamplerState(this.anInt4579, 7, class26.aBoolean269 ? method2073(26476, class26.aClass200_272) : 0);
                    this.aBooleanArray6105[this.anInt4579] = class26.aBoolean269;
                }
            }
            else if (!class26.aBoolean269 != !this.aBooleanArray6105[this.anInt4579]) {
                this.anIDirect3DDevice6098.SetSamplerState(this.anInt4579, 7, class26.aBoolean269 ? method2073(26476, class26.aClass200_272) : 0);
                this.aBooleanArray6105[this.anInt4579] = class26.aBoolean269;
            }
            if (n == 0) {
                if (!this.aBooleanArray6094[this.anInt4579]) {
                    this.aBooleanArray6094[this.anInt4579] = true;
                    this.method1958((byte)(-48));
                    this.method2011(2);
                }
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1994(final byte b, final int n) {
        try {
            if (b < 0) {
                this.anIntArray6087 = null;
            }
            this.anIDirect3DDevice6098.SetTextureStageState(this.anInt4579, 11, n);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method2065(final byte b) {
        try {
            this.aD3DLIGHT6099.SetDirection(-this.aFloatArray4596[0], -this.aFloatArray4596[1], -this.aFloatArray4596[2]);
            this.aD3DLIGHT6104.SetDirection(-this.aFloatArray4572[0], -this.aFloatArray4572[1], -this.aFloatArray4572[2]);
            if (b == 80) {
                this.aBoolean6096 = false;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static final ha createToolkit(final Canvas canvas, final d d, final Class207 class207, final Integer n) {
        try {
            ha_Sub3_Sub1 ha_Sub3_Sub1 = null;
            ha_Sub3_Sub1 ha_Sub3_Sub2;
            try {
                final int n2 = 0;
                final int n3 = 1;
                final jaa jaa = new jaa();
                final IDirect3D a = IDirect3D.a(-2147483616, jaa);
                final D3DCAPS a2 = a.a(n2, n3);
                if (0x0 == (0x1000000 & a2.RasterCaps)) {
                    throw new RuntimeException("");
                }
                if (2 > a2.MaxSimultaneousTextures) {
                    throw new RuntimeException("");
                }
                if ((a2.TextureOpCaps & 0x2) == 0x0) {
                    throw new RuntimeException("");
                }
                if (0x0 == (0x8 & a2.TextureOpCaps)) {
                    throw new RuntimeException("");
                }
                if (-1 == ~(0x40 & a2.TextureOpCaps)) {
                    throw new RuntimeException("");
                }
                if ((a2.TextureOpCaps & 0x200) == 0x0) {
                    throw new RuntimeException("");
                }
                if ((0x2000000 & a2.TextureOpCaps) == 0x0) {
                    throw new RuntimeException("");
                }
                if (0x0 == (0x10 & (a2.DestBlendCaps & a2.SrcBlendCaps))) {
                    throw new RuntimeException("");
                }
                if (0x0 == (a2.SrcBlendCaps & a2.DestBlendCaps & 0x20)) {
                    throw new RuntimeException("");
                }
                if (~(0x2 & (a2.DestBlendCaps & a2.SrcBlendCaps)) == -1) {
                    throw new RuntimeException("");
                }
                if (-1 > ~a2.MaxActiveLights && -3 < ~a2.MaxActiveLights) {
                    throw new RuntimeException("");
                }
                if (~a2.MaxStreams > -6) {
                    throw new RuntimeException("");
                }
                final D3DPRESENT_PARAMETERS d3DPRESENT_PARAMETERS = new D3DPRESENT_PARAMETERS(canvas);
                if (!method2080(n, n2, a, 0, d3DPRESENT_PARAMETERS, n3)) {
                    throw new RuntimeException("");
                }
                d3DPRESENT_PARAMETERS.PresentationInterval = Integer.MIN_VALUE;
                d3DPRESENT_PARAMETERS.EnableAutoDepthStencil = true;
                d3DPRESENT_PARAMETERS.Windowed = true;
                int n4 = 2;
                if (-1 != ~(0x100000 & a2.DevCaps)) {
                    n4 |= 0x10;
                }
                IDirect3DDevice direct3DDevice;
                try {
                    direct3DDevice = a.a(n2, n3, canvas, 0x40 | n4, d3DPRESENT_PARAMETERS);
                }
                catch (jc jc) {
                    direct3DDevice = a.a(n2, n3, canvas, 0x20 | n4, d3DPRESENT_PARAMETERS);
                }
                ha_Sub3_Sub1 = new ha_Sub3_Sub1(n2, n3, canvas, jaa, a, direct3DDevice, new Class229(direct3DDevice.c(0), direct3DDevice.b()), d3DPRESENT_PARAMETERS, a2, d, class207, n);
                ha_Sub3_Sub1.method1965(true);
                ha_Sub3_Sub2 = ha_Sub3_Sub1;
            }
            catch (RuntimeException ex) {
                if (ha_Sub3_Sub1 != null) {
                    ha_Sub3_Sub1.method1773();
                }
                throw ex;
            }
            return ha_Sub3_Sub2;
        }
        catch (RuntimeException ex2) {
            throw ex2;
        }
    }
    
    @Override
    final void method1991(final int n) {
        try {
            this.aD3DLIGHT6099.SetAmbient(this.aFloat4611 * this.aFloat4576, this.aFloat4549 * this.aFloat4576, this.aFloat4576 * this.aFloat4591, 0.0f);
            this.aBoolean6096 = false;
            if (n != -24391) {
                this.method2007(true);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method2030(final byte b) {
        try {
            if (b != -122) {
                this.F(29, 70);
            }
            this.anIDirect3DDevice6098.a(174, this.aBoolean4672);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final boolean method1942(final int n, final Class164 class164, final Class162 class165) {
        boolean b;
        try {
            final D3DDISPLAYMODE d3DDISPLAYMODE = new D3DDISPLAYMODE();
            if (n != 0) {
                method2080(-93, -77, null, -97, null, -25);
            }
            if (kg.a(-21593, this.anIDirect3D6089.a(this.anInt6103, d3DDISPLAYMODE)) && kg.a(-21593, this.anIDirect3D6089.CheckDeviceFormat(this.anInt6103, this.anInt6090, d3DDISPLAYMODE.Format, 0, 3, method2074(-1935, class164, class165)))) {
                return true;
            }
            b = false;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b;
    }
    
    @Override
    final Class256 method1982(final Class49[] array, final int n) {
        dxVertexLayout dxVertexLayout;
        try {
            if (n != 6) {
                return null;
            }
            dxVertexLayout = new dxVertexLayout(this, array);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return dxVertexLayout;
    }
    
    @Override
    final Interface4_Impl2 method2063(final int n, final byte b, final int[] array, final boolean b2, final int n2, final int n3, final int n4) {
        Class26_Sub3 class26_Sub3;
        try {
            class26_Sub3 = new Class26_Sub3(this, n2, n3, b2, array, n, n4);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return class26_Sub3;
    }
    
    @Override
    final void method1972(final int n) {
        try {
            if (n == 0) {
                this.anIDirect3DDevice6098.a(14, this.aBoolean4606 && this.aBoolean4620);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1764(final int n, final int n2) throws Exception_Sub1 {
        try {
            this.anIDirect3DDevice6098.EndScene();
            if (this.aClass229_6110.method2867(0)) {
                this.anInt6083 = 0;
                if (kg.b(-7175, this.aClass229_6110.method2865(true, 0))) {
                    this.method2076(-128);
                }
            }
            else {
                if (50 < ++this.anInt6083) {
                    throw new Exception_Sub1();
                }
                this.method2076(-128);
            }
            this.anIDirect3DDevice6098.BeginScene();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final Interface4_Impl2 method2032(final byte[] array, final boolean b, final Class164 class164, final int n, final int n2, final boolean b2, final int n3, final int n4) {
        Class26_Sub3 class26_Sub3;
        try {
            if (b) {
                this.method2016((byte)12);
            }
            class26_Sub3 = new Class26_Sub3(this, class164, n4, n2, b2, array, n3, n);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return class26_Sub3;
    }
    
    @Override
    final void method1944(final Object o, final Canvas canvas, final byte b) {
        try {
            this.aClass229_6110 = (Class229)o;
            if (b != -34) {
                this.aJaa6108 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1950(final byte b) {
    }
    
    final void method2079(final boolean b, final Class26_Sub3 class26_Sub3) {
        try {
            this.method2078(class26_Sub3, 0);
            if (class26_Sub3.aBoolean3584 != this.aBooleanArray6088[this.anInt4579]) {
                this.anIDirect3DDevice6098.SetSamplerState(this.anInt4579, 1, class26_Sub3.aBoolean3584 ? 1 : 3);
                this.aBooleanArray6088[this.anInt4579] = class26_Sub3.aBoolean3584;
            }
            if (b) {
                this.method1942(-63, null, null);
            }
            if (class26_Sub3.aBoolean3585 == !this.aBooleanArray6100[this.anInt4579]) {
                this.anIDirect3DDevice6098.SetSamplerState(this.anInt4579, 2, class26_Sub3.aBoolean3585 ? 1 : 3);
                this.aBooleanArray6100[this.anInt4579] = class26_Sub3.aBoolean3585;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1999(final byte b) {
        try {
            this.anIDirect3DDevice6098.a(28, this.aBoolean4571 && this.aBoolean4562 && this.anInt4581 >= 0);
            if (b != 112) {
                ha_Sub3_Sub1.anIntArray6106 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void GA(final int n) {
        try {
            this.anIDirect3DDevice6098.Clear(1, n, 0.0f, 0);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final Interface4_Impl3 method1934(final int n, final boolean b, final int[][] array, final int n2) {
        Class26_Sub1 class26_Sub1;
        try {
            if (n != 8) {
                return null;
            }
            class26_Sub1 = new Class26_Sub1(this, n2, b, array);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return class26_Sub1;
    }
    
    @Override
    final void method1971(final int n, final boolean b, final Interface2_Impl1 interface2_Impl1) {
        try {
            final Class80 class80 = (Class80)interface2_Impl1;
            this.anIDirect3DDevice6098.SetStreamSource(n, class80.anIDirect3DVertexBuffer5337, 0, class80.method810((byte)(-22)));
            if (!b) {
                ha_Sub3_Sub1.anIntArray6084 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1958(final byte b) {
        try {
            this.anIDirect3DDevice6098.SetTextureStageState(this.anInt4579, 4, this.aBooleanArray6094[this.anInt4579] ? method2072(false, this.aClass128Array4639[this.anInt4579]) : 1);
            if (b != -48) {
                this.aBooleanArray6100 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1959(final int n) {
        try {
            if (this.aBooleanArray6094[this.anInt4579]) {
                this.aBooleanArray6094[this.anInt4579] = false;
                this.anIDirect3DDevice6098.SetTexture(this.anInt4579, null);
                this.method1958((byte)(-48));
                this.method2011(2);
            }
            if (n != 0) {
                this.aBoolean6096 = true;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final Object method2058(final Canvas canvas, final int n) {
        Object o;
        try {
            if (n != -8401) {
                return null;
            }
            o = null;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return o;
    }
    
    @Override
    final void method1966(final byte b) {
        try {
            this.anIDirect3DDevice6098.SetTransform(3, this.aFloatArray4566);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final Interface17 method1815(final Interface5 interface5, final Interface13 interface6) {
        Interface17 interface7;
        try {
            interface7 = null;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return interface7;
    }
    
    @Override
    final void method2042(final Class256 class256, final byte b) {
        try {
            this.anIDirect3DDevice6098.SetVertexDeclaration(((dxVertexLayout)class256).anIDirect3DVertexDeclaration5155);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1988(final Canvas canvas, final Object o, final int n) {
        try {
            if (n > -40) {
                this.method1950((byte)(-56));
            }
            if (this.aCanvas4533 == canvas) {
                final Dimension size = canvas.getSize();
                if (~size.width < -1 && ~size.height < -1) {
                    this.anIDirect3DDevice6098.EndScene();
                    this.method2076(-128);
                    this.anIDirect3DDevice6098.BeginScene();
                }
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private static final boolean method2080(int n, final int n2, final IDirect3D direct3D, final int n3, final D3DPRESENT_PARAMETERS d3DPRESENT_PARAMETERS, final int n4) {
        boolean b2;
        try {
            int autoDepthStencilFormat = 0;
            int backBufferFormat = 0;
            int multiSampleType = 0;
            boolean b;
            try {
                final D3DDISPLAYMODE d3DDISPLAYMODE = new D3DDISPLAYMODE();
                if (kg.b(n3 - 7175, direct3D.a(n2, d3DDISPLAYMODE))) {
                    return false;
                }
            Label_0266:
                while (0 <= n) {
                    if (0xFFFFFFFE != ~n) {
                        multiSampleType = 0 + n;
                        for (int n5 = 0; ~n5 > ~ha_Sub3_Sub1.anIntArray6106.length; ++n5) {
                            if (direct3D.CheckDeviceType(n2, n4, d3DDISPLAYMODE.Format, ha_Sub3_Sub1.anIntArray6106[n5], true) == 0 && direct3D.CheckDeviceFormat(n2, n4, d3DDISPLAYMODE.Format, 1, 1, ha_Sub3_Sub1.anIntArray6106[n5]) == 0 && (n == 0 || ~direct3D.CheckDeviceMultiSampleType(n2, n4, ha_Sub3_Sub1.anIntArray6106[n5], true, multiSampleType) == -1)) {
                                for (int n6 = 0; ha_Sub3_Sub1.anIntArray6084.length > n6; ++n6) {
                                    if (direct3D.CheckDeviceFormat(n2, n4, d3DDISPLAYMODE.Format, 2, 1, ha_Sub3_Sub1.anIntArray6084[n6]) == 0 && 0 == direct3D.CheckDepthStencilMatch(n2, n4, d3DDISPLAYMODE.Format, ha_Sub3_Sub1.anIntArray6106[n5], ha_Sub3_Sub1.anIntArray6084[n6]) && (n == 0 || direct3D.CheckDeviceMultiSampleType(n2, n4, ha_Sub3_Sub1.anIntArray6084[n5], true, multiSampleType) == 0)) {
                                        backBufferFormat = ha_Sub3_Sub1.anIntArray6106[n5];
                                        autoDepthStencilFormat = ha_Sub3_Sub1.anIntArray6084[n6];
                                        break Label_0266;
                                    }
                                }
                            }
                        }
                    }
                    --n;
                }
                if (n < n3 || backBufferFormat == 0 || autoDepthStencilFormat == 0) {
                    return false;
                }
                d3DPRESENT_PARAMETERS.BackBufferFormat = backBufferFormat;
                d3DPRESENT_PARAMETERS.MultiSampleQuality = 0;
                d3DPRESENT_PARAMETERS.MultiSampleType = multiSampleType;
                d3DPRESENT_PARAMETERS.AutoDepthStencilFormat = autoDepthStencilFormat;
                b = true;
            }
            catch (Throwable t) {
                b2 = false;
                return b2;
            }
            return b;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return b2;
    }
    
    @Override
    final void method2035(final int n) {
        try {
            int n2;
            for (n2 = 0; this.anInt4619 > n2; ++n2) {
                final Class98_Sub5 class98_Sub5 = this.aClass98_Sub5Array4597[n2];
                final int n3 = n2 + 2;
                final int method961 = class98_Sub5.method961((byte)(-78));
                final float n4 = class98_Sub5.method956(false) / 255.0f;
                this.aD3DLIGHT6092.SetPosition(class98_Sub5.method954(7019), class98_Sub5.method963((byte)115), class98_Sub5.method962(n + 43412));
                this.aD3DLIGHT6092.SetDiffuse(((method961 & 0xFF6196) >> 1871372912) * n4, ((method961 & 0xFF55) >> -1608995352) * n4, (0xFF & method961) * n4, 0.0f);
                this.aD3DLIGHT6092.SetAttenuation(0.0f, 0.0f, 1.0f / (class98_Sub5.method958(n + 14840) * class98_Sub5.method958(n ^ 0xFFFFC6F9)));
                this.aD3DLIGHT6092.SetRange(class98_Sub5.method958(n ^ 0xFFFFC6F9));
                this.anIDirect3DDevice6098.SetLight(n3, this.aD3DLIGHT6092);
                this.anIDirect3DDevice6098.LightEnable(n3, true);
            }
            while (this.anInt4628 > n2) {
                this.anIDirect3DDevice6098.LightEnable(n2 + 2, false);
                ++n2;
            }
            super.method2035(n);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method2026(final int n, final boolean b, final byte b2, final Class65 class65, final boolean b3) {
        try {
            if (b2 != 27) {
                this.method1740(null);
            }
            int n2;
            if (1 != n) {
                if (2 != n) {
                    n2 = 2;
                }
                else {
                    n2 = 26;
                }
            }
            else {
                n2 = 3;
            }
            int n3 = 0;
            if (b) {
                n3 |= 0x20;
            }
            if (b3) {
                n3 |= 0x10;
            }
            this.anIDirect3DDevice6098.SetTextureStageState(this.anInt4579, n2, n3 | method2068(class65, (byte)127));
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method2004(final byte b) {
        try {
            if (b <= -98) {
                final float n = this.aBoolean4555 ? this.aFloat4630 : 0.0f;
                final float n2 = this.aBoolean4555 ? (-this.aFloat4594) : 0.0f;
                this.aD3DLIGHT6099.SetDiffuse(n * this.aFloat4611, this.aFloat4549 * n, n * this.aFloat4591, 0.0f);
                this.aD3DLIGHT6104.SetDiffuse(this.aFloat4611 * n2, this.aFloat4549 * n2, this.aFloat4591 * n2, 0.0f);
                this.aBoolean6096 = false;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void b(final int n, final int n2, final int n3, final int n4, final double n5) {
    }
    
    @Override
    final Interface4_Impl1 method2044(final int n, final Class164 class164, final byte[] array, final int n2, final int n3, final int n4) {
        Class26_Sub2 class26_Sub2;
        try {
            class26_Sub2 = new Class26_Sub2(this, class164, n2, n3, n4, array);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return class26_Sub2;
    }
    
    @Override
    final void method2033(final int n) {
        try {
            this.anIDirect3DDevice6098.a(27, this.aBoolean4560);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method2059(final boolean b, final boolean b2) {
        try {
            this.anIDirect3DDevice6098.a(161, b);
            if (b2) {
                this.method2025((byte)(-31));
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1941(final int n) {
        try {
            for (int n2 = 0; ~n2 > ~this.anInt4608; ++n2) {
                this.anIDirect3DDevice6098.SetSamplerState(n2, 7, 0);
                this.anIDirect3DDevice6098.SetSamplerState(n2, 6, 2);
                this.anIDirect3DDevice6098.SetSamplerState(n2, 5, 2);
                this.anIDirect3DDevice6098.SetSamplerState(n2, 1, 1);
                this.anIDirect3DDevice6098.SetSamplerState(n2, 2, 1);
                this.aClass200Array6102[n2] = Class284_Sub1_Sub1.aClass200_6187;
                this.aBooleanArray6088[n2] = (this.aBooleanArray6100[n2] = true);
                this.aBooleanArray6105[n2] = false;
                this.anIntArray6087[n2] = 0;
            }
            this.anIDirect3DDevice6098.SetTextureStageState(0, 6, 1);
            this.anIDirect3DDevice6098.SetRenderState(9, 2);
            this.anIDirect3DDevice6098.SetRenderState(23, 4);
            this.anIDirect3DDevice6098.SetRenderState(25, 5);
            this.anIDirect3DDevice6098.SetRenderState(24, 0);
            this.anIDirect3DDevice6098.SetRenderState(22, 2);
            this.anIDirect3DDevice6098.SetRenderState(147, n);
            this.anIDirect3DDevice6098.SetRenderState(145, 1);
            this.anIDirect3DDevice6098.a(38, 0.95f);
            this.anIDirect3DDevice6098.SetRenderState(140, 3);
            this.aD3DLIGHT6099.SetType(3);
            this.aD3DLIGHT6104.SetType(3);
            this.aD3DLIGHT6092.SetType(1);
            this.aBoolean6096 = false;
            super.method1941(1);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method2047(final int n, final boolean b, final byte b2, final Class65 class65) {
        try {
            int n2 = 0;
            if (b2 != -42) {
                ha_Sub3_Sub1.aFloatArray6101 = null;
            }
            int n3;
            if (n != 1) {
                if (0xFFFFFFFD != ~n) {
                    n3 = 5;
                }
                else {
                    n3 = 27;
                }
            }
            else {
                n3 = 6;
            }
            if (b) {
                n2 |= 0x10;
            }
            this.anIDirect3DDevice6098.SetTextureStageState(this.anInt4579, n3, n2 | method2068(class65, (byte)127));
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method2011(final int n) {
        try {
            final int n2 = this.aBooleanArray6094[this.anInt4579] ? method2072(false, this.aClass128Array4585[this.anInt4579]) : 1;
            if (n != 2) {
                this.aGeometryBuffer6086 = null;
            }
            this.anIDirect3DDevice6098.SetTextureStageState(this.anInt4579, 1, n2);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1791(final float n, final float n2, final float n3) {
    }
    
    @Override
    final void method2016(final byte b) {
        try {
            if (b <= -109) {
                if (this.aClass126_4625 != Class101.aClass126_848) {
                    if (this.aClass126_4625 != Class373_Sub1_Sub1.aClass126_6216) {
                        if (Class83.aClass126_632 == this.aClass126_4625) {
                            this.anIDirect3DDevice6098.SetRenderState(19, 9);
                            this.anIDirect3DDevice6098.SetRenderState(20, 2);
                        }
                    }
                    else {
                        this.anIDirect3DDevice6098.SetRenderState(19, 2);
                        this.anIDirect3DDevice6098.SetRenderState(20, 2);
                    }
                }
                else {
                    this.anIDirect3DDevice6098.SetRenderState(19, 5);
                    this.anIDirect3DDevice6098.SetRenderState(20, 6);
                }
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method1974(final int n) {
        try {
            this.anIDirect3DDevice6098.a(7, this.aBoolean4577);
            if (n != 0) {
                this.anInt6083 = -18;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void a(final Rectangle[] array, final int n, final int n2, final int n3) throws Exception_Sub1 {
        try {
            this.method1764(n2, n3);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static {
        ha_Sub3_Sub1.aFloatArray6101 = new float[16];
        ha_Sub3_Sub1.anIntArray6106 = new int[] { 22, 23 };
        ha_Sub3_Sub1.anIntArray6084 = new int[] { 77, 80 };
    }
}
