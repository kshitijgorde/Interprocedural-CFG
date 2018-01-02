// 
// Decompiled by Procyon v0.5.30
// 

final class Class244
{
    Class197 aClass197_1858;
    Class43 aClass43_1859;
    static int anInt1860;
    static Class6 aClass6_1861;
    
    static final void method2953(final byte b, final int anInt6054, final int anInt6055) {
        try {
            final Class98_Sub46_Sub17 method2628 = Class185.method2628(0, -71, 15);
            method2628.method1626((byte)(-103));
            method2628.anInt6051 = anInt6055;
            method2628.anInt6054 = anInt6054;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pga.B(" + b + ',' + anInt6054 + ',' + anInt6055 + ')');
        }
    }
    
    Class244(final Class43 aClass43_1859) {
        this.aClass43_1859 = null;
        this.aClass197_1858 = null;
        try {
            this.aClass43_1859 = aClass43_1859;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pga.<init>(" + ((aClass43_1859 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class244(final Class43 aClass43_1859, final Class197 aClass197_1858) {
        this.aClass43_1859 = null;
        this.aClass197_1858 = null;
        try {
            this.aClass197_1858 = aClass197_1858;
            this.aClass43_1859 = aClass43_1859;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pga.<init>(" + ((aClass43_1859 != null) ? "{...}" : "null") + ',' + ((aClass197_1858 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2954(final int n) {
        try {
            if (n <= 125) {
                Class244.aClass6_1861 = null;
            }
            Class244.aClass6_1861 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pga.A(" + n + ')');
        }
    }
    
    static {
        Class244.aClass6_1861 = new Class6("WIP", 2);
    }
}
