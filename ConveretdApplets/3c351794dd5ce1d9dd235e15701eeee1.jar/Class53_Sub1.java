// 
// Decompiled by Procyon v0.5.30
// 

final class Class53_Sub1 extends Class53
{
    String aString3630;
    int anInt3631;
    int anInt3632;
    static Class164 aClass164_3633;
    String aString3634;
    static int anInt3635;
    static int anInt3636;
    
    Class53_Sub1() {
        this.anInt3631 = -1;
    }
    
    static final void method498(final int n) {
        try {
            for (int n2 = n; ~Class63.anInt493 < ~n2; ++n2) {
                final int n3 = Class198.method2678((byte)6, n2 - -Class268.anInt2007, Class63.anInt493) * Class191.anInt1477;
                for (int i = 0; i < Class191.anInt1477; ++i) {
                    final int n4 = n3 + Class198.method2678((byte)6, Class76_Sub8.anInt3780 + i, Class191.anInt1477);
                    if (~Class230.anInt1732 == ~Class146_Sub2.anIntArray4873[n4]) {
                        Class172.anInterface17Array1327[n4].method57(0, 0, Class197.anInt1513, Class98_Sub10_Sub38.anInt5761, Class197.anInt1513 * i, n2 * Class98_Sub10_Sub38.anInt5761, true, true);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jha.F(" + n + ')');
        }
    }
    
    static final boolean method499(final int n, final int n2) {
        try {
            if (n != 2048) {
                method502(119, -121, (byte)95);
            }
            return ~n2 == 0xFFFFFFFC || n2 == 4 || n2 == 5 || n2 == 6;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jha.G(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method500(final int n) {
        try {
            if (n >= -93) {
                method499(86, 125);
            }
            Class53_Sub1.aClass164_3633 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jha.J(" + n + ')');
        }
    }
    
    final Class114 method501(final int n) {
        try {
            if (n != -1) {
                method500(-79);
            }
            return Class98_Sub10_Sub36.aClass114Array5744[super.anInt426];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jha.I(" + n + ')');
        }
    }
    
    static final boolean method502(final int n, final int n2, final byte b) {
        try {
            if (b < 110) {
                method500(-79);
            }
            return ~(n2 & 0x800) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jha.H(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    static {
        Class53_Sub1.aClass164_3633 = new Class164(1);
        Class53_Sub1.anInt3636 = -1;
        Class53_Sub1.anInt3635 = -1;
    }
}
