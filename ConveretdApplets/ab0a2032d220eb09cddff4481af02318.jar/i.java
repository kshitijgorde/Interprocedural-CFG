// 
// Decompiled by Procyon v0.5.30
// 

public class i
{
    private static final char[] PIb;
    
    private static final boolean _(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '-' || c == '_' || c == '.' || c == '*';
    }
    
    public static String b(final String s) {
        final int length = s.length();
        int n = 0;
        int n2 = 0;
        final StringBuffer sb = new StringBuffer(length);
        while (true) {
            if (n < length && _(s.charAt(n))) {
                ++n;
            }
            else {
                sb.append(s.substring(n2, n));
                if (n >= length) {
                    break;
                }
                if (s.charAt(n) == ' ') {
                    sb.append('+');
                    ++n;
                }
                else {
                    final int n3 = n;
                    char char1;
                    while (n < length && (char1 = s.charAt(n)) != ' ' && !_(char1)) {
                        ++n;
                    }
                    final byte[] bytes = s.substring(n3, n).getBytes("UTF8");
                    for (int i = 0; i < bytes.length; ++i) {
                        sb.append('%');
                        final byte b = bytes[i];
                        sb.append(i.PIb[(b & 0xF0) >> 4]);
                        sb.append(i.PIb[b & 0xF]);
                    }
                }
                n2 = n;
            }
        }
        return sb.toString();
    }
    
    public static String b(final String s, final String s2, String s3) {
        if (s == null || s2 == null) {
            return s;
        }
        if (s3 == null) {
            s3 = "";
        }
        if (s2.length() == 0 || s2.equals(s3)) {
            return s;
        }
        int n = 0;
        int i = s.indexOf(s2);
        if (i == -1) {
            return s;
        }
        final StringBuffer sb = new StringBuffer(s.length() * 2 + s3.length());
        do {
            sb.append(s.substring(n, i));
            sb.append(s3);
            n = i + s2.length();
            i = s.indexOf(s2, n);
        } while (i != -1);
        sb.append(s.substring(n));
        return sb.toString();
    }
    
    public static void _(final String[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int length;
        int n;
        for (length = array.length, n = 1; n < length && array[n - 1].compareTo(array[n]) <= 0; ++n) {}
        if (n == length) {
            return;
        }
        for (int i = length / 2 - 1; i > 0; --i) {
            a(i, length - 1, array);
        }
        for (int j = length - 1; j > 0; --j) {
            a(0, j, array);
            final String s = array[0];
            array[0] = array[j];
            array[j] = s;
        }
    }
    
    private static void a(int n, final int n2, final String[] array) {
        final String s = array[n];
        final int n3 = n;
        int i;
        for (i = n + n + 1; i < n2; i = (n = i) + i + 1) {
            if (array[i].compareTo(array[i + 1]) < 0) {
                ++i;
            }
            array[n] = array[i];
            n = i;
        }
        if (i == n2) {
            array[n] = array[i];
            n = i;
        }
        for (int n4 = n - 1 >> 1; n4 >= n3 && array[n4].compareTo(s) <= 0; n4 = (n = n4) - 1 >> 1) {
            array[n] = array[n4];
        }
        array[n] = s;
    }
    
    static {
        PIb = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
}
