import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class301
{
    Class279 aClass279_2502;
    boolean aBoolean2503;
    private Class207 aClass207_2504;
    private Class79 aClass79_2505;
    Class207 aClass207_2506;
    static IncomingOpcode aClass58_2507;
    static Color[] aColorArray2508;
    Class79 aClass79_2509;
    Class79 aClass79_2510;
    int anInt2511;
    
    final void method3534(final int n) {
        try {
            synchronized (this.aClass79_2509) {
                this.aClass79_2509.method794(91);
            }
            if (n <= -22) {
                synchronized (this.aClass79_2510) {
                    this.aClass79_2510.method794(43);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sha.A(" + n + ')');
        }
    }
    
    final void method3535(final byte b) {
        try {
            if (b < -5) {
                synchronized (this.aClass79_2505) {
                    this.aClass79_2505.method806((byte)113);
                }
                synchronized (this.aClass79_2509) {
                    this.aClass79_2509.method806((byte)(-103));
                }
                synchronized (this.aClass79_2510) {
                    this.aClass79_2510.method806((byte)(-104));
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sha.E(" + b + ')');
        }
    }
    
    public static void method3536(final byte b) {
        try {
            Class301.aColorArray2508 = null;
            Class301.aClass58_2507 = null;
            if (b != -82) {
                Class301.aColorArray2508 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sha.C(" + b + ')');
        }
    }
    
    static final void method3537(final int n, final byte b, final int n2, final int n3, final int n4, final int n5) {
        try {
            if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4066.method641((byte)127) != 0 && n3 != 0 && ~Class306.anInt2566 > -51 && n2 != -1) {
                Class245.aClass338Array1865[Class306.anInt2566++] = new Class338((byte)1, n2, n3, n4, n5, 0, n, null);
            }
            if (b != 1) {
                Class301.aClass58_2507 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sha.B(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    final Class141 method3538(final int n, final int anInt1097) {
        try {
            final Class141 class141;
            synchronized (this.aClass79_2505) {
                if (n != 5) {
                    method3536((byte)88);
                }
                class141 = (Class141)this.aClass79_2505.method802(-124, anInt1097);
            }
            if (class141 != null) {
                return class141;
            }
            final byte[] method2745;
            synchronized (this.aClass207_2504) {
                method2745 = this.aClass207_2504.method2745(Class163.method2520(anInt1097, (byte)123), Class98_Sub46_Sub13_Sub2.method1598(anInt1097, n - 22650), false);
            }
            final Class141 class142 = new Class141();
            class142.anInt1097 = anInt1097;
            class142.aClass301_1133 = this;
            if (method2745 != null) {
                class142.method2297(new Class98_Sub22(method2745), true);
            }
            class142.method2295((byte)70);
            synchronized (this.aClass79_2505) {
                this.aClass79_2505.method805(anInt1097, class142, (byte)(-80));
            }
            return class142;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sha.D(" + n + ',' + anInt1097 + ')');
        }
    }
    
    final void method3539(final int n, final byte b) {
        try {
            synchronized (this.aClass79_2505) {
                this.aClass79_2505.method800((byte)62, n);
            }
            synchronized (this.aClass79_2509) {
                this.aClass79_2509.method800((byte)62, n);
            }
            synchronized (this.aClass79_2510) {
                this.aClass79_2510.method800((byte)62, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sha.H(" + n + ',' + b + ')');
        }
    }
    
    static final void method3540(final int n) {
        try {
            if (n != -2) {
                method3542(69);
            }
            if (Class113.aClass143_953 != null) {
                if (~Class113.aClass143_953.anInt1163 == 0xFFFFFFFE) {
                    Class113.aClass143_953 = null;
                }
                else if (Class113.aClass143_953.anInt1163 == 2) {
                    Class98_Sub10_Sub32.method1097(-18871, Class246_Sub3_Sub4_Sub2_Sub1.aString6507, Class364.aClass88_3104, 2);
                    Class113.aClass143_953 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sha.F(" + n + ')');
        }
    }
    
    final void method3541(final boolean b, final int anInt2511) {
        try {
            this.anInt2511 = anInt2511;
            synchronized (this.aClass79_2509) {
                if (!b) {
                    this.method3539(127, (byte)(-119));
                }
                this.aClass79_2509.method794(24);
            }
            synchronized (this.aClass79_2510) {
                this.aClass79_2510.method794(85);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sha.J(" + b + ',' + anInt2511 + ')');
        }
    }
    
    static final void method3542(final int n) {
        try {
            if (n != 50) {
                Class301.aColorArray2508 = null;
            }
            for (Class98_Sub36 class98_Sub36 = (Class98_Sub36)Class156_Sub1.aClass377_3277.method3998(97); class98_Sub36 != null; class98_Sub36 = (Class98_Sub36)Class156_Sub1.aClass377_3277.method3995(-1)) {
                Class98_Sub11.method1127((byte)67, class98_Sub36.anInt4160);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sha.L(" + n + ')');
        }
    }
    
    static final void method3543(final int n) {
        try {
            if (n != 16535) {
                Class301.aColorArray2508 = null;
            }
            Class315.aClient3529.method80(0);
            Class49.method477(-5788);
            Class98_Sub10_Sub6.anInt5569 = 0;
            Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 = 0;
            Class224_Sub1.anInt5031 = 0;
            Class98_Sub10_Sub21.aClass58_5641 = null;
            Class98_Sub30.aClass58_4094 = null;
            Class260.aClass58_3262 = null;
            Class191.method2650((byte)(-126));
            Class314.anInt2692 = 0;
            Class153.aString1229 = null;
            Class374.aClass147Array3157 = null;
            Class98_Sub28.anInt4078 = 0;
            Class32.anInt308 = 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sha.I(" + n + ')');
        }
    }
    
    final void method3544(final byte b) {
        try {
            if (b == 1) {
                synchronized (this.aClass79_2505) {
                    this.aClass79_2505.method794(67);
                }
                synchronized (this.aClass79_2509) {
                    this.aClass79_2509.method794(b + 119);
                }
                synchronized (this.aClass79_2510) {
                    this.aClass79_2510.method794(b + 59);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sha.G(" + b + ')');
        }
    }
    
    final void method3545(final int n, final boolean aBoolean2503) {
        try {
            if (n != 9179409) {
                Class301.aColorArray2508 = null;
            }
            if (aBoolean2503 != this.aBoolean2503) {
                this.aBoolean2503 = aBoolean2503;
                this.method3544((byte)1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sha.K(" + n + ',' + aBoolean2503 + ')');
        }
    }
    
    Class301(final Class279 aClass279_2502, final int n, final boolean aBoolean2503, final Class207 aClass207_2504, final Class207 aClass207_2505) {
        this.aClass79_2505 = new Class79(64);
        this.aClass79_2509 = new Class79(50);
        this.aClass79_2510 = new Class79(5);
        try {
            this.aClass207_2504 = aClass207_2504;
            this.aClass207_2506 = aClass207_2505;
            this.aBoolean2503 = aBoolean2503;
            this.aClass279_2502 = aClass279_2502;
            if (this.aClass207_2504 != null) {
                this.aClass207_2504.method2761(0, this.aClass207_2504.method2752((byte)(-11)) - 1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sha.<init>(" + ((aClass279_2502 != null) ? "{...}" : "null") + ',' + n + ',' + aBoolean2503 + ',' + ((aClass207_2504 != null) ? "{...}" : "null") + ',' + ((aClass207_2505 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class301.aClass58_2507 = new IncomingOpcode(100, 6);
        Class301.aColorArray2508 = new Color[] { new Color(9179409), new Color(3289650), new Color(3289650), new Color(3289650) };
    }
}
