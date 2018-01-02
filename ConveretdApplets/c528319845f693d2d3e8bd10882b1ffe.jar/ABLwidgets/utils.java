// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.util.Vector;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.net.URLEncoder;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Component;

public class utils
{
    public static String a(final String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int length;
        int n;
        for (n = (length = s.length()); length > 0 && s.charAt(length - 1) == ' '; --length) {}
        if (length < n) {
            return s.substring(0, length);
        }
        return s;
    }
    
    public static boolean a(final String s, final String s2) {
        if (s == null) {
            return s2 == null;
        }
        return s2 != null && s.equals(s2);
    }
    
    public static boolean b(final String s, final String s2) {
        return a(a(s), a(s2));
    }
    
    public static boolean c(final String s, final String s2) {
        if (s == null) {
            return s2 == null;
        }
        return s2 != null && s.equalsIgnoreCase(s2);
    }
    
    public static boolean d(final String s, final String s2) {
        return c(a(s), a(s2));
    }
    
    public static String b(String string) {
        if (string != null && string.length() > 0) {
            final char char1 = string.charAt(string.length() - 1);
            if (char1 != '/' && char1 != '\\') {
                string = String.valueOf(string) + "/";
            }
        }
        return string;
    }
    
    public static String a(final String s, final int n, final char c) {
        return String.valueOf(s.substring(0, n)) + c + s.substring(n + 1);
    }
    
    public static String a(String string, final String s, String s2) {
        if (string == null || string.length() == 0) {
            return string;
        }
        if (s == null || s.length() == 0) {
            return string;
        }
        if (s2 == null) {
            s2 = "";
        }
        int index;
        for (int length = s.length(), length2 = s2.length(), n = 0; (index = string.indexOf(s, n)) >= 0; string = String.valueOf(string.substring(0, index)) + s2 + string.substring(index + length), n = index + length2) {}
        return string;
    }
    
    public static int a(final byte[] array, final int n, final int n2, final StringBuffer sb) {
        sb.setLength(0);
        if (array[n] != 34) {
            return 0;
        }
        for (int i = n + 1; i < n2; ++i) {
            int n3 = array[i];
            if (n3 == 34) {
                return i - n;
            }
            if (n3 == 92) {
                if (++i >= n2) {
                    return 0;
                }
                switch (array[i]) {
                    case 113: {
                        n3 = 34;
                        break;
                    }
                    case 92: {
                        n3 = 92;
                        break;
                    }
                    default: {
                        n3 = array[i];
                        break;
                    }
                }
            }
            sb.append((char)n3);
        }
        return 0;
    }
    
    public static String e(final String s, final String s2) {
        String string = s;
        boolean b = false;
        int index;
        int n2;
        String s3;
        for (int n = 0; (index = string.indexOf(92, n)) >= 0; string = String.valueOf(string.substring(0, index)) + s3 + string.substring(index + n2), n = index + s3.length()) {
            n2 = 1;
            s3 = "";
            if (index + 1 >= string.length()) {
                b = true;
            }
            else {
                switch (string.charAt(index + 1)) {
                    case 'q': {
                        n2 = 2;
                        s3 = "\"";
                        break;
                    }
                    case '\\': {
                        n2 = 2;
                        s3 = "\\";
                        break;
                    }
                    default: {
                        b = true;
                        break;
                    }
                }
            }
        }
        if (b) {
            abljem.b("Invalid \\ sequence in " + s2 + "=" + s + " set=" + string);
        }
        return string;
    }
    
    public static int a(final byte[] array, final int n) {
        int n2 = 0;
        for (int n3 = n; n3 < array.length && (char)array[n3] < ' '; ++n3) {
            ++n2;
        }
        return n2;
    }
    
    public static int b(final byte[] array, final int n) {
        if (n + 3 >= array.length) {
            return 0;
        }
        if (array[n] != 60 || array[n + 1] != 33 || array[n + 2] != 45 || array[n + 3] != 45) {
            return 0;
        }
        int n2;
        for (n2 = n + 4; n2 < array.length && (array[n2] != 62 || array[n2 - 1] != 45 || array[n2 - 2] != 45); ++n2) {}
        return n2 - n;
    }
    
    public static int c(final byte[] array, final int n) {
        int n2 = n;
        while (true) {
            final int a;
            if ((a = a(array, n2)) > 0) {
                n2 += a;
            }
            else {
                final int b;
                if ((b = b(array, n2)) <= 0) {
                    break;
                }
                n2 += b;
            }
        }
        return n2 - n;
    }
    
    public static int[] a(final int[] array, final int n) {
        final int[] array2 = new int[n];
        if (array != null) {
            int length = array.length;
            if (length > n) {
                length = n;
            }
            System.arraycopy(array, 0, array2, 0, length);
        }
        return array2;
    }
    
    public static String[] a(final String[] array, final int n) {
        final String[] array2 = new String[n];
        if (array != null) {
            int length = array.length;
            if (length > n) {
                length = n;
            }
            System.arraycopy(array, 0, array2, 0, length);
        }
        return array2;
    }
    
    public static Image a(final Component component, final int n, final int n2) {
        Image image;
        try {
            image = component.createImage(n, n2);
        }
        catch (Throwable t) {
            image = null;
        }
        if (image == null) {
            try {
                image = component.createImage(n, n2);
            }
            catch (Throwable t2) {
                image = null;
            }
        }
        return image;
    }
    
    public static void a(final Graphics graphics, Color white, final int n, final int n2, final int n3, final int n4) {
        if (white == null) {
            white = Color.white;
        }
        graphics.setColor(white);
        graphics.clearRect(n, n2, n3, n4);
        graphics.fillRect(n, n2, n3, n4);
    }
    
    public static Font c(String s) {
        String s2 = null;
        String s3 = null;
        int n = 0;
        if (s == null) {
            s = "";
        }
        final int index = s.indexOf(44);
        String substring;
        if (index < 0) {
            substring = s;
        }
        else {
            substring = s.substring(0, index);
            final int n2 = index + 1;
            final int index2 = s.indexOf(44, n2);
            if (index2 < 0) {
                s2 = s.substring(n2);
            }
            else {
                s2 = s.substring(n2, index2);
                final int n3 = index2 + 1;
                final int index3 = s.indexOf(44, n3);
                if (index3 < 0) {
                    s3 = s.substring(n3);
                }
                else {
                    s3 = s.substring(n3, index3);
                }
            }
        }
        if (substring == null || substring.length() == 0) {
            substring = "Default";
        }
        if (s2 == null || s2.length() == 0) {
            n = 0;
        }
        else if (s2.equalsIgnoreCase("Default")) {
            n = 0;
        }
        else if (s2.equalsIgnoreCase("PLAIN")) {
            n = 0;
        }
        else if (s2.equalsIgnoreCase("BOLD")) {
            n = 1;
        }
        else if (s2.equalsIgnoreCase("ITALIC")) {
            n = 2;
        }
        else if (s2.equalsIgnoreCase("BOLDITALIC")) {
            n = 3;
        }
        else if (s2.equalsIgnoreCase("ITALICBOLD")) {
            n = 3;
        }
        else if (s2.equalsIgnoreCase("BOLD ITALIC")) {
            n = 3;
        }
        else if (s2.equalsIgnoreCase("ITALIC BOLD")) {
            n = 3;
        }
        if (s3 == null || s3.length() == 0) {
            s3 = "10";
        }
        int int1;
        try {
            int1 = Integer.parseInt(s3);
        }
        catch (Exception ex) {
            abljem.b("utils.toFont: invalid font size: " + s3);
            int1 = 10;
        }
        return new_font.a(substring, n, int1);
    }
    
    public static Color[] d(final String s) {
        final Color[] array = new Color[2];
        final int index = s.indexOf(59);
        array[0] = new Color(0, 0, 0);
        array[1] = new Color(255, 255, 255);
        if (index == -1) {
            array[0] = e(s);
        }
        else {
            if (index > 0) {
                array[0] = e(s.substring(0, index));
            }
            if (index < s.length() - 1) {
                array[1] = e(s.substring(index + 1));
            }
        }
        return array;
    }
    
    public static Color e(final String s) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        final int n4 = 0;
        if (!s.equals("")) {
            try {
                if (s.startsWith("#")) {
                    n = g("#" + s.substring(1, 3));
                    n2 = g("#" + s.substring(3, 5));
                    n3 = g("#" + s.substring(5, 7));
                }
                else {
                    final int length = s.length();
                    int index = s.indexOf(44, n4);
                    if (index == -1) {
                        index = length;
                    }
                    n = g(s.substring(n4, index));
                    final int n5 = index + 1;
                    int index2 = s.indexOf(44, n5);
                    if (index2 == -1) {
                        index2 = length;
                    }
                    n2 = g(s.substring(n5, index2));
                    n3 = g(s.substring(index2 + 1));
                }
            }
            catch (Exception ex) {
                abljem.b("utils.toColor[1]: invalid number format: " + s);
                ex.printStackTrace();
            }
        }
        return new Color(n, n2, n3);
    }
    
    public static double f(String trim) {
        if (trim == null) {
            abljem.b("utils.dblValue[1]: invalid number format: (null)");
            return 0.0;
        }
        double doubleValue = 0.0;
        trim = trim.trim();
        if (trim.length() == 0) {
            return 0.0;
        }
        try {
            doubleValue = Double.valueOf(trim);
        }
        catch (Exception ex) {
            abljem.b("utils.dblValue[2]: invalid number format: " + trim);
            ex.printStackTrace();
        }
        return doubleValue;
    }
    
    public static byte a(final byte b, final byte b2) {
        return (byte)g("#" + (char)b + (char)b2);
    }
    
    public static int g(String trim) {
        if (trim == null) {
            abljem.b("utils.intValue[1]: invalid number format: (null)");
            return 0;
        }
        int n = 0;
        trim = trim.trim();
        if (trim.startsWith("#")) {
            if (trim.length() == 1) {
                return 0;
            }
            try {
                n = Integer.parseInt(trim.substring(1), 16);
            }
            catch (Exception ex) {
                abljem.b("utils.intValue[2]: invalid number format: " + trim);
                ex.printStackTrace();
            }
        }
        else {
            if (trim.length() == 0) {
                return 0;
            }
            try {
                n = Integer.parseInt(trim);
            }
            catch (Exception ex2) {
                abljem.b("utils.intValue[3]: invalid number format: " + trim);
                ex2.printStackTrace();
            }
        }
        return n;
    }
    
    public static int a(final byte[] array, int n, int n2) {
        int n3 = 0;
        while (n2-- > 0) {
            n3 = n3 * 10 + (array[n++] - 48);
        }
        return n3;
    }
    
    public static String f(String s, final String s2) {
        if (s2 == null || s2.length() == 0) {
            return s;
        }
        if (s == null) {
            s = "";
        }
        else {
            s = String.valueOf(s) + "&";
        }
        s = String.valueOf(s) + s2;
        return s;
    }
    
    public static String g(String string, final String s) {
        if (string == null || string.length() == 0) {
            return string;
        }
        if (s == null || s.length() == 0) {
            return string;
        }
        string = String.valueOf(string) + ((string.indexOf(63) < 0) ? "?" : "&") + s;
        return string;
    }
    
    public static String b(String s, final String s2, final String s3) {
        if (s == null) {
            s = "";
        }
        if (s2 == null || s2.length() == 0) {
            return s;
        }
        s = String.valueOf(s) + ((s.length() > 0) ? "&" : "") + URLEncoder.encode(s2);
        if (s3 == null || s3.length() == 0) {
            return s;
        }
        s = String.valueOf(s) + "=" + URLEncoder.encode(s3);
        return s;
    }
    
    public static Point a(final int n, final int n2, final int n3, final Image image, final int n4, final int n5, final int n6, final int n7) {
        int n8 = n2;
        int n9 = n3;
        final int n10 = n6 - image.getWidth(null);
        final int n11 = n7 - image.getHeight(null);
        final int n12 = n4 + n10 / 2;
        final int n13 = n5 + n11 / 2;
        final int n14 = n4 + n10;
        final int n15 = n5 + n11;
        switch (n) {
            case 0: {
                n8 = n2;
                n9 = n3;
                break;
            }
            case 1: {
                n8 = n4;
                n9 = n5;
                break;
            }
            case 2: {
                n8 = n12;
                n9 = n5;
                break;
            }
            case 3: {
                n8 = n14;
                n9 = n5;
                break;
            }
            case 4: {
                n8 = n4;
                n9 = n13;
                break;
            }
            case 5: {
                n8 = n12;
                n9 = n13;
                break;
            }
            case 6: {
                n8 = n14;
                n9 = n13;
                break;
            }
            case 7: {
                n8 = n4;
                n9 = n15;
                break;
            }
            case 8: {
                n8 = n12;
                n9 = n15;
                break;
            }
            case 9: {
                n8 = n14;
                n9 = n15;
                break;
            }
        }
        return new Point(n8, n9);
    }
    
    public static void a(final Graphics graphics, final edge edge, int n, int n2, int n3, int n4) {
        if (edge == null) {
            return;
        }
        if (edge.j == 0) {
            return;
        }
        a(graphics, edge.g);
        graphics.drawLine(n, n2, n3 - 1, n2);
        graphics.drawLine(n, n2, n, n4 - 1);
        a(graphics, edge.i);
        graphics.drawLine(n, n4, n3, n4);
        graphics.drawLine(n3, n2, n3, n4);
        --n;
        --n2;
        ++n3;
        ++n4;
        a(graphics, edge.f);
        graphics.drawLine(n, n2, n3 - 1, n2);
        graphics.drawLine(n, n2, n, n4 - 1);
        a(graphics, edge.h);
        graphics.drawLine(n, n4, n3, n4);
        graphics.drawLine(n3, n2, n3, n4);
    }
    
    public static void a(final Graphics graphics, Color white) {
        if (white == null) {
            white = Color.white;
        }
        graphics.setColor(white);
    }
    
    public static String a() {
        return a(-1, "<", 1);
    }
    
    public static String a(int n, final String s, final int n2) {
        String string = "";
        final String[] b = b();
        final int n3 = 2 + n2;
        int length = b.length;
        if (n == -2 || n == -1) {
            for (length = n3; length < b.length && !b[length].startsWith("sun") && !b[length].startsWith("java") && !b[length].startsWith("com/ms/"); ++length) {}
        }
        switch (n) {
            case -3: {
                n = b.length - n3;
                break;
            }
            case -2: {
                n = length - n3 + 1;
                break;
            }
            case -1: {
                n = length - n3;
                break;
            }
        }
        for (int n4 = 0, n5 = n3; n4 < n && n5 < b.length; ++n4, ++n5) {
            string = String.valueOf(new StringBuffer(String.valueOf(string)).append(s).toString()) + b[n5];
        }
        if (string.length() < 1) {
            string = "(Stack trace unavailable)";
        }
        return string;
    }
    
    public static String[] b() {
        final Throwable t = new Throwable("Dummy throwable");
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        t.printStackTrace(new PrintStream(byteArrayOutputStream));
        final String string = byteArrayOutputStream.toString();
        final Vector vector = new Vector<String>();
        int n = 0;
        int n3;
        for (int n2 = 0, index = 0; n2 < string.length() && index >= 0; n2 = n3 + 1) {
            index = string.indexOf(10, n2);
            n3 = ((index < 0) ? string.length() : index);
            String s = string.substring(n2, n3);
            final int n4 = s.length() - 1;
            if (n4 >= 0 && s.charAt(n4) == '\r') {
                s = s.substring(0, n4);
            }
            while (s.length() > 0 && s.charAt(0) == '\t') {
                s = s.substring(1);
            }
            if (s.startsWith("at ")) {
                s = s.substring(3);
            }
            if (n > 1) {
                vector.addElement(s);
            }
            ++n;
        }
        final String[] array = new String[vector.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
}
