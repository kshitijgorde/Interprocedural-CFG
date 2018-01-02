// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

public class a
{
    public static final String a;
    public static final String b;
    public static final String c;
    private static String[] z;
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "\u000fz\u001a\u0016w4X".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c2 = charArray[n3];
            char c3 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c3 = '@';
                    break;
                }
                case 1: {
                    c3 = '\n';
                    break;
                }
                case 2: {
                    c3 = 'n';
                    break;
                }
                case 3: {
                    c3 = 'E';
                    break;
                }
                default: {
                    c3 = '\u0012';
                    break;
                }
            }
            charArray[n3] = (char)(c2 ^ c3);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u000fz\u001a\u0016w4F".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c4 = charArray2[n6];
            char c5 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c5 = '@';
                    break;
                }
                case 1: {
                    c5 = '\n';
                    break;
                }
                case 2: {
                    c5 = 'n';
                    break;
                }
                case 3: {
                    c5 = 'E';
                    break;
                }
                default: {
                    c5 = '\u0012';
                    break;
                }
            }
            charArray2[n6] = (char)(c4 ^ c5);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u000fz\u001a\u0001s4k".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c6 = charArray3[n9];
            char c7 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c7 = '@';
                    break;
                }
                case 1: {
                    c7 = '\n';
                    break;
                }
                case 2: {
                    c7 = 'n';
                    break;
                }
                case 3: {
                    c7 = 'E';
                    break;
                }
                default: {
                    c7 = '\u0012';
                    break;
                }
            }
            charArray3[n9] = (char)(c6 ^ c7);
        }
        z[n7] = new String(charArray3).intern();
        a.a.a.a.z = z;
        a = new String(a.a.a.a.z[0]);
        b = new String(a.a.a.a.z[1]);
        c = new String(a.a.a.a.z[2]);
    }
}
