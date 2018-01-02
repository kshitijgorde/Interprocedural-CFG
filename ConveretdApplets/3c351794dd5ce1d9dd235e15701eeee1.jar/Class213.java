// 
// Decompiled by Procyon v0.5.30
// 

final class Class213
{
    static OutgoingOpcode aClass171_1604;
    private byte[] aByteArray1605;
    private int[] anIntArray1606;
    static int[] anIntArray1607;
    private int[] anIntArray1608;
    static IncomingOpcode aClass58_1609;
    static int[] anIntArray1610;
    static Class155[] aClass155Array1611;
    
    static final void method2778(final boolean b, final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2) {
        try {
            if (!b) {
                Class213.anIntArray1610 = null;
            }
            final Class98_Sub42 class98_Sub42 = (Class98_Sub42)Class98_Sub10_Sub14.aClass377_5612.method3990(class246_Sub3_Sub4_Sub2_Sub2.anInt6369, -1);
            if (class98_Sub42 != null) {
                if (class98_Sub42.aClass98_Sub31_Sub5_4232 != null) {
                    Class81.aClass98_Sub31_Sub3_619.method1374(class98_Sub42.aClass98_Sub31_Sub5_4232);
                    class98_Sub42.aClass98_Sub31_Sub5_4232 = null;
                }
                class98_Sub42.method942(73);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nq.C(" + b + ',' + ((class246_Sub3_Sub4_Sub2_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final Class98_Sub46_Sub4 method2779(final byte b, final int n, final int n2, final Class105 aClass105_5957) {
        try {
            final int n3 = aClass105_5957.anInt3416 | n2 << -1405442934;
            final Class98_Sub46_Sub4 class98_Sub46_Sub4 = (Class98_Sub46_Sub4)Class38.aClass100_357.method1694((byte)113, n3 << -1997125744);
            if (class98_Sub46_Sub4 != null) {
                return class98_Sub46_Sub4;
            }
            final byte[] method2733 = Class52.aClass207_3494.method2733(Class52.aClass207_3494.method2763(106, n3), 103);
            if (method2733 != null) {
                if (method2733.length <= 1) {
                    return null;
                }
                Class98_Sub46_Sub4 method2734;
                try {
                    method2734 = Class22.method280(method2733, 0);
                }
                catch (Exception ex) {
                    throw new RuntimeException(ex.getMessage() + " S: " + n3);
                }
                method2734.aClass105_5957 = aClass105_5957;
                Class38.aClass100_357.method1695(26404, method2734, n3 << -2064343152);
                return method2734;
            }
            else {
                final int n4 = aClass105_5957.anInt3416 | n + 65536 << 1862719850;
                final Class98_Sub46_Sub4 class98_Sub46_Sub5 = (Class98_Sub46_Sub4)Class38.aClass100_357.method1694((byte)(-34), n4 << -988809008);
                if (class98_Sub46_Sub5 != null) {
                    return class98_Sub46_Sub5;
                }
                final byte[] method2735 = Class52.aClass207_3494.method2733(Class52.aClass207_3494.method2763(116, n4), 40);
                if (method2735 != null) {
                    if (method2735.length <= 1) {
                        return null;
                    }
                    Class98_Sub46_Sub4 method2736;
                    try {
                        method2736 = Class22.method280(method2735, 0);
                    }
                    catch (Exception ex2) {
                        throw new RuntimeException(ex2.getMessage() + " S: " + n4);
                    }
                    method2736.aClass105_5957 = aClass105_5957;
                    Class38.aClass100_357.method1695(26404, method2736, n4 << 744524432);
                    return method2736;
                }
                else {
                    final int n5 = aClass105_5957.anInt3416 | 0x3FFFC00;
                    final Class98_Sub46_Sub4 class98_Sub46_Sub6 = (Class98_Sub46_Sub4)Class38.aClass100_357.method1694((byte)(-82), n5 << 846811984);
                    if (class98_Sub46_Sub6 != null) {
                        return class98_Sub46_Sub6;
                    }
                    final byte[] method2737 = Class52.aClass207_3494.method2733(Class52.aClass207_3494.method2763(80, n5), -103);
                    if (method2737 == null) {
                        return null;
                    }
                    if (method2737.length <= 1) {
                        return null;
                    }
                    Class98_Sub46_Sub4 method2738;
                    try {
                        method2738 = Class22.method280(method2737, 0);
                    }
                    catch (Exception ex3) {
                        throw new RuntimeException(ex3.getMessage() + " S: " + n5);
                    }
                    method2738.aClass105_5957 = aClass105_5957;
                    Class38.aClass100_357.method1695(26404, method2738, n5 << -303464880);
                    return method2738;
                }
            }
        }
        catch (RuntimeException ex4) {
            throw Class64_Sub27.method667(ex4, "nq.F(" + b + ',' + n + ',' + n2 + ',' + ((aClass105_5957 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final int method2780(int i, final byte[] array, int n, final int n2, final byte[] array2, final int n3) {
        try {
            int method366 = 0;
            i += n;
            int n4 = n2 << -1823989501;
            if (n3 != 6350) {
                method2783((byte)(-128));
            }
            while (i > n) {
                final int n5 = 0xFF & array2[n];
                final int n6 = this.anIntArray1608[n5];
                final byte b = this.aByteArray1605[n5];
                if (~b == -1) {
                    throw new RuntimeException("No codeword for data value " + n5);
                }
                int n7 = n4 >> 747036739;
                byte b2 = (byte)(n4 & 0x7);
                final int n8 = method366 & -b2 >> 588283839;
                final int n9 = (b2 + b - 1 >> -78573981) + n7;
                b2 += 24;
                array[n7] = (byte)(method366 = Class41.method366(n8, n6 >>> b2));
                if (n9 > n7) {
                    b2 -= 8;
                    ++n7;
                    array[n7] = (byte)(method366 = n6 >>> b2);
                    if (n7 < n9) {
                        b2 -= 8;
                        ++n7;
                        array[n7] = (byte)(method366 = n6 >>> b2);
                        if (n9 > n7) {
                            ++n7;
                            b2 -= 8;
                            array[n7] = (byte)(method366 = n6 >>> b2);
                            if (~n7 > ~n9) {
                                b2 -= 8;
                                ++n7;
                                array[n7] = (byte)(method366 = n6 << -b2);
                            }
                        }
                    }
                }
                n4 += b;
                ++n;
            }
            return -n2 + (7 + n4 >> 1571009731);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nq.B(" + i + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n3 + ')');
        }
    }
    
    static final void method2781(final int n) {
        try {
            final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(260, Class98_Sub27.aClass171_4045, Class331.aClass117_2811);
            method3023.aClass98_Sub22_Sub1_3865.writeShort(Class75.anInt581, n + 1571862880);
            Class98_Sub10_Sub29.sendPacket(false, method3023);
            if (n != 8) {
                method2779((byte)108, 108, -78, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nq.E(" + n + ')');
        }
    }
    
    final int method2782(final int n, final byte[] array, final int n2, int n3, final byte[] array2, int n4) {
        try {
            if (n3 == 0) {
                return 0;
            }
            int n5 = 0;
            n3 += n4;
            if (n2 > -62) {
                return -32;
            }
            int n6 = n;
            while (true) {
                final byte b = array2[n6];
                if (~b > -1) {
                    n5 = this.anIntArray1606[n5];
                }
                else {
                    ++n5;
                }
                final int n7;
                if ((n7 = this.anIntArray1606[n5]) < 0) {
                    array[n4++] = (byte)~n7;
                    if (~n3 >= ~n4) {
                        break;
                    }
                    n5 = 0;
                }
                if ((0x40 & b) != 0x0) {
                    n5 = this.anIntArray1606[n5];
                }
                else {
                    ++n5;
                }
                final int n8;
                if (~(n8 = this.anIntArray1606[n5]) > -1) {
                    array[n4++] = (byte)~n8;
                    if (n4 >= n3) {
                        break;
                    }
                    n5 = 0;
                }
                if (~(b & 0x20) == -1) {
                    ++n5;
                }
                else {
                    n5 = this.anIntArray1606[n5];
                }
                final int n9;
                if ((n9 = this.anIntArray1606[n5]) < 0) {
                    array[n4++] = (byte)~n9;
                    if (~n3 >= ~n4) {
                        break;
                    }
                    n5 = 0;
                }
                if ((b & 0x10) == 0x0) {
                    ++n5;
                }
                else {
                    n5 = this.anIntArray1606[n5];
                }
                final int n10;
                if (~(n10 = this.anIntArray1606[n5]) > -1) {
                    array[n4++] = (byte)~n10;
                    if (~n4 <= ~n3) {
                        break;
                    }
                    n5 = 0;
                }
                if (~(0x8 & b) != -1) {
                    n5 = this.anIntArray1606[n5];
                }
                else {
                    ++n5;
                }
                final int n11;
                if (~(n11 = this.anIntArray1606[n5]) > -1) {
                    array[n4++] = (byte)~n11;
                    if (n3 <= n4) {
                        break;
                    }
                    n5 = 0;
                }
                if ((b & 0x4) != 0x0) {
                    n5 = this.anIntArray1606[n5];
                }
                else {
                    ++n5;
                }
                final int n12;
                if ((n12 = this.anIntArray1606[n5]) < 0) {
                    array[n4++] = (byte)~n12;
                    if (~n3 >= ~n4) {
                        break;
                    }
                    n5 = 0;
                }
                if (~(0x2 & b) != -1) {
                    n5 = this.anIntArray1606[n5];
                }
                else {
                    ++n5;
                }
                final int n13;
                if ((n13 = this.anIntArray1606[n5]) < 0) {
                    array[n4++] = (byte)~n13;
                    if (~n4 <= ~n3) {
                        break;
                    }
                    n5 = 0;
                }
                if ((b & 0x1) != 0x0) {
                    n5 = this.anIntArray1606[n5];
                }
                else {
                    ++n5;
                }
                final int n14;
                if ((n14 = this.anIntArray1606[n5]) < 0) {
                    array[n4++] = (byte)~n14;
                    if (n3 <= n4) {
                        break;
                    }
                    n5 = 0;
                }
                ++n6;
            }
            return -n + (1 + n6);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nq.D(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + ((array2 != null) ? "{...}" : "null") + ',' + n4 + ')');
        }
    }
    
    Class213(final byte[] aByteArray1605) {
        try {
            final int length = aByteArray1605.length;
            this.anIntArray1608 = new int[length];
            this.aByteArray1605 = aByteArray1605;
            this.anIntArray1606 = new int[8];
            final int[] array = new int[33];
            int n = 0;
            for (int i = 0; i < length; ++i) {
                final byte b = aByteArray1605[i];
                if (b != 0) {
                    final int n2 = 1 << -b + 32;
                    final int n3 = array[b];
                    this.anIntArray1608[i] = n3;
                    int n6;
                    if (~(n2 & n3) == -1) {
                        for (int j = b - 1; j >= 1; --j) {
                            final int n4 = array[j];
                            if (n4 != n3) {
                                break;
                            }
                            final int n5 = 1 << 32 - j;
                            if (~(n5 & n4) != -1) {
                                array[j] = array[j - 1];
                                break;
                            }
                            array[j] = Class41.method366(n5, n4);
                        }
                        n6 = (n2 | n3);
                    }
                    else {
                        n6 = array[b - 1];
                    }
                    array[b] = n6;
                    for (int n7 = b + 1; ~n7 >= -33; ++n7) {
                        if (~n3 == ~array[n7]) {
                            array[n7] = n6;
                        }
                    }
                    int n8 = 0;
                    for (byte b2 = 0; b > b2; ++b2) {
                        if ((n3 & Integer.MIN_VALUE >>> b2) != 0x0) {
                            if (this.anIntArray1606[n8] == 0) {
                                this.anIntArray1606[n8] = n;
                            }
                            n8 = this.anIntArray1606[n8];
                        }
                        else {
                            ++n8;
                        }
                        if (n8 >= this.anIntArray1606.length) {
                            final int[] anIntArray1606 = new int[2 * this.anIntArray1606.length];
                            for (int n9 = 0; ~this.anIntArray1606.length < ~n9; ++n9) {
                                anIntArray1606[n9] = this.anIntArray1606[n9];
                            }
                            this.anIntArray1606 = anIntArray1606;
                        }
                    }
                    if (n8 >= n) {
                        n = 1 + n8;
                    }
                    this.anIntArray1606[n8] = ~i;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nq.<init>(" + ((aByteArray1605 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2783(final byte b) {
        try {
            if (b < -120) {
                Class213.aClass58_1609 = null;
                Class213.aClass171_1604 = null;
                Class213.aClass155Array1611 = null;
                Class213.anIntArray1610 = null;
                Class213.anIntArray1607 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nq.A(" + b + ')');
        }
    }
    
    static {
        Class213.anIntArray1607 = new int[64];
        Class213.aClass171_1604 = new OutgoingOpcode(10, 16);
        Class213.aClass58_1609 = new IncomingOpcode(102, -1);
        Class213.anIntArray1610 = new int[4096];
    }
}
