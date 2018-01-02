// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.internet;

import javax.servlet.http.Cookie;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.Servlet;
import java.util.StringTokenizer;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.BitSet;

public class InternetUtility
{
    public static final String CGI_ESCAPE_SIN_QUOTE = "&#39;";
    public static final String CGI_ESCAPE_DBL_QUOTE = "&#34;";
    public static final String CGI_ESCAPE_BACKSLASH = "&#92;";
    public static final String CGI_ESCAPE_AMP = "&amp;";
    public static final String CGI_ESCAPE_LESS_THAN = "&lt;";
    public static final String CGI_ESCAPE_GREATER_THAN = "&gt;";
    private static final char[] CGI_ESCAPE_SIN_QUOTE_C;
    private static final char[] CGI_ESCAPE_DBL_QUOTE_C;
    private static final char[] CGI_ESCAPE_BACKSLASH_C;
    private static final char[] CGI_ESCAPE_AMP_C;
    private static final char[] CGI_ESCAPE_LESS_THAN_C;
    private static final char[] CGI_ESCAPE_GREATER_THAN_C;
    private static int maxLenOfEscapeSequences;
    public static final String WindowsOS = "WINDOWS";
    public static final String AixOS = "AIX";
    public static final String programSubPath1 = "WEB-INF";
    public static final String programSubPath2 = "lib";
    public static final boolean BY_BYTE = true;
    public static final boolean BY_CHARACTER = false;
    private static BitSet dontNeedEncoding;
    static char[] digits;
    
    public static String HandleSpecialCharacters(final String s) {
        return ReplaceCharWithString(convertCarriageReturns(ReplaceCharWithString(ReplaceCharWithString(ReplaceCharWithString(s, '\\', "\\\\"), '\'', "\\'"), '%', "%%")), '\"', "\"+'\"'+\"");
    }
    
    public static String convertCarriageReturns(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            if (String.valueOf(EscapeChar(s.charAt(i))).equalsIgnoreCase("%0D")) {
                sb.append(" ");
            }
            else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    
    public static String ReplaceCharWithString(final String s, final char c, final String s2) {
        if (s == null) {
            return "";
        }
        if (s.indexOf(c) != -1) {
            final StringBuffer sb = new StringBuffer("");
            for (int length = s.length(), i = 0; i < length; ++i) {
                if (s.charAt(i) == c) {
                    sb.append(s2);
                }
                else {
                    sb.append(s.charAt(i));
                }
            }
            return sb.toString();
        }
        return s;
    }
    
    public static String ReplaceStringWithChar(String s, final String s2, final char c) {
        String string = s;
        if (s != null && s.length() > 0) {
            final int length = s2.length();
            for (int i = s.indexOf(s2); i != -1; i = s.indexOf(s2)) {
                final StringBuffer sb = new StringBuffer(string.substring(0, i));
                sb.append(c);
                sb.append(string.substring(i + length));
                s = (string = sb.toString());
            }
        }
        return s;
    }
    
    public static String JavaEncode(final String s) {
        if (s == null) {
            return "";
        }
        final int length = s.length();
        int n = 0;
        final char[] array = new char[length * 2];
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                case 92: {
                    array[n++] = '\\';
                    array[n++] = '\\';
                    break;
                }
                case 39: {
                    array[n++] = '\\';
                    array[n++] = '\'';
                    break;
                }
                case 34: {
                    array[n++] = '\\';
                    array[n++] = '\"';
                    break;
                }
                default: {
                    array[n++] = char1;
                    break;
                }
            }
        }
        return new String(array, 0, n);
    }
    
    public static String JSEncode(final String s) {
        if (s == null) {
            return "";
        }
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(length * 2);
        for (int i = 0; i < length; ++i) {
            final int index;
            if ((index = "\f\b\n\r\t'\"\\".indexOf(s.charAt(i))) != -1) {
                sb.append('\\').append("fbnrt'\"\\".charAt(index));
            }
            else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    
    public static String HTMLEncode(final String s) {
        if (s == null) {
            return "";
        }
        final int length = s.length();
        final char[] charArray = s.toCharArray();
        int n = 0;
        char[] array = new char[Math.max(length * 2, InternetUtility.maxLenOfEscapeSequences)];
        for (int i = 0; i < length; ++i) {
            if (array.length < n + InternetUtility.maxLenOfEscapeSequences) {
                final char[] array2 = array;
                array = new char[array2.length * 2];
                for (int j = 0; j < array2.length; ++j) {
                    array[j] = array2[j];
                }
            }
            switch (charArray[i]) {
                case '&': {
                    for (int k = 0; k < InternetUtility.CGI_ESCAPE_AMP_C.length; ++k) {
                        array[n++] = InternetUtility.CGI_ESCAPE_AMP_C[k];
                    }
                    break;
                }
                case '\'': {
                    for (int l = 0; l < InternetUtility.CGI_ESCAPE_SIN_QUOTE_C.length; ++l) {
                        array[n++] = InternetUtility.CGI_ESCAPE_SIN_QUOTE_C[l];
                    }
                    break;
                }
                case '\"': {
                    for (int n2 = 0; n2 < InternetUtility.CGI_ESCAPE_DBL_QUOTE_C.length; ++n2) {
                        array[n++] = InternetUtility.CGI_ESCAPE_DBL_QUOTE_C[n2];
                    }
                    break;
                }
                case '\\': {
                    for (int n3 = 0; n3 < InternetUtility.CGI_ESCAPE_BACKSLASH_C.length; ++n3) {
                        array[n++] = InternetUtility.CGI_ESCAPE_BACKSLASH_C[n3];
                    }
                    break;
                }
                case '<': {
                    for (int n4 = 0; n4 < InternetUtility.CGI_ESCAPE_LESS_THAN_C.length; ++n4) {
                        array[n++] = InternetUtility.CGI_ESCAPE_LESS_THAN_C[n4];
                    }
                    break;
                }
                case '>': {
                    for (int n5 = 0; n5 < InternetUtility.CGI_ESCAPE_GREATER_THAN_C.length; ++n5) {
                        array[n++] = InternetUtility.CGI_ESCAPE_GREATER_THAN_C[n5];
                    }
                    break;
                }
                default: {
                    array[n++] = charArray[i];
                    break;
                }
            }
        }
        return new String(array, 0, n);
    }
    
    public static String EscapeString(final String s) {
        return HTMLEncode(s);
    }
    
    public static String UnEscapeString(final String s) {
        String replaceStringWithChar = "";
        if (s != null && s.length() > 0) {
            replaceStringWithChar = ReplaceStringWithChar(ReplaceStringWithChar(ReplaceStringWithChar(ReplaceStringWithChar(s, "&#39;", '\''), "&#34;", '\"'), "&#92;", '\\'), "&amp;", '&');
        }
        return replaceStringWithChar;
    }
    
    public static String escapeJavaString(final String s) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            if (Character.isIdentifierIgnorable(s.charAt(i))) {
                sb.append("\\u");
                sb.append(ch2hex(s.charAt(i)));
            }
            else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    
    public static boolean isSpecial(final char c) {
        return !InternetUtility.dontNeedEncoding.get(c);
    }
    
    public static String CGIUnEscapeString(final String s) {
        if (s == null) {
            return null;
        }
        final byte[] array = new byte[s.length()];
        int i = 0;
        int n = 0;
        while (i < s.length()) {
            int char1 = s.charAt(i);
            ++i;
            if (char1 == 43) {
                char1 = 32;
            }
            else if (char1 == 37) {
                if (s.charAt(i) == 'u') {
                    array[n] = (byte)(int)Integer.decode("0x" + s.substring(i + 1, i + 3));
                    i += 3;
                    ++n;
                }
                char1 = (char)(int)Integer.decode("0x" + s.substring(i, i + 2));
                i += 2;
            }
            array[n] = (byte)char1;
            ++n;
        }
        try {
            return new String(array, 0, n, "UTF8");
        }
        catch (UnsupportedEncodingException ex) {
            System.out.println(ex);
            array[n] = 0;
            return new String(array);
        }
    }
    
    public static String StripNewLines(final String s) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(length);
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 != '\r' && char1 != '\n') {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    public static String CGIEscapeString(final String s) {
        if (s == null) {
            return null;
        }
        byte[] array;
        try {
            array = s.getBytes("UTF8");
        }
        catch (UnsupportedEncodingException ex) {
            array = s.getBytes();
            System.out.println(ex);
        }
        final char[] array2 = new char[array.length * 3];
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            final char c = (char)array[i];
            if (isSpecial(c)) {
                final char[] escapeChar = EscapeChar((char)array[i]);
                for (int j = 0; j < escapeChar.length; ++j) {
                    array2[n++] = escapeChar[j];
                }
            }
            else if (c == ' ') {
                array2[n++] = '+';
            }
            else {
                array2[n++] = c;
            }
        }
        return new String(array2, 0, n);
    }
    
    private static char[] EscapeChar(final char c) {
        return new char[] { '%', InternetUtility.digits[c >> 4 & '\u000f'], InternetUtility.digits[c & '\u000f'] };
    }
    
    private static char[] ch2hex(final char c) {
        return new char[] { InternetUtility.digits[c >>> 12 & '\u000f'], InternetUtility.digits[c >>> 8 & '\u000f'], InternetUtility.digits[c >>> 4 & '\u000f'], InternetUtility.digits[c & '\u000f'] };
    }
    
    private static char hex2ch(final String s) {
        char c = '\0';
        for (int i = 0; i < 2; ++i) {
            final char c2 = (char)(c << 4);
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                c = (char)(c2 + (char)(s.charAt(i) - '0'));
            }
            else if ('a' <= s.charAt(i) && s.charAt(i) <= 'f') {
                c = (char)(c2 + (char)(s.charAt(i) - 'a' + '\n'));
            }
            else {
                if ('A' > s.charAt(i) || s.charAt(i) > 'F') {
                    return ' ';
                }
                c = (char)(c2 + (char)(s.charAt(i) - 'A' + '\n'));
            }
        }
        return c;
    }
    
    public static String GetInstallPath(final String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = 0;
        for (int i = s.length() - 1; i >= 0; --i) {
            final char char1 = s.charAt(i);
            if (char1 == '/' || char1 == '\\') {
                ++n;
            }
            if (n == 2) {
                return s.substring(0, i) + File.separator;
            }
        }
        return "";
    }
    
    public static String GetInstallRootDirectory(String replace) {
        if (replace == null || replace.length() == 0) {
            return "";
        }
        replace = replace.replace('\\', '/');
        final String[] tokenizePath = tokenizePath(replace, "/");
        if (tokenizePath == null || tokenizePath.length <= 0) {
            return "";
        }
        if (tokenizePath[tokenizePath.length - 2].equals("servlet")) {
            return tokenizePath[tokenizePath.length - 3];
        }
        return tokenizePath[tokenizePath.length - 2];
    }
    
    public static String[] tokenizePath(final String s, final String s2) {
        if (s == null) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final String[] array = new String[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            array[n] = stringTokenizer.nextToken();
            ++n;
        }
        return array;
    }
    
    public static String removeWhiteSpace(final String s) {
        final StringBuffer sb = new StringBuffer("");
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 != ' ') {
                sb.append(String.valueOf(char1));
            }
        }
        return sb.toString();
    }
    
    public static boolean isWindowsServerOS() {
        final String property = System.getProperty("os.name");
        return property != null && property.length() != 0 && new String(property).toUpperCase().indexOf("WINDOWS") != -1;
    }
    
    public static boolean isServerOS(final String s) {
        if (s == null) {
            return false;
        }
        final String property = System.getProperty("os.name");
        return property != null && property.length() != 0 && new String(property).toUpperCase().indexOf(s) != -1;
    }
    
    public static String ConstructProgramPath(String string) {
        if (string == null || string.length() == 0) {
            return "";
        }
        if (string.charAt(string.length() - 1) != File.separatorChar) {
            string += File.separator;
        }
        return string + "WEB-INF" + File.separator + "lib" + File.separator;
    }
    
    public static String ConstructWebInfPath(String string) {
        if (string == null || string.length() == 0) {
            return "";
        }
        if (string.charAt(string.length() - 1) != File.separatorChar) {
            string += File.separator;
        }
        return string + "WEB-INF" + File.separator;
    }
    
    public static String getProtocolFromRequestURL(final String s) {
        if (s == null) {
            return "";
        }
        if (s.length() == 0) {
            return "";
        }
        final int index = s.indexOf("://");
        if (index != -1) {
            return s.substring(0, index + 3);
        }
        return "";
    }
    
    public static String getHostNameFromRequestURL(String s) {
        if (s == null) {
            return "";
        }
        if (s.length() == 0) {
            return "";
        }
        final int index = s.indexOf("://");
        if (index != -1) {
            s = s.substring(index + 3);
            final int index2 = s.indexOf("/");
            if (index2 != -1) {
                s = s.substring(0, index2);
            }
            return s;
        }
        return "";
    }
    
    public static String appendDirectory(final String s, final String s2) {
        return (s.charAt(s.length() - 1) != File.separatorChar) ? (s + File.separator + s2) : (s + s2);
    }
    
    public static String getRealPath(final Servlet servlet, final HttpServletRequest httpServletRequest) {
        return getRealPath(servlet.getServletConfig().getServletContext());
    }
    
    public static String getRealPath(final ServletContext servletContext) {
        final String realPath = servletContext.getRealPath("/");
        if (realPath == null || realPath.length() == 0) {
            return null;
        }
        String s = normalizePathSlashes(realPath);
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) != File.separatorChar) {
                s = s.substring(0, i + 1);
                break;
            }
        }
        if (s != null && s.length() != 0 && s.charAt(s.length() - 1) != File.separatorChar) {
            s += File.separator;
        }
        return s;
    }
    
    public static String normalizePathSlashes(final String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return new String(s).replace('/', '\\').replace('\\', File.separatorChar);
    }
    
    public static String encodeURL(final String s, final String s2, final String s3) {
        if (s == null || s2 == null || s3 == null) {
            return s;
        }
        String s4 = s;
        String substring = "";
        String substring2 = "";
        final int index = s.indexOf(63);
        if (index >= 0) {
            substring = s4.substring(index);
            s4 = s4.substring(0, index);
        }
        final int index2 = s4.indexOf(35);
        if (index2 >= 0) {
            substring2 = s4.substring(index2);
            s4 = s4.substring(0, index2);
        }
        final StringBuffer sb = new StringBuffer(s4);
        if (sb.length() > 0) {
            sb.append(';');
            sb.append(s2);
            sb.append('=');
            sb.append(s3);
        }
        sb.append(substring2);
        sb.append(substring);
        return sb.toString();
    }
    
    public static Vector LoadVectorFromList(String s, final String s2) {
        final Vector<String> vector = new Vector<String>();
        if (s == null) {
            s = "";
        }
        int n = 0;
        for (int i = s.indexOf(s2); i != -1; i = s.indexOf(s2, n)) {
            vector.addElement(s.substring(n, i));
            n = i + 1;
        }
        vector.addElement(s.substring(n));
        vector.trimToSize();
        return vector;
    }
    
    public static String LoadListFromVector(final Vector vector, final String s) {
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < vector.size(); ++i) {
            if (vector.elementAt(i) == null) {
                vector.setElementAt("", i);
            }
            sb = sb.append(vector.elementAt(i).toString());
            if (i + 1 < vector.size()) {
                sb = sb.append(s);
            }
        }
        return sb.toString();
    }
    
    public static String GetCookieValue(final HttpServletRequest httpServletRequest, final String s) {
        final Cookie[] cookies = httpServletRequest.getCookies();
        final StringBuffer sb = new StringBuffer("");
        if (cookies != null) {
            for (int i = 0; i < cookies.length; ++i) {
                if (cookies[i].getName().equals(s)) {
                    sb.append(cookies[i].getValue());
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    public static String padWithTrailingSpaces(final String s, final int n, final boolean b) {
        if (s.length() >= n) {
            return s;
        }
        final StringBuffer sb = new StringBuffer(s);
        for (int i = s.length(); i < n; ++i) {
            sb.append(" ");
        }
        if (!b) {
            return sb.toString();
        }
        final byte[] bytes = sb.toString().getBytes();
        if (bytes.length > n) {
            final byte[] array = new byte[n];
            System.arraycopy(bytes, 0, array, 0, n);
            return new String(array);
        }
        return sb.toString();
    }
    
    static {
        CGI_ESCAPE_SIN_QUOTE_C = "&#39;".toCharArray();
        CGI_ESCAPE_DBL_QUOTE_C = "&#34;".toCharArray();
        CGI_ESCAPE_BACKSLASH_C = "&#92;".toCharArray();
        CGI_ESCAPE_AMP_C = "&amp;".toCharArray();
        CGI_ESCAPE_LESS_THAN_C = "&lt;".toCharArray();
        CGI_ESCAPE_GREATER_THAN_C = "&gt;".toCharArray();
        InternetUtility.maxLenOfEscapeSequences = 5;
        InternetUtility.dontNeedEncoding = null;
        synchronized (InternetUtility.dontNeedEncoding = new BitSet(256)) {
            for (int i = 97; i <= 122; ++i) {
                InternetUtility.dontNeedEncoding.set(i);
            }
            for (int j = 65; j <= 90; ++j) {
                InternetUtility.dontNeedEncoding.set(j);
            }
            for (int k = 48; k <= 57; ++k) {
                InternetUtility.dontNeedEncoding.set(k);
            }
            InternetUtility.dontNeedEncoding.set(32);
            InternetUtility.dontNeedEncoding.set(45);
            InternetUtility.dontNeedEncoding.set(95);
            InternetUtility.dontNeedEncoding.set(46);
            InternetUtility.dontNeedEncoding.set(42);
        }
        InternetUtility.digits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
}
