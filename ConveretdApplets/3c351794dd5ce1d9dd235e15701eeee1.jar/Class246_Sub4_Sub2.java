// 
// Decompiled by Procyon v0.5.30
// 

class Class246_Sub4_Sub2 extends Class246_Sub4
{
    static Class246_Sub3[] aClass246_Sub3Array6173;
    boolean aBoolean6174;
    int anInt6175;
    int anInt6176;
    int anInt6177;
    int anInt6178;
    int anInt6179;
    int anInt6180;
    static int anInt6181;
    static IncomingOpcode aClass58_6182;
    byte aByte6183;
    static int anInt6184;
    static OutgoingOpcode aClass171_6185;
    static Class85 aClass85_6186;
    
    public static void method3106(final byte b) {
        try {
            Class246_Sub4_Sub2.aClass171_6185 = null;
            Class246_Sub4_Sub2.aClass58_6182 = null;
            if (b < 52) {
                Class246_Sub4_Sub2.aClass171_6185 = null;
            }
            Class246_Sub4_Sub2.aClass85_6186 = null;
            Class246_Sub4_Sub2.aClass246_Sub3Array6173 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tha.B(" + b + ')');
        }
    }
    
    static final boolean method3107(final int n, final byte b) {
        try {
            if (b >= -65) {
                method3107(-77, (byte)42);
            }
            return ~n == -1 || ~n == 0xFFFFFFFD;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tha.C(" + n + ',' + b + ')');
        }
    }
    
    static final void method3108(final byte b, int n, final int n2, final int n3, int n4, final byte[] array) {
        try {
            if (b != 1) {
                method3106((byte)100);
            }
            if (~n3 < ~n2) {
                n += n2;
                n4 = -n2 + n3 >> 1044038946;
                while (~(--n4) <= -1) {
                    array[n++] = 1;
                    array[n++] = 1;
                    array[n++] = 1;
                    array[n++] = 1;
                }
                n4 = (0x3 & -n2 + n3);
                while (--n4 >= 0) {
                    array[n++] = 1;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "tha.A(" + b + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    public Class246_Sub4_Sub2() {
        this.aByte6183 = 5;
    }
    
    static {
        Class246_Sub4_Sub2.anInt6181 = 0;
        Class246_Sub4_Sub2.aClass58_6182 = new IncomingOpcode(13, 1);
        Class246_Sub4_Sub2.aClass171_6185 = new OutgoingOpcode(41, -1);
        Class246_Sub4_Sub2.aClass85_6186 = new Class85(11, 8);
    }
}
