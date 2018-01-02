import java.io.EOFException;
import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class17
{
    private int anInt200;
    private Class225 aClass225_201;
    static OutgoingOpcode aClass171_202;
    static int anInt203;
    private Class225 aClass225_204;
    static Class198 aClass198_205;
    private int anInt206;
    
    static final void method239(final int n, final int n2) {
        try {
            Class98 class98 = Class168.aClass377_1287.method3998(97);
            if (n <= 11) {
                Class17.anInt203 = -119;
            }
            while (class98 != null) {
                if (~(class98.aLong832 >> -1444989456 & 0xFFFFL) == ~n2) {
                    class98.method942(83);
                }
                class98 = Class168.aClass377_1287.method3995(-1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bg.G(" + n + ',' + n2 + ')');
        }
    }
    
    final byte[] method240(final int n, final boolean b) {
        try {
            if (b) {
                Class17.aClass198_205 = null;
            }
            synchronized (this.aClass225_204) {
                try {
                    if (n * 6 + 6 > this.aClass225_201.method2847(!b)) {
                        return null;
                    }
                    this.aClass225_201.method2846(6 * n, 0);
                    this.aClass225_201.method2849(0, Class159.aByteArray1255, -12913, 6);
                    final int n2 = (0xFF & Class159.aByteArray1255[2]) + ((Class159.aByteArray1255[1] & 0xFF) << 1688022760) + ((0xFF & Class159.aByteArray1255[0]) << 1168448688);
                    int n3 = (0xFF & Class159.aByteArray1255[5]) + (((Class159.aByteArray1255[3] & 0xFF) << 2124255056) - -(0xFF00 & Class159.aByteArray1255[4] << 1644622216));
                    if (n2 < 0 || n2 > this.anInt206) {
                        return null;
                    }
                    if (~n3 >= -1 || ~(this.aClass225_204.method2847(!b) / 520L) > ~n3) {
                        return null;
                    }
                    final byte[] array = new byte[n2];
                    int n4 = 0;
                    int n5 = 0;
                    while (~n2 < ~n4) {
                        if (n3 == 0) {
                            return null;
                        }
                        this.aClass225_204.method2846(520 * n3, 0);
                        int i = -n4 + n2;
                        if (~i < -513) {
                            i = 512;
                        }
                        this.aClass225_204.method2849(0, Class159.aByteArray1255, -12913, 8 + i);
                        final int n6 = ((0xFF & Class159.aByteArray1255[0]) << -1312835672) - -(0xFF & Class159.aByteArray1255[1]);
                        final int n7 = (0xFF00 & Class159.aByteArray1255[2] << -96038520) + (Class159.aByteArray1255[3] & 0xFF);
                        final int n8 = ((Class159.aByteArray1255[5] & 0xFF) << -657785976) + ((Class159.aByteArray1255[4] << -142719632 & 0xFF0000) + (Class159.aByteArray1255[6] & 0xFF));
                        final int n9 = Class159.aByteArray1255[7] & 0xFF;
                        if (~n != ~n6 || n5 != n7 || this.anInt200 != n9) {
                            return null;
                        }
                        if (n8 < 0 || ~(this.aClass225_204.method2847(true) / 520L) > ~n8) {
                            return null;
                        }
                        ++n5;
                        for (int n10 = 0; i > n10; ++n10) {
                            array[n4++] = Class159.aByteArray1255[8 + n10];
                        }
                        n3 = n8;
                    }
                    return array;
                }
                catch (IOException ex2) {
                    return null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bg.B(" + n + ',' + b + ')');
        }
    }
    
    static final void method241(final int n, final ha ha, final int n2, final int n3, final byte b, final int n4, final int n5, final String s) {
        try {
            if (Class246_Sub3_Sub4_Sub2_Sub1.aClass332_6508 == null || Class300.aClass332_2500 == null) {
                if (!Class332_Sub2.aClass207_5423.method2742(-71, Class38.anInt360) || !Class332_Sub2.aClass207_5423.method2742(-48, Class222.anInt1672)) {
                    ha.aa(n4, n5, n2, n, -Class355.anInt3017 + 255 << 544186680 | Class260.anInt3261, 1);
                }
                else {
                    Class246_Sub3_Sub4_Sub2_Sub1.aClass332_6508 = ha.method1758(Class324.method3685(Class332_Sub2.aClass207_5423, Class38.anInt360, 0), true);
                    final Class324 method3685 = Class324.method3685(Class332_Sub2.aClass207_5423, Class222.anInt1672, 0);
                    Class300.aClass332_2500 = ha.method1758(method3685, true);
                    method3685.method3691();
                    Class76_Sub11.aClass332_3795 = ha.method1758(method3685, true);
                }
            }
            if (Class246_Sub3_Sub4_Sub2_Sub1.aClass332_6508 != null && Class300.aClass332_2500 != null) {
                for (int n6 = (n2 - Class300.aClass332_2500.method3734() * 2) / Class246_Sub3_Sub4_Sub2_Sub1.aClass332_6508.method3734(), n7 = 0; ~n6 < ~n7; ++n7) {
                    Class246_Sub3_Sub4_Sub2_Sub1.aClass332_6508.method3735(Class300.aClass332_2500.method3734() + n4 + n7 * Class246_Sub3_Sub4_Sub2_Sub1.aClass332_6508.method3734(), n5);
                }
                Class300.aClass332_2500.method3735(n4, n5);
                Class76_Sub11.aClass332_3795.method3735(n4 + (n2 - Class76_Sub11.aClass332_3795.method3734()), n5);
            }
            Class98_Sub10_Sub34.aClass43_5730.method411((byte)114, 14 + n5, s, Class147.anInt1194 | 0xFF000000, -1, 3 + n4);
            ha.aa(n4, n + n5, n2, n3 + -n, -Class355.anInt3017 + 255 << 738306488 | Class260.anInt3261, 1);
            if (b != 58) {
                Class17.aClass198_205 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bg.E(" + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + b + ',' + n4 + ',' + n5 + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final boolean method242(boolean b, final int i, final byte b2, final byte[] array, final int n) {
        try {
            synchronized (this.aClass225_204) {
                try {
                    int n2;
                    if (!b) {
                        n2 = (int)((519L + this.aClass225_204.method2847(true)) / 520L);
                        if (~n2 == -1) {
                            n2 = 1;
                        }
                    }
                    else {
                        if (~(6 * n + 6) < ~this.aClass225_201.method2847(true)) {
                            return false;
                        }
                        this.aClass225_201.method2846(6 * n, 0);
                        this.aClass225_201.method2849(0, Class159.aByteArray1255, -12913, 6);
                        n2 = (Class159.aByteArray1255[5] & 0xFF) + ((0xFF & Class159.aByteArray1255[4]) << -1769789464) + (Class159.aByteArray1255[3] << 233682064 & 0xFF0000);
                        if (n2 <= 0 || ~(this.aClass225_204.method2847(true) / 520L) > ~n2) {
                            return false;
                        }
                    }
                    Class159.aByteArray1255[2] = (byte)i;
                    Class159.aByteArray1255[0] = (byte)(i >> 555589968);
                    Class159.aByteArray1255[3] = (byte)(n2 >> -111684720);
                    Class159.aByteArray1255[1] = (byte)(i >> -1570816984);
                    Class159.aByteArray1255[5] = (byte)n2;
                    Class159.aByteArray1255[4] = (byte)(n2 >> -423385592);
                    this.aClass225_201.method2846(n * 6, 0);
                    this.aClass225_201.method2852(6, 0, -1, Class159.aByteArray1255);
                    if (b2 != -41) {
                        return true;
                    }
                    int n3 = 0;
                    int n4 = 0;
                    while (i > n3) {
                        int n5 = 0;
                        if (b) {
                            this.aClass225_204.method2846(520 * n2, 0);
                            try {
                                this.aClass225_204.method2849(0, Class159.aByteArray1255, -12913, 8);
                            }
                            catch (EOFException ex2) {
                                break;
                            }
                            final int n6 = (0xFF & Class159.aByteArray1255[1]) + ((Class159.aByteArray1255[0] & 0xFF) << -2029480344);
                            n5 = (0xFF & Class159.aByteArray1255[6]) + ((Class159.aByteArray1255[5] & 0xFF) << -2069346712) + (0xFF0000 & Class159.aByteArray1255[4] << -94230768);
                            final int n7 = (Class159.aByteArray1255[3] & 0xFF) + (Class159.aByteArray1255[2] << -1770629848 & 0xFF00);
                            final int n8 = Class159.aByteArray1255[7] & 0xFF;
                            if (n6 != n || ~n4 != ~n7 || this.anInt200 != n8) {
                                return false;
                            }
                            if (~n5 > -1 || n5 > this.aClass225_204.method2847(true) / 520L) {
                                return false;
                            }
                        }
                        if (n5 == 0) {
                            b = false;
                            n5 = (int)((519L + this.aClass225_204.method2847(true)) / 520L);
                            if (~n5 == -1) {
                                ++n5;
                            }
                            if (n2 == n5) {
                                ++n5;
                            }
                        }
                        Class159.aByteArray1255[0] = (byte)(n >> 1054699720);
                        if (i + -n3 <= 512) {
                            n5 = 0;
                        }
                        Class159.aByteArray1255[2] = (byte)(n4 >> -272228248);
                        Class159.aByteArray1255[3] = (byte)n4;
                        Class159.aByteArray1255[1] = (byte)n;
                        Class159.aByteArray1255[7] = (byte)this.anInt200;
                        Class159.aByteArray1255[5] = (byte)(n5 >> 1720878632);
                        Class159.aByteArray1255[4] = (byte)(n5 >> -559801072);
                        Class159.aByteArray1255[6] = (byte)n5;
                        this.aClass225_204.method2846(n2 * 520, b2 + 41);
                        this.aClass225_204.method2852(8, 0, b2 + 40, Class159.aByteArray1255);
                        int n9 = -n3 + i;
                        if (~n9 < -513) {
                            n9 = 512;
                        }
                        this.aClass225_204.method2852(n9, n3, -1, array);
                        n2 = n5;
                        ++n4;
                        n3 += n9;
                    }
                    return true;
                }
                catch (IOException ex3) {
                    return false;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bg.D(" + b + ',' + i + ',' + b2 + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    public final String toString() {
        try {
            return "Cache:" + this.anInt200;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bg.toString()");
        }
    }
    
    static final void method243(final int n, final int n2, final int n3, final int n4, final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2, final int n5, final int n6) {
        try {
            Class168.method2533(n2, class246_Sub3_Sub4_Sub2.anInt5084, n5, 0, (byte)111, class246_Sub3_Sub4_Sub2.anInt5079, n6, class246_Sub3_Sub4_Sub2.aByte5088, n4, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bg.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((class246_Sub3_Sub4_Sub2 != null) ? "{...}" : "null") + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    public static void method244(final int n) {
        try {
            Class17.aClass198_205 = null;
            Class17.aClass171_202 = null;
            if (n != -24652) {
                Class17.anInt203 = -28;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bg.F(" + n + ')');
        }
    }
    
    final boolean method245(final boolean b, final int n, final int n2, final byte[] array) {
        try {
            synchronized (this.aClass225_204) {
                if (~n > -1 || n > this.anInt206) {
                    throw new IllegalArgumentException();
                }
                if (b) {
                    this.method240(29, false);
                }
                boolean b2 = this.method242(true, n, (byte)(-41), array, n2);
                if (!b2) {
                    b2 = this.method242(false, n, (byte)(-41), array, n2);
                }
                return b2;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bg.A(" + b + ',' + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class17(final int anInt200, final Class225 aClass225_204, final Class225 aClass225_205, final int anInt201) {
        this.aClass225_201 = null;
        this.aClass225_204 = null;
        this.anInt206 = 65000;
        try {
            this.aClass225_201 = aClass225_205;
            this.anInt206 = anInt201;
            this.anInt200 = anInt200;
            this.aClass225_204 = aClass225_204;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bg.<init>(" + anInt200 + ',' + ((aClass225_204 != null) ? "{...}" : "null") + ',' + ((aClass225_205 != null) ? "{...}" : "null") + ',' + anInt201 + ')');
        }
    }
    
    static {
        Class17.aClass171_202 = new OutgoingOpcode(82, 8);
    }
}
