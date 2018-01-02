// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub8 extends Class98_Sub10
{
    static d aD5578;
    static boolean[] aBooleanArray5579;
    
    static final Class98_Sub46_Sub10 method1026(final int n) {
        try {
            if (n != -3) {
                return null;
            }
            return Class278.aClass98_Sub46_Sub10_2056;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ej.B(" + n + ')');
        }
    }
    
    static final int method1027(int n, final int n2, final int n3) {
        try {
            if (~n2 == 0x0) {
                return 12345678;
            }
            n = n * (n2 & 0x7F) >> 2041991591;
            Label_0049: {
                if (~n > -3) {
                    n = 2;
                    if (!client.aBoolean3553) {
                        break Label_0049;
                    }
                }
                if (~n < -127) {
                    n = 126;
                }
            }
            if (n3 >= -73) {
                Class98_Sub10_Sub8.aBooleanArray5579 = null;
            }
            return n + (n2 & 0xFF80);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ej.F(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            if (n != 255) {
                Class98_Sub10_Sub8.aBooleanArray5579 = null;
            }
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                final int n3 = Class352.anIntArray3001[n2];
                for (int n4 = 0; ~n4 > ~Class25.anInt268; ++n4) {
                    method237[n4] = this.method1030(Class64_Sub1.anIntArray3640[n4], (byte)3, n3) % 4096;
                }
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ej.G(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method1028(final boolean b) {
        try {
            Class98_Sub10_Sub8.aBooleanArray5579 = null;
            Class98_Sub10_Sub8.aD5578 = null;
            if (b) {
                Class98_Sub10_Sub8.aD5578 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ej.D(" + b + ')');
        }
    }
    
    static final Class350 method1029(final byte b, final int n) {
        try {
            final Class350[] method3595 = Class306.method3595((byte)67);
            for (int i = 0; i < method3595.length; ++i) {
                final Class350 class350 = method3595[i];
                if (n == class350.anInt2920) {
                    return class350;
                }
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ej.E(" + b + ',' + n + ')');
        }
    }
    
    private final int method1030(final int n, final byte b, final int n2) {
        try {
            final int n3 = 57 * n2 + n;
            if (b != 3) {
                method1027(-5, 93, 26);
            }
            final int n4 = n3 << -924402879 ^ n3;
            return -(((789221 + n4 * n4 * 15731) * n4 + 1376312589 & Integer.MAX_VALUE) / 262144) + 4096;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ej.H(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    public Class98_Sub10_Sub8() {
        super(0, true);
    }
    
    static {
        Class98_Sub10_Sub8.aBooleanArray5579 = new boolean[8];
    }
}
