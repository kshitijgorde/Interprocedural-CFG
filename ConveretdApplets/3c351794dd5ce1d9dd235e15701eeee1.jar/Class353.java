// 
// Decompiled by Procyon v0.5.30
// 

final class Class353
{
    static int anInt3009;
    
    static final String method3867(final int n, final String s) {
        try {
            if (s == null) {
                return null;
            }
            int i;
            int length;
            for (i = 0, length = s.length(); i < length; ++i) {
                if (!Class93_Sub2.method911(s.charAt(i), 95)) {
                    break;
                }
            }
            while (i < length && Class93_Sub2.method911(s.charAt(-1 + length), 95)) {
                --length;
            }
            final int n2 = -i + length;
            if (~n2 > -2 || n2 > 12) {
                return null;
            }
            if (n != -1) {
                Class353.anInt3009 = 84;
            }
            final StringBuffer sb = new StringBuffer(n2);
            for (int n3 = i; ~n3 > ~length; ++n3) {
                final char char1 = s.charAt(n3);
                if (Class98_Sub43_Sub3.method1499((byte)105, char1)) {
                    final char method3829 = Class346.method3829(char1, n + 1);
                    if (~method3829 != -1) {
                        sb.append(method3829);
                    }
                }
            }
            if (sb.length() == 0) {
                return null;
            }
            return sb.toString();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vi.A(" + n + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method3868(final int anInt6053, final int n, final byte b, final int anInt6054, final int anInt6055) {
        try {
            final Class98_Sub46_Sub17 method2628 = Class185.method2628(n, -114, 8);
            method2628.method1626((byte)(-103));
            method2628.anInt6054 = anInt6054;
            method2628.anInt6053 = anInt6053;
            method2628.anInt6051 = anInt6055;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vi.B(" + anInt6053 + ',' + n + ',' + b + ',' + anInt6054 + ',' + anInt6055 + ')');
        }
    }
    
    static final void method3869(final int n, final int n2, final int n3) {
        try {
            if (Class21_Sub4.anInt5396 == 1) {
                PacketSender.method3604(n, (byte)78, n2, Class347.aClass98_Sub46_Sub8_2908);
            }
            else if (~Class21_Sub4.anInt5396 == 0xFFFFFFFD) {
                if (za_Sub2.aBoolean6079) {
                    Class98_Sub4.method953(n - -Class335.method3765(false), n2 - -Class189.method2642((byte)42), true);
                }
                else {
                    Class98_Sub4.method953(n, n2, true);
                }
            }
            Class21_Sub4.anInt5396 = 0;
            Class347.aClass98_Sub46_Sub8_2908 = null;
            if (n3 != -2) {
                method3867(-15, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vi.C(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
}
