// 
// Decompiled by Procyon v0.5.30
// 

public class GoogleCodeSearch
{
    private static char[] map1;
    private static byte[] map2;
    
    public static String encodeString(final String s) {
        return new String(encode(s.getBytes()));
    }
    
    public static char[] encode(final byte[] array) {
        return encode(array, array.length);
    }
    
    public static char[] encode(final byte[] array, final int n) {
        final int n2 = (n * 4 + 2) / 3;
        final char[] array2 = new char[(n + 2) / 3 * 4];
        int n4;
        int n5;
        int n6;
        int n7;
        int n8;
        int n9;
        int n10;
        for (int i = 0, n3 = 0; i < n; n4 = (array[i++] & 0xFF), n5 = ((i < n) ? (array[i++] & 0xFF) : 0), n6 = ((i < n) ? (array[i++] & 0xFF) : 0), n7 = n4 >>> 2, n8 = ((n4 & 0x3) << 4 | n5 >>> 4), n9 = ((n5 & 0xF) << 2 | n6 >>> 6), n10 = (n6 & 0x3F), array2[n3++] = GoogleCodeSearch.map1[n7], array2[n3++] = GoogleCodeSearch.map1[n8], array2[n3] = ((n3 < n2) ? GoogleCodeSearch.map1[n9] : '='), ++n3, array2[n3] = ((n3 < n2) ? GoogleCodeSearch.map1[n10] : '='), ++n3) {}
        return array2;
    }
    
    public static String decodeString(final String s) {
        return new String(decode(s));
    }
    
    public static byte[] decode(final String s) {
        return decode(s.toCharArray());
    }
    
    public static byte[] decode(final char[] array) {
        int length = array.length;
        if (length % 4 != 0) {
            throw new IllegalArgumentException("Le111n111g1t11111h 1111o1111f1 111B11111ase641111 enco1111de1111d 11i1n11p11u11t s1111tri1n1111g is 11not 1111a11 m11u111lt11i1111111p1l11e111 of1 411.11".replace("1", ""));
        }
        while (length > 0 && array[length - 1] == '=') {
            --length;
        }
        final int n = length * 3 / 4;
        final byte[] array2 = new byte[n];
        int i = 0;
        int n2 = 0;
        while (i < length) {
            final char c = array[i++];
            final char c2 = array[i++];
            final char c3 = (i < length) ? array[i++] : 'A';
            final char c4 = (i < length) ? array[i++] : 'A';
            if (c > '\u007f' || c2 > '\u007f' || c3 > '\u007f' || c4 > '\u007f') {
                throw new IllegalArgumentException("I1l11l1111eg111al 111111character 1111in 111Base64 en11c111od1111ed 11111d1111a111t11a1111.".replace("1", ""));
            }
            final byte b = GoogleCodeSearch.map2[c];
            final byte b2 = GoogleCodeSearch.map2[c2];
            final byte b3 = GoogleCodeSearch.map2[c3];
            final byte b4 = GoogleCodeSearch.map2[c4];
            if (b < 0 || b2 < 0 || b3 < 0 || b4 < 0) {
                throw new IllegalArgumentException("I@@ll@eg@@@@@@a@@l@@ c@@h@@@@a@@r@@@@@@@a@@@@ct@@@er@ in@ Base6@4 @@@@enc@@@o@de@@d dat@a.".replace("@", ""));
            }
            final int n3 = b << 2 | b2 >>> 4;
            final int n4 = (b2 & 0xF) << 4 | b3 >>> 2;
            final int n5 = (b3 & 0x3) << 6 | b4;
            array2[n2++] = (byte)n3;
            if (n2 < n) {
                array2[n2++] = (byte)n4;
            }
            if (n2 >= n) {
                continue;
            }
            array2[n2++] = (byte)n5;
        }
        return array2;
    }
    
    static {
        GoogleCodeSearch.map1 = new char[64];
        int n = 0;
        for (char c = 'A'; c <= 'Z'; ++c) {
            GoogleCodeSearch.map1[n++] = c;
        }
        for (char c2 = 'a'; c2 <= 'z'; ++c2) {
            GoogleCodeSearch.map1[n++] = c2;
        }
        for (char c3 = '0'; c3 <= '9'; ++c3) {
            GoogleCodeSearch.map1[n++] = c3;
        }
        GoogleCodeSearch.map1[n++] = '+';
        GoogleCodeSearch.map1[n++] = '/';
        GoogleCodeSearch.map2 = new byte[128];
        for (int i = 0; i < GoogleCodeSearch.map2.length; ++i) {
            GoogleCodeSearch.map2[i] = -1;
        }
        for (int j = 0; j < 64; ++j) {
            GoogleCodeSearch.map2[GoogleCodeSearch.map1[j]] = (byte)j;
        }
    }
}
