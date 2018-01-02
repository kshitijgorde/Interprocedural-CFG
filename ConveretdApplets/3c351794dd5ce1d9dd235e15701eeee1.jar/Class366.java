// 
// Decompiled by Procyon v0.5.30
// 

final class Class366
{
    static int anInt3111;
    static int anInt3112;
    static String[] aStringArray3113;
    static Class377 aClass377_3114;
    int anInt3115;
    int anInt3116;
    static int anInt3117;
    int anInt3118;
    static byte[][] aByteArrayArray3119;
    static float aFloat3120;
    static int anInt3121;
    static Class98_Sub31_Sub2 aClass98_Sub31_Sub2_3122;
    
    final void method3945(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            if (n != -6364) {
                this.method3946(true, -13, null);
            }
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)125);
                if (~unsignedByte == -1) {
                    break;
                }
                this.method3946(false, unsignedByte, class98_Sub22);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wc.A(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    private final void method3946(final boolean b, final int n, final Class98_Sub22 class98_Sub22) {
        try {
            if (!b && n == 1) {
                this.anInt3115 = class98_Sub22.readShort((byte)127);
                this.anInt3116 = class98_Sub22.readUnsignedByte((byte)125);
                this.anInt3118 = class98_Sub22.readUnsignedByte((byte)(-121));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wc.B(" + b + ',' + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method3947(final int n) {
        try {
            Class366.aByteArrayArray3119 = null;
            Class366.aClass98_Sub31_Sub2_3122 = null;
            Class366.aClass377_3114 = null;
            if (n != -15598) {
                method3947(-67);
            }
            Class366.aStringArray3113 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wc.C(" + n + ')');
        }
    }
    
    static {
        Class366.aStringArray3113 = new String[] { "en", "de", "fr", "pt", "nl" };
        Class366.anInt3117 = 0;
        Class366.aClass377_3114 = new Class377(8);
        Class366.aByteArrayArray3119 = new byte[250][];
    }
}
