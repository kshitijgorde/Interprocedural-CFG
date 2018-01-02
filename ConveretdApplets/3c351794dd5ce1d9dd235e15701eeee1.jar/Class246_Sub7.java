// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub7 extends Class246
{
    static OutgoingOpcode aClass171_5115;
    int anInt5116;
    int anInt5117;
    int anInt5118;
    static Class48 aClass48_5119;
    int anInt5120;
    String aString5121;
    int anInt5122;
    int anInt5123;
    
    static final void method3131(final int n, final long n2) {
        try {
            if (n != 0) {
                Class246_Sub7.aClass48_5119 = null;
            }
            if (~n2 < -1L) {
                if (~(n2 % 10L) != -1L) {
                    Class74.method733(n2, 75);
                }
                else {
                    Class74.method733(-1L + n2, 60);
                    Class74.method733(1L, n + 116);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mda.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final int method3132(int n, final byte b, final int n2) {
        try {
            if (b != 118) {
                return 45;
            }
            n = (n2 & 0x7F) * n >> -1157637401;
            if (n >= 2) {
                if (~n >= -127) {
                    return (n2 & 0xFF80) + n;
                }
                n = 126;
                if (!client.aBoolean3553) {
                    return (n2 & 0xFF80) + n;
                }
            }
            n = 2;
            return (n2 & 0xFF80) + n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mda.A(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    public static void method3133(final byte b) {
        try {
            Class246_Sub7.aClass48_5119 = null;
            Class246_Sub7.aClass171_5115 = null;
            if (b != 64) {
                method3133((byte)(-73));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mda.B(" + b + ')');
        }
    }
    
    static {
        Class246_Sub7.aClass171_5115 = new OutgoingOpcode(30, 4);
    }
}
