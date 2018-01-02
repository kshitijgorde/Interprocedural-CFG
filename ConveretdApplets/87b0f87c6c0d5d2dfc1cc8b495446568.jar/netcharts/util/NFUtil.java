// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.net.URLEncoder;
import java.util.Enumeration;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.Container;
import java.awt.Graphics;
import java.io.File;
import netcharts.util.zip.ZipURLConnection;
import java.applet.Applet;
import java.net.URL;
import java.awt.Dialog;
import java.awt.Component;
import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Color;
import java.awt.Frame;
import java.util.Hashtable;

public class NFUtil implements NFCompare
{
    private static Hashtable a;
    private static double b;
    private static String c;
    public static final char DECIMAL_SYMBOL_DEFAULT = '.';
    public static final char GROUP_SYMBOL_DEFAULT = ',';
    public static final int GROUP_SIZE_DEFAULT = 3;
    private static Object d;
    private static Frame[] e;
    private static int[] f;
    private static int[] g;
    private static final int h = 1;
    private static final int i = 2;
    private static final int j = 3;
    private static final int k = 4;
    private static final int l = 5;
    private static final int m = 6;
    protected static Color blackEvalColor;
    protected static Color whiteEvalColor;
    
    public static double getJDKVersion() {
        if (NFUtil.b > 0.0) {
            return NFUtil.b;
        }
        double jdkVersion;
        try {
            jdkVersion = getJDKVersion(System.getProperty("java.version", "1.0"));
        }
        catch (Exception ex) {
            jdkVersion = 1.0;
        }
        return NFUtil.b = jdkVersion;
    }
    
    protected static double getJDKVersion(final String s) {
        final double n = 1.0;
        try {
            int n2;
            for (n2 = 0; n2 < s.length() && !Character.isDigit(s.charAt(n2)); ++n2) {}
            if (n2 >= s.length()) {
                return n;
            }
            final int n3 = n2;
            while (n2 < s.length() && Character.isDigit(s.charAt(n2))) {
                ++n2;
            }
            if (n2 >= s.length() || s.charAt(n2) != '.') {
                return n;
            }
            ++n2;
            while (n2 < s.length() && Character.isDigit(s.charAt(n2))) {
                ++n2;
            }
            return Double.valueOf(s.substring(n3, n2));
        }
        catch (Exception ex) {
            return 1.0;
        }
    }
    
    public static synchronized Font getFont(final String s, final int n, final int n2) {
        if (NFUtil.a == null) {
            NFUtil.a = new Hashtable();
        }
        final String string = s + "." + n + "." + n2;
        Font font = NFUtil.a.get(string);
        if (font == null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
            if (stringTokenizer == null || stringTokenizer.countTokens() < 1) {
                return new Font("TimesRoman", 1, 16);
            }
            String s2 = stringTokenizer.nextToken();
            boolean b = false;
            boolean b2 = false;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.toLowerCase().equals("italic")) {
                    b = true;
                }
                else {
                    if (nextToken.toLowerCase().equals("bold")) {
                        continue;
                    }
                    if (nextToken.toLowerCase().equals("plain")) {
                        b2 = true;
                    }
                    else {
                        s2 = s2 + " " + nextToken;
                    }
                }
            }
            int n3 = 0;
            if (!b2) {
                n3 |= 0x1;
            }
            if (b) {
                n3 |= 0x2;
            }
            font = new Font(s2, n3, n2);
            NFUtil.a.put(string, font);
        }
        return font;
    }
    
    private static void a(final Vector vector, final int n, final int n2) {
        final Object element = vector.elementAt(n);
        vector.setElementAt(vector.elementAt(n2), n);
        vector.setElementAt(element, n2);
    }
    
    public int compare(final Object o, final Object o2) {
        if (o == null || o2 == null || !(o instanceof Number) || !(o2 instanceof Number)) {
            return o.toString().compareTo(o2.toString());
        }
        final double doubleValue = ((Number)o).doubleValue();
        final double doubleValue2 = ((Number)o2).doubleValue();
        if (doubleValue < doubleValue2) {
            return -1;
        }
        if (doubleValue > doubleValue2) {
            return 1;
        }
        return 0;
    }
    
    public static void qsort(final Vector vector, int n, final int n2, NFCompare nfCompare) {
        if (nfCompare == null) {
            nfCompare = new NFUtil();
        }
        if (n >= n2) {
            return;
        }
        a(vector, n, (n + n2) / 2);
        for (int i = n + 1; i <= n2; ++i) {
            if (nfCompare.compare(vector.elementAt(i), vector.elementAt(n)) < 0) {
                a(vector, ++n, i);
            }
        }
        a(vector, n, n);
        qsort(vector, n, n - 1, nfCompare);
        qsort(vector, n + 1, n2, nfCompare);
    }
    
    public static Component getWindow(final Component component) {
        if (component == null) {
            return null;
        }
        if (component instanceof Frame || component instanceof Dialog) {
            return component;
        }
        return getWindow(component.getParent());
    }
    
    public static Frame getFrame(final Component component) {
        if (component == null) {
            return null;
        }
        if (component instanceof Frame) {
            return (Frame)component;
        }
        return getFrame(component.getParent());
    }
    
    public static Component getTopLevelComponent(final Component component) {
        if (component == null) {
            return null;
        }
        if (component.getParent() == null) {
            return component;
        }
        return getTopLevelComponent(component.getParent());
    }
    
    public static void busyCursor(final Component component, final boolean b) {
        final Frame frame = getFrame(component);
        if (frame == null) {
            return;
        }
        synchronized (NFUtil.d) {
            int n;
            for (n = 0; n < 100 && NFUtil.e[n] != null && NFUtil.e[n] != frame; ++n) {}
            if (n == 100) {
                return;
            }
            if (NFUtil.e[n] == null) {
                NFUtil.e[n] = frame;
            }
            if (b) {
                if (NFUtil.f[n] == 0) {
                    NFUtil.g[n] = NFUtil.e[n].getCursorType();
                }
                final int[] f = NFUtil.f;
                final int n2 = n;
                ++f[n2];
                final Frame frame2 = NFUtil.e[n];
                final Frame[] e = NFUtil.e;
                frame2.setCursor(3);
            }
            else {
                final int[] f2 = NFUtil.f;
                final int n3 = n;
                --f2[n3];
                if (NFUtil.f[n] <= 0) {
                    NFUtil.e[n].setCursor(NFUtil.g[n]);
                    NFUtil.f[n] = 0;
                    NFUtil.e[n] = null;
                }
            }
        }
        frame.repaint();
    }
    
    private static String a(final String s, final String s2) {
        if (s2.charAt(0) == '/') {
            return s.replace('\\', '/');
        }
        return s.replace('/', '\\');
    }
    
    private static String a(final String s) {
        return s.replace('\\', '/');
    }
    
    public static final URL getFileURL(final String s, final NFContext nfContext) throws Exception {
        if (nfContext == null || nfContext.getDocumentBase() == null) {
            return getFileURL(s);
        }
        return getFileURL(s, nfContext.getDocumentBase().toExternalForm());
    }
    
    public static final URL getFileURL(String a, final Applet applet, final String s) throws Exception {
        a = a(a);
        URL fileURL;
        try {
            String s2 = applet.getDocumentBase().toString();
            final int lastIndex = s2.lastIndexOf(63);
            if (lastIndex != -1) {
                s2 = s2.substring(0, lastIndex);
            }
            if (s2.endsWith(".chart")) {
                s2 += "/";
            }
            final URL url = new URL(s2);
            debug("$DOCBASE = <" + url + ">");
            fileURL = new URL(url, a);
        }
        catch (Exception ex) {
            try {
                fileURL = getFileURL(a, s);
            }
            catch (Exception ex2) {
                throw new Exception("NFUtil: Unable to open file <" + a + ">");
            }
        }
        return fileURL;
    }
    
    public static final URL getFileURL(final String s) throws Exception {
        return getFileURL(s, (String)null);
    }
    
    public static final URL getFileURL(String a, final String s) throws Exception {
        a = a(a);
        if (a.indexOf(58) <= 2) {
            final String s2 = "/";
            String s3;
            if (a.length() > 1 && a.charAt(1) == ':') {
                s3 = "file:" + a;
                debug("Absolute Path = " + s3);
            }
            else {
                if (s != null) {
                    return getFileURL(a, new URL(s));
                }
                if (a.charAt(0) == s2.charAt(0)) {
                    s3 = "file:" + a;
                    debug("Absolute Path = " + s3);
                }
                else {
                    s3 = "file:" + a(System.getProperty("user.dir")) + s2 + a;
                    debug("UserDir Path = " + s3);
                }
            }
            URL url;
            if (isZipFile(s3)) {
                if (s3.startsWith("zip:")) {
                    url = new URL("zip", "", s3.substring(4));
                }
                else {
                    url = new URL("zip", "", s3);
                }
            }
            else {
                url = new URL(s3);
            }
            return url;
        }
        debug("Full URL Path = " + a);
        if (!isZipFile(a)) {
            return new URL(a);
        }
        if (a.startsWith("zip:")) {
            return new URL("zip", "", a.substring(4));
        }
        return new URL("zip", "", a);
    }
    
    public static final URL getFileURL(final String s, final URL url) throws Exception {
        if (s.indexOf(58) > 2) {
            debug("Full URL Path = " + s);
            return new URL(s);
        }
        if (url == null) {
            return getFileURL(s);
        }
        return new URL(url, s);
    }
    
    public static void debug(final String s) {
        NFDebug.print(64L, "NFUtil: " + s);
    }
    
    public static String resolvePath(final String s, final NFContext nfContext) {
        if (nfContext == null) {
            return resolvePath(s, (Applet)null);
        }
        return resolvePath(s, nfContext.getDocumentBase(), nfContext.getCodeBase());
    }
    
    public static String resolvePath(final String s, final Applet applet) {
        URL documentBase = null;
        URL codeBase = null;
        try {
            documentBase = applet.getDocumentBase();
        }
        catch (Exception ex) {}
        try {
            codeBase = applet.getCodeBase();
        }
        catch (Exception ex2) {}
        return resolvePath(s, documentBase, codeBase);
    }
    
    public static String resolvePath(String a, final URL url, final URL url2) {
        if (a == null || a.length() == 0 || a.charAt(0) != '$') {
            return a;
        }
        a = a(a);
        if (a.startsWith("$DOCBASE")) {
            String s = ".";
            if (url != null) {
                s = url.toString();
                final int lastIndex = s.lastIndexOf("/");
                if (lastIndex != -1) {
                    s = s.substring(0, lastIndex);
                }
            }
            debug("$DOCBASE = <" + s + ">");
            return s + a.substring(8);
        }
        String c = null;
        if (url2 != null) {
            c = url2.toString();
        }
        if (c == null && NFUtil.c != null) {
            c = NFUtil.c;
        }
        if (c == null) {
            try {
                String chasePath = chasePath(System.getProperty("java.class.path"), "netcharts/");
                if (chasePath == null) {
                    chasePath = ".";
                }
                c = getFileURL(chasePath).toString() + "/";
                if (NFUtil.c == null) {
                    NFUtil.c = c;
                }
            }
            catch (Exception ex) {
                c = null;
            }
        }
        if (c == null) {
            return a;
        }
        final String substring = c.substring(c.length() - 1);
        final String substring2 = c.substring(0, c.length() - 1);
        if (a.startsWith("$CODEBASE")) {
            debug("$CODEBASE = <" + substring2 + ">");
            return substring2 + a.substring(9);
        }
        if (a.startsWith("$NETCHARTS")) {
            final String string = substring2 + substring + "netcharts";
            debug("$NETCHARTS = <" + string + ">");
            return string + a.substring(10);
        }
        if (a.startsWith("$IMAGES")) {
            final String string2 = substring2 + substring + "netcharts" + substring + "images";
            debug("$IMAGES = <" + string2 + ">");
            return string2 + a.substring(7);
        }
        if (a.startsWith("$SYMBOLS")) {
            final String string3 = substring2 + substring + "netcharts" + substring + "symbols";
            debug("$SYMBOLS = <" + string3 + ">");
            return string3 + a.substring(8);
        }
        if (a.startsWith("$PATTERNS")) {
            final String string4 = substring2 + substring + "netcharts" + substring + "patterns";
            debug("$PATTERNS = <" + string4 + ">");
            return string4 + a.substring(9);
        }
        if (a.startsWith("$ICONS")) {
            final String string5 = substring2 + substring + "netcharts" + substring + "icons";
            debug("$ICONS = <" + string5 + ">");
            return string5 + a.substring(6);
        }
        return a;
    }
    
    public static String chasePath(final String s, final String s2) {
        final String property = System.getProperty("path.separator");
        final String property2 = System.getProperty("file.separator");
        final StringTokenizer stringTokenizer = new StringTokenizer(s, property);
        debug("chasePath = <" + s + ">");
        debug("pathsep = <" + property + ">");
        debug("filesep = <" + property2 + ">");
        while (stringTokenizer.hasMoreTokens()) {
            final String a = a(stringTokenizer.nextToken(), property2);
            debug("dir = <" + a + ">");
            if ((a.endsWith(".jar") || a.endsWith(".zip")) && isZipDirEntry(a + "!/", s2)) {
                try {
                    return ((ZipURLConnection)getFileURL(a + "!/").openConnection()).getZipFileURL().getFile() + "!";
                }
                catch (Exception ex) {
                    NFDebug.print("NFUtil.chasePath: exception: " + ex);
                    return null;
                }
            }
            try {
                if (new File(a, s2).exists()) {
                    return a;
                }
                continue;
            }
            catch (Exception ex2) {
                NFDebug.print(ex2.getMessage());
            }
        }
        return null;
    }
    
    public static double rint(final double n) {
        if (n < 0.0) {
            return (int)(n - 0.5);
        }
        return (int)(n + 0.5);
    }
    
    public static String sprintf(final String s) {
        return sprintf(3, ',', '.', s);
    }
    
    public static String sprintf(final int n, final char c, final char c2, final String s) {
        return sprintf(n, c, c2, s, null);
    }
    
    public static String sprintf(final String s, final Object o) {
        return sprintf(3, ',', '.', s, o);
    }
    
    public static String sprintf(final int n, final char c, final char c2, final String s, final Object o) {
        return sprintf(n, c, c2, s, new Object[] { o });
    }
    
    public static String sprintf(final String s, final Object o, final Object o2) {
        return sprintf(3, ',', '.', s, o, o2);
    }
    
    public static String sprintf(final int n, final char c, final char c2, final String s, final Object o, final Object o2) {
        return sprintf(n, c, c2, s, new Object[] { o, o2 });
    }
    
    public static String sprintf(final String s, final Object o, final Object o2, final Object o3) {
        return sprintf(3, ',', '.', s, o, o2, o3);
    }
    
    public static String sprintf(final int n, final char c, final char c2, final String s, final Object o, final Object o2, final Object o3) {
        return sprintf(n, c, c2, s, new Object[] { o, o2, o3 });
    }
    
    public static String sprintf(final String s, final Object o, final Object o2, final Object o3, final Object o4) {
        return sprintf(3, ',', '.', s, o, o2, o3, o4);
    }
    
    public static String sprintf(final int n, final char c, final char c2, final String s, final Object o, final Object o2, final Object o3, final Object o4) {
        return sprintf(n, c, c2, s, new Object[] { o, o2, o3, o4 });
    }
    
    public static String sprintf(final String s, final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
        return sprintf(3, ',', '.', s, o, o2, o3, o4, o5);
    }
    
    public static String sprintf(final int n, final char c, final char c2, final String s, final Object o, final Object o2, final Object o3, final Object o4, final Object o5) {
        return sprintf(n, c, c2, s, new Object[] { o, o2, o3, o4, o5 });
    }
    
    public static String sprintf(final int n, final char c, final char c2, final String s, final Object[] array) {
        return sprintf(n, c, c2, s, array, true);
    }
    
    public static String sprintf(final int n, final char c, final char c2, final String s, final Object[] array, final boolean b) {
        final StringBuffer sb = new StringBuffer("");
        final StringBuffer sb2 = new StringBuffer("");
        int n2 = 0;
        final int length = s.length();
        int i = 0;
        while (i < length) {
            if (s.charAt(i) != '%' || array == null || n2 >= array.length) {
                if (b) {
                    sb.append(s.charAt(i));
                }
                ++i;
            }
            else if (i < length - 1 && s.charAt(i + 1) == '%') {
                sb.append('%');
                i += 2;
            }
            else {
                int n3 = 0;
                int length2 = -1;
                boolean b2 = false;
                boolean b3 = false;
                int n4 = 1;
                boolean b4 = false;
                int n5 = i + 1;
                while (n5 < length && n4 != 5) {
                    final char char1 = s.charAt(n5);
                    switch (n4) {
                        case 3: {
                            if (Character.isDigit(char1)) {
                                n3 = 10 * n3 + Character.digit(char1, 10);
                                ++n5;
                                continue;
                            }
                            if (char1 == c2) {
                                n4 = 4;
                                ++n5;
                                continue;
                            }
                            n4 = 4;
                            continue;
                        }
                        case 4: {
                            if (Character.isDigit(char1)) {
                                if (length2 < 0) {
                                    length2 = 0;
                                }
                                length2 = 10 * length2 + Character.digit(char1, 10);
                                ++n5;
                                continue;
                            }
                            n4 = 5;
                            continue;
                        }
                        default: {
                            continue;
                        }
                        case 1: {
                            if (char1 == '-') {
                                b2 = true;
                                ++n5;
                            }
                            n4 = 6;
                            continue;
                        }
                        case 6: {
                            if (char1 == c) {
                                b4 = true;
                                ++n5;
                            }
                            n4 = 2;
                            continue;
                        }
                        case 2: {
                            if (char1 == '0') {
                                b3 = true;
                                ++n5;
                            }
                            n4 = 3;
                            continue;
                        }
                    }
                }
                if (n4 != 5 || n5 == length) {
                    sb.append('%');
                    ++i;
                }
                else {
                    sb2.setLength(0);
                    switch (s.charAt(n5)) {
                        case 's': {
                            sb2.append(array[n2]);
                            ++n2;
                            i = n5 + 1;
                            if (b2) {
                                b(sb2, n3, ' ');
                            }
                            else {
                                a(sb2, n3, ' ');
                            }
                            if (length2 > -1 && sb2.length() > length2) {
                                sb2.setLength(length2);
                                break;
                            }
                            break;
                        }
                        case 'd': {
                            if (!(array[n2] instanceof Number)) {
                                sb.append('%');
                                ++i;
                                continue;
                            }
                            final Number n6 = (Number)array[n2];
                            if (!b3 && b4) {
                                sb2.append(a(n, c, c2, String.valueOf(n6.longValue())));
                            }
                            else {
                                sb2.append(n6.longValue());
                            }
                            ++n2;
                            i = n5 + 1;
                            if (b3) {
                                a(sb2, n3, '0');
                                break;
                            }
                            if (b2) {
                                b(sb2, n3, ' ');
                                break;
                            }
                            a(sb2, n3, ' ');
                            break;
                        }
                        case 'x': {
                            if (!(array[n2] instanceof Number)) {
                                sb.append('%');
                                ++i;
                                continue;
                            }
                            final String string = Long.toString(((Number)array[n2]).longValue(), 16);
                            if (!b3 && b4) {
                                sb2.append(a(n, c, c2, string));
                            }
                            else {
                                sb2.append(string);
                            }
                            ++n2;
                            i = n5 + 1;
                            if (b3) {
                                a(sb2, n3, '0');
                                break;
                            }
                            if (b2) {
                                b(sb2, n3, ' ');
                                break;
                            }
                            a(sb2, n3, ' ');
                            break;
                        }
                        case 'g': {
                            if (!(array[n2] instanceof Number)) {
                                sb.append('%');
                                ++i;
                                continue;
                            }
                            String s2 = ((Number)array[n2]).toString();
                            if (s2.endsWith(".0")) {
                                s2 = s2.substring(0, s2.length() - 2);
                            }
                            if (c2 != '.') {
                                s2 = s2.replace('.', c2);
                            }
                            if (!b3 && b4) {
                                sb2.append(a(n, c, c2, s2));
                            }
                            else {
                                sb2.append(s2);
                            }
                            ++n2;
                            i = n5 + 1;
                            if (b3) {
                                a(sb2, n3, '0');
                                break;
                            }
                            if (b2) {
                                b(sb2, n3, ' ');
                                break;
                            }
                            a(sb2, n3, ' ');
                            break;
                        }
                        case 'f': {
                            if (!(array[n2] instanceof Number)) {
                                sb.append('%');
                                ++i;
                                continue;
                            }
                            final Number n7 = (Number)array[n2];
                            if (length2 < 0) {
                                length2 = 2;
                            }
                            double doubleValue = n7.doubleValue();
                            if (length2 == 0) {
                                final long n8 = (long)(doubleValue + 0.5);
                                if (!b3 && b4) {
                                    sb2.append(a(n, c, c2, String.valueOf(n8)));
                                }
                                else {
                                    sb2.append(n8);
                                }
                            }
                            else {
                                boolean b5 = false;
                                if (doubleValue < 0.0) {
                                    doubleValue = -doubleValue;
                                    b5 = true;
                                }
                                long n9 = (long)doubleValue;
                                final double n10 = doubleValue - n9;
                                double n11 = 1.0;
                                for (int j = 0; j < length2; ++j) {
                                    n11 *= 10.0;
                                }
                                double floor = Math.floor(n10 * n11 + 0.5);
                                if (floor >= n11) {
                                    floor -= n11;
                                    ++n9;
                                }
                                sb2.append((long)floor);
                                a(sb2, length2, '0');
                                sb2.insert(0, c2);
                                if (!b3 && b4) {
                                    sb2.insert(0, a(n, c, c2, String.valueOf(n9)));
                                }
                                else {
                                    sb2.insert(0, n9);
                                }
                                if (b5) {
                                    sb2.insert(0, '-');
                                }
                            }
                            ++n2;
                            i = n5 + 1;
                            if (b3) {
                                a(sb2, n3, '0');
                                break;
                            }
                            if (b2) {
                                b(sb2, n3, ' ');
                                break;
                            }
                            a(sb2, n3, ' ');
                            break;
                        }
                        default: {
                            sb.append('%');
                            ++i;
                            continue;
                        }
                    }
                    sb.append((Object)sb2);
                }
            }
        }
        return sb.toString();
    }
    
    private static String a(final int n, final char c, final char c2, final String s) {
        final StringBuffer sb = new StringBuffer();
        if (s.indexOf("e+") != -1 || s.indexOf("e-") != -1 || s.indexOf("E+") != -1 || s.indexOf("E-") != -1) {
            return s;
        }
        sb.setLength(0);
        final int length = s.length();
        final int index = s.indexOf(c2);
        int i = length - 1;
        if (index != -1) {
            while (i >= index) {
                sb.insert(0, s.charAt(i));
                --i;
            }
        }
        int n2 = 0;
        while (i >= 0) {
            final char char1 = s.charAt(i);
            if (char1 == '-') {
                n2 = 0;
                sb.insert(0, '-');
                --i;
            }
            else {
                if (n2 == n) {
                    sb.insert(0, c);
                    n2 = 0;
                }
                sb.insert(0, char1);
                ++n2;
                --i;
            }
        }
        return sb.toString();
    }
    
    private static void a(final StringBuffer sb, final int n, final char c) {
        if (sb.length() < 1) {
            return;
        }
        for (int i = sb.length(); i < n; ++i) {
            if (sb.charAt(0) == '-' && c == '0') {
                sb.insert(1, c);
            }
            else {
                sb.insert(0, c);
            }
        }
    }
    
    private static void b(final StringBuffer sb, final int n, final char c) {
        for (int i = sb.length(); i < n; ++i) {
            sb.append(c);
        }
    }
    
    public static void compPaint(final Graphics graphics, final Component component) {
        if (!(component instanceof Container)) {
            component.paint(graphics);
            return;
        }
        final int countComponents = ((Container)component).countComponents();
        component.paint(graphics);
        for (int i = 0; i < countComponents; ++i) {
            final Component component2 = ((Container)component).getComponent(i);
            if (component2.isVisible()) {
                final Point location = component2.location();
                graphics.translate(location.x, location.y);
                compPaint(graphics, component2);
                graphics.translate(-location.x, -location.y);
            }
        }
    }
    
    public static void compPrint(final Graphics graphics, final Component component) {
        if (!(component instanceof Container)) {
            component.print(graphics);
            return;
        }
        final int countComponents = ((Container)component).countComponents();
        component.print(graphics);
        for (int i = 0; i < countComponents; ++i) {
            final Component component2 = ((Container)component).getComponent(i);
            if (component2.isVisible()) {
                final Point location = component2.location();
                graphics.translate(location.x, location.y);
                compPrint(graphics, component2);
                graphics.translate(-location.x, -location.y);
            }
        }
    }
    
    public static void setFrameIcon(final Frame frame, final URL url) {
        final Image image = Toolkit.getDefaultToolkit().getImage(url);
        final MediaTracker mediaTracker = new MediaTracker(frame);
        mediaTracker.addImage(image, 1);
        try {
            mediaTracker.waitForID(1);
            if (!mediaTracker.isErrorID(1)) {
                frame.setIconImage(image);
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public static void centerWindow(final Component component, final Component component2) {
        Rectangle bounds;
        if (component != null) {
            bounds = component.bounds();
            if (getJDKVersion() > 1.0) {
                final Point locationOnScreen = component.getLocationOnScreen();
                bounds.x = locationOnScreen.x;
                bounds.y = locationOnScreen.y;
            }
        }
        else {
            bounds = new Rectangle();
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            bounds.x = 0;
            bounds.y = 0;
            bounds.width = screenSize.width;
            bounds.height = screenSize.height;
        }
        final Dimension size = component2.size();
        int n = bounds.x + bounds.width / 2 - size.width / 2;
        int n2 = bounds.y + bounds.height / 2 - size.height / 2;
        if (n < 0) {
            n = 0;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        component2.move(n, n2);
    }
    
    public static void showEval(final Graphics graphics, final Rectangle rectangle) {
        graphics.setFont(getFont("Helvetica", 0, 10));
        if (NFUtil.blackEvalColor == null) {
            NFUtil.blackEvalColor = NFColor.get("black_128");
        }
        if (NFUtil.whiteEvalColor == null) {
            NFUtil.whiteEvalColor = NFColor.get("white_128");
        }
        final String string = NFContext.getUserAgent() + " - Evaluation Copy - www.visualmining.com";
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int n = rectangle.x + (rectangle.width - fontMetrics.stringWidth(string)) / 2;
        if (!Color.white.equals(NFUtil.whiteEvalColor)) {
            graphics.setColor(NFUtil.whiteEvalColor);
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.y + fontMetrics.getAscent() + fontMetrics.getDescent());
            graphics.fillRect(rectangle.x, rectangle.y + rectangle.height - (fontMetrics.getAscent() + fontMetrics.getDescent()), rectangle.width, fontMetrics.getAscent() + fontMetrics.getDescent());
        }
        if (getJDKVersion() >= 1.2 && !NF12Util.isAntiAliased(graphics)) {
            graphics.setColor(Color.black);
        }
        else {
            graphics.setColor(NFUtil.blackEvalColor);
        }
        graphics.drawString(string, n, rectangle.y + fontMetrics.getAscent());
        graphics.drawString(string, n, rectangle.y + rectangle.height - fontMetrics.getDescent());
    }
    
    public static Hashtable getZipFileEntryList(final File file) {
        return getZipFileEntryList(file.getAbsolutePath());
    }
    
    public static Hashtable getZipFileEntryList(final String s) {
        final String zipFileName = getZipFileName(s);
        if (zipFileName == null) {
            return new Hashtable();
        }
        try {
            return ((ZipURLConnection)getFileURL(zipFileName).openConnection()).getTopLevelZipEntries();
        }
        catch (Exception ex) {
            NFDebug.print(64L, "NFUtil.getZipFileEntryList(): exception: " + ex + " while examining " + s);
            return new Hashtable();
        }
    }
    
    public static boolean isZipFile(final String s) {
        return s != null && (s.indexOf(".jar", 0) != -1 || s.indexOf(".zip", 0) != -1);
    }
    
    public static String getZipFileName(final String s) {
        if (!isZipFile(s)) {
            return null;
        }
        if (!s.endsWith("!/")) {
            if (s.endsWith("!")) {
                return new String(s + "/");
            }
            if (s.endsWith(".jar") || s.endsWith(".zip")) {
                return new String(s + "!/");
            }
        }
        return s;
    }
    
    public static boolean isZipDirEntry(final String s, final String s2) {
        if (s == null || s2 == null) {
            return false;
        }
        final Enumeration<Object> keys = getZipFileEntryList(s).keys();
        while (keys.hasMoreElements()) {
            if (keys.nextElement().equals(s2)) {
                return true;
            }
        }
        return false;
    }
    
    public static String encodeURL(final String s) {
        String substring = "";
        final int index;
        String substring2;
        if ((index = s.indexOf("?")) > -1) {
            substring2 = s.substring(0, index);
            substring = s.substring(index);
        }
        else {
            substring2 = s;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(substring2, "/ ", true);
        final StringBuffer sb = new StringBuffer();
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals("/")) {
                sb.append(nextToken);
            }
            else if (nextToken.equals(" ")) {
                sb.append("%20");
            }
            else {
                sb.append(URLEncoder.encode(nextToken));
            }
        }
        return sb.toString() + substring;
    }
    
    public static int getNumber(final Object o, final int n) {
        if (o == null || !(o instanceof Number) || Double.isNaN(((Number)o).doubleValue())) {
            return n;
        }
        return ((Number)o).intValue();
    }
    
    public static double getNumber(final Object o, final double n) {
        if (o == null || !(o instanceof Number) || Double.isNaN(((Number)o).doubleValue())) {
            return n;
        }
        return ((Number)o).doubleValue();
    }
    
    public static float getNumber(final Object o, final float n) {
        if (o == null || !(o instanceof Number) || Double.isNaN(((Number)o).doubleValue())) {
            return n;
        }
        return ((Number)o).floatValue();
    }
    
    public static Object getObject(final Object o, final Object o2) {
        if (o == null) {
            return o2;
        }
        return o;
    }
    
    public static Object getObject(final Vector vector, final int n, final Object o) {
        if (vector == null || n < 0 || n >= vector.size()) {
            return o;
        }
        return getObject(vector.elementAt(n), o);
    }
    
    public static Color getColor(final Object o, final Color color) {
        if (o == null || !(o instanceof Color)) {
            return color;
        }
        return (Color)o;
    }
    
    public static Color getColor(final Vector vector, final int n, final Color color) {
        if (vector == null || n < 0 || n >= vector.size()) {
            return color;
        }
        return getColor(vector.elementAt(n), color);
    }
    
    public static int getNumber(final Vector vector, final int n, final int n2) {
        if (vector == null || n < 0 || n >= vector.size()) {
            return n2;
        }
        return getNumber(vector.elementAt(n), n2);
    }
    
    public static float getNumber(final Vector vector, final int n, final float n2) {
        if (vector == null || n < 0 || n >= vector.size()) {
            return n2;
        }
        return getNumber(vector.elementAt(n), n2);
    }
    
    public static double getNumber(final Vector vector, final int n, final double n2) {
        if (vector == null || n < 0 || n >= vector.size()) {
            return n2;
        }
        return getNumber(vector.elementAt(n), n2);
    }
    
    public static String getString(final Object o, final String s) {
        if (o == null || !(o instanceof String)) {
            return s;
        }
        return (String)o;
    }
    
    public static String getString(final Vector vector, final int n, final String s) {
        if (vector == null || n < 0 || n >= vector.size()) {
            return s;
        }
        return getString(vector.elementAt(n), s);
    }
    
    public static double log(final double n, final double n2) {
        return Math.log(n) / Math.log(n2);
    }
    
    public static double getNumber(final String s, final double n) {
        Double value;
        try {
            value = Double.valueOf(s);
        }
        catch (Exception ex) {
            value = null;
        }
        return getNumber(value, n);
    }
    
    public static float getNumber(final String s, final float n) {
        Float value;
        try {
            value = Float.valueOf(s);
        }
        catch (Exception ex) {
            value = null;
        }
        return getNumber(value, n);
    }
    
    public static int getNumber(final String s, final int n) {
        Integer value;
        try {
            value = Integer.valueOf(s);
        }
        catch (Exception ex) {
            value = null;
        }
        return getNumber(value, n);
    }
    
    public static boolean getBoolean(final String s, final boolean b) {
        boolean b2 = b;
        try {
            if (s.equalsIgnoreCase("true")) {
                b2 = true;
            }
        }
        catch (Exception ex) {
            b2 = b;
        }
        return b2;
    }
    
    public static String urlDecode(final String s) {
        if (s == null) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                case 43: {
                    sb.append(' ');
                    break;
                }
                case 37: {
                    if (++i == s.length()) {
                        return null;
                    }
                    int char2 = s.charAt(i);
                    if (++i == s.length()) {
                        return null;
                    }
                    int char3 = s.charAt(i);
                    if (Character.isDigit((char)char2)) {
                        char2 -= 48;
                    }
                    else {
                        char2 = '\n' + (Character.toUpperCase((char)char2) - 'A');
                    }
                    if (Character.isDigit((char)char3)) {
                        char3 -= 48;
                    }
                    else {
                        char3 = '\n' + (Character.toUpperCase((char)char3) - 'A');
                    }
                    sb.append((char)((byte)char3 | (byte)char2 << 4));
                    break;
                }
                default: {
                    sb.append(char1);
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    public static void appendNameValuePair(final StringBuffer sb, final String s, final String s2) {
        if (sb == null || s == null) {
            return;
        }
        if (sb.length() > 0) {
            sb.append("&");
        }
        sb.append(URLEncoder.encode(s));
        sb.append("=");
        if (s2 != null) {
            sb.append(URLEncoder.encode(s2));
        }
    }
    
    public static String formatNumericValue(final double n, final int n2, final String s, final int n3, final char c, final char c2) {
        switch (n2) {
            case 5: {
                if (s != null && s.length() > 0 && getJDKVersion() >= 1.1) {
                    return NF11Util.formatDecimal(new Double(n), n3, c, c2, s);
                }
                return sprintf(n3, c, c2, "%" + c + "g", new Double(n));
            }
            case 2: {
                if (s != null && s.length() > 0) {
                    return sprintf(n3, c, c2, s, new Double(n));
                }
                return sprintf(n3, c, c2, "%" + c + "g", new Double(n));
            }
            case 1: {
                final int n4 = (int)rint(n);
                if (s != null && s.length() > 0) {
                    return sprintf(n3, c, c2, s, new Integer(n4));
                }
                return sprintf(n3, c, c2, "%" + c + "d", new Integer(n4));
            }
            default: {
                return "ERROR";
            }
        }
    }
    
    static {
        NFUtil.a = null;
        NFUtil.b = 0.0;
        NFUtil.c = null;
        NFUtil.d = new Object();
        NFUtil.e = new Frame[100];
        NFUtil.f = new int[100];
        NFUtil.g = new int[100];
        NFUtil.blackEvalColor = null;
        NFUtil.whiteEvalColor = null;
    }
}
