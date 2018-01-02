// 
// Decompiled by Procyon v0.5.30
// 

final class Class224_Sub2_Sub1 extends Class224_Sub2
{
    static int anInt6141;
    static volatile boolean aBoolean6142;
    static int anInt6143;
    
    static final void method2838(final int n, final int n2) {
        try {
            Class185.method2628(n2, n - 139, n).method1621(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jm.A(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method2839(final int n, final int n2, final int n3) {
        final boolean b = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[0][n2][n3] != null && Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[0][n2][n3].aClass172_1330 != null;
        for (int i = n; i >= 0; --i) {
            if (Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[i][n2][n3] == null) {
                final Class172[] array = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[i][n2];
                final Class172 class172 = new Class172(i);
                array[n3] = class172;
                final Class172 class173 = class172;
                if (b) {
                    final Class172 class174 = class173;
                    ++class174.aByte1322;
                }
            }
        }
    }
    
    static {
        Class224_Sub2_Sub1.anInt6141 = -1;
        Class224_Sub2_Sub1.aBoolean6142 = false;
    }
}
