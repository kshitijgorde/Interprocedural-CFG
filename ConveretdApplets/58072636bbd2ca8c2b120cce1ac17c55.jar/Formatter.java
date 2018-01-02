// 
// Decompiled by Procyon v0.5.30
// 

public class Formatter
{
    public static final String START_END_TAG = "&";
    
    public static String trim(String string) {
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) == ' ' || string.charAt(i) == '\n' || string.charAt(i) == '\r') {
                string = string.substring(0, i) + string.substring(i + 1, string.length());
                --i;
            }
        }
        return string;
    }
    
    public static String replaceAll(final String s, final String s2, final String s3) {
        if (s.equals("") || s.indexOf(s2) == -1) {
            return s;
        }
        final int index = s.indexOf(s2);
        return (s.substring(0, index) + s3).concat(replaceAll(s.substring(index + s2.length(), s.length()), s2, s3));
    }
    
    public static String replaceFunctionBrackets(final String s, final String s2) {
        if (s.equals("") || s.indexOf(s2) == -1) {
            return s;
        }
        final int n = s.indexOf(s2) + s2.length();
        int n2 = 0;
        if (s.charAt(n) == '(') {
            int n3 = 1;
            for (int i = n + 1; i < s.length(); ++i) {
                if (s.charAt(i) == '(') {
                    ++n3;
                }
                else if (s.charAt(i) == ')') {
                    --n3;
                }
                if (n3 == 0) {
                    n2 = i;
                    break;
                }
            }
        }
        return (s.substring(0, n) + "{" + replaceFunctionBrackets(s.substring(n + 1, n2), s2) + "}").concat(replaceFunctionBrackets(s.substring(n2 + 1, s.length()), s2));
    }
}
