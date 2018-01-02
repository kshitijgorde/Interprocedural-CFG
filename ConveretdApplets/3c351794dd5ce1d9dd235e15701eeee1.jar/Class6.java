// 
// Decompiled by Procyon v0.5.30
// 

final class Class6
{
    int anInt87;
    static int anInt88;
    static Class85 aClass85_89;
    static int[] anIntArray90;
    static int[] anIntArray91;
    
    public static void method179(final int n) {
        try {
            Class6.anIntArray91 = null;
            if (n != 2566) {
                method179(-105);
            }
            Class6.anIntArray90 = null;
            Class6.aClass85_89 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "afa.C(" + n + ')');
        }
    }
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "afa.toString()");
        }
    }
    
    static final Class196[] method180(final int n) {
        try {
            if (n < 1) {
                return null;
            }
            return new Class196[] { Class43.aClass196_375, Class146.aClass196_1182, Class246_Sub3_Sub4_Sub2_Sub2.aClass196_6543, Class207.aClass196_1578, Class98_Sub43_Sub2.aClass196_5908, Class134.aClass196_3458 };
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "afa.D(" + n + ')');
        }
    }
    
    Class6(final String s, final int anInt87) {
        try {
            this.anInt87 = anInt87;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "afa.<init>(" + ((s != null) ? "{...}" : "null") + ',' + anInt87 + ')');
        }
    }
    
    static final void method181(final byte b) throws Exception_Sub1 {
        try {
            Label_0039: {
                if (~Class98_Sub46.anInt4261 != 0xFFFFFFFE) {
                    Class154.aHa1231.method1764(0, 0);
                    if (!client.aBoolean3553) {
                        break Label_0039;
                    }
                }
                Class154.aHa1231.method1764(Class98_Sub46_Sub13_Sub2.anInt6309, Class272.anInt2037);
            }
            if (b != 10) {
                method179(-82);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "afa.B(" + b + ')');
        }
    }
    
    final int method182(final int n) {
        try {
            if (n > -37) {
                this.method182(121);
            }
            return this.anInt87;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "afa.A(" + n + ')');
        }
    }
    
    static {
        Class6.aClass85_89 = new Class85(0, 5);
        Class6.anIntArray91 = new int[1000];
        Class6.anIntArray90 = new int[] { 4, 2, 1, 1, 2, 2, 3, 1, 3, 3, 3, 2, 0 };
    }
}
