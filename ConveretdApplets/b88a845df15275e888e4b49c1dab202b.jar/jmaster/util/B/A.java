// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.B;

import java.util.StringTokenizer;
import java.awt.Dimension;
import java.io.IOException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.Date;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

public class A
{
    static final String C = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final SimpleDateFormat B;
    protected static A A;
    
    public static synchronized A A() {
        if (jmaster.util.B.A.A == null) {
            jmaster.util.B.A.A = new A();
        }
        return jmaster.util.B.A.A;
    }
    
    public static boolean C(final String s) {
        return s == null || s.trim().length() == 0;
    }
    
    public static String B(final String s, final String s2, final String s3) {
        if (s == null || s2 == null || s3 == null) {
            return s;
        }
        final StringBuffer sb = new StringBuffer("");
        int n;
        int index;
        for (n = 0; (index = s.indexOf(s2, n)) != -1; n = index + s2.length()) {
            sb.append(s.substring(n, index) + s3);
        }
        sb.append(s.substring(n));
        return sb.toString();
    }
    
    public static String A(String s, final int n, final char c) {
        if (s == null) {
            s = "";
        }
        if (s.length() >= n) {
            return s;
        }
        final StringBuffer sb = new StringBuffer(s);
        while (sb.length() < n) {
            sb.insert(0, c);
        }
        return sb.toString();
    }
    
    public static String A(final String s, final String s2, final String s3) throws UnsupportedEncodingException {
        return (s == null) ? null : new String((s2 == null) ? s.getBytes() : s.getBytes(s2), s3);
    }
    
    public static String D(final String s) {
        String string = null;
        if (s != null) {
            final int length = s.length();
            final StringBuffer sb = new StringBuffer(length);
            for (int i = 0; i < length; ++i) {
                final char char1 = s.charAt(i);
                switch (char1) {
                    case 34: {
                        sb.append("&#34;");
                        break;
                    }
                    case 60: {
                        sb.append("&#60;");
                        break;
                    }
                    case 62: {
                        sb.append("&#62;");
                        break;
                    }
                    case 38: {
                        sb.append("&#38;");
                        break;
                    }
                    default: {
                        sb.append(char1);
                        break;
                    }
                }
            }
            string = sb.toString();
        }
        return string;
    }
    
    public static final String A(final byte[] array) {
        final StringBuffer sb = new StringBuffer((array.length + 2) / 3 * 4);
        int n;
        for (n = 0; n + 2 < array.length; n += 3) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((array[n] & 0xFC) >> 2));
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((array[n] & 0x3) << 4 | (array[n + 1] & 0xF0) >> 4));
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((array[n + 1] & 0xF) << 2 | (array[n + 2] & 0xC0) >> 6));
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(array[n + 2] & 0x3F));
        }
        switch (array.length - n) {
            case 0: {
                break;
            }
            case 1: {
                sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((array[n] & 0xFC) >> 2));
                sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((array[n] & 0x3) << 4));
                sb.append("==");
                break;
            }
            case 2: {
                sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((array[n] & 0xFC) >> 2));
                sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((array[n] & 0x3) << 4 | (array[n + 1] & 0xF0) >> 4));
                sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt((array[n + 1] & 0xF) << 2));
                sb.append("=");
                break;
            }
            default: {
                throw new RuntimeException("Can't have other than 0, 1, or 2");
            }
        }
        return sb.toString();
    }
    
    public static final byte[] E(String trim) throws NumberFormatException {
        trim = trim.replace('=', ' ').trim();
        final byte[] array = new byte[trim.length() * 3 / 4];
        int n = 0;
        final char[] charArray = trim.toCharArray();
        int n2 = 0;
        do {
            final int index = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(charArray[n2]);
            if (index < 0) {
                throw new NumberFormatException("Invalid character " + charArray[n2] + " in base 64 string");
            }
            if (++n2 == charArray.length) {
                break;
            }
            final int index2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(charArray[n2]);
            if (index2 < 0) {
                throw new NumberFormatException("Invalid character " + charArray[n2] + " in base 64 string");
            }
            array[n++] = (byte)((index << 2) + (index2 >> 4));
            if (++n2 == charArray.length) {
                break;
            }
            final int index3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(charArray[n2]);
            if (index3 < 0) {
                throw new NumberFormatException("Invalid character " + charArray[n2] + " in base 64 string");
            }
            array[n++] = (byte)((index2 << 4 & 0xF0) + (index3 >> 2));
            if (++n2 == charArray.length) {
                break;
            }
            final int index4 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(charArray[n2]);
            if (index4 < 0) {
                throw new NumberFormatException("Invalid character " + charArray[n2] + " in base 64 string ");
            }
            array[n++] = (byte)((index3 << 6 & 0xC0) + index4);
        } while (++n2 != charArray.length);
        return array;
    }
    
    public static String C(final String s, final String s2, final String s3) throws UnsupportedEncodingException {
        final byte[] array = (s2 == null) ? s.getBytes() : s.getBytes(s2);
        return (s3 == null) ? new String(array) : new String(array, s3);
    }
    
    public static String A(final Object[] array, String s) {
        final StringBuffer sb = new StringBuffer();
        if (array != null) {
            if (s == null) {
                s = "";
            }
            for (int i = 0; i < array.length; ++i) {
                sb.append(((i > 0) ? s : "") + array[i]);
            }
        }
        return sb.toString();
    }
    
    public static String A(final Long n) {
        return (n == null) ? "" : ("" + n);
    }
    
    public static String B(final String s) {
        StringBuffer sb = null;
        if (s != null) {
            final int length = s.length();
            sb = new StringBuffer(length + 10);
            for (int i = 0; i < length; ++i) {
                final char char1 = s.charAt(i);
                if (Character.isDefined(char1)) {
                    if (char1 == '>') {
                        sb.append("&gt;");
                    }
                    else if (char1 == '<') {
                        sb.append("&lt;");
                    }
                    else if (char1 == '&') {
                        sb.append("&amp;");
                    }
                    else if (char1 == '\"') {
                        sb.append("&quot;");
                    }
                    else {
                        sb.append(char1);
                    }
                }
                else {
                    sb.append(char1);
                }
            }
        }
        return (s == null) ? "" : sb.toString();
    }
    
    public static String F(final String s) {
        StringBuffer sb = null;
        if (s != null) {
            final int length = s.length();
            sb = new StringBuffer(length + 10);
            for (int i = 0; i < length; ++i) {
                final char char1 = s.charAt(i);
                if (Character.isDefined(char1)) {
                    if (char1 == '<') {
                        sb.append("&lt;");
                    }
                    else if (char1 == '>') {
                        sb.append("&gt;");
                    }
                    else if (char1 == '&') {
                        sb.append("&amp;");
                    }
                    else if (char1 == '\n') {
                        sb.append("<br>");
                    }
                    else if (char1 == ' ') {
                        sb.append("&nbsp;");
                    }
                    else {
                        sb.append(char1);
                    }
                }
                else {
                    sb.append("?");
                }
            }
        }
        return (s == null) ? "" : sb.toString();
    }
    
    public static int C(final String s, final String s2) {
        int compareTo;
        if (s == null && s2 == null) {
            compareTo = 0;
        }
        else if (s == null) {
            compareTo = Integer.MIN_VALUE;
        }
        else if (s2 == null) {
            compareTo = Integer.MAX_VALUE;
        }
        else {
            compareTo = s.compareTo(s2);
        }
        return compareTo;
    }
    
    public static int A(final String s, final String s2) {
        int compareToIgnoreCase;
        if (s == null && s2 == null) {
            compareToIgnoreCase = 0;
        }
        else if (s == null) {
            compareToIgnoreCase = Integer.MIN_VALUE;
        }
        else if (s2 == null) {
            compareToIgnoreCase = Integer.MAX_VALUE;
        }
        else {
            compareToIgnoreCase = s.compareToIgnoreCase(s2);
        }
        return compareToIgnoreCase;
    }
    
    public static String A(final Date date) {
        synchronized (jmaster.util.B.A.B) {
            return jmaster.util.B.A.B.format(date);
        }
    }
    
    public static Date G(final String s) throws ParseException {
        synchronized (jmaster.util.B.A.B) {
            return jmaster.util.B.A.B.parse(s);
        }
    }
    
    public static String A(final Throwable t) throws IOException {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        t.printStackTrace(printWriter);
        printWriter.close();
        stringWriter.close();
        return stringWriter.getBuffer().toString();
    }
    
    public static Dimension A(final String s) {
        final Dimension dimension = new Dimension();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "x");
        dimension.width = Integer.parseInt(stringTokenizer.nextToken());
        dimension.height = Integer.parseInt(stringTokenizer.nextToken());
        return dimension;
    }
    
    public static String B(final byte[] array) {
        final StringBuffer sb = new StringBuffer();
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                final String hexString = Integer.toHexString(array[i] & 0xFF);
                if (hexString.length() < 2) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
        }
        return sb.toString();
    }
    
    public static String B(final String s, final String s2) {
        return (s == null) ? s2 : s;
    }
    
    static {
        B = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss Z");
        jmaster.util.B.A.A = null;
    }
}
