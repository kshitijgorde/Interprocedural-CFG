import java.awt.Dimension;
import java.util.Enumeration;
import jaclib.memory.Buffer;
import jaclib.memory.Stream;
import jagex3.graphics2.hw.NativeInterface;
import java.awt.Canvas;
import java.util.Hashtable;
import jaclib.memory.heap.NativeHeap;
import jaclib.memory.heap.NativeHeapBuffer;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class ha_Sub3 extends ha
{
    NativeHeapBuffer aNativeHeapBuffer4521;
    NativeHeap aNativeHeap4522;
    private Hashtable aHashtable4523;
    private Canvas aCanvas4524;
    long aLong4525;
    NativeInterface aNativeInterface4526;
    int anInt4527;
    static Class207 aClass207_4528;
    Class195 aClass195_4529;
    Object anObject4530;
    int anInt4531;
    private Class148 aClass148_4532;
    Canvas aCanvas4533;
    private int anInt4534;
    Class207 aClass207_4535;
    private int anInt4536;
    private Object anObject4537;
    int anInt4538;
    int anInt4539;
    boolean aBoolean4540;
    int anInt4541;
    Class111_Sub3 aClass111_Sub3_4542;
    Class111_Sub3 aClass111_Sub3_4543;
    Class111_Sub3 aClass111_Sub3_4544;
    Class111_Sub3 aClass111_Sub3_4545;
    private Class111_Sub3 aClass111_Sub3_4546;
    private Class111_Sub3 aClass111_Sub3_4547;
    private int anInt4548;
    float aFloat4549;
    private boolean aBoolean4550;
    Class204 aClass204_4551;
    int anInt4552;
    int anInt4553;
    private float aFloat4554;
    boolean aBoolean4555;
    int anInt4556;
    private int anInt4557;
    int anInt4558;
    boolean aBoolean4559;
    boolean aBoolean4560;
    private float aFloat4561;
    boolean aBoolean4562;
    boolean aBoolean4563;
    int anInt4564;
    int anInt4565;
    float[] aFloatArray4566;
    private boolean aBoolean4567;
    float aFloat4568;
    boolean aBoolean4569;
    private boolean aBoolean4570;
    boolean aBoolean4571;
    float[] aFloatArray4572;
    int anInt4573;
    private boolean aBoolean4574;
    int anInt4575;
    float aFloat4576;
    boolean aBoolean4577;
    int anInt4578;
    int anInt4579;
    int anInt4580;
    int anInt4581;
    private float[] aFloatArray4582;
    private int anInt4583;
    float aFloat4584;
    Class128[] aClass128Array4585;
    Interface4 anInterface4_4586;
    int anInt4587;
    boolean aBoolean4588;
    int anInt4589;
    private float[] aFloatArray4590;
    float aFloat4591;
    float aFloat4592;
    int anInt4593;
    float aFloat4594;
    private int anInt4595;
    float[] aFloatArray4596;
    Class98_Sub5[] aClass98_Sub5Array4597;
    private Class76 aClass76_4598;
    boolean aBoolean4599;
    private int anInt4600;
    int anInt4601;
    int anInt4602;
    private Stream aStream4603;
    private float[] aFloatArray4604;
    int anInt4605;
    boolean aBoolean4606;
    int anInt4607;
    int anInt4608;
    Class111_Sub3[] aClass111_Sub3Array4609;
    float aFloat4610;
    float aFloat4611;
    private int anInt4612;
    private Class76[] aClass76Array4613;
    private Class48_Sub2 aClass48_Sub2_4614;
    float aFloat4615;
    int anInt4616;
    private Interface4[] anInterface4Array4617;
    private int anInt4618;
    int anInt4619;
    boolean aBoolean4620;
    private boolean aBoolean4621;
    int anInt4622;
    private int anInt4623;
    private Class319 aClass319_4624;
    Class126 aClass126_4625;
    private float aFloat4626;
    private float[] aFloatArray4627;
    int anInt4628;
    private float[] aFloatArray4629;
    float aFloat4630;
    private boolean aBoolean4631;
    private int anInt4632;
    private int anInt4633;
    private Class81 aClass81_4634;
    private int anInt4635;
    int anInt4636;
    boolean aBoolean4637;
    int anInt4638;
    Class128[] aClass128Array4639;
    int anInt4640;
    float aFloat4641;
    float aFloat4642;
    boolean aBoolean4643;
    Class258[] aClass258Array4644;
    int anInt4645;
    private float[] aFloatArray4646;
    float aFloat4647;
    int anInt4648;
    Class146_Sub3 aClass146_Sub3_4649;
    Class146_Sub3 aClass146_Sub3_4650;
    Class146_Sub3 aClass146_Sub3_4651;
    private Interface2_Impl1 anInterface2_Impl1_4652;
    private Class256 aClass256_4653;
    Class256 aClass256_4654;
    private Class256 aClass256_4655;
    Class146_Sub3 aClass146_Sub3_4656;
    Class146_Sub3 aClass146_Sub3_4657;
    Class146_Sub3 aClass146_Sub3_4658;
    Class256 aClass256_4659;
    private Interface2_Impl1 anInterface2_Impl1_4660;
    Class146_Sub3 aClass146_Sub3_4661;
    Class146_Sub3 aClass146_Sub3_4662;
    private Class111_Sub3 aClass111_Sub3_4663;
    Class256 aClass256_4664;
    Class146_Sub3 aClass146_Sub3_4665;
    private Interface2_Impl2 anInterface2_Impl2_4666;
    private Interface2_Impl1 anInterface2_Impl1_4667;
    Class256 aClass256_4668;
    private Class256 aClass256_4669;
    Class146_Sub3 aClass146_Sub3_4670;
    private int anInt4671;
    boolean aBoolean4672;
    
    private final void method1933(final byte b) {
        try {
            if (b != -94) {
                this.anInt4601 = 32;
            }
            if (!this.aBoolean4631) {
                final float[] aFloatArray4646 = this.aFloatArray4646;
                final float n = this.anInt4640 * -this.anInt4645 / this.anInt4593;
                final float n2 = (-this.anInt4645 + this.anInt4527) * this.anInt4640 / this.anInt4593;
                final float n3 = this.anInt4587 * this.anInt4640 / this.anInt4589;
                final float n4 = (this.anInt4587 + -this.anInt4531) * this.anInt4640 / this.anInt4589;
                Label_0373: {
                    if (n != n2 && n4 != n3) {
                        final float n5 = this.anInt4640 * 2.0f;
                        aFloatArray4646[13] = (aFloatArray4646[1] = 0.0f);
                        aFloatArray4646[11] = -1.0f;
                        aFloatArray4646[6] = 0.0f;
                        aFloatArray4646[5] = n5 / (n3 - n4);
                        aFloatArray4646[8] = (n + n2) / (-n + n2);
                        aFloatArray4646[0] = n5 / (n2 - n);
                        aFloatArray4646[4] = (aFloatArray4646[2] = 0.0f);
                        aFloatArray4646[9] = (n4 + n3) / (-n4 + n3);
                        aFloatArray4646[7] = 0.0f;
                        aFloatArray4646[14] = (this.aFloat4554 = this.anInt4640 * this.anInt4605 / (this.anInt4640 + -this.anInt4605));
                        aFloatArray4646[12] = (aFloatArray4646[3] = 0.0f);
                        aFloatArray4646[10] = (this.aFloat4626 = this.anInt4605 / (-this.anInt4605 + this.anInt4640));
                        aFloatArray4646[15] = 0.0f;
                        if (!client.aBoolean3553) {
                            break Label_0373;
                        }
                    }
                    aFloatArray4646[8] = (aFloatArray4646[13] = 0.0f);
                    aFloatArray4646[7] = (aFloatArray4646[2] = 0.0f);
                    aFloatArray4646[11] = (aFloatArray4646[3] = 0.0f);
                    aFloatArray4646[5] = 1.0f;
                    aFloatArray4646[1] = 0.0f;
                    aFloatArray4646[0] = 1.0f;
                    aFloatArray4646[12] = 0.0f;
                    aFloatArray4646[14] = (aFloatArray4646[9] = 0.0f);
                    aFloatArray4646[4] = (aFloatArray4646[6] = 0.0f);
                    aFloatArray4646[15] = (aFloatArray4646[10] = 1.0f);
                }
                this.method1970(-24793);
                this.aBoolean4631 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.LE(" + b + ')');
        }
    }
    
    @Override
    final void C(final boolean aBoolean4606) {
        try {
            this.aBoolean4606 = aBoolean4606;
            this.method1972(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.C(" + aBoolean4606 + ')');
        }
    }
    
    abstract Interface4_Impl3 method1934(final int p0, final boolean p1, final int[][] p2, final int p3);
    
    final void method1935(final int n) {
        try {
            this.aBoolean4540 = false;
            this.method2055(0);
            if (n != 1) {
                this.method1974(-121);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.LI(" + n + ')');
        }
    }
    
    abstract void method1936(final int p0, final Object p1, final Canvas p2);
    
    @Override
    final void T(final int anInt4602, final int anInt4603, final int anInt4604, final int anInt4605) {
        try {
            boolean b = false;
            if (anInt4602 > this.anInt4602) {
                b = true;
                this.anInt4602 = anInt4602;
            }
            if (~this.anInt4575 < ~anInt4604) {
                this.anInt4575 = anInt4604;
                b = true;
            }
            if (this.anInt4558 < anInt4603) {
                this.anInt4558 = anInt4603;
                b = true;
            }
            if (anInt4605 < this.anInt4638) {
                b = true;
                this.anInt4638 = anInt4605;
            }
            if (b) {
                if (!this.aBoolean4672) {
                    this.aBoolean4672 = true;
                    this.method2030((byte)(-122));
                }
                this.method2009(28976);
                this.method2048((byte)32);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.T(" + anInt4602 + ',' + anInt4603 + ',' + anInt4604 + ',' + anInt4605 + ')');
        }
    }
    
    final void method1937(final int n) {
        try {
            if (n != 4) {
                this.aFloat4626 = -0.15274778f;
            }
            if (this.anInt4633 != 16) {
                this.method1952(2);
                this.method2028(true, (byte)(-101));
                this.method2013(true, n + 2099);
                this.method1997(n ^ 0x4, true);
                this.method2001(1, 109);
                this.anInt4633 = 16;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.EF(" + n + ')');
        }
    }
    
    private final void method1938(final byte b) {
        try {
            if (Class176.aClass204_1372 != this.aClass204_4551) {
                final Class204 aClass204_4551 = this.aClass204_4551;
                this.aClass204_4551 = Class176.aClass204_1372;
                if (!aClass204_4551.method2708(94)) {
                    this.method1961(107);
                }
                this.method1933((byte)(-94));
                this.aFloatArray4566 = this.aFloatArray4646;
                this.method1962(-108);
                this.anInt4633 &= 0xFFFFFFF8;
            }
            if (b != -20) {
                this.anInt4640 = -76;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.BE(" + b + ')');
        }
    }
    
    final Interface4_Impl3 method1939(final int n) {
        try {
            if (n > -92) {
                return null;
            }
            if (this.aClass48_Sub2_4614 == null) {
                return null;
            }
            return this.aClass48_Sub2_4614.method469(109);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.EE(" + n + ')');
        }
    }
    
    @Override
    final void method1807(final int n) {
    }
    
    final void method1940(final byte b) {
        try {
            this.method1992((byte)125);
            this.method1962(-83);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.QG(" + b + ')');
        }
    }
    
    void method1941(final int n) {
        try {
            this.method1975(0);
            if (n != 1) {
                this.method1945((byte)115, false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.OC(" + n + ')');
        }
    }
    
    abstract boolean method1942(final int p0, final Class164 p1, final Class162 p2);
    
    private final void method1943(final int n) {
        try {
            this.aBoolean4631 = false;
            this.method1933((byte)(-94));
            if (n != 10886) {
                this.pa();
            }
            if (Class176.aClass204_1372 == this.aClass204_4551) {
                this.method1962(n ^ 0xFFFFD508);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.RG(" + n + ')');
        }
    }
    
    @Override
    final void method1774(final int n) {
    }
    
    @Override
    final void U(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            this.method1795(n, n2, n + n3, n2, n4, n5);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.U(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    abstract void method1944(final Object p0, final Canvas p1, final byte p2);
    
    final void method1945(final byte b, final boolean aBoolean4571) {
        try {
            if (aBoolean4571 == !this.aBoolean4571) {
                this.aBoolean4571 = aBoolean4571;
                this.method1999((byte)112);
            }
            if (b <= 107) {
                this.method1984(-94, 91);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.KF(" + b + ',' + aBoolean4571 + ')');
        }
    }
    
    private final void method1946(final int n) {
        try {
            if (n != this.anInt4633) {
                this.method1993((byte)103);
                this.method2028(false, (byte)(-101));
                this.method1979(false, -104);
                this.method2013(false, 2103);
                this.method1997(n - 1, false);
                this.method2039(false, n ^ 0x1, -2, false);
                this.method2015(1, (byte)40);
                this.method2005(this.anInterface4_4586, -128);
                this.anInt4633 = 1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.TE(" + n + ')');
        }
    }
    
    final NativeHeapBuffer method1947(final int n, final boolean b, final int n2) {
        try {
            if (n2 != 0) {
                return null;
            }
            return this.aNativeHeap4522.a(n, b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.JJ(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    @Override
    final void za(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            this.method1946(1);
            this.method1984(2, n4);
            this.method2051(0, -91, Class64_Sub16.aClass65_3681);
            this.method1953(-119, Class64_Sub16.aClass65_3681, 0);
            this.method2001(n5, 76);
            this.aClass111_Sub3_4542.method2137(n3, (byte)(-116), n3, 1.0f);
            this.aClass111_Sub3_4542.method2106(n, n2, 0);
            this.method1935(1);
            this.method2059(false, false);
            this.method1971(0, true, this.anInterface2_Impl1_4652);
            this.method2042(this.aClass256_4669, (byte)104);
            this.method2037(Class98_Sub46_Sub15.aClass232_6043, 0, (byte)13, 256);
            this.method2059(true, false);
            this.method1953(-108, Class300.aClass65_2499, 0);
            this.method2051(0, -72, Class300.aClass65_2499);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.za(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    final int method1948(final int n) {
        try {
            return this.anInt4548;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.UE(" + n + ')');
        }
    }
    
    final void method1949(final int n) {
        try {
            if (this.anInt4633 != 4) {
                this.method1993((byte)103);
                this.method2028(false, (byte)(-101));
                this.method1979(false, -65);
                this.method2013(false, 2103);
                this.method1997(0, false);
                this.method2039(false, 0, -2, false);
                this.method2001(1, 111);
                this.method2015(0, (byte)(-124));
                this.anInt4633 = 4;
            }
            if (n != 0) {
                this.aBoolean4643 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.AE(" + n + ')');
        }
    }
    
    abstract void method1950(final byte p0);
    
    final void method1951(final byte b, final int anInt4579) {
        try {
            if (b != 120) {
                this.aClass146_Sub3_4661 = null;
            }
            if (this.anInt4579 != anInt4579) {
                this.anInt4579 = anInt4579;
                this.method1950((byte)72);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.TI(" + b + ',' + anInt4579 + ')');
        }
    }
    
    private final void method1952(final int n) {
        try {
            if (this.aClass204_4551 != Class149.aClass204_1206) {
                final Class204 aClass204_4551 = this.aClass204_4551;
                this.aClass204_4551 = Class149.aClass204_1206;
                if (!aClass204_4551.method2708(103)) {
                    this.method1961(-43);
                }
                this.method2000((byte)(-117));
                this.aFloatArray4566 = this.aFloatArray4604;
                this.method1962(-84);
                this.anInt4633 &= 0xFFFFFFF8;
            }
            if (n != 2) {
                this.anInt4587 = 43;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.SG(" + n + ')');
        }
    }
    
    final void method1953(final int n, final Class65 class65, final int n2) {
        try {
            if (n <= -66) {
                this.method2047(n2, false, (byte)(-42), class65);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.WG(" + n + ',' + ((class65 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    final void method1954(final int n, final byte b) {
        try {
            this.method1984(2, b | (b << 26610024 | (b << 407779224 | b << -231365232)));
            if (n != 4) {
                this.method1823();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.LF(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final boolean method1788() {
        try {
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.KI()");
        }
    }
    
    @Override
    final void ra(final int anInt4600, final int anInt4601, final int anInt4602, final int anInt4603) {
        try {
            this.anInt4618 = anInt4603;
            this.aBoolean4563 = true;
            this.anInt4548 = anInt4602;
            this.anInt4557 = anInt4601;
            this.anInt4600 = anInt4600;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.ra(" + anInt4600 + ',' + anInt4601 + ',' + anInt4602 + ',' + anInt4603 + ')');
        }
    }
    
    abstract void method1955(final int p0);
    
    final Class111_Sub3 method1956(final byte b) {
        try {
            return this.aClass111_Sub3_4545;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.QH(" + b + ')');
        }
    }
    
    final Class111_Sub3 method1957(final byte b) {
        try {
            return this.aClass111_Sub3Array4609[this.anInt4579];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.NF(" + b + ')');
        }
    }
    
    @Override
    final Class332 method1739(final int n, final int n2, final boolean b) {
        try {
            return new Class332_Sub2(this, n, n2, b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.EG(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    @Override
    final void method1816(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
    }
    
    @Override
    final void f(final int anInt4640, final int anInt4641) {
        try {
            if (anInt4640 != this.anInt4640 || ~this.anInt4605 != ~anInt4641) {
                this.anInt4640 = anInt4640;
                this.anInt4605 = anInt4641;
                this.method1943(10886);
                this.method2038((byte)119);
                this.method2020(-111);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.f(" + anInt4640 + ',' + anInt4641 + ')');
        }
    }
    
    abstract void method1958(final byte p0);
    
    abstract void method1959(final int p0);
    
    @Override
    final void method1782(final Canvas canvas, final int n, final int n2) {
        try {
            Object o = null;
            if (canvas == null || canvas == this.aCanvas4533) {
                o = this.anObject4537;
            }
            else if (this.aHashtable4523.containsKey(canvas)) {
                o = this.aHashtable4523.get(canvas);
            }
            if (o == null) {
                throw new RuntimeException();
            }
            this.method1988(canvas, o, -117);
            if (canvas == this.aCanvas4524) {
                this.method2003(1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.JI(" + ((canvas != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    final void method1960(final int n) {
        try {
            this.aClass111_Sub3_4542.method2091();
            if (n != 13951) {
                this.method1993((byte)54);
            }
            this.aBoolean4540 = true;
            this.method2055(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.UI(" + n + ')');
        }
    }
    
    private final void method1961(final int n) {
        try {
            this.aBoolean4621 = false;
            if (this.aClass76_4598 != null) {
                this.aClass76_4598.method737(2899);
            }
            this.method2021(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.FH(" + n + ')');
        }
    }
    
    private final void method1962(final int n) {
        try {
            if (n <= -69) {
                this.method1966((byte)48);
                if (this.aClass76_4598 != null) {
                    this.aClass76_4598.method740(-121);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.SI(" + n + ')');
        }
    }
    
    final Interface2_Impl2 method1963(final int n, final int n2) {
        try {
            if (n <= 43) {
                return null;
            }
            if (~(2 * n2) < ~this.anInterface2_Impl2_4666.method2(200)) {
                this.anInterface2_Impl2_4666.method76(n2, 20779);
            }
            return this.anInterface2_Impl2_4666;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.RF(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void a(final za za) {
        try {
            this.aNativeHeap4522 = ((za_Sub1)za).aNativeHeap6078;
            this.aNativeHeapBuffer4521 = this.aNativeHeap4522.a(32768, false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.EH(" + ((za != null) ? "{...}" : "null") + ')');
        }
    }
    
    abstract void method1964(final Class38 p0, final byte p1);
    
    final void method1965(final boolean b) {
        try {
            this.aClass128Array4585 = new Class128[this.anInt4608];
            this.aClass111_Sub3Array4609 = new Class111_Sub3[this.anInt4608];
            this.anInterface4Array4617 = new Interface4[this.anInt4608];
            this.aClass128Array4639 = new Class128[this.anInt4608];
            this.aClass258Array4644 = new Class258[this.anInt4608];
            for (int n = 0; this.anInt4608 > n; ++n) {
                this.aClass128Array4639[n] = Class249.aClass128_1903;
                this.aClass128Array4585[n] = Class249.aClass128_1903;
                this.aClass258Array4644[n] = Class98_Sub46_Sub19.aClass258_6062;
                this.aClass111_Sub3Array4609[n] = new Class111_Sub3();
            }
            this.aClass98_Sub5Array4597 = new Class98_Sub5[this.anInt4565 - 2];
            this.anInterface4_4586 = this.method2006(1, 1, Class62.aClass164_486, (byte)45, Class162.aClass162_1266);
            this.a(new za_Sub1(262144));
            this.aClass256_4664 = this.method1982(new Class49[] { new Class49(new Class169[] { Class169.aClass169_1294, Class169.aClass169_1301 }) }, 6);
            this.aClass256_4659 = this.method1982(new Class49[] { new Class49(new Class169[] { Class169.aClass169_1294, Class169.aClass169_1298 }) }, 6);
            this.aClass256_4654 = this.method1982(new Class49[] { new Class49(Class169.aClass169_1294), new Class49(Class169.aClass169_1298), new Class49(Class169.aClass169_1301), new Class49(Class169.aClass169_1297) }, 6);
            this.aClass256_4668 = this.method1982(new Class49[] { new Class49(Class169.aClass169_1294), new Class49(Class169.aClass169_1298), new Class49(Class169.aClass169_1301) }, 6);
            this.aClass146_Sub3_4656 = new Class146_Sub3(this, 0, 0, false, false);
            this.aClass146_Sub3_4665 = new Class146_Sub3(this, 0, 0, true, true);
            this.aClass146_Sub3_4658 = new Class146_Sub3(this, 0, 0, false, false);
            this.aClass146_Sub3_4651 = new Class146_Sub3(this, 0, 0, true, true);
            this.aClass146_Sub3_4661 = new Class146_Sub3(this, 0, 0, false, false);
            this.aClass146_Sub3_4657 = new Class146_Sub3(this, 0, 0, true, true);
            this.aClass146_Sub3_4649 = new Class146_Sub3(this, 0, 0, false, false);
            this.aClass146_Sub3_4650 = new Class146_Sub3(this, 0, 0, true, true);
            this.aClass146_Sub3_4670 = new Class146_Sub3(this, 0, 0, false, false);
            this.aClass146_Sub3_4662 = new Class146_Sub3(this, 0, 0, true, b);
            this.aClass81_4634 = new Class81(this);
            this.anInterface2_Impl2_4666 = this.method1990((byte)84, true);
            this.method2064((byte)(-61));
            this.aClass195_4529 = new Class195(this);
            this.aClass76Array4613[1] = this.method2067(1, (byte)125);
            this.aClass76Array4613[2] = this.method2067(2, (byte)(-66));
            this.aClass76Array4613[4] = this.method2067(4, (byte)126);
            this.aClass76Array4613[5] = this.method2067(5, (byte)114);
            this.aClass76Array4613[6] = this.method2067(6, (byte)(-82));
            this.aClass76Array4613[7] = this.method2067(7, (byte)127);
            this.aClass76Array4613[3] = this.method2067(3, (byte)(-80));
            this.aClass76Array4613[8] = this.method2067(8, (byte)103);
            this.aClass76Array4613[9] = this.method2067(9, (byte)103);
            if (!this.aClass76Array4613[2].method745((byte)27)) {
                this.aClass76Array4613[2] = this.method2067(0, (byte)125);
            }
            if (!this.aClass76Array4613[4].method745((byte)27)) {
                this.aClass76Array4613[4] = this.aClass76Array4613[2];
            }
            if (!this.aClass76Array4613[8].method745((byte)27)) {
                this.aClass76Array4613[8] = this.aClass76Array4613[4];
            }
            if (!this.aClass76Array4613[9].method745((byte)27)) {
                this.aClass76Array4613[9] = this.aClass76Array4613[8];
            }
            this.method1941(1);
            this.la();
            this.method1817();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.LH(" + b + ')');
        }
    }
    
    abstract void method1966(final byte p0);
    
    final int method1967(final int n) {
        try {
            if (n <= 91) {
                this.aBoolean4550 = false;
            }
            return this.anInt4579;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.GE(" + n + ')');
        }
    }
    
    abstract Interface4_Impl2 method1968(final int p0, final int p1, final boolean p2, final Class164 p3, final int p4, final int p5, final int p6, final float[] p7);
    
    @Override
    final boolean method1780() {
        try {
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.FG()");
        }
    }
    
    @Override
    final Class332 method1758(final Class324 class324, final boolean b) {
        try {
            Class332 class325;
            if (class324.anInt2722 == 0 || class324.anInt2720 == 0) {
                class325 = this.method1748(-7962, 0, 1, 1, new int[1], 1);
            }
            else {
                final int[] array = new int[class324.anInt2720 * class324.anInt2722];
                int n = 0;
                int n2 = 0;
                if (class324.aByteArray2723 != null) {
                    for (int n3 = 0; ~n3 > ~class324.anInt2720; ++n3) {
                        for (int n4 = 0; class324.anInt2722 > n4; ++n4) {
                            array[n2++] = Class41.method366(class324.aByteArray2723[n] << 709795896, class324.anIntArray2718[Class202.method2702(class324.aByteArray2717[n], 255)]);
                            ++n;
                        }
                    }
                }
                else {
                    for (int i = 0; i < class324.anInt2720; ++i) {
                        for (int n5 = 0; class324.anInt2722 > n5; ++n5) {
                            final int n6 = class324.anIntArray2718[class324.aByteArray2717[n++] & 0xFF];
                            array[n2++] = ((n6 == 0) ? 0 : Class41.method366(-16777216, n6));
                        }
                    }
                }
                class325 = this.method1748(-7962, 0, class324.anInt2722, class324.anInt2720, array, class324.anInt2722);
            }
            class325.method3740(class324.anInt2725, class324.anInt2721, class324.anInt2719, class324.anInt2724);
            return class325;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.IE(" + ((class324 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final void aa(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            this.method1946(1);
            this.method1984(2, n5);
            this.method2051(0, -68, Class64_Sub16.aClass65_3681);
            this.method1953(-75, Class64_Sub16.aClass65_3681, 0);
            this.method2001(n6, 126);
            this.aClass111_Sub3_4542.method2137(n4, (byte)(-119), n3, 1.0f);
            this.aClass111_Sub3_4542.method2106(n, n2, 0);
            this.method1935(1);
            this.method2059(false, false);
            this.method2002((byte)(-125));
            this.method2059(true, false);
            this.method1953(-103, Class300.aClass65_2499, 0);
            this.method2051(0, -87, Class300.aClass65_2499);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.aa(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    private final boolean method1969(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7) {
        try {
            final Buffer method75 = this.anInterface2_Impl1_4667.method75(true, (byte)27);
            if (n7 != 8) {
                return false;
            }
            if (method75 == null) {
                return false;
            }
            final Stream method76 = this.method2043(24022, method75);
            Label_0134: {
                if (!Stream.a()) {
                    method76.a(n3);
                    method76.a(n2);
                    method76.a(n6);
                    method76.a(n5);
                    method76.a(n4);
                    method76.a(n);
                    if (!client.aBoolean3553) {
                        break Label_0134;
                    }
                }
                method76.b(n3);
                method76.b(n2);
                method76.b(n6);
                method76.b(n5);
                method76.b(n4);
                method76.b(n);
            }
            method76.c();
            return this.anInterface2_Impl1_4667.method71(13623);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.JE(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    @Override
    final void X(int n) {
        try {
            this.anInt4573 = 0;
            while (~n < -2) {
                ++this.anInt4573;
                n >>= 1;
            }
            this.anInt4553 = 1 << this.anInt4573;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.X(" + n + ')');
        }
    }
    
    private final void method1970(final int n) {
        try {
            this.aFloatArray4646[14] = this.aFloat4554;
            this.aFloatArray4646[10] = this.aFloat4626;
            this.aFloat4568 = (-this.anInt4605 + this.aFloatArray4646[14]) / this.aFloatArray4646[10];
            if (n != -24793) {
                this.aClass126_4625 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.QF(" + n + ')');
        }
    }
    
    abstract void method1971(final int p0, final boolean p1, final Interface2_Impl1 p2);
    
    abstract void method1972(final int p0);
    
    abstract void method1973(final Class232 p0, final int p1, final int p2, final int p3, final Interface2_Impl2 p4, final int p5, final int p6);
    
    @Override
    final int i() {
        try {
            return this.anInt4640;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.i()");
        }
    }
    
    @Override
    final void method1750(final Canvas aCanvas4524) {
        try {
            this.anObject4537 = null;
            this.aCanvas4524 = null;
            Label_0075: {
                if (aCanvas4524 == null || aCanvas4524 == this.aCanvas4533) {
                    this.anObject4537 = this.anObject4530;
                    this.aCanvas4524 = this.aCanvas4533;
                    if (!client.aBoolean3553) {
                        break Label_0075;
                    }
                }
                if (this.aHashtable4523.containsKey(aCanvas4524)) {
                    this.anObject4537 = this.aHashtable4523.get(aCanvas4524);
                    this.aCanvas4524 = aCanvas4524;
                }
            }
            if (this.aCanvas4524 == null || this.anObject4537 == null) {
                throw new RuntimeException();
            }
            this.method1944(this.anObject4537, this.aCanvas4524, (byte)(-34));
            this.method2003(1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.DH(" + ((aCanvas4524 != null) ? "{...}" : "null") + ')');
        }
    }
    
    abstract void method1974(final int p0);
    
    @Override
    final za method1762(final int n) {
        try {
            final za_Sub1 za_Sub1 = new za_Sub1(n);
            this.aClass148_4532.method2419(za_Sub1, -20911);
            return za_Sub1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.ME(" + n + ')');
        }
    }
    
    @Override
    final void pa() {
        try {
            this.aBoolean4563 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.pa()");
        }
    }
    
    private final void method1975(final int n) {
        try {
            this.method1991(n - 24391);
            this.method2004((byte)(-120));
            this.method1955(n - 5668);
            this.method2035(-14713);
            this.method2065((byte)80);
            this.method1989(-128);
            this.method2025((byte)(-37));
            this.method1974(n);
            this.method1972(n);
            this.method1999((byte)112);
            this.method2007(false);
            this.method2033(-112);
            this.method2016((byte)(-118));
            this.method2046(n);
            for (int i = -1 + this.anInt4608; i >= 0; --i) {
                this.method1951((byte)120, i);
                this.method2011(2);
                this.method1958((byte)(-48));
                this.method1985(n ^ 0x2);
            }
            this.method2057(n + 12362);
            this.method2036(-11155);
            this.method1966((byte)57);
            this.method1983((byte)(-47));
            this.method2021(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.UG(" + n + ')');
        }
    }
    
    final void method1976(final int n) {
        try {
            if (n >= -11) {
                this.anInterface2_Impl1_4667 = null;
            }
            if (this.anInt4633 != 8) {
                this.method1938((byte)(-20));
                this.method2028(true, (byte)(-101));
                this.method2013(true, 2103);
                this.method1997(0, true);
                this.method2001(1, 89);
                this.anInt4633 = 8;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.QJ(" + n + ')');
        }
    }
    
    @Override
    final void EA(final int anInt4600, final int anInt4601, final int anInt4602, final int anInt4603) {
        try {
            if (!this.aBoolean4563) {
                throw new RuntimeException("");
            }
            this.anInt4618 = anInt4603;
            this.anInt4548 = anInt4602;
            this.anInt4557 = anInt4601;
            this.anInt4600 = anInt4600;
            if (this.aBoolean4567) {
                this.aClass76Array4613[3].method747(-25684);
                this.aClass76Array4613[3].method738(-127);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.EA(" + anInt4600 + ',' + anInt4601 + ',' + anInt4602 + ',' + anInt4603 + ')');
        }
    }
    
    @Override
    final boolean method1819() {
        try {
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.KE()");
        }
    }
    
    abstract boolean method1977(final Class162 p0, final boolean p1, final Class164 p2);
    
    @Override
    final boolean method1810() {
        try {
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.PH()");
        }
    }
    
    @Override
    final boolean method1824() {
        try {
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.UF()");
        }
    }
    
    final Class111_Sub3 method1978(final byte b) {
        try {
            return this.aClass111_Sub3_4542;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.TF(" + b + ')');
        }
    }
    
    @Override
    void method1806(final int n) {
        try {
            if (this.aClass319_4624 != null) {
                this.aClass319_4624.method3662(-124);
            }
            this.anInt4556 = (n & Integer.MAX_VALUE);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.UB(" + n + ')');
        }
    }
    
    final void method1979(final boolean aBoolean4643, final int n) {
        try {
            if (n >= -57) {
                this.method1959(56);
            }
            if (!aBoolean4643 != !this.aBoolean4643) {
                this.aBoolean4643 = aBoolean4643;
                this.method1955(-5668);
                this.anInt4633 &= 0xFFFFFFF8;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.OI(" + aBoolean4643 + ',' + n + ')');
        }
    }
    
    @Override
    final Class111 method1793() {
        try {
            return this.aClass111_Sub3_4663;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.RI()");
        }
    }
    
    private final void method1980(final int n) {
        try {
            this.aFloat4642 = this.anInt4605;
            if (n != -20711) {
                this.anInt4593 = 47;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.WI(" + n + ')');
        }
    }
    
    final void method1981(final byte b) {
        try {
            final Enumeration<Canvas> keys = this.aHashtable4523.keys();
            while (keys.hasMoreElements()) {
                final Canvas canvas = keys.nextElement();
                this.method1936(0, this.aHashtable4523.get(canvas), canvas);
            }
            this.anInterface2_Impl1_4660.method72(false);
            if (b >= -93) {
                this.DA(-25, -24, -97, -114);
            }
            this.anInterface2_Impl1_4667.method72(false);
            this.anInterface2_Impl1_4652.method72(false);
            this.aClass146_Sub3_4665.method2402(-12884);
            this.aClass146_Sub3_4651.method2402(-12884);
            this.aClass146_Sub3_4657.method2402(-12884);
            this.aClass146_Sub3_4650.method2402(-12884);
            this.aClass146_Sub3_4662.method2402(-12884);
            this.aClass81_4634.method814((byte)4);
            this.anInterface2_Impl2_4666.method72(false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.DJ(" + b + ')');
        }
    }
    
    abstract Class256 method1982(final Class49[] p0, final int p1);
    
    @Override
    final void method1749(final boolean b) {
    }
    
    abstract void method1983(final byte p0);
    
    final void method1984(final int n, final int anInt4648) {
        try {
            if (~anInt4648 != ~this.anInt4648) {
                this.anInt4648 = anInt4648;
                this.method2057(12362);
            }
            if (n != 2) {
                this.aBoolean4599 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.VI(" + n + ',' + anInt4648 + ')');
        }
    }
    
    @Override
    final Class98_Sub5 method1765(final int n, final int n2, final int n3, final int n4, final int n5, final float n6) {
        try {
            return new Class98_Sub5_Sub1(n, n2, n3, n4, n5, n6);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.HG(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    final void method1985(final int n) {
        try {
            if (this.aClass258Array4644[this.anInt4579] != Class98_Sub46_Sub19.aClass258_6062) {
                this.aClass258Array4644[this.anInt4579] = Class98_Sub46_Sub19.aClass258_6062;
                this.aClass111_Sub3Array4609[this.anInt4579].method2091();
                this.method2014(n ^ 0x3D);
            }
            if (n != 2) {
                this.method1797(87, 53, -22, 92, true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.WD(" + n + ')');
        }
    }
    
    final void method1986(final boolean aBoolean4555, final byte b) {
        try {
            if (!this.aBoolean4555 == aBoolean4555) {
                this.aBoolean4555 = aBoolean4555;
                this.method2025((byte)13);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.CI(" + aBoolean4555 + ',' + b + ')');
        }
    }
    
    private final void method1987(final boolean b) {
        try {
            this.method1971(0, b, this.anInterface2_Impl1_4667);
            if (!b) {
                this.anInt4638 = -70;
            }
            this.method2042(this.aClass256_4653, (byte)118);
            this.method2037(Class334.aClass232_3468, 0, (byte)56, 1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.TG(" + b + ')');
        }
    }
    
    abstract void method1988(final Canvas p0, final Object p1, final int p2);
    
    abstract void method1989(final int p0);
    
    abstract Interface2_Impl2 method1990(final byte p0, final boolean p1);
    
    abstract void method1991(final int p0);
    
    @Override
    final void a(final Class111 class111) {
        try {
            this.aClass111_Sub3_4543 = (Class111_Sub3)class111;
            this.aClass111_Sub3_4545.method2092(this.aClass111_Sub3_4543);
            this.aClass111_Sub3_4545.method2136(false);
            this.aClass111_Sub3_4546.method2132((byte)(-52), this.aClass111_Sub3_4545);
            this.aClass111_Sub3_4544.method2132((byte)101, this.aClass111_Sub3_4543);
            if (this.aClass204_4551.method2708(102)) {
                this.method1961(124);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.QE(" + ((class111 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method1795(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            final float n7 = n3 - n;
            float n8 = n4 - n2;
            float n10 = 0.0f;
            Label_0075: {
                if (n7 != 0.0f || n8 != 0.0f) {
                    final float n9 = (float)(1.0 / Math.sqrt(n8 * n8 + n7 * n7));
                    n10 = n7 * n9;
                    n8 *= n9;
                    if (!client.aBoolean3553) {
                        break Label_0075;
                    }
                }
                n10 = 1.0f;
            }
            if (this.method1969(0.0f, n2, n, n8 + n4, n10 + n3, 0.0f, 8)) {
                this.method1946(1);
                this.method1984(2, n5);
                this.method2051(0, -102, Class64_Sub16.aClass65_3681);
                this.method1953(-116, Class64_Sub16.aClass65_3681, 0);
                this.method2001(n6, 93);
                this.method1960(13951);
                this.method2059(false, false);
                this.method1987(true);
                this.method2059(true, false);
                this.method1953(-118, Class300.aClass65_2499, 0);
                this.method2051(0, -121, Class300.aClass65_2499);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.PG(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    ha_Sub3(final Canvas canvas, final Object o, final d d, final Class207 aClass207_4535, final int anInt4616, final int anInt4617) {
        super(d);
        this.aClass148_4532 = new Class148();
        this.aBoolean4540 = true;
        this.aClass111_Sub3_4542 = new Class111_Sub3();
        this.aClass111_Sub3_4543 = new Class111_Sub3();
        this.aClass111_Sub3_4544 = new Class111_Sub3();
        this.aClass111_Sub3_4545 = new Class111_Sub3();
        this.aClass111_Sub3_4546 = new Class111_Sub3();
        this.aClass111_Sub3_4547 = new Class111_Sub3();
        this.aFloat4549 = 1.0f;
        this.aBoolean4555 = true;
        this.aFloat4561 = 1.0f;
        this.aBoolean4570 = false;
        this.aBoolean4550 = false;
        this.anInt4578 = 0;
        this.anInt4583 = -1;
        this.aFloat4576 = 1.0f;
        this.anInt4548 = -1;
        this.aBoolean4567 = false;
        this.aClass204_4551 = Class246_Sub3_Sub1_Sub2.aClass204_6247;
        this.anInt4581 = -1;
        this.aFloatArray4596 = new float[] { 0.0f, 0.0f, 1.0f, 0.0f };
        this.anInt4558 = 0;
        this.anInt4612 = 0;
        this.aBoolean4560 = true;
        this.aBoolean4574 = false;
        this.aFloat4611 = 1.0f;
        this.anInt4573 = 3;
        this.aFloatArray4572 = new float[] { 0.0f, 0.0f, -1.0f, 0.0f };
        this.anInt4589 = 512;
        this.anInt4622 = 0;
        this.aBoolean4620 = false;
        this.anInt4601 = 0;
        this.aFloatArray4604 = new float[16];
        this.anInt4575 = 0;
        this.anInt4602 = 0;
        this.aClass76Array4613 = new Class76[10];
        this.aFloat4591 = 1.0f;
        this.aBoolean4606 = true;
        this.aBoolean4571 = true;
        this.aBoolean4621 = false;
        this.aFloatArray4590 = new float[16];
        this.aBoolean4631 = false;
        this.anInt4607 = 128;
        this.anInt4632 = 1;
        this.anInt4553 = 8;
        this.anInt4593 = 512;
        this.anInt4605 = 3584;
        this.aFloatArray4629 = new float[] { 0.0f, 0.0f, 1.0f, 0.0f };
        this.aFloat4630 = -1.0f;
        this.aClass126_4625 = Class101.aClass126_848;
        this.aFloat4594 = -1.0f;
        this.anInt4640 = 50;
        this.anInt4595 = 0;
        this.anInt4579 = 0;
        this.anInt4587 = 0;
        this.aFloatArray4627 = new float[] { 0.0f, 0.0f, 1.0f, 0.0f };
        this.aBoolean4637 = false;
        this.anInt4623 = 16777215;
        this.anInt4636 = -1;
        this.anInt4618 = 0;
        this.aBoolean4599 = true;
        this.anInt4638 = 0;
        this.aFloat4642 = 3584.0f;
        this.aFloat4568 = 3584.0f;
        this.anInt4635 = 0;
        this.aFloatArray4646 = new float[16];
        this.aBoolean4577 = false;
        this.anInt4557 = -1;
        this.aFloatArray4582 = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f };
        this.aFloatArray4566 = this.aFloatArray4582;
        this.aBoolean4643 = false;
        this.anInt4645 = 0;
        this.aStream4603 = new Stream();
        this.aClass111_Sub3_4663 = new Class111_Sub3();
        try {
            try {
                this.anObject4530 = o;
                this.anObject4537 = o;
                this.aClass207_4535 = aClass207_4535;
                this.aCanvas4533 = canvas;
                this.aCanvas4524 = canvas;
                this.anInt4616 = anInt4616;
                final Dimension size = canvas.getSize();
                final int height = size.height;
                this.anInt4534 = height;
                this.anInt4531 = height;
                this.anInt4580 = anInt4617;
                final int width = size.width;
                this.anInt4536 = width;
                this.anInt4527 = width;
                Class101.method1702(8, false, true);
                if (super.aD938 != null) {
                    this.aClass319_4624 = new Class319(this, super.aD938);
                    this.aNativeInterface4526 = new NativeInterface(super.aD938.method12(true), this.anInt4580);
                    for (int i = 0; i < super.aD938.method12(true); ++i) {
                        final Class238 method11 = super.aD938.method11(i, -28755);
                        if (method11 != null) {
                            this.aNativeInterface4526.initTextureMetrics(i, method11.aByte1830, method11.aByte1829);
                        }
                    }
                }
                else {
                    this.aNativeInterface4526 = new NativeInterface(0, this.anInt4580);
                    this.aClass319_4624 = null;
                }
            }
            catch (Throwable t) {
                t.printStackTrace();
                this.method1743(-1);
                throw new RuntimeException("");
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.<init>(" + ((canvas != null) ? "{...}" : "null") + ',' + ((o != null) ? "{...}" : "null") + ',' + ((d != null) ? "{...}" : "null") + ',' + ((aClass207_4535 != null) ? "{...}" : "null") + ',' + anInt4616 + ',' + anInt4617 + ')');
        }
    }
    
    final void method1992(final byte b) {
        try {
            if (Class246_Sub3_Sub1_Sub2.aClass204_6247 != this.aClass204_4551) {
                final Class204 aClass204_4551 = this.aClass204_4551;
                this.aClass204_4551 = Class246_Sub3_Sub1_Sub2.aClass204_6247;
                if (aClass204_4551.method2708(98)) {
                    this.method1961(97);
                }
                this.aFloatArray4566 = this.aFloatArray4582;
                this.anInt4633 &= 0xFFFFFFE0;
            }
            if (b != 125) {
                this.method1790(null, 39, -121, 10, 17);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.VH(" + b + ')');
        }
    }
    
    @Override
    final void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final aa aa, final int n7, final int n8, final int n9, final int n10, final int n11) {
    }
    
    private final void method1993(final byte b) {
        try {
            if (b != 103) {
                this.aCanvas4524 = null;
            }
            if (this.aClass204_4551 != Class64_Sub4.aClass204_3649) {
                final Class204 aClass204_4551 = this.aClass204_4551;
                this.aClass204_4551 = Class64_Sub4.aClass204_3649;
                if (aClass204_4551.method2708(118)) {
                    this.method1961(-51);
                }
                this.method1996(6281);
                this.aFloatArray4566 = this.aFloatArray4590;
                this.method1962(-76);
                this.anInt4633 &= 0xFFFFFFE7;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.NJ(" + b + ')');
        }
    }
    
    @Override
    final Class146 method1790(final Class178 class178, final int n, final int n2, final int n3, final int n4) {
        try {
            return new Class146_Sub3(this, class178, n, n3, n4, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.VD(" + ((class178 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    abstract void method1994(final byte p0, final int p1);
    
    @Override
    final void xa(final float n) {
        try {
            if (this.aFloat4576 != n) {
                this.aFloat4576 = n;
                this.aNativeInterface4526.setAmbient(n);
                this.method1991(-24391);
                this.method1989(87);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.xa(" + n + ')');
        }
    }
    
    @Override
    final void method1811(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, int n9) {
        try {
            final float n10 = n3 - n;
            float n11 = n4 - n2;
            float n13;
            if (n10 != 0.0f || n11 != 0.0f) {
                final float n12 = (float)(1.0 / Math.sqrt(n11 * n11 + n10 * n10));
                n11 *= n12;
                n13 = n10 * n12;
            }
            else {
                n13 = 1.0f;
            }
            this.method1946(1);
            this.method1984(2, n5);
            this.method2051(0, -106, Class64_Sub16.aClass65_3681);
            this.method1953(-106, Class64_Sub16.aClass65_3681, 0);
            this.method2001(n6, 93);
            this.method1960(13951);
            n9 %= n8 + n7;
            this.method2059(false, false);
            final float n14 = n7 * n13;
            final float n15 = n7 * n11;
            float n16 = 0.0f;
            float n17 = 0.0f;
            float n18 = n14;
            float n19 = n15;
            if (n9 <= n7) {
                n18 = (n7 + -n9) * n13;
                n19 = (n7 + -n9) * n11;
            }
            else {
                n17 = n11 * (n8 + n7 + -n9);
                n16 = (n7 - (-n8 - -n9)) * n13;
            }
            float n20 = n + n16;
            float n21 = n2 + n17;
            final float n22 = n13 * n8;
            final float n23 = n8 * n11;
            while (true) {
                if (n3 <= n) {
                    if (n3 > n20) {
                        break;
                    }
                    if (n3 > n18 + n20) {
                        n18 = n3 - n20;
                    }
                }
                else {
                    if (n20 > n3) {
                        break;
                    }
                    if (n20 + n18 > n3) {
                        n18 = -n20 + n3;
                    }
                }
                if (~n2 <= ~n4) {
                    if (n4 > n21) {
                        break;
                    }
                    if (n21 + n19 < n4) {
                        n19 = -n21 + n4;
                    }
                }
                else {
                    if (n4 < n21) {
                        break;
                    }
                    if (n4 < n19 + n21) {
                        n19 = n4 - n21;
                    }
                }
                if (!this.method1969(0.0f, n21, n20, n21 + n19, n18 + n20, 0.0f, 8)) {
                    return;
                }
                n20 += n22 + n18;
                this.method1987(true);
                n21 += n23 + n19;
                n18 = n14;
                n19 = n15;
            }
            this.method2059(true, false);
            this.method1953(-128, Class300.aClass65_2499, 0);
            this.method2051(0, -121, Class300.aClass65_2499);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.CF(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    private final void method1995(final int n) {
        try {
            (this.anInterface2_Impl1_4660 = this.method2060(false, 58)).method74(-20279, 28, 140);
            int i = 0;
            if (n != 9) {
                this.method1993((byte)112);
            }
            while (i < 4) {
                final Buffer method75 = this.anInterface2_Impl1_4660.method75(true, (byte)27);
                if (method75 != null) {
                    final Stream method76 = this.method2043(n + 24013, method75);
                    if (!Stream.a()) {
                        method76.a(0.0f);
                        method76.a(0.0f);
                        method76.a(0.0f);
                        method76.a(0.0f);
                        method76.a(0.0f);
                        method76.a(0.0f);
                        method76.a(0.0f);
                        method76.a(0.0f);
                        method76.a(1.0f);
                        method76.a(0.0f);
                        method76.a(0.0f);
                        method76.a(1.0f);
                        method76.a(0.0f);
                        method76.a(1.0f);
                        method76.a(1.0f);
                        method76.a(1.0f);
                        method76.a(0.0f);
                        method76.a(1.0f);
                        method76.a(1.0f);
                        method76.a(1.0f);
                        method76.a(1.0f);
                        method76.a(1.0f);
                        method76.a(0.0f);
                        method76.a(0.0f);
                        method76.a(1.0f);
                        method76.a(0.0f);
                        method76.a(1.0f);
                        method76.a(0.0f);
                        method76.a(0.0f);
                        method76.a(0.0f);
                        method76.a(0.0f);
                        method76.a(0.0f);
                        method76.a(0.0f);
                        method76.a(0.0f);
                        method76.a(0.0f);
                    }
                    else {
                        method76.b(0.0f);
                        method76.b(0.0f);
                        method76.b(0.0f);
                        method76.b(0.0f);
                        method76.b(0.0f);
                        method76.b(0.0f);
                        method76.b(0.0f);
                        method76.b(0.0f);
                        method76.b(1.0f);
                        method76.b(0.0f);
                        method76.b(0.0f);
                        method76.b(1.0f);
                        method76.b(0.0f);
                        method76.b(1.0f);
                        method76.b(1.0f);
                        method76.b(1.0f);
                        method76.b(0.0f);
                        method76.b(1.0f);
                        method76.b(1.0f);
                        method76.b(1.0f);
                        method76.b(1.0f);
                        method76.b(1.0f);
                        method76.b(0.0f);
                        method76.b(0.0f);
                        method76.b(1.0f);
                        method76.b(0.0f);
                        method76.b(1.0f);
                        method76.b(0.0f);
                        method76.b(0.0f);
                        method76.b(0.0f);
                        method76.b(0.0f);
                        method76.b(0.0f);
                        method76.b(0.0f);
                        method76.b(0.0f);
                        method76.b(0.0f);
                    }
                    method76.c();
                    if (this.anInterface2_Impl1_4660.method71(13623)) {
                        break;
                    }
                }
                ++i;
            }
            this.aClass256_4655 = this.method1982(new Class49[] { new Class49(new Class169[] { Class169.aClass169_1294, Class169.aClass169_1301, Class169.aClass169_1301 }) }, 6);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.BG(" + n + ')');
        }
    }
    
    private final void method1996(final int n) {
        try {
            if (n != 6281) {
                this.method1795(114, -87, -38, -57, 16, 20);
            }
            if (!this.aBoolean4574) {
                final float[] aFloatArray4590 = this.aFloatArray4590;
                this.aBoolean4574 = true;
                if (~this.anInt4527 != -1 && ~this.anInt4531 != -1) {
                    aFloatArray4590[3] = (aFloatArray4590[1] = 0.0f);
                    aFloatArray4590[0] = 2.0f / this.anInt4527;
                    aFloatArray4590[4] = 0.0f;
                    aFloatArray4590[13] = (aFloatArray4590[15] = 1.0f);
                    aFloatArray4590[11] = 0.0f;
                    aFloatArray4590[10] = 0.5f;
                    aFloatArray4590[8] = 0.0f;
                    aFloatArray4590[9] = (aFloatArray4590[6] = 0.0f);
                    aFloatArray4590[2] = (aFloatArray4590[7] = 0.0f);
                    aFloatArray4590[14] = 0.5f;
                    aFloatArray4590[12] = -1.0f;
                    aFloatArray4590[5] = -2.0f / this.anInt4531;
                }
                else {
                    aFloatArray4590[0] = 1.0f;
                    aFloatArray4590[7] = 0.0f;
                    aFloatArray4590[4] = (aFloatArray4590[8] = 0.0f);
                    aFloatArray4590[5] = 1.0f;
                    aFloatArray4590[3] = (aFloatArray4590[1] = 0.0f);
                    aFloatArray4590[15] = 1.0f;
                    aFloatArray4590[9] = (aFloatArray4590[6] = 0.0f);
                    aFloatArray4590[10] = 1.0f;
                    aFloatArray4590[2] = 0.0f;
                    aFloatArray4590[11] = (aFloatArray4590[13] = 0.0f);
                    aFloatArray4590[12] = (aFloatArray4590[14] = 0.0f);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.AI(" + n + ')');
        }
    }
    
    @Override
    final boolean method1767() {
        try {
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.JF()");
        }
    }
    
    final void method1997(final int n, final boolean aBoolean4620) {
        try {
            if (n != 0) {
                this.method1996(-123);
            }
            if (!aBoolean4620 != !this.aBoolean4620) {
                this.aBoolean4620 = aBoolean4620;
                this.method1972(0);
                this.anInt4633 &= 0xFFFFFFE0;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.IJ(" + n + ',' + aBoolean4620 + ')');
        }
    }
    
    @Override
    final boolean method1800() {
        try {
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.RE()");
        }
    }
    
    @Override
    final void KA(final int n, final int n2, final int n3, final int n4) {
        try {
            if (~n >= -1 && -1 + this.anInt4527 <= n3 && n2 <= 0 && this.anInt4531 - 1 <= n4) {
                this.la();
            }
            else {
                this.anInt4602 = ((n >= 0) ? n : 0);
                this.anInt4575 = ((~n3 < ~this.anInt4527) ? 0 : n3);
                this.anInt4558 = ((n2 < 0) ? 0 : n2);
                this.anInt4638 = ((this.anInt4527 < n4) ? 0 : n4);
                if (!this.aBoolean4672) {
                    this.aBoolean4672 = true;
                    this.method2030((byte)(-122));
                }
                this.method2009(28976);
                this.method2048((byte)32);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.KA(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    final int method1998(final byte b) {
        try {
            if (b <= 88) {
                this.method1970(22);
            }
            return this.anInt4557;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.KH(" + b + ')');
        }
    }
    
    abstract void method1999(final byte p0);
    
    private final void method2000(final byte b) {
        try {
            if (b != -117) {
                this.aBoolean4550 = false;
            }
            if (!this.aBoolean4570) {
                final float[] aFloatArray4604 = this.aFloatArray4604;
                final float n = this.anInt4640;
                final float n2 = this.anInt4605;
                final float n3 = -this.anInt4587 * this.aFloat4561 / this.anInt4589;
                final float n4 = this.aFloat4561 * -this.anInt4645 / this.anInt4593;
                final float n5 = this.aFloat4561 * (-this.anInt4645 + this.anInt4527) / this.anInt4593;
                final float n6 = this.aFloat4561 * (this.anInt4531 - this.anInt4587) / this.anInt4589;
                Label_0340: {
                    if (n5 != n4 && n3 != n6) {
                        aFloatArray4604[5] = 2.0f / (-n3 + n6);
                        aFloatArray4604[0] = 2.0f / (-n4 + n5);
                        aFloatArray4604[10] = 1.0f / (n - n2);
                        aFloatArray4604[2] = (aFloatArray4604[11] = 0.0f);
                        aFloatArray4604[4] = (aFloatArray4604[9] = 0.0f);
                        aFloatArray4604[7] = (aFloatArray4604[8] = 0.0f);
                        aFloatArray4604[3] = (aFloatArray4604[6] = 0.0f);
                        aFloatArray4604[12] = (n5 + n4) / (-n5 + n4);
                        aFloatArray4604[14] = n / (n - n2);
                        aFloatArray4604[15] = 1.0f;
                        aFloatArray4604[1] = 0.0f;
                        aFloatArray4604[13] = (n3 + n6) / (-n3 + n6);
                        if (!client.aBoolean3553) {
                            break Label_0340;
                        }
                    }
                    aFloatArray4604[9] = 0.0f;
                    aFloatArray4604[0] = (aFloatArray4604[15] = 1.0f);
                    aFloatArray4604[7] = 0.0f;
                    aFloatArray4604[13] = (aFloatArray4604[3] = 0.0f);
                    aFloatArray4604[12] = (aFloatArray4604[2] = 0.0f);
                    aFloatArray4604[10] = 1.0f;
                    aFloatArray4604[4] = (aFloatArray4604[11] = 0.0f);
                    aFloatArray4604[1] = (aFloatArray4604[8] = 0.0f);
                    aFloatArray4604[5] = 1.0f;
                    aFloatArray4604[14] = (aFloatArray4604[6] = 0.0f);
                }
                this.method1980(-20711);
                this.aBoolean4570 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.UH(" + b + ')');
        }
    }
    
    @Override
    final void ZA(final int anInt4623, final float aFloat4630, final float aFloat4631, final float n, final float n2, final float n3) {
        try {
            final boolean b = this.anInt4623 != anInt4623;
            if (b || this.aFloat4630 != aFloat4630 || aFloat4631 != this.aFloat4594) {
                this.aFloat4594 = aFloat4631;
                this.anInt4623 = anInt4623;
                this.aFloat4630 = aFloat4630;
                if (b) {
                    this.aFloat4611 = (this.anInt4623 & 0xFF0000) / 1.671168E7f;
                    this.aFloat4549 = (this.anInt4623 & 0xFF00) / 65280.0f;
                    this.aFloat4591 = (this.anInt4623 & 0xFF) / 255.0f;
                    this.method1991(-24391);
                }
                this.aNativeInterface4526.setSunColour(this.aFloat4611, this.aFloat4549, this.aFloat4591, aFloat4630, aFloat4631);
                this.method2004((byte)(-103));
            }
            if (this.aFloatArray4627[0] != n || this.aFloatArray4627[1] != n2 || this.aFloatArray4627[2] != n3) {
                this.aFloatArray4627[2] = n3;
                this.aFloatArray4627[0] = n;
                this.aFloatArray4627[1] = n2;
                this.aFloatArray4629[1] = -n2;
                this.aFloatArray4629[0] = -n;
                this.aFloatArray4629[2] = -n3;
                final float n4 = (float)(1.0 / Math.sqrt(n2 * n2 + n * n + n3 * n3));
                this.aFloatArray4596[1] = n2 * n4;
                this.aFloatArray4596[2] = n4 * n3;
                this.aFloatArray4596[0] = n * n4;
                this.aFloatArray4572[2] = -this.aFloatArray4596[2];
                this.aFloatArray4572[1] = -this.aFloatArray4596[1];
                this.aFloatArray4572[0] = -this.aFloatArray4596[0];
                this.aNativeInterface4526.setSunDirection(this.aFloatArray4596[0], this.aFloatArray4596[1], this.aFloatArray4596[2]);
                this.method2065((byte)80);
                this.anInt4564 = (int)(256.0f * n / n2);
                this.anInt4552 = (int)(n3 * 256.0f / n2);
            }
            this.method1989(72);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.ZA(" + anInt4623 + ',' + aFloat4630 + ',' + aFloat4631 + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final void method2001(final int anInt4632, final int n) {
        try {
            if (n > 68 && anInt4632 != this.anInt4632) {
                boolean aBoolean4599;
                Class126 aClass126_4625;
                boolean aBoolean4600;
                if (anInt4632 == 1) {
                    aBoolean4599 = true;
                    aClass126_4625 = Class101.aClass126_848;
                    aBoolean4600 = true;
                }
                else if (~anInt4632 != 0xFFFFFFFD) {
                    if (~anInt4632 == 0xFFFFFF7F) {
                        aClass126_4625 = Class83.aClass126_632;
                        aBoolean4600 = true;
                        aBoolean4599 = true;
                    }
                    else {
                        aClass126_4625 = Class39.aClass126_361;
                        aBoolean4599 = false;
                        aBoolean4600 = false;
                    }
                }
                else {
                    aBoolean4599 = false;
                    aClass126_4625 = Class373_Sub1_Sub1.aClass126_6216;
                    aBoolean4600 = true;
                }
                if (!aBoolean4599 == this.aBoolean4599) {
                    this.aBoolean4599 = aBoolean4599;
                    this.method2046(0);
                }
                if (this.aBoolean4560 != aBoolean4600) {
                    this.aBoolean4560 = aBoolean4600;
                    this.method2033(52);
                }
                if (this.aClass126_4625 != aClass126_4625) {
                    this.aClass126_4625 = aClass126_4625;
                    this.method2016((byte)(-118));
                }
                this.anInt4633 &= 0xFFFFFFE3;
                this.anInt4632 = anInt4632;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.CJ(" + anInt4632 + ',' + n + ')');
        }
    }
    
    @Override
    final void method1785(final Class242 class242, final int n) {
        try {
            this.aClass81_4634.method818((byte)(-108), class242, n, this);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.CG(" + ((class242 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final int M() {
        try {
            return this.anInt4671;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.M()");
        }
    }
    
    @Override
    final void DA(final int anInt4645, final int anInt4646, final int anInt4647, final int anInt4648) {
        try {
            this.anInt4593 = anInt4647;
            this.anInt4587 = anInt4646;
            this.anInt4645 = anInt4645;
            this.anInt4589 = anInt4648;
            this.method2038((byte)121);
            this.method1943(10886);
            this.method1992((byte)125);
            this.method2048((byte)32);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.DA(" + anInt4645 + ',' + anInt4646 + ',' + anInt4647 + ',' + anInt4648 + ')');
        }
    }
    
    @Override
    final void method1775(final Class48 class48) {
        try {
            this.aClass48_Sub2_4614 = (Class48_Sub2)class48;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.MH(" + ((class48 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void la() {
        try {
            this.anInt4558 = 0;
            this.anInt4602 = 0;
            this.anInt4575 = this.anInt4527;
            this.anInt4638 = this.anInt4531;
            if (this.aBoolean4672) {
                this.aBoolean4672 = false;
                this.method2030((byte)(-122));
            }
            this.method2048((byte)32);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.la()");
        }
    }
    
    @Override
    final Class48 method1803(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            return new Class48_Sub2_Sub1(this, n, n2, n3, n4, n5, n6);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.II(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    final void method2002(final byte b) {
        try {
            this.method2054(false, 2, Class98_Sub46_Sub15.aClass232_6043);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.CE(" + b + ')');
        }
    }
    
    private final void method2003(final int n) {
        try {
            if (n != 1) {
                this.anInt4648 = 58;
            }
            Label_0061: {
                if (this.aCanvas4524 == null) {
                    final boolean b = true;
                    this.anInt4534 = (b ? 1 : 0);
                    this.anInt4536 = (b ? 1 : 0);
                    if (!client.aBoolean3553) {
                        break Label_0061;
                    }
                }
                final Dimension size = this.aCanvas4524.getSize();
                this.anInt4536 = size.width;
                this.anInt4534 = size.height;
            }
            this.anInt4527 = this.anInt4536;
            this.anInt4531 = this.anInt4534;
            this.method2031(-12545);
            this.method1943(n + 10885);
            this.method2038((byte)115);
            this.method2036(-11155);
            this.method2048((byte)32);
            this.method1992((byte)125);
            this.la();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.EJ(" + n + ')');
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
            throw Class64_Sub27.method667(ex, "uv.OF(" + n + ')');
        }
    }
    
    abstract void method2004(final byte p0);
    
    final void method2005(final Interface4 interface4, final int n) {
        try {
            if (interface4 != this.anInterface4Array4617[this.anInt4579]) {
                Label_0060: {
                    if ((this.anInterface4Array4617[this.anInt4579] = interface4) != null) {
                        interface4.method5(101);
                        if (!client.aBoolean3553) {
                            break Label_0060;
                        }
                    }
                    this.method1959(0);
                }
                this.anInt4633 &= 0xFFFFFFFE;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.FE(" + ((interface4 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    abstract Interface4_Impl2 method2006(final int p0, final int p1, final Class164 p2, final byte p3, final Class162 p4);
    
    abstract void method2007(final boolean p0);
    
    final void method2008(final Class258 class258, final byte b) {
        try {
            this.aClass258Array4644[this.anInt4579] = class258;
            if (b >= 25) {
                this.method2014(105);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.AH(" + ((class258 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    abstract void method2009(final int p0);
    
    abstract void method2010(final int p0);
    
    abstract void method2011(final int p0);
    
    @Override
    final s a(final int n, final int n2, final int[][] array, final int[][] array2, final int n3, final int n4, final int n5) {
        try {
            return new s_Sub2(this, n4, n5, n, n2, array, array2, n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.DG(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    final Interface4_Impl2 method2012(final int n, final int n2, final byte b, final int[] array, final boolean b2) {
        try {
            if (b != 31) {
                this.aClass76_4598 = null;
            }
            return this.method2063(0, (byte)126, array, b2, n, n2, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.BI(" + n + ',' + n2 + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + b2 + ')');
        }
    }
    
    @Override
    final boolean method1823() {
        try {
            return this.aBoolean4559;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.AF()");
        }
    }
    
    @Override
    final void L(final int anInt4636, final int anInt4637, final int anInt4638) {
        try {
            if (~this.anInt4636 != ~anInt4636 || this.anInt4581 != anInt4637 || ~this.anInt4601 != ~anInt4638) {
                this.anInt4636 = anInt4636;
                this.anInt4601 = anInt4638;
                this.anInt4581 = anInt4637;
                this.method2020(-123);
                this.method1999((byte)112);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.L(" + anInt4636 + ',' + anInt4637 + ',' + anInt4638 + ')');
        }
    }
    
    final void method2013(final boolean aBoolean4577, final int n) {
        try {
            if (aBoolean4577 != this.aBoolean4577) {
                this.aBoolean4577 = aBoolean4577;
                this.method1974(n - 2103);
                this.anInt4633 &= 0xFFFFFFE0;
            }
            if (n != 2103) {
                this.aFloat4642 = -1.3455139f;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.GJ(" + aBoolean4577 + ',' + n + ')');
        }
    }
    
    private final void method2014(final int n) {
        try {
            this.method2010(-107);
            if (this.aClass76_4598 != null) {
                this.aClass76_4598.method749(8);
            }
            if (n <= 37) {
                this.anInt4538 = 2;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.DI(" + n + ')');
        }
    }
    
    @Override
    final void HA(final int n, final int n2, final int n3, final int n4, final int[] array) {
        try {
            final float method2135 = this.aClass111_Sub3_4543.method2135(n2, n3, n, false);
            if (this.anInt4640 > method2135 || method2135 > this.anInt4605) {
                final int n5 = 0;
                final int n6 = 1;
                final int n7 = 2;
                final int n8 = -1;
                array[n7] = n8;
                array[n5] = (array[n6] = n8);
            }
            else {
                final int n9 = (int)(this.anInt4593 * this.aClass111_Sub3_4543.method2126(n3, n, n2, 118) / n4);
                final int n10 = (int)(this.anInt4589 * this.aClass111_Sub3_4543.method2139(n2, n, n3, true) / n4);
                array[0] = (int)(-this.aFloat4641 + n9);
                array[1] = (int)(-this.aFloat4610 + n10);
                array[2] = (int)method2135;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.HA(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final int[] Y() {
        try {
            return new int[] { this.anInt4645, this.anInt4587, this.anInt4593, this.anInt4589 };
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.Y()");
        }
    }
    
    final void method2015(final int n, final byte b) {
        try {
            if (n != 1) {
                if (n != 0) {
                    if (n == 2) {
                        this.method2019(Class288.aClass128_3381, Class323.aClass128_2715, 22831);
                    }
                    else if (~n != 0xFFFFFFFC) {
                        if (~n == 0xFFFFFFFB) {
                            this.method2019(Class28.aClass128_286, Class28.aClass128_286, 22831);
                        }
                    }
                    else {
                        this.method2019(Class249.aClass128_1903, Class1.aClass128_64, 22831);
                    }
                }
                else {
                    this.method2019(Class249.aClass128_1903, Class249.aClass128_1903, 22831);
                }
            }
            else {
                this.method2019(Class288.aClass128_3381, Class288.aClass128_3381, 22831);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.VF(" + n + ',' + b + ')');
        }
    }
    
    abstract void method2016(final byte p0);
    
    final int method2017(final byte b) {
        try {
            if (b != 67) {
                this.method1773();
            }
            return this.anInt4600;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.WF(" + b + ')');
        }
    }
    
    @Override
    final int JA(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            int n7 = 0;
            float n8 = this.aClass111_Sub3_4543.aFloat4708 * n + this.aClass111_Sub3_4543.aFloat4713 * n2 + n3 * this.aClass111_Sub3_4543.aFloat4704 + this.aClass111_Sub3_4543.aFloat4703;
            if (n8 < 1.0f) {
                n8 = 1.0f;
            }
            float n9 = n4 * this.aClass111_Sub3_4543.aFloat4708 + this.aClass111_Sub3_4543.aFloat4713 * n5 + n6 * this.aClass111_Sub3_4543.aFloat4704 + this.aClass111_Sub3_4543.aFloat4703;
            if (n9 < 1.0f) {
                n9 = 1.0f;
            }
            if (n8 >= this.anInt4640 || n9 >= this.anInt4640) {
                if (n8 > this.anInt4605 && n9 > this.anInt4605) {
                    n7 |= 0x20;
                }
            }
            else {
                n7 |= 0x10;
            }
            final int n10 = (int)((n3 * this.aClass111_Sub3_4543.aFloat4705 + (this.aClass111_Sub3_4543.aFloat4711 * n2 + this.aClass111_Sub3_4543.aFloat4712 * n) + this.aClass111_Sub3_4543.aFloat4702) * this.anInt4593 / n8);
            final int n11 = (int)(this.anInt4593 * (n6 * this.aClass111_Sub3_4543.aFloat4705 + (this.aClass111_Sub3_4543.aFloat4712 * n4 + n5 * this.aClass111_Sub3_4543.aFloat4711) + this.aClass111_Sub3_4543.aFloat4702) / n9);
            if (n10 >= this.aFloat4641 || n11 >= this.aFloat4641) {
                if (this.aFloat4647 < n10 && n11 > this.aFloat4647) {
                    n7 |= 0x2;
                }
            }
            else {
                n7 |= 0x1;
            }
            final int n12 = (int)((n2 * this.aClass111_Sub3_4543.aFloat4706 + this.aClass111_Sub3_4543.aFloat4714 * n + this.aClass111_Sub3_4543.aFloat4710 * n3 + this.aClass111_Sub3_4543.aFloat4709) * this.anInt4589 / n8);
            final int n13 = (int)(this.anInt4589 * (this.aClass111_Sub3_4543.aFloat4709 + (this.aClass111_Sub3_4543.aFloat4710 * n6 + (this.aClass111_Sub3_4543.aFloat4706 * n5 + n4 * this.aClass111_Sub3_4543.aFloat4714))) / n9);
            if (this.aFloat4610 > n12 && this.aFloat4610 > n13) {
                n7 |= 0x4;
            }
            else if (n12 > this.aFloat4584 && this.aFloat4584 < n13) {
                n7 |= 0x8;
            }
            return n7;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.JA(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    final int method2018(final byte b) {
        try {
            if (b < 78) {
                this.aClass319_4624 = null;
            }
            return this.anInt4618;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.WH(" + b + ')');
        }
    }
    
    final void method2019(final Class128 class128, final Class128 class129, final int n) {
        try {
            boolean b = false;
            if (n == 22831) {
                if (class129 != this.aClass128Array4585[this.anInt4579]) {
                    this.aClass128Array4585[this.anInt4579] = class129;
                    this.method2011(2);
                    b = true;
                }
                if (this.aClass128Array4639[this.anInt4579] != class128) {
                    this.aClass128Array4639[this.anInt4579] = class128;
                    this.method1958((byte)(-48));
                    b = true;
                }
                if (b) {
                    this.anInt4633 &= 0xFFFFFFE2;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.IF(" + ((class128 != null) ? "{...}" : "null") + ',' + ((class129 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    final void A(final int n, final aa aa, final int n2, final int n3) {
        try {
            final Interface4_Impl2 anInterface4_Impl2_3563 = ((aa_Sub2)aa).anInterface4_Impl2_3563;
            this.method2052(false);
            this.method2005(anInterface4_Impl2_3563, 95);
            this.method2001(1, 100);
            this.method2019(Class288.aClass128_3381, Class288.aClass128_3381, 22831);
            this.method2051(0, -60, Class64_Sub16.aClass65_3681);
            this.method1984(2, n);
            this.aClass111_Sub3_4542.method2137(this.anInt4531, (byte)(-110), this.anInt4527, 0.0f);
            this.method1935(1);
            this.aClass111_Sub3Array4609[0].method2137(anInterface4_Impl2_3563.method45(-8473, this.anInt4531), (byte)(-128), anInterface4_Impl2_3563.method42((byte)115, this.anInt4527), 1.0f);
            this.aClass111_Sub3Array4609[0].method2141(-94, anInterface4_Impl2_3563.method45(-8473, -n3), 0.0f, anInterface4_Impl2_3563.method42((byte)(-124), -n2));
            this.aClass258Array4644[0] = Class246_Sub3_Sub4_Sub5.aClass258_6260;
            this.method2014(113);
            this.method2002((byte)(-104));
            this.method1985(2);
            this.method2051(0, -58, Class300.aClass65_2499);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.A(" + n + ',' + ((aa != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    void method1773() {
        try {
            if (!this.aBoolean4550) {
                for (Class98 class98 = this.aClass148_4532.method2418(32); class98 != null; class98 = this.aClass148_4532.method2417(126)) {
                    ((za_Sub1)class98).method1677((byte)41);
                }
                final Enumeration<Canvas> keys = this.aHashtable4523.keys();
                while (keys.hasMoreElements()) {
                    final Canvas canvas = keys.nextElement();
                    this.method1936(0, this.aHashtable4523.get(canvas), canvas);
                }
                Class18.method248(true, 125, false);
                this.aNativeInterface4526.release();
                this.aBoolean4550 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.AB()");
        }
    }
    
    private final void method2020(final int n) {
        try {
            if (this.aClass76_4598 != null) {
                this.aClass76_4598.method738(-59);
            }
            this.method2007(false);
            if (n > -110) {
                this.aBoolean4621 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.PJ(" + n + ')');
        }
    }
    
    @Override
    final int method1822() {
        try {
            return this.anInt4565 - 2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.LG()");
        }
    }
    
    @Override
    final void da(final int n, final int n2, final int n3, final int[] array) {
        try {
            final float method2135 = this.aClass111_Sub3_4543.method2135(n2, n3, n, false);
            int anInt4587 = 0;
            int anInt4588 = 0;
            Label_0108: {
                if (method2135 >= -0.0078125f && method2135 <= 0.0078125f) {
                    anInt4587 = this.anInt4587;
                    anInt4588 = this.anInt4645;
                    if (!client.aBoolean3553) {
                        break Label_0108;
                    }
                }
                anInt4588 = (int)(this.anInt4593 * this.aClass111_Sub3_4543.method2126(n3, n, n2, 119) / method2135);
                anInt4587 = (int)(this.anInt4589 * this.aClass111_Sub3_4543.method2139(n2, n, n3, true) / method2135);
            }
            array[1] = (int)(anInt4587 - this.aFloat4610);
            array[0] = (int)(anInt4588 - this.aFloat4641);
            array[2] = (int)method2135;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.da(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    abstract void method2021(final int p0);
    
    private final void method2022(final byte b) {
        try {
            (this.anInterface2_Impl1_4652 = this.method2060(false, 71)).method74(-20279, 12, 3096);
            for (int n = 0; ~n > -5; ++n) {
                final Buffer method75 = this.anInterface2_Impl1_4652.method75(true, (byte)27);
                if (method75 != null) {
                    final Stream method76 = this.method2043(24022, method75);
                    method76.b(0.0f);
                    method76.b(0.0f);
                    method76.b(0.0f);
                    for (int n2 = 0; ~n2 >= -257; ++n2) {
                        final double n3 = 3.141592653589793 * (n2 * 2) / 256.0;
                        final float n4 = (float)Math.cos(n3);
                        final float n5 = (float)Math.sin(n3);
                        if (!Stream.a()) {
                            method76.a(n5);
                            method76.a(n4);
                            method76.a(0.0f);
                        }
                        else {
                            method76.b(n5);
                            method76.b(n4);
                            method76.b(0.0f);
                        }
                    }
                    method76.c();
                    if (this.anInterface2_Impl1_4652.method71(13623)) {
                        break;
                    }
                }
            }
            this.aClass256_4669 = this.method1982(new Class49[] { new Class49(Class169.aClass169_1294) }, 6);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.BJ(" + b + ')');
        }
    }
    
    final Class111_Sub3 method2023(final int n) {
        try {
            if (n != 1) {
                return null;
            }
            return this.aClass111_Sub3_4546;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.GH(" + n + ')');
        }
    }
    
    public static void method2024(final boolean b) {
        try {
            if (b) {
                ha_Sub3.aClass207_4528 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.EI(" + b + ')');
        }
    }
    
    @Override
    final Class111 method1821() {
        try {
            return new Class111_Sub3();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.SE()");
        }
    }
    
    abstract void method2025(final byte p0);
    
    @Override
    final void method1798(final int n) {
    }
    
    abstract void method2026(final int p0, final boolean p1, final byte p2, final Class65 p3, final boolean p4);
    
    final Class111_Sub3 method2027(final int n) {
        try {
            if (n != 0) {
                return null;
            }
            if (!this.aBoolean4621) {
                this.aClass111_Sub3_4547.method2130(this.aClass111_Sub3_4545, this.aClass111_Sub3_4542);
                this.aBoolean4621 = true;
            }
            return this.aClass111_Sub3_4547;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.VE(" + n + ')');
        }
    }
    
    final void method2028(final boolean aBoolean4562, final byte b) {
        try {
            if (!this.aBoolean4562 != !aBoolean4562) {
                this.aBoolean4562 = aBoolean4562;
                this.method1999((byte)112);
                this.anInt4633 &= 0xFFFFFFE0;
            }
            if (b != -101) {
                this.anInt4578 = 35;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.OJ(" + aBoolean4562 + ',' + b + ')');
        }
    }
    
    final void method2029(final byte b, final boolean aBoolean4637) {
        try {
            if (b > -37) {
                this.method2037(null, 65, (byte)115, 56);
            }
            if (this.aBoolean4637 == !aBoolean4637) {
                this.aBoolean4637 = aBoolean4637;
                this.method1955(-5668);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.FF(" + b + ',' + aBoolean4637 + ')');
        }
    }
    
    abstract void method2030(final byte p0);
    
    private final void method2031(final int n) {
        try {
            if (n == -12545) {
                this.aBoolean4574 = false;
                if (Class64_Sub4.aClass204_3649 == this.aClass204_4551) {
                    this.method1996(n ^ 0xFFFFD676);
                    this.method1962(-110);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.NI(" + n + ')');
        }
    }
    
    abstract Interface4_Impl2 method2032(final byte[] p0, final boolean p1, final Class164 p2, final int p3, final int p4, final boolean p5, final int p6, final int p7);
    
    @Override
    final int method1777(final int n, final int n2) {
        try {
            return n | n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.HF(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method1778(final int anInt4607) {
        try {
            if (~anInt4607 > -129 || ~anInt4607 < -1025) {
                throw new IllegalArgumentException();
            }
            if (this.aClass319_4624 != null) {
                this.aClass319_4624.method3659(-3);
            }
            this.anInt4607 = anInt4607;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.CH(" + anInt4607 + ')');
        }
    }
    
    @Override
    final void method1818(final int anInt4619, final Class98_Sub5[] array) {
        try {
            for (int n = 0; ~anInt4619 < ~n; ++n) {
                this.aClass98_Sub5Array4597[n] = array[n];
            }
            this.anInt4619 = anInt4619;
            if (this.aClass204_4551.method2708(100)) {
                this.method2035(-14713);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.GF(" + anInt4619 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    abstract void method2033(final int p0);
    
    @Override
    final Class332 method1770(final int[] array, final int n, final int n2, final int n3, final int n4, final boolean b) {
        try {
            return new Class332_Sub2(this, n3, n4, array, n, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.MF(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    @Override
    final Class332 method1797(final int n, final int n2, final int n3, final int n4, final boolean b) {
        try {
            final Class332_Sub2 class332_Sub2 = new Class332_Sub2(this, n3, n4, b);
            class332_Sub2.method3736(0, 0, n3, n4, n, n2);
            return class332_Sub2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.MG(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    final float[] method2034(final int n) {
        try {
            if (n >= -37) {
                method2062(96);
            }
            return this.aFloatArray4582;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.HI(" + n + ')');
        }
    }
    
    @Override
    final Class111 method1752() {
        try {
            return this.aClass111_Sub3_4543;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.NE()");
        }
    }
    
    void method2035(final int n) {
        try {
            this.anInt4628 = this.anInt4619;
            if (n != -14713) {
                this.aBoolean4571 = true;
            }
            this.anInt4619 = 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.BC(" + n + ')');
        }
    }
    
    @Override
    final boolean method1771() {
        try {
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.VG()");
        }
    }
    
    abstract void method2036(final int p0);
    
    abstract void method2037(final Class232 p0, final int p1, final byte p2, final int p3);
    
    private final void method2038(final byte b) {
        try {
            this.aBoolean4570 = false;
            if (b >= 103) {
                this.method2000((byte)(-117));
                if (Class149.aClass204_1206 == this.aClass204_4551) {
                    this.method1962(-107);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.GI(" + b + ')');
        }
    }
    
    @Override
    final void Q(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final byte[] array, final int n7, final int n8) {
    }
    
    final void method2039(final boolean b, final int n, final int anInt4583, final boolean b2) {
        try {
            if (n != 0) {
                this.aFloat4568 = 0.2778904f;
            }
            if (anInt4583 != this.anInt4583 || !this.aBoolean4567 != !this.aBoolean4563) {
                Interface4 method3661 = null;
                int anInt4584 = 0;
                int aByte1816 = 0;
                int anInt4585 = 0;
                int aByte1817 = this.aBoolean4563 ? 3 : 0;
                if (anInt4583 >= 0) {
                    method3661 = this.aClass319_4624.method3661(0, anInt4583);
                    final Class238 method3662 = super.aD938.method11(anInt4583, n - 28755);
                    if (method3662.aByte1823 != 0 || ~method3662.aByte1837 != -1) {
                        final int n2 = 50 * (method3662.aBoolean1822 ? 64 : 128);
                        this.method1957((byte)61).method2119(0.0f, 1, this.anInt4556 % n2 * method3662.aByte1837 / n2, method3662.aByte1823 * (this.anInt4556 % n2) / n2);
                        this.method2008(Class246_Sub3_Sub4_Sub5.aClass258_6260, (byte)114);
                    }
                    else {
                        this.method1985(2);
                    }
                    anInt4584 = method3662.anInt1821;
                    if (!this.aBoolean4563) {
                        aByte1816 = method3662.aByte1816;
                        aByte1817 = method3662.aByte1820;
                        anInt4585 = method3662.anInt1835;
                    }
                }
                else {
                    this.method1985(2);
                }
                this.method2045((byte)(-113), anInt4585, aByte1817, b, b2, aByte1816);
                if (this.aClass76_4598 == null) {
                    this.method2005(method3661, 23);
                    this.method2015(anInt4584, (byte)118);
                }
                else {
                    this.aClass76_4598.method742(6, anInt4584, method3661);
                }
                this.anInt4583 = anInt4583;
                this.aBoolean4567 = this.aBoolean4563;
            }
            this.anInt4633 &= 0xFFFFFFF8;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.NH(" + b + ',' + n + ',' + anInt4583 + ',' + b2 + ')');
        }
    }
    
    final float[] method2040(final float[] array, final int n) {
        try {
            array[4] = this.aFloatArray4566[1];
            array[8] = this.aFloatArray4566[2];
            array[0] = this.aFloatArray4566[0];
            array[12] = this.aFloatArray4566[3];
            array[5] = this.aFloatArray4566[5];
            array[13] = this.aFloatArray4566[7];
            if (n != 32227) {
                return null;
            }
            array[9] = this.aFloatArray4566[6];
            array[2] = this.aFloatArray4566[8];
            array[1] = this.aFloatArray4566[4];
            array[3] = this.aFloatArray4566[12];
            array[10] = this.aFloatArray4566[10];
            array[14] = this.aFloatArray4566[11];
            array[6] = this.aFloatArray4566[9];
            array[7] = this.aFloatArray4566[13];
            array[15] = this.aFloatArray4566[15];
            array[11] = this.aFloatArray4566[14];
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.SF(" + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    private final void method2041(final int n) {
        try {
            (this.anInterface2_Impl1_4667 = this.method2060(true, 117)).method74(-20279, 12, 24);
            this.aClass256_4653 = this.method1982(new Class49[] { new Class49(Class169.aClass169_1294) }, 6);
            if (n >= -62) {
                this.Q(-59, -44, 25, -91, -71, -40, null, 99, 111);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.LJ(" + n + ')');
        }
    }
    
    @Override
    final void method1786(final Canvas canvas) {
        try {
            if (canvas == this.aCanvas4533) {
                throw new RuntimeException();
            }
            if (this.aHashtable4523.containsKey(canvas)) {
                this.method1936(0, this.aHashtable4523.get(canvas), canvas);
                this.aHashtable4523.remove(canvas);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.KG(" + ((canvas != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final int XA() {
        try {
            return this.anInt4605;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.XA()");
        }
    }
    
    abstract void method2042(final Class256 p0, final byte p1);
    
    @Override
    final void K(final int[] array) {
        try {
            array[0] = this.anInt4602;
            array[2] = this.anInt4575;
            array[1] = this.anInt4558;
            array[3] = this.anInt4638;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.K(" + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method1747() {
        try {
            return this.aClass76Array4613[3].method745((byte)27);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.HJ()");
        }
    }
    
    @Override
    final boolean method1766() {
        try {
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.NG()");
        }
    }
    
    final Stream method2043(final int n, final Buffer buffer) {
        try {
            this.aStream4603.a(buffer);
            if (n != 24022) {
                this.aFloat4642 = -2.1684475f;
            }
            return this.aStream4603;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.RH(" + n + ',' + ((buffer != null) ? "{...}" : "null") + ')');
        }
    }
    
    abstract Interface4_Impl1 method2044(final int p0, final Class164 p1, final byte[] p2, final int p3, final int p4, final int p5);
    
    private final void method2045(final byte b, int n, int anInt4612, boolean b2, final boolean b3, int n2) {
        try {
            b2 &= this.method1747();
            if (!b2 && (~anInt4612 == 0xFFFFFFFB || ~anInt4612 == 0xFFFFFFF7 || ~anInt4612 == 0xFFFFFFF6)) {
                anInt4612 = 2;
                n = ((~anInt4612 != 0xFFFFFFFB) ? 1 : (0x1 & n2));
                n2 = 0;
            }
            if (b >= -93) {
                this.method1945((byte)95, true);
            }
            if (anInt4612 != 0 && b3) {
                anInt4612 |= Integer.MIN_VALUE;
            }
            if (this.anInt4612 != anInt4612) {
                if (this.anInt4612 != 0) {
                    this.aClass76Array4613[Integer.MAX_VALUE & this.anInt4612].method739(-2);
                }
                Label_0187: {
                    if (anInt4612 != 0) {
                        (this.aClass76_4598 = this.aClass76Array4613[Integer.MAX_VALUE & anInt4612]).method748(69, b3);
                        this.aClass76_4598.method743(97, b3);
                        this.aClass76_4598.method746(n2, n, -121);
                        if (!client.aBoolean3553) {
                            break Label_0187;
                        }
                    }
                    this.aClass76_4598 = null;
                }
                this.anInt4635 = n2;
                this.anInt4595 = n;
                this.anInt4612 = anInt4612;
            }
            else if (~this.anInt4612 != -1) {
                this.aClass76Array4613[this.anInt4612 & Integer.MAX_VALUE].method743(99, b3);
                if (~n2 != ~this.anInt4635 || ~n != ~this.anInt4595) {
                    this.aClass76Array4613[Integer.MAX_VALUE & this.anInt4612].method746(n2, n, -125);
                    this.anInt4635 = n2;
                    this.anInt4595 = n;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.TH(" + b + ',' + n + ',' + anInt4612 + ',' + b2 + ',' + b3 + ',' + n2 + ')');
        }
    }
    
    abstract void method2046(final int p0);
    
    abstract void method2047(final int p0, final boolean p1, final byte p2, final Class65 p3);
    
    private final void method2048(final byte b) {
        try {
            this.aFloat4647 = this.anInt4575 - this.anInt4645;
            this.aFloat4610 = this.anInt4558 + -this.anInt4587;
            if (b != 32) {
                this.aClass146_Sub3_4649 = null;
            }
            this.aFloat4641 = this.anInt4602 - this.anInt4645;
            this.aFloat4584 = -this.anInt4587 + this.anInt4638;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.OG(" + b + ')');
        }
    }
    
    final void method2049(final int n, final Class111_Sub3 class111_Sub3) {
        try {
            this.aClass111_Sub3_4542.method2092(class111_Sub3);
            this.aBoolean4540 = false;
            this.method2055(n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.HE(" + n + ',' + ((class111_Sub3 != null) ? "{...}" : "null") + ')');
        }
    }
    
    abstract float method2050(final byte p0);
    
    @Override
    final void method1741(final Canvas canvas, final int n, final int n2) {
        try {
            if (canvas == this.aCanvas4533) {
                throw new RuntimeException();
            }
            if (!this.aHashtable4523.containsKey(canvas)) {
                if (!canvas.isShowing()) {
                    throw new RuntimeException();
                }
                try {
                    Class.forName("java.awt.Canvas").getMethod("setIgnoreRepaint", Boolean.TYPE).invoke(canvas, Boolean.TRUE);
                }
                catch (Exception ex2) {}
                final Object method2058 = this.method2058(canvas, -8401);
                if (method2058 == null) {
                    throw new RuntimeException();
                }
                this.aHashtable4523.put(canvas, method2058);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.FI(" + ((canvas != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method1751(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final int n11, final int n12, final int n13) {
    }
    
    @Override
    final void method1801(final int[] array) {
        try {
            array[1] = this.anInt4531;
            array[0] = this.anInt4527;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.UD(" + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final Class43 method1804(final Class197 class197, final Class324[] array, final boolean b) {
        try {
            return new Class43_Sub2(this, class197, array, b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.HH(" + ((class197 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method2051(final int n, final int n2, final Class65 class65) {
        try {
            if (n2 >= -52) {
                this.method1940((byte)56);
            }
            this.method2026(n, false, (byte)27, class65, false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.BH(" + n + ',' + n2 + ',' + ((class65 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final int c(final int n, final int n2) {
        try {
            return n2 ^ (n2 & n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.JH(" + n + ',' + n2 + ')');
        }
    }
    
    final void method2052(final boolean b) {
        try {
            if (!b) {
                if (~this.anInt4633 != 0xFFFFFFFD) {
                    this.method1993((byte)103);
                    this.method2028(false, (byte)(-101));
                    this.method1979(false, -82);
                    this.method2013(false, 2103);
                    this.method1997(0, false);
                    this.method2039(false, 0, -2, false);
                    this.anInt4633 = 2;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.PI(" + b + ')');
        }
    }
    
    final Interface4_Impl2 method2053(final int n, final Class164 class164, final byte b, final byte[] array, final boolean b2, final int n2) {
        try {
            if (b != 87) {
                this.A(16, null, 0, -16);
            }
            return this.method2032(array, false, class164, 0, n2, b2, 0, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.PE(" + n + ',' + ((class164 != null) ? "{...}" : "null") + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + b2 + ',' + n2 + ')');
        }
    }
    
    @Override
    final aa method1772(final int n, final int n2, final int[] array, final int[] array2) {
        try {
            return Canvas_Sub1.method120(n2, this, n, array, 107, array2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.AG(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method1820(final Class242 class242) {
        try {
            this.aClass81_4634.method818((byte)(-108), class242, -1, this);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.DE(" + ((class242 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final int E() {
        try {
            return this.anInt4538 - -this.anInt4541 - -this.anInt4539;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.E()");
        }
    }
    
    private final void method2054(final boolean b, final int n, final Class232 class232) {
        try {
            if (!b) {
                this.method1971(0, true, this.anInterface2_Impl1_4660);
                this.method2042(this.aClass256_4655, (byte)(-91));
                this.method2037(class232, 0, (byte)44, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.GG(" + b + ',' + n + ',' + ((class232 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method1812() {
        try {
            if (this.aClass319_4624 != null) {
                this.aClass319_4624.method3659(-3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.PF()");
        }
    }
    
    @Override
    final void P(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            this.method1795(n, n2, n, n2 - -n3, n4, n5);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.P(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    @Override
    final void method1779(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            final float method2050 = this.method2050((byte)56);
            this.method1946(1);
            this.method1984(2, n5);
            this.method2051(0, -73, Class64_Sub16.aClass65_3681);
            this.method1953(-95, Class64_Sub16.aClass65_3681, 0);
            this.method2001(n6, 86);
            this.aClass111_Sub3_4542.method2137(-1 + n4, (byte)(-109), n3 - 1, 1.0f);
            this.aClass111_Sub3_4542.method2141(-108, n2 - method2050, 0.0f, -method2050 + n);
            this.method1935(1);
            this.method2059(false, false);
            this.method2054(false, 4, Class287_Sub1.aClass232_3420);
            this.method2059(true, false);
            this.method1953(-75, Class300.aClass65_2499, 0);
            this.method2051(0, -106, Class300.aClass65_2499);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.OE(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    private final void method2055(final int n) {
        try {
            if (Class64_Sub4.aClass204_3649 == this.aClass204_4551) {
                final float method2050 = this.method2050((byte)56);
                this.aClass111_Sub3_4542.method2141(-108, method2050, 0.0f, method2050);
            }
            if (n != 0) {
                this.aClass111_Sub3_4547 = null;
            }
            this.aBoolean4621 = false;
            this.method1983((byte)121);
            if (this.aClass76_4598 != null) {
                this.aClass76_4598.method741((byte)(-112));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.AJ(" + n + ')');
        }
    }
    
    final void method2056(final int n, final float aFloat4561) {
        try {
            if (aFloat4561 != this.aFloat4561) {
                this.aFloat4561 = aFloat4561;
                this.method2038((byte)118);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.IH(" + n + ',' + aFloat4561 + ')');
        }
    }
    
    @Override
    final void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final aa aa, final int n7, final int n8) {
    }
    
    @Override
    final void H(final int n, final int n2, final int n3, final int[] array) {
        try {
            final float method2135 = this.aClass111_Sub3_4543.method2135(n2, n3, n, false);
            int anInt4645 = 0;
            int anInt4646 = 0;
            Label_0108: {
                if (method2135 < -0.0078125f || method2135 > 0.0078125f) {
                    anInt4645 = (int)(this.anInt4593 * this.aClass111_Sub3_4543.method2126(n3, n, n2, 119) / method2135);
                    anInt4646 = (int)(this.anInt4589 * this.aClass111_Sub3_4543.method2139(n2, n, n3, true) / method2135);
                    if (!client.aBoolean3553) {
                        break Label_0108;
                    }
                }
                anInt4645 = this.anInt4645;
                anInt4646 = this.anInt4587;
            }
            array[2] = (int)method2135;
            array[0] = (int)(anInt4645 - this.aFloat4641);
            array[1] = (int)(-this.aFloat4610 + anInt4646);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.H(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    abstract void method2057(final int p0);
    
    abstract Object method2058(final Canvas p0, final int p1);
    
    abstract void method2059(final boolean p0, final boolean p1);
    
    abstract Interface2_Impl1 method2060(final boolean p0, final int p1);
    
    final Class111_Sub3 method2061(final int n) {
        try {
            if (n != 1) {
                this.aClass256_4669 = null;
            }
            return this.aClass111_Sub3Array4609[this.anInt4579];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.MI(" + n + ')');
        }
    }
    
    static final boolean method2062(final int n) {
        try {
            boolean b = true;
            if (n != -4264) {
                method2062(-113);
            }
            if (Class42_Sub2.aClass324_5359 == null) {
                if (Class332_Sub2.aClass207_5423.method2742(n ^ 0x10C4, Class277.anInt2050)) {
                    Class42_Sub2.aClass324_5359 = Class324.method3683(Class332_Sub2.aClass207_5423, Class277.anInt2050);
                }
                else {
                    b = false;
                }
            }
            if (Class231.aClass324_1733 == null) {
                if (!Class332_Sub2.aClass207_5423.method2742(n ^ 0x10FB, Class302.anInt2524)) {
                    b = false;
                }
                else {
                    Class231.aClass324_1733 = Class324.method3683(Class332_Sub2.aClass207_5423, Class302.anInt2524);
                }
            }
            if (Class64_Sub26.aClass324_3713 == null) {
                if (!Class332_Sub2.aClass207_5423.method2742(-111, Class98_Sub10_Sub38.anInt5751)) {
                    b = false;
                }
                else {
                    Class64_Sub26.aClass324_3713 = Class324.method3683(Class332_Sub2.aClass207_5423, Class98_Sub10_Sub38.anInt5751);
                }
            }
            if (Class121.aClass197_1004 == null) {
                if (Class36.aClass207_348.method2742(-94, Class246_Sub10.anInt5153)) {
                    Class121.aClass197_1004 = Class119_Sub1.method2182(Class36.aClass207_348, true, Class246_Sub10.anInt5153);
                }
                else {
                    b = false;
                }
            }
            if (Class98_Sub10_Sub7.aClass324Array5575 == null) {
                if (!Class332_Sub2.aClass207_5423.method2742(-123, Class246_Sub10.anInt5153)) {
                    b = false;
                }
                else {
                    Class98_Sub10_Sub7.aClass324Array5575 = Class324.method3684(Class332_Sub2.aClass207_5423, Class246_Sub10.anInt5153);
                }
            }
            return b;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.DF(" + n + ')');
        }
    }
    
    abstract Interface4_Impl2 method2063(final int p0, final byte p1, final int[] p2, final boolean p3, final int p4, final int p5, final int p6);
    
    @Override
    final int r(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            int n8 = 0;
            final float n9 = n3 * this.aClass111_Sub3_4543.aFloat4704 + (n * this.aClass111_Sub3_4543.aFloat4708 + n2 * this.aClass111_Sub3_4543.aFloat4713) + this.aClass111_Sub3_4543.aFloat4703;
            final float n10 = n4 * this.aClass111_Sub3_4543.aFloat4708 + n5 * this.aClass111_Sub3_4543.aFloat4713 + n6 * this.aClass111_Sub3_4543.aFloat4704 + this.aClass111_Sub3_4543.aFloat4703;
            if (this.anInt4640 <= n9 || n10 >= this.anInt4640) {
                if (this.anInt4605 < n9 && n10 > this.anInt4605) {
                    n8 |= 0x20;
                }
            }
            else {
                n8 |= 0x10;
            }
            final int n11 = (int)((this.aClass111_Sub3_4543.aFloat4705 * n3 + (n2 * this.aClass111_Sub3_4543.aFloat4711 + n * this.aClass111_Sub3_4543.aFloat4712) + this.aClass111_Sub3_4543.aFloat4702) * this.anInt4593 / n7);
            final int n12 = (int)(this.anInt4593 * (this.aClass111_Sub3_4543.aFloat4712 * n4 + n5 * this.aClass111_Sub3_4543.aFloat4711 + n6 * this.aClass111_Sub3_4543.aFloat4705 + this.aClass111_Sub3_4543.aFloat4702) / n7);
            if (n11 < this.aFloat4641 && this.aFloat4641 > n12) {
                n8 |= 0x1;
            }
            else if (n11 > this.aFloat4647 && this.aFloat4647 < n12) {
                n8 |= 0x2;
            }
            final int n13 = (int)(this.anInt4589 * (this.aClass111_Sub3_4543.aFloat4710 * n3 + (this.aClass111_Sub3_4543.aFloat4714 * n + this.aClass111_Sub3_4543.aFloat4706 * n2) + this.aClass111_Sub3_4543.aFloat4709) / n7);
            final int n14 = (int)(this.anInt4589 * (this.aClass111_Sub3_4543.aFloat4710 * n6 + (n4 * this.aClass111_Sub3_4543.aFloat4714 + n5 * this.aClass111_Sub3_4543.aFloat4706) + this.aClass111_Sub3_4543.aFloat4709) / n7);
            if (n13 < this.aFloat4610 && this.aFloat4610 > n14) {
                n8 |= 0x4;
            }
            else if (this.aFloat4584 < n13 && this.aFloat4584 < n14) {
                n8 |= 0x8;
            }
            return n8;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.r(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    final void method2064(final byte b) {
        try {
            if (b != -61) {
                this.anInterface2_Impl1_4652 = null;
            }
            final Hashtable<Canvas, Object> aHashtable4523 = new Hashtable<Canvas, Object>();
            if (this.aHashtable4523 != null && !this.aHashtable4523.isEmpty()) {
                final Enumeration<Canvas> keys = this.aHashtable4523.keys();
                while (keys.hasMoreElements()) {
                    final Canvas canvas = keys.nextElement();
                    aHashtable4523.put(canvas, this.method2058(canvas, -8401));
                }
            }
            this.aHashtable4523 = aHashtable4523;
            this.method1995(b ^ 0xFFFFFFCA);
            this.method2041(b ^ 0x52);
            this.method2022((byte)(-107));
            this.aClass81_4634.method812(this, (byte)36);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.WE(" + b + ')');
        }
    }
    
    abstract void method2065(final byte p0);
    
    final Interface4_Impl2 method2066(final Class164 class164, final boolean b, final float[] array, final boolean b2, final int n, final int n2) {
        try {
            if (b2) {
                this.aFloat4591 = 1.1575497f;
            }
            return this.method1968(0, n, b, class164, -8, n2, 0, array);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.QI(" + ((class164 != null) ? "{...}" : "null") + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + b2 + ',' + n + ',' + n2 + ')');
        }
    }
    
    Class76 method2067(final int n, final byte b) {
        try {
            if (n != 6) {
                if (n == 1) {
                    return new Class76_Sub4(this);
                }
                if (n == 2) {
                    return new Class76_Sub7(this, this.aClass195_4529);
                }
                if (~n != 0xFFFFFFF8) {
                    return new Class76_Sub11(this);
                }
                if (!client.aBoolean3553) {
                    return new Class76_Sub5(this);
                }
            }
            return new Class76_Sub10(this);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uv.CD(" + n + ',' + b + ')');
        }
    }
}
