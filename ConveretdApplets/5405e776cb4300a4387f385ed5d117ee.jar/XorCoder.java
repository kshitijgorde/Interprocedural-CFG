// 
// Decompiled by Procyon v0.5.30
// 

class XorCoder
{
    public static String Encripta(final String s, final String s2) {
        String string = new String("");
        for (int i = 1; i <= s.length(); ++i) {
            string += Right("0" + Integer.toHexString(Mid(s2, (i - 1) % s2.length() + 1, 1).toCharArray()[0] ^ Mid(s, i, 1).toCharArray()[0]), 2);
        }
        return string;
    }
    
    public static String Desencripta(final String s, final String s2) {
        int n = 1;
        String string = new String("");
        for (int i = 1; i <= s.length(); i += 2) {
            final String mid = Mid(s, i, 2);
            mid.toCharArray();
            string += (char)(Mid(s2, (n - 1) % s2.length() + 1, 1).toCharArray()[0] ^ Integer.parseInt(mid, 16));
            ++n;
        }
        return string;
    }
    
    public static String Mid(String substring, final int n, final int n2) {
        substring = substring.substring(n - 1, n + n2 - 1);
        return substring;
    }
    
    public static String Right(String s, final int n) {
        s = reverse(s);
        s = s.substring(0, n);
        s = reverse(s);
        return s;
    }
    
    public static String reverse(final String s) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(length);
        for (int i = length - 1; i >= 0; --i) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
