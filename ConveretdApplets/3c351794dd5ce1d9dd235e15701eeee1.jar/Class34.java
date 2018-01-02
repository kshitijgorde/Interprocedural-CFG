// 
// Decompiled by Procyon v0.5.30
// 

final class Class34
{
    static boolean aBoolean324;
    static boolean[][][] aBooleanArrayArrayArray325;
    static float[] aFloatArray326;
    
    static final void method328(final int n) {
        try {
            Class232.aClass79_1740.method806((byte)60);
            if (n != 0) {
                method330((byte)(-71), true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cha.C(" + n + ')');
        }
    }
    
    public static void method329(final int n) {
        try {
            if (n != 0) {
                method330((byte)(-103), false);
            }
            Class34.aBooleanArrayArrayArray325 = null;
            Class34.aFloatArray326 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cha.B(" + n + ')');
        }
    }
    
    static final void method330(final byte b, final boolean b2) {
        try {
            if (Class140.aClass47_3241 == null) {
                Class266.method3238(0);
            }
            if (b2) {
                Class140.aClass47_3241.method452(-7423);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cha.A(" + b + ',' + b2 + ')');
        }
    }
    
    static {
        Class34.aBoolean324 = false;
        Class34.aFloatArray326 = new float[4];
    }
}
