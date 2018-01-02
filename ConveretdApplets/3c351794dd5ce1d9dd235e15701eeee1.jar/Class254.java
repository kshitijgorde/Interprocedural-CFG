// 
// Decompiled by Procyon v0.5.30
// 

final class Class254
{
    private int anInt1938;
    private Class98_Sub46[] aClass98_Sub46Array1939;
    static OutgoingOpcode aClass171_1940;
    private Class98_Sub46 aClass98_Sub46_1941;
    private long aLong1942;
    static Class332[] aClass332Array1943;
    static int anInt1944;
    
    final void method3185(final byte b, final Class98_Sub46 class98_Sub46, final long aLong4259) {
        try {
            if (class98_Sub46.aClass98_Sub46_4265 != null) {
                class98_Sub46.method1524((byte)(-90));
            }
            final Class98_Sub46 aClass98_Sub46_4262 = this.aClass98_Sub46Array1939[(int)(-1 + this.anInt1938 & aLong4259)];
            class98_Sub46.aClass98_Sub46_4262 = aClass98_Sub46_4262;
            class98_Sub46.aClass98_Sub46_4265 = aClass98_Sub46_4262.aClass98_Sub46_4265;
            class98_Sub46.aClass98_Sub46_4265.aClass98_Sub46_4262 = class98_Sub46;
            if (b > -6) {
                this.aLong1942 = -42L;
            }
            class98_Sub46.aClass98_Sub46_4262.aClass98_Sub46_4265 = class98_Sub46;
            class98_Sub46.aLong4259 = aLong4259;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pu.E(" + b + ',' + ((class98_Sub46 != null) ? "{...}" : "null") + ',' + aLong4259 + ')');
        }
    }
    
    static final void method3186(final int n) {
        try {
            int n2 = 0;
            if (n < 6) {
                method3188(6);
            }
            for (int i = 0; i < Class165.anInt1276; ++i) {
                for (int n3 = 0; ~Class98_Sub10_Sub7.anInt5572 < ~n3; ++n3) {
                    if (Class253.method3175(i, n2, Class98_Sub46_Sub1.aClass172ArrayArrayArray5948, n3, (byte)(-76), true)) {
                        ++n2;
                    }
                    if (n2 >= 512) {
                        return;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pu.A(" + n + ')');
        }
    }
    
    static final boolean method3187(int n, int n2, int n3, final byte b, int n4, int n5, int n6, int n7, int n8, int n9) {
        try {
            if (!Class73.method719(n3, n9, n4, b ^ 0x52)) {
                return false;
            }
            n4 = Class114.anIntArray958[1];
            n3 = Class114.anIntArray958[2];
            n9 = Class114.anIntArray958[0];
            if (b != 82) {
                method3186(-21);
            }
            if (!Class73.method719(n7, n8, n, 0)) {
                return false;
            }
            n = Class114.anIntArray958[1];
            n7 = Class114.anIntArray958[2];
            n8 = Class114.anIntArray958[0];
            if (!Class73.method719(n5, n2, n6, 0)) {
                return false;
            }
            n6 = Class114.anIntArray958[1];
            n2 = Class114.anIntArray958[0];
            n5 = Class114.anIntArray958[2];
            return Class267.method3243(n8, n9, (byte)82, n7, n6, n4, n2, n, n3, n5);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pu.F(" + n + ',' + n2 + ',' + n3 + ',' + b + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    public static void method3188(final int n) {
        try {
            Class254.aClass171_1940 = null;
            if (n != -386) {
                method3187(80, 18, 123, (byte)83, -110, -108, -70, -39, -108, -12);
            }
            Class254.aClass332Array1943 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pu.C(" + n + ')');
        }
    }
    
    final Class98_Sub46 method3189(final long aLong1942, final byte b) {
        try {
            this.aLong1942 = aLong1942;
            final Class98_Sub46 class98_Sub46 = this.aClass98_Sub46Array1939[(int)(aLong1942 & this.anInt1938 - 1)];
            this.aClass98_Sub46_1941 = class98_Sub46.aClass98_Sub46_4262;
            while (this.aClass98_Sub46_1941 != class98_Sub46) {
                if (~this.aClass98_Sub46_1941.aLong4259 == ~aLong1942) {
                    final Class98_Sub46 aClass98_Sub46_1941 = this.aClass98_Sub46_1941;
                    this.aClass98_Sub46_1941 = this.aClass98_Sub46_1941.aClass98_Sub46_4262;
                    return aClass98_Sub46_1941;
                }
                this.aClass98_Sub46_1941 = this.aClass98_Sub46_1941.aClass98_Sub46_4262;
            }
            return this.aClass98_Sub46_1941 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pu.D(" + aLong1942 + ',' + b + ')');
        }
    }
    
    Class254(final int i) {
        try {
            this.anInt1938 = i;
            this.aClass98_Sub46Array1939 = new Class98_Sub46[i];
            for (int n = 0; i > n; ++n) {
                final Class98_Sub46[] aClass98_Sub46Array1939 = this.aClass98_Sub46Array1939;
                final int n2 = n;
                final Class98_Sub46 class98_Sub46 = new Class98_Sub46();
                aClass98_Sub46Array1939[n2] = class98_Sub46;
                final Class98_Sub46 class98_Sub47 = class98_Sub46;
                class98_Sub47.aClass98_Sub46_4262 = class98_Sub47;
                class98_Sub47.aClass98_Sub46_4265 = class98_Sub47;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pu.<init>(" + i + ')');
        }
    }
    
    final Class98_Sub46 method3190(final boolean b) {
        try {
            if (this.aClass98_Sub46_1941 == null) {
                return null;
            }
            if (b) {
                return null;
            }
            while (this.aClass98_Sub46Array1939[(int)(-1 + this.anInt1938 & this.aLong1942)] != this.aClass98_Sub46_1941) {
                if (this.aLong1942 == this.aClass98_Sub46_1941.aLong4259) {
                    final Class98_Sub46 aClass98_Sub46_1941 = this.aClass98_Sub46_1941;
                    this.aClass98_Sub46_1941 = this.aClass98_Sub46_1941.aClass98_Sub46_4262;
                    return aClass98_Sub46_1941;
                }
                this.aClass98_Sub46_1941 = this.aClass98_Sub46_1941.aClass98_Sub46_4262;
            }
            return this.aClass98_Sub46_1941 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pu.B(" + b + ')');
        }
    }
    
    static {
        Class254.aClass171_1940 = new OutgoingOpcode(35, 5);
        Class254.anInt1944 = 0;
    }
}
