// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub14 extends Class98_Sub10
{
    private int anInt5604;
    private int anInt5605;
    static IncomingOpcode aClass58_5606;
    private int anInt5607;
    static IncomingOpcode aClass58_5608;
    private int[] anIntArray5609;
    static int anInt5610;
    private int anInt5611;
    static Class377 aClass377_5612;
    static int anInt5613;
    static int anInt5614;
    
    static final void method1045(final int anInt4855, final int n) {
        try {
            Class98_Sub46_Sub20_Sub2.anInt6317 = 2;
            Class146_Sub2.anInt4855 = anInt4855;
            if (Class98_Sub10_Sub10.aString5593 != null) {
                final Class98_Sub22 class98_Sub22 = new Class98_Sub22(Class167.method2531(Class378.method4006(Class98_Sub10_Sub10.aString5593, -1), n + 18887));
                final long method1246 = class98_Sub22.method1246(-101);
                Class98_Sub10_Sub19.aLong5631 = class98_Sub22.method1246(-90);
                if (!client.aBoolean3553) {
                    if (n != -6182) {
                        Class98_Sub10_Sub14.aClass58_5606 = null;
                    }
                    Class342.method3814(true, Class98_Sub28.method1305(-68, method1246), 73, "");
                    return;
                }
            }
            Class369.method3952(35, (byte)(-55));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "he.E(" + anInt4855 + ',' + n + ')');
        }
    }
    
    static final Class119_Sub3 method1046(final boolean b, final Class98_Sub22 class98_Sub22) {
        try {
            if (b) {
                Class98_Sub10_Sub14.anInt5610 = 113;
            }
            return new Class119_Sub3(class98_Sub22.readUShort(false), class98_Sub22.readUShort(false), class98_Sub22.readUShort(false), class98_Sub22.readUShort(false), class98_Sub22.readUShort(false), class98_Sub22.readUShort(b), class98_Sub22.readUShort(false), class98_Sub22.readUShort(false), class98_Sub22.method1186(-124), class98_Sub22.readUnsignedByte((byte)57));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "he.B(" + b + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (super.aClass223_3859.aBoolean1683) {
                final int[][] method2829 = this.method994(n2, 24431, 0);
                final int[] array = method2829[0];
                final int[] array2 = method2829[1];
                final int[] array3 = method2829[2];
                final int[] array4 = method2828[0];
                final int[] array5 = method2828[1];
                final int[] array6 = method2828[2];
                for (int n3 = 0; ~Class25.anInt268 < ~n3; ++n3) {
                    final int n4 = array[n3];
                    int n5 = -this.anIntArray5609[0] + n4;
                    if (~n5 > -1) {
                        n5 = -n5;
                    }
                    if (this.anInt5604 < n5) {
                        array4[n3] = n4;
                        array5[n3] = array2[n3];
                        array6[n3] = array3[n3];
                    }
                    else {
                        final int n6 = array2[n3];
                        int n7 = n6 + -this.anIntArray5609[1];
                        if (~n7 > -1) {
                            n7 = -n7;
                        }
                        if (this.anInt5604 < n7) {
                            array4[n3] = n4;
                            array5[n3] = n6;
                            array6[n3] = array3[n3];
                        }
                        else {
                            final int n8 = array3[n3];
                            int n9 = n8 - this.anIntArray5609[2];
                            if (~n9 > -1) {
                                n9 = -n9;
                            }
                            if (~n9 < ~this.anInt5604) {
                                array4[n3] = n4;
                                array5[n3] = n6;
                                array6[n3] = n8;
                            }
                            else {
                                array4[n3] = n4 * this.anInt5605 >> -5198036;
                                array5[n3] = n6 * this.anInt5607 >> 949873324;
                                array6[n3] = this.anInt5611 * n8 >> -1314808852;
                            }
                        }
                    }
                }
            }
            if (n >= -76) {
                Class98_Sub10_Sub14.aClass58_5606 = null;
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "he.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final boolean method1047(final int n, final byte b, final int n2) {
        try {
            return b > 21 && (s_Sub1.method3433(n2, 15849, n) || Class64_Sub19.method631(n, n2, -2));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "he.D(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (n != 0) {
                if (n != 1) {
                    if (~n != 0xFFFFFFFD) {
                        if (n != 3) {
                            if (n == 4) {
                                final int method1186 = class98_Sub22.method1186(-127);
                                this.anIntArray5609[0] = Class202.method2702(method1186, 16711680) << 324143748;
                                this.anIntArray5609[2] = Class202.method2702(255, method1186) >> -296948276;
                                this.anIntArray5609[1] = Class202.method2702(method1186, 65280) >> 374537476;
                            }
                        }
                        else {
                            this.anInt5605 = class98_Sub22.readShort((byte)127);
                        }
                    }
                    else {
                        this.anInt5607 = class98_Sub22.readShort((byte)127);
                    }
                }
                else {
                    this.anInt5611 = class98_Sub22.readShort((byte)127);
                }
            }
            else {
                this.anInt5604 = class98_Sub22.readShort((byte)127);
            }
            if (b >= -92) {
                this.anInt5611 = 115;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "he.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public static void method1048(final byte b) {
        try {
            Class98_Sub10_Sub14.aClass58_5606 = null;
            Class98_Sub10_Sub14.aClass58_5608 = null;
            Class98_Sub10_Sub14.aClass377_5612 = null;
            if (b != -100) {
                Class98_Sub10_Sub14.anInt5610 = -12;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "he.F(" + b + ')');
        }
    }
    
    public Class98_Sub10_Sub14() {
        super(1, false);
        this.anInt5607 = 4096;
        this.anIntArray5609 = new int[3];
        this.anInt5604 = 409;
        this.anInt5605 = 4096;
        this.anInt5611 = 4096;
    }
    
    static {
        Class98_Sub10_Sub14.aClass58_5606 = new IncomingOpcode(68, 3);
        Class98_Sub10_Sub14.anInt5610 = 0;
        Class98_Sub10_Sub14.aClass58_5608 = new IncomingOpcode(77, 2);
        Class98_Sub10_Sub14.aClass377_5612 = new Class377(16);
        Class98_Sub10_Sub14.anInt5614 = 0;
        Class98_Sub10_Sub14.anInt5613 = 0;
    }
}
