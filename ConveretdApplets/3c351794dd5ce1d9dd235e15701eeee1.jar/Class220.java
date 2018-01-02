// 
// Decompiled by Procyon v0.5.30
// 

final class Class220
{
    private int[] anIntArray1646;
    private int[] anIntArray1647;
    private int[][] anIntArrayArray1648;
    int anInt1649;
    private Class377 aClass377_1650;
    private int[] anIntArray1651;
    private int[] anIntArray1652;
    private int[] anIntArray1653;
    private String aString1654;
    private int[] anIntArray1655;
    private String[] aStringArray1656;
    static Class98_Sub4 aClass98_Sub4_1657;
    private int[][] anIntArrayArray1658;
    private int[][] anIntArrayArray1659;
    private int[] anIntArray1660;
    private int[] anIntArray1661;
    private String[] aStringArray1662;
    private String aString1663;
    
    final void method2816(final byte b, final Class98_Sub22 class98_Sub22) {
        try {
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)32);
                if (~unsignedByte == -1) {
                    break;
                }
                this.method2818(class98_Sub22, unsignedByte, 98);
            }
            if (b > -106) {
                this.aString1663 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oba.E(" + b + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2817(final int n) {
        try {
            Class220.aClass98_Sub4_1657 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oba.D(" + n + ')');
        }
    }
    
    private final void method2818(final Class98_Sub22 class98_Sub22, final int n, final int n2) {
        try {
            if (n != 1) {
                if (n != 2) {
                    if (n != 3) {
                        if (n == 4) {
                            final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-1));
                            this.anIntArrayArray1648 = new int[unsignedByte][3];
                            for (int n3 = 0; ~unsignedByte < ~n3; ++n3) {
                                this.anIntArrayArray1648[n3][0] = class98_Sub22.readShort((byte)127);
                                this.anIntArrayArray1648[n3][1] = class98_Sub22.readInt(-2);
                                this.anIntArrayArray1648[n3][2] = class98_Sub22.readInt(-2);
                            }
                        }
                        else if (n != 5) {
                            if (n != 6) {
                                if (n != 7) {
                                    if (n != 8) {
                                        if (~n != 0xFFFFFFF6) {
                                            if (n == 10) {
                                                final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)(-126));
                                                this.anIntArray1652 = new int[unsignedByte2];
                                                for (int n4 = 0; ~unsignedByte2 < ~n4; ++n4) {
                                                    this.anIntArray1652[n4] = class98_Sub22.readInt(-2);
                                                }
                                            }
                                            else if (n != 12) {
                                                if (n == 13) {
                                                    final int unsignedByte3 = class98_Sub22.readUnsignedByte((byte)44);
                                                    this.anIntArray1651 = new int[unsignedByte3];
                                                    for (int n5 = 0; ~n5 > ~unsignedByte3; ++n5) {
                                                        this.anIntArray1651[n5] = class98_Sub22.readShort((byte)127);
                                                    }
                                                }
                                                else if (~n == 0xFFFFFFF1) {
                                                    final int unsignedByte4 = class98_Sub22.readUnsignedByte((byte)41);
                                                    this.anIntArrayArray1658 = new int[unsignedByte4][2];
                                                    for (int i = 0; i < unsignedByte4; ++i) {
                                                        this.anIntArrayArray1658[i][0] = class98_Sub22.readUnsignedByte((byte)(-114));
                                                        this.anIntArrayArray1658[i][1] = class98_Sub22.readUnsignedByte((byte)(-119));
                                                    }
                                                }
                                                else if (n != 15) {
                                                    if (n == 17) {
                                                        this.anInt1649 = class98_Sub22.readShort((byte)127);
                                                    }
                                                    else if (~n == 0xFFFFFFED) {
                                                        final int unsignedByte5 = class98_Sub22.readUnsignedByte((byte)87);
                                                        this.anIntArray1653 = new int[unsignedByte5];
                                                        this.anIntArray1647 = new int[unsignedByte5];
                                                        this.aStringArray1662 = new String[unsignedByte5];
                                                        this.anIntArray1661 = new int[unsignedByte5];
                                                        for (int n6 = 0; ~n6 > ~unsignedByte5; ++n6) {
                                                            this.anIntArray1647[n6] = class98_Sub22.readInt(-2);
                                                            this.anIntArray1653[n6] = class98_Sub22.readInt(-2);
                                                            this.anIntArray1661[n6] = class98_Sub22.readInt(-2);
                                                            this.aStringArray1662[n6] = class98_Sub22.readString((byte)84);
                                                        }
                                                    }
                                                    else if (~n != 0xFFFFFFEC) {
                                                        if (n == 249) {
                                                            final int unsignedByte6 = class98_Sub22.readUnsignedByte((byte)(-109));
                                                            if (this.aClass377_1650 == null) {
                                                                this.aClass377_1650 = new Class377(Class48.method453(423660257, unsignedByte6));
                                                            }
                                                            for (int j = 0; j < unsignedByte6; ++j) {
                                                                final boolean b = class98_Sub22.readUnsignedByte((byte)3) == 1;
                                                                final int method1186 = class98_Sub22.method1186(-125);
                                                                Class98 class98;
                                                                if (b) {
                                                                    class98 = new Class98_Sub15(class98_Sub22.readString((byte)84));
                                                                }
                                                                else {
                                                                    class98 = new Class98_Sub34(class98_Sub22.readInt(-2));
                                                                }
                                                                this.aClass377_1650.method3996(class98, method1186, -1);
                                                            }
                                                        }
                                                    }
                                                    else {
                                                        final int unsignedByte7 = class98_Sub22.readUnsignedByte((byte)(-119));
                                                        this.anIntArray1655 = new int[unsignedByte7];
                                                        this.aStringArray1656 = new String[unsignedByte7];
                                                        this.anIntArray1646 = new int[unsignedByte7];
                                                        this.anIntArray1660 = new int[unsignedByte7];
                                                        for (int n7 = 0; ~n7 > ~unsignedByte7; ++n7) {
                                                            this.anIntArray1646[n7] = class98_Sub22.readInt(-2);
                                                            this.anIntArray1655[n7] = class98_Sub22.readInt(-2);
                                                            this.anIntArray1660[n7] = class98_Sub22.readInt(-2);
                                                            this.aStringArray1656[n7] = class98_Sub22.readString((byte)84);
                                                        }
                                                    }
                                                }
                                                else {
                                                    class98_Sub22.readShort((byte)127);
                                                }
                                            }
                                            else {
                                                class98_Sub22.readInt(-2);
                                            }
                                        }
                                        else {
                                            class98_Sub22.readUnsignedByte((byte)(-102));
                                        }
                                    }
                                }
                                else {
                                    class98_Sub22.readUnsignedByte((byte)95);
                                }
                            }
                            else {
                                class98_Sub22.readUnsignedByte((byte)(-121));
                            }
                        }
                        else {
                            class98_Sub22.readShort((byte)127);
                        }
                    }
                    else {
                        final int unsignedByte8 = class98_Sub22.readUnsignedByte((byte)35);
                        this.anIntArrayArray1659 = new int[unsignedByte8][3];
                        for (int n8 = 0; ~unsignedByte8 < ~n8; ++n8) {
                            this.anIntArrayArray1659[n8][0] = class98_Sub22.readShort((byte)127);
                            this.anIntArrayArray1659[n8][1] = class98_Sub22.readInt(-2);
                            this.anIntArrayArray1659[n8][2] = class98_Sub22.readInt(-2);
                        }
                    }
                }
                else {
                    this.aString1654 = class98_Sub22.method1223(-1);
                }
            }
            else {
                this.aString1663 = class98_Sub22.method1223(-1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oba.A(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    final void method2819(final int n) {
        try {
            if (n != -9639) {
                this.anIntArray1651 = null;
            }
            if (this.aString1654 == null) {
                this.aString1654 = this.aString1663;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oba.C(" + n + ')');
        }
    }
    
    static final void method2820(final byte b) {
        try {
            Class245.aClass338Array1865 = new Class338[50];
            Class306.anInt2566 = 0;
            if (b <= 117) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oba.B(" + b + ')');
        }
    }
    
    public Class220() {
        this.anInt1649 = -1;
    }
    
    static {
        Class220.aClass98_Sub4_1657 = null;
    }
}
