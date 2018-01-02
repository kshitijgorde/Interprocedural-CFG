// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

public class GscUtils
{
    private static String a;
    
    public static String a(final String s, final String s2) {
        return a("4pfMx3N9K", "609", s, s2);
    }
    
    private static String a(final String s, final String s2, final String s3, final String s4) {
        final int[] array = new int[s.length()];
        final int[] array2 = { 23, 77, 105, 59, 123, 35, 81, 27, 53 };
        if (s3.length() < s.length() + s2.length() + 1) {
            throw new RuntimeException(String.valueOf(s4) + " file too short");
        }
        int i;
        for (i = 0; i < array.length; ++i) {
            int n = s3.charAt(i) - s.charAt(i);
            if (n < 1) {
                n += 128;
            }
            array[i] = n;
        }
        final int length = s2.length();
        final int length2 = s3.length();
        final StringBuffer sb = new StringBuffer(s3.length());
        int n2 = 0;
        int n3 = 0;
        while (i < length2) {
            char char1 = s3.charAt(i);
            if (char1 < '\u0080') {
                int n4 = char1 - array[n3];
                if (n4 < 0) {
                    n4 += 128;
                }
                char1 = (char)n4;
                if (++n3 >= array2.length) {
                    n3 = 0;
                }
            }
            if (n2 <= length) {
                if (((n2 == length) ? '\n' : s2.charAt(n2)) != char1) {
                    throw new RuntimeException(String.valueOf(s4) + " file confirm failed");
                }
            }
            else {
                sb.append(char1);
            }
            if (char1 == '\n') {
                for (int j = 0; j < array2.length; ++j) {
                    int n5 = array[j] + array2[j];
                    if (n5 > 128) {
                        n5 -= 128;
                    }
                    array[j] = n5;
                }
                n3 = 0;
            }
            ++n2;
            ++i;
        }
        return sb.toString();
    }
    
    static {
        final byte[] array = { 120 };
        GscUtils.a = "UTF-8";
        String s;
        try {
            s = new String(array, GscUtils.a);
        }
        catch (Throwable t) {
            GscUtils.a = "UTF8";
            try {
                s = new String(array, GscUtils.a);
            }
            catch (Throwable t2) {
                throw new RuntimeException("GscUtils: No UTF-8 encoding supported");
            }
        }
        if (s.length() == 0) {
            throw new RuntimeException("GscUtils: UTF-8 length zero");
        }
    }
}
