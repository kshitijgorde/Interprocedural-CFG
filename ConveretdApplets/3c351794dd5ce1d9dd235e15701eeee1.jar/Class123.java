import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class123
{
    static Class79 aClass79_1010;
    static long aLong1011;
    
    abstract void method2202(final int p0, final int p1, final byte[] p2, final int p3) throws IOException;
    
    abstract boolean method2203(final int p0, final int p1) throws IOException;
    
    abstract void method2204(final int p0);
    
    static final boolean method2205(final int n, final byte[] array, final int n2, final int n3, final int n4, final int n5) {
        try {
            if (n5 < 85) {
                method2210(81);
            }
            boolean b = true;
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array);
            int n6 = -1;
            while (true) {
                final int method1208 = class98_Sub22.method1208(3893);
                if (~method1208 == -1) {
                    break;
                }
                n6 += method1208;
                int n7 = 0;
                int n8 = 0;
                while (true) {
                    if (n8 == 0) {
                        final int smart = class98_Sub22.readSmart(1689622712);
                        if (~smart == -1) {
                            break;
                        }
                        n7 += smart - 1;
                        final int n9 = 0x3F & n7;
                        final int n10 = 0x3F & n7 >> 145149062;
                        final int n11 = class98_Sub22.readUnsignedByte((byte)(-122)) >> -2093245406;
                        final int n12 = n10 - -n2;
                        final int n13 = n9 - -n;
                        if (~n12 >= -1 || n13 <= 0 || ~(-1 + n3) >= ~n12 || n13 >= -1 + n4) {
                            continue;
                        }
                        final Class352 method1209 = Class130.aClass302_1028.method3546(n6, (byte)119);
                        if (~n11 == 0xFFFFFFE9 && ~Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub11_4038.method596((byte)120) == -1 && method1209.anInt2998 == 0 && method1209.actionCount != 1 && !method1209.aBoolean2969) {
                            continue;
                        }
                        if (!method1209.method3857(18182)) {
                            ++Class132.anInt1043;
                            b = false;
                        }
                        n8 = 1;
                    }
                    else {
                        if (class98_Sub22.readSmart(1689622712) == 0) {
                            break;
                        }
                        class98_Sub22.readUnsignedByte((byte)(-124));
                    }
                }
            }
            return b;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ida.I(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    static final void method2206(final Class293 class293, final byte b) {
        try {
            if (b == 19 && class293.anInt2307 == Class375.anInt3168) {
                if (Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aString6537 == null) {
                    class293.anInt2343 = 0;
                    class293.anInt2210 = 0;
                }
                else {
                    class293.anInt2310 = 150;
                    class293.anInt2218 = ((int)(256.0 * Math.sin(Class215.anInt1614 / 40.0)) & 0x7FF);
                    class293.anInt2343 = za_Sub2.anInt6080;
                    class293.anInt2233 = 5;
                    class293.anInt2210 = Class98_Sub32.method1438(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aString6537, b + 6224);
                    class293.anInt2312 = 0;
                    class293.anInt2303 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6350;
                    class293.anInt2287 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6419;
                    class293.anInt2208 = Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6385;
                    final Class97 class294 = (~class293.anInt2208 == 0x0) ? null : Class151_Sub7.aClass183_5001.method2623(class293.anInt2208, 16383);
                    if (class294 != null) {
                        Class280.method3327(class293.anInt2303, class294, (byte)93);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ida.J(" + ((class293 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    abstract void method2207(final int p0);
    
    abstract int method2208(final byte[] p0, final int p1, final int p2, final int p3) throws IOException;
    
    static final short[] method2209(final int n, final int n2, final short[] array) {
        try {
            if (n2 != 21119) {
                Class123.aClass79_1010 = null;
            }
            final short[] array2 = new short[n];
            Class236.method2895(array, 0, array2, 0, n);
            return array2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ida.H(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2210(final int n) {
        try {
            Class123.aClass79_1010 = null;
            if (n != 145149062) {
                Class123.aClass79_1010 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ida.G(" + n + ')');
        }
    }
    
    static {
        Class123.aClass79_1010 = new Class79(8);
    }
}
