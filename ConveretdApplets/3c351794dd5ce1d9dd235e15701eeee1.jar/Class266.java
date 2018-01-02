// 
// Decompiled by Procyon v0.5.30
// 

final class Class266
{
    int anInt1984;
    boolean aBoolean1985;
    static Class111 aClass111_1986;
    int anInt1987;
    static Class84 aClass84_1988;
    int anInt1989;
    int anInt1990;
    int anInt1991;
    static IncomingOpcode aClass58_1992;
    int anInt1993;
    static Class98_Sub46_Sub8 aClass98_Sub46_Sub8_1994;
    int anInt1995;
    
    public static void method3233(final int n) {
        try {
            Class266.aClass58_1992 = null;
            Class266.aClass84_1988 = null;
            Class266.aClass98_Sub46_Sub8_1994 = null;
            Class266.aClass111_1986 = null;
            if (n < 68) {
                method3234(null, -99, null, null, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ql.G(" + n + ')');
        }
    }
    
    static final void method3234(final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2, final int n, final int[] array, final int[] array2, final int[] array3) {
        try {
            if (n != -3433) {
                method3233(100);
            }
            for (int n2 = 0; ~n2 > ~array3.length; ++n2) {
                final int anInt1700 = array3[n2];
                int n3 = array[n2];
                final int n4 = array2[n2];
                for (int n5 = 0; n3 != 0 && class246_Sub3_Sub4_Sub2_Sub2.aClass226Array6387.length > n5; n3 >>>= 1, ++n5) {
                    if ((n3 & 0x1) != 0x0) {
                        if (~anInt1700 == 0x0) {
                            class246_Sub3_Sub4_Sub2_Sub2.aClass226Array6387[n5] = null;
                        }
                        else {
                            final Class97 method2623 = Class151_Sub7.aClass183_5001.method2623(anInt1700, 16383);
                            final int anInt1701 = method2623.anInt819;
                            Class226 class226 = class246_Sub3_Sub4_Sub2_Sub2.aClass226Array6387[n5];
                            if (class226 != null) {
                                if (anInt1700 != class226.anInt1700) {
                                    if (method2623.anInt829 >= Class151_Sub7.aClass183_5001.method2623(class226.anInt1700, 16383).anInt829) {
                                        final Class226[] aClass226Array6387 = class246_Sub3_Sub4_Sub2_Sub2.aClass226Array6387;
                                        final int n6 = n5;
                                        final Class226 class227 = null;
                                        aClass226Array6387[n6] = class227;
                                        class226 = class227;
                                    }
                                }
                                else if (anInt1701 == 0) {
                                    final Class226[] aClass226Array6388 = class246_Sub3_Sub4_Sub2_Sub2.aClass226Array6387;
                                    final int n7 = n5;
                                    final Class226 class228 = null;
                                    aClass226Array6388[n7] = class228;
                                    class226 = class228;
                                }
                                else if (anInt1701 != 1) {
                                    if (anInt1701 == 2) {
                                        class226.anInt1704 = 0;
                                    }
                                }
                                else {
                                    class226.anInt1707 = 0;
                                    class226.anInt1704 = 0;
                                    class226.anInt1703 = n4;
                                    class226.anInt1702 = 0;
                                    class226.anInt1701 = 1;
                                    if (!class246_Sub3_Sub4_Sub2_Sub2.aBoolean6371) {
                                        Class349.method3840((byte)(-128), class246_Sub3_Sub4_Sub2_Sub2, 0, method2623);
                                    }
                                }
                            }
                            if (class226 == null) {
                                final Class226[] aClass226Array6389 = class246_Sub3_Sub4_Sub2_Sub2.aClass226Array6387;
                                final int n8 = n5;
                                final Class226 class229 = new Class226();
                                aClass226Array6389[n8] = class229;
                                final Class226 class230 = class229;
                                class230.anInt1703 = n4;
                                class230.anInt1707 = 0;
                                class230.anInt1700 = anInt1700;
                                class230.anInt1702 = 0;
                                class230.anInt1704 = 0;
                                class230.anInt1701 = 1;
                                if (!class246_Sub3_Sub4_Sub2_Sub2.aBoolean6371) {
                                    Class349.method3840((byte)(-126), class246_Sub3_Sub4_Sub2_Sub2, 0, method2623);
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ql.F(" + ((class246_Sub3_Sub4_Sub2_Sub2 != null) ? "{...}" : "null") + ',' + n + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + ((array3 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method3235(final byte b) {
        try {
            if (b <= -17 && Class39_Sub1.anInterface10Array3592 != null) {
                final Interface10[] anInterface10Array3592 = Class39_Sub1.anInterface10Array3592;
                for (int n = 0; anInterface10Array3592.length > n; ++n) {
                    anInterface10Array3592[n].method27(-31295);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ql.A(" + b + ')');
        }
    }
    
    final void method3236(final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b != -16) {
                method3239(-39, -58, -29, 127, null);
            }
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)15);
                if (unsignedByte == 0) {
                    break;
                }
                this.method3237(false, unsignedByte, class98_Sub22);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ql.E(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    private final void method3237(final boolean b, final int n, final Class98_Sub22 class98_Sub22) {
        try {
            if (n == 1) {
                this.anInt1984 = class98_Sub22.readShort((byte)127);
            }
            else if (n == 2) {
                this.aBoolean1985 = true;
            }
            else if (n == 3) {
                this.anInt1990 = class98_Sub22.readUShort(b);
                this.anInt1989 = class98_Sub22.readUShort(false);
                this.anInt1987 = class98_Sub22.readUShort(false);
            }
            else if (~n != 0xFFFFFFFB) {
                if (n == 5) {
                    this.anInt1995 = class98_Sub22.readShort((byte)127);
                }
                else if (n == 6) {
                    this.anInt1991 = class98_Sub22.method1186(-123);
                }
            }
            else {
                this.anInt1993 = class98_Sub22.readUnsignedByte((byte)(-108));
            }
            if (b) {
                Class266.aClass98_Sub46_Sub8_1994 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ql.C(" + b + ',' + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method3238(final int n) {
        try {
            if (Class45.aClass75_381 != null) {
                (Class140.aClass47_3241 = new Class47()).method446(Class235.aLong1753, (byte)95, Class45.aClass75_381.anInt552, Class45.aClass75_381, Class45.aClass75_381.aClass309_560.method3615(Class374.anInt3159, (byte)25));
                Class76_Sub9.aThread3783 = new Thread(Class140.aClass47_3241, "");
                if (n == 0) {
                    Class76_Sub9.aThread3783.start();
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ql.B(" + n + ')');
        }
    }
    
    static final void method3239(final int n, final int n2, final int n3, final int anInt5089, final Class246_Sub3_Sub2 aClass246_Sub3_Sub2_1331) {
        final Class172 method1693 = Class100.method1693(n, n2, n3);
        if (method1693 != null) {
            aClass246_Sub3_Sub2_1331.anInt5084 = (n2 << Class151_Sub8.anInt5015) + Class207.anInt1577;
            aClass246_Sub3_Sub2_1331.anInt5089 = anInt5089;
            aClass246_Sub3_Sub2_1331.anInt5079 = (n3 << Class151_Sub8.anInt5015) + Class207.anInt1577;
            method1693.aClass246_Sub3_Sub2_1331 = aClass246_Sub3_Sub2_1331;
            final int n4 = (Class78.aSArray594 == Class81.aSArray618) ? 1 : 0;
            if (aClass246_Sub3_Sub2_1331.method2978(-2)) {
                if (aClass246_Sub3_Sub2_1331.method2987(6540)) {
                    aClass246_Sub3_Sub2_1331.aClass246_Sub3_5090 = Class359.aClass246_Sub3Array3056[n4];
                    Class359.aClass246_Sub3Array3056[n4] = aClass246_Sub3_Sub2_1331;
                }
                else {
                    aClass246_Sub3_Sub2_1331.aClass246_Sub3_5090 = Class379.aClass246_Sub3Array3198[n4];
                    Class379.aClass246_Sub3Array3198[n4] = aClass246_Sub3_Sub2_1331;
                    Class358.aBoolean3033 = true;
                }
            }
            else {
                aClass246_Sub3_Sub2_1331.aClass246_Sub3_5090 = Class130.aClass246_Sub3Array1029[n4];
                Class130.aClass246_Sub3Array1029[n4] = aClass246_Sub3_Sub2_1331;
            }
        }
    }
    
    public Class266() {
        this.anInt1984 = 8;
        this.anInt1991 = 16777215;
    }
    
    static {
        Class266.aClass84_1988 = new Class84(true);
        Class266.aClass58_1992 = new IncomingOpcode(82, 0);
    }
}
