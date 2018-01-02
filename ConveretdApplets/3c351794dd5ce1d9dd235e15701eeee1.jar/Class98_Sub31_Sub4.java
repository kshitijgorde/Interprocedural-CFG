// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub31_Sub4 extends Class98_Sub31
{
    private int anInt5858;
    private Class148 aClass148_5859;
    static int anInt5860;
    static int[] anIntArray5861;
    private int anInt5862;
    private int anInt5863;
    private boolean aBoolean5864;
    private int anInt5865;
    private int anInt5866;
    private boolean aBoolean5867;
    
    final synchronized void method1379(final int n) {
        try {
            if (n != 0) {
                this.aBoolean5867 = false;
            }
            this.aBoolean5867 = true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.R(" + n + ')');
        }
    }
    
    @Override
    final synchronized void method1321(int n) {
        try {
            if (!this.aBoolean5864) {
                while (true) {
                    final Class98_Sub46_Sub15 method1381 = this.method1381(-95);
                    if (method1381 == null) {
                        if (this.aBoolean5867) {
                            this.method942(59);
                            Class98_Sub49.aClass100_4283.method1690(1);
                            break;
                        }
                        break;
                    }
                    else {
                        if (n < -this.anInt5862 + method1381.aShortArrayArray6040[0].length) {
                            this.anInt5862 += n;
                            break;
                        }
                        n -= -this.anInt5862 + method1381.aShortArrayArray6040[0].length;
                        this.method1388((byte)100);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.M(" + n + ')');
        }
    }
    
    final synchronized void method1380(final byte b, final Class98_Sub46_Sub15 class98_Sub46_Sub15) {
        try {
            while (this.anInt5863 >= 100) {
                this.aClass148_5859.method2421(6494);
                --this.anInt5863;
            }
            if (b <= 10) {
                method1383(null, null, -103, 19, -21, 97, 127, null);
            }
            this.aClass148_5859.method2419(class98_Sub46_Sub15, -20911);
            ++this.anInt5863;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.P(" + b + ',' + ((class98_Sub46_Sub15 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final synchronized Class98_Sub46_Sub15 method1381(final int n) {
        try {
            if (n > -26) {
                Class98_Sub31_Sub4.anInt5860 = 119;
            }
            return (Class98_Sub46_Sub15)this.aClass148_5859.method2418(32);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.N(" + n + ')');
        }
    }
    
    final synchronized double method1382(final boolean b) {
        try {
            if (~this.anInt5863 > -2) {
                return -1.0;
            }
            if (b) {
                this.aBoolean5864 = true;
            }
            final Class98_Sub46_Sub15 class98_Sub46_Sub15 = (Class98_Sub46_Sub15)this.aClass148_5859.method2418(32);
            if (class98_Sub46_Sub15 == null) {
                return -1.0;
            }
            return class98_Sub46_Sub15.aDouble6042 - class98_Sub46_Sub15.aShortArrayArray6040[0].length / Class64_Sub15.anInt3678;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.S(" + b + ')');
        }
    }
    
    static final void method1383(final Class246_Sub3_Sub4_Sub2_Sub2 aClass246_Sub3_Sub4_Sub2_Sub2_4206, final Class352 aClass352_4233, final int n, final int n2, final int n3, final int n4, final int anInt4220, final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4209) {
        try {
            if (n3 != 3) {
                method1386(40);
            }
            final Class98_Sub42 class98_Sub42 = new Class98_Sub42();
            class98_Sub42.anInt4220 = anInt4220;
            class98_Sub42.anInt4225 = n4 << 988015593;
            class98_Sub42.anInt4229 = n << -1906491031;
            if (aClass352_4233 == null) {
                if (aClass246_Sub3_Sub4_Sub2_Sub1_4209 != null) {
                    class98_Sub42.aClass246_Sub3_Sub4_Sub2_Sub1_4209 = aClass246_Sub3_Sub4_Sub2_Sub1_4209;
                    Class141 class141 = aClass246_Sub3_Sub4_Sub2_Sub1_4209.aClass141_6504;
                    if (class141.anIntArray1109 != null) {
                        class98_Sub42.aBoolean4207 = true;
                        class141 = class141.method2300(Class75.aClass140_584, (byte)95);
                    }
                    if (class141 != null) {
                        class98_Sub42.anInt4224 = n - -class141.anInt1112 << -602009655;
                        class98_Sub42.anInt4216 = n4 - -class141.anInt1112 << 1128804969;
                        class98_Sub42.anInt4210 = Class277.method3293(n3 + 119, aClass246_Sub3_Sub4_Sub2_Sub1_4209);
                        class98_Sub42.anInt4236 = class141.anInt1156;
                        class98_Sub42.aBoolean4215 = class141.aBoolean1093;
                        class98_Sub42.anInt4228 = class141.anInt1128 << 46162057;
                        class98_Sub42.anInt4223 = class141.anInt1101;
                        class98_Sub42.anInt4217 = class141.anInt1125 << -1887902231;
                        class98_Sub42.anInt4237 = class141.anInt1090;
                    }
                    Class358.aClass148_3032.method2419(class98_Sub42, -20911);
                }
                else if (aClass246_Sub3_Sub4_Sub2_Sub2_4206 != null) {
                    class98_Sub42.aClass246_Sub3_Sub4_Sub2_Sub2_4206 = aClass246_Sub3_Sub4_Sub2_Sub2_4206;
                    class98_Sub42.anInt4224 = n - -aClass246_Sub3_Sub4_Sub2_Sub2_4206.method3034(n3 ^ 0x3) << 255186825;
                    class98_Sub42.anInt4216 = n4 + aClass246_Sub3_Sub4_Sub2_Sub2_4206.method3034(0) << -58758775;
                    class98_Sub42.anInt4210 = Class286.method3383(aClass246_Sub3_Sub4_Sub2_Sub2_4206, true);
                    class98_Sub42.anInt4237 = 256;
                    class98_Sub42.anInt4228 = aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt6525 << -78927831;
                    class98_Sub42.anInt4217 = 0;
                    class98_Sub42.aBoolean4215 = aClass246_Sub3_Sub4_Sub2_Sub2_4206.aBoolean6526;
                    class98_Sub42.anInt4223 = 256;
                    class98_Sub42.anInt4236 = aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt6514;
                    Class98_Sub10_Sub14.aClass377_5612.method3996(class98_Sub42, aClass246_Sub3_Sub4_Sub2_Sub2_4206.anInt6369, -1);
                }
            }
            else {
                class98_Sub42.aClass352_4233 = aClass352_4233;
                int n5 = aClass352_4233.sizeY;
                int n6 = aClass352_4233.sizeX;
                if (n2 == 1 || ~n2 == 0xFFFFFFFC) {
                    n6 = aClass352_4233.sizeY;
                    n5 = aClass352_4233.sizeX;
                }
                class98_Sub42.anInt4205 = aClass352_4233.anInt2972;
                class98_Sub42.aBoolean4226 = aClass352_4233.aBoolean2957;
                class98_Sub42.anInt4219 = aClass352_4233.anInt2949;
                class98_Sub42.aBoolean4215 = aClass352_4233.aBoolean2992;
                class98_Sub42.anInt4217 = aClass352_4233.anInt2970 << 1467122217;
                class98_Sub42.anInt4216 = n6 + n4 << 636122729;
                class98_Sub42.anInt4236 = aClass352_4233.anInt2987;
                class98_Sub42.anInt4224 = n5 + n << 531390185;
                class98_Sub42.anInt4237 = aClass352_4233.anInt2950;
                class98_Sub42.anIntArray4208 = aClass352_4233.anIntArray2926;
                class98_Sub42.anInt4228 = aClass352_4233.anInt2981 << 998368073;
                class98_Sub42.anInt4223 = aClass352_4233.anInt3006;
                class98_Sub42.anInt4210 = aClass352_4233.anInt2996;
                if (aClass352_4233.anIntArray2928 != null) {
                    class98_Sub42.method1478(class98_Sub42.aBoolean4207 = true);
                }
                if (class98_Sub42.anIntArray4208 != null) {
                    class98_Sub42.anInt4221 = (int)((-class98_Sub42.anInt4219 + class98_Sub42.anInt4205) * Math.random()) + class98_Sub42.anInt4219;
                }
                Class98_Sub10_Sub37.aClass148_5748.method2419(class98_Sub42, -20911);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.Q(" + ((aClass246_Sub3_Sub4_Sub2_Sub2_4206 != null) ? "{...}" : "null") + ',' + ((aClass352_4233 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + anInt4220 + ',' + ((aClass246_Sub3_Sub4_Sub2_Sub1_4209 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final int method1326() {
        try {
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.G()");
        }
    }
    
    static final Class98_Sub46_Sub17 method1384(final int n) {
        try {
            final Class98_Sub46_Sub17 class98_Sub46_Sub17 = (Class98_Sub46_Sub17)Class367.aClass215_3545.method2792(-1);
            if (class98_Sub46_Sub17 != null) {
                class98_Sub46_Sub17.method942(45);
                class98_Sub46_Sub17.method1524((byte)(-90));
                return class98_Sub46_Sub17;
            }
            Class98_Sub46_Sub17 class98_Sub46_Sub18;
            do {
                class98_Sub46_Sub18 = (Class98_Sub46_Sub17)Class98_Sub10_Sub34.aClass215_5728.method2792(-1);
                if (class98_Sub46_Sub18 == null) {
                    return null;
                }
                if (~class98_Sub46_Sub18.method1620((byte)(-108)) < ~Class343.method3819(-47)) {
                    return null;
                }
                class98_Sub46_Sub18.method942(61);
                class98_Sub46_Sub18.method1524((byte)(-90));
            } while (~(Long.MIN_VALUE & class98_Sub46_Sub18.aLong4259) == -1L);
            return class98_Sub46_Sub18;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.K(" + n + ')');
        }
    }
    
    final Class98_Sub46_Sub15 method1385(final double aDouble6042, final int n, final int n2) {
        try {
            final long n3 = this.anInt5858 << 336243488 | n;
            Class98_Sub46_Sub15 class98_Sub46_Sub15 = (Class98_Sub46_Sub15)Class98_Sub49.aClass100_4283.method1694((byte)120, n3);
            if (class98_Sub46_Sub15 == null) {
                class98_Sub46_Sub15 = new Class98_Sub46_Sub15(new short[this.anInt5858][n], aDouble6042);
                if (!client.aBoolean3553) {
                    return class98_Sub46_Sub15;
                }
            }
            class98_Sub46_Sub15.aDouble6042 = aDouble6042;
            Class98_Sub49.aClass100_4283.method1689(n3, (byte)58);
            return class98_Sub46_Sub15;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.E(" + aDouble6042 + ',' + n + ',' + n2 + ')');
        }
    }
    
    static final void method1386(final int n) {
        try {
            if (aa_Sub2.anIntArray3565 == null || Class278_Sub1.anIntArray5168 == null) {
                Class278_Sub1.anIntArray5168 = new int[256];
                aa_Sub2.anIntArray3565 = new int[256];
                for (int i = 0; i < 256; ++i) {
                    final double n2 = 6.283185307179586 * (i / 255.0);
                    aa_Sub2.anIntArray3565[i] = (int)(4096.0 * Math.sin(n2));
                    Class278_Sub1.anIntArray5168[i] = (int)(4096.0 * Math.cos(n2));
                }
            }
            if (n != 0) {
                method1383(null, null, 15, 74, -18, 45, 82, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.H(" + n + ')');
        }
    }
    
    final synchronized int method1387(final boolean b) {
        try {
            if (!b) {
                this.method1388((byte)32);
            }
            return this.anInt5863;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.C(" + b + ')');
        }
    }
    
    private final synchronized void method1388(final byte b) {
        try {
            if (b != 100) {
                this.method1385(1.413056312553466, -73, -59);
            }
            final Class98_Sub46_Sub15 method1381 = this.method1381(-92);
            if (method1381 != null) {
                method1381.method942(b ^ 0x56);
                this.anInt5862 = 0;
                --this.anInt5863;
                Class98_Sub49.aClass100_4283.method1695(26404, method1381, method1381.method1608(2));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.F(" + b + ')');
        }
    }
    
    @Override
    final synchronized void method1325(final int[] array, int n, final int n2) {
        try {
            if (!this.aBoolean5864) {
                if (this.method1381(-67) == null) {
                    if (this.aBoolean5867) {
                        this.method942(111);
                        Class98_Sub49.aClass100_4283.method1690(1);
                    }
                }
                else {
                    int i = n2 + n;
                    if (Class151_Sub7.aBoolean5007) {
                        i <<= 1;
                    }
                    final int n3 = 0;
                    int n4 = 0;
                    if (this.anInt5858 == 2) {
                        n4 = 1;
                    }
                    while (i > n) {
                        final Class98_Sub46_Sub15 method1381 = this.method1381(-54);
                        if (method1381 == null) {
                            break;
                        }
                        final short[][] aShortArrayArray6040 = method1381.aShortArrayArray6040;
                        while (~n > ~i && ~aShortArrayArray6040[0].length < ~this.anInt5862) {
                            if (!Class151_Sub7.aBoolean5007) {
                                final int n5 = n++;
                                array[n5] += this.anInt5865 * aShortArrayArray6040[n4][this.anInt5862] + this.anInt5866 * aShortArrayArray6040[n3][this.anInt5862];
                            }
                            else {
                                array[n++] = this.anInt5866 * aShortArrayArray6040[n3][this.anInt5862];
                                array[n++] = this.anInt5865 * aShortArrayArray6040[n4][this.anInt5862];
                            }
                            ++this.anInt5862;
                        }
                        if (this.anInt5862 < aShortArrayArray6040[0].length) {
                            continue;
                        }
                        this.method1388((byte)100);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.A(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    public static void method1389(final int n) {
        try {
            if (n != -18925) {
                Class98_Sub31_Sub4.anIntArray5861 = null;
            }
            Class98_Sub31_Sub4.anIntArray5861 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.J(" + n + ')');
        }
    }
    
    static final int method1390(final int n, final int n2, final int n3, final int n4) {
        try {
            if (n4 != -8941) {
                return -107;
            }
            if (~(0x8 & Class281.aByteArrayArrayArray2117[n2][n3][n]) != -1) {
                return 0;
            }
            if (~n2 < -1 && ~(Class281.aByteArrayArrayArray2117[1][n3][n] & 0x2) != -1) {
                return n2 - 1;
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.I(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    final synchronized void method1391(final boolean aBoolean5864, final int n) {
        try {
            if (n == -58758775) {
                this.aBoolean5864 = aBoolean5864;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.L(" + aBoolean5864 + ',' + n + ')');
        }
    }
    
    @Override
    final Class98_Sub31 method1322() {
        try {
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.B()");
        }
    }
    
    final void method1392(final int n, final int n2) {
        try {
            this.anInt5866 = n2;
            this.anInt5865 = n2;
            if (n != 255186825) {
                this.method1326();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.O(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final Class98_Sub31 method1327() {
        try {
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.D()");
        }
    }
    
    Class98_Sub31_Sub4(final int anInt5858) {
        this.aClass148_5859 = new Class148();
        this.anInt5865 = 256;
        this.anInt5863 = 0;
        this.anInt5866 = 256;
        try {
            this.anInt5858 = anInt5858;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wb.<init>(" + anInt5858 + ')');
        }
    }
    
    static {
        Class98_Sub31_Sub4.anIntArray5861 = new int[] { 0, 1, 2, 2, 1, 1, 2, 3, 1, 3, 3, 4, 2, 0, 4 };
    }
}
