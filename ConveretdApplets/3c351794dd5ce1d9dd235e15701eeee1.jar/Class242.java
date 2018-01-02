// 
// Decompiled by Procyon v0.5.30
// 

final class Class242
{
    static Class88 aClass88_1848;
    static Class85 aClass85_1849;
    Class358 aClass358_1850;
    static Class244[] aClass244Array1851;
    
    public static void method2933(final int n) {
        try {
            if (n > -30) {
                Class242.aClass88_1848 = null;
            }
            Class242.aClass88_1848 = null;
            Class242.aClass244Array1851 = null;
            Class242.aClass85_1849 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pf.A(" + n + ')');
        }
    }
    
    static final int method2934(final int n, final int n2, final int n3) {
        try {
            final int n4 = n2 - -(57 * n3);
            if (n != 11348) {
                method2935((byte)75, null);
            }
            final int n5 = n4 ^ n4 << 952799405;
            return 0xFF & (Integer.MAX_VALUE & 1376312589 + (789221 + n5 * n5 * 15731) * n5) >> -1652763981;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pf.B(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    public Class242() {
        this.aClass358_1850 = new Class358();
    }
    
    static final void method2935(final byte b, final Class207 aClass207_6206) {
        try {
            Class42_Sub1_Sub1.aClass207_6206 = aClass207_6206;
            if (b <= 15) {
                method2933(-93);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pf.C(" + b + ',' + ((aClass207_6206 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class242.aClass85_1849 = new Class85(13, 16);
        Class242.aClass244Array1851 = null;
    }
}
