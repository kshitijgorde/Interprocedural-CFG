// 
// Decompiled by Procyon v0.5.30
// 

package a.b.a;

public class d
{
    private static String[] z;
    
    public static int a(final String s) {
        if (s == null) {
            return -1;
        }
        if (s.equalsIgnoreCase(d.z[0])) {
            return 0;
        }
        if (s.equalsIgnoreCase(d.z[1])) {
            return 1;
        }
        if (s.equalsIgnoreCase(d.z[2])) {
            return 2;
        }
        if (s.equalsIgnoreCase(d.z[3])) {
            return 3;
        }
        return -1;
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "n[^Y".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\"';
                    break;
                }
                case 1: {
                    c2 = '\u001e';
                    break;
                }
                case 2: {
                    c2 = '\u0018';
                    break;
                }
                case 3: {
                    c2 = '\r';
                    break;
                }
                default: {
                    c2 = '\\';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "pW_E\b".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\"';
                    break;
                }
                case 1: {
                    c4 = '\u001e';
                    break;
                }
                case 2: {
                    c4 = '\u0018';
                    break;
                }
                case 3: {
                    c4 = '\r';
                    break;
                }
                default: {
                    c4 = '\\';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "wN".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\"';
                    break;
                }
                case 1: {
                    c6 = '\u001e';
                    break;
                }
                case 2: {
                    c6 = '\u0018';
                    break;
                }
                case 3: {
                    c6 = '\r';
                    break;
                }
                default: {
                    c6 = '\\';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "fQOC".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\"';
                    break;
                }
                case 1: {
                    c8 = '\u001e';
                    break;
                }
                case 2: {
                    c8 = '\u0018';
                    break;
                }
                case 3: {
                    c8 = '\r';
                    break;
                }
                default: {
                    c8 = '\\';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        d.z = z;
    }
}
