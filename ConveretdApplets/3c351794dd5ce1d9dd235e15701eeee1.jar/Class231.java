// 
// Decompiled by Procyon v0.5.30
// 

final class Class231
{
    static Class324 aClass324_1733;
    static int[] anIntArray1734;
    private int anInt1735;
    int anInt1736;
    Class11 aClass11_1737;
    int anInt1738;
    static int anInt1739;
    
    static final String method2873(final int n) {
        try {
            if (n != 0) {
                Class231.aClass324_1733 = null;
            }
            if (Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540 || Class266.aClass98_Sub46_Sub8_1994 == null) {
                return "";
            }
            return Class266.aClass98_Sub46_Sub8_1994.aString5994;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "op.C(" + n + ')');
        }
    }
    
    static final Class246_Sub3_Sub4_Sub2 method2874(final int n, final int n2, final int n3, final int n4) {
        try {
            final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2][n3];
            if (n4 <= 42) {
                method2877(-69);
            }
            if (class172 == null) {
                return null;
            }
            Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2 = null;
            int n5 = -1;
            for (Class154 class173 = class172.aClass154_1325; class173 != null; class173 = class173.aClass154_1233) {
                final Class246_Sub3_Sub4 aClass246_Sub3_Sub4_1232 = class173.aClass246_Sub3_Sub4_1232;
                if (aClass246_Sub3_Sub4_1232 instanceof Class246_Sub3_Sub4_Sub2) {
                    final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub3 = (Class246_Sub3_Sub4_Sub2)aClass246_Sub3_Sub4_1232;
                    final int n6 = class246_Sub3_Sub4_Sub3.method3034(0) * 256 - 256 + 252;
                    final int n7 = -n6 + class246_Sub3_Sub4_Sub3.anInt5084 >> 734785737;
                    final int n8 = class246_Sub3_Sub4_Sub3.anInt5079 + -n6 >> -1810572375;
                    final int n9 = class246_Sub3_Sub4_Sub3.anInt5084 + n6 >> 1460607529;
                    final int n10 = class246_Sub3_Sub4_Sub3.anInt5079 + n6 >> -397287991;
                    if (n2 >= n7 && n3 >= n8 && ~n2 >= ~n9 && ~n3 >= ~n10) {
                        final int n11 = (n10 + 1 - n3) * (n9 + 1 + -n2);
                        if (~n11 < ~n5) {
                            n5 = n11;
                            class246_Sub3_Sub4_Sub2 = class246_Sub3_Sub4_Sub3;
                        }
                    }
                }
            }
            return class246_Sub3_Sub4_Sub2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "op.E(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static final void method2875(final int n, final int n2) {
        try {
            if (Class331.anIntArray2810 == null || ~Class331.anIntArray2810.length > ~n2) {
                Class331.anIntArray2810 = new int[n2];
            }
            if (n != 256) {
                method2875(-98, 95);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "op.F(" + n + ',' + n2 + ')');
        }
    }
    
    final synchronized Class324 method2876(final byte b) {
        try {
            if (b != 126) {
                Class231.anInt1739 = -116;
            }
            final Class324 class324 = (Class324)this.aClass11_1737.aClass79_126.method802(-126, this.anInt1735);
            if (class324 != null) {
                return class324;
            }
            final Class324 method3685 = Class324.method3685(this.aClass11_1737.aClass207_124, this.anInt1735, 0);
            if (method3685 != null) {
                this.aClass11_1737.aClass79_126.method805(this.anInt1735, method3685, (byte)(-80));
            }
            return method3685;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "op.B(" + b + ')');
        }
    }
    
    public static void method2877(final int n) {
        try {
            Class231.aClass324_1733 = null;
            if (n <= 0) {
                method2878(16);
            }
            Class231.anIntArray1734 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "op.H(" + n + ')');
        }
    }
    
    static final void method2878(final int n) {
        try {
            if (n != 2) {
                Class231.anInt1739 = -105;
            }
            Class61.method538(11, false);
            Class373_Sub3.method3977(true);
            System.gc();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "op.D(" + n + ')');
        }
    }
    
    private final void method2879(final int n, final Class98_Sub22 class98_Sub22, final int n2) {
        try {
            if (n != 1) {
                if (~n != 0xFFFFFFFD) {
                    return;
                }
                this.anInt1738 = class98_Sub22.readUnsignedByte((byte)84);
                this.anInt1736 = class98_Sub22.readUnsignedByte((byte)(-109));
                if (!client.aBoolean3553) {
                    return;
                }
            }
            this.anInt1735 = class98_Sub22.readShort((byte)127);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "op.G(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    final void method2880(final boolean b, final Class98_Sub22 class98_Sub22) {
        try {
            if (b) {
                while (true) {
                    final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-119));
                    if (unsignedByte == 0) {
                        break;
                    }
                    this.method2879(unsignedByte, class98_Sub22, -127);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "op.A(" + b + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class231.anIntArray1734 = new int[2];
    }
}
