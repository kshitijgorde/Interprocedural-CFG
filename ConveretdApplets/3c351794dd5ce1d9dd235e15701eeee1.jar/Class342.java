// 
// Decompiled by Procyon v0.5.30
// 

final class Class342
{
    static int[] anIntArray2859;
    static int[] anIntArray2860;
    static Class200 aClass200_2861;
    static long aLong2862;
    
    static final void method3814(final boolean aBoolean6244, final String aString3837, final int n, final String aString3838) {
        try {
            Class98_Sub5.aString3837 = aString3837;
            Class360.aString3064 = aString3838;
            Class246_Sub3_Sub4_Sub1.aBoolean6244 = aBoolean6244;
            if (!Class246_Sub3_Sub4_Sub1.aBoolean6244 && (Class98_Sub5.aString3837.equals("") || Class360.aString3064.equals(""))) {
                Class369.method3952(3, (byte)(-55));
            }
            else {
                Class76_Sub9.aBoolean3788 = false;
                if (~Class98_Sub46_Sub20_Sub2.anInt6317 != 0xFFFFFFFE) {
                    Class98_Sub48.anInt4277 = 0;
                    Class69_Sub1.anInt5330 = -1;
                }
                Class369.method3952(-3, (byte)(-55));
                Class139.anInt1087 = 0;
                Class151_Sub9.anInt5020 = 0;
                Class64_Sub16.anInt3680 = 1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uq.A(" + aBoolean6244 + ',' + ((aString3837 != null) ? "{...}" : "null") + ',' + n + ',' + ((aString3838 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method3815(final int n, final int n2) {
        try {
            Class212.aLong1599 = 1000000000L / n;
            if (n2 < 40) {
                method3815(-114, -57);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uq.C(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method3816(final int n) {
        try {
            if (n != 30935) {
                method3814(true, null, 125, null);
            }
            Class342.anIntArray2859 = null;
            Class342.aClass200_2861 = null;
            Class342.anIntArray2860 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uq.B(" + n + ')');
        }
    }
    
    static {
        Class342.anIntArray2860 = new int[1];
        Class342.anIntArray2859 = new int[8];
        Class342.aClass200_2861 = new Class200();
    }
}
