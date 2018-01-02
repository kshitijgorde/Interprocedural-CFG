// 
// Decompiled by Procyon v0.5.30
// 

final class Class104
{
    static int anInt897;
    byte aByte898;
    short aShort899;
    byte aByte900;
    static OutgoingOpcode aClass171_901;
    Interface16 anInterface16_902;
    static Class36[] aClass36Array903;
    
    static final void method1712(final boolean b, final int anInt3424, int n, final int n2, final int n3, final int n4, final int anInt3425, final int n5) {
        try {
            int n6 = -334 + n3;
            Label_0039: {
                if (n6 >= 0) {
                    if (n6 <= 100) {
                        break Label_0039;
                    }
                    n6 = 100;
                    if (!client.aBoolean3553) {
                        break Label_0039;
                    }
                }
                n6 = 0;
            }
            final short n7 = (short)((Class135.aShort1057 + -Class64_Sub19.aShort3692) * n6 / 100 + Class64_Sub19.aShort3692);
            Class16.anInt197 = n7 * Class16.anInt199 >> 626243656;
            n = n * n7 >> -1212701304;
            final int n8 = 16384 - anInt3425 & 0x3FFF;
            final int n9 = 16384 + -anInt3424 & 0x3FFF;
            int n10 = 0;
            if (b) {
                Class104.anInt897 = -79;
            }
            int n11 = 0;
            int n12 = n;
            if (~n8 != -1) {
                n11 = Class284_Sub2_Sub2.anIntArray6200[n8] * -n12 >> -1123635698;
                n12 = n12 * Class284_Sub2_Sub2.anIntArray6202[n8] >> 1849889102;
            }
            if (~n9 != -1) {
                n10 = Class284_Sub2_Sub2.anIntArray6200[n9] * n12 >> 1440327534;
                n12 = n12 * Class284_Sub2_Sub2.anIntArray6202[n9] >> -1335926898;
            }
            Class134.anInt3461 = -n12 + n2;
            Class308.anInt2584 = 0;
            Class246_Sub3_Sub4_Sub2.anInt6357 = anInt3425;
            Class98_Sub46_Sub10.anInt6020 = -n10 + n4;
            Class186.anInt3424 = anInt3424;
            Class79.anInt601 = n5 - n11;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gk.A(" + b + ',' + anInt3424 + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + anInt3425 + ',' + n5 + ')');
        }
    }
    
    public static void method1713(final byte b) {
        try {
            if (b != 23) {
                Class104.anInt897 = -82;
            }
            Class104.aClass36Array903 = null;
            Class104.aClass171_901 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gk.B(" + b + ')');
        }
    }
    
    Class104(final Interface16 anInterface16_902, final int n, final int n2, final int n3) {
        try {
            this.aByte898 = (byte)n3;
            this.anInterface16_902 = anInterface16_902;
            this.aShort899 = (short)n;
            this.aByte900 = (byte)n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gk.<init>(" + ((anInterface16_902 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static {
        Class104.aClass171_901 = new OutgoingOpcode(18, 3);
        Class104.aClass36Array903 = new Class36[8];
    }
}
