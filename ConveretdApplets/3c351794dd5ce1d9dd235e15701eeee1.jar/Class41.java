// 
// Decompiled by Procyon v0.5.30
// 

final class Class41
{
    static OutgoingOpcode aClass171_371;
    
    public static void method365(final int n) {
        try {
            Class41.aClass171_371 = null;
            if (n != 0) {
                Class41.aClass171_371 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cs.B(" + n + ')');
        }
    }
    
    static int method366(final int n, final int n2) {
        try {
            return n | n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cs.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method367(final double n, final int n2) {
        try {
            Class42_Sub3.aClass111_5364.method2092(Class98_Sub5_Sub3.aClass111_5540);
            Class42_Sub3.aClass111_5364.method2106(0, 0, (int)n);
            if (n2 != 14794) {
                method365(-123);
            }
            Class154.aHa1231.a(Class42_Sub3.aClass111_5364);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cs.A(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class41.aClass171_371 = new OutgoingOpcode(31, 3);
    }
}
