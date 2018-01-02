// 
// Decompiled by Procyon v0.5.30
// 

package mechanalyst;

public class EString
{
    static final String num = "0123456789";
    
    public static int amatch(final String s, final String s2) {
        int n = 0;
        for (int n2 = 0, n3 = 0; n2 < s.length() && n3 < s2.length(); ++n2, ++n3, ++n) {
            final char char1 = s2.charAt(n3);
            if (char1 == '*' || char1 == '#') {
                return n;
            }
            if (s.charAt(n2) != char1) {
                return -1;
            }
        }
        return n;
    }
    
    public static int findPat(final String s, final String s2) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (amatch(s.substring(i), s2) >= 0) {
                return n;
            }
            ++n;
        }
        return -1;
    }
    
    public static int findNum(final String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if ("0123456789".indexOf(s.charAt(i)) == -1) {
                return n;
            }
            ++n;
        }
        return n;
    }
    
    static boolean matchA(final String s, final String s2, final String[] array) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        while (n3 < s2.length() && n2 < array.length) {
            final char char1 = s2.charAt(n3);
            if (char1 == '*') {
                int pat;
                if (n3 + 1 == s2.length()) {
                    pat = s.length() - n;
                }
                else {
                    pat = findPat(s.substring(n), s2.substring(n3 + 1));
                }
                if (pat < 0) {
                    return false;
                }
                array[n2++] = s.substring(n, n + pat);
                n += pat;
                ++n3;
            }
            else if (char1 == '#') {
                final int num = findNum(s.substring(n));
                array[n2++] = s.substring(n, n + num);
                n += num;
                ++n3;
            }
            else {
                final int amatch = amatch(s.substring(n), s2.substring(n3));
                if (amatch <= 0) {
                    return false;
                }
                n += amatch;
                n3 += amatch;
            }
        }
        return n >= s.length() && n3 >= s2.length();
    }
    
    static boolean matchB(final String s, final String s2, final String[] array) {
        String s3 = new String(s);
        String s4 = new String(s2);
        int n = 0;
        while (s4.length() > 0 && s3.length() >= 0 && n < array.length) {
            final char char1 = s4.charAt(0);
            if (char1 == '*') {
                int n2;
                if (s4.length() == 1) {
                    n2 = s3.length();
                }
                else {
                    n2 = findPat(s3, s4.substring(1));
                }
                if (n2 < 0) {
                    return false;
                }
                array[n++] = s3.substring(0, n2);
                s3 = s3.substring(n2);
                s4 = s4.substring(1);
            }
            else if (char1 == '#') {
                final int num = findNum(s3);
                array[n++] = s3.substring(0, num);
                s3 = s3.substring(num);
                s4 = s4.substring(1);
            }
            else {
                final int amatch = amatch(s3, s4);
                if (amatch <= 0) {
                    return false;
                }
                s3 = s3.substring(amatch);
                s4 = s4.substring(amatch);
            }
        }
        return s3.length() == 0 && s4.length() == 0;
    }
    
    public static boolean match(final String s, final String s2, final String[] array) {
        return matchA(s, s2, array);
    }
    
    public static String translate(String replace, final String s, final String s2) {
        s.length();
        s2.length();
        for (int i = 0; i < s.length(); ++i) {
            replace = replace.replace(s.charAt(i), s2.charAt(i));
        }
        return replace;
    }
    
    public static String compress(final String s) {
        String s2 = "";
        if (s.length() == 0) {
            return s;
        }
        char c = s.charAt(0);
        for (int i = 1; i < s.length(); ++i) {
            if (c != ' ' || (s.charAt(i) != ' ' && s.charAt(i) != ',' && s.charAt(i) != '.')) {
                if (c != ' ' && s.charAt(i) == '?') {
                    s2 = String.valueOf(s2) + c + " ";
                }
                else {
                    s2 = String.valueOf(s2) + c;
                }
            }
            c = s.charAt(i);
        }
        return String.valueOf(s2) + c;
    }
    
    public static String trim(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != ' ') {
                return s.substring(i);
            }
        }
        return "";
    }
    
    public static String pad(final String s) {
        if (s.length() == 0) {
            return " ";
        }
        final char char1 = s.charAt(0);
        final char char2 = s.charAt(s.length() - 1);
        if (char1 == ' ' && char2 == ' ') {
            return s;
        }
        if (char1 == ' ' && char2 != ' ') {
            return String.valueOf(s) + " ";
        }
        if (char1 != ' ' && char2 == ' ') {
            return " " + s;
        }
        if (char1 != ' ' && char2 != ' ') {
            return " " + s + " ";
        }
        return s;
    }
    
    public static int count(final String s, final char c) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == c) {
                ++n;
            }
        }
        return n;
    }
}
