// 
// Decompiled by Procyon v0.5.30
// 

final class Class152
{
    private short[] aShortArray1217;
    private int[] anIntArray1218;
    private short[] aShortArray1219;
    Class83 aClass83_1220;
    static Class332 aClass332_1221;
    private int[] anIntArray1222;
    private short[] aShortArray1223;
    private short[] aShortArray1224;
    
    final Class178 method2473(final int n) {
        try {
            if (this.anIntArray1218 == null) {
                return null;
            }
            final Class178[] array = new Class178[this.anIntArray1218.length];
            synchronized (this.aClass83_1220.aClass207_631) {
                if (n != 2) {
                    Class152.aClass332_1221 = null;
                }
                for (int n2 = 0; this.anIntArray1218.length > n2; ++n2) {
                    array[n2] = Class98_Sub6.method981(0, -9252, this.aClass83_1220.aClass207_631, this.anIntArray1218[n2]);
                }
            }
            for (int n3 = 0; ~n3 > ~this.anIntArray1218.length; ++n3) {
                if (array[n3].anInt1387 < 13) {
                    array[n3].method2592(n ^ 0x35B0, 2);
                }
            }
            Class178 class178;
            if (~array.length != 0xFFFFFFFE) {
                class178 = new Class178(array, array.length);
            }
            else {
                class178 = array[0];
            }
            if (class178 == null) {
                return null;
            }
            if (this.aShortArray1219 != null) {
                for (int n4 = 0; ~this.aShortArray1219.length < ~n4; ++n4) {
                    class178.method2593(0, this.aShortArray1219[n4], this.aShortArray1217[n4]);
                }
            }
            if (this.aShortArray1224 != null) {
                for (int i = 0; i < this.aShortArray1224.length; ++i) {
                    class178.method2590(this.aShortArray1223[i], (byte)(-114), this.aShortArray1224[i]);
                }
            }
            return class178;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ke.F(" + n + ')');
        }
    }
    
    final boolean method2474(final int n) {
        try {
            boolean b = true;
            synchronized (this.aClass83_1220.aClass207_631) {
                for (int n2 = 0; ~n2 > -6; ++n2) {
                    if (~this.anIntArray1222[n2] != 0x0 && !this.aClass83_1220.aClass207_631.method2751(0, this.anIntArray1222[n2], -6329)) {
                        b = false;
                    }
                }
            }
            return n < 105 || b;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ke.A(" + n + ')');
        }
    }
    
    final boolean method2475(final int n) {
        try {
            if (this.anIntArray1218 == null) {
                return true;
            }
            boolean b = true;
            synchronized (this.aClass83_1220.aClass207_631) {
                for (int i = n; i < this.anIntArray1218.length; ++i) {
                    if (!this.aClass83_1220.aClass207_631.method2751(0, this.anIntArray1218[i], n - 6329)) {
                        b = false;
                    }
                }
            }
            return b;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ke.D(" + n + ')');
        }
    }
    
    final Class178 method2476(final byte b) {
        try {
            final Class178[] array = new Class178[5];
            int n = 0;
            synchronized (this.aClass83_1220.aClass207_631) {
                for (int n2 = 0; ~n2 > -6; ++n2) {
                    if (~this.anIntArray1222[n2] != 0x0) {
                        array[n++] = Class98_Sub6.method981(0, -9252, this.aClass83_1220.aClass207_631, this.anIntArray1222[n2]);
                    }
                }
            }
            for (int n3 = 0; ~n3 > -6; ++n3) {
                if (array[n3] != null && array[n3].anInt1387 < 13) {
                    array[n3].method2592(13746, 2);
                }
            }
            final Class178 class178 = new Class178(array, n);
            if (this.aShortArray1219 != null) {
                for (int n4 = 0; ~n4 > ~this.aShortArray1219.length; ++n4) {
                    class178.method2593(0, this.aShortArray1219[n4], this.aShortArray1217[n4]);
                }
            }
            if (this.aShortArray1224 != null) {
                for (int n5 = 0; this.aShortArray1224.length > n5; ++n5) {
                    class178.method2590(this.aShortArray1223[n5], (byte)(-93), this.aShortArray1224[n5]);
                }
            }
            return class178;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ke.I(" + b + ')');
        }
    }
    
    static final String method2477(final int n) {
        try {
            if (Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540 || Class266.aClass98_Sub46_Sub8_1994 == null) {
                return "";
            }
            if ((Class266.aClass98_Sub46_Sub8_1994.aString5992 == null || ~Class266.aClass98_Sub46_Sub8_1994.aString5992.length() == -1) && Class266.aClass98_Sub46_Sub8_1994.aString5985 != null && Class266.aClass98_Sub46_Sub8_1994.aString5985.length() > 0) {
                return Class266.aClass98_Sub46_Sub8_1994.aString5985;
            }
            if (n != 29558) {
                Class152.aClass332_1221 = null;
            }
            return Class266.aClass98_Sub46_Sub8_1994.aString5992;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ke.B(" + n + ')');
        }
    }
    
    static final void method2478(final int anInt4113, final int anInt4114, final Class185 aClass185_4125, final int anInt4115, final int n, final int anInt4116) {
        try {
            if (n <= -1) {
                Class98_Sub33 class98_Sub33 = null;
                for (Class98_Sub33 class98_Sub34 = (Class98_Sub33)Class98_Sub11.aClass148_3866.method2418(32); class98_Sub34 != null; class98_Sub34 = (Class98_Sub33)Class98_Sub11.aClass148_3866.method2417(106)) {
                    if (class98_Sub34.anInt4116 == anInt4116 && anInt4115 == class98_Sub34.anInt4112 && anInt4113 == class98_Sub34.anInt4113 && ~class98_Sub34.anInt4118 == ~anInt4114) {
                        class98_Sub33 = class98_Sub34;
                        break;
                    }
                }
                if (class98_Sub33 == null) {
                    class98_Sub33 = new Class98_Sub33();
                    class98_Sub33.anInt4113 = anInt4113;
                    class98_Sub33.anInt4118 = anInt4114;
                    class98_Sub33.anInt4112 = anInt4115;
                    class98_Sub33.anInt4116 = anInt4116;
                    Class98_Sub11.aClass148_3866.method2419(class98_Sub33, -20911);
                }
                class98_Sub33.aClass185_4125 = aClass185_4125;
                class98_Sub33.aBoolean4124 = false;
                class98_Sub33.aBoolean4123 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ke.C(" + anInt4113 + ',' + anInt4114 + ',' + ((aClass185_4125 != null) ? "{...}" : "null") + ',' + anInt4115 + ',' + n + ',' + anInt4116 + ')');
        }
    }
    
    private final void method2479(final Class98_Sub22 class98_Sub22, final int n, final byte b) {
        try {
            if (~n != 0xFFFFFFFE) {
                if (~n != 0xFFFFFFFD) {
                    if (n != 3) {
                        if (~n == 0xFFFFFFD7) {
                            final int unsignedByte = class98_Sub22.readUnsignedByte((byte)107);
                            this.aShortArray1217 = new short[unsignedByte];
                            this.aShortArray1219 = new short[unsignedByte];
                            for (int n2 = 0; ~n2 > ~unsignedByte; ++n2) {
                                this.aShortArray1219[n2] = (short)class98_Sub22.readShort((byte)127);
                                this.aShortArray1217[n2] = (short)class98_Sub22.readShort((byte)127);
                            }
                        }
                        else if (n != 41) {
                            if (~n <= -61 && ~n > -71) {
                                this.anIntArray1222[-60 + n] = class98_Sub22.readShort((byte)127);
                            }
                        }
                        else {
                            final int i = class98_Sub22.readUnsignedByte((byte)(-123));
                            this.aShortArray1223 = new short[i];
                            this.aShortArray1224 = new short[i];
                            for (int n3 = 0; i > n3; ++n3) {
                                this.aShortArray1224[n3] = (short)class98_Sub22.readShort((byte)127);
                                this.aShortArray1223[n3] = (short)class98_Sub22.readShort((byte)127);
                            }
                        }
                    }
                }
                else {
                    final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)100);
                    this.anIntArray1218 = new int[unsignedByte2];
                    for (int n4 = 0; ~n4 > ~unsignedByte2; ++n4) {
                        this.anIntArray1218[n4] = class98_Sub22.readShort((byte)127);
                    }
                }
            }
            else {
                class98_Sub22.readUnsignedByte((byte)(-111));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ke.G(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ',' + b + ')');
        }
    }
    
    final void method2480(final boolean b, final Class98_Sub22 class98_Sub22) {
        try {
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)95);
                if (~unsignedByte == -1) {
                    break;
                }
                this.method2479(class98_Sub22, unsignedByte, (byte)115);
            }
            if (b) {
                this.method2475(44);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ke.E(" + b + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public Class152() {
        this.anIntArray1222 = new int[] { -1, -1, -1, -1, -1 };
    }
    
    public static void method2481(final boolean b) {
        try {
            if (!b) {
                Class152.aClass332_1221 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ke.H(" + b + ')');
        }
    }
    
    static {
        new Class105("", 76);
    }
}
