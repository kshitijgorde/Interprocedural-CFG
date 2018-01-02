// 
// Decompiled by Procyon v0.5.30
// 

final class Class83
{
    Class207 aClass207_631;
    static Class126 aClass126_632;
    private Class207 aClass207_633;
    private Class79 aClass79_634;
    
    static final int method824(final byte b) {
        try {
            if (b > -59) {
                return -112;
            }
            if (Class255.aClass293_3208 == null) {
                if (!Class246_Sub3_Sub4_Sub2_Sub2.aBoolean6540 && Class266.aClass98_Sub46_Sub8_1994 != null) {
                    return Class266.aClass98_Sub46_Sub8_1994.anInt5986;
                }
                final int method3514 = Class2.aClass299_73.method3514(116);
                final int method3515 = Class2.aClass299_73.method3507((byte)58);
                if (!Class248.aBoolean1896) {
                    if (Class38.anInt355 < method3514 && ~method3514 > ~(Class246_Sub3_Sub4_Sub4.anInt6488 + Class38.anInt355)) {
                        int n = -1;
                        for (int i = 0; i < Class359.anInt3058; ++i) {
                            if (!Class98_Sub5_Sub3.aBoolean5539) {
                                final int n2 = (Class359.anInt3058 + (-1 + -i)) * 16 + 31 + Class104.anInt897;
                                if (~(n2 - 13) > ~method3515 && ~(n2 + 3) <= ~method3515) {
                                    n = i;
                                }
                            }
                            else {
                                final int n3 = 33 + (Class104.anInt897 + (-i + Class359.anInt3058 - 1) * 16);
                                if (~method3515 < ~(n3 - 13) && ~method3515 >= ~(n3 + 3)) {
                                    n = i;
                                }
                            }
                        }
                        if (n != -1) {
                            int n4 = 0;
                            final Class157 class157 = new Class157(Class33.aClass148_315);
                            for (Class98_Sub46_Sub8 class98_Sub46_Sub8 = (Class98_Sub46_Sub8)class157.method2504((byte)(-121)); class98_Sub46_Sub8 != null; class98_Sub46_Sub8 = (Class98_Sub46_Sub8)class157.method2503(1000)) {
                                if (n4++ == n) {
                                    return class98_Sub46_Sub8.anInt5986;
                                }
                            }
                        }
                    }
                }
                else if (Class38.anInt355 >= method3514 || Class38.anInt355 + Class246_Sub3_Sub4_Sub4.anInt6488 <= method3514) {
                    if (Class308.aClass98_Sub46_Sub9_2583 != null && ~method3514 < ~Class282.anInt2128 && ~(Class5.anInt3439 + Class282.anInt2128) < ~method3514) {
                        int n5 = -1;
                        for (int n6 = 0; ~n6 > ~Class308.aClass98_Sub46_Sub9_2583.anInt6001; ++n6) {
                            if (!Class98_Sub5_Sub3.aBoolean5539) {
                                final int n7 = 31 + (Class163.anInt3518 + n6 * 16);
                                if (-13 + n7 < method3515 && n7 + 3 >= method3515) {
                                    n5 = n6;
                                }
                            }
                            else {
                                final int n8 = Class163.anInt3518 + 33 - -(n6 * 16);
                                if (-13 + n8 < method3515 && n8 + 3 >= method3515) {
                                    n5 = n6;
                                }
                            }
                        }
                        if (n5 != -1) {
                            int n9 = 0;
                            final Class252 class158 = new Class252(Class308.aClass98_Sub46_Sub9_2583.aClass215_5999);
                            for (Class98_Sub46_Sub8 class98_Sub46_Sub9 = (Class98_Sub46_Sub8)class158.method3173(true); class98_Sub46_Sub9 != null; class98_Sub46_Sub9 = (Class98_Sub46_Sub8)class158.method3174(0)) {
                                if (~n5 == ~(n9++)) {
                                    return class98_Sub46_Sub9.anInt5986;
                                }
                            }
                        }
                    }
                }
                else {
                    int n10 = -1;
                    for (int n11 = 0; Class64_Sub12.anInt3672 > n11; ++n11) {
                        if (!Class98_Sub5_Sub3.aBoolean5539) {
                            final int n12 = Class104.anInt897 + (31 - -(16 * n11));
                            if (~(n12 - 13) > ~method3515 && 3 + n12 >= method3515) {
                                n10 = n11;
                            }
                        }
                        else {
                            final int n13 = 33 + (Class104.anInt897 - -(n11 * 16));
                            if (n13 - 13 < method3515 && ~(n13 + 3) <= ~method3515) {
                                n10 = n11;
                            }
                        }
                    }
                    if (~n10 != 0x0) {
                        int n14 = 0;
                        final Class252 class159 = new Class252(Class98_Sub18.aClass215_3949);
                        for (Class98_Sub46_Sub9 class98_Sub46_Sub10 = (Class98_Sub46_Sub9)class159.method3173(true); class98_Sub46_Sub10 != null; class98_Sub46_Sub10 = (Class98_Sub46_Sub9)class159.method3174(0)) {
                            if (n14++ == n10) {
                                return ((Class98_Sub46_Sub8)class98_Sub46_Sub10.aClass215_5999.aClass98_Sub46_1615.aClass98_Sub46_4262).anInt5986;
                            }
                        }
                    }
                }
            }
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fga.A(" + b + ')');
        }
    }
    
    static final void method825(final int anInt3702) {
        Class64_Sub21.anInt3702 = anInt3702;
    }
    
    final Class152 method826(final int n, final int n2) {
        try {
            final Class152 class152;
            synchronized (this.aClass79_634) {
                class152 = (Class152)this.aClass79_634.method802(n2 - 123, n);
            }
            if (class152 != null) {
                return class152;
            }
            final byte[] method2745;
            synchronized (this.aClass207_633) {
                method2745 = this.aClass207_633.method2745(n, n2, false);
            }
            final Class152 class153 = new Class152();
            class153.aClass83_1220 = this;
            if (method2745 != null) {
                class153.method2480(false, new Class98_Sub22(method2745));
            }
            synchronized (this.aClass79_634) {
                this.aClass79_634.method805(n, class153, (byte)(-80));
            }
            return class153;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fga.C(" + n + ',' + n2 + ')');
        }
    }
    
    final void method827(final byte b, final int n) {
        try {
            synchronized (this.aClass79_634) {
                this.aClass79_634.method800((byte)62, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fga.G(" + b + ',' + n + ')');
        }
    }
    
    final void method828(final int n) {
        try {
            synchronized (this.aClass79_634) {
                this.aClass79_634.method806((byte)(-107));
            }
            if (n >= -92) {
                this.method827((byte)(-66), 21);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fga.D(" + n + ')');
        }
    }
    
    final void method829(final int n) {
        try {
            synchronized (this.aClass79_634) {
                this.aClass79_634.method794(5);
                if (n <= 107) {
                    this.aClass79_634 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fga.B(" + n + ')');
        }
    }
    
    public static void method830(final byte b) {
        try {
            if (b <= 57) {
                Class83.aClass126_632 = null;
            }
            Class83.aClass126_632 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fga.F(" + b + ')');
        }
    }
    
    static final Class98_Sub31_Sub2 method831(final int n) {
        try {
            if (n >= -36) {
                return null;
            }
            return Class366.aClass98_Sub31_Sub2_3122;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fga.H(" + n + ')');
        }
    }
    
    Class83(final Class279 class279, final int n, final Class207 aClass207_633, final Class207 aClass207_634) {
        this.aClass79_634 = new Class79(64);
        try {
            this.aClass207_633 = aClass207_633;
            this.aClass207_631 = aClass207_634;
            this.aClass207_633.method2761(0, 3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fga.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_633 != null) ? "{...}" : "null") + ',' + ((aClass207_634 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class83.aClass126_632 = new Class126();
    }
}
