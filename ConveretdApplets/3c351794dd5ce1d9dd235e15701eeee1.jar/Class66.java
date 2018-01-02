import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class66
{
    int anInt505;
    int anInt506;
    static boolean aBoolean507;
    int anInt508;
    int anInt509;
    private int anInt510;
    int anInt511;
    int anInt512;
    int anInt513;
    int anInt514;
    int anInt515;
    long aLong516;
    int anInt517;
    int anInt518;
    private boolean aBoolean519;
    
    final void method682(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)96);
                if (~unsignedByte == -1) {
                    break;
                }
                this.method686(unsignedByte, class98_Sub22, (byte)(-126));
            }
            if (n != 17127) {
                this.method686(-122, null, (byte)(-5));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ega.B(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final int method683(final byte b, final int n, final int n2) {
        try {
            if (~Class64_Sub10.anInt3666 == 0x0) {
                return 1;
            }
            if (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)122) != ~n2) {
                Class151_Sub9.method2472(true, Class309.aClass309_2599.method3615(Class374.anInt3159, (byte)25), n2, true);
                if (~n2 != ~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)126)) {
                    return -1;
                }
            }
            try {
                final Dimension size = Class42_Sub3.aCanvas5361.getSize();
                if (b > -77) {
                    method683((byte)(-124), -85, 24);
                }
                Class246_Sub2.method2972(-117, Class98_Sub46_Sub10.aClass197_6019, Class195.aClass43_1499, true, Class265.aHa1974, Class309.aClass309_2599.method3615(Class374.anInt3159, (byte)25));
                final Class178 method981 = Class98_Sub6.method981(0, -9252, Class76_Sub9.aClass207_3787, Class64_Sub10.anInt3666);
                final long method982 = Class343.method3819(-47);
                Class265.aHa1974.la();
                Class266.aClass111_1986.method2100(0, Class207.anInt1577, 0);
                Class265.aHa1974.a(Class266.aClass111_1986);
                Class265.aHa1974.DA(size.width / 2, size.height / 2, 512, 512);
                Class265.aHa1974.xa(1.0f);
                Class265.aHa1974.ZA(16777215, 0.5f, 0.5f, 20.0f, -50.0f, 30.0f);
                final Class146 method983 = Class265.aHa1974.method1790(method981, 2048, 64, 64, 768);
                int n3 = 0;
            Label_0366:
                for (int i = 0; i < 500; ++i) {
                    Class265.aHa1974.GA(0);
                    Class265.aHa1974.ya();
                    for (int j = 15; j >= 0; --j) {
                        for (int n4 = 0; j >= n4; ++n4) {
                            Class76_Sub5.aClass111_3745.method2100((int)((n4 - j / 2.0f) * r_Sub2.anInt6333), 0, (1 + j) * r_Sub2.anInt6333);
                            method983.method2325(Class76_Sub5.aClass111_3745, null, 0);
                            ++n3;
                            if (~n >= ~(-method982 + Class343.method3819(-47))) {
                                break Label_0366;
                            }
                        }
                    }
                }
                Class265.aHa1974.method1825();
                final long n5 = n3 * 1000 / (-method982 + Class343.method3819(-47));
                Class265.aHa1974.GA(0);
                Class265.aHa1974.ya();
                return (int)n5;
            }
            catch (Throwable t) {
                t.printStackTrace();
                return -1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ega.E(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    static final void method684(final int n, final String s, final boolean b) {
        try {
            if (s != null) {
                if (Class248.anInt1897 >= 100) {
                    za_Sub2.method1684(Class309.aClass309_2625.method3615(Class374.anInt3159, (byte)25), 4, (byte)(-40));
                }
                else {
                    final String method3867 = Class353.method3867(-1, s);
                    if (method3867 != null) {
                        for (int i = 0; i < Class248.anInt1897; ++i) {
                            final String method3868 = Class353.method3867(-1, Class246_Sub4_Sub1.aStringArray6171[i]);
                            if (method3868 != null && method3868.equals(method3867)) {
                                za_Sub2.method1684(s + Class309.aClass309_2626.method3615(Class374.anInt3159, (byte)25), 4, (byte)(-66));
                                return;
                            }
                            if (Class98_Sub45.aStringArray4255[i] != null) {
                                final String method3869 = Class353.method3867(-1, Class98_Sub45.aStringArray4255[i]);
                                if (method3869 != null && method3869.equals(method3867)) {
                                    za_Sub2.method1684(s + Class309.aClass309_2626.method3615(Class374.anInt3159, (byte)25), 4, (byte)(-84));
                                    return;
                                }
                            }
                        }
                        for (int n2 = 0; ~n2 > ~Class314.anInt2692; ++n2) {
                            final String method3870 = Class353.method3867(-1, Class98_Sub25.aStringArray4026[n2]);
                            if (method3870 != null && method3870.equals(method3867)) {
                                za_Sub2.method1684(Class309.aClass309_2631.method3615(Class374.anInt3159, (byte)25) + s + Class309.aClass309_2632.method3615(Class374.anInt3159, (byte)25), 4, (byte)59);
                                return;
                            }
                            if (Class315.aStringArray3527[n2] != null) {
                                final String method3871 = Class353.method3867(-1, Class315.aStringArray3527[n2]);
                                if (method3871 != null && method3871.equals(method3867)) {
                                    za_Sub2.method1684(Class309.aClass309_2631.method3615(Class374.anInt3159, (byte)25) + s + Class309.aClass309_2632.method3615(Class374.anInt3159, (byte)25), 4, (byte)(-95));
                                    return;
                                }
                            }
                        }
                        if (Class353.method3867(-1, Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aString6537).equals(method3867)) {
                            za_Sub2.method1684(Class309.aClass309_2628.method3615(Class374.anInt3159, (byte)25), 4, (byte)(-66));
                        }
                        else {
                            final Class98_Sub11 method3872 = Class246_Sub3_Sub4.method3023(260, Class98_Sub8.aClass171_3264, Class331.aClass117_2811);
                            method3872.aClass98_Sub22_Sub1_3865.method1194(1 + r_Sub2.method1650(s, (byte)90), 91);
                            method3872.aClass98_Sub22_Sub1_3865.method1188(s, (byte)113);
                            method3872.aClass98_Sub22_Sub1_3865.method1194(b ? 1 : 0, -89);
                            if (n < -40) {
                                Class98_Sub10_Sub29.sendPacket(false, method3872);
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ega.C(" + n + ',' + ((s != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method685(final int n) {
        try {
            this.anInt514 = Class284_Sub2_Sub2.anIntArray6202[this.anInt510 << 1969546147];
            final long n2 = this.anInt506;
            if (n != 1) {
                this.anInt509 = 44;
            }
            final long n3 = this.anInt511;
            final long n4 = this.anInt505;
            this.anInt517 = (int)Math.sqrt(n2 * n2 + n3 * n3 + n4 * n4);
            if (this.anInt512 == 0) {
                this.anInt512 = 1;
            }
            if (~this.anInt518 == -1) {
                this.aLong516 = 2147483647L;
            }
            else if (~this.anInt518 == 0xFFFFFFFE) {
                this.aLong516 = 8 * this.anInt517 / this.anInt512;
                this.aLong516 *= this.aLong516;
            }
            else if (~this.anInt518 == 0xFFFFFFFD) {
                this.aLong516 = 8 * this.anInt517 / this.anInt512;
            }
            if (this.aBoolean519) {
                this.anInt517 *= -1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ega.A(" + n + ')');
        }
    }
    
    private final void method686(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (~n == 0xFFFFFFFE) {
                this.anInt510 = class98_Sub22.readShort((byte)127);
            }
            else if (n == 2) {
                class98_Sub22.readUnsignedByte((byte)2);
            }
            else if (n != 3) {
                if (~n == 0xFFFFFFFB) {
                    this.anInt518 = class98_Sub22.readUnsignedByte((byte)90);
                    this.anInt512 = class98_Sub22.readInt(-2);
                }
                else if (n == 6) {
                    this.anInt508 = class98_Sub22.readUnsignedByte((byte)58);
                }
                else if (~n != 0xFFFFFFF7) {
                    if (n == 9) {
                        this.anInt515 = 1;
                    }
                    else if (~n == 0xFFFFFFF5) {
                        this.aBoolean519 = true;
                    }
                }
                else {
                    this.anInt513 = 1;
                }
            }
            else {
                this.anInt506 = class98_Sub22.readInt(-2);
                this.anInt511 = class98_Sub22.readInt(-2);
                this.anInt505 = class98_Sub22.readInt(-2);
            }
            if (b > -120) {
                this.anInt518 = 31;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ega.D(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public Class66() {
        this.anInt513 = 0;
        this.anInt515 = 0;
        this.aBoolean519 = false;
    }
    
    static {
        Class66.aBoolean507 = false;
    }
}
