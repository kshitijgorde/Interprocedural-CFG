import java.util.Enumeration;
import java.awt.Dimension;
import jaclib.memory.Stream;
import java.awt.Rectangle;
import jaclib.memory.Buffer;
import jaclib.memory.heap.NativeHeap;
import java.util.Hashtable;
import java.awt.Canvas;
import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class ha_Sub1 extends ha
{
    private int anInt4297;
    private OpenGL anOpenGL4298;
    static Class85 aClass85_4299;
    private Canvas aCanvas4300;
    private int anInt4301;
    private long aLong4302;
    private Canvas aCanvas4303;
    int anInt4304;
    int anInt4305;
    private Hashtable aHashtable4306;
    private long aLong4307;
    private Class364 aClass364_4308;
    int anInt4309;
    int anInt4310;
    private Class98_Sub28_Sub1 aClass98_Sub28_Sub1_4311;
    private Class283 aClass283_4312;
    private Class360 aClass360_4313;
    private Class55 aClass55_4314;
    private Class111_Sub1 aClass111_Sub1_4315;
    Class111_Sub1 aClass111_Sub1_4316;
    private boolean aBoolean4317;
    int anInt4318;
    int anInt4319;
    private Class148 aClass148_4320;
    int anInt4321;
    Class118 aClass118_4322;
    NativeHeap aNativeHeap4323;
    private int anInt4324;
    private Interface12 anInterface12_4325;
    private Interface12[] anInterface12Array4326;
    private int anInt4327;
    private Interface12 anInterface12_4328;
    private Interface12[] anInterface12Array4329;
    private Class332_Sub1 aClass332_Sub1_4330;
    private int anInt4331;
    private Interface12[] anInterface12Array4332;
    private Class288 aClass288_4333;
    private Class148 aClass148_4334;
    private int anInt4335;
    int anInt4336;
    int anInt4337;
    private Class148 aClass148_4338;
    private Class148 aClass148_4339;
    private Class148 aClass148_4340;
    private Class148 aClass148_4341;
    private Class148 aClass148_4342;
    private Class148 aClass148_4343;
    private int anInt4344;
    private boolean aBoolean4345;
    private boolean aBoolean4346;
    private int anInt4347;
    Class111_Sub1 aClass111_Sub1_4348;
    private boolean aBoolean4349;
    private int anInt4350;
    private long aLong4351;
    private boolean aBoolean4352;
    Class111_Sub1 aClass111_Sub1_4353;
    Class111_Sub1 aClass111_Sub1_4354;
    Class146_Sub2 aClass146_Sub2_4355;
    float aFloat4356;
    private int anInt4357;
    Class42_Sub1 aClass42_Sub1_4358;
    float aFloat4359;
    private boolean aBoolean4360;
    Class146_Sub2 aClass146_Sub2_4361;
    int anInt4362;
    Class288 aClass288_4363;
    float aFloat4364;
    Class104 aClass104_4365;
    boolean aBoolean4366;
    boolean aBoolean4367;
    private int anInt4368;
    Class146_Sub2 aClass146_Sub2_4369;
    private int anInt4370;
    private float aFloat4371;
    private float[] aFloatArray4372;
    private boolean aBoolean4373;
    private Interface16 anInterface16_4374;
    boolean aBoolean4375;
    private float aFloat4376;
    int anInt4377;
    boolean aBoolean4378;
    float aFloat4379;
    private float aFloat4380;
    int anInt4381;
    Class146_Sub2 aClass146_Sub2_4382;
    boolean aBoolean4383;
    private int anInt4384;
    private int anInt4385;
    Class146_Sub2 aClass146_Sub2_4386;
    private int anInt4387;
    private boolean aBoolean4388;
    private int anInt4389;
    private int anInt4390;
    boolean aBoolean4391;
    private float aFloat4392;
    Class146_Sub2 aClass146_Sub2_4393;
    int anInt4394;
    private int anInt4395;
    private Class42[] aClass42Array4396;
    boolean aBoolean4397;
    int anInt4398;
    boolean aBoolean4399;
    private float aFloat4400;
    float aFloat4401;
    private int anInt4402;
    private String aString4403;
    int anInt4404;
    private boolean aBoolean4405;
    boolean aBoolean4406;
    float aFloat4407;
    private Class98_Sub5[] aClass98_Sub5Array4408;
    private int anInt4409;
    int anInt4410;
    private int anInt4411;
    private int anInt4412;
    float aFloat4413;
    private int anInt4414;
    private int anInt4415;
    private float aFloat4416;
    private int anInt4417;
    private float[] aFloatArray4418;
    int anInt4419;
    float aFloat4420;
    float aFloat4421;
    private boolean aBoolean4422;
    int anInt4423;
    boolean aBoolean4424;
    int anInt4425;
    boolean aBoolean4426;
    int anInt4427;
    Class146_Sub2 aClass146_Sub2_4428;
    private float aFloat4429;
    private String aString4430;
    boolean aBoolean4431;
    private int anInt4432;
    float aFloat4433;
    boolean aBoolean4434;
    float aFloat4435;
    Class104 aClass104_4436;
    float aFloat4437;
    float[] aFloatArray4438;
    private boolean aBoolean4439;
    private float[] aFloatArray4440;
    int anInt4441;
    private Interface8 anInterface8_4442;
    private Class48_Sub1 aClass48_Sub1_4443;
    private Class42_Sub1_Sub1 aClass42_Sub1_Sub1_4444;
    private int anInt4445;
    Class98_Sub22_Sub2 aClass98_Sub22_Sub2_4446;
    boolean aBoolean4447;
    private boolean aBoolean4448;
    private boolean aBoolean4449;
    private boolean aBoolean4450;
    int anInt4451;
    private float aFloat4452;
    int anInt4453;
    int anInt4454;
    int anInt4455;
    Class146_Sub2 aClass146_Sub2_4456;
    private boolean aBoolean4457;
    float aFloat4458;
    private boolean aBoolean4459;
    boolean aBoolean4460;
    Class146_Sub2 aClass146_Sub2_4461;
    Class146_Sub2 aClass146_Sub2_4462;
    private float[] aFloatArray4463;
    private Interface16 anInterface16_4464;
    private boolean aBoolean4465;
    private int anInt4466;
    private int anInt4467;
    int[] anIntArray4468;
    byte[] aByteArray4469;
    int[] anIntArray4470;
    int[] anIntArray4471;
    
    private final void method1826(final int n) {
        try {
            Label_0035: {
                if (this.aBoolean4457 && !this.aBoolean4405) {
                    OpenGL.glEnable(2896);
                    if (!client.aBoolean3553) {
                        break Label_0035;
                    }
                }
                OpenGL.glDisable(2896);
            }
            if (n != 2896) {
                this.anInt4402 = -77;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.RC(" + n + ')');
        }
    }
    
    final Class42_Sub2 method1827(final int n) {
        try {
            if (n > -121) {
                return null;
            }
            if (this.aClass48_Sub1_4443 == null) {
                return null;
            }
            return this.aClass48_Sub1_4443.method456((byte)121);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.OA(" + n + ')');
        }
    }
    
    @Override
    final void method1817() {
        try {
            if (this.aBoolean4439 && this.anInt4305 > 0 && this.anInt4304 > 0) {
                final int anInt4370 = this.anInt4370;
                final int anInt4371 = this.anInt4432;
                final int anInt4372 = this.anInt4402;
                final int anInt4373 = this.anInt4414;
                this.la();
                OpenGL.glReadBuffer(1028);
                OpenGL.glDrawBuffer(1029);
                this.method1867(29458);
                this.method1856(false, 6914);
                this.method1851(false, false);
                this.method1881(false, false);
                this.method1875((byte)(-124), false);
                this.method1863(1, null);
                this.method1834(115, -2);
                this.method1896(260, 1);
                this.method1870((byte)(-55), 0);
                OpenGL.glMatrixMode(5889);
                OpenGL.glLoadIdentity();
                OpenGL.glOrtho(0.0, 1.0, 0.0, 1.0, -1.0, 1.0);
                OpenGL.glMatrixMode(5888);
                OpenGL.glLoadIdentity();
                OpenGL.glRasterPos2i(0, 0);
                OpenGL.glCopyPixels(0, 0, this.anInt4305, this.anInt4304, 6144);
                OpenGL.glFlush();
                OpenGL.glReadBuffer(1029);
                OpenGL.glDrawBuffer(1029);
                this.KA(anInt4370, anInt4372, anInt4371, anInt4373);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.O()");
        }
    }
    
    private final void method1828(final byte b) {
        try {
            if (this.anInt4370 > this.anInt4432 || ~this.anInt4402 < ~this.anInt4414) {
                OpenGL.glScissor(0, 0, 0, 0);
                if (!client.aBoolean3553) {
                    return;
                }
            }
            OpenGL.glScissor(this.anInt4415 + this.anInt4370, -this.anInt4414 + this.anInt4409 - -this.anInt4304, this.anInt4432 + -this.anInt4370, this.anInt4414 - this.anInt4402);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.G(" + b + ')');
        }
    }
    
    final void method1829(final byte b) {
        try {
            if (b >= -78) {
                this.anInt4384 = -7;
            }
            if (~this.anInt4350 != 0xFFFFFFFD) {
                this.method1877((byte)(-60));
                this.method1856(false, 6914);
                this.method1851(false, false);
                this.method1881(false, false);
                this.method1875((byte)(-123), false);
                this.method1834(-81, -2);
                this.anInt4350 = 2;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.OB(" + b + ')');
        }
    }
    
    @Override
    final void method1761(final boolean b) {
    }
    
    @Override
    final int JA(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            int n7 = 0;
            float n8 = this.aClass111_Sub1_4348.aFloat4677 + (n2 * this.aClass111_Sub1_4348.aFloat4676 + this.aClass111_Sub1_4348.aFloat4684 * n + n3 * this.aClass111_Sub1_4348.aFloat4673);
            if (n8 < 1.0f) {
                n8 = 1.0f;
            }
            float n9 = this.aClass111_Sub1_4348.aFloat4677 + (n4 * this.aClass111_Sub1_4348.aFloat4684 + this.aClass111_Sub1_4348.aFloat4676 * n5 + n6 * this.aClass111_Sub1_4348.aFloat4673);
            if (n9 < 1.0f) {
                n9 = 1.0f;
            }
            if (this.anInt4404 <= n8 || n9 >= this.anInt4404) {
                if (n8 > this.anInt4389 && this.anInt4389 < n9) {
                    n7 |= 0x20;
                }
            }
            else {
                n7 |= 0x10;
            }
            final int n10 = (int)(this.anInt4419 * (this.aClass111_Sub1_4348.aFloat4674 + (this.aClass111_Sub1_4348.aFloat4679 * n2 + this.aClass111_Sub1_4348.aFloat4686 * n + this.aClass111_Sub1_4348.aFloat4680 * n3)) / n8);
            final int n11 = (int)(this.anInt4419 * (n6 * this.aClass111_Sub1_4348.aFloat4680 + (n4 * this.aClass111_Sub1_4348.aFloat4686 + n5 * this.aClass111_Sub1_4348.aFloat4679) + this.aClass111_Sub1_4348.aFloat4674) / n9);
            if (n10 < this.aFloat4421 && this.aFloat4421 > n11) {
                n7 |= 0x1;
            }
            else if (n10 > this.aFloat4364 && this.aFloat4364 < n11) {
                n7 |= 0x2;
            }
            final int n12 = (int)(this.anInt4381 * (this.aClass111_Sub1_4348.aFloat4683 + (n3 * this.aClass111_Sub1_4348.aFloat4687 + (this.aClass111_Sub1_4348.aFloat4678 * n + n2 * this.aClass111_Sub1_4348.aFloat4675))) / n8);
            final int n13 = (int)((this.aClass111_Sub1_4348.aFloat4683 + (this.aClass111_Sub1_4348.aFloat4678 * n4 + this.aClass111_Sub1_4348.aFloat4675 * n5 + this.aClass111_Sub1_4348.aFloat4687 * n6)) * this.anInt4381 / n9);
            if (n12 < this.aFloat4359 && n13 < this.aFloat4359) {
                n7 |= 0x4;
            }
            else if (n12 > this.aFloat4437 && this.aFloat4437 < n13) {
                n7 |= 0x8;
            }
            return n7;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.JA(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    @Override
    final void A(final int n, final aa aa, final int n2, final int n3) {
        try {
            final aa_Sub3 aa_Sub3 = (aa_Sub3)aa;
            final Class42_Sub1_Sub1 aClass42_Sub1_Sub1_3568 = aa_Sub3.aClass42_Sub1_Sub1_3568;
            this.method1829((byte)(-90));
            this.method1863(1, aa_Sub3.aClass42_Sub1_Sub1_3568);
            this.method1870((byte)(-50), 1);
            this.method1899(8448, 8960, 7681);
            this.method1840(0, 768, 108, 34167);
            final float n4 = aClass42_Sub1_Sub1_3568.aFloat6205 / aClass42_Sub1_Sub1_3568.anInt6207;
            final float n5 = aClass42_Sub1_Sub1_3568.aFloat6209 / aClass42_Sub1_Sub1_3568.anInt6204;
            OpenGL.glColor4ub((byte)(n >> 1933103952), (byte)(n >> -419860344), (byte)n, (byte)(n >> -1429152424));
            OpenGL.glBegin(7);
            OpenGL.glTexCoord2f((-n2 + this.anInt4370) * n4, (this.anInt4402 - n3) * n5);
            OpenGL.glVertex2i(this.anInt4370, this.anInt4402);
            OpenGL.glTexCoord2f(n4 * (this.anInt4370 + -n2), (-n3 + this.anInt4414) * n5);
            OpenGL.glVertex2i(this.anInt4370, this.anInt4414);
            OpenGL.glTexCoord2f(n4 * (-n2 + this.anInt4432), (-n3 + this.anInt4414) * n5);
            OpenGL.glVertex2i(this.anInt4432, this.anInt4414);
            OpenGL.glTexCoord2f((-n2 + this.anInt4432) * n4, (this.anInt4402 - n3) * n5);
            OpenGL.glVertex2i(this.anInt4432, this.anInt4402);
            OpenGL.glEnd();
            this.method1840(0, 768, -65, 5890);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.A(" + n + ',' + ((aa != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final Class48 method1803(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            if (!this.aBoolean4391) {
                return null;
            }
            return new Class48_Sub1_Sub1(this, n, n2, n3, n4, n5, n6);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.II(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    final void method1830(final Interface8 anInterface8_4442, final int n) {
        try {
            if (n == 2936) {
                if (this.anInterface8_4442 != anInterface8_4442) {
                    if (this.aBoolean4449) {
                        OpenGL.glBindBufferARB(34963, anInterface8_4442.method19(-22132));
                    }
                    this.anInterface8_4442 = anInterface8_4442;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.TA(" + ((anInterface8_4442 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final boolean method1810() {
        try {
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.PH()");
        }
    }
    
    @Override
    final void method1820(final Class242 class242) {
        try {
            this.aClass360_4313.method3906(class242, -114, -1, this);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.DE(" + ((class242 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method1771() {
        try {
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.VG()");
        }
    }
    
    @Override
    final int c(final int n, final int n2) {
        try {
            return (n & n2) ^ n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.JH(" + n + ',' + n2 + ')');
        }
    }
    
    final void method1831(final int n) {
        try {
            if (n <= 125) {
                this.aBoolean4366 = false;
            }
            OpenGL.glLightfv(16384, 4611, this.aFloatArray4438, 0);
            OpenGL.glLightfv(16385, 4611, this.aFloatArray4463, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.MA(" + n + ')');
        }
    }
    
    final void method1832(final Interface12 interface12, final int n) {
        try {
            if (this.anInt4324 < 0 || interface12 != this.anInterface12Array4329[this.anInt4324]) {
                throw new RuntimeException();
            }
            this.anInterface12Array4329[this.anInt4324--] = null;
            if (n < 16) {
                this.anInt4390 = -56;
            }
            interface12.method38(-27095);
            if (~this.anInt4324 <= -1) {
                (this.anInterface12_4325 = this.anInterface12Array4329[this.anInt4324]).method37((byte)77);
            }
            else {
                this.anInterface12_4325 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.CE(" + ((interface12 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    private final void method1833(final int n) {
        try {
            OpenGL.glLoadIdentity();
            OpenGL.glMultMatrixf(this.aClass111_Sub1_4353.method2113(n - 114), 0);
            if (this.aBoolean4448) {
                this.aClass55_4314.aClass151_Sub9_432.method2471((byte)34);
            }
            this.method1831(127);
            this.method1842(103);
            if (n != 4) {
                this.da(-81, 96, 24, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.ID(" + n + ')');
        }
    }
    
    @Override
    final void H(final int n, final int n2, final int n3, final int[] array) {
        try {
            final float n4 = this.aClass111_Sub1_4348.aFloat4677 + (this.aClass111_Sub1_4348.aFloat4673 * n3 + (n2 * this.aClass111_Sub1_4348.aFloat4676 + this.aClass111_Sub1_4348.aFloat4684 * n));
            if (n4 == 0.0f) {
                final int n5 = 0;
                final int n6 = 1;
                final int n7 = 2;
                final int n8 = -1;
                array[n7] = n8;
                array[n5] = (array[n6] = n8);
            }
            else {
                array[0] = (int)(-this.aFloat4421 + (int)((n * this.aClass111_Sub1_4348.aFloat4686 + n2 * this.aClass111_Sub1_4348.aFloat4679 + n3 * this.aClass111_Sub1_4348.aFloat4680 + this.aClass111_Sub1_4348.aFloat4674) * this.anInt4419 / n4));
                array[1] = (int)(-this.aFloat4359 + (int)(this.anInt4381 * (this.aClass111_Sub1_4348.aFloat4683 + (n3 * this.aClass111_Sub1_4348.aFloat4687 + (n * this.aClass111_Sub1_4348.aFloat4678 + n2 * this.aClass111_Sub1_4348.aFloat4675))) / n4));
                array[2] = (int)n4;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.H(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method1798(final int n) {
    }
    
    final void method1834(final int n, final int n2) {
        try {
            this.method1908(true, -109, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.HE(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void f(final int anInt4404, final int anInt4405) {
        try {
            if (anInt4404 != this.anInt4404 || this.anInt4389 != anInt4405) {
                this.anInt4389 = anInt4405;
                this.anInt4404 = anInt4404;
                this.method1847(true);
                this.method1852((byte)(-66));
                if (~this.anInt4385 != 0xFFFFFFFC) {
                    if (this.anInt4385 == 2) {
                        this.method1885((byte)(-125));
                    }
                }
                else {
                    this.method1884((byte)56);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.f(" + anInt4404 + ',' + anInt4405 + ')');
        }
    }
    
    @Override
    final void la() {
        try {
            this.anInt4432 = this.anInt4305;
            this.anInt4370 = 0;
            this.anInt4402 = 0;
            this.anInt4414 = this.anInt4304;
            OpenGL.glDisable(3089);
            this.method1906(4353);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.la()");
        }
    }
    
    final void method1835(final Interface12 anInterface12_4328, final int n) {
        try {
            if (~this.anInt4327 <= -4) {
                throw new RuntimeException();
            }
            if (this.anInt4327 >= n) {
                this.anInterface12Array4326[this.anInt4327].method40((byte)(-30));
            }
            this.anInterface12Array4326[++this.anInt4327] = anInterface12_4328;
            (this.anInterface12_4328 = anInterface12_4328).method36((byte)(-120));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.KD(" + ((anInterface12_4328 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final void L(final int anInt4455, final int anInt4456, final int anInt4457) {
        try {
            if (~this.anInt4455 != ~anInt4455 || ~anInt4456 != ~this.anInt4441 || this.anInt4427 != anInt4457) {
                this.anInt4455 = anInt4455;
                this.anInt4427 = anInt4457;
                this.anInt4441 = anInt4456;
                this.method1852((byte)(-98));
                this.method1893(109);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.L(" + anInt4455 + ',' + anInt4456 + ',' + anInt4457 + ')');
        }
    }
    
    @Override
    final Class111 method1752() {
        try {
            return this.aClass111_Sub1_4348;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.NE()");
        }
    }
    
    @Override
    final void method1778(final int anInt4309) {
        try {
            if (anInt4309 < 128 || ~anInt4309 < -1025) {
                throw new IllegalArgumentException();
            }
            this.anInt4309 = anInt4309;
            this.aClass364_4308.method3934((byte)100);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.CH(" + anInt4309 + ')');
        }
    }
    
    @Override
    final void F(final int n, final int n2) {
    }
    
    private final void method1836(final int n) {
        try {
            if (n != -513) {
                this.r(-65, -25, -101, 48, -10, -24, -25);
            }
            this.anOpenGL4298.a();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.LA(" + n + ')');
        }
    }
    
    @Override
    final void method1776() {
        try {
            Label_0111: {
                if (!this.aBoolean4460) {
                    if (!this.aBoolean4388) {
                        throw new RuntimeException("");
                    }
                    this.aClass332_Sub1_4330.method3736(0, 0, this.anInt4305, this.anInt4304, 0, 0);
                    this.anOpenGL4298.setSurface(this.aLong4302);
                    if (!client.aBoolean3553) {
                        break Label_0111;
                    }
                }
                if (this.anInterface12_4328 != this.aClass288_4333) {
                    throw new RuntimeException();
                }
                this.aClass288_4333.method3401(0, true);
                this.aClass288_4333.method3401(8, true);
                this.method1907(this.aClass288_4333, -1);
            }
            this.aClass332_Sub1_4330 = null;
            this.anInt4304 = this.anInt4297;
            this.anInt4305 = this.anInt4301;
            this.method1867(29458);
            this.method1844((byte)60);
            this.la();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.QD()");
        }
    }
    
    final synchronized void method1837(final byte b, final int n) {
        try {
            if (b <= -27) {
                this.aClass148_4340.method2419(new Class98_Sub34(n), -20911);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.DC(" + b + ',' + n + ')');
        }
    }
    
    @Override
    final Class332 method1739(final int n, final int n2, final boolean b) {
        try {
            return new Class332_Sub1(this, n, n2, b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.EG(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    @Override
    final int[] na(final int n, final int n2, final int n3, final int n4) {
        try {
            final int[] array = new int[n4 * n3];
            for (int i = 0; i < n4; ++i) {
                OpenGL.glReadPixelsi(n, -i + -n2 + this.anInt4304, n3, 1, 32993, this.anInt4425, array, i * n3);
            }
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.na(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final void K(final int[] array) {
        try {
            array[0] = this.anInt4370;
            array[3] = this.anInt4414;
            array[2] = this.anInt4432;
            array[1] = this.anInt4402;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.K(" + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final Interface13 method1744(final int n, final int n2) {
        try {
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.SB(" + n + ',' + n2 + ')');
        }
    }
    
    final Interface8 method1838(final int n, final byte[] array, final int n2, final boolean b, final int n3) {
        try {
            if (n2 != 7) {
                this.method1812();
            }
            if (this.aBoolean4449 && (!b || this.aBoolean4459)) {
                return new Class287_Sub2(this, n, array, n3, b);
            }
            return new Class156_Sub1(this, n, array, n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.PA(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + b + ',' + n3 + ')');
        }
    }
    
    final int method1839(final int n, final int n2) {
        try {
            if (n2 == ~n || n == 5120) {
                return 1;
            }
            if (~n == 0xFFFFEBFC || ~n == 0xFFFFEBFD) {
                return 2;
            }
            if (~n == 0xFFFFEBFA || n == 5124 || ~n == 0xFFFFEBF9) {
                return 4;
            }
            throw new IllegalArgumentException("");
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.RA(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method1783(final int n) {
        try {
            if (~n != 0xFFFFFFFE) {
                throw new IllegalArgumentException("");
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.OF(" + n + ')');
        }
    }
    
    final void method1840(final int n, final int n2, final int n3, final int n4) {
        try {
            OpenGL.glTexEnvi(8960, 34176 + n, n4);
            OpenGL.glTexEnvi(8960, n + 34192, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.N(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final void a(final za za) {
        try {
            this.aNativeHeap4323 = ((za_Sub2)za).aNativeHeap6082;
            if (this.anInterface16_4464 == null) {
                final Class98_Sub22_Sub2 class98_Sub22_Sub2 = new Class98_Sub22_Sub2(80);
                Label_0334: {
                    if (!this.aBoolean4397) {
                        class98_Sub22_Sub2.method1265((byte)(-52), -1.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), -1.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), 0.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), 0.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), 1.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), 1.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), -1.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), 0.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), 1.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), 1.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), 1.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), 1.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), 0.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), 1.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), 0.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), -1.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), 1.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), 0.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), 0.0f);
                        class98_Sub22_Sub2.method1265((byte)(-52), 0.0f);
                        if (!client.aBoolean3553) {
                            break Label_0334;
                        }
                    }
                    class98_Sub22_Sub2.method1264((byte)72, -1.0f);
                    class98_Sub22_Sub2.method1264((byte)(-116), -1.0f);
                    class98_Sub22_Sub2.method1264((byte)74, 0.0f);
                    class98_Sub22_Sub2.method1264((byte)81, 0.0f);
                    class98_Sub22_Sub2.method1264((byte)82, 1.0f);
                    class98_Sub22_Sub2.method1264((byte)49, 1.0f);
                    class98_Sub22_Sub2.method1264((byte)103, -1.0f);
                    class98_Sub22_Sub2.method1264((byte)(-113), 0.0f);
                    class98_Sub22_Sub2.method1264((byte)21, 1.0f);
                    class98_Sub22_Sub2.method1264((byte)(-98), 1.0f);
                    class98_Sub22_Sub2.method1264((byte)(-116), 1.0f);
                    class98_Sub22_Sub2.method1264((byte)4, 1.0f);
                    class98_Sub22_Sub2.method1264((byte)83, 0.0f);
                    class98_Sub22_Sub2.method1264((byte)(-107), 1.0f);
                    class98_Sub22_Sub2.method1264((byte)22, 0.0f);
                    class98_Sub22_Sub2.method1264((byte)(-106), -1.0f);
                    class98_Sub22_Sub2.method1264((byte)(-96), 1.0f);
                    class98_Sub22_Sub2.method1264((byte)11, 0.0f);
                    class98_Sub22_Sub2.method1264((byte)(-105), 0.0f);
                    class98_Sub22_Sub2.method1264((byte)(-120), 0.0f);
                }
                this.anInterface16_4464 = this.method1878(class98_Sub22_Sub2.anInt3991, false, 20, -54, class98_Sub22_Sub2.aByteArray3992);
                this.aClass104_4365 = new Class104(this.anInterface16_4464, 5126, 3, 0);
                this.aClass104_4436 = new Class104(this.anInterface16_4464, 5126, 2, 12);
                this.aClass360_4313.method3912(this, 196584);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.EH(" + ((za != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void aa(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            final float n7 = n + 0.35f;
            final float n8 = n2 + 0.35f;
            final float n9 = n7 + n3;
            this.method1859(-24);
            final float n10 = n4 + n8;
            this.method1870((byte)(-30), n6);
            OpenGL.glColor4ub((byte)(n5 >> 993788880), (byte)(n5 >> 848547304), (byte)n5, (byte)(n5 >> -1134524424));
            if (this.aBoolean4360) {
                OpenGL.glDisable(32925);
            }
            OpenGL.glBegin(7);
            OpenGL.glVertex2f(n7, n8);
            OpenGL.glVertex2f(n7, n10);
            OpenGL.glVertex2f(n9, n10);
            OpenGL.glVertex2f(n9, n8);
            OpenGL.glEnd();
            if (this.aBoolean4360) {
                OpenGL.glEnable(32925);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.aa(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    final void method1841(final int n) {
        try {
            OpenGL.glPushMatrix();
            if (n != 34167) {
                this.method1836(77);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.EB(" + n + ')');
        }
    }
    
    private final void method1842(final int n) {
        try {
            int i;
            for (i = 0; this.anInt4384 > i; ++i) {
                final Class98_Sub5 class98_Sub5 = this.aClass98_Sub5Array4408[i];
                final int n2 = 16386 - -i;
                Class98_Sub15.aFloatArray3916[0] = class98_Sub5.method954(7019);
                Class98_Sub15.aFloatArray3916[1] = class98_Sub5.method963((byte)99);
                Class98_Sub15.aFloatArray3916[2] = class98_Sub5.method962(28699);
                Class98_Sub15.aFloatArray3916[3] = 1.0f;
                OpenGL.glLightfv(n2, 4611, Class98_Sub15.aFloatArray3916, 0);
                final int method961 = class98_Sub5.method961((byte)(-78));
                final float n3 = class98_Sub5.method956(false) / 255.0f;
                Class98_Sub15.aFloatArray3916[1] = n3 * (Class202.method2702(method961, 65311) >> 1265447176);
                Class98_Sub15.aFloatArray3916[2] = Class202.method2702(method961, 255) * n3;
                Class98_Sub15.aFloatArray3916[0] = n3 * Class202.method2702(255, method961 >> 411401808);
                OpenGL.glLightfv(n2, 4609, Class98_Sub15.aFloatArray3916, 0);
                OpenGL.glLightf(n2, 4617, 1.0f / (class98_Sub5.method958(-76) * class98_Sub5.method958(-105)));
                OpenGL.glEnable(n2);
            }
            if (n <= 13) {
                this.anInt4404 = -99;
            }
            while (i < this.anInt4411) {
                OpenGL.glDisable(16386 + i);
                ++i;
            }
            this.anInt4411 = this.anInt4384;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.WB(" + n + ')');
        }
    }
    
    final Interface16 method1843(final int n, final byte b, final Buffer buffer, final boolean b2, final int n2) {
        try {
            if (this.aBoolean4449 && (!b2 || this.aBoolean4459)) {
                return new Class287_Sub1(this, n, buffer, n2, b2);
            }
            if (b < 49) {
                return null;
            }
            return new Class156_Sub2(this, n, buffer);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.FE(" + n + ',' + b + ',' + ((buffer != null) ? "{...}" : "null") + ',' + b2 + ',' + n2 + ')');
        }
    }
    
    private final void method1844(final byte b) {
        try {
            OpenGL.glViewport(this.anInt4415, this.anInt4409, this.anInt4305, this.anInt4304);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.WD(" + b + ')');
        }
    }
    
    @Override
    final boolean method1824() {
        try {
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.UF()");
        }
    }
    
    @Override
    final void a(final Rectangle[] array, final int n, final int n2, final int n3) throws Exception_Sub1 {
        try {
            this.method1764(n2, n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.DD(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method1816(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            OpenGL.glLineWidth(n6);
            this.method1795(n, n2, n3, n4, n5, n7);
            OpenGL.glLineWidth(1.0f);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.SH(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    @Override
    final void DA(final int anInt4451, final int anInt4452, final int anInt4453, final int anInt4454) {
        try {
            this.anInt4394 = anInt4452;
            this.anInt4419 = anInt4453;
            this.anInt4451 = anInt4451;
            this.anInt4381 = anInt4454;
            this.method1847(true);
            this.method1906(4353);
            if (this.anInt4385 == 3) {
                this.method1884((byte)121);
            }
            else if (~this.anInt4385 == 0xFFFFFFFD) {
                this.method1885((byte)(-127));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.DA(" + anInt4451 + ',' + anInt4452 + ',' + anInt4453 + ',' + anInt4454 + ')');
        }
    }
    
    final void method1845(final int anInt4417, final int n) {
        try {
            if (n != 847872872) {
                this.method1845(108, -11);
            }
            if (this.anInt4417 != anInt4417) {
                OpenGL.glActiveTexture(anInt4417 + 33984);
                this.anInt4417 = anInt4417;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.QA(" + anInt4417 + ',' + n + ')');
        }
    }
    
    @Override
    final int M() {
        try {
            final int anInt4466 = this.anInt4466;
            this.anInt4466 = 0;
            return anInt4466;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.M()");
        }
    }
    
    final synchronized void method1846(final int n, final int n2, final int n3) {
        try {
            final Class98_Sub34 class98_Sub34 = new Class98_Sub34(n3);
            class98_Sub34.aLong832 = n;
            if (n2 <= 36) {
                this.anInt4324 = -29;
            }
            this.aClass148_4341.method2419(class98_Sub34, -20911);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.KC(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final Interface17 method1815(final Interface5 interface5, final Interface13 interface6) {
        try {
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.EC(" + ((interface5 != null) ? "{...}" : "null") + ',' + ((interface6 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method1786(final Canvas canvas) {
        try {
            if (this.aCanvas4303 == canvas) {
                throw new RuntimeException();
            }
            if (this.aHashtable4306.containsKey(canvas)) {
                this.anOpenGL4298.releaseSurface(canvas, this.aHashtable4306.get(canvas));
                this.aHashtable4306.remove(canvas);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.KG(" + ((canvas != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method1847(final boolean b) {
        try {
            final float[] aFloatArray4440 = this.aFloatArray4440;
            if (!b) {
                this.method1880(42, null);
            }
            final float n = -this.anInt4451 * this.anInt4404 / this.anInt4419;
            final float n2 = this.anInt4404 * (-this.anInt4451 + this.anInt4305) / this.anInt4419;
            final float n3 = this.anInt4394 * this.anInt4404 / this.anInt4381;
            final float n4 = (-this.anInt4304 + this.anInt4394) * this.anInt4404 / this.anInt4381;
            Label_0365: {
                if (n == n2 || n4 == n3) {
                    aFloatArray4440[6] = 0.0f;
                    aFloatArray4440[14] = (aFloatArray4440[4] = 0.0f);
                    aFloatArray4440[0] = (aFloatArray4440[15] = 1.0f);
                    aFloatArray4440[9] = 0.0f;
                    aFloatArray4440[8] = (aFloatArray4440[2] = 0.0f);
                    aFloatArray4440[10] = 1.0f;
                    aFloatArray4440[7] = (aFloatArray4440[12] = 0.0f);
                    aFloatArray4440[3] = (aFloatArray4440[11] = 0.0f);
                    aFloatArray4440[5] = 1.0f;
                    aFloatArray4440[1] = (aFloatArray4440[13] = 0.0f);
                    if (!client.aBoolean3553) {
                        break Label_0365;
                    }
                }
                final float n5 = 2.0f * this.anInt4404;
                aFloatArray4440[5] = n5 / (n3 - n4);
                aFloatArray4440[14] = (this.aFloat4416 = -(n5 * this.anInt4389) / (this.anInt4389 - this.anInt4404));
                aFloatArray4440[11] = -1.0f;
                aFloatArray4440[1] = 0.0f;
                aFloatArray4440[9] = (n3 + n4) / (-n4 + n3);
                aFloatArray4440[0] = n5 / (n2 - n);
                aFloatArray4440[8] = (n + n2) / (n2 - n);
                aFloatArray4440[3] = 0.0f;
                aFloatArray4440[12] = (aFloatArray4440[4] = 0.0f);
                aFloatArray4440[15] = (aFloatArray4440[7] = 0.0f);
                aFloatArray4440[10] = (this.aFloat4371 = -(this.anInt4404 + this.anInt4389) / (this.anInt4389 - this.anInt4404));
                aFloatArray4440[2] = 0.0f;
                aFloatArray4440[13] = (aFloatArray4440[6] = 0.0f);
            }
            this.method1894((byte)(-104));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.IC(" + b + ')');
        }
    }
    
    @Override
    final int r(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            final float n8 = this.aClass111_Sub1_4348.aFloat4673 * n3 + (this.aClass111_Sub1_4348.aFloat4676 * n2 + this.aClass111_Sub1_4348.aFloat4684 * n) + this.aClass111_Sub1_4348.aFloat4677;
            final float n9 = this.aClass111_Sub1_4348.aFloat4673 * n6 + (this.aClass111_Sub1_4348.aFloat4676 * n5 + this.aClass111_Sub1_4348.aFloat4684 * n4) + this.aClass111_Sub1_4348.aFloat4677;
            int n10 = 0;
            if (n8 >= this.anInt4404 || this.anInt4404 <= n9) {
                if (n8 > this.anInt4389 && n9 > this.anInt4389) {
                    n10 |= 0x20;
                }
            }
            else {
                n10 |= 0x10;
            }
            final int n11 = (int)(this.anInt4419 * (this.aClass111_Sub1_4348.aFloat4680 * n3 + (this.aClass111_Sub1_4348.aFloat4679 * n2 + this.aClass111_Sub1_4348.aFloat4686 * n) + this.aClass111_Sub1_4348.aFloat4674) / n7);
            final int n12 = (int)((n5 * this.aClass111_Sub1_4348.aFloat4679 + n4 * this.aClass111_Sub1_4348.aFloat4686 + this.aClass111_Sub1_4348.aFloat4680 * n6 + this.aClass111_Sub1_4348.aFloat4674) * this.anInt4419 / n7);
            if (n11 < this.aFloat4421 && this.aFloat4421 > n12) {
                n10 |= 0x1;
            }
            else if (this.aFloat4364 < n11 && n12 > this.aFloat4364) {
                n10 |= 0x2;
            }
            final int n13 = (int)((this.aClass111_Sub1_4348.aFloat4675 * n2 + n * this.aClass111_Sub1_4348.aFloat4678 + n3 * this.aClass111_Sub1_4348.aFloat4687 + this.aClass111_Sub1_4348.aFloat4683) * this.anInt4381 / n7);
            final int n14 = (int)(this.anInt4381 * (this.aClass111_Sub1_4348.aFloat4683 + (n6 * this.aClass111_Sub1_4348.aFloat4687 + (n5 * this.aClass111_Sub1_4348.aFloat4675 + this.aClass111_Sub1_4348.aFloat4678 * n4))) / n7);
            if (this.aFloat4359 > n13 && n14 < this.aFloat4359) {
                n10 |= 0x4;
            }
            else if (n13 > this.aFloat4437 && this.aFloat4437 < n14) {
                n10 |= 0x8;
            }
            return n10;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.r(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    final void method1848(final float aFloat4392, final boolean b, final float aFloat4393) {
        try {
            this.aFloat4392 = aFloat4392;
            this.aFloat4376 = aFloat4393;
            if (!b) {
                this.anInt4336 = 110;
            }
            this.method1852((byte)(-91));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.IB(" + aFloat4392 + ',' + b + ',' + aFloat4393 + ')');
        }
    }
    
    @Override
    final void da(final int n, final int n2, final int n3, final int[] array) {
        try {
            final float n4 = this.aClass111_Sub1_4348.aFloat4677 + (n2 * this.aClass111_Sub1_4348.aFloat4676 + this.aClass111_Sub1_4348.aFloat4684 * n + n3 * this.aClass111_Sub1_4348.aFloat4673);
            if (this.anInt4404 > n4 || n4 > this.anInt4389) {
                final int n5 = 0;
                final int n6 = 1;
                final int n7 = 2;
                final int n8 = -1;
                array[n7] = n8;
                array[n5] = (array[n6] = n8);
            }
            else {
                final int n9 = (int)((this.aClass111_Sub1_4348.aFloat4680 * n3 + (this.aClass111_Sub1_4348.aFloat4679 * n2 + this.aClass111_Sub1_4348.aFloat4686 * n) + this.aClass111_Sub1_4348.aFloat4674) * this.anInt4419 / n4);
                final int n10 = (int)(this.anInt4381 * (this.aClass111_Sub1_4348.aFloat4675 * n2 + n * this.aClass111_Sub1_4348.aFloat4678 + n3 * this.aClass111_Sub1_4348.aFloat4687 + this.aClass111_Sub1_4348.aFloat4683) / n4);
                if (n9 < this.aFloat4421 || this.aFloat4364 < n9 || this.aFloat4359 > n10 || this.aFloat4437 < n10) {
                    final int n11 = 0;
                    final int n12 = 1;
                    final int n13 = 2;
                    final int n14 = -1;
                    array[n13] = n14;
                    array[n11] = (array[n12] = n14);
                }
                else {
                    array[0] = (int)(n9 - this.aFloat4421);
                    array[1] = (int)(-this.aFloat4359 + n10);
                    array[2] = (int)n4;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.da(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final Class146 method1790(final Class178 class178, final int n, final int n2, final int n3, final int n4) {
        try {
            return new Class146_Sub2(this, class178, n, n3, n4, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.VD(" + ((class178 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final void method1801(final int[] array) {
        try {
            array[0] = this.anInt4305;
            array[1] = this.anInt4304;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.UD(" + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final int method1849(final byte b) {
        try {
            int n = 0;
            this.aString4430 = OpenGL.glGetString(7936).toLowerCase();
            this.aString4403 = OpenGL.glGetString(7937).toLowerCase();
            if (this.aString4430.indexOf("microsoft") != -1) {
                n |= 0x1;
            }
            if (~this.aString4430.indexOf("brian paul") != 0x0 || this.aString4430.indexOf("mesa") != -1) {
                n |= 0x1;
            }
            final String[] method2142 = Class112.method2142(OpenGL.glGetString(7938).replace('.', ' '), ' ', false);
            Label_0168: {
                if (~method2142.length > -3) {
                    n |= 0x4;
                    if (!client.aBoolean3553) {
                        break Label_0168;
                    }
                }
                try {
                    this.anInt4390 = 10 * PacketSender.method3607(127, method2142[0]) + PacketSender.method3607(-120, method2142[1]);
                }
                catch (NumberFormatException ex2) {
                    n |= 0x4;
                }
            }
            if (this.anInt4390 < 12) {
                n |= 0x2;
            }
            if (!this.anOpenGL4298.a("GL_ARB_multitexture")) {
                n |= 0x8;
            }
            if (!this.anOpenGL4298.a("GL_ARB_texture_env_combine")) {
                n |= 0x20;
            }
            final int[] array = { 0 };
            OpenGL.glGetIntegerv(34018, array, 0);
            this.anInt4410 = array[0];
            OpenGL.glGetIntegerv(34929, array, 0);
            this.anInt4368 = array[0];
            OpenGL.glGetIntegerv(34930, array, 0);
            this.anInt4445 = array[0];
            if (this.anInt4410 < 2 || this.anInt4368 < 2 || ~this.anInt4445 > -3) {
                n |= 0x10;
            }
            this.aBoolean4397 = Stream.a();
            this.aBoolean4388 = this.anOpenGL4298.arePbuffersAvailable();
            this.aBoolean4449 = this.anOpenGL4298.a("GL_ARB_vertex_buffer_object");
            this.aBoolean4360 = this.anOpenGL4298.a("GL_ARB_multisample");
            this.aBoolean4431 = this.anOpenGL4298.a("GL_ARB_vertex_program");
            this.anOpenGL4298.a("GL_ARB_fragment_program");
            this.aBoolean4399 = this.anOpenGL4298.a("GL_ARB_vertex_shader");
            this.aBoolean4447 = this.anOpenGL4298.a("GL_ARB_fragment_shader");
            this.aBoolean4434 = this.anOpenGL4298.a("GL_EXT_texture3D");
            this.aBoolean4378 = this.anOpenGL4298.a("GL_ARB_texture_rectangle");
            this.aBoolean4391 = this.anOpenGL4298.a("GL_ARB_texture_cube_map");
            this.aBoolean4383 = this.anOpenGL4298.a("GL_ARB_texture_float");
            this.aBoolean4426 = false;
            this.aBoolean4460 = this.anOpenGL4298.a("GL_EXT_framebuffer_object");
            this.aBoolean4375 = this.anOpenGL4298.a("GL_EXT_framebuffer_blit");
            this.aBoolean4424 = this.anOpenGL4298.a("GL_EXT_framebuffer_multisample");
            this.aBoolean4450 = (this.aBoolean4375 & this.aBoolean4424);
            this.aBoolean4367 = Class19.aString3448.startsWith("mac");
            OpenGL.glGetFloatv(2834, Class115.aFloatArray961, 0);
            this.aFloat4400 = Class115.aFloatArray961[1];
            this.aFloat4429 = Class115.aFloatArray961[0];
            if (n != 0) {
                return n;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.AD(" + b + ')');
        }
    }
    
    private final void method1850(final byte b) {
        try {
            Label_0050: {
                if (this.aCanvas4300 != null) {
                    final Dimension size = this.aCanvas4300.getSize();
                    this.anInt4297 = size.height;
                    this.anInt4301 = size.width;
                    if (!client.aBoolean3553) {
                        break Label_0050;
                    }
                }
                final boolean b2 = false;
                this.anInt4297 = (b2 ? 1 : 0);
                this.anInt4301 = (b2 ? 1 : 0);
            }
            if (this.anInterface12_4328 == null) {
                this.anInt4305 = this.anInt4301;
                this.anInt4304 = this.anInt4297;
                this.method1844((byte)113);
            }
            this.method1867(b + 29489);
            if (b == -31) {
                this.la();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.PB(" + b + ')');
        }
    }
    
    @Override
    final void T(final int anInt4370, final int anInt4371, final int anInt4372, final int anInt4373) {
        try {
            if (~this.anInt4414 < ~anInt4373) {
                this.anInt4414 = anInt4373;
            }
            if (~anInt4370 < ~this.anInt4370) {
                this.anInt4370 = anInt4370;
            }
            if (this.anInt4432 > anInt4372) {
                this.anInt4432 = anInt4372;
            }
            if (~anInt4371 < ~this.anInt4402) {
                this.anInt4402 = anInt4371;
            }
            OpenGL.glEnable(3089);
            this.method1906(4353);
            this.method1828((byte)(-118));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.T(" + anInt4370 + ',' + anInt4371 + ',' + anInt4372 + ',' + anInt4373 + ')');
        }
    }
    
    @Override
    final void U(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            this.method1859(-106);
            this.method1870((byte)(-82), n5);
            final float n6 = 0.35f + n;
            OpenGL.glColor4ub((byte)(n4 >> -1500168624), (byte)(n4 >> -2018405784), (byte)n4, (byte)(n4 >> -1514779816));
            final float n7 = 0.35f + n2;
            OpenGL.glBegin(1);
            OpenGL.glVertex2f(n6, n7);
            OpenGL.glVertex2f(n3 + n6, n7);
            OpenGL.glEnd();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.U(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    @Override
    final za method1762(final int n) {
        try {
            final za_Sub2 za_Sub2 = new za_Sub2(n);
            this.aClass148_4320.method2419(za_Sub2, -20911);
            return za_Sub2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.ME(" + n + ')');
        }
    }
    
    final void method1851(final boolean aBoolean4457, final boolean b) {
        try {
            if (b) {
                this.method1739(38, -93, true);
            }
            if (aBoolean4457 == !this.aBoolean4457) {
                this.aBoolean4457 = aBoolean4457;
                this.method1826(2896);
                this.anInt4350 &= 0xFFFFFFF8;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.NC(" + aBoolean4457 + ',' + b + ')');
        }
    }
    
    private final void method1852(final byte b) {
        try {
            this.aFloat4380 = this.anInt4389 - this.anInt4427 - this.aFloat4376;
            this.aFloat4356 = this.aFloat4380 - this.aFloat4392 * this.anInt4441;
            if (this.aFloat4356 < this.anInt4404) {
                this.aFloat4356 = this.anInt4404;
            }
            if (b >= -57) {
                this.aBoolean4345 = true;
            }
            OpenGL.glFogf(2915, this.aFloat4356);
            OpenGL.glFogf(2916, this.aFloat4380);
            Class115.aFloatArray961[0] = Class202.method2702(this.anInt4455, 16711680) / 1.671168E7f;
            Class115.aFloatArray961[2] = Class202.method2702(255, this.anInt4455) / 255.0f;
            Class115.aFloatArray961[1] = Class202.method2702(this.anInt4455, 65280) / 65280.0f;
            OpenGL.glFogfv(2918, Class115.aFloatArray961, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.HD(" + b + ')');
        }
    }
    
    @Override
    final synchronized void method1806(int anInt4321) {
        try {
            int n = 0;
            anInt4321 &= Integer.MAX_VALUE;
            while (!this.aClass148_4338.method2420(-124)) {
                final Class98_Sub34 class98_Sub34 = (Class98_Sub34)this.aClass148_4338.method2421(6494);
                Class255.anIntArray3210[n++] = (int)class98_Sub34.aLong832;
                this.anInt4336 -= class98_Sub34.anInt4126;
                if (n == 1000) {
                    OpenGL.glDeleteBuffersARB(n, Class255.anIntArray3210, 0);
                    n = 0;
                }
            }
            if (~n < -1) {
                OpenGL.glDeleteBuffersARB(n, Class255.anIntArray3210, 0);
                n = 0;
            }
            while (!this.aClass148_4339.method2420(-125)) {
                final Class98_Sub34 class98_Sub35 = (Class98_Sub34)this.aClass148_4339.method2421(6494);
                Class255.anIntArray3210[n++] = (int)class98_Sub35.aLong832;
                this.anInt4337 -= class98_Sub35.anInt4126;
                if (~n == 0xFFFFFC17) {
                    OpenGL.glDeleteTextures(n, Class255.anIntArray3210, 0);
                    n = 0;
                }
            }
            if (n > 0) {
                OpenGL.glDeleteTextures(n, Class255.anIntArray3210, 0);
                n = 0;
            }
            while (!this.aClass148_4340.method2420(-124)) {
                Class255.anIntArray3210[n++] = ((Class98_Sub34)this.aClass148_4340.method2421(6494)).anInt4126;
                if (n == 1000) {
                    OpenGL.glDeleteFramebuffersEXT(n, Class255.anIntArray3210, 0);
                    n = 0;
                }
            }
            if (~n < -1) {
                OpenGL.glDeleteFramebuffersEXT(n, Class255.anIntArray3210, 0);
                n = 0;
            }
            while (!this.aClass148_4341.method2420(-125)) {
                final Class98_Sub34 class98_Sub36 = (Class98_Sub34)this.aClass148_4341.method2421(6494);
                Class255.anIntArray3210[n++] = (int)class98_Sub36.aLong832;
                this.anInt4335 -= class98_Sub36.anInt4126;
                if (n == 1000) {
                    OpenGL.glDeleteRenderbuffersEXT(n, Class255.anIntArray3210, 0);
                    n = 0;
                }
            }
            if (~n < -1) {
                OpenGL.glDeleteRenderbuffersEXT(n, Class255.anIntArray3210, 0);
            }
            while (!this.aClass148_4334.method2420(-126)) {
                final Class98_Sub34 class98_Sub37 = (Class98_Sub34)this.aClass148_4334.method2421(6494);
                OpenGL.glDeleteLists((int)class98_Sub37.aLong832, class98_Sub37.anInt4126);
            }
            while (!this.aClass148_4342.method2420(-126)) {
                OpenGL.glDeleteProgramARB((int)this.aClass148_4342.method2421(6494).aLong832);
            }
            while (!this.aClass148_4343.method2420(-126)) {
                OpenGL.glDeleteObjectARB(this.aClass148_4343.method2421(6494).aLong832);
            }
            while (!this.aClass148_4334.method2420(-125)) {
                final Class98_Sub34 class98_Sub38 = (Class98_Sub34)this.aClass148_4334.method2421(6494);
                OpenGL.glDeleteLists((int)class98_Sub38.aLong832, class98_Sub38.anInt4126);
            }
            this.aClass364_4308.method3933(0);
            if (this.E() > 100663296 && 60000L + this.aLong4351 < Class343.method3819(-47)) {
                System.gc();
                this.aLong4351 = Class343.method3819(-47);
            }
            this.anInt4321 = anInt4321;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.UB(" + anInt4321 + ')');
        }
    }
    
    @Override
    final void KA(int anInt4370, int anInt4371, int anInt4372, int anInt4373) {
        try {
            if (~this.anInt4305 > ~anInt4372) {
                anInt4372 = this.anInt4305;
            }
            if (anInt4370 < 0) {
                anInt4370 = 0;
            }
            if (anInt4371 < 0) {
                anInt4371 = 0;
            }
            if (anInt4373 > this.anInt4304) {
                anInt4373 = this.anInt4304;
            }
            this.anInt4414 = anInt4373;
            this.anInt4402 = anInt4371;
            this.anInt4370 = anInt4370;
            this.anInt4432 = anInt4372;
            OpenGL.glEnable(3089);
            this.method1906(4353);
            this.method1828((byte)107);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.KA(" + anInt4370 + ',' + anInt4371 + ',' + anInt4372 + ',' + anInt4373 + ')');
        }
    }
    
    static final void method1853(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            final int smart = class98_Sub22.readSmart(n + 1689622710);
            if (n == 2) {
                Class98_Sub10_Sub36.aClass114Array5744 = new Class114[smart];
                for (int n2 = 0; ~smart < ~n2; ++n2) {
                    Class98_Sub10_Sub36.aClass114Array5744[n2] = new Class114();
                    Class98_Sub10_Sub36.aClass114Array5744[n2].anInt956 = class98_Sub22.readSmart(1689622712);
                    Class98_Sub10_Sub36.aClass114Array5744[n2].aString957 = class98_Sub22.method1223(n ^ 0xFFFFFFFD);
                }
                Class164.anInt1274 = class98_Sub22.readSmart(1689622712);
                Class101.anInt854 = class98_Sub22.readSmart(1689622712);
                Class42_Sub3.anInt5366 = class98_Sub22.readSmart(n ^ 0x64B598BA);
                Class98_Sub20.aClass53_Sub1Array3967 = new Class53_Sub1[1 + (-Class164.anInt1274 + Class101.anInt854)];
                for (int n3 = 0; Class42_Sub3.anInt5366 > n3; ++n3) {
                    final int smart2 = class98_Sub22.readSmart(1689622712);
                    final Class53_Sub1[] aClass53_Sub1Array3967 = Class98_Sub20.aClass53_Sub1Array3967;
                    final int n4 = smart2;
                    final Class53_Sub1 class53_Sub1 = new Class53_Sub1();
                    aClass53_Sub1Array3967[n4] = class53_Sub1;
                    final Class53_Sub1 class53_Sub2 = class53_Sub1;
                    class53_Sub2.anInt426 = class98_Sub22.readUnsignedByte((byte)(-111));
                    class53_Sub2.anInt427 = class98_Sub22.readInt(-2);
                    class53_Sub2.anInt3632 = smart2 - -Class164.anInt1274;
                    class53_Sub2.aString3630 = class98_Sub22.method1223(-1);
                    class53_Sub2.aString3634 = class98_Sub22.method1223(-1);
                }
                Class6.anInt88 = class98_Sub22.readInt(n ^ 0xFFFFFFFC);
                Class98_Sub17.aBoolean3944 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.QC(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method1854(final int n) {
        try {
            int n2 = 0;
            while (!this.anOpenGL4298.b()) {
                if (~(n2++) < -6) {
                    throw new RuntimeException("");
                }
                Class246_Sub7.method3131(0, 1000L);
            }
            if (n > -25) {
                this.aClass98_Sub5Array4408 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.BD(" + n + ')');
        }
    }
    
    @Override
    final void ZA(final int anInt4412, final float aFloat4435, final float aFloat4436, final float n, final float n2, final float n3) {
        try {
            final boolean b = ~anInt4412 != ~this.anInt4412;
            if (b || this.aFloat4435 != aFloat4435 || aFloat4436 != this.aFloat4407) {
                this.anInt4412 = anInt4412;
                this.aFloat4407 = aFloat4436;
                this.aFloat4435 = aFloat4435;
                if (b) {
                    this.aFloat4458 = (this.anInt4412 & 0xFF) / 255.0f;
                    this.aFloat4420 = (this.anInt4412 & 0xFF00) / 65280.0f;
                    this.aFloat4433 = (0xFF0000 & this.anInt4412) / 1.671168E7f;
                    this.method1860((byte)(-41));
                }
                this.method1900((byte)90);
            }
            if (this.aFloatArray4372[0] != n || this.aFloatArray4372[1] != n2 || n3 != this.aFloatArray4372[2]) {
                this.aFloatArray4372[0] = n;
                this.aFloatArray4372[1] = n2;
                this.aFloatArray4372[2] = n3;
                this.aFloatArray4418[0] = -n;
                this.aFloatArray4418[2] = -n3;
                this.aFloatArray4418[1] = -n2;
                final float n4 = (float)(1.0 / Math.sqrt(n * n + n2 * n2 + n3 * n3));
                this.aFloatArray4438[2] = n3 * n4;
                this.aFloatArray4438[0] = n * n4;
                this.aFloatArray4438[1] = n4 * n2;
                this.aFloatArray4463[1] = -this.aFloatArray4438[1];
                this.aFloatArray4463[2] = -this.aFloatArray4438[2];
                this.aFloatArray4463[0] = -this.aFloatArray4438[0];
                this.method1831(127);
                this.anInt4377 = (int)(256.0f * n3 / n2);
                this.anInt4398 = (int)(256.0f * n / n2);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.ZA(" + anInt4412 + ',' + aFloat4435 + ',' + aFloat4436 + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final int XA() {
        try {
            return this.anInt4389;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.XA()");
        }
    }
    
    @Override
    final boolean method1819() {
        try {
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.KE()");
        }
    }
    
    final synchronized void method1855(final boolean b, final long aLong832) {
        try {
            if (b) {
                this.method1740(null);
            }
            final Class98 class98 = new Class98();
            class98.aLong832 = aLong832;
            this.aClass148_4343.method2419(class98, -20911);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.PD(" + b + ',' + aLong832 + ')');
        }
    }
    
    final void method1856(final boolean aBoolean4373, final int n) {
        try {
            if (n != 6914) {
                this.anInt4411 = -103;
            }
            if (this.aBoolean4373 == !aBoolean4373) {
                this.aBoolean4373 = aBoolean4373;
                this.method1893(115);
                this.anInt4350 &= 0xFFFFFFE0;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.BA(" + aBoolean4373 + ',' + n + ')');
        }
    }
    
    private final void method1857(final float n, final float n2, final byte b, final float n3) {
        try {
            OpenGL.glMatrixMode(5890);
            if (this.aBoolean4422) {
                OpenGL.glLoadIdentity();
            }
            OpenGL.glTranslatef(n, n3, n2);
            OpenGL.glMatrixMode(5888);
            this.aBoolean4422 = true;
            if (b != 44) {
                this.anInterface16_4374 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.FD(" + n + ',' + n2 + ',' + b + ',' + n3 + ')');
        }
    }
    
    final void method1858(final float n, final float n2, final float n3, final float n4, final int n5) {
        try {
            Class115.aFloatArray961[1] = n;
            Class115.aFloatArray961[3] = n4;
            Class115.aFloatArray961[2] = n2;
            Class115.aFloatArray961[0] = n3;
            OpenGL.glTexEnvfv(8960, 8705, Class115.aFloatArray961, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.GB(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    @Override
    final int i() {
        try {
            return this.anInt4404;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.i()");
        }
    }
    
    @Override
    final void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final aa aa, final int n7, final int n8, final int n9, final int n10, int n11) {
        try {
            if (n3 != n || ~n2 != ~n4) {
                final aa_Sub3 aa_Sub3 = (aa_Sub3)aa;
                final Class42_Sub1_Sub1 aClass42_Sub1_Sub1_3568 = aa_Sub3.aClass42_Sub1_Sub1_3568;
                this.method1829((byte)(-119));
                this.method1863(1, aa_Sub3.aClass42_Sub1_Sub1_3568);
                this.method1870((byte)(-69), n6);
                this.method1899(8448, 8960, 7681);
                this.method1840(0, 768, -121, 34167);
                final float n12 = aClass42_Sub1_Sub1_3568.aFloat6205 / aClass42_Sub1_Sub1_3568.anInt6207;
                final float n13 = aClass42_Sub1_Sub1_3568.aFloat6209 / aClass42_Sub1_Sub1_3568.anInt6204;
                final float n14 = -n + n3;
                final float n15 = n4 - n2;
                final float n16 = (float)(1.0 / Math.sqrt(n15 * n15 + n14 * n14));
                final float n17 = n14 * n16;
                final float n18 = n15 * n16;
                OpenGL.glColor4ub((byte)(n5 >> 1969603824), (byte)(n5 >> -519019928), (byte)n5, (byte)(n5 >> 1163947384));
                n11 %= n10 - -n9;
                final float n19 = n17 * n9;
                final float n20 = n9 * n18;
                float n21 = 0.0f;
                float n22 = 0.0f;
                float n23 = n19;
                float n24 = n20;
                if (~n9 <= ~n11) {
                    n23 = (-n11 + n9) * n17;
                    n24 = n18 * (n9 - n11);
                }
                else {
                    n21 = (n10 + (n9 - n11)) * n17;
                    n22 = n18 * (-n11 + n10 + n9);
                }
                float n25 = 0.35f + n + n21;
                float n26 = 0.35f + n2 + n22;
                final float n27 = n17 * n10;
                final float n28 = n10 * n18;
                while (true) {
                    if (n3 <= n) {
                        if (0.35f + n3 > n25) {
                            break;
                        }
                        if (n3 > n23 + n25) {
                            n23 = -n25 + n3;
                        }
                    }
                    else {
                        if (n25 > n3 + 0.35f) {
                            break;
                        }
                        if (n25 + n23 > n3) {
                            n23 = n3 - n25;
                        }
                    }
                    if (~n4 >= ~n2) {
                        if (0.35f + n4 > n26) {
                            break;
                        }
                        if (n24 + n26 < n4) {
                            n24 = n4 - n26;
                        }
                    }
                    else {
                        if (n4 + 0.35f < n26) {
                            break;
                        }
                        if (n4 < n24 + n26) {
                            n24 = n4 - n26;
                        }
                    }
                    OpenGL.glBegin(1);
                    OpenGL.glTexCoord2f(n12 * (-n7 + n25), n13 * (-n8 + n26));
                    OpenGL.glVertex2f(n25, n26);
                    OpenGL.glTexCoord2f((-n7 + (n23 + n25)) * n12, (-n8 + (n24 + n26)) * n13);
                    OpenGL.glVertex2f(n25 + n23, n26 + n24);
                    OpenGL.glEnd();
                    n25 += n27 + n23;
                    n26 += n24 + n28;
                    n24 = n20;
                    n23 = n19;
                }
                this.method1840(0, 768, 111, 5890);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.FJ(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + ((aa != null) ? "{...}" : "null") + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ',' + n11 + ')');
        }
    }
    
    @Override
    final void method1785(final Class242 class242, final int n) {
        try {
            this.aClass360_4313.method3906(class242, -104, n, this);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.CG(" + ((class242 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    private final void method1859(final int n) {
        try {
            if (n > -5) {
                this.method1818(-116, null);
            }
            if (this.anInt4350 != 1) {
                this.method1877((byte)(-35));
                this.method1856(false, 6914);
                this.method1851(false, false);
                this.method1881(false, false);
                this.method1875((byte)(-126), false);
                this.method1863(1, null);
                this.method1834(-105, -2);
                this.method1896(260, 1);
                this.anInt4350 = 1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.LC(" + n + ')');
        }
    }
    
    private final void method1860(final byte b) {
        try {
            Class115.aFloatArray961[1] = this.aFloat4420 * this.aFloat4413;
            Class115.aFloatArray961[0] = this.aFloat4413 * this.aFloat4433;
            Class115.aFloatArray961[3] = 1.0f;
            if (b != -41) {
                this.M();
            }
            Class115.aFloatArray961[2] = this.aFloat4413 * this.aFloat4458;
            OpenGL.glLightModelfv(2899, Class115.aFloatArray961, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.DB(" + b + ')');
        }
    }
    
    final void method1861(final int n) {
        try {
            if (n != 19330) {
                this.aClass146_Sub2_4361 = null;
            }
            if (~this.anInt4350 != 0xFFFFFFF7) {
                this.method1904(-22);
                this.method1856(true, 6914);
                this.method1881(true, false);
                this.method1875((byte)109, true);
                this.method1870((byte)(-68), 1);
                this.anInt4350 = 8;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.S(" + n + ')');
        }
    }
    
    @Override
    final Class48 method1769(final Class48 class48, final Class48 class49, final float n, final Class48 class50) {
        try {
            if (class48 != null && class49 != null && this.aBoolean4391 && this.aBoolean4460) {
                Class48_Sub1_Sub2 class48_Sub1_Sub2 = null;
                final Class48_Sub1 class48_Sub1 = (Class48_Sub1)class48;
                final Class48_Sub1 class48_Sub2 = (Class48_Sub1)class49;
                final Class42_Sub2 method456 = class48_Sub1.method456((byte)122);
                final Class42_Sub2 method457 = class48_Sub2.method456((byte)123);
                if (method456 != null && method457 != null) {
                    final int n2 = (method456.anInt5357 <= method457.anInt5357) ? method457.anInt5357 : method456.anInt5357;
                    if (class50 != class48 && class49 != class50 && class50 instanceof Class48_Sub1_Sub2) {
                        final Class48_Sub1_Sub2 class48_Sub1_Sub3 = (Class48_Sub1_Sub2)class50;
                        if (~n2 == ~class48_Sub1_Sub3.method465(-65534)) {
                            class48_Sub1_Sub2 = class48_Sub1_Sub3;
                        }
                    }
                    if (class48_Sub1_Sub2 == null) {
                        class48_Sub1_Sub2 = new Class48_Sub1_Sub2(this, n2);
                    }
                    if (class48_Sub1_Sub2.method464((byte)(-21), method456, n, method457)) {
                        return class48_Sub1_Sub2;
                    }
                }
            }
            if (n >= 0.5f) {
                return class49;
            }
            return class48;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.UC(" + ((class48 != null) ? "{...}" : "null") + ',' + ((class49 != null) ? "{...}" : "null") + ',' + n + ',' + ((class50 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method1862(final int n) {
        try {
            ha_Sub1.aClass85_4299 = null;
            if (n != 0) {
                ha_Sub1.aClass85_4299 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.PE(" + n + ')');
        }
    }
    
    @Override
    final boolean method1800() {
        try {
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.RE()");
        }
    }
    
    @Override
    final void ya() {
        try {
            this.method1875((byte)(-121), true);
            OpenGL.glClear(256);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.ya()");
        }
    }
    
    final void method1863(final int n, final Class42 class42) {
        try {
            if (n == 1) {
                final Class42 class43 = this.aClass42Array4396[this.anInt4417];
                if (class42 != class43) {
                    if (class42 != null) {
                        if (class43 == null) {
                            OpenGL.glEnable(class42.anInt3226);
                        }
                        else if (~class42.anInt3226 != ~class43.anInt3226) {
                            OpenGL.glDisable(class43.anInt3226);
                            OpenGL.glEnable(class42.anInt3226);
                        }
                        OpenGL.glBindTexture(class42.anInt3226, class42.method369(true));
                    }
                    else {
                        OpenGL.glDisable(class43.anInt3226);
                    }
                    this.aClass42Array4396[this.anInt4417] = class42;
                }
                this.anInt4350 &= 0xFFFFFFFE;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.JE(" + n + ',' + ((class42 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final aa method1772(final int n, final int n2, final int[] array, final int[] array2) {
        try {
            return InputStream_Sub2.method123(n2, array, this, n, array2, (byte)111);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.AG(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method1864(final int n) {
        try {
            this.method1834(102, -2);
            for (int i = -1 + this.anInt4410; i >= 0; --i) {
                this.method1845(i, n + 847872904);
                this.method1863(1, null);
                OpenGL.glTexEnvi(8960, 8704, 34160);
            }
            this.method1899(8448, 8960, 8448);
            this.method1840(2, 770, n + 128, 34168);
            this.method1891(370914608);
            this.anInt4347 = 1;
            OpenGL.glEnable(3042);
            OpenGL.glBlendFunc(770, 771);
            this.anInt4344 = 1;
            OpenGL.glEnable(3008);
            OpenGL.glAlphaFunc(516, 0.0f);
            OpenGL.glColorMask(this.aBoolean4345 = true, true, true, true);
            if (n == -32) {
                this.method1856(this.aBoolean4346 = true, 6914);
                this.method1851(true, false);
                this.method1881(true, false);
                this.method1875((byte)113, true);
                this.method1867(n ^ 0xFFFF8CF2);
                this.anOpenGL4298.setSwapInterval(0);
                OpenGL.glShadeModel(7425);
                OpenGL.glClearDepth(1.0f);
                OpenGL.glDepthFunc(515);
                OpenGL.glPolygonMode(1028, 6914);
                OpenGL.glEnable(2884);
                OpenGL.glCullFace(1029);
                OpenGL.glMatrixMode(5888);
                OpenGL.glLoadIdentity();
                OpenGL.glColorMaterial(1028, 5634);
                OpenGL.glEnable(2903);
                final float[] array = { 0.0f, 0.0f, 0.0f, 1.0f };
                for (int j = 0; j < 8; ++j) {
                    final int n2 = j + 16384;
                    OpenGL.glLightfv(n2, 4608, array, 0);
                    OpenGL.glLightf(n2, 4615, 0.0f);
                    OpenGL.glLightf(n2, 4616, 0.0f);
                }
                OpenGL.glEnable(16384);
                OpenGL.glEnable(16385);
                OpenGL.glFogf(2914, 0.95f);
                OpenGL.glFogi(2917, 9729);
                OpenGL.glHint(3156, 4353);
                final int n3 = -1;
                this.anInt4455 = n3;
                this.anInt4412 = n3;
                this.la();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.FA(" + n + ')');
        }
    }
    
    @Override
    final void method1774(final int n) {
        try {
            this.method1836(-513);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.IG(" + n + ')');
        }
    }
    
    @Override
    final void method1741(final Canvas canvas, final int n, final int n2) {
        try {
            if (this.aCanvas4303 == canvas) {
                throw new RuntimeException();
            }
            if (!this.aHashtable4306.containsKey(canvas)) {
                if (!canvas.isShowing()) {
                    throw new RuntimeException();
                }
                try {
                    Class.forName("java.awt.Canvas").getMethod("setIgnoreRepaint", Boolean.TYPE).invoke(canvas, Boolean.TRUE);
                }
                catch (Exception ex2) {}
                final long prepareSurface = this.anOpenGL4298.prepareSurface(canvas);
                if (prepareSurface == -1L) {
                    throw new RuntimeException();
                }
                this.aHashtable4306.put(canvas, new Long(prepareSurface));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.FI(" + ((canvas != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method1795(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            this.method1859(-89);
            this.method1870((byte)(-88), n6);
            final float n7 = -n + n3;
            float n8 = n4 - n2;
            float n10 = 0.0f;
            Label_0090: {
                if (n7 != 0.0f || n8 != 0.0f) {
                    final float n9 = (float)(1.0 / Math.sqrt(n7 * n7 + n8 * n8));
                    n8 *= n9;
                    n10 = n7 * n9;
                    if (!client.aBoolean3553) {
                        break Label_0090;
                    }
                }
                n10 = 1.0f;
            }
            OpenGL.glColor4ub((byte)(n5 >> 968665392), (byte)(n5 >> 493545704), (byte)n5, (byte)(n5 >> 804890104));
            OpenGL.glBegin(1);
            OpenGL.glVertex2f(n + 0.35f, 0.35f + n2);
            OpenGL.glVertex2f(n10 + n3 + 0.35f, 0.35f + (n8 + n4));
            OpenGL.glEnd();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.PG(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    @Override
    final boolean method1802() {
        try {
            if (this.aClass98_Sub28_Sub1_4311 != null) {
                if (!this.aClass98_Sub28_Sub1_4311.method1300(0)) {
                    if (this.aClass283_4312.method3344(this.aClass98_Sub28_Sub1_4311, -47)) {
                        this.aClass364_4308.method3934((byte)100);
                        if (!client.aBoolean3553) {
                            return true;
                        }
                    }
                    return false;
                }
                return true;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.FB()");
        }
    }
    
    final void method1865(final int n, final int n2, final Interface8 interface8, final boolean b, int n3) {
        try {
            if (b) {
                this.method1872(-128, 67);
            }
            final int method21 = interface8.method21(5061);
            n3 *= this.method1839(method21, -5122);
            this.method1830(interface8, 2936);
            OpenGL.glDrawElements(n2, n, method21, n3 + interface8.method22(20260));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.VC(" + n + ',' + n2 + ',' + ((interface8 != null) ? "{...}" : "null") + ',' + b + ',' + n3 + ')');
        }
    }
    
    @Override
    final int E() {
        try {
            return this.anInt4337 + this.anInt4336 + this.anInt4335;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.E()");
        }
    }
    
    final int method1866(final int n, final int n2) {
        try {
            if (n >= -73) {
                this.method1822();
            }
            if (n2 == 6406 || ~n2 == 0xFFFFE6F6) {
                return 1;
            }
            if (~n2 == 0xFFFFE6F5 || ~n2 == 0xFFFF77E1 || ~n2 == 0xFFFF77E3) {
                return 2;
            }
            if (n2 == 6407) {
                return 3;
            }
            if (~n2 == 0xFFFFE6F7 || n2 == 34847) {
                return 4;
            }
            if (~n2 == 0xFFFF77E4) {
                return 6;
            }
            if (~n2 == 0xFFFF77E5) {
                return 8;
            }
            if (~n2 == 0xFFFFE6FD) {
                return 3;
            }
            if (n2 == 6401) {
                return 1;
            }
            throw new IllegalArgumentException("");
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.J(" + n + ',' + n2 + ')');
        }
    }
    
    final void method1867(final int n) {
        try {
            if (n != 29458) {
                this.method1893(72);
            }
            if (this.anInt4385 != 0) {
                this.anInt4385 = 0;
                this.anInt4350 &= 0xFFFFFFE0;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.MD(" + n + ')');
        }
    }
    
    final void method1868(final Class104 class104, final Class104 class105, final Class104 class106, final Class104 class107, final int n) {
        try {
            if (class107 != null) {
                this.method1887(class107.anInterface16_902, 34962);
                OpenGL.glVertexPointer(class107.aByte900, class107.aShort899, this.anInterface16_4374.method55(-115), this.anInterface16_4374.method52(24582) - -class107.aByte898);
                OpenGL.glEnableClientState(32884);
            }
            else {
                OpenGL.glDisableClientState(32884);
            }
            if (n != 0) {
                this.F(-106, -93);
            }
            if (class105 == null) {
                OpenGL.glDisableClientState(32885);
            }
            else {
                this.method1887(class105.anInterface16_902, 34962);
                OpenGL.glNormalPointer(class105.aShort899, this.anInterface16_4374.method55(-122), this.anInterface16_4374.method52(24582) - -class105.aByte898);
                OpenGL.glEnableClientState(32885);
            }
            if (class104 == null) {
                OpenGL.glDisableClientState(32886);
            }
            else {
                this.method1887(class104.anInterface16_902, 34962);
                OpenGL.glColorPointer(class104.aByte900, class104.aShort899, this.anInterface16_4374.method55(49), this.anInterface16_4374.method52(24582) - -class104.aByte898);
                OpenGL.glEnableClientState(32886);
            }
            if (class106 == null) {
                OpenGL.glDisableClientState(32888);
            }
            else {
                this.method1887(class106.anInterface16_902, 34962);
                OpenGL.glTexCoordPointer(class106.aByte900, class106.aShort899, this.anInterface16_4374.method55(79), this.anInterface16_4374.method52(24582) - -class106.aByte898);
                OpenGL.glEnableClientState(32888);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.SD(" + ((class104 != null) ? "{...}" : "null") + ',' + ((class105 != null) ? "{...}" : "null") + ',' + ((class106 != null) ? "{...}" : "null") + ',' + ((class107 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final void method1869(final int n, final Interface12 interface12) {
        try {
            if (this.anInt4327 < n || interface12 != this.anInterface12Array4326[this.anInt4327]) {
                throw new RuntimeException();
            }
            this.anInterface12Array4326[this.anInt4327--] = null;
            interface12.method40((byte)(-30));
            if (~this.anInt4327 <= -1) {
                (this.anInterface12_4328 = this.anInterface12Array4326[this.anInt4327]).method36((byte)(-115));
            }
            else {
                this.anInterface12_4328 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.UA(" + n + ',' + ((interface12 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void EA(final int anInt4362, final int anInt4363, final int anInt4364, final int anInt4365) {
        try {
            if (!this.aBoolean4366) {
                throw new RuntimeException("");
            }
            this.anInt4453 = anInt4365;
            this.anInt4362 = anInt4362;
            this.anInt4423 = anInt4363;
            this.anInt4454 = anInt4364;
            if (this.aBoolean4448) {
                this.aClass55_4314.aClass151_Sub9_432.method2471((byte)34);
                this.aClass55_4314.aClass151_Sub9_432.method2470(-16661);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.EA(" + anInt4362 + ',' + anInt4363 + ',' + anInt4364 + ',' + anInt4365 + ')');
        }
    }
    
    @Override
    final Class332 method1797(final int n, final int n2, final int n3, final int n4, final boolean b) {
        try {
            return new Class332_Sub1(this, n, n2, n3, n4);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.MG(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    final void method1870(final byte b, final int anInt4347) {
        try {
            if (b > -18) {
                this.anInt4337 = 47;
            }
            if (anInt4347 != this.anInt4347) {
                boolean aBoolean4345;
                boolean aBoolean4346;
                int anInt4348;
                if (~anInt4347 == 0xFFFFFFFE) {
                    aBoolean4345 = true;
                    aBoolean4346 = true;
                    anInt4348 = 1;
                }
                else if (anInt4347 == 2) {
                    aBoolean4346 = true;
                    aBoolean4345 = false;
                    anInt4348 = 2;
                }
                else if (anInt4347 == 128) {
                    aBoolean4345 = true;
                    aBoolean4346 = true;
                    anInt4348 = 3;
                }
                else {
                    anInt4348 = 0;
                    aBoolean4346 = true;
                    aBoolean4345 = false;
                }
                if (aBoolean4346 == !this.aBoolean4346) {
                    OpenGL.glColorMask(aBoolean4346, aBoolean4346, aBoolean4346, true);
                    this.aBoolean4346 = aBoolean4346;
                }
                if (this.aBoolean4345 != aBoolean4345) {
                    if (!aBoolean4345) {
                        OpenGL.glDisable(3008);
                    }
                    else {
                        OpenGL.glEnable(3008);
                    }
                    this.aBoolean4345 = aBoolean4345;
                }
                if (~anInt4348 != ~this.anInt4344) {
                    if (~anInt4348 != 0xFFFFFFFE) {
                        if (anInt4348 == 2) {
                            OpenGL.glEnable(3042);
                            OpenGL.glBlendFunc(1, 1);
                        }
                        else if (anInt4348 == 3) {
                            OpenGL.glEnable(3042);
                            OpenGL.glBlendFunc(774, 1);
                        }
                        else {
                            OpenGL.glDisable(3042);
                        }
                    }
                    else {
                        OpenGL.glEnable(3042);
                        OpenGL.glBlendFunc(770, 771);
                    }
                    this.anInt4344 = anInt4348;
                }
                this.anInt4347 = anInt4347;
                this.anInt4350 &= 0xFFFFFFE3;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.LE(" + b + ',' + anInt4347 + ')');
        }
    }
    
    @Override
    final void method1764(final int n, final int n2) throws Exception_Sub1 {
        try {
            try {
                this.anOpenGL4298.swapBuffers();
            }
            catch (Exception ex2) {}
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.BB(" + n + ',' + n2 + ')');
        }
    }
    
    ha_Sub1(final Canvas canvas, final d d, final int anInt4310) {
        super(d);
        this.aHashtable4306 = new Hashtable();
        this.anInt4309 = 128;
        this.aClass360_4313 = new Class360();
        this.aClass111_Sub1_4315 = new Class111_Sub1();
        this.aClass111_Sub1_4316 = new Class111_Sub1();
        this.anInt4318 = 8;
        this.aBoolean4317 = false;
        this.anInt4319 = 3;
        this.aClass148_4320 = new Class148();
        this.anInterface12Array4326 = new Interface12[4];
        this.anInterface12Array4329 = new Interface12[4];
        this.anInt4324 = -1;
        this.anInt4327 = -1;
        this.anInt4331 = -1;
        this.anInterface12Array4332 = new Interface12[4];
        new Class215();
        new Class377(16);
        this.aClass148_4334 = new Class148();
        this.aClass148_4338 = new Class148();
        this.aClass148_4339 = new Class148();
        this.aClass148_4340 = new Class148();
        this.aClass148_4341 = new Class148();
        this.aClass148_4342 = new Class148();
        this.aClass148_4343 = new Class148();
        this.aClass111_Sub1_4348 = new Class111_Sub1();
        this.aClass111_Sub1_4353 = new Class111_Sub1();
        this.aClass111_Sub1_4354 = new Class111_Sub1();
        this.aFloat4376 = 0.0f;
        this.anInt4370 = 0;
        this.aFloat4392 = 1.0f;
        this.anInt4395 = 8448;
        this.anInt4387 = 8448;
        this.aClass98_Sub5Array4408 = new Class98_Sub5[Class90.anInt721];
        this.aFloat4400 = -1.0f;
        this.aFloatArray4372 = new float[4];
        this.anInt4389 = 3584;
        this.anInt4414 = 0;
        this.aFloatArray4418 = new float[4];
        this.anInt4404 = 50;
        this.anInt4412 = -1;
        this.anInt4402 = 0;
        this.aFloat4407 = -1.0f;
        this.anInt4423 = -1;
        this.anInt4427 = 0;
        this.aFloat4435 = -1.0f;
        this.aFloat4429 = -1.0f;
        this.anInt4381 = 512;
        this.aFloat4420 = 1.0f;
        this.aFloatArray4440 = new float[16];
        this.aBoolean4448 = false;
        this.anInt4432 = 0;
        this.aFloat4379 = 3584.0f;
        this.aFloatArray4438 = new float[4];
        this.anInt4455 = -1;
        this.anInt4454 = -1;
        this.anInt4441 = -1;
        this.anInt4394 = 0;
        this.anInt4415 = 0;
        this.aFloat4452 = 1.0f;
        this.aFloat4458 = 1.0f;
        this.anInt4419 = 512;
        this.aFloat4401 = 3584.0f;
        this.aFloat4433 = 1.0f;
        this.anInt4451 = 0;
        this.aFloatArray4463 = new float[4];
        this.anInt4453 = 0;
        this.anInt4409 = 0;
        this.aBoolean4465 = true;
        this.aClass98_Sub22_Sub2_4446 = new Class98_Sub22_Sub2(8192);
        this.anIntArray4468 = new int[1];
        this.aByteArray4469 = new byte[16384];
        this.anIntArray4470 = new int[1];
        this.anIntArray4471 = new int[1];
        try {
            this.aCanvas4303 = canvas;
            this.aCanvas4300 = canvas;
            this.anInt4310 = anInt4310;
            if (!Class134_Sub1.method2246("jaclib", (byte)(-36))) {
                throw new RuntimeException("");
            }
            if (!Class134_Sub1.method2246("jaggl", (byte)(-36))) {
                throw new RuntimeException("");
            }
            try {
                this.anOpenGL4298 = new OpenGL();
                final long init = this.anOpenGL4298.init(canvas, 8, 8, 8, 24, 0, this.anInt4310);
                this.aLong4307 = init;
                this.aLong4302 = init;
                if (~this.aLong4307 == -1L) {
                    throw new RuntimeException("");
                }
                this.method1854(-33);
                if (~this.method1849((byte)(-123)) != -1) {
                    throw new RuntimeException("");
                }
                this.anInt4425 = (this.aBoolean4397 ? 33639 : 5121);
                if (~this.aString4403.indexOf("radeon") != 0x0) {
                    int method3607 = 0;
                    boolean b = false;
                    boolean b2 = false;
                    final String[] method3608 = Class112.method2142(this.aString4403.replace('/', ' '), ' ', false);
                    for (int n = 0; ~n > ~method3608.length; ++n) {
                        String s = method3608[n];
                        try {
                            if (~s.length() < -1) {
                                if (~s.charAt(0) == 0xFFFFFF87 && s.length() >= 3 && Class77_Sub1.method781((byte)53, s.substring(1, 3))) {
                                    b2 = true;
                                    s = s.substring(1);
                                }
                                if (s.equals("hd")) {
                                    b = true;
                                }
                                else {
                                    if (s.startsWith("hd")) {
                                        b = true;
                                        s = s.substring(2);
                                    }
                                    if (~s.length() <= -5 && Class77_Sub1.method781((byte)53, s.substring(0, 4))) {
                                        method3607 = PacketSender.method3607(-94, s.substring(0, 4));
                                        break;
                                    }
                                }
                            }
                        }
                        catch (Exception ex2) {}
                    }
                    if (!b2 && !b) {
                        if (~method3607 <= -7001 && method3607 <= 9250) {
                            this.aBoolean4434 = false;
                        }
                        if (method3607 >= 7000 && ~method3607 >= -8000) {
                            this.aBoolean4449 = false;
                        }
                    }
                    if (!b || method3607 < 4000) {
                        this.aBoolean4383 = false;
                    }
                    this.aBoolean4378 &= this.anOpenGL4298.a("GL_ARB_half_float_pixel");
                    this.aBoolean4459 = this.aBoolean4449;
                    this.aBoolean4406 = true;
                }
                if (this.aString4430.indexOf("intel") != -1) {
                    this.aBoolean4460 = false;
                }
                this.aBoolean4439 = !this.aString4430.equals("s3 graphics");
                if (this.aBoolean4449) {
                    try {
                        OpenGL.glGenBuffersARB(1, new int[1], 0);
                    }
                    catch (Throwable t2) {
                        throw new RuntimeException("");
                    }
                }
                Class101.method1702(8, false, true);
                this.aBoolean4317 = true;
                this.aClass364_4308 = new Class364(this, super.aD938);
                this.method1903(98);
                this.aClass118_4322 = new Class118(this);
                this.aClass283_4312 = new Class283(this);
                if (this.aClass283_4312.method3349(true)) {
                    this.aClass98_Sub28_Sub1_4311 = new Class98_Sub28_Sub1(this);
                    if (!this.aClass98_Sub28_Sub1_4311.method1309((byte)41)) {
                        this.aClass98_Sub28_Sub1_4311.method1304((byte)(-83));
                        this.aClass98_Sub28_Sub1_4311 = null;
                    }
                }
                this.aClass55_4314 = new Class55(this);
                this.method1864(-32);
                this.method1850((byte)(-31));
                this.method1817();
            }
            catch (Throwable t) {
                t.printStackTrace();
                this.method1773();
                throw new RuntimeException("");
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.<init>(" + ((canvas != null) ? "{...}" : "null") + ',' + ((d != null) ? "{...}" : "null") + ',' + anInt4310 + ')');
        }
    }
    
    static final void method1871(final int anInt6300, final int anInt6301, final boolean b, final int anInt6302, final int anInt6303, final int anInt6304, final int n) {
        try {
            Class308.anInt2580 = anInt6302;
            Class263.anInt1967 = anInt6304;
            Class98_Sub10_Sub21.anInt5643 = anInt6303;
            Class98_Sub46_Sub2_Sub2.anInt6300 = anInt6300;
            if (n >= -91) {
                method1871(73, 45, false, -110, -71, -102, 48);
            }
            Class363.anInt3098 = anInt6301;
            if (b && ~Class263.anInt1967 <= -101) {
                Class134.anInt3461 = 256 + Class98_Sub10_Sub21.anInt5643 * 512;
                Class98_Sub46_Sub10.anInt6020 = 256 + Class363.anInt3098 * 512;
                Class79.anInt601 = Class98_Sub46_Sub2_Sub2.method1538(Class43.anInt377, Class134.anInt3461, Class98_Sub46_Sub10.anInt6020, 24111) + -Class308.anInt2580;
            }
            Class116.anInt967 = (Class64_Sub26.anInt3712 = -1);
            Class98_Sub46_Sub20_Sub2.anInt6319 = 2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.WA(" + anInt6300 + ',' + anInt6301 + ',' + b + ',' + anInt6302 + ',' + anInt6303 + ',' + anInt6304 + ',' + n + ')');
        }
    }
    
    @Override
    final boolean method1766() {
        try {
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.NG()");
        }
    }
    
    @Override
    final void pa() {
        try {
            this.aBoolean4366 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.pa()");
        }
    }
    
    @Override
    final void method1779(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            final float n7 = n + 0.35f;
            final float n8 = 0.35f + n2;
            final float n9 = n7 + n3 - 1.0f;
            final float n10 = n4 + n8 - 1.0f;
            this.method1859(-68);
            this.method1870((byte)(-66), n6);
            OpenGL.glColor4ub((byte)(n5 >> 1422671184), (byte)(n5 >> 1460018312), (byte)n5, (byte)(n5 >> -1612786792));
            if (this.aBoolean4360) {
                OpenGL.glDisable(32925);
            }
            OpenGL.glBegin(2);
            OpenGL.glVertex2f(n7, n8);
            OpenGL.glVertex2f(n7, n10);
            OpenGL.glVertex2f(n9, n10);
            OpenGL.glVertex2f(n9, n8);
            OpenGL.glEnd();
            if (this.aBoolean4360) {
                OpenGL.glEnable(32925);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.OE(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    @Override
    final Class62 method1799() {
        try {
            int n = -1;
            if (this.aString4430.indexOf("nvidia") != -1) {
                n = 4318;
            }
            else if (~this.aString4430.indexOf("intel") != 0x0) {
                n = 32902;
            }
            else if (this.aString4430.indexOf("ati") != -1) {
                n = 4098;
            }
            return new Class62(n, "OpenGL", this.anInt4390, this.aString4403, 0L);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.RB()");
        }
    }
    
    final synchronized void method1872(final int n, final int n2) {
        try {
            final Class98 class98 = new Class98();
            if (n2 != 2834) {
                this.method1839(59, 97);
            }
            class98.aLong832 = n;
            this.aClass148_4342.method2419(class98, n2 ^ 0xFFFFA543);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.NA(" + n + ',' + n2 + ')');
        }
    }
    
    final synchronized void method1873(final int n, final int n2, final int n3) {
        try {
            final Class98_Sub34 class98_Sub34 = new Class98_Sub34(n);
            class98_Sub34.aLong832 = n3;
            if (n2 != 4) {
                this.anInt4409 = -76;
            }
            this.aClass148_4339.method2419(class98_Sub34, -20911);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.MB(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final boolean method1823() {
        try {
            return this.aBoolean4360 && (!this.method1768() || this.aBoolean4450);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.AF()");
        }
    }
    
    @Override
    final void GA(final int n) {
        try {
            this.method1870((byte)(-80), 0);
            OpenGL.glClearColor((n & 0xFF0000) / 1.671168E7f, (n & 0xFF00) / 65280.0f, (n & 0xFF) / 255.0f, (n >>> 727917496) / 255.0f);
            OpenGL.glClear(16384);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.GA(" + n + ')');
        }
    }
    
    private final void method1874(final int n) {
        try {
            if (n != -30) {
                this.method1831(-70);
            }
            if (this.anInt4385 != 3) {
                this.anInt4385 = 3;
                this.method1884((byte)78);
                this.method1833(4);
                this.anInt4350 &= 0xFFFFFFF8;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.ND(" + n + ')');
        }
    }
    
    @Override
    final Class332 method1758(final Class324 class324, final boolean b) {
        try {
            final int[] array = new int[class324.anInt2720 * class324.anInt2722];
            int n = 0;
            int n2 = 0;
            if (class324.aByteArray2723 != null) {
                for (int n3 = 0; class324.anInt2720 > n3; ++n3) {
                    for (int n4 = 0; ~class324.anInt2722 < ~n4; ++n4) {
                        array[n2++] = Class41.method366(class324.anIntArray2718[Class202.method2702(255, class324.aByteArray2717[n])], class324.aByteArray2723[n] << -986422664);
                        ++n;
                    }
                }
            }
            else {
                for (int i = 0; i < class324.anInt2720; ++i) {
                    for (int n5 = 0; ~n5 > ~class324.anInt2722; ++n5) {
                        final int n6 = class324.anIntArray2718[class324.aByteArray2717[n++] & 0xFF];
                        array[n2++] = ((n6 != 0) ? Class41.method366(n6, -16777216) : 0);
                    }
                }
            }
            final Class332 method1748 = this.method1748(-7962, 0, class324.anInt2722, class324.anInt2720, array, class324.anInt2722);
            method1748.method3740(class324.anInt2725, class324.anInt2721, class324.anInt2719, class324.anInt2724);
            return method1748;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.IE(" + ((class324 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final void method1818(final int i, final Class98_Sub5[] array) {
        try {
            for (int n = 0; i > n; ++n) {
                this.aClass98_Sub5Array4408[n] = array[n];
            }
            this.anInt4384 = i;
            if (this.anInt4385 != 1) {
                this.method1842(114);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.GF(" + i + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final int I() {
        try {
            final int anInt4467 = this.anInt4467;
            this.anInt4467 = 0;
            return anInt4467;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.I()");
        }
    }
    
    @Override
    final s a(final int n, final int n2, final int[][] array, final int[][] array2, final int n3, final int n4, final int n5) {
        try {
            return new s_Sub1(this, n4, n5, n, n2, array, array2, n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.DG(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    @Override
    final Class43 method1804(final Class197 class197, final Class324[] array, final boolean b) {
        try {
            return new Class43_Sub1(this, class197, array, b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.HH(" + ((class197 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method1875(final byte b, final boolean aBoolean4349) {
        try {
            if (!this.aBoolean4349 == aBoolean4349) {
                this.aBoolean4349 = aBoolean4349;
                this.method1876((byte)(-75));
                this.anInt4350 &= 0xFFFFFFE0;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.EE(" + b + ',' + aBoolean4349 + ')');
        }
    }
    
    @Override
    final boolean method1767() {
        try {
            return this.aClass98_Sub28_Sub1_4311 != null && (this.anInt4310 <= 1 || this.aBoolean4450);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.JF()");
        }
    }
    
    private final void method1876(final byte b) {
        try {
            if (b != -75) {
                this.aBoolean4459 = false;
            }
            OpenGL.glDepthMask(this.aBoolean4349 && this.aBoolean4465);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.W(" + b + ')');
        }
    }
    
    @Override
    final Class111 method1821() {
        try {
            return new Class111_Sub1();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.SE()");
        }
    }
    
    @Override
    final Class332 method1770(final int[] array, final int n, final int n2, final int n3, final int n4, final boolean b) {
        try {
            return new Class332_Sub1(this, n3, n4, array, n, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.MF(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    private final void method1877(final byte b) {
        try {
            if (b < -33 && ~this.anInt4385 != 0xFFFFFFFE) {
                OpenGL.glMatrixMode(5889);
                OpenGL.glLoadIdentity();
                if (~this.anInt4305 < -1 && ~this.anInt4304 < -1) {
                    OpenGL.glOrtho(0.0, this.anInt4305, this.anInt4304, 0.0, -1.0, 1.0);
                }
                OpenGL.glMatrixMode(5888);
                OpenGL.glLoadIdentity();
                this.anInt4385 = 1;
                this.anInt4350 &= 0xFFFFFFE7;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.HC(" + b + ')');
        }
    }
    
    final Interface16 method1878(final int n, final boolean b, final int n2, final int n3, final byte[] array) {
        try {
            if (this.aBoolean4449 && (!b || this.aBoolean4459)) {
                return new Class287_Sub1(this, n2, array, n, b);
            }
            if (n3 >= -46) {
                return null;
            }
            return new Class156_Sub2(this, n2, array, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.V(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final synchronized void method1879(final int n, final byte b, final int n2) {
        try {
            final Class98_Sub34 class98_Sub34 = new Class98_Sub34(n);
            class98_Sub34.aLong832 = n2;
            this.aClass148_4338.method2419(class98_Sub34, -20911);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.BC(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method1825() {
        try {
            OpenGL.glFinish();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.TC()");
        }
    }
    
    @Override
    final void method1773() {
        try {
            for (Class98 class98 = this.aClass148_4320.method2418(32); class98 != null; class98 = this.aClass148_4320.method2417(117)) {
                ((za_Sub2)class98).method1681((byte)36);
            }
            if (this.aClass283_4312 != null) {
                this.aClass283_4312.method3346((byte)(-127));
            }
            if (this.anOpenGL4298 != null) {
                this.method1836(-513);
                final Enumeration<Canvas> keys = this.aHashtable4306.keys();
                while (keys.hasMoreElements()) {
                    final Canvas canvas = keys.nextElement();
                    this.anOpenGL4298.releaseSurface(canvas, (long)this.aHashtable4306.get(canvas));
                }
                this.anOpenGL4298.release();
                this.anOpenGL4298 = null;
            }
            if (this.aBoolean4317) {
                Class18.method248(true, 75, false);
                this.aBoolean4317 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.AB()");
        }
    }
    
    final void method1880(final int n, final Interface12 anInterface12_4325) {
        try {
            if (~this.anInt4324 <= -4) {
                throw new RuntimeException();
            }
            if (this.anInt4324 >= 0) {
                this.anInterface12Array4329[this.anInt4324].method38(-27095);
            }
            if (n >= 65) {
                this.anInterface12Array4329[++this.anInt4324] = anInterface12_4325;
                (this.anInterface12_4325 = anInterface12_4325).method37((byte)77);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.JC(" + n + ',' + ((anInterface12_4325 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void P(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            this.method1859(-67);
            this.method1870((byte)(-120), n5);
            final float n6 = 0.35f + n;
            final float n7 = n2 + 0.35f;
            OpenGL.glColor4ub((byte)(n4 >> -532968944), (byte)(n4 >> 1812124904), (byte)n4, (byte)(n4 >> 1778410936));
            OpenGL.glBegin(1);
            OpenGL.glVertex2f(n6, n7);
            OpenGL.glVertex2f(n6, n3 + n7);
            OpenGL.glEnd();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.P(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    @Override
    final void method1812() {
    }
    
    @Override
    final boolean method1780() {
        try {
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.FG()");
        }
    }
    
    @Override
    final void method1746(final int n, final int n2, final int n3, final int n4) {
        try {
            this.aClass283_4312.method3345(n3, n2, n4, n, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.LD(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    final void method1881(final boolean aBoolean4352, final boolean b) {
        try {
            if (aBoolean4352 == !this.aBoolean4352) {
                Label_0041: {
                    if (!aBoolean4352) {
                        OpenGL.glDisable(2929);
                        if (!client.aBoolean3553) {
                            break Label_0041;
                        }
                    }
                    OpenGL.glEnable(2929);
                }
                this.aBoolean4352 = aBoolean4352;
                this.anInt4350 &= 0xFFFFFFE0;
            }
            if (b) {
                this.method1811(9, -63, 119, 56, 110, 39, -105, -9, -22);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.VA(" + aBoolean4352 + ',' + b + ')');
        }
    }
    
    final void method1882(final int n, final int n2) {
        try {
            Class115.aFloatArray961[3] = (n >>> 911577944) / 255.0f;
            if (n2 > -67) {
                this.method1741(null, 84, 45);
            }
            Class115.aFloatArray961[0] = Class202.method2702(16711680, n) / 1.671168E7f;
            Class115.aFloatArray961[2] = Class202.method2702(n, 255) / 255.0f;
            Class115.aFloatArray961[1] = Class202.method2702(n, 65280) / 65280.0f;
            OpenGL.glTexEnvfv(8960, 8705, Class115.aFloatArray961, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.SA(" + n + ',' + n2 + ')');
        }
    }
    
    final void method1883(final Class111_Sub1 class111_Sub1, final byte b) {
        try {
            OpenGL.glPushMatrix();
            OpenGL.glMultMatrixf(class111_Sub1.method2113(-117), 0);
            if (b > -123) {
                this.aClass148_4338 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.JD(" + ((class111_Sub1 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final void method1749(final boolean b) {
    }
    
    @Override
    final Interface5 method1813(final int n, final int n2) {
        try {
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.GD(" + n + ',' + n2 + ')');
        }
    }
    
    private final void method1884(final byte b) {
        try {
            if (b < 15) {
                this.anOpenGL4298 = null;
            }
            final float n = this.aFloat4452 * -this.anInt4451 / this.anInt4419;
            final float n2 = this.aFloat4452 * -this.anInt4394 / this.anInt4381;
            final float n3 = this.aFloat4452 * (this.anInt4305 + -this.anInt4451) / this.anInt4419;
            final float n4 = (this.anInt4304 + -this.anInt4394) * this.aFloat4452 / this.anInt4381;
            OpenGL.glMatrixMode(5889);
            OpenGL.glLoadIdentity();
            if (n3 != n && n2 != n4) {
                OpenGL.glOrtho(n, n3, -n4, -n2, this.anInt4404, this.anInt4389);
            }
            OpenGL.glMatrixMode(5888);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.CC(" + b + ')');
        }
    }
    
    private final void method1885(final byte b) {
        try {
            OpenGL.glMatrixMode(5889);
            OpenGL.glLoadMatrixf(this.aFloatArray4440, 0);
            OpenGL.glMatrixMode(5888);
            if (b > -31) {
                this.method1803(55, 77, -121, 59, -28, 57);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.CD(" + b + ')');
        }
    }
    
    @Override
    final int[] Y() {
        try {
            return new int[] { this.anInt4451, this.anInt4394, this.anInt4419, this.anInt4381 };
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.Y()");
        }
    }
    
    final void method1886(final int n, final int n2, final int n3, final int n4) {
        try {
            OpenGL.glTexEnvi(8960, n2 + 34184, n4);
            OpenGL.glTexEnvi(8960, n3 - -n2, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final void method1775(final Class48 class48) {
        try {
            this.aClass48_Sub1_4443 = (Class48_Sub1)class48;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.MH(" + ((class48 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method1887(final Interface16 anInterface16_4374, final int n) {
        try {
            if (this.anInterface16_4374 != anInterface16_4374) {
                if (this.aBoolean4449) {
                    OpenGL.glBindBufferARB(34962, anInterface16_4374.method53(-14112));
                }
                this.anInterface16_4374 = anInterface16_4374;
            }
            if (n != 34962) {
                this.method1860((byte)(-89));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.MC(" + ((anInterface16_4374 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final void method1888(final int anInt4409, final int n, final int anInt4410) {
        try {
            this.anInt4409 = anInt4409;
            this.anInt4415 = anInt4410;
            this.method1844((byte)55);
            this.method1828((byte)103);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.SC(" + anInt4409 + ',' + n + ',' + anInt4410 + ')');
        }
    }
    
    @Override
    final void za(final int n, final int n2, int n3, final int n4, final int n5) {
        try {
            if (n3 < 0) {
                n3 = -n3;
            }
            if (~(n3 + n) <= ~this.anInt4370 && this.anInt4432 >= -n3 + n && this.anInt4402 <= n2 + n3 && n2 - n3 <= this.anInt4414) {
                this.method1859(-51);
                this.method1870((byte)(-23), n5);
                OpenGL.glColor4ub((byte)(n4 >> 2073758448), (byte)(n4 >> -1643739512), (byte)n4, (byte)(n4 >> -1884862856));
                final float n6 = n + 0.35f;
                final float n7 = n2 + 0.35f;
                final int n8 = n3 << -2109983999;
                if (n8 < this.aFloat4429) {
                    OpenGL.glBegin(7);
                    OpenGL.glVertex2f(1.0f + n6, 1.0f + n7);
                    OpenGL.glVertex2f(n6 + 1.0f, -1.0f + n7);
                    OpenGL.glVertex2f(-1.0f + n6, n7 - 1.0f);
                    OpenGL.glVertex2f(n6 - 1.0f, n7 + 1.0f);
                    OpenGL.glEnd();
                }
                else if (this.aFloat4400 < n8) {
                    OpenGL.glBegin(6);
                    OpenGL.glVertex2f(n6, n7);
                    int n9 = 262144 / (6 * n3);
                    if (n9 <= 64) {
                        n9 = 64;
                    }
                    else if (n9 > 512) {
                        n9 = 512;
                    }
                    final int method282 = Class23.method282(73, n9);
                    OpenGL.glVertex2f(n6 + n3, n7);
                    for (int i = -method282 + 16384; i > 0; i -= method282) {
                        OpenGL.glVertex2f(n6 + Class98_Sub10_Sub36.aFloatArray5741[i] * n3, Class98_Sub10_Sub36.aFloatArray5742[i] * n3 + n7);
                    }
                    OpenGL.glVertex2f(n3 + n6, n7);
                    OpenGL.glEnd();
                }
                else {
                    OpenGL.glEnable(2832);
                    OpenGL.glPointSize(n8);
                    OpenGL.glBegin(0);
                    OpenGL.glVertex2f(n6, n7);
                    OpenGL.glEnd();
                    OpenGL.glDisable(2832);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.za(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    @Override
    final void method1740(final Interface17 interface17) {
    }
    
    final void method1889(final boolean b) {
        try {
            if (~this.anInt4350 != 0xFFFFFFFB) {
                this.method1877((byte)(-82));
                this.method1856(false, 6914);
                this.method1851(false, false);
                this.method1881(false, b);
                this.method1875((byte)18, false);
                this.method1834(124, -2);
                this.method1870((byte)(-72), 1);
                this.anInt4350 = 4;
            }
            if (b) {
                this.anInt4387 = -6;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.LB(" + b + ')');
        }
    }
    
    final void method1890(final float aFloat4452, final boolean b) {
        try {
            if (!b) {
                this.X(112);
            }
            if (this.aFloat4452 != aFloat4452) {
                this.aFloat4452 = aFloat4452;
                if (this.anInt4385 == 3) {
                    this.method1884((byte)87);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.AE(" + aFloat4452 + ',' + b + ')');
        }
    }
    
    @Override
    final boolean method1747() {
        try {
            return this.aClass55_4314.method507(3, -6634);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.HJ()");
        }
    }
    
    private final void method1891(final int n) {
        try {
            if (n != 370914608) {
                this.b(96, -106, -101, 12, 0.5374041586352528);
            }
            if (this.aBoolean4422) {
                OpenGL.glMatrixMode(5890);
                OpenGL.glLoadIdentity();
                OpenGL.glMatrixMode(5888);
                this.aBoolean4422 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.GC(" + n + ')');
        }
    }
    
    @Override
    final void Q(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final byte[] array, final int n7, final int n8) {
        try {
            float aFloat6205 = 0.0f;
            float aFloat6206 = 0.0f;
            Label_0157: {
                if (this.aClass42_Sub1_Sub1_4444 != null && this.aClass42_Sub1_Sub1_4444.anInt5355 >= n3 && n4 <= this.aClass42_Sub1_Sub1_4444.anInt5352) {
                    this.aClass42_Sub1_Sub1_4444.method378(n3, 6406, false, 0, array, 0, (byte)(-80), 0, 0, n4);
                    aFloat6205 = this.aClass42_Sub1_Sub1_4444.aFloat6205 * n3 / this.aClass42_Sub1_Sub1_4444.anInt5355;
                    aFloat6206 = n4 * this.aClass42_Sub1_Sub1_4444.aFloat6209 / this.aClass42_Sub1_Sub1_4444.anInt5352;
                    if (!client.aBoolean3553) {
                        break Label_0157;
                    }
                }
                (this.aClass42_Sub1_Sub1_4444 = Class284_Sub2.method3374(6406, n4, 14764, 6406, this, false, array, n3)).method383(false, 10242, false);
                aFloat6206 = this.aClass42_Sub1_Sub1_4444.aFloat6209;
                aFloat6205 = this.aClass42_Sub1_Sub1_4444.aFloat6205;
            }
            this.method1829((byte)(-95));
            this.method1863(1, this.aClass42_Sub1_Sub1_4444);
            this.method1870((byte)(-106), n8);
            OpenGL.glColor4ub((byte)(n5 >> -1114409264), (byte)(n5 >> 1074215208), (byte)n5, (byte)(n5 >> 1619670840));
            this.method1882(n6, -126);
            this.method1899(34165, 8960, 34165);
            this.method1840(0, 768, 109, 34166);
            this.method1840(2, 770, -72, 5890);
            this.method1886(770, 0, 34200, 34166);
            this.method1886(770, 2, 34200, 5890);
            final float n9 = n;
            final float n10 = n2;
            final float n11 = n9 + n3;
            final float n12 = n4 + n10;
            OpenGL.glBegin(7);
            OpenGL.glTexCoord2f(0.0f, 0.0f);
            OpenGL.glVertex2f(n9, n10);
            OpenGL.glTexCoord2f(0.0f, aFloat6205);
            OpenGL.glVertex2f(n9, n12);
            OpenGL.glTexCoord2f(aFloat6206, aFloat6205);
            OpenGL.glVertex2f(n11, n12);
            OpenGL.glTexCoord2f(aFloat6206, 0.0f);
            OpenGL.glVertex2f(n11, n10);
            OpenGL.glEnd();
            this.method1840(0, 768, 103, 5890);
            this.method1840(2, 770, 74, 34166);
            this.method1886(770, 0, 34200, 5890);
            this.method1886(770, 2, 34200, 34166);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.Q(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + ((array != null) ? "{...}" : "null") + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    @Override
    final void method1756() {
        try {
            if (this.aClass98_Sub28_Sub1_4311 != null && this.aClass98_Sub28_Sub1_4311.method1300(0)) {
                this.aClass283_4312.method3341(this.aClass98_Sub28_Sub1_4311, -17722);
                this.aClass364_4308.method3934((byte)100);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.PC()");
        }
    }
    
    final int method1892(final int n, final int n2) {
        try {
            if (n2 != 596294056) {
                return -116;
            }
            if (~n == 0xFFFFFFFE) {
                return 7681;
            }
            if (n == 0) {
                return 8448;
            }
            if (n == 2) {
                return 34165;
            }
            if (~n == 0xFFFFFFFC) {
                return 260;
            }
            if (n == 4) {
                return 34023;
            }
            throw new IllegalArgumentException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.TB(" + n + ',' + n2 + ')');
        }
    }
    
    private final void method1893(final int n) {
        try {
            Label_0038: {
                if (!this.aBoolean4373 || ~this.anInt4441 > -1) {
                    OpenGL.glDisable(2912);
                    if (!client.aBoolean3553) {
                        break Label_0038;
                    }
                }
                OpenGL.glEnable(2912);
            }
            if (n <= 105) {
                this.method1866(-33, -87);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.ED(" + n + ')');
        }
    }
    
    @Override
    final void method1782(final Canvas canvas, final int n, final int n2) {
        try {
            long n3 = 0L;
            Label_0061: {
                if (canvas == null || this.aCanvas4303 == canvas) {
                    n3 = this.aLong4307;
                    if (!client.aBoolean3553) {
                        break Label_0061;
                    }
                }
                if (this.aHashtable4306.containsKey(canvas)) {
                    n3 = this.aHashtable4306.get(canvas);
                }
            }
            if (n3 == 0L) {
                throw new RuntimeException();
            }
            this.anOpenGL4298.surfaceResized(n3);
            if (this.aCanvas4300 == canvas) {
                this.method1850((byte)(-31));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.JI(" + ((canvas != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    private final void method1894(final byte b) {
        try {
            this.aFloatArray4440[10] = this.aFloat4371;
            this.aFloatArray4440[14] = this.aFloat4416;
            this.aFloat4401 = (-this.anInt4389 + this.aFloatArray4440[14]) / this.aFloatArray4440[10];
            this.aFloat4379 = this.anInt4389;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.BE(" + b + ')');
        }
    }
    
    static final void method1895(final int n, final byte b, final String aString6052) {
        try {
            final Class98_Sub46_Sub17 method2628 = Class185.method2628(n, -76, 3);
            if (b < -38) {
                method2628.method1626((byte)(-103));
                method2628.aString6052 = aString6052;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.OD(" + n + ',' + b + ',' + ((aString6052 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final int method1822() {
        try {
            return 4;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.LG()");
        }
    }
    
    @Override
    final void ra(final int anInt4362, final int anInt4363, final int anInt4364, final int anInt4365) {
        try {
            this.anInt4362 = anInt4362;
            this.anInt4454 = anInt4364;
            this.aBoolean4366 = true;
            this.anInt4423 = anInt4363;
            this.anInt4453 = anInt4365;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.ra(" + anInt4362 + ',' + anInt4363 + ',' + anInt4364 + ',' + anInt4365 + ')');
        }
    }
    
    final void method1896(final int n, final int n2) {
        try {
            if (~n2 == 0xFFFFFFFE) {
                this.method1899(7681, 8960, 7681);
            }
            else if (n2 == 0) {
                this.method1899(8448, 8960, 8448);
            }
            else if (n2 != 2) {
                if (~n2 != 0xFFFFFFFC) {
                    if (n2 == 4) {
                        this.method1899(34023, 8960, 34023);
                    }
                }
                else {
                    this.method1899(8448, 8960, 260);
                }
            }
            else {
                this.method1899(7681, n + 8700, 34165);
            }
            if (n != 260) {
                this.aClass148_4341 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.WC(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method1791(final float aFloat6257, final float aFloat6258, final float aFloat6259) {
        try {
            Class246_Sub3_Sub3_Sub1.aFloat6257 = aFloat6257;
            Class313.aFloat2680 = aFloat6259;
            Class177.aFloat1378 = aFloat6258;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.TD(" + aFloat6257 + ',' + aFloat6258 + ',' + aFloat6259 + ')');
        }
    }
    
    @Override
    final void method1814() {
        try {
            this.aClass283_4312.method3342(-121);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.RD()");
        }
    }
    
    final void method1897(final int anInt4357, final boolean b, final boolean b2, final byte b3) {
        try {
            if (~anInt4357 != ~this.anInt4357 || !this.aBoolean4448 == this.aBoolean4366) {
                Class42 method3931 = null;
                int anInt4358 = 0;
                int aByte1816 = 0;
                int anInt4359 = 0;
                int aByte1817 = this.aBoolean4366 ? 3 : 0;
                if (anInt4357 < 0) {
                    this.method1891(370914608);
                }
                else {
                    method3931 = this.aClass364_4308.method3931(b3 + 196, anInt4357);
                    final Class238 method3932 = super.aD938.method11(anInt4357, -28755);
                    if (~method3932.aByte1823 != -1 || method3932.aByte1837 != 0) {
                        final int n = 50 * (method3932.aBoolean1822 ? 64 : 128);
                        this.method1857(this.anInt4321 % n * method3932.aByte1823 / n, 0.0f, (byte)44, method3932.aByte1837 * (this.anInt4321 % n) / n);
                    }
                    else {
                        this.method1891(370914608);
                    }
                    anInt4358 = method3932.anInt1821;
                    if (!this.aBoolean4366) {
                        aByte1817 = method3932.aByte1820;
                        anInt4359 = method3932.anInt1835;
                        aByte1816 = method3932.aByte1816;
                    }
                }
                this.aClass55_4314.method508(b2, b, anInt4359, aByte1816, true, aByte1817);
                if (!this.aClass55_4314.method509(method3931, false, anInt4358)) {
                    this.method1863(1, method3931);
                    this.method1896(260, anInt4358);
                }
                this.anInt4357 = anInt4357;
                this.aBoolean4448 = this.aBoolean4366;
            }
            if (b3 != -70) {
                this.U(4, 29, -13, 16, 49);
            }
            this.anInt4350 &= 0xFFFFFFF8;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.OC(" + anInt4357 + ',' + b + ',' + b2 + ',' + b3 + ')');
        }
    }
    
    final void method1898(final boolean b, final Interface12 interface12) {
        try {
            if (!b) {
                this.method1893(-128);
            }
            if (!this.aBoolean4375) {
                if (~this.anInt4331 <= -4) {
                    throw new RuntimeException();
                }
                if (this.anInt4331 >= 0) {
                    this.anInterface12Array4332[this.anInt4331].method35((byte)69);
                }
                this.anInterface12Array4332[++this.anInt4331] = interface12;
                this.anInterface12_4328 = interface12;
                (this.anInterface12_4325 = interface12).method39((byte)122);
            }
            else {
                this.method1880(127, interface12);
                this.method1835(interface12, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.QB(" + b + ',' + ((interface12 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method1899(final int anInt4387, final int n, final int anInt4388) {
        try {
            Label_0118: {
                if (this.anInt4417 == 0) {
                    boolean b = false;
                    if (~this.anInt4395 != ~anInt4388) {
                        OpenGL.glTexEnvi(8960, 34161, anInt4388);
                        b = true;
                        this.anInt4395 = anInt4388;
                    }
                    if (~anInt4387 != ~this.anInt4387) {
                        OpenGL.glTexEnvi(8960, 34162, anInt4387);
                        this.anInt4387 = anInt4387;
                        b = true;
                    }
                    if (!b) {
                        break Label_0118;
                    }
                    this.anInt4350 &= 0xFFFFFFE2;
                    if (!client.aBoolean3553) {
                        break Label_0118;
                    }
                }
                OpenGL.glTexEnvi(8960, 34161, anInt4388);
                OpenGL.glTexEnvi(8960, 34162, anInt4387);
            }
            if (n != 8960) {
                this.aClass283_4312 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.AA(" + anInt4387 + ',' + n + ',' + anInt4388 + ')');
        }
    }
    
    private final void method1900(final byte b) {
        try {
            Class115.aFloatArray961[3] = 1.0f;
            Class115.aFloatArray961[0] = this.aFloat4433 * this.aFloat4435;
            Class115.aFloatArray961[1] = this.aFloat4420 * this.aFloat4435;
            Class115.aFloatArray961[2] = this.aFloat4458 * this.aFloat4435;
            OpenGL.glLightfv(16384, 4609, Class115.aFloatArray961, 0);
            Class115.aFloatArray961[2] = this.aFloat4458 * -this.aFloat4407;
            Class115.aFloatArray961[3] = 1.0f;
            Class115.aFloatArray961[0] = this.aFloat4433 * -this.aFloat4407;
            Class115.aFloatArray961[1] = -this.aFloat4407 * this.aFloat4420;
            if (b <= 64) {
                this.f(-28, 34);
            }
            OpenGL.glLightfv(16385, 4609, Class115.aFloatArray961, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.IA(" + b + ')');
        }
    }
    
    final void method1901(final byte b) {
        try {
            if (~this.anInt4350 != 0xFFFFFFEF) {
                this.method1874(b + 5);
                this.method1856(true, 6914);
                this.method1881(true, false);
                this.method1875((byte)(-123), true);
                this.method1870((byte)(-33), 1);
                this.anInt4350 = 16;
            }
            if (b != -35) {
                this.aFloatArray4463 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.GE(" + b + ')');
        }
    }
    
    @Override
    final void method1807(final int n) {
        try {
            this.method1854(-30);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.KJ(" + n + ')');
        }
    }
    
    @Override
    final void method1811(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, int n9) {
        try {
            if (~n != ~n3 || ~n4 != ~n2) {
                this.method1859(-113);
                this.method1870((byte)(-126), n6);
                final float n10 = -n + n3;
                final float n11 = -n2 + n4;
                final float n12 = (float)(1.0 / Math.sqrt(n11 * n11 + n10 * n10));
                final float n13 = n11 * n12;
                final float n14 = n10 * n12;
                OpenGL.glColor4ub((byte)(n5 >> -84381840), (byte)(n5 >> -980470872), (byte)n5, (byte)(n5 >> 859356024));
                n9 %= n8 + n7;
                final float n15 = n14 * n7;
                final float n16 = n13 * n7;
                float n17 = 0.0f;
                float n18 = 0.0f;
                float n19 = n15;
                float n20 = n16;
                if (n9 <= n7) {
                    n20 = n13 * (-n9 + n7);
                    n19 = (-n9 + n7) * n14;
                }
                else {
                    n17 = n14 * (n8 + n7 + -n9);
                    n18 = (-n9 + (n7 + n8)) * n13;
                }
                float n21 = n + 0.35f + n17;
                float n22 = n18 + (n2 + 0.35f);
                final float n23 = n8 * n14;
                final float n24 = n8 * n13;
                while (true) {
                    if (n3 > n) {
                        if (n21 > 0.35f + n3) {
                            break;
                        }
                        if (n3 < n21 + n19) {
                            n19 = -n21 + n3;
                        }
                    }
                    else {
                        if (0.35f + n3 > n21) {
                            break;
                        }
                        if (n3 > n21 + n19) {
                            n19 = -n21 + n3;
                        }
                    }
                    if (~n4 < ~n2) {
                        if (0.35f + n4 < n22) {
                            break;
                        }
                        if (n4 < n20 + n22) {
                            n20 = -n22 + n4;
                        }
                    }
                    else {
                        if (n4 + 0.35f > n22) {
                            break;
                        }
                        if (n22 + n20 < n4) {
                            n20 = -n22 + n4;
                        }
                    }
                    OpenGL.glBegin(1);
                    OpenGL.glVertex2f(n21, n22);
                    OpenGL.glVertex2f(n19 + n21, n20 + n22);
                    OpenGL.glEnd();
                    n22 += n24 + n20;
                    n21 += n19 + n23;
                    n19 = n15;
                    n20 = n16;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.CF(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    final void method1902(final byte b) {
        try {
            if (b != 60) {
                this.aBoolean4422 = true;
            }
            OpenGL.glPopMatrix();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.NB(" + b + ')');
        }
    }
    
    @Override
    final void b(final int n, final int n2, final int n3, final int n4, final double n5) {
    }
    
    private final void method1903(final int n) {
        try {
            this.aClass42Array4396 = new Class42[this.anInt4410];
            this.aClass42_Sub1_4358 = new Class42_Sub1(this, 3553, 6408, 1, 1);
            new Class42_Sub1(this, 3553, 6408, 1, 1);
            new Class42_Sub1(this, 3553, 6408, 1, 1);
            this.aClass146_Sub2_4428 = new Class146_Sub2(this);
            this.aClass146_Sub2_4393 = new Class146_Sub2(this);
            this.aClass146_Sub2_4462 = new Class146_Sub2(this);
            this.aClass146_Sub2_4456 = new Class146_Sub2(this);
            this.aClass146_Sub2_4386 = new Class146_Sub2(this);
            this.aClass146_Sub2_4369 = new Class146_Sub2(this);
            this.aClass146_Sub2_4461 = new Class146_Sub2(this);
            if (n <= 26) {
                this.b(113, -33, -15, 84, -0.18123041621142552);
            }
            this.aClass146_Sub2_4361 = new Class146_Sub2(this);
            this.aClass146_Sub2_4355 = new Class146_Sub2(this);
            this.aClass146_Sub2_4382 = new Class146_Sub2(this);
            if (this.aBoolean4460) {
                this.aClass288_4363 = new Class288(this);
                new Class288(this);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.TE(" + n + ')');
        }
    }
    
    @Override
    final int method1777(final int n, final int n2) {
        try {
            return n | n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.HF(" + n + ',' + n2 + ')');
        }
    }
    
    private final void method1904(final int n) {
        try {
            if (this.anInt4385 != 2) {
                this.anInt4385 = 2;
                this.method1885((byte)(-47));
                this.method1833(4);
                this.anInt4350 &= 0xFFFFFFF8;
            }
            if (n > -3) {
                this.aFloat4371 = 0.12732868f;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.FC(" + n + ')');
        }
    }
    
    @Override
    final void a(final Class111 class111) {
        try {
            this.aClass111_Sub1_4348.method2092(class111);
            this.aClass111_Sub1_4353.method2092(this.aClass111_Sub1_4348);
            this.aClass111_Sub1_4353.method2111(2);
            this.aClass111_Sub1_4354.method2112(this.aClass111_Sub1_4353, (byte)(-118));
            if (~this.anInt4385 != 0xFFFFFFFE) {
                this.method1833(4);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.QE(" + ((class111 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void X(int n) {
        try {
            this.anInt4319 = 0;
            while (~n < -2) {
                ++this.anInt4319;
                n >>= 1;
            }
            this.anInt4318 = 1 << this.anInt4319;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.X(" + n + ')');
        }
    }
    
    @Override
    final Class98_Sub5 method1765(final int n, final int n2, final int n3, final int n4, final int n5, final float n6) {
        try {
            return new Class98_Sub5_Sub2(n, n2, n3, n4, n5, n6);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.HG(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    @Override
    final void C(final boolean aBoolean4465) {
        try {
            this.aBoolean4465 = aBoolean4465;
            this.method1876((byte)(-75));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.C(" + aBoolean4465 + ')');
        }
    }
    
    @Override
    final void xa(final float aFloat4413) {
        try {
            if (this.aFloat4413 != aFloat4413) {
                this.aFloat4413 = aFloat4413;
                this.method1860((byte)(-41));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.xa(" + aFloat4413 + ')');
        }
    }
    
    @Override
    final void method1750(final Canvas aCanvas4300) {
        try {
            this.aCanvas4300 = null;
            this.aLong4302 = 0L;
            Label_0086: {
                if (aCanvas4300 != null && aCanvas4300 != this.aCanvas4303) {
                    if (!this.aHashtable4306.containsKey(aCanvas4300)) {
                        break Label_0086;
                    }
                    this.aLong4302 = this.aHashtable4306.get(aCanvas4300);
                    this.aCanvas4300 = aCanvas4300;
                    if (!client.aBoolean3553) {
                        break Label_0086;
                    }
                }
                this.aLong4302 = this.aLong4307;
                this.aCanvas4300 = this.aCanvas4303;
            }
            if (this.aCanvas4300 == null || ~this.aLong4302 == -1L) {
                throw new RuntimeException();
            }
            this.anOpenGL4298.setSurface(this.aLong4302);
            this.method1850((byte)(-31));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.DH(" + ((aCanvas4300 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void HA(final int n, final int n2, final int n3, final int n4, final int[] array) {
        try {
            final float n5 = this.aClass111_Sub1_4348.aFloat4677 + (this.aClass111_Sub1_4348.aFloat4673 * n3 + (n * this.aClass111_Sub1_4348.aFloat4684 + this.aClass111_Sub1_4348.aFloat4676 * n2));
            if (n5 < this.anInt4404 || n5 > this.anInt4389) {
                final int n6 = 0;
                final int n7 = 1;
                final int n8 = 2;
                final int n9 = -1;
                array[n8] = n9;
                array[n6] = (array[n7] = n9);
            }
            else {
                final int n10 = (int)((this.aClass111_Sub1_4348.aFloat4674 + (n3 * this.aClass111_Sub1_4348.aFloat4680 + (this.aClass111_Sub1_4348.aFloat4686 * n + n2 * this.aClass111_Sub1_4348.aFloat4679))) * this.anInt4419 / n4);
                final int n11 = (int)((this.aClass111_Sub1_4348.aFloat4683 + (n2 * this.aClass111_Sub1_4348.aFloat4675 + n * this.aClass111_Sub1_4348.aFloat4678 + n3 * this.aClass111_Sub1_4348.aFloat4687)) * this.anInt4381 / n4);
                if (n10 >= this.aFloat4421 && this.aFloat4364 >= n10 && n11 >= this.aFloat4359 && n11 <= this.aFloat4437) {
                    array[2] = (int)n5;
                    array[1] = (int)(-this.aFloat4359 + n11);
                    array[0] = (int)(n10 - this.aFloat4421);
                }
                else {
                    final int n12 = 0;
                    final int n13 = 1;
                    final int n14 = 2;
                    final int n15 = -1;
                    array[n14] = n15;
                    array[n12] = (array[n13] = n15);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.HA(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method1905(final boolean aBoolean4405, final int n) {
        try {
            if (n != 0) {
                this.aClass98_Sub5Array4408 = null;
            }
            if (aBoolean4405 == !this.aBoolean4405) {
                this.aBoolean4405 = aBoolean4405;
                this.method1826(2896);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.AC(" + aBoolean4405 + ',' + n + ')');
        }
    }
    
    @Override
    final boolean method1788() {
        try {
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.KI()");
        }
    }
    
    private final void method1906(final int n) {
        try {
            this.aFloat4359 = this.anInt4402 - this.anInt4394;
            this.aFloat4421 = this.anInt4370 - this.anInt4451;
            this.aFloat4437 = this.anInt4414 + -this.anInt4394;
            this.aFloat4364 = this.anInt4432 + -this.anInt4451;
            if (n != 4353) {
                this.anInterface16_4374 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.HB(" + n + ')');
        }
    }
    
    final void method1907(final Interface12 interface12, final int n) {
        try {
            if (n == -1) {
                if (!this.aBoolean4375) {
                    if (~this.anInt4331 > -1 || interface12 != this.anInterface12Array4332[this.anInt4331]) {
                        throw new RuntimeException();
                    }
                    this.anInterface12Array4332[this.anInt4331--] = null;
                    interface12.method35((byte)69);
                    if (~this.anInt4331 > -1) {
                        final Interface12 interface13 = null;
                        this.anInterface12_4328 = interface13;
                        this.anInterface12_4325 = interface13;
                    }
                    else {
                        final Interface12 interface14 = this.anInterface12Array4332[this.anInt4331];
                        this.anInterface12_4328 = interface14;
                        (this.anInterface12_4325 = interface14).method39((byte)110);
                    }
                }
                else {
                    this.method1832(interface12, 88);
                    this.method1869(0, interface12);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.KB(" + ((interface12 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final aa aa, final int n7, final int n8) {
        try {
            final aa_Sub3 aa_Sub3 = (aa_Sub3)aa;
            final Class42_Sub1_Sub1 aClass42_Sub1_Sub1_3568 = aa_Sub3.aClass42_Sub1_Sub1_3568;
            this.method1829((byte)(-99));
            this.method1863(1, aa_Sub3.aClass42_Sub1_Sub1_3568);
            this.method1870((byte)(-109), n6);
            this.method1899(8448, 8960, 7681);
            this.method1840(0, 768, 90, 34167);
            final float n9 = aClass42_Sub1_Sub1_3568.aFloat6205 / aClass42_Sub1_Sub1_3568.anInt6207;
            final float n10 = aClass42_Sub1_Sub1_3568.aFloat6209 / aClass42_Sub1_Sub1_3568.anInt6204;
            final float n11 = n3 - n;
            final float n12 = n4 - n2;
            final float n13 = (float)(1.0 / Math.sqrt(n11 * n11 + n12 * n12));
            OpenGL.glColor4ub((byte)(n5 >> 99388688), (byte)(n5 >> 1644719592), (byte)n5, (byte)(n5 >> -1606324584));
            final float n14 = n12 * n13;
            final float n15 = n11 * n13;
            OpenGL.glBegin(1);
            OpenGL.glTexCoord2f(n9 * (-n7 + n), (n2 + -n8) * n10);
            OpenGL.glVertex2f(0.35f + n, n2 + 0.35f);
            OpenGL.glTexCoord2f(n9 * (n3 + -n7), (-n8 + n4) * n10);
            OpenGL.glVertex2f(n3 + n15 + 0.35f, 0.35f + (n14 + n4));
            OpenGL.glEnd();
            this.method1840(0, 768, -52, 5890);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.MJ(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + ((aa != null) ? "{...}" : "null") + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    @Override
    final Class111 method1793() {
        try {
            return this.aClass111_Sub1_4315;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.RI()");
        }
    }
    
    final void method1908(final boolean b, final int n, final int n2) {
        try {
            if (n > -61) {
                this.X(-111);
            }
            this.method1897(n2, true, b, (byte)(-70));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.JB(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final boolean method1768() {
        try {
            return this.aClass98_Sub28_Sub1_4311 != null && this.aClass98_Sub28_Sub1_4311.method1300(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.CB()");
        }
    }
    
    final void method1909(final int n, final Class111_Sub1 class111_Sub1) {
        try {
            OpenGL.glLoadMatrixf(class111_Sub1.method2113(-108), 0);
            if (n != -32076) {
                this.method1779(110, 43, -54, 86, -102, -54);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.CA(" + n + ',' + ((class111_Sub1 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method1751(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13) {
        try {
            this.method1859(-100);
            this.method1870((byte)(-127), n13);
            OpenGL.glBegin(4);
            OpenGL.glColor4ub((byte)(n10 >> 1405670544), (byte)(n10 >> 847872872), (byte)n10, (byte)(n10 >> -567715176));
            OpenGL.glVertex3f(n + 0.35f, 0.35f + n2, n3);
            OpenGL.glColor4ub((byte)(n11 >> 1700327632), (byte)(n11 >> 601299240), (byte)n11, (byte)(n11 >> 840925496));
            OpenGL.glVertex3f(n4 + 0.35f, n5 + 0.35f, n6);
            OpenGL.glColor4ub((byte)(n12 >> -2126717264), (byte)(n12 >> 984802728), (byte)n12, (byte)(n12 >> -382114632));
            OpenGL.glVertex3f(n7 + 0.35f, 0.35f + n8, n9);
            OpenGL.glEnd();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.OH(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ',' + n11 + ',' + n12 + ',' + n13 + ')');
        }
    }
    
    final void method1910(final int n, final int n2, final boolean b, final int n3) {
        try {
            OpenGL.glDrawArrays(n, n3, n2);
            if (b) {
                this.method1800();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ac.R(" + n + ',' + n2 + ',' + b + ',' + n3 + ')');
        }
    }
    
    static {
        ha_Sub1.aClass85_4299 = new Class85(2, -1);
    }
}
