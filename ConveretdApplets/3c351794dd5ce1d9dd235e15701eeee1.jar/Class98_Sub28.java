// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class98_Sub28 extends Class98
{
    static int anInt4078;
    ha_Sub1 aHa_Sub1_4079;
    static int anInt4080;
    boolean aBoolean4081;
    
    abstract boolean method1294(final byte p0);
    
    abstract void method1295(final int p0, final int p1, final boolean p2);
    
    static final void method1296(final byte b) {
        try {
            Class142.aClass79_1158.method794(106);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lb.K(" + b + ')');
        }
    }
    
    abstract void method1297(final int p0, final byte p1);
    
    abstract boolean method1298(final int p0);
    
    int method1299(final boolean b) {
        try {
            if (b) {
                this.method1302(null, -118, (byte)5, null);
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lb.B(" + b + ')');
        }
    }
    
    final boolean method1300(final int n) {
        try {
            if (n != 0) {
                this.aBoolean4081 = false;
            }
            return this.aBoolean4081;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lb.N(" + n + ')');
        }
    }
    
    final boolean method1301(final int n) {
        try {
            return n > -5 && false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lb.M(" + n + ')');
        }
    }
    
    abstract void method1302(final Class42_Sub1 p0, final int p1, final byte p2, final Class42_Sub1 p3);
    
    final int method1303(final int n) {
        try {
            if (n != 0) {
                this.aBoolean4081 = true;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lb.Q(" + n + ')');
        }
    }
    
    abstract void method1304(final byte p0);
    
    static final String method1305(final int n, long n2) {
        try {
            if (n2 <= 0L || n2 >= 6582952005840035281L) {
                return null;
            }
            if (n2 % 37L == 0L) {
                return null;
            }
            int n3 = 0;
            for (long n4 = n2; n4 != 0L; n4 /= 37L) {
                ++n3;
            }
            final StringBuffer sb = new StringBuffer(n3);
            if (n > -42) {
                method1307(16, -80, -112, 57);
            }
            while (n2 != 0L) {
                final long n5 = n2;
                n2 /= 37L;
                sb.append(Exception_Sub1.aCharArray45[(int)(-(37L * n2) + n5)]);
            }
            return sb.reverse().toString();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lb.P(" + n + ',' + n2 + ')');
        }
    }
    
    static final int method1306(final byte b) {
        try {
            boolean b2 = false;
            boolean b3 = false;
            boolean b4 = false;
            if (Class98_Sub43_Sub2.aClass88_5907.aBoolean682 && !Class98_Sub43_Sub2.aClass88_5907.aBoolean675) {
                b2 = true;
                if (Exception_Sub1.aClass98_Sub35_47.anInt4129 < 512 && Exception_Sub1.aClass98_Sub35_47.anInt4129 != 0) {
                    b2 = false;
                }
                if (!Class88.aString699.startsWith("win")) {
                    b3 = true;
                }
                else {
                    b4 = true;
                    b3 = true;
                }
            }
            if (Class98_Sub10_Sub38.aBoolean5756) {
                b2 = false;
            }
            if (Class95.aBoolean798) {
                b3 = false;
            }
            if (Class67.aBoolean520) {
                b4 = false;
            }
            if (!b2 && !b3 && !b4) {
                return Class246_Sub3_Sub4_Sub5.method3085(b ^ 0xFFFFFF96);
            }
            int method683 = -1;
            int method684 = -1;
            if (b2) {
                try {
                    method683 = Class66.method683((byte)(-79), 1000, 2);
                }
                catch (Exception ex2) {}
            }
            int method685 = -1;
            if (b4) {
                try {
                    method685 = Class66.method683((byte)(-113), 1000, 3);
                    if (Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)127) == 3) {
                        final Class62 method686 = Class265.aHa1974.method1799();
                        final long n = 0xFFFFFFFFFFFFL & method686.aLong485;
                        final int anInt484 = method686.anInt484;
                        if (~anInt484 != 0xFFFFEF21) {
                            if (~anInt484 == 0xFFFFEFFD) {
                                b3 &= (n >= 60129613779L);
                            }
                        }
                        else {
                            b3 &= (~n <= -64425238955L);
                        }
                    }
                }
                catch (Exception ex3) {}
            }
            if (b != -106) {
                Class98_Sub28.anInt4078 = -48;
            }
            if (b3) {
                try {
                    method684 = Class66.method683((byte)(-115), 1000, 1);
                }
                catch (Exception ex4) {}
            }
            if (~method683 == 0x0 && ~method684 == 0x0 && method685 == -1) {
                return Class246_Sub3_Sub4_Sub5.method3085(0);
            }
            final int n2 = (int)(method685 * 1.1f);
            final int n3 = (int)(method684 * 1.1f);
            if (n2 < method683 && method683 > n3) {
                return Class98_Sub46_Sub2_Sub2.method1537(method683, 5000);
            }
            if (n2 <= n3) {
                return Class132.method2235(n3, 1, (byte)98);
            }
            return Class132.method2235(n2, 3, (byte)98);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lb.O(" + b + ')');
        }
    }
    
    static final int method1307(final int n, final int n2, final int n3, final int n4) {
        try {
            if (~Class212.anInt1600 > -101) {
                return -2;
            }
            if (n2 != 1) {
                return 57;
            }
            int n5 = -2;
            int n6 = Integer.MAX_VALUE;
            final int n7 = -Class278.anInt2075 + n3;
            final int n8 = -Class278.anInt2078 + n4;
            for (Class98_Sub47 class98_Sub47 = (Class98_Sub47)Class278.aClass148_2065.method2418(n2 ^ 0x21); class98_Sub47 != null; class98_Sub47 = (Class98_Sub47)Class278.aClass148_2065.method2417(88)) {
                if (n == class98_Sub47.anInt4268) {
                    final int anInt4272 = class98_Sub47.anInt4272;
                    final int anInt4273 = class98_Sub47.anInt4267;
                    final int n9 = anInt4272 + Class278.anInt2075 << -1106336498 | Class278.anInt2078 + anInt4273;
                    final int n10 = (n8 + -anInt4273) * (n8 + -anInt4273) + (-anInt4272 + n7) * (n7 - anInt4272);
                    if (n5 < 0 || n10 < n6) {
                        n5 = n9;
                        n6 = n10;
                    }
                }
            }
            return n5;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lb.L(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    Class98_Sub28(final ha_Sub1 aHa_Sub1_4079) {
        try {
            this.aHa_Sub1_4079 = aHa_Sub1_4079;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lb.<init>(" + ((aHa_Sub1_4079 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub28.anInt4078 = 0;
        Class98_Sub28.anInt4080 = 0;
    }
}
