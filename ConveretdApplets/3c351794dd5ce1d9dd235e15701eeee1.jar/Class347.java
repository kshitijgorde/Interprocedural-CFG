import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class347
{
    static int[] anIntArray2906;
    static int anInt2907;
    static Class98_Sub46_Sub8 aClass98_Sub46_Sub8_2908;
    
    public static void method3832(final byte b) {
        try {
            Class347.aClass98_Sub46_Sub8_2908 = null;
            Class347.anIntArray2906 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vc.B(" + b + ')');
        }
    }
    
    static final void method3833(final int n, final int n2) {
        try {
            Class185.method2628(n, -70, n2).method1621(0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vc.A(" + n + ',' + n2 + ')');
        }
    }
    
    static final int method3834(final int n, char lowerCase, final byte b) {
        try {
            if (b > -69) {
                return -103;
            }
            char c = (char)(lowerCase << -1402494108);
            if (Character.isUpperCase(lowerCase) || Character.isTitleCase(lowerCase)) {
                lowerCase = Character.toLowerCase(lowerCase);
                c = (char)('\u0001' + (lowerCase << -1894868316));
            }
            return c;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vc.D(" + n + ',' + lowerCase + ',' + b + ')');
        }
    }
    
    static final Class202 method3835(final int n, final ha_Sub1 ha_Sub1, final int n2, final String s) {
        try {
            final int glGenProgramARB = OpenGL.glGenProgramARB();
            OpenGL.glBindProgramARB(n2, glGenProgramARB);
            OpenGL.glProgramStringARB(n2, 34933, s);
            OpenGL.glGetIntegerv(34379, Class11.anIntArray123, 0);
            if (Class11.anIntArray123[0] != -1) {
                OpenGL.glBindProgramARB(n2, 0);
                return null;
            }
            OpenGL.glBindProgramARB(n2, n);
            return new Class202(ha_Sub1, n2, glGenProgramARB);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vc.C(" + n + ',' + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n2 + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class347.anInt2907 = 0;
    }
}
