// 
// Decompiled by Procyon v0.5.30
// 

public class b
{
    public static String a(final String a) {
        final char[] array = new char[a.length()];
        final char c = 'U';
        int n;
        int i = n = array.length - 1;
        final char[] array2 = array;
        char c2 = c;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n2 = n;
            final char c3 = (char)(a.charAt(n) ^ c2);
            final char c4 = (char)((char)(n2 ^ c2) & '?');
            array3[n2] = c3;
            if (--n < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n3 = n;
            final char c5 = (char)(a.charAt(n) ^ c4);
            c2 = (char)((char)(n3 ^ c4) & '?');
            array4[n3] = c5;
            i = --n;
        }
        return new String(array2);
    }
}
