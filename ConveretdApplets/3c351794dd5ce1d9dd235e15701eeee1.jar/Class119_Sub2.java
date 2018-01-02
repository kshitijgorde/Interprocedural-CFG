// 
// Decompiled by Procyon v0.5.30
// 

final class Class119_Sub2 extends Class119
{
    private int anInt4722;
    private int anInt4723;
    private int anInt4724;
    private int anInt4725;
    static Class207 aClass207_4726;
    static int[] anIntArray4727;
    
    private static final void method2183(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        try {
            Label_0083: {
                if (~n4 <= ~Class76_Sub8.anInt3778 && ~Class3.anInt77 <= ~n2 && ~n8 <= ~Class98_Sub10_Sub38.anInt5753 && Class218.anInt1635 >= n6) {
                    Class48_Sub1.method455(n5, n7, n2, n4, false, n8, n6, n3);
                    if (!client.aBoolean3553) {
                        break Label_0083;
                    }
                }
                r.method1641(n5, n6, n4, n7, n2, -18907, n8, n3);
            }
            if (n != 1701353708) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lc.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ')');
        }
    }
    
    public static void method2184(final byte b) {
        try {
            Class119_Sub2.aClass207_4726 = null;
            if (b != 72) {
                method2184((byte)50);
            }
            Class119_Sub2.anIntArray4727 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lc.D(" + b + ')');
        }
    }
    
    @Override
    final void method2178(final int n, final int n2, final int n3) {
        try {
            Class119.method2177(super.anInt988, n3 * this.anInt4723 >> -968048116, -80, this.anInt4722 * n2 >> 310827276, this.anInt4724 * n2 >> 1701353708, n3 * this.anInt4725 >> -526500468);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lc.B(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method2179(final byte b, final int n, final int n2) {
        try {
            Class64_Sub19.method632(super.anInt985, n2 * this.anInt4722 >> -611555860, (byte)(-51), super.anInt987, n2 * this.anInt4724 >> -1259702804, n * this.anInt4725 >> 1917461164, n * this.anInt4723 >> -415500500);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lc.E(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method2174(final int n, final int n2, final int n3) {
        try {
            final int n4 = n * this.anInt4724 >> -1082509588;
            if (n3 == -5515) {
                method2183(1701353708, this.anInt4722 * n >> -2074443060, super.anInt988, n4, super.anInt985, n2 * this.anInt4723 >> -634503956, super.anInt987, n2 * this.anInt4725 >> 1484019436);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lc.C(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    Class119_Sub2(final int anInt4724, final int anInt4725, final int anInt4726, final int anInt4727, final int n, final int n2, final int n3) {
        super(n, n2, n3);
        try {
            this.anInt4723 = anInt4727;
            this.anInt4725 = anInt4725;
            this.anInt4722 = anInt4726;
            this.anInt4724 = anInt4724;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lc.<init>(" + anInt4724 + ',' + anInt4725 + ',' + anInt4726 + ',' + anInt4727 + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static {
        Class119_Sub2.anIntArray4727 = new int[500];
    }
}
