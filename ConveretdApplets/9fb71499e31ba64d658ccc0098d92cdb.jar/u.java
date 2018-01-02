import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.PixelGrabber;
import java.awt.Color;
import java.awt.Font;
import sexy.gui.SexyApplet;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public class u implements Cloneable
{
    public j a;
    public int[] b;
    public int[] c;
    public int d;
    public int e;
    
    public u() {
        this.b = new int[256];
        this.c = new int[256];
    }
    
    public u(final j a, final String s) {
        this.b = new int[256];
        this.c = new int[256];
        this.a = a;
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        this.c[32] = new Integer(stringTokenizer.nextToken());
        this.b[32] = -1;
        this.e = new Integer(stringTokenizer.nextToken());
        this.d = a.c();
        while (stringTokenizer.hasMoreElements()) {
            final String nextToken = stringTokenizer.nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            final char char1 = nextToken.charAt(0);
            final int intValue = new Integer(nextToken2);
            this.b[char1] = n;
            this.c[char1] = intValue;
            n += intValue;
        }
    }
    
    public u(final SexyApplet sexyApplet, final Font font) {
        this.b = new int[256];
        this.c = new int[256];
        final char c = ' ';
        final char c2 = sexyApplet.g ? '\u00ff' : '~';
        final Graphics graphics = sexyApplet.d.w.getGraphics();
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        this.d = fontMetrics.getHeight();
        this.e = fontMetrics.getAscent();
        int n = 0;
        int n2 = 0;
        for (char c3 = c; c3 <= c2; ++c3) {
            this.b[c3] = n2;
            this.c[c3] = fontMetrics.charWidth(c3);
            if (this.c[c3] > n) {
                n = this.c[c3];
            }
            n2 += this.c[c3] + 2;
        }
        final Image image = sexyApplet.createImage(n, this.d);
        graphics.dispose();
        final Graphics graphics2 = image.getGraphics();
        final int[] array = new int[n2 * this.d];
        for (char c4 = c; c4 < c2; ++c4) {
            graphics2.setFont(font);
            graphics2.setColor(new Color(0, 0, 0));
            graphics2.fillRect(0, 0, n, this.d);
            graphics2.setColor(new Color(255, 255, 255));
            graphics2.drawString("" + c4, 0, this.e);
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n, this.d, true);
            try {
                pixelGrabber.grabPixels();
            }
            catch (Exception ex) {}
            final int[] array2 = (int[])pixelGrabber.getPixels();
            for (int i = 0; i < this.d; ++i) {
                int n3 = i * n;
                int n4 = i * n2 + this.b[c4];
                for (int j = 0; j < this.c[c4]; ++j) {
                    array[n4++] = (Math.max(array2[n3] & 0xFF, array2[n3] >> 8 & 0xFF) << 24 | 0xFFFFFF);
                    ++n3;
                }
            }
        }
        graphics2.dispose();
        this.a = new j(array, n2, this.d);
    }
    
    public synchronized Object clone() throws CloneNotSupportedException {
        final u u = new u();
        u.a = this.a;
        for (int i = 0; i < this.b.length; ++i) {
            u.b[i] = this.b[i];
        }
        for (int j = 0; j < this.c.length; ++j) {
            u.c[j] = this.c[j];
        }
        u.d = this.d;
        u.e = this.e;
        return u;
    }
    
    public int a(final String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            n += this.c[s.charAt(i) & '\u00ff'];
        }
        return n;
    }
}
