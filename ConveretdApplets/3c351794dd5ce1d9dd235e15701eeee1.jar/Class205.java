import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class205
{
    int anInt1554;
    int anInt1555;
    Class207 aClass207_1556;
    private Class79 aClass79_1557;
    private boolean aBoolean1558;
    private Class207 aClass207_1559;
    Class79 aClass79_1560;
    Class74 aClass74_1561;
    private Class73 aClass73_1562;
    private Class365 aClass365_1563;
    int anInt1564;
    private String[] aStringArray1565;
    private String[] aStringArray1566;
    
    final Class332 method2710(final int anInt3486, final int anInt3487, final ha ha, final Class313 class313, final int anInt3488, final int anInt3489, final int anInt3490, final int n) {
        try {
            this.aClass73_1562.aBoolean3488 = (class313 != null);
            this.aClass73_1562.anInt3487 = anInt3490;
            if (n != 24056) {
                return null;
            }
            this.aClass73_1562.anInt3480 = anInt3487;
            this.aClass73_1562.anInt3481 = anInt3489;
            this.aClass73_1562.anInt3483 = ha.anInt937;
            this.aClass73_1562.anInt3484 = anInt3488;
            this.aClass73_1562.anInt3486 = anInt3486;
            return (Class332)this.aClass74_1561.method732(this.aClass73_1562, n ^ 0x5DF8);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nh.C(" + anInt3486 + ',' + anInt3487 + ',' + ((ha != null) ? "{...}" : "null") + ',' + ((class313 != null) ? "{...}" : "null") + ',' + anInt3488 + ',' + anInt3489 + ',' + anInt3490 + ',' + n + ')');
        }
    }
    
    static final Class246_Sub3_Sub3 method2711(final int n, final int n2, final int n3) {
        final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2][n3];
        if (class172 == null) {
            return null;
        }
        return class172.aClass246_Sub3_Sub3_1333;
    }
    
    final void method2712(final int n, final int anInt1564) {
        try {
            this.anInt1564 = anInt1564;
            synchronized (this.aClass79_1560) {
                this.aClass79_1560.method794(103);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nh.K(" + n + ',' + anInt1564 + ')');
        }
    }
    
    static final Class82 method2713(final ha_Sub3_Sub2 ha_Sub3_Sub2, final int n, final Class230[] array) {
        try {
            for (int n2 = 0; array.length > n2; ++n2) {
                if (array[n2] == null || ~array[n2].aLong1723 >= -1L) {
                    return null;
                }
            }
            final long glCreateProgramObjectARB = OpenGL.glCreateProgramObjectARB();
            for (int n3 = 0; ~array.length < ~n3; ++n3) {
                OpenGL.glAttachObjectARB(glCreateProgramObjectARB, array[n3].aLong1723);
            }
            OpenGL.glLinkProgramARB(glCreateProgramObjectARB);
            OpenGL.glGetObjectParameterivARB(glCreateProgramObjectARB, 35714, Class238.anIntArray1836, 0);
            if (n == Class238.anIntArray1836[0]) {
                if (Class238.anIntArray1836[0] == 0) {
                    System.out.println("Shader linking failed:");
                }
                OpenGL.glGetObjectParameterivARB(glCreateProgramObjectARB, 35716, Class238.anIntArray1836, 1);
                if (~Class238.anIntArray1836[1] < -2) {
                    final byte[] array2 = new byte[Class238.anIntArray1836[1]];
                    OpenGL.glGetInfoLogARB(glCreateProgramObjectARB, Class238.anIntArray1836[1], Class238.anIntArray1836, 0, array2, 0);
                    System.out.println(new String(array2));
                }
                if (~Class238.anIntArray1836[0] == -1) {
                    for (int n4 = 0; ~n4 > ~array.length; ++n4) {
                        OpenGL.glDetachObjectARB(glCreateProgramObjectARB, array[n4].aLong1723);
                    }
                    OpenGL.glDeleteObjectARB(glCreateProgramObjectARB);
                    return null;
                }
            }
            return new Class82(ha_Sub3_Sub2, glCreateProgramObjectARB, array);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nh.G(" + ((ha_Sub3_Sub2 != null) ? "{...}" : "null") + ',' + n + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final Class297 method2714(final int anInt2468, final byte b) {
        try {
            final Class297 class297;
            synchronized (this.aClass79_1557) {
                class297 = (Class297)this.aClass79_1557.method802(-126, anInt2468);
            }
            if (class297 != null) {
                return class297;
            }
            final byte[] method2745;
            synchronized (this.aClass207_1559) {
                method2745 = this.aClass207_1559.method2745(Class119_Sub3.method2187((byte)(-84), anInt2468), Class150.method2437((byte)124, anInt2468), false);
            }
            final Class297 class298 = new Class297();
            class298.aClass205_2419 = this;
            class298.anInt2468 = anInt2468;
            class298.aStringArray2446 = new String[] { null, null, Class309.aClass309_2593.method3615(this.anInt1555, (byte)25), null, null };
            class298.aStringArray2473 = new String[] { null, null, null, null, Class309.aClass309_2594.method3615(this.anInt1555, (byte)25) };
            if (method2745 != null) {
                class298.method3497(new Class98_Sub22(method2745), (byte)(-114));
            }
            class298.method3485(850);
            if (~class298.anInt2414 != 0x0) {
                class298.method3487(this.method2714(class298.anInt2433, (byte)(-120)), 118, this.method2714(class298.anInt2414, (byte)(-122)));
            }
            if (class298.anInt2459 != -1) {
                class298.method3498(this.method2714(class298.anInt2472, (byte)(-123)), this.method2714(class298.anInt2459, (byte)(-123)), 123);
            }
            if (!this.aBoolean1558 && class298.aBoolean2420) {
                class298.aString2450 = Class309.aClass309_2591.method3615(this.anInt1555, (byte)25);
                class298.aStringArray2446 = this.aStringArray1565;
                class298.aBoolean2461 = false;
                class298.aStringArray2473 = this.aStringArray1566;
                class298.anInt2435 = 0;
                class298.anIntArray2436 = null;
                if (class298.aClass377_2443 != null) {
                    boolean b2 = false;
                    for (Class98 class299 = class298.aClass377_2443.method3998(124); class299 != null; class299 = class298.aClass377_2443.method3995(-1)) {
                        if (this.aClass365_1563.method3940((byte)31, (int)class299.aLong832).aBoolean1204) {
                            class299.method942(44);
                        }
                        else {
                            b2 = true;
                        }
                    }
                    if (!b2) {
                        class298.aClass377_2443 = null;
                    }
                }
            }
            synchronized (this.aClass79_1557) {
                this.aClass79_1557.method805(anInt2468, class298, (byte)(-80));
            }
            if (b >= -115) {
                return null;
            }
            return class298;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nh.H(" + anInt2468 + ',' + b + ')');
        }
    }
    
    final void method2715(final int n) {
        try {
            synchronized (this.aClass79_1557) {
                this.aClass79_1557.method794(110);
            }
            synchronized (this.aClass79_1560) {
                this.aClass79_1560.method794(64);
            }
            synchronized (this.aClass74_1561) {
                this.aClass74_1561.method722(true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nh.M(" + n + ')');
        }
    }
    
    static final void method2716(final int n) {
        try {
            for (Class98_Sub46_Sub5 class98_Sub46_Sub5 = (Class98_Sub46_Sub5)Class280.aClass148_2108.method2418(32); class98_Sub46_Sub5 != null; class98_Sub46_Sub5 = (Class98_Sub46_Sub5)Class280.aClass148_2108.method2417(n ^ 0xFFFFDA12)) {
                final Class246_Sub3_Sub4_Sub4 aClass246_Sub3_Sub4_Sub4_5969 = class98_Sub46_Sub5.aClass246_Sub3_Sub4_Sub4_5969;
                if (Class215.anInt1614 > aClass246_Sub3_Sub4_Sub4_5969.anInt6466) {
                    class98_Sub46_Sub5.method942(81);
                    aClass246_Sub3_Sub4_Sub4_5969.method3078(n ^ 0x3254);
                }
                else if (Class215.anInt1614 >= aClass246_Sub3_Sub4_Sub4_5969.anInt6479) {
                    aClass246_Sub3_Sub4_Sub4_5969.method3080((byte)109);
                    if (aClass246_Sub3_Sub4_Sub4_5969.anInt6482 > 0) {
                        final Class98_Sub39 class98_Sub39 = (Class98_Sub39)Class260.aClass377_3254.method3990(aClass246_Sub3_Sub4_Sub4_5969.anInt6482 - 1, -1);
                        if (class98_Sub39 != null) {
                            final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                            if (~aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 <= -1 && ~aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084 > ~(Class165.anInt1276 * 512) && ~aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 <= -1 && aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079 < 512 * Class98_Sub10_Sub7.anInt5572) {
                                aClass246_Sub3_Sub4_Sub4_5969.method3074(aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084, Class98_Sub46_Sub2_Sub2.method1538(aClass246_Sub3_Sub4_Sub4_5969.aByte5088, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5084, n + 33738) - aClass246_Sub3_Sub4_Sub4_5969.anInt6463, Class215.anInt1614, (byte)108, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt5079);
                            }
                        }
                    }
                    if (aClass246_Sub3_Sub4_Sub4_5969.anInt6482 < 0) {
                        final int n2 = -aClass246_Sub3_Sub4_Sub4_5969.anInt6482 - 1;
                        Class246_Sub3_Sub4_Sub2_Sub2 aClass246_Sub3_Sub4_Sub2_Sub2_660;
                        if (n2 != za_Sub2.anInt6080) {
                            aClass246_Sub3_Sub4_Sub2_Sub2_660 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n2];
                        }
                        else {
                            aClass246_Sub3_Sub4_Sub2_Sub2_660 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660;
                        }
                        if (aClass246_Sub3_Sub4_Sub2_Sub2_660 != null && ~aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 <= -1 && ~aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084 > ~(512 * Class165.anInt1276) && ~aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 <= -1 && ~aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 > ~(Class98_Sub10_Sub7.anInt5572 * 512)) {
                            aClass246_Sub3_Sub4_Sub4_5969.method3074(aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084, Class98_Sub46_Sub2_Sub2.method1538(aClass246_Sub3_Sub4_Sub4_5969.aByte5088, aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079, aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084, 24111) - aClass246_Sub3_Sub4_Sub4_5969.anInt6463, Class215.anInt1614, (byte)108, aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079);
                        }
                    }
                    aClass246_Sub3_Sub4_Sub4_5969.method3075(-10462, Class279.anInt2099);
                    Class222.method2826(aClass246_Sub3_Sub4_Sub4_5969, true);
                }
            }
            if (n != -9627) {
                method2711(3, 3, 3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nh.A(" + n + ')');
        }
    }
    
    final void method2717(final int n) {
        try {
            synchronized (this.aClass74_1561) {
                this.aClass74_1561.method722(true);
            }
            if (n != 64) {
                this.aClass74_1561 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nh.J(" + n + ')');
        }
    }
    
    final void method2718(final int n, final boolean aBoolean1558) {
        try {
            if (!aBoolean1558 != !this.aBoolean1558) {
                this.aBoolean1558 = aBoolean1558;
                if (n > -30) {
                    this.method2712(15, 22);
                }
                this.method2715(94);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nh.F(" + n + ',' + aBoolean1558 + ')');
        }
    }
    
    final void method2719(final byte b) {
        try {
            synchronized (this.aClass79_1557) {
                this.aClass79_1557.method806((byte)(-86));
            }
            synchronized (this.aClass79_1560) {
                this.aClass79_1560.method806((byte)104);
            }
            synchronized (this.aClass74_1561) {
                this.aClass74_1561.method725(13937);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nh.E(" + b + ')');
        }
    }
    
    final void method2720(final int n, final int n2) {
        try {
            synchronized (this.aClass79_1557) {
                this.aClass79_1557.method800((byte)62, n);
            }
            if (n2 != 1) {
                this.method2714(50, (byte)(-48));
            }
            synchronized (this.aClass79_1560) {
                this.aClass79_1560.method800((byte)62, n);
            }
            synchronized (this.aClass74_1561) {
                this.aClass74_1561.method724((byte)96, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nh.I(" + n + ',' + n2 + ')');
        }
    }
    
    final void method2721(final int n) {
        try {
            synchronized (this.aClass79_1560) {
                if (n <= 13) {
                    this.aClass79_1560 = null;
                }
                this.aClass79_1560.method794(38);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nh.B(" + n + ')');
        }
    }
    
    final Class332 method2722(final Class43 class43, final boolean b, final int anInt3480, final int anInt3481, final int anInt3482, final Class313 class44, final ha ha, final int anInt3483, final boolean b2, final int anInt3484, final ha ha2, final boolean b3) {
        try {
            final Class332 method2710 = this.method2710(anInt3481, anInt3480, ha2, class44, anInt3484, anInt3483, anInt3482, 24056);
            if (method2710 != null) {
                return method2710;
            }
            Class297 class45 = this.method2714(anInt3480, (byte)(-125));
            if (anInt3484 > 1 && class45.anIntArray2428 != null) {
                int n = -1;
                for (int i = 0; i < 10; ++i) {
                    if (class45.anIntArray2454[i] <= anInt3484 && ~class45.anIntArray2454[i] != -1) {
                        n = class45.anIntArray2428[i];
                    }
                }
                if (n != -1) {
                    class45 = this.method2714(n, (byte)(-116));
                }
            }
            final int[] method2711 = class45.method3488(anInt3482, anInt3483, b3, anInt3484, anInt3481, ha2, ha, (byte)(-125), class44, class43);
            if (method2711 == null) {
                return null;
            }
            Class332 class46;
            if (b2) {
                class46 = ha.method1748(-7962, 0, 36, 32, method2711, 36);
            }
            else {
                class46 = ha2.method1748(-7962, 0, 36, 32, method2711, 36);
            }
            if (!b2) {
                final Class73 class47 = new Class73();
                class47.anInt3486 = anInt3481;
                class47.anInt3480 = anInt3480;
                class47.aBoolean3488 = (class44 != null);
                class47.anInt3487 = anInt3482;
                class47.anInt3484 = anInt3484;
                class47.anInt3483 = ha2.anInt937;
                class47.anInt3481 = anInt3483;
                this.aClass74_1561.method729(class46, class47, false);
            }
            if (b) {
                this.method2718(-24, false);
            }
            return class46;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nh.L(" + ((class43 != null) ? "{...}" : "null") + ',' + b + ',' + anInt3480 + ',' + anInt3481 + ',' + anInt3482 + ',' + ((class44 != null) ? "{...}" : "null") + ',' + ((ha != null) ? "{...}" : "null") + ',' + anInt3483 + ',' + b2 + ',' + anInt3484 + ',' + ((ha2 != null) ? "{...}" : "null") + ',' + b3 + ')');
        }
    }
    
    Class205(final Class279 class279, final int anInt1555, final boolean aBoolean1558, final Class365 aClass365_1563, final Class207 aClass207_1559, final Class207 aClass207_1560) {
        this.aClass79_1557 = new Class79(64);
        this.aClass79_1560 = new Class79(50);
        this.aClass74_1561 = new Class74(250);
        this.aClass73_1562 = new Class73();
        try {
            this.anInt1555 = anInt1555;
            this.aClass365_1563 = aClass365_1563;
            this.aClass207_1556 = aClass207_1560;
            this.aBoolean1558 = aBoolean1558;
            this.aClass207_1559 = aClass207_1559;
            Label_0138: {
                if (this.aClass207_1559 == null) {
                    this.anInt1554 = 0;
                    if (!client.aBoolean3553) {
                        break Label_0138;
                    }
                }
                final int n = -1 + this.aClass207_1559.method2752((byte)(-11));
                this.anInt1554 = this.aClass207_1559.method2761(0, n) + 256 * n;
            }
            this.aStringArray1565 = new String[] { null, null, Class309.aClass309_2593.method3615(this.anInt1555, (byte)25), null, null };
            this.aStringArray1566 = new String[] { null, null, null, null, Class309.aClass309_2594.method3615(this.anInt1555, (byte)25) };
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nh.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + anInt1555 + ',' + aBoolean1558 + ',' + ((aClass365_1563 != null) ? "{...}" : "null") + ',' + ((aClass207_1559 != null) ? "{...}" : "null") + ',' + ((aClass207_1560 != null) ? "{...}" : "null") + ')');
        }
    }
}
