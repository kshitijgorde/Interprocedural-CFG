// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class246_Sub3_Sub3 extends Class246_Sub3
{
    static Class254 aClass254_6152;
    short aShort6153;
    static Class206 aClass206_6154;
    static Class143 aClass143_6155;
    static String aString6156;
    
    @Override
    final int method2980(final int n, final Class98_Sub5[] array) {
        try {
            final int n2 = super.anInt5084 >> Class151_Sub8.anInt5015;
            final int n3 = super.anInt5079 >> Class151_Sub8.anInt5015;
            int n4 = 0;
            if (n2 != Class241.anInt1845) {
                if (Class241.anInt1845 < n2) {
                    n4 += 2;
                }
            }
            else {
                ++n4;
            }
            if (~Class64_Sub26.anInt3714 != ~n3) {
                if (~n3 > ~Class64_Sub26.anInt3714) {
                    n4 += 6;
                }
            }
            else {
                n4 += 3;
            }
            if (~(this.aShort6153 & Class241.anIntArray1846[n4]) != -1) {
                return this.method2989(n2, false, array, n3);
            }
            if (~this.aShort6153 == 0xFFFFFFFE && n2 > 0) {
                return this.method2989(-1 + n2, false, array, n3);
            }
            if (~this.aShort6153 == 0xFFFFFFFB && n2 <= Class366.anInt3112) {
                return this.method2989(1 + n2, false, array, n3);
            }
            if (this.aShort6153 == 8 && n3 > 0) {
                return this.method2989(n2, false, array, -1 + n3);
            }
            if (this.aShort6153 == 2 && ~Class64_Sub9.anInt3662 <= ~n3) {
                return this.method2989(n2, false, array, 1 + n3);
            }
            if (this.aShort6153 == 16 && n2 > 0 && ~n3 >= ~Class64_Sub9.anInt3662) {
                return this.method2989(-1 + n2, false, array, 1 + n3);
            }
            if (~this.aShort6153 == 0xFFFFFFDF && ~Class366.anInt3112 <= ~n2 && Class64_Sub9.anInt3662 >= n3) {
                return this.method2989(n2 + 1, false, array, 1 + n3);
            }
            if (this.aShort6153 == 128 && ~n2 < -1 && ~n3 < -1) {
                return this.method2989(n2 - 1, false, array, n3 - 1);
            }
            if (~this.aShort6153 == 0xFFFFFFBF && n2 <= Class366.anInt3112 && ~n3 < -1) {
                return this.method2989(n2 + 1, false, array, n3 - 1);
            }
            throw new RuntimeException("");
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jn.GA(" + n + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method2991(final boolean b) {
        try {
            if (b) {
                Class246_Sub3_Sub3.aString6156 = null;
            }
            return Class74.aBooleanArrayArray551[(super.anInt5084 >> Class151_Sub8.anInt5015) + (-Class241.anInt1845 - -Class259.anInt1959)][-Class64_Sub26.anInt3714 + ((super.anInt5079 >> Class151_Sub8.anInt5015) + Class259.anInt1959)];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jn.FA(" + b + ')');
        }
    }
    
    static final boolean method3011(final int n, final int n2) {
        try {
            return n == -6410 && (~n2 == 0xFFFFFFF8 || n2 == 8 || n2 == 9);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jn.L(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method3012(final Class246_Sub3_Sub4_Sub2 class246_Sub3_Sub4_Sub2, final byte b) {
        try {
            if (b >= -105) {
                method3012(null, (byte)25);
            }
            if (!(class246_Sub3_Sub4_Sub2 instanceof Class246_Sub3_Sub4_Sub2_Sub1)) {
                if (class246_Sub3_Sub4_Sub2 instanceof Class246_Sub3_Sub4_Sub2_Sub2) {
                    final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = (Class246_Sub3_Sub4_Sub2_Sub2)class246_Sub3_Sub4_Sub2;
                    Class98_Sub30.method1311(~class246_Sub3_Sub4_Sub2_Sub2.aByte5088 != ~Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088, true, class246_Sub3_Sub4_Sub2_Sub2);
                }
            }
            else {
                final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub3 = (Class246_Sub3_Sub4_Sub2_Sub1)class246_Sub3_Sub4_Sub2;
                if (class246_Sub3_Sub4_Sub2_Sub3.aClass141_6504 != null) {
                    Class98_Sub10.method995(class246_Sub3_Sub4_Sub2_Sub3, (byte)55, class246_Sub3_Sub4_Sub2_Sub3.aByte5088 != Class87.aClass246_Sub3_Sub4_Sub2_Sub2_660.aByte5088);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jn.D(" + ((class246_Sub3_Sub4_Sub2 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public static void method3013(final byte b) {
        try {
            if (b != -93) {
                Class246_Sub3_Sub3.aClass143_6155 = null;
            }
            Class246_Sub3_Sub3.aClass206_6154 = null;
            Class246_Sub3_Sub3.aString6156 = null;
            Class246_Sub3_Sub3.aClass254_6152 = null;
            Class246_Sub3_Sub3.aClass143_6155 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jn.F(" + b + ')');
        }
    }
    
    @Override
    final boolean method2977(final ha ha, final byte b) {
        try {
            return b == 77 && Class100.method1688(super.aByte5081, super.anInt5084 >> Class151_Sub8.anInt5015, super.anInt5079 >> Class151_Sub8.anInt5015, this, (byte)112);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jn.AA(" + ((ha != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final int method3014(final int n, final int n2) {
        try {
            if (n != 1) {
                method3013((byte)(-80));
            }
            if (n2 == 6406) {
                return 1;
            }
            if (~n2 == 0xFFFFE6F6) {
                return 1;
            }
            if (n2 == 32841) {
                return 1;
            }
            if (n2 == 6410) {
                return 2;
            }
            if (n2 == 6407) {
                return 3;
            }
            if (~n2 == 0xFFFFE6F7) {
                return 4;
            }
            throw new IllegalArgumentException("");
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jn.K(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method3015(final int n, final int n2, final byte b, final Class28 class28) {
        try {
            Class76.aClass28ArrayArray586[n2][n] = class28;
            if (b <= 20) {
                method3013((byte)(-29));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jn.M(" + n + ',' + n2 + ',' + b + ',' + ((class28 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class246_Sub3_Sub3(final int anInt5084, final int anInt5085, final int anInt5086, final int n, final int n2, final int n3) {
        try {
            this.aShort6153 = (short)n3;
            super.anInt5084 = anInt5084;
            super.aByte5088 = (byte)n;
            super.aByte5081 = (byte)n2;
            super.anInt5089 = anInt5085;
            super.anInt5079 = anInt5086;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jn.<init>(" + anInt5084 + ',' + anInt5085 + ',' + anInt5086 + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static {
        Class246_Sub3_Sub3.aClass254_6152 = new Class254(8);
        Class246_Sub3_Sub3.aClass206_6154 = Class64_Sub20.method636((byte)5);
        Class246_Sub3_Sub3.aString6156 = null;
    }
}
