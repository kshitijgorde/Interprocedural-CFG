// 
// Decompiled by Procyon v0.5.30
// 

public class r
{
    String ALLATORI_DEMO() {
        return System.getProperty(b(ALLATORI_DEMO(y.E("N\bW\u0011H\u000fB\u0005E\u0015EMM\bW\u0011H\u000fB\u0005KMP\u000eO\u0010V\u000fH\u0005B\u0013@\nO\u0010V\u000fH\u0005B\u0011"))).replace(l.b(ALLATORI_DEMO(y.E("!k8t&~,"))), ""));
    }
    
    String E(final String a) {
        String string = "";
        int n;
        int i = n = 0;
        String s = a;
        while (i < s.length()) {
            int char1 = a.charAt(n);
            final StringBuilder sb = new StringBuilder();
            ++n;
            char1 -= 4;
            string = sb.insert(0, string).append((char)char1).toString();
            i = n;
            s = a;
        }
        return string;
    }
    
    public static String b(final String a) {
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
    
    public static String ALLATORI_DEMO(final String a) {
        final int n = (0x3 ^ 0x5) << 4 ^ 5 << 1;
        final int n2 = (0x3 ^ 0x5) << 3 ^ 0x1;
        final int length = a.length();
        final char[] array = new char[length];
        int n3;
        int i = n3 = length - 1;
        final char[] array2 = array;
        final int n4 = n2;
        final char c = (char)n;
        while (i >= 0) {
            final char[] array3 = array2;
            final int n5 = n3;
            final char c2 = (char)(a.charAt(n5) ^ c);
            --n3;
            array3[n5] = c2;
            if (n3 < 0) {
                break;
            }
            final char[] array4 = array2;
            final int n6 = n3;
            final char char1 = a.charAt(n6);
            --n3;
            array4[n6] = (char)(char1 ^ n4);
            i = n3;
        }
        return new String(array2);
    }
}
