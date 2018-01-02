import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.awt.Font;
import wordfall.WordFallApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ae
{
    public WordFallApplet a;
    public r b;
    public Font c;
    public PixelGrabber d;
    public int e;
    
    public int a(final String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 >= '\0' && char1 < '\u00ff') {
                n += this.b.a1[char1] - 3;
            }
        }
        return n + 2;
    }
    
    public ae(final WordFallApplet a) {
        this.e = 0;
        this.a = a;
        this.b = this.a.f;
        this.c = this.a.a("Serif", 1, 16);
        if (this.c == null) {
            this.a.a("loading", "CreateSysFont failed");
        }
        final Graphics graphics = this.b.w.getGraphics();
        graphics.setFont(this.c);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        this.b.a3 = fontMetrics.getHeight() + 4;
        this.b.a4 = fontMetrics.getAscent() + 2;
        final int length = this.b.a0.length();
        this.e = 0;
        for (int i = 0; i < length; ++i) {
            final char char1 = this.b.a0.charAt(i);
            this.b.a2[char1] = this.e;
            final int e = this.e;
            final int[] a2 = this.b.a1;
            final char c = char1;
            final int n = fontMetrics.charWidth(char1) + 5;
            a2[c] = n;
            this.e = e + n;
        }
        final Image image = this.a.createImage(this.e, this.b.a3);
        graphics.dispose();
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(new Color(0, 0, 0));
        graphics2.fillRect(0, 0, this.e, this.b.a3);
        graphics2.setFont(this.c);
        int n2 = 2;
        for (int j = 0; j < length; ++j) {
            final char char2 = this.b.a0.charAt(j);
            new StringBuffer().append("").append(char2).toString();
            final int n3 = n2;
            final int n4 = fontMetrics.getAscent() + 2;
            final String string = "" + this.b.a0.charAt(j);
            graphics2.setColor(new Color(128, 128, 128));
            int n5 = -2;
            do {
                int n6 = -2;
                do {
                    graphics2.drawString(string, n3 + n5, n4 + n6);
                } while (++n6 <= 2);
            } while (++n5 <= 2);
            graphics2.setColor(new Color(255, 255, 255));
            graphics2.drawString(string, n3, n4);
            n2 += this.b.a1[char2];
        }
        graphics2.dispose();
        this.d = new PixelGrabber(image, 0, 0, this.e, this.b.a3, true);
        try {
            this.d.grabPixels();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.a(0.1);
    }
    
    public void a(final double n) {
        final Color color = new Color(0, 0, 0);
        final int[] array = (int[])this.d.getPixels();
        int n2 = 0;
        (this.b.a5 = new j()).a(this.e, this.b.a3);
        for (int i = 0; i < this.b.a3; ++i) {
            final Color color2 = new Color(this.b.a(new double[] { n + i / this.b.a3 / 3.0, 1.0, 0.75 })[0]);
            for (int j = 0; j < this.e; ++j) {
                double n3 = (Math.max(array[n2] & 0xFF, array[n2] >> 8 & 0xFF) - 128) / 127.0;
                if (n3 < -0.5) {
                    this.b.a5.c[n2] = 0;
                }
                else {
                    if (n3 < 0.0) {
                        n3 = 0.0;
                    }
                    this.b.a5.c[n2] = new Color((int)(n3 * color2.getRed() + (1.0 - n3) * color.getRed()), (int)(n3 * color2.getGreen() + (1.0 - n3) * color.getGreen()), (int)(n3 * color2.getBlue() + (1.0 - n3) * color.getBlue())).getRGB();
                }
                ++n2;
            }
        }
        this.b.a5.g();
    }
    
    public void a(final n n, final String s, final int n2, final int n3, final int n4) {
        int n5 = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 >= '\0' && char1 < '\u00ff') {
                final int n6 = this.b.a2[char1];
                final int n7 = this.b.a1[char1];
                final n n8 = new n(n);
                n8.d = true;
                n8.e = new f(255, 255, 255, n4);
                n8.a(n2 + n5, n3 - this.b.a4, n7, this.b.a3);
                n8.a(this.b.a5, n2 + n5 - n6, n3 - this.b.a4);
                n8.d = false;
                n5 += n7 - 3;
            }
        }
    }
}
