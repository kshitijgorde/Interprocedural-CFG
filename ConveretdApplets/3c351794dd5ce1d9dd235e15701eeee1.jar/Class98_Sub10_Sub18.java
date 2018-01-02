// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub18 extends Class98_Sub10
{
    static int anInt5626;
    static long aLong5627;
    
    static final boolean method1054(final int n) {
        try {
            if (Class76_Sub7.aBoolean3761) {
                try {
                    return !(boolean)Class203.method2704("showingVideoAd", Class76_Sub11.anApplet3799, -26978);
                }
                catch (Throwable t) {}
            }
            if (n <= 67) {
                Class98_Sub10_Sub18.anInt5626 = 88;
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ll.D(" + n + ')');
        }
    }
    
    static final boolean method1055(final int n, final int n2, final byte b) {
        try {
            if (b != -11) {
                Class98_Sub10_Sub18.aLong5627 = -40L;
            }
            return (Class373_Sub2.method3974(n2, n, b - 101) | ~(0x70000 & n) != -1) || Class76_Sub7.method763(n, n2, false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ll.E(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    static final void method1056(final byte b, final int n) {
        try {
            if (b == 97) {
                Class185.method2628(n, b ^ 0xFFFFFFA6, 7).method1621(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ll.B(" + b + ',' + n + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            if (n != 255) {
                Class98_Sub10_Sub18.aLong5627 = -124L;
            }
            return Class64_Sub1.anIntArray3640;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ll.G(" + n + ',' + n2 + ')');
        }
    }
    
    public Class98_Sub10_Sub18() {
        super(0, true);
    }
    
    static {
        Class98_Sub10_Sub18.aLong5627 = 0L;
    }
}
