import jaclib.memory.Buffer;
import jaclib.memory.Stream;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class81
{
    private int[] anIntArray608;
    private Interface2_Impl2 anInterface2_Impl2_609;
    private Class246_Sub4_Sub2[][] aClass246_Sub4_Sub2ArrayArray610;
    private Class256 aClass256_611;
    private Interface2_Impl1 anInterface2_Impl1_612;
    private int anInt613;
    private int[] anIntArray614;
    private int[] anIntArray615;
    private Class246_Sub4_Sub2[][] aClass246_Sub4_Sub2ArrayArray616;
    static Class100 aClass100_617;
    static s[] aSArray618;
    static Class98_Sub31_Sub3 aClass98_Sub31_Sub3_619;
    static Class98_Sub7 aClass98_Sub7_620;
    static boolean aBoolean621;
    static Class207 aClass207_622;
    private Interface2_Impl1 anInterface2_Impl1_623;
    static int anInt624;
    
    private final void method811(final ha_Sub3 ha_Sub3, final byte b, final int n) {
        try {
            int n2 = 0;
            final Class111_Sub3 method2027 = ha_Sub3.method2027(0);
            final float aFloat4712 = method2027.aFloat4712;
            final float aFloat4713 = method2027.aFloat4711;
            final float aFloat4714 = method2027.aFloat4705;
            final float aFloat4715 = method2027.aFloat4714;
            final float aFloat4716 = method2027.aFloat4706;
            final float aFloat4717 = method2027.aFloat4710;
            final float n3 = aFloat4715 + aFloat4712;
            final float n4 = aFloat4713 + aFloat4716;
            final float n5 = aFloat4714 + aFloat4717;
            final float n6 = -aFloat4715 + aFloat4712;
            final float n7 = -aFloat4716 + aFloat4713;
            final float n8 = -aFloat4717 + aFloat4714;
            final float n9 = -aFloat4712 + aFloat4715;
            final float n10 = aFloat4716 - aFloat4713;
            final float n11 = aFloat4717 - aFloat4714;
            if (b != -14) {
                Class81.anInt624 = -5;
            }
            final Buffer method2028 = this.anInterface2_Impl1_623.method75(true, (byte)27);
            if (method2028 != null) {
                final Stream method2029 = ha_Sub3.method2043(b + 24036, method2028);
                if (Stream.a()) {
                    for (int n12 = n - 1; ~n12 <= -1; --n12) {
                        final int n13 = (this.anIntArray608[n12] > 64) ? 64 : this.anIntArray608[n12];
                        if (~n13 < -1) {
                            for (int n14 = n13 - 1; ~n14 <= -1; --n14) {
                                final Class246_Sub4_Sub2 class246_Sub4_Sub2 = this.aClass246_Sub4_Sub2ArrayArray616[n12][n14];
                                final int anInt6178 = class246_Sub4_Sub2.anInt6178;
                                final byte b2 = (byte)(anInt6178 >> 1004982320);
                                final byte b3 = (byte)(anInt6178 >> -1475022584);
                                final byte b4 = (byte)anInt6178;
                                final byte b5 = (byte)(anInt6178 >>> 499468248);
                                final float n15 = class246_Sub4_Sub2.anInt6176 >> -1148612052;
                                final float n16 = class246_Sub4_Sub2.anInt6177 >> -1842071796;
                                final float n17 = class246_Sub4_Sub2.anInt6175 >> 1522356492;
                                final int n18 = class246_Sub4_Sub2.anInt6179 >> -2078565748;
                                method2029.b(n15 + n3 * -n18);
                                method2029.b(n16 + n4 * -n18);
                                method2029.b(-n18 * n5 + n17);
                                if (ha_Sub3.anInt4580 != 0) {
                                    method2029.b(b2, b3, b4, b5);
                                }
                                else {
                                    method2029.a(b2, b3, b4, b5);
                                }
                                method2029.b(0.0f);
                                method2029.b(0.0f);
                                method2029.b(n15 + n6 * n18);
                                method2029.b(n16 + n7 * n18);
                                method2029.b(n8 * n18 + n17);
                                if (~ha_Sub3.anInt4580 == -1) {
                                    method2029.a(b2, b3, b4, b5);
                                }
                                else {
                                    method2029.b(b2, b3, b4, b5);
                                }
                                method2029.b(1.0f);
                                method2029.b(0.0f);
                                method2029.b(n3 * n18 + n15);
                                method2029.b(n18 * n4 + n16);
                                method2029.b(n18 * n5 + n17);
                                if (ha_Sub3.anInt4580 == 0) {
                                    method2029.a(b2, b3, b4, b5);
                                }
                                else {
                                    method2029.b(b2, b3, b4, b5);
                                }
                                method2029.b(1.0f);
                                method2029.b(1.0f);
                                method2029.b(n15 + n9 * n18);
                                method2029.b(n10 * n18 + n16);
                                method2029.b(n18 * n11 + n17);
                                if (~ha_Sub3.anInt4580 != -1) {
                                    method2029.b(b2, b3, b4, b5);
                                }
                                else {
                                    method2029.a(b2, b3, b4, b5);
                                }
                                method2029.b(0.0f);
                                ++n2;
                                method2029.b(1.0f);
                            }
                            if (this.anIntArray608[n12] > 64) {
                                final int n19 = this.anIntArray608[n12] - 64 - 1;
                                for (int i = this.anIntArray614[n19] - 1; i >= 0; --i) {
                                    final Class246_Sub4_Sub2 class246_Sub4_Sub3 = this.aClass246_Sub4_Sub2ArrayArray610[n19][i];
                                    final int anInt6179 = class246_Sub4_Sub3.anInt6178;
                                    final byte b6 = (byte)(anInt6179 >> -1731208688);
                                    final byte b7 = (byte)(anInt6179 >> 2114755272);
                                    final byte b8 = (byte)anInt6179;
                                    final byte b9 = (byte)(anInt6179 >>> 42394680);
                                    final float n20 = class246_Sub4_Sub3.anInt6176 >> -1812783700;
                                    final float n21 = class246_Sub4_Sub3.anInt6177 >> -1875090740;
                                    final float n22 = class246_Sub4_Sub3.anInt6175 >> -1949960628;
                                    final int n23 = class246_Sub4_Sub3.anInt6179 >> 1949500780;
                                    method2029.b(n20 + n3 * -n23);
                                    method2029.b(n4 * -n23 + n21);
                                    method2029.b(n22 + -n23 * n5);
                                    if (ha_Sub3.anInt4580 == 0) {
                                        method2029.a(b6, b7, b8, b9);
                                    }
                                    else {
                                        method2029.b(b6, b7, b8, b9);
                                    }
                                    method2029.b(0.0f);
                                    method2029.b(0.0f);
                                    method2029.b(n6 * n23 + n20);
                                    method2029.b(n23 * n7 + n21);
                                    method2029.b(n22 + n8 * n23);
                                    if (ha_Sub3.anInt4580 != 0) {
                                        method2029.b(b6, b7, b8, b9);
                                    }
                                    else {
                                        method2029.a(b6, b7, b8, b9);
                                    }
                                    method2029.b(1.0f);
                                    method2029.b(0.0f);
                                    method2029.b(n20 + n3 * n23);
                                    method2029.b(n21 + n23 * n4);
                                    method2029.b(n22 + n23 * n5);
                                    if (~ha_Sub3.anInt4580 != -1) {
                                        method2029.b(b6, b7, b8, b9);
                                    }
                                    else {
                                        method2029.a(b6, b7, b8, b9);
                                    }
                                    method2029.b(1.0f);
                                    method2029.b(1.0f);
                                    method2029.b(n20 + n23 * n9);
                                    method2029.b(n23 * n10 + n21);
                                    method2029.b(n22 + n23 * n11);
                                    if (ha_Sub3.anInt4580 == 0) {
                                        method2029.a(b6, b7, b8, b9);
                                    }
                                    else {
                                        method2029.b(b6, b7, b8, b9);
                                    }
                                    method2029.b(0.0f);
                                    ++n2;
                                    method2029.b(1.0f);
                                }
                            }
                        }
                    }
                }
                else {
                    for (int n24 = -1 + n; ~n24 <= -1; --n24) {
                        final int n25 = (this.anIntArray608[n24] > 64) ? 64 : this.anIntArray608[n24];
                        if (n25 > 0) {
                            for (int j = n25 - 1; j >= 0; --j) {
                                final Class246_Sub4_Sub2 class246_Sub4_Sub4 = this.aClass246_Sub4_Sub2ArrayArray616[n24][j];
                                final int anInt6180 = class246_Sub4_Sub4.anInt6178;
                                final byte b10 = (byte)(anInt6180 >> -1414407312);
                                final byte b11 = (byte)(anInt6180 >> 1123272776);
                                final byte b12 = (byte)anInt6180;
                                final byte b13 = (byte)(anInt6180 >>> -997863304);
                                final float n26 = class246_Sub4_Sub4.anInt6176 >> -83946644;
                                final float n27 = class246_Sub4_Sub4.anInt6177 >> -867938740;
                                final float n28 = class246_Sub4_Sub4.anInt6175 >> 13807948;
                                final int n29 = class246_Sub4_Sub4.anInt6179 >> -1405584212;
                                method2029.a(-n29 * n3 + n26);
                                method2029.a(n27 + n4 * -n29);
                                method2029.a(n28 + -n29 * n5);
                                if (~ha_Sub3.anInt4580 == -1) {
                                    method2029.a(b10, b11, b12, b13);
                                }
                                else {
                                    method2029.b(b10, b11, b12, b13);
                                }
                                method2029.a(0.0f);
                                method2029.a(0.0f);
                                method2029.a(n26 + n29 * n6);
                                method2029.a(n27 + n29 * n7);
                                method2029.a(n8 * n29 + n28);
                                if (~ha_Sub3.anInt4580 != -1) {
                                    method2029.b(b10, b11, b12, b13);
                                }
                                else {
                                    method2029.a(b10, b11, b12, b13);
                                }
                                method2029.a(1.0f);
                                method2029.a(0.0f);
                                method2029.a(n29 * n3 + n26);
                                method2029.a(n29 * n4 + n27);
                                method2029.a(n28 + n29 * n5);
                                if (~ha_Sub3.anInt4580 == -1) {
                                    method2029.a(b10, b11, b12, b13);
                                }
                                else {
                                    method2029.b(b10, b11, b12, b13);
                                }
                                method2029.a(1.0f);
                                method2029.a(1.0f);
                                method2029.a(n26 + n9 * n29);
                                method2029.a(n29 * n10 + n27);
                                method2029.a(n29 * n11 + n28);
                                if (~ha_Sub3.anInt4580 == -1) {
                                    method2029.a(b10, b11, b12, b13);
                                }
                                else {
                                    method2029.b(b10, b11, b12, b13);
                                }
                                method2029.a(0.0f);
                                ++n2;
                                method2029.a(1.0f);
                            }
                            if (~this.anIntArray608[n24] < -65) {
                                final int n30 = -1 + (-64 + this.anIntArray608[n24]);
                                for (int k = -1 + this.anIntArray614[n30]; k >= 0; --k) {
                                    final Class246_Sub4_Sub2 class246_Sub4_Sub5 = this.aClass246_Sub4_Sub2ArrayArray610[n30][k];
                                    final int anInt6181 = class246_Sub4_Sub5.anInt6178;
                                    final byte b14 = (byte)(anInt6181 >> -924804944);
                                    final byte b15 = (byte)(anInt6181 >> 1479709480);
                                    final byte b16 = (byte)anInt6181;
                                    final byte b17 = (byte)(anInt6181 >>> 989558680);
                                    final float n31 = class246_Sub4_Sub5.anInt6176 >> -1539946420;
                                    final float n32 = class246_Sub4_Sub5.anInt6177 >> 1485597036;
                                    final float n33 = class246_Sub4_Sub5.anInt6175 >> 750057452;
                                    final int n34 = class246_Sub4_Sub5.anInt6179 >> 2108567500;
                                    method2029.a(-n34 * n3 + n31);
                                    method2029.a(n32 + n4 * -n34);
                                    method2029.a(-n34 * n5 + n33);
                                    if (ha_Sub3.anInt4580 != 0) {
                                        method2029.b(b14, b15, b16, b17);
                                    }
                                    else {
                                        method2029.a(b14, b15, b16, b17);
                                    }
                                    method2029.a(0.0f);
                                    method2029.a(0.0f);
                                    method2029.a(n34 * n6 + n31);
                                    method2029.a(n32 + n34 * n7);
                                    method2029.a(n33 + n8 * n34);
                                    if (~ha_Sub3.anInt4580 == -1) {
                                        method2029.a(b14, b15, b16, b17);
                                    }
                                    else {
                                        method2029.b(b14, b15, b16, b17);
                                    }
                                    method2029.a(1.0f);
                                    method2029.a(0.0f);
                                    method2029.a(n31 + n3 * n34);
                                    method2029.a(n32 + n4 * n34);
                                    method2029.a(n5 * n34 + n33);
                                    if (~ha_Sub3.anInt4580 != -1) {
                                        method2029.b(b14, b15, b16, b17);
                                    }
                                    else {
                                        method2029.a(b14, b15, b16, b17);
                                    }
                                    method2029.a(1.0f);
                                    method2029.a(1.0f);
                                    method2029.a(n9 * n34 + n31);
                                    method2029.a(n32 + n10 * n34);
                                    method2029.a(n34 * n11 + n33);
                                    if (ha_Sub3.anInt4580 != 0) {
                                        method2029.b(b14, b15, b16, b17);
                                    }
                                    else {
                                        method2029.a(b14, b15, b16, b17);
                                    }
                                    method2029.a(0.0f);
                                    method2029.a(1.0f);
                                    ++n2;
                                }
                            }
                        }
                    }
                }
                method2029.c();
                if (this.anInterface2_Impl1_623.method71(b + 13637)) {
                    ha_Sub3.method1971(0, true, this.anInterface2_Impl1_623);
                    ha_Sub3.method1971(1, true, this.anInterface2_Impl1_612);
                    ha_Sub3.method2042(this.aClass256_611, (byte)101);
                    ha_Sub3.method1973(Class336.aClass232_2822, 4 * n2, 0, b + 26824, this.anInterface2_Impl2_609, 0, 2 * n2);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fea.I(" + ((ha_Sub3 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    final void method812(final ha_Sub3 ha_Sub3, final byte b) {
        try {
            if (b != 36) {
                this.aClass246_Sub4_Sub2ArrayArray616 = null;
            }
            this.anInterface2_Impl1_623.method74(-20279, 24, 786336);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fea.H(" + ((ha_Sub3 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    private final void method813(final ha_Sub3 ha_Sub3, final int n) {
        try {
            Class98_Sub32_Sub1.aFloat5886 = ha_Sub3.aFloat4576;
            if (n != -64) {
                this.anInterface2_Impl2_609 = null;
            }
            ha_Sub3.method1976(n ^ 0x4E);
            ha_Sub3.method1986(false, (byte)(-119));
            ha_Sub3.method1997(0, false);
            ha_Sub3.method1960(13951);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fea.G(" + ((ha_Sub3 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final void method814(final byte b) {
        try {
            if (b != 4) {
                this.anIntArray615 = null;
            }
            this.anInterface2_Impl1_623.method72(false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fea.C(" + b + ')');
        }
    }
    
    static final boolean method815(final int n, final int n2) {
        try {
            if (n2 != 0) {
                method817(true);
            }
            return ~n == ~(-n & n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fea.A(" + n + ',' + n2 + ')');
        }
    }
    
    private final void method816(final int n, final ha_Sub3 ha_Sub3) {
        try {
            ha_Sub3.method1997(0, true);
            ha_Sub3.method1986(true, (byte)(-7));
            if (Class98_Sub32_Sub1.aFloat5886 != ha_Sub3.aFloat4576) {
                ha_Sub3.xa(Class98_Sub32_Sub1.aFloat5886);
            }
            if (n > -97) {
                this.anIntArray614 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fea.J(" + n + ',' + ((ha_Sub3 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method817(final boolean b) {
        try {
            Class129.method2229();
            for (int i = 0; i < 4; ++i) {
                Class167.aClass243Array1281[i].method2950((byte)(-99));
            }
            InputStream_Sub2.method124(52);
            Class98_Sub10_Sub15.method1050((byte)125);
            Class301.method3542(50);
            System.gc();
            Class265.aHa1974.ya();
            if (b) {
                Class81.aBoolean621 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fea.B(" + b + ')');
        }
    }
    
    final void method818(final byte b, final Class242 class242, final int n, final ha_Sub3 ha_Sub3) {
        try {
            if (ha_Sub3.aClass111_Sub3_4543 != null) {
                if (n < 0) {
                    this.method813(ha_Sub3, -64);
                }
                else {
                    this.method819(-11402, n, ha_Sub3);
                }
                final float aFloat4708 = ha_Sub3.aClass111_Sub3_4543.aFloat4708;
                final float aFloat4709 = ha_Sub3.aClass111_Sub3_4543.aFloat4713;
                final float aFloat4710 = ha_Sub3.aClass111_Sub3_4543.aFloat4704;
                final float aFloat4711 = ha_Sub3.aClass111_Sub3_4543.aFloat4703;
                try {
                    int n2 = 0;
                    int n3 = Integer.MAX_VALUE;
                    int n4 = 0;
                    final Class246_Sub4 aClass246_Sub4_3028 = class242.aClass358_1850.aClass246_Sub4_3028;
                    for (Class246_Sub4 class246_Sub4 = aClass246_Sub4_3028.aClass246_Sub4_5091; class246_Sub4 != aClass246_Sub4_3028; class246_Sub4 = class246_Sub4.aClass246_Sub4_5091) {
                        final Class246_Sub4_Sub2 class246_Sub4_Sub2 = (Class246_Sub4_Sub2)class246_Sub4;
                        final int n5 = (int)(aFloat4711 + ((class246_Sub4_Sub2.anInt6175 >> 912414860) * aFloat4710 + ((class246_Sub4_Sub2.anInt6177 >> -493384788) * aFloat4709 + (class246_Sub4_Sub2.anInt6176 >> 1402072204) * aFloat4708)));
                        if (~n4 > ~n5) {
                            n4 = n5;
                        }
                        if (~n5 > ~n3) {
                            n3 = n5;
                        }
                        this.anIntArray615[n2++] = n5;
                    }
                    int n6 = n4 - n3;
                    int n7;
                    if (~(2 + n6) >= -1601) {
                        n6 += 2;
                        n7 = 0;
                    }
                    else {
                        n7 = 1 + (Class48_Sub2_Sub1.method474(n6, (byte)31) + -Class373.anInt3477);
                        n6 = 2 + (n6 >> n7);
                    }
                    int n8 = 0;
                    Class246_Sub4 class246_Sub5 = aClass246_Sub4_3028.aClass246_Sub4_5091;
                    int anInt6180 = -2;
                    if (b != -108) {
                        this.anInterface2_Impl1_623 = null;
                    }
                    boolean aBoolean6174 = true;
                    int n9 = 1;
                    while (aClass246_Sub4_3028 != class246_Sub5) {
                        this.anInt613 = 0;
                        for (int n10 = 0; ~n10 > ~n6; ++n10) {
                            this.anIntArray608[n10] = 0;
                        }
                        for (int i = 0; i < 64; ++i) {
                            this.anIntArray614[i] = 0;
                        }
                        while (class246_Sub5 != aClass246_Sub4_3028) {
                            final Class246_Sub4_Sub2 class246_Sub4_Sub3 = (Class246_Sub4_Sub2)class246_Sub5;
                            if (n9 != 0) {
                                n9 = 0;
                                aBoolean6174 = class246_Sub4_Sub3.aBoolean6174;
                                anInt6180 = class246_Sub4_Sub3.anInt6180;
                            }
                            if (n8 > 0 && (anInt6180 != class246_Sub4_Sub3.anInt6180 || class246_Sub4_Sub3.aBoolean6174 == !aBoolean6174)) {
                                n9 = 1;
                                break;
                            }
                            final int n11 = -n3 + this.anIntArray615[n8++] >> n7;
                            Label_0593: {
                                if (~n11 > -1601) {
                                    if (~this.anIntArray608[n11] > -65) {
                                        this.aClass246_Sub4_Sub2ArrayArray616[n11][this.anIntArray608[n11]++] = class246_Sub4_Sub3;
                                    }
                                    else {
                                        if (~this.anIntArray608[n11] == 0xFFFFFFBF) {
                                            if (~this.anInt613 == 0xFFFFFFBF) {
                                                break Label_0593;
                                            }
                                            final int[] anIntArray608 = this.anIntArray608;
                                            final int n12 = n11;
                                            anIntArray608[n12] += 1 + this.anInt613++;
                                        }
                                        this.aClass246_Sub4_Sub2ArrayArray610[this.anIntArray608[n11] - 64 - 1][this.anIntArray614[-1 + this.anIntArray608[n11] - 64]++] = class246_Sub4_Sub3;
                                    }
                                }
                            }
                            class246_Sub5 = class246_Sub5.aClass246_Sub4_5091;
                        }
                        ha_Sub3.method2039(false, b ^ 0xFFFFFF94, (~anInt6180 > -1) ? -1 : anInt6180, false);
                        if (!aBoolean6174 || Class98_Sub32_Sub1.aFloat5886 == ha_Sub3.aFloat4576) {
                            if (ha_Sub3.aFloat4576 != 1.0f) {
                                ha_Sub3.xa(1.0f);
                            }
                        }
                        else {
                            ha_Sub3.xa(Class98_Sub32_Sub1.aFloat5886);
                        }
                        this.method811(ha_Sub3, (byte)(-14), n6);
                    }
                }
                catch (Exception ex2) {}
                this.method816(-103, ha_Sub3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fea.D(" + b + ',' + ((class242 != null) ? "{...}" : "null") + ',' + n + ',' + ((ha_Sub3 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method819(final int n, final int n2, final ha_Sub3 ha_Sub3) {
        try {
            Class98_Sub32_Sub1.aFloat5886 = ha_Sub3.aFloat4576;
            ha_Sub3.method2056(n ^ 0xFFFFD342, n2);
            if (n != -11402) {
                this.anIntArray614 = null;
            }
            ha_Sub3.method1937(4);
            ha_Sub3.method1986(false, (byte)(-126));
            ha_Sub3.method1997(0, false);
            ha_Sub3.method1960(13951);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fea.E(" + n + ',' + n2 + ',' + ((ha_Sub3 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class81(final ha_Sub3 ha_Sub3) {
        this.anIntArray608 = new int[1600];
        this.aClass246_Sub4_Sub2ArrayArray610 = new Class246_Sub4_Sub2[64][768];
        this.anIntArray614 = new int[64];
        this.anInt613 = 0;
        this.anIntArray615 = new int[8191];
        this.aClass246_Sub4_Sub2ArrayArray616 = new Class246_Sub4_Sub2[1600][64];
        try {
            this.aClass256_611 = ha_Sub3.method1982(new Class49[] { new Class49(new Class169[] { Class169.aClass169_1294, Class169.aClass169_1298, Class169.aClass169_1301 }), new Class49(Class169.aClass169_1297) }, 6);
            this.anInterface2_Impl1_623 = ha_Sub3.method2060(true, 107);
            (this.anInterface2_Impl1_612 = ha_Sub3.method2060(false, 119)).method74(-20279, 12, 393168);
            (this.anInterface2_Impl2_609 = ha_Sub3.method1990((byte)84, false)).method76(49146, 20779);
            final Buffer method78 = this.anInterface2_Impl2_609.method78(true, -87);
            if (method78 != null) {
                final Stream method79 = ha_Sub3.method2043(24022, method78);
                if (!Stream.a()) {
                    for (int n = 0; ~n > -8192; ++n) {
                        final int n2 = 4 * n;
                        method79.d(n2);
                        method79.d(1 + n2);
                        method79.d(2 + n2);
                        method79.d(2 + n2);
                        method79.d(3 + n2);
                        method79.d(n2);
                    }
                }
                else {
                    for (int n3 = 0; ~n3 > -8192; ++n3) {
                        final int n4 = 4 * n3;
                        method79.c(n4);
                        method79.c(n4 + 1);
                        method79.c(n4 + 2);
                        method79.c(2 + n4);
                        method79.c(3 + n4);
                        method79.c(n4);
                    }
                }
                method79.c();
                this.anInterface2_Impl2_609.method79((byte)(-117));
            }
            final Buffer method80 = this.anInterface2_Impl1_612.method75(true, (byte)27);
            if (method80 != null) {
                final Stream method81 = ha_Sub3.method2043(24022, method80);
                if (Stream.a()) {
                    for (int i = 0; i < 8191; ++i) {
                        method81.b(0.0f);
                        method81.b(-1.0f);
                        method81.b(0.0f);
                        method81.b(0.0f);
                        method81.b(-1.0f);
                        method81.b(0.0f);
                        method81.b(0.0f);
                        method81.b(-1.0f);
                        method81.b(0.0f);
                        method81.b(0.0f);
                        method81.b(-1.0f);
                        method81.b(0.0f);
                    }
                }
                else {
                    for (int n5 = 0; ~n5 > -8192; ++n5) {
                        method81.a(0.0f);
                        method81.a(-1.0f);
                        method81.a(0.0f);
                        method81.a(0.0f);
                        method81.a(-1.0f);
                        method81.a(0.0f);
                        method81.a(0.0f);
                        method81.a(-1.0f);
                        method81.a(0.0f);
                        method81.a(0.0f);
                        method81.a(-1.0f);
                        method81.a(0.0f);
                    }
                }
                method81.c();
                this.anInterface2_Impl1_612.method71(13623);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fea.<init>(" + ((ha_Sub3 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method820(final int n) {
        try {
            Class81.aSArray618 = null;
            Class81.aClass100_617 = null;
            Class81.aClass98_Sub31_Sub3_619 = null;
            if (n != 1) {
                Class81.aBoolean621 = true;
            }
            Class81.aClass98_Sub7_620 = null;
            Class81.aClass207_622 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fea.F(" + n + ')');
        }
    }
    
    static {
        Class81.aClass100_617 = new Class100(16);
        Class81.aBoolean621 = false;
    }
}
