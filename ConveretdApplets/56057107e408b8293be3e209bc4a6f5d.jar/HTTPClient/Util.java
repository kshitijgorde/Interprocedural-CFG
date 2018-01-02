// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.text.DateFormat;
import java.util.TimeZone;
import java.util.SimpleTimeZone;
import java.util.Locale;
import java.util.Date;
import java.net.URL;
import java.util.Vector;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import java.util.BitSet;

public class Util
{
    private static final BitSet Separators;
    private static final BitSet TokenChar;
    private static final BitSet UnsafeChar;
    private static SimpleDateFormat http_format;
    static final char[] hex_map;
    private static final String nl;
    static /* synthetic */ Class class$HTTPClient$Util;
    
    static final Object[] resizeArray(final Object[] array, final int n) {
        final Object[] array2 = new Object[n];
        System.arraycopy(array, 0, array2, 0, (array.length < n) ? array.length : n);
        return array2;
    }
    
    static final NVPair[] resizeArray(final NVPair[] array, final int n) {
        final NVPair[] array2 = new NVPair[n];
        System.arraycopy(array, 0, array2, 0, (array.length < n) ? array.length : n);
        return array2;
    }
    
    static final AuthorizationInfo[] resizeArray(final AuthorizationInfo[] array, final int n) {
        final AuthorizationInfo[] array2 = new AuthorizationInfo[n];
        System.arraycopy(array, 0, array2, 0, (array.length < n) ? array.length : n);
        return array2;
    }
    
    static final Cookie[] resizeArray(final Cookie[] array, final int n) {
        final Cookie[] array2 = new Cookie[n];
        System.arraycopy(array, 0, array2, 0, (array.length < n) ? array.length : n);
        return array2;
    }
    
    static final String[] resizeArray(final String[] array, final int n) {
        final String[] array2 = new String[n];
        System.arraycopy(array, 0, array2, 0, (array.length < n) ? array.length : n);
        return array2;
    }
    
    static final boolean[] resizeArray(final boolean[] array, final int n) {
        final boolean[] array2 = new boolean[n];
        System.arraycopy(array, 0, array2, 0, (array.length < n) ? array.length : n);
        return array2;
    }
    
    static final byte[] resizeArray(final byte[] array, final int n) {
        final byte[] array2 = new byte[n];
        System.arraycopy(array, 0, array2, 0, (array.length < n) ? array.length : n);
        return array2;
    }
    
    static final char[] resizeArray(final char[] array, final int n) {
        final char[] array2 = new char[n];
        System.arraycopy(array, 0, array2, 0, (array.length < n) ? array.length : n);
        return array2;
    }
    
    static final int[] resizeArray(final int[] array, final int n) {
        final int[] array2 = new int[n];
        System.arraycopy(array, 0, array2, 0, (array.length < n) ? array.length : n);
        return array2;
    }
    
    static String[] splitProperty(final String s) {
        if (s == null) {
            return new String[0];
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken().trim();
        }
        return array;
    }
    
    static String[] splitList(final String s, final String s2) {
        if (s == null) {
            return new String[0];
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken().trim();
        }
        return array;
    }
    
    static final Hashtable getList(final Hashtable hashtable, final Object o) {
        Hashtable hashtable2 = hashtable.get(o);
        if (hashtable2 == null) {
            synchronized (hashtable) {
                hashtable2 = hashtable.get(o);
                if (hashtable2 == null) {
                    hashtable2 = new Hashtable();
                    hashtable.put(o, hashtable2);
                }
            }
        }
        return hashtable2;
    }
    
    static final int[] compile_search(final byte[] array) {
        final int[] array2 = { 0, 1, 0, 1, 0, 1 };
        for (int i = 0; i < array.length; ++i) {
            int n;
            for (n = i + 1; n < array.length && array[i] != array[n]; ++n) {}
            if (n < array.length) {
                if (n - i > array2[1]) {
                    array2[4] = array2[2];
                    array2[5] = array2[3];
                    array2[2] = array2[0];
                    array2[3] = array2[1];
                    array2[1] = n - (array2[0] = i);
                }
                else if (n - i > array2[3]) {
                    array2[4] = array2[2];
                    array2[5] = array2[3];
                    array2[3] = n - (array2[2] = i);
                }
                else if (n - i > array2[3]) {
                    array2[5] = n - (array2[4] = i);
                }
            }
        }
        final int[] array3 = array2;
        final int n2 = 1;
        array3[n2] += array2[0];
        final int[] array4 = array2;
        final int n3 = 3;
        array4[n3] += array2[2];
        final int[] array5 = array2;
        final int n4 = 5;
        array5[n4] += array2[4];
        return array2;
    }
    
    static final int findStr(final byte[] array, final int[] array2, final byte[] array3, int n, final int n2) {
        final int n3 = array2[0];
        final int n4 = array2[1];
        final int n5 = n4 - n3;
        final int n6 = array2[2];
        final int n7 = array2[3];
        final int n8 = n7 - n6;
        final int n9 = array2[4];
        final int n10 = array2[5];
        final int n11 = n10 - n9;
        while (n + array.length <= n2) {
            if (array[n4] == array3[n + n4]) {
                if (array[n3] == array3[n + n3]) {
                    boolean b = true;
                    for (int i = 0; i < array.length; ++i) {
                        if (array[i] != array3[n + i]) {
                            b = false;
                            break;
                        }
                    }
                    if (b) {
                        break;
                    }
                }
                n += n5;
            }
            else if (array[n7] == array3[n + n7]) {
                n += n8;
            }
            else if (array[n10] == array3[n + n10]) {
                n += n11;
            }
            else {
                ++n;
            }
        }
        if (n + array.length > n2) {
            return -1;
        }
        return n;
    }
    
    public static final String dequoteString(final String s) {
        if (s.indexOf(92) == -1) {
            return s;
        }
        final char[] charArray = s.toCharArray();
        int i = 0;
        int n = 0;
        while (i < charArray.length) {
            if (charArray[i] == '\\' && i + 1 < charArray.length) {
                System.arraycopy(charArray, i + 1, charArray, i, charArray.length - i - 1);
                ++n;
            }
            ++i;
        }
        return new String(charArray, 0, charArray.length - n);
    }
    
    public static final String quoteString(final String s, final String s2) {
        char[] charArray;
        int n;
        for (charArray = s2.toCharArray(), n = 0; n < charArray.length && s.indexOf(charArray[n]) == -1; ++n) {}
        if (n == charArray.length) {
            return s;
        }
        int length = s.length();
        char[] resizeArray = new char[length * 2];
        s.getChars(0, length, resizeArray, 0);
        for (int i = 0; i < length; ++i) {
            if (s2.indexOf(resizeArray[i], 0) != -1) {
                if (length == resizeArray.length) {
                    resizeArray = resizeArray(resizeArray, length + s.length());
                }
                System.arraycopy(resizeArray, i, resizeArray, i + 1, length - i);
                ++length;
                resizeArray[i++] = '\\';
            }
        }
        return new String(resizeArray, 0, length);
    }
    
    public static final Vector parseHeader(final String s) throws ParseException {
        return parseHeader(s, true);
    }
    
    public static final Vector parseHeader(final String s, final boolean b) throws ParseException {
        if (s == null) {
            return null;
        }
        final Vector<HttpHeaderElement> vector = new Vector<HttpHeaderElement>();
        int n = 1;
        int n2 = -1;
        int n3 = 0;
        final int length = s.length();
        final int[] array = { 0 };
        while (true) {
            if (n == 0) {
                n2 = skipSpace(s, n3);
                if (n2 == length) {
                    break;
                }
                if (s.charAt(n2) != ',') {
                    throw new ParseException("Bad header format: '" + s + "'\nExpected \",\" at position " + n2);
                }
            }
            n = 0;
            n2 = skipSpace(s, n2 + 1);
            if (n2 == length) {
                break;
            }
            final char char1;
            if ((char1 = s.charAt(n2)) == ',') {
                n3 = n2;
            }
            else {
                if (char1 == '=' || char1 == ';' || char1 == '\"') {
                    throw new ParseException("Bad header format: '" + s + "'\nEmpty element name at position " + n2);
                }
                int n4;
                char char2;
                for (n4 = n2 + 1; n4 < length && !Character.isWhitespace(char2 = s.charAt(n4)) && char2 != '=' && char2 != ',' && char2 != ';'; ++n4) {}
                final String substring = s.substring(n2, n4);
                final int skipSpace = skipSpace(s, n4);
                String value;
                if (skipSpace < length && s.charAt(skipSpace) == '=') {
                    array[0] = skipSpace + 1;
                    value = parseValue(s, array, b);
                    n3 = array[0];
                }
                else {
                    value = null;
                    n3 = skipSpace;
                }
                NVPair[] resizeArray = new NVPair[0];
                while (true) {
                    n2 = skipSpace(s, n3);
                    if (n2 == length) {
                        break;
                    }
                    if (s.charAt(n2) != ';') {
                        break;
                    }
                    n2 = skipSpace(s, n2 + 1);
                    final char char3;
                    if (n2 == length || (char3 = s.charAt(n2)) == ',') {
                        n3 = n2;
                        break;
                    }
                    if (char3 == ';') {
                        n3 = n2;
                    }
                    else {
                        if (char3 == '=' || char3 == '\"') {
                            throw new ParseException("Bad header format: '" + s + "'\nEmpty parameter name at position " + n2);
                        }
                        int n5;
                        char char4;
                        for (n5 = n2 + 1; n5 < length && !Character.isWhitespace(char4 = s.charAt(n5)) && char4 != '=' && char4 != ',' && char4 != ';'; ++n5) {}
                        final String substring2 = s.substring(n2, n5);
                        final int skipSpace2 = skipSpace(s, n5);
                        String value2;
                        if (skipSpace2 < length && s.charAt(skipSpace2) == '=') {
                            array[0] = skipSpace2 + 1;
                            value2 = parseValue(s, array, b);
                            n3 = array[0];
                        }
                        else {
                            value2 = null;
                            n3 = skipSpace2;
                        }
                        resizeArray = resizeArray(resizeArray, resizeArray.length + 1);
                        resizeArray[resizeArray.length - 1] = new NVPair(substring2, value2);
                    }
                }
                vector.addElement(new HttpHeaderElement(substring, value, resizeArray));
            }
        }
        return vector;
    }
    
    private static String parseValue(final char[] array, final int[] array2, final String s, final boolean b) throws ParseException {
        final int n = array2[0];
        final int length = array.length;
        int skipSpace = skipSpace(array, n);
        int n2;
        String s2;
        if (skipSpace < length && array[skipSpace] == '\"') {
            n2 = ++skipSpace;
            char[] array3 = null;
            int n3 = 0;
            int n4 = skipSpace;
            while (n2 < length && array[n2] != '\"') {
                if (array[n2] == '\\') {
                    if (b) {
                        if (array3 == null) {
                            array3 = new char[array.length];
                        }
                        System.arraycopy(array, n4, array3, n3, n2 - n4);
                        n3 += n2 - n4;
                        n4 = ++n2;
                    }
                    else {
                        ++n2;
                    }
                }
                ++n2;
            }
            if (n2 == length) {
                throw new ParseException("Bad header format: '" + s + "'\nClosing <\"> for quoted-string" + " starting at position " + (skipSpace - 1) + " not found");
            }
            if (array3 != null) {
                System.arraycopy(array, n4, array3, n3, n2 - n4);
                s2 = new String(array3, 0, n3 + (n2 - n4));
            }
            else {
                s2 = new String(array, skipSpace, n2 - skipSpace);
            }
            ++n2;
        }
        else {
            for (n2 = skipSpace; n2 < length && !Character.isWhitespace(array[n2]) && array[n2] != ',' && array[n2] != ';'; ++n2) {}
            s2 = new String(array, skipSpace, n2 - skipSpace);
        }
        array2[0] = n2;
        return s2;
    }
    
    private static String parseValue(final String s, final int[] array, final boolean b) throws ParseException {
        final int n = array[0];
        final int length = s.length();
        int skipSpace = skipSpace(s, n);
        int n2;
        String s2;
        if (skipSpace < length && s.charAt(skipSpace) == '\"') {
            n2 = ++skipSpace;
            char[] array2 = null;
            int n3 = 0;
            int n4 = skipSpace;
            char char1;
            while (n2 < length && (char1 = s.charAt(n2)) != '\"') {
                if (char1 == '\\') {
                    if (b) {
                        if (array2 == null) {
                            array2 = new char[length];
                        }
                        s.getChars(n4, n2, array2, n3);
                        n3 += n2 - n4;
                        n4 = ++n2;
                    }
                    else {
                        ++n2;
                    }
                }
                ++n2;
            }
            if (n2 == length) {
                throw new ParseException("Bad header format: '" + s + "'\nClosing <\"> for quoted-string" + " starting at position " + (skipSpace - 1) + " not found");
            }
            if (array2 != null) {
                s.getChars(n4, n2, array2, n3);
                s2 = new String(array2, 0, n3 + (n2 - n4));
            }
            else {
                s2 = s.substring(skipSpace, n2);
            }
            ++n2;
        }
        else {
            char char2;
            for (n2 = skipSpace; n2 < length && !Character.isWhitespace(char2 = s.charAt(n2)) && char2 != ',' && char2 != ';'; ++n2) {}
            s2 = s.substring(skipSpace, n2);
        }
        array[0] = n2;
        return s2;
    }
    
    public static final boolean hasToken(final String s, final String s2) throws ParseException {
        return s != null && parseHeader(s).contains(new HttpHeaderElement(s2));
    }
    
    public static final HttpHeaderElement getElement(final Vector vector, final String s) {
        final int index = vector.indexOf(new HttpHeaderElement(s));
        if (index == -1) {
            return null;
        }
        return vector.elementAt(index);
    }
    
    public static final String getParameter(final String s, final String s2) throws ParseException {
        final NVPair[] params = parseHeader(s2).firstElement().getParams();
        for (int i = 0; i < params.length; ++i) {
            if (params[i].getName().equalsIgnoreCase(s)) {
                return params[i].getValue();
            }
        }
        return null;
    }
    
    public static final String assembleHeader(final Vector vector) {
        final StringBuffer sb = new StringBuffer(200);
        for (int size = vector.size(), i = 0; i < size; ++i) {
            vector.elementAt(i).appendTo(sb);
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }
    
    static final int skipSpace(final String s, int n) {
        while (n < s.length() && Character.isWhitespace(s.charAt(n))) {
            ++n;
        }
        return n;
    }
    
    static final int skipSpace(final char[] array, int n) {
        while (n < array.length && Character.isWhitespace(array[n])) {
            ++n;
        }
        return n;
    }
    
    static final int findSpace(final String s, int n) {
        while (n < s.length() && !Character.isWhitespace(s.charAt(n))) {
            ++n;
        }
        return n;
    }
    
    static final int findSpace(final char[] array, int n) {
        while (n < array.length && !Character.isWhitespace(array[n])) {
            ++n;
        }
        return n;
    }
    
    static final int skipToken(final char[] array, int n) {
        while (n < array.length && Util.TokenChar.get(array[n])) {
            ++n;
        }
        return n;
    }
    
    static final boolean needsQuoting(final String s) {
        int length;
        int n;
        for (length = s.length(), n = 0; n < length && Util.TokenChar.get(s.charAt(n)); ++n) {}
        return n < length;
    }
    
    public static final String getValue(final NVPair[] array, final String s) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (array[i].getName().equalsIgnoreCase(s)) {
                return array[i].getValue();
            }
        }
        return null;
    }
    
    public static final int getIndex(final NVPair[] array, final String s) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (array[i].getName().equalsIgnoreCase(s)) {
                return i;
            }
        }
        return -1;
    }
    
    public static final NVPair[] setValue(NVPair[] resizeArray, final String s, final String s2) {
        int n = getIndex(resizeArray, s);
        if (n == -1) {
            n = resizeArray.length;
            resizeArray = resizeArray(resizeArray, resizeArray.length + 1);
        }
        resizeArray[n] = new NVPair(s, s2);
        return resizeArray;
    }
    
    public static final NVPair[] setValue(NVPair[] resizeArray, final String s, final String s2, final boolean b) {
        int n = getIndex(resizeArray, s);
        if (n == -1) {
            n = resizeArray.length;
            resizeArray = resizeArray(resizeArray, resizeArray.length + 1);
        }
        resizeArray[n] = new NVPair(s, s2, b);
        return resizeArray;
    }
    
    public static final void updateValue(final NVPair[] array, final String s, final String s2) {
        final int index = getIndex(array, s);
        if (index != -1) {
            array[index] = new NVPair(s, s2);
        }
    }
    
    public static final NVPair[] addValue(NVPair[] resizeArray, final String s, final String s2) {
        final int length = resizeArray.length;
        resizeArray = resizeArray(resizeArray, resizeArray.length + 1);
        resizeArray[length] = new NVPair(s, s2);
        return resizeArray;
    }
    
    public static final NVPair[] removeAllValues(NVPair[] resizeArray, final String s) {
        int length = resizeArray.length;
        for (int i = 0; i < resizeArray.length; ++i) {
            final int n = i;
            while (i < resizeArray.length && resizeArray[i].getName().equalsIgnoreCase(s)) {
                ++i;
            }
            if (i - n > 0) {
                length -= i - n;
                System.arraycopy(resizeArray, i, resizeArray, n, length - n);
            }
        }
        if (length < resizeArray.length) {
            resizeArray = resizeArray(resizeArray, length);
        }
        return resizeArray;
    }
    
    public static final NVPair[] addToken(final NVPair[] array, final String s, final String s2) throws ParseException {
        final int index = getIndex(array, s);
        if (index == -1) {
            return addValue(array, s, s2);
        }
        if (!hasToken(array[index].getValue(), s2)) {
            array[index] = new NVPair(s, array[index].getValue() + ", " + s2);
        }
        return array;
    }
    
    public static final NVPair[] removeToken(NVPair[] resizeArray, final String s, final String s2) throws ParseException {
        final int index = getIndex(resizeArray, s);
        if (index == -1) {
            return resizeArray;
        }
        final Vector header = parseHeader(resizeArray[index].getValue());
        if (!header.removeElement(new HttpHeaderElement(s2))) {
            return resizeArray;
        }
        if (header.isEmpty()) {
            System.arraycopy(resizeArray, index + 1, resizeArray, index, resizeArray.length - index - 1);
            resizeArray = resizeArray(resizeArray, resizeArray.length - 1);
        }
        else {
            resizeArray[index] = new NVPair(s, assembleHeader(header));
        }
        return resizeArray;
    }
    
    public static final boolean sameHttpURL(final URL url, final URL url2) {
        if (!url.getProtocol().equalsIgnoreCase(url2.getProtocol())) {
            return false;
        }
        if (!url.getHost().equalsIgnoreCase(url2.getHost())) {
            return false;
        }
        int n = url.getPort();
        int n2 = url2.getPort();
        if (n == -1) {
            n = URI.defaultPort(url.getProtocol());
        }
        if (n2 == -1) {
            n2 = URI.defaultPort(url.getProtocol());
        }
        if (n != n2) {
            return false;
        }
        try {
            return URI.unescape(url.getFile()).equals(URI.unescape(url2.getFile()));
        }
        catch (ParseException ex) {
            return url.getFile().equals(url2.getFile());
        }
    }
    
    public static final String httpDate(final Date date) {
        if (Util.http_format == null) {
            final Class clazz = (Util.class$HTTPClient$Util == null) ? (Util.class$HTTPClient$Util = class$("HTTPClient.Util")) : Util.class$HTTPClient$Util;
            synchronized (clazz) {
                if (Util.http_format == null) {
                    (Util.http_format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US)).setTimeZone(new SimpleTimeZone(0, "GMT"));
                }
            }
        }
        return Util.http_format.format(date);
    }
    
    static final String escapeUnsafeChars(final String s) {
        final int length = s.length();
        final char[] array = new char[3 * length];
        int n = 0;
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 >= '\u0080' || Util.UnsafeChar.get(char1)) {
                array[n++] = '%';
                array[n++] = Util.hex_map[(char1 & '\u00f0') >>> 4];
                array[n++] = Util.hex_map[char1 & '\u000f'];
            }
            else {
                array[n++] = char1;
            }
        }
        if (n > length) {
            return new String(array, 0, n);
        }
        return s;
    }
    
    public static final String getPath(final String s) {
        int length = s.length();
        final int index;
        if ((index = s.indexOf(35)) != -1) {
            length = index;
        }
        final int index2;
        if ((index2 = s.indexOf(63)) != -1 && index2 < length) {
            length = index2;
        }
        return s.substring(0, length);
    }
    
    public static final String getQuery(final String s) {
        int length = s.length();
        final int index;
        if ((index = s.indexOf(35)) != -1) {
            length = index;
        }
        final int index2;
        if ((index2 = s.indexOf(63)) != -1 && index2 < length) {
            return s.substring(index2 + 1, length);
        }
        return null;
    }
    
    public static final String getFragment(final String s) {
        final int index;
        if ((index = s.indexOf(35)) != -1) {
            return s.substring(index + 1);
        }
        return null;
    }
    
    static final void logLine(final String s) {
        System.err.print(s + " (" + Thread.currentThread() + ")" + Util.nl);
        System.err.flush();
    }
    
    static final void logLine() {
        System.err.println();
        System.err.flush();
    }
    
    static final void logMessage(final String s) {
        System.err.print(s);
        System.err.flush();
    }
    
    static final void logStackTrace(final Throwable t) {
        t.printStackTrace(System.err);
        System.err.flush();
    }
    
    public static void getBytes(final String s, final byte[] array, final int n) {
        final byte[] bytes = s.getBytes();
        System.arraycopy(bytes, 0, array, n, bytes.length);
    }
    
    public static void getBytes(final String s, final int n, final byte[] array, final int n2) {
        System.arraycopy(s.getBytes(), 0, array, n2, n);
    }
    
    public static Date parseDate(final String s) throws IllegalArgumentException {
        try {
            return DateFormat.getDateTimeInstance().parse(s);
        }
        catch (java.text.ParseException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        (Separators = new BitSet(128)).set(40);
        Util.Separators.set(41);
        Util.Separators.set(60);
        Util.Separators.set(62);
        Util.Separators.set(64);
        Util.Separators.set(44);
        Util.Separators.set(59);
        Util.Separators.set(58);
        Util.Separators.set(92);
        Util.Separators.set(34);
        Util.Separators.set(47);
        Util.Separators.set(91);
        Util.Separators.set(93);
        Util.Separators.set(63);
        Util.Separators.set(61);
        Util.Separators.set(123);
        Util.Separators.set(125);
        Util.Separators.set(32);
        Util.Separators.set(9);
        TokenChar = new BitSet(128);
        for (int i = 32; i < 127; ++i) {
            Util.TokenChar.set(i);
        }
        Util.TokenChar.xor(Util.Separators);
        UnsafeChar = new BitSet(128);
        for (int j = 0; j < 32; ++j) {
            Util.UnsafeChar.set(j);
        }
        Util.UnsafeChar.set(32);
        Util.UnsafeChar.set(60);
        Util.UnsafeChar.set(62);
        Util.UnsafeChar.set(34);
        Util.UnsafeChar.set(123);
        Util.UnsafeChar.set(125);
        Util.UnsafeChar.set(124);
        Util.UnsafeChar.set(92);
        Util.UnsafeChar.set(94);
        Util.UnsafeChar.set(126);
        Util.UnsafeChar.set(91);
        Util.UnsafeChar.set(93);
        Util.UnsafeChar.set(96);
        Util.UnsafeChar.set(127);
        hex_map = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        nl = System.getProperty("line.separator");
    }
}
