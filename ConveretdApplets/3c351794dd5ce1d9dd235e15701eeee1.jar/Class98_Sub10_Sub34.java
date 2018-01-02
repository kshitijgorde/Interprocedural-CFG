// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub34 extends Class98_Sub10
{
    private int anInt5725;
    private int anInt5726;
    private int anInt5727;
    static Class215 aClass215_5728;
    private int anInt5729;
    static Class43 aClass43_5730;
    
    public Class98_Sub10_Sub34() {
        super(0, true);
        this.anInt5725 = 0;
        this.anInt5727 = 0;
        this.anInt5729 = 20;
        this.anInt5726 = 1365;
    }
    
    static final String method1103(int n, final boolean b, final int n2, final int n3) {
        try {
            if (~n3 > -3 || n3 > 36) {
                throw new IllegalArgumentException("Invalid radix:" + n3);
            }
            if (!b || ~n > -1) {
                return Integer.toString(n, n3);
            }
            int n4 = 2;
            for (int n5 = n / n3; ~n5 != -1; n5 /= n3) {
                ++n4;
            }
            if (n2 != 328) {
                return null;
            }
            final char[] array = new char[n4];
            array[0] = '+';
            for (int i = n4 - 1; i > 0; --i) {
                final int n6 = n;
                n /= n3;
                final int n7 = -(n3 * n) + n6;
                if (n7 >= 10) {
                    array[i] = (char)(87 + n7);
                }
                else {
                    array[i] = (char)(48 + n7);
                }
            }
            return new String(array);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ta.D(" + n + ',' + b + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method1104(final int n) {
        try {
            if (n <= 78) {
                method1103(-104, true, 77, -76);
            }
            Class246_Sub4_Sub2.anInt6184 = (int)(Class165.anInt1276 * 34.46);
            Class64_Sub20.anInt3696 = 200;
            Class246_Sub4_Sub2.anInt6184 <<= 2;
            if (Class265.aHa1974.method1788()) {
                Class246_Sub4_Sub2.anInt6184 += 512;
            }
            Class201.method2697(-546, false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ta.F(" + n + ')');
        }
    }
    
    public static void method1105(final int n) {
        try {
            Class98_Sub10_Sub34.aClass43_5730 = null;
            Class98_Sub10_Sub34.aClass215_5728 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ta.E(" + n + ')');
        }
    }
    
    static final void method1106(final byte b) {
        try {
            if (b == -61) {
                Class142.aClass377_1157.method3994(-95);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ta.B(" + b + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b > -92) {
                method1106((byte)111);
            }
            if (~n != -1) {
                if (n == 1) {
                    this.anInt5729 = class98_Sub22.readShort((byte)127);
                    return;
                }
                if (n == 2) {
                    this.anInt5727 = class98_Sub22.readShort((byte)127);
                    return;
                }
                if (n != 3) {
                    return;
                }
                if (!client.aBoolean3553) {
                    this.anInt5725 = class98_Sub22.readShort((byte)127);
                    return;
                }
            }
            this.anInt5726 = class98_Sub22.readShort((byte)127);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ta.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            if (n != 255) {
                return null;
            }
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                for (int i = 0; i < Class25.anInt268; ++i) {
                    final int n3 = (Class64_Sub1.anIntArray3640[i] << 1511869708) / this.anInt5726 + this.anInt5727;
                    final int n4 = this.anInt5725 + (Class352.anIntArray3001[n2] << 1223121772) / this.anInt5726;
                    final int n5 = n3;
                    final int n6 = n4;
                    int n7;
                    int n8;
                    int n9;
                    int n10;
                    int n11;
                    for (n7 = n3, n8 = n4, n9 = n3 * n3 >> -74704404, n10 = n4 * n4 >> 476654668, n11 = 0; ~(n10 + n9) > -16385 && ~n11 > ~this.anInt5729; n9 = n7 * n7 >> -998006516, ++n11, n10 = n8 * n8 >> -973863668) {
                        n8 = (n7 * n8 >> 1013466860) * 2 + n6;
                        n7 = n5 + (n9 - n10);
                    }
                    method237[i] = ((~(this.anInt5729 - 1) < ~n11) ? ((n11 << -1736488308) / this.anInt5729) : 0);
                }
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ta.G(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub34.aClass215_5728 = new Class215();
    }
}
