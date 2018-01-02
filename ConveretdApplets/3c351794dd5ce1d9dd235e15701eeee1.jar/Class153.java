import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class153
{
    static ha aHa1225;
    private Class79 aClass79_1226;
    private Class207 aClass207_1227;
    static volatile boolean aBoolean1228;
    static String aString1229;
    static boolean aBoolean1230;
    
    final void method2482(final byte b) {
        try {
            synchronized (this.aClass79_1226) {
                this.aClass79_1226.method806((byte)61);
            }
            if (b <= 17) {
                method2487(126);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kf.B(" + b + ')');
        }
    }
    
    final Class72 method2483(final int n, final int n2) {
        try {
            final Class72 class72;
            synchronized (this.aClass79_1226) {
                class72 = (Class72)this.aClass79_1226.method802(-128, n);
            }
            if (class72 != null) {
                return class72;
            }
            if (n2 < 117) {
                Class153.aBoolean1228 = false;
            }
            final byte[] method2745;
            synchronized (this.aClass207_1227) {
                method2745 = this.aClass207_1227.method2745(n, 1, false);
            }
            final Class72 class73 = new Class72();
            if (method2745 != null) {
                class73.method717(new Class98_Sub22(method2745), -52);
            }
            synchronized (this.aClass79_1226) {
                this.aClass79_1226.method805(n, class73, (byte)(-80));
            }
            return class73;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kf.A(" + n + ',' + n2 + ')');
        }
    }
    
    static final Class268 method2484(int n, final int n2, final byte b, final Class88 class88, final Component component) {
        try {
            if (Class64_Sub15.anInt3678 == 0) {
                throw new IllegalStateException();
            }
            if (~n2 > -1 || n2 >= 2) {
                throw new IllegalArgumentException();
            }
            if (n < 256) {
                n = 256;
            }
            try {
                final Class268 class89 = (Class268)Class.forName("Class268_Sub2").newInstance();
                class89.anInt2016 = n;
                class89.anIntArray2005 = new int[(Class151_Sub7.aBoolean5007 ? 2 : 1) * 256];
                class89.method3253(component);
                class89.anInt2010 = 1024 + (n & 0xFFFFFC00);
                if (class89.anInt2010 > 16384) {
                    class89.anInt2010 = 16384;
                }
                class89.method3250(class89.anInt2010);
                if (~PlayerUpdateMask.anInt529 < -1 && Class177.aClass103_1375 == null) {
                    Class177.aClass103_1375 = new Class103();
                    (Class177.aClass103_1375.aClass88_891 = class88).method858(PlayerUpdateMask.anInt529, Class177.aClass103_1375, 1);
                }
                if (Class177.aClass103_1375 != null) {
                    if (Class177.aClass103_1375.aClass268Array894[n2] != null) {
                        throw new IllegalArgumentException();
                    }
                    Class177.aClass103_1375.aClass268Array894[n2] = class89;
                }
                return class89;
            }
            catch (Throwable t) {
                try {
                    final Class268_Sub1 class268_Sub1 = new Class268_Sub1(class88, n2);
                    class268_Sub1.anInt2016 = n;
                    class268_Sub1.anIntArray2005 = new int[256 * (Class151_Sub7.aBoolean5007 ? 2 : 1)];
                    class268_Sub1.method3253(component);
                    class268_Sub1.anInt2010 = 16384;
                    if (b != -126) {
                        Class153.aBoolean1228 = true;
                    }
                    class268_Sub1.method3250(class268_Sub1.anInt2010);
                    if (PlayerUpdateMask.anInt529 > 0 && Class177.aClass103_1375 == null) {
                        Class177.aClass103_1375 = new Class103();
                        (Class177.aClass103_1375.aClass88_891 = class88).method858(PlayerUpdateMask.anInt529, Class177.aClass103_1375, 1);
                    }
                    if (Class177.aClass103_1375 != null) {
                        if (Class177.aClass103_1375.aClass268Array894[n2] != null) {
                            throw new IllegalArgumentException();
                        }
                        Class177.aClass103_1375.aClass268Array894[n2] = class268_Sub1;
                    }
                    return class268_Sub1;
                }
                catch (Throwable t2) {
                    return new Class268();
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kf.D(" + n + ',' + n2 + ',' + b + ',' + ((class88 != null) ? "{...}" : "null") + ',' + ((component != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method2485(final int n, final int n2) {
        try {
            synchronized (this.aClass79_1226) {
                this.aClass79_1226.method800((byte)62, n);
            }
            if (n2 != 1) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kf.H(" + n + ',' + n2 + ')');
        }
    }
    
    final void method2486(final byte b) {
        try {
            synchronized (this.aClass79_1226) {
                this.aClass79_1226.method794(97);
            }
            if (b >= -67) {
                this.method2485(-5, -3);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kf.C(" + b + ')');
        }
    }
    
    static final void method2487(final int n) {
        try {
            Class186.aClass148_3428 = new Class148();
            if (n != -1) {
                Class153.aBoolean1230 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kf.I(" + n + ')');
        }
    }
    
    public static void method2488(final int n) {
        try {
            Class153.aHa1225 = null;
            Class153.aString1229 = null;
            if (n != -1) {
                Class153.aString1229 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kf.F(" + n + ')');
        }
    }
    
    static final void method2489(final long n, final byte b) {
        try {
            final int anInt1545 = Class304.anInt2533 + Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5084;
            final int anInt1546 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt5079 - -Class98_Sub17.anInt3943;
            if (~(-anInt1545 + Class201.anInt1545) > 1999 || ~(Class201.anInt1545 + -anInt1545) < -2001 || Class224_Sub3_Sub1.anInt6147 + -anInt1546 < -2000 || ~(-anInt1546 + Class224_Sub3_Sub1.anInt6147) < -2001) {
                Class201.anInt1545 = anInt1545;
                Class224_Sub3_Sub1.anInt6147 = anInt1546;
            }
            if (Class201.anInt1545 != anInt1545) {
                final int n2 = anInt1545 + -Class201.anInt1545;
                int n3 = (int)(n2 * n / 320L);
                if (~n2 >= -1) {
                    if (n3 == 0) {
                        n3 = -1;
                    }
                    else if (~n3 > ~n2) {
                        n3 = n2;
                    }
                }
                else if (n3 != 0) {
                    if (~n3 < ~n2) {
                        n3 = n2;
                    }
                }
                else {
                    n3 = 1;
                }
                Class201.anInt1545 += n3;
            }
            if (anInt1546 != Class224_Sub3_Sub1.anInt6147) {
                final int n4 = -Class224_Sub3_Sub1.anInt6147 + anInt1546;
                int n5 = (int)(n * n4 / 320L);
                if (~n4 >= -1) {
                    if (~n5 != -1) {
                        if (~n4 < ~n5) {
                            n5 = n4;
                        }
                    }
                    else {
                        n5 = -1;
                    }
                }
                else if (n5 == 0) {
                    n5 = 1;
                }
                else if (n4 < n5) {
                    n5 = n4;
                }
                Class224_Sub3_Sub1.anInt6147 += n5;
            }
            Class119_Sub4.aFloat4740 += n * Class305.aFloat2545 / 6.0f;
            Class98_Sub22_Sub2.aFloat5794 += n * Class180.aFloat3405 / 6.0f;
            Class42_Sub2.method388(true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kf.G(" + n + ',' + b + ')');
        }
    }
    
    Class153(final Class279 class279, final int n, final Class207 aClass207_1227) {
        this.aClass79_1226 = new Class79(128);
        try {
            (this.aClass207_1227 = aClass207_1227).method2761(0, 1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kf.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_1227 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final int method2490(final int n, final boolean b) {
        try {
            if (b) {
                Class153.aHa1225 = null;
            }
            return n >>> -1052998200;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kf.E(" + n + ',' + b + ')');
        }
    }
    
    static {
        Class153.aBoolean1228 = true;
        Class153.aString1229 = null;
        Class153.aBoolean1230 = false;
    }
}
