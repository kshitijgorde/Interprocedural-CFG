// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class98_Sub10 extends Class98
{
    Class98_Sub10[] aClass98_Sub10Array3857;
    static boolean aBoolean3858;
    Class223 aClass223_3859;
    int anInt3860;
    boolean aBoolean3861;
    static boolean aBoolean3862;
    Class16 aClass16_3863;
    
    int[] method990(final int n, final int n2) {
        try {
            if (n != 255) {
                this.aClass98_Sub10Array3857 = null;
            }
            throw new IllegalStateException("This operation does not have a monochrome output");
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dt.G(" + n + ',' + n2 + ')');
        }
    }
    
    void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b > -92) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dt.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    int method992(final int n) {
        try {
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dt.S(" + n + ')');
        }
    }
    
    void method993(final int n) {
        try {
            if (n != 1002) {
                Class98_Sub10.aBoolean3862 = true;
            }
            if (this.aBoolean3861) {
                this.aClass16_3863.method236(34);
                this.aClass16_3863 = null;
            }
            else {
                this.aClass223_3859.method2831(0);
                this.aClass223_3859 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dt.P(" + n + ')');
        }
    }
    
    final int[][] method994(final int n, final int n2, final int n3) {
        try {
            if (n2 != 24431) {
                this.aClass16_3863 = null;
            }
            if (!this.aClass98_Sub10Array3857[n3].aBoolean3861) {
                return this.aClass98_Sub10Array3857[n3].method997(-119, n);
            }
            final int[] method990 = this.aClass98_Sub10Array3857[n3].method990(n2 - 24176, n);
            return new int[][] { method990, method990, method990 };
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dt.L(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method995(final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub1, final byte b, final boolean b2) {
        try {
            if (Class359.anInt3058 < 400) {
                Class141 class141 = class246_Sub3_Sub4_Sub2_Sub1.aClass141_6504;
                if (b != 55) {
                    Class98_Sub10.aBoolean3858 = true;
                }
                if (class141.anIntArray1109 != null) {
                    class141 = class141.method2300(Class75.aClass140_584, (byte)120);
                    if (class141 == null) {
                        return;
                    }
                }
                if (class141.aBoolean1116) {
                    String s = class141.aString1144;
                    if (class141.anInt1115 != 0) {
                        s = s + Class108.method1730(Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6519, class141.anInt1115, b + 16328) + " (" + ((Class64_Sub2.aClass279_3643 != Class4.aClass279_86) ? Class309.aClass309_2614.method3615(Class374.anInt3159, (byte)25) : Class309.aClass309_2616.method3615(Class374.anInt3159, (byte)25)) + class141.anInt1115 + ")";
                    }
                    if (Class98_Sub10_Sub9.aBoolean5585 && !b2) {
                        final Class149 class142 = (~Class98_Sub46_Sub1.anInt5945 != 0x0) ? Class98_Sub43_Sub1.aClass365_5897.method3940((byte)31, Class98_Sub46_Sub1.anInt5945) : null;
                        if (~(0x2 & Class98_Sub4.anInt3826) != -1 && (class142 == null || ~class141.method2305(Class98_Sub46_Sub1.anInt5945, class142.anInt1202, (byte)125) != ~class142.anInt1202)) {
                            Class293.method3470(false, true, class246_Sub3_Sub4_Sub2_Sub1.anInt6369, Class336.anInt2823, 0, Class246_Sub3_Sub3.aString6156 + " -> <col=ffff00>" + s, false, 0, 48, class246_Sub3_Sub4_Sub2_Sub1.anInt6369, -1, false, Class287_Sub2.aString3272);
                        }
                    }
                    if (!b2) {
                        String[] array = class141.aStringArray1148;
                        if (Class34.aBoolean324) {
                            array = Class98_Sub10_Sub6.method1022(b ^ 0xFFFFFC84, array);
                        }
                        if (array != null) {
                            for (int n = 4; ~n <= -1; --n) {
                                if (array[n] != null && (class141.aByte1129 == 0 || !array[n].equalsIgnoreCase(Class309.aClass309_2609.method3615(Class374.anInt3159, (byte)25)))) {
                                    int n2 = 0;
                                    if (n == 0) {
                                        n2 = 10;
                                    }
                                    int n3 = Class284_Sub2.anInt5186;
                                    if (n == 1) {
                                        n2 = 25;
                                    }
                                    if (n == 2) {
                                        n2 = 11;
                                    }
                                    if (~n == 0xFFFFFFFC) {
                                        n2 = 12;
                                    }
                                    if (class141.anInt1143 == n) {
                                        n3 = class141.anInt1154;
                                    }
                                    if (n == 4) {
                                        n2 = 17;
                                    }
                                    if (class141.anInt1114 == n) {
                                        n3 = class141.anInt1110;
                                    }
                                    Class293.method3470(false, true, class246_Sub3_Sub4_Sub2_Sub1.anInt6369, array[n].equalsIgnoreCase(Class309.aClass309_2609.method3615(Class374.anInt3159, (byte)25)) ? class141.anInt1099 : n3, 0, "<col=ffff00>" + s, false, 0, n2, class246_Sub3_Sub4_Sub2_Sub1.anInt6369, -1, false, array[n]);
                                }
                            }
                        }
                        if (class141.aByte1129 == 1 && array != null) {
                            for (int i = 4; i >= 0; --i) {
                                if (array[i] != null && array[i].equalsIgnoreCase(Class309.aClass309_2609.method3615(Class374.anInt3159, (byte)25))) {
                                    int n4 = 0;
                                    if (~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.anInt6519 > ~class141.anInt1115) {
                                        n4 = 2000;
                                    }
                                    int n5 = 0;
                                    if (i == 0) {
                                        n5 = 10;
                                    }
                                    if (~i == 0xFFFFFFFE) {
                                        n5 = 25;
                                    }
                                    if (~i == 0xFFFFFFFD) {
                                        n5 = 11;
                                    }
                                    if (~i == 0xFFFFFFFC) {
                                        n5 = 12;
                                    }
                                    if (~i == 0xFFFFFFFB) {
                                        n5 = 17;
                                    }
                                    if (~n5 != -1) {
                                        n5 = (short)(n5 + n4);
                                    }
                                    Class293.method3470(false, true, class246_Sub3_Sub4_Sub2_Sub1.anInt6369, class141.anInt1099, 0, "<col=ffff00>" + s, false, 0, n5, class246_Sub3_Sub4_Sub2_Sub1.anInt6369, -1, false, array[i]);
                                }
                            }
                        }
                    }
                    Class293.method3470(false, true, class246_Sub3_Sub4_Sub2_Sub1.anInt6369, Class16.anInt190, 0, "<col=ffff00>" + s, false, 0, 1002, class246_Sub3_Sub4_Sub2_Sub1.anInt6369, -1, b2, Class309.aClass309_2608.method3615(Class374.anInt3159, (byte)25));
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dt.N(" + ((class246_Sub3_Sub4_Sub2_Sub1 != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ')');
        }
    }
    
    int method996(final byte b) {
        try {
            if (b > -119) {
                return 85;
            }
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dt.M(" + b + ')');
        }
    }
    
    int[][] method997(final int n, final int n2) {
        try {
            if (n > -76) {
                method999((byte)1);
            }
            throw new IllegalStateException("This operation does not have a colour output");
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dt.C(" + n + ',' + n2 + ')');
        }
    }
    
    void method998(final int n, final int n2, final int n3) {
        try {
            final int n4 = (n3 == ~this.anInt3860) ? n : this.anInt3860;
            if (this.aBoolean3861) {
                this.aClass16_3863 = new Class16(n4, n, n2);
            }
            else {
                this.aClass223_3859 = new Class223(n4, n, n2);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dt.O(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final void method999(final byte b) {
        try {
            if (~Class177.anInt1376 != 0xFFFFFFFC) {
                if (~Class177.anInt1376 == 0xFFFFFFF8) {
                    Class61.method538(8, false);
                }
                else if (Class177.anInt1376 == 10) {
                    Class61.method538(11, false);
                }
            }
            else {
                Class61.method538(4, false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dt.R(" + b + ')');
        }
    }
    
    Class98_Sub10(final int n, final boolean aBoolean3861) {
        try {
            this.aBoolean3861 = aBoolean3861;
            this.aClass98_Sub10Array3857 = new Class98_Sub10[n];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dt.<init>(" + n + ',' + aBoolean3861 + ')');
        }
    }
    
    final int[] method1000(final int n, final int n2, final int n3) {
        try {
            if (n3 != 0) {
                this.method993(-48);
            }
            if (this.aClass98_Sub10Array3857[n2].aBoolean3861) {
                return this.aClass98_Sub10Array3857[n2].method990(255, n);
            }
            return this.aClass98_Sub10Array3857[n2].method997(-94, n)[0];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dt.Q(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    void method1001(final byte b) {
        try {
            if (b != 66) {
                this.anInt3860 = 121;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dt.I(" + b + ')');
        }
    }
}
