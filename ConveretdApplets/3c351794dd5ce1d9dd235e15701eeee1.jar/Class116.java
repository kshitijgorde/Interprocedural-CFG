// 
// Decompiled by Procyon v0.5.30
// 

final class Class116
{
    static Class377 aClass377_964;
    static Class98_Sub31_Sub2 aClass98_Sub31_Sub2_965;
    static String[] aStringArray966;
    static int anInt967;
    
    static final void method2159(final byte b, final ha ha) {
        try {
            Label_0028: {
                if (!Class98_Sub5_Sub3.aBoolean5539) {
                    Class98_Sub46_Sub9.method1561(ha, -256);
                    if (!client.aBoolean3553) {
                        break Label_0028;
                    }
                }
                Class163.method2519(6, ha);
            }
            if (b <= 31) {
                Class116.anInt967 = -116;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hha.B(" + b + ',' + ((ha != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2160(final int n) {
        try {
            Class116.aClass377_964 = null;
            if (n != -25882) {
                method2160(-43);
            }
            Class116.aClass98_Sub31_Sub2_965 = null;
            Class116.aStringArray966 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hha.A(" + n + ')');
        }
    }
    
    static final void method2161(final int anInt6054, final int n, final byte b) {
        try {
            if (b == -120) {
                final Class98_Sub46_Sub17 method2628 = Class185.method2628(n, -124, 1);
                method2628.method1626((byte)(-103));
                method2628.anInt6054 = anInt6054;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hha.D(" + anInt6054 + ',' + n + ',' + b + ')');
        }
    }
    
    static final boolean method2162(final boolean b) {
        try {
            try {
                if (b) {
                    return true;
                }
                Class271.method3277(new Class263().method3220((byte)126, Class74.aByteArray546), 1);
                return true;
            }
            catch (Exception ex2) {
                return false;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hha.C(" + b + ')');
        }
    }
    
    static {
        Class116.aClass377_964 = new Class377(8);
    }
}
