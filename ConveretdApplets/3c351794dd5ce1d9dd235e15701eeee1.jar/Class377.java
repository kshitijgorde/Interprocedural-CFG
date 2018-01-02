// 
// Decompiled by Procyon v0.5.30
// 

final class Class377
{
    static int[] anIntArray3178;
    int anInt3179;
    Class98[] aClass98Array3180;
    private long aLong3181;
    static byte[][] aByteArrayArray3182;
    static int anInt3183;
    private Class98 aClass98_3184;
    private Class98 aClass98_3185;
    private int anInt3186;
    
    final Class98 method3990(final long aLong3181, final int n) {
        try {
            this.aLong3181 = aLong3181;
            final Class98 class98 = this.aClass98Array3180[(int)(aLong3181 & this.anInt3179 + n)];
            this.aClass98_3184 = class98.aClass98_836;
            while (this.aClass98_3184 != class98) {
                if (~aLong3181 == ~this.aClass98_3184.aLong832) {
                    final Class98 aClass98_3184 = this.aClass98_3184;
                    this.aClass98_3184 = this.aClass98_3184.aClass98_836;
                    return aClass98_3184;
                }
                this.aClass98_3184 = this.aClass98_3184.aClass98_836;
            }
            return this.aClass98_3184 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wr.J(" + aLong3181 + ',' + n + ')');
        }
    }
    
    static final int method3991(final Class293 class293, final int n, final int n2) {
        try {
            if (n >= -23) {
                method3991(null, -63, -59);
            }
            if (!client.method116(class293).method1666((byte)(-72), n2) && class293.anObjectArray2329 == null) {
                return -1;
            }
            if (class293.anIntArray2326 != null && ~class293.anIntArray2326.length < ~n2) {
                return class293.anIntArray2326[n2];
            }
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wr.K(" + ((class293 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    final int method3992(final Class98[] array, final byte b) {
        try {
            int n = 0;
            for (int n2 = 0; this.anInt3179 > n2; ++n2) {
                for (Class98 class98 = this.aClass98Array3180[n2], class99 = class98.aClass98_836; class98 != class99; class99 = class99.aClass98_836) {
                    array[n++] = class99;
                }
            }
            if (b != 74) {
                method4000((byte)120);
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wr.B(" + ((array != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final Class98 method3993(final int n) {
        try {
            if (this.aClass98_3184 == null) {
                return null;
            }
            while (this.aClass98_3184 != this.aClass98Array3180[(int)(-1 + this.anInt3179 & this.aLong3181)]) {
                if (~this.aLong3181 == ~this.aClass98_3184.aLong832) {
                    final Class98 aClass98_3184 = this.aClass98_3184;
                    this.aClass98_3184 = this.aClass98_3184.aClass98_836;
                    return aClass98_3184;
                }
                this.aClass98_3184 = this.aClass98_3184.aClass98_836;
            }
            return this.aClass98_3184 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wr.D(" + n + ')');
        }
    }
    
    final void method3994(final int n) {
        try {
            for (int n2 = 0; ~this.anInt3179 < ~n2; ++n2) {
                final Class98 class98 = this.aClass98Array3180[n2];
                while (true) {
                    final Class98 aClass98_836 = class98.aClass98_836;
                    if (aClass98_836 == class98) {
                        break;
                    }
                    aClass98_836.method942(79);
                }
            }
            if (n < -59) {
                this.aClass98_3184 = null;
                this.aClass98_3185 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wr.H(" + n + ')');
        }
    }
    
    final Class98 method3995(final int n) {
        try {
            if (~this.anInt3186 < n && this.aClass98Array3180[this.anInt3186 - 1] != this.aClass98_3185) {
                final Class98 aClass98_3185 = this.aClass98_3185;
                this.aClass98_3185 = aClass98_3185.aClass98_836;
                return aClass98_3185;
            }
            while (~this.anInt3186 > ~this.anInt3179) {
                final Class98 aClass98_3186 = this.aClass98Array3180[this.anInt3186++].aClass98_836;
                if (aClass98_3186 != this.aClass98Array3180[this.anInt3186 - 1]) {
                    this.aClass98_3185 = aClass98_3186.aClass98_836;
                    return aClass98_3186;
                }
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wr.F(" + n + ')');
        }
    }
    
    final void method3996(final Class98 class98, final long aLong832, final int n) {
        try {
            if (class98.aClass98_833 != null) {
                class98.method942(58);
            }
            final Class98 aClass98_836 = this.aClass98Array3180[(int)(aLong832 & n + this.anInt3179)];
            class98.aClass98_836 = aClass98_836;
            class98.aClass98_833 = aClass98_836.aClass98_833;
            class98.aClass98_833.aClass98_836 = class98;
            class98.aLong832 = aLong832;
            class98.aClass98_836.aClass98_833 = class98;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wr.G(" + ((class98 != null) ? "{...}" : "null") + ',' + aLong832 + ',' + n + ')');
        }
    }
    
    Class377(final int anInt3179) {
        this.anInt3186 = 0;
        try {
            this.anInt3179 = anInt3179;
            this.aClass98Array3180 = new Class98[anInt3179];
            for (int n = 0; ~n > ~anInt3179; ++n) {
                final Class98[] aClass98Array3180 = this.aClass98Array3180;
                final int n2 = n;
                final Class98 class98 = new Class98();
                aClass98Array3180[n2] = class98;
                final Class98 class99 = class98;
                class99.aClass98_833 = class99;
                class99.aClass98_836 = class99;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wr.<init>(" + anInt3179 + ')');
        }
    }
    
    final int method3997(final byte b) {
        try {
            if (b <= 40) {
                Class377.aByteArrayArray3182 = null;
            }
            return this.anInt3179;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wr.I(" + b + ')');
        }
    }
    
    final Class98 method3998(final int n) {
        try {
            if (n <= 93) {
                this.method3992(null, (byte)45);
            }
            this.anInt3186 = 0;
            return this.method3995(-1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wr.C(" + n + ')');
        }
    }
    
    final int method3999(final byte b) {
        try {
            int n = 0;
            for (int n2 = 0; ~this.anInt3179 < ~n2; ++n2) {
                for (Class98 class98 = this.aClass98Array3180[n2], class99 = class98.aClass98_836; class98 != class99; class99 = class99.aClass98_836) {
                    ++n;
                }
            }
            if (b != -6) {
                Class377.anIntArray3178 = null;
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wr.A(" + b + ')');
        }
    }
    
    public static void method4000(final byte b) {
        try {
            Class377.aByteArrayArray3182 = null;
            if (b > -62) {
                Class377.anInt3183 = -27;
            }
            Class377.anIntArray3178 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wr.E(" + b + ')');
        }
    }
}
