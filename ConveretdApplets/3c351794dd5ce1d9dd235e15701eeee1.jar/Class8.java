import java.awt.datatransfer.Clipboard;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class8
{
    private Class79 aClass79_109;
    static Class148 aClass148_110;
    private Class207 aClass207_111;
    static IncomingOpcode aClass58_112;
    static Clipboard aClipboard113;
    
    public static void method184(final byte b) {
        try {
            if (b == -109) {
                Class8.aClipboard113 = null;
                Class8.aClass58_112 = null;
                Class8.aClass148_110 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ai.C(" + b + ')');
        }
    }
    
    final Class98_Sub46_Sub18 method185(final int n, final int n2) {
        try {
            final Class98_Sub46_Sub18 class98_Sub46_Sub18;
            synchronized (this.aClass79_109) {
                class98_Sub46_Sub18 = (Class98_Sub46_Sub18)this.aClass79_109.method802(n - 133, n2);
            }
            if (class98_Sub46_Sub18 != null) {
                return class98_Sub46_Sub18;
            }
            final byte[] method2745;
            synchronized (this.aClass207_111) {
                method2745 = this.aClass207_111.method2745(n2, 5, false);
            }
            final Class98_Sub46_Sub18 class98_Sub46_Sub19 = new Class98_Sub46_Sub18();
            if (method2745 != null) {
                class98_Sub46_Sub19.method1628(new Class98_Sub22(method2745), -125);
            }
            synchronized (this.aClass79_109) {
                this.aClass79_109.method805(n2, class98_Sub46_Sub19, (byte)(-80));
            }
            if (n != 9) {
                method186(-13, null, 80, -89);
            }
            return class98_Sub46_Sub19;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ai.D(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method186(final int n, final Class293 class293, final int n2, final int n3) {
        try {
            if (Class98_Sub10_Sub9.aBoolean5585) {
                final Class149 class294 = (~Class98_Sub46_Sub1.anInt5945 != 0x0) ? Class98_Sub43_Sub1.aClass365_5897.method3940((byte)31, Class98_Sub46_Sub1.anInt5945) : null;
                if (client.method116(class293).method1667((byte)74) && ~(Class98_Sub4.anInt3826 & 0x20) != -1 && (class294 == null || ~class293.method3472(n ^ 0x56DA, class294.anInt1202, Class98_Sub46_Sub1.anInt5945) != ~class294.anInt1202)) {
                    Class293.method3470(false, true, 0L, Class336.anInt2823, class293.anInt2300, Class246_Sub3_Sub3.aString6156 + " -> " + class293.aString2224, false, class293.anInt2248, 59, class293.anInt2300 << 1322373568 | class293.anInt2248, class293.anInt2302, false, Class287_Sub2.aString3272);
                }
            }
            if (n == 59) {
                for (int i = 9; i >= 5; --i) {
                    final String method347 = Class38.method347(class293, (byte)66, i);
                    if (method347 != null) {
                        ++Class132.anInt1046;
                        Class293.method3470(false, true, i + 1, Class377.method3991(class293, -127, i), class293.anInt2300, class293.aString2224, false, class293.anInt2248, 1006, class293.anInt2248 | class293.anInt2300 << 75346272, class293.anInt2302, false, method347);
                    }
                }
                final String method348 = Class170.method2538(-1, class293);
                if (method348 != null) {
                    Class293.method3470(false, true, 0L, class293.anInt2254, class293.anInt2300, class293.aString2224, false, class293.anInt2248, 21, class293.anInt2300 << -1538321408 | class293.anInt2248, class293.anInt2302, false, method348);
                }
                for (int j = 4; j >= 0; --j) {
                    final String method349 = Class38.method347(class293, (byte)65, j);
                    if (method349 != null) {
                        ++Class132.anInt1046;
                        Class293.method3470(false, true, j + 1, Class377.method3991(class293, n ^ 0xFFFFFFB6, j), class293.anInt2300, class293.aString2224, false, class293.anInt2248, 49, class293.anInt2248 | class293.anInt2300 << -2042533440, class293.anInt2302, false, method349);
                    }
                }
                if (client.method116(class293).method1669(1964468)) {
                    if (class293.aString2333 == null) {
                        Class293.method3470(false, true, 0L, -1, class293.anInt2300, "", false, class293.anInt2248, 9, class293.anInt2248 | class293.anInt2300 << -35313376, class293.anInt2302, false, Class309.aClass309_2595.method3615(Class374.anInt3159, (byte)25));
                    }
                    else {
                        Class293.method3470(false, true, 0L, -1, class293.anInt2300, "", false, class293.anInt2248, 9, class293.anInt2248 | class293.anInt2300 << -1733821568, class293.anInt2302, false, class293.aString2333);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ai.A(" + n + ',' + ((class293 != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method187(final boolean b) {
        try {
            Class98_Sub17_Sub1.aBoolean5778 = false;
            Class98_Sub43.method1481(2);
            if (!b) {
                Class8.aClass58_112 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ai.B(" + b + ')');
        }
    }
    
    static final boolean method188(final boolean b) {
        try {
            if (b) {
                Class8.aClass148_110 = null;
            }
            return Class257.anInt1948 != 0 || Class366.aClass98_Sub31_Sub2_3122.method1354(-3619);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ai.E(" + b + ')');
        }
    }
    
    Class8(final Class279 class279, final int n, final Class207 aClass207_111) {
        this.aClass79_109 = new Class79(64);
        try {
            (this.aClass207_111 = aClass207_111).method2761(0, 5);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ai.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_111 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class8.aClass58_112 = new IncomingOpcode(35, -1);
    }
}
