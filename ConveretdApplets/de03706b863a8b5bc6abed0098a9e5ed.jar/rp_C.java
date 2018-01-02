import java.net.URLEncoder;
import java.awt.Window;
import java.awt.Frame;
import java.net.URL;
import java.util.regex.Pattern;
import java.io.UnsupportedEncodingException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.awt.Color;
import java.util.Iterator;
import java.util.ArrayList;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_C
{
    public Point a;
    public Object a;
    
    public rp_C(final String s, final Point a, final Object a2) {
        this.a = a;
        this.a = a2;
    }
    
    private rp_C() {
    }
    
    public static double a(final double n, final double n2) {
        final double abs = Math.abs(n);
        final double abs2 = Math.abs(n2);
        double n3;
        if (abs == 0.0) {
            n3 = abs2;
        }
        else if (abs2 == 0.0) {
            n3 = abs;
        }
        else if (abs >= abs2) {
            final double n4 = abs2 / abs;
            n3 = abs * Math.sqrt(1.0 + n4 * n4);
        }
        else {
            final double n5 = abs / abs2;
            n3 = abs2 * Math.sqrt(1.0 + n5 * n5);
        }
        return n3;
    }
    
    public static double a(final int n, final int n2, final int n3, final int n4) {
        final double n5 = n3 - n;
        final double n6 = n4 - n2;
        return (int)(0.5 + Math.sqrt(n5 * n5 + n6 * n6));
    }
    
    public static double a(final Point point, final Point point2) {
        final double n = point2.x - point.x;
        final double n2 = point2.y - point.y;
        if (n != 0.0) {
            double atan = Math.atan(n2 / n);
            if ((n < 0.0 && n2 >= 0.0) || (n2 < 0.0 && n > 0.0)) {
                atan += 3.141592653589793;
            }
            if (n2 < 0.0) {
                atan += 3.141592653589793;
            }
            return atan;
        }
        if (n2 >= 0.0) {
            return 1.5707963267948966;
        }
        return 4.71238898038469;
    }
    
    public static int a(final String s) {
        if (s == null) {
            return 0;
        }
        try {
            return Integer.parseInt(s.trim());
        }
        catch (NumberFormatException ex) {
            System.out.println("NumberFormatException when parsing: " + s);
            return 0;
        }
    }
    
    public static double a(final String s) {
        if (s == null) {
            return 0.0;
        }
        try {
            return Double.parseDouble(s.trim());
        }
        catch (NumberFormatException ex) {
            System.out.println("NumberFormatException when parsing: " + s);
            return 0.0;
        }
    }
    
    public static void a(final ArrayList list, final int n, final int n2) {
        for (final Point point2 : list) {
            final Point point = point2;
            point.x += n;
            final Point point3 = point2;
            point3.y += n2;
        }
    }
    
    public static Color[] a(final rp_eA rp_eA) {
        Color a = null;
        Color a2 = null;
        final Enumeration<rp_eA> elements = (Enumeration<rp_eA>)rp_eA.a.elements();
        while (elements.hasMoreElements()) {
            final rp_eA rp_eA2;
            if ((rp_eA2 = elements.nextElement()).a.equals("border-color")) {
                a = a(rp_eA2);
            }
            else {
                if (!rp_eA2.a.equals("fill-color")) {
                    continue;
                }
                a2 = a(rp_eA2);
            }
        }
        final Color[] array;
        (array = new Color[2])[0] = a;
        array[1] = a2;
        return array;
    }
    
    public static Color a(final rp_eA rp_eA) {
        final int a = rp_eA.a("r", -1);
        final int a2 = rp_eA.a("g", -1);
        final int a3 = rp_eA.a("b", -1);
        if (a < 0 || a2 < 0 || a3 < 0 || a > 255 || a2 > 255 || a3 > 255) {
            return null;
        }
        return new Color(a, a2, a3);
    }
    
    public static String a(final Color color) {
        if (color == null) {
            return "";
        }
        return String.format("%d;%d;%d", color.getRed(), color.getGreen(), color.getBlue());
    }
    
    public static Color a(final String s) {
        try {
            return new Color(Integer.parseInt(s, 16));
        }
        catch (NumberFormatException ex) {
            return null;
        }
    }
    
    public static Color[] a(final String s) {
        if (s == null) {
            return null;
        }
        try {
            final String[] split;
            if ((split = s.split(";")) != null && split.length == 2) {
                final Color[] array;
                (array = new Color[2])[0] = a(split[0]);
                array[1] = a(split[1]);
                return array;
            }
        }
        catch (NumberFormatException ex) {}
        return null;
    }
    
    public static String a(final boolean b) {
        if (b) {
            return "1";
        }
        return "0";
    }
    
    public static void a(final int n, final String s) {
        if (rp_aJ.a < n) {
            return;
        }
        if (rp_aJ.a) {
            System.out.print("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] ");
        }
        System.out.println(s);
    }
    
    public static String a(final InputStream inputStream) {
        final StringBuilder sb = new StringBuilder();
        final byte[] array = new byte[4096];
        int read;
        while ((read = inputStream.read(array)) != -1) {
            sb.append(new String(array, 0, read));
        }
        return sb.toString();
    }
    
    public static InputStream a(final String s) {
        try {
            return new ByteArrayInputStream(s.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
    
    public static String a(final String s) {
        try {
            return new String(s.getBytes(), "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            return s;
        }
    }
    
    public static boolean a(final String s) {
        return s.matches("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$");
    }
    
    public static boolean b(final String s) {
        return !Pattern.compile(".*<[^>]+>.*", 32).matcher(s).matches();
    }
    
    public static boolean a(final URL url) {
        final String string;
        final int index;
        return (index = (string = url.toString()).indexOf(63)) == -1 || (string.indexOf(32, index) == -1 && string.indexOf(60, index) == -1 && string.indexOf(62, index) == -1);
    }
    
    public static String a(final String s, final Throwable t) {
        final StringBuilder sb;
        (sb = new StringBuilder(s)).append(t.toString());
        final String property = System.getProperty("line.separator");
        sb.append(property);
        StackTraceElement[] stackTrace;
        for (int length = (stackTrace = t.getStackTrace()).length, i = 0; i < length; ++i) {
            sb.append(stackTrace[i]);
            sb.append(property);
        }
        return sb.toString();
    }
    
    public static boolean a(final String s, final boolean b, final Frame frame) {
        String s2 = null;
        if (s == null || s.length() == 0) {
            s2 = "ut3";
        }
        else if (s.length() > 128) {
            s2 = "ut1";
        }
        else if (!s.matches("[^\\\\/:\\*\\?\\\"\\<\\>]*")) {
            s2 = "ut2";
        }
        if (s2 == null) {
            return true;
        }
        rp_bd.a(frame, rp_au.a.a(), "err", s2);
        return false;
    }
    
    public static String b(String s) {
        if (s == null) {
            return null;
        }
        int index;
        while ((index = s.indexOf(32)) > 0) {
            final String substring = s.substring(0, index);
            s = s.substring(index + 1);
            s = substring + "%20" + s;
        }
        int index2;
        while ((index2 = s.indexOf(63)) > 0) {
            final String substring2 = s.substring(0, index2);
            s = s.substring(index2 + 1);
            s = substring2 + "%3F" + s;
        }
        int index3;
        while ((index3 = s.indexOf(38)) > 0) {
            final String substring3 = s.substring(0, index3);
            s = s.substring(index3 + 1);
            s = substring3 + "%26" + s;
        }
        int index4;
        while ((index4 = s.indexOf(61)) > 0) {
            final String substring4 = s.substring(0, index4);
            s = s.substring(index4 + 1);
            s = substring4 + "%3D" + s;
        }
        int index5;
        while ((index5 = s.indexOf(44)) > 0) {
            final String substring5 = s.substring(0, index5);
            s = s.substring(index5 + 1);
            s = substring5 + "%2C" + s;
        }
        int index6;
        while ((index6 = s.indexOf("'")) > 0) {
            final String substring6 = s.substring(0, index6);
            s = s.substring(index6 + 1);
            s = substring6 + "%27" + s;
        }
        int index7;
        while ((index7 = s.indexOf(";")) > 0) {
            final String substring7 = s.substring(0, index7);
            s = s.substring(index7 + 1);
            s = substring7 + "%3B" + s;
        }
        return s;
    }
    
    public static String c(final String s) {
        String d = null;
        try {
            d = d(s);
        }
        catch (Exception ex) {}
        return d;
    }
    
    public static String d(final String s) {
        final StringBuffer sb = new StringBuffer();
        final char[] charArray = s.toCharArray();
        int i = 0;
        final int length = s.length();
        while (i < length) {
            final char c;
            switch (c = charArray[i]) {
                case '+': {
                    sb.append(' ');
                    ++i;
                    continue;
                }
                case '%': {
                    if (i >= length - 2) {
                        sb.append(c);
                        ++i;
                        continue;
                    }
                    final int digit = Character.digit(charArray[i + 1], 16);
                    final int digit2 = Character.digit(charArray[i + 2], 16);
                    if (digit < 0 || digit2 < 0) {
                        sb.append(c);
                        ++i;
                        continue;
                    }
                    sb.append((char)((digit << 4) + digit2));
                    i += 3;
                    continue;
                }
                default: {
                    sb.append(c);
                    ++i;
                    continue;
                }
            }
        }
        return new String(sb);
    }
    
    public static String e(String s) {
        if (s == null) {
            return "";
        }
        try {
            s = URLEncoder.encode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            System.out.println(ex);
        }
        int index;
        while ((index = s.indexOf(43)) > 0) {
            final String substring = s.substring(0, index);
            s = s.substring(index + 1);
            s = substring + "%20" + s;
        }
        return s;
    }
}
