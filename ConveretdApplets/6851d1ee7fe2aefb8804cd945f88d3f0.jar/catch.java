import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

// 
// Decompiled by Procyon v0.5.30
// 

public class catch
{
    private static final char[] Ura;
    
    private static final boolean a(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '-' || c == '_' || c == '.' || c == '*';
    }
    
    public static String h(final String s) {
        final int length = s.length();
        int n = 0;
        int n2 = 0;
        final StringBuffer sb = new StringBuffer(length);
        while (true) {
            if (n < length && a(s.charAt(n))) {
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
                    while (n < length && (char1 = s.charAt(n)) != ' ' && !a(char1)) {
                        ++n;
                    }
                    final byte[] bytes = s.substring(n3, n).getBytes("UTF8");
                    for (int i = 0; i < bytes.length; ++i) {
                        sb.append('%');
                        final byte b = bytes[i];
                        sb.append(catch.Ura[(b & 0xF0) >> 4]);
                        sb.append(catch.Ura[b & 0xF]);
                    }
                }
                n2 = n;
            }
        }
        return sb.toString();
    }
    
    public static String a(final String s, final String s2, String s3) {
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
    
    public static void b(final String[] array) {
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
            _(i, length - 1, array);
        }
        for (int j = length - 1; j > 0; --j) {
            _(0, j, array);
            final String s = array[0];
            array[0] = array[j];
            array[j] = s;
        }
    }
    
    private static void _(int n, final int n2, final String[] array) {
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
    
    public static byte[] _(final String s) {
        try {
            return MessageDigest.getInstance("MD5").digest(s.getBytes("UTF8"));
        }
        catch (NoSuchAlgorithmException ex) {
            return new byte[0];
        }
        catch (UnsupportedEncodingException ex2) {
            return new byte[0];
        }
    }
    
    public static byte[] b(final String s) {
        if (s == null) {
            return new byte[0];
        }
        final byte[] array = new byte[16];
        int n = 0;
        try {
            for (int n2 = 0; n2 < s.length() && n <= array.length - 1; n2 += 2) {
                int n3;
                if (n2 + 2 > s.length()) {
                    n3 = Integer.parseInt(s.substring(n2), 16);
                }
                else {
                    n3 = Integer.parseInt(s.substring(n2, n2 + 2), 16);
                }
                if (n3 <= 127) {
                    array[n++] = (byte)n3;
                }
                else {
                    array[n++] = (byte)(n3 - 256);
                }
            }
            return array;
        }
        catch (NumberFormatException ex) {
            return new byte[0];
        }
    }
    
    public static String a(final byte[] array) {
        final StringBuffer sb = new StringBuffer(64);
        for (int i = 0; i < array.length; ++i) {
            String s;
            for (s = Integer.toHexString((array[i] >= 0) ? array[i] : (256 + array[i])); s.length() < 2; s = "0" + s) {}
            sb.append(s);
        }
        return sb.toString();
    }
    
    public static String i(final String s) {
        if (s == null) {
            return null;
        }
        final StringBuffer sb = new StringBuffer(s.length());
        for (int i = s.length() - 1; i >= 0; --i) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    
    static {
        Ura = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
}
