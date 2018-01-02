import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

class PacketParser extends Class373
{
    private Class332 aClass332_5461;
    Class332 aClass332_5462;
    private Class332 aClass332_5463;
    private Class332 aClass332_5464;
    private Class332 aClass332_5465;
    static IncomingOpcode aClass58_5466;
    private Class332 aClass332_5467;
    static Class98_Sub46_Sub16[] aClass98_Sub46_Sub16Array5468;
    
    @Override
    final void method3965(final int n, final int n2, final int n3, final boolean b) {
        try {
            final int n4 = this.aClass332_5463.method3737() + n3;
            final int n5 = n3 + (super.aClass93_3478.anInt3514 + -this.aClass332_5465.method3737());
            final int n6 = n2 + this.aClass332_5461.method3749();
            final int n7 = n2 + (super.aClass93_3478.anInt3504 + -this.aClass332_5464.method3749());
            final int n8 = n5 + -n4;
            final int n9 = -n6 + n7;
            final int n10 = n8 * this.method3963(n ^ 0x66) / 10000;
            final int[] array = new int[4];
            Class265.aHa1974.K(array);
            Class265.aHa1974.KA(n4, n6, n10 + n4, n7);
            this.method3966(n9, n8, n6, n4, (byte)88);
            Class265.aHa1974.KA(n4 - -n10, n6, n5, n7);
            this.aClass332_5467.method3738(n4, n6, n8, n9);
            Class265.aHa1974.KA(array[0], array[1], array[n], array[3]);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bia.F(" + n + ',' + n2 + ',' + n3 + ',' + b + ')');
        }
    }
    
    PacketParser(final Class207 class207, final Class207 class208, final Class93_Sub1 class93_Sub1) {
        super(class207, class208, class93_Sub1);
    }
    
    void method3966(final int n, final int n2, final int n3, final int n4, final byte b) {
        try {
            this.aClass332_5462.method3738(n4, n3, n2, n);
            method3969(56, 14);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bia.G(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ')');
        }
    }
    
    static final boolean method3967(final int n) throws IOException {
        try {
            if (aa_Sub1.aClass123_3561 == null) {
                return false;
            }
            if (Class92.currentIncommingOpcode == null) {
                if (Class98_Sub43.aBoolean4243) {
                    if (!aa_Sub1.aClass123_3561.method2203(-1949, 1)) {
                        return false;
                    }
                    aa_Sub1.aClass123_3561.method2208(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992, 0, 2047, 1);
                    Class224_Sub1.anInt5031 = 0;
                    ++Class103.anInt892;
                    Class98_Sub43.aBoolean4243 = false;
                }
                Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 = 0;
                if (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1260((byte)54)) {
                    if (!aa_Sub1.aClass123_3561.method2203(-1949, 1)) {
                        return false;
                    }
                    aa_Sub1.aClass123_3561.method2208(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992, 1, 2047, 1);
                    ++Class103.anInt892;
                    Class224_Sub1.anInt5031 = 0;
                }
                Class98_Sub43.aBoolean4243 = true;
                final IncomingOpcode[] method3629 = Class313.method3629(125);
                final int method3630 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1255(0);
                if (method3630 < 0 || method3630 >= method3629.length) {
                    throw new IOException("invo:" + method3630 + " ip:" + Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991);
                }
                Class92.currentIncommingOpcode = method3629[method3630];
                Class65.anInt496 = Class92.currentIncommingOpcode.anInt460;
            }
            if (~Class65.anInt496 == 0x0) {
                if (!aa_Sub1.aClass123_3561.method2203(-1949, 1)) {
                    return false;
                }
                aa_Sub1.aClass123_3561.method2208(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992, 0, 2047, 1);
                Class224_Sub1.anInt5031 = 0;
                Class65.anInt496 = (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992[0] & 0xFF);
                ++Class103.anInt892;
            }
            if (Class65.anInt496 == -2) {
                if (!aa_Sub1.aClass123_3561.method2203(-1949, 2)) {
                    return false;
                }
                aa_Sub1.aClass123_3561.method2208(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992, 0, 2047, 2);
                Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 = 0;
                Class65.anInt496 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                Class103.anInt892 += 2;
                Class224_Sub1.anInt5031 = 0;
            }
            if (~Class65.anInt496 < -1) {
                if (!aa_Sub1.aClass123_3561.method2203(-1949, Class65.anInt496)) {
                    return false;
                }
                Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 = 0;
                aa_Sub1.aClass123_3561.method2208(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.aByteArray3992, 0, n - 525198532, Class65.anInt496);
                Class224_Sub1.anInt5031 = 0;
                Class103.anInt892 += Class65.anInt496;
            }
            Class98_Sub10_Sub21.aClass58_5641 = Class260.aClass58_3262;
            Class260.aClass58_3262 = Class98_Sub30.aClass58_4094;
            Class98_Sub30.aClass58_4094 = Class92.currentIncommingOpcode;
            if (Class92.currentIncommingOpcode == Class77.aClass58_591) {
                Class92.currentIncommingOpcode = null;
                return false;
            }
            if (Class60.aClass58_476 == Class92.currentIncommingOpcode) {
                Class75.aClass140_584.method2286(-7469, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt1(false), Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127));
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (aa_Sub3.aClass58_3566 == Class92.currentIncommingOpcode) {
                final boolean b = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)64) == 1;
                String s2;
                final String s = s2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                if (b) {
                    s2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                }
                final int unsignedByte = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)81);
                boolean b2 = false;
                if (unsignedByte <= 1) {
                    if ((Class109.aBoolean933 && !Class98_Sub10_Sub35.aBoolean5732) || Class178.aBoolean1401) {
                        b2 = true;
                    }
                    else if (unsignedByte <= 1 && Class14.method225(s2, (byte)113)) {
                        b2 = true;
                    }
                }
                if (!b2 && ~Class22.anInt216 == -1) {
                    final String method3631 = Class249.method3160(Class322.method3670(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514, (byte)72), 62);
                    if (~unsignedByte == 0xFFFFFFFD) {
                        Class137.method2276(s, 24, "<img=1>" + s, method3631, -1, null, (byte)(-67), 0, "<img=1>" + s2);
                    }
                    else if (~unsignedByte == 0xFFFFFFFE) {
                        Class137.method2276(s, 24, "<img=0>" + s, method3631, -1, null, (byte)(-82), 0, "<img=0>" + s2);
                    }
                    else {
                        Class137.method2276(s, 24, s, method3631, -1, null, (byte)(-63), 0, s2);
                    }
                }
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class284_Sub1.aClass58_5176 == Class92.currentIncommingOpcode) {
                final int int1 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt(-2);
                final int short1 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                Class98_Sub25.method1274(n - 525200580);
                za_Sub2.method1680(short1, int1, 9767);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class98_Sub46_Sub19.aClass58_6057) {
                int leShortA = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)99);
                if (leShortA == 65535) {
                    leShortA = -1;
                }
                final int int2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt(-2);
                final int int3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt2(-43);
                Class98_Sub25.method1274(n ^ 0xE0B2133C);
                Class64_Sub26.method660(int3, int2, 113, leShortA);
                final Class297 method3632 = Class98_Sub46_Sub19.aClass205_6068.method2714(leShortA, (byte)(-125));
                Class353.method3868(method3632.anInt2465, int3, (byte)(-104), method3632.anInt2416, method3632.anInt2476);
                Class323.method3675(int3, method3632.anInt2437, true, method3632.anInt2447, method3632.anInt2441);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class39.aClass58_364) {
                Class98_Sub25.method1274(-1);
                Class352.method3856((byte)1);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (PacketParser.aClass58_5466 == Class92.currentIncommingOpcode) {
                final int shortA = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(n ^ 0x1F4DECAD);
                final int byteA = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true);
                Class98_Sub25.method1274(-1);
                Class98_Sub10_Sub30.method1093(-29680, byteA, true, shortA);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class207.aClass58_1576) {
                Class98_Sub10_Sub22.method1070((byte)(-45), Class242.aClass85_1849);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class322.aClass58_2713) {
                final int int4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt2(-84);
                final int leShortA2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)(-106));
                final int byteC = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)(-126));
                Class98_Sub25.method1274(-1);
                final Class98_Sub18 class98_Sub18 = (Class98_Sub18)Class116.aClass377_964.method3990(int4, n ^ 0xE0B2133C);
                if (class98_Sub18 != null) {
                    Class196.method2666(16398, false, class98_Sub18, leShortA2 != class98_Sub18.anInt3945);
                }
                Class323.method3677(false, -126, leShortA2, int4, byteC);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class92.currentIncommingOpcode == s_Sub1.aClass58_5205) {
                final int short2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                final int int5 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt2(-89);
                Class98_Sub25.method1274(-1);
                Class225.method2848(int5, 17, short2);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class352.aClass58_2943) {
                Class98.anInt837 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true);
                Class265.anInt1983 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(-126);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class299.aClass58_2495 == Class92.currentIncommingOpcode) {
                final String string = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                final int short3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                Class137.method2276(string, 19, string, Class52.aClass280_3500.method3325(short3, 67).method1576(15281, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514), short3, null, (byte)(-93), 0, string);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class302.aClass58_2514 == Class92.currentIncommingOpcode) {
                Class98_Sub10_Sub22.method1070((byte)(-45), Class6.aClass85_89);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class98_Sub10_Sub14.aClass58_5606 == Class92.currentIncommingOpcode) {
                Class98_Sub10_Sub22.method1070((byte)(-45), Class133.aClass85_3454);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class76_Sub2.aClass58_3731 == Class92.currentIncommingOpcode) {
                Class246_Sub3_Sub4_Sub2.method3041(n - 525200579, Class98_Sub43_Sub2.aClass88_5907, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514, Class65.anInt496);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class151_Sub8.aClass58_5011 == Class92.currentIncommingOpcode) {
                final int short4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                final String string2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                Class98_Sub25.method1274(-1);
                Class94.method919(string2, short4, 52);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class213.aClass58_1609) {
                Class142.method2309(19208, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84));
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class98_Sub22_Sub2.aClass58_5793 == Class92.currentIncommingOpcode) {
                final int int6 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt2(-124);
                final int int7 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt(n - 525200581);
                Class98_Sub25.method1274(-1);
                final Class98_Sub18 class98_Sub19 = (Class98_Sub18)Class116.aClass377_964.method3990(int7, -1);
                final Class98_Sub18 class98_Sub20 = (Class98_Sub18)Class116.aClass377_964.method3990(int6, -1);
                if (class98_Sub20 != null) {
                    Class196.method2666(16398, false, class98_Sub20, class98_Sub19 == null || class98_Sub19.anInt3945 != class98_Sub20.anInt3945);
                }
                if (class98_Sub19 != null) {
                    class98_Sub19.method942(n ^ 0x1F4DEC9A);
                    Class116.aClass377_964.method3996(class98_Sub19, int6, n ^ 0xE0B2133C);
                }
                final Class293 method3633 = Class159.method2509(int7, -9820);
                if (method3633 != null) {
                    Class341.method3812(1, method3633);
                }
                final Class293 method3634 = Class159.method2509(int6, -9820);
                if (method3634 != null) {
                    Class341.method3812(n - 525200578, method3634);
                    Class63.method549(method3634, true, (byte)(-117));
                }
                if (Class15.anInt185 != -1) {
                    Class207.method2764(1, Class15.anInt185, -43);
                }
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class246_Sub4_Sub1.aClass58_6166 == Class92.currentIncommingOpcode) {
                final boolean b3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)15) == 1;
                final byte[] array = new byte[Class65.anInt496 - 1];
                Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1190(array, true, -1 + Class65.anInt496, 0);
                Class98.method943(array, b3, false);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class232.aClass58_1743 == Class92.currentIncommingOpcode) {
                while (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 < Class65.anInt496) {
                    final boolean b4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)102) == 1;
                    String string3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                    final String string4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                    final int short5 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                    final int unsignedByte2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)87);
                    String string5 = "";
                    boolean b5 = false;
                    if (short5 > 0) {
                        string5 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                        b5 = (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-100)) == 1);
                    }
                    for (int n2 = 0; ~n2 > ~Class314.anInt2692; ++n2) {
                        if (!b4) {
                            if (string3.equals(Class98_Sub25.aStringArray4026[n2])) {
                                if (short5 != Class98_Sub26.anIntArray4030[n2]) {
                                    boolean b6 = true;
                                    for (Class246_Sub4_Sub1 class246_Sub4_Sub1 = (Class246_Sub4_Sub1)Class119.aClass218_986.method2803((byte)15); class246_Sub4_Sub1 != null; class246_Sub4_Sub1 = (Class246_Sub4_Sub1)Class119.aClass218_986.method2809(false)) {
                                        if (class246_Sub4_Sub1.aString6168.equals(string3)) {
                                            if (~short5 != -1 && ~class246_Sub4_Sub1.aShort6167 == -1) {
                                                b6 = false;
                                                class246_Sub4_Sub1.method2965((byte)(-72));
                                            }
                                            else if (short5 == 0 && ~class246_Sub4_Sub1.aShort6167 != -1) {
                                                b6 = false;
                                                class246_Sub4_Sub1.method2965((byte)126);
                                            }
                                        }
                                    }
                                    if (b6) {
                                        Class119.aClass218_986.method2808(true, new Class246_Sub4_Sub1(string3, short5));
                                    }
                                    Class98_Sub26.anIntArray4030[n2] = short5;
                                }
                                Class315.aStringArray3527[n2] = string4;
                                Class98_Sub10_Sub17.aStringArray5625[n2] = string5;
                                Class69.anIntArray3222[n2] = unsignedByte2;
                                string3 = null;
                                aa_Sub3.aBooleanArray3575[n2] = b5;
                                break;
                            }
                        }
                        else if (string4.equals(Class98_Sub25.aStringArray4026[n2])) {
                            Class98_Sub25.aStringArray4026[n2] = string3;
                            string3 = null;
                            Class315.aStringArray3527[n2] = string4;
                            break;
                        }
                    }
                    if (string3 != null && ~Class314.anInt2692 > -201) {
                        Class98_Sub25.aStringArray4026[Class314.anInt2692] = string3;
                        Class315.aStringArray3527[Class314.anInt2692] = string4;
                        Class98_Sub26.anIntArray4030[Class314.anInt2692] = short5;
                        Class98_Sub10_Sub17.aStringArray5625[Class314.anInt2692] = string5;
                        Class69.anIntArray3222[Class314.anInt2692] = unsignedByte2;
                        aa_Sub3.aBooleanArray3575[Class314.anInt2692] = b5;
                        ++Class314.anInt2692;
                    }
                }
                Class363.anInt3099 = Class24.anInt242;
                Class98_Sub28.anInt4078 = 2;
                int anInt2692 = Class314.anInt2692;
                while (~anInt2692 < -1) {
                    boolean b7 = true;
                    --anInt2692;
                    for (int n3 = 0; ~anInt2692 < ~n3; ++n3) {
                        if ((Class98_Sub26.anIntArray4030[n3] != Class98_Sub46_Sub10.aClass354_6011.anInt3011 && ~Class98_Sub26.anIntArray4030[1 + n3] == ~Class98_Sub46_Sub10.aClass354_6011.anInt3011) || (Class98_Sub26.anIntArray4030[n3] == 0 && Class98_Sub26.anIntArray4030[1 + n3] != 0)) {
                            final int n4 = Class98_Sub26.anIntArray4030[n3];
                            Class98_Sub26.anIntArray4030[n3] = Class98_Sub26.anIntArray4030[n3 + 1];
                            Class98_Sub26.anIntArray4030[1 + n3] = n4;
                            final String s3 = Class98_Sub10_Sub17.aStringArray5625[n3];
                            Class98_Sub10_Sub17.aStringArray5625[n3] = Class98_Sub10_Sub17.aStringArray5625[1 + n3];
                            Class98_Sub10_Sub17.aStringArray5625[1 + n3] = s3;
                            final String s4 = Class98_Sub25.aStringArray4026[n3];
                            Class98_Sub25.aStringArray4026[n3] = Class98_Sub25.aStringArray4026[n3 + 1];
                            Class98_Sub25.aStringArray4026[n3 + 1] = s4;
                            final String s5 = Class315.aStringArray3527[n3];
                            Class315.aStringArray3527[n3] = Class315.aStringArray3527[1 + n3];
                            Class315.aStringArray3527[n3 + 1] = s5;
                            final int n5 = Class69.anIntArray3222[n3];
                            Class69.anIntArray3222[n3] = Class69.anIntArray3222[1 + n3];
                            Class69.anIntArray3222[n3 + 1] = n5;
                            final boolean b8 = aa_Sub3.aBooleanArray3575[n3];
                            aa_Sub3.aBooleanArray3575[n3] = aa_Sub3.aBooleanArray3575[1 + n3];
                            b7 = false;
                            aa_Sub3.aBooleanArray3575[1 + n3] = b8;
                        }
                    }
                    if (b7) {
                        break;
                    }
                }
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class188.aClass58_1452 == Class92.currentIncommingOpcode) {
                final int leShortA3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)(-114));
                final int byteA2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true);
                final int byteS = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(108);
                final int byteS2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(-110);
                final int byteC2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)122);
                Class98_Sub25.method1274(-1);
                Class217.aBooleanArray3410[byteC2] = true;
                aa_Sub3.anIntArray3571[byteC2] = byteA2;
                Class98_Sub10_Sub13.anIntArray5603[byteC2] = byteS2;
                Class98_Sub32.anIntArray4109[byteC2] = byteS;
                Class212.anIntArray1597[byteC2] = leShortA3;
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class246_Sub4_Sub2.aClass58_6182 == Class92.currentIncommingOpcode) {
                Class368.anInt3124 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)48);
                Class92.currentIncommingOpcode = null;
                Class98_Sub36.anInt4161 = Class24.anInt242;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class151_Sub6.aClass58_4999) {
                Class98_Sub41.method1475(119);
                Class92.currentIncommingOpcode = null;
                return false;
            }
            if (Class92.currentIncommingOpcode == Class98_Sub41.aClass58_4199) {
                Class98_Sub10_Sub22.method1070((byte)(-45), Class373_Sub3.aClass85_5474);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class40.aClass58_369) {
                final Class98_Sub22_Sub1 aClass98_Sub22_Sub1_5514 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514;
                aClass98_Sub22_Sub1_5514.anInt3991 += 28;
                if (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1210(-114)) {
                    Class76_Sub7.method762(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 - 28, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514, true);
                }
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class77.aClass58_592) {
                final int int8 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt(-2);
                Class98_Sub25.method1274(n - 525200580);
                if (int8 != -1) {
                    final int n6 = (0xFFFEABC & int8) >> 884258670;
                    final int n7 = int8 & 0x3FFF;
                    int anInt2693 = n6 - Class272.anInt2038;
                    int anInt2694 = n7 - aa_Sub2.anInt3562;
                    if (~anInt2693 > -1) {
                        anInt2693 = 0;
                    }
                    else if (Class165.anInt1276 <= anInt2693) {
                        anInt2693 = Class165.anInt1276;
                    }
                    Class116.anInt967 = 256 + (anInt2693 << -1786298711);
                    if (~anInt2694 <= -1) {
                        if (Class98_Sub10_Sub7.anInt5572 <= anInt2694) {
                            anInt2694 = Class98_Sub10_Sub7.anInt5572;
                        }
                    }
                    else {
                        anInt2694 = 0;
                    }
                    Class64_Sub26.anInt3712 = 256 + (anInt2694 << 1215708553);
                }
                else {
                    Class64_Sub26.anInt3712 = -1;
                    Class116.anInt967 = -1;
                }
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class315.aClass58_3533) {
                final int byteA3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true);
                final int byteS3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(n - 525200499);
                final int unsignedByte3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)127);
                final int byteC3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)(-120));
                final int n8 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)107) << -1408209406;
                Class98_Sub25.method1274(n - 525200580);
                Class98_Sub46_Sub13.method1592(-25686, byteC3, byteS3, n8, byteA3, unsignedByte3);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class358.aClass58_3029 == Class92.currentIncommingOpcode) {
                Class98_Sub10_Sub22.method1070((byte)(-45), Class64_Sub10.aClass85_3667);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class180.aClass58_3398 == Class92.currentIncommingOpcode) {
                final int int9 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt1(false);
                final int leShortA4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)91);
                final int leShortA5 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)(-81));
                int short6 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                final int byteC4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)(-124));
                final boolean b9 = ~(0x80 & byteC4) != -1;
                final int n9 = byteC4 & 0x7;
                int n10 = 0xF & byteC4 >> 525200579;
                if (n10 == 15) {
                    n10 = -1;
                }
                if (int9 >> -1165516802 != 0) {
                    final int n11 = int9 >> 1762702268 & 0x3;
                    final int n12 = ((int9 & 0xFFFDB84) >> -1260772882) - Class272.anInt2038;
                    final int n13 = -aa_Sub2.anInt3562 + (0x3FFF & int9);
                    if (n12 >= 0 && n13 >= 0 && Class165.anInt1276 > n12 && ~Class98_Sub10_Sub7.anInt5572 < ~n13) {
                        final int n14 = 512 * n12 + 256;
                        final int n15 = 256 + n13 * 512;
                        int n16 = n11;
                        if (~n16 > -4 && Class1.method162(n13, (byte)(-118), n12)) {
                            ++n16;
                        }
                        Class98_Sub10_Sub11.aClass148_5596.method2419(new Class98_Sub46_Sub3(new Class246_Sub3_Sub4_Sub3(short6, leShortA5, Class215.anInt1614, n11, n16, n14, -leShortA4 + Class98_Sub46_Sub2_Sub2.method1538(n11, n15, n14, 24111), n15, n12, n12, n13, n13, n9)), -20911);
                    }
                }
                else if (~(int9 >> -602350499) == -1) {
                    if (~(int9 >> 1424175516) != -1) {
                        final int n17 = int9 & 0xFFFF;
                        Class246_Sub3_Sub4_Sub2_Sub2 aClass246_Sub3_Sub4_Sub2_Sub2_660;
                        if (n17 == za_Sub2.anInt6080) {
                            aClass246_Sub3_Sub4_Sub2_Sub2_660 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660;
                        }
                        else {
                            aClass246_Sub3_Sub4_Sub2_Sub2_660 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[n17];
                        }
                        if (aClass246_Sub3_Sub4_Sub2_Sub2_660 != null) {
                            if (short6 == 65535) {
                                short6 = -1;
                            }
                            boolean b10 = true;
                            final int n18 = b9 ? aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6365 : aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6379;
                            if (short6 != -1 && ~n18 != 0x0) {
                                if (~short6 != ~n18) {
                                    final Class107 method3635 = Class196.aClass304_1509.method3564(2, short6);
                                    final Class107 method3636 = Class196.aClass304_1509.method3564(2, n18);
                                    if (~method3635.anInt910 != 0x0 && method3636.anInt910 != -1 && ~Class151_Sub7.aClass183_5001.method2623(method3635.anInt910, 16383).anInt829 > ~Class151_Sub7.aClass183_5001.method2623(method3636.anInt910, 16383).anInt829) {
                                        b10 = false;
                                    }
                                }
                                else {
                                    final Class107 method3637 = Class196.aClass304_1509.method3564(2, short6);
                                    if (method3637.aBoolean909 && ~method3637.anInt910 != 0x0) {
                                        final int anInt2695 = Class151_Sub7.aClass183_5001.method2623(method3637.anInt910, n - 525184196).anInt819;
                                        if (~anInt2695 == -1 || ~anInt2695 == 0xFFFFFFFD) {
                                            b10 = false;
                                        }
                                        else if (~anInt2695 == 0xFFFFFFFE) {
                                            b10 = true;
                                        }
                                    }
                                }
                            }
                            if (b10) {
                                if (!b9) {
                                    aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6376 = 0;
                                    aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6396 = 0;
                                    aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6367 = 1;
                                    aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6410 = n10;
                                    aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6379 = short6;
                                    aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6382 = leShortA4;
                                    aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6389 = n9;
                                    aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6391 = leShortA5 + Class215.anInt1614;
                                    if (~aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6391 < ~Class215.anInt1614) {
                                        aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6376 = -1;
                                    }
                                    if (~aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6379 == 0xFFFF0000) {
                                        aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6379 = -1;
                                    }
                                    if (aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6379 != -1 && ~aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6391 == ~Class215.anInt1614) {
                                        final int anInt2696 = Class196.aClass304_1509.method3564(n - 525200577, aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6379).anInt910;
                                        if (anInt2696 != -1) {
                                            final Class97 method3638 = Class151_Sub7.aClass183_5001.method2623(anInt2696, 16383);
                                            if (method3638 != null && method3638.anIntArray818 != null && !aClass246_Sub3_Sub4_Sub2_Sub2_660.aBoolean6371) {
                                                Class349.method3840((byte)(-128), aClass246_Sub3_Sub4_Sub2_Sub2_660, 0, method3638);
                                            }
                                        }
                                    }
                                }
                                else {
                                    aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6427 = 0;
                                    aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6426 = leShortA5 + Class215.anInt1614;
                                    aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6353 = n10;
                                    aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6365 = short6;
                                    aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6360 = n9;
                                    aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6428 = 0;
                                    aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6363 = leShortA4;
                                    aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6421 = 1;
                                    if (~Class215.anInt1614 > ~aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6426) {
                                        aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6428 = -1;
                                    }
                                    if (~aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6365 == 0xFFFF0000) {
                                        aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6365 = -1;
                                    }
                                    if (aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6365 != -1 && aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6426 == Class215.anInt1614) {
                                        final int anInt2697 = Class196.aClass304_1509.method3564(2, aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6365).anInt910;
                                        if (anInt2697 != -1) {
                                            final Class97 method3639 = Class151_Sub7.aClass183_5001.method2623(anInt2697, n ^ 0x1F4DD33C);
                                            if (method3639 != null && method3639.anIntArray818 != null && !aClass246_Sub3_Sub4_Sub2_Sub2_660.aBoolean6371) {
                                                Class349.method3840((byte)(-128), aClass246_Sub3_Sub4_Sub2_Sub2_660, 0, method3639);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else {
                    final Class98_Sub39 class98_Sub21 = (Class98_Sub39)Class260.aClass377_3254.method3990(int9 & 0xFFFF, -1);
                    if (class98_Sub21 != null) {
                        final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = class98_Sub21.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                        if (short6 == 65535) {
                            short6 = -1;
                        }
                        boolean b11 = true;
                        final int n19 = b9 ? aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6365 : aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6379;
                        if (~short6 != 0x0 && ~n19 != 0x0) {
                            if (~short6 == ~n19) {
                                final Class107 method3640 = Class196.aClass304_1509.method3564(n ^ 0x1F4DECC1, short6);
                                if (method3640.aBoolean909 && ~method3640.anInt910 != 0x0) {
                                    final int anInt2698 = Class151_Sub7.aClass183_5001.method2623(method3640.anInt910, 16383).anInt819;
                                    if (anInt2698 != 0 && anInt2698 != 2) {
                                        if (anInt2698 == 1) {
                                            b11 = true;
                                        }
                                    }
                                    else {
                                        b11 = false;
                                    }
                                }
                            }
                            else {
                                final Class107 method3641 = Class196.aClass304_1509.method3564(2, short6);
                                final Class107 method3642 = Class196.aClass304_1509.method3564(2, n19);
                                if (~method3641.anInt910 != 0x0 && ~method3642.anInt910 != 0x0 && ~Class151_Sub7.aClass183_5001.method2623(method3642.anInt910, 16383).anInt829 < ~Class151_Sub7.aClass183_5001.method2623(method3641.anInt910, 16383).anInt829) {
                                    b11 = false;
                                }
                            }
                        }
                        if (b11) {
                            if (b9) {
                                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6426 = leShortA5 + Class215.anInt1614;
                                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6353 = n10;
                                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6427 = 0;
                                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6360 = n9;
                                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6365 = short6;
                                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6428 = 0;
                                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6421 = 1;
                                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6363 = leShortA4;
                                if (Class215.anInt1614 < aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6426) {
                                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6428 = -1;
                                }
                                if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6365 != -1 && Class215.anInt1614 == aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6426) {
                                    final int anInt2699 = Class196.aClass304_1509.method3564(2, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6365).anInt910;
                                    if (anInt2699 != -1) {
                                        final Class97 method3643 = Class151_Sub7.aClass183_5001.method2623(anInt2699, 16383);
                                        if (method3643 != null && method3643.anIntArray818 != null && !aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6371) {
                                            Class349.method3840((byte)(-128), aClass246_Sub3_Sub4_Sub2_Sub1_4187, 0, method3643);
                                        }
                                    }
                                }
                            }
                            else {
                                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6367 = 1;
                                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6389 = n9;
                                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6376 = 0;
                                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6396 = 0;
                                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6379 = short6;
                                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6410 = n10;
                                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6391 = Class215.anInt1614 - -leShortA5;
                                aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6382 = leShortA4;
                                if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6391 > Class215.anInt1614) {
                                    aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6376 = -1;
                                }
                                if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6379 != -1 && ~aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6391 == ~Class215.anInt1614) {
                                    final int anInt2700 = Class196.aClass304_1509.method3564(2, aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6379).anInt910;
                                    if (~anInt2700 != 0x0) {
                                        final Class97 method3644 = Class151_Sub7.aClass183_5001.method2623(anInt2700, 16383);
                                        if (method3644 != null && method3644.anIntArray818 != null && !aClass246_Sub3_Sub4_Sub2_Sub1_4187.aBoolean6371) {
                                            Class349.method3840((byte)(-128), aClass246_Sub3_Sub4_Sub2_Sub1_4187, 0, method3644);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class98_Sub12.aClass58_3877) {
                Class86.aClass350_649 = Class98_Sub10_Sub8.method1029((byte)(-107), Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-108)));
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class55.aClass58_433 == Class92.currentIncommingOpcode) {
                Class98_Sub10_Sub22.method1070((byte)(-45), Class35.aClass85_332);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class149.aClass58_1207) {
                final int smart = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSmart(1689622712);
                final int int10 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt(-2);
                final int unsignedByte4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)122);
                String string7;
                String string6 = string7 = "";
                if (~(unsignedByte4 & 0x1) != -1) {
                    string6 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                    if (~(unsignedByte4 & 0x2) != -1) {
                        string7 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                    }
                    else {
                        string7 = string6;
                    }
                }
                final String string8 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                if (smart != 99) {
                    if (!string7.equals("") && Class14.method225(string7, (byte)125)) {
                        Class92.currentIncommingOpcode = null;
                        return true;
                    }
                    Class98_Sub45.method1521((byte)(-113), smart, string8, int10, string7, string6, string6);
                }
                else {
                    Class98_Sub46.method1525(string8, -92);
                }
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class348.aClass58_2912 == Class92.currentIncommingOpcode) {
                Class98_Sub10_Sub22.method1070((byte)(-45), Class98_Sub23.aClass85_4007);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class98_Sub10_Sub15.aClass58_5615) {
                final String string9 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                String string10;
                if (~Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-103)) == 0xFFFFFFFE) {
                    string10 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                }
                else {
                    string10 = string9;
                }
                final int short7 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                final byte signedByte = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSignedByte((byte)(-19));
                boolean b12 = false;
                if (signedByte == -128) {
                    b12 = true;
                }
                if (!b12) {
                    final String string11 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                    final Class147 class147 = new Class147();
                    class147.aString1191 = string9;
                    class147.aString1186 = string10;
                    class147.aString1185 = Class353.method3867(-1, class147.aString1186);
                    class147.aByte1187 = signedByte;
                    class147.anInt1188 = short7;
                    class147.aString1190 = string11;
                    int n20;
                    for (n20 = -1 + Class32.anInt308; ~n20 <= -1; --n20) {
                        final int compareTo = Class374.aClass147Array3157[n20].aString1185.compareTo(class147.aString1185);
                        if (~compareTo == -1) {
                            Class374.aClass147Array3157[n20].anInt1188 = short7;
                            Class374.aClass147Array3157[n20].aByte1187 = signedByte;
                            Class374.aClass147Array3157[n20].aString1190 = string11;
                            if (string10.equals(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aString6537)) {
                                Class111.aByte947 = signedByte;
                            }
                            Class64_Sub22.anInt3705 = Class24.anInt242;
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (compareTo < 0) {
                            break;
                        }
                    }
                    if (~Class32.anInt308 <= ~Class374.aClass147Array3157.length) {
                        Class92.currentIncommingOpcode = null;
                        return true;
                    }
                    for (int n21 = Class32.anInt308 - 1; ~n20 > ~n21; --n21) {
                        Class374.aClass147Array3157[n21 + 1] = Class374.aClass147Array3157[n21];
                    }
                    if (Class32.anInt308 == 0) {
                        Class374.aClass147Array3157 = new Class147[100];
                    }
                    Class374.aClass147Array3157[n20 + 1] = class147;
                    ++Class32.anInt308;
                    if (string10.equals(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aString6537)) {
                        Class111.aByte947 = signedByte;
                    }
                }
                else {
                    if (~Class32.anInt308 == -1) {
                        Class92.currentIncommingOpcode = null;
                        return true;
                    }
                    int n22;
                    for (n22 = 0; ~n22 > ~Class32.anInt308 && (!Class374.aClass147Array3157[n22].aString1186.equals(string10) || Class374.aClass147Array3157[n22].anInt1188 != short7); ++n22) {}
                    if (Class32.anInt308 > n22) {
                        while (~n22 > ~(Class32.anInt308 - 1)) {
                            Class374.aClass147Array3157[n22] = Class374.aClass147Array3157[n22 + 1];
                            ++n22;
                        }
                        --Class32.anInt308;
                        Class374.aClass147Array3157[Class32.anInt308] = null;
                    }
                }
                Class92.currentIncommingOpcode = null;
                Class64_Sub22.anInt3705 = Class24.anInt242;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class98_Sub32_Sub1.aClass58_5883) {
                final int int11 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt2(-91);
                final int byteC5 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)59);
                Class98_Sub25.method1274(-1);
                Class305_Sub1.method3587(byteC5, -39, int11);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class98_Sub18.aClass58_3946) {
                final String string12 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                final int leShortA6 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)101);
                Class98_Sub25.method1274(n - 525200580);
                Class94.method919(string12, leShortA6, 68);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (InputStream_Sub1.aClass58_28 == Class92.currentIncommingOpcode) {
                int short8 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                if (~short8 == 0xFFFF0000) {
                    short8 = -1;
                }
                Class98_Sub10_Sub9.method1036(n ^ 0x94491BCF, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-118)), Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-123)), short8, true, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127), 256);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class69_Sub2.aClass58_5333) {
                final int leShortA7 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)(-31));
                final int intReverse = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readIntReverse(true);
                Class98_Sub25.method1274(-1);
                Class64_Sub8.method585(leShortA7, (byte)(-85), intReverse);
                Class92.currentIncommingOpcode = null;
                return true;
            }
            if (Class92.currentIncommingOpcode == Class93_Sub1.aClass58_5482) {
                final int short9 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                Class246_Sub3_Sub4_Sub2_Sub2 aClass246_Sub3_Sub4_Sub2_Sub2_661;
                if (short9 != za_Sub2.anInt6080) {
                    aClass246_Sub3_Sub4_Sub2_Sub2_661 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[short9];
                }
                else {
                    aClass246_Sub3_Sub4_Sub2_Sub2_661 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660;
                }
                if (aClass246_Sub3_Sub4_Sub2_Sub2_661 == null) {
                    Class92.currentIncommingOpcode = null;
                    return true;
                }
                int short10 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                final int unsignedByte5 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-112));
                final boolean b13 = ~(0x8000 & short10) != -1;
                if (aClass246_Sub3_Sub4_Sub2_Sub2_661.aString6537 != null && aClass246_Sub3_Sub4_Sub2_Sub2_661.aClass313_6518 != null) {
                    boolean b14 = false;
                    if (~unsignedByte5 >= -2) {
                        if (!b13 && ((Class109.aBoolean933 && !Class98_Sub10_Sub35.aBoolean5732) || Class178.aBoolean1401)) {
                            b14 = true;
                        }
                        else if (Class14.method225(aClass246_Sub3_Sub4_Sub2_Sub2_661.aString6537, (byte)117)) {
                            b14 = true;
                        }
                    }
                    if (!b14 && Class22.anInt216 == 0) {
                        int anInt2701 = -1;
                        String s6;
                        if (!b13) {
                            s6 = Class249.method3160(Class322.method3670(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514, (byte)72), 62);
                        }
                        else {
                            short10 &= 0x7FFF;
                            final Class300 method3645 = Class42.method376((byte)(-12), Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514);
                            anInt2701 = method3645.anInt2496;
                            s6 = method3645.aClass98_Sub46_Sub11_2498.method1576(15281, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514);
                        }
                        aClass246_Sub3_Sub4_Sub2_Sub2_661.aString6374 = s6.trim();
                        aClass246_Sub3_Sub4_Sub2_Sub2_661.anInt6398 = short10 >> -1332042328;
                        aClass246_Sub3_Sub4_Sub2_Sub2_661.anInt6402 = (0xFF & short10);
                        aClass246_Sub3_Sub4_Sub2_Sub2_661.anInt6384 = 150;
                        int n23;
                        if (~unsignedByte5 == 0xFFFFFFFE || ~unsignedByte5 == 0xFFFFFFFD) {
                            n23 = (b13 ? 17 : 1);
                        }
                        else {
                            n23 = (b13 ? 17 : 2);
                        }
                        if (~unsignedByte5 == 0xFFFFFFFD) {
                            Class137.method2276(aClass246_Sub3_Sub4_Sub2_Sub2_661.aString6536, n23, "<img=1>" + aClass246_Sub3_Sub4_Sub2_Sub2_661.method3063(0, true), s6, anInt2701, null, (byte)(-107), 0, "<img=1>" + aClass246_Sub3_Sub4_Sub2_Sub2_661.method3059(-1, false));
                        }
                        else if (unsignedByte5 == 1) {
                            Class137.method2276(aClass246_Sub3_Sub4_Sub2_Sub2_661.aString6536, n23, "<img=0>" + aClass246_Sub3_Sub4_Sub2_Sub2_661.method3063(0, true), s6, anInt2701, null, (byte)(-84), 0, "<img=0>" + aClass246_Sub3_Sub4_Sub2_Sub2_661.method3059(-1, false));
                        }
                        else {
                            Class137.method2276(aClass246_Sub3_Sub4_Sub2_Sub2_661.aString6536, n23, aClass246_Sub3_Sub4_Sub2_Sub2_661.method3063(n ^ 0x1F4DECC3, true), s6, anInt2701, null, (byte)(-81), 0, aClass246_Sub3_Sub4_Sub2_Sub2_661.method3059(-1, false));
                        }
                    }
                }
                Class92.currentIncommingOpcode = null;
                return true;
            }
            else {
                if (Class246_Sub3_Sub4_Sub5.aClass58_6264 == Class92.currentIncommingOpcode) {
                    int short11 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                    if (~short11 == 0xFFFF0000) {
                        short11 = -1;
                    }
                    final int intReverse2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readIntReverse(true);
                    Class98_Sub25.method1274(n - 525200580);
                    Class98_Sub46_Sub10.method1572(intReverse2, 30585, short11);
                    Class92.currentIncommingOpcode = null;
                    return true;
                }
                if (Class92.currentIncommingOpcode == Class98_Sub10_Sub20.aClass58_5638) {
                    final int byteA4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true);
                    final byte method3646 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1187((byte)(-112));
                    Class98_Sub25.method1274(-1);
                    OutputStream_Sub1.method130(method3646, 65280, byteA4);
                    Class92.currentIncommingOpcode = null;
                    return true;
                }
                if (Class92.currentIncommingOpcode == Class151_Sub5.aClass58_4992) {
                    Class98_Sub10_Sub22.method1070((byte)(-45), Class98_Sub11.aClass85_3868);
                    Class92.currentIncommingOpcode = null;
                    return true;
                }
                if (Class92.currentIncommingOpcode == Class246_Sub3_Sub2.aClass58_6151) {
                    final int short12 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)112);
                    Class98_Sub25.method1274(-1);
                    Class98_Sub42.method1476(256, short12);
                    Class92.currentIncommingOpcode = null;
                    return true;
                }
                if (Class92.currentIncommingOpcode == Class98_Sub10_Sub20.aClass58_5635) {
                    final int short13 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)(-85));
                    final int leShortA8 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)92);
                    Class98_Sub25.method1274(-1);
                    Class308.method3608(leShortA8, 0, n - 525200495, short13);
                    Class92.currentIncommingOpcode = null;
                    return true;
                }
                if (Class339.aClass58_2844 == Class92.currentIncommingOpcode) {
                    final int[] array2 = new int[4];
                    for (int n24 = 0; ~n24 > -5; ++n24) {
                        array2[n24] = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)(-104));
                    }
                    final int short14 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                    final int byteS4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(n ^ 0xE0B2134A);
                    final Class98_Sub39 class98_Sub22 = (Class98_Sub39)Class260.aClass377_3254.method3990(short14, -1);
                    if (class98_Sub22 != null) {
                        Class98_Sub43.method1483(byteS4, class98_Sub22.aClass246_Sub3_Sub4_Sub2_Sub1_4187, n ^ 0x1F4DECC2, array2);
                    }
                    Class92.currentIncommingOpcode = null;
                    return true;
                }
                if (Class246_Sub3.aClass58_5086 == Class92.currentIncommingOpcode) {
                    final int unsignedByte6 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)37);
                    if (~Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)75) == -1) {
                        Class98_Sub10_Sub24.aClass101Array5666[unsignedByte6] = new Class101();
                    }
                    else {
                        final Class98_Sub22_Sub1 aClass98_Sub22_Sub1_5515 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514;
                        --aClass98_Sub22_Sub1_5515.anInt3991;
                        Class98_Sub10_Sub24.aClass101Array5666[unsignedByte6] = new Class101(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514);
                    }
                    Class92.currentIncommingOpcode = null;
                    Class64_Sub11.anInt3668 = Class24.anInt242;
                    return true;
                }
                if (Class92.currentIncommingOpcode == Class15.aClass58_184) {
                    final int byteC6 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)(-112));
                    final int n25 = byteC6 >> 41032322;
                    final int n26 = byteC6 & 0x3;
                    final int n27 = Class64_Sub17.anIntArray3685[n25];
                    int leShortA9 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)(-40));
                    if (leShortA9 == 65535) {
                        leShortA9 = -1;
                    }
                    final int int12 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt2(n - 525200657);
                    Class283.method3351(n25, n26, true, ((0xFFFF619 & int12) >> 1305310190) - Class272.anInt2038, (int12 & 0x3FFF) - aa_Sub2.anInt3562, 0x3 & int12 >> 87030492, leShortA9, n27);
                    Class92.currentIncommingOpcode = null;
                    return true;
                }
                if (Class65.aClass58_499 == Class92.currentIncommingOpcode) {
                    Class98_Sub10_Sub22.method1070((byte)(-45), Class60.aClass85_471);
                    Class92.currentIncommingOpcode = null;
                    return true;
                }
                if (Class48_Sub1_Sub2.aClass58_5520 == Class92.currentIncommingOpcode) {
                    Class75.aClass140_584.method2286(-7469, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1234(128), Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(50));
                    Class92.currentIncommingOpcode = null;
                    return true;
                }
                if (Class92.currentIncommingOpcode == Class246_Sub3_Sub4_Sub3.aClass58_6457) {
                    if (Class98_Sub18.aFrame3950 != null) {
                        Class98_Sub16.method1150(Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub27_4052.method666((byte)120), -1, 3, -1, false);
                    }
                    final byte[] array3 = new byte[Class65.anInt496];
                    Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1251(0, Class65.anInt496, array3, true);
                    Class315.method3647(true, ~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub8_4042.method583((byte)127) == 0xFFFFFFFE, Class98_Sub46_Sub6.method1546(Class65.anInt496, 0, (byte)(-84), array3), true, Class98_Sub43_Sub2.aClass88_5907);
                    Class92.currentIncommingOpcode = null;
                    return true;
                }
                if (Class98_Sub46_Sub8.aClass58_5996 == Class92.currentIncommingOpcode) {
                    final boolean b15 = ~Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)65) == 0xFFFFFFFE;
                    String s8;
                    final String s7 = s8 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                    if (b15) {
                        s8 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                    }
                    final long n28 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                    final long n29 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1186(n ^ 0xE0B21340);
                    final int unsignedByte7 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)106);
                    final long n30 = (n28 << -1427120736) + n29;
                    boolean b16 = false;
                    while (true) {
                        for (int n31 = 0; ~n31 > -101; ++n31) {
                            if (n30 == Class94.aLongArray794[n31]) {
                                b16 = true;
                                if (!b16 && Class22.anInt216 == 0) {
                                    Class94.aLongArray794[Class147.anInt1193] = n30;
                                    Class147.anInt1193 = (1 + Class147.anInt1193) % 100;
                                    final String method3647 = Class249.method3160(Class322.method3670(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514, (byte)72), 62);
                                    if (~unsignedByte7 != 0xFFFFFFFD) {
                                        if (~unsignedByte7 != 0xFFFFFFFE) {
                                            Class137.method2276(s7, 3, s7, method3647, -1, null, (byte)(-108), 0, s8);
                                        }
                                        else {
                                            Class137.method2276(s7, 7, "<img=0>" + s7, method3647, -1, null, (byte)(-120), 0, "<img=0>" + s8);
                                        }
                                    }
                                    else {
                                        Class137.method2276(s7, 7, "<img=1>" + s7, method3647, -1, null, (byte)(-93), 0, "<img=1>" + s8);
                                    }
                                }
                                Class92.currentIncommingOpcode = null;
                                return true;
                            }
                        }
                        if (unsignedByte7 > 1) {
                            continue;
                        }
                        if ((Class109.aBoolean933 && !Class98_Sub10_Sub35.aBoolean5732) || Class178.aBoolean1401) {
                            b16 = true;
                            continue;
                        }
                        if (Class14.method225(s8, (byte)126)) {
                            b16 = true;
                        }
                        continue;
                    }
                }
                else {
                    if (Class92.currentIncommingOpcode == Exception_Sub1.aClass58_43) {
                        Class273.method3280((byte)(-101));
                        Class92.currentIncommingOpcode = null;
                        return true;
                    }
                    if (Class372.aClass58_3147 == Class92.currentIncommingOpcode) {
                        final int short15 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)50);
                        final byte signedByte2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSignedByte((byte)(-19));
                        Class98_Sub25.method1274(-1);
                        Class116.method2161(signedByte2, short15, (byte)(-120));
                        Class92.currentIncommingOpcode = null;
                        return true;
                    }
                    if (Class92.currentIncommingOpcode == Class224_Sub1.aClass58_5032) {
                        final String string13 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                        final int intReverse3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readIntReverse(true);
                        Class98_Sub25.method1274(n ^ 0xE0B2133C);
                        ha_Sub1.method1895(intReverse3, (byte)(-52), string13);
                        Class92.currentIncommingOpcode = null;
                        return true;
                    }
                    if (Class92.currentIncommingOpcode == Class16.aClass58_191) {
                        Class98_Sub10_Sub1.method1003(false, aa_Sub3.aBoolean3569);
                        Class92.currentIncommingOpcode = null;
                        return false;
                    }
                    if (Class92.currentIncommingOpcode == Class277.aClass58_2052) {
                        final int short16 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)(-79));
                        final int leShortA10 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)(-72));
                        final int int13 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt1(false);
                        Class98_Sub25.method1274(-1);
                        Class98_Sub10_Sub33.method1099(int13, leShortA10 + (short16 << -528695792), (byte)111);
                        Class92.currentIncommingOpcode = null;
                        return true;
                    }
                    if (Class283.aClass58_2143 == Class92.currentIncommingOpcode) {
                        final int intReverse4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readIntReverse(true);
                        final int leShortA11 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)(-48));
                        final int intReverse5 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readIntReverse(true);
                        Class98_Sub25.method1274(-1);
                        Class98_Sub19.method1164(leShortA11, intReverse4, 5, 4, intReverse5);
                        Class92.currentIncommingOpcode = null;
                        return true;
                    }
                    if (Class369.aClass58_3132 == Class92.currentIncommingOpcode) {
                        Class248.anInt1897 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)15);
                        for (int n32 = 0; ~Class248.anInt1897 < ~n32; ++n32) {
                            Class246_Sub4_Sub1.aStringArray6171[n32] = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                            Class255.aStringArray3209[n32] = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                            if (Class255.aStringArray3209[n32].equals("")) {
                                Class255.aStringArray3209[n32] = Class246_Sub4_Sub1.aStringArray6171[n32];
                            }
                            Class98_Sub45.aStringArray4255[n32] = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                            Class110.aStringArray945[n32] = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                            if (Class110.aStringArray945[n32].equals("")) {
                                Class110.aStringArray945[n32] = Class98_Sub45.aStringArray4255[n32];
                            }
                            Class98_Sub10_Sub38.aBooleanArray5759[n32] = false;
                        }
                        Class363.anInt3099 = Class24.anInt242;
                        Class92.currentIncommingOpcode = null;
                        return true;
                    }
                    if (Class92.currentIncommingOpcode == Class150.aClass58_1212) {
                        Class98_Sub36.method1459(-1048016408);
                        Class92.currentIncommingOpcode = null;
                        return false;
                    }
                    if (Class309.aClass58_2651 == Class92.currentIncommingOpcode) {
                        Class75.aClass140_584.method2292(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(75), Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)(-107)), -32727);
                        Class92.currentIncommingOpcode = null;
                        return true;
                    }
                    if (Class73.aClass58_3482 == Class92.currentIncommingOpcode) {
                        int shortA2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(n ^ 0x1F4DEC82);
                        if (shortA2 == 65535) {
                            shortA2 = -1;
                        }
                        final int byteS5 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(-83);
                        String string14 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                        final int byteS6 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(126);
                        if (~byteS6 <= -2 && ~byteS6 >= -9) {
                            if (string14.equalsIgnoreCase("null")) {
                                string14 = null;
                            }
                            Class269.aStringArray2021[-1 + byteS6] = string14;
                            Class151_Sub9.anIntArray5019[byteS6 - 1] = shortA2;
                            Class146_Sub2.aBooleanArray4840[-1 + byteS6] = (~byteS5 == -1);
                        }
                        Class92.currentIncommingOpcode = null;
                        return true;
                    }
                    if (Class370.aClass58_3134 == Class92.currentIncommingOpcode) {
                        final int unsignedByte8 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)51);
                        final int n33 = unsignedByte8 >> -2017319579;
                        final int anInt2702 = unsignedByte8 & 0x1F;
                        if (anInt2702 == 0) {
                            Class104.aClass36Array903[n33] = null;
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        final Class36 class148 = new Class36();
                        class148.anInt346 = anInt2702;
                        class148.anInt341 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)90);
                        if (class148.anInt341 >= 0 && class148.anInt341 < Class306.aClass332Array2557.length) {
                            if (~class148.anInt346 != 0xFFFFFFFE && class148.anInt346 != 10) {
                                if (~class148.anInt346 <= -3 && class148.anInt346 <= 6) {
                                    if (class148.anInt346 == 2) {
                                        class148.anInt338 = 256;
                                        class148.anInt347 = 256;
                                    }
                                    if (~class148.anInt346 == 0xFFFFFFFC) {
                                        class148.anInt338 = 0;
                                        class148.anInt347 = 256;
                                    }
                                    if (~class148.anInt346 == 0xFFFFFFFB) {
                                        class148.anInt338 = 512;
                                        class148.anInt347 = 256;
                                    }
                                    if (~class148.anInt346 == 0xFFFFFFFA) {
                                        class148.anInt338 = 256;
                                        class148.anInt347 = 0;
                                    }
                                    if (~class148.anInt346 == 0xFFFFFFF9) {
                                        class148.anInt347 = 512;
                                        class148.anInt338 = 256;
                                    }
                                    class148.anInt346 = 2;
                                    class148.anInt342 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-112));
                                    final Class36 class149 = class148;
                                    class149.anInt338 += Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127) - Class272.anInt2038 << -1727288087;
                                    final Class36 class150 = class148;
                                    class150.anInt347 += Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127) + -aa_Sub2.anInt3562 << 357961001;
                                    class148.anInt343 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)2) << -1401715486;
                                    class148.anInt340 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                                }
                            }
                            else {
                                class148.anInt345 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                                final Class98_Sub22_Sub1 aClass98_Sub22_Sub1_5516 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514;
                                aClass98_Sub22_Sub1_5516.anInt3991 += 6;
                            }
                            class148.anInt339 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                            if (class148.anInt339 == 65535) {
                                class148.anInt339 = -1;
                            }
                            Class104.aClass36Array903[n33] = class148;
                        }
                        Class92.currentIncommingOpcode = null;
                        return true;
                    }
                    else {
                        if (Class92.currentIncommingOpcode == Class211.aClass58_1595) {
                            int short17 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                            if (short17 == 65535) {
                                short17 = -1;
                            }
                            Class98_Sub10_Sub9.method1036(-1962608884, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)5), Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)91), short17, false, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127), Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127));
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class301.aClass58_2507) {
                            final int byteS7 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(n ^ 0x1F4DEC88);
                            final int byteA5 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true);
                            final int n34 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)57) << 12040226;
                            final int byteA6 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true);
                            final int byteA7 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true);
                            Class98_Sub25.method1274(-1);
                            ha_Sub1.method1871(byteS7, byteA6, true, n34, byteA5, byteA7, n - 525200676);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class59.aClass58_469 == Class92.currentIncommingOpcode) {
                            final int short18 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                            final boolean b17 = (0x1 & Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)101)) == 0x1;
                            Class232.method2882(n - 525200605, short18, b17);
                            for (int short19 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127), n35 = 0; ~short19 < ~n35; ++n35) {
                                final int shortA3 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(84);
                                int n36 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)(-122));
                                if (n36 == 255) {
                                    n36 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt1(false);
                                }
                                Class349.method3841(b17, n36, 3113, short18, -1 + shortA3, n35);
                            }
                            Class78.anIntArray597[Class202.method2702(Class145.anInt1172++, 31)] = short18;
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class151_Sub6.aClass58_4997) {
                            final String string15 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                            Class98_Sub45.method1521((byte)44, 6, Class249.method3160(Class322.method3670(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514, (byte)72), 62), 0, string15, string15, string15);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class150.aClass58_1210) {
                            int byteS8 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(74);
                            int byteS9 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(n ^ 0x1F4DEC88);
                            if (byteS8 == 255) {
                                byteS9 = -1;
                                byteS8 = -1;
                            }
                            Class244.method2953((byte)(-103), byteS8, byteS9);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class98_Sub31_Sub2.aClass58_5838) {
                            Class333.anInt3386 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-118));
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class98_Sub42.aClass58_4222) {
                            Class98_Sub10_Sub22.method1070((byte)(-45), Class246_Sub4_Sub2.aClass85_6186);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class25.aClass58_266 == Class92.currentIncommingOpcode) {
                            final int intReverse6 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readIntReverse(true);
                            final int leShortA12 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)121);
                            Class98_Sub25.method1274(-1);
                            Class45.method430(true, leShortA12, intReverse6);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == aa_Sub1.aClass58_3554) {
                            final int short20 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                            final int short21 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                            final int short22 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                            Class98_Sub25.method1274(-1);
                            if (Class159.aClass293ArrayArray1252[short20] != null) {
                                for (int i = short21; i < short22; ++i) {
                                    final int method3648 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1186(n - 525200702);
                                    if (~Class159.aClass293ArrayArray1252[short20].length < ~i && Class159.aClass293ArrayArray1252[short20][i] != null) {
                                        Class159.aClass293ArrayArray1252[short20][i].anInt2259 = method3648;
                                    }
                                }
                            }
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class3.aClass58_75) {
                            Class98_Sub10_Sub22.method1070((byte)(-45), Class79.aClass85_600);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class44.aClass58_379 == Class92.currentIncommingOpcode) {
                            final int byteS10 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(n - 525200497);
                            final int leShortA13 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)(-81));
                            Class181.method2610(true, ~(0x1 & byteS10) == 0xFFFFFFFE, leShortA13);
                            Class78.anIntArray597[Class202.method2702(Class145.anInt1172++, 31)] = leShortA13;
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class98_Sub46_Sub15.aClass58_6041 == Class92.currentIncommingOpcode) {
                            final int byteA8 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteA(true);
                            final int shortA4 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(102);
                            Class98_Sub25.method1274(-1);
                            if (byteA8 == 2) {
                                Class231.method2878(n ^ 0x1F4DECC1);
                            }
                            Class98_Sub46_Sub15.method1609(Class15.anInt185 = shortA4, n - 525213468);
                            Class40.method359(-124, false);
                            Class247.method3155(Class15.anInt185);
                            for (int n37 = 0; ~n37 > -101; ++n37) {
                                aa_Sub3.aBooleanArray3574[n37] = true;
                            }
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class64_Sub2.aClass58_3645 == Class92.currentIncommingOpcode) {
                            final int byteC7 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)(-128));
                            final int byteC8 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)(-128));
                            int short23 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)90);
                            if (~short23 == 0xFFFF0000) {
                                short23 = -1;
                            }
                            Class246_Sub3_Sub1.method2994(short23, byteC7, (byte)(-83), byteC8);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class64_Sub10.aClass58_3665 == Class92.currentIncommingOpcode) {
                            final int method3649 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1192((byte)(-108));
                            final int byteS11 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(88);
                            int short24 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)42);
                            if (~short24 == 0xFFFF0000) {
                                short24 = -1;
                            }
                            Class228.method2861(method3649, byteS11, short24, 18596);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class13.aClass58_161) {
                            Class206.anInt1568 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)100);
                            Class335.anInt2819 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1187((byte)(-112)) << -1201567837;
                            Class53.anInt430 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1184(n - 2023493939) << -1233392637;
                            while (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 < Class65.anInt496) {
                                Class98_Sub10_Sub22.method1070((byte)(-45), Class113.method2143(-1)[Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)111)]);
                            }
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class76.aClass58_589 == Class92.currentIncommingOpcode) {
                            int short25 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                            if (short25 == 65535) {
                                short25 = -1;
                            }
                            Class301.method3537(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127), (byte)1, short25, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)56), Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127), Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)25));
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class211.aClass58_1596 == Class92.currentIncommingOpcode) {
                            Class98_Sub25.method1274(n - 525200580);
                            Class284.method3359(9268);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class246_Sub3_Sub4_Sub4.aClass58_6487) {
                            Class75.aClass140_584.method2292(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)(-39)), Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt(-2), n ^ 0xE0B26CEA);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class217.aClass58_3406) {
                            Class98_Sub10_Sub22.method1070((byte)(-45), Class98_Sub10_Sub16.aClass85_5621);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class98_Sub22.aClass58_3993) {
                            Class53.anInt430 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1184(-1498293360) << -182592509;
                            Class206.anInt1568 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteC((byte)(-106));
                            Class335.anInt2819 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1184(n - 2023493939) << 1849187075;
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class98_Sub26.aClass58_4029 == Class92.currentIncommingOpcode) {
                            final int short26 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                            final boolean b18 = ~(0x1 & Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-100))) == 0xFFFFFFFE;
                            while (~Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.anInt3991 > ~Class65.anInt496) {
                                final int smart2 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSmart(1689622712);
                                final int short27 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                                int n38 = 0;
                                if (~short27 != -1) {
                                    n38 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-124));
                                    if (n38 == 255) {
                                        n38 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt(n - 525200581);
                                    }
                                }
                                Class349.method3841(b18, n38, n ^ 0x1F4DE0EA, short26, -1 + short27, smart2);
                            }
                            Class78.anIntArray597[Class202.method2702(Class145.anInt1172++, 31)] = short26;
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class98_Sub47.aClass58_4270 == Class92.currentIncommingOpcode) {
                            final boolean aBoolean5943 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(-31) == 1;
                            Class98_Sub25.method1274(-1);
                            Class98_Sub46_Sub1.aBoolean5943 = aBoolean5943;
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class27.aClass58_274) {
                            Class108.method1731(Class65.anInt496, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514, n - 525200579);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class159.aClass58_1253 == Class92.currentIncommingOpcode) {
                            final int unsignedByte9 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-114));
                            final boolean b19 = ~(unsignedByte9 & 0x1) == 0xFFFFFFFE;
                            final String string16 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                            String string17 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                            if (string17.equals("")) {
                                string17 = string16;
                            }
                            final String string18 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                            String string19 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                            if (string19.equals("")) {
                                string19 = string18;
                            }
                            if (b19) {
                                for (int n39 = 0; ~n39 > ~Class248.anInt1897; ++n39) {
                                    if (Class255.aStringArray3209[n39].equals(string19)) {
                                        Class246_Sub4_Sub1.aStringArray6171[n39] = string16;
                                        Class255.aStringArray3209[n39] = string17;
                                        Class98_Sub45.aStringArray4255[n39] = string18;
                                        Class110.aStringArray945[n39] = string19;
                                        break;
                                    }
                                }
                            }
                            else {
                                Class246_Sub4_Sub1.aStringArray6171[Class248.anInt1897] = string16;
                                Class255.aStringArray3209[Class248.anInt1897] = string17;
                                Class98_Sub45.aStringArray4255[Class248.anInt1897] = string18;
                                Class110.aStringArray945[Class248.anInt1897] = string19;
                                Class98_Sub10_Sub38.aBooleanArray5759[Class248.anInt1897] = (Class202.method2702(2, unsignedByte9) == 2);
                                ++Class248.anInt1897;
                            }
                            Class92.currentIncommingOpcode = null;
                            Class363.anInt3099 = Class24.anInt242;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class246_Sub3_Sub2_Sub1.aClass58_6335) {
                            final boolean b20 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-125)) == 1;
                            String s10;
                            final String s9 = s10 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                            if (b20) {
                                s10 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                            }
                            final int unsignedByte10 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-6));
                            final int short28 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                            boolean b21 = false;
                            if (unsignedByte10 <= 1 && Class14.method225(s10, (byte)108)) {
                                b21 = true;
                            }
                            if (!b21 && ~Class22.anInt216 == -1) {
                                final String method3650 = Class52.aClass280_3500.method3325(short28, 44).method1576(n - 525185298, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514);
                                if (unsignedByte10 != 2) {
                                    if (unsignedByte10 != 1) {
                                        Class137.method2276(s9, 25, s9, method3650, short28, null, (byte)(-100), 0, s10);
                                    }
                                    else {
                                        Class137.method2276(s9, 25, "<img=0>" + s9, method3650, short28, null, (byte)(-127), 0, "<img=0>" + s10);
                                    }
                                }
                                else {
                                    Class137.method2276(s9, 25, "<img=1>" + s9, method3650, short28, null, (byte)(-123), 0, "<img=1>" + s10);
                                }
                            }
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class320.aClass58_2708 == Class92.currentIncommingOpcode) {
                            Class98_Sub10_Sub1.method1003(false, false);
                            Class92.currentIncommingOpcode = null;
                            return false;
                        }
                        if (Class92.currentIncommingOpcode == Class53.aClass58_431) {
                            final int int14 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt1(false);
                            final int byteS12 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(n ^ 0x1F4DEC8F);
                            final int byteS13 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readByteS(-103);
                            Class52.anIntArray3493[byteS12] = int14;
                            Class64_Sub21.anIntArray3701[byteS12] = byteS13;
                            Class256_Sub1.anIntArray5158[byteS12] = 1;
                            for (int j = -1 + Class98_Sub46_Sub4.anIntArray5955[byteS12], n40 = 0; j > n40; ++n40) {
                                if (int14 >= Class48_Sub1.anIntArray3629[n40]) {
                                    Class256_Sub1.anIntArray5158[byteS12] = 2 + n40;
                                }
                            }
                            Class98_Sub16.anIntArray3928[Class202.method2702(31, Class93_Sub1.anInt5477++)] = byteS12;
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class64_Sub1.aClass58_3637) {
                            Class98_Sub10_Sub22.method1070((byte)(-45), Class39.aClass85_362);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class98_Sub50.aClass58_4291) {
                            Class218.aString1636 = ((~Class65.anInt496 < -3) ? Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84) : Class309.aClass309_2612.method3615(Class374.anInt3159, (byte)25));
                            Class199.anInt1541 = ((Class65.anInt496 > 0) ? Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127) : -1);
                            Class92.currentIncommingOpcode = null;
                            if (~Class199.anInt1541 == 0xFFFF0000) {
                                Class199.anInt1541 = -1;
                            }
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class151_Sub6.aClass58_4998) {
                            Class98_Sub28.anInt4078 = 1;
                            Class92.currentIncommingOpcode = null;
                            Class363.anInt3099 = Class24.anInt242;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class146.aClass58_1179) {
                            final int int15 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt2(n ^ 0xE0B21332);
                            Class98_Sub25.method1274(-1);
                            Class98_Sub19.method1164(za_Sub2.anInt6080, 0, 5, n ^ 0x1F4DECC7, int15);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class147.aClass58_1192) {
                            final int method3651 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1242(-1420625632);
                            final int int16 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt(n - 525200581);
                            final int method3652 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1183(65536);
                            Class98_Sub25.method1274(-1);
                            Class246_Sub3.method2984(method3652, (byte)(-105), method3651, int16);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class27.aClass58_277) {
                            final int intReverse7 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readIntReverse(true);
                            Class98_Sub25.method1274(-1);
                            final Class98_Sub18 class98_Sub23 = (Class98_Sub18)Class116.aClass377_964.method3990(intReverse7, -1);
                            if (class98_Sub23 != null) {
                                Class196.method2666(16398, false, class98_Sub23, true);
                            }
                            if (OutputStream_Sub1.aClass293_33 != null) {
                                Class341.method3812(1, OutputStream_Sub1.aClass293_33);
                                OutputStream_Sub1.aClass293_33 = null;
                            }
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class98_Sub34.aClass58_4128) {
                            final int int17 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt1(false);
                            final int method3653 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1198(n - 525200697);
                            Class98_Sub25.method1274(n ^ 0xE0B2133C);
                            Class218.method2806(int17, method3653, true);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class308.aClass58_2581 == Class92.currentIncommingOpcode) {
                            final int short29 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                            final int intReverse8 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readIntReverse(true);
                            final int leShortA14 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)(-115));
                            final int shortA5 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(96);
                            Class98_Sub25.method1274(-1);
                            Class98_Sub19.method1164(leShortA14 << 725805072 | short29, shortA5, 7, 4, intReverse8);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class283.aClass58_2139) {
                            Class98_Sub10_Sub22.method1070((byte)(-45), Class351.aClass85_2921);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class352.aClass58_2993) {
                            Class98_Sub46_Sub9.anInt6003 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1227((byte)(-1));
                            Class109.aBoolean933 = (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)112) == 1);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class266.aClass58_1992 == Class92.currentIncommingOpcode) {
                            if (~Class15.anInt185 != 0x0) {
                                Class207.method2764(0, Class15.anInt185, -46);
                            }
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class98_Sub10_Sub14.aClass58_5608 == Class92.currentIncommingOpcode) {
                            if (!Class246_Sub3_Sub3.method3011(-6410, Class177.anInt1376)) {
                                Class98_Sub10_Sub6.anInt5569 = 30 * Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                            }
                            else {
                                Class98_Sub10_Sub6.anInt5569 = (int)(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127) * 2.5f);
                            }
                            Class98_Sub36.anInt4161 = Class24.anInt242;
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class93_Sub3.aClass58_5493 == Class92.currentIncommingOpcode) {
                            Class98_Sub10_Sub22.method1070((byte)(-45), ha_Sub1.aClass85_4299);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class98_Sub10_Sub28.aClass58_5697 == Class92.currentIncommingOpcode) {
                            final int short30 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)75);
                            Class98_Sub25.method1274(n - 525200580);
                            Class98_Sub11.method1127((byte)67, short30);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class312.aClass58_2661 == Class92.currentIncommingOpcode) {
                            for (int k = 0; k < Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030.length; ++k) {
                                if (Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[k] != null) {
                                    Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[k].anIntArray6373 = null;
                                    Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[k].anInt6413 = -1;
                                }
                            }
                            for (int n41 = 0; ~n41 > ~Class98_Sub10_Sub20.anInt5640; ++n41) {
                                Class163.aClass98_Sub39Array3516[n41].aClass246_Sub3_Sub4_Sub2_Sub1_4187.anIntArray6373 = null;
                                Class163.aClass98_Sub39Array3516[n41].aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6413 = -1;
                            }
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class336.aClass58_2825 == Class92.currentIncommingOpcode) {
                            final int int18 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt1(false);
                            Class98_Sub25.method1274(-1);
                            Class98_Sub19.method1164(-1, -1, 3, n ^ 0x1F4DECC7, int18);
                            Class92.currentIncommingOpcode = null;
                            return true;
                        }
                        if (Class92.currentIncommingOpcode == Class8.aClass58_112) {
                            final boolean b22 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)20) == 1;
                            String s12;
                            final String s11 = s12 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                            if (b22) {
                                s12 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                            }
                            final long method3654 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1246(-105);
                            final long n42 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                            final long n43 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1186(-124);
                            final int unsignedByte11 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)126);
                            final long n44 = (n42 << -172131936) + n43;
                            boolean b23 = false;
                            while (true) {
                                for (int l = 0; l < 100; ++l) {
                                    if (n44 == Class94.aLongArray794[l]) {
                                        b23 = true;
                                        if (!b23 && ~Class22.anInt216 == -1) {
                                            Class94.aLongArray794[Class147.anInt1193] = n44;
                                            Class147.anInt1193 = (Class147.anInt1193 + 1) % 100;
                                            final String method3655 = Class249.method3160(Class322.method3670(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514, (byte)72), 62);
                                            if (unsignedByte11 != 2 && unsignedByte11 != 3) {
                                                if (~unsignedByte11 == 0xFFFFFFFE) {
                                                    Class137.method2276(s11, 9, "<img=0>" + s11, method3655, -1, Class18.method247(method3654, n - 525200681), (byte)(-65), 0, "<img=0>" + s12);
                                                }
                                                else {
                                                    Class137.method2276(s11, 9, s11, method3655, -1, Class18.method247(method3654, -120), (byte)(-84), 0, s12);
                                                }
                                            }
                                            else {
                                                Class137.method2276(s11, 9, "<img=1>" + s11, method3655, -1, Class18.method247(method3654, n - 525200644), (byte)(-113), 0, "<img=1>" + s12);
                                            }
                                        }
                                        Class92.currentIncommingOpcode = null;
                                        return true;
                                    }
                                }
                                if (unsignedByte11 > 1) {
                                    continue;
                                }
                                if ((Class109.aBoolean933 && !Class98_Sub10_Sub35.aBoolean5732) || Class178.aBoolean1401) {
                                    b23 = true;
                                    continue;
                                }
                                if (Class14.method225(s12, (byte)121)) {
                                    b23 = true;
                                }
                                continue;
                            }
                        }
                        else {
                            if (Class92.currentIncommingOpcode == Class64_Sub15.aClass58_3677) {
                                final int int19 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt2(-108);
                                int shortA6 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(n - 525200459);
                                if (shortA6 == 65535) {
                                    shortA6 = -1;
                                }
                                int shortA7 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(74);
                                if (~shortA7 == 0xFFFF0000) {
                                    shortA7 = -1;
                                }
                                final int intReverse9 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readIntReverse(true);
                                Class98_Sub25.method1274(-1);
                                for (int n45 = shortA7; ~shortA6 <= ~n45; ++n45) {
                                    final long n46 = (intReverse9 << -1557162784) - -n45;
                                    final Class98_Sub49 class98_Sub24 = (Class98_Sub49)Class168.aClass377_1287.method3990(n46, -1);
                                    Class98_Sub49 class98_Sub25;
                                    if (class98_Sub24 != null) {
                                        class98_Sub25 = new Class98_Sub49(int19, class98_Sub24.anInt4285);
                                        class98_Sub24.method942(103);
                                    }
                                    else if (~n45 == 0x0) {
                                        class98_Sub25 = new Class98_Sub49(int19, Class159.method2509(intReverse9, -9820).aClass98_Sub49_2348.anInt4285);
                                    }
                                    else {
                                        class98_Sub25 = new Class98_Sub49(int19, -1);
                                    }
                                    Class168.aClass377_1287.method3996(class98_Sub25, n46, -1);
                                }
                                Class92.currentIncommingOpcode = null;
                                return true;
                            }
                            if (Class92.currentIncommingOpcode == Class36.aClass58_344) {
                                final int int20 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt1(false);
                                int short31 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                                if (~short31 == 0xFFFF0000) {
                                    short31 = -1;
                                }
                                final int shortA8 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(66);
                                int short32 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                                if (~short32 == 0xFFFF0000) {
                                    short32 = -1;
                                }
                                Class98_Sub25.method1274(-1);
                                for (int n47 = short31; ~n47 >= ~short32; ++n47) {
                                    final long n48 = (int20 << -1828767200) - -n47;
                                    final Class98_Sub49 class98_Sub26 = (Class98_Sub49)Class168.aClass377_1287.method3990(n48, -1);
                                    Class98_Sub49 class98_Sub27;
                                    if (class98_Sub26 != null) {
                                        class98_Sub27 = new Class98_Sub49(class98_Sub26.anInt4284, shortA8);
                                        class98_Sub26.method942(110);
                                    }
                                    else if (~n47 == 0x0) {
                                        class98_Sub27 = new Class98_Sub49(Class159.method2509(int20, n ^ 0xE0B23567).aClass98_Sub49_2348.anInt4284, shortA8);
                                    }
                                    else {
                                        class98_Sub27 = new Class98_Sub49(0, shortA8);
                                    }
                                    Class168.aClass377_1287.method3996(class98_Sub27, n48, n ^ 0xE0B2133C);
                                }
                                Class92.currentIncommingOpcode = null;
                                return true;
                            }
                            if (Class284_Sub2_Sub2.aClass58_6197 == Class92.currentIncommingOpcode) {
                                final int intReverse10 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readIntReverse(true);
                                final int shortA9 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShortA(111);
                                final int short33 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                                final int short34 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                                Class98_Sub25.method1274(n - 525200580);
                                Class353.method3868(short34, intReverse10, (byte)(-121), short33, shortA9);
                                Class92.currentIncommingOpcode = null;
                                return true;
                            }
                            if (Class92.currentIncommingOpcode == Class246_Sub3_Sub4_Sub2_Sub2.aClass58_6516) {
                                Class64_Sub22.anInt3705 = Class24.anInt242;
                                if (Class65.anInt496 == 0) {
                                    Class374.aClass147Array3157 = null;
                                    Class93_Sub3.aString5494 = null;
                                    Class32.anInt308 = 0;
                                    Class153.aString1229 = null;
                                    Class92.currentIncommingOpcode = null;
                                    return true;
                                }
                                Class93_Sub3.aString5494 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                                if (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)85) == 1) {
                                    Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                                }
                                Class153.aString1229 = Class98_Sub28.method1305(-89, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1246(-126));
                                Class232.aByte1742 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSignedByte((byte)(-19));
                                final int unsignedByte12 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)120);
                                if (~unsignedByte12 == 0xFFFFFF00) {
                                    Class92.currentIncommingOpcode = null;
                                    return true;
                                }
                                Class32.anInt308 = unsignedByte12;
                                final Class147[] aClass147Array3157 = new Class147[100];
                                for (int n49 = 0; ~n49 > ~Class32.anInt308; ++n49) {
                                    aClass147Array3157[n49] = new Class147();
                                    aClass147Array3157[n49].aString1191 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                                    if (Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-106)) == 1) {
                                        aClass147Array3157[n49].aString1186 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                                    }
                                    else {
                                        aClass147Array3157[n49].aString1186 = aClass147Array3157[n49].aString1191;
                                    }
                                    aClass147Array3157[n49].aString1185 = Class353.method3867(n ^ 0xE0B2133C, aClass147Array3157[n49].aString1186);
                                    aClass147Array3157[n49].anInt1188 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                                    aClass147Array3157[n49].aByte1187 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSignedByte((byte)(-19));
                                    aClass147Array3157[n49].aString1190 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                                    if (aClass147Array3157[n49].aString1186.equals(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aString6537)) {
                                        Class111.aByte947 = aClass147Array3157[n49].aByte1187;
                                    }
                                }
                                int anInt2703 = Class32.anInt308;
                                while (anInt2703 > 0) {
                                    boolean b24 = true;
                                    --anInt2703;
                                    for (int n50 = 0; anInt2703 > n50; ++n50) {
                                        if (~aClass147Array3157[n50].aString1185.compareTo(aClass147Array3157[1 + n50].aString1185) < -1) {
                                            final Class147 class151 = aClass147Array3157[n50];
                                            aClass147Array3157[n50] = aClass147Array3157[n50 + 1];
                                            aClass147Array3157[1 + n50] = class151;
                                            b24 = false;
                                        }
                                    }
                                    if (b24) {
                                        break;
                                    }
                                }
                                Class92.currentIncommingOpcode = null;
                                Class374.aClass147Array3157 = aClass147Array3157;
                                return true;
                            }
                            else if (Class18.aClass58_215 == Class92.currentIncommingOpcode) {
                                final boolean b25 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-117)) == 1;
                                String s14;
                                final String s13 = s14 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                                if (b25) {
                                    s14 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                                }
                                final long method3656 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1246(-122);
                                final long n51 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                                final long n52 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1186(n ^ 0xE0B21341);
                                final int unsignedByte13 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-119));
                                final int short35 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                                final long n53 = n52 + (n51 << -741032544);
                                boolean b26 = false;
                                while (true) {
                                    for (int n54 = 0; ~n54 > -101; ++n54) {
                                        if (n53 == Class94.aLongArray794[n54]) {
                                            b26 = true;
                                            if (!b26 && Class22.anInt216 == 0) {
                                                Class94.aLongArray794[Class147.anInt1193] = n53;
                                                Class147.anInt1193 = (Class147.anInt1193 + 1) % 100;
                                                final String method3657 = Class52.aClass280_3500.method3325(short35, 98).method1576(15281, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514);
                                                if (unsignedByte13 == 2) {
                                                    Class137.method2276(s13, 20, "<img=1>" + s13, method3657, short35, Class18.method247(method3656, -76), (byte)(-117), 0, "<img=1>" + s14);
                                                }
                                                else if (unsignedByte13 == 1) {
                                                    Class137.method2276(s13, 20, "<img=0>" + s13, method3657, short35, Class18.method247(method3656, -113), (byte)(-121), 0, "<img=0>" + s14);
                                                }
                                                else {
                                                    Class137.method2276(s13, 20, s13, method3657, short35, Class18.method247(method3656, -81), (byte)(-53), 0, s14);
                                                }
                                            }
                                            Class92.currentIncommingOpcode = null;
                                            return true;
                                        }
                                    }
                                    if (unsignedByte13 <= 1 && Class14.method225(s14, (byte)103)) {
                                        b26 = true;
                                    }
                                    continue;
                                }
                            }
                            else {
                                if (Class177.aClass58_1381 == Class92.currentIncommingOpcode) {
                                    final int leShortA15 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readLEShortA((byte)(-102));
                                    final int intReverse11 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readIntReverse(true);
                                    Class98_Sub25.method1274(n ^ 0xE0B2133C);
                                    Class116.method2161(intReverse11, leShortA15, (byte)(-120));
                                    Class92.currentIncommingOpcode = null;
                                    return true;
                                }
                                if (Class92.currentIncommingOpcode == Class98_Sub6.aClass58_3844) {
                                    final boolean b27 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)117) == 1;
                                    String s16;
                                    final String s15 = s16 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                                    if (b27) {
                                        s16 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                                    }
                                    final long n55 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                                    final long n56 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1186(-123);
                                    final int unsignedByte14 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)79);
                                    final int short36 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort((byte)127);
                                    final long n57 = n56 + (n55 << 812765856);
                                    boolean b28 = false;
                                    while (true) {
                                        for (int n58 = 0; n58 < 100; ++n58) {
                                            if (Class94.aLongArray794[n58] == n57) {
                                                b28 = true;
                                                if (!b28 && ~Class22.anInt216 == -1) {
                                                    Class94.aLongArray794[Class147.anInt1193] = n57;
                                                    Class147.anInt1193 = (Class147.anInt1193 + 1) % 100;
                                                    final String method3658 = Class52.aClass280_3500.method3325(short36, 98).method1576(15281, Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514);
                                                    if (~unsignedByte14 == 0xFFFFFFFD) {
                                                        Class137.method2276(s15, 18, "<img=1>" + s15, method3658, short36, null, (byte)(-101), 0, "<img=1>" + s16);
                                                    }
                                                    else if (~unsignedByte14 != 0xFFFFFFFE) {
                                                        Class137.method2276(s15, 18, s15, method3658, short36, null, (byte)(-97), 0, s16);
                                                    }
                                                    else {
                                                        Class137.method2276(s15, 18, "<img=0>" + s15, method3658, short36, null, (byte)(-126), 0, "<img=0>" + s16);
                                                    }
                                                }
                                                Class92.currentIncommingOpcode = null;
                                                return true;
                                            }
                                        }
                                        if (unsignedByte14 <= 1 && Class14.method225(s16, (byte)104)) {
                                            b28 = true;
                                        }
                                        continue;
                                    }
                                }
                                else {
                                    if (Class76.aClass58_587 == Class92.currentIncommingOpcode) {
                                        Class166.method2525(0);
                                        Class92.currentIncommingOpcode = null;
                                        return true;
                                    }
                                    if (Class92.currentIncommingOpcode == Class64_Sub27.aClass58_3715) {
                                        final String string20 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                                        final Object[] anObjectArray3981 = new Object[string20.length() + 1];
                                        for (int n59 = string20.length() - 1; n59 >= 0; --n59) {
                                            if (string20.charAt(n59) != 's') {
                                                anObjectArray3981[1 + n59] = new Integer(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt(-2));
                                            }
                                            else {
                                                anObjectArray3981[n59 + 1] = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readString((byte)84);
                                            }
                                        }
                                        anObjectArray3981[0] = new Integer(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt(-2));
                                        Class98_Sub25.method1274(-1);
                                        final Class98_Sub21 class98_Sub28 = new Class98_Sub21();
                                        class98_Sub28.anObjectArray3981 = anObjectArray3981;
                                        Class247.method3144(class98_Sub28);
                                        Class92.currentIncommingOpcode = null;
                                        return true;
                                    }
                                    if (Class92.currentIncommingOpcode == Class335.aClass58_2816) {
                                        final int int21 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt2(-112);
                                        int short37 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)(-77));
                                        if (~short37 == 0xFFFF0000) {
                                            short37 = -1;
                                        }
                                        Class98_Sub25.method1274(-1);
                                        Class98_Sub19.method1164(short37, -1, 2, n ^ 0x1F4DECC7, int21);
                                        Class92.currentIncommingOpcode = null;
                                        return true;
                                    }
                                    if (Class92.currentIncommingOpcode == Class251.aClass58_1921) {
                                        int short38 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readShort1((byte)78);
                                        if (short38 == 65535) {
                                            short38 = -1;
                                        }
                                        final int int22 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt(-2);
                                        Class98_Sub25.method1274(n ^ 0xE0B2133C);
                                        Class98_Sub19.method1164(short38, -1, 1, 4, int22);
                                        Class92.currentIncommingOpcode = null;
                                        return true;
                                    }
                                    if (Class287.aClass58_2187 == Class92.currentIncommingOpcode) {
                                        final int unsignedByte15 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)8);
                                        Class98_Sub25.method1274(-1);
                                        Class103.anInt896 = unsignedByte15;
                                        Class92.currentIncommingOpcode = null;
                                        return true;
                                    }
                                    if (Class287.aClass58_2194 == Class92.currentIncommingOpcode) {
                                        Class24.anInt255 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUShort(false);
                                        Class98_Sub36.anInt4161 = Class24.anInt242;
                                        Class92.currentIncommingOpcode = null;
                                        return true;
                                    }
                                    if (Class98_Sub46_Sub6.aClass58_5975 == Class92.currentIncommingOpcode) {
                                        Class187.aClass143_1449 = Class98_Sub43_Sub2.aClass88_5907.method868(Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readInt(-2), 113);
                                        Class92.currentIncommingOpcode = null;
                                        return true;
                                    }
                                    if (Class196.aClass58_1507 == Class92.currentIncommingOpcode) {
                                        Class75.aClass140_584.method2288((byte)(-79));
                                        Class239.anInt1844 += 32;
                                        Class92.currentIncommingOpcode = null;
                                        return true;
                                    }
                                    if (Class64_Sub2.aClass58_3642 == Class92.currentIncommingOpcode) {
                                        Class206.anInt1568 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readUnsignedByte((byte)(-115));
                                        Class335.anInt2819 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.method1234(128) << 212170051;
                                        Class53.anInt430 = Class48_Sub1_Sub2.aClass98_Sub22_Sub1_5514.readSignedByte((byte)(-19)) << 773724419;
                                        for (Class98_Sub45 class98_Sub29 = (Class98_Sub45)Class146.aClass377_1180.method3998(105); class98_Sub29 != null; class98_Sub29 = (Class98_Sub45)Class146.aClass377_1180.method3995(-1)) {
                                            final int n60 = (int)(0x3L & class98_Sub29.aLong832 >> 1200025692);
                                            final int n61 = (int)(0x3FFFL & class98_Sub29.aLong832) + -Class272.anInt2038;
                                            final int n62 = -aa_Sub2.anInt3562 + (int)(0x3FFFL & class98_Sub29.aLong832 >> 1428694926);
                                            if (Class206.anInt1568 == n60 && n61 >= Class53.anInt430 && ~(Class53.anInt430 + 8) < ~n61 && Class335.anInt2819 <= n62 && ~n62 > ~(Class335.anInt2819 + 8)) {
                                                class98_Sub29.method942(n ^ 0x1F4DEC80);
                                                if (~n61 <= -1 && ~n62 <= -1 && n61 < Class165.anInt1276 && ~Class98_Sub10_Sub7.anInt5572 < ~n62) {
                                                    Class98_Sub32.method1437(n61, Class206.anInt1568, (byte)122, n62);
                                                }
                                            }
                                        }
                                        for (Class98_Sub33 class98_Sub30 = (Class98_Sub33)Class191.aClass148_1478.method2418(32); class98_Sub30 != null; class98_Sub30 = (Class98_Sub33)Class191.aClass148_1478.method2417(96)) {
                                            if (~class98_Sub30.anInt4112 <= ~Class53.anInt430 && class98_Sub30.anInt4112 < 8 + Class53.anInt430 && ~class98_Sub30.anInt4113 <= ~Class335.anInt2819 && ~(8 + Class335.anInt2819) < ~class98_Sub30.anInt4113 && Class206.anInt1568 == class98_Sub30.anInt4116) {
                                                class98_Sub30.aBoolean4124 = true;
                                            }
                                        }
                                        for (Class98_Sub33 class98_Sub31 = (Class98_Sub33)Class98_Sub11.aClass148_3866.method2418(32); class98_Sub31 != null; class98_Sub31 = (Class98_Sub33)Class98_Sub11.aClass148_3866.method2417(n - 525200461)) {
                                            if (~Class53.anInt430 >= ~class98_Sub31.anInt4112 && class98_Sub31.anInt4112 < 8 + Class53.anInt430 && Class335.anInt2819 <= class98_Sub31.anInt4113 && ~(8 + Class335.anInt2819) < ~class98_Sub31.anInt4113 && ~Class206.anInt1568 == ~class98_Sub31.anInt4116) {
                                                class98_Sub31.aBoolean4124 = true;
                                            }
                                        }
                                        Class92.currentIncommingOpcode = null;
                                        return true;
                                    }
                                    Class305_Sub1.method3585(null, n ^ 0xE0B21347, "T1 - " + ((Class92.currentIncommingOpcode != null) ? Class92.currentIncommingOpcode.method521((byte)67) : -1) + "," + ((Class260.aClass58_3262 == null) ? -1 : Class260.aClass58_3262.method521((byte)76)) + "," + ((Class98_Sub10_Sub21.aClass58_5641 == null) ? -1 : Class98_Sub10_Sub21.aClass58_5641.method521((byte)71)) + " - " + Class65.anInt496);
                                    Class98_Sub10_Sub1.method1003(false, false);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bia.I(" + n + ')');
        }
    }
    
    @Override
    public final void method58(final byte b) {
        try {
            super.method58(b);
            final Class93_Sub1 class93_Sub1 = (Class93_Sub1)super.aClass93_3478;
            this.aClass332_5462 = Class237_Sub1.method2915(class93_Sub1.anInt5483, super.aClass207_3473, (byte)(-89));
            this.aClass332_5467 = Class237_Sub1.method2915(class93_Sub1.anInt5480, super.aClass207_3473, (byte)(-89));
            this.aClass332_5463 = Class237_Sub1.method2915(class93_Sub1.anInt5485, super.aClass207_3473, (byte)(-89));
            this.aClass332_5465 = Class237_Sub1.method2915(class93_Sub1.anInt5481, super.aClass207_3473, (byte)(-89));
            this.aClass332_5461 = Class237_Sub1.method2915(class93_Sub1.anInt5478, super.aClass207_3473, (byte)(-89));
            this.aClass332_5464 = Class237_Sub1.method2915(class93_Sub1.anInt5484, super.aClass207_3473, (byte)(-89));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bia.C(" + b + ')');
        }
    }
    
    public static void method3968(final boolean b) {
        try {
            PacketParser.aClass58_5466 = null;
            if (b) {
                method3969(-29, 33);
            }
            PacketParser.aClass98_Sub46_Sub16Array5468 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bia.H(" + b + ')');
        }
    }
    
    @Override
    public final boolean method59(final int n) {
        try {
            if (!super.method59(n)) {
                return false;
            }
            final Class93_Sub1 class93_Sub1 = (Class93_Sub1)super.aClass93_3478;
            return super.aClass207_3473.method2742(-22, class93_Sub1.anInt5483) && super.aClass207_3473.method2742(-81, class93_Sub1.anInt5480) && super.aClass207_3473.method2742(-87, class93_Sub1.anInt5485) && super.aClass207_3473.method2742(-58, class93_Sub1.anInt5481) && super.aClass207_3473.method2742(-61, class93_Sub1.anInt5478) && super.aClass207_3473.method2742(-61, class93_Sub1.anInt5484);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bia.A(" + n + ')');
        }
    }
    
    @Override
    final void method3960(final int n, final int n2, final byte b, final boolean b2) {
        try {
            if (b == -36 && b2) {
                final int[] array = new int[4];
                Class265.aHa1974.K(array);
                Class265.aHa1974.KA(n2, n, n2 + super.aClass93_3478.anInt3514, n - -super.aClass93_3478.anInt3504);
                final int method3737 = this.aClass332_5463.method3737();
                final int method3738 = this.aClass332_5463.method3749();
                final int method3739 = this.aClass332_5465.method3737();
                final int method3740 = this.aClass332_5465.method3749();
                this.aClass332_5463.method3735(n2, (-method3738 + super.aClass93_3478.anInt3504) / 2 + n);
                this.aClass332_5465.method3735(-method3739 + (n2 - -super.aClass93_3478.anInt3514), (super.aClass93_3478.anInt3504 + -method3740) / 2 + n);
                Class265.aHa1974.KA(n2, n, super.aClass93_3478.anInt3514 + n2, this.aClass332_5461.method3749() + n);
                this.aClass332_5461.method3738(n2 + method3737, n, -method3739 + -method3737 + super.aClass93_3478.anInt3514, super.aClass93_3478.anInt3504);
                final int method3741 = this.aClass332_5464.method3749();
                Class265.aHa1974.KA(n2, n + super.aClass93_3478.anInt3504 - method3741, super.aClass93_3478.anInt3514 + n2, n - -super.aClass93_3478.anInt3504);
                this.aClass332_5464.method3738(n2 + method3737, -method3741 + (n - -super.aClass93_3478.anInt3504), -method3739 + super.aClass93_3478.anInt3514 + -method3737, super.aClass93_3478.anInt3504);
                Class265.aHa1974.KA(array[0], array[1], array[2], array[3]);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bia.D(" + n + ',' + n2 + ',' + b + ',' + b2 + ')');
        }
    }
    
    static final void method3969(final int n, final int n2) {
        try {
            Class185.method2628(n2, -114, 17).method1621(0);
            if (n < 94) {
                PacketParser.aClass58_5466 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bia.E(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        PacketParser.aClass58_5466 = new IncomingOpcode(18, 3);
        PacketParser.aClass98_Sub46_Sub16Array5468 = new Class98_Sub46_Sub16[14];
    }
}
