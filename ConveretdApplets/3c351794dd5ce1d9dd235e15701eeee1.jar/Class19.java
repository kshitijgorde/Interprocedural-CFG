import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class19 implements Interface18
{
    static String aString3442;
    static int[][] anIntArrayArray3443;
    private Class43 aClass43_3444;
    private Class207 aClass207_3445;
    private Class315 aClass315_3446;
    static short[] aShortArray3447;
    static String aString3448;
    private Class207 aClass207_3449;
    static int anInt3450;
    static int[] anIntArray3451;
    
    @Override
    public final void method60(final boolean b, final byte b2) {
        try {
            if (b2 > -81) {
                this.method59(-24);
            }
            if (b) {
                this.aClass43_3444.method408(this.aClass315_3446.aClass63_3528.method545(Class98_Sub17_Sub1.anInt5782, this.aClass315_3446.anInt3532, (byte)41) - -this.aClass315_3446.anInt3521, null, this.aClass315_3446.anInt3532, this.aClass315_3446.aString3519, 0, this.aClass315_3446.anInt3531, null, this.aClass315_3446.anInt3524, (byte)(-67), this.aClass315_3446.anInt3523, null, this.aClass315_3446.anInt3530, 0, this.aClass315_3446.anInt3534, this.aClass315_3446.aClass110_3522.method2088(this.aClass315_3446.anInt3526, Class246_Sub2.anInt5072, (byte)(-56)) - -this.aClass315_3446.anInt3520, this.aClass315_3446.anInt3526);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bh.B(" + b + ',' + b2 + ')');
        }
    }
    
    @Override
    public final boolean method59(final int n) {
        try {
            boolean b = true;
            if (!this.aClass207_3445.method2742(-79, this.aClass315_3446.anInt3525)) {
                b = false;
            }
            if (n != 14017) {
                this.aClass43_3444 = null;
            }
            if (!this.aClass207_3449.method2742(n - 14055, this.aClass315_3446.anInt3525)) {
                b = false;
            }
            return b;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bh.A(" + n + ')');
        }
    }
    
    @Override
    public final void method58(final byte b) {
        try {
            if (b != -43) {
                method251(-62);
            }
            this.aClass43_3444 = Class265.aHa1974.method1804(Class119_Sub1.method2182(this.aClass207_3449, true, this.aClass315_3446.anInt3525), Class324.method3684(this.aClass207_3445, this.aClass315_3446.anInt3525), true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bh.C(" + b + ')');
        }
    }
    
    public static void method251(final int n) {
        try {
            if (n != 51) {
                method251(-6);
            }
            Class19.anIntArrayArray3443 = null;
            Class19.anIntArray3451 = null;
            Class19.aString3442 = null;
            Class19.aShortArray3447 = null;
            Class19.aString3448 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bh.D(" + n + ')');
        }
    }
    
    Class19(final Class207 aClass207_3445, final Class207 aClass207_3446, final Class315 aClass315_3446) {
        try {
            this.aClass207_3445 = aClass207_3445;
            this.aClass315_3446 = aClass315_3446;
            this.aClass207_3449 = aClass207_3446;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bh.<init>(" + ((aClass207_3445 != null) ? "{...}" : "null") + ',' + ((aClass207_3446 != null) ? "{...}" : "null") + ',' + ((aClass315_3446 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class19.anIntArrayArray3443 = new int[6][];
        Class19.aShortArray3447 = new short[] { 20, 22, 44, 47, 51, 3, 45, 19 };
        String lowerCase = "Unknown";
        try {
            lowerCase = System.getProperty("java.vendor").toLowerCase();
        }
        catch (Exception ex) {}
        lowerCase.toLowerCase();
        String lowerCase2 = "Unknown";
        try {
            lowerCase2 = System.getProperty("java.version").toLowerCase();
        }
        catch (Exception ex2) {}
        lowerCase2.toLowerCase();
        String lowerCase3 = "Unknown";
        try {
            lowerCase3 = System.getProperty("os.name").toLowerCase();
        }
        catch (Exception ex3) {}
        Class19.aString3448 = lowerCase3.toLowerCase();
        String lowerCase4 = "Unknown";
        try {
            lowerCase4 = System.getProperty("os.arch").toLowerCase();
        }
        catch (Exception ex4) {}
        Class19.aString3442 = lowerCase4.toLowerCase();
        String lowerCase5 = "Unknown";
        try {
            lowerCase5 = System.getProperty("os.version").toLowerCase();
        }
        catch (Exception ex5) {}
        lowerCase5.toLowerCase();
        String lowerCase6 = "~/";
        try {
            lowerCase6 = System.getProperty("user.home").toLowerCase();
        }
        catch (Exception ex6) {}
        new File(lowerCase6);
        Class19.anIntArray3451 = new int[1000];
    }
}
