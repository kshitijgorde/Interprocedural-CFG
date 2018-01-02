// 
// Decompiled by Procyon v0.5.30
// 

final class Class334 implements Interface18
{
    private Class363 aClass363_3467;
    static Class232 aClass232_3468;
    private Class52 aClass52_3469;
    static OutgoingOpcode aClass171_3470;
    static Class332 aClass332_3471;
    
    @Override
    public final boolean method59(final int n) {
        try {
            if (n != 14017) {
                Class334.aClass232_3468 = null;
            }
            return this.aClass363_3467.method3928(-1);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uha.A(" + n + ')');
        }
    }
    
    public static void method3763(final boolean b) {
        try {
            Class334.aClass171_3470 = null;
            Class334.aClass332_3471 = null;
            Class334.aClass232_3468 = null;
            if (!b) {
                Class334.aClass171_3470 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uha.E(" + b + ')');
        }
    }
    
    @Override
    public final void method58(final byte b) {
        try {
            if (b != -43) {
                this.aClass52_3469 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uha.C(" + b + ')');
        }
    }
    
    @Override
    public final void method60(final boolean b, final byte b2) {
        try {
            if (b2 >= -81) {
                Class334.aClass332_3471 = null;
            }
            final Class368 method3929 = this.aClass363_3467.method3929(23885, this.aClass52_3469.anInt3501);
            if (method3929 != null) {
                final int n = this.aClass52_3469.aClass63_3502.method545(Class98_Sub17_Sub1.anInt5782, this.aClass52_3469.anInt3496, (byte)110) - -this.aClass52_3469.anInt3498;
                final int n2 = this.aClass52_3469.aClass110_3499.method2088(this.aClass52_3469.anInt3497, Class246_Sub2.anInt5072, (byte)(-56)) + this.aClass52_3469.anInt3490;
                if (this.aClass52_3469.aBoolean3495) {
                    Class265.aHa1974.method1779(n, n2, this.aClass52_3469.anInt3496, this.aClass52_3469.anInt3497, this.aClass52_3469.anInt3489, 0);
                }
                int n3 = n2 + this.method3764(5, n2, Class98_Sub10_Sub34.aClass43_5730, method3929.aString3127, true, n) * 12;
                n3 += 8;
                if (this.aClass52_3469.aBoolean3495) {
                    Class265.aHa1974.method1795(n, n3, n + (this.aClass52_3469.anInt3496 - 1), n3, this.aClass52_3469.anInt3489, 0);
                }
                int n4 = ++n3 + 12 * this.method3764(5, n3, Class98_Sub10_Sub34.aClass43_5730, method3929.aString3123, true, n);
                n4 += 5;
                final int n5 = n4 + this.method3764(5, n4, Class98_Sub10_Sub34.aClass43_5730, method3929.aString3125, true, n) * 12;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uha.B(" + b + ',' + b2 + ')');
        }
    }
    
    private final int method3764(final int n, final int n2, final Class43 class43, final String s, final boolean b, final int n3) {
        try {
            if (!b) {
                this.aClass363_3467 = null;
            }
            return class43.method408(n + n3, null, -(n * 2) + this.aClass52_3469.anInt3496, s, 0, this.aClass52_3469.anInt3491, null, 0, (byte)(-119), this.aClass52_3469.anInt3492, null, 0, 0, 0, n + n2, -(n * 2) + this.aClass52_3469.anInt3497);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uha.D(" + n + ',' + n2 + ',' + ((class43 != null) ? "{...}" : "null") + ',' + ((s != null) ? "{...}" : "null") + ',' + b + ',' + n3 + ')');
        }
    }
    
    Class334(final Class363 aClass363_3467, final Class52 aClass52_3469) {
        try {
            this.aClass363_3467 = aClass363_3467;
            this.aClass52_3469 = aClass52_3469;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uha.<init>(" + ((aClass363_3467 != null) ? "{...}" : "null") + ',' + ((aClass52_3469 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class334.aClass232_3468 = new Class232();
        Class334.aClass171_3470 = new OutgoingOpcode(28, 3);
    }
}
