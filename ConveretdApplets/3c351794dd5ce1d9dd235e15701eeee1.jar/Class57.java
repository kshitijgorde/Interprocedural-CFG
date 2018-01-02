// 
// Decompiled by Procyon v0.5.30
// 

final class Class57
{
    static int[] anIntArray457;
    static int[] anIntArray458;
    
    public static void method518(final int n) {
        try {
            Class57.anIntArray457 = null;
            Class57.anIntArray458 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "du.A(" + n + ')');
        }
    }
    
    static final void method519(final boolean b, final String s, final int n, final int n2, final int n3) {
        try {
            if (n2 == -1) {
                Class277.method3288(b, n3, s, 2, null, false, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "du.B(" + b + ',' + ((s != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static {
        Class57.anIntArray458 = new int[1];
        Class57.anIntArray457 = null;
    }
}
