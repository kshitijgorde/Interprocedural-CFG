import jaggl.OpenGL;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub16 extends Class98_Sub46
{
    static int anInt6044;
    Class7[] aClass7Array6045;
    static Class79 aClass79_6046;
    private int anInt6047;
    private byte[][] aByteArrayArray6048;
    
    static final void method1613(int n, final int n2, final byte[] array, final int n3, int n4, final int n5, final int n6) {
        try {
            if (~n < n3 && !Class81.method815(n, 0)) {
                throw new IllegalArgumentException("");
            }
            if (~n4 < -1 && !Class81.method815(n4, 0)) {
                throw new IllegalArgumentException("");
            }
            final int method3014 = Class246_Sub3_Sub3.method3014(1, n6);
            int n7 = 0;
            int n8 = (~n <= ~n4) ? n4 : n;
            int n9 = n >> 1044223969;
            int n10 = n4 >> -694038175;
            byte[] array2 = array;
            byte[] array3 = new byte[method3014 * (n10 * n9)];
            while (true) {
                OpenGL.glTexImage2Dub(n2, n7, n5, n, n4, 0, n6, 5121, array2, 0);
                if (~n8 >= -2) {
                    break;
                }
                final int n11 = method3014 * n;
                for (int i = 0; i < method3014; ++i) {
                    int n12 = i;
                    int n13 = i;
                    int n14 = n13 - -n11;
                    for (int n15 = 0; ~n15 > ~n10; ++n15) {
                        for (int n16 = 0; ~n9 < ~n16; ++n16) {
                            final byte b = array2[n13];
                            final int n17 = n13 + method3014;
                            final byte b2 = (byte)(b + array2[n17]);
                            n13 = n17 + method3014;
                            final byte b3 = (byte)(b2 + array2[n14]);
                            final int n18 = n14 + method3014;
                            final byte b4 = (byte)(b3 + array2[n18]);
                            n14 = n18 + method3014;
                            array3[n12] = (byte)(b4 >> 1943518018);
                            n12 += method3014;
                        }
                        n13 += n11;
                        n14 += n11;
                    }
                }
                final byte[] array4 = array3;
                array3 = array2;
                n = n9;
                array2 = array4;
                n4 = n10;
                n10 >>= 1;
                n8 >>= 1;
                ++n7;
                n9 >>= 1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jea.F(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    final boolean method1614(final byte b) {
        try {
            if (this.aClass7Array6045 != null) {
                return true;
            }
            if (this.aByteArrayArray6048 == null) {
                synchronized (Class64_Sub15.aClass207_3679) {
                    if (!Class64_Sub15.aClass207_3679.method2756(false, this.anInt6047)) {
                        return false;
                    }
                    final int[] method2743 = Class64_Sub15.aClass207_3679.method2743(this.anInt6047, 6341);
                    this.aByteArrayArray6048 = new byte[method2743.length][];
                    for (int i = 0; i < method2743.length; ++i) {
                        this.aByteArrayArray6048[i] = Class64_Sub15.aClass207_3679.method2745(method2743[i], this.anInt6047, false);
                    }
                }
            }
            boolean b2 = true;
            for (int n = 0; ~this.aByteArrayArray6048.length < ~n; ++n) {
                final int[] method2744 = (int[])this.aByteArrayArray6048[n];
                final Class98_Sub22 class98_Sub22 = new Class98_Sub22((byte[])method2744);
                class98_Sub22.anInt3991 = 1;
                final int short1 = class98_Sub22.readShort((byte)127);
                synchronized (Class64_Sub2.aClass207_3644) {
                    b2 &= Class64_Sub2.aClass207_3644.method2742(-89, short1);
                }
            }
            if (!b2) {
                return false;
            }
            final Class148 class148 = new Class148();
            int[] method2744;
            synchronized (Class64_Sub15.aClass207_3679) {
                this.aClass7Array6045 = new Class7[Class64_Sub15.aClass207_3679.method2761(0, this.anInt6047)];
                method2744 = Class64_Sub15.aClass207_3679.method2743(this.anInt6047, 6341);
            }
            for (int j = 0; j < ((byte[])method2744).length; ++j) {
                final byte[] array = this.aByteArrayArray6048[j];
                final Class98_Sub22 class98_Sub23 = new Class98_Sub22(array);
                class98_Sub23.anInt3991 = 1;
                final int short2 = class98_Sub23.readShort((byte)127);
                Class98_Sub1 class98_Sub24 = null;
                for (Class98_Sub1 class98_Sub25 = (Class98_Sub1)class148.method2418(32); class98_Sub25 != null; class98_Sub25 = (Class98_Sub1)class148.method2417(92)) {
                    if (short2 == class98_Sub25.anInt3813) {
                        class98_Sub24 = class98_Sub25;
                        break;
                    }
                }
                if (class98_Sub24 == null) {
                    synchronized (Class64_Sub2.aClass207_3644) {
                        class98_Sub24 = new Class98_Sub1(short2, Class64_Sub2.aClass207_3644.method2733(short2, -118));
                    }
                    class148.method2419(class98_Sub24, -20911);
                }
                this.aClass7Array6045[method2744[j]] = new Class7(array, class98_Sub24);
            }
            this.aByteArrayArray6048 = null;
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jea.B(" + b + ')');
        }
    }
    
    final boolean method1615(final int n, final boolean b) {
        try {
            return b || this.aClass7Array6045[n].aBoolean95;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jea.C(" + n + ',' + b + ')');
        }
    }
    
    public static void method1616(final int n) {
        try {
            if (n >= -3) {
                Class98_Sub46_Sub16.aClass79_6046 = null;
            }
            Class98_Sub46_Sub16.aClass79_6046 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jea.D(" + n + ')');
        }
    }
    
    final boolean method1617(final boolean b, final int n) {
        try {
            return b || this.aClass7Array6045[n].aBoolean102;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jea.A(" + b + ',' + n + ')');
        }
    }
    
    static final void method1618(final int anInt3794, final byte b) {
        try {
            Class76_Sub10.anInt3794 = anInt3794;
            if (b == -85) {
                Class64_Sub5.aClass79_3650.method794(105);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jea.E(" + anInt3794 + ',' + b + ')');
        }
    }
    
    Class98_Sub46_Sub16(final int anInt6047) {
        try {
            this.anInt6047 = anInt6047;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jea.<init>(" + anInt6047 + ')');
        }
    }
    
    final boolean method1619(final int n, final int n2) {
        try {
            if (n2 != 31239) {
                method1613(53, 0, null, 91, 99, -3, -67);
            }
            return this.aClass7Array6045[n].aBoolean104;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jea.G(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class98_Sub46_Sub16.aClass79_6046 = new Class79(30);
    }
}
