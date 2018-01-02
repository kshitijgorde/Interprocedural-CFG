// 
// Decompiled by Procyon v0.5.30
// 

public class Pol
{
    public static String k(final String a) {
        final int n = 4 << 3 ^ 0x1;
        final int n2 = 4;
        final int n3 = n2 << n2;
        final int length = a.length();
        final char[] array = new char[length];
        int n4;
        int i = n4 = length - 1;
        final char[] array2 = array;
        final char c = (char)n3;
        final int n5 = n;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n6 = n4;
            final char char1 = a.charAt(n6);
            --n4;
            array3[n6] = (char)(char1 ^ n5);
            if (n4 < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n7 = n4;
            final char c2 = (char)(a.charAt(n7) ^ c);
            --n4;
            array4[n7] = c2;
            i = n4;
        }
        return new String(array2);
    }
    
    String ALLATORI_DEMO(final String a) {
        String string = "";
        int n;
        int i = n = 0;
        while (i < a.length()) {
            int char1 = a.charAt(n);
            final StringBuilder sb = new StringBuilder();
            char1 -= 4;
            final StringBuilder insert = sb.insert(0, string);
            final char c = (char)char1;
            ++n;
            string = insert.append(c).toString();
            i = n;
        }
        return string;
    }
    
    String ALLATORI_DEMO() {
        return System.getProperty(k("*J3S,M&G!W!\u000f)J3S,M&G/\u000f4L+R2M,G&Q$H+R2M,G&S").replace(Efira.ALLATORI_DEMO("c\u000fz\u0010d\u001an"), ""));
    }
}
