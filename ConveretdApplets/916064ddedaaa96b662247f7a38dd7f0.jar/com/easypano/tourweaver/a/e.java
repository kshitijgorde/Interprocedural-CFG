// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.a;

import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.util.StringTokenizer;
import java.net.URL;
import java.awt.Label;

public final class e
{
    public static final double a = 6.283185307179586;
    public static final double b = 0.017453292519943295;
    public static final double c = 1.0E-6;
    private static volatile int d;
    private static volatile int e;
    public static Label f;
    public static URL g;
    public static URL h;
    public static int i;
    public static byte[] j;
    public static byte[] k;
    public static int l;
    private static String[] z;
    
    public static synchronized void a() {
        ++com.easypano.tourweaver.a.e.d;
        ++com.easypano.tourweaver.a.e.e;
    }
    
    public static synchronized void b() {
        --com.easypano.tourweaver.a.e.d;
    }
    
    public static void a(final byte[] array) {
        final int l = com.easypano.tourweaver.a.e.l;
        byte[] array2 = com.easypano.tourweaver.a.e.k;
        int length = array.length;
        int n = array2.length;
        final int n2 = length;
        Label_0164: {
            if (l != 0 || n2 > 0) {
                int i = n2;
                int n3 = 0;
                while (true) {
                    while (i < length) {
                        final int n4 = i;
                        array[n4] ^= array2[n3];
                        ++i;
                        if (++n3 >= n) {
                            n3 = 0;
                            if (l == 0) {
                                if (l != 0) {
                                    break;
                                }
                                continue;
                            }
                            else {
                                final int n5 = length;
                                if (l == 0 && n5 <= 0) {
                                    return;
                                }
                                final byte[] array3 = new byte[n5];
                                int j = 0;
                            Label_0159_Outer:
                                while (j < length) {
                                    System.arraycopy(array, j, array3, 0, n);
                                    int k = 0;
                                    while (true) {
                                        while (k < n) {
                                            array[j + k] = array3[array2[k]];
                                            ++k;
                                            if (l == 0) {
                                                if (l != 0) {
                                                    break;
                                                }
                                                continue Label_0159_Outer;
                                            }
                                            else {
                                                if (l != 0) {
                                                    break Label_0164;
                                                }
                                                continue Label_0159_Outer;
                                            }
                                        }
                                        j += n;
                                        continue;
                                    }
                                }
                                return;
                            }
                        }
                    }
                    array2 = com.easypano.tourweaver.a.e.j;
                    n = array2.length;
                    length -= n;
                    continue;
                }
            }
        }
    }
    
    public static synchronized int c() {
        return com.easypano.tourweaver.a.e.d;
    }
    
    public static synchronized int d() {
        return com.easypano.tourweaver.a.e.e;
    }
    
    public static double a(final double n) {
        return n * 3.141592653589793 / 180.0;
    }
    
    public static String a(String s, final String s2, final String s3) {
        final int l = com.easypano.tourweaver.a.e.l;
        int index;
        String string = null;
        while ((index = s.indexOf(s2)) != -1) {
            string = s.substring(0, index) + s3 + s.substring(index + s2.length());
            if (l != 0) {
                return string;
            }
            s = string;
            if (l != 0) {
                break;
            }
        }
        return string;
    }
    
    public static String a(final String s) {
        final int l = com.easypano.tourweaver.a.e.l;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "/");
        String string = "";
        String string2 = null;
        while (stringTokenizer.hasMoreElements()) {
            string += stringTokenizer.nextElement();
            if (stringTokenizer.hasMoreElements()) {
                string2 = string + "/";
                if (l != 0) {
                    return string2;
                }
                string = string2;
                if (l != 0) {
                    break;
                }
                continue;
            }
        }
        return string2;
    }
    
    public static void a(final Image image) {
        if (image != null) {
            final MediaTracker mediaTracker = new MediaTracker(com.easypano.tourweaver.a.e.f);
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
    
    public static boolean b(final double n) {
        final int l = com.easypano.tourweaver.a.e.l;
        final double n2 = dcmpg(n, 1.0E-6);
        if (l == 0) {
            if (n2 < 0) {
                final double n3 = dcmpl(n, -1.0E-6);
                if (l == 0) {
                    if (n3 > 0) {
                        return true;
                    }
                }
            }
        }
        return n2 != 0.0;
    }
    
    public static int[] b(final Image image) {
        final int l = com.easypano.tourweaver.a.e.l;
        Image image2 = image;
        if (l == 0) {
            if (image == null) {
                return null;
            }
            image2 = image;
        }
        final int width = image2.getWidth(com.easypano.tourweaver.a.e.f);
        final int height = image.getHeight(com.easypano.tourweaver.a.e.f);
        int n2;
        final int n = n2 = width;
        if (l == 0) {
            if (n <= 0) {
                return null;
            }
            final int n3;
            n2 = (n3 = height);
        }
        if (l == 0) {
            if (n <= 0) {
                return null;
            }
            n2 = width * height;
        }
        int[] array = new int[n2];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex) {
            array = null;
        }
        return array;
    }
    
    public static Color b(final String s) {
        final int l = com.easypano.tourweaver.a.e.l;
        final int[] array = new int[4];
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        int n = 0;
        int n2;
        int n3;
        Label_0084:Label_0087_Outer:
        while (true) {
            while (true) {
                while (stringTokenizer.hasMoreElements()) {
                    try {
                        array[n++] = Integer.parseInt(stringTokenizer.nextToken().trim());
                        if (l == 0) {
                            continue Label_0087_Outer;
                        }
                        break Label_0084;
                    }
                    catch (Exception ex) {
                        return null;
                    }
                    break;
                    n2 = array[0];
                    if (n3 == 0) {
                        return null;
                    }
                    return new Color(array[1], array[2], array[3]);
                }
                n3 = n;
                if (l != 0) {
                    continue;
                }
                break;
            }
            if (n3 == 3) {
                return new Color(array[0], array[1], array[2]);
            }
            continue Label_0084;
        }
    }
    
    public static void a(final Hashtable hashtable) {
        final int l = com.easypano.tourweaver.a.e.l;
        final Enumeration<Object> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            hashtable.remove(keys.nextElement());
            if (l != 0) {
                break;
            }
        }
    }
    
    public static Rectangle c(String s) {
        final int l = com.easypano.tourweaver.a.e.l;
        int intValue = 0;
        int intValue2 = 0;
        int intValue3 = 0;
        int n = 0;
        final String s2 = s;
        if (l == 0) {
            if (s2 == null) {
                return new Rectangle(intValue, intValue2, intValue3, n);
            }
        }
        try {
            int n2 = s2.indexOf(",");
            int intValue4;
            int n4;
            final int n3 = n4 = (intValue4 = n2);
            if (l == 0) {
                if (n3 > 0) {
                    intValue = Integer.valueOf(s.substring(0, n2).trim());
                    s = s.substring(n2 + 1);
                }
                n2 = s.indexOf(",");
                final int n5;
                n4 = (n5 = (intValue4 = n2));
            }
            if (l == 0) {
                if (n3 > 0) {
                    intValue2 = Integer.valueOf(s.substring(0, n2).trim());
                    s = s.substring(n2 + 1);
                }
                n2 = s.indexOf(",");
                intValue4 = (n4 = n2);
            }
            if (l == 0) {
                if (n4 <= 0) {
                    return new Rectangle(intValue, intValue2, intValue3, n);
                }
                intValue3 = Integer.valueOf(s.substring(0, n2).trim());
                intValue4 = Integer.valueOf(s.substring(n2 + 1).trim());
            }
            n = intValue4;
        }
        catch (Exception ex) {}
        return new Rectangle(intValue, intValue2, intValue3, n);
    }
    
    public static Integer d(final String s) {
        try {
            return new Integer(s);
        }
        catch (Exception ex) {
            return new Integer(-1);
        }
    }
    
    public static boolean e(final String s) {
        final int l = com.easypano.tourweaver.a.e.l;
        boolean b = true;
        try {
            final boolean equalsIgnoreCase = s.equalsIgnoreCase(com.easypano.tourweaver.a.e.z[0]);
            if (l == 0 && equalsIgnoreCase) {
                b = true;
                if (l != 0) {
                    goto Label_0028;
                }
            }
            else {
                b = equalsIgnoreCase;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    public static void a(final URL g) {
        com.easypano.tourweaver.a.e.g = g;
    }
    
    public static void b(final URL h) {
        com.easypano.tourweaver.a.e.h = h;
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
    
    public static URL f(final String s) {
        String s2 = s;
        if (com.easypano.tourweaver.a.e.l == 0) {
            if (s == null) {
                return null;
            }
            s2 = s;
        }
        if (!s2.equals("")) {
            try {
                return new URL(com.easypano.tourweaver.a.e.g, s);
            }
            catch (MalformedURLException ex) {
                return com.easypano.tourweaver.a.e.g;
            }
        }
        return null;
    }
    
    public static String g(final String s) {
        final int l = com.easypano.tourweaver.a.e.l;
        String string = "";
        int i = 0;
        while (i < s.length()) {
            final String substring = s.substring(i, i + 1);
            final String s3;
            final String s2 = s3 = substring;
            if (l != 0) {
                return s3;
            }
            Label_0096: {
                if (l == 0) {
                    if (s2.equals(" ")) {
                        string += com.easypano.tourweaver.a.e.z[1];
                        if (l == 0) {
                            break Label_0096;
                        }
                    }
                    new StringBuffer().append(string).append(substring).toString();
                }
                string = s2;
            }
            ++i;
            if (l != 0) {
                break;
            }
        }
        return string;
    }
    
    public static void a(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (Exception ex) {}
    }
    
    public static String h(String s) {
        final String lowerCase = s.toLowerCase();
        if (com.easypano.tourweaver.a.e.l == 0) {
            if (lowerCase.startsWith(com.easypano.tourweaver.a.e.z[2])) {
                return s;
            }
            final String replace;
            s = (replace = s.replace('/', '\\'));
        }
        return lowerCase;
    }
    
    public static int a(final String s, final int n) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            int1 = n;
        }
        return int1;
    }
    
    public static double a(final String s, final double n) {
        double doubleValue;
        try {
            doubleValue = new Double(s);
        }
        catch (NumberFormatException ex) {
            doubleValue = n;
        }
        return doubleValue;
    }
    
    public static long a(final String s, final long n) {
        long long1;
        try {
            long1 = Long.parseLong(s);
        }
        catch (NumberFormatException ex) {
            long1 = n;
        }
        return long1;
    }
    
    public static Image a(final Image image, final int n, final int n2, final int n3, final int n4) {
        final Image image2 = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n, n2, n3, n4)));
        a(image2);
        return image2;
    }
    
    public static int c(final Image image) {
        a(image);
        return image.getWidth(com.easypano.tourweaver.a.e.f);
    }
    
    public static int d(final Image image) {
        a(image);
        return image.getHeight(com.easypano.tourweaver.a.e.f);
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "A+zb".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '5';
                            break;
                        }
                        case 1: {
                            c2 = 'Y';
                            break;
                        }
                        case 2: {
                            c2 = '\u000f';
                            break;
                        }
                        case 3: {
                            c2 = '\u0007';
                            break;
                        }
                        default: {
                            c2 = '\u0017';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "\u0010k?".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '5';
                            break;
                        }
                        case 1: {
                            c4 = 'Y';
                            break;
                        }
                        case 2: {
                            c4 = '\u000f';
                            break;
                        }
                        case 3: {
                            c4 = '\u0007';
                            break;
                        }
                        default: {
                            c4 = '\u0017';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "]-{w".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '5';
                            break;
                        }
                        case 1: {
                            c6 = 'Y';
                            break;
                        }
                        case 2: {
                            c6 = '\u000f';
                            break;
                        }
                        case 3: {
                            c6 = '\u0007';
                            break;
                        }
                        default: {
                            c6 = '\u0017';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                com.easypano.tourweaver.a.e.z = z;
                com.easypano.tourweaver.a.e.d = 0;
                com.easypano.tourweaver.a.e.e = 0;
                com.easypano.tourweaver.a.e.f = new Label();
                com.easypano.tourweaver.a.e.g = null;
                com.easypano.tourweaver.a.e.h = null;
                com.easypano.tourweaver.a.e.i = 0;
                com.easypano.tourweaver.a.e.j = new byte[] { 16, 19, 17, 7, 20, 13, 23, 1, 24, 8, 6, 18, 0, 15, 2, 21, 9, 14, 3, 10, 5, 12, 25, 4, 22, 11 };
                com.easypano.tourweaver.a.e.k = new byte[] { 89, 76, 34, 3, -100, -19, 45, -78, 122, 73, 11, 83, 55, -58, 21, 45, 81, -123, 127, -9, 119, 48, 121, 28, 101, 77 };
                return;
            }
            continue;
        }
    }
}
