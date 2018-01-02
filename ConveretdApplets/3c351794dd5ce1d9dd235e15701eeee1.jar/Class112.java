// 
// Decompiled by Procyon v0.5.30
// 

final class Class112
{
    static short aShort948;
    static int anInt949;
    
    static final String[] method2142(final String s, final char c, final boolean b) {
        try {
            final int method721 = Class73.method721(s, 72, c);
            final String[] array = new String[method721 + 1];
            int n = 0;
            int n2 = 0;
            for (int n3 = 0; ~n3 > ~method721; ++n3) {
                int n4;
                for (n4 = n2; c != s.charAt(n4); ++n4) {}
                array[n++] = s.substring(n2, n4);
                n2 = 1 + n4;
            }
            array[method721] = s.substring(n2);
            if (b) {
                Class112.anInt949 = 105;
            }
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hca.A(" + ((s != null) ? "{...}" : "null") + ',' + c + ',' + b + ')');
        }
    }
    
    static {
        Class112.aShort948 = 32767;
    }
}
