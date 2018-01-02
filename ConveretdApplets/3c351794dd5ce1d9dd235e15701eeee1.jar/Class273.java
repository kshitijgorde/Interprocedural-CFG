import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class273
{
    static int anInt2039;
    int anInt2040;
    private ha_Sub3_Sub2 aHa_Sub3_Sub2_2041;
    
    static final void method3280(final byte b) {
        try {
            for (int n = 0; ~Class306.anInt2566 < ~n; ++n) {
                final Class338 class338 = Class245.aClass338Array1865[n];
                if (~class338.aByte2840 == 0xFFFFFFFC) {
                    if (class338.aClass98_Sub31_Sub5_2836 != null) {
                        Class81.aClass98_Sub31_Sub3_619.method1374(class338.aClass98_Sub31_Sub5_2836);
                    }
                    else {
                        class338.anInt2832 = Integer.MIN_VALUE;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qv.A(" + b + ')');
        }
    }
    
    @Override
    protected final void finalize() throws Throwable {
        try {
            this.aHa_Sub3_Sub2_2041.method2085(true, this.anInt2040);
            super.finalize();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qv.finalize()");
        }
    }
    
    static final byte[] method3281(final int n, final File file) {
        try {
            return Class375.method3988(file, (byte)78, (int)file.length());
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qv.B(" + n + ',' + ((file != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class273(final ha_Sub3_Sub2 aHa_Sub3_Sub2_2041, final int n, final int anInt2040) {
        try {
            this.anInt2040 = anInt2040;
            this.aHa_Sub3_Sub2_2041 = aHa_Sub3_Sub2_2041;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qv.<init>(" + ((aHa_Sub3_Sub2_2041 != null) ? "{...}" : "null") + ',' + n + ',' + anInt2040 + ')');
        }
    }
    
    static {
        Class273.anInt2039 = 0;
    }
}
