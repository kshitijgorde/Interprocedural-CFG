// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Frame;
import java.awt.Image;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.applet.Applet;

public class a extends Applet
{
    public int a;
    public String b;
    
    public a() {
        this.b = d("gnG\u007f2O\u007f");
    }
    
    public double[] a(final int n, final double n2, final double n3, final boolean b, final int n4) {
        final double[] array = new double[n + 1];
        int n5 = 0;
        double n7;
        final double n6 = n7 = (n3 - n2) / (n - 1);
        double ceil;
        if (b) {
            final boolean b2 = n6 > 100.0;
            int n8 = 0;
            if (b2) {
                while (n7 > 1000.0) {
                    if (n8 >= 50) {
                        break;
                    }
                    n7 /= 10.0;
                    ++n8;
                }
            }
            else {
                while (n7 < 10.0 && n8 < 50) {
                    ++n5;
                    n7 *= 10.0;
                    ++n8;
                }
                --n5;
            }
            ceil = Math.ceil(n7 / 10.0) * 10.0;
            int n9 = 0;
            if (b2) {
                while (ceil <= n6) {
                    if (n9 >= 50) {
                        break;
                    }
                    if (ceil * 1.2 > n6) {
                        ceil *= 1.2;
                    }
                    else {
                        ceil *= 10.0;
                    }
                    ++n9;
                }
            }
            else {
                while (ceil >= n6 && n9 < 50) {
                    ceil /= 10.0;
                    ++n9;
                }
                ceil *= 10.0;
            }
        }
        else if (n4 == 0) {
            ceil = Math.ceil(n6);
        }
        else {
            n5 = n4;
            ceil = Math.ceil(n6 * n5 * 10.0) / (n5 * 10);
        }
        for (int i = 0; i < n; ++i) {
            array[i] = n2 + ceil * i;
        }
        if (n5 < 0) {
            n5 = 0;
        }
        array[n] = n5;
        return array;
    }
    
    public int a(final String s, final Font font, final int n) {
        return n / 2 - this.getFontMetrics(font).stringWidth(s) / 2;
    }
    
    public int a(final String s, final Font font) {
        return this.getFontMetrics(font).stringWidth(s) / 2;
    }
    
    public int b(final String s, final Font font) {
        if (s == null || s.length() == 0) {
            return 1;
        }
        if (font == null) {
            System.out.println(d("kH\u0001[5QdS$gkHuq(Ox\u000fR\"MlUv\u0013FsU$gmd\u0001x(M\u007f\u0001}/LxDp"));
            return 1;
        }
        return this.getFontMetrics(font).stringWidth(s);
    }
    
    public int c(final String s, final Font font) {
        return this.getFontMetrics(font).stringWidth(s);
    }
    
    public void a(final Graphics graphics, final String s, final int n, final int n2, final int n3, final int n4) {
        final Color color = new Color(192, 192, 192);
        final Color color2 = new Color(223, 223, 223);
        final Color color3 = new Color(128, 128, 128);
        graphics.setColor(color);
        graphics.fillRect(n, n2, n3, n4);
        graphics.setColor(color2);
        graphics.drawLine(n - 2, n2 - 2, n - 2, n2 + n4);
        graphics.drawLine(n - 2, n2 - 2, n + n3, n2 - 2);
        graphics.setColor(Color.white);
        graphics.drawLine(n - 1, n2 - 1, n - 1, n2 - 1 + n4);
        graphics.drawLine(n - 1, n2 - 1, n - 1 + n3, n2 - 1);
        graphics.setColor(Color.black);
        graphics.drawLine(n + 1 + n3, n2 - 2, n + 1 + n3, n2 + 1 + n4);
        graphics.drawLine(n - 2, n2 + 1 + n4, n + 1 + n3, n2 + 1 + n4);
        graphics.drawRect(n - 3, n2 - 3, n3 + 5, n4 + 5);
        graphics.setColor(color3);
        graphics.drawLine(n + n3, n2 - 1, n + n3, n2 + n4);
        graphics.drawLine(n - 1, n2 + n4, n + n3, n2 + n4);
        if (s.length() > 0) {
            final Font font = new Font(d("gnG\u007f2O\u007f"), 0, 12);
            graphics.setColor(Color.black);
            graphics.setFont(font);
            graphics.drawString(s, n + this.a(s, font, n3), n2 + 15);
        }
    }
    
    public void a(int n, int n2, final int n3, final int n4, final Graphics graphics, final n n5, final int n6, final Color color, final Color color2) {
        final int n7 = -3;
        final int n8 = -24;
        final String g = n5.g;
        if (g != null) {
            graphics.setFont(new Font(this.b, 0, n6));
            final FontMetrics fontMetrics = this.getFontMetrics(new Font(this.b, 0, n6));
            if (g.indexOf(d("\u0000iS")) == -1) {
                final int stringWidth = fontMetrics.stringWidth(g);
                if (n - n7 + stringWidth + 6 > n3) {
                    n = n3 - (n7 + stringWidth + 17);
                }
                if (n2 - n8 + n6 + 20 > n4) {
                    n += 20;
                    n2 -= 40;
                    if (n - n7 + stringWidth + 6 > n3) {
                        n = n3 - (n7 + stringWidth + 17);
                        n2 -= 22;
                    }
                }
                graphics.setColor(color);
                graphics.fillRect(n - n7, n2 - n8, stringWidth + 5, n6 + 2);
                graphics.setColor(Color.black);
                graphics.drawRect(n - n7 - 1, n2 - n8 - 1, stringWidth + 6, n6 + 3);
                graphics.drawString(g, n - n7 + 2, n2 - n8 + n6 - 1);
                return;
            }
            int n9 = 0;
            int stringWidth2 = 0;
            final String[] a = a(g, d("\u0000iS="));
            for (int n10 = 0; n10 < 25 && a[n10] != null; ++n10) {
                if (fontMetrics.stringWidth(a[n10]) > stringWidth2) {
                    stringWidth2 = fontMetrics.stringWidth(a[n10]);
                }
                ++n9;
            }
            final int n11 = stringWidth2;
            final int n12 = (n6 + 2) * n9;
            if (n - n7 + n11 + 6 > n3) {
                n = n3 - (n7 + n11 + 17);
            }
            if (n2 - n8 + n12 + 20 > n4) {
                n += 20;
                n2 -= 40;
                if (n - n7 + n11 + 6 > n3) {
                    n = n3 - (n7 + n11 + 17);
                    n2 -= 22;
                }
            }
            graphics.setColor(color);
            graphics.fillRect(n - n7, n2 - n8, n11 + 5, n12 + 2);
            graphics.setColor(Color.black);
            graphics.drawRect(n - n7 - 1, n2 - n8 - 1, n11 + 6, n12 + 3);
            for (int i = 0; i < n9; ++i) {
                graphics.drawString(a[i], n - n7 + 2, n2 - n8 - 1 + (i + 1) * (n6 + 2));
            }
        }
    }
    
    public void a(final int n, int n2, final Graphics graphics, final String s, int n3, final int n4) {
        int n5;
        for (n5 = 2; (s.length() - 1) * (n3 + n5) > n4 && n3 > 7; n5 = 1) {
            if (--n3 == 12) {}
        }
        final Font font = new Font(this.b, 1, n3);
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        graphics.setFont(font);
        if (this.a == 0) {
            for (int i = 0; i < s.length(); ++i) {
                if (fontMetrics.stringWidth(String.valueOf(s.charAt(i))) > this.a) {
                    this.a = fontMetrics.stringWidth(String.valueOf(s.charAt(i)));
                }
            }
            this.a /= 2;
        }
        n2 += (n4 - (s.length() - 1) * (n3 + n5)) / 2;
        for (int j = 0; j < s.length(); ++j) {
            graphics.drawString(String.valueOf(s.charAt(j)), n + this.a - fontMetrics.stringWidth(String.valueOf(s.charAt(j))) / 2, n2 + j * (n3 + n5));
        }
    }
    
    public boolean a(final m m, final int n, final int n2, final int n3) {
        final int[] array = new int[n];
        boolean b = false;
        final Font font = new Font(this.b, 0, n3);
        for (int i = 0; i < n; ++i) {
            if (m.b[i] != null) {
                array[i] = this.b(m.b[i], font);
            }
            else {
                array[i] = 0;
            }
        }
        for (int j = 0; j < n; ++j) {
            if (array[j] >= n2) {
                for (int n4 = (int)Math.ceil(array[j] / (n2 * 2)), k = 1; k <= n4; ++k) {
                    if (j - k >= 0 && m.b[j - k] != null && m.b[j - k].length() > 0) {
                        b = true;
                        break;
                    }
                    if (j + k < n && m.b[j + k] != null && m.b[j + k].length() > 0) {
                        b = true;
                        break;
                    }
                }
            }
        }
        return b;
    }
    
    public boolean a(final o o, final int n, final int n2, final int n3) {
        final int[] array = new int[n];
        boolean b = false;
        final Font font = new Font(this.b, 0, n3);
        for (int i = 0; i < n; ++i) {
            if (o.a[i] != null && o.a[i].b != null) {
                array[i] = this.b(o.a[i].b, font);
            }
            else {
                array[i] = 0;
            }
        }
        for (int j = 0; j < n; ++j) {
            if (array[j] >= n2) {
                b = true;
                break;
            }
        }
        return b;
    }
    
    public int a(final String s) {
        final String upperCase = s.toUpperCase();
        int n = '\0';
        final char char1 = upperCase.charAt(0);
        if (char1 >= '0' && char1 <= '9') {
            n += (char1 - '0') * '\u0010';
        }
        else if (char1 >= 'A' && char1 <= 'F') {
            n += (char1 - 'A' + '\n') * '\u0010';
        }
        final char char2 = upperCase.charAt(1);
        if (char2 >= '0' && char2 <= '9') {
            n += char2 - '0';
        }
        else if (char2 >= 'A' && char2 <= 'F') {
            n += char2 - 'A' + '\n';
        }
        return n;
    }
    
    public Color b(final String s) {
        int n = 255;
        int n2 = 255;
        int n3 = 255;
        final String upperCase = s.toUpperCase();
        if (upperCase.length() == 6) {
            n = this.a(upperCase.substring(0, 2));
            n2 = this.a(upperCase.substring(2, 4));
            n3 = this.a(upperCase.substring(4, 6));
        }
        if (upperCase.length() == 7) {
            n = this.a(upperCase.substring(1, 3));
            n2 = this.a(upperCase.substring(3, 5));
            n3 = this.a(upperCase.substring(5, 7));
        }
        return new Color(n, n2, n3);
    }
    
    public boolean c(final String s) {
        if (s == null) {
            return false;
        }
        final String upperCase = s.toUpperCase();
        boolean b = true;
        if (upperCase.length() == 6 || upperCase.length() == 7) {
            int n = 0;
            if (upperCase.length() == 7) {
                if (upperCase.charAt(0) != '#') {
                    b = false;
                }
                n = 1;
            }
            for (int i = n; i < upperCase.length(); ++i) {
                final char char1 = upperCase.charAt(i);
                if (char1 != '0' && char1 != '1' && char1 != '2' && char1 != '3' && char1 != '4' && char1 != '5' && char1 != '6' && char1 != '7' && char1 != '8' && char1 != '9' && char1 != 'A' && char1 != 'B' && char1 != 'C' && char1 != 'D' && char1 != 'E' && char1 != 'F') {
                    b = false;
                }
            }
        }
        else {
            b = false;
        }
        return b;
    }
    
    public static String[] a(final String s, final String s2) {
        final String[] array = new String[25];
        int n = 0;
        int n2 = 0;
        int n3 = s.indexOf(s2, n2);
        do {
            array[n] = s.substring(n2, n3);
            ++n;
            n2 = n3 + s2.length();
            n3 = s.indexOf(s2, n2);
        } while (n3 != -1 && n2 < s.length());
        array[n] = s.substring(n2);
        return array;
    }
    
    public void a(final Graphics graphics, final b b) {
        final Font font = new Font(this.b, 1, b.c + 4);
        graphics.setFont(font);
        graphics.setColor(b.h);
        if (b.F != null) {
            graphics.drawString(b.F, this.a(b.F, font, b.d), b.c + 10);
        }
    }
    
    public URL a(final String s, final URL url) {
        try {
            return new URL(s);
        }
        catch (Exception ex) {
            try {
                final String string = url.toString();
                if (s.startsWith("/")) {
                    if (string.startsWith(d("K\u007fUn}\f$"))) {
                        final int index = string.indexOf("/", 7);
                        if (index != -1) {
                            return new URL(String.valueOf(string.substring(0, index)) + s);
                        }
                    }
                }
                else {
                    final int lastIndex = string.lastIndexOf(47);
                    if (lastIndex != -1) {
                        return new URL(String.valueOf(string.substring(0, lastIndex + 1)) + s);
                    }
                }
                return new URL(String.valueOf(string) + s);
            }
            catch (Exception ex2) {
                return null;
            }
        }
    }
    
    public Image a(final String s, final Font font, final boolean b, final Color color, final Color color2) {
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        final int n = fontMetrics.stringWidth(s) + 5;
        final int height = fontMetrics.getHeight();
        final int ascent = fontMetrics.getAscent();
        if (n <= 1 || height <= 1) {
            return null;
        }
        Image image = this.createImage(n, height);
        if (image == null) {
            final Frame frame = new Frame();
            frame.addNotify();
            image = frame.createImage(n, height);
            frame.dispose();
        }
        if (image == null) {
            return null;
        }
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, n, height);
        graphics.setColor(Color.black);
        graphics.setFont(font);
        graphics.drawString(s, 1, ascent);
        return this.a(image, n, height, b, color, color2);
    }
    
    public Image a(final Image image, final int n, final int n2, final boolean b, final Color color, final Color color2) {
        final int[] array = new int[n * n2];
        final int[] array2 = new int[n2 * n];
        try {
            new PixelGrabber(image, 0, 0, n, n2, array, 0, n).grabPixels();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        final int n3 = array[0];
        final int rgb = color.getRGB();
        final int rgb2 = color2.getRGB();
        if (b) {
            for (int i = 0; i < n2; ++i) {
                for (int j = 0; j < n; ++j) {
                    array2[j * n2 + (n2 - 1 - i)] = ((array[i * n + j] == n3) ? rgb2 : rgb);
                }
            }
        }
        else {
            for (int k = 0; k < n2; ++k) {
                for (int l = 0; l < n; ++l) {
                    array2[(n - 1 - l) * n2 + k] = ((array[k * n + l] == n3) ? rgb2 : rgb);
                }
            }
        }
        return this.createImage(new MemoryImageSource(n2, n, array2, 0, n2));
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final Color color) {
        graphics.setColor(color);
        if (n5 == 1) {
            graphics.drawLine(n, n2, n3, n4);
            return;
        }
        final int n6 = n3 - n;
        final int n7 = n4 - n2;
        final double n8 = n5 / (2.0 * Math.sqrt(n6 * n6 + n7 * n7));
        final double n9 = -n8 * n7;
        final double n10 = n8 * n6;
        final double n11 = n9 + ((n9 > 0.0) ? 0.5 : -0.5);
        final double n12 = n10 + ((n10 > 0.0) ? 0.5 : -0.5);
        final int n13 = (int)n11;
        final int n14 = (int)n12;
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        array[0] = n + n13;
        array2[0] = n2 + n14;
        array[1] = n - n13;
        array2[1] = n2 - n14;
        array[2] = n3 - n13;
        array2[2] = n4 - n14;
        array[3] = n3 + n13;
        array2[3] = n4 + n14;
        graphics.fillPolygon(array, array2, 4);
    }
    
    private static String d(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '#';
                    break;
                }
                case 1: {
                    c2 = '\u000b';
                    break;
                }
                case 2: {
                    c2 = '!';
                    break;
                }
                case 3: {
                    c2 = '\u001e';
                    break;
                }
                default: {
                    c2 = 'G';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
