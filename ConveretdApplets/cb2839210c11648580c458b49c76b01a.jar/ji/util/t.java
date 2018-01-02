// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

public final class t
{
    public static String a(final String s, final String s2, final String s3) {
        if (s2 != null && s3 != null && s != null && s2.length() > 0 && !s2.equals(s3)) {
            StringBuffer sb = null;
            final String lowerCase = s.toLowerCase();
            final String lowerCase2 = s2.toLowerCase();
            s3.toLowerCase();
            int n;
            int index;
            for (n = 0; (index = lowerCase.indexOf(lowerCase2, n)) >= 0; n = index + lowerCase2.length()) {
                if (sb == null) {
                    sb = new StringBuffer();
                }
                sb.append(s.substring(n, index));
                sb.append(s3);
            }
            if (sb != null) {
                sb.append(s.substring(n));
                return sb.toString();
            }
        }
        return s;
    }
    
    public static String b(final String s, final String s2, final String s3) {
        if (s2 != null && s3 != null && s != null && s2.length() > 0 && !s2.equals(s3)) {
            final StringBuffer sb = new StringBuffer();
            s3.toLowerCase();
            int n;
            int index;
            for (n = 0; (index = s.indexOf(s2, n)) >= 0; n = index + s2.length()) {
                sb.append(s.substring(n, index));
                sb.append(s3);
            }
            sb.append(s.substring(n));
            return sb.toString();
        }
        return s;
    }
    
    public static final StringBuffer a(final StringBuffer sb, final int n, int n2, final String s) {
        final int length = sb.length();
        if (n < 0) {
            throw new StringIndexOutOfBoundsException(n);
        }
        if (n2 > length) {
            n2 = length;
        }
        if (n > n2) {
            throw new StringIndexOutOfBoundsException();
        }
        final StringBuffer sb2 = new StringBuffer();
        if (n > 0) {
            sb2.append(a(sb.toString(), 0, n));
        }
        sb2.append(s);
        if (n2 < length) {
            sb2.append(sb.substring(n2));
        }
        sb.delete(0, sb.length());
        sb.append(sb2);
        return sb;
    }
    
    public static final String a(final String s, final int n, final int n2) {
        if (n < 0) {
            throw new StringIndexOutOfBoundsException(n);
        }
        if (n2 > s.length()) {
            throw new StringIndexOutOfBoundsException(n2);
        }
        if (n > n2) {
            throw new StringIndexOutOfBoundsException(n2 - n);
        }
        return new String(s.toCharArray(), n, n2 - n);
    }
}
