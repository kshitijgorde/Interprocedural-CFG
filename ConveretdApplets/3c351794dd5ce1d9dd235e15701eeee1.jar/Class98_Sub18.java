import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub18 extends Class98
{
    int anInt3945;
    static IncomingOpcode aClass58_3946;
    int anInt3947;
    static float aFloat3948;
    static Class215 aClass215_3949;
    static Frame aFrame3950;
    static int anInt3951;
    static int anInt3952;
    
    public static void method1161(final int n) {
        try {
            Class98_Sub18.aClass58_3946 = null;
            Class98_Sub18.aClass215_3949 = null;
            if (n != 0) {
                method1161(-120);
            }
            Class98_Sub18.aFrame3950 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hr.B(" + n + ')');
        }
    }
    
    static final void method1162(final int n, final int n2, final int n3, final Class clazz) {
        final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[n][n2][n3];
        if (class172 != null) {
            for (Class154 class173 = class172.aClass154_1325; class173 != null; class173 = class173.aClass154_1233) {
                final Class246_Sub3_Sub4 aClass246_Sub3_Sub4_1232 = class173.aClass246_Sub3_Sub4_1232;
                if (clazz.isAssignableFrom(aClass246_Sub3_Sub4_1232.getClass()) && aClass246_Sub3_Sub4_1232.aShort6158 == n2 && aClass246_Sub3_Sub4_1232.aShort6157 == n3) {
                    Class99.method1687(aClass246_Sub3_Sub4_1232, false);
                    break;
                }
            }
        }
    }
    
    static boolean method1163(final boolean b, final boolean b2) {
        try {
            return b | b2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hr.C(" + b + ',' + b2 + ')');
        }
    }
    
    static {
        Class98_Sub18.aClass58_3946 = new IncomingOpcode(88, -2);
        Class98_Sub18.aClass215_3949 = new Class215();
        Class98_Sub18.anInt3952 = 0;
    }
}
