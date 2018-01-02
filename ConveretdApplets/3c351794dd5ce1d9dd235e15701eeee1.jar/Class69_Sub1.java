import jaclib.memory.Buffer;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class69_Sub1 extends Class69 implements Interface2_Impl2
{
    static int anInt5329;
    static int anInt5330;
    private Class162 aClass162_5331;
    
    Class69_Sub1(final ha_Sub3_Sub2 ha_Sub3_Sub2, final Class162 aClass162_5331, final boolean b) {
        super(ha_Sub3_Sub2, 34963, b);
        try {
            this.aClass162_5331 = aClass162_5331;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ud.<init>(" + ((ha_Sub3_Sub2 != null) ? "{...}" : "null") + ',' + ((aClass162_5331 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    public final void method72(final boolean b) {
        try {
            super.method72(b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ud.L(" + b + ')');
        }
    }
    
    @Override
    public final int method2(final int n) {
        try {
            if (n != 200) {
                this.aClass162_5331 = null;
            }
            return super.method2(200);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ud.E(" + n + ')');
        }
    }
    
    @Override
    public final boolean method79(final byte b) {
        try {
            return super.method703((byte)(-68), super.aHa_Sub3_Sub2_3217.aMapBuffer6125);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ud.N(" + b + ')');
        }
    }
    
    @Override
    public final Buffer method78(final boolean b, final int n) {
        try {
            if (n > -79) {
                Class69_Sub1.anInt5330 = 20;
            }
            return super.method694(b, super.aHa_Sub3_Sub2_3217.aMapBuffer6125, -15793);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ud.O(" + b + ',' + n + ')');
        }
    }
    
    @Override
    public final void method76(final int n, final int n2) {
        try {
            super.method76(this.aClass162_5331.anInt1263 * n, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ud.G(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    public final Class162 method77(final int n) {
        try {
            if (n != -15448) {
                this.method2(120);
            }
            return this.aClass162_5331;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ud.P(" + n + ')');
        }
    }
    
    static {
        Class69_Sub1.anInt5329 = 0;
        Class69_Sub1.anInt5330 = -1;
    }
}
