// 
// Decompiled by Procyon v0.5.30
// 

final class Class14
{
    short[] aShortArray165;
    short[] aShortArray166;
    short[] aShortArray167;
    byte[] aByteArray168;
    
    static final boolean method225(final String s, final byte b) {
        try {
            if (s == null) {
                return false;
            }
            for (int i = 0; i < Class248.anInt1897; ++i) {
                if (s.equalsIgnoreCase(Class255.aStringArray3209[i])) {
                    return true;
                }
                if (s.equalsIgnoreCase(Class110.aStringArray945[i])) {
                    return true;
                }
            }
            return b <= 100;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bb.A(" + ((s != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final int method226(final int n) {
        try {
            if (n <= 106) {
                method225(null, (byte)101);
            }
            return Class246_Sub3_Sub2_Sub1.anInt6345++;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bb.B(" + n + ')');
        }
    }
}
