// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub31_Sub1 extends Class98_Sub31
{
    private Class98_Sub31_Sub2 aClass98_Sub31_Sub2_5815;
    static Class305_Sub1 aClass305_Sub1_5816;
    Class148 aClass148_5817;
    Class98_Sub31_Sub3 aClass98_Sub31_Sub3_5818;
    
    @Override
    final int method1326() {
        try {
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ap.G()");
        }
    }
    
    @Override
    final void method1325(final int[] array, final int n, final int n2) {
        try {
            this.aClass98_Sub31_Sub3_5818.method1325(array, n, n2);
        Label_0152:
            for (Class98_Sub16 class98_Sub16 = (Class98_Sub16)this.aClass148_5817.method2418(32); class98_Sub16 != null; class98_Sub16 = (Class98_Sub16)this.aClass148_5817.method2417(111)) {
                if (!this.aClass98_Sub31_Sub2_5815.method1345(1816, class98_Sub16)) {
                    int n3 = n;
                    int n4 = n2;
                    while (class98_Sub16.anInt3923 < n4) {
                        this.method1328(n4 + n3, class98_Sub16.anInt3923, n3, 1048575, array, class98_Sub16);
                        n3 += class98_Sub16.anInt3923;
                        n4 -= class98_Sub16.anInt3923;
                        if (this.aClass98_Sub31_Sub2_5815.method1340(n4, array, 2, n3, class98_Sub16)) {
                            continue Label_0152;
                        }
                    }
                    this.method1328(n4 + n3, n4, n3, 1048575, array, class98_Sub16);
                    final Class98_Sub16 class98_Sub17 = class98_Sub16;
                    class98_Sub17.anInt3923 -= n4;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ap.A(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    private final void method1328(final int n, int n2, int n3, final int n4, final int[] array, final Class98_Sub16 class98_Sub16) {
        try {
            if (~(this.aClass98_Sub31_Sub2_5815.anIntArray5840[class98_Sub16.anInt3940] & 0x4) != -1 && class98_Sub16.anInt3930 < 0) {
                final int n5 = this.aClass98_Sub31_Sub2_5815.anIntArray5843[class98_Sub16.anInt3940] / Class64_Sub15.anInt3678;
                while (true) {
                    final int n6 = (1048575 - -n5 + -class98_Sub16.anInt3919) / n5;
                    if (n2 < n6) {
                        break;
                    }
                    class98_Sub16.aClass98_Sub31_Sub5_3939.method1325(array, n3, n6);
                    n3 += n6;
                    class98_Sub16.anInt3919 += n6 * n5 - 1048576;
                    n2 -= n6;
                    int n7 = Class64_Sub15.anInt3678 / 100;
                    final int n8 = 262144 / n5;
                    if (n8 < n7) {
                        n7 = n8;
                    }
                    final Class98_Sub31_Sub5 aClass98_Sub31_Sub5_3939 = class98_Sub16.aClass98_Sub31_Sub5_3939;
                    if (~this.aClass98_Sub31_Sub2_5815.anIntArray5842[class98_Sub16.anInt3940] != -1) {
                        class98_Sub16.aClass98_Sub31_Sub5_3939 = Class98_Sub31_Sub5.method1402(class98_Sub16.aClass98_Sub24_Sub1_3934, aClass98_Sub31_Sub5_3939.method1430(), 0, aClass98_Sub31_Sub5_3939.method1426());
                        this.aClass98_Sub31_Sub2_5815.method1361(1, ~class98_Sub16.aClass98_Sub44_3918.aShortArray4248[class98_Sub16.anInt3936] > -1, class98_Sub16);
                        class98_Sub16.aClass98_Sub31_Sub5_3939.method1397(n7, aClass98_Sub31_Sub5_3939.method1400());
                    }
                    else {
                        class98_Sub16.aClass98_Sub31_Sub5_3939 = Class98_Sub31_Sub5.method1402(class98_Sub16.aClass98_Sub24_Sub1_3934, aClass98_Sub31_Sub5_3939.method1430(), aClass98_Sub31_Sub5_3939.method1400(), aClass98_Sub31_Sub5_3939.method1426());
                    }
                    if (~class98_Sub16.aClass98_Sub44_3918.aShortArray4248[class98_Sub16.anInt3936] > -1) {
                        class98_Sub16.aClass98_Sub31_Sub5_3939.method1422(-1);
                    }
                    aClass98_Sub31_Sub5_3939.method1423(n7);
                    aClass98_Sub31_Sub5_3939.method1325(array, n3, n - n3);
                    if (!aClass98_Sub31_Sub5_3939.method1425()) {
                        continue;
                    }
                    this.aClass98_Sub31_Sub3_5818.method1371(aClass98_Sub31_Sub5_3939);
                }
                class98_Sub16.anInt3919 += n5 * n2;
            }
            class98_Sub16.aClass98_Sub31_Sub5_3939.method1325(array, n3, n2);
            if (n4 != 1048575) {
                this.method1321(-128);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ap.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((array != null) ? "{...}" : "null") + ',' + ((class98_Sub16 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method1329(final byte b) {
        try {
            if (b >= -17) {
                Class98_Sub31_Sub1.aClass305_Sub1_5816 = null;
            }
            Class98_Sub31_Sub1.aClass305_Sub1_5816 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ap.E(" + b + ')');
        }
    }
    
    @Override
    final Class98_Sub31 method1327() {
        try {
            Class98_Sub16 class98_Sub16;
            do {
                class98_Sub16 = (Class98_Sub16)this.aClass148_5817.method2417(117);
                if (class98_Sub16 == null) {
                    return null;
                }
            } while (class98_Sub16.aClass98_Sub31_Sub5_3939 == null);
            return class98_Sub16.aClass98_Sub31_Sub5_3939;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ap.D()");
        }
    }
    
    @Override
    final Class98_Sub31 method1322() {
        try {
            final Class98_Sub16 class98_Sub16 = (Class98_Sub16)this.aClass148_5817.method2418(32);
            if (class98_Sub16 == null) {
                return null;
            }
            if (class98_Sub16.aClass98_Sub31_Sub5_3939 != null) {
                return class98_Sub16.aClass98_Sub31_Sub5_3939;
            }
            return this.method1327();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ap.B()");
        }
    }
    
    @Override
    final void method1321(final int n) {
        try {
            this.aClass98_Sub31_Sub3_5818.method1321(n);
        Label_0105:
            for (Class98_Sub16 class98_Sub16 = (Class98_Sub16)this.aClass148_5817.method2418(32); class98_Sub16 != null; class98_Sub16 = (Class98_Sub16)this.aClass148_5817.method2417(94)) {
                if (!this.aClass98_Sub31_Sub2_5815.method1345(1816, class98_Sub16)) {
                    int n2 = n;
                    while (~n2 < ~class98_Sub16.anInt3923) {
                        this.method1330(class98_Sub16.anInt3923, class98_Sub16, -1);
                        n2 -= class98_Sub16.anInt3923;
                        if (this.aClass98_Sub31_Sub2_5815.method1340(n2, null, 2, 0, class98_Sub16)) {
                            continue Label_0105;
                        }
                    }
                    this.method1330(n2, class98_Sub16, -1);
                    final Class98_Sub16 class98_Sub17 = class98_Sub16;
                    class98_Sub17.anInt3923 -= n2;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ap.M(" + n + ')');
        }
    }
    
    private final void method1330(int n, final Class98_Sub16 class98_Sub16, final int n2) {
        try {
            if ((0x4 & this.aClass98_Sub31_Sub2_5815.anIntArray5840[class98_Sub16.anInt3940]) != 0x0 && ~class98_Sub16.anInt3930 > -1) {
                final int n3 = this.aClass98_Sub31_Sub2_5815.anIntArray5843[class98_Sub16.anInt3940] / Class64_Sub15.anInt3678;
                final int n4 = (1048575 - (-n3 - -class98_Sub16.anInt3919)) / n3;
                class98_Sub16.anInt3919 = (0xFFFFF & n * n3 + class98_Sub16.anInt3919);
                if (~n4 >= ~n) {
                    Label_0204: {
                        if (this.aClass98_Sub31_Sub2_5815.anIntArray5842[class98_Sub16.anInt3940] != 0) {
                            class98_Sub16.aClass98_Sub31_Sub5_3939 = Class98_Sub31_Sub5.method1402(class98_Sub16.aClass98_Sub24_Sub1_3934, class98_Sub16.aClass98_Sub31_Sub5_3939.method1430(), 0, class98_Sub16.aClass98_Sub31_Sub5_3939.method1426());
                            this.aClass98_Sub31_Sub2_5815.method1361(n2 + 2, ~class98_Sub16.aClass98_Sub44_3918.aShortArray4248[class98_Sub16.anInt3936] > -1, class98_Sub16);
                            if (!client.aBoolean3553) {
                                break Label_0204;
                            }
                        }
                        class98_Sub16.aClass98_Sub31_Sub5_3939 = Class98_Sub31_Sub5.method1402(class98_Sub16.aClass98_Sub24_Sub1_3934, class98_Sub16.aClass98_Sub31_Sub5_3939.method1430(), class98_Sub16.aClass98_Sub31_Sub5_3939.method1400(), class98_Sub16.aClass98_Sub31_Sub5_3939.method1426());
                    }
                    if (~class98_Sub16.aClass98_Sub44_3918.aShortArray4248[class98_Sub16.anInt3936] > -1) {
                        class98_Sub16.aClass98_Sub31_Sub5_3939.method1422(-1);
                    }
                    n = class98_Sub16.anInt3919 / n3;
                }
            }
            if (n2 != -1) {
                this.method1327();
            }
            class98_Sub16.aClass98_Sub31_Sub5_3939.method1321(n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ap.F(" + n + ',' + ((class98_Sub16 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    Class98_Sub31_Sub1(final Class98_Sub31_Sub2 aClass98_Sub31_Sub2_5815) {
        this.aClass148_5817 = new Class148();
        this.aClass98_Sub31_Sub3_5818 = new Class98_Sub31_Sub3();
        try {
            this.aClass98_Sub31_Sub2_5815 = aClass98_Sub31_Sub2_5815;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ap.<init>(" + ((aClass98_Sub31_Sub2_5815 != null) ? "{...}" : "null") + ')');
        }
    }
}
