// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class111
{
    static byte aByte947;
    
    static final boolean method2089(final byte b, final char c) {
        try {
            if ((c > '\0' && c < '\u0080') || (c >= ' ' && ~c >= -256)) {
                return true;
            }
            if (c != '\0') {
                final char[] aCharArray497 = Class65.aCharArray497;
                for (int i = 0; i < aCharArray497.length; ++i) {
                    if (c == aCharArray497[i]) {
                        return true;
                    }
                }
            }
            return b <= 47;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hba.CB(" + b + ',' + c + ')');
        }
    }
    
    abstract void method2090(final int p0);
    
    abstract void method2091();
    
    abstract void method2092(final Class111 p0);
    
    abstract void method2093(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    static final int method2094(final String s, final int n) {
        try {
            if (n >= -95) {
                Class111.aByte947 = -46;
            }
            if (s == null) {
                return -1;
            }
            for (int n2 = 0; ~Class314.anInt2692 < ~n2; ++n2) {
                if (s.equalsIgnoreCase(Class98_Sub25.aStringArray4026[n2])) {
                    return n2;
                }
            }
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hba.BB(" + ((s != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final boolean method2095(final int n, final int n2, final byte b) {
        try {
            if (b > -84) {
                method2095(67, 68, (byte)112);
            }
            return ~(n2 & 0x20) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hba.DB(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    abstract void method2096(final int p0, final int p1, final int p2, final int[] p3);
    
    abstract void method2097(final int p0);
    
    static final void method2098(final Class246_Sub3 class246_Sub3) {
        Class98_Sub10_Sub30.aHa5709.H(class246_Sub3.anInt5084, class246_Sub3.anInt5089 + (class246_Sub3.method2990(0) >> 1), class246_Sub3.anInt5079, Class226.anIntArray1699);
        class246_Sub3.anInt5085 = Class226.anIntArray1699[0];
        class246_Sub3.anInt5080 = Class226.anIntArray1699[1];
        class246_Sub3.anInt5083 = Class226.anIntArray1699[2];
    }
    
    abstract void method2099(final int p0, final int p1, final int p2, final int[] p3);
    
    abstract void method2100(final int p0, final int p1, final int p2);
    
    abstract void method2101(final int p0);
    
    abstract Class111 method2102();
    
    abstract void method2103(final int p0, final int p1, final int p2, final int[] p3);
    
    abstract void method2104(final int p0);
    
    abstract void method2105(final int p0);
    
    abstract void method2106(final int p0, final int p1, final int p2);
    
    abstract void method2107(final int p0);
    
    abstract void method2108(final int[] p0);
}
