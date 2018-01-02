// 
// Decompiled by Procyon v0.5.30
// 

public class m
{
    public static String C(final String r) {
        final char[] array = new char[r.length()];
        final char c = 'U';
        int n;
        int i = n = array.length - 1;
        final char[] array2 = array;
        char c2 = c;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n2 = n;
            final char c3 = (char)(r.charAt(n) ^ c2);
            final char c4 = (char)((char)(n2 ^ c2) & '?');
            array3[n2] = c3;
            if (--n < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n3 = n;
            final char c5 = (char)(r.charAt(n) ^ c4);
            c2 = (char)((char)(n3 ^ c4) & '?');
            array4[n3] = c5;
            i = --n;
        }
        return new String(array2);
    }
    
    public static String b(final String r) {
        final int n = 1;
        final StackTraceElement stackTraceElement = new Exception().getStackTrace()[n];
        final String string = stackTraceElement.getClassName() + stackTraceElement.getMethodName();
        final int n2 = string.length() - n;
        final String s = string;
        int n3 = n2;
        final int length = r.length();
        final char[] array = new char[length];
        char c = 'U';
        int n4 = length - n;
        Label_0125: {
            break Label_0125;
            int i = 0;
            do {
                final char c2 = '?';
                final char c3 = c;
                final char char1 = s.charAt(n3);
                final int n5 = n4;
                final char[] array2;
                array2[n5] = (char)(char1 ^ (r.charAt(n5) ^ c));
                c = (char)(c2 & (c3 ^ (i ^ char1)));
                if (--n3 < 0) {
                    n3 = n2;
                }
                --n4;
                array2 = array;
                i = n4;
            } while (i >= 0);
        }
        return new String(array);
    }
}
