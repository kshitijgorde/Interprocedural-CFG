// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URI;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public final class Strings
{
    public static final String EMPTY = "";
    private static final long MSEC = 1L;
    private static final long SECS = 1000L;
    private static final long MINS = 60000L;
    private static final long HOUR = 3600000L;
    private static final String[] keywords;
    private static final String[] ejbQlIdentifiers;
    
    public static String subst(final StringBuffer buff, final String from, final String to, final String string) {
        int begin = 0;
        for (int end = 0; (end = string.indexOf(from, end)) != -1; begin = (end += from.length())) {
            buff.append(string.substring(begin, end));
            buff.append(to);
        }
        buff.append(string.substring(begin, string.length()));
        return buff.toString();
    }
    
    public static String subst(final String from, final String to, final String string) {
        return subst(new StringBuffer(), from, to, string);
    }
    
    public static String subst(final StringBuffer buff, final String string, final Map map, final String beginToken, final String endToken) {
        int begin = 0;
        Range range;
        for (int rangeEnd = 0; (range = rangeOf(beginToken, endToken, string, rangeEnd)) != null; begin = (rangeEnd = range.end + endToken.length())) {
            buff.append(string.substring(begin, range.begin));
            final String key = string.substring(range.begin + beginToken.length(), range.end);
            Object value = map.get(key);
            if (value == null) {
                value = "";
            }
            buff.append(value);
        }
        buff.append(string.substring(begin, string.length()));
        return buff.toString();
    }
    
    public static String subst(final String string, final Map map, final String beginToken, final String endToken) {
        return subst(new StringBuffer(), string, map, beginToken, endToken);
    }
    
    public static String subst(final StringBuffer buff, final String string, final String[] replace, final char token) {
        for (int i = string.length(), j = 0; j >= 0 && j < i; ++j) {
            final char c = string.charAt(j);
            if (c == token) {
                if (j != i) {
                    final int k = Character.digit(string.charAt(j + 1), 10);
                    if (k == -1) {
                        buff.append(string.charAt(j + 1));
                    }
                    else if (k < replace.length) {
                        buff.append(replace[k]);
                    }
                    ++j;
                }
            }
            else {
                buff.append(c);
            }
        }
        return buff.toString();
    }
    
    public static String subst(final String string, final String[] replace, final char token) {
        return subst(new StringBuffer(), string, replace, token);
    }
    
    public static String subst(final String string, final String[] replace) {
        return subst(new StringBuffer(), string, replace, '%');
    }
    
    public static Range rangeOf(final String beginToken, final String endToken, final String string, final int fromIndex) {
        final int begin = string.indexOf(beginToken, fromIndex);
        if (begin != -1) {
            final int end = string.indexOf(endToken, begin + 1);
            if (end != -1) {
                return new Range(begin, end);
            }
        }
        return null;
    }
    
    public static Range rangeOf(final String beginToken, final String endToken, final String string) {
        return rangeOf(beginToken, endToken, string, 0);
    }
    
    public static String[] split(final String string, final String delim, final int limit) {
        int count = count(string, delim) + 1;
        if (limit > 0 && count > limit) {
            count = limit;
        }
        final String[] strings = new String[count];
        int begin = 0;
        for (int i = 0; i < count; ++i) {
            int end = string.indexOf(delim, begin);
            if (end == -1 || i + 1 == count) {
                end = string.length();
            }
            if (end == 0) {
                strings[i] = "";
            }
            else {
                strings[i] = string.substring(begin, end);
            }
            begin = end + 1;
        }
        return strings;
    }
    
    public static String[] split(final String string, final String delim) {
        return split(string, delim, -1);
    }
    
    public static String join(final StringBuffer buff, final Object[] array, final String delim) {
        final boolean haveDelim = delim != null;
        for (int i = 0; i < array.length; ++i) {
            buff.append(array[i]);
            if (haveDelim && i + 1 < array.length) {
                buff.append(delim);
            }
        }
        return buff.toString();
    }
    
    public static String join(final Object[] array, final String delim) {
        return join(new StringBuffer(), array, delim);
    }
    
    public static String join(final Object[] array) {
        return join(array, null);
    }
    
    public static String join(final byte[] array) {
        final Byte[] bytes = new Byte[array.length];
        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] = new Byte(array[i]);
        }
        return join(bytes, null);
    }
    
    public static String join(final StringBuffer buff, final Object[] array, final String prefix, final String separator, final String suffix) {
        buff.append(prefix);
        join(buff, array, separator);
        buff.append(suffix);
        return buff.toString();
    }
    
    public static String join(final Object[] array, final String prefix, final String separator, final String suffix) {
        return join(new StringBuffer(), array, prefix, separator, suffix);
    }
    
    public static int count(final String string, final String substring) {
        int count = 0;
        for (int idx = 0; (idx = string.indexOf(substring, idx)) != -1; ++idx, ++count) {}
        return count;
    }
    
    public static int count(final String string, final char c) {
        return count(string, String.valueOf(c));
    }
    
    public static String pad(final StringBuffer buff, final String string, final int count) {
        for (int i = 0; i < count; ++i) {
            buff.append(string);
        }
        return buff.toString();
    }
    
    public static String pad(final String string, final int count) {
        return pad(new StringBuffer(), string, count);
    }
    
    public static String pad(final Object obj, final int count) {
        return pad(new StringBuffer(), String.valueOf(obj), count);
    }
    
    public static boolean compare(final String me, final String you) {
        return me == you || ((me != null || you == null) && me.equals(you));
    }
    
    public static boolean isEmpty(final String string) {
        return string.equals("");
    }
    
    public static int nthIndexOf(final String string, final String token, final int index) {
        int j = 0;
        for (int i = 0; i < index; ++i) {
            j = string.indexOf(token, j + 1);
            if (j == -1) {
                break;
            }
        }
        return j;
    }
    
    public static String capitalize(final String string) {
        if (string == null) {
            throw new NullArgumentException("string");
        }
        if (string.equals("")) {
            throw new EmptyStringException("string");
        }
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }
    
    public static String[] trim(final String[] strings) {
        for (int i = 0; i < strings.length; ++i) {
            strings[i] = strings[i].trim();
        }
        return strings;
    }
    
    public static URL toURL(String urlspec, final String relativePrefix) throws MalformedURLException {
        urlspec = urlspec.trim();
        URL url;
        try {
            url = new URL(urlspec);
            if (url.getProtocol().equals("file")) {
                url = makeURLFromFilespec(url.getFile(), relativePrefix);
            }
        }
        catch (Exception e) {
            try {
                url = makeURLFromFilespec(urlspec, relativePrefix);
            }
            catch (IOException n) {
                throw new MalformedURLException(n.toString());
            }
        }
        return url;
    }
    
    public static URI toURI(String urispec, final String relativePrefix) throws URISyntaxException {
        urispec = urispec.trim();
        URI uri;
        if (urispec.startsWith("file:")) {
            uri = makeURIFromFilespec(urispec.substring(5), relativePrefix);
        }
        else {
            uri = new URI(urispec);
        }
        return uri;
    }
    
    private static URL makeURLFromFilespec(final String filespec, final String relativePrefix) throws IOException {
        File file = new File(filespec);
        if (relativePrefix != null && !file.isAbsolute()) {
            file = new File(relativePrefix, filespec);
        }
        file = file.getCanonicalFile();
        return file.toURL();
    }
    
    private static URI makeURIFromFilespec(final String filespec, final String relativePrefix) {
        File file = new File(filespec);
        if (relativePrefix != null && !file.isAbsolute()) {
            file = new File(relativePrefix, filespec);
        }
        return file.toURI();
    }
    
    public static URL toURL(final String urlspec) throws MalformedURLException {
        return toURL(urlspec, null);
    }
    
    public static URI toURI(final String urispec) throws URISyntaxException {
        return toURI(urispec, null);
    }
    
    public static final boolean isJavaKeyword(final String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        for (int i = 0; i < Strings.keywords.length; ++i) {
            if (Strings.keywords[i].equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public static final boolean isEjbQlIdentifier(final String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        for (int i = 0; i < Strings.ejbQlIdentifiers.length; ++i) {
            if (Strings.ejbQlIdentifiers[i].equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    public static final boolean isValidJavaIdentifier(final String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        final char[] c = s.toCharArray();
        if (!Character.isJavaIdentifierStart(c[0])) {
            return false;
        }
        for (int i = 1; i < c.length; ++i) {
            if (!Character.isJavaIdentifierPart(c[i])) {
                return false;
            }
        }
        return true;
    }
    
    public static String removeWhiteSpace(final String s) {
        String retn = null;
        if (s != null) {
            final int len = s.length();
            final StringBuffer sbuf = new StringBuffer(len);
            for (int i = 0; i < len; ++i) {
                final char c = s.charAt(i);
                if (!Character.isWhitespace(c)) {
                    sbuf.append(c);
                }
            }
            retn = sbuf.toString();
        }
        return retn;
    }
    
    public static final String defaultToString(final Object object) {
        if (object == null) {
            return "null";
        }
        return object.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(object));
    }
    
    public static final void defaultToString(final JBossStringBuilder buffer, final Object object) {
        if (object == null) {
            buffer.append("null");
        }
        else {
            buffer.append(object.getClass().getName());
            buffer.append('@');
            buffer.append(Integer.toHexString(System.identityHashCode(object)));
        }
    }
    
    public static final void defaultToString(final StringBuffer buffer, final Object object) {
        if (object == null) {
            buffer.append("null");
        }
        else {
            buffer.append(object.getClass().getName());
            buffer.append('@');
            buffer.append(Integer.toHexString(System.identityHashCode(object)));
        }
    }
    
    public static long parseTimePeriod(final String period) {
        try {
            String s = period.toLowerCase();
            long factor;
            if (s.endsWith("msec")) {
                s = s.substring(0, s.lastIndexOf("msec"));
                factor = 1L;
            }
            else if (s.endsWith("sec")) {
                s = s.substring(0, s.lastIndexOf("sec"));
                factor = 1000L;
            }
            else if (s.endsWith("min")) {
                s = s.substring(0, s.lastIndexOf("min"));
                factor = 60000L;
            }
            else if (s.endsWith("h")) {
                s = s.substring(0, s.lastIndexOf("h"));
                factor = 3600000L;
            }
            else {
                factor = 1L;
            }
            return Long.parseLong(s) * factor;
        }
        catch (RuntimeException e) {
            throw new NumberFormatException("For input time period: '" + period + "'");
        }
    }
    
    public static long parsePositiveTimePeriod(final String period) {
        final long retval = parseTimePeriod(period);
        if (retval < 0L) {
            throw new NumberFormatException("Negative input time period: '" + period + "'");
        }
        return retval;
    }
    
    static {
        keywords = new String[] { "abstract", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "extends", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while", "true", "false", "null" };
        ejbQlIdentifiers = new String[] { "AND", "AS", "BETWEEN", "DISTINCT", "EMPTY", "FALSE", "FROM", "IN", "IS", "LIKE", "MEMBER", "NOT", "NULL", "OBJECT", "OF", "OR", "SELECT", "UNKNOWN", "TRUE", "WHERE" };
    }
    
    public static class Range
    {
        public int begin;
        public int end;
        
        public Range(final int begin, final int end) {
            this.begin = begin;
            this.end = end;
        }
        
        public Range() {
        }
    }
}
