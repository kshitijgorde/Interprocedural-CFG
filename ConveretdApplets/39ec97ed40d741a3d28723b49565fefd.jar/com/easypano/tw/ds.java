// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.util.Vector;
import java.awt.Font;
import java.net.MalformedURLException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.awt.Image;
import java.net.URL;
import java.awt.Component;

public final class ds
{
    public static final double a = 1.0E-6;
    private static volatile int b;
    private static volatile int c;
    public static final Component d;
    public static TWViewer e;
    public static URL f;
    public static URL g;
    
    static {
        ds.b = 0;
        ds.c = 0;
        d = new d();
        ds.e = null;
        ds.f = null;
        ds.g = null;
    }
    
    public static synchronized void a() {
        ++ds.b;
        ++ds.c;
    }
    
    public static synchronized void b() {
        --ds.b;
    }
    
    public static synchronized int c() {
        return ds.b;
    }
    
    public static synchronized int d() {
        return ds.c;
    }
    
    public static void a(final Image image) {
        if (image != null) {
            final MediaTracker mediaTracker = new MediaTracker(ds.d);
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            mediaTracker.removeImage(image, 0);
        }
    }
    
    public static void a(final Dimension dimension, final int width, final int height) {
        if (dimension.width < width) {
            dimension.width = width;
        }
        if (dimension.height < height) {
            dimension.height = height;
        }
    }
    
    public static Color a(String substring) {
        int intValue = 0;
        int intValue2 = 0;
        int intValue3 = 0;
        if (substring != null) {
            try {
                final int index = substring.indexOf(",");
                if (index > 0) {
                    intValue = Integer.valueOf(substring.substring(0, index).trim());
                    substring = substring.substring(index + 1);
                }
                final int index2 = substring.indexOf(",");
                if (index2 > 0) {
                    intValue2 = Integer.valueOf(substring.substring(0, index2).trim());
                    intValue3 = Integer.valueOf(substring.substring(index2 + 1).trim());
                }
            }
            catch (Exception ex) {}
        }
        return new Color(intValue, intValue2, intValue3);
    }
    
    public static Rectangle b(String s) {
        int intValue = 0;
        int intValue2 = 0;
        int intValue3 = 0;
        int intValue4 = 0;
        if (s != null) {
            try {
                final int index = s.indexOf(",");
                if (index > 0) {
                    intValue = Integer.valueOf(s.substring(0, index).trim());
                    s = s.substring(index + 1);
                }
                final int index2 = s.indexOf(",");
                if (index2 > 0) {
                    intValue2 = Integer.valueOf(s.substring(0, index2).trim());
                    s = s.substring(index2 + 1);
                }
                final int index3 = s.indexOf(",");
                if (index3 > 0) {
                    intValue3 = Integer.valueOf(s.substring(0, index3).trim());
                    intValue4 = Integer.valueOf(s.substring(index3 + 1).trim());
                }
            }
            catch (Exception ex) {}
        }
        return new Rectangle(intValue, intValue2, intValue3, intValue4);
    }
    
    public static Dimension c(final String s) {
        int intValue = 0;
        int intValue2 = 0;
        if (s != null) {
            try {
                final int index = s.indexOf(",");
                if (index > 0) {
                    intValue = Integer.valueOf(s.substring(0, index).trim());
                    intValue2 = Integer.valueOf(s.substring(index + 1).trim());
                }
            }
            catch (Exception ex) {}
        }
        return new Dimension(intValue, intValue2);
    }
    
    public static Point d(final String s) {
        int intValue = 0;
        int intValue2 = 0;
        if (s != null) {
            try {
                final int index = s.indexOf(",");
                if (index > 0) {
                    intValue = Integer.valueOf(s.substring(0, index).trim());
                    intValue2 = Integer.valueOf(s.substring(index + 1).trim());
                }
            }
            catch (Exception ex) {}
        }
        return new Point(intValue, intValue2);
    }
    
    public static int e(final String s) {
        int intValue = 0;
        try {
            intValue = Integer.valueOf(s);
        }
        catch (NumberFormatException ex) {}
        return intValue;
    }
    
    public static Integer f(final String s) {
        try {
            return new Integer(s);
        }
        catch (Exception ex) {
            return new Integer(-1);
        }
    }
    
    public static boolean g(final String s) {
        boolean equalsIgnoreCase = true;
        try {
            equalsIgnoreCase = s.equalsIgnoreCase(m("^\u0001\u0002\u0000"));
        }
        catch (Exception ex) {}
        return equalsIgnoreCase;
    }
    
    public static void a(final URL f) {
        ds.f = f;
    }
    
    public static void b(final URL g) {
        ds.g = g;
    }
    
    public static URL a(final URL url, final String s) {
        if (url == null || s == null) {
            return null;
        }
        try {
            return new URL(url, s);
        }
        catch (MalformedURLException ex) {
            return url;
        }
    }
    
    public static Font h(String substring) {
        String s = m("~\u001a\u001a\u0000|\n=\u0012\u0012/x\u001c\u001a\u0004a");
        m("z\u001f\u0016\fa");
        int n = 0;
        int intValue = 10;
        if (substring != null) {
            try {
                final int index = substring.indexOf(",");
                if (index > 0) {
                    s = substring.substring(0, index).trim();
                    substring = substring.substring(index + 1);
                }
                final int index2 = substring.indexOf(",");
                if (index2 > 0) {
                    final String trim = substring.substring(0, index2).trim();
                    if (trim.equalsIgnoreCase(m("c'6)Fi"))) {
                        n = 2;
                    }
                    else if (trim.equalsIgnoreCase(m("h<;!"))) {
                        n = 1;
                    }
                    else if (trim.equalsIgnoreCase(m("h<;!F~2;,L"))) {
                        n = 3;
                    }
                    else {
                        n = 0;
                    }
                    intValue = Integer.valueOf(substring.substring(index2 + 1).trim());
                }
            }
            catch (Exception ex) {}
        }
        return new Font(s, n, intValue);
    }
    
    public static Vector i(final String s) {
        return new Vector();
    }
    
    public static URL j(final String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        try {
            return new URL(ds.f, s);
        }
        catch (MalformedURLException ex) {
            return ds.f;
        }
    }
    
    public static dx k(String substring) {
        int intValue = 0;
        int intValue2 = 0;
        int intValue3 = 0;
        if (substring != null) {
            try {
                final int index = substring.indexOf(",");
                if (index > 0) {
                    intValue = Integer.valueOf(substring.substring(0, index).trim());
                    substring = substring.substring(index + 1);
                }
                final int index2 = substring.indexOf(",");
                if (index2 > 0) {
                    intValue2 = Integer.valueOf(substring.substring(0, index2).trim());
                    intValue3 = Integer.valueOf(substring.substring(index2 + 1).trim());
                }
            }
            catch (Exception ex) {}
        }
        return new dx(intValue, intValue2, intValue3);
    }
    
    public static void stopThread(final Thread thread, final String s, final int n, final int n2) {
        int n3 = 0;
        while (thread.isAlive()) {
            Label_0039: {
                if (n3++ >= n2) {
                    try {
                        thread.stop();
                    }
                    catch (SecurityException ex) {
                        thread.destroy();
                        if (n3 < n2 + 1) {
                            break Label_0039;
                        }
                    }
                    break;
                }
                try {
                    Thread.sleep(n);
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public static String l(final String s) {
        String s2 = "";
        for (int i = 0; i < s.length(); ++i) {
            final String substring = s.substring(i, i + 1);
            if (substring.equals(" ")) {
                s2 = String.valueOf(s2) + m("\u000fAG");
            }
            else {
                s2 = String.valueOf(s2) + substring;
            }
        }
        return s2;
    }
    
    public static void a(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (Exception ex) {}
    }
    
    public static void destroy() {
        ds.e = null;
    }
    
    public static void e() {
        System.runFinalization();
        System.gc();
    }
    
    public static String a(String s, final Vector vector) {
        String s2 = "";
        if (vector != null) {
            vector.removeAllElements();
            if (s != null && s.trim().length() > 0) {
                final int index;
                if ((index = s.indexOf("(")) < 0) {
                    s2 = s.trim();
                }
                else {
                    s2 = s.substring(0, index).trim();
                    s = s.substring(index + 1);
                    int index2;
                    while ((index2 = s.indexOf(",")) >= 0) {
                        vector.addElement(s.substring(0, index2).trim());
                        if (index2 + 1 < s.length()) {
                            s = s.substring(index2 + 1);
                        }
                        else {
                            s = "";
                        }
                    }
                    s = s.substring(0, s.length() - 1).trim();
                    if (!s.equals("")) {
                        vector.addElement(s);
                    }
                }
            }
        }
        return s2;
    }
    
    private static String m(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = '*';
                            break;
                        }
                        case 1: {
                            c2 = 's';
                            break;
                        }
                        case 2: {
                            c2 = 'w';
                            break;
                        }
                        case 3: {
                            c2 = 'e';
                            break;
                        }
                        default: {
                            c2 = '\u000f';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
