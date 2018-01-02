// 
// Decompiled by Procyon v0.5.30
// 

package jclass.util;

import java.util.Date;
import java.util.Vector;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.Image;
import java.applet.Applet;
import java.awt.Component;
import java.util.Hashtable;

public class JCUtilConverter
{
    public static JCConverter conv;
    public static Hashtable param_source;
    
    public static void setParamSource(final Component component, String s) {
        if (s != null) {
            int index = 0;
            int index2 = 0;
            boolean b = false;
            while (index < s.length() && (index = s.indexOf(60, index)) != -1) {
                if (s.regionMatches(true, index, "<applet", 0, 7)) {
                    for (index2 = index + 7; (index2 = s.indexOf("</", index2)) != -1; ++index2) {
                        if (s.regionMatches(true, index2, "</applet>", 0, 9)) {
                            b = true;
                            break;
                        }
                    }
                }
                if (b) {
                    break;
                }
                ++index;
            }
            if (b) {
                s = s.substring(index, index2);
            }
            if (JCUtilConverter.param_source == null) {
                JCUtilConverter.param_source = new Hashtable();
            }
            final Hashtable<String, String> hashtable = new Hashtable<String, String>();
            JCUtilConverter.param_source.put(component, hashtable);
            int index3;
            while ((index3 = s.indexOf(60)) != -1) {
                s = s.substring(index3 + 1);
                final JCStringTokenizer jcStringTokenizer = new JCStringTokenizer(s);
                jcStringTokenizer.strip_esc = false;
                final String trim = trim(jcStringTokenizer.nextToken('>'));
                if (trim == null || trim.length() == 0) {
                    return;
                }
                s = trim(s.substring(jcStringTokenizer.getPosition()));
                if (trim.charAt(0) == '!' || trim.length() < 20 || Character.isSpace(trim.charAt(11)) || !trim.regionMatches(true, 0, "param name=", 0, 11)) {
                    continue;
                }
                String substring;
                int n;
                for (substring = trim.substring(11), n = 1; n < substring.length() && !Character.isSpace(substring.charAt(n)); ++n) {}
                int n2;
                for (n2 = n + 1; n2 < substring.length() && Character.isSpace(substring.charAt(n2)); ++n2) {}
                if (n2 == substring.length() || !substring.regionMatches(true, n2, "value=", 0, 6)) {
                    continue;
                }
                String s2 = substring.substring(n2 + 6);
                if (s2.charAt(0) == '\"') {
                    if (s2.length() < 2) {
                        continue;
                    }
                    s2 = s2.substring(1);
                    if (s2.charAt(s2.length() - 1) == '\"') {
                        s2 = s2.substring(0, s2.length() - 1);
                    }
                }
                hashtable.put(substring.substring(0, n).toLowerCase(), s2);
            }
            return;
        }
        if (JCUtilConverter.param_source == null) {
            return;
        }
        if (JCUtilConverter.param_source.containsKey(component)) {
            JCUtilConverter.param_source.remove(component);
        }
    }
    
    public static String getParam(final Applet applet, final Component component, final String s) {
        String s2 = null;
        if (JCUtilConverter.param_source != null) {
            final Hashtable<Object, String> hashtable = JCUtilConverter.param_source.get(component);
            if (hashtable != null) {
                s2 = hashtable.get(s.toLowerCase());
            }
        }
        if (s2 == null && applet != null) {
            try {
                return applet.getParameter(s);
            }
            catch (Exception ex) {
                return null;
            }
        }
        return s2;
    }
    
    public static String getParam(final Applet applet, final Component component, final String s, final String s2) {
        if (JCUtilConverter.param_source != null && JCUtilConverter.param_source.containsKey(component) && s2.equalsIgnoreCase("paramFile")) {
            return null;
        }
        String s3 = null;
        if (s != null) {
            s3 = getParam(applet, component, String.valueOf(String.valueOf(s).concat(String.valueOf("."))).concat(String.valueOf(s2)));
        }
        if (s3 == null) {
            s3 = getParam(applet, component, s2);
        }
        if (s3 != null) {
            int index;
            while ((index = s3.indexOf(10)) != -1) {
                s3 = String.valueOf(s3.substring(0, index)).concat(String.valueOf(s3.substring(index + 1)));
            }
        }
        return s3;
    }
    
    public static void error(final String s, final String s2) {
        error(String.valueOf(String.valueOf(String.valueOf("Error parsing '").concat(String.valueOf(s2))).concat(String.valueOf("' in "))).concat(String.valueOf(s)));
    }
    
    public static void error(final String s) {
        System.err.println(s);
    }
    
    public static String trim(final Object o) {
        final String string;
        if (o == null || (string = o.toString()) == null) {
            return null;
        }
        final String trim = string.trim();
        final int index = trim.indexOf(0);
        if (index != -1) {
            return trim.substring(0, index);
        }
        return trim;
    }
    
    public static int toInt(final String s, final int n) {
        if (s == null) {
            return n;
        }
        if (s.equalsIgnoreCase("maxint")) {
            return Integer.MAX_VALUE;
        }
        if (s.equalsIgnoreCase("novalue")) {
            return -999;
        }
        if (s.equalsIgnoreCase("variable")) {
            return -998;
        }
        if (s.equalsIgnoreCase("default")) {
            return -999;
        }
        try {
            return Integer.parseInt(s);
        }
        catch (Exception ex) {
            error(s);
            return n;
        }
    }
    
    public static double toDouble(final String s, final double n) {
        if (s == null) {
            return n;
        }
        try {
            return Double.valueOf(s);
        }
        catch (Exception ex) {
            error(s);
            return n;
        }
    }
    
    public static boolean toBoolean(final String s, final boolean b) {
        if (s == null) {
            return b;
        }
        try {
            return Boolean.valueOf(s);
        }
        catch (Exception ex) {
            error(s);
            return b;
        }
    }
    
    public static boolean toBoolean(final Applet applet, final Component component, final String s, final String s2, final boolean b) {
        return JCUtilConverter.conv.toBoolean(getParam(applet, component, s, s2), b);
    }
    
    public static String[] toStringList(final String s) {
        if (s == null) {
            return null;
        }
        final JCStringTokenizer jcStringTokenizer = new JCStringTokenizer(s);
        final String[] array = new String[jcStringTokenizer.countTokens(',')];
        int n = 0;
        while (jcStringTokenizer.hasMoreTokens()) {
            array[n] = jcStringTokenizer.nextToken(',').trim();
            ++n;
        }
        return array;
    }
    
    public static int[] toIntList(final String s, final char c) {
        if (s == null) {
            return null;
        }
        final JCStringTokenizer jcStringTokenizer = new JCStringTokenizer(s);
        final int[] array = new int[jcStringTokenizer.countTokens(c)];
        int n = 0;
        while (jcStringTokenizer.hasMoreTokens()) {
            array[n] = JCUtilConverter.conv.toInt(jcStringTokenizer.nextToken(c).trim(), 0);
            ++n;
        }
        return array;
    }
    
    public static int[] toIntList(final String s, final char c, final int[] array) {
        final int[] intList = toIntList(s, c);
        return (intList != null) ? intList : array;
    }
    
    public static String toNewLine(final String s) {
        if (s == null || s.indexOf("\\n") == -1) {
            return s;
        }
        final char[] array = new char[s.length()];
        for (int i = 0, n = 0; i < array.length; ++i, ++n) {
            if (s.charAt(i) == '\\' && i < array.length - 1 && s.charAt(i + 1) == 'n') {
                array[n] = '\n';
                ++i;
            }
            else {
                array[n] = s.charAt(i);
            }
        }
        return new String(array);
    }
    
    public static String fromNewLine(final String s) {
        if (s == null || s.indexOf(10) == -1) {
            return s;
        }
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '\n') {
                ++n;
            }
        }
        final char[] array = new char[s.length() + n];
        int j;
        int n2;
        for (j = 0, n2 = 0; j < s.length(); ++j, ++n2) {
            if (s.charAt(j) == '\n') {
                array[n2++] = '\\';
                array[n2] = 'n';
            }
            else {
                array[n2] = s.charAt(j);
            }
        }
        return new String(array, 0, n2);
    }
    
    public static JCVector toVector(final Component component, final String s, final char c, final boolean b) {
        return ConvertUtil.toVector(component, s, c, b);
    }
    
    public static JCVector toVector(final Component component, final String s, final char c, final boolean b, final JCVector jcVector) {
        final JCVector vector = JCUtilConverter.conv.toVector(component, s, c, b);
        return (vector != null) ? vector : jcVector;
    }
    
    public static JCVector toVectorFromCSV(final Component component, final String s) {
        return ConvertUtil.toVectorFromCSV(component, s);
    }
    
    public static JCVector toVectorFromCSV(final Component component, final String s, final JCVector jcVector) {
        final JCVector vectorFromCSV = toVectorFromCSV(component, s);
        return (vectorFromCSV != null) ? vectorFromCSV : jcVector;
    }
    
    public static Image toImage(final Component component, final String s, final Image image) {
        final Image image2 = JCUtilConverter.conv.toImage(component, s);
        return (image2 != null) ? image2 : image;
    }
    
    public static Image toImage(final Component component, String s) {
        if (s == null) {
            return null;
        }
        Image image = null;
        boolean b = false;
        final Applet applet = (Applet)((component instanceof Applet) ? component : JCString.getApplet(component));
        if (applet != null) {
            URL documentBase = null;
            try {
                documentBase = applet.getDocumentBase();
                b = true;
            }
            catch (Exception ex) {}
            if (b) {
                image = applet.getImage(documentBase, s);
            }
        }
        if (!b) {
            if (s.indexOf(":") == -1) {
                final char char1 = System.getProperty("file.separator").charAt(0);
                String property;
                try {
                    property = System.getProperty("user.dir");
                }
                catch (Exception ex2) {
                    property = "";
                }
                s = String.valueOf(String.valueOf(String.valueOf("file:").concat(String.valueOf(property))).concat(String.valueOf(char1))).concat(String.valueOf(s));
                s = s.replace('/', char1);
                if (char1 != '\\' && System.getProperty("os.name").indexOf("Windows") != -1) {
                    s = s.replace('/', '\\');
                }
            }
            try {
                image = ((component != null) ? component.getToolkit() : Toolkit.getDefaultToolkit()).getImage(new URL(s));
            }
            catch (MalformedURLException ex3) {
                error(String.valueOf("Error loading image ").concat(String.valueOf(s)));
            }
        }
        if (component == null || image == null) {
            return image;
        }
        if (!waitForImage(component, image)) {
            image = null;
        }
        if (image == null) {
            error(String.valueOf("Error loading image ").concat(String.valueOf(s)));
        }
        return image;
    }
    
    public static boolean waitForImage(final Component component, final Image image) {
        final MediaTracker mediaTracker = new MediaTracker(component);
        try {
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
        }
        catch (Exception ex) {
            return false;
        }
        return !mediaTracker.isErrorAny();
    }
    
    public static Image[] toImageList(final Component component, final String s, final Image[] array) {
        if (s == null) {
            return array;
        }
        final JCStringTokenizer jcStringTokenizer = new JCStringTokenizer(s);
        final Image[] array2 = new Image[jcStringTokenizer.countTokens(',')];
        int n = 0;
        while (jcStringTokenizer.hasMoreTokens()) {
            final String trim = jcStringTokenizer.nextToken(',').trim();
            final Image image = (array != null && n < array.length) ? array[n] : null;
            array2[n] = JCUtilConverter.conv.toImage(component, trim, array[n]);
            ++n;
        }
        return array2;
    }
    
    public static Color[] toColorList(final String s, final Color[] array) {
        final Color[] colorList = toColorList(s);
        return (s != null) ? colorList : array;
    }
    
    public static Color[] toColorList(final String s) {
        if (s == null) {
            return null;
        }
        final JCStringTokenizer jcStringTokenizer = new JCStringTokenizer(s);
        final Color[] array = new Color[jcStringTokenizer.countTokens(',')];
        int n = 0;
        while (jcStringTokenizer.hasMoreTokens()) {
            final String trim = jcStringTokenizer.nextToken(',').trim();
            if ((array[n] = JCUtilConverter.conv.toColor(trim)) == null) {
                error(s, trim);
            }
            ++n;
        }
        return array;
    }
    
    public static Color toColor(final String s, final Color color) {
        final Color color2 = toColor(s);
        return (s != null) ? color2 : color;
    }
    
    public static Color toColor(final String s) {
        if (s == null) {
            return null;
        }
        final JCStringTokenizer jcStringTokenizer = new JCStringTokenizer(s);
        final String nextToken = jcStringTokenizer.nextToken('-');
        if (nextToken == null) {
            return null;
        }
        final String trim = nextToken.trim();
        if (trim.equalsIgnoreCase("black")) {
            return Color.black;
        }
        if (trim.equalsIgnoreCase("blue")) {
            return Color.blue;
        }
        if (trim.equalsIgnoreCase("cyan")) {
            return Color.cyan;
        }
        if (trim.equalsIgnoreCase("darkGray")) {
            return Color.darkGray;
        }
        if (trim.equalsIgnoreCase("darkGrey")) {
            return Color.darkGray;
        }
        if (trim.equalsIgnoreCase("gray")) {
            return Color.gray;
        }
        if (trim.equalsIgnoreCase("grey")) {
            return Color.gray;
        }
        if (trim.equalsIgnoreCase("green")) {
            return Color.green;
        }
        if (trim.equalsIgnoreCase("lightGray")) {
            return Color.lightGray;
        }
        if (trim.equalsIgnoreCase("lightGrey")) {
            return Color.lightGray;
        }
        if (trim.equalsIgnoreCase("lightBlue")) {
            return new Color(173, 216, 230);
        }
        if (trim.equalsIgnoreCase("magenta")) {
            return Color.magenta;
        }
        if (trim.equalsIgnoreCase("orange")) {
            return Color.orange;
        }
        if (trim.equalsIgnoreCase("pink")) {
            return Color.pink;
        }
        if (trim.equalsIgnoreCase("red")) {
            return Color.red;
        }
        if (trim.equalsIgnoreCase("white")) {
            return Color.white;
        }
        if (trim.equalsIgnoreCase("yellow")) {
            return Color.yellow;
        }
        try {
            if (!trim.startsWith("#")) {
                return new Color(Integer.parseInt(trim), Integer.parseInt(jcStringTokenizer.nextToken('-')), Integer.parseInt(jcStringTokenizer.nextToken('-')));
            }
            if (trim.length() >= 13) {
                return new Color(Integer.valueOf(trim.substring(1, 5), 16) / 256, Integer.valueOf(trim.substring(5, 9), 16) / 256, Integer.valueOf(trim.substring(9, 13), 16) / 256);
            }
            if (trim.length() >= 7) {
                return new Color(Integer.valueOf(trim.substring(1, 3), 16), Integer.valueOf(trim.substring(3, 5), 16), Integer.valueOf(trim.substring(5, 7), 16));
            }
            return new Color(Integer.valueOf(trim.substring(1), 16));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static Font toFont(final String s, final Font font) {
        final Font font2 = JCUtilConverter.conv.toFont(s);
        return (font2 != null) ? font2 : font;
    }
    
    public static Font toFont(final String s) {
        if (s == null) {
            return null;
        }
        int n = 0;
        final JCStringTokenizer jcStringTokenizer = new JCStringTokenizer(s);
        if (jcStringTokenizer.countTokens('-') != 3) {
            return null;
        }
        final String nextToken = jcStringTokenizer.nextToken('-');
        if (nextToken == null) {
            return null;
        }
        final String trim = nextToken.trim();
        final String nextToken2 = jcStringTokenizer.nextToken('-');
        if (nextToken2 == null) {
            n = 0;
        }
        else {
            final String trim2 = nextToken2.trim();
            if (trim2.equalsIgnoreCase("PLAIN")) {
                n = 0;
            }
            if (trim2.equalsIgnoreCase("ITALIC")) {
                n = 2;
            }
            if (trim2.equalsIgnoreCase("BOLD")) {
                n = 1;
            }
        }
        final String nextToken3 = jcStringTokenizer.nextToken('-');
        int int1 = 8;
        if (nextToken3 != null) {
            int1 = Integer.parseInt(nextToken3.trim());
        }
        return new Font(trim, n, int1);
    }
    
    public static int toEnum(String trim, final String s, String s2, final String[][] array, final int[][] array2, final int n) {
        if (s2 == null) {
            s2 = s;
        }
        int n2;
        for (n2 = 0; n2 < array.length && !array[n2][0].equalsIgnoreCase(s); ++n2) {}
        if (n2 == array.length) {
            error(String.valueOf(String.valueOf(String.valueOf("Error converting '").concat(String.valueOf(trim))).concat(String.valueOf("' to "))).concat(String.valueOf(s2)));
            return n;
        }
        trim = trim.trim();
        for (int i = 1; i < array[n2].length; ++i) {
            if (trim.equalsIgnoreCase(array[n2][i])) {
                return array2[n2][i - 1];
            }
        }
        try {
            return Integer.parseInt(trim);
        }
        catch (Exception ex) {
            error(String.valueOf(String.valueOf(String.valueOf("Error converting '").concat(String.valueOf(trim))).concat(String.valueOf("' to "))).concat(String.valueOf(s2)));
            return n;
        }
    }
    
    public static int toEnum(String trim, final String s, final String[] array, final int[] array2, final int n) {
        if (trim == null) {
            return n;
        }
        trim = trim.trim();
        for (int i = 0; i < array.length; ++i) {
            if (trim.equalsIgnoreCase(array[i])) {
                return array2[i];
            }
        }
        try {
            return Integer.parseInt(trim);
        }
        catch (Exception ex) {
            error(String.valueOf(String.valueOf(String.valueOf("Error converting '").concat(String.valueOf(trim))).concat(String.valueOf("' to "))).concat(String.valueOf(s)));
            return n;
        }
    }
    
    public static long toEnum(String trim, final String s, final String[] array, final long[] array2, final long n) {
        if (trim == null) {
            return n;
        }
        trim = trim.trim();
        for (int i = 0; i < array.length; ++i) {
            if (trim.equalsIgnoreCase(array[i])) {
                return array2[i];
            }
        }
        try {
            return Long.parseLong(trim);
        }
        catch (Exception ex) {
            error(String.valueOf(String.valueOf(String.valueOf("Error converting '").concat(String.valueOf(trim))).concat(String.valueOf("' to "))).concat(String.valueOf(s)));
            return n;
        }
    }
    
    public static int toEnum(String trim, final String[] array, final int[] array2, final int n) {
        if (trim == null) {
            return n;
        }
        trim = trim.trim();
        for (int i = 0; i < array.length; ++i) {
            if (trim.equalsIgnoreCase(array[i])) {
                return array2[i];
            }
        }
        return n;
    }
    
    public static int[] toEnumList(final String s, final String s2, final String[] array, final int[] array2, final int[] array3) {
        if (s == null) {
            return array3;
        }
        final JCStringTokenizer jcStringTokenizer = new JCStringTokenizer(s);
        final int[] array4 = new int[jcStringTokenizer.countTokens(',')];
        int n = 0;
        while (jcStringTokenizer.hasMoreTokens()) {
            array4[n] = JCUtilConverter.conv.toEnum(jcStringTokenizer.nextToken(',').trim(), s2, array, array2, (array3 != null && n < array3.length) ? array3[n] : 0);
            ++n;
        }
        return array4;
    }
    
    public static String fromEnum(final int n, final String[] array, final int[] array2) {
        if (array2 == null || array == null) {
            return null;
        }
        for (int i = 0; i < array2.length; ++i) {
            if (n == array2[i] && i < array.length) {
                return array[i];
            }
        }
        return null;
    }
    
    public static String fromEnum(final int n, final String s, final String s2, final String[][] array, final int[][] array2, final String s3) {
        int n2;
        for (n2 = 0; n2 < array.length && !array[n2][0].equalsIgnoreCase(s2); ++n2) {}
        if (n2 == array.length) {
            return null;
        }
        for (int i = 0; i < array2[n2].length; ++i) {
            if (array2[n2][i] == n) {
                return array[n2][i + 1];
            }
        }
        try {
            return String.valueOf(n);
        }
        catch (Exception ex) {
            error(String.valueOf(String.valueOf(String.valueOf("Error converting '").concat(String.valueOf(n))).concat(String.valueOf("' to "))).concat(String.valueOf(s)));
            return s3;
        }
    }
    
    public static void checkEnum(final int n, final String s, final int[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == n) {
                return;
            }
        }
        throw new IllegalArgumentException(String.valueOf(String.valueOf(String.valueOf("invalid ").concat(String.valueOf(s))).concat(String.valueOf(": "))).concat(String.valueOf(n)));
    }
    
    public static Insets toInsets(final String s, final Insets insets) {
        final int[] intList = toIntList(s, ',', null);
        if (intList != null && intList.length == 4) {
            return new Insets(intList[0], intList[1], intList[2], intList[3]);
        }
        return insets;
    }
    
    public static Dimension toDimension(final String s, final Dimension dimension) {
        final int[] intList = toIntList(s, 'x', null);
        if (intList != null && intList.length == 2) {
            return new Dimension(intList[0], intList[1]);
        }
        return dimension;
    }
    
    public static Point toPoint(final String s, final Point point) {
        final int[] intList = toIntList(s, ',', null);
        if (intList != null && intList.length == 2) {
            return new Point(intList[0], intList[1]);
        }
        return point;
    }
    
    public static String toString(final Object o) {
        if (!(o instanceof Vector)) {
            return fromNewLine((o != null) ? o.toString() : "");
        }
        final Vector vector = (Vector)o;
        final StringBuffer sb = new StringBuffer(vector.size());
        for (int i = 0, size = vector.size(); i < size; ++i) {
            sb.append(toString(vector.elementAt(i)));
            if (i < size - 1) {
                sb.append(',');
            }
        }
        return new String(sb);
    }
    
    public static Date toDate(final String s, final Date date) {
        if (s == null) {
            return date;
        }
        try {
            return new Date(s);
        }
        catch (Exception ex) {
            return date;
        }
    }
    
    public static String removeEscape(final String s) {
        if (s == null || s.indexOf(92) == -1) {
            return s;
        }
        final int length = s.length();
        final char[] array = new char[length];
        int i;
        int n;
        for (i = 0, n = 0; i < length; ++i, ++n) {
            final char char1 = s.charAt(i);
            if (i + 1 < length && char1 == '\\') {
                ++i;
                if (s.charAt(i) == 'n') {
                    array[n] = '\n';
                }
                else {
                    array[n] = s.charAt(i);
                }
            }
            else {
                array[n] = char1;
            }
        }
        return (n > 0) ? new String(array, 0, n) : null;
    }
    
    static {
        JCUtilConverter.conv = new JCConverter();
    }
}
