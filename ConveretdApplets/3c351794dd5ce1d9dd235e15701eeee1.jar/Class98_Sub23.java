// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub23 extends Class98
{
    int anInt3996;
    Class98_Sub47 aClass98_Sub47_3997;
    static OutgoingOpcode aClass171_3998;
    int anInt3999;
    int anInt4000;
    static int anInt4001;
    int anInt4002;
    int anInt4003;
    int anInt4004;
    int anInt4005;
    int anInt4006;
    static Class85 aClass85_4007;
    
    public static void method1266(final int n) {
        try {
            Class98_Sub23.aClass171_3998 = null;
            if (n != -6292) {
                Class98_Sub23.aClass171_3998 = null;
            }
            Class98_Sub23.aClass85_4007 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jia.C(" + n + ')');
        }
    }
    
    final boolean method1267(final int n, final int n2, final int n3) {
        try {
            if (this.anInt3996 <= n3 && this.anInt4006 >= n3 && this.anInt3999 <= n2 && n2 <= this.anInt4000) {
                return true;
            }
            if (n != Integer.MIN_VALUE) {
                this.method1267(85, -86, -89);
            }
            return ~this.anInt4003 >= ~n3 && ~this.anInt4002 <= ~n3 && this.anInt4005 <= n2 && this.anInt4004 >= n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jia.A(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    Class98_Sub23(final Class98_Sub47 aClass98_Sub47_3997) {
        this.anInt3996 = Integer.MAX_VALUE;
        this.anInt3999 = Integer.MAX_VALUE;
        this.anInt4000 = Integer.MIN_VALUE;
        this.anInt4005 = Integer.MAX_VALUE;
        this.anInt4003 = Integer.MAX_VALUE;
        this.anInt4006 = Integer.MIN_VALUE;
        this.anInt4002 = Integer.MIN_VALUE;
        this.anInt4004 = Integer.MIN_VALUE;
        try {
            this.aClass98_Sub47_3997 = aClass98_Sub47_3997;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jia.<init>(" + ((aClass98_Sub47_3997 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final byte[] method1268(final int n, final int n2, final byte[] array, final byte b) {
        try {
            final byte[] array2 = new byte[n];
            Class236.method2894(array, n2, array2, 0, n);
            return array2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jia.B(" + n + ',' + n2 + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static {
        Class98_Sub23.aClass171_3998 = new OutgoingOpcode(1, -1);
        Class98_Sub23.aClass85_4007 = new Class85(3, 2);
    }
}
