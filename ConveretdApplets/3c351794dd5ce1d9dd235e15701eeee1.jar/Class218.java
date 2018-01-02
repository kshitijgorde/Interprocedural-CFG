// 
// Decompiled by Procyon v0.5.30
// 

final class Class218
{
    static Class348 aClass348_1630;
    static int[] anIntArray1631;
    private Class246 aClass246_1632;
    private Class246 aClass246_1633;
    static Class212 aClass212_1634;
    static int anInt1635;
    static String aString1636;
    
    final void method2802(final int n) {
        try {
            while (true) {
                final Class246 aClass246_1874 = this.aClass246_1632.aClass246_1874;
                if (this.aClass246_1632 == aClass246_1874) {
                    break;
                }
                aClass246_1874.method2965((byte)(-2));
            }
            this.aClass246_1633 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "o.A(" + n + ')');
        }
    }
    
    final Class246 method2803(final byte b) {
        try {
            if (b != 15) {
                Class218.aClass348_1630 = null;
            }
            final Class246 aClass246_1874 = this.aClass246_1632.aClass246_1874;
            if (aClass246_1874 == this.aClass246_1632) {
                return this.aClass246_1633 = null;
            }
            this.aClass246_1633 = aClass246_1874.aClass246_1874;
            return aClass246_1874;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "o.I(" + b + ')');
        }
    }
    
    public static void method2804(final byte b) {
        try {
            Class218.aString1636 = null;
            Class218.aClass348_1630 = null;
            Class218.aClass212_1634 = null;
            Class218.anIntArray1631 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "o.D(" + b + ')');
        }
    }
    
    final Class246 method2805(final byte b) {
        try {
            if (b != -72) {
                Class218.aClass212_1634 = null;
            }
            final Class246 aClass246_1874 = this.aClass246_1632.aClass246_1874;
            if (this.aClass246_1632 == aClass246_1874) {
                return null;
            }
            aClass246_1874.method2965((byte)(-63));
            return aClass246_1874;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "o.B(" + b + ')');
        }
    }
    
    static final void method2806(final int n, final int anInt6054, final boolean b) {
        try {
            if (!b) {
                method2806(-21, -126, true);
            }
            final Class98_Sub46_Sub17 method2628 = Class185.method2628(n, -104, 5);
            method2628.method1626((byte)(-103));
            method2628.anInt6054 = anInt6054;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "o.K(" + n + ',' + anInt6054 + ',' + b + ')');
        }
    }
    
    static final void method2807(final int n, final int n2, final int n3) {
        final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2][n3];
        if (class172 != null) {
            Class129.method2227(class172.aClass246_Sub3_Sub5_1334);
            Class129.method2227(class172.aClass246_Sub3_Sub5_1326);
            if (class172.aClass246_Sub3_Sub5_1334 != null) {
                class172.aClass246_Sub3_Sub5_1334 = null;
            }
            if (class172.aClass246_Sub3_Sub5_1326 != null) {
                class172.aClass246_Sub3_Sub5_1326 = null;
            }
        }
    }
    
    final void method2808(final boolean b, final Class246 class246) {
        try {
            if (!b) {
                this.method2811(119);
            }
            if (class246.aClass246_1873 != null) {
                class246.method2965((byte)127);
            }
            class246.aClass246_1873 = this.aClass246_1632.aClass246_1873;
            class246.aClass246_1874 = this.aClass246_1632;
            class246.aClass246_1873.aClass246_1874 = class246;
            class246.aClass246_1874.aClass246_1873 = class246;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "o.G(" + b + ',' + ((class246 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final Class246 method2809(final boolean b) {
        try {
            final Class246 aClass246_1633 = this.aClass246_1633;
            if (this.aClass246_1632 == aClass246_1633) {
                return this.aClass246_1633 = null;
            }
            if (b) {
                return null;
            }
            this.aClass246_1633 = aClass246_1633.aClass246_1874;
            return aClass246_1633;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "o.H(" + b + ')');
        }
    }
    
    final Class246 method2810(final byte b) {
        try {
            final Class246 aClass246_1873 = this.aClass246_1632.aClass246_1873;
            if (b > -33) {
                Class218.aString1636 = null;
            }
            if (this.aClass246_1632 == aClass246_1873) {
                return this.aClass246_1633 = null;
            }
            this.aClass246_1633 = aClass246_1873.aClass246_1873;
            return aClass246_1873;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "o.E(" + b + ')');
        }
    }
    
    final int method2811(final int n) {
        try {
            if (n != 15) {
                method2804((byte)(-5));
            }
            int n2 = 0;
            for (Class246 class246 = this.aClass246_1632.aClass246_1874; class246 != this.aClass246_1632; class246 = class246.aClass246_1874, ++n2) {}
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "o.F(" + n + ')');
        }
    }
    
    final boolean method2812(final boolean b) {
        try {
            if (!b) {
                Class218.aString1636 = null;
            }
            return this.aClass246_1632.aClass246_1874 == this.aClass246_1632;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "o.C(" + b + ')');
        }
    }
    
    public Class218() {
        this.aClass246_1632 = new Class246();
        try {
            this.aClass246_1632.aClass246_1874 = this.aClass246_1632;
            this.aClass246_1632.aClass246_1873 = this.aClass246_1632;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "o.<init>()");
        }
    }
    
    static {
        Class218.anIntArray1631 = new int[14];
        Class218.aClass348_1630 = new Class348(12, 0, 1, 0);
        Class218.anInt1635 = 100;
    }
}
