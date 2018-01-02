// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub9 extends Class246
{
    Class87 aClass87_5131;
    Class92 aClass92_5132;
    Class246_Sub5 aClass246_Sub5_5133;
    private long aLong5134;
    int anInt5135;
    Class216 aClass216_5136;
    private int anInt5137;
    static Class79 aClass79_5138;
    boolean aBoolean5139;
    Class218 aClass218_5140;
    static int[][] anIntArrayArray5141;
    private Class216 aClass216_5142;
    private int anInt5143;
    private int anInt5144;
    private int anInt5145;
    private int anInt5146;
    private int anInt5147;
    private int anInt5148;
    private int anInt5149;
    private boolean aBoolean5150;
    
    final void method3134(final long n, final ha ha, final byte b) {
        try {
            for (Class246_Sub4_Sub2_Sub1 class246_Sub4_Sub2_Sub1 = (Class246_Sub4_Sub2_Sub1)this.aClass218_5140.method2803((byte)15); class246_Sub4_Sub2_Sub1 != null; class246_Sub4_Sub2_Sub1 = (Class246_Sub4_Sub2_Sub1)this.aClass218_5140.method2809(false)) {
                class246_Sub4_Sub2_Sub1.method3111(ha, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qba.C(" + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method3135(final ha ha, boolean b, final long n, final int n2, final int n3) {
        try {
            if (!this.aBoolean5139) {
                if (this.aClass92_5132.anInt752 <= Class337_Sub1.anInt5497) {
                    if (Class337.anIntArray3536[Class337_Sub1.anInt5497] < Class237_Sub1.anInt5047) {
                        b = false;
                    }
                    else if (this.aBoolean5150) {
                        b = false;
                    }
                    else if (~this.aClass92_5132.anInt762 != 0x0) {
                        int n4 = (int)(-this.aLong5134 + n);
                        if (!this.aClass92_5132.aBoolean783 && ~n4 < ~this.aClass92_5132.anInt762) {
                            b = false;
                        }
                        else {
                            n4 %= this.aClass92_5132.anInt762;
                        }
                        if (!this.aClass92_5132.aBoolean732 && n4 < this.aClass92_5132.anInt746) {
                            b = false;
                        }
                        if (this.aClass92_5132.aBoolean732 && ~this.aClass92_5132.anInt746 >= ~n4) {
                            b = false;
                        }
                    }
                }
                else {
                    b = false;
                }
            }
            else {
                b = false;
            }
            if (b) {
                ++Class246_Sub3_Sub1_Sub2.anInt6252;
                final int anInt1617 = (this.aClass216_5136.anInt1619 + this.aClass216_5136.anInt1624 - -this.aClass216_5136.anInt1623) / 3;
                final int anInt1618 = (this.aClass216_5136.anInt1620 + this.aClass216_5136.anInt1627 - -this.aClass216_5136.anInt1625) / 3;
                final int anInt1619 = (this.aClass216_5136.anInt1626 + (this.aClass216_5136.anInt1629 - -this.aClass216_5136.anInt1628)) / 3;
                if (~anInt1617 != ~this.aClass216_5136.anInt1617 || anInt1618 != this.aClass216_5136.anInt1618 || this.aClass216_5136.anInt1621 != anInt1619) {
                    this.aClass216_5136.anInt1617 = anInt1617;
                    this.aClass216_5136.anInt1618 = anInt1618;
                    this.aClass216_5136.anInt1621 = anInt1619;
                    final int n5 = this.aClass216_5136.anInt1624 - this.aClass216_5136.anInt1619;
                    final int n6 = this.aClass216_5136.anInt1627 + -this.aClass216_5136.anInt1620;
                    final int n7 = this.aClass216_5136.anInt1628 + -this.aClass216_5136.anInt1629;
                    final int n8 = -this.aClass216_5136.anInt1619 + this.aClass216_5136.anInt1623;
                    final int n9 = -this.aClass216_5136.anInt1620 + this.aClass216_5136.anInt1625;
                    final int n10 = -this.aClass216_5136.anInt1629 + this.aClass216_5136.anInt1626;
                    this.anInt5144 = -(n8 * n6) + n9 * n5;
                    this.anInt5146 = -(n7 * n9) + n6 * n10;
                    this.anInt5143 = n7 * n8 - n5 * n10;
                    while (~this.anInt5146 < -32768 || ~this.anInt5143 < -32768 || this.anInt5144 > 32767 || ~this.anInt5146 > 32766 || ~this.anInt5143 > 32766 || this.anInt5144 < -32767) {
                        this.anInt5144 >>= 1;
                        this.anInt5146 >>= 1;
                        this.anInt5143 >>= 1;
                    }
                    int n11 = (int)Math.sqrt(this.anInt5144 * this.anInt5144 + this.anInt5146 * this.anInt5146 - -(this.anInt5143 * this.anInt5143));
                    if (~n11 >= -1) {
                        n11 = 1;
                    }
                    this.anInt5144 = 32767 * this.anInt5144 / n11;
                    this.anInt5143 = this.anInt5143 * 32767 / n11;
                    this.anInt5146 = this.anInt5146 * 32767 / n11;
                    if (this.aClass92_5132.aShort786 > 0 || this.aClass92_5132.aShort754 > 0) {
                        final int n12 = (int)(2607.5945876176133 * Math.atan2(this.anInt5144, this.anInt5146));
                        final int n13 = (int)(Math.atan2(this.anInt5143, Math.sqrt(this.anInt5144 * this.anInt5144 + this.anInt5146 * this.anInt5146)) * 2607.5945876176133);
                        this.anInt5148 = -this.aClass92_5132.aShort747 + this.aClass92_5132.aShort786;
                        this.anInt5149 = n12 - -this.aClass92_5132.aShort747 + -(this.anInt5148 >> 1768988289);
                        this.anInt5145 = this.aClass92_5132.aShort754 - this.aClass92_5132.aShort763;
                        this.anInt5147 = this.aClass92_5132.aShort763 + (n13 + -(this.anInt5145 >> 981292577));
                    }
                }
                this.anInt5137 += (int)(n3 * (Math.random() * (this.aClass92_5132.anInt790 + -this.aClass92_5132.anInt750) + this.aClass92_5132.anInt750));
                if (~this.anInt5137 < -64) {
                    final int n14 = this.anInt5137 >> 759811430;
                    this.anInt5137 &= 0x3F;
                    for (int n15 = 0; ~n15 > ~n14; ++n15) {
                        int anInt1620;
                        int anInt1621;
                        int anInt1622;
                        if (this.aClass92_5132.aShort786 > 0 || this.aClass92_5132.aShort754 > 0) {
                            final int n16 = (int)(Math.random() * this.anInt5148) + this.anInt5149 & 0x3FFF;
                            final int n17 = Class284_Sub2_Sub2.anIntArray6200[n16];
                            final int n18 = Class284_Sub2_Sub2.anIntArray6202[n16];
                            final int n19 = (int)(this.anInt5145 * Math.random()) + this.anInt5147 & 0x1FFF;
                            final int n20 = Class284_Sub2_Sub2.anIntArray6200[n19];
                            final int n21 = Class284_Sub2_Sub2.anIntArray6202[n19];
                            final int n22 = 13;
                            anInt1620 = n18 * n20 >> n22;
                            anInt1621 = (n21 << 515344801) * -1;
                            anInt1622 = n17 * n20 >> n22;
                        }
                        else {
                            anInt1620 = this.anInt5146;
                            anInt1621 = this.anInt5143;
                            anInt1622 = this.anInt5144;
                        }
                        float n23 = (float)Math.random();
                        float n24 = (float)Math.random();
                        if (n23 + n24 > 1.0f) {
                            n23 = 1.0f - n23;
                            n24 = -n24 + 1.0f;
                        }
                        final float n25 = 1.0f - (n24 + n23);
                        final int n26 = (int)(this.aClass216_5136.anInt1624 * n24 + this.aClass216_5136.anInt1619 * n23 + this.aClass216_5136.anInt1623 * n25);
                        final int n27 = (int)(n25 * this.aClass216_5136.anInt1625 + (this.aClass216_5136.anInt1620 * n23 + n24 * this.aClass216_5136.anInt1627));
                        final int n28 = (int)(this.aClass216_5136.anInt1626 * n25 + (this.aClass216_5136.anInt1629 * n23 + this.aClass216_5136.anInt1628 * n24));
                        final int n29 = (int)(this.aClass216_5142.anInt1619 * n23 + n24 * this.aClass216_5142.anInt1624 + n25 * this.aClass216_5142.anInt1623);
                        final int n30 = (int)(n25 * this.aClass216_5142.anInt1625 + (this.aClass216_5142.anInt1627 * n24 + this.aClass216_5142.anInt1620 * n23));
                        final int n31 = (int)(n24 * this.aClass216_5142.anInt1628 + n23 * this.aClass216_5142.anInt1629 + this.aClass216_5142.anInt1626 * n25);
                        final int n32 = -n29 + n26;
                        final int n33 = -n30 + n27;
                        final int n34 = n28 + -n31;
                        final int n35 = (int)(n32 * Math.random() + n29);
                        final int n36 = (int)(n30 + n33 * Math.random());
                        final int n37 = (int)(n31 + Math.random() * n34);
                        final int n38 = (int)(Math.random() * (-this.aClass92_5132.anInt770 + this.aClass92_5132.anInt731)) + this.aClass92_5132.anInt770;
                        final int n39 = (int)(Math.random() * (this.aClass92_5132.anInt787 - this.aClass92_5132.anInt766)) + this.aClass92_5132.anInt766;
                        final int n40 = this.aClass92_5132.anInt780 - -(int)(Math.random() * (this.aClass92_5132.anInt788 - this.aClass92_5132.anInt780));
                        int n41;
                        if (!this.aClass92_5132.aBoolean759) {
                            n41 = ((int)(Math.random() * this.aClass92_5132.anInt765 + this.aClass92_5132.anInt756) << -1210811208 | ((int)(this.aClass92_5132.anInt741 + Math.random() * this.aClass92_5132.anInt730) << -752155600 | (int)(Math.random() * this.aClass92_5132.anInt734 + this.aClass92_5132.anInt757) << 891035144 | (int)(this.aClass92_5132.anInt771 + this.aClass92_5132.anInt737 * Math.random())));
                        }
                        else {
                            final double random = Math.random();
                            n41 = ((int)(this.aClass92_5132.anInt741 + random * this.aClass92_5132.anInt730) << 1742099248 | (int)(this.aClass92_5132.anInt734 * random + this.aClass92_5132.anInt757) << -1937900184 | (int)(random * this.aClass92_5132.anInt737 + this.aClass92_5132.anInt771) | (int)(Math.random() * this.aClass92_5132.anInt765 + this.aClass92_5132.anInt756) << -1050005608);
                        }
                        int anInt1623 = this.aClass92_5132.anInt729;
                        if (!ha.method1780() && !this.aClass92_5132.aBoolean791) {
                            anInt1623 = -1;
                        }
                        if (~Class351.anInt2922 != ~Class361.anInt3089) {
                            final Class246_Sub4_Sub2_Sub1 class246_Sub4_Sub2_Sub1 = Class185.aClass246_Sub4_Sub2_Sub1Array1445[Class351.anInt2922];
                            Class351.anInt2922 = (Class351.anInt2922 + 1 & 0x3FF);
                            class246_Sub4_Sub2_Sub1.method3112(this, n35, n36, n37, anInt1620, anInt1621, anInt1622, n38, n39, n41, n40, anInt1623, this.aClass92_5132.aBoolean753, this.aClass92_5132.aBoolean778);
                        }
                        else {
                            final Class246_Sub4_Sub2_Sub1 class246_Sub4_Sub2_Sub2 = new Class246_Sub4_Sub2_Sub1(this, n35, n36, n37, anInt1620, anInt1621, anInt1622, n38, n39, n41, n40, anInt1623, this.aClass92_5132.aBoolean753, this.aClass92_5132.aBoolean778);
                        }
                    }
                }
            }
            if (!this.aClass216_5136.method2795(this.aClass216_5142, true)) {
                final Class216 aClass216_5142 = this.aClass216_5142;
                this.aClass216_5142 = this.aClass216_5136;
                this.aClass216_5136 = aClass216_5142;
                this.aClass216_5136.anInt1620 = this.aClass87_5131.anInt668;
                this.aClass216_5136.anInt1626 = this.aClass87_5131.anInt662;
                this.aClass216_5136.anInt1621 = this.aClass216_5142.anInt1621;
                this.aClass216_5136.anInt1623 = this.aClass87_5131.anInt659;
                this.aClass216_5136.anInt1619 = this.aClass87_5131.anInt670;
                this.aClass216_5136.anInt1628 = this.aClass87_5131.anInt656;
                this.aClass216_5136.anInt1629 = this.aClass87_5131.anInt671;
                this.aClass216_5136.anInt1627 = this.aClass87_5131.anInt664;
                this.aClass216_5136.anInt1617 = this.aClass216_5142.anInt1617;
                this.aClass216_5136.anInt1625 = this.aClass87_5131.anInt669;
                this.aClass216_5136.anInt1618 = this.aClass216_5142.anInt1618;
                this.aClass216_5136.anInt1624 = this.aClass87_5131.anInt663;
            }
            if (n2 == -64) {
                this.anInt5135 = 0;
                for (Class246_Sub4_Sub2_Sub1 class246_Sub4_Sub2_Sub3 = (Class246_Sub4_Sub2_Sub1)this.aClass218_5140.method2803((byte)15); class246_Sub4_Sub2_Sub3 != null; class246_Sub4_Sub2_Sub3 = (Class246_Sub4_Sub2_Sub1)this.aClass218_5140.method2809(false)) {
                    class246_Sub4_Sub2_Sub3.method3109(n, n3);
                    ++this.anInt5135;
                }
                Class113.anInt952 += this.anInt5135;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qba.F(" + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final Class42_Sub1_Sub1 method3136(final ha_Sub1 ha_Sub1, final boolean b, final int[] array, final int n, final byte b2, final int n2, final int n3, final int n4) {
        try {
            if (b2 != 120) {
                return null;
            }
            if (ha_Sub1.aBoolean4426 || (Class81.method815(n, b2 - 120) && Class81.method815(n4, b2 - 120))) {
                return new Class42_Sub1_Sub1(ha_Sub1, 3553, n, n4, b, array, n2, n3);
            }
            if (ha_Sub1.aBoolean4378) {
                return new Class42_Sub1_Sub1(ha_Sub1, 34037, n, n4, b, array, n2, n3);
            }
            return new Class42_Sub1_Sub1(ha_Sub1, n, n4, Class48.method453(423660257, n), Class48.method453(423660257, n4), array);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qba.B(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ',' + b2 + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    public static void method3137(final int n) {
        try {
            if (n == -6086) {
                Class246_Sub9.aClass79_5138 = null;
                Class246_Sub9.anIntArrayArray5141 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qba.A(" + n + ')');
        }
    }
    
    final void method3138(final int n) {
        try {
            this.aClass216_5136.anInt1629 = this.aClass87_5131.anInt671;
            this.aClass216_5136.anInt1624 = this.aClass87_5131.anInt663;
            this.aClass216_5136.anInt1626 = this.aClass87_5131.anInt662;
            if (n == -1) {
                this.aClass216_5136.anInt1620 = this.aClass87_5131.anInt668;
                this.aClass216_5136.anInt1619 = this.aClass87_5131.anInt670;
                this.aClass216_5136.anInt1627 = this.aClass87_5131.anInt664;
                this.aClass216_5136.anInt1625 = this.aClass87_5131.anInt669;
                this.aClass216_5136.anInt1623 = this.aClass87_5131.anInt659;
                this.aClass216_5136.anInt1628 = this.aClass87_5131.anInt656;
                if (this.aClass216_5136.anInt1624 == this.aClass216_5136.anInt1619 && this.aClass216_5136.anInt1623 == this.aClass216_5136.anInt1624 && this.aClass216_5136.anInt1627 == this.aClass216_5136.anInt1620 && ~this.aClass216_5136.anInt1627 == ~this.aClass216_5136.anInt1625 && ~this.aClass216_5136.anInt1628 == ~this.aClass216_5136.anInt1629 && this.aClass216_5136.anInt1626 == this.aClass216_5136.anInt1628) {
                    this.aBoolean5150 = true;
                }
                else if (this.aBoolean5150) {
                    this.aClass216_5142.anInt1623 = this.aClass216_5136.anInt1623;
                    this.aClass216_5142.anInt1625 = this.aClass216_5136.anInt1625;
                    this.aClass216_5142.anInt1624 = this.aClass216_5136.anInt1624;
                    this.aClass216_5142.anInt1620 = this.aClass216_5136.anInt1620;
                    this.aClass216_5142.anInt1629 = this.aClass216_5136.anInt1629;
                    this.aClass216_5142.anInt1627 = this.aClass216_5136.anInt1627;
                    this.aClass216_5142.anInt1626 = this.aClass216_5136.anInt1626;
                    this.aClass216_5142.anInt1619 = this.aClass216_5136.anInt1619;
                    this.aClass216_5142.anInt1628 = this.aClass216_5136.anInt1628;
                    this.aBoolean5150 = false;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qba.E(" + n + ')');
        }
    }
    
    static final Class293 method3139(final byte b, final int n, final int n2) {
        try {
            final Class293 method2509 = Class159.method2509(n, -9820);
            if (b != 72) {
                Class246_Sub9.anIntArrayArray5141 = null;
            }
            if (n2 == -1) {
                return method2509;
            }
            if (method2509 == null || method2509.aClass293Array2339 == null || ~n2 <= ~method2509.aClass293Array2339.length) {
                return null;
            }
            return method2509.aClass293Array2339[n2];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qba.D(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    Class246_Sub9(final ha ha, final Class87 aClass87_5131, final Class246_Sub5 aClass246_Sub5_5133, final long aLong5134) {
        this.aBoolean5139 = false;
        this.anInt5137 = 0;
        this.aClass216_5136 = new Class216();
        this.aClass216_5142 = new Class216();
        this.aBoolean5150 = false;
        try {
            this.aClass246_Sub5_5133 = aClass246_Sub5_5133;
            this.aClass87_5131 = aClass87_5131;
            this.aLong5134 = aLong5134;
            this.aClass92_5132 = this.aClass87_5131.method856(0);
            if (!ha.method1780() && this.aClass92_5132.anInt764 != -1) {
                this.aClass92_5132 = Class98_Sub45.method1520(this.aClass92_5132.anInt764, 14883);
            }
            this.aClass218_5140 = new Class218();
            this.anInt5137 += (int)(64.0 * Math.random());
            this.method3138(-1);
            this.aClass216_5142.anInt1620 = this.aClass216_5136.anInt1620;
            this.aClass216_5142.anInt1619 = this.aClass216_5136.anInt1619;
            this.aClass216_5142.anInt1629 = this.aClass216_5136.anInt1629;
            this.aClass216_5142.anInt1625 = this.aClass216_5136.anInt1625;
            this.aClass216_5142.anInt1627 = this.aClass216_5136.anInt1627;
            this.aClass216_5142.anInt1623 = this.aClass216_5136.anInt1623;
            this.aClass216_5142.anInt1624 = this.aClass216_5136.anInt1624;
            this.aClass216_5142.anInt1626 = this.aClass216_5136.anInt1626;
            this.aClass216_5142.anInt1628 = this.aClass216_5136.anInt1628;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qba.<init>(" + ((ha != null) ? "{...}" : "null") + ',' + ((aClass87_5131 != null) ? "{...}" : "null") + ',' + ((aClass246_Sub5_5133 != null) ? "{...}" : "null") + ',' + aLong5134 + ')');
        }
    }
    
    static {
        Class246_Sub9.anIntArrayArray5141 = new int[][] { { 0, 2, 4, 6 }, { 6, 0, 2, 3, 5, 3 }, { 6, 0, 2, 4 }, { 2, 5, 6, 1 }, { 0, 2, 6 }, { 6, 0, 2 }, { 5, 6, 0, 1, 2, 4 }, { 7, 7, 1, 2, 4, 6 }, { 2, 4, 4, 7 }, { 6, 6, 4, 0, 1, 1, 3, 3 }, { 0, 2, 2, 6, 6, 4 }, { 0, 2, 2, 3, 7, 0, 4, 3 }, { 0, 2, 4, 6 } };
        Class246_Sub9.aClass79_5138 = new Class79(64);
    }
}
