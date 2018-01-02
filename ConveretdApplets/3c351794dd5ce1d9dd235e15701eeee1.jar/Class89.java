// 
// Decompiled by Procyon v0.5.30
// 

final class Class89
{
    int anInt707;
    int anInt708;
    static long[] aLongArray709;
    int anInt710;
    int anInt711;
    int anInt712;
    byte[] aByteArray713;
    byte[] aByteArray714;
    int anInt715;
    static String aString716;
    int anInt717;
    
    public static void method879(final boolean b) {
        try {
            if (!b) {
                method879(false);
            }
            Class89.aString716 = null;
            Class89.aLongArray709 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fm.A(" + b + ')');
        }
    }
    
    static final void method880(final int n, final Class207 aClass207_3648) {
        try {
            if (n == -13258) {
                Class64_Sub3.aClass207_3648 = aClass207_3648;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fm.B(" + n + ',' + ((aClass207_3648 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class89.aLongArray709 = new long[32];
        Class89.aString716 = null;
    }
}
