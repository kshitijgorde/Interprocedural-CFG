// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub15 extends Class98
{
    static int anInt3915;
    static float[] aFloatArray3916;
    String aString3917;
    
    static final void method1144(final int n, final int n2, final boolean b, final int n3, final Class207 class207, final int n4, final long n5) {
        try {
            if (n2 != 4) {
                Class98_Sub15.anInt3915 = 126;
            }
            Class64_Sub13.method604(0, b, false, class207, n3, n, n4, n5);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gia.B(" + n + ',' + n2 + ',' + b + ',' + n3 + ',' + ((class207 != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    public static void method1145(final int n) {
        try {
            Class98_Sub15.aFloatArray3916 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gia.A(" + n + ')');
        }
    }
    
    public Class98_Sub15() {
    }
    
    Class98_Sub15(final String aString3917) {
        try {
            this.aString3917 = aString3917;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gia.<init>(" + ((aString3917 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub15.aFloatArray3916 = new float[4];
    }
}
