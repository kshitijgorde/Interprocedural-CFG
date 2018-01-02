// 
// Decompiled by Procyon v0.5.30
// 

final class Class226
{
    static int[] anIntArray1699;
    int anInt1700;
    int anInt1701;
    int anInt1702;
    int anInt1703;
    int anInt1704;
    static int anInt1705;
    static int anInt1706;
    int anInt1707;
    
    public static void method2853(final int n) {
        try {
            Class226.anIntArray1699 = null;
            if (n != 1) {
                method2854(true, false, 46, null, -102, 74, -54);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oga.B(" + n + ')');
        }
    }
    
    static final void method2854(final boolean b, final boolean aBoolean66, final int anInt5037, final Class207 aClass207_2025, final int anInt5038, final int anInt5039, final int anInt5040) {
        try {
            Class76_Sub8.anInt3770 = anInt5038;
            Class257.anInt1948 = 1;
            Class224_Sub3.anInt5037 = anInt5037;
            Class1.aBoolean66 = aBoolean66;
            Class98_Sub18.anInt3951 = anInt5040;
            Class269.aClass207_2025 = aClass207_2025;
            Class348.anInt2911 = anInt5039;
            if (b) {
                method2853(44);
            }
            Class116.aClass98_Sub31_Sub2_965 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oga.A(" + b + ',' + aBoolean66 + ',' + anInt5037 + ',' + ((aClass207_2025 != null) ? "{...}" : "null") + ',' + anInt5038 + ',' + anInt5039 + ',' + anInt5040 + ')');
        }
    }
    
    static {
        Class226.anIntArray1699 = new int[3];
    }
}
