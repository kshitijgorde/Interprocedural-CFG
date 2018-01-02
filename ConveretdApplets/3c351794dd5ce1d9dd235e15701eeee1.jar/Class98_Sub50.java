// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub50 extends Class98
{
    static Class332 aClass332_4287;
    long aLong4288;
    static int anInt4289;
    static OutgoingOpcode aClass171_4290;
    static IncomingOpcode aClass58_4291;
    static int anInt4292;
    static Class97[] aClass97Array4293;
    static int anInt4294;
    
    static final int method1670(final byte b, int n) {
        try {
            n = (0xD5555555 & n >>> 2099622177) + (n & 0x55555555);
            n = ((0xCCCCCCCE & n) >>> -1815143518) + (0x33333333 & n);
            n = ((n >>> -954051964) + n & 0xF0F0F0F);
            n += n >>> 810774152;
            if (b <= 88) {
                Class98_Sub50.anInt4294 = -42;
            }
            n += n >>> -194578672;
            return n & 0xFF;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wca.D(" + b + ',' + n + ')');
        }
    }
    
    static final void method1671(final int n, final byte b, int method3219, int method3220, final int n2) {
        try {
            if (b != 0) {
                Class98_Sub50.aClass171_4290 = null;
            }
            if (Class76_Sub8.anInt3778 <= n && ~n >= ~Class3.anInt77) {
                method3219 = Class263.method3219(false, Class218.anInt1635, Class98_Sub10_Sub38.anInt5753, method3219);
                method3220 = Class263.method3219(false, Class218.anInt1635, Class98_Sub10_Sub38.anInt5753, method3220);
                Class48_Sub2_Sub1.method471(n, (byte)59, n2, method3219, method3220);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wca.C(" + n + ',' + b + ',' + method3219 + ',' + method3220 + ',' + n2 + ')');
        }
    }
    
    public Class98_Sub50() {
    }
    
    static final void method1672(final byte b) {
        try {
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4041);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub3_4076);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 2, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub15_4034);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 2, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub15_4058);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub11_4038);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub25_4039);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub24_4047);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub13_4063);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub26_4035);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub20_4056);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub7_4073);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub10_4070);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub28_4064);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub14_4049);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4044);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub23_4055);
            Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub6_4033);
            if (b == 19) {
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub17_4046);
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 0, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub5_4065);
                Class151_Sub1.method2450((byte)101);
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 1, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub16_4040);
                Class98_Sub9.aClass98_Sub27_3856.method1285((byte)(-13), 3, Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub12_4048);
                Class98_Sub46_Sub13_Sub1.method1593((byte)62);
                Class374.method3980((byte)127);
                Class33.aBoolean316 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wca.A(" + b + ')');
        }
    }
    
    public static void method1673(final int n) {
        try {
            Class98_Sub50.aClass58_4291 = null;
            Class98_Sub50.aClass332_4287 = null;
            if (n != 1) {
                Class98_Sub50.anInt4294 = 41;
            }
            Class98_Sub50.aClass171_4290 = null;
            Class98_Sub50.aClass97Array4293 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wca.B(" + n + ')');
        }
    }
    
    Class98_Sub50(final long aLong4288) {
        try {
            this.aLong4288 = aLong4288;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wca.<init>(" + aLong4288 + ')');
        }
    }
    
    static {
        Class98_Sub50.aClass171_4290 = new OutgoingOpcode(69, 2);
        Class98_Sub50.aClass97Array4293 = new Class97[14];
        Class98_Sub50.aClass58_4291 = new IncomingOpcode(9, -1);
        Class98_Sub50.anInt4294 = 0;
    }
}
