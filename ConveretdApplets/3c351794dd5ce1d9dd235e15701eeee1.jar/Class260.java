// 
// Decompiled by Procyon v0.5.30
// 

final class Class260 implements d
{
    private Class238[] aClass238Array3252;
    private Class100 aClass100_3253;
    static Class377 aClass377_3254;
    private int anInt3255;
    static short aShort3256;
    private Class207 aClass207_3257;
    private Class207 aClass207_3258;
    static int anInt3259;
    static float aFloat3260;
    static int anInt3261;
    static IncomingOpcode aClass58_3262;
    static Class326 aClass326_3263;
    
    @Override
    public final int[] method13(final int n, final int n2, final int n3, final float n4, final boolean b, final int n5) {
        try {
            if (n < 108) {
                this.method8(-35, -59);
            }
            return this.method3206(false, n3).method1633(this.aClass238Array3252[n3].aBoolean1824, n4, n5, this, this.aClass207_3257, (byte)79, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qf.I(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ',' + n5 + ')');
        }
    }
    
    @Override
    public final int method12(final boolean b) {
        try {
            if (!b) {
                this.method9(-28, (byte)95, -93, -0.31396338f, false, -83);
            }
            return this.anInt3255;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qf.G(" + b + ')');
        }
    }
    
    private final Class98_Sub46_Sub19 method3206(final boolean b, final int n) {
        try {
            final Class98_Sub46 method1694 = this.aClass100_3253.method1694((byte)127, n);
            if (method1694 != null) {
                return (Class98_Sub46_Sub19)method1694;
            }
            final byte[] method1695 = this.aClass207_3258.method2733(n, -5);
            if (method1695 == null) {
                return null;
            }
            if (b) {
                return null;
            }
            final Class98_Sub46_Sub19 class98_Sub46_Sub19 = new Class98_Sub46_Sub19(new Class98_Sub22(method1695));
            this.aClass100_3253.method1695(26404, class98_Sub46_Sub19, n);
            return class98_Sub46_Sub19;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qf.B(" + b + ',' + n + ')');
        }
    }
    
    static final void method3207(final int n, final short[] array, final String[] array2, final int n2, final int n3) {
        try {
            if (~n3 > ~n2) {
                final int n4 = (n2 + n3) / 2;
                int n5 = n3;
                final String s = array2[n4];
                array2[n4] = array2[n2];
                array2[n2] = s;
                final short n6 = array[n4];
                array[n4] = array[n2];
                array[n2] = n6;
                for (int n7 = n3; ~n7 > ~n2; ++n7) {
                    if (s == null || (array2[n7] != null && ~(n7 & 0x1) < ~array2[n7].compareTo(s))) {
                        final String s2 = array2[n7];
                        array2[n7] = array2[n5];
                        array2[n5] = s2;
                        final short n8 = array[n7];
                        array[n7] = array[n5];
                        array[n5++] = n8;
                    }
                }
                array2[n2] = array2[n5];
                array2[n5] = s;
                array[n2] = array[n5];
                array[n5] = n6;
                method3207(47, array, array2, -1 + n5, n3);
                method3207(47, array, array2, n2, 1 + n5);
            }
            if (n != 47) {
                method3209(42);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qf.D(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    public final int[] method9(final int n, final byte b, final int n2, final float n3, final boolean b2, final int n4) {
        try {
            if (b > -111) {
                this.method3206(true, 110);
            }
            return this.method3206(false, n).method1631(n2, b2, this, n3, this.aClass238Array3252[n].aBoolean1824, this.aClass207_3257, n4, (byte)(-34));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qf.E(" + n + ',' + b + ',' + n2 + ',' + n3 + ',' + b2 + ',' + n4 + ')');
        }
    }
    
    @Override
    public final boolean method8(final int n, final int n2) {
        try {
            final Class98_Sub46_Sub19 method3206 = this.method3206(false, n2);
            return method3206 != null && method3206.method1629(0, this.aClass207_3257, this);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qf.F(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method3208(final int anInt5952, final int n, final int anInt5953, final int anInt5954, final int anInt5955) {
        try {
            Class98_Sub46_Sub11.anInt6025 = anInt5955;
            if (n >= -81) {
                method3208(33, 69, -94, -55, 47);
            }
            Class98_Sub46_Sub2.anInt5952 = anInt5952;
            Class98_Sub46_Sub20.anInt6074 = anInt5954;
            Class282.anInt2132 = anInt5953;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qf.J(" + anInt5952 + ',' + n + ',' + anInt5953 + ',' + anInt5954 + ',' + anInt5955 + ')');
        }
    }
    
    @Override
    public final float[] method10(final byte b, final boolean b2, final int n, final int n2, final float n3, final int n4) {
        try {
            if (b > -116) {
                this.aClass238Array3252 = null;
            }
            return this.method3206(false, n).method1630(this, this.aClass207_3257, (byte)(-86), n4, n2, this.aClass238Array3252[n].aBoolean1824);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qf.H(" + b + ',' + b2 + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    public final Class238 method11(final int n, final int n2) {
        try {
            if (n2 != -28755) {
                this.method3206(false, 2);
            }
            return this.aClass238Array3252[n];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qf.A(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method3209(final int n) {
        try {
            if (n == -19788) {
                Class260.aClass58_3262 = null;
                Class260.aClass377_3254 = null;
                Class260.aClass326_3263 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qf.C(" + n + ')');
        }
    }
    
    Class260(final Class207 class207, final Class207 aClass207_3258, final Class207 aClass207_3259) {
        this.aClass100_3253 = new Class100(256);
        try {
            this.aClass207_3258 = aClass207_3258;
            this.aClass207_3257 = aClass207_3259;
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(class207.method2745(0, 0, false));
            this.anInt3255 = class98_Sub22.readShort((byte)127);
            this.aClass238Array3252 = new Class238[this.anInt3255];
            for (int n = 0; this.anInt3255 > n; ++n) {
                if (class98_Sub22.readUnsignedByte((byte)72) == 1) {
                    this.aClass238Array3252[n] = new Class238();
                }
            }
            for (int n2 = 0; ~n2 > ~this.anInt3255; ++n2) {
                if (this.aClass238Array3252[n2] != null) {
                    this.aClass238Array3252[n2].aBoolean1825 = (~class98_Sub22.readUnsignedByte((byte)50) == -1);
                }
            }
            for (int n3 = 0; ~n3 > ~this.anInt3255; ++n3) {
                if (this.aClass238Array3252[n3] != null) {
                    this.aClass238Array3252[n3].aBoolean1822 = (~class98_Sub22.readUnsignedByte((byte)95) == 0xFFFFFFFE);
                }
            }
            for (int n4 = 0; ~n4 > ~this.anInt3255; ++n4) {
                if (this.aClass238Array3252[n4] != null) {
                    this.aClass238Array3252[n4].aBoolean1833 = (class98_Sub22.readUnsignedByte((byte)(-105)) == 1);
                }
            }
            for (int i = 0; i < this.anInt3255; ++i) {
                if (this.aClass238Array3252[i] != null) {
                    this.aClass238Array3252[i].aByte1829 = class98_Sub22.readSignedByte((byte)(-19));
                }
            }
            for (int n5 = 0; this.anInt3255 > n5; ++n5) {
                if (this.aClass238Array3252[n5] != null) {
                    this.aClass238Array3252[n5].aByte1830 = class98_Sub22.readSignedByte((byte)(-19));
                }
            }
            for (int n6 = 0; ~n6 > ~this.anInt3255; ++n6) {
                if (this.aClass238Array3252[n6] != null) {
                    this.aClass238Array3252[n6].aByte1820 = class98_Sub22.readSignedByte((byte)(-19));
                }
            }
            for (int n7 = 0; ~n7 > ~this.anInt3255; ++n7) {
                if (this.aClass238Array3252[n7] != null) {
                    this.aClass238Array3252[n7].aByte1816 = class98_Sub22.readSignedByte((byte)(-19));
                }
            }
            for (int n8 = 0; ~this.anInt3255 < ~n8; ++n8) {
                if (this.aClass238Array3252[n8] != null) {
                    this.aClass238Array3252[n8].aShort1831 = (short)class98_Sub22.readShort((byte)127);
                }
            }
            for (int j = 0; j < this.anInt3255; ++j) {
                if (this.aClass238Array3252[j] != null) {
                    this.aClass238Array3252[j].aByte1823 = class98_Sub22.readSignedByte((byte)(-19));
                }
            }
            for (int n9 = 0; this.anInt3255 > n9; ++n9) {
                if (this.aClass238Array3252[n9] != null) {
                    this.aClass238Array3252[n9].aByte1837 = class98_Sub22.readSignedByte((byte)(-19));
                }
            }
            for (int k = 0; k < this.anInt3255; ++k) {
                if (this.aClass238Array3252[k] != null) {
                    this.aClass238Array3252[k].aBoolean1827 = (class98_Sub22.readUnsignedByte((byte)(-126)) == 1);
                }
            }
            for (int n10 = 0; this.anInt3255 > n10; ++n10) {
                if (this.aClass238Array3252[n10] != null) {
                    this.aClass238Array3252[n10].aBoolean1824 = (class98_Sub22.readUnsignedByte((byte)(-117)) == 1);
                }
            }
            for (int n11 = 0; this.anInt3255 > n11; ++n11) {
                if (this.aClass238Array3252[n11] != null) {
                    this.aClass238Array3252[n11].aByte1832 = class98_Sub22.readSignedByte((byte)(-19));
                }
            }
            for (int n12 = 0; ~this.anInt3255 < ~n12; ++n12) {
                if (this.aClass238Array3252[n12] != null) {
                    this.aClass238Array3252[n12].aBoolean1826 = (~class98_Sub22.readUnsignedByte((byte)46) == 0xFFFFFFFE);
                }
            }
            for (int n13 = 0; this.anInt3255 > n13; ++n13) {
                if (this.aClass238Array3252[n13] != null) {
                    this.aClass238Array3252[n13].aBoolean1819 = (class98_Sub22.readUnsignedByte((byte)31) == 1);
                }
            }
            for (int n14 = 0; ~this.anInt3255 < ~n14; ++n14) {
                if (this.aClass238Array3252[n14] != null) {
                    this.aClass238Array3252[n14].aBoolean1817 = (class98_Sub22.readUnsignedByte((byte)122) == 1);
                }
            }
            for (int n15 = 0; ~this.anInt3255 < ~n15; ++n15) {
                if (this.aClass238Array3252[n15] != null) {
                    this.aClass238Array3252[n15].anInt1821 = class98_Sub22.readUnsignedByte((byte)89);
                }
            }
            for (int n16 = 0; this.anInt3255 > n16; ++n16) {
                if (this.aClass238Array3252[n16] != null) {
                    this.aClass238Array3252[n16].anInt1835 = class98_Sub22.readInt(-2);
                }
            }
            for (int n17 = 0; ~n17 > ~this.anInt3255; ++n17) {
                if (this.aClass238Array3252[n17] != null) {
                    this.aClass238Array3252[n17].anInt1818 = class98_Sub22.readUnsignedByte((byte)35);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qf.<init>(" + ((class207 != null) ? "{...}" : "null") + ',' + ((aClass207_3258 != null) ? "{...}" : "null") + ',' + ((aClass207_3259 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class260.aShort3256 = 32767;
        Class260.aClass377_3254 = new Class377(64);
    }
}
