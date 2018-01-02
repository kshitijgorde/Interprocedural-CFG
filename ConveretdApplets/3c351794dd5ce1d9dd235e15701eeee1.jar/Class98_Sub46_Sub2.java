// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class98_Sub46_Sub2 extends Class98_Sub46
{
    int anInt5950;
    static Class105 aClass105_5951;
    static int anInt5952;
    
    abstract Object method1533(final boolean p0);
    
    static final void method1534(final int n, final boolean b) {
        try {
            if (b) {
                method1534(-73, true);
            }
            if (n != 37) {
                if (n != 50) {
                    if (n == 75) {
                        Class278.aFloat2068 = 6.0f;
                    }
                    else if (n != 100) {
                        if (~n == 0xFFFFFF37) {
                            Class278.aFloat2068 = 16.0f;
                        }
                    }
                    else {
                        Class278.aFloat2068 = 8.0f;
                    }
                }
                else {
                    Class278.aFloat2068 = 4.0f;
                }
            }
            else {
                Class278.aFloat2068 = 3.0f;
            }
            Class169.anInt1307 = -1;
            Class169.anInt1307 = -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bda.A(" + n + ',' + b + ')');
        }
    }
    
    Class98_Sub46_Sub2(final int anInt5950) {
        try {
            this.anInt5950 = anInt5950;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bda.<init>(" + anInt5950 + ')');
        }
    }
    
    public static void method1535(final int n) {
        try {
            if (n == 26767) {
                Class98_Sub46_Sub2.aClass105_5951 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bda.D(" + n + ')');
        }
    }
    
    abstract boolean method1536(final int p0);
    
    static {
        Class98_Sub46_Sub2.aClass105_5951 = new Class105("", 12);
    }
}
