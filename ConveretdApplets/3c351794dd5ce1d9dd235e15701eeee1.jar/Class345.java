// 
// Decompiled by Procyon v0.5.30
// 

final class Class345
{
    static Class132 aClass132_2889;
    private ha_Sub1 aHa_Sub1_2890;
    long aLong2891;
    
    static final void method3824(final String s, final int n) {
        try {
            if (n == 2 && !s.equals("")) {
                ++Class39_Sub1.anInt3594;
                final Class98_Sub11 method3023 = Class246_Sub3_Sub4.method3023(n ^ 0x106, Class98_Sub23.aClass171_3998, Class331.aClass117_2811);
                method3023.aClass98_Sub22_Sub1_3865.method1194(r_Sub2.method1650(s, (byte)74), n ^ 0xFFFFFFBF);
                method3023.aClass98_Sub22_Sub1_3865.method1188(s, (byte)113);
                Class98_Sub10_Sub29.sendPacket(false, method3023);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "v.B(" + ((s != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final int method3825(int n, final int n2, final byte b) {
        try {
            if (n2 == -2) {
                return 12345678;
            }
            if (b != -21) {
                method3826(-112);
            }
            if (~n2 == 0x0) {
                if (~n > -3) {
                    n = 2;
                }
                else if (n > 126) {
                    n = 126;
                }
                return n;
            }
            n = (0x7F & n2) * n >> -1571731161;
            if (~n <= -3) {
                if (~n < -127) {
                    n = 126;
                }
            }
            else {
                n = 2;
            }
            return (0xFF80 & n2) + n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "v.C(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    @Override
    protected final void finalize() throws Throwable {
        try {
            this.aHa_Sub1_2890.method1855(false, this.aLong2891);
            super.finalize();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "v.finalize()");
        }
    }
    
    public static void method3826(final int n) {
        try {
            Class345.aClass132_2889 = null;
            if (n > -68) {
                Class345.aClass132_2889 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "v.A(" + n + ')');
        }
    }
    
    Class345(final ha_Sub1 aHa_Sub1_2890, final long aLong2891, final int n) {
        try {
            this.aLong2891 = aLong2891;
            this.aHa_Sub1_2890 = aHa_Sub1_2890;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "v.<init>(" + ((aHa_Sub1_2890 != null) ? "{...}" : "null") + ',' + aLong2891 + ',' + n + ')');
        }
    }
}
