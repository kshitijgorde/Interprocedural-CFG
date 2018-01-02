// 
// Decompiled by Procyon v0.5.30
// 

final class aa_Sub1 extends aa
{
    static IncomingOpcode aClass58_3554;
    int[] anIntArray3555;
    static int anInt3556;
    int[] anIntArray3557;
    static int anInt3558;
    static OutgoingOpcode aClass171_3559;
    static boolean[][] aBooleanArrayArray3560;
    static Class123 aClass123_3561;
    
    static final Class42_Sub1_Sub1 method153(final byte b, final int n, final ha_Sub1 ha_Sub1, final int n2, final int n3, final int n4) {
        try {
            if (ha_Sub1.aBoolean4426 || (Class81.method815(n2, 0) && Class81.method815(n, 0))) {
                return new Class42_Sub1_Sub1(ha_Sub1, 3553, n3, n4, n2, n, true);
            }
            if (!ha_Sub1.aBoolean4378) {
                return new Class42_Sub1_Sub1(ha_Sub1, n3, n4, n2, n, Class48.method453(423660257, n2), Class48.method453(423660257, n), true);
            }
            return new Class42_Sub1_Sub1(ha_Sub1, 34037, n3, n4, n2, n, true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ht.B(" + b + ',' + n + ',' + ((ha_Sub1 != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    public static void method154(final byte b) {
        try {
            aa_Sub1.aBooleanArrayArray3560 = null;
            aa_Sub1.aClass58_3554 = null;
            aa_Sub1.aClass171_3559 = null;
            aa_Sub1.aClass123_3561 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ht.A(" + b + ')');
        }
    }
    
    static final void method155(final int n) {
        try {
            if (Class42_Sub4.anInt5371 < 0) {
                Class42_Sub4.anInt5371 = 0;
                Class101.anInt849 = -1;
                Class169.anInt1307 = -1;
            }
            if (Class42_Sub4.anInt5371 > Class278.anInt2089) {
                Class169.anInt1307 = -1;
                Class101.anInt849 = -1;
                Class42_Sub4.anInt5371 = Class278.anInt2089;
            }
            if (~Class98_Sub40.anInt4197 > n) {
                Class169.anInt1307 = -1;
                Class101.anInt849 = -1;
                Class98_Sub40.anInt4197 = 0;
            }
            if (~Class98_Sub40.anInt4197 < ~Class278.anInt2084) {
                Class101.anInt849 = -1;
                Class98_Sub40.anInt4197 = Class278.anInt2084;
                Class169.anInt1307 = -1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ht.C(" + n + ')');
        }
    }
    
    aa_Sub1(final int n, final int n2, final int[] anIntArray3555, final int[] anIntArray3556) {
        try {
            this.anIntArray3557 = anIntArray3556;
            this.anIntArray3555 = anIntArray3555;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ht.<init>(" + n + ',' + n2 + ',' + ((anIntArray3555 != null) ? "{...}" : "null") + ',' + ((anIntArray3556 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        aa_Sub1.anInt3558 = -1;
        aa_Sub1.aClass58_3554 = new IncomingOpcode(94, -1);
        aa_Sub1.aClass171_3559 = new OutgoingOpcode(48, 2);
    }
}
