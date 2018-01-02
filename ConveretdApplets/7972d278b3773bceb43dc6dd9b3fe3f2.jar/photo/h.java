// 
// Decompiled by Procyon v0.5.30
// 

package photo;

public final class h
{
    public static String dada(final String s) {
        return e(s, t(56, "(((/),"));
    }
    
    public static String e(final String s, final String s2) {
        final StringBuffer sb = new StringBuffer();
        final int length = s.length();
        final int length2 = s2.length();
        for (int i = 0; i < length; ++i) {
            sb.append((char)(s.charAt(i) ^ s2.charAt(i % length2)));
        }
        return sb.toString();
    }
    
    public static String t(int n, final String s) {
        final int n2 = 4;
        final int n3 = 5;
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        final char[] array = charArray;
        int i = 0;
        final int n4 = (n2 << n3) - 1 ^ 0x20;
        while (i != length) {
            final int n5 = i;
            final int n6 = array[i] ^ (n & n4);
            ++n;
            ++i;
            array[n5] = (char)n6;
        }
        return String.valueOf(array, 0, length).intern();
    }
}
