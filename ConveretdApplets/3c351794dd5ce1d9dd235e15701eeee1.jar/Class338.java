// 
// Decompiled by Procyon v0.5.30
// 

final class Class338
{
    int anInt2830;
    int anInt2831;
    int anInt2832;
    static short[] aShortArray2833;
    Class246_Sub3 aClass246_Sub3_2834;
    Class37 aClass37_2835;
    Class98_Sub31_Sub5 aClass98_Sub31_Sub5_2836;
    int anInt2837;
    Class98_Sub13 aClass98_Sub13_2838;
    Class98_Sub24_Sub1 aClass98_Sub24_Sub1_2839;
    byte aByte2840;
    int anInt2841;
    static int anInt2842;
    int anInt2843;
    
    static final void method3778(final byte b) {
        try {
            if (~Class98_Sub22_Sub1.anInt5789 != 0x0) {
                Class119.method2176(-1, false, Class98_Sub22_Sub1.anInt5789, (byte)89, -1);
                Class98_Sub22_Sub1.anInt5789 = -1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ul.B(" + b + ')');
        }
    }
    
    public static void method3779(final int n) {
        try {
            if (n < 70) {
                method3780((byte)10);
            }
            Class338.aShortArray2833 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ul.E(" + n + ')');
        }
    }
    
    static final void method3780(final byte b) {
        try {
            Class135.aClass79_1054.method794(15);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ul.A(" + b + ')');
        }
    }
    
    static final Class93_Sub1 method3781(final byte b, final Class98_Sub22 class98_Sub22) {
        try {
            final Class93 method1716 = Class105.method1716(b - 95, class98_Sub22);
            if (b != 94) {
                Class338.anInt2842 = 13;
            }
            return new Class93_Sub1(method1716.aClass63_3509, method1716.aClass110_3511, method1716.anInt3505, method1716.anInt3507, method1716.anInt3514, method1716.anInt3504, method1716.anInt3508, method1716.anInt3506, method1716.anInt3513, class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ul.D(" + b + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final boolean method3782(final int n) {
        try {
            if (n != -4) {
                this.aClass246_Sub3_2834 = null;
            }
            return this.aByte2840 == 2 || ~this.aByte2840 == 0xFFFFFFFC;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ul.C(" + n + ')');
        }
    }
    
    Class338(final byte aByte2840, final int anInt2830, final int anInt2831, final int anInt2832, final int anInt2833, final int anInt2834, final int anInt2835, final Class246_Sub3 aClass246_Sub3_2834) {
        try {
            this.anInt2832 = anInt2832;
            this.anInt2843 = anInt2835;
            this.anInt2830 = anInt2830;
            this.anInt2841 = anInt2833;
            this.anInt2837 = anInt2834;
            this.aClass246_Sub3_2834 = aClass246_Sub3_2834;
            this.anInt2831 = anInt2831;
            this.aByte2840 = aByte2840;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ul.<init>(" + aByte2840 + ',' + anInt2830 + ',' + anInt2831 + ',' + anInt2832 + ',' + anInt2833 + ',' + anInt2834 + ',' + anInt2835 + ',' + ((aClass246_Sub3_2834 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class338.aShortArray2833 = new short[256];
        Class338.anInt2842 = 0;
    }
}
