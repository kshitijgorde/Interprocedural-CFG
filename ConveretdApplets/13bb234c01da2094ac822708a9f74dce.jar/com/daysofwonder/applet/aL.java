// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import com.daysofwonder.util.t;
import java.awt.image.ImageObserver;
import com.daysofwonder.b.b;
import com.daysofwonder.util.UIProperties;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import com.daysofwonder.b.a;
import java.util.StringTokenizer;
import com.daysofwonder.util.K;
import java.awt.FontMetrics;

public class aL
{
    private static final int[] a;
    private static final int[] b;
    private static final char[] c;
    
    public static K a(final String s, final int n, final FontMetrics fontMetrics) {
        return a(s, n, fontMetrics, true);
    }
    
    public static K a(final String s, final int n, final FontMetrics fontMetrics, final boolean b) {
        if (s == null) {
            return new K();
        }
        final K k = new K();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        final int countTokens = stringTokenizer.countTokens();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < countTokens; ++i) {
            final String nextToken = stringTokenizer.nextToken();
            if (fontMetrics.stringWidth(sb.toString() + nextToken) > n - 15) {
                if (b) {
                    k.a(sb.toString());
                }
                else {
                    k.c(sb.toString());
                }
                sb.setLength(0);
            }
            sb.append(nextToken).append(' ');
        }
        final String string = sb.toString();
        if (fontMetrics.stringWidth(string) >= n - 15) {
            sb.setLength(0);
            for (int length = string.length(), j = 0; j < length; ++j) {
                sb.append(string.charAt(j));
                if (fontMetrics.stringWidth(sb.toString()) >= n - 15) {
                    if (b) {
                        k.a(sb.toString());
                    }
                    else {
                        k.c(sb.toString());
                    }
                    sb.setLength(0);
                }
            }
        }
        if (b) {
            k.a(sb.toString());
        }
        else {
            k.c(sb.toString());
        }
        return k;
    }
    
    public static K a(final a a, final String s, final int n, final boolean b, final Font font, final Font font2) {
        if (s == null) {
            return new K();
        }
        final K k = new K();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " %", true);
        final StringBuffer sb = new StringBuffer();
        while (stringTokenizer.hasMoreTokens()) {
            String s2 = stringTokenizer.nextToken();
            if (s2.equals(" ")) {
                continue;
            }
            if (s2.equals("%") && stringTokenizer.hasMoreTokens()) {
                s2 += stringTokenizer.nextToken();
            }
            if (a(a, sb.toString() + s2, font, font2) > n - 5) {
                if (b) {
                    k.a(sb.toString());
                }
                else {
                    k.c(sb.toString());
                }
                sb.setLength(0);
            }
            sb.append(s2);
            sb.append(' ');
        }
        final String string = sb.toString();
        if (a(a, string, font, font2) >= n - 5) {
            sb.setLength(0);
            for (int length = string.length(), i = 0; i < length; ++i) {
                final char char1 = string.charAt(i);
                if (char1 != '%') {
                    sb.append(char1);
                }
                else if (i < length - 1) {
                    sb.append(char1).append(string.charAt(++i));
                }
                if (a(a, sb.toString(), font, font2) >= n - 5) {
                    if (b) {
                        k.a(sb.toString());
                    }
                    else {
                        k.c(sb.toString());
                    }
                    sb.setLength(0);
                }
            }
        }
        if (b) {
            k.a(sb.toString());
        }
        else {
            k.c(sb.toString());
        }
        return k;
    }
    
    private static int a(final a a, final String s, final Font font, final Font font2) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        final Font b = a.b();
        a.a(font2);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '%') {
                if (i < s.length() - 1) {
                    final char char2 = s.charAt(++i);
                    if (char2 == 'b') {
                        if (n3 == 0) {
                            n += a(a, sb);
                            a.a(font);
                            n3 = 1;
                        }
                        else {
                            n += a(a, sb);
                            a.a(font2);
                            n3 = 0;
                        }
                    }
                    else if (char2 == 'r') {
                        if (n2 == 0) {
                            n += a(a, sb);
                            a.a(Color.red);
                            n2 = 1;
                        }
                        else {
                            n += a(a, sb);
                            a.a(Color.black);
                            n2 = 0;
                        }
                    }
                    else {
                        sb.append(char1).append(char2);
                    }
                }
                else {
                    sb.append(char1);
                }
            }
            else {
                sb.append(char1);
            }
        }
        final int n4 = n + a(a, sb);
        a.a(b);
        return n4;
    }
    
    private static int a(final a a, final StringBuffer sb) {
        final int stringWidth = a.d().stringWidth(sb.toString());
        sb.setLength(0);
        return stringWidth;
    }
    
    public static boolean a(final a a, final String s, int n, final int n2, final Font font, final Font font2, boolean b) {
        int n3 = 0;
        final Font b2 = a.b();
        a.a(font2);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '%') {
                if (i < s.length() - 1) {
                    final char char2 = s.charAt(++i);
                    if (char2 == 'b') {
                        if (!b) {
                            n = a(a, n, n2, sb);
                            a.a(font);
                            b = true;
                        }
                        else {
                            n = a(a, n, n2, sb);
                            a.a(font2);
                            b = false;
                        }
                    }
                    else if (char2 == 'r') {
                        if (n3 == 0) {
                            n = a(a, n, n2, sb);
                            a.a(Color.red);
                            n3 = 1;
                        }
                        else {
                            n = a(a, n, n2, sb);
                            a.a(Color.black);
                            n3 = 0;
                        }
                    }
                    else {
                        sb.append(char1).append(char2);
                    }
                }
                else {
                    sb.append(char1);
                }
            }
            else {
                sb.append(char1);
            }
        }
        a(a, n, n2, sb);
        a.a(b2);
        return b;
    }
    
    public static void a(final a a, final String s, int n, final int n2, final Font font, final Font font2) {
        int n3 = 0;
        int n4 = 0;
        final Font b = a.b();
        a.a(font2);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '%') {
                if (i < s.length() - 1) {
                    final char char2 = s.charAt(++i);
                    if (char2 == 'b') {
                        if (n4 == 0) {
                            n = a(a, n, n2, sb);
                            a.a(font);
                            n4 = 1;
                        }
                        else {
                            n = a(a, n, n2, sb);
                            a.a(font2);
                            n4 = 0;
                        }
                    }
                    else if (char2 == 'r') {
                        if (n3 == 0) {
                            n = a(a, n, n2, sb);
                            a.a(Color.red);
                            n3 = 1;
                        }
                        else {
                            n = a(a, n, n2, sb);
                            a.a(Color.black);
                            n3 = 0;
                        }
                    }
                    else {
                        sb.append(char1).append(char2);
                    }
                }
                else {
                    sb.append(char1);
                }
            }
            else {
                sb.append(char1);
            }
        }
        a(a, n, n2, sb);
        a.a(b);
    }
    
    public static int a(final a a, int n, final int n2, final StringBuffer sb) {
        final String string = sb.toString();
        a.a(string, n, n2);
        n += a.d().stringWidth(string);
        sb.setLength(0);
        return n;
    }
    
    public static int b(final String s, final int n, final FontMetrics fontMetrics) {
        return (n - fontMetrics.stringWidth(s)) / 2;
    }
    
    public static int c(final String s, final int n, final FontMetrics fontMetrics) {
        return n - fontMetrics.stringWidth(s) - 4;
    }
    
    public static void a(final a a, final Rectangle rectangle, final String s, final Font font, final int n) {
        if (s == null || s.length() == 0) {
            return;
        }
        final Font b = a.b();
        a.a(font);
        final FontMetrics d = a.d();
        final Vector<String> vector = new Vector<String>();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        while (stringTokenizer.hasMoreTokens()) {
            vector.addElement(stringTokenizer.nextToken());
        }
        final int n2 = rectangle.height / vector.size();
        int n3 = d.getMaxAscent() + d.getMaxDescent() + 1;
        int n4 = 0;
        if (vector.size() * n3 > rectangle.height) {
            n3 = n2;
        }
        else {
            n4 = (n2 - n3) / 2;
        }
        int n5 = rectangle.y + d.getMaxAscent();
        final int x = rectangle.x;
        for (int i = 0; i < vector.size(); ++i) {
            final String s2 = vector.elementAt(i);
            int n6 = 2;
            if (n == 1) {
                n6 = b(s2, rectangle.width, d);
            }
            else if (n == 2) {
                n6 = c(s2, rectangle.width, d);
            }
            a.a(s2, x + n6, n5 + n4);
            n5 += n3;
        }
        a.a(b);
    }
    
    public static void a(final Graphics graphics, final Rectangle rectangle, final String s, final Font font, final int n) {
        if (s == null || s.length() == 0) {
            return;
        }
        final Font font2 = graphics.getFont();
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final Vector<String> vector = new Vector<String>();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        while (stringTokenizer.hasMoreTokens()) {
            vector.addElement(stringTokenizer.nextToken());
        }
        final int n2 = rectangle.height / vector.size();
        int n3 = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent() + 1;
        int n4 = 0;
        if (vector.size() * n3 > rectangle.height) {
            n3 = n2;
        }
        else {
            n4 = (n2 - n3) / 2;
        }
        int n5 = rectangle.y + fontMetrics.getMaxAscent();
        final int x = rectangle.x;
        for (int i = 0; i < vector.size(); ++i) {
            final String s2 = vector.elementAt(i);
            int n6 = 2;
            if (n == 1) {
                n6 = b(s2, rectangle.width, fontMetrics);
            }
            else if (n == 2) {
                n6 = c(s2, rectangle.width, fontMetrics);
            }
            graphics.drawString(s2, x + n6, n5 + n4);
            n5 += n3;
        }
        graphics.setFont(font2);
    }
    
    public static boolean a(final Rectangle rectangle, final Rectangle rectangle2) {
        return a(rectangle.x, rectangle.y, rectangle.width, rectangle.height, rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
    }
    
    public static boolean a(final int n, final int n2, int n3, int n4, final int n5, final int n6, int n7, int n8) {
        if ((n3 | n4 | n7 | n8) < 0) {
            return false;
        }
        if (n5 < n || n6 < n2) {
            return false;
        }
        n3 += n;
        n7 += n5;
        if (n7 <= n5) {
            if (n3 >= n || n7 > n3) {
                return false;
            }
        }
        else if (n3 >= n && n7 > n3) {
            return false;
        }
        n4 += n2;
        n8 += n6;
        if (n8 <= n6) {
            if (n4 >= n2 || n8 > n4) {
                return false;
            }
        }
        else if (n4 >= n2 && n8 > n4) {
            return false;
        }
        return true;
    }
    
    public static Rectangle a(final UIProperties uiProperties, final String s) {
        final int[] array = new int[4];
        int n = 0;
        final String a = uiProperties.a(s);
        if (a != null) {
            for (StringTokenizer stringTokenizer = new StringTokenizer(a, ","); stringTokenizer.hasMoreTokens() && n < 4; array[n++] = Integer.parseInt(stringTokenizer.nextToken().trim())) {}
            return new Rectangle(array[0], array[1], array[2], array[3]);
        }
        return null;
    }
    
    public static Color b(final UIProperties uiProperties, final String s) {
        final int[] array = new int[3];
        int n = 0;
        final String a = uiProperties.a(s);
        if (a == null) {
            return null;
        }
        for (StringTokenizer stringTokenizer = new StringTokenizer(a, ","); stringTokenizer.hasMoreTokens() && n < 3; array[n++] = Integer.parseInt(stringTokenizer.nextToken().trim())) {}
        return new Color(array[0], array[1], array[2]);
    }
    
    public static void a(final z z, final int n, final w w, final Rectangle rectangle, final Rectangle rectangle2) {
        float n2 = rectangle.x;
        float n3 = rectangle.y;
        float n4 = rectangle.width;
        float n5 = rectangle.height;
        final float n6 = (rectangle2.x - rectangle.x) / n;
        final float n7 = (rectangle2.y - rectangle.y) / n;
        final float n8 = (rectangle2.width - rectangle.width) / n;
        final float n9 = (rectangle2.height - rectangle.height) / n;
        z.a(Color.black);
        z.b(Color.white);
        for (int i = 0; i < n; ++i) {
            z.a((int)n2, (int)n3, (int)n4, (int)n5);
            z.b((int)n2, (int)n3, (int)n4 + 1, (int)n5 + 1);
            w.d();
            z.a((int)n2, (int)n3, (int)n4, (int)n5);
            n2 += n6;
            n3 += n7;
            n4 += n8;
            n5 += n9;
        }
        z.b();
    }
    
    public static Rectangle b(final Rectangle rectangle, final Rectangle rectangle2) {
        if (rectangle.x == 0 && rectangle.y == 0) {
            rectangle.x = rectangle2.x;
            rectangle.y = rectangle2.y;
            rectangle.width = rectangle2.width;
            rectangle.height = rectangle2.height;
            return rectangle;
        }
        return rectangle.union(rectangle2);
    }
    
    public static Rectangle c(final Rectangle rectangle, final Rectangle rectangle2) {
        return new Rectangle(rectangle.x + rectangle2.x, rectangle.y + rectangle2.y, rectangle2.width, rectangle2.height);
    }
    
    public static boolean d(final Rectangle rectangle, final Rectangle rectangle2) {
        int x = rectangle.x;
        int y = rectangle.y;
        final int x2 = rectangle2.x;
        final int y2 = rectangle2.y;
        long n = x + rectangle.width;
        long n2 = y + rectangle.height;
        final long n3 = x2 + rectangle2.width;
        final long n4 = y2 + rectangle2.height;
        if (x < x2) {
            x = x2;
        }
        if (y < y2) {
            y = y2;
        }
        if (n > n3) {
            n = n3;
        }
        if (n2 > n4) {
            n2 = n4;
        }
        long n5 = n - x;
        long n6 = n2 - y;
        if (n5 <= 0L || n6 <= 0L) {
            return false;
        }
        if (n5 < -2147483648L) {
            n5 = -2147483648L;
        }
        if (n6 < -2147483648L) {
            n6 = -2147483648L;
        }
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = (int)n5;
        rectangle.height = (int)n6;
        return true;
    }
    
    public static void a(final a a, final int n, final Rectangle rectangle, final Rectangle rectangle2, final b[] array) {
        if (n < 0) {
            return;
        }
        int i = n;
        int n2 = rectangle.x + rectangle.width + ((rectangle2 != null) ? rectangle2.x : 0);
        final int n3 = rectangle.y + ((rectangle2 != null) ? rectangle2.y : 0);
        while (i >= 65536) {
            final int n4 = i / 100;
            final int n5 = i - n4 * 100;
            i = n4;
            a.a(array[aL.b[n5]], n2, n3, null);
            final int n6 = n2 - array[aL.b[n5]].a(null);
            a.a(array[aL.a[n5]], n6, n3, null);
            n2 = n6 - array[aL.a[n5]].a(null);
        }
        while (i >= 100) {
            final int n7 = i * 52429 >>> 19;
            final int n8 = i - ((n7 << 3) + (n7 << 1));
            a.a(array[aL.c[n8]], n2, n3, null);
            n2 -= array[aL.c[n8]].a(null);
            i = n7;
        }
        if (i >= 10) {
            a.a(array[aL.b[i]], n2, n3, null);
            final int n9 = n2 - array[aL.b[i]].a(null);
            a.a(array[aL.a[i]], n9, n3, null);
            final int n10 = n9 - array[aL.a[i]].a(null);
        }
        else {
            a.a(array[aL.b[i]], n2, n3, null);
        }
    }
    
    public static int b(final a a, final int n, final Rectangle rectangle, final Rectangle rectangle2, final b[] array) {
        if (n < 0) {
            return 0;
        }
        int i;
        int n2;
        int n3;
        int n4;
        for (i = n, n2 = 0; i >= 65536; i = n3, n2 = n2 + array[aL.b[n4]].a(null) + array[aL.a[n4]].a(null)) {
            n3 = i / 100;
            n4 = i - n3 * 100;
        }
        while (i >= 100) {
            final int n5 = i * 52429 >>> 19;
            n2 += array[aL.c[i - ((n5 << 3) + (n5 << 1))]].a(null);
            i = n5;
        }
        int n6;
        if (i >= 10) {
            n6 = n2 + array[aL.b[i]].a(null) + array[aL.a[i]].a(null);
        }
        else {
            n6 = n2 + array[aL.b[i]].a(null);
        }
        int n7 = rectangle.x + rectangle.width / 2 + n6 / 2 + ((rectangle2 != null) ? rectangle2.x : 0);
        final int n8 = rectangle.y + ((rectangle2 != null) ? rectangle2.y : 0);
        int j = n;
        while (j >= 65536) {
            final int n9 = j / 100;
            final int n10 = j - n9 * 100;
            j = n9;
            final int n11 = n7 - array[aL.b[n10]].a(null);
            a.a(array[aL.b[n10]], n11, n8, null);
            n7 = n11 - array[aL.a[n10]].a(null);
            a.a(array[aL.a[n10]], n7, n8, null);
        }
        while (j >= 100) {
            final int n12 = j * 52429 >>> 19;
            final int n13 = j - ((n12 << 3) + (n12 << 1));
            n7 -= array[aL.c[n13]].a(null);
            a.a(array[aL.c[n13]], n7, n8, null);
            j = n12;
        }
        if (j >= 10) {
            final int n14 = n7 - array[aL.b[j]].a(null);
            a.a(array[aL.b[j]], n14, n8, null);
            a.a(array[aL.a[j]], n14 - array[aL.a[j]].a(null), n8, null);
        }
        else {
            a.a(array[aL.b[j]], n7 - array[aL.b[j]].a(null), n8, null);
        }
        return rectangle.x + rectangle.width / 2 - n6 / 2 + ((rectangle2 != null) ? rectangle2.x : 0);
    }
    
    public static int a(final int n, final int n2, final int n3) {
        final int max = Math.max(Math.max(n, n2), n3);
        final int min = Math.min(Math.min(n, n2), n3);
        final int n4 = ((max + min) * 240 + 255) / 510;
        int n5;
        int n6;
        if (max == min) {
            n5 = 0;
            n6 = 160;
        }
        else {
            if (n4 <= 120) {
                n5 = ((max - min) * 240 + (max + min) / 2) / (max + min);
            }
            else {
                n5 = ((max - min) * 240 + (510 - max - min) / 2) / (510 - max - min);
            }
            final int n7 = ((max - n) * 40 + (max - min) / 2) / (max - min);
            final int n8 = ((max - n2) * 40 + (max - min) / 2) / (max - min);
            final int n9 = ((max - n3) * 40 + (max - min) / 2) / (max - min);
            if (n == max) {
                n6 = n9 - n8;
            }
            else if (n2 == max) {
                n6 = 80 + n7 - n9;
            }
            else {
                n6 = 160 + n8 - n7;
            }
            if (n6 < 0) {
                n6 += 240;
            }
            if (n6 > 240) {
                n6 -= 240;
            }
        }
        return (n6 << 16) + (n4 << 8) + n5;
    }
    
    private static int c(final int n, final int n2, int n3) {
        if (n3 < 0) {
            n3 += 240;
        }
        if (n3 > 240) {
            n3 -= 240;
        }
        if (n3 < 40) {
            return n + ((n2 - n) * n3 + 20) / 40;
        }
        if (n3 < 120) {
            return n2;
        }
        if (n3 < 160) {
            return n + ((n2 - n) * (160 - n3) + 20) / 40;
        }
        return n;
    }
    
    public static int b(final int n, final int n2, final int n3) {
        int n6;
        int n5;
        int n4;
        if (n3 == 0) {
            n4 = (n5 = (n6 = n2 * 255 / 240));
            if (n != 160) {
                t.e("HLS2RGB: Error");
            }
        }
        else {
            int n7;
            if (n2 <= 120) {
                n7 = (n2 * (240 + n3) + 120) / 240;
            }
            else {
                n7 = n2 + n3 - (n2 * n3 + 120) / 240;
            }
            final int n8 = 2 * n2 - n7;
            n5 = (c(n8, n7, n + 80) * 255 + 120) / 240;
            n4 = (c(n8, n7, n) * 255 + 120) / 240;
            n6 = (c(n8, n7, n - 80) * 255 + 120) / 240;
        }
        return (n5 << 16) + (n4 << 8) + n6;
    }
    
    public static final int a(int n, int n2) {
        n &= 0xFEFEFE;
        n2 &= 0xFEFEFE;
        final int n3 = n + n2;
        final int n4 = n3 & 0x1010100;
        return n4 - (n4 >> 8) | n3;
    }
    
    static {
        a = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 };
        b = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        c = new char[] { '\0', '\u0001', '\u0002', '\u0003', '\u0004', '\u0005', '\u0006', '\u0007', '\b', '\t' };
    }
}
