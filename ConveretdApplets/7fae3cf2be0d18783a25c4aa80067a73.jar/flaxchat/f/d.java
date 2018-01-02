// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.f;

public class d
{
    public static String a(final String s) {
        final boolean y = e.y;
        final int length = s.length();
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        while (true) {
            Label_0201: {
                if (!y) {
                    break Label_0201;
                }
                char c = s.charAt(n);
                if (c == '\u0003') {
                    if (++n >= length || !Character.isDigit(s.charAt(n))) {
                        break Label_0201;
                    }
                    if (++n < length && Character.isDigit(s.charAt(n))) {
                        ++n;
                    }
                    if (n >= length) {
                        break Label_0201;
                    }
                    c = s.charAt(n);
                    if (c != ',') {
                        break Label_0201;
                    }
                    if (++n < length) {
                        c = s.charAt(n);
                        if (Character.isDigit(c)) {
                            if (++n >= length) {
                                break Label_0201;
                            }
                            c = s.charAt(n);
                            if (!Character.isDigit(c)) {
                                break Label_0201;
                            }
                            ++n;
                            if (!y) {
                                break Label_0201;
                            }
                        }
                        --n;
                        if (!y) {
                            break Label_0201;
                        }
                    }
                    --n;
                    if (!y) {
                        break Label_0201;
                    }
                }
                if (c == '\u000f') {
                    ++n;
                    if (!y) {
                        break Label_0201;
                    }
                }
                sb.append(c);
                ++n;
            }
            if (n >= length) {
                return sb.toString();
            }
            continue;
        }
    }
    
    public static String b(final String s) {
        final boolean y = e.y;
        final int length = s.length();
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        while (true) {
            Label_0069: {
                if (!y) {
                    break Label_0069;
                }
                final char char1 = s.charAt(n);
                if (char1 != '\u000f' && char1 != '\u0002' && char1 != '\u001f' && char1 != '\u0016') {
                    sb.append(char1);
                }
                ++n;
            }
            if (n >= length) {
                return sb.toString();
            }
            continue;
        }
    }
    
    public static String c(final String s) {
        return b(a(s));
    }
    
    public static String d(final String s) {
        final int e = e(s);
        if (e < 0) {
            return "";
        }
        return s.substring(0, e);
    }
    
    public static int e(final String s) {
        final boolean y = e.y;
        final int length = s.length();
        if (length == 0) {
            return -1;
        }
        int n = 0;
        if (s.charAt(n) != '\u0003') {
            return -2;
        }
        if (n >= length - 1) {
            return -3;
        }
        ++n;
        Label_0112: {
            if (a(s.charAt(n) - '0')) {
                if (length - n < 2) {
                    return n + 1;
                }
                if (a(s.charAt(n + 1) - '0')) {
                    if (length - n <= 2) {
                        return n + 2;
                    }
                    n += 2;
                    if (!y) {
                        break Label_0112;
                    }
                }
                ++n;
            }
        }
        if (s.charAt(n) == ',' && n < length - 1) {
            ++n;
            if (a(s.charAt(n) - '0')) {
                if (length - n < 2) {
                    return n + 1;
                }
                if (a(s.charAt(n + 1) - '0')) {
                    if (length - n < 2) {
                        return n + 1;
                    }
                    n += 2;
                    if (!y) {
                        return n;
                    }
                }
                ++n;
                if (!y) {
                    return n;
                }
            }
            --n;
        }
        return n;
    }
    
    private static boolean a(final int n) {
        return n > -1 && n < 10;
    }
}
