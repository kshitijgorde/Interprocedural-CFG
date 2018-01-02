// 
// Decompiled by Procyon v0.5.30
// 

final class Class189
{
    Interface2_Impl1 anInterface2_Impl1_1454;
    static int anInt1455;
    Interface2_Impl1 anInterface2_Impl1_1456;
    static Class293 aClass293_1457;
    boolean aBoolean1458;
    boolean aBoolean1459;
    
    final void method2639(final boolean b) {
        try {
            if (this.anInterface2_Impl1_1454 != null) {
                this.anInterface2_Impl1_1454.method72(!b);
            }
            if (!b) {
                method2642((byte)27);
            }
            this.aBoolean1458 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mia.D(" + b + ')');
        }
    }
    
    final boolean method2640(final int n) {
        try {
            if (n != 13156520) {
                Class189.aClass293_1457 = null;
            }
            return this.aBoolean1458 && !this.aBoolean1459;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mia.A(" + n + ')');
        }
    }
    
    public static void method2641(final byte b) {
        try {
            if (b > 11) {
                Class189.aClass293_1457 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mia.B(" + b + ')');
        }
    }
    
    static final int method2642(final byte b) {
        try {
            if (b != 42) {
                method2641((byte)(-66));
            }
            if (Class98_Sub46.anInt4261 == 1) {
                return Class98_Sub46_Sub13_Sub2.anInt6309;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mia.C(" + b + ')');
        }
    }
    
    Class189(final boolean aBoolean1459) {
        try {
            this.aBoolean1459 = aBoolean1459;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mia.<init>(" + aBoolean1459 + ')');
        }
    }
    
    static {
        Class189.anInt1455 = 13156520;
        Class189.aClass293_1457 = null;
    }
}
