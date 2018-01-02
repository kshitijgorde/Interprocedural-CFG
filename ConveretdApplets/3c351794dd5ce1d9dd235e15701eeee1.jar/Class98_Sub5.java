// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class98_Sub5 extends Class98
{
    int anInt3830;
    private int anInt3831;
    float aFloat3832;
    int anInt3833;
    int anInt3834;
    static Class157 aClass157_3835;
    private int anInt3836;
    static String aString3837;
    
    final int method954(final int n) {
        try {
            if (n != 7019) {
                method957(70);
            }
            return this.anInt3833;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cba.F(" + n + ')');
        }
    }
    
    abstract void method955(final int p0, final byte p1, final int p2, final int p3);
    
    final float method956(final boolean b) {
        try {
            if (b) {
                return -0.407301f;
            }
            return this.aFloat3832;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cba.G(" + b + ')');
        }
    }
    
    public static void method957(final int n) {
        try {
            if (n == -3509) {
                Class98_Sub5.aString3837 = null;
                Class98_Sub5.aClass157_3835 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cba.I(" + n + ')');
        }
    }
    
    final int method958(final int n) {
        try {
            return this.anInt3836;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cba.B(" + n + ')');
        }
    }
    
    abstract void method959(final float p0, final int p1);
    
    static final boolean method960(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        try {
            return ~n6 > ~(n - -n2) && ~n > ~(n9 + n6) && n5 + n7 > n4 && n7 < n4 + n8 && (n3 < -100 || true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cba.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + n9 + ')');
        }
    }
    
    final int method961(final byte b) {
        try {
            if (b != -78) {
                Class98_Sub5.aClass157_3835 = null;
            }
            return this.anInt3831;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cba.J(" + b + ')');
        }
    }
    
    final int method962(final int n) {
        try {
            if (n != 28699) {
                this.anInt3831 = -23;
            }
            return this.anInt3834;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cba.H(" + n + ')');
        }
    }
    
    final int method963(final byte b) {
        try {
            if (b < 68) {
                method960(-59, -92, -21, -124, 59, -109, 69, 61, -32);
            }
            return this.anInt3830;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cba.E(" + b + ')');
        }
    }
    
    Class98_Sub5(final int anInt3833, final int anInt3834, final int anInt3835, final int anInt3836, final int anInt3837, final float aFloat3832) {
        try {
            this.anInt3831 = anInt3837;
            this.anInt3833 = anInt3833;
            this.anInt3836 = anInt3836;
            this.aFloat3832 = aFloat3832;
            this.anInt3834 = anInt3835;
            this.anInt3830 = anInt3834;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cba.<init>(" + anInt3833 + ',' + anInt3834 + ',' + anInt3835 + ',' + anInt3836 + ',' + anInt3837 + ',' + aFloat3832 + ')');
        }
    }
    
    static {
        Class98_Sub5.aClass157_3835 = new Class157();
        Class98_Sub5.aString3837 = "";
    }
}
