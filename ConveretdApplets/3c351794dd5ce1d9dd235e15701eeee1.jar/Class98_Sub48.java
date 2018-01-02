// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub48 extends Class98
{
    static int anInt4277;
    int anInt4278;
    static int anInt4279;
    static int anInt4280;
    static int anInt4281;
    int anInt4282;
    
    static final int method1660(final int n) {
        try {
            if (n <= 21) {
                return 2;
            }
            return 16;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vfa.B(" + n + ')');
        }
    }
    
    static final void method1661(int n, final int n2, final Class39 class39, final byte b, final float n3, final float n4, final int n5, final float n6, final int n7, final int n8, final float n9, final byte[] array, final float n10) {
        try {
            for (int i = 0; i < n2; ++i) {
                Class98_Sub10_Sub5_Sub1.method1018(n6, false, n, n4, n2, n3, class39, n8, n9, n5, array, n10, n7, i);
                n += n7 * n5;
            }
            if (b > -14) {
                Class98_Sub48.anInt4281 = 28;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vfa.A(" + n + ',' + n2 + ',' + ((class39 != null) ? "{...}" : "null") + ',' + b + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ',' + ((array != null) ? "{...}" : "null") + ',' + n10 + ')');
        }
    }
    
    Class98_Sub48(final int anInt4278, final int anInt4279) {
        try {
            this.anInt4278 = anInt4278;
            this.anInt4282 = anInt4279;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vfa.<init>(" + anInt4278 + ',' + anInt4279 + ')');
        }
    }
    
    static {
        Class98_Sub48.anInt4277 = 0;
        Class98_Sub48.anInt4281 = 0;
    }
}
