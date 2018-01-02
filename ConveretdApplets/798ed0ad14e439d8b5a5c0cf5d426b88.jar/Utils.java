import java.util.Vector;
import java.awt.Frame;
import java.awt.Component;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.awt.Point;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class Utils
{
    private static char[] map1;
    private static byte[] map2;
    
    public static final boolean equals(final String s, final String s2) {
        return s == s2 || (s != null && s2 != null && s.toLowerCase().equals(s2.toLowerCase()));
    }
    
    public static final boolean equals(final Object o, final Object o2) {
        if (o == o2) {
            return true;
        }
        if (o == null) {
            return o2 == null;
        }
        return o.equals(o2);
    }
    
    public static final int indexOf(final Object[] array, final Object o) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }
    
    public static final Rectangle unio(final Rectangle rectangle, final Rectangle rectangle2) {
        if (rectangle == null || rectangle.width == 0 || rectangle.height == 0) {
            return rectangle2;
        }
        if (rectangle2 == null || rectangle2.width == 0 || rectangle.height == 0) {
            return rectangle;
        }
        return rectangle.union(rectangle2);
    }
    
    public static final Point projeccio(final Point point, final Rectangle rectangle) {
        final Point point2 = new Point(point);
        if (point2.x < rectangle.x) {
            point2.x = rectangle.x;
        }
        else if (point2.x > rectangle.x + rectangle.width - 1) {
            point2.x = rectangle.x + rectangle.width - 1;
        }
        if (point2.y < rectangle.y) {
            point2.y = rectangle.y;
        }
        else if (point2.y > rectangle.y + rectangle.height - 1) {
            point2.y = rectangle.y + rectangle.height - 1;
        }
        return point2;
    }
    
    public static final String netejaCadenaOTeclat(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            final String char2Latex = char2Latex(char1);
            if (char2Latex == null) {
                sb.append(char1);
            }
            else {
                sb.append(char2Latex);
            }
        }
        return sb.toString();
    }
    
    public static final String char2Latex(final char c) {
        if (c < ' ') {
            return "";
        }
        switch (c) {
            case '_': {
                return "\\_";
            }
            case ' ': {
                return "\\space";
            }
            case '{': {
                return "\\{";
            }
            case '}': {
                return "\\}";
            }
            case '&': {
                return "\\&";
            }
            case '^': {
                return "\\^";
            }
            case '\"': {
                return "\\quote";
            }
            case '\\': {
                return "\\\\";
            }
            case '%': {
                return "\\%";
            }
            case '\u00d7': {
                return "\\times";
            }
            case 'º': {
                return "\\SIangledegree";
            }
            case '°': {
                return "\\SIangledegree";
            }
            case '\'': {
                return "\\prima";
            }
            default: {
                return null;
            }
        }
    }
    
    public static final String parameter(final String s, final String s2) {
        final String s3 = "<param/>";
        final int index = s.indexOf(s3);
        return s.substring(0, index) + s2 + s.substring(index + s3.length());
    }
    
    public static final void loadBinHashtable(final String s, final Hashtable hashtable, final Hashtable hashtable2) {
        loadBinHashtable(s, hashtable, hashtable2, '@');
    }
    
    public static final void loadBinHashtable(final String s, final Hashtable hashtable, final Hashtable hashtable2, final char c) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "" + c);
        while (stringTokenizer.hasMoreElements()) {
            final String nextToken = stringTokenizer.nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            if (hashtable != null) {
                hashtable.put(nextToken, nextToken2);
            }
            if (hashtable2 != null) {
                hashtable2.put(nextToken2, nextToken);
            }
        }
    }
    
    public static final String urlCat(final String s, final String s2) {
        if (s == null || s2 == null) {
            return null;
        }
        if (s.length() == 0) {
            return s2;
        }
        if (s2.length() == 0) {
            return s;
        }
        if (s.endsWith("/")) {
            return s + s2;
        }
        return s + "/" + s2;
    }
    
    public static final String toFirstUpperCase(String s) {
        if (s != null && s.length() > 0) {
            final char[] charArray = s.toCharArray();
            charArray[0] = Character.toUpperCase(charArray[0]);
            s = new String(charArray);
        }
        return s;
    }
    
    public static final String substitute(final String s, final char c, final String s2) {
        return substitute(s, "" + c, s2);
    }
    
    public static final String substitute(final String s, final String s2, final String s3) {
        final StringBuffer sb = new StringBuffer();
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (s.startsWith(s2, i)) {
                sb.append(s3);
                i += s2.length() - 1;
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    public static final Frame getMainFrame(final Component component) {
        Component parent;
        for (parent = component; parent != null && !(parent instanceof Frame); parent = parent.getParent()) {}
        return (Frame)parent;
    }
    
    public static final String[] split(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        final Vector vector = new Vector<String>();
        while (stringTokenizer.hasMoreTokens()) {
            vector.addElement(stringTokenizer.nextToken());
        }
        final String[] array = new String[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public static final String encode(final byte[] array) {
        return encode(array, array.length);
    }
    
    public static final String encode(final byte[] array, final int n) {
        final int n2 = (n * 4 + 2) / 3;
        final char[] array2 = new char[(n + 2) / 3 * 4];
        int n4;
        int n5;
        int n6;
        int n7;
        int n8;
        int n9;
        int n10;
        for (int i = 0, n3 = 0; i < n; n4 = (array[i++] & 0xFF), n5 = ((i < n) ? (array[i++] & 0xFF) : 0), n6 = ((i < n) ? (array[i++] & 0xFF) : 0), n7 = n4 >>> 2, n8 = ((n4 & 0x3) << 4 | n5 >>> 4), n9 = ((n5 & 0xF) << 2 | n6 >>> 6), n10 = (n6 & 0x3F), array2[n3++] = Utils.map1[n7], array2[n3++] = Utils.map1[n8], array2[n3] = ((n3 < n2) ? Utils.map1[n9] : '='), ++n3, array2[n3] = ((n3 < n2) ? Utils.map1[n10] : '='), ++n3) {}
        return new String(array2);
    }
    
    public static final byte[] decode(final String s) {
        final char[] array = new char[s.length()];
        s.getChars(0, array.length, array, 0);
        return decode(array);
    }
    
    public static final byte[] decode(final char[] array) {
        int length = array.length;
        if (length % 4 != 0) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
        }
        while (length > 0 && array[length - 1] == '=') {
            --length;
        }
        final int n = length * 3 / 4;
        final byte[] array2 = new byte[n];
        int i = 0;
        int n2 = 0;
        while (i < length) {
            final char c = array[i++];
            final char c2 = array[i++];
            final char c3 = (i < length) ? array[i++] : 'A';
            final char c4 = (i < length) ? array[i++] : 'A';
            if (c > '\u007f' || c2 > '\u007f' || c3 > '\u007f' || c4 > '\u007f') {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            final byte b = Utils.map2[c];
            final byte b2 = Utils.map2[c2];
            final byte b3 = Utils.map2[c3];
            final byte b4 = Utils.map2[c4];
            if (b < 0 || b2 < 0 || b3 < 0 || b4 < 0) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            final int n3 = b << 2 | b2 >>> 4;
            final int n4 = (b2 & 0xF) << 4 | b3 >>> 2;
            final int n5 = (b3 & 0x3) << 6 | b4;
            array2[n2++] = (byte)n3;
            if (n2 < n) {
                array2[n2++] = (byte)n4;
            }
            if (n2 >= n) {
                continue;
            }
            array2[n2++] = (byte)n5;
        }
        return array2;
    }
    
    public static final void sort(final String[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            for (int j = i + 1; j < length; ++j) {
                if (array[i].compareTo(array[j]) > 0) {
                    final String s = array[i];
                    array[i] = array[j];
                    array[j] = s;
                }
            }
        }
    }
    
    static {
        Utils.map1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
        Utils.map2 = new byte[128];
        for (int i = 0; i < Utils.map2.length; ++i) {
            Utils.map2[i] = -1;
        }
        for (int j = 0; j < 64; ++j) {
            Utils.map2[Utils.map1[j]] = (byte)j;
        }
    }
}
