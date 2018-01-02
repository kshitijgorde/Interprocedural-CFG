// 
// Decompiled by Procyon v0.5.30
// 

final class IncomingOpcode
{
    static Class65 aClass65_459;
    int anInt460;
    static int anInt461;
    private int anInt462;
    static int anInt463;
    
    static final Class6[] method520(final byte b) {
        try {
            if (b >= -54) {
                method522(11);
            }
            return new Class6[] { Class2.aClass6_68, Class1.aClass6_63, Class244.aClass6_1861 };
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ea.A(" + b + ')');
        }
    }
    
    final int method521(final byte b) {
        try {
            if (b <= 60) {
                this.method521((byte)(-127));
            }
            return this.anInt462;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ea.C(" + b + ')');
        }
    }
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ea.toString()");
        }
    }
    
    IncomingOpcode(final int anInt462, final int anInt463) {
        try {
            this.anInt460 = anInt463;
            this.anInt462 = anInt462;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ea.<init>(" + anInt462 + ',' + anInt463 + ')');
        }
    }
    
    public static void method522(final int n) {
        try {
            IncomingOpcode.aClass65_459 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ea.D(" + n + ')');
        }
    }
    
    static final boolean method523(final int n, final int n2, final int n3) {
        try {
            return n2 != -1 || (0x21 & n3) != 0x0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ea.B(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static {
        IncomingOpcode.anInt461 = -1;
        IncomingOpcode.aClass65_459 = new Class65();
    }
}
