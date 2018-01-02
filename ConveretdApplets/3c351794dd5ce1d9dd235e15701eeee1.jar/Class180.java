// 
// Decompiled by Procyon v0.5.30
// 

final class Class180 implements Interface14
{
    static short[][][] aShortArrayArrayArray3397;
    static IncomingOpcode aClass58_3398;
    private Class207 aClass207_3399;
    private String aString3400;
    private static short[][] aShortArrayArray3401;
    private static short[][] aShortArrayArray3402;
    private static short[][] aShortArrayArray3403;
    static long aLong3404;
    static float aFloat3405;
    
    static final boolean method2603(final byte b, final Interface19 interface19) {
        try {
            final Class352 method3546 = Class130.aClass302_1028.method3546(interface19.method64(30472), (byte)119);
            if (~method3546.anInt2990 == 0x0) {
                return true;
            }
            final Class9 method3547 = Class98_Sub10_Sub23.aClass335_5662.method3766(method3546.anInt2990, (byte)(-73));
            return ~method3547.anInt114 == 0x0 || b != 76 || method3547.method191(-87);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "maa.C(" + b + ',' + ((interface19 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final Class191 method50(final int n) {
        try {
            if (n != 15763) {
                return null;
            }
            return Class191.aClass191_1474;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "maa.B(" + n + ')');
        }
    }
    
    static final void method2604(final int n, final int n2, final int n3, final int n4, final Class98_Sub11 class98_Sub11) {
        try {
            if (n2 != -21568) {
                Class180.aShortArrayArray3402 = null;
            }
            class98_Sub11.aClass98_Sub22_Sub1_3865.writeInt(1571862888, n3);
            class98_Sub11.aClass98_Sub22_Sub1_3865.writeLEShortA(n, n2 + 21696);
            class98_Sub11.aClass98_Sub22_Sub1_3865.writeShort(n4, 1571862888);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "maa.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + ((class98_Sub11 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final int method51(final byte b) {
        try {
            if (this.aClass207_3399.method2734(this.aString3400, false)) {
                return 100;
            }
            if (b <= 126) {
                return -21;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "maa.E(" + b + ')');
        }
    }
    
    public static void method2605(final byte b) {
        try {
            Class180.aShortArrayArray3401 = null;
            Class180.aClass58_3398 = null;
            Class180.aShortArrayArrayArray3397 = null;
            if (b >= -5) {
                Class180.aShortArrayArray3403 = null;
            }
            Class180.aShortArrayArray3403 = null;
            Class180.aShortArrayArray3402 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "maa.D(" + b + ')');
        }
    }
    
    Class180(final Class207 aClass207_3399, final String aString3400) {
        try {
            this.aClass207_3399 = aClass207_3399;
            this.aString3400 = aString3400;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "maa.<init>(" + ((aClass207_3399 != null) ? "{...}" : "null") + ',' + ((aString3400 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class180.aShortArrayArray3401 = new short[][] { { 6798, 12, 78, 8384, 14511, 9162, 5056, 939, 5025, 4760, 9108, 7719, 14241, 22443, 30247, -29781, -25675, -21568, -17472, -12373, -8256, -3545 }, { 8741, 12, 78, 8384, 14511, 9162, 5056, 939, 5025, 4760, 9108, 7719, 14241, 22443, 30247, -29781, -25675, -21568, -17472, -12373, -8256, -3545 }, { 25238, 12, 78, 8384, 14511, 9162, 5056, 939, 5025, 4760, 9108, 7719, 14241, 22443, 30247, -29781, -25675, -21568, -17472, -12373, -8256, -3545 }, { 4626, 12, 78, 8384, 14511, 9162, 5056, 939, 5025, 4760, 9108, 7719, 14241, 22443, 30247, -29781, -25675, -21568, -17472, -12373, -8256, -3545 }, { 4550, 12, 78, 8384, 14511, 9162, 5056, 939, 5025, 4760, 9108, 7719, 14241, 22443, 30247, -29781, -25675, -21568, -17472, -12373, -8256, -3545 } };
        Class180.aShortArrayArray3402 = new short[][] { new short[0], new short[0], new short[0], new short[0], new short[0] };
        Class180.aShortArrayArray3403 = new short[][] { new short[0], new short[0], new short[0], new short[0], new short[0] };
        Class180.aShortArrayArrayArray3397 = new short[][][] { Class180.aShortArrayArray3401, Class180.aShortArrayArray3402, Class180.aShortArrayArray3403 };
        Class180.aClass58_3398 = new IncomingOpcode(109, 11);
        Class180.aLong3404 = 0L;
        Class180.aFloat3405 = 0.0f;
    }
}
