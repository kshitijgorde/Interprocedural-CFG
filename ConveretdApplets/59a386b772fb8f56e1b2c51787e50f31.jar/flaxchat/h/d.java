// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.h;

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
}
