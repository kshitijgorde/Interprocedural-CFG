// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Locale;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Frame;
import java.awt.Image;
import java.net.URL;
import java.lang.reflect.Array;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.text.NumberFormat;
import java.applet.Applet;

public class a extends Applet
{
    public int a;
    public String b;
    
    public a() {
        this.b = e("\"?!\\S\n.");
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
    
    public double[] a(double n, double n2, final boolean b, final boolean b2, final int n3, final boolean b3) {
        int n4 = 0;
        int n5 = 0;
        boolean b4 = false;
        boolean b5 = false;
        boolean b6 = false;
        final int[] array = new int[11];
        final double n6 = n;
        final double n7 = n2;
        if (n > 0.0 && n2 < 0.0) {
            b4 = true;
        }
        if (!b && !b2) {
            if (n > 0.0 && n2 > 0.0) {
                n2 = 0.0;
            }
            else if (n < 0.0 && n2 < 0.0) {
                n = 0.0;
            }
            n2 = this.c(n2);
            n = this.c(n);
            b6 = true;
        }
        else if (b && !b2) {
            if (n2 < 0.0 && n < 0.0) {
                n = 0.0;
            }
            n = this.b(n);
        }
        else if (!b && b2) {
            if (n > 0.0 && n2 > 0.0) {
                n2 = 0.0;
            }
            n2 = this.b(n2);
        }
        double f = this.f(n, n2);
        for (int i = 2; i < 11; ++i) {
            final double c = this.c(f, i);
            array[i] = this.h(c);
            if (b4) {
                final double n8 = this.a(c, i, n2);
                if (n8 > 0.0) {
                    b5 = true;
                    final int[] array2 = array;
                    final int n9 = i;
                    array2[n9] += (int)n8;
                }
            }
        }
        for (int j = 3; j < 11; ++j) {
            if (array[j] > n4) {
                n4 = array[j];
                n5 = j;
            }
        }
        if (b4 && !b5 && !b && !b2) {
            final double n10 = n;
            final double n11 = n2;
            n = Math.max(n, Math.abs(n2));
            n2 = n * -1.0;
            f = n - n2;
            n5 = 4;
            if (n11 >= n2 + this.c(f, 4.0)) {
                n2 += this.c(f, 4.0);
                f = n - n2;
                n5 = 3;
            }
            else if (n10 <= n - this.c(f, 4.0)) {
                n -= this.c(f, 4.0);
                f = n - n2;
                n5 = 3;
            }
        }
        double n12;
        int n13;
        double n14;
        double n15;
        if (n5 == 0) {
            if (b || b2) {
                n12 = this.c(f, 5.0);
                n13 = 5;
                n14 = n;
                n15 = n2;
            }
            else {
                final double ceil = Math.ceil(this.c(f, 5.0));
                final int n16 = (int)Math.ceil(this.c(n, ceil));
                final int n17 = (int)Math.floor(this.c(n2, ceil));
                n14 = ceil * n16;
                n15 = ceil * n17;
                n12 = ceil;
                n13 = Math.abs(n16) + Math.abs(n17);
                n = f - n;
                n2 = f - n2;
            }
        }
        else {
            n12 = this.c(f, n5);
            n13 = n5;
            if (b6) {
                if (n != 0.0 && n - n12 > n6) {
                    n -= n12;
                    --n13;
                }
                if (n2 < 0.0 && n2 + n12 < n7) {
                    n2 += n12;
                    --n13;
                }
            }
            n14 = n;
            n15 = n2;
        }
        int g = this.g(n12);
        if (b3) {
            g = n3;
        }
        return new double[] { n14, n15, n12, n13, g };
    }
    
    public void a(final double n) {
        this.g(n);
    }
    
    public double b(double n) {
        if (n == 0.0) {
            return 0.0;
        }
        if (n > 0.0) {
            if (n > 0.0 && n < 1.0) {
                n = this.f(n);
            }
            else {
                n = Math.ceil(n);
            }
        }
        else if (n < 0.0 && n > -1.0) {
            n = this.f(n);
        }
        else {
            n = Math.floor(n);
        }
        return n;
    }
    
    public double c(final double n) {
        final double d = this.d(n);
        if (n == 0.0) {
            return 0.0;
        }
        double n2;
        if (n >= 0.0) {
            n2 = this.d(Math.ceil(this.c(n, Math.pow(10.0, d))), Math.pow(10.0, d));
        }
        else {
            n2 = this.d(Math.floor(this.c(n, Math.pow(10.0, d))), Math.pow(10.0, d));
        }
        return n2;
    }
    
    public void a(double n, double n2) {
        int n3 = 0;
        double n4 = 0.0;
        final double n5 = n - n2;
        for (int i = 3; i < 11; ++i) {
            final double c = this.c(n5, i);
            for (int j = 0; j <= i; ++j) {
                final double n6 = n2 + j * c;
                if (n3 == 0) {
                    n3 = i;
                    n4 = n6;
                }
                else if (Math.abs(n6) < Math.abs(n4)) {
                    n3 = i;
                    n4 = n6;
                }
            }
        }
        if (n4 > 0.0) {
            n2 += n4;
            return;
        }
        n += n4;
    }
    
    public void b(double n, final double n2) {
        final double n3 = n * (int)Math.ceil(Math.abs(n2) / n);
        if (Math.abs(n2) > Math.abs(n) / 4.0) {
            n = Math.abs(n2) / 4.0;
        }
    }
    
    public double d(double abs) {
        if (abs == 0.0) {
            return 0.0;
        }
        abs = Math.abs(abs);
        return Math.floor(Math.log(abs) / Math.log(10.0));
    }
    
    public int e(final double n) {
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setParseIntegerOnly(true);
        instance.setGroupingUsed(false);
        return instance.format(n).length();
    }
    
    public double c(final double n, final double n2) {
        if (n2 == 0.0) {
            return 0.0;
        }
        final double d = this.d(n);
        final double d2 = this.d(n2);
        double n3;
        if (d < 0.0 || d2 < 0.0) {
            n3 = Math.min(d, d2);
        }
        else {
            n3 = Math.max(d, d2);
        }
        if (n3 <= 0.0) {
            final double n4 = Math.abs(n3) + 4.0;
            return n * Math.pow(10.0, n4) / (n2 * Math.pow(10.0, n4));
        }
        return n / n2;
    }
    
    public double d(final double n, final double n2) {
        if (n2 == 0.0 || n == 0.0) {
            return 0.0;
        }
        final double d = this.d(n);
        final double d2 = this.d(n2);
        double n3;
        if (d < 0.0 || d2 < 0.0) {
            n3 = Math.min(d, d2);
        }
        else {
            n3 = Math.max(d, d2);
        }
        if (n3 <= 0.0) {
            final double n4 = Math.abs(n3) + 4.0;
            return n * Math.pow(10.0, n4) * (n2 * Math.pow(10.0, n4)) / Math.pow(10.0, n4 * 2.0);
        }
        return n * n2;
    }
    
    public double e(final double n, final double n2) {
        final double d = this.d(n);
        final double d2 = this.d(n2);
        double n3;
        if (d < 0.0 || d2 < 0.0) {
            n3 = Math.min(d, d2);
        }
        else {
            n3 = Math.max(d, d2);
        }
        if (n3 <= 0.0) {
            final double n4 = Math.abs(n3) + 4.0;
            return (n * Math.pow(10.0, n4) + n2 * Math.pow(10.0, n4)) / Math.pow(10.0, n4);
        }
        return n + n2;
    }
    
    public double f(final double n, final double n2) {
        final double d = this.d(n);
        final double d2 = this.d(n2);
        double n3;
        if (d < 0.0 || d2 < 0.0) {
            n3 = Math.min(d, d2);
        }
        else {
            n3 = Math.max(d, d2);
        }
        if (n3 <= 0.0) {
            final double n4 = Math.abs(n3) + 3.0;
            return (n * Math.pow(10.0, n4) - n2 * Math.pow(10.0, n4)) / Math.pow(10.0, n4);
        }
        return n - n2;
    }
    
    public double g(final double n, final double n2) {
        final double d = this.d(n);
        final double d2 = this.d(n2);
        double n3;
        if (d < 0.0 || d2 < 0.0) {
            n3 = Math.min(d, d2);
        }
        else {
            n3 = Math.max(d, d2);
        }
        if (n3 <= 0.0) {
            final double n4 = Math.abs(n3) + 3.0;
            return n * Math.pow(10.0, n4) % (n2 * Math.pow(10.0, n4)) / Math.pow(10.0, n4);
        }
        return n % n2;
    }
    
    public double f(final double n) {
        boolean b = false;
        int n2 = 0;
        if (n < 0.0) {
            b = true;
        }
        final double abs = Math.abs(n);
        if (abs <= 0.0 || abs >= 1.0) {
            return n;
        }
        double n3 = abs;
        for (int i = 1; i < 100; ++i) {
            n3 *= 10.0;
            if (n3 > 1.0) {
                n2 = i;
                break;
            }
        }
        final double n4 = abs * Math.pow(10.0, n2);
        double n5;
        if (b) {
            n5 = Math.floor(n4);
        }
        else {
            n5 = Math.ceil(n4);
        }
        final double n6 = n5 / Math.pow(10.0, n2);
        if (b) {
            return -1.0 * n6;
        }
        return n6;
    }
    
    public int g(final double n) {
        int n2;
        if (this.g(n, 1.0) == 0.0) {
            n2 = 0;
        }
        else if (this.g(n, 0.1) == 0.0) {
            n2 = 1;
        }
        else if (this.g(n, 0.01) == 0.0) {
            n2 = 2;
        }
        else if (this.g(n, 0.001) == 0.0) {
            n2 = 3;
        }
        else if (this.g(n, 1.0E-4) == 0.0) {
            n2 = 4;
        }
        else if (this.g(n, 1.0E-5) == 0.0) {
            n2 = 5;
        }
        else if (this.g(n, 1.0E-6) == 0.0) {
            n2 = 6;
        }
        else if (this.g(n, 1.0E-7) == 0.0) {
            n2 = 7;
        }
        else if (this.g(n, 1.0E-8) == 0.0) {
            n2 = 8;
        }
        else if (this.g(n, 1.0E-9) == 0.0) {
            n2 = 9;
        }
        else if (this.g(n, 1.0E-10) == 0.0) {
            n2 = 10;
        }
        else if (this.g(n, 1.0E-11) == 0.0) {
            n2 = 11;
        }
        else if (this.g(n, 1.0E-12) == 0.0) {
            n2 = 12;
        }
        else if (this.g(n, 1.0E-13) == 0.0) {
            n2 = 13;
        }
        else if (this.g(n, 1.0E-14) == 0.0) {
            n2 = 14;
        }
        else if (this.g(n, 1.0E-15) == 0.0) {
            n2 = 15;
        }
        else if (this.g(n, 1.0E-16) == 0.0) {
            n2 = 16;
        }
        else if (this.g(n, 1.0E-17) == 0.0) {
            n2 = 17;
        }
        else if (this.g(n, 1.0E-18) == 0.0) {
            n2 = 18;
        }
        else if (this.g(n, 1.0E-19) == 0.0) {
            n2 = 19;
        }
        else {
            n2 = 20;
        }
        return n2;
    }
    
    public int h(final double n) {
        int n2 = 0;
        if (n % 1.0 == 0.0) {
            n2 += 30;
        }
        for (int length = new Double(n - n % 1.0).toString().length(), n3 = 1; n3 < length && n % Math.pow(10.0, n3) == 0.0; ++n3) {
            n2 += 100;
        }
        final String string = new Double(n).toString();
        return n2 + (20 - (string.length() - string.lastIndexOf(".")));
    }
    
    public int a(final double n, final int n2, final double n3) {
        for (int i = 0; i <= n2; ++i) {
            if (i * n + n3 == 0.0) {
                return 1000;
            }
        }
        return 0;
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
            System.out.println(e(".\u0019gxT\u001455\u0007\u0006.\u0019\u0013RI\n)iuC\u000f=/Ir\u0003\"3\u0007\u0006(5g[I\b.g^N\t)\"S"));
            return 1;
        }
        return this.getFontMetrics(font).getHeight();
    }
    
    public int c(final String s, final Font font) {
        if (s == null || s.length() == 0) {
            return 1;
        }
        if (font == null) {
            System.out.println(e(".\u0019gxT\u001455\u0007\u0006.\u0019\u0013RI\n)iqC\b=3Ur\u0003\"3\u0007\u0006(5g[I\b.g^N\t)\"S"));
            return 1;
        }
        return this.getFontMetrics(font).stringWidth(s);
    }
    
    public int d(final String s, final Font font) {
        return this.getFontMetrics(font).stringWidth(s);
    }
    
    public int[] a(final String s, final Font font, final int n, int n2, final int n3, final int n4) {
        int n5 = n3;
        int n6 = n4;
        final int c = this.c(s, font);
        if (n2 < 0) {
            n2 += 360;
        }
        else if (n2 > 360) {
            n2 -= 360;
        }
        if (n2 > 250 && n2 < 290) {
            n5 -= c / 2;
        }
        else if (n2 > 70 && n2 < 110) {
            n5 -= c / 2;
            n6 += n;
        }
        else if (n2 > 160 && n2 < 200) {
            n5 -= c;
            n6 += n / 2;
        }
        else if (n2 > 340 && n2 < 20) {
            n6 += n / 2;
        }
        else if (n2 >= 200 && n2 <= 250) {
            n5 -= c;
        }
        else if (n2 < 290 || n2 > 340) {
            if (n2 >= 20 && n2 <= 70) {
                n6 += n;
            }
            else if (n2 >= 110 && n2 <= 160) {
                n5 -= c;
                n6 += n;
            }
        }
        return new int[] { n5, n6 };
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final int n3) {
        switch (n) {
            case 1: {
                graphics.fillRect(n2 + 3, n3, 2, 2);
                graphics.fillRect(n2 + 2, n3 + 2, 4, 2);
                graphics.fillRect(n2 + 1, n3 + 4, 6, 2);
                graphics.fillRect(n2, n3 + 6, 8, 2);
            }
            case 2: {
                graphics.fillRect(n2, n3 + 3, 8, 2);
                graphics.fillRect(n2 + 3, n3, 2, 8);
            }
            case 3: {
                graphics.fillOval(n2, n3, 8, 8);
            }
            case 4: {
                graphics.drawLine(n2, n3, n2 + 1, n3);
                graphics.drawLine(n2 + 6, n3, n2 + 7, n3);
                graphics.drawLine(n2 + 1, n3 + 1, n2 + 2, n3 + 1);
                graphics.drawLine(n2 + 5, n3 + 1, n2 + 6, n3 + 1);
                graphics.drawLine(n2 + 2, n3 + 2, n2 + 5, n3 + 2);
                graphics.fillRect(n2 + 3, n3 + 3, 2, 2);
                graphics.drawLine(n2 + 2, n3 + 5, n2 + 5, n3 + 5);
                graphics.drawLine(n2 + 1, n3 + 6, n2 + 2, n3 + 6);
                graphics.drawLine(n2 + 5, n3 + 6, n2 + 6, n3 + 6);
                graphics.drawLine(n2, n3 + 7, n2 + 1, n3 + 7);
                graphics.drawLine(n2 + 6, n3 + 7, n2 + 7, n3 + 7);
            }
            case 5: {
                graphics.fillRect(n2 + 3, n3 + 6, 2, 2);
                graphics.fillRect(n2 + 2, n3 + 4, 4, 2);
                graphics.fillRect(n2 + 1, n3 + 2, 6, 2);
                graphics.fillRect(n2, n3, 8, 2);
            }
            default: {}
        }
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
            final Font font = new Font(e("\"?!\\S\n."), 0, 12);
            graphics.setColor(Color.black);
            graphics.setFont(font);
            graphics.drawString(s, n + this.a(s, font, n3), n2 + 15);
        }
    }
    
    public int a(final Graphics graphics, final boolean b, final String s, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final Color color = new Color(192, 192, 192);
        final Color color2 = new Color(49, 106, 197);
        final Color color3 = new Color(128, 128, 128);
        int n7 = 99;
        graphics.setColor(Color.black);
        graphics.fillRect(n, n2, n3, n4);
        graphics.setColor(color);
        graphics.fillRect(n + 1, n2 + 1, n3 - 2, n4 - 2);
        graphics.setFont(new Font(e("\"?!\\S\n."), 0, 12));
        final int n8 = n + 6;
        final int n9 = 15;
        graphics.setColor(color3);
        if (b) {
            graphics.drawLine(n8, n2 + n9 + 5, n8 + n3 - 10, n2 + n9 + 5);
        }
        else {
            graphics.drawLine(n8, n2 + n9 * 5 + 5, n8 + n3 - 10, n2 + n9 * 5 + 5);
        }
        graphics.setColor(Color.black);
        if (b) {
            final String[] array = { e("*3$XH\u0015?#\u001dR\tti\u0013"), e("'8(HRF") + s };
            for (int i = 0; i < 2; ++i) {
                int n10 = n9 * (i + 1);
                if (i == 1) {
                    n10 += 5;
                }
                if (n5 > 0 && n5 < n3 && n6 > n10 - 12 && n6 < n10 + 3) {
                    graphics.setColor(color2);
                    graphics.fillRect(n + 1, n2 + n10 - 12, n3 - 2, 15);
                    graphics.setColor(Color.white);
                    if (i == 1) {
                        n7 = 5;
                    }
                    else {
                        n7 = i;
                    }
                }
                else {
                    graphics.setColor(Color.black);
                }
                graphics.drawString(array[i], n8, n2 + n10);
            }
        }
        else {
            final String[] array2 = { e("*3$XH\u0015?#\u001dR\tti\u0013"), e("%2&OR13=\\T\u0002"), e("\"5$HK\u000343\\R\u000f5)"), e("5/7MI\u0014."), e("%2\"^MF<(O\u00063*#\\R\u0003)"), e("'8(HRF") + s };
            for (int j = 0; j < 6; ++j) {
                int n11 = n9 * (j + 1);
                if (j == 5) {
                    n11 += 5;
                }
                if (n5 > 0 && n5 < n3 && n6 > n11 - 12 && n6 < n11 + 3) {
                    graphics.setColor(color2);
                    graphics.fillRect(n + 1, n2 + n11 - 12, n3 - 2, 15);
                    graphics.setColor(Color.white);
                    n7 = j;
                }
                else {
                    graphics.setColor(Color.black);
                }
                graphics.drawString(array2[j], n8, n2 + n11);
            }
        }
        return n7;
    }
    
    public void a(int n, int n2, final int n3, final int n4, final Graphics graphics, final o o, final int n5, final Color color, final Color color2) {
        final int n6 = -3;
        final int n7 = -24;
        final String k = o.k;
        if (k != null) {
            graphics.setFont(new Font(this.b, 0, n5));
            final FontMetrics fontMetrics = this.getFontMetrics(new Font(this.b, 0, n5));
            if (k.indexOf(e("E85")) == -1) {
                final int stringWidth = fontMetrics.stringWidth(k);
                if (n - n6 + stringWidth + 6 > n3) {
                    n = n3 - (n6 + stringWidth + 17);
                }
                if (n2 - n7 + n5 + 20 > n4) {
                    n += 20;
                    n2 -= 40;
                    if (n - n6 + stringWidth + 6 > n3) {
                        n = n3 - (n6 + stringWidth + 17);
                        n2 -= 22;
                    }
                }
                graphics.setColor(color);
                graphics.fillRect(n - n6, n2 - n7, stringWidth + 5, n5 + 2);
                graphics.setColor(Color.black);
                graphics.drawRect(n - n6 - 1, n2 - n7 - 1, stringWidth + 6, n5 + 3);
                graphics.drawString(k, n - n6 + 2, n2 - n7 + n5 - 1);
                return;
            }
            int n8 = 0;
            int stringWidth2 = 0;
            final String[] a = a(k, e("E85\u001e"));
            for (int n9 = 0; n9 < 25 && a[n9] != null; ++n9) {
                if (fontMetrics.stringWidth(a[n9]) > stringWidth2) {
                    stringWidth2 = fontMetrics.stringWidth(a[n9]);
                }
                ++n8;
            }
            final int n10 = stringWidth2;
            final int n11 = (n5 + 2) * n8;
            if (n - n6 + n10 + 6 > n3) {
                n = n3 - (n6 + n10 + 17);
            }
            if (n2 - n7 + n11 + 20 > n4) {
                n += 20;
                n2 -= 40;
                if (n - n6 + n10 + 6 > n3) {
                    n = n3 - (n6 + n10 + 17);
                    n2 -= 22;
                }
            }
            graphics.setColor(color);
            graphics.fillRect(n - n6, n2 - n7, n10 + 5, n11 + 2);
            graphics.setColor(Color.black);
            graphics.drawRect(n - n6 - 1, n2 - n7 - 1, n10 + 6, n11 + 3);
            for (int i = 0; i < n8; ++i) {
                graphics.drawString(a[i], n - n6 + 2, n2 - n7 - 1 + (i + 1) * (n5 + 2));
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
    
    public boolean a(final n n, final int n2, final int n3, final int n4) {
        final int[] array = new int[n2];
        boolean b = false;
        final Font font = new Font(this.b, 0, n4);
        for (int i = 0; i < n2; ++i) {
            if (n.b[i] != null) {
                array[i] = this.c(n.b[i], font);
            }
            else {
                array[i] = 0;
            }
        }
        for (int j = 0; j < n2; ++j) {
            if (array[j] >= n3) {
                for (int n5 = (int)Math.ceil(array[j] / (n3 * 2)), k = 1; k <= n5; ++k) {
                    if (j - k >= 0 && n.b[j - k] != null && n.b[j - k].length() > 0) {
                        b = true;
                        break;
                    }
                    if (j + k < n2 && n.b[j + k] != null && n.b[j + k].length() > 0) {
                        b = true;
                        break;
                    }
                }
            }
        }
        return b;
    }
    
    public boolean a(final p p4, final int n, final int n2, final int n3) {
        final int[] array = new int[n];
        boolean b = false;
        final Font font = new Font(this.b, 0, n3);
        for (int i = 0; i < n; ++i) {
            if (p4.a[i] != null && p4.a[i].f != null) {
                array[i] = this.c(p4.a[i].f, font);
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
    
    public boolean b(final n n, final int n2, final int n3, final int n4) {
        final int[] array = new int[n2];
        boolean b = false;
        final Font font = new Font(this.b, 0, n4);
        for (int i = 0; i < n2; ++i) {
            if (n.b[i] != null) {
                array[i] = this.c(n.b[i], font);
            }
            else {
                array[i] = 0;
            }
        }
        for (int j = 0; j < n2; ++j) {
            if (array[j] >= n3) {
                for (int n5 = (int)Math.ceil(array[j] / (n3 * 2)), k = 2; k <= n5; ++k) {
                    if (k % 1 == 0) {
                        if (j - k >= 0 && n.b[j - k] != null && n.b[j - k].length() > 0) {
                            b = true;
                            break;
                        }
                        if (j + k < n2 && n.b[j + k] != null && n.b[j + k].length() > 0) {
                            b = true;
                            break;
                        }
                    }
                }
            }
        }
        return b;
    }
    
    public boolean b(final p p4, final int n, final int n2, final int n3) {
        final int[] array = new int[n];
        boolean b = false;
        final Font font = new Font(this.b, 0, n3);
        for (int i = 0; i < n; ++i) {
            if (p4.a[i].f != null) {
                array[i] = this.c(p4.a[i].f, font);
            }
            else {
                array[i] = 0;
            }
        }
        for (int j = 0; j < n; ++j) {
            if (array[j] >= n2) {
                for (int n4 = (int)Math.ceil(array[j] / (n2 * 2)), k = 2; k <= n4; ++k) {
                    if (k % 1 == 0) {
                        if (j - k >= 0 && p4.a[j - k].f != null && p4.a[j - k].f.length() > 0) {
                            b = true;
                            break;
                        }
                        if (j + k < n && p4.a[j + k] != null && p4.a[j + k].f.length() > 0) {
                            b = true;
                            break;
                        }
                    }
                }
            }
        }
        return b;
    }
    
    public int a(final String[] array, final int n, final int n2, final int n3, final int n4, final Font font) {
        int n5 = 0;
        int n6 = 1;
        boolean b = false;
        final int[][] array2 = new int[n][2];
        if (n < 2) {
            return 1;
        }
        final int[] array3 = new int[n];
        for (int i = 0; i < n; ++i) {
            if (array[i] != null) {
                array3[i] = this.c(array[i], font);
                if (array3[i] > n5) {
                    n5 = array3[i];
                }
            }
            else {
                array3[i] = 0;
            }
        }
        if (n2 > n5) {
            return 1;
        }
        for (int j = 0; j < n; ++j) {
            if (array3[j] != 0) {
                array2[j][0] = j * n2 - array3[j] / 2 - 2;
                array2[j][1] = j * n2 + array3[j] / 2 + 2;
            }
            else {
                array2[j][0] = -9999;
                array2[j][1] = -9999;
            }
        }
        for (int k = 0; k < n; ++k) {
            if (array2[k][0] != -9999 && (array2[k][0] + n3 < 0 || array2[k][1] > n4 - n3)) {
                b = true;
                break;
            }
        }
        if (b) {
            return 3;
        }
        for (int l = 0; l < n; ++l) {
            if (array2[l][0] != -9999) {
                int n7 = l - 1;
                while (n7 > 0) {
                    if (array2[n7][0] != -9999) {
                        if (array2[l][0] <= array2[n7][1]) {
                            n6 = 0;
                            break;
                        }
                        break;
                    }
                    else {
                        --n7;
                    }
                }
                if (n6 == 0) {
                    break;
                }
                int n8 = l + 1;
                while (n8 < n) {
                    if (array2[n8][0] != -9999) {
                        if (array2[l][1] >= array2[n8][0]) {
                            n6 = 0;
                            break;
                        }
                        break;
                    }
                    else {
                        ++n8;
                    }
                }
                if (n6 == 0) {
                    break;
                }
            }
        }
        if (n6 != 0) {
            return 1;
        }
        int n9 = 1;
        for (int n10 = 0; n10 < n; ++n10) {
            if (array2[n10][0] != -9999) {
                int n11 = n10 - 2;
                while (n11 > 0) {
                    if (array2[n11][0] != -9999) {
                        if (array2[n10][0] <= array2[n11][1]) {
                            n9 = 0;
                            break;
                        }
                        break;
                    }
                    else {
                        n11 -= 2;
                    }
                }
                if (n9 == 0) {
                    break;
                }
                int n12 = n10 + 2;
                while (n12 < n) {
                    if (array2[n12][0] != -9999) {
                        if (array2[n10][1] >= array2[n12][0]) {
                            n9 = 0;
                            break;
                        }
                        break;
                    }
                    else {
                        n12 += 2;
                    }
                }
                if (n9 == 0) {
                    break;
                }
            }
        }
        if (n9 != 0) {
            return 2;
        }
        return 3;
    }
    
    public int a(final String[] array, final int n, final Font font) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            if (array[i] != null) {
                final int c = this.c(array[i], font);
                if (c > n2) {
                    n2 = c;
                }
            }
        }
        return n2;
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
        String[] array = new String[25];
        int n = 0;
        int n2 = 0;
        int n3 = s.indexOf(s2, n2);
        int n4 = 25;
        if (s.indexOf(s2) == -1) {
            return new String[] { s };
        }
        do {
            if (n >= n4 - 1) {
                n4 += 50;
                array = (String[])a(array, n4);
            }
            array[n] = s.substring(n2, n3);
            ++n;
            n2 = n3 + s2.length();
            n3 = s.indexOf(s2, n2);
        } while (n3 != -1 && n2 < s.length());
        array[n] = s.substring(n2);
        return array;
    }
    
    private static Object a(final Object o, final int n) {
        final int length = Array.getLength(o);
        final Object instance = Array.newInstance(o.getClass().getComponentType(), n);
        final int min = Math.min(length, n);
        if (min > 0) {
            System.arraycopy(o, 0, instance, 0, min);
        }
        return instance;
    }
    
    public void a(final Graphics graphics, final b b) {
        final Font font = new Font(this.b, 1, b.c + 4);
        graphics.setFont(font);
        graphics.setColor(b.h);
        if (b.P != null) {
            graphics.drawString(b.P, this.a(b.P, font, b.d), b.c + 10);
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
                    if (string.startsWith(e("\u000e.3M\u001cIu"))) {
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
    
    public double i(final double n) {
        return 3.141592653589793 * n / 180.0;
    }
    
    public NumberFormat d(final String s) {
        if (s.compareTo(e("#\u0014\u0000qo5\u0012")) == 0) {
            return NumberFormat.getInstance(Locale.ENGLISH);
        }
        return NumberFormat.getInstance();
    }
    
    public boolean b(final String s, final String s2) {
        return s.compareTo(s2) == 0;
    }
    
    private static String e(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'f';
                    break;
                }
                case 1: {
                    c2 = 'Z';
                    break;
                }
                case 2: {
                    c2 = 'G';
                    break;
                }
                case 3: {
                    c2 = '=';
                    break;
                }
                default: {
                    c2 = '&';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
