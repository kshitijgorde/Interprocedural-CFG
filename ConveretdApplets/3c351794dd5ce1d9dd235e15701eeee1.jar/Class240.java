import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class240
{
    abstract long method2922(final byte p0);
    
    abstract void method2923(final boolean p0);
    
    abstract long method2924(final byte p0);
    
    final int method2925(final int n, final long n2) {
        try {
            final long method2922 = this.method2922((byte)(-93));
            if (method2922 > n) {
                Class246_Sub7.method3131(0, method2922);
            }
            return this.method2926(123, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pca.D(" + n + ',' + n2 + ')');
        }
    }
    
    abstract int method2926(final int p0, final long p1);
    
    static final Class273 method2927(final byte[] array, final ha_Sub3_Sub2 ha_Sub3_Sub2, final int n, final int n2) {
        try {
            if (array == null) {
                return null;
            }
            final int glGenProgramARB = OpenGL.glGenProgramARB();
            OpenGL.glBindProgramARB(n, glGenProgramARB);
            OpenGL.glProgramRawARB(n, 34933, array);
            OpenGL.glGetIntegerv(34379, Class4.anIntArray83, 0);
            if (~Class4.anIntArray83[0] != 0x0) {
                OpenGL.glBindProgramARB(n, 0);
                return null;
            }
            OpenGL.glBindProgramARB(n, 0);
            if (n2 != 25246) {
                return null;
            }
            return new Class273(ha_Sub3_Sub2, n, glGenProgramARB);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pca.F(" + ((array != null) ? "{...}" : "null") + ',' + ((ha_Sub3_Sub2 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
}
