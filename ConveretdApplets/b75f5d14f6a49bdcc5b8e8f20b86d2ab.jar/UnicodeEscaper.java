// 
// Decompiled by Procyon v0.5.30
// 

public final class UnicodeEscaper
{
    private static String[] HTML_ESCAPES;
    private static String[] JAVA_ESCAPES;
    private static String[] padding;
    
    public static String escapeForHtml(final String s) {
        return escape(s, "&#", ";", UnicodeEscaper.HTML_ESCAPES);
    }
    
    public static String escapeForJava(final String s) {
        return escape(s, "\\u", "", UnicodeEscaper.JAVA_ESCAPES);
    }
    
    private static String escape(final String s, final String s2, final String s3, final String[] array) {
        final int length = s.length();
        final int length2 = array.length;
        final StringBuffer sb = new StringBuffer((s2.length() + s3.length()) * length);
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (('a' <= char1 && char1 <= 'z') || ('A' <= char1 && char1 <= 'Z') || ('0' <= char1 && char1 <= '9')) {
                sb.append(char1);
            }
            else if (char1 < length2 && array[char1] != null) {
                sb.append(array[char1]);
            }
            else {
                sb.append(s2);
                sb.append(hexForChar(char1));
                sb.append(s3);
            }
        }
        return sb.toString();
    }
    
    private static String hexForChar(final char c) {
        final String hexString = Integer.toHexString(c);
        if (hexString.length() < 4) {
            return UnicodeEscaper.padding[hexString.length()] + hexString;
        }
        return hexString;
    }
    
    static {
        (UnicodeEscaper.HTML_ESCAPES = new String[127])[38] = "&amp;";
        UnicodeEscaper.HTML_ESCAPES[60] = "&lt;";
        UnicodeEscaper.HTML_ESCAPES[62] = "&gt;";
        (UnicodeEscaper.JAVA_ESCAPES = new String[127])[13] = "\\r";
        UnicodeEscaper.JAVA_ESCAPES[10] = "\\n";
        UnicodeEscaper.JAVA_ESCAPES[32] = " ";
        UnicodeEscaper.JAVA_ESCAPES[9] = "\\t";
        UnicodeEscaper.JAVA_ESCAPES[92] = "\\\\";
        UnicodeEscaper.JAVA_ESCAPES[39] = "\\'";
        UnicodeEscaper.JAVA_ESCAPES[34] = "\\\"";
        UnicodeEscaper.JAVA_ESCAPES[47] = "/";
        UnicodeEscaper.JAVA_ESCAPES[62] = ">";
        UnicodeEscaper.JAVA_ESCAPES[60] = "<";
        UnicodeEscaper.padding = new String[] { "0000", "000", "00", "0" };
    }
}
