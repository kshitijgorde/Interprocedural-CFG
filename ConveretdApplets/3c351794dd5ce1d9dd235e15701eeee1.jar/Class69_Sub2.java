import jaclib.memory.Buffer;
import jaclib.memory.Source;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class69_Sub2 extends Class69 implements Interface2_Impl1
{
    private byte aByte5332;
    static IncomingOpcode aClass58_5333;
    static Class79 aClass79_5334;
    static int anInt5335;
    static Class43 aClass43_5336;
    
    final int method704(final byte b) {
        try {
            if (b != -22) {
                method705(-35, -56, null);
            }
            return this.aByte5332;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "va.Q(" + b + ')');
        }
    }
    
    static final byte[] method705(final int n, final int n2, final byte[] array) {
        try {
            final byte[] array2 = new byte[n];
            Class236.method2894(array, 0, array2, 0, n);
            if (n2 < 58) {
                Class69_Sub2.aClass79_5334 = null;
            }
            return array2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "va.P(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final Class53_Sub1 method706(final int n) {
        try {
            if (n != 200) {
                Class69_Sub2.aClass58_5333 = null;
            }
            if (Class239.anInt1843 < Class98_Sub28_Sub1.aClass53_Sub1Array5805.length) {
                return Class98_Sub28_Sub1.aClass53_Sub1Array5805[Class239.anInt1843++];
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "va.O(" + n + ')');
        }
    }
    
    @Override
    public final boolean method71(final int n) {
        try {
            if (n != 13623) {
                this.method2(94);
            }
            return super.method703((byte)(-68), super.aHa_Sub3_Sub2_3217.aMapBuffer6128);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "va.N(" + n + ')');
        }
    }
    
    Class69_Sub2(final ha_Sub3_Sub2 ha_Sub3_Sub2, final boolean b) {
        super(ha_Sub3_Sub2, 34962, b);
    }
    
    @Override
    public final boolean method74(final int n, final int n2, final int n3) {
        try {
            this.aByte5332 = (byte)n2;
            if (n != -20279) {
                this.method73((byte)58, 121, 8, null);
            }
            super.method76(n3, n + 41058);
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "va.S(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    public final void method72(final boolean b) {
        try {
            super.method72(b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "va.L(" + b + ')');
        }
    }
    
    static final String method707(final byte[] array, final boolean b) {
        try {
            if (!b) {
                Class69_Sub2.aClass58_5333 = null;
            }
            return Class98_Sub46_Sub6.method1546(array.length, 0, (byte)(-110), array);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "va.V(" + ((array != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public static void method708(final int n) {
        try {
            if (n != 7315) {
                Class69_Sub2.aClass79_5334 = null;
            }
            Class69_Sub2.aClass79_5334 = null;
            Class69_Sub2.aClass43_5336 = null;
            Class69_Sub2.aClass58_5333 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "va.R(" + n + ')');
        }
    }
    
    @Override
    public final boolean method73(final byte b, final int n, final int n2, final Source source) {
        try {
            this.aByte5332 = (byte)n2;
            super.method697(source, 1, n);
            if (b > -79) {
                Class69_Sub2.aClass43_5336 = null;
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "va.T(" + b + ',' + n + ',' + n2 + ',' + ((source != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final int method2(final int n) {
        try {
            if (n != 200) {
                this.aByte5332 = 44;
            }
            return super.method2(200);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "va.E(" + n + ')');
        }
    }
    
    @Override
    public final Buffer method75(final boolean b, final byte b2) {
        try {
            if (b2 != 27) {
                return null;
            }
            return super.method694(b, super.aHa_Sub3_Sub2_3217.aMapBuffer6128, -15793);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "va.U(" + b + ',' + b2 + ')');
        }
    }
    
    static {
        Class69_Sub2.aClass58_5333 = new IncomingOpcode(105, 6);
        Class69_Sub2.anInt5335 = 0;
        Class69_Sub2.aClass79_5334 = new Class79(3000000, 200);
    }
}
