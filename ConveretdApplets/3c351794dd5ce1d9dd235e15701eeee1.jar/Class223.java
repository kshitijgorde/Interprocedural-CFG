// 
// Decompiled by Procyon v0.5.30
// 

final class Class223
{
    private Class148 aClass148_1673;
    private int anInt1674;
    private int anInt1675;
    static int anInt1676;
    private int anInt1677;
    private Class98_Sub9[] aClass98_Sub9Array1678;
    static boolean aBoolean1679;
    private int[][][] anIntArrayArrayArray1680;
    static Class207 aClass207_1681;
    private int anInt1682;
    boolean aBoolean1683;
    
    final int[][] method2828(final int anInt1675, final int n) {
        try {
            if (n != 0) {
                return null;
            }
            if (~this.anInt1674 == ~this.anInt1677) {
                this.aBoolean1683 = (this.aClass98_Sub9Array1678[anInt1675] == null);
                this.aClass98_Sub9Array1678[anInt1675] = Class354.aClass98_Sub9_3014;
                return this.anIntArrayArrayArray1680[anInt1675];
            }
            if (this.anInt1677 == 1) {
                this.aBoolean1683 = (~this.anInt1675 != ~anInt1675);
                this.anInt1675 = anInt1675;
                return this.anIntArrayArrayArray1680[0];
            }
            Class98_Sub9 class98_Sub9 = this.aClass98_Sub9Array1678[anInt1675];
            if (class98_Sub9 == null) {
                this.aBoolean1683 = true;
                if (~this.anInt1677 < ~this.anInt1682) {
                    class98_Sub9 = new Class98_Sub9(anInt1675, this.anInt1682);
                    ++this.anInt1682;
                }
                else {
                    final Class98_Sub9 class98_Sub10 = (Class98_Sub9)this.aClass148_1673.method2427(n - 52);
                    class98_Sub9 = new Class98_Sub9(anInt1675, class98_Sub10.anInt3853);
                    this.aClass98_Sub9Array1678[class98_Sub10.anInt3852] = null;
                    class98_Sub10.method942(n ^ 0x35);
                }
                this.aClass98_Sub9Array1678[anInt1675] = class98_Sub9;
            }
            else {
                this.aBoolean1683 = false;
            }
            this.aClass148_1673.method2423(-2, class98_Sub9);
            return this.anIntArrayArrayArray1680[class98_Sub9.anInt3853];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oea.E(" + anInt1675 + ',' + n + ')');
        }
    }
    
    static final short[] method2829(final short[] array, final int n, final int n2) {
        try {
            final short[] array2 = new short[n];
            Class236.method2895(array, 0, array2, 0, n);
            if (n2 != 26813) {
                return null;
            }
            return array2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oea.D(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    final int[][][] method2830(final int n) {
        try {
            if (n != 26279) {
                this.method2828(-62, -13);
            }
            if (~this.anInt1674 != ~this.anInt1677) {
                throw new RuntimeException("Can only retrieve a full image cache");
            }
            for (int n2 = 0; ~n2 > ~this.anInt1677; ++n2) {
                this.aClass98_Sub9Array1678[n2] = Class354.aClass98_Sub9_3014;
            }
            return this.anIntArrayArrayArray1680;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oea.B(" + n + ')');
        }
    }
    
    final void method2831(final int n) {
        try {
            for (int i = n; i < this.anInt1677; ++i) {
                this.anIntArrayArrayArray1680[i][0] = null;
                this.anIntArrayArrayArray1680[i][1] = null;
                this.anIntArrayArrayArray1680[i][2] = null;
                this.anIntArrayArrayArray1680[i] = null;
            }
            this.anIntArrayArrayArray1680 = null;
            this.aClass98_Sub9Array1678 = null;
            this.aClass148_1673.method2422((byte)47);
            this.aClass148_1673 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oea.C(" + n + ')');
        }
    }
    
    public static void method2832(final byte b) {
        try {
            if (b <= 45) {
                Class223.aClass207_1681 = null;
            }
            Class223.aClass207_1681 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oea.A(" + b + ')');
        }
    }
    
    Class223(final int anInt1677, final int anInt1678, final int n) {
        this.anInt1675 = -1;
        this.anInt1682 = 0;
        this.aClass148_1673 = new Class148();
        this.aBoolean1683 = false;
        try {
            this.anInt1674 = anInt1678;
            this.anInt1677 = anInt1677;
            this.anIntArrayArrayArray1680 = new int[this.anInt1677][3][n];
            this.aClass98_Sub9Array1678 = new Class98_Sub9[this.anInt1674];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oea.<init>(" + anInt1677 + ',' + anInt1678 + ',' + n + ')');
        }
    }
    
    static {
        Class223.anInt1676 = 0;
        Class223.aBoolean1679 = false;
    }
}
