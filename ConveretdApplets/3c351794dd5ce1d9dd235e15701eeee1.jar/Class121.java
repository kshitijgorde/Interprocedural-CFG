// 
// Decompiled by Procyon v0.5.30
// 

final class Class121
{
    static OutgoingOpcode aClass171_1001;
    private Class207 aClass207_1002;
    Class79 aClass79_1003;
    static Class197 aClass197_1004;
    Class207 aClass207_1005;
    static int[] anIntArray1006;
    private Class79 aClass79_1007;
    
    final void method2191(final int n, final byte b) {
        try {
            synchronized (this.aClass79_1007) {
                this.aClass79_1007.method800((byte)62, n);
            }
            if (b != 126) {
                Class121.anIntArray1006 = null;
            }
            synchronized (this.aClass79_1003) {
                this.aClass79_1003.method800((byte)62, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hv.A(" + n + ',' + b + ')');
        }
    }
    
    static final int[] method2192(final int n) {
        try {
            if (n > -88) {
                method2195(49);
            }
            return new int[] { Class244.anInt1860, Class269.anInt2026, Class123_Sub1.anInt4742 };
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hv.B(" + n + ')');
        }
    }
    
    final void method2193(final int n) {
        try {
            synchronized (this.aClass79_1007) {
                this.aClass79_1007.method806((byte)(-104));
            }
            synchronized (this.aClass79_1003) {
                this.aClass79_1003.method806((byte)49);
            }
            if (n != 0) {
                this.method2191(66, (byte)(-78));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hv.E(" + n + ')');
        }
    }
    
    final Class86 method2194(final int n, final int n2) {
        try {
            final Class86 class86;
            synchronized (this.aClass79_1007) {
                class86 = (Class86)this.aClass79_1007.method802(-120, n2);
            }
            if (class86 != null) {
                return class86;
            }
            final byte[] method2745;
            synchronized (this.aClass207_1002) {
                method2745 = this.aClass207_1002.method2745(n2, 46, false);
            }
            final Class86 class87 = new Class86();
            class87.aClass121_644 = this;
            if (method2745 != null) {
                class87.method851(new Class98_Sub22(method2745), -1);
            }
            synchronized (this.aClass79_1007) {
                this.aClass79_1007.method805(n2, class87, (byte)(-80));
            }
            return class87;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hv.D(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method2195(final int n) {
        try {
            if (n != 30574) {
                Class121.anIntArray1006 = null;
            }
            Class2.anInt71 = 0;
            for (int n2 = 0; ~n2 > -2049; ++n2) {
                Class224_Sub3_Sub1.aClass98_Sub22Array6146[n2] = null;
                Class98_Sub10_Sub21.aByteArray5642[n2] = 1;
                Class306.aClass376Array2562[n2] = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hv.H(" + n + ')');
        }
    }
    
    public static void method2196(final byte b) {
        try {
            Class121.aClass171_1001 = null;
            if (b != -10) {
                method2196((byte)(-3));
            }
            Class121.aClass197_1004 = null;
            Class121.anIntArray1006 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hv.G(" + b + ')');
        }
    }
    
    final void method2197(final byte b) {
        try {
            synchronized (this.aClass79_1007) {
                if (b > -34) {
                    this.method2193(-47);
                }
                this.aClass79_1007.method794(95);
            }
            synchronized (this.aClass79_1003) {
                this.aClass79_1003.method794(94);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hv.F(" + b + ')');
        }
    }
    
    static final boolean method2198(int n, final int n2, final int n3, final byte[] array, final int n4, final int n5, final int n6) {
        try {
            final int n7 = n3 % n4;
            int n8;
            if (n7 == 0) {
                n8 = 0;
            }
            else {
                n8 = n4 - n7;
            }
            final int n9 = -((n6 - (-n4 + 1)) / n4);
            final int n10 = -((-1 + n3 + n4) / n4);
            if (n5 != 14849) {
                Class121.aClass171_1001 = null;
            }
            for (int i = n9; i < 0; ++i) {
                for (int j = n10; j < 0; ++j) {
                    if (~array[n] == -1) {
                        return true;
                    }
                    n += n4;
                }
                n -= n8;
                if (~array[-1 + n] == -1) {
                    return true;
                }
                n += n2;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hv.C(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    Class121(final Class279 class279, final int n, final Class207 aClass207_1002, final Class207 aClass207_1003) {
        this.aClass79_1003 = new Class79(20);
        this.aClass79_1007 = new Class79(64);
        try {
            this.aClass207_1002 = aClass207_1002;
            this.aClass207_1005 = aClass207_1003;
            this.aClass207_1002.method2761(0, 46);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hv.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_1002 != null) ? "{...}" : "null") + ',' + ((aClass207_1003 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class121.aClass171_1001 = new OutgoingOpcode(64, 3);
    }
}
