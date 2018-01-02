// 
// Decompiled by Procyon v0.5.30
// 

final class aa_Sub3 extends aa
{
    static IncomingOpcode aClass58_3566;
    static float aFloat3567;
    Class42_Sub1_Sub1 aClass42_Sub1_Sub1_3568;
    static boolean aBoolean3569;
    static OutgoingOpcode aClass171_3570;
    static int[] anIntArray3571;
    static char[] aCharArray3572;
    static Class348 aClass348_3573;
    static boolean[] aBooleanArray3574;
    static boolean[] aBooleanArray3575;
    
    static final int method157(final int n, final byte b) {
        try {
            if (b != 64) {
                method158(100);
            }
            return (0x3FF26 & n) >> -1240258357;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nc.C(" + n + ',' + b + ')');
        }
    }
    
    aa_Sub3(final ha_Sub1 ha_Sub1, final int n, final int n2, final byte[] array) {
        try {
            (this.aClass42_Sub1_Sub1_3568 = Class284_Sub2.method3374(6406, n2, 14764, 6406, ha_Sub1, false, array, n)).method383(false, 10242, false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nc.<init>(" + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method158(final int n) {
        try {
            aa_Sub3.aBooleanArray3575 = null;
            if (n != 6406) {
                method159(null, 9, null, null, null, null);
            }
            aa_Sub3.aBooleanArray3574 = null;
            aa_Sub3.aClass171_3570 = null;
            aa_Sub3.aClass348_3573 = null;
            aa_Sub3.anIntArray3571 = null;
            aa_Sub3.aClass58_3566 = null;
            aa_Sub3.aCharArray3572 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nc.B(" + n + ')');
        }
    }
    
    static final boolean method159(final Class207 aClass207_3641, final int n, final Class98_Sub31_Sub2 aClass98_Sub31_Sub2_3122, final Class268 aClass268_2032, final Class207 aClass207_3642, final Class207 aClass207_3643) {
        try {
            Class366.aClass98_Sub31_Sub2_3122 = aClass98_Sub31_Sub2_3122;
            Class98_Sub34.aClass207_4127 = aClass207_3642;
            Class94.aClass207_793 = aClass207_3643;
            Class77_Sub1.anIntArray3804 = new int[16];
            Class270.aClass268_2032 = aClass268_2032;
            Class64_Sub1.aClass207_3641 = aClass207_3641;
            if (n != -25233) {
                aa_Sub3.aClass58_3566 = null;
            }
            for (int i = 0; i < 16; ++i) {
                Class77_Sub1.anIntArray3804[i] = 255;
            }
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nc.A(" + ((aClass207_3641 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass98_Sub31_Sub2_3122 != null) ? "{...}" : "null") + ',' + ((aClass268_2032 != null) ? "{...}" : "null") + ',' + ((aClass207_3642 != null) ? "{...}" : "null") + ',' + ((aClass207_3643 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        aa_Sub3.aBoolean3569 = false;
        aa_Sub3.aClass58_3566 = new IncomingOpcode(67, -1);
        aa_Sub3.anIntArray3571 = new int[5];
        aa_Sub3.aClass171_3570 = new OutgoingOpcode(40, 4);
        aa_Sub3.aCharArray3572 = new char[] { '[', ']', '#' };
        aa_Sub3.aBooleanArray3574 = new boolean[100];
        aa_Sub3.aClass348_3573 = new Class348(9, 0, 4, 1);
        aa_Sub3.aBooleanArray3575 = new boolean[200];
    }
}
