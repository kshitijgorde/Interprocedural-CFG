// 
// Decompiled by Procyon v0.5.30
// 

final class Class151_Sub5 extends Class151
{
    static int anInt4990;
    static boolean aBoolean4991;
    static IncomingOpcode aClass58_4992;
    static int anInt4993;
    
    @Override
    final void method2441(final int n, final int n2, final int n3) {
        try {
            if (n3 > -2) {
                this.method2445((byte)(-119));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mca.G(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final boolean method2439(final int n) {
        try {
            if (n != 31565) {
                method2461(-54);
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mca.A(" + n + ')');
        }
    }
    
    public static void method2461(final int n) {
        try {
            Class151_Sub5.aClass58_4992 = null;
            if (n != -3) {
                Class151_Sub5.anInt4993 = -98;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mca.B(" + n + ')');
        }
    }
    
    @Override
    final void method2442(final Class42 class42, final boolean b, final int n) {
        try {
            if (b) {
                this.method2440(false, false);
            }
            super.aHa_Sub1_1215.method1863(1, class42);
            super.aHa_Sub1_1215.method1896(260, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mca.F(" + ((class42 != null) ? "{...}" : "null") + ',' + b + ',' + n + ')');
        }
    }
    
    Class151_Sub5(final ha_Sub1 ha_Sub1) {
        super(ha_Sub1);
    }
    
    @Override
    final void method2440(final boolean b, final boolean b2) {
        try {
            if (b) {
                Class151_Sub5.aClass58_4992 = null;
            }
            super.aHa_Sub1_1215.method1905(true, 0);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mca.D(" + b + ',' + b2 + ')');
        }
    }
    
    @Override
    final void method2443(final boolean b, final int n) {
        try {
            if (n != 255) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mca.C(" + b + ',' + n + ')');
        }
    }
    
    static final boolean method2462(final int n, final byte b) {
        try {
            return n == 0 || ~n == 0xFFFFFFFD;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mca.H(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final void method2445(final byte b) {
        try {
            super.aHa_Sub1_1215.method1905(false, 0);
            if (b < 25) {
                Class151_Sub5.aBoolean4991 = false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "mca.E(" + b + ')');
        }
    }
    
    static {
        Class151_Sub5.aBoolean4991 = false;
        Class151_Sub5.aClass58_4992 = new IncomingOpcode(87, 7);
    }
}
