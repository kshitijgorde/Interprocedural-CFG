// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub8 extends Class98 implements Interface7
{
    static OutgoingOpcode aClass171_3264;
    char aChar3265;
    int anInt3266;
    long aLong3267;
    int anInt3268;
    int anInt3269;
    
    @Override
    public final long method18(final int n) {
        try {
            if (n >= -20) {
                method987(53);
            }
            return this.aLong3267;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cja.B(" + n + ')');
        }
    }
    
    @Override
    public final int method17(final boolean b) {
        try {
            if (!b) {
                this.aLong3267 = 43L;
            }
            return this.anInt3269;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cja.E(" + b + ')');
        }
    }
    
    static final void method986(final int n, final int n2, final Class293 class293, final int n3) {
        try {
            if (~class293.aByte2245 == -1) {
                class293.anInt2299 = class293.anInt2229;
            }
            else if (~class293.aByte2245 == 0xFFFFFFFE) {
                class293.anInt2299 = class293.anInt2229 + (-class293.anInt2258 + n2) / 2;
            }
            else if (class293.aByte2245 != 2) {
                if (~class293.aByte2245 != 0xFFFFFFFC) {
                    if (~class293.aByte2245 != 0xFFFFFFFB) {
                        class293.anInt2299 = -(class293.anInt2229 * n2 >> -1245136754) + (-class293.anInt2258 + n2);
                    }
                    else {
                        class293.anInt2299 = (n2 * class293.anInt2229 >> 925133390) + (n2 - class293.anInt2258) / 2;
                    }
                }
                else {
                    class293.anInt2299 = n2 * class293.anInt2229 >> -1820578994;
                }
            }
            else {
                class293.anInt2299 = n2 - class293.anInt2258 + -class293.anInt2229;
            }
            if (~class293.aByte2240 == -1) {
                class293.anInt2347 = class293.anInt2283;
            }
            else if (class293.aByte2240 == 1) {
                class293.anInt2347 = class293.anInt2283 + (n - class293.anInt2311) / 2;
            }
            else if (class293.aByte2240 != 2) {
                if (class293.aByte2240 != 3) {
                    if (~class293.aByte2240 == 0xFFFFFFFB) {
                        class293.anInt2347 = (-class293.anInt2311 + n) / 2 + (n * class293.anInt2283 >> 853340622);
                    }
                    else {
                        class293.anInt2347 = n + (-class293.anInt2311 + -(n * class293.anInt2283 >> -1549429234));
                    }
                }
                else {
                    class293.anInt2347 = class293.anInt2283 * n >> 1647618894;
                }
            }
            else {
                class293.anInt2347 = n - class293.anInt2311 - class293.anInt2283;
            }
            if (n3 > 105) {
                if (Class15.aBoolean169) {
                    if (~client.method116(class293).anInt4284 != -1 || class293.anInt2354 == 0) {
                        if (~class293.anInt2299 > -1) {
                            class293.anInt2299 = 0;
                        }
                        else if (n2 < class293.anInt2299 + class293.anInt2258) {
                            class293.anInt2299 = -class293.anInt2258 + n2;
                        }
                        if (class293.anInt2347 < 0) {
                            class293.anInt2347 = 0;
                        }
                        else if (n < class293.anInt2347 + class293.anInt2311) {
                            class293.anInt2347 = -class293.anInt2311 + n;
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cja.F(" + n + ',' + n2 + ',' + ((class293 != null) ? "{...}" : "null") + ',' + n3 + ')');
        }
    }
    
    @Override
    public final int method14(final int n) {
        try {
            return this.anInt3268;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cja.A(" + n + ')');
        }
    }
    
    public static void method987(final int n) {
        try {
            Class98_Sub8.aClass171_3264 = null;
            if (n < 103) {
                method986(38, -44, null, 87);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cja.G(" + n + ')');
        }
    }
    
    @Override
    public final int method16(final byte b) {
        try {
            if (b != 82) {
                return -4;
            }
            return this.anInt3266;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cja.C(" + b + ')');
        }
    }
    
    @Override
    public final char method15(final int n) {
        try {
            if (n != 13313) {
                return '4';
            }
            return this.aChar3265;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cja.D(" + n + ')');
        }
    }
    
    static {
        Class98_Sub8.aClass171_3264 = new OutgoingOpcode(74, -1);
    }
}
