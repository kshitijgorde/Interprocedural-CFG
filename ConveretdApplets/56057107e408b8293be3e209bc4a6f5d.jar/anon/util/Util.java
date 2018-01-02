// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import java.text.NumberFormat;
import java.text.DecimalFormat;
import anon.infoservice.ListenerInterface;
import anon.infoservice.InfoServiceDBEntry;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Vector;
import logging.LogHolder;
import logging.LogType;
import java.util.StringTokenizer;

public final class Util
{
    public static final String VERSION_FORMAT = "00.00.000";
    private static final String WHITESPACE_ENCODED = "%20";
    private static final String WHITESPACE = " ";
    public static final int MAX_FORMAT_BYTES = 0;
    public static final int MAX_FORMAT_KBYTES = 1;
    public static final int MAX_FORMAT_MBYTES = 2;
    public static final int MAX_FORMAT_GBYTES = 3;
    public static final int MAX_FORMAT_KBIT_PER_SEC = 0;
    public static final int MAX_FORMAT_MBIT_PER_SEC = 1;
    public static final int MAX_FORMAT_GBIT_PER_SEC = 2;
    public static final int MAX_FORMAT_ALL = 4;
    
    public static String cutString(String trim, final int n) {
        if (trim != null && trim.length() > n) {
            trim = trim.substring(0, n).trim();
        }
        return trim;
    }
    
    public static String stripString(final String s, final String s2) {
        if (s == null || s2 == null) {
            return null;
        }
        String string = "";
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        while (stringTokenizer.hasMoreTokens()) {
            string += stringTokenizer.nextToken().trim();
        }
        return string;
    }
    
    public static String decodeString(final String s) {
        String s2 = s;
        try {
            final byte[] decode = Base64.decode(s);
            if (decode != null) {
                s2 = new String(ZLibTools.decompress(decode));
            }
        }
        catch (Exception ex) {
            LogHolder.log(1, LogType.MISC, ex);
        }
        return s2;
    }
    
    public static boolean arraysEqual(final byte[] array, final byte[] array2) {
        if (array == null && array2 == null) {
            return true;
        }
        if (array == null || array2 == null) {
            return false;
        }
        if (array.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean arraysEqual(final char[] array, final char[] array2) {
        if (array == null && array2 == null) {
            return true;
        }
        if (array == null || array2 == null) {
            return false;
        }
        if (array.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static final boolean arraysEqual(final byte[] array, final int n, final byte[] array2, final int n2, final int n3) {
        if (n3 <= 0) {
            return true;
        }
        if (array == null || array2 == null || n < 0 || n2 < 0) {
            return false;
        }
        if (n + n3 > array.length || n2 + n3 > array2.length) {
            return false;
        }
        for (int i = 0; i < n3; ++i) {
            if (array[n + i] != array2[n2 + i]) {
                return false;
            }
        }
        return true;
    }
    
    public static Vector toVector(final Object o) {
        final Vector<Object> vector = new Vector<Object>();
        if (o != null) {
            vector.addElement(o);
        }
        return vector;
    }
    
    public static Object[] toArray(final Object o) {
        Object[] array;
        if (o != null) {
            array = new Object[] { o };
        }
        else {
            array = new Object[0];
        }
        return array;
    }
    
    private static void swap(final String[] array, final String[] array2, final int n, final int n2) {
        final String s = array[n];
        array[n] = array[n2];
        array[n2] = s;
        final String s2 = array2[n];
        array2[n] = array2[n2];
        array2[n2] = s2;
    }
    
    public static Vector sortStrings(final Vector vector) {
        final Vector<Object> vector2 = new Vector<Object>();
        final String[] array = new String[vector.size()];
        final int[] array2 = new int[vector.size()];
        final String[] array3 = new String[2];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i).toString().toLowerCase();
            array2[i] = i;
            boolean b = false;
            for (int n = 0; n < array3.length && n < array[i].length(); ++n) {
                if (isUmlaut(array[i].charAt(n), array3, n)) {
                    b = true;
                }
            }
            if (b) {
                String s = "";
                int n2;
                for (n2 = 0; n2 < array3.length && n2 < array[i].length(); ++n2) {
                    if (array3[n2] == null) {
                        s += array[i].charAt(n2);
                    }
                    else {
                        s += array3[n2];
                    }
                }
                if (n2 < array[i].length()) {
                    s += array[i].substring(n2, array[i].length());
                }
                array[i] = s;
            }
        }
        bubbleSortStrings(vector, array, array2);
        for (int j = 0; j < array.length; ++j) {
            vector2.addElement(vector.elementAt(array2[j]));
        }
        return vector2;
    }
    
    public static double parseDouble(final String s) throws NumberFormatException {
        int n = 0;
        int n2 = 0;
        int n3 = 1;
        int n4 = 1;
        int n5 = 1;
        if (s == null) {
            throw new NumberFormatException("NULL cannot be parsed as float!");
        }
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (Character.isDigit(char1)) {
                if (n4 != 0) {
                    n = n * 10 + (char1 - '0');
                }
                else {
                    n3 *= 10;
                    n2 = n2 * 10 + (char1 - '0');
                }
            }
            else if (n4 != 0 && (char1 == '.' || char1 == ',') && s.length() > 1) {
                n4 = 0;
            }
            else if (char1 != '+') {
                if (char1 != '-' || i != 0) {
                    throw new NumberFormatException("No valid float value '" + s + "'!");
                }
                n5 = -1;
            }
        }
        return (n + n2 / n3) * n5;
    }
    
    public static void sort(final String[] array, final String[] array2) {
        quicksort(array, array2, 0, array.length - 1);
    }
    
    private static int divide(final String[] array, final String[] array2, final int n, final int n2) {
        int n3 = n;
        for (int i = n; i < n2; ++i) {
            if (array[i].compareTo(array[n2]) <= 0) {
                swap(array, array2, n3, i);
                ++n3;
            }
        }
        swap(array, array2, n3, n2);
        return n3;
    }
    
    private static void quicksort(final String[] array, final String[] array2, final int n, final int n2) {
        if (n2 > n) {
            final int divide = divide(array, array2, n, n2);
            quicksort(array, array2, n, divide - 1);
            quicksort(array, array2, divide + 1, n2);
        }
    }
    
    public static void sort(final Vector vector, final Comparable comparable) {
        if (vector != null) {
            quicksort(vector, 0, vector.size() - 1, comparable);
        }
    }
    
    private static int divide(final Vector vector, final int n, final int n2, final Comparable comparable) {
        int n3 = n;
        for (int i = n; i < n2; ++i) {
            if (comparable.compare(vector.elementAt(i), vector.elementAt(n2)) <= 0) {
                swap(vector, n3, i);
                ++n3;
            }
        }
        swap(vector, n3, n2);
        return n3;
    }
    
    private static void quicksort(final Vector vector, final int n, final int n2, final Comparable comparable) {
        if (n2 > n) {
            final int divide = divide(vector, n, n2, comparable);
            quicksort(vector, n, divide - 1, comparable);
            quicksort(vector, divide + 1, n2, comparable);
        }
    }
    
    private static void swap(final Vector vector, final int n, final int n2) {
        final Object element = vector.elementAt(n);
        vector.setElementAt(vector.elementAt(n2), n);
        vector.setElementAt(element, n2);
    }
    
    private static void bubbleSortStrings(final Vector vector, final String[] array, final int[] array2) {
        for (int i = 1; i <= vector.size(); ++i) {
            for (int j = vector.size() - 1; j > i; --j) {
                if (array[j].compareTo(array[j - 1]) < 0) {
                    final String s = array[j];
                    final int n = array2[j];
                    array[j] = array[j - 1];
                    array2[j] = array2[j - 1];
                    array[j - 1] = s;
                    array2[j - 1] = n;
                }
            }
        }
    }
    
    private static boolean isUmlaut(final char c, final String[] array, final int n) {
        switch (c) {
            case '\u00e4': {
                array[n] = "ae";
                return true;
            }
            case '\u00f6': {
                array[n] = "oe";
                return true;
            }
            case '\u00fc': {
                array[n] = "ue";
                return true;
            }
            default: {
                array[n] = null;
                return false;
            }
        }
    }
    
    public static long convertVersionStringToNumber(final String s) throws NumberFormatException {
        if (s == null) {
            throw new NumberFormatException("Version string is null!");
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ".");
        long n;
        try {
            n = Long.parseLong(stringTokenizer.nextToken()) * 100000L + Long.parseLong(stringTokenizer.nextToken()) * 1000L + Long.parseLong(stringTokenizer.nextToken());
        }
        catch (NoSuchElementException ex) {
            throw new NumberFormatException("Version string is too short!");
        }
        return n;
    }
    
    public static String replaceAll(final String s, final String s2, final String s3) {
        return replaceAll(s, s2, s3, null);
    }
    
    public static String replaceAll(final String s, final String s2, final String s3, final String[] array) {
        final StringBuffer sb = new StringBuffer("");
        int i;
        int n;
        boolean b;
        for (i = s.indexOf(s2, 0), n = 0; i != -1; i = s.indexOf(s2, b ? n : (i + s2.length()))) {
            b = true;
            if (array != null) {
                final String substring = s.substring(i);
                for (int j = 0; j < array.length; ++j) {
                    if (substring.startsWith(array[j])) {
                        b = false;
                        break;
                    }
                }
            }
            if (b) {
                sb.append(s.substring(n, i));
                sb.append(s3);
                n = i + s2.length();
            }
        }
        sb.append(s.substring(n));
        return sb.toString();
    }
    
    public static String encodeWhiteSpaces(final String s) {
        final StringBuffer sb = new StringBuffer("");
        int i = s.indexOf(" ", 0);
        int n = 0;
        while (i != -1) {
            sb.append(s.substring(n, i));
            sb.append("%20");
            n = i + " ".length();
            i = s.indexOf(" ", i + 1);
        }
        sb.append(s.substring(n));
        return sb.toString();
    }
    
    public static void closeStream(final InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            }
            catch (Exception ex) {
                LogHolder.log(3, LogType.FILE, ex);
            }
        }
    }
    
    public static void closeStream(final OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            }
            catch (Exception ex) {
                LogHolder.log(3, LogType.FILE, ex);
            }
        }
    }
    
    public static void copyStream(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        if (inputStream == null) {
            throw new IOException("Input stream is null!");
        }
        if (outputStream == null) {
            throw new IOException("Output stream is null!");
        }
        final byte[] array = new byte[2048];
        int read;
        while ((read = inputStream.read(array)) != -1) {
            outputStream.write(array, 0, read);
        }
        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }
    
    public static String getStaticFieldValue(final Class clazz, final String s) {
        String s2 = null;
        try {
            s2 = (String)clazz.getField(s).get(null);
        }
        catch (Exception ex) {}
        return s2;
    }
    
    public static String colonizeSKI(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            sb.append(s.charAt(i));
            if ((i + 1) % 2 == 0 && i != s.length() - 1) {
                sb.append(":");
            }
        }
        return sb.toString();
    }
    
    public static InfoServiceDBEntry[] createDefaultInfoServices(final String[] array, final String[] array2, final int[][] array3) throws Exception {
        final InfoServiceDBEntry[] array4 = new InfoServiceDBEntry[array.length];
        for (int i = 0; i < array4.length; ++i) {
            final Vector vector = new Vector<ListenerInterface>(array3[i].length);
            for (int j = 0; j < array3[i].length; ++j) {
                vector.addElement(new ListenerInterface(array2[i], array3[i][j]));
            }
            (array4[i] = new InfoServiceDBEntry(array[i], array[i], vector, false, true, 0L, 0L, false)).markAsBootstrap();
        }
        return array4;
    }
    
    public static String formatKbitPerSecValueWithUnit(final long n) {
        return formatKbitPerSecValueWithUnit(n, 4);
    }
    
    public static String formatKbitPerSecValueWithUnit(final long n, final int n2) {
        return formatKbitPerSecValueWithoutUnit(n, n2) + " " + formatKbitPerSecValueOnlyUnit(n, n2);
    }
    
    public static String formatKbitPerSecValueOnlyUnit(final long n) {
        return formatKbitPerSecValueOnlyUnit(n, 4);
    }
    
    public static String formatKbitPerSecValueOnlyUnit(final long n, final int n2) {
        if (n < 1000L || n2 < 1) {
            return JAPMessages.getString("kbit/s");
        }
        if (n < 1000000L || n2 < 2) {
            return JAPMessages.getString("Mbit/s");
        }
        return JAPMessages.getString("Gbit/s");
    }
    
    public static String formatKbitPerSecValueWithoutUnit(final long n) {
        return formatKbitPerSecValueWithoutUnit(n, 4);
    }
    
    public static String formatKbitPerSecValueWithoutUnit(final long n, final int n2) {
        final DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getInstance(JAPMessages.getLocale());
        double n3 = n;
        if (n < 1000L || n2 < 1) {
            decimalFormat.applyPattern("#,####");
        }
        else if (n < 1000000L || n2 < 2) {
            n3 /= 1000.0;
            decimalFormat.applyPattern("#,##0.0");
        }
        else {
            n3 /= 1000000.0;
            decimalFormat.applyPattern("#,##0.0");
        }
        return decimalFormat.format(n3);
    }
    
    public static String formatBytesValueWithUnit(final long n) {
        return formatBytesValueWithUnit(n, 4);
    }
    
    public static String formatBytesValueWithUnit(final long n, final int n2) {
        return formatBytesValueWithoutUnit(n, n2) + " " + formatBytesValueOnlyUnit(n, n2);
    }
    
    public static String formatBytesValueOnlyUnit(final long n) {
        return formatBytesValueOnlyUnit(n, 4);
    }
    
    public static String formatBytesValueOnlyUnit(final long n, final int n2) {
        if (n < 1000L || n2 < 1) {
            return JAPMessages.getString("Byte");
        }
        if (n < 1000000L || n2 < 2) {
            return JAPMessages.getString("kByte");
        }
        if (n < 1000000000L || n2 < 3) {
            return JAPMessages.getString("MByte");
        }
        return JAPMessages.getString("GByte");
    }
    
    public static String formatBytesValueWithoutUnit(final long n) {
        return formatBytesValueWithoutUnit(n, 4);
    }
    
    public static String formatBytesValueWithoutUnit(final long n, final int n2) {
        final DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getInstance(JAPMessages.getLocale());
        double n3 = n;
        if (n < 1000L || n2 < 1) {
            decimalFormat.applyPattern("#,####");
        }
        else if (n < 1000000L || n2 < 2) {
            n3 /= 1000.0;
            decimalFormat.applyPattern("#,##0.0");
        }
        else if (n < 1000000000L || n2 < 3) {
            n3 /= 1000000.0;
            decimalFormat.applyPattern("#,##0.0");
        }
        else {
            n3 /= 1.0E9;
            decimalFormat.applyPattern("#,##0.0");
        }
        return decimalFormat.format(n3);
    }
    
    public static String toHTMLEntities(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 < '\0' || char1 > '\u007f') {
                sb.append("&#").append(Integer.toString(char1)).append(";");
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    public static class LongSortAsc implements Comparable
    {
        public int compare(final Object o, final Object o2) {
            if (o == null && o2 == null) {
                return 0;
            }
            if (o == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            if ((int)o == Long.MAX_VALUE) {
                return 1;
            }
            if ((int)o2 == Long.MAX_VALUE) {
                return -1;
            }
            return (int)((long)o - (long)o2);
        }
    }
    
    public static class LongSortDesc implements Comparable
    {
        public int compare(final Object o, final Object o2) {
            if (o == null && o2 == null) {
                return 0;
            }
            if (o == null) {
                return 1;
            }
            if (o2 == null) {
                return -1;
            }
            if ((int)o == Long.MAX_VALUE) {
                return -1;
            }
            if ((int)o2 == Long.MAX_VALUE) {
                return 1;
            }
            return (int)((long)o2 - (long)o);
        }
    }
    
    public static class IntegerSortAsc implements Comparable
    {
        public int compare(final Object o, final Object o2) {
            if (o == null && o2 == null) {
                return 0;
            }
            if (o == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            if ((int)o == Integer.MAX_VALUE) {
                return 1;
            }
            if ((int)o2 == Integer.MAX_VALUE) {
                return -1;
            }
            return (int)o - (int)o2;
        }
    }
    
    public static class IntegerSortDesc implements Comparable
    {
        public int compare(final Object o, final Object o2) {
            if (o == null && o2 == null) {
                return 0;
            }
            if (o == null) {
                return 1;
            }
            if (o2 == null) {
                return -1;
            }
            if ((int)o == Integer.MAX_VALUE) {
                return -1;
            }
            if ((int)o2 == Integer.MAX_VALUE) {
                return 1;
            }
            return (int)o2 - (int)o;
        }
    }
    
    public static class StringSortAsc implements Comparable
    {
        public int compare(final Object o, final Object o2) {
            if (o == null && o2 == null) {
                return 0;
            }
            if (o == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            return ((String)o).compareTo((String)o2);
        }
    }
    
    public interface Comparable
    {
        int compare(final Object p0, final Object p1);
    }
}
