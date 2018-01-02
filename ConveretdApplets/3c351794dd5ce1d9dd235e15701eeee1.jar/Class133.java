// 
// Decompiled by Procyon v0.5.30
// 

final class Class133 implements Interface18
{
    static int anInt3452;
    private Class163 aClass163_3453;
    static Class85 aClass85_3454;
    static long aLong3455;
    static boolean aBoolean3456;
    
    @Override
    public final boolean method59(final int n) {
        try {
            if (n != 14017) {
                this.method59(-23);
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jb.A(" + n + ')');
        }
    }
    
    @Override
    public final void method58(final byte b) {
        try {
            if (b != -43) {
                method2239(88, 77, (byte)120);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jb.C(" + b + ')');
        }
    }
    
    static final boolean method2238(final boolean b, final Class196 class196) {
        try {
            if (!b) {
                method2239(-9, 115, (byte)(-81));
            }
            return class196 == Class146.aClass196_1182 || class196 == Class246_Sub3_Sub4_Sub2_Sub2.aClass196_6543 || Class207.aClass196_1578 == class196 || class196 == Class134.aClass196_3458;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jb.D(" + b + ',' + ((class196 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class133(final Class163 aClass163_3453) {
        try {
            this.aClass163_3453 = aClass163_3453;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jb.<init>(" + ((aClass163_3453 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void method60(final boolean b, final byte b2) {
        try {
            if (b) {
                Class265.aHa1974.aa(0, 0, Class39_Sub1.anInt3593, Class98_Sub25.anInt4024, this.aClass163_3453.anInt3515, 0);
            }
            if (b2 >= -81) {
                method2238(true, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jb.B(" + b + ',' + b2 + ')');
        }
    }
    
    static final boolean method2239(final int n, final int n2, final byte b) {
        try {
            if (b != 100) {
                Class133.anInt3452 = -91;
            }
            return (((n & 0x37) == 0x0) ? Class98_Sub27.method1292(n, (byte)117, n2) : Class21_Sub3.method276(n2, 15123, n)) | (Class373_Sub3.method3978(n, n2, (byte)103) | (0x10000 & n2) != 0x0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jb.F(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    public static void method2240(final byte b) {
        try {
            if (b != 16) {
                method2239(14, 38, (byte)(-60));
            }
            Class133.aClass85_3454 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jb.E(" + b + ')');
        }
    }
    
    static {
        Class133.aBoolean3456 = false;
        Class133.aClass85_3454 = new Class85(5, 3);
    }
}
