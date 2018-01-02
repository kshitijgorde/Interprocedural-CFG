// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub1 extends Class98
{
    int anInt3809;
    boolean[] aBooleanArray3810;
    static OutgoingOpcode aClass171_3811;
    int[] anIntArray3812;
    int anInt3813;
    static int anInt3814;
    int[] anIntArray3815;
    int[][] anIntArrayArray3816;
    
    static final Class43 method945(final int n, final ha ha, final byte b, final boolean b2) {
        try {
            if (b < 74) {
                return null;
            }
            final Class244 method2151 = Class114.method2151(n, b2, ha, true);
            if (method2151 == null) {
                return null;
            }
            return method2151.aClass43_1859;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ah.C(" + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ')');
        }
    }
    
    static final void method946(final int anInt5623, final int n, final int anInt5624, final Class293 aClass293_1979) {
        try {
            Class265.aClass293_1979 = aClass293_1979;
            if (n <= -104) {
                Class64_Sub10.anInt3664 = anInt5624;
                Class98_Sub10_Sub17.anInt5623 = anInt5623;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ah.B(" + anInt5623 + ',' + n + ',' + anInt5624 + ',' + ((aClass293_1979 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method947(final int n) {
        try {
            Class98_Sub1.aClass171_3811 = null;
            if (n != -20899) {
                Class98_Sub1.aClass171_3811 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ah.A(" + n + ')');
        }
    }
    
    Class98_Sub1(final int anInt3813, final byte[] array) {
        try {
            this.anInt3813 = anInt3813;
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array);
            this.anInt3809 = class98_Sub22.readUnsignedByte((byte)38);
            this.anIntArray3815 = new int[this.anInt3809];
            this.anIntArray3812 = new int[this.anInt3809];
            this.aBooleanArray3810 = new boolean[this.anInt3809];
            this.anIntArrayArray3816 = new int[this.anInt3809][];
            for (int i = 0; i < this.anInt3809; ++i) {
                this.anIntArray3812[i] = class98_Sub22.readUnsignedByte((byte)(-126));
                if (this.anIntArray3812[i] == 6) {
                    this.anIntArray3812[i] = 2;
                }
            }
            for (int n = 0; ~this.anInt3809 < ~n; ++n) {
                this.aBooleanArray3810[n] = (~class98_Sub22.readUnsignedByte((byte)(-128)) == 0xFFFFFFFE);
            }
            for (int n2 = 0; ~this.anInt3809 < ~n2; ++n2) {
                this.anIntArray3815[n2] = class98_Sub22.readShort((byte)127);
            }
            for (int n3 = 0; ~n3 > ~this.anInt3809; ++n3) {
                this.anIntArrayArray3816[n3] = new int[class98_Sub22.readUnsignedByte((byte)(-119))];
            }
            for (int n4 = 0; ~this.anInt3809 < ~n4; ++n4) {
                for (int n5 = 0; this.anIntArrayArray3816[n4].length > n5; ++n5) {
                    this.anIntArrayArray3816[n4][n5] = class98_Sub22.readUnsignedByte((byte)125);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ah.<init>(" + anInt3813 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub1.aClass171_3811 = new OutgoingOpcode(67, 8);
    }
}
