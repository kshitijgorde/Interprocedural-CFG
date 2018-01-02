// 
// Decompiled by Procyon v0.5.30
// 

final class Class49
{
    private int anInt411;
    private long aLong412;
    static OutgoingOpcode aClass171_413;
    static int[] anIntArray414;
    static int anInt415;
    static float aFloat416;
    
    static final void method477(final int n) {
        try {
            Class336.aClass148_2827.method2422((byte)47);
            if (n != -5788) {
                method477(-89);
            }
            Class62.anInt490 = 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dia.C(" + n + ')');
        }
    }
    
    private final void method478(final Class169 class169, final int n) {
        try {
            if (n != 13) {
                Class49.aClass171_413 = null;
            }
            this.aLong412 |= class169.anInt1300 << Class169.anInt1304 * this.anInt411++;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dia.E(" + ((class169 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final Class169 method479(final int n, final byte b) {
        try {
            if (b >= -73) {
                this.method481(99, 88);
            }
            return Class169.method2537(this.method481(15, n), (byte)40);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dia.B(" + n + ',' + b + ')');
        }
    }
    
    final int method480(final byte b) {
        try {
            if (b >= -7) {
                return 115;
            }
            return this.anInt411;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dia.A(" + b + ')');
        }
    }
    
    private final int method481(final int n, final int n2) {
        try {
            if (n != 15) {
                this.method481(81, -68);
            }
            return 0xF & (int)(this.aLong412 >> Class169.anInt1304 * n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dia.D(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method482(final int n) {
        try {
            Class49.anIntArray414 = null;
            if (n != 13) {
                method482(-50);
            }
            Class49.aClass171_413 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dia.F(" + n + ')');
        }
    }
    
    Class49(final Class169 class169) {
        try {
            this.anInt411 = 1;
            this.aLong412 = class169.anInt1300;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dia.<init>(" + ((class169 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class49(final Class169[] array) {
        try {
            for (int n = 0; ~array.length < ~n; ++n) {
                this.method478(array[n], 13);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dia.<init>(" + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class49.aClass171_413 = new OutgoingOpcode(38, 7);
        Class49.anInt415 = 0;
        Class49.anIntArray414 = new int[] { 7, 8, 9, 10, 11, 12, 13, 15 };
    }
}
