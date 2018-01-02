// 
// Decompiled by Procyon v0.5.30
// 

final class Class341
{
    Class207 aClass207_2852;
    static OutgoingOpcode aClass171_2853;
    private Class79 aClass79_2854;
    private Class207 aClass207_2855;
    static int anInt2856;
    Class79 aClass79_2857;
    static int anInt2858;
    
    final void method3806(final int n, final boolean b) {
        try {
            if (b) {
                Class341.anInt2856 = 20;
            }
            synchronized (this.aClass79_2854) {
                this.aClass79_2854.method800((byte)62, n);
            }
            synchronized (this.aClass79_2857) {
                this.aClass79_2857.method800((byte)62, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "up.C(" + n + ',' + b + ')');
        }
    }
    
    final Class24 method3807(final int n, final int anInt228) {
        try {
            final Class24 class24;
            synchronized (this.aClass79_2854) {
                class24 = (Class24)this.aClass79_2854.method802(-126, anInt228);
            }
            if (class24 != null) {
                return class24;
            }
            final byte[] method2745;
            synchronized (this.aClass207_2855) {
                method2745 = this.aClass207_2855.method2745(anInt228, 36, false);
            }
            final Class24 class25 = new Class24();
            class25.anInt228 = anInt228;
            class25.aClass341_233 = this;
            if (method2745 != null) {
                class25.method290(new Class98_Sub22(method2745), 5);
            }
            class25.method291(-25798);
            synchronized (this.aClass79_2854) {
                this.aClass79_2854.method805(anInt228, class25, (byte)(-80));
            }
            return class25;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "up.B(" + n + ',' + anInt228 + ')');
        }
    }
    
    final void method3808(final int n) {
        try {
            synchronized (this.aClass79_2854) {
                this.aClass79_2854.method794(84);
                if (n != 0) {
                    Class341.anInt2856 = 80;
                }
            }
            synchronized (this.aClass79_2857) {
                this.aClass79_2857.method794(n ^ 0x3D);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "up.F(" + n + ')');
        }
    }
    
    final void method3809(final int n, final int n2, final int n3) {
        try {
            if (n2 != -30502) {
                Class341.aClass171_2853 = null;
            }
            this.aClass79_2854 = new Class79(n3);
            this.aClass79_2857 = new Class79(n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "up.A(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method3810(final byte b) {
        try {
            while (~Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1258(Class65.anInt496, 75) <= -16) {
                final int bits = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-111), 15);
                if (bits == 32767) {
                    break;
                }
                boolean b2 = false;
                Class98_Sub39 class98_Sub39 = (Class98_Sub39)Class260.aClass377_3254.method3990(bits, -1);
                if (class98_Sub39 == null) {
                    final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub1 = new Class246_Sub3_Sub4_Sub2_Sub1();
                    class246_Sub3_Sub4_Sub2_Sub1.anInt6369 = bits;
                    class98_Sub39 = new Class98_Sub39(class246_Sub3_Sub4_Sub2_Sub1);
                    Class260.aClass377_3254.method3996(class98_Sub39, bits, -1);
                    b2 = true;
                    Class163.aClass98_Sub39Array3516[Class98_Sub10_Sub20.anInt5640++] = class98_Sub39;
                }
                final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                Class325.anIntArray2726[Class150.anInt1211++] = bits;
                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6406 = Class201.anInt1544;
                if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504 != null && aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.method2302((byte)65)) {
                    Class98_Sub43_Sub4.method1504(aClass246_Sub3_Sub4_Sub2_Sub1_4187, -16255);
                }
                aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3054(Class4.aClass301_85.method3538(5, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-64), 14)), 1);
                final int bits2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-67), 1);
                int bits3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-24), 5);
                if (~bits3 < -16) {
                    bits3 -= 32;
                }
                int bits4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-34), 5);
                if (~bits4 < -16) {
                    bits4 -= 32;
                }
                final int bits5 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-25), 2);
                if (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-68), 1) == 1) {
                    Class76_Sub11.anIntArray3796[Class65.anInt502++] = bits;
                }
                final int n = 4 + Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readBits((byte)(-70), 3) << 280873515 & 0x3EE3;
                aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3045((byte)87, aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.anInt1112);
                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6414 = aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.anInt1091 << -90290813;
                if (b2) {
                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3047(n, true, 31);
                }
                aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3049(aClass246_Sub3_Sub4_Sub2_Sub1_4187.method3034(0), bits3 + Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6437[0], ~bits2 == 0xFFFFFFFE, (byte)(-106), bits4 + Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anIntArray6438[0], bits5);
                if (!aClass246_Sub3_Sub4_Sub2_Sub1_4187.aClass141_6504.method2302((byte)24)) {
                    continue;
                }
                Class98_Sub31_Sub4.method1383(null, null, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6437[0], 0, 3, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6438[0], aClass246_Sub3_Sub4_Sub2_Sub1_4187.aByte5088, aClass246_Sub3_Sub4_Sub2_Sub1_4187);
            }
            if (b >= 112) {
                Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1254((byte)120);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "up.G(" + b + ')');
        }
    }
    
    public static void method3811(final int n) {
        try {
            if (n == -8433) {
                Class341.aClass171_2853 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "up.H(" + n + ')');
        }
    }
    
    static final void method3812(final int n, final Class293 class293) {
        try {
            if (Class77_Sub1.anInt3803 == class293.anInt2250) {
                aa_Sub3.aBooleanArray3574[class293.anInt2238] = true;
            }
            if (n != 1) {
                method3811(91);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "up.D(" + n + ',' + ((class293 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class341(final Class279 class279, final int n, final Class207 aClass207_2855, final Class207 aClass207_2856) {
        this.aClass79_2854 = new Class79(128);
        this.aClass79_2857 = new Class79(64);
        try {
            this.aClass207_2852 = aClass207_2856;
            (this.aClass207_2855 = aClass207_2855).method2761(0, 36);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "up.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_2855 != null) ? "{...}" : "null") + ',' + ((aClass207_2856 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method3813(final int n) {
        try {
            synchronized (this.aClass79_2854) {
                if (n != 36) {
                    return;
                }
                this.aClass79_2854.method806((byte)(-118));
            }
            synchronized (this.aClass79_2857) {
                this.aClass79_2857.method806((byte)61);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "up.E(" + n + ')');
        }
    }
    
    static {
        Class341.aClass171_2853 = new OutgoingOpcode(17, -1);
        Class341.anInt2856 = 100;
        Class341.anInt2858 = -1;
    }
}
