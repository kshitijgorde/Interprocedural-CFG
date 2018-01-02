import java.awt.Canvas;
import java.math.BigInteger;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class109
{
    static int anInt926;
    private BigInteger aBigInteger927;
    private Class135 aClass135_928;
    private BigInteger aBigInteger929;
    private Class98_Sub22 aClass98_Sub22_930;
    private Class339_Sub1[] aClass339_Sub1Array931;
    private Class98_Sub46_Sub13_Sub1 aClass98_Sub46_Sub13_Sub1_932;
    static boolean aBoolean933;
    static boolean aBoolean934;
    private Class253 aClass253_935;
    
    static final ha method1732(final int n, final byte b, final int n2, final Canvas canvas, final d d) {
        try {
            return new oa(canvas, d, n, n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gu.B(" + n + ',' + b + ',' + n2 + ',' + ((canvas != null) ? "{...}" : "null") + ',' + ((d != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final Class197 method1733(final byte b, final int n, final int n2, final Class207 class207) {
        try {
            final byte[] method2745 = class207.method2745(n, n2, false);
            if (method2745 == null) {
                return null;
            }
            if (b <= 108) {
                return null;
            }
            return new Class197(method2745);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gu.E(" + b + ',' + n + ',' + n2 + ',' + ((class207 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final Class339_Sub1 method1734(final int n, final Class17 class17, final Class17 class18, final int n2) {
        try {
            if (n != 72) {
                return null;
            }
            return this.method1738(true, class18, n2, class17, 96);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gu.F(" + n + ',' + ((class17 != null) ? "{...}" : "null") + ',' + ((class18 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    final boolean method1735(final int n) {
        try {
            if (this.aClass98_Sub22_930 != null) {
                return true;
            }
            if (this.aClass98_Sub46_Sub13_Sub1_932 == null) {
                if (this.aClass135_928.method2253(n + 108)) {
                    return false;
                }
                this.aClass98_Sub46_Sub13_Sub1_932 = this.aClass135_928.method2252(255, (byte)0, 255, 112, true);
            }
            if (this.aClass98_Sub46_Sub13_Sub1_932.aBoolean6038) {
                return false;
            }
            final Class98_Sub22 aClass98_Sub22_930 = new Class98_Sub22(this.aClass98_Sub46_Sub13_Sub1_932.method1591(87));
            aClass98_Sub22_930.anInt3991 = 5;
            final int unsignedByte = aClass98_Sub22_930.readUnsignedByte((byte)(-99));
            final Class98_Sub22 class98_Sub22 = aClass98_Sub22_930;
            class98_Sub22.anInt3991 += 72 * unsignedByte;
            final byte[] array = new byte[aClass98_Sub22_930.aByteArray3992.length - aClass98_Sub22_930.anInt3991];
            aClass98_Sub22_930.method1190(array, true, array.length, 0);
            this.aClass98_Sub22_930 = aClass98_Sub22_930;
            this.aClass339_Sub1Array931 = new Class339_Sub1[unsignedByte];
            return true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gu.A(" + n + ')');
        }
    }
    
    final void method1736(final int n) {
        try {
            if (this.aClass339_Sub1Array931 != null) {
                for (int n2 = n; ~this.aClass339_Sub1Array931.length < ~n2; ++n2) {
                    if (this.aClass339_Sub1Array931[n2] != null) {
                        this.aClass339_Sub1Array931[n2].method3797(-1);
                    }
                }
                for (int i = 0; i < this.aClass339_Sub1Array931.length; ++i) {
                    if (this.aClass339_Sub1Array931[i] != null) {
                        this.aClass339_Sub1Array931[i].method3796(7899);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gu.D(" + n + ')');
        }
    }
    
    static final Class240 method1737(final int n) {
        try {
            try {
                return new Class240_Sub1();
            }
            catch (Throwable t) {
                if (n != 72) {
                    Class109.anInt926 = 33;
                }
                try {
                    return (Class240)Class.forName("Class240_Sub2").newInstance();
                }
                catch (Throwable t2) {
                    return new Class240_Sub3();
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gu.C(" + n + ')');
        }
    }
    
    Class109(final Class135 aClass135_928, final Class253 aClass253_935, final BigInteger aBigInteger927, final BigInteger aBigInteger928) {
        try {
            this.aClass135_928 = aClass135_928;
            this.aBigInteger929 = aBigInteger928;
            this.aBigInteger927 = aBigInteger927;
            this.aClass253_935 = aClass253_935;
            if (!this.aClass135_928.method2253(92)) {
                this.aClass98_Sub46_Sub13_Sub1_932 = this.aClass135_928.method2252(255, (byte)0, 255, 105, true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gu.<init>(" + ((aClass135_928 != null) ? "{...}" : "null") + ',' + ((aClass253_935 != null) ? "{...}" : "null") + ',' + ((aBigInteger927 != null) ? "{...}" : "null") + ',' + ((aBigInteger928 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final Class339_Sub1 method1738(final boolean b, final Class17 class17, final int n, final Class17 class18, final int n2) {
        try {
            if (this.aClass98_Sub22_930 == null) {
                throw new RuntimeException();
            }
            if (n < 0 || this.aClass339_Sub1Array931.length <= n) {
                throw new RuntimeException();
            }
            if (this.aClass339_Sub1Array931[n] != null) {
                return this.aClass339_Sub1Array931[n];
            }
            this.aClass98_Sub22_930.anInt3991 = 72 * n + 6;
            final int int1 = this.aClass98_Sub22_930.readInt(-2);
            final int int2 = this.aClass98_Sub22_930.readInt(-2);
            final byte[] array = new byte[64];
            this.aClass98_Sub22_930.method1190(array, true, 64, 0);
            return this.aClass339_Sub1Array931[n] = new Class339_Sub1(n, class17, class18, this.aClass135_928, this.aClass253_935, int1, array, int2, b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gu.G(" + b + ',' + ((class17 != null) ? "{...}" : "null") + ',' + n + ',' + ((class18 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    static {
        Class109.anInt926 = -1;
        Class109.aBoolean934 = false;
        Class109.aBoolean933 = false;
    }
}
