import java.awt.Canvas;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class98_Sub32 extends Class98
{
    int anInt4105;
    static OutgoingOpcode aClass171_4106;
    static Class293 aClass293_4107;
    int[] anIntArray4108;
    static int[] anIntArray4109;
    int anInt4110;
    static int[][] anIntArrayArray4111;
    
    static final int method1433(final int n, int n2, final int n3, final int n4) {
        try {
            n2 &= 0x3;
            if (n2 == 0) {
                return n3;
            }
            if (n4 != -7175) {
                method1437(125, 75, (byte)(-103), -13);
            }
            if (n2 == 1) {
                return n;
            }
            if (n2 == 2) {
                return -n3 + 4095;
            }
            return -n + 4095;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nca.G(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    abstract void method1434(final Graphics p0, final int p1, final byte p2, final int p3, final int p4, final int p5, final int p6, final int p7);
    
    static final String method1435(final String s, final String s2, final byte b, final char c) {
        try {
            final int length = s.length();
            final int length2 = s2.length();
            if (b >= -57) {
                method1439(96, 20, 39, 5);
            }
            int n = length;
            final int n2 = -1 + length2;
            if (n2 != 0) {
                int index = 0;
                while (true) {
                    index = s.indexOf(c, index);
                    if (index < 0) {
                        break;
                    }
                    ++index;
                    n += n2;
                }
            }
            final StringBuffer sb = new StringBuffer(n);
            int n3 = 0;
            while (true) {
                final int index2 = s.indexOf(c, n3);
                if (index2 < 0) {
                    break;
                }
                sb.append(s.substring(n3, index2));
                n3 = index2 + 1;
                sb.append(s2);
            }
            sb.append(s.substring(n3));
            return sb.toString();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nca.E(" + ((s != null) ? "{...}" : "null") + ',' + ((s2 != null) ? "{...}" : "null") + ',' + b + ',' + c + ')');
        }
    }
    
    static final void method1436(final int n, final boolean b) {
        try {
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4041);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub15_4034);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub15_4058);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub11_4038);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub14_4049);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub25_4039);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub24_4047);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub13_4063);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub26_4035);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub7_4073);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub20_4056);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub10_4070);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub28_4064);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4044);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4055);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub6_4033);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub17_4046);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub5_4065);
            Class151_Sub1.method2450((byte)103);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 2, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub16_4040);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub12_4048);
            Class98_Sub46_Sub13_Sub1.method1593((byte)76);
            if (n > -77) {
                method1435(null, null, (byte)62, 'n');
            }
            Class374.method3980((byte)121);
            Class33.aBoolean316 = true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nca.C(" + n + ',' + b + ')');
        }
    }
    
    static final void method1437(final int n, final int n2, final byte b, final int n3) {
        try {
            final int n4 = Class272.anInt2038 + n;
            final int n5 = aa_Sub2.anInt3562 + n3;
            if (Class98_Sub46_Sub1.aClass172ArrayArrayArray5948 != null && n >= 0 && n3 >= 0 && ~Class165.anInt1276 < ~n && ~n3 > ~Class98_Sub10_Sub7.anInt5572 && (~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076.method564((byte)122) != -1 || ~n2 == ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088)) {
                final Class98_Sub45 class98_Sub45 = (Class98_Sub45)Class146.aClass377_1180.method3990(n2 << 1137066972 | n5 << -790678898 | n4, -1);
                if (class98_Sub45 == null) {
                    Class281.method3333(n2, n, n3);
                }
                else {
                    if (b <= 38) {
                        Class98_Sub32.anIntArrayArray4111 = null;
                    }
                    final Class98_Sub26 class98_Sub46 = (Class98_Sub26)class98_Sub45.aClass148_4254.method2418(32);
                    if (class98_Sub46 == null) {
                        Class281.method3333(n2, n, n3);
                    }
                    else {
                        Class246_Sub3_Sub2_Sub1 class246_Sub3_Sub2_Sub1 = (Class246_Sub3_Sub2_Sub1)Class281.method3333(n2, n, n3);
                        if (class246_Sub3_Sub2_Sub1 == null) {
                            class246_Sub3_Sub2_Sub1 = new Class246_Sub3_Sub2_Sub1(n << -489573239, Class78.aSArray594[n2].method3420(n3, -12639, n), n3 << 1477649545, n2, n2);
                        }
                        else {
                            final Class246_Sub3_Sub2_Sub1 class246_Sub3_Sub2_Sub2 = class246_Sub3_Sub2_Sub1;
                            final Class246_Sub3_Sub2_Sub1 class246_Sub3_Sub2_Sub3 = class246_Sub3_Sub2_Sub1;
                            final int n6 = -1;
                            class246_Sub3_Sub2_Sub3.anInt6343 = n6;
                            class246_Sub3_Sub2_Sub2.anInt6341 = n6;
                        }
                        class246_Sub3_Sub2_Sub1.anInt6338 = class98_Sub46.anInt4032;
                        class246_Sub3_Sub2_Sub1.anInt6340 = class98_Sub46.anInt4031;
                        while (true) {
                            final Class98_Sub26 class98_Sub47 = (Class98_Sub26)class98_Sub45.aClass148_4254.method2417(88);
                            if (class98_Sub47 == null) {
                                break;
                            }
                            if (class246_Sub3_Sub2_Sub1.anInt6340 != class98_Sub47.anInt4031) {
                                class246_Sub3_Sub2_Sub1.anInt6346 = class98_Sub47.anInt4032;
                                class246_Sub3_Sub2_Sub1.anInt6341 = class98_Sub47.anInt4031;
                                while (true) {
                                    final Class98_Sub26 class98_Sub48 = (Class98_Sub26)class98_Sub45.aClass148_4254.method2417(101);
                                    if (class98_Sub48 == null) {
                                        break;
                                    }
                                    if (class98_Sub48.anInt4031 == class246_Sub3_Sub2_Sub1.anInt6340 || ~class246_Sub3_Sub2_Sub1.anInt6341 == ~class98_Sub48.anInt4031) {
                                        continue;
                                    }
                                    class246_Sub3_Sub2_Sub1.anInt6343 = class98_Sub48.anInt4031;
                                    class246_Sub3_Sub2_Sub1.anInt6337 = class98_Sub48.anInt4032;
                                }
                                break;
                            }
                        }
                        final int method1538 = Class98_Sub46_Sub2_Sub2.method1538(n2, (n3 << -1106673015) + 256, 256 + (n << 1297062281), 24111);
                        class246_Sub3_Sub2_Sub1.anInt6339 = 0;
                        class246_Sub3_Sub2_Sub1.aByte5088 = (byte)n2;
                        class246_Sub3_Sub2_Sub1.anInt5079 = n3 << 657245129;
                        class246_Sub3_Sub2_Sub1.anInt5089 = method1538;
                        class246_Sub3_Sub2_Sub1.anInt5084 = n << -121249015;
                        class246_Sub3_Sub2_Sub1.aByte5081 = (byte)n2;
                        if (Class1.method162(n3, (byte)(-102), n)) {
                            final Class246_Sub3_Sub2_Sub1 class246_Sub3_Sub2_Sub4 = class246_Sub3_Sub2_Sub1;
                            ++class246_Sub3_Sub2_Sub4.aByte5081;
                        }
                        Class266.method3239(n2, n, n3, method1538, class246_Sub3_Sub2_Sub1);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nca.F(" + n + ',' + n2 + ',' + b + ',' + n3 + ')');
        }
    }
    
    static final int method1438(final String s, final int n) {
        try {
            final int length = s.length();
            int n2 = 0;
            for (int n3 = 0; ~length < ~n3; ++n3) {
                n2 = -n2 + ((n2 << -672898683) + s.charAt(n3));
            }
            if (n != 6243) {
                method1436(-52, false);
            }
            return n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nca.I(" + ((s != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final void method1439(final int n, final int n2, final int n3, final int n4) {
        final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2][n3];
        if (class172 != null) {
            final Class246_Sub3_Sub5 aClass246_Sub3_Sub5_1334 = class172.aClass246_Sub3_Sub5_1334;
            final Class246_Sub3_Sub5 aClass246_Sub3_Sub5_1335 = class172.aClass246_Sub3_Sub5_1326;
            if (aClass246_Sub3_Sub5_1334 != null) {
                aClass246_Sub3_Sub5_1334.aShort6165 = (short)(aClass246_Sub3_Sub5_1334.aShort6165 * n4 / (16 << Class151_Sub8.anInt5015 - 7));
                aClass246_Sub3_Sub5_1334.aShort6163 = (short)(aClass246_Sub3_Sub5_1334.aShort6163 * n4 / (16 << Class151_Sub8.anInt5015 - 7));
            }
            if (aClass246_Sub3_Sub5_1335 != null) {
                aClass246_Sub3_Sub5_1335.aShort6165 = (short)(aClass246_Sub3_Sub5_1335.aShort6165 * n4 / (16 << Class151_Sub8.anInt5015 - 7));
                aClass246_Sub3_Sub5_1335.aShort6163 = (short)(aClass246_Sub3_Sub5_1335.aShort6163 * n4 / (16 << Class151_Sub8.anInt5015 - 7));
            }
        }
    }
    
    public static void method1440(final int n) {
        try {
            Class98_Sub32.aClass293_4107 = null;
            Class98_Sub32.anIntArrayArray4111 = null;
            Class98_Sub32.aClass171_4106 = null;
            Class98_Sub32.anIntArray4109 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nca.H(" + n + ')');
        }
    }
    
    abstract void method1441(final int p0, final int p1, final int p2, final Canvas p3);
    
    static {
        Class98_Sub32.aClass293_4107 = null;
        Class98_Sub32.anIntArray4109 = new int[5];
        Class98_Sub32.aClass171_4106 = new OutgoingOpcode(26, -1);
        Class98_Sub32.anIntArrayArray4111 = new int[][] { { 12, 12, 12, 12 }, { 12, 12, 12, 12, 12, 5 }, { 5, 5, 1, 1 }, { 5, 1, 1, 5 }, { 5, 5, 5 }, { 5, 5, 5 }, { 12, 12, 12, 12, 12, 12 }, { 1, 12, 12, 12, 12, 12 }, { 1, 1, 7, 1 }, { 8, 9, 9, 8, 8, 3, 1, 9 }, { 8, 8, 9, 8, 9, 9 }, { 10, 10, 11, 11, 11, 7, 3, 7 }, { 12, 12, 12, 12 } };
    }
}
