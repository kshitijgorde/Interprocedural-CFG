import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.applet.Applet;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class WvLocaleChecker extends Canvas
{
    private boolean isEnglish;
    private boolean isAllowMultibyteCode;
    private String fontName;
    
    public boolean isEnglish() {
        return this.isEnglish;
    }
    
    public boolean isAllowMultibyteCode() {
        return this.isAllowMultibyteCode;
    }
    
    public String getFontName() {
        return this.fontName;
    }
    
    public WvLocaleChecker(final Applet applet) {
        this.isEnglish = true;
        this.resize(0, 0);
        applet.add(this);
        String locale = applet.getParameter("locale");
        if (locale == null) {
            locale = "english";
        }
        this.fontName = this.getFont().getName();
        this.isEnglish = true;
        this.isAllowMultibyteCode = false;
        if ("japanese".equalsIgnoreCase(locale)) {
            final byte byte0 = 30;
            final byte byte2 = 30;
            final Image image = this.createImage(byte0, byte2);
            final Graphics g = image.getGraphics();
            g.setColor(Color.black);
            g.fillRect(0, 0, byte0, byte2);
            g.setColor(Color.white);
            g.setFont(new Font(this.fontName, 0, 20));
            final FontMetrics fontmetrics = g.getFontMetrics();
            final String s1 = "\u25ce";
            final int i = fontmetrics.getAscent();
            final int j = fontmetrics.getDescent();
            final int k = fontmetrics.stringWidth(s1);
            g.drawString(s1, (byte0 - k) / 2, (byte2 - j) / 2 + i / 2);
            final int[] ai = new int[byte0 * byte2];
            final PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, byte0, byte2, ai, 0, byte0);
            try {
                pixelgrabber.grabPixels();
            }
            catch (InterruptedException ex) {}
            int l = -1;
            int i2 = -1;
            int i3 = 0;
            int j2 = 0;
            int k2 = 0;
            int l2 = ai[0];
            while (k2 < byte2) {
                final int k3 = byte2 * k2 + byte0 / 2;
                if (ai[k3] != l2) {
                    ++j2;
                    if (l < 0) {
                        l = k2;
                    }
                    i2 = k2;
                }
                l2 = ai[k3];
                ++k2;
            }
            int j3 = (l + i2) / 2;
            j3 = Math.max(0, Math.min(byte2, j3));
            k2 = 0;
            l2 = ai[0];
            while (k2 < byte0) {
                final int l3 = byte0 * j3 + k2;
                if (ai[l3] != l2) {
                    ++i3;
                }
                l2 = ai[l3];
                ++k2;
            }
            if (i3 == 8 && j2 == 8) {
                this.isEnglish = false;
                this.isAllowMultibyteCode = true;
            }
        }
        else if (!"english".equalsIgnoreCase(locale)) {
            this.isAllowMultibyteCode = true;
        }
        applet.remove(this);
    }
}
