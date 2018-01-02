// 
// Decompiled by Procyon v0.5.30
// 

final class Class18
{
    boolean aBoolean207;
    Interface2_Impl2 anInterface2_Impl2_208;
    boolean aBoolean209;
    static Class113 aClass113_210;
    Interface2_Impl2 anInterface2_Impl2_211;
    static int anInt212;
    static Class11 aClass11_213;
    static int anInt214;
    static IncomingOpcode aClass58_215;
    
    public static void method246(final byte b) {
        try {
            Class18.aClass58_215 = null;
            if (b < -101) {
                Class18.aClass11_213 = null;
                Class18.aClass113_210 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bga.B(" + b + ')');
        }
    }
    
    static final String method247(long n, final int n2) {
        try {
            if (~n >= -1L || n >= 6582952005840035281L) {
                return null;
            }
            if (~(n % 37L) == -1L) {
                return null;
            }
            int n3 = 0;
            if (n2 > -59) {
                Class18.aClass11_213 = null;
            }
            for (long n4 = n; n4 != 0L; n4 /= 37L) {
                ++n3;
            }
            final StringBuffer sb = new StringBuffer(n3);
            while (n != 0L) {
                final long n5 = n;
                n /= 37L;
                char c = Exception_Sub1.aCharArray45[(int)(n5 + -(n * 37L))];
                if (~c == 0xFFFFFFA0) {
                    final int n6 = sb.length() - 1;
                    sb.setCharAt(n6, Character.toUpperCase(sb.charAt(n6)));
                    c = ' ';
                }
                sb.append(c);
            }
            sb.reverse();
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            return sb.toString();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bga.A(" + n + ',' + n2 + ')');
        }
    }
    
    Class18(final boolean aBoolean209) {
        try {
            this.aBoolean209 = aBoolean209;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bga.<init>(" + aBoolean209 + ')');
        }
    }
    
    static final void method248(final boolean b, final int n, final boolean b2) {
        try {
            if (b) {
                --Class98_Sub10_Sub9.anInt5580;
                if (~Class98_Sub10_Sub9.anInt5580 == -1) {
                    Class208.anIntArray1579 = null;
                }
            }
            if (b2) {
                --Class98_Sub18.anInt3952;
                if (Class98_Sub18.anInt3952 == 0) {
                    Class221.anIntArray1665 = null;
                }
            }
            if (n <= 33) {
                method248(false, -122, true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bga.C(" + b + ',' + n + ',' + b2 + ')');
        }
    }
    
    final void method249(final int n) {
        try {
            if (this.anInterface2_Impl2_208 != null) {
                this.anInterface2_Impl2_208.method72(false);
            }
            this.aBoolean207 = false;
            if (n != 0) {
                this.anInterface2_Impl2_208 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bga.E(" + n + ')');
        }
    }
    
    final boolean method250(final byte b) {
        try {
            if (b >= -19) {
                method248(false, 117, true);
            }
            return this.aBoolean207 && !this.aBoolean209;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bga.D(" + b + ')');
        }
    }
    
    static {
        Class18.aClass113_210 = new Class113(9, 2);
        Class18.anInt212 = 1;
        Class18.aClass58_215 = new IncomingOpcode(1, -1);
    }
}
