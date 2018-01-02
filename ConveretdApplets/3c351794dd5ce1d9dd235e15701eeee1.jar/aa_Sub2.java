// 
// Decompiled by Procyon v0.5.30
// 

final class aa_Sub2 extends aa
{
    static int anInt3562;
    Interface4_Impl2 anInterface4_Impl2_3563;
    static OutgoingOpcode aClass171_3564;
    static int[] anIntArray3565;
    
    public static void method156(final int n) {
        try {
            if (n != 13) {
                aa_Sub2.anInt3562 = -35;
            }
            aa_Sub2.aClass171_3564 = null;
            aa_Sub2.anIntArray3565 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hu.A(" + n + ')');
        }
    }
    
    aa_Sub2(final ha_Sub3 ha_Sub3, final int n, final int n2, final byte[] array) {
        try {
            (this.anInterface4_Impl2_3563 = ha_Sub3.method2053(n, Class53_Sub1.aClass164_3633, (byte)87, array, false, n2)).method46(false, false, 76);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hu.<init>(" + ((ha_Sub3 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    aa_Sub2(final ha_Sub3 ha_Sub3, final int n, final int n2, final int[] array) {
        try {
            (this.anInterface4_Impl2_3563 = ha_Sub3.method2012(n, n2, (byte)31, array, false)).method46(false, false, 77);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hu.<init>(" + ((ha_Sub3 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        aa_Sub2.aClass171_3564 = new OutgoingOpcode(13, 8);
    }
}
