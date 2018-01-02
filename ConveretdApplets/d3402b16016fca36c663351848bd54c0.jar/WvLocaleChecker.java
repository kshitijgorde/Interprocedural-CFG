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
        this.resize(0, 0);
        applet.add(this);
        String parameter = applet.getParameter("locale");
        if (parameter == null) {
            parameter = "japanese";
        }
        this.fontName = this.getFont().getName();
        this.isEnglish = true;
        this.isAllowMultibyteCode = false;
        if ("japanese".equalsIgnoreCase(parameter)) {
            final int n = 30;
            final int n2 = 30;
            final Image image = this.createImage(n, n2);
            final Graphics graphics = image.getGraphics();
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, n, n2);
            graphics.setColor(Color.white);
            graphics.setFont(new Font(this.fontName, 0, 20));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final String s = "\u25ce";
            graphics.drawString(s, (n - fontMetrics.stringWidth(s)) / 2, (n2 - fontMetrics.getDescent()) / 2 + fontMetrics.getAscent() / 2);
            final int[] array = new int[n * n2];
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n, n2, array, 0, n);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {}
            int n3 = -1;
            int n4 = -1;
            int n5 = 0;
            int n6 = 0;
            int i = 0;
            int n7 = array[0];
            while (i < n2) {
                final int n8 = n2 * i + n / 2;
                if (array[n8] != n7) {
                    ++n6;
                    if (n3 < 0) {
                        n3 = i;
                    }
                    n4 = i;
                }
                n7 = array[n8];
                ++i;
            }
            final int max = Math.max(0, Math.min(n2, (n3 + n4) / 2));
            int j = 0;
            int n9 = array[0];
            while (j < n) {
                final int n10 = n * max + j;
                if (array[n10] != n9) {
                    ++n5;
                }
                n9 = array[n10];
                ++j;
            }
            if (n5 == 8 && n6 == 8) {
                this.isEnglish = false;
                this.isAllowMultibyteCode = true;
            }
        }
        else if (!"english".equalsIgnoreCase(parameter)) {
            this.isAllowMultibyteCode = true;
        }
        applet.remove(this);
    }
}
