// 
// Decompiled by Procyon v0.5.30
// 

final class Class170
{
    byte aByte1308;
    short aShort1309;
    short aShort1310;
    static int[][][] anIntArrayArrayArray1311;
    byte aByte1312;
    static boolean aBoolean1313;
    boolean aBoolean1314;
    int anInt1315;
    int anInt1316;
    short aShort1317;
    
    static final String method2538(final int n, final Class293 class293) {
        try {
            if (~client.method116(class293).method1668(n) == -1) {
                return null;
            }
            if (class293.aString2214 != null && ~class293.aString2214.trim().length() != -1) {
                return class293.aString2214;
            }
            if (Class15.aBoolean169) {
                return "Hidden-use";
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lfa.A(" + n + ',' + ((class293 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2539(final byte b) {
        try {
            if (b != 13) {
                Class170.aBoolean1313 = true;
            }
            Class170.anIntArrayArrayArray1311 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lfa.B(" + b + ')');
        }
    }
    
    Class170(final int anInt1315, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final boolean aBoolean1314, final int anInt1316) {
        try {
            this.aByte1312 = (byte)n8;
            this.aShort1317 = (short)n4;
            this.aShort1310 = (short)n6;
            this.aByte1308 = (byte)n7;
            this.anInt1316 = anInt1316;
            this.aShort1309 = (short)n5;
            this.anInt1315 = anInt1315;
            this.aBoolean1314 = aBoolean1314;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lfa.<init>(" + anInt1315 + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + aBoolean1314 + ',' + anInt1316 + ')');
        }
    }
    
    static {
        Class170.aBoolean1313 = false;
    }
}
